<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻阅读情况</title>
<%String newsId=request.getParameter("newsId"); %>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/news/news.css"></link>
<script>
var newsId="<%=newsId%>";
var menuData;
$(function(){
	getDeptJson();
	getNewsId();
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
			}
			}
		});
});
function getDeptJson(){
	requestUrl=contextPath+"/tfd/system/unit/dept/act/DeptAct/getDeptTreeAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
		},
		success:function(data){
			menuData = data;
			var menu = findChilds("0");
			var html = "";
			$.each(menu,function(index,menu){
				html += "<tr id=\""+menu.id+"\"><td width='40%'>├"+menu.name+"</td><td class='foot'></td><td class='foot'></td></tr>";
				childHtml = "";
				createChildHtml(1,menu.id);
				html += childHtml;
			});
			$("#deptSelect").append(html);
		}
	});
}
function findChilds(id){
	var childs=[];
	for(var i=0;i<menuData.length;i++){
		if(menuData[i].pId==id){
			childs.push(menuData[i]);
		}
	}
	return childs;
}
function createChildHtml(classi,id){
	var childs=findMenuChilds(id);
	if(childs.length>0){
		classi++;	
	}
	for(var i=0;i<childs.length;i++){
		var cs=findMenuChilds(childs[i].id);
		var seriesNum=series(childs[i].pId,num);
		num=1;
		var con="";
		for(var j=0;j<seriesNum;j++){
			con+="&nbsp;&nbsp;&nbsp;";
		}
		childHtml+="<tr id=\""+childs[i].id+"\"><td width='40%'>"+con+"├"+childs[i].name+"</td><td class='foot'></td><td class='foot'></td></tr>";
		createChildHtml(classi,childs[i].id);
	}
}
var num=1;
function series(Pid){
	var returnData=num;
	for(var i=0;i<menuData.length;i++){
		if(menuData[i].id==Pid){
			Pid=menuData[i].pId;
			if(Pid=="0"){
				break;
			}else{
			num++;
			returnData = series(Pid);
			break;
			}
		}
	}
	return returnData;
}
function findMenuChilds(id){
	var childs=[];
	for(var i=0;i<menuData.length;i++){
		if(menuData[i].pId==id){
			childs.push(menuData[i]);
		}
	}
	return childs;
} 
//获取所有可读人员的信息
function getNewsId(){
	var readUsername=[];
	requestUrl=contextPath+"/news/act/NewsAct/getIdreadstatusAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{newsId:newsId},
		async:false,
		error:function(e){
		},
		success:function(data){
				readUsername=getreadUser();
				$("#noread").html(data.length-readUsername.length);
				$("#read").html(readUsername.length);
				for(var j=0;j<readUsername.length;j++){
					for(var i=0;i<data.length;i++){
					if(data[i].userName==readUsername[j]){
						$("#"+data[i].deptId+" td:eq(1)").append(data[i].userName+"&nbsp;&nbsp;");
						data.splice(i,1);
					}
				}
			}
				for(var i=0;i<data.length;i++){
					$("#"+data[i].deptId+" td:eq(2)").append(data[i].userName+"&nbsp;&nbsp;");
				}
		}
	});
}
//获取已读人员的信息
function getreadUser(){
	var returnData=[];
	requestUrl=contextPath+"/news/act/NewsAct/getreadUserAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"text",
		data:{newsId:newsId},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=""){
				returnData=data.split(",");
			}
		}
	});
	return returnData;
}
</script>
<style>
.foot{
background-color: white;
width: 30%;
}
table{
table-layout:fixed;
word-break:break-all;
}
</style>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >查阅情况
</span>
</div>
</div>
<div align="center"><div id="title" style="font-weight:bold;font-size: 16px;line-height: 50px;background-color: white;"></div></div>
   <div id="foot" style="font-size: 14px;background-color: #F2F2F2;line-height: 30px;height: 30px;"align="right"></div></div>
<table class="table " >
<tr align="center" style="background-color: white;">
<td width="40%">
部门/成员单位
</td>
<td width="30%">
已读人员
</td>
<td width="30%">
未读人员
</td>
</tr>
<tbody id="deptSelect">

</tbody>
<tr>
<td align="center">合计：</td>
<td id="read" class="foot">
</td>
<td id="noread" class="foot">
</td>
</tr>
</table>
<div align="center">
<button type="button" class="btn btn-default" onclick="history.back();">返回</button>
</div>
</body>
</html>