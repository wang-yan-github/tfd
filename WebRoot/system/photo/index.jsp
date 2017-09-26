<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相册浏览</title>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/noinfo/style.css" type="text/css" />
<style type="text/css">
.photo-img{max-height:120px;max-width:128px;overflow:hidden;}
.photo-div{height:128px;width:128px;text-align:center;line-height:128px;}
.photo-bg{float:left;margin-left:15px;border:#F5F5F5 1px solid;cursor:pointer;z-index:998;}
.photo-bg:hover{background-color:#F1F7FD;border:#B8D6FB 1px solid;}
.photo-bg:hover .good-div,.photo-bg:hover .cover-div{display:block;}
.floder-div{background:url("<%=imgPath%>/filetype/folder.png");width:128px;height:120px;background-repeat:no-repeat;}
.content-body{width:80%;margin-left:100px;margin-top:30px;border:#CCC 1px solid;min-height:400px;padding:0xp;}
.good-div,.cover-div{background:url('<%=stylePath%>/frame/25-32.png');background-repeat:no-repeat;height:18px;width:24px;float:right;margin-right:5px;display:none;}
.cover-div{background-position:-414px -98px;}
.good-div{background-position:-360px -173px;,z-index:999;}
</style>
<script type="text/javascript">
var zNodes ;
$(function(){
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/getPhotoJsonAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if($.isEmptyObject(data)){
				$(".MessageBox").show();
				$(".msg-content").html("暂无相册");
				$("#photo-body").hide();
			}else{
				$(".MessageBox").hide();
				$("#photo-body").show();
				var html="";
				for(var i=0;data.length>i;i++){
					if(data[i].photoCover==null||data[i].photoCover==""){
						html+="<div onclick=\"editinfo('"+data[i].id+"','"+data[i].name+"','"+data[i].photoPath+"')\" class=\"photo-bg\" ><div class=\"photo-div\" ><div class=\"floder-div\" ></div></div><div style=\"text-align:center\" >"+data[i].name+"</div></div>";
					}else{
						//html+="<div onclick=\"editinfo('"+data[i].id+"','"+data[i].name+"','"+data[i].photoPath+"')\" class=\"photo-bg\" ><div class=\"photo-div\" ><img class=\"photo-img\" src=\""+contextPath+"/tfd/system/photo/act/PhotoAct/getPhotoAct.act?photoPath="+data[i].photoCover+"\"/></div></div><div style=\"text-align:center\" >"+data[i].name+"</div></div>";
						html+="<div onclick=\"editinfo('"+data[i].id+"','"+data[i].name+"','"+data[i].photoPath+"')\" class=\"photo-bg\" title="+data[i].name+" ><div class=\"photo-div\" ><img alt="+data[i].name+
						" class=\"photo-img\" src=\""+contextPath+"/tfd/system/photo/act/PhotoAct/getPhotoAct.act?photoPath="+
						data[i].photoCover+"\"/></div><div style=\"text-align:center;\"  >"+data[i].name+
						"<div onclick=\"setCover('"+data[i].id+"')\" class=\"cover-div\" id=\"cover"+i+"\" title=\"取消封面\" ></div></div></div>";
					}
					
				}
				$("#photo-body").html(html);
			}
		}
	});
});
function editinfo(id,name,path){
	window.location=contextPath+"/system/photo/manage.jsp?photoId="+id+"&folderName="+name+"&path="+path;
}
function setCover(photoId){
	var newPath = "";
	 var requestUrl= contextPath+'/tfd/system/photo/act/PhotoAct/setPhotoCoverAct.act';
	 $.ajax({
		 url:requestUrl,
		 data:{coverPath:newPath,photoId:photoId},
		 type:"POST",
		 dataType:"json",
		 async:false,
		 error:function(e){
		 	alert(e.message);
		 },
		 success:function(data){
		 	if(data=='1'){
		 		top.layer.msg("设置成功!");
		 		history.go(0);
		 		return false;
			 }
		 }
	 });
}
</script>
</head>
<body style="margin-top:0;">
<div class="content-body" >
<div class="panel panel-info">
   <div class="panel-heading">
      <h5 class="panel-title">
      相册
      </h5>
   </div>
</div>
<div id="photo-body" class="photo-body" ></div>
<table class="MessageBox" style="display:none;margin-top:100px;" align="center" width="440" cellpadding="0" cellspacing="0">
   <tbody><tr class="head-no-title">
      <td class="left"></td>
      <td class="center">
      </td>
      <td class="right"></td>
   </tr>
   <tr class="msg">
      <td class="left"></td>
      <td class="center info">
         <div class="msg-content"></div>
      </td>
      <td class="right"></td>
   </tr>
   <tr class="foot">
      <td class="left"></td>
      <td class="center"><b></b></td>
      <td class="right"></td>
   </tr>
   </tbody>
</table>
</div>
</body>
</html>