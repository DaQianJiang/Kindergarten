/**
 * 用户登录
 */

$(function(){
	$("#login_btn").click(function(){

		var userType = $('#userType').val();
		var userAccount=$('#userAccount').val();
		var password=$('#password1').val();
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/userLogin.do", 
			scriptCharset: 'utf-8',	
			data:{
				userType:userType,
				userAccount:userAccount,
				password1:password
			},
			success : function(data) {
				console.log("响应的数据：", data);
				if(data.retCode=='00001')
				{
					/*alert(data.retDesc);*/
/*					className = $(this).attr('class');
					$('#dialogBg').fadeIn(300);
					$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
					$('.message_info').text(data.retDesc);
				    $('#sure').click(function(){*/
				    	window.location = getRootPath()+"/YouErYuan/administor_right.html";
					
					
				}
				else if (data.retCode=='00002')
				{
					/*alert(data.retDesc);*/
					/*className = $(this).attr('class');
					$('#dialogBg').fadeIn(300);
					$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
					$('.message_info').text(data.retDesc);*/
						window.location = getRootPath()+"/YouErYuan/ParentNormal_owenpage.html";
					
				}
				else if(data.retCode=='00003')
				{
					/*alert(data.retDesc);*/
					/*className = $(this).attr('class');
					$('#dialogBg').fadeIn(300);
					$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
					$('.message_info').text(data.retDesc);*/
					   window.location = getRootPath()+"/YouErYuan/Parent_owenpage.html";
	
					
				}else if(data.retCode=='00000'){
					/*alert(data.retDesc);*/
					className = $(this).attr('class');
					$('#dialogBg').fadeIn(300);
					$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
					$('.message_info').text(data.retDesc)
				}
			}
				
				
			
		});
		
		
		
		
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
	    //alert(localhostPath + projectName);
	    return (localhostPath + projectName);//发布前用此
	}
});