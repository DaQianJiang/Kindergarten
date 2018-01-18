/**
 * 
 */	
	/*教师信息展示*/
	$(function(){
		
		init();	
		/*edit();*/
	/*	var addData=[];*/
		var checkedData=[];
		$("#search_teacher").click(function(){
			$("#teacher_table").bootstrapTable('destroy');
			$("#teacher_table").bootstrapTable('resetView');
			var teacherName = $("#search_teachername").val();
			var teacherNumber = $("#search_teachernum").val();
			//alert(sno);
			var leasonName = $("#search_teacherleason").val();
			
			$.ajax({
				type : "POST",
				dataType : 'json',
				url: getRootPath()+"/qryOneTeacher.do", 
				scriptCharset: 'utf-8',
				data : {
					teacherName : teacherName,
					teacherNumber : teacherNumber,
					leasonName : leasonName
				},			
				success : function(data) {
					console.log("响应的数据：", data);
					$("#search_teachername").val("");
					$("#search_teachernum").val("");
					$("#search_teacherleason").val("");
					$("#teacher_table").bootstrapTable({
						data : data
					});
					//$('#cusTable').bootstrapTable('refresh'); 
				}
			});
		});
		
		function init(){
			$("#teacher_table").bootstrapTable('destroy');
			$.ajax({
				type : "POST",
				dataType : 'json',
				url: getRootPath()+"/qryAllTeacher.do",
				scriptCharset: 'utf-8',
				
				success : function(data) {
					console.log("响应的数据：", data);
					$("#teacher_table").bootstrapTable({
					  data : data
						
						
					});
						
				}
			});
		}	
		
//编辑	
		$('#editteacher_btn').click(function(){
	        alert('您选择的id是: ' + JSON.stringify($('#teacher_table').bootstrapTable('getSelections')));
			var stData = $('#teacher_table').bootstrapTable('getSelections')[0];
			var selectData = $('#teacher_table').bootstrapTable('getSelections');
			var addData = [];
	        $.each(selectData,function(n,value){
	        	addData[n] = value.teacherNumber;
	       	 	alert(addData[n]);
	        });
	        console.log(addData)
	       if(addData.length==1){
	        	console.log("响应的数据：", stData);
	        	$(".form-control1").eq(1).val(stData.teacherName)
				$(".form-control1").eq(0).val(stData.teacherNumber)
				$(".form-control1").eq(2).val(stData.age)
				$(".form-control1").eq(3).val(stData.sex)
				$(".form-control1").eq(4).val(stData.phone)
				$(".form-control1").eq(5).val(stData.education)
				$(".form-control1").eq(6).val(stData.adress)
				$(".form-control1").eq(7).val(stData.leasonName)
				$(".form-control1").eq(8).val(stData.img)
				
				className = $(this).attr('class');
	    		$('#dialogBg_tacher').fadeIn(300);
	    		$('#dialog_editteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	        }else{
	          	 className = $(this).attr('class');
	 			$('#dialogBg_tacher').fadeIn(300);
	 			$('#dialog_messagetea').removeAttr('class').addClass('animated '+className+'').fadeIn();
	 			$('.message_info').text('最多选择一行数据')
	        }
		});
		

		$('#deleteacher_btn').click(function() {
	         alert('您选择的id是: ' + JSON.stringify($('#teacher_table').bootstrapTable('getSelections')));
	         var selectData = $('#teacher_table').bootstrapTable('getSelections');
		    //alert (JSON.stringify($('#cusTable th[data-checkbox="true"]').data('data-field')));
	         $.each(selectData,function(n,value){
	        	 checkedData[n] = value.teacherNumber;
	        	 alert(checkedData[n]);
	         }); 
	         if(checkedData.length<=0){
	 			/*alert("请选择至少一行数据");*/
	        	 className = $(this).attr('class');
	  			$('#dialogBg_tacher').fadeIn(300);
	  			$('#dialog_messagetea').removeAttr('class').addClass('animated '+className+'').fadeIn();
	  			$('.message_info').text('请选择至少一行数据')
	        	 
	 		}else{
	 			className = $(this).attr('class');
	 			$('#dialogBg_tacher').fadeIn(300);
	 			$('#dialog_delete').removeAttr('class').addClass('animated '+className+'').fadeIn();
	 		}
		});
		
		//确认删除
		$('#sure_delete').click(function(){
			if(checkedData.length<=0){
	       	 className = $(this).attr('class');
				$('#dialogBg_tacher').fadeIn(300);
				$('#dialog_messagetea').removeAttr('class').addClass('animated '+className+'').fadeIn();
				$('.message_info').text('请选择至少一行数据')
			}else{
				$.ajax({
					type : "POST",
					dataType : 'json',
					url: getRootPath()+"/delTeachertInfo.do", 
					scriptCharset: 'utf-8',
					data : {
						list : checkedData
					},			
					success : function(data) {
						console.log("响应的数据：", data);
						window.location = getRootPath()+"/YouErYuan/administor_manageteacherinfo.html";
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
});
