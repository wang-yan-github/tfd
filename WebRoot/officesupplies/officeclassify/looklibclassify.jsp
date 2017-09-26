<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file="/system/returnapi/api.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>办公用品库</title>
<%String libraryId=request.getParameter("libraryId"); %>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/selectorg/showModalDialog.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js"></script>
<script type="text/javascript" src="<%=contextPath%>/officesupplies/officeclassify/looklibclassify.js"></script>
<script type="text/javascript">
var topId="<%=libraryId%>";
</script>
</head>
<body>
<div align="right" style="width:95%;height: 50px;margin-top: 10px;margin-right: 5%;">
<button type="button" class="btn btn-default" onclick="history.back();">返回</button>&nbsp;&nbsp;&nbsp;
 <button type="button" class="btn btn-primary btn-large" onclick="modalshow();">
   新建办公用品库分类
</button>
</div>
<div class="list-group-item"  style="padding: 0px;cursor: auto;width:90%;margin-left: 5%;">
<a style="cursor: auto;" class="list-group-item active">办公用品库分类设置</a>
<table class="table table-striped table-condensed" id="offlibclassify">
<tr>
<td align="center" width="5%">序号</td>
<td align="center" width="20%">办公用品类别</td>
<td align="center" width="10%">所属库</td>
<td align="center" width="20%">所属部门</td>
<td align="center" width="10%">排序号</td>
<td align="center" width="20%">操作</td>
</tr>
</table>
   </div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" style="overflow:hidden;overflow-y:hidden;">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close delempty" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新建办公用品库分类
            </h4>
         </div>
         <div class="modal-body" align="center">
           <table style="line-height: 30px;">
           <tr>
           <td>所属库：</td>
           <td>
           <div class="col-xs-10" id="libraryName">
           </div>
           </td>
           </tr>
           <tr>
           <td>物品分类名：</td>
           <td>
           <div class="col-xs-10">
			<input type="text" id="classifyName" name="classifyName" class="form-control "  placeholder="请输入名称......"></div>
           </td>
           </tr>
            <tr>
           <td>排序号：</td>
           <td>
           <div class="col-xs-10">
			<input type="text" id="sortId" name="sortId" class="form-control " ></div>
           </td>
           </tr>
           </table>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default delempty" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="addclassify();">
               保存
            </button>
         </div>
      </div><!-- /.modal-content -->
</div>
</div>


<div class="modal fade" id="updatemyModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close delempty" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               修改办公用品库分类
            </h4>
         </div>
         <div class="modal-body" align="center">
           <table style="line-height: 30px;">
           <tr>
           <td>所属库：</td>
           <td>
           <div class="col-xs-10" id="updatelibraryName">
           </div>
           </td>
           </tr>
           <tr>
           <td>物品分类名：</td>
           <td>
           <div class="col-xs-10">
			<input type="text" id="updateclassifyName" name="updateclassifyName" class="form-control "  placeholder="请输入名称......"></div>
           </td>
           </tr>
            <tr>
           <td>排序号：</td>
           <td>
           <div class="col-xs-10">
			<input type="text" id="updatesortId" name="updatesortId" class="form-control " ></div>
           </td>
           </tr>
           </table>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default delempty" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" id="updateclassify">
               修改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div>
</div>
</body>

</html>