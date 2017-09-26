<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%String requestId=request.getParameter("requestId"); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审批会议</title>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/ckeditor_full/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>
<script type="text/javascript">
var editor;
var datalength;
var requestId="<%=requestId%>";
$(function (){
	getIdrequest();
	getIdapproval();
});
var meetingStarttime="";
var meetingEndtime="";
var meetingName="";
var meetingTheme="";
var accountId="";
var warnTime="";
var meetingType="";
var cycType="";
var attendStaff="";
var isSms="";
var cycEndtime="";
function getIdapproval(){
	var url=contextPath+"/meeting/act/ApprovalmeetingAct/getmeetingIdAct.act";
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{meetingId:requestId},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data.approvalContent!=""&& data.approvalContent!=undefined){
				var editorElement = CKEDITOR.document.getById('editor');
					editorElement.setHtml(data.approvalContent);
			}
		}
	});
}
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
			meetingStarttime=data.meetingStarttime;
			meetingEndtime=data.meetingEndtime;
			meetingName=data.meetingName;
			meetingTheme=data.meetingTheme;
			accountId=data.createUser;
			meetingType=data.meetingType;
			cycType=data.cycType;
			attendStaff=data.attendStaff;
			isSms=JSON.stringify(data.isSms);
			cycEndtime=data.cycEndtime;
			
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
				$("#boardroomStaff").append("<option value='"+data.boardroomStaff+"'>"+data.boardroomuserName+"</option>")
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
						 $("#meetingDevice").append("<span id='"+data[i].boardroomdeviceId+"' style=\"display:none\"> "+data[i].deviceName+" </span>&nbsp;&nbsp;"); 
					}
				}
		}
		});
}
function approvalmeeting(){
	var status;
	var url=contextPath+"/meeting/act/ApprovalmeetingAct/updatemeetingAct.act";
	var approvalContent=CKEDITOR.instances.editor.getData();
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{meetingId:requestId,approvalContent:approvalContent},
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				status="true";
			}else{
				status="false";
			}
	}
	});
	return status;
}
function passmeeting(){
	var url=contextPath+"/meeting/act/MeetingrequestAct/adoptmeetingAct.act";
	$.ajax({
		   url:url,
		   type:'post',
	   		async:false,
	   		data:{requestId:requestId,
	   			meetingStarttime:meetingStarttime,
	   			meetingEndtime:meetingEndtime,
	   			meetingName:meetingName,
	   			meetingTheme:meetingTheme,
	   			accountId:accountId,
	   			warnTime:warnTime,
	   			meetingType:meetingType,
	   			cycType:cycType,
	   			attendStaff:attendStaff,
	   			isSms:isSms,
	   			cycEndtime:cycEndtime
	   		},
	   		success:function(data){
	   			if(data!=0){
	   			parent.layer.msg('通过成功！');
	   			history.back();
	   			}
	   		}
	   });
}
function notpassmeeting(){
	var url=contextPath+"/meeting/act/MeetingrequestAct/notadoptmeetingAct.act";
	$.ajax({
		   url:url,
		   type:'post',
	   		async:false,
	   		data:{requestId:requestId},
	   		success:function(data){
	   			if(data!=0){
	   			parent.layer.msg('不通过成功！');
	   			history.back();
	   			}
	   		}
	   });
}
function pass(){
	if(approvalmeeting()){
		passmeeting();
	}
}
function notpass(){
	if(approvalmeeting()){
		notpassmeeting();
	}
}
</script>
</head>
<body>
     <div class="list-group-item"  style="padding: 0px;cursor: auto;width:98%;margin-left: 1%;">
<a style="cursor: auto;" class="list-group-item active">修改会议审批</a>
   <form action="" id="meetingform" name="meetingform" method="post">
<table class="table table-striped ">
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
  <tr>
    <td colspan="4">审批意见：</td>
  </tr>
  <tr >
    <td colspan="4">
	<textarea  id="editor" name="editor"  style="width: 100%; height: 180px;" ></textarea>
	<script type="text/javascript">CKEDITOR.replace('editor')</script>
    </td>
  </tr>
  
  <tr >
    <td colspan="4" align="center">
    <input type="button"  value="通过" class="btn btn-primary" onClick="pass();">
    <input type="button" value="不通过 " class="btn btn-danger" onclick="notpass();">
            <input type="button"  value="返回" class="btn" onClick="history.back();">
       
    </td>
  </tr>
  </table>
  </form>
<div id="modaldialog"></div>
</body>
</html>