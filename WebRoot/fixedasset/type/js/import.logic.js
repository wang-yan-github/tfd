$(function(){
	var REQUEST_URL_TEMPLATE_DOWNLOAD=contextPath+"/fixedasset/act/FixedassetTypeAct/templateDownload.act";
	var PAGE_URL_TYPE_MANAGE=contextPath+"/fixedasset/type/index.jsp";
	
	function importBatch(){
		var file=$("#import-file").filebox("getValue");
		var msg="";
		if (file!="") {
			if (file.lastIndexOf(".xls")+3==file.length-1) {
				$("#import-form").get(0).submit();
			}else{
				msg="只允许为.xls的excel文件！";
			}
		}else{
			msg="请选择文件！";
		}
		if (msg!="") {
			$.messager.alert({
				title:"提示",
				msg:msg,
				icon:"info"
			});
		}else{
			window.setTimeout(function(){
				$("#protective-screen").show();
				$("#import-progress").show();
			},200);
			
			var i=1;
			var progressInterval=window.setInterval(function(){
				$("#import-progress .progress-bar").html(i+"%").css({
					"width":i+"%"
				});
				if (i==99) {
					window.clearInterval(progressInterval);
				}
				i++;
			},20);
		}
	}
	function initPageData(){
		
	}
	function initPageOpt(){
		$("#opt-back").click(function(){
			location.href=PAGE_URL_TYPE_MANAGE;
		});
		$("#opt-template-download").click(function(){
			window.open(REQUEST_URL_TEMPLATE_DOWNLOAD);
		});
		$("#opt-import").click(function(){
			importBatch();
		});
	}
	initPageData();
	initPageOpt();
});