package com.jq.child.util;

import java.util.Properties;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterClient {
	private static Logger logger = Logger.getLogger(JedisClusterClient.class);
	
	//集群结点
	
	private static Properties properties;
	
	private static JedisPool pool = null;
	
	static{
		JedisPoolConfig config=new JedisPoolConfig();
		try {
			properties = PropertiesUtil.getInstance("redis.properties");
			config.setMaxTotal(Integer.valueOf(properties.getProperty("MaxTotal")));
			config.setMaxIdle(Integer.valueOf(properties.getProperty("MaxIdle")));
			config.setMinIdle(Integer.valueOf(properties.getProperty("MinIdle")));
			config.setMaxWaitMillis(Integer.valueOf(properties.getProperty("MaxWaitMillis")));
			config.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("TestOnBorrow")));
			config.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("TestOnReturn")));
			config.setTestWhileIdle(Boolean.parseBoolean(properties.getProperty("TestWhileIdle")));
		} catch (Exception e) {
			//初始化连接池配置失效
			logger.error("初始化连接池配置失效"+e.getMessage(),e);
		}
		pool = new JedisPool (config,properties.getProperty("m_r_ip"),Integer.valueOf(properties.getProperty("m_r_port")),10000);
	}
	    
	public static JedisPool getPool(){
		return pool;
	}
	public static Jedis getJedis() {
		return JedisClusterClient.getPool().getResource();
	}
	    
	    /**
		 * 归还Jedis对象
		 * 
		 * @param jedis
		 */
	public static void recycleJedis(Jedis jedis) {
		if (pool != null) {
			pool.returnResource(jedis);
		}
	}
}
