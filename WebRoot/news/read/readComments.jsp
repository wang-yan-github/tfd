<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%String newsId=request.getParameter("newsId"); %>
<% String userName = session.getAttribute("USER_NAME").toString(); %> 
<%String status=request.getParameter("status"); %>
<title>查看新闻评论</title>
<script>
var newsId="<%=newsId%>";
var userName="<%=userName%>";
var status="<%=status%>";
$(function () {
	getNewsId();
});
function getNewsId(){
	var requestUrl =contextPath+"/news/act/NewsAct/getNewsByNewsIdAct.act";
	$.ajax({
			url:requestUrl,
			dataType:"json",
			data:{newsId:newsId},
			async:false,
			success:function(data){
				if(!jQuery.isEmptyObject(data)){
				$("#title").html("["+data.newstype+"]"+data.title);
				$("#foot").html(data.createName+"&nbsp;&nbsp;发布于:&nbsp;&nbsp;"+data.createTime+"&nbsp;&nbsp;点击数："+data.onclickcount+"&nbsp;&nbsp;&nbsp;");
					if(data.commentStatus=="2"){
						$("#commentsdiv").hide();
							$("#newsComments").hide();
							$("#backbtn").show();
						}else{
						if(data.commentStatus=="0"){
						$("#commName").prop("readonly",'readonly');
						$("#commName").val(userName);
					}
						getComments();
					}
			}
			}
		});
}

function getComments(){
	$("#myTable").datagrid({
	width:'100%',
	rows:5,
	scrollbarSize :0,
    collapsible: true,
    url: contextPath+"/news/act/NewsCommentsAct/getCommentsAct.act",
    method: 'POST',
    loadMsg: "数据加载中...",
    pagination:true,
    sortName: 'COMM_TIME',
	sortOrder: 'DESC',
    singleSelect:true,  
    queryParams:{
    	newsId:newsId
    },
    columns:[[
   	 {title: '全部评论', field: 'ID', width: '100%', align: 'center',
   		formatter: function(value, rowData, rowIndex){
   			var tbodycon="";
   			tbodycon+="<table class=\"table\"><tr><td align=\"left\" class=\"top\"><div class=\"topName\">"+rowData.COMM_NAME+"</div><div class=\"topTime\">发布时间："+rowData.COMM_TIME+"</div></td></tr>"+
			  "<td align=\"left\" id=\"con"+rowData.COMM_ID+"\">"+rowData.COMM_CONTECT;
			  if(rowData.P_CONTECT!=undefined){
				  tbodycon+="<div class=\"boderdiv\"></div><span class=\"commPcon\">[原帖]</span></br>"+rowData.P_CONTECT;
			  }
			  tbodycon+= "</td></tr>"+
			  "<td align=\"right\"><a href=\"javascript:void(0);\" onclick=\"replyComm('"+rowData.COMM_ID+"','"+rowData.COMM_NAME+"')\" >回复本帖</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
			  "<a href=\"javascript:void(0);\" onclick=\"delComments('"+rowData.COMM_ID+"');\">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;回复数："+rowData.REPLYNUM+"</td></tr></table>";
			  return tbodycon;
   		}
   	 }
    ]]
});
 
var p = $('#myTable').datagrid('getPager');  
    $(p).pagination({  
    pageSize: 10,//每页显示的记录条数，默认为5  
    pageList: [10, 20, 30 ,50],//可以设置每页记录条数的列表  
    beforePageText: '第',//页数文本框前显示的汉字  
    afterPageText: '页    共 {pages} 页',  
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
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
								getComments();
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
						getComments();
						delreply();
					}
				}
			});
}
</script>
<style >
.top{
line-height: 50px;
font-size: 14px;
}
.topName{
float:left;
margin-left: 10px;
width: 80px;
}
.topTime{
font-size:12px;
float: left;
margin-left: 50px;
}
.boderdiv{
width:90%;
border:solid 1px #9A9A9A;
}
.commPcon{
font-weight: bold;
}
</style>
</head>
<body>
<div class="panel-body" style="width: 98%;margin-left: 1%;margin-top: 10px;">
   <div>
   <div align="center"><div id="title" style="font-weight:500;font-size: 16px;line-height: 40px;"></div></div>
   <div id="foot" style="font-size: 14px;background-color: #F2F2F2;line-height: 30px;height: 30px;"align="right"></div></div>
   </div>
   </div>
   </div>
   
   <div  class="panel panel-info" style="width: 98%;margin-left: 1%;text-align: center;margin-top: 20px;" id="commentsdiv">
   <div  class="panel-body">
   <div id="myTable"></div>
   </div>
   </div>



<div id="newsComments" name="newsComments" class="panel panel-info" style="width:98%;margin-left: 1%;text-align: center;" >
   <div  class="panel-body">
   <table class="table table-striped table-condensed">
   <tr>
   <td colspan="2" align="center" style="font-weight: bold;font-size: 14px;" id="titleName"> 发表评论</td>
   </tr>
   <tr id="commPname"></tr>
   <tr>
   <td width="100px;">署名：</td>
   <td>
   <input id="commPid" name="commPid" type="hidden" />
   <div class="col-xs-3 form-group">
   <input id="commName" name="commName" type="text" class="form-control" maxlength="15"/>
   </div>
   </td>
   </tr>
   <tr>
   <td> 内容：</td>
   <td>
   <div class="col-xs-6 form-group">
   <textarea rows="5" cols="50" class="form-control" id="commContect" name="commContect"></textarea>
   </div>
   </td>
   </tr>
   </table>
   <div align="center">
   <button type="button" class="btn btn-primary"  onclick="saveComments();" id="savebtn">发表</button>
   <button type="button" class="btn btn-default" onclick="history.back();">返回</button>
   </div>
      </div>
      </div>
</body>
</html>