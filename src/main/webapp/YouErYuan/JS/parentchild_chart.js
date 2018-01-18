/**
 * 活动详情页面
 */
function detail_btn(obj){
		var className = $(obj).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_parentchildinfo').removeAttr('class').addClass('animated '+className+'').fadeIn();
		var parents = $(obj).find("#parents").text();
		var content = $(obj).find("#content").text();
		/*alert(content);*/
		$("#activity_content").val(content);
		$("#jion_parents").val(parents);
}

$(function(){
	var param ={};
	init();

	function init(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/activityChart.do", 
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

