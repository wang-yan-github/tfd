var result=null;
$(function(){
	var PAGE_URL_TYPE_MANAGE=contextPath+"/fixedasset/type/index.jsp";
	var PAGE_URL_TYPE_IMPORT_INDEX=contextPath+"/fixedasset/type/import-index.jsp";
	
	function initPageData(){
	}
	function initPageOpt(){
		if (result!=null) {
			var optionResult=result.optionResult;
			var checkResult=result.checkResult;
			if (optionResult) {
				var checkResultAlert="";
				for(var resultType in checkResult){
					var checkResults=checkResult[resultType];
					if (checkResults.length>0) {
						checkResultAlert+=resultType+":<br/>";
						for (var i = 0; i < checkResults.length; i++) {
							checkResultAlert+="&nbsp;&nbsp;"+checkResults[i]+"<br/>";
						}
					}
				}
				if (checkResultAlert=="") {
					$.messager.alert({
						title:"提示",
						msg:"导入成功！",
						icon:"info",
						fn:function(){
							location.href=PAGE_URL_TYPE_MANAGE;
						}
					});
				}else{
					$.messager.alert({
						title:"错误",
						msg:checkResultAlert,
						icon:"error"
					});
				}
				
			}else{
				$.messager.alert({
					title:"错误",
					msg:"导入失败！",
					icon:"error"
				});
			}
		}
	}
	initPageData();
	initPageOpt();
});