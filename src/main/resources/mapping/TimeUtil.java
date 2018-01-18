package com.jq.child.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimeUtil {
	
	private static Logger logger= Logger.getLogger(TimeUtil.class);
	/**
     * �õ�n��ǰ��ʱ��
     * 
     * @param d����,n����
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
     * �õ�n����ʱ��
     * 
     * @param d���ڣ�n����
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
     * stringתdate
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
			logger.error("ʱ��ת������..."+e.getMessage(),e);
		}  
		return date;
    }
    
    /**
     * �ж�ʱ���Ƿ�ʧЧ
     * @param time1
     * @return ����0��ȣ�С��0��Ч������0��Ч
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
			logger.error("ʱ��ת������..."+e.getMessage(),e);
		}
    	int i = time1.compareTo(nowTime);
    	return i;
    }
    
    /**
     * ��ȡ��ǰ���ڣ���ȷ����
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
			logger.error("ʱ��ת������..."+e.getMessage(),e);
		}
    	return nowTime;
    }
    
    /**
     * dateת����string
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
