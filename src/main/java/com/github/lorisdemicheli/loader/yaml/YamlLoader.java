package com.github.lorisdemicheli.loader.yaml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.github.lorisdemicheli.loader.Convert;
import com.github.lorisdemicheli.loader.EntityManager;
import com.github.lorisdemicheli.loader.yaml.annotations.YmlFile;

public class YamlLoader implements EntityManager {

	private Plugin plugin;

	public YamlLoader(Plugin plugin) {
		this.plugin = plugin;
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}
	}

	@Override
	public boolean save(Object obj) {
		YmlFile path = obj.getClass().getAnnotation(YmlFile.class);
		File file;
		String fileName = null;
		if (Convert.isMultiple(obj.getClass())) {
			StringBuilder name = new StringBuilder();
			for (Field field : Convert.multipleFields(obj.getClass())) {
				field.setAccessible(true);
				try {
					name.append(field.get(obj).toString());
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			name.append(".yml");
			fileName = name.toString();
		} else {
			if (path.fileName().trim().length() > 0) {
				fileName = path.fileName() + ".yml";
			} else {
				fileName = obj.getClass().getSimpleName() + ".yml";
			}
		}
		if (path.path().trim().length() < 1) {
			file = new File(plugin.getDataFolder(), fileName);
		} else {
			File dir = new File(plugin.getDataFolder(),path.path());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			file = new File(dir, fileName);
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				plugin.getLogger().log(Level.WARNING, "Can not create file " + file.getName(), e);
				return false;
			}
		}
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		try {
			conf.createSection(Convert.className(obj.getClass()), save(conf.getValues(true), obj));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			plugin.getLogger().log(Level.WARNING, e.getClass().getName(), e);
			return false;
		}
		try {
			conf.save(file);
			return true;
		} catch (IOException e) {
			plugin.getLogger().log(Level.WARNING, "Can not save file " + file.getName(), e);
			return false;
		}
	}

	@Override
	public <T> T load(Class<T> clazz, Object... id) {
		YmlFile path = clazz.getAnnotation(YmlFile.class);
		File file;
		String fileName;
		if (id.length > 0 && Convert.isMultiple(clazz)) {
			StringBuilder name = new StringBuilder();
			for (Object obj : id) {
				name.append(obj.toString());
			}
			name.append(".yml");
			fileName = name.toString();
		} else {
			if (path.fileName().trim().length() > 0) {
				fileName = path.fileName() + ".yml";
			} else {
				fileName = clazz.getSimpleName() + ".yml";
			}
		}
		if (path.path().trim().length() < 1) {
			file = new File(plugin.getDataFolder(), fileName);
		} else {
			file = new File(new File(plugin.getDataFolder(),path.path()), fileName);
		}
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);;
		try {
			return load(null, conf.getValues(true), clazz);
		} catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			plugin.getLogger().log(Level.WARNING, e.getClass().getName(), e);
			return null;
		}
	}

	private Map<String, Object> save(Map<String, Object> map, Object toSave)
			throws IllegalArgumentException, IllegalAccessException {
		for (Field field : toSave.getClass().getDeclaredFields()) {
			if (Convert.processField(field)) {
				String pathName = Convert.fieldName(field);
				field.setAccessible(true);
				Object valueToSave = field.get(toSave);
				if (Convert.isCustomClass(field.getType())) {
					map.put(pathName, save(map, valueToSave));
				} else if (Convert.isCustomList(field)) {
					List<?> list = (List<?>) field.get(toSave);
					List<Map<?, ?>> mapToSave = new ArrayList<Map<?, ?>>();
					for (Object obj : list) {
						mapToSave.add(save(new HashMap<>(), obj));
					}
					map.put(pathName, mapToSave);
				} else {
					map.put(pathName, valueToSave);
				}
			}
		}
		return map;
	}

	private <T> T load(String root, Map<String, Object> map, Class<T> clazz)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		T instance = clazz.newInstance();
		if (root == null) {
			root = Convert.className(clazz) + ".";
		}
		for (Field field : clazz.getDeclaredFields()) {
			if (Convert.processField(field)) {
				Class<?> classField = field.getType();
				String pathName = root + Convert.fieldName(field);
				field.setAccessible(true);
				
				if (Convert.isCustomClass(classField)) {
					field.set(instance, load(pathName, map, classField));
				} else if (Convert.isCustomList(field)) {
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> list = (List<Map<String, Object>>) map.get(pathName);
					List<Object> toLoad = new ArrayList<>();
					ParameterizedType genericType = (ParameterizedType) field.getGenericType();
					Class<?> elementType = (Class<?>) genericType.getActualTypeArguments()[0];
					for (Map<String, Object> mapList : list) {
						toLoad.add(load("", mapList, elementType));
					}
					field.set(instance, toLoad);

				} else {
					field.set(instance, map.get(pathName));
				}
			}
		}
		return instance;
	}
}
