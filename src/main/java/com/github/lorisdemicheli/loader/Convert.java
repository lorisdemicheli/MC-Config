package com.github.lorisdemicheli.loader;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.lorisdemicheli.loader.yaml.annotations.Column;
import com.github.lorisdemicheli.loader.yaml.annotations.Entity;
import com.github.lorisdemicheli.loader.yaml.annotations.Id;
import com.github.lorisdemicheli.loader.yaml.annotations.IgnoreSave;

public class Convert {
	public static List<Field> multipleFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(Id.class) != null) {
				fields.add(f);
			}
		}
		return fields;
	}

	public static String fieldName(Field f) {
		Column ann = f.getAnnotation(Column.class);
		if (ann != null) {
			if (ann.name().length() > 0) {
				return ann.name();
			} else {
				return f.getName();
			}
		} else {
			return null;
		}
	}
	
	public static String className(Class<?> clazz) {
		Entity ann = clazz.getAnnotation(Entity.class);
		if (ann != null) {
			String name = ann.name();
			if (name.trim().length() > 0) {
				return name;
			} else {
				return clazz.getSimpleName();
			}
		} else {
			return null;
		}
	}
	
	public static boolean isCustomClass(Class<?> clazz) {
		return clazz.getAnnotation(Entity.class) != null;
	}
	
	public static boolean isMultiple(Class<?> clazz) {
		for(Field field : clazz.getDeclaredFields()) {
			if(field.getAnnotation(Id.class)!=null) {
				return true;
			}
		}
		return false;
	}

	public static boolean isCustomList(Field f) {
		if (f.getType().isInstance(Collections.emptyList())) {
			ParameterizedType genericType = (ParameterizedType) f.getGenericType();
			Class<?> elementType = (Class<?>) genericType.getActualTypeArguments()[0];
			return elementType.getAnnotation(Entity.class) != null;
		} else {
			return false;
		}
	}
	
	public static boolean processField(Field field) {
		return field.getAnnotation(IgnoreSave.class) == null && field.getAnnotation(Column.class) != null;
	}

	
}
