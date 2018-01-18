package com.jq.child.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jq.child.student.dao.ClassAccountMapper;
import com.jq.child.student.pojo.ClassAccount;
import com.jq.child.student.pojo.ClassAccountExample;
import com.jq.child.student.pojo.ClassAccountExample.Criteria;
import com.jq.child.student.service.IClassAccountSV;
import com.jq.child.util.OutPutParamUtil;

@Service("accountSV")
public class ClassAccountSVImpl implements IClassAccountSV{
	@Resource
	private ClassAccountMapper accountMapper;
	/**
	 * 插入账本信息
	 */
	public void insertAccount(ClassAccount value) throws Exception {
		// TODO Auto-generated method stub
		List<ClassAccount> list = getAccountByClassNum(value.getClassNum());
		if(list.size()==0){
			accountMapper.insert(value);
		}else{
			ClassAccountExample example = new ClassAccountExample();
			Criteria criteria = example.createCriteria();
			criteria.andClassNumEqualTo(value.getClassNum());
			int balance = list.get(0).getBalance();
			list.get(0).setBalance(value.getBalance()+balance);
			accountMapper.updateByExampleSelective(list.get(0), example);
		}
		
	}
	
	/**
	 * 扣款
	 */
	public OutPutParamUtil reduceAccount(String classNum, String balance) throws Exception {
		// TODO Auto-generated method stub
		int accountBalance = 0;
		OutPutParamUtil ret = new OutPutParamUtil();
		List<ClassAccount> list = getAccountByClassNum(classNum);
		if(list.size() == 1){
			accountBalance = list.get(0).getBalance();
		}
		if(accountBalance < Integer.valueOf(balance)){
			ret.setRetCode("0001");
			ret.setRetDesc("余额不足");
		}else if(accountBalance > Integer.valueOf(balance)){
			accountBalance = accountBalance - Integer.valueOf(balance);
			list.get(0).setBalance(accountBalance);
			updateAccount(list.get(0));
			ret.setRetCode("0000");
			ret.setRetDesc("扣款成功");
		}else{
			list.get(0).setBalance(0);
			updateAccount(list.get(0));
			ret.setRetCode("0000");
			ret.setRetDesc("扣款成功,余额已经扣完");
		}
		return ret;
	}
	
	/**
	 * 根据班级查询账本信息
	 */
	public List<ClassAccount> getAccountByClassNum(String classNum) throws Exception {
		// TODO Auto-generated method stub
		ClassAccountExample example = new ClassAccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andClassNumEqualTo(classNum);
		List<ClassAccount> list = accountMapper.selectByExample(example);
		return list;
	}

	public void updateAccount(ClassAccount bean) throws Exception {
		// TODO Auto-generated method stub
		ClassAccountExample example = new ClassAccountExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountIdEqualTo(bean.getAccountId());
		accountMapper.updateByExampleSelective(bean, example);
	}

	public int getTotalCoinByClassNum(String classNum) throws Exception {
		// TODO Auto-generated method stub
		List<ClassAccount> list = getAccountByClassNum(classNum);
		int totalCoin = 0;
		for(int i = 0; i < list.size(); i++){
			totalCoin += list.get(i).getBalance();
		}
		return totalCoin;
	}

	public int reBackIncome(String classNum, int balance) throws Exception {
		// TODO Auto-generated method stub
		List<ClassAccount> list = getAccountByClassNum(classNum);
		int totalBalance = 0; 
		int succ = 0;
		if(list.size() == 1){
			totalBalance = list.get(0).getBalance();
			if(totalBalance >= balance){
				ClassAccountExample example = new ClassAccountExample();
				Criteria criteria = example.createCriteria();
				criteria.andClassNumEqualTo(classNum);
				list.get(0).setBalance(totalBalance-balance);
				succ = accountMapper.updateByExampleSelective(list.get(0), example);
			}else{
				throw new Exception("余额不足，数据已经被支出");
			}
		}
		return succ;
	}

	public int reBackOutpay(String classNum, int balance) throws Exception {
		// TODO Auto-generated method stub
		List<ClassAccount> list = getAccountByClassNum(classNum);
		int totalBalance = 0; 
		int succ = 0;
		if(list.size() == 1){
			totalBalance = list.get(0).getBalance();
			ClassAccountExample example = new ClassAccountExample();
			Criteria criteria = example.createCriteria();
			criteria.andClassNumEqualTo(classNum);
			list.get(0).setBalance(totalBalance+balance);
			succ = accountMapper.updateByExampleSelective(list.get(0), example);
		}
		return succ;
	}
}
