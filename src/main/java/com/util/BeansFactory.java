package com.util;

import java.io.FileInputStream;
import java.util.*;

public class BeansFactory {
	private static final Map<String, Object> BEANS_POOL_MAP = new HashMap<String, Object>();
	static {
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"com/util/ModelBeans"; 
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(path));
			for(Map.Entry<Object, Object> entry : properties.entrySet()) {
				String keyName = entry.getKey().toString(); 
				Class<?> clazz = Class.forName(entry.getValue().toString());
				BEANS_POOL_MAP.put(keyName, clazz.getConstructor().newInstance());
//				System.out.println("key "+keyName+" value "+entry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	@SuppressWarnings("unchecked")
	public static <T>T getInstance(String beanName,Class<T> type){
		return (T)BEANS_POOL_MAP.get(beanName);
	}
}
