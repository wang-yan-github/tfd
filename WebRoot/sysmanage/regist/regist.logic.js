$(function(){
	var productMap=new Object();
	$.ajax({
		url:contextPath+"/sysmanage/product/act/ProductAct/productMap.act",
		type:"POST",
		dataType:"json",
		success:function(data){
			productMap=data;
			for(var productName in productMap){
				$("form *[name='productName']").append("<option value='"+productName+"'>"+productName+"</option>");
			}
		}
	});
	$("form *[name='productName']").on("change",function(){
		var productList=productMap[$(this).val()];
		if (!productList) {
			productList=[];
		}
		var optionHtml="<option value=''>请选择</option>";
		for (var i = 0; i < productList.length; i++) {
			optionHtml+="<option value='"+productList[i].version+"'>"+productList[i].version+"</option>";
		}
		$("form *[name='productVersion']").html(optionHtml);
		$("form *[name='productSn']").val("");
		$("form *[name='productTeam']").val("");
		$("form *[name='productSite']").val("");

		$("form").data('formValidation')
	    .updateStatus("productSn", 'NOT_VALIDATED')
	    .validateField("productSn");
	});
	
	$("form *[name='productVersion']").on("change",function(){
		var productList=productMap[$("form *[name='productName']").val()];
		var find=false;
		if (productList) {
			for (var i = 0; i < productList.length; i++) {
				if(productList[i].version==$(this).val()){
					find=true;
					$("form *[name='productSn']").val(productList[i].sn);
					$("form *[name='productTeam']").val(productList[i].productTeam);
					$("form *[name='productSite']").val(productList[i].productSite);
					break;
				}
			}
		}
		if(!find){
			$("form *[name='productSn']").val("");
			$("form *[name='productTeam']").val("");
			$("form *[name='productSite']").val("");
		}
		$("form").data('formValidation')
	    .updateStatus("productSn", 'NOT_VALIDATED')
	    .validateField("productSn");
	});
	
	
	$("form").formValidation({
        framework:"bootstrap",
        err:{
            container:"tooltip"
        },
        icon:{
            valid:"glyphicon glyphicon-ok",
            invalid:"glyphicon glyphicon-remove",
            validating:"glyphicon glyphicon-refresh"
        },
        fields:{
            productName:{
                validators:{
                    notEmpty:{
                        message:"请选择"
                    }
                }
            },
            productVersion:{
                validators:{
                    notEmpty:{
                        message:"请选择"
                    }
                }
            },
            productSn:{
                validators:{
                    notEmpty:{
                        message:"不可为空"
                    }
                }
            },
            registUnit:{
                validators:{
                    notEmpty:{
                        message:"请填写"
                    }
                }
            },
            registUserCount:{
                validators:{
                    notEmpty:{
                        message:"请填写"
                    },
					regexp:{
						message:"请填写大于0的数字！",
						regexp:new RegexUtil().REGEX_INT_1_
					}
                }
            },
            registImUserCount:{
                validators:{
                    notEmpty:{
                        message:"请填写"
                    },
					regexp:{
						message:"请填写大于0的数字！",
						regexp:new RegexUtil().REGEX_INT_1_
					}
                }
            },
            registDiskSn:{
                validators:{
                    notEmpty:{
                        message:"请填写"
                    }
                }
            },
            registTime:{
                validators:{
                    notEmpty:{
                        message:"请填写"
                    },
					regexp:{
						message:"请填写正确日期！",
						regexp:new RegexUtil().REGEX_DATE_ALL
					}
                }
            },
            registDeadline:{
                validators:{
                    notEmpty:{
                        message:"请填写"
                    },
					regexp:{
						message:"请填写正确日期！",
						regexp:new RegexUtil().REGEX_DATE_ALL
					}
                }
            }
        }
    })
	.on("success.form.fv",function(e){//表单验证成功
		e.preventDefault();//阻止自动提交
		
		//自定义提交方式
		var $form=$(e.target);
		$.ajax({
			url:contextPath+"/sysmanage/regist/act/RegistAct/regist.act",
			data:$form.serialize(),
			async:false,
			type:"POST",
			dataType:"text",
			success:function(resitResult){
				if(resitResult=="true"){
					top.layer.msg('注册成功！',function(){
						location.reload();
					});
				}
			}
		});
		
	});
	
	
	$("form *[name='registTime']").datetimepicker({
		locale:"zh-cn",
		format:"YYYY-MM-DD"
    });
	$("form *[name='registDeadline']").datetimepicker({
		locale:"zh-cn",
		format:"YYYY-MM-DD"
    });
});