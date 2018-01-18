package com.jq.child.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jq.child.student.dao.ClassIncomeMapper;
import com.jq.child.student.pojo.ClassIncome;
import com.jq.child.student.pojo.ClassIncomeExample;
import com.jq.child.student.pojo.ClassIncomeExample.Criteria;
import com.jq.child.student.service.IClassIncomeSV;


@Service("incomeSV")
public class ClassIncomeSVImpl implements IClassIncomeSV {
	@Resource
	private ClassIncomeMapper incomeMapper;
	public void insertIncome(ClassIncome value) throws Exception {
		// TODO Auto-generated method stub
		incomeMapper.insert(value);
	}
	
	/**
	 * ≤È—Ø ’»Î
	 */
	public List<ClassIncome> getIncomeByClassNum(String classNum) throws Exception {
		// TODO Auto-generated method stub
		ClassIncomeExample example = new ClassIncomeExample();
		Criteria criteria = example.createCriteria();
		criteria.andClassNumEqualTo(classNum);
		List<ClassIncome> list = incomeMapper.selectByExample(example);
		return list;
	}

	public int delIncomeInfo(int incomeid) throws Exception {
		// TODO Auto-generated method stub
		return incomeMapper.deleteByPrimaryKey(incomeid);
	}

}
