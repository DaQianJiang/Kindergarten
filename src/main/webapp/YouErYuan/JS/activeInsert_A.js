//亲子活动
//删除
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
//详情查看
function detail_btn(obj){
	className = $(this).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_activeinfo').removeAttr('class').addClass('animated '+className+'').fadeIn();
	var content = $(obj).find("#content").text();
	$("#activity_content").val(content);
}
//信息录入
function atc_btn(){
	var start_time = $("#childactive_time").val();
	var theme = $("#childactive_them").val();
	//alert(sno);
	var content = $("#chile_content").val();
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/addActivityConfig.do", 
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

$(function(){
	var param ={};
	init();
	init1();
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




//家长值班find("option:selected").text();
function select_name(obj){
	className = $(obj).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_parentduty').removeAttr('class').addClass('animated '+className+'').fadeIn();
	classNuma=$("#duty_class").find("option:selected").text();
	console.log("选择的班级为"+classNuma);
	get_select();
	$('#duty_class').click(function(){
		classNuma=$("#duty_class").find("option:selected").text();
		get_select();
	});
}

//下拉框赋值
function get_select(){
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/qryOneStudent.do", 
		scriptCharset: 'utf-8',	
		data:{
			className:classNuma
		},
		success : function(data) {
			console.log("响应的数据：", data);
			var selectParam ={
					"selectlist" : data
			}
			$.fillTpl("select_list","sT_list", selectParam);
			console.log("响应的数据下拉信息：", selectParam);
		}
	});
}

//信息插入
function auth_act(obj){
	var duty_time=$('#duty_time').val();
	var duty_class=$('#duty_class').val();
	var selsno=$("#select_list").find("option:selected").text();
	var arr = selsno.split(',');
	var duty_childnum = arr[0];
	var pname = arr[1];
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/entryparentduty.do", 
		scriptCharset: 'utf-8',	
		data:{
			duty_time1:duty_time,
			duty_class1:duty_class,
			duty_childnum1:duty_childnum
		},
		success : function(data) {
			console.log("响应的数据：", data);
			alert(data.res);
			window.location = getRootPath()+"/YouErYuan/administor_entermanageactivinfo.html";
		}
	});
}


/*	function init1(){
		$("#parentdutyTable").bootstrapTable('destroy');
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/selectAllparent_duty.do",
			scriptCharset: 'utf-8',
			
			success : function(data) {
				console.log("家长值班信息响应的数据：", data);
				$("#parentdutyTable").bootstrapTable({
					data : data
				});
			}
	});
	}*/



$(function(){
		//查询显示家长值班信息
		var param ={};
		init();
		function init(){
			$.ajax({
				type : "POST",
				dataType : 'json',
				url: getRootPath()+"/selectAllparent_duty.do", 
				scriptCharset: 'utf-8',	
				success : function(data) {
					console.log("响应的数据：", data);
					$.each(data,function(n,rs){
						if(rs.ext2 == 1){
							data[n].ext2=false;
						}else{
							data[n].ext2=true;
						}
					});
					param ={
							"list1" : data
					}
					$.fillTpl("data_list1","T_list1", param);
					console.log("响应的数据：", param);
				}
			});
			
		}
});

//删除家长值班信息
function delparentduty_btn(objct){
	className = $(objct).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_deleteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	var stusno = $(objct).find("#deleteparentduty_bt").text();
	alert(stusno);
	$('#sure_delete').click(function(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/deleteParent_duty.do", 
			scriptCharset: 'utf-8',	
			data : {
				stusno : stusno
			},
			success : function(data) {
				console.log("响应的数据：", data);
				alert(data.retDesc);
				window.location = getRootPath()+"/YouErYuan/administor_entermanageactivinfo.html";
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
/*});*/



