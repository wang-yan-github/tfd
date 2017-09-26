<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>内部邮件</title>
<script scr="<%=contextPath%>/system/email/js/email.js" type="text/javascript"></script>
<style type="text/css">
html,body{
	width:100%;
	height:100%;
	margin:0px;
	padding:0px;
}
#notify_menu,#other_menu {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.li {
	height: 45px;
	line-height: 45px;
	border-bottom: solid 1px #ccc;
	border-left: solid 1px #ccc;
	color:#999;
	cursor:pointer;
}
.li a{
	color:#999;
}
.li a:HOVER{
text-decoration:none;
}
.li b{margin-left:15px;}

.lihover{
	background-color:#FFF;
}
.liclicked {
	background-color:#CCCCCC;
}
.liclicked a {
	color: white;
}

.title{height:40px;border-bottom:#CCC solid 1px;width:100%;border-bottom:#CCC solid 1px;}
.wirter{height:24px;line-height:24px;color:#FFF;background-color:#007AC1;width:40px;float:right;margin-top:8px;
		font-size:12px;text-align:center;margin-right:8px;font-family:Simsun, Arial, sans-serif;font-weight:bold;
		cursor:pointer;}
.notify_img{width:24px;height:24px;margin-left:12px;}
#inbox,#sendbox,#draftbox,#outbox,#otherbox,.otherBoxs{float:right;height:16px;width:24px;border:#999 1px solid;margin-top:14px;margin-right:10px;font-size:13px;color:#999;line-height:16px;text-align:center;background-color:#FFF;-moz-border-radius: 32px / 12px; -webkit-border-radius: 32px / 12px; border-radius: 32px / 12px;display:none; }
.otherBoxs{display:block;}
.notify_img1{float:left;width:24px;height:24px;margin-left:15px;margin-top:12px;background:url("/tfd/system/styles/images/email/icon/tl_icons.png");background-repeat:no-repeat;background-position:-0px -46px;}
.notify_img2{float:left;width:24px;height:24px;margin-left:15px;margin-top:12px;background:url("/tfd/system/styles/images/email/icon/tl_icons.png");background-repeat:no-repeat;background-position:-0px -73px;}
.notify_img3{float:left;width:24px;height:24px;margin-left:15px;margin-top:12px;background:url("/tfd/system/styles/images/email/icon/tl_icons.png");background-repeat:no-repeat;background-position:-0px -100px;}
.notify_img4{float:left;width:24px;height:24px;margin-left:15px;margin-top:12px;background:url("/tfd/system/styles/images/email/icon/tl_icons.png");background-repeat:no-repeat;background-position:-0px -126px;}
.notify_img5{float:left;width:16px;height:16px;margin-left:15px;margin-top:12px;background:url("/tfd/system/styles/images/email/email-16.png");background-repeat:no-repeat;}
</style>
<script type="text/javascript">
function wirter(){
	parent["edit"].location=contextPath+"/system/email/new/index.jsp";
}
function getInbox(){
	parent["edit"].location=contextPath+"/system/email/inbox/index.jsp";
}
function getSendbox(){
	parent["edit"].location=contextPath+"/system/email/sendbox/index.jsp";
}
function getOutbox(){
	parent["edit"].location=contextPath+"/system/email/outbox/index.jsp";
}
function getDraftbox(){
	parent["edit"].location=contextPath+"/system/email/draftbox/index.jsp";
}
function getSettings(){
	parent["edit"].location=contextPath+"/system/email/settings/index.jsp";
}
	
function doReady(){
	doinit();
	getCount();
	$(".li").mouseover(function(){
		$(this).addClass("lihover");
	});
	$(".li").mouseout(function(){
		$(this).removeClass("lihover");
	});
	$(".li").click(function(){
		$(".li").removeClass("liclicked");
		$(this).addClass("liclicked");
	});
};
function getCount(){
	doinit();
	var requestUrl='<%=contextPath%>/tfd/system/email/act/EmailAct/getEmailCount.act';
	$.ajax({
			url:requestUrl,
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data[0].InCount != 0){
					$('#inbox').attr("style","display:block");
					if(data[0].InCount>99){
						$('#inbox').html("99+");
					}else{
						$('#inbox').html(data[0].InCount);
					}
				}else{
					$('#inbox').attr("style","display:none");
				}
				if(data[0].SendCount != 0){
					$('#sendbox').attr("style","display:block");
					if(data[0].SendCount>99){
						$('#sendbox').html("99+");
					}else{
						$('#sendbox').html(data[0].SendCount);
					}	
				}else{
					$('#sendbox').attr("style","display:none");
				}
				if(data[0].DraftCount != 0){
					$('#draftbox').attr("style","display:block");
					if(data[0].DraftCount>99){
						$('#draftbox').html("99+");
					}else{
						$('#draftbox').html(data[0].DraftCount);
					}	
				}else{
					$('#draftbox').attr("style","display:none");
				}
				if(data[0].OutCount != 0){
					$('#outbox').attr("style","display:block");
					if(data[0].OutCount>99){
						$('#outbox').html("99+");
					}else{
						$('#outbox').html(data[0].OutCount);
					}	
				}else{
					$('#outbox').attr("style","display:none");
				}
				if(data[0].OtherCount != 0){
					$('#otherbox').attr("style","display:block");
					if(data[0].OtherCount>99){
						$('#otherbox').html("99+");
					}else{
						$('#otherbox').html(data[0].OtherCount);
					}	
				}else{
					$('#otherbox').attr("style","display:none");
				}
			}
	});
}
function doinit(){
	var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/getEmailBoxList.act';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(!($.isEmptyObject(data))){
				$(".other").css("display","block");
				var html = "";
				$.each(data,function(index,data){
				   var boxCount = getBoxCount(data.boxId);
				   if(boxCount == '0'){
					   html += "<li class='li' onclick=\"javascript:goOtherBox('"+data.boxId+"')\" ><i class='doing-1'></i><b style=\"margin-left:70px;\" >"+data.boxName+"</b></li>"
				   }else{
					   if(boxCount>99){
						   boxCount = "99+";
					   }
					   html += "<li class='li' onclick=\"javascript:goOtherBox('"+data.boxId+"')\" ><i class='doing-1'></i><b style=\"margin-left:70px;\" >"+data.boxName+"</b><span class=\"otherBoxs\" >"+boxCount+"</span></li>"
				   }
				});
				$("#other_menu").html(html);
			}
		}
	});
}
function getBoxCount(boxId){
	var returnData = "";
	var requestUrl= '<%=contextPath%>/tfd/system/email/act/EmailAct/getEmailBoxCount.act';
	$.ajax({
		url:requestUrl,
		data:{boxId:boxId},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			returnData = data.boxCount;
		}
	});
	return returnData;
}
function showMenu(){
	$("#other_menu").toggle(300);
}
function goOtherBox(boxId){
	parent["edit"].location=contextPath+"/system/email/otherBox.jsp?boxId="+boxId;
}
function getWebMail(){
	parent["edit"].location=contextPath+"/system/email/webMail/index.jsp";
}
</script>
</head>
<body onload="doReady();" >
	<div >
		<div class="title" >
			<img src="/tfd/system/styles/images/email/email.png" width="70" height="30" style="margin-top:5px;margin-left:30px;" />
			<input type="button" class="btn btn-primary btn-sm" style="margin-left:30px;margin-top:5px;" onclick="javascript:wirter()"  value="写邮件" />
		</div>
		<div class="content" >
			 <ul id="notify_menu">
				<li class="li" onclick="javascript:getInbox()" ><i class="doing-1"><div class="notify_img1" ></div></i><b>收件箱 </b><span id="inbox" ></span></li>
				<li class="li" onclick="javascript:getDraftbox()" ><i class="doing-1"><div class="notify_img2" ></div></i><b>草稿箱</b><span id="draftbox" ></span></li>
				<li class="li" onclick="javascript:getSendbox()" ><i class="doing-1"><div class="notify_img3" ></div></i><b>发件箱</b><span id="sendbox" ></span></li>
				<li class="li" onclick="javascript:getOutbox()" ><i class="doing-1"><div class="notify_img4" ></div></i><b>废件箱</b><span id="outbox" ></span></li>
				<li class="li other" onclick="javascript:showMenu()" style="display:none;" ><i class="doing-1"><div class="notify_img5" ></div></i><b>我的文件夹</b><span id="otherbox" ></span></li>
				<ul id="other_menu" style="display:none;" >
				</ul>
				<li class="li" onclick="javascript:getSettings()" ><i class="doing-1"><img class="notify_img" src="/tfd/system/styles/images/email/icon/setting.png" /></i><b>高级设置</b></li>
				<li class="li" onclick="javascript:getWebMail()" ><i class="doing-1"><img class="notify_img" src="/tfd/system/styles/images/email/icon/moerset.png" /></i><b>企业邮件设置</b></li>
			</ul>
		</div>
	</div>
</body>
</html>