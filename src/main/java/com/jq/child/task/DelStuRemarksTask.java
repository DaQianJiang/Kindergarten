package com.jq.child.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jq.child.admin.service.IChildRemarksSV;

/**
 * ��ʱɾ������ǰ���׶�����
 * @author jiangqian
 *
 */
@Component
public class DelStuRemarksTask {
	private static Logger logger = Logger.getLogger(DelStuRemarksTask.class);
	
	@Resource
	private IChildRemarksSV childRemarkSV;
	/**
	 * �ݶ�ÿ��ִ��
	 */
	@Scheduled(cron = "0/100 * * * * ?")
	public void delRemarksBefore(){
		logger.info("��ʱ����ʼ....");
		try {
			childRemarkSV.deleteRemarksBefore();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("ɾ������..."+e.getMessage(),e);
		}
		logger.info("����������...");
	}
}
