/**
 * 教师经费查询
 */

$(function(){
	var total = {};
	var outpay = {};
	var income = {};
	
	var classNum;
	
	init();
	
});

	function select_btn(){
		classNum = $('#classNum').val();
/*		alert(classNum);*/
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/adminQryAccount.do", 
			scriptCharset: 'utf-8',
			data : {
				classNum : classNum
			},			
			success : function(data){
				console.log(data);
				dealData(data);
			}
		});
	}
	
	function init(){
		classNum = $('#classNum').val();
	/*	alert(classNum);*/
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/adminQryAccount.do", 
			scriptCharset: 'utf-8',
			data : {
				classNum : classNum
			},			
			success : function(data){
				console.log(data);
				dealData(data);
			}
		});
	}	

	function dealData(data){
		total = data;
		$.fillTpl("data_list","T_list", total);
		income = {
				"inlist" : data.inCoin
		}
		$.fillTpl("in_list","inT_list", income);
		outPay = {
				"outlist" : data.outCoin
		}
		$.fillTpl("out_list","outT_list", outPay);
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