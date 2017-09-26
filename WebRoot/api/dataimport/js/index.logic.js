
var insertResult=null;
var resultData=null;
$(function(){
	var FIELD_CHECK_RESULT="[备注]";
	
    $("#import-form").formValidation({
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
                        message:"请选择xls、xlsx文件！",
                        regexp:/.*(\.xls)|(\.xlsx)$/
                    }
                }
            }
        }
    });

    $("#import-form input[name='fileshow']").on("click",function(){
        $("#import-form input[name='file']").trigger("click");
    });

    $("#import-form input[name='file']").on("change",function(){
        $("#import-form input[name='fileshow']").val($(this).val());
        $("#import-form").data("formValidation").revalidateField("fileshow");
    });
    
    $("#data-require .expand-up").on("click",function(){
    	$(this).hide();
    	$("#data-require .expand-down").show();
    	$(this).parent().find("table").slideUp(20);
    });
    $("#data-require .expand-down").on("click",function(){
    	$(this).hide();
    	$("#data-require .expand-up").show();
    	$(this).parent().find("table").slideDown(20);
    });
    
    
    $.ajax({
		url:contextPath+"/api/dataimport/DataImportAct/dataRequire.act",
		dataType:"json",
		success:function(dataRequire){
			for(var fieldName in dataRequire){
				if ($.trim(dataRequire[fieldName])=="") {
					continue;
				}
				var requireHtml="<tr>" +
						"			<td style='width:150px;'><b>"+fieldName+":</b></td>" +
						"			<td>"+dataRequire[fieldName]+"</td>" +
						"		</tr>"; 
				$("#data-require table").append(requireHtml);
			}
		}
	});
    
    
    if (insertResult!=null) {
    	if (insertResult) {
        	$("#import-alert-title").html("导入成功！").addClass("text-success");
		}else{
        	$("#import-alert-title").html("导入失败，请重试！").addClass("text-danger").parent().addClass("col-xs-offset-2");
		}
	}else{
		if (resultData!=null) {
			$("#import-alert-title").html("请修正导入的数据").addClass("text-danger");
		}
	}
    if (resultData!=null&&(insertResult==null||insertResult)) {
    	function loadResultData(){
    		var resultDataHtml=[];
    		
    		//导入的数据是否为空、字段是否缺失等非数据内容错误信息
    		var otherError=resultData[0][FIELD_CHECK_RESULT];
    		if (otherError.length>0) {
    			$("#other-error").show().html(otherError[0]);
    			$("#import-alert-title").hide();
    			return false;
    		}
    		
    		
    		
    		resultDataHtml=[];
    		
    		$("#result-data").show();
    		//展示列名
    		resultDataHtml.push("<tr>");
    		for(var fieldName in resultData[1]){
    			resultDataHtml.push("<td>");
    			resultDataHtml.push(fieldName);
    			resultDataHtml.push("</td>");
    		}
    		resultDataHtml.push("</tr>");
    		$("#column-name").html(resultDataHtml.join(""));
    		
    		resultDataHtml=[];
    		
    		//展示数据及导入的结果
    		for (var i = 1; i < resultData.length; i++) {
    			var trClass=resultData[i][FIELD_CHECK_RESULT].length>0?"bg-danger":"";
    			resultDataHtml.push("<tr class='"+trClass+"'>");
    			for(var fieldName in resultData[i]){
    				var fieldValue=resultData[i][fieldName];
    				
    				resultDataHtml.push("<td>");
    				if (fieldName==FIELD_CHECK_RESULT) {
    					if (fieldValue.length>0) {
    						for (var j = 0; j < fieldValue.length; j++) {
    							resultDataHtml.push("<div>"+fieldValue[j]+"</div>");
    						}
    					}else{
    						resultDataHtml.push("<div class='text-success'><span class='glyphicon glyphicon-ok'></span></div>");
    					}
    				}else{
    					resultDataHtml.push(fieldValue);
    				}
    				resultDataHtml.push("</td>");
    			}
    			resultDataHtml.push("</tr>");
    		}
    		$("#column-data").html(resultDataHtml.join(""));
    	}
    	loadResultData();
	}
});
