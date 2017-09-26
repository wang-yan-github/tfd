<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>工作日志设置</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/diary/diary.css"></link>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>

<script>
$(function(){
	getFit();
});
function getFit(){
	var url=contextPath+"/diary/act/DiaryFitAct/lookFitAct.act";
	$.ajax({
		   url:url,
		   type:'POST',
	   	   dataType:"json",
	   		async:false,
	   		success:function(data){
	   			if(!jQuery.isEmptyObject(data)){
	   			var fromdata=data;
				for(var name in fromdata){
					$("#"+name).val(fromdata[name]);
					if(fromdata[name]==1){
						$("#"+name).prop("checked",true);
					}
				}
	   		}
	   		}
	   });
}
function saveFit(){
	var url=contextPath+"/diary/act/DiaryFitAct/saveFitAct.act";
	var commStatus=0;
	if($('#commStatus').prop("checked")){
		commStatus=1;
	}
	var shareStatus=0;
	if($('#shareStatus').prop("checked")){
		shareStatus=1;
	}
	$.ajax({
		   url:url,
		   type:'POST',
	   	   dataType:"text",
	   	   data:{
	   		fitId:$("#fitId").val(),
	   		startTime:$("#startTime").val(),
	   		endTime:$("#endTime").val(),
	   		lockDay:$("#lockDay").val(),
	   		commStatus:commStatus,
	   		shareStatus:shareStatus
	   	   },
	   		async:false,
	   		success:function(data){
	   			if(data!=0){
	   				parent.layer.msg('保存成功');
	   			}
	   		}
	   });
}
</script>
</head>
<body>
<div class="top_info">
<div class="top_info_left icontop-basic_hover">
<span class="title_name" >工作日志设置</span>
</div>
</div>
<div  style="margin-top: 20px;width: 60%;margin-left: 20%;">
   <div class="widget-header bg-blueberry">
<span class="widget-caption">设置锁定时间范围</span>
</div>
<div class="list-group-item"  style="padding: 0px;">
<table class="table table-striped table-condensed">
<tr>
<input id="fitId" name="fitId" type="hidden">
<td width="240px;">锁定以下时间范围的日志：</td>
<td>
<div class="col-xs-5">
<input type="text" name="startTime" id="startTime" size="20" style="cursor: pointer;" readonly="readonly"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control"></div>
			<span style="float: left;margin-top: 8px;">至</span>
			<div class="col-xs-5 form-group">
			<input type="text" name="endTime" id="endTime" size="20" readonly="readonly" style="cursor: pointer;"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control">
			</div>
			</br><br>
			<div class="col-xs-12" style="font-size: 12px;">说明：都为空表示不锁定，也可以只填写其中一个</div>
</td>
</tr>
<tr>
<td>锁定指定天数以前的日志：</td>
<td>
<div class="col-xs-2" style="float: left;margin-top: 8px;">锁定&nbsp;</div>
<div class="col-xs-2" style="padding-right: 0px;padding-left: 0px;">
<input id="lockDay" name="lockDay" class="form-control" type="text"></input>
</div>
<span style="float: left;margin-top: 8px;">&nbsp;天前的日志</span> 

			</br>
			<div class="col-xs-12" style="font-size: 12px;">说明：0或空表示不锁定 </div>
</td>
</tr>
<tr>
<td>是否允许评论：</td>
<td>
<div class="col-xs-12">
<input type="checkbox" id="commStatus" name="commStatus" style="margin-top: 1px;"></input>
<span>允许其他人给予点评</span>
</div>
			<div class="col-xs-12" style="font-size: 12px;margin-top: 10px;">说明：选中为允许所有人给予点评  </div>
</td>
</tr>
<tr>
<td>是否允许设置默认对所有人共享：</td>
<td>
<div class="col-xs-12">
<input type="checkbox" id="shareStatus" name="shareStatus" style="margin-top: 1px;"></input>
<span>允许对所有人共享 </span>
</div>
<div class="col-xs-12" style="font-size: 12px;margin-top: 10px;">说明：选中为允许对所有人共享   </div>
</td>
</tr>
</table>
</div>
<div align="center" style="margin-top: 20px;">
  <button type="button" name="send" onclick="saveFit();" class="btn btn-primary">保存</button>
 </div>
</div>
</body>
</html>