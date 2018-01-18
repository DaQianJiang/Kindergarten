package com.jq.child.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisUtil {
	private static Logger logger = Logger.getLogger(JedisUtil.class);
	
	private static Jedis jedis = JedisClusterClient.getJedis();    
    
	/**
     *expire 设置过期时间
     * @param key
     * @return
     * @throws Exception
     */
    public static long expire(String key,int seconds) throws Exception{
    	long expire = 0l;
    	if(jedis != null) {
    		expire = jedis.expire(key,seconds);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return expire;
    }
    
    /**
     *expire 设置过期时间
     * @param key
     * @return
     * @throws Exception
     */
    public static long expireByte(byte[] key,int seconds) throws Exception{
    	long expire = 0l;
    	if(jedis != null) {
    		expire = jedis.expire(key,seconds);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return expire;
    }
    
    /**
     *ttl 查看过期时间
     * @param key
     * @return
     * @throws Exception
     */
    public static long ttlString(String key) throws Exception{
    	long ttl = 0l;
    	if(jedis != null) {
    		ttl = jedis.ttl(key);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return ttl;
    }
	
    /**
     *ttl 查看过期时间
     * @param key
     * @return
     * @throws Exception
     */
    public static long ttlByte(byte[] key) throws Exception{
    	long ttl = 0l;
    	if(jedis != null) {
    		ttl = jedis.ttl(key);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return ttl;
    }
    
	/**
     *set
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public static void set(String key, String value) throws Exception{
    	if(jedis != null) {
    		jedis.set(key,value);
    		//jedis.close();
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    }
    
    /**
     *set大红包和未抢红包
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public static void setByte(byte[] key, byte[] value) throws Exception{
    	if(jedis != null) {
    		jedis.set(key, value);
    		jedis.close();
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    }
    
    /**
     *get
     * @param key
     * @return
     * @throws Exception
     */
    public static String get(String key) throws Exception{
    	String value = null;
    	if(jedis != null) {
    		value = jedis.get(key);
    		//jedis.close();
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return value;
    }
    
    /**
     *get
     * @param key
     * @return
     * @throws Exception
     */
    public static Object getByte(byte[] key) throws Exception{
    	Object obj = null;
    	if(jedis != null) {
    		obj = SerializeUtil.unserialize(jedis.get(key));
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return obj;
    }
    
    /**
     *decr
     * @param key
     * @return
     * @throws Exception
     */
    public static long decrNoExp(String key) throws Exception{
    	long decr = -1l;
    	if(jedis != null) {
    		decr = jedis.decr(key);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return decr;
    }
	
    public static long decr(String key) throws Exception{
    	long decr = -1l;
    	if(jedis != null) {
    		decr = jedis.decr(key);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return decr;
    }
    
    /**
     *incr
     * @param key
     * @return
     * @throws Exception
     */
    public static long incr(String key) throws Exception{
    	long incr = -1l;
    	if(jedis != null) {
    		incr = jedis.incr(key);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return incr;
    }
    
    /**
     * list set
     * @param key
     * @param field
     * @return
     * @throws Exception
     */
    public static void lpush(String key, String value) throws Exception{
    	if(jedis != null) {
    		jedis.lpush(key,value);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    }
    /**
     * list get
     * @param key
     * @param field
     * @return
     * @throws Exception
     */
    public static List<String> lrange(String key, long start, long end) throws Exception{
    	List<String> lrange =null;
    	if(jedis != null) {
    		lrange = jedis.lrange(key, start, end);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return lrange;
    }
    
    public static Object hget(byte[] key, byte[] field) throws Exception{
    	Object obj = null;
    	if(jedis != null) {
    		obj = SerializeUtil.unserialize(jedis.hget(key, field));
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return obj;
    }
    
    public static void hset(byte[] key, byte[] field, byte[] value) throws Exception {
    	if(jedis != null) {
    		jedis.hset(key, field, value);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    }
    
    public static void hdel(byte[] key, byte[] field) throws Exception {
    	if(jedis != null) {
    		jedis.hdel(key, field);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    }
   
    public static void del(byte[] key) throws Exception {
    	if(jedis != null) {
    		jedis.del(key);
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    }
    
 // 更新redis
    public static  void updateObjectValueToRedis(String key,Object value) throws Exception {
 			long expireDate = JedisUtil.ttlByte(key.getBytes());
 			JedisUtil.setByte(key.getBytes(), SerializeUtil.serialize(value));
 			JedisUtil.expireByte(key.getBytes(), (int) expireDate);// 设置过期时间
    }
 		
//    public static  void resetVerToRedis(String key) throws Exception{
//		long expireDate = JedisUtil.ttlString(key);
// 			JedisUtil.set(key, Constants.RED_VERSION.RED_W);
// 			JedisUtil.expire(key, (int) expireDate);// 设置过期时间
//    }
    
 
    public static  String keyOfUndo(String pkgDtlId){
 			return pkgDtlId+"undo";
    }
    public static  String keyOfList(String pkgId){
 			return pkgId+"lst";
    }
    public static  String keyOfVersion(String pkgDtlId){
 			return pkgDtlId+"ver";
    }
    public static  String keyOfDtl(String pkgDtlId){
 			return pkgDtlId+"dtl";
    }
    
    public static List<Object> hgetAllAsList(byte[] key) throws Exception{
    	List<Object> list = new ArrayList<Object>();
    	if(jedis != null) {
    		Map<byte[], byte[]> resMap = jedis.hgetAll(key);
    		for(byte[] mkey : resMap.keySet()){
    			byte[] mVal = resMap.get(mkey);
    			list.add(SerializeUtil.unserialize(mVal));
    		}
    		
    	}else{
    		throw new Exception("从连接池获取不到Jedis资源");
    	}
    	return list;
    }
    
    public static void main(String[] args) {
		try {

			JedisUtil.set("jq","lcs");			
			System.out.println("gh="+JedisUtil.get("jq"));
			//jedis.close();
			logger.debug("gh="+JedisUtil.get("gh"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("redis操作出错！！"+e.getMessage(),e);;
		}
		
	}
    
    @Test
    public void test(){
    	try {
			JedisUtil.set("ts","tianshenhao");
    		
			System.out.println("lxx="+JedisUtil.get("ts"));
			logger.debug("gh="+JedisUtil.get("gh"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("redis操作出错！！"+e.getMessage(),e);;
		}
    }
}
