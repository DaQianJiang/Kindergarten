package com.jq.child.admin.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jq.child.admin.dao.UserLoginMapper;
import com.jq.child.admin.pojo.ParentAuthority;
import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.pojo.TeacherInfo;
import com.jq.child.admin.pojo.UserLogin;
import com.jq.child.admin.pojo.UserLoginExample;
import com.jq.child.admin.pojo.UserLoginExample.Criteria;
import com.jq.child.admin.service.IParentAuthoritySV;
import com.jq.child.admin.service.IStudentInfoSV;
import com.jq.child.admin.service.ITeacherInfoSV;
import com.jq.child.admin.service.IUserLoginSV;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.constants;
import com.jq.child.util.encryptionUtil;

@Service("userLoginSV")
public class UserLoginSVImpl implements IUserLoginSV {

	private static Logger logger = Logger.getLogger(UserLoginSVImpl.class);
	
	@Resource
	private UserLoginMapper userLoginMapper;

	@Resource
	private ITeacherInfoSV teacherInfoSV;
	
	@Resource
	private IStudentInfoSV stuSV;
	
	@Resource
	private IParentAuthoritySV authoritySV;
	
	public OutPutParamUtil userLoginAndRegist(HttpServletRequest request, String account, String userType, String password) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<UserLogin> list = qryUserLoginByAccount(account);
		boolean flag = false;
		String pass = encryptionUtil.encoderByMD5(password);
		UserLogin bean = new UserLogin();
		OutPutParamUtil ret = new OutPutParamUtil();
		//logger.info("���ܺ�����룺"+pass);
		if(list.size()==1){
			bean = list.get(0);
		}
		if(userType.equals(constants.USER_TYPE.TEACHER)){
			List<TeacherInfo> tlist = teacherInfoSV.qryTeacherInfoByNum(account);
			if(tlist.size()==1){
				session.setAttribute("teacherName", tlist.get(0).getTeacherName());
				session.setAttribute("teacherNum", tlist.get(0).getTeacherNumber());
				if(list.size()==0){
					if(password.equals(account)){
						bean.setAccountNum(account);
						bean.setCreateTime(new Timestamp(System.currentTimeMillis()));
						bean.setPassword(pass);
						bean.setUserType(userType);
						saveUserLoginInfo(bean);
						logger.info("��һ�ε�¼....");
						ret.setRetCode("00001");
						ret.setRetDesc("��¼�ɹ�");
					}
				}else{
					if(pass.equals(bean.getPassword())){
						ret.setRetCode("00001");
						ret.setRetDesc("��¼�ɹ�");
					}
					else{
						ret.setRetCode("00000");
						ret.setRetDesc("���벻��ȷ");
					}
				}
			}
			else{
				ret.setRetCode("00000");
				ret.setRetDesc("��¼ʧ��,�û������ڻ�����ݲ�ƥ��");
				logger.info("�û������ڻ�����ݲ�ƥ��....");
			}
		}else if(userType.equals(constants.USER_TYPE.STUDENT)){
			List<StudentInfo> slist = stuSV.getStuInfoBySno(account);
			if(slist.size()==1){
				session.setAttribute("stuName", slist.get(0).getName());
				session.setAttribute("sno", slist.get(0).getSno());
				session.setAttribute("parentName", slist.get(0).getPname());
				if(list.size()==0){
					if(password.equals(account)){
						bean.setAccountNum(account);
						bean.setCreateTime(new Timestamp(System.currentTimeMillis()));
						bean.setPassword(pass);
						bean.setUserType(userType);
						saveUserLoginInfo(bean);
						logger.info("��һ�ε�¼....");
						ret.setRetCode("00002");
						ret.setRetDesc("��¼�ɹ�");
					}
				}else{
					if(pass.equals(bean.getPassword())){
						ret.setRetCode("00002");
						ret.setRetDesc("��¼�ɹ�");
						logger.info("������ȷ....");
					}
					else{
						ret.setRetCode("00000");
						ret.setRetDesc("��������ݲ�ƥ��");
						logger.info("������ȷ....");
					}
				}
			}
			else{
				ret.setRetCode("00000");
				ret.setRetDesc("��¼ʧ��,�û������ڻ�����ݲ�ƥ��");
				logger.info("��¼ʧ��,�û������ڻ�����ݲ�ƥ��");
			}
			
		}
		
		
		if(userType.equals(constants.USER_TYPE.STUDENT) && ret.getRetCode().equals("00002")){
			List<ParentAuthority> parentList = authoritySV.qryAuthorBySno(account);
			if(parentList.size()==1){
				ret.setRetCode("00003");
			}
		}

		return ret;
	}
	
	public List<UserLogin> qryUserLoginByAccount(String account) throws Exception {
		// TODO Auto-generated method stub
		UserLoginExample example = new UserLoginExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountNumEqualTo(account);
		List<UserLogin> list = userLoginMapper.selectByExample(example);
		return list;
	}
	public void saveUserLoginInfo(UserLogin value) throws Exception {
		// TODO Auto-generated method stub
		userLoginMapper.insert(value);
	}

	
}
