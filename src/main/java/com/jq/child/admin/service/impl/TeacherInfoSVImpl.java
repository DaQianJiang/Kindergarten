package com.jq.child.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jq.child.admin.dao.TeacherInfoMapper;
import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.pojo.StudentInfoExample;
import com.jq.child.admin.pojo.TeacherInfo;
import com.jq.child.admin.pojo.TeacherInfoExample;
import com.jq.child.admin.pojo.TeacherInfoExample.Criteria;
import com.jq.child.admin.service.ITeacherInfoSV;
import com.jq.child.util.OutPutParamUtil;

@Service("teacherInfoSV")
public class TeacherInfoSVImpl implements ITeacherInfoSV {

	private static Logger logger = Logger.getLogger(TeacherInfoSVImpl.class);
	@Resource
	private TeacherInfoMapper teacherMapper;
	
	//插入老师信息
	public boolean saveTeacherInfo(TeacherInfo value) throws Exception {
		// TODO Auto-generated method stub
		List<TeacherInfo> list = getTeacherInfoBySno(value.getTeacherNumber());
		if(list.size()==0){
			teacherMapper.insert(value);
		}else{
			TeacherInfoExample example = new TeacherInfoExample();
			Criteria criteria = example.createCriteria();
			criteria.andTeacherNumberEqualTo(value.getTeacherNumber());
			teacherMapper.updateByExample(value, example);
		}
		return true;
	}
	
	//删除老师信息
	public OutPutParamUtil delTeacherInfo(List<String> list) {
		// TODO Auto-generated method stub
		OutPutParamUtil ret = new OutPutParamUtil();
		for(int i = 0; i < list.size();i++){
			TeacherInfoExample example = new TeacherInfoExample();
			Criteria criteria = example.createCriteria();
			criteria.andTeacherNumberEqualTo(list.get(i));
			teacherMapper.deleteByExample(example);
		}
		ret.setRetCode("0000");
		ret.setRetDesc("删除成功");
		return ret;
	}
	
	//根据职工号查找
	public List<TeacherInfo> getTeacherInfoBySno(String teacherNumber)throws Exception {
		// TODO Auto-generated method stub
		TeacherInfoExample example = new TeacherInfoExample();
		if(teacherNumber != null){
			Criteria criteria = example.createCriteria();
			criteria.andTeacherNumberEqualTo(teacherNumber);
		}		
		List<TeacherInfo> list = teacherMapper.selectByExample(example);
		return list;
	}

	public List<TeacherInfo> qryTeacherInfoByNum(String teacherNum) throws Exception {
		// TODO Auto-generated method stub
		TeacherInfoExample example = new TeacherInfoExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andTeacherNumberEqualTo(teacherNum);
		List<TeacherInfo> list= teacherMapper.selectByExample(example);
		
		return list;
	}
	public List<TeacherInfo> qryOneTeacherInfo(String t_name, String t_number, String t_class) {
		// TODO Auto-generated method stub
		if(t_number==null){
			t_number="";
		}
		if(t_name==null){
			t_name="";
		}
		if(t_class==null){
			t_class="";
		}
		Map paramMap = new HashMap();
		paramMap.put("t_number", t_number);
		paramMap.put("t_name", t_name);
		paramMap.put("t_class", t_class);
		List<TeacherInfo> list = teacherMapper.selectOneTeacherInfo(paramMap);
		
		return list;
	}



}
