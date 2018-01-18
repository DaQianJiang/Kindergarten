package com.jq.child.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jq.child.student.dao.ClassPayoutMapper;
import com.jq.child.student.pojo.ClassPayout;
import com.jq.child.student.pojo.ClassPayoutExample;
import com.jq.child.student.pojo.ClassPayoutExample.Criteria;
import com.jq.child.student.service.IClassPayoutSV;

@Service("payoutSV")
public class ClassPayoutSVImpl implements IClassPayoutSV {

	@Resource
	private ClassPayoutMapper payoutMapper;
	public void insertPayOutInfo(ClassPayout value) throws Exception {
		// TODO Auto-generated method stub
		payoutMapper.insertSelective(value);
	}
	
	/**
	 * ²éÑ¯Ö§³ö
	 */
	public List<ClassPayout> getInCoinByClassNum(String calssNum) throws Exception {
		// TODO Auto-generated method stub
		ClassPayoutExample example =new ClassPayoutExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andClassNumEqualTo(calssNum);
		List<ClassPayout> list = payoutMapper.selectByExample(example);
		
		return list;
	}

	public int delPayOutInfo(int payOutId) throws Exception {
		// TODO Auto-generated method stub
		return payoutMapper.deleteByPrimaryKey(payOutId);
	}

}
