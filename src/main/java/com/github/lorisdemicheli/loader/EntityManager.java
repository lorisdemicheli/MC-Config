package com.github.lorisdemicheli.loader;

public interface EntityManager {
	public boolean save(Object obj);
	public <T> T load(Class<T> clazz, Object...id);
}
