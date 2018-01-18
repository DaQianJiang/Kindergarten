$(document).ready(function(){
			$('#defaultForm').bootstrapValidator({
				 message: 'This value is not valid',
				 feedbackIcons: {
				 	 valid: 'glyphicon glyphicon-ok',
                     invalid: 'glyphicon glyphicon-remove',
                     validating: 'glyphicon glyphicon-refresh'
				 },
				 fields:{
				 	username:{
				 		message:'The username is not valid',
				 		validators:{
				 			notEmpty:{
				 				message:"用户名不能为空"
				 			},
				 		    stringLength:{
				 		    	min:6,
				 		    	max:6,
				 		    	message:"职工号或学号为6位"
				 		    },
				 		    threshold :  6,
/*				 		    remote:{
				 		    	url:'/KindergartenMgSystem/checkUsername',
				 		    	message:'用户名已存在',
				 		    	delay:2000,
				 		    	type:'POST',
								data:{
									username:function(){return $("#username").val();}
								}
							}*/
				 		}
				 	},
				 	user_password1:{
				 		message:'密码错误',
				 		validators:{
				 			notEmpty:{
				 				message:"密码不能为空"
				 			},
				 			stringLength:{
				 				min:6,
				 				max:10,
				 				message:"密码6到10位"
				 			},
				 			threshold:6,
				 		}
				 	},

				 	user_password2:{
				 		message:'不能为空',
				 		validators:{
				 			notEmpty:{
				 				message:'不能为空'
				 			},
				 			identical:{
				 				field:'user_password1',
				 				message:"密码不一致"
				 			}
				 		}
				 	},

				 }
				 
			})


	$('#defaultLoginForm').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields:{
			userAccount:{
				message:'The username is not valid',
				validators:{
					notEmpty:{
						message:"用户名不能为空"
					},
					stringLength:{
						min:6,
						max:6,
						message:"职工号或学号为6位"
					},
					threshold :  6
				}
			},
			password1:{
				message:'密码错误',
				validators:{
					notEmpty:{
						message:"密码不能为空"
					},
					stringLength:{
						min:6,
						max:10,
						message:"密码6到10位"
					},
					threshold:6,
				}
			}

		}

	})
	
/*$("#btn_de").click(function(){
	console.log(1111);
	var data=$("#student_table").bootstrapTable('getSelections')[0];
	console.log(data);
	})

$("#btn_edit").click(function(){
	var data=$("#student_table").bootstrapTable('getSelections')[0];
	console.log(data);
	$(".form-control1").eq(0).val(data.student_name)

})*/


});