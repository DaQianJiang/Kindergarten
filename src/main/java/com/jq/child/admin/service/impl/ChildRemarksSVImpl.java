package com.jq.child.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jq.child.admin.dao.ChildRemarksMapper;
import com.jq.child.admin.dao.StudentInfoMapper;
import com.jq.child.admin.pojo.ChildRemarks;
import com.jq.child.admin.pojo.ChildRemarksExample;
import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.pojo.ChildRemarksExample.Criteria;
import com.jq.child.admin.service.IChildRemarksSV;
import com.jq.child.util.TimeUtil;

@Service("childRemarkSV")
public class ChildRemarksSVImpl implements IChildRemarksSV {

	private static Logger logger = Logger.getLogger(ChildRemarksSVImpl.class);
	
	@Resource
	private ChildRemarksMapper childRemarksMapper;
	@Resource 
	private StudentInfoMapper stuMapper;

	public void insertRemarke(ChildRemarks value) throws Exception {
		// TODO Auto-generated method stub
		childRemarksMapper.insert(value);
	}

	public void deleteRemarksBefore() throws Exception {
		// TODO Auto-generated method stub
		Date nowTime = TimeUtil.getNowDate();
		Gson gson = new Gson();
		ChildRemarksExample example = new ChildRemarksExample();
		logger.info("当前时间..."+nowTime);
		List<ChildRemarks> list = qryRemarksBefore(nowTime);
		if(list.size() == 0){
			logger.info("暂无需删除的数据");
		}else{
			logger.info("查询出需删除数据："+gson.toJson(list));
			Criteria criteria = example.createCriteria();
			criteria.andCreatTimeLessThan(TimeUtil.getDateBefore(nowTime, 14));
			childRemarksMapper.deleteByExample(example);
		}
		
	}

	/**
	 * 查询14天前的记录
	 */
	public List<ChildRemarks> qryRemarksBefore(Date time) throws Exception {
		// TODO Auto-generated method stub
		//Date nowTime = TimeUtil.getNowDate();
		ChildRemarksExample example = new ChildRemarksExample();
		Criteria criteria = example.createCriteria();
		criteria.andCreatTimeLessThan(TimeUtil.getDateBefore(time, 14));
		List<ChildRemarks> list = childRemarksMapper.selectByExample(example);
		return list;
	}

	/**
	 * 查询某学生n天前到现在的记录
	 */
	public List<ChildRemarks> qryRemarksBetween7(Date time,String sno, int n) throws Exception {
		// TODO Auto-generated method stub
		ChildRemarksExample example = new ChildRemarksExample();
		Criteria criteria = example.createCriteria();
		criteria.andCreatTimeGreaterThan(TimeUtil.getDateBefore(time, n));
		criteria.andSnoEqualTo(sno);
		List<ChildRemarks> list = childRemarksMapper.selectByExample(example);
		return list;
	}
	
	//查询七天前的数据
	public List<ChildRemarks> qryBefor7days(Date time)throws Exception{
		logger.info("查询七天前的学生点评信息");
		ChildRemarksExample example = new ChildRemarksExample();
		Criteria criteria = example.createCriteria();
		criteria.andCreatTimeLessThan(TimeUtil.getDateBefore(time, 7));
		List<ChildRemarks> list = childRemarksMapper.selectByExample(example);	
		return list;
	}


	public List<ChildRemarks> qryRemarksBySnameAndClassNum(String classNum, String sname) throws Exception {
		// TODO Auto-generated method stub
		/*ChildRemarksExample example = new ChildRemarksExample();
		
		Criteria criteria = example.createCriteria();
		if(!classNum.isEmpty()){
			criteria.andExt1EqualTo(classNum);
		}
		if(!sname.isEmpty()){
			criteria.andSnameEqualTo(sname);
		}
		List<ChildRemarks> list = childRemarksMapper.selectByExample(example);*/
		
		if(classNum==null){
			classNum="";
		}
		if(sname==null){
			sname="";
		}
		Map paramMap = new HashMap();
		paramMap.put("classNum", classNum);
		paramMap.put("name", sname);
		List<ChildRemarks> list = childRemarksMapper.selectChildRmark(paramMap);
		return list;
	}

	public void deleteRemarks(String sno) throws Exception {
		// TODO Auto-generated method stub
		ChildRemarksExample example = new ChildRemarksExample();
		Criteria criteria = example.createCriteria();
		criteria.andSnoEqualTo(sno);
		childRemarksMapper.deleteByExample(example);
	}
	
	
}
