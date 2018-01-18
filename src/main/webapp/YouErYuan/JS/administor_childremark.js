/**
 * 幼儿点评和录入
 */

$(function(){
	var param ={};
	var  sname= $('#s_name').val();
	var classNum = $('#class_num').val();
	var sno;
	initi();
	function initi(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/qryStuRemarks.do", 
			scriptCharset: 'utf-8',
			data : {
				classNum : classNum,
				sname : sname
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
	
});


function qyr_act(){
	sname= $('#s_name').val();
	classNum = $('#class_num').val();
	
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/qryStuRemarks.do", 
		scriptCharset: 'utf-8',
		data : {
			classNum : classNum,
			sname : sname
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




function del_btn(obj){
	className = $(obj).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_childfeaturedele').removeAttr('class').addClass('animated '+className+'').fadeIn();
	sno = $(obj).find("#sno").text();
	$('#sure_delete').click(function(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/delStuRemarks.do", 
			scriptCharset: 'utf-8',
			data : {
				sno : sno
			},
			success : function(data) {
				console.log("响应的数据：", data);
				alert(data.retDesc);
				window.location = getRootPath()+"/YouErYuan/administor_childremark.html";
			}
		});
	});
}


function select(){
	//默认初始值
		var param ={};
		var  sname= $('#s_name').val();
		console.log("学生姓名：", sname);
		var classNum = $('#class_num').val();
		var sno;
		init();
		function init(){
			$.ajax({
				type : "POST",
				dataType : 'json',
				url: getRootPath()+"/qryOneStudent.do", 
				scriptCharset: 'utf-8',
				data : {
					className : classNum,
					name : sname,
					sno:null
				},
				success : function(data) {
					console.log("响应的数据：", data);
					$.each(data,function(n,rs){
						if(rs.ext3 == 1){
							data[n].ext3=false;
						}else{
							data[n].ext3=true;
						}
					});
					param ={
							"flist" : data
					}
					$.fillTpl("fdata_list","fT_list", param);
					console.log("响应的数据：", param);
				}
			});
		}
					
}
//模态框查询
function fqyr_act(){
	sname= $('#fs_name').val();
	classNum = $('#fclass_num').val();
	
	$.ajax({
		type : "POST",
		dataType : 'json',
		url: getRootPath()+"/qryOneStudent.do", 
		scriptCharset: 'utf-8',
		data : {
			className : classNum,
			name : sname,
			sno:null
		},
		success : function(data) {
			console.log("响应的数据：", data);
			$.each(data,function(n,rs){
				if(rs.ext3 == 1){
					data[n].ext3=false;
				}else{
					data[n].ext3=true;
				}
			});
			param ={
					"flist" : data
			}
			$.fillTpl("fdata_list","fT_list", param);
			console.log("响应的数据：", param);
		}
	});
}

//模态框点评
function act_dp(obj){
	sno = $(obj).find("#snop").text();
	console.log('点评学生学号为:'+sno);
	var totalRemark = $("input[name='feature"+sno+"']:checked").val();
	if(totalRemark!=null){
		console.log('点评学生点评信息为:'+totalRemark);
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/addStudentRemarks.do", 
			scriptCharset: 'utf-8',
			data : {
				sno : sno,
				totalRemark : totalRemark,
				featureflag:1
			},
			success : function(data) {
				console.log("响应的数据：", data);
				/*alert(data.retDesc);*/
				className = $(this).attr('class');
				$('#dialogBg').fadeIn(300);
				$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
				$('.message_info').text(data.retDesc);
				
				$(obj).attr("disabled", true);
				/*obj.name*/
				/*window.location = getRootPath()+"/YouErYuan/administor_childremark.html";*/
			}
		});
	}else{
		/*alert("未选择评价");*/
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
		$('.message_info').text('未选择评价')
	}
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