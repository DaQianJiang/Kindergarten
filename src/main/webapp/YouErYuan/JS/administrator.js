/**
 * 学生信息管理
 */
$(function(){
	var checkedData = [];
	/*var addData = [];*/
	init();	
	$("#search_btn").click(function(){
		$("#cusTable").bootstrapTable('destroy');
		$("#cusTable").bootstrapTable('resetView');
		var name = $("#search_name").val();
		var sno = $("#search_sno").val();
		console.log(sno);
		//alert(sno);
		var className = $("#search_class").val();
		
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/qryOneStudent.do", 
			scriptCharset: 'utf-8',
			data : {
				name : name,
				sno : sno,
				className : className
			},			
			success : function(data) {
				console.log("响应的数据：", data);
				$("#search_name").val("");
				$("#search_sno").val("");
				$("#search_class").val("");
				$("#cusTable").bootstrapTable({
					data : data
				});
				//$('#cusTable').bootstrapTable('refresh'); 
			}
		});
	});
	
	function init(){
		$("#cusTable").bootstrapTable('destroy');
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/qryAllStudent.do",
			scriptCharset: 'utf-8',
			
			success : function(data) {
				console.log("响应的数据：", data);
				$("#cusTable").bootstrapTable({
					data : data
				});
					
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
	
	$('#del_btnlll').click(function() {
/*         alert('您选择的id是: ' + JSON.stringify($('#cusTable').bootstrapTable('getSelections')));*/
         var selectData = $('#cusTable').bootstrapTable('getSelections');
	    //alert (JSON.stringify($('#cusTable th[data-checkbox="true"]').data('data-field')));
         $.each(selectData,function(n,value){
        	 checkedData[n] = value.sno;
        	/* alert(checkedData[n]);*/
         }); 
         if(checkedData.length<=0){
 			/*alert("请选择至少一行数据");*/
        	 className = $(this).attr('class');
  			$('#dialogBg').fadeIn(300);
  			$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
  			$('.message_info').text('请选择至少一行数据')
        	 
 		}else{
 			className = $(this).attr('class');
 			$('#dialogBg').fadeIn(300);
 			$('#dialog_delete').removeAttr('class').addClass('animated '+className+'').fadeIn();
 		}
	});
	
	//确认删除
	$('#sure_delete').click(function(){
		if(checkedData.length<=0){
       	 className = $(this).attr('class');
			$('#dialogBg').fadeIn(300);
			$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
			$('.message_info').text('请选择至少一行数据')
		}else{
			$.ajax({
				type : "POST",
				dataType : 'json',
				url: getRootPath()+"/delStudentInfo.do", 
				scriptCharset: 'utf-8',
				data : {
					list : checkedData
				},			
				success : function(data) {
					console.log("响应的数据：", data);
					window.location = getRootPath()+"/YouErYuan/administor_managestuinfo.html";
				}
			});
		}
    });
	
	
	//修改学生信息
	$('#btn_edit').click(function(){
		/*alert('您选择的id是: ' + JSON.stringify($('#cusTable').bootstrapTable('getSelections')));*/
		var stData = $('#cusTable').bootstrapTable('getSelections')[0];
		var selectData = $('#cusTable').bootstrapTable('getSelections');
		var addData = [];
        $.each(selectData,function(n,value){
        	addData[n] = value.sno;
       	 	/*alert(addData[n]);*/
        });
        console.log(addData)
        if(addData.length==1){
        	console.log("响应的数据：", stData);
        	$(".form-control1").eq(1).val(stData.sno);
        	$(".form-control1").eq(0).val(stData.name);
        	$(".form-control1").eq(2).val(stData.age);
        	$(".form-control1").eq(3).val(stData.sex);
        	console.log("性别"+stData.sex);
        	$(".form-control1").eq(4).val(stData.classNum);
        	$(".form-control1").eq(5).val(stData.pname);
        	$(".form-control1").eq(6).val(stData.parentPhone);
        	$(".form-control1").eq(7).val(stData.adress);
        	$(".form-control1").eq(8).val(stData.ext1);
        	className = $(this).attr('class');
    		$('#dialogBg').fadeIn(300);
    		$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
        	
        }else{
          	 className = $(this).attr('class');
 			$('#dialogBg').fadeIn(300);
 			$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
 			$('.message_info').text('最多选择一行数据')
        }
	});
	
	
});
