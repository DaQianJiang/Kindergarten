$(document).ready(function(){
			var w,h,className;
function getSrceenWH(){
	w = $(window).width();
	h = $(window).height();
	$('#dialogBg').width(w).height(h);
}

window.onresize = function(){  
	getSrceenWH();
}  
$(window).resize();  

$(function(){
	getSrceenWH();
	
	//ÏÔÊ¾µ¯¿ò
	$('#btn_edit').click(function(){
		/*className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();*/
	});
	
	//¹Ø±Õµ¯´°
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog').addClass('bounceOutUp').fadeOut();
		});
	});

	/*删除*/
 $('#btn_de').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_delete').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	//¹Ø±Õµ¯´°
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_delete').addClass('bounceOutUp').fadeOut();
		});
	});
		$('#abolish').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_delete').addClass('bounceOutUp').fadeOut();
		});
	});

});
/*家长信息编辑删除*/
var w,h,className;
function getSrceenWH(){
	w = $(window).width();
	h = $(window).height();
	$('#dialogBg_tacher').width(w).height(h);
}

window.onresize = function(){  
	getSrceenWH();
}  
$(window).resize();  

$(function(){
	getSrceenWH();
	
	//ÏÔÊ¾µ¯¿ò
	$('#editteacher_btn').click(function(){
		/*className = $(this).attr('class');
		$('#dialogBg_tacher').fadeIn(300);
		$('#dialog_editteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();*/
	});
	
	//¹Ø±Õµ¯´°
	$('.claseDialogBtn').click(function(){
		$('#dialogBg_tacher').fadeOut(300,function(){
			$('#dialog_editteacher').addClass('bounceOutUp').fadeOut();
		});
	});

	/*删除*/
 $('#deleteacher_btn').click(function(){
		className = $(this).attr('class');
		$('#dialogBg_tacher').fadeIn(300);
		$('#dialog_deleteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	//¹Ø±Õµ¯´°
	$('.claseDialogBtn').click(function(){
		$('#dialogBg_tacher').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});
		$('#abolish').click(function(){
		$('#dialogBg_tacher').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});

});

/*亲子活动*/

var w,h,className;
function getSrceenWH(){
	w = $(window).width();
	h = $(window).height();
	$('#dialogBg_parentchild').width(w).height(h);
}

window.onresize = function(){  
	getSrceenWH();
}  
$(window).resize();  

$(function(){
	getSrceenWH();
	
	//ÏÔÊ¾µ¯¿ò
	$('#parentchidactive').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_parentchild').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	//¹Ø±Õµ¯´°
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_parentchild').addClass('bounceOutUp').fadeOut();
		});
	});
});

	$('#parentdutyactive').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_parentduty').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	//¹Ø±Õµ¯´°
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_parentduty').addClass('bounceOutUp').fadeOut();
		});
	});
	/*活动详情*/
		$('#content_btn').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_activeinfo').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	//¹Ø±Õµ¯´°
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_activeinfo').addClass('bounceOutUp').fadeOut();
		});
	});

	/*删除*/
	$('#childparent_dele').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_deleteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	/*关闭按钮*/
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#abolish').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});
	/*家长值班删除*/
	$('#parentduty_dele').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_deleteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	/*关闭按钮*/
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#abolish').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});
	/*收入支出*/
	$('#incom_btn').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_incominfo').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	//¹Ø±Õµ¯´°
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_incominfo').addClass('bounceOutUp').fadeOut();
		});
	});
	/*支出*/
	$('#expend_btn').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_expendinfo').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	//¹Ø±Õµ¯´°
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_expendinfo').addClass('bounceOutUp').fadeOut();
		});
	});
/*收入删除*/

$('#incom_delebtn').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_deleteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	/*关闭按钮*/
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#abolish').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});
/*支出删除*/
$('#expenddelete_btn').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_deleteacher').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	/*关闭按钮*/
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#abolish').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_deleteacher').addClass('bounceOutUp').fadeOut();
		});
	});
/*亲子活动详情模态框*/

		$('#parentchild_btn').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_activejoin').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
		
		$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_activejoin').addClass('bounceOutUp').fadeOut();
		});
	});
/*亲子活动统计详情*/
		$('#childparent_detail').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_parentchildinfo').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
		
		$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_parentchildinfo').addClass('bounceOutUp').fadeOut();
		});
	});
	/*教师幼儿点评*/
		$('#btn_childfeature').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_childfeature').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
		
		$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_childfeature').addClass('bounceOutUp').fadeOut();
		});
	});
		/*幼儿点评删除*/
		$('#child_featuredete').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_childfeaturedele').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	/*关闭按钮*/
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_childfeaturedele').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#abolish').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_childfeaturedele').addClass('bounceOutUp').fadeOut();
		});
	});
	/*权限分配*/
		$('#btn_parentright').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_parentright').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
		
		$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_parentright').addClass('bounceOutUp').fadeOut();
		});
	});
		/*权限信息删除*/
$('#parentrightdelete_btn').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_parentrightdele').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	/*关闭按钮*/
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_parentrightdele').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#abolish').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_parentrightdele').addClass('bounceOutUp').fadeOut();
		});
	});
	/*安全作业详情*/
	$('#security_detail').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_securitydetail').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
		
		$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_securitydetail').addClass('bounceOutUp').fadeOut();
		});
	});

/*教学相册上传*/
	$('#btn_studyphoto').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_studyphoto').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
		
		$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_studyphoto').addClass('bounceOutUp').fadeOut();
		});
	});
/*删除*/
$('#studyphoto_dele').click(function(){
		className = $(this).attr('class');
		$('#dialogBg').fadeIn(300);
		$('#dialog_studyphotodele').removeAttr('class').addClass('animated '+className+'').fadeIn();
	});
	
	/*关闭按钮*/
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_studyphotodele').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#abolish').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_studyphotodele').addClass('bounceOutUp').fadeOut();
		});
	});
	
	//信息弹出框
	$('.claseDialogBtn').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_message').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#sure').click(function(){
		$('#dialogBg').fadeOut(300,function(){
			$('#dialog_message').addClass('bounceOutUp').fadeOut();
		});
	});
	
	$('.claseDialogBtn').click(function(){
		$('#dialogBg_tacher').fadeOut(300,function(){
			$('#dialog_messagetea').addClass('bounceOutUp').fadeOut();
		});
	});
	$('#sure').click(function(){
		$('#dialogBg_tacher').fadeOut(300,function(){
			$('#dialog_messagetea').addClass('bounceOutUp').fadeOut();
		});
	});
});