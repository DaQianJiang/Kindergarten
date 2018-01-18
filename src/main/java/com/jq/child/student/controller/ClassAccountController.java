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
	 * 插入收款信息
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
		logger.info("家长姓名："+pname);
		//查询班级
		List<StudentInfo> stuList = stuSV.getStuInfoBySno(sno);
		
		if(stuList.size()==1){
			
		//1.封装账本参数
		ClassAccount accountBean = new ClassAccount();
		accountBean.setBalance(Integer.valueOf(inNum));
		accountBean.setClassNum(stuList.get(0).getClassNum());
		//accountBean.setClassNum(classNum);
		accountBean.setCreateTime(TimeUtil.getNowDate());

		//2.封装收入参数
		ClassIncome incomeBean = new ClassIncome();
		incomeBean.setClassNum(stuList.get(0).getClassNum());
		incomeBean.setInNum(Integer.valueOf(inNum));
		incomeBean.setOptParent(pname);
		incomeBean.setOptTime(TimeUtil.string2Date(date));
		
		try {
			//3.保存插入账本信息
			accountSV.insertAccount(accountBean);
			incomeSV.insertIncome(incomeBean);
			ret.setRetCode("0000");
			ret.setRetDesc("录入成功");
		} catch (Exception e) {
			ret.setRetCode("0001");
			ret.setRetDesc("录入失败");
		}
		}
		str = gson.toJson(ret);
		return str;
	}
	
	/**
	 * 插入支出信息
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
		
	
		logger.info("家长姓名："+pname);
		//查询班级
		List<StudentInfo> stuList = stuSV.getStuInfoBySno(sno);
		
		if(stuList.size()==1){
	    String classNum=stuList.get(0).getClassNum() ;	
		bean.setClassNum(classNum);
		
		
		//1.封装参数
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
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            //e.printStackTrace();
        	logger.error("保存凭证出错..."+e.getMessage(),e);
        } 
        try {
			
			//1.进行扣款
			OutPutParamUtil ret = accountSV.reduceAccount(classNum, Integer.toString(outNum));
			if(ret.getRetCode().equals("0000")){
				payoutSV.insertPayOutInfo(bean);
				mv.addObject("message", "扣款成功");
				mv.setViewName("redirect:/YouErYuan/Parent_financialpage.html");
			}else{
				mv.addObject("message", "扣款失败，余额不足");
				mv.setViewName("redirect:/YouErYuan/Parent_financialpage.html");
			}
        } catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("保存支出信息出错"+e.getMessage(),e);
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
		//1.这里只能根据userType来进行比较，防止sno和teacherNum都存在的情况
		try {
			List<StudentInfo> list = stuSV.getStuInfoBySno(sno);
			classNum = list.get(0).getClassNum();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查询学生班级有误.."+e.getMessage(),e);
			ret.setRetCode("0001");
			ret.setRetDesc("学生信息有误");
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
			logger.error("获取支出详情出错");
		}
		try {
			//2.查询出收入详情并进行金额汇总
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
			logger.error("获取收入详情出错");
		}
		try {
			ret.setTotalCoin(accountSV.getTotalCoinByClassNum(classNum));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("获取总金额出错");
		}
		str = gson.toJson(ret);
		logger.info("收支详情："+str);
		return str;
	}
	
	@RequestMapping(value="adminQryAccount",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String adminQryAccount(String classNum){
		logger.info("班级："+classNum);
		Gson gson = new Gson();
		String str = new String();
		OutPutParamUtil ret = new OutPutParamUtil();
		int income = 0;
		int outPay = 0;
		try{
			//1.查询出支出详情并进行金额汇总
			List<ClassPayout> outList = payoutSV.getInCoinByClassNum(classNum);
			for(int outI = 0; outI < outList.size(); outI++){
				outPay += outList.get(outI).getOutNum();
				outList.get(outI).setExt1(TimeUtil.date2String(outList.get(outI).getOptTime()));
			}
			ret.setOutCoin(outList);
			ret.setOutPay(outPay);
			
			//2.查询出收入详情并进行金额汇总
			List<ClassIncome> inList = incomeSV.getIncomeByClassNum(classNum);
			for(int inI = 0; inI < inList.size(); inI++){
				income += inList.get(inI).getInNum();
				inList.get(inI).setExt1(TimeUtil.date2String(inList.get(inI).getOptTime()));
			}
			ret.setInCoin(inList);
			ret.setIncome(income);
			
			//3.查询出班级总余额
			ret.setTotalCoin(accountSV.getTotalCoinByClassNum(classNum));
		}catch(Exception e){
			logger.error("收支查询有误");
			ret.setRetCode("0001");
			ret.setRetDesc("查询失败");
		}
		str = gson.toJson(ret);
		logger.info("收支详情："+str);
		return str;
	}
	
	@RequestMapping(value="delInOrOutAccount",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delInOrOutAccount(String delType,String balance, String transId,HttpServletRequest request){
		logger.info("删除类型和ID："+delType+","+transId);
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
				throw new Exception("未登录。。。");
			}
			String classNum = stuList.get(0).getClassNum();
			if(delType.equals(constants.ACCOUNT_TYPE.INCOME)){
				//1.收入金额回滚
				succ = accountSV.reBackIncome(classNum, Integer.parseInt(balance));
				if(succ == 1){
					del = incomeSV.delIncomeInfo(Integer.valueOf(transId));
				}
			}else if(delType.equals(constants.ACCOUNT_TYPE.OUTPAY)){
				//2.支撑金额回滚
				succ = accountSV.reBackOutpay(classNum, Integer.parseInt(balance));
				if(succ == 1){
					del = payoutSV.delPayOutInfo(Integer.valueOf(transId));
				}
			}
		}catch(Exception e){
			logger.error("删除失败"+e.getMessage(),e);
			ret.setRetCode("0001");
			ret.setRetDesc("删除失败");
		}
		if(del == 1){
			ret.setRetCode("0000");
			ret.setRetDesc("删除成功");
		}else{
			ret.setRetCode("0001");
			ret.setRetDesc("删除失败");
		}
		
		str = gson.toJson(ret);
		logger.info(str);
		return str;
	}
}
