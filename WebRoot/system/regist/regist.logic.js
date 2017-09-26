$(function(){
	
	
	$.ajax({
		url:contextPath+"/tfd/system/regist/act/RegistAct/readReg.act",
		type:"POST",
		dataType:"text",
		success:function(regStr){
			if (regStr!="") {
				var reg=eval("("+regStr+")");
				for(var fieldName in reg){
					if ($("#"+fieldName).length>0) {
						$("#"+fieldName).html(reg[fieldName]);
					}
				}
				$("#validate-result").html(reg["validateResult"]=="true"?"已注册":"未注册").attr("result",reg["validateResult"]);
			}
		}
	});
	
	$("#reg-form").formValidation({
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
            fileshow:{
                validators:{
                    notEmpty:{
                        message:"请选择"
                    },
                    regexp:{
                        message:"请选择文件reg.lsq",
                        regexp:/.*reg\.lsq$/
                    }
                }
            }
        }
    });

    $("#reg-form input[name='fileshow']").on("click",function(){
        $("#reg-form input[name='file']").trigger("click");
    });

    $("#reg-form input[name='file']").on("change",function(){
        $("#reg-form input[name='fileshow']").val($(this).val());
        $("#reg-form").data("formValidation").revalidateField("fileshow");
    });
    
    $("#productName").on("click",function(){
    	location.href=contextPath+"/index.jsp";
    });
});