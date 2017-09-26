<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>相册设置</title>
<script type="text/javascript" src="<%=contextPath%>/system/photo/js/set.logic.js"></script>
<script type="text/javascript">

</script>
</head>
<body onload="doinit();">
<div class="panel panel-info">
   <div class="panel-heading">
      <h5 class="panel-title">
      相册设置
      </h5>
   </div>
   <div class="panel-body">
<table class="table table-striped">
<tr>
<td>序号</td>
<td>相册名称</td>
<td>相册路径</td>
<td>创建人员</td>
<td>创建日期</td>
<td>设置</td>
<td>操作</td>
</tr>
<tbody id="content" name="content"></tbody>
</table>
</div>
</div>

<input type="hidden" data-toggle="modal" data-target="#myModal_update" id="btn_edit">
<div class="modal fade" id="myModal_update" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               编辑相册
            </h4>
         </div>
         <div class="modal-body">
         <table class="table table-striped" >
           <tr>
<td width="15%">序号:</td>
<td><input class="form-control " type="text" id="sortId"  ><input class="form-control " type="hidden" id="photoId"  ></td>
</tr>
<tr>
<td>相册名称:</td>
<td><input class="form-control "  type="text" id="photoName"  >	</td>
</tr>
<tr>
<td>硬盘路径:</td>
<td><input class="form-control "  type="text" id="photoPath"  >	</td>
</tr>
</table>
         </div>
         <div class="modal-footer">
          <button type="button" id="btn_update" onclick="editPhoto()"  class="btn btn-primary">确认</button>
            <button type="button" id="btn_close" class="btn btn-default" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>
</div>

</body>
</html>