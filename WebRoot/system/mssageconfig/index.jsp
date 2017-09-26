<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title></title>
<script type="text/javascript" src="<%=contextPath%>/system/mssageconfig/indes.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/json2.js"></script>
<script type="text/javascript">
function doinit()
{
	var requestUrl=contextPath+"/tfd/system/messageconfig/act/MessageConfigAct/getAllMessageConfigAct.act";
    $.ajax({
            url:requestUrl,
            dataType:"json",
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
            	var workNext=data.workNext;
            	$("#worknextsms1").val(workNext.sms1);
            	$("#worknextsms2").val(workNext.sms2);
            	$("#worknextsms3").val(workNext.sms3);
            	$("#worknextsms4").val(workNext.sms4);
            	$("#worknextsms5").val(workNext.sms5);
            	var passTime = data.passTime;
            	$("#passtimesms1").val(passTime.sms1);
            	$("#passtimesms2").val(passTime.sms2);
            	$("#passtimesms3").val(passTime.sms3);
            	$("#passtimesms4").val(passTime.sms4);
            	$("#passtimesms5").val(passTime.sms5);
            	var workEnd =data.workEnd;
            	$("#workendsms1").val(workEnd.sms1);
            	$("#workendsms2").val(workEnd.sms2);
            	$("#workendsms3").val(workEnd.sms3);
            	$("#workendsms4").val(workEnd.sms4);
            	$("#workendsms5").val(workEnd.sms5);
            	var workFollow = data.workFollow;
            	$("#workfollowsms1").val(workFollow.sms1);
            	$("#workfollowsms2").val(workFollow.sms2);
            	$("#workfollowsms3").val(workFollow.sms3);
            	$("#workfollowsms4").val(workFollow.sms4);
            	$("#workfollowsms5").val(workFollow.sms5);
            	var notice = data.notice;
            	$("#noticesms1").val(notice.sms1);
            	$("#noticesms2").val(notice.sms2);
            	$("#noticesms3").val(notice.sms3);
            	$("#noticesms4").val(notice.sms4);
            	$("#noticesms5").val(notice.sms5);
            	var news = data.news;
            	$("#newssms1").val(news.sms1);
            	$("#newssms2").val(news.sms2);
            	$("#newssms3").val(news.sms3);
            	$("#newssms4").val(news.sms4);
            	$("#newssms5").val(news.sms5);
            	var calendar = data.calendar;
            	$("#calendarsms1").val(calendar.sms1);
            	$("#calendarsms2").val(calendar.sms2);
            	$("#calendarsms3").val(calendar.sms3);
            	$("#calendarsms4").val(calendar.sms4);
            	$("#calendarsms5").val(calendar.sms5);
            	var diary = data.diary;
            	$("#diarysms1").val(diary.sms1);
            	$("#diarysms2").val(diary.sms2);
            	$("#diarysms3").val(diary.sms3);
            	$("#diarysms4").val(diary.sms4);
            	$("#diarysms5").val(diary.sms5);
            	var meet = data.meeting;
            	$("#meetsms1").val(meet.sms1);
            	$("#meetsms2").val(meet.sms2);
            	$("#meetsms3").val(meet.sms3);
            	$("#meetsms4").val(meet.sms4);
            	$("#meetsms5").val(meet.sms5);
            	}
        });
	}
</script>
<style>
.headdiv
{
border-bottom: 2px solid red;
padding-left: 10px;
height: 40px;
padding-top:10px;
}
.headspan
{
color: #183152;
font-size: 14px;
}
</style>
</head>
<body onload="doinit();">
<div align="center" style="margin-top: 30px;">
<div style="width: 70%;box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);">
<div class="headdiv" align="left">
<span class="headspan">短信息设置</span>
</div>
<table class="table table-striped  table-condensed" >
<tr align="center">
<td>模块名称</td>
<td>手机微信</td>
<td>内部短信</td>
<td>手机短信</td>
<td>内部邮件</td>
<td>外部邮件</td>
</tr>
<tr  align="center">
<td>流程转交</td>
<td>
		<select class="form-control" name="worknextsms1"  id="worknextsms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
		<select class="form-control" name="worknextsms2"  id="worknextsms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
		<select class="form-control" name="worknextsms3"  id="worknextsms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
		<select class="form-control" name="worknextsms4"  id="worknextsms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
		<select class="form-control" name="worknextsms5"  id="worknextsms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
<tr  align="center">
<td>流程超时</td>
<td>
<select class="form-control" name="passtimesms1"  id="passtimesms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="passtimesms2"  id="passtimesms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="passtimesms3"  id="passtimesms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="passtimesms4"  id="passtimesms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="passtimesms5"  id="passtimesms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
<tr  align="center">
<td>流程结束</td>
<td>
<select class="form-control" name="workendsms1"  id="workendsms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="workendsms2"  id="workendsms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="workendsms3"  id="workendsms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="workendsms4"  id="workendsms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="workendsms5"  id="workendsms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
<tr  align="center">
<td>流程关注</td>
<td>
<select class="form-control" name="workfollowsms1" id="workfollowsms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="workfollowsms2" id="workfollowsms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="workfollowsms3" id="workfollowsms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="workfollowsms4" id="workfollowsms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="workfollowsms5" id="workfollowsms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
<tr  align="center">
<td>通知公告</td>
<td>
<select class="form-control" name="noticesms1" id="noticesms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="noticesms2" id="noticesms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="noticesms3" id="noticesms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="noticesms4" id="noticesms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="noticesms5" id="noticesms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
<tr  align="center">
<td>单位新闻</td>
<td>
<select class="form-control" name="newssms1" id="newssms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="newssms2" id="newssms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="newssms3" id="newssms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="newssms4" id="newssms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="newssms5" id="newssms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
<tr  align="center">
<td>日程安排</td>
<td>
<select class="form-control" name="calendarsms1" id="calendarsms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="calendarsms2" id="calendarsms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="calendarsms3" id="calendarsms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="calendarsms4" id="calendarsms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="calendarsms5" id="calendarsms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
<tr  align="center">
<td>工作日志</td>
<td>
<select class="form-control" name="diarysms1" id="diarysms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="diarysms2" id="diarysms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="diarysms3" id="diarysms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="diarysms4" id="diarysms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="diarysms5" id="diarysms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
<tr  align="center">
<td>会议安排</td>
<td>
<select class="form-control" name="meetsms1" id="meetsms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="meetsms2" id="meetsms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="meetsms3" id="meetsms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="meetsms4" id="meetsms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="meetsms5" id="meetsms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
<tr  align="center">
<td>内部邮件</td>
<td>
<select class="form-control" name="emailsms1" id="emailsms1">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="emailsms2" id="emailsms2">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="emailsms3" id="emailsms3">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="emailsms4" id="emailsms4">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
<td>
<select class="form-control" name="emailsms5" id="emailsms5">
			<option value="0">禁用</option>
			<option value="1">可选</option>
			<option value="2">启用</option>
		</select>
</td>
</tr>
</table>
</div>
</div>
</br>
<div align="center"><button class="btn btn-primary" onclick="save();">设置</button></div>
</body>
</html>