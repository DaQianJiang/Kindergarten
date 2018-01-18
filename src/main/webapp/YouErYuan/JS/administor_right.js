/**
 * 权限赋予
 */

$(function(){
	var param ={};
	var classNum=$("#duty_class").find("option:selected").text();
	var classNuma;
	init();
	function init(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/getAuthorty.do", 
			scriptCharset: 'utf-8',
			data : {
				classNum : classNum
			},
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

	$('#qry_acct').click(function(){
		classNum=$("#duty_class").find("option:selected").text();
		init();
	});
});



function parentrightdelete_btn(obj){
	className = $(obj).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_parentrightdele').removeAttr('class').addClass('animated '+className+'').fadeIn();
	var sno = $(obj).find("#snoss").text();
	$('#sure_delete').click(function(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/delAuthorty.do", 
			scriptCharset: 'utf-8',	
			data : {
				sno : sno
			},
			success : function(data) {
				console.log("响应的数据：", data);
				/*alert(data.retDesc);*/
				window.location = getRootPath()+"/YouErYuan/administor_right.html";
			}
		});
	});
	
}

function btn_parentright(obj){
	className = $(obj).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_parentright').removeAttr('class').addClass('animated '+className+'').fadeIn();
	classNuma=$("#duty_classaa").find("option:selected").text();
	get_select();
	$('#duty_classaa').click(function(){
		classNuma=$("#duty_classaa").find("option:selected").text();
		get_select();
	});
}


/**
 * 获取下拉框值
 */

function get_select(){
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/qryOneStudent.do", 
		scriptCharset: 'utf-8',	
		data : {
			className : classNuma
		},
		success : function(data) {
			console.log("响应的数据：", data);
			var selectParam ={
					"selectlist" : data
			}
			$.fillTpl("select_list","sT_list", selectParam);
			console.log("响应的数据：", selectParam);
		}
	});
}
/**
 * 分配权限
 */
function auth_act(obj){
	var className=$("#duty_classaa").find("option:selected").text();
	var selsno=$("#select_list").find("option:selected").text();
	var arr = selsno.split(',');
	var sesno = arr[0];
	var pname = arr[1];
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/giveAuthorty.do", 
		scriptCharset: 'utf-8',	
		data : {
			classNum : className,
			sno : sesno,
			pname : pname
		},
		success : function(data) {
			console.log("响应的数据：", data);
			/*alert(data.retDesc);*/
            $('#dialog_parentright').addClass('bounceOutUp').fadeOut();
			className = $(this).attr('class');
			$('#dialogBg').fadeIn(300);
			$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
			$('.message_info').text(data.retDesc);
			
			$('#sure').click(function(){
				window.location = getRootPath()+"/YouErYuan/administor_right.html";
			})
			
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