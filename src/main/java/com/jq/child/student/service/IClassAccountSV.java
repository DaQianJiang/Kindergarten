package com.jq.child.student.service;

import java.util.List;

import com.jq.child.student.pojo.ClassAccount;
import com.jq.child.util.OutPutParamUtil;

public interface IClassAccountSV {
	public void insertAccount(ClassAccount value)throws Exception;
	
	public OutPutParamUtil reduceAccount(String classNum,String balance)throws Exception;
	
	public List<ClassAccount> getAccountByClassNum(String classNum)throws Exception;

	
	public int getTotalCoinByClassNum(String classNum)throws Exception;
	
	public void updateAccount(ClassAccount bean)throws Exception;
	
	/**
	 * 收入回款
	 * @param classNum
	 * @param transId
	 * @return
	 * @throws Exception
	 */
	public int reBackIncome(String classNum, int balance)throws Exception;
	
	/**
	 * 支出
	 * @param classNum
	 * @param transId
	 * @return
	 * @throws Exception
	 */
	public int reBackOutpay(String classNum, int balance)throws Exception;
}
