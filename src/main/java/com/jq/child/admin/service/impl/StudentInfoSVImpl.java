package com.jq.child.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jq.child.admin.dao.StudentInfoMapper;
import com.jq.child.admin.pojo.ChildRemarks;
import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.pojo.StudentInfoExample;
import com.jq.child.admin.pojo.StudentInfoExample.Criteria;
import com.jq.child.admin.service.IChildRemarksSV;
import com.jq.child.admin.service.IStudentInfoSV;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.TimeUtil;

@Service("stuSV")
public class StudentInfoSVImpl implements IStudentInfoSV{
	
	private static Logger logger = Logger.getLogger(StudentInfoSVImpl.class);
	
	@Resource
	private StudentInfoMapper stuMapper;
	@Resource
	private IChildRemarksSV childRemarkSV;
	@Resource
	private IStudentInfoSV stuSV;

	//插入新学生信息
	public boolean saveStuInfo(StudentInfo value) throws Exception {
		// TODO Auto-generated method stub
		List<StudentInfo> list = getStuInfoBySno(value.getSno());
		StudentInfoExample example = new StudentInfoExample();
		if(list.size()==0){
			stuMapper.insert(value);
		}else{
			Criteria criteria = example.createCriteria();
			criteria.andSnoEqualTo(value.getSno());
			stuMapper.updateByExample(value, example);
		}
		return true;
	}
	
	//根据学号查询学生信息
	public List<StudentInfo> getStuInfoBySno(String sno) throws Exception {
		// TODO Auto-generated method stub
		StudentInfoExample example = new StudentInfoExample();
		if(sno != null){
			Criteria criteria = example.createCriteria();
			criteria.andSnoEqualTo(sno);
		}		
		List<StudentInfo> list = stuMapper.selectByExample(example);
		return list;
	}

	public List<StudentInfo> qryOneStuInfo(String name, String sno, String className) throws Exception {
		// TODO Auto-generated method stub
		
		if(sno==null){
			sno="";
		}
		if(name==null){
			name="";
		}
		if(className==null){
			className="";
		}
		Map paramMap = new HashMap();
		paramMap.put("sno", sno);
		paramMap.put("name", name);
		paramMap.put("classNum", className);
		List<StudentInfo> list = stuMapper.selectOneStuInfo(paramMap);
		
		return list;
	}

	public OutPutParamUtil delStudentInfo(List<String> list) throws Exception {
		// TODO Auto-generated method stub
		OutPutParamUtil ret = new OutPutParamUtil();
		for(int i = 0; i < list.size();i++){
			StudentInfoExample example = new StudentInfoExample();
			Criteria criteria = example.createCriteria();
			criteria.andSnoEqualTo(list.get(i));
			stuMapper.deleteByExample(example);
		}
		ret.setRetCode("0000");
		ret.setRetDesc("删除成功");
		return ret;
	}

	public List<StudentInfo> qryStuInfo(String classNum, String sname) {
		
		StudentInfoExample example = new StudentInfoExample();
		Criteria criteria = example.createCriteria();
		if(!classNum.isEmpty())
		{
			criteria.andClassNumEqualTo(classNum);
		}
		if(!sname.isEmpty()){
			criteria.andNameEqualTo(sname);
		}
		List<StudentInfo> list = stuMapper.selectByExample(example);
		return list;
	}
	
	
	//更新学生信息表flage状态
	public void updateFeatureFlage()throws Exception{
		
		Date nowTime = TimeUtil.getNowDate();
		List<ChildRemarks> list = childRemarkSV.qryBefor7days(nowTime);
		if(list.size()==0){
			logger.info("暂时无需要更新状态的数据");
		}
		else{
			for(int i=0 ; i<list.size();i++){
				
				//String sno = list.get(i).getSno();
		    List<StudentInfo> list1 = stuSV.getStuInfoBySno(list.get(i).getSno());
		    if(list1.size()!=0){
		    	list1.get(0).setExt3("0");
				StudentInfoExample example = new StudentInfoExample();
				Criteria criteria = example.createCriteria();
				criteria.andSnoEqualTo(list.get(i).getSno());
				stuMapper.updateByExampleSelective(list1.get(0), example);
		    }
			
		
			
				
			}
			
		}
	}
	
}
