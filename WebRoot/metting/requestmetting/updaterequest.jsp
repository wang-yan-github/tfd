<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%String requestId=request.getParameter("requestId"); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议申请</title>
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
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript">
var datalength;
var requestId="<%=requestId%>";
$(function (){
	getMessagePriv("meeting");
	filesUpLoad("meeting");
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
			attachId=data.attachId;
			creatAttachDiv(attachId,"attach");
			getIdboardroom(data.boardroomId);
			if(data.isSms.sitesms==1){
				$("#sitesms").prop("checked",true);
			}
			if(data.isSms.mobilesms==1){
				$("#mobilesms").prop("checked",true);
			}
			if(data.isSms.emailsms==1){
				$("#emailsms").prop("checked",true);
			}
			if(data.isSms.webemilesms==1){
				$("#webemilesms").prop("checked",true);
			}
			if(data.isSms.wxsms==1){
				$("#wxsms").prop("checked",true);
			}
			fromdata=data;
				for(var name in fromdata){
						$("#"+name).val(fromdata[name]);
					if(name=="meetingDescription"&&fromdata[name]!=null){
						var editorElement = CKEDITOR.document.getById('editor');
	   					editorElement.setHtml(fromdata[name]);
					}
					if(name=="cycType"&&fromdata[name]!=""){
						$("#cycType"+fromdata[name]).prop("checked",true);
						$("#cyc").show();
					}
					if(name=="meetingType"){
						$("#meetingType"+fromdata[name]).prop("checked",true);
					}
					if(name=="meetingDevice"&&fromdata[name]!=null){
						var ids=fromdata[name].split(",");
 						for(var i=0;i<ids.length;i++){
 							for(var i=0;i<datalength;i++){
 	 							if(ids[i]==$("#meetingDevice"+i).val()){
 	 								$("#meetingDevice"+i).prop("checked",true);
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
				$("#boardroomStaff").val(data.boardroomStaff);
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
					$("#take").hide();
					for(var i=0;i<data.length;i++){
						$("#meetingDevice").append("&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"checkbox\" name='meetingDevice"+i+"' id='meetingDevice"+i+"' value='"+data[i].boardroomdeviceId+"'> "+data[i].deviceName+" &nbsp;&nbsp;");
					}
				}
		}
		});
		
}
function updatemeeting(){
		    $("#meetingDescription").val(CKEDITOR.instances.editor.getData());
	var url=contextPath+"/meeting/act/MeetingrequestAct/updaterequestAct.act";
	var para=$('#meetingform').serialize();
	para+="&attachId="+$("#attachId").val();
	if(datalength!=0){
	var meetingDevice="";
	for(var i=0; i<datalength;i++){
		if($("#meetingDevice"+i).prop("checked")==true){
			meetingDevice+=$("#meetingDevice"+i).val()+",";
		}
	}
	}
	para+="&meetingDevice="+meetingDevice+"&requestId="+requestId;
	para+="&smsReminds="+getsmsRemind();
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:para,
		async:false,
		error:function(e){
		},
		success:function(data){
			if(data!=0){
				parent.layer.msg('申请成功！');
				history.back();
			}
	}
	});
}
function userNameval(){
	$('#meetingform').bootstrapValidator('revalidateField', 'userName');
}
function deptNameval(){
	$('#meetingform').bootstrapValidator('revalidateField', 'deptName');
}
function SummanNameval(){
	$('#meetingform').bootstrapValidator('revalidateField', 'SummanName');
}
function cycleEndtime(){
	$('#meetingform').bootstrapValidator('revalidateField', 'cycEndtime');
}
function cycclick(){
	$("#cyc").show();
}
function cycnoclick(){
	$("#cyc").hide();
}
</script>
</head>
<body>
   <form  id="meetingform" name="meetingform" method="post" class="form-horizontal">
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:98%;margin-left: 1%">
<a style="cursor: auto;" class="list-group-item active">会议申请</a>
<table class="table table-striped table-condensed">
  <tr>
    <td> 出席人员：<!-- <br /><a href="JavaScript:;" onClick="set_att()"><span id="att_show">添加外部人员</span></a> --></td>
    <td colspan="3">
    	<div class="col-xs-4">
<textarea rows="3" cols="40" name="attendStaff" id="attendStaff" style="display: none;" ></textarea>
<textarea rows="3" cols="40" name="userName" readonly="readonly" id="userName" class="form-control"></textarea></div>
<div style="margin-top: 30px;">
<a href="javascript:void(0);" onclick="userinit(['attendStaff','userName'],'false','userNameval');">添加人员</a>
</div>
    </td>
  </tr>
    <tr>
      <td >查看范围（部门）：<!-- <br /><a href="JavaScript:;" onClick="set_fw()"><span id="fw_show">添加查看范围</span></a> --></td>
      <td colspan="3">
			        <div class="col-xs-4">
			<textarea rows="3" cols="40" name="selectDept" id="selectDept" style="display: none;"></textarea>
			<textarea rows="3" cols="40" name="deptName" readonly="readonly" id="deptName"  class="form-control"></textarea>
			</div>
			<div style="margin-top: 30px;">
			<a href="javascript:void(0);" onclick="deptinit(['selectDept','deptName'],'false','deptNameval');">添加部门</a>
			</div>
      </td>
    </tr>
   <tr>
    <td width="15%">名称：</td>
    <td width="35%">
    <div class="col-xs-12 form-group">
      <input type="text" name="meetingName" id="meetingName" size="40" class="form-control " value=""/>
      </div>
    </td>
    <td width="15%"> 主题：</td>
    <td width="35%">
    <div class="col-xs-12 form-group">
      <input type="text" name="meetingTheme" id="meetingTheme"  class="form-control " value="" />
      </div>
    </td>
  	</tr>
  <tr>
    <td >会议室：
    <input type="hidden" id="boardroomId"   name="boardroomId" value=""></input>
    </td>
    <td>
    <div class="col-xs-12" id="boardroomName">
    </div>
   		  </td>
<td style="width:150px;"> 会议室管理员：</td>
    <td >
    <input type="hidden" id="boardroomStaff" name="boardroomStaff" />
   <div class="col-xs-12" id="boardroomuserName"></div>
</td>
  </tr>
    <td> 开始时间：</td>
    <td >
    <div class="col-xs-12">
      <input type="text" id="meetingStarttime" name="meetingStarttime" class="form-control " style="cursor: pointer;" readonly="readonly"   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
      </input>
      </div>
    </td>
    <td> 结束时间：</td>
    <td>
     <div class="col-xs-12">
      <input type="text" name="meetingEndtime" id="meetingEndtime" class="form-control " style="cursor: pointer;" readonly="readonly" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
   	</input>
   	</div>
    </td>
  </tr>
  <tr>
    <td >会议室设备：</td>
    <td colspan="3" id="meetingDevice">
    <div class="col-xs-12" id="take">无记录</div>
    </td>
  </tr>
  <tr>
     <td >会议纪要员：</td>
    <td colspan="3">
           <div class="col-xs-5 form-group">
<textarea rows="3" cols="40" name="meetingSumman" id="meetingSumman" style="display: none;" ></textarea>
<input name="SummanName" readonly="readonly" id="SummanName" class="form-control"></input> </div>
<div style="margin-top: 8px;">
<a href="javascript:void(0);" onclick="userinit(['meetingSumman','SummanName'],'true','SummanNameval');">添加人员</a>
</div>
  </td>
  </tr>
  <tr>
   <td >周期性会议申请:</td>
    <td >
    <div class="col-xs-12">
    	<input type="radio" name="meetingType" id="meetingType1" value="1" onclick="cycclick();" />是 
    	<input type="radio" name="meetingType" id="meetingType0" value="0" checked="true" onclick="cycnoclick();" />否 
    	</div>
    </td>
    <td> 提前时间：</td>
    <td >
    <div class="col-xs-12 form-group"  ><select style="float:left;" class="form-control BigSelect" id="warnTime" name='warnTime' >
              <option value="15">15分钟前</option>
              <option value="30">30分钟前</option>
              <option value="60">1个小时前</option>
              <option value="120">2个小时前</option>
              <option value="1440">1天前</option>
              <option value="10080">1周前</option>
          </select>
  	</div>
  	</td>
  </tr>
   <tr id="cyc" style="display: none;">
  <td>
  选择周期性类型：
  </td>
  <td>
  <div class="col-xs-12">
    	<input type="radio" name="cycType" id="cycType2" value="2" />天
    	<input type="radio" name="cycType" id="cycType3" value="3" checked="true" />周
    	<input type="radio" name="cycType" id="cycType4" value="4"  />月
    	<input type="radio" name="cycType" id="cycType5" value="5"  />年
    	</div>
  </td>
  <td>
  周期结束时间：
  </td>
  <td>
  <div class="col-xs-12 form-group">
      <input type="text" name="cycEndtime" id="cycEndtime" class="form-control " style="cursor: pointer;" readonly="readonly" placeholder="请选择周期结束日期" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" onblur="cycleEndtime();" >
   	</input>
  </td>
  </tr>
  <tr>
    <td >附件文档：</td>
    <td colspan="3">
    <div id="attachDiv" name="attachDiv"></div>
    </td>
  </tr>
  <tr>
<td>附件选择:</td>
<td colspan="3">	
<div style="display: none;" class="fieldset flash" id="fsUploadProgress"></div>
<div style="display: none;" id="divStatus"></div>
<div>

	<a class="addfile"  href="javascript:void(0)">单附件
	<input type="file" onchange="fileUpLoad('meeting','attach');" hidefocus="true" size="1" id="fileattach" name="fileattach" class="addfile"></a>
	
	<input type="hidden" id="attachId" name="attachId"/>
	
	
	<a class="add_swfupload" href="javascript:void(0)">多附件<span id="attach"></span></a>
	<div style="display: none;"><a href="#"  id="btnCancel" onclick="swfu.cancelQueue();" disabled="disabled"  >取消上传</a></div>

</div>

</td>
</tr>
  <tr>
    <td colspan="4">会议描述：</td>
  </tr>
  <tr >
    <td colspan="4">
		<input type="hidden" id="meetingDescription" name="meetingDescription"/>
	<textarea  id="editor" name="editor" style="width: 100%; height: 180px;" ></textarea>
	<script type="text/javascript">CKEDITOR.replace('editor')</script>
    </td>
  </tr>
  <tr>
  <td>提醒：</td>
  <td colspan="3">
  <div id="smsdiv" name="smsdiv" ></div>
  </td>
  </tr>
  <tr >
    <td colspan="4" align="center">
      <input type="submit" value="确定" class="btn btn-primary" >&nbsp;&nbsp;
            <input type="button"  value="返回" class="btn btn-default" onClick="history.back();">
       
    </td>
  </tr>
  </table>
  </form>
<div id="modaldialog"></div>
</body>
<script type="text/javascript">
$(document).ready(function() {
$("#meetingform").bootstrapValidator({
	 message: 'Pas valide',
	 container: 'tooltip',
	 feedbackIcons: {
	     valid: 'glyphicon glyphicon-ok',
	     invalid: 'glyphicon glyphicon-remove',
	     validating: 'glyphicon glyphicon-refresh'
	 },
	 fields: {
		 meetingName: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },meetingTheme: {
	         validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },userName:{
	    	 validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },deptName:{
	    	 validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },SummanName:{
	    	 validators: {
	        	 container: 'popover',
	             notEmpty: {
	                 message: '不能为空'
	             }
	         }
	     },cycEndtime:{
	         validators: {
	        	 container: 'popover',
	        	 callback: {
	                    message: '不能为空',
	                    callback: function(value, validator) {
	                    	if($("#meetingType").val()==1){
	                    		if($("#cycEndtime").val()!=""){
	                    			return true;
	                    		}else{
	                    		return false;
	                    		}
	                    	}else{
	                    		return true;
	                    	}
	                    }
	                }
	         }
	     }
	 }
	 }).on('success.form.bv',function(e){
		 e.preventDefault();
			
	     // Get the form instance
	     var $form = $(e.target);

	     // Get the BootstrapValidator instance
	     var bv = $form.data('bootstrapValidator');
	     updatemeeting();
		});
});
</script>
</html>