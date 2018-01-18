/**
 * 活动规则配置
 */
$(function(){
	var param ={};
	init();
	function init(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/getActivityInfo.do", 
			scriptCharset: 'utf-8',	
			success : function(data) {
				console.log("响应的数据：", data);
				param ={
						"list" : data
				}
				$.fillTpl("data_list","T_list", param);
				console.log("响应的数据：", param);
			}
		});
	}

});


function del_btn(obj){
	className = $(this).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_deleteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	var activityId = $(obj).find("#delete_bt").text();
	alert(activityId);
	$('#sure_delete').click(function(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/delActivityConfig.do", 
			scriptCharset: 'utf-8',	
			data : {
				activityId : activityId
			},
			success : function(data) {
				console.log("响应的数据：", data);
				alert(data.retDesc);
				window.location = getRootPath()+"/YouErYuan/administor_entermanageactivinfo.html";
			}
		});
	});
	
}
function detail_btn(obj){
	className = $(this).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_activeinfo').removeAttr('class').addClass('animated '+className+'').fadeIn();
	var content = $(obj).find("#content").text();
	$("#activity_content").val(content);
}

function atc_btn(){
	var start_time = $("#childactive_time").val();
	var theme = $("#childactive_them").val();
	//alert(sno);
	var content = $("#chile_content").val();
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/updatePersonPage.do", 
		scriptCharset: 'utf-8',
		data : {
			theme : theme,
			startTime : start_time,
			content : content
		},			
		success : function(data) {
			alert(data.retDesc);
			window.location = getRootPath()+"/YouErYuan/administor_entermanageactivinfo.html";
			//$('#cusTable').bootstrapTable('refresh'); 
		}
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
