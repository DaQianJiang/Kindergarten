package com.jq.child.student.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jq.child.admin.pojo.StudentInfo;
import com.jq.child.admin.service.IStudentInfoSV;
import com.jq.child.admin.service.ITeacherInfoSV;
import com.jq.child.student.pojo.ClassAccount;
import com.jq.child.student.pojo.ClassIncome;
import com.jq.child.student.pojo.ClassPayout;
import com.jq.child.student.service.IClassAccountSV;
import com.jq.child.student.service.IClassIncomeSV;
import com.jq.child.student.service.IClassPayoutSV;
import com.jq.child.util.OutPutParamUtil;
import com.jq.child.util.TimeUtil;
import com.jq.child.util.constants;

@Controller
public class ClassAccountController {
	private Logger logger = Logger.getLogger(ClassAccountController.class);
	
	@Resource
	private IClassAccountSV accountSV;
	@Resource
	private IClassIncomeSV incomeSV;
	@Resource
	private IClassPayoutSV payoutSV;
	@Resource
	private IStudentInfoSV stuSV;
	@Resource
	private ITeacherInfoSV teacherInfoSV;
	/**
	 * �����տ���Ϣ
	 * @param inNum
	 * @param classNum
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="entryIncome",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String entryIncome(String date, String inNum,HttpServletRequest request) throws Exception{
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		HttpSession session = request.getSession();
		String pname = (String)session.getAttribute("parentName");
		String sno = (String)session.getAttribute("sno");
		logger.info("�ҳ�������"+pname);
		//��ѯ�༶
		List<StudentInfo> stuList = stuSV.getStuInfoBySno(sno);
		
		if(stuList.size()==1){
			
		//1.��װ�˱�����
		ClassAccount accountBean = new ClassAccount();
		accountBean.setBalance(Integer.valueOf(inNum));
		accountBean.setClassNum(stuList.get(0).getClassNum());
		//accountBean.setClassNum(classNum);
		accountBean.setCreateTime(TimeUtil.getNowDate());

		//2.��װ�������
		ClassIncome incomeBean = new ClassIncome();
		incomeBean.setClassNum(stuList.get(0).getClassNum());
		incomeBean.setInNum(Integer.valueOf(inNum));
		incomeBean.setOptParent(pname);
		incomeBean.setOptTime(TimeUtil.string2Date(date));
		
		try {
			//3.��������˱���Ϣ
			accountSV.insertAccount(accountBean);
			incomeSV.insertIncome(incomeBean);
			ret.setRetCode("0000");
			ret.setRetDesc("¼��ɹ�");
		} catch (Exception e) {
			ret.setRetCode("0001");
			ret.setRetDesc("¼��ʧ��");
		}
		}
		str = gson.toJson(ret);
		return str;
	}
	
	/**
	 * ����֧����Ϣ
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("entryPayout")
	public ModelAndView addPayoutInfo(@RequestParam(value = "out_invoice", required = false) MultipartFile file, 
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		ModelAndView mv = new ModelAndView();
		ClassPayout bean = new ClassPayout();
		request.setCharacterEncoding("utf-8");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		HttpSession session = request.getSession();
		String sno = (String)session.getAttribute("sno");
		String pname = (String)session.getAttribute("parentName");
		int outNum = Integer.valueOf(multipartRequest.getParameter("expend_money"));
		/*String classNum = multipartRequest.getParameter("classNum");*/
		String path = "E:\\photo\\entryPayout\\";
		//String path = request.getSession().getServletContext().getRealPath("entryPayout");
		String fileName = sno+"_"+file.getOriginalFilename();
		String outType = multipartRequest.getParameter("expend_item");
		String expend_time  = multipartRequest.getParameter("expend_time");
		
	
		logger.info("�ҳ�������"+pname);
		//��ѯ�༶
		List<StudentInfo> stuList = stuSV.getStuInfoBySno(sno);
		
		if(stuList.size()==1){
	    String classNum=stuList.get(0).getClassNum() ;	
		bean.setClassNum(classNum);
		
		
		//1.��װ����
		//bean.setClassNum(classNum);
		bean.setOptParent(pname);
		bean.setOutInvoice(fileName);
		bean.setOptTime(TimeUtil.getNowDate());
		bean.setOutDetail(expend_time);
		bean.setOutNum(outNum);
		bean.setOutType(outType);
		
		File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }   
        //����  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            //e.printStackTrace();
        	logger.error("����ƾ֤����..."+e.getMessage(),e);
        } 
        try {
			
			//1.���пۿ�
			OutPutParamUtil ret = accountSV.reduceAccount(classNum, Integer.toString(outNum));
			if(ret.getRetCode().equals("0000")){
				payoutSV.insertPayOutInfo(bean);
				mv.addObject("message", "�ۿ�ɹ�");
				mv.setViewName("redirect:/YouErYuan/Parent_financialpage.html");
			}else{
				mv.addObject("message", "�ۿ�ʧ�ܣ�����");
				mv.setViewName("redirect:/YouErYuan/Parent_financialpage.html");
			}
        } catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("����֧����Ϣ����"+e.getMessage(),e);
		}
		}
		return mv;
	}
	
	@RequestMapping(value="qryAllAccount",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String qryClassAccountDtl(HttpServletRequest request){
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		HttpSession session = request.getSession();
		String sno = (String) session.getAttribute("sno");
		String classNum = new String();
		int outPay = 0;
		int income = 0;
		//1.����ֻ�ܸ���userType�����бȽϣ���ֹsno��teacherNum�����ڵ����
		try {
			List<StudentInfo> list = stuSV.getStuInfoBySno(sno);
			classNum = list.get(0).getClassNum();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("��ѯѧ���༶����.."+e.getMessage(),e);
			ret.setRetCode("0001");
			ret.setRetDesc("ѧ����Ϣ����");
		}
		try {
			List<ClassPayout> outList = payoutSV.getInCoinByClassNum(classNum);
			for(int outI = 0; outI < outList.size(); outI++){
				outPay += outList.get(outI).getOutNum();
			}
			ret.setOutCoin(outList);
			ret.setOutPay(outPay);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("��ȡ֧���������");
		}
		try {
			//2.��ѯ���������鲢���н�����
			List<ClassIncome> inList = incomeSV.getIncomeByClassNum(classNum);
			for(int inI = 0; inI < inList.size(); inI++){
				income += inList.get(inI).getInNum();
				inList.get(inI).setExt1(TimeUtil.date2String(inList.get(inI).getOptTime()));
			}
			ret.setInCoin(inList);
			ret.setIncome(income);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("��ȡ�����������");
		}
		try {
			ret.setTotalCoin(accountSV.getTotalCoinByClassNum(classNum));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("��ȡ�ܽ�����");
		}
		str = gson.toJson(ret);
		logger.info("��֧���飺"+str);
		return str;
	}
	
	@RequestMapping(value="adminQryAccount",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String adminQryAccount(String classNum){
		logger.info("�༶��"+classNum);
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		int income = 0;
		int outPay = 0;
		try{
			//1.��ѯ��֧�����鲢���н�����
			List<ClassPayout> outList = payoutSV.getInCoinByClassNum(classNum);
			for(int outI = 0; outI < outList.size(); outI++){
				outPay += outList.get(outI).getOutNum();
				outList.get(outI).setExt1(TimeUtil.date2String(outList.get(outI).getOptTime()));
			}
			ret.setOutCoin(outList);
			ret.setOutPay(outPay);
			
			//2.��ѯ���������鲢���н�����
			List<ClassIncome> inList = incomeSV.getIncomeByClassNum(classNum);
			for(int inI = 0; inI < inList.size(); inI++){
				income += inList.get(inI).getInNum();
				inList.get(inI).setExt1(TimeUtil.date2String(inList.get(inI).getOptTime()));
			}
			ret.setInCoin(inList);
			ret.setIncome(income);
			
			//3.��ѯ���༶�����
			ret.setTotalCoin(accountSV.getTotalCoinByClassNum(classNum));
		}catch(Exception e){
			logger.error("��֧��ѯ����");
			ret.setRetCode("0001");
			ret.setRetDesc("��ѯʧ��");
		}
		str = gson.toJson(ret);
		logger.info("��֧���飺"+str);
		return str;
	}
	
	@RequestMapping(value="delInOrOutAccount",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delInOrOutAccount(String delType,String balance, String transId,HttpServletRequest request){
		logger.info("ɾ�����ͺ�ID��"+delType+","+transId);
		Gson gson = new Gson();
		String str = new String();
		HttpSession session = request.getSession();
		String sno = (String) session.getAttribute("sno");
		int succ = 0;
		int del = 0;
		OutPutParamUtil ret = new OutPutParamUtil();
		try{
			List<StudentInfo> stuList = stuSV.getStuInfoBySno(sno);
			if(stuList.size()==0){
				throw new Exception("δ��¼������");
			}
			String classNum = stuList.get(0).getClassNum();
			if(delType.equals(constants.ACCOUNT_TYPE.INCOME)){
				//1.������ع�
				succ = accountSV.reBackIncome(classNum, Integer.parseInt(balance));
				if(succ == 1){
					del = incomeSV.delIncomeInfo(Integer.valueOf(transId));
				}
			}else if(delType.equals(constants.ACCOUNT_TYPE.OUTPAY)){
				//2.֧�Ž��ع�
				succ = accountSV.reBackOutpay(classNum, Integer.parseInt(balance));
				if(succ == 1){
					del = payoutSV.delPayOutInfo(Integer.valueOf(transId));
				}
			}
		}catch(Exception e){
			logger.error("ɾ��ʧ��"+e.getMessage(),e);
			ret.setRetCode("0001");
			ret.setRetDesc("ɾ��ʧ��");
		}
		if(del == 1){
			ret.setRetCode("0000");
			ret.setRetDesc("ɾ���ɹ�");
		}else{
			ret.setRetCode("0001");
			ret.setRetDesc("ɾ��ʧ��");
		}
		
		str = gson.toJson(ret);
		logger.info(str);
		return str;
	}
}
