package com.dao.jdbc;

public class SingletonHelper {
	private static final Object obj = new Object();
	
	public static <T> T getInstance(T instance, Class<T> classType) {
		if(instance == null) {
			synchronized (obj) {
				if(instance == null) {
					try {
						return instance = classType.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return instance;
	}
}
