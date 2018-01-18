$(document).ready(function(){
	$('#enterstu_btn').click(function(){
		 var stname=$("#student_name").val();
		var student_number=$("#student_number").val();
		 console.log(student_number);
		var student_age=$("#student_age").val();
		 var parent_name=$("#parent_name").val();
		 var parent_phone=$("#parent_phone").val();
		 var student_adress=$("#student_adress").val();
		var student_picture=$("#student_adress").val();
		if(stname==""||student_number==""||student_age==""||parent_name==""
				||parent_phone==""||student_adress==""
				||student_adress==""){
			className = $(this).attr('class');
  			$('#dialogBg').fadeIn(300);
  			$('#dialog_message').removeAttr('class').addClass('animated '+className+'').fadeIn();
  			$('.message_info').text('录入信息不能有空')
  				window.location = getRootPath()+"/YouErYuan/administor_enterstuinfo.html";
  			
			
		}
		
	});

});