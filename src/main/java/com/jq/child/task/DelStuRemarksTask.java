package com.jq.child.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jq.child.admin.service.IChildRemarksSV;

/**
 * 定时删除两周前的幼儿点评
 * @author jiangqian
 *
 */
@Component
public class DelStuRemarksTask {
	private static Logger logger = Logger.getLogger(DelStuRemarksTask.class);
	
	@Resource
	private IChildRemarksSV childRemarkSV;
	/**
	 * 暂定每秒执行
	 */
	@Scheduled(cron = "0/100 * * * * ?")
	public void delRemarksBefore(){
		logger.info("定时任务开始....");
		try {
			childRemarkSV.deleteRemarksBefore();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("删除出错..."+e.getMessage(),e);
		}
		logger.info("这次任务结束...");
	}
}
