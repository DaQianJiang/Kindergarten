/**
 * 相册管理
 */

$(function(){
	var param ={};
	var classNum = $('#duty_class').val();
	init();
	
	function init(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/qryPhotoMessage.do", 
			scriptCharset: 'utf-8',
			data : {
				classNum : classNum,
			},
			success : function(data) {
/*				param ={
						"list" : data
				}
				$.fillTpl("data_list","T_list", param);*/
				
				$(".photo_album").html("");
				console.log("相应数据",data);
				
				for(var i=0;i<data.length;i++){
					var img = "<img src=/Kindergarten/YouErYuan/photo/uploadTeachPhoto/"+data[i].img+" height=\"100\" width=\"100\" style=\"padding-left:20px; border-radius:5px;\" />";
					$(".photo_album").append(img);
				}
			}
		});
	}
	$('#serch_msg_photo').click(function(){
		classNum = $('#duty_class').val();
		init();
	});
	$(".photo_album").on("click","img",function(e){
		console.log("e",e);
		$("#dialogBg_photo").show();
		$("#dialogBg_photo img").attr("src",$(this).attr("src"));
	});
	
	$("#dialogBg_photo").on("click",".photo_X",function(){
		$("#dialogBg_photo").hide();
	})
});



function studyphoto_dele(obj){
	className = $(obj).attr('class');
	$('#dialogBg').fadeIn(300);
	$('#dialog_studyphotodele').removeAttr('class').addClass('animated '+className+'').fadeIn();
	var photoId = $(obj).find("#photoId").text();
	$('#sure_delete').click(function(){
		$.ajax({
			type : "POST",
			dataType : 'json',
			url: getRootPath()+"/delPhotoMsg.do", 
			scriptCharset: 'utf-8',
			data : {
				photoId : photoId
			},
			success : function(data) {
				console.log("响应的数据：", data);
				
				alert(data.retDesc);
				window.location = getRootPath()+"/YouErYuan/administor_studyphoto.html";
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

(function(e){function u(u){function c(){function h(e){e.show();n.removeClass("loading")}var t=e(this),r=parseInt(n.css("borderLeftWidth")),i=s-r*2,u=o-r*2,a=t.width(),f=t.height();if(a==n.width()&&a<=i&&f==n.height()&&f<=u){h(t);return}if(a>i||f>u){var l=u<f?u:f,c=i<a?i:a;if(l/f<=c/a){t.width(a*l/f);t.height(l)}else{t.width(c);t.height(f*c/a)}}n.animate({width:t.width(),height:t.height(),marginTop:-(t.height()/2)-r,marginLeft:-(t.width()/2)-r},200,function(){h(t)})}if(u)u.preventDefault();var a=e(this),f=a.attr("href");if(!f)return;var l=e(new Image).hide();e("#zoom .previous, #zoom .next").show();if(a.hasClass("zoom"))e("#zoom .previous, #zoom .next").hide();if(!r){r=true;t.show();e("body").addClass("zoomed")}n.html(l).delay(500).addClass("loading");l.load(c).attr("src",f);i=a}function a(){var t=i.parent("li").prev();if(t.length==0)t=e(".gallery li:last-child");t.find("a").trigger("click")}function f(){var t=i.parent("li").next();if(t.length==0)t=e(".gallery li:first-child");t.children("a").trigger("click")}function l(s){if(s)s.preventDefault();r=false;i=null;t.hide();e("body").removeClass("zoomed");n.empty()}function c(){s=e(window).width();o=e(window).height()}e("body").append('<div id="zoom"><a class="close"></a><a href="#previous" class="previous"></a><a href="#next" class="next"></a><div class="content loading"></div></div>');var t=e("#zoom").hide(),n=e("#zoom .content"),r=false,i=null,s=e(window).width(),o=e(window).height();(function(){t.on("click",function(t){t.preventDefault();if(e(t.target).attr("id")=="zoom")l()});e("#zoom .close").on("click",l);e("#zoom .previous").on("click",a);e("#zoom .next").on("click",f);e(document).keydown(function(e){if(!i)return;if(e.which==38||e.which==40)e.preventDefault();if(e.which==27)l();if(e.which==37&&!i.hasClass("zoom"))a();if(e.which==39&&!i.hasClass("zoom"))f()});if(e(".gallery li a").length==1)e(".gallery li a")[0].addClass("zoom");e(".zoom, .gallery li a").on("click",u)})();(function(){e(window).on("resize",c)})();(function(){e(window).on("mousewheel DOMMouseScroll",function(e){if(!i)return;e.stopPropagation();e.preventDefault();e.cancelBubble=false})})()})(jQuery);
