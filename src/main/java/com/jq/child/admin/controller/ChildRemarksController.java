package com.jq.child.admin.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jq.child.admin.pojo.ChildRemarks;
import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.service.IChildRemarksSV;
import com.jq.child.admin.service.IStudentInfoSV;
import com.jq.child.util.JedisUtil;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.TimeUtil;

@Controller
public class ChildRemarksController {

	private static Logger logger = Logger.getLogger(ChildRemarksController.class);
	
	@Resource
	private IChildRemarksSV childRemarkSV;
	@Resource
	private IStudentInfoSV stuSV;
	@RequestMapping(value="addStudentRemarks",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String remarksStudent(String sno, String totalRemark, String featureflag,
			HttpServletRequest request){
		logger.info("��ʼ����ѧ������....");
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		ChildRemarks bean = new ChildRemarks();
		HttpSession session = request.getSession();
		String tname = (String)session.getAttribute("teacherName");
		try {
			List<StudentInfo> stuList = stuSV.getStuInfoBySno(sno);
			if(stuList.size()==1){
				List<ChildRemarks> list = childRemarkSV.qryRemarksBetween7(TimeUtil.getNowDate(), sno,7);
				
				if(list.size()==0){
					stuList.get(0).setExt3(featureflag);
					stuSV.saveStuInfo(stuList.get(0));
					
					bean.setSno(sno);
					bean.setTotalRemark(totalRemark);
					bean.setCreatTime(TimeUtil.getNowDate());
					bean.setSname(stuList.get(0).getName());
					bean.setExt1(stuList.get(0).getClassNum());
					bean.setExt2(tname);
					//��redis����
					JedisUtil.set("totalRemark"+sno, totalRemark);
					childRemarkSV.insertRemarke(bean);
					ret.setRetCode("0000");
					ret.setRetDesc("�����ɹ�");
				}else{
					ret.setRetCode("0001");
					ret.setRetDesc("�����Ѿ�����");
				}
			}else{
				ret.setRetCode("0003");
				ret.setRetDesc("δ�鵽����ѧ��");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("�����쳣��"+e.getMessage(),e);
			ret.setRetCode("0002");
			ret.setRetDesc("�����쳣");
		}
		
		str = gson.toJson(ret);
		return str;
	}
	
	
	@RequestMapping(value="getStuRemarksNew",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getStuRemarksNew(HttpServletRequest request){
		logger.info("��ѯѧ������....");
		HttpSession session = request.getSession();
		String sno = (String) session.getAttribute("sno");
		Gson gson = new Gson();
		String str = new String();
		try {
			List<ChildRemarks> list = childRemarkSV.qryRemarksBetween7(TimeUtil.getNowDate(), sno,14);
			for(int i = 0; i < list.size(); i++){
				list.get(i).setExt1(TimeUtil.date2String(list.get(i).getCreatTime()));
			}
			str = gson.toJson(list);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("��ѯ����"+e.getMessage(),e);
		}
		logger.info("���µ��������"+str);
		return str;
	}
	
	
	@RequestMapping(value="qryStuRemarks",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String qryStuRemarksByMore(String classNum, String sname){
		logger.info("��ѯ��ʼ�༶��������"+classNum+","+sname);
		Gson gson = new Gson();
		String str = new String();
		
		try {
			List<ChildRemarks> list = childRemarkSV.qryRemarksBySnameAndClassNum(classNum, sname);
			str = gson.toJson(list);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("��ѯ����"+e.getMessage(),e);
		}
		logger.info("�׶��������飺"+str);
		return str;
	}
	
	
	@RequestMapping(value="delStuRemarks",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delStuRemarks(String sno){
		logger.info("ѧ�ţ�"+sno);
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		
		if(sno!=null){
			try {
				childRemarkSV.deleteRemarks(sno);
				ret.setRetCode("0000");
				ret.setRetDesc("ɾ���ɹ�");
				List<StudentInfo> stuList = stuSV.getStuInfoBySno(sno);
				if(stuList.size()!=0){
					stuList.get(0).setExt3("0");
					stuSV.saveStuInfo(stuList.get(0));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.error("ɾ��ʧ��"+e.getMessage(),e);
				ret.setRetCode("0001");
				ret.setRetDesc("ɾ��ʧ��");
			}
		}else{
			ret.setRetCode("0002");
			ret.setRetDesc("δ��ȡ��ѧ��");
		}
		str = gson.toJson(ret);
		return str;
	}
}
