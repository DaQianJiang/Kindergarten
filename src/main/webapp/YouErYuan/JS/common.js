/**
 * 初始化，界面用户
 */

$(function(){
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/initWelcome.do", 
		scriptCharset: 'utf-8',	
		success : function(data) {
			console.log("响应的数据：", data);
			$(".login_part").text(data.retCode);
			//$('#wel_part').val(data.retDesc);
		}
	});
});