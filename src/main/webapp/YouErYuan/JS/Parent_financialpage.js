/**
 * 家长录入收支
 */

$(function(){
	var total = {};
	var outpay = {};
	var income = {};
	var transId;
	var balance;
	var delType;
	var message = getUrlParam("message");
	if(message!="" && message!=null){
		alert(message);
	}
	init();
	function init(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/qryAllAccount.do", 
			scriptCharset: 'utf-8',			
			success : function(data){
				console.log(data);
				dealData(data);
			}
		});
	}	

	function dealData(data){
		total = data;
		$.fillTpl("data_list","T_list", data);
		income = {
				"inlist" : data.inCoin
		}
		$.fillTpl("in_list","inT_list", income);
		outPay = {
				"outlist" : data.outCoin
		}
		$.fillTpl("out_list","outT_list", outPay);
	}
	
});
function incom_delebtn(obj){
	className = $(this).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_deleteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	transId = $(obj).find("#income_id").text();
	balance = $(obj).find("#in_num").text();
	delType = "2";
	$('#sure_delete').click(function(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/delInOrOutAccount.do", 
			scriptCharset: 'utf-8',
			data : {
				delType : delType,
				balance : balance,
				transId : transId
			},			
			success : function(data){
				console.log(data);
				alert(data.retDesc);
				window.location = getRootPath()+"/YouErYuan/Parent_financialpage.html";
			}
		});
	});
}
function expenddelete_btn(obj){
	className = $(this).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_deleteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	transId = $(obj).find("#payout_id").text();
	balance = $(obj).find("#out_num").text();
	delType = "1";
	$('#sure_delete').click(function(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/delInOrOutAccount.do", 
			scriptCharset: 'utf-8',
			data : {
				delType : delType,
				balance : balance,
				transId : transId
			},			
			success : function(data){
				console.log(data);
				alert(data.retDesc);
				window.location = getRootPath()+"/YouErYuan/Parent_financialpage.html";
			}
		});
	});
}



function get_select(){
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/updatePersonPage.do", 
		scriptCharset: 'utf-8',	
		success : function(data) {
			console.log("响应的数据：", data);
			$('#income_class').text(data.classNum)
			console.log("班级",data.classNum);
		}
	});
}


	function in_btn(obj){
		className = $(obj).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_incominfo').removeAttr('class').addClass('animated '+className+'').fadeIn();
		get_select();
		$('#income_act').click(function(){
			var date = $('#income_time').val();
			var inNum = $('#income_number').val();
			/*var classNum = $('#income_class').val();*/
			
			$.ajax({
				type : "POST",
				dataType : 'json',
				url: getRootPath()+"/entryIncome.do", 
				scriptCharset: 'utf-8',
				data : {
					date : date,
					inNum : inNum
					/*classNum : classNum*/
				},			
				success : function(data){
					console.log(data);
					alert(data.retDesc);
					window.location = getRootPath()+"/YouErYuan/Parent_financialpage.html";
				}
			});
		});
		
	}

	/**   
	 * 获取项目根目录
	 */
	function getRootPath() {
		// 获取当前网址，如： http://localhost:8088/test/test.jsp
		var curPath = window.document.location.href;
		//获取主机地址之后的目录，如： test/test.jsp
		var pathName = window.document.location.pathname;
		var pos = curPath.indexOf(pathName);
		//获取主机地址，如： http://localhost:8088
		var localhostPath = curPath.substring(0, pos);
		//获取带"/"的项目名，如：/test
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		return (localhostPath + projectName);//发布前用此
	}
	
	function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); // 匹配目标参数
		if (r != null)
			return decodeURI(r[2]);
		return ""; // 返回参数值
	}
