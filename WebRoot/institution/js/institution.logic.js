$(function(){
	var height = $(window).height();
	if(id != "null"){
		$("#inst-content").show();
		$(".MessageBox").hide();
		var requestUrl= contextPath+'/institution/act/InstitutionAct/getInstitutionById.act';
		$.ajax({
			url:requestUrl,
			data:{id:encodeURIComponent(id)},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=="[]"||data==""||data==null||data=="undefinde"){
					$("#inst-content").hide();
					$(".MessageBox").show();
					$(".msg-content").html("请选择制度进行查看");
				}else{
					$("#inst-content").show();
					$(".MessageBox").hide();
					$('.top').html(data[0].name);
					
					$('#content').html(data[0].content);
					attachId=data[0].attachId;
					if(attachId!=""&&attachId!=null&&attachId!="null"){
						$("#attachDiv").show();
						readAttachDiv(attachId,"attach");
					}
					$('#info').html("<p style='font-size:12px;' >修订人:<a onclick=\"javascript:showPersonal('"+data[0].accountId+"')\" href=\"javascript:void(0)\" >"+data[0].username+"</a></p><p style='font-size:12px;' >修订时间 : "+data[0].time+"</p>");
				}
			}
		});
		requestUrl=contextPath+'/institution/act/InstitutionAct/getInstCommentByInstId.act';
		$.ajax({
			url:requestUrl,
			data:{id:encodeURIComponent(id)},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=="[]"||data==""||data==null||data=="undefinde"){
					
				}else{
					var str = "";
					$.each(data,function(i,d1){
						if(d1.pId == null){
							str += "<div id=\"reply"+d1.id+"\" class=reply><div style=\"color:blue;\" ><a onclick=\"javascript:showPersonal('"+d1.accountId+"')\" href=\"javascript:void(0)\" >"
								+d1.username+"</a>:<div class=\"timeStyle\" >"
								+d1.time+"</div></div><div style=\"word-break: break-all;word-wrap: break-word;\" id=\"content"+d1.id+"\" >"
								+d1.content+"<div class=\"opt\" ><span><a href=\"javascript:void(0)\" onclick=\"javascript:reply('"+d1.id+"');\">回复</a></span></div></div><div id=\"recontent"+d1.id+"\" style=\"display:none;\" ><input type=\"text\" class=\"textStyle\"  /><input class=\"buttonStyle\" type=\"button\" value=\"回复\" onclick=\"javascript:doReply('"+d1.id+"','"+d1.id+"');\" /></div></div>";
						}
					});
					$('.comments').html(str);
					$.each(data,function(i,d1){
						if(d1.pId == null){
							$.each(data,function(s,d2){
								var repylHtml = "";
								if(d2.pId == d1.id){
									repylHtml += "<div style=\"width:98%;margin-left:2%;\" id=\"reply"+d2.id+"\" class=\"reply\"><div style=\"color:blue;\" ><a onclick=\"javascript:showPersonal('"+d2.accountId+"')\" href=\"javascript:void(0)\" >"+d2.username+"</a>:<div class=\"timeStyle\" >"+d2.time+"</div></div><div style=\"word-break: break-all;word-wrap: break-word;\" id=\"content"+d2.id+"\" >"+d2.content+"<div class=\"opt\" ><span><a href=\"javascript:void(0)\" onclick=\"javascript:reply('"+d2.id+"');\" >回复</a></span></div></div><div id=\"recontent"+d2.id+"\" style=\"display:none;\" ><input type=\"text\" class=\"textStyle\"  /><input class=\"buttonStyle\" type=\"button\" value=\"回复\" onclick=\"javascript:doReply('"+d1.id+"','"+d2.id+"');\" /></div></div>";
								}
								$("#reply"+d1.id).after(repylHtml);
							});
						}
					});
				}
			}
		});
	}else{
		$("#inst-content").hide();
		$(".MessageBox").show();
		$(".msg-content").html("请选择制度进行查看");
	}
	$('#comment_ok').click(function(){
		var comPid = null;
		var content = $('#com_content').val();
		requestUrl=contextPath+'/institution/act/InstitutionAct/addInstComment.act';
		$.ajax({
			url:requestUrl,
			data:{comPid:encodeURIComponent(comPid),content:content,instId:encodeURIComponent(id)},
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				top.layer.msg("评论成功");
				history.go(0);
				return false;
			}
		});
	});
});
function reply(i){
	var height = $("#recontent"+i).height();
	var width = $(window).width();
	$(".textStyle").css("width",(width-80)+"px");
	if(document.getElementById("recontent"+i).style.display == "none"){
		$("#reply"+i).css("min-height",(height+30)+"px");
		$("#recontent"+i).css("display","block");
	}else{
		$("#recontent"+i).css("display","none");
		$("#reply"+i).css("min-height",height+"px");
	}
}
function doReply(comPid,i){
	var content = $('#recontent'+i+" .textStyle").val();
	requestUrl=contextPath+'/institution/act/InstitutionAct/addInstComment.act';
	$.ajax({
		url:requestUrl,
		data:{comPid:encodeURIComponent(comPid),content:content,instId:encodeURIComponent(id)},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			top.layer.msg("回复成功");
			history.go(0);
			return false;
		}
	});
}