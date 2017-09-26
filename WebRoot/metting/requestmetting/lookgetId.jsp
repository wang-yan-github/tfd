<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%String requestId=request.getParameter("requestId"); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看会议信息</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript">
var datalength;
var requestId="<%=requestId%>";
$(function (){
	getIdrequest();
});
function getIdrequest(){
	var url=contextPath+"/meeting/act/MeetingrequestAct/getIdrequestAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{requestId:requestId},
		async:false,
		error:function(e){
		},
		success:function(data){
			readAttachDiv(data.attachId,"attach");
			getIdboardroom(data.boardroomId);
			fromdata=data;
			var warnTime=data.warnTime;
				for(var name in fromdata){
					if(name=="meetingDescription"&&fromdata[name]!=null){
						$("#meetingDescription").html(fromdata[name]);
					}else{
						if(name=="meetingDevice"&&fromdata[name]!=null){
							if(fromdata[name]!=""){
								$("#take").hide();
								 var ids=fromdata[name].split(",");
			 						for(var i=0;i<ids.length;i++){
			 							for(var i=0;i<datalength;i++){
			 	 							$("#"+ids[i]).show();
			 	 						}
			 						}	
							}
						}else{
							$("#"+name).html(fromdata[name]);
						}
					}
				}
				if(data.meetingType==0){
					$("#meetingType").html("否");
				}else{
					$("#meetingType").html("是");
					$("#cyc").show();
					if(data.cycType==2){
						$("#cycType").html("日周期性会议");
					}else{
						if(data.cycType==3){
							$("#cycType").html("周周期性会议");
						}else{
							if(data.cycType==4){
								$("#cycType").html("月周期性会议");	
							}else{
								if(data.cycType==5){
									$("#cycType").html("年周期性会议");
								}
							}
						}
					}
				}
	}
	});
}
function getIdboardroom(boardroomId){
	var url=contextPath+"/meeting/act/BoardroomAct/getIdboardroomAct.act";
		$.ajax({
			url:url,
			type:"POST",
			dataType:"json",
			data:{boardroomId:boardroomId},
			async:false,
			error:function(e){
			},
			success:function(data){
				$("#boardroomId").val(data.boardroomId);
				$("#boardroomName").text(data.boardroomName);
				$("#boardroomuserName").text(data.boardroomuserName);
		}
		});
		var url=contextPath+"/meeting/act/BoardroomdeviceAct/getboardroomIddeviceAct.act";
		$.ajax({
			url:url,
			type:"POST",
			dataType:"json",
			data:{boardroomId:boardroomId},
			async:false,
			error:function(e){
			},
			success:function(data){
				datalength=data.length;
				if(data.length!=0){
					for(var i=0;i<data.length;i++){
						 $("#meetingDevice").append("<div id='"+data[i].boardroomdeviceId+"' style=\"display:none\"> "+data[i].deviceName+" </div>"); 
					}
				}
		}
		});
}

function returntable(){
	parent.document.getElementById('left').style.display="block";
	parent.document.getElementById('detailright').style.display="none";
	parent.document.getElementById("right").src="";
}
</script>
</head>
<body>
<form  id="meetingform" name="meetingform" class="form-horizontal">
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:98%;margin-left: 1%;margin-top: 3%;">
<a style="cursor: auto;" class="list-group-item active">会议详情</a>
<table class="table table-striped table-condensed">
<tr>
    <td> 出席人员：</td>
    <td colspan="3">
    	<div class="col-xs-12" id="userName"></div>
    </td>
  </tr>
    <tr>
      <td >查看范围（部门）：</td>
      <td colspan="3">
			        <div class="col-xs-12" id="deptName">
			</div>
      </td>
    </tr>
  <tr>
    <td width="15%">名称：</td>
    <td width="35%">
    <div class="col-xs-12" id="meetingName">
      </div>
    </td>
    <td width="15%"> 主题：</td>
    <td width="35%">
    <div class="col-xs-12" id="meetingTheme">
      </div>
    </td>
  	</tr>
  <tr>
    <td >会议室：
    </td>
    <td>
    <div class="col-xs-12" id="boardroomName"></div>
   		  </td>
  <td > 会议室管理员：</td>
    <td ><div class="col-xs-10" id="boardroomuserName"> 
</select> 
</div>
</td>
    <tr>
    <td> 开始时间：</td>
    <td >
    <div class="col-xs-12" id="meetingStarttime">
      </div>
    </td>
    <td> 结束时间：</td>
    <td>
     <div class="col-xs-12" id="meetingEndtime" >
   	</input>
   	</div>
    </td>
  </tr>
  <tr>
    <td >会议室设备：</td>
    <td colspan="3">
    <div class="col-xs-12" id="meetingDevice"></div>
    <div class="col-xs-12" id="take">无记录</div>
    </td>
  </tr>
  <tr>
    <td>会议纪要员：</td>
      <td>
        <div class="col-xs-12" id="SummanName"></div>
      </td>
  	<td >周期性会议申请:</td>
    <td  id="meetingType">
    </tr>  
    <tr id="cyc" style="display: none;">
    <td>
    周期性会议类型：
    </td>
    <td >
    <div class="col-xs-12" id="cycType">
    </div>
    </td>
    <td>
    周期结束时间：
    </td>
    <td>
    <div class="col-xs-12" id="cycEndtime">
    
    </div>
    </td>	
  </tr>
  <tr>
    <td >附件文档：</td>
    <td colspan="3">
    <div id="attachDiv" name="attachDiv"></div>
    </td>
  </tr>
  <tr>
    <td colspan="1">会议描述：</td>
    <td colspan="3">
    <div id="meetingDescription" class="col-xs-12"></div>
    </td>
  </tr>
  </table>
  </div>
  <div align="center"><input type="button"  value="返回" class="btn btn-primary" onclick="returntable();"></div>
  </form>
<div id="modaldialog"></div>
</body>
</html>