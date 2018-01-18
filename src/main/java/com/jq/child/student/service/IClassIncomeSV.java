package com.jq.child.student.service;

import java.util.List;

import com.jq.child.student.pojo.ClassIncome;

public interface IClassIncomeSV {
	public void insertIncome(ClassIncome value)throws Exception;
	
	public List<ClassIncome> getIncomeByClassNum(String classNum)throws Exception;

	public int delIncomeInfo(int incomeid)throws Exception;
}
