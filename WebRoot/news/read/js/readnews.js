$(function () {
	getNewsId();
	if(status==1){
		$(".histbtn").click(function(){
			new SysFrame().tabs('close','查看新闻');
		});
		$(".histbtn").text("关闭");
	}else{
		$(".histbtn").click(function(){
			history.back();
		});
	}
});
function getNewsId(){
	var attachId;
	var requestUrl =contextPath+"/news/act/NewsAct/getNewsByNewsIdAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{newsId:newsId},
			async:false,
			success:function(data){
				if(!jQuery.isEmptyObject(data)){
				attachId=data.attachId;
				readAttachDiv(attachId,"attach");
				$("#title").html("["+data.typeName+"]"+data.title);
				$("#contect").html(data.contect);
				$("#foot").html("<a href=javascript:showPersonal('"+data.createUser+"')>"+data.createName+"</a>&nbsp;&nbsp;发布于:&nbsp;&nbsp;"+data.createTime+"&nbsp;&nbsp;点击数："+data.onclickcount+"&nbsp;&nbsp;&nbsp;");
					if(data.commentStatus=="2"){
						$("#commentsdiv").hide();
							$("#newsComments").hide();
							$("#backbtn").show();
						}else{
						if(data.commentStatus=="0"){
						$("#commName").prop("readonly",'readonly');
						$("#commName").val(userName);
					}
					getNewsComments();
					}
					readNews();
			}
			}
		});
}
//获取新闻的评论内容 最近五条
function getNewsComments(){
	var url=contextPath+"/news/act/NewsCommentsAct/getlateCommAct.act";
		
			$.ajax({
				url : url,
				type:"POST",
				dataType:"json",
				data : {
					newsId:newsId
				},
				async : false,
				success : function(data) {
					if(!jQuery.isEmptyObject(data)){
						var tbodycon="";
						for (var i=0; i < data.length; i++) {
						  tbodycon+="<tr><td align=\"left\" class=\"top\"><div class=\"topName\"><a href=javascript:showPersonal('"+data[i].accountId+"')>"+data[i].commName+"</a></div><div class=\"topTime\">发布时间："+data[i].commTime+"</div></td></tr>"+
						  "<td align=\"left\" id=\"con"+data[i].commId+"\">"+data[i].commContect+"</td></tr>"+
						  "<td align=\"right\"><a href=\"javascript:void(0);\" onclick=\"replyComm('"+data[i].commId+"','"+data[i].commName+"')\" >回复本帖</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
						  "<a href=\"javascript:void(0);\" onclick=\"delComments('"+data[i].commId+"');\">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;回复数："+data[i].replyNum+"</td></tr>";
						};
						$("#CommentsCon").html(tbodycon);
						for (var i=0; i < data.length; i++) {
						  if(data[i].commPid!=""){
						  	for (var j=0; j < data.length; j++){
								if(data[i].commPid==data[j].commId){
									var conContect="<div class=\"boderdiv\"></div><span class=\"commPcon\">[原帖]</span></br>"+data[j].commContect;
									$("#con"+data[i].commId).append(conContect);
								}
							  };
						  }
						};
					}else{
						$("#CommentsCon").html("<tr><td>无评论记录</td></tr>");	
					}
				}
			});
}

//读新闻处理
function readNews(){
	var Url =contextPath+"/news/act/NewsAct/setReadNews.act";
					$.ajax({
						url:Url,
						dataType:"text",
						data:{newsId:newsId},
						async:false,
						success:function(data){
						}
					}); 
}
//回复帖子
function replyComm(commPid,commName){
	$("#commPid").val(commPid);
	$("#commPname").html("<td colspan='2' align='left' style=\"line-height:30px;\"><div style=\"margin-left:30px;\">回复："+commName+"&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" style=\"color:red;\" onclick=\"delreply();\">X</a></div></td>");
	$("#titleName").text("回复评论");
	$("#savebtn").text("回复");
	if(!$("#commName").prop("readonly")){
							$("#commName").val("");
						}
						$("#commContect").val("");
}
function delreply(){
	$("#commPid").val("");
	$("#commPname").html("");
	$("#titleName").text("发表评论");
	$("#savebtn").text("发表");
	if(!$("#commName").prop("readonly")){
							$("#commName").val("");
						}
						$("#commContect").val("");
}
//删除
function delComments(commId){
	var url=contextPath+"/news/act/NewsCommentsAct/delCommIdAct.act";
					$.ajax({
						url:url,
						dataType:"text",
						data:{commId:commId},
						async:false,
						success:function(data){
							if(data!=0){
								getNewsComments();
							}
						}
					});
}
//保存
function saveComments(){
	var url=contextPath+"/news/act/NewsCommentsAct/addCommAct.act";
			$.ajax({
				url : url,
				type:"POST",
				dataType:"text",
				data : {
					commPid:$("#commPid").val(),
					commContect:$("#commContect").val(),
					newsId:newsId,
					commName:$("#commName").val()
				},
				async : false,
				success : function(data) {
					if(data!=0){
						getNewsComments();
						delreply();
					}
				}
			});
}
function lookComments(){
	var url=contextPath+"/news/read/readComments.jsp?newsId="+newsId+"&status="+status;
	window.location=url;
}
