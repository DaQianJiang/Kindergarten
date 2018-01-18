package com.jq.child.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.junit.Test;

import sun.misc.BASE64Encoder;

public class encryptionUtil {
	private static Logger logger = Logger.getLogger(encryptionUtil.class);
	
	public static String encoderByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
		logger.info("加密后字符串"+newstr);
		return newstr;
	}
	
	@Test
	public void test(){
		String s = "asd123";
		try {
			String code = new String();
			OutPutParamUtil ret = new OutPutParamUtil();
			ret.setRetCode(code);
			String n = encryptionUtil.encoderByMD5(s);
			System.out.println("加密后的字符"+n);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
}
