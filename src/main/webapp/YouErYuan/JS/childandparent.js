/**
 * 报名参加活动
 */

function detail_btn(obj){
		//alert('您选择的id是: ' + JSON.stringify(obj));
		className = $(obj).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_activejoin').removeAttr('class').addClass('animated '+className+'').fadeIn();
		var content = $(obj).find("#content").text();
		alert(content);
		$("#chile_content").val(content);
	}

	
function sign_up(obj){
	//alert('您选择的id是: ' + JSON.stringify(obj));
	var activityId = $(obj).find("#baoming").text();
/*	alert(activityId);*/
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/SignUpActivity.do", 
		scriptCharset: 'utf-8',	
		data : {
			activityId : activityId
		},
		success : function(data) {
			/*alert(data.retDesc);*/
			className = $(this).attr('class');
			$('#dialogBg').fadeIn(300);
			$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
			$('.message_info').text(data.retDesc);
		}
	});
}

$(function(){
	var param ={};
	init();
	
	
	function init(){
		//$('#childparent_active').modal('show');
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
