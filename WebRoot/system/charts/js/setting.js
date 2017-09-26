function doinit(){
	getModules();
	$(".charts_li").click(function(){
		$(".charts_li").removeClass("charts_li_clicked");
		$(this).addClass("charts_li_clicked");
	});
	$('.charts_modules').quickFlip({
        vertical : true
    });
    $(".charts_switch").click(function(){
    	var src = $("#charts_more").prop("src");
    	imgName = src.substr(src.lastIndexOf("/"),src.length);
    	if(imgName=="/switch.png"){
    		$(".redPanel .quickFlipCta").trigger("click");
    		$("#charts_more").prop("src",imgPath+"/charts/switch_2.png");
    	}else{
    		$(".redPanel .quickFlipCta").trigger("click");
    		$("#charts_more").prop("src",imgPath+"/charts/switch.png");
    	}
    	
    });
}

function getModules(){
	var requestUrl=contextPath+"/tfd/system/charts/act/ChartsAct/getChartsModules.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var html = "";
			if(data.length>=10){
				$(".charts_switch").show();
			}
			$.each(data,function(index,data){
					if(index==0){
						html += "<div class=\"redPanel\" ><a class=\"quickFlipCta\" href=\"javascript:void(0);\" ></a><ul id=\"charts_ul\" ><li class=\"charts_li charts_li_clicked\" onclick=\"javascript:setModule('"+data.chartsModule+"','"+data.chartsName+"')\" id="+data.chartsModule+" >"+data.chartsName+"</li>";
						setModule(data.chartsModule,data.chartsName);
					}else if(index==10){
						html += "</ul></div><div class=\"blackPanel\" ><a class=\"quickFlipCta\" href=\"javascript:void(0);\" ></a><ul id=\"charts_ul\" >";
						html += "<li class=\"charts_li\" onclick=\"javascript:setModule('"+data.chartsModule+"','"+data.chartsName+"')\" id="+data.chartsModule+" >"+data.chartsName+"</li>";
					}else{
						html += "<li class=\"charts_li\" onclick=\"javascript:setModule('"+data.chartsModule+"','"+data.chartsName+"')\" id="+data.chartsModule+" >"+data.chartsName+"</li>";
					}
			});
			$(".charts_modules").html(html+"<ul></div>");
		}
	});
}

function setModule(module,name){
	$("#module_title").html("设置"+name+"权限");
	var requestUrl=contextPath+"/tfd/system/charts/act/ChartsAct/getPrivByModule.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{module:module},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$('#chartsConfigId').val(data.chartsConfigId);
			$('#deptPriv').val(data.dept);
			$('#deptName').val(data.deptName);
			$('#userPriv').val(data.priv);
			$('#userPrivName').val(data.privName);
			$('#accountId').val(data.user);
			$('#userName').val(data.userName);
		}
	});
}

function setPrivByModule(){
	var requestUrl=contextPath+"/tfd/system/charts/act/ChartsAct/setPrivByModule.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{
			chartsConfigId:$("#chartsConfigId").val(),
			Dept:$('#deptPriv').val(),
			Priv:$('#userPriv').val(),
			User:$('#accountId').val()
			},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data>0){
				top.layer.msg("保存成功");
			}else{
				top.layer.msg("保存失败");
			}
		}
	});
}
