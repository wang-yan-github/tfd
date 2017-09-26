var Id="";
$(function () {
	if(status==1){
		$(".histbtn").click(function(){
			new SysFrame().tabs('close','查看公告');
		});
		$(".histbtn").text("关闭");
	}else{
		$(".histbtn").click(function(){
			history.back();
		});
	}
	var requestUrl =contextPath+"/notice/act/NoticeAct/getIdnoticAct.act";
	getnotice(requestUrl);
});
function getnotice(url){
	$.ajax({
			url:url,
			dataType:"json",
			data:{noticeId:noticeId},
			async:false,
			success:function(data){
				if(!jQuery.isEmptyObject(data)){
					Id=data.Id;
				var attachId=data.attachId;
				if(data.attachPower==1){
				readAttachDiv(attachId,"attach");
				}else{
					readOnlyAttachDiv(attachId,"attach");
				}
				$("#title").html("<h3>["+data.typeName+"]"+data.noticeTitle+"</h3>");
				$("#noticeContent").html(data.noticeContent);
				$("#foot").html("<a href=javascript:showPersonal('"+data.createUser+"')>"+data.createName+"</a>&nbsp;发布于:  "+data.createTime+"  点击数：  "+data.oncount);
				readnotice();
			}
			}
		});
}
function readnotice(){
	var Url =contextPath+"/notice/act/NoticeAct/setreadnoticeAct.act";
					$.ajax({
						url:Url,
						dataType:"text",
						data:{noticeId:noticeId},
						async:false,
						success:function(data){
						}
					});
}
function downNotice(){
	var requestUrl =contextPath+"/notice/act/NoticeAct/downNoticeAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{Id:Id},
			async:false,
			success:function(data){
				if(!jQuery.isEmptyObject(data)){
					Id=data.Id;
				attachId=data.attachId;
				if(data.attachPower==1){
				readAttachDiv(attachId,"attach");
				}else{
					readOnlyAttachDiv(attachId,"attach");
				}
				$("#title").html("<h3>["+data.typeName+"]"+data.noticeTitle+"</h3>");
				$("#noticeContent").html(data.noticeContent);				
				$("#foot").html("<a href=javascript:showPersonal('"+data.createUser+"')>"+data.createName+"</a>&nbsp;发布于:  "+data.createTime+"  点击数：  "+data.oncount);
					var Url =contextPath+"/notice/act/NoticeAct/setreadnoticeAct.act";
					$.ajax({
						url:Url,
						dataType:"text",
						data:{noticeId:data.noticeId},
						async:false,
						success:function(data){
						}
					});
					
			}else{
				parent.layer.msg('已没有下一篇公告',function(){});
			}
			}
		});
}
function upNotice(){
	var attachId;
	var requestUrl =contextPath+"/notice/act/NoticeAct/upNoticeAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{Id:Id},
			async:false,
			success:function(data){
				if(!jQuery.isEmptyObject(data)){
					Id=data.Id;
				attachId=data.attachId;
				if(data.attachPower==1){
				readAttachDiv(attachId,"attach");
				}else{
					readOnlyAttachDiv(attachId,"attach");
				}
				$("#title").html("<h3>["+data.typeName+"]"+data.noticeTitle+"</h3>");
				$("#noticeContent").html(data.noticeContent);				
				$("#foot").html("<a href=javascript:showPersonal('"+data.createUser+"')>"+data.createName+"</a>&nbsp;发布于:  "+data.createTime+"  点击数：  "+data.oncount);
					var Url =contextPath+"/notice/act/NoticeAct/setreadnoticeAct.act";
					$.ajax({
						url:Url,
						dataType:"text",
						data:{noticeId:data.noticeId},
						async:false,
						success:function(data){
						}
					});
					
			}else{
				parent.layer.msg('已没有上一篇公告',function(){});
			}
			}
		});
}
