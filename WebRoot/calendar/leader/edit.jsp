<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
	String calId = request.getParameter("id");
	String type = request.getParameter("type");
%>
<html>
<head>
<title>
</title>
<script src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>
<script  type="text/javascript"  src='<%=contextPath%>/calendar/js/affair.js'></script>
<link href='<%=contextPath%>/calendar/css/calendar.css' rel='stylesheet' />
<script type="text/javascript" src="<%=contextPath%>/calendar/leader/js/lender.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/sysall/messageunit.js"></script>
<script type="text/javascript" src="<%=contextPath%>/calendar/leader/js/edit.logic.js"></script>

<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<style type="text/css">
	.checked{height:30px;line-height:30px;cursor:pointer;text-align:center;}
	.checked:hover{background-color:#F5F5F5;}
	.checkeds{background-color:#CCC;}
</style>
<script type="text/javascript">
var calId = "<%=calId%>";
var type = "<%=type%>";
</script>
</head>
<body onload="doinit();">
<form   method="post" name="form1" id="form1" class="form-horizontal" >
	<input   type="hidden" name="calId" id="Up_calId" value="" />
	<input   type="hidden" name="accountIds" id="Up_accountId" value="" />
	<input   type="hidden" name="affairId" id="affairId" value="" />
	<div class="panel panel-info">
	<div class="panel-body" style="border:none;" >
	 <table class="table table-striped" width="100%" align="center">
		<tr>
			<td nowrap>事务内容：</td>
			<td nowrap align="left"><div class="col-xs-8 form-group" >
				 <textarea  name="content" id="CAL_CONTENT" style="float:left;" class="form-control" required="true" rows="3" cols="30"></textarea></div><font color="red">(必填)</font>
         </td>
		</tr>
		
		<tr>
			<td nowrap>开始时间：</td>
			<td nowrap align="left"><div class="col-xs-8 form-group" >
			 	<input type="text" readonly="readonly"  name="startDate" style="float:left;"  id="START_DATE" class="form-control BigInput" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/></div>
       </td>
		</tr>
		<tr>
			<td nowrap>结束时间：</td>
			<td nowrap align="left"><div class="col-xs-8 form-group" >
			 	<input type="text" readonly="readonly"  name="endDate" style="float:left;"  id="END_DATE" class="form-control BigInput" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/></div>
        </td>
		</tr>
		
		<tr>
			<td nowrap>事务类型：</td>
			<td nowrap align="left"><div class="col-xs-8 form-group" >
				 <select style="float:left;" class="form-control BigSelect" name="calType" id="calType">
                   <option value="1">工作日程(领导可见)</option>
						<!-- <option value="2">个人日程(自己可见)</option> -->
						<option value="3">完全公开</option>
            </select></div>
           </td>
			</tr>
		<tr>
			<td nowrap>优先级：</td>
			<td nowrap align="left"><div class="col-xs-8 form-group" >
		     <select style="float:left;" class="form-control BigSelect" name="calLevel" id="calLevel">
		     		<option value="0">不指定</option>
                   <option value="1">紧急/重要</option>
						<option value="2">紧急/不重要</option>
						<option value="3">不紧急/重要</option>
						<option value="4">不紧急/不重要</option>
            </select></div>
			</td>
		</tr>
		<tr>
			<td nowrap>是否重复：</td>
			<td nowrap align="left"><div class="col-xs-8 form-group" >
			   <input name="calAffType" id='calAffType' type="checkbox" value="1" disabled="disabled"  onclick='setCalAffType(this);'></div></label for='calAffType'></label>
			</td>
	     </tr>
	     
	     <tr id="remindTypeTr" style="display:none;">
			<td nowrap>提醒类型：</td>
			<td nowrap align="left"><div class="col-xs-8 form-group" >
          <select style="width:50%;float:left;" class="form-control BigSelect" id="remindType" name='remindType' disabled="disabled" onchange="sel_change(this.value)">
              <option value="2">按天重复</option>
              <option value="3">按周重复</option>
              <option value="4">按月重复</option>
              <option value="5">按年重复</option>
          </select>    
          
           <span id='dayShow'>&nbsp;&nbsp;<input type="checkbox" disabled="disabled" name="isWeekend" id="isWeekend"  value='1'></input>&nbsp;选中为排除周六、日 </span></div>
       </td>
	     </tr>
	       <tr id="FrequencyTr" style="display:none;" >
		<td nowrap>频率：</td>
			<td nowrap align="left" style="line-height:35px;" ><div class="col-xs-8 form-group"  >
			 	<span id='forday' >
			 	<input name="freDay" readonly="readonly" id="freDay" value="1" class="form-control" style="width:50%;float:left;" type="text" />天提醒一次
			 	</span>
			 	<span id='forweek' style='display:none;'>
			 	<input name="freWeek" readonly="readonly" id="freWeek" value="1" class="form-control" style="width:50%;float:left;" type="text" />周提醒一次
             </span>
             <span id='formon' style='display:none;'>
				<input name="freMon" readonly="readonly" id="freMon" value="1" class="form-control" style="width:50%;float:left;" type="text" />月提醒一次
             </span>
             <span id='foryears' style='display:none;'>
	                <input name="freYear" readonly="readonly" id="freYear" value="1" class="form-control" style="width:50%;float:left;" type="text" />年提醒一次
             </span>
			</div></td>
		</div></td>
		</tr>
	     <tr id="remindTimeTr" style="display:none;">
			<td nowrap>提醒时间：</td>
			<td nowrap align="left"><div class="col-xs-12 form-group" >
			 	<span id='day' >
			 		</span>
			 	<span id='week' style='display:none;'>
             </span>
             <span id='mon' style='display:none;'>
             	<input readonly="readonly"  type="text" onclick="choiceMonth();" style="width:35%;float:left;" class="form-control BigSelect" id="remindDate4" name="remindDate4" >
             </span>
             
             <span id='years' style='display:none;'>
	                <select style="width:25%;float:left;" disabled="disabled" class="form-control BigSelect" id="remindDate5Mon" name="remindDate5Mon">
					</select>
					<select style="width:25%;float:left;" disabled="disabled" class="form-control BigSelect" id="remindDate5Day" name="remindDate5Day">
            	</select>
             </span>
         	<input id="remindTime" value="00:00:00" name="remindTime" value='' disabled="disabled" type="text" onclick="setRemindTime(this);" style="width:25%;float:left;" class="form-control BigInput easyui-validatebox" validType="time[]"   data-placement="right" data-content="" data-toggle="popover" data-html="true">&nbsp;为空为当前时间</div>
			</td>
	    </tr>
	    <tr id="endTr" style="display:none;" >
			<td nowrap>结束重复：</td>
			<td nowrap align="left"><div class="col-xs-8 form-group"  >
			 	<input type="text" readonly="readonly" disabled="disabled"  name="endWhile" style="float:left;" id="endWhile" class="form-control BigInput" onClick="WdatePicker({dateFmt:'yyyy-MM-dd '})"/></div>
        	</td>
		</tr>
		<tr>
		<td nowrap>参与者：</td>
		<td nowrap align="left"><div class="col-xs-8 form-group" >
			<input id="accountId" name="accountId" type="hidden">
			<textarea  name="userName" id="userName" style="float:left;" class="form-control" onClick="selectUser(['accountId','userName']);" required="true" rows="2" cols="35" readonly="readonly"></textarea>
		    </div>
       </td>
       
       </tr>
		<tr id="beforeRemindTr">
		<td nowrap>提前时间：</td>
		<td nowrap align="left" style="line-height:35px;" >
			<div class="col-xs-8 form-group"  ><select style="float:left;" class="form-control BigSelect" id="remindmins" name='remindmins' >
              <option value="0">事件发生时</option>
              <option value="15">15分钟前</option>
              <option value="30">30分钟前</option>
              <option value="60">1个小时前</option>
              <option value="120">2个小时前</option>
              <option value="1440">1天前</option>
              <option value="10080">1周前</option>
          </select></div>
		</td>
		</tr>
		</tr>
		<tr>
		<td nowrap>提醒：</td>
		<td nowrap align="left"><div class="col-xs-8 form-group" >
			<!-- <input id="smsRemind" name="smsRemind"  type="checkbox" value='1'> <label for='smsRemind'>是否使用内部短信</label> -->
			<div id="smsdiv" name="smsdiv"></div>
		</td>
		</tr>
		<tr class="">
			<td colspan="2" align="center">
				<div align='center'>
					<input type="text" id="sid" name="sid" style="display:none;" value='0'/>
					<input type="text" id="oldMenuId" name="oldMenuId" style="display:none;"/>
				</div>
			</td>
		</tr>
	</table>
	</div>
	</div>
</form>

<div class="modal fade" id="weekModal" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true"  style="overflow:hidden;overflow-y:hidden;">
   <div class="modal-dialog" id="week-div-modal-dialog"  name="div-modal-dialog">
      <div class="modal-content" >
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times;  </button>
            <h5 class="modal-title" id="weekModalLabel">
               选择星期
            </h5>
         </div>
         <div class="modal-body" style="padding: 0px;" id="week-modal-body" >
         </div>
         <div class="modal-footer" align="center" >
         	<button type="button" class="btn btn-primary" onclick="getCheck();" >确定  </button>
            <button type="button" class="btn btn-default btn_close"  data-dismiss="modal">关闭  </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<div id="modaldialog"></div>
</body>
</html>