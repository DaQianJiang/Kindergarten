package com.jq.child.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	private volatile static PropertiesUtil newInstance = null;
	private static Map<String,Properties> propertiesMap ;
	
	
	public static Properties getInstance(String path) throws Exception{
		if(newInstance == null){
			synchronized(PropertiesUtil.class){
				if(newInstance == null){
					newInstance = new PropertiesUtil(path);
				}
			}
		} else {
			if(newInstance.getPropertiesMap().get(path) == null){
				synchronized(PropertiesUtil.class){
					if(newInstance.getPropertiesMap().get(path) == null){
						newInstance.loadProperties(path);
					}
				}
			}
		}
		
		return propertiesMap.get(path);
	}
	
	
	private PropertiesUtil(String path) throws Exception{
		this.loadProperties(path);
	}
	
	private void loadProperties(String path) throws Exception{
		Properties prop =  new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream(path);
		try{
			prop.load(in);
			in.close();
			
			if(propertiesMap == null || propertiesMap.isEmpty()){
				propertiesMap = new HashMap<String, Properties>();
			}
			propertiesMap.put(path, prop);
		} catch(IOException e){
			throw new Exception();
		}
	}

	private Map<String, Properties> getPropertiesMap() {
		return propertiesMap;
	}

	public static void main(String[] args) {
		try {
			Properties redis = PropertiesUtil.getInstance("redis.properties");
			System.out.println(redis);
			System.out.println(newInstance);
			System.out.println(propertiesMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
