/**
 * 首页信息更新
 */

$(function(){
	var param ={};
	init();
	function init(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/updatePersonPage.do", 
			scriptCharset: 'utf-8',	
			success : function(data) {
				console.log("响应的数据：", data);
				
				$.fillTpl("data_list","T_list", data);
				console.log("响应的数据：", param);
			}
		});
	}
});


//按钮状态
function compare(){
	datatime=$('#compare_time').text();
	var dutytime=new Date(datatime.replace("-", "/").replace("-", "/")).toDateString();
	
	console.log("值班时间"+dutytime);
	var data = new Date();
	/*var nowdata = data.toLocaleDateString();*/
	var nowdata = data.toDateString();
	console.log("当前时间"+data);
	if(dutytime < nowdata  )
	{
		className = $(this).attr('class');
			$('#dialogBg').fadeIn(300);
			$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
			$('.message_info').text('值班时间已过')
	}
	else if(dutytime>nowdata){
		className = $(this).attr('class');
			$('#dialogBg').fadeIn(300);
			$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
			$('.message_info').text('时间未到，无法签到')
	}
	else
	{
		
			$.ajax({
				type : "POST",
				dataType : 'json',
				url: getRootPath()+"/updata_parentduty.do", 
				scriptCharset: 'utf-8',
				data : {
					flag : 1,
					dutytime:datatime
				},			
				success : function(data) {
					console.log("响应的数据：", data);
					if(data.retCode=='00001')
					{
						className = $(this).attr('class');
						$('#dialogBg').fadeIn(300);
						$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
						$('.message_info').text(data.retDesc)
					}
					else if(data.retCode=='00000'){
						className = $(this).attr('class');
						$('#dialogBg').fadeIn(300);
						$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
						$('.message_info').text('签到成功')
					}
					
				}
			});
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
