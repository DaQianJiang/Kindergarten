package com.jq.child.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimeUtil {
	
	private static Logger logger= Logger.getLogger(TimeUtil.class);
	/**
     * 得到n天前的时间
     * 
     * @param d日期,n天数
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d,int n) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - n);
        return now.getTime();
    }
 
    /**
     * 得到n天后的时间
     * 
     * @param d日期，n天数
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d,int n) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + n);
        return now.getTime();
    }
    
    /**
     * string转date
     * @param time
     * @return
     */
    public static Date string2Date(String time) {
    	Date date = new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	
    	try {
			date=sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("时间转换出错..."+e.getMessage(),e);
		}  
		return date;
    }
    
    /**
     * 判断时间是否失效
     * @param time1
     * @return 等于0相等，小于0有效，大于0无效
     */
    public static int compareTo(Date time1){
    	Date nowTime = new Date(System.currentTimeMillis());
    	SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	String retStrFormatNowDate = sdFormatter.format(nowTime);
    	try {
			nowTime = sdFormatter.parse(retStrFormatNowDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("时间转换出错..."+e.getMessage(),e);
		}
    	int i = time1.compareTo(nowTime);
    	return i;
    }
    
    /**
     * 获取当前日期，精确到天
     * @return
     */
    public static Date getNowDate(){
    	Date nowTime = new Date(System.currentTimeMillis());
    	SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	String retStrFormatNowDate = sdFormatter.format(nowTime);
    	try {
			nowTime = sdFormatter.parse(retStrFormatNowDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("时间转换出错..."+e.getMessage(),e);
		}
    	return nowTime;
    }
    
    /**
     * date转换成string
     * @param d
     * @return
     */
    public static String date2String(Date d){
    	//Date nowTime = new Date();
    	SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	String retStrFormatNowDate = sdFormatter.format(d);
    	return retStrFormatNowDate;
    }
}
