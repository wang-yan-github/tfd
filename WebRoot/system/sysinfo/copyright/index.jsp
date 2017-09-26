<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<html>
<head>
<title>系统信息</title>
<script type="text/javascript">
$(function(){
	var requestUrl=contextPath+"/tfd/system/sysinfo/copyright/act/SysInfoAct/getSysInfoAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			for(var name in data){
				$("#"+name).html(data[name]);
			}
		}
	});
});
</script>
</head>
<body>
<div align="center">
<div class="list-group"  style="width: 60%;margin-top: 20px;">
   <a  class="list-group-item active" >
      <h5 class="list-group-item-heading">
        系统信息
      </h5>
   </a>
   <div  class="list-group-item">
      <h5 class="list-group-item-heading">
      <div style="float: left;">
         单位信息：</div><div id="UNIT"  style="float: left;"></div>
      </h5>
   </div>
   <div  class="list-group-item">
      <h5 class="list-group-item-heading"><div style="float: left;">服务器系统： </div><div id="OS_NAME" style="float: left;"></div>
      </h5>
   </div>
   
    <div  class="list-group-item">
      <h5 class="list-group-item-heading"><div style="float: left;">数据库：</div><div id="DB_NAME" style="float: left;"></div></h5>
   </div>
   
   <div  class="list-group-item">
      <h5 class="list-group-item-heading"><div style="float: left;">发布器版本： </div><div style="float: left;"><%= application.getServerInfo() %></div></h5>
   </div>
   
   <div  class="list-group-item">
      <h5 class="list-group-item-heading"><div style="float: left;">JDK版本： </div><div id="JDK" style="float: left;"></div></h5>
   </div>
   
    <div  class="list-group-item">
      <h5 class="list-group-item-heading"><div style="float: left;">授权人员数： </div><div id="USER_COUNT" style="float: left;"></div></h5>
   </div>
   
   <div  class="list-group-item">
      <h5 class="list-group-item-heading"><div style="float: left;">序列号： </div><div id="SN" style="float: left;"></div></h5>
   </div>
   
   <div  class="list-group-item">
      <h5 class="list-group-item-heading"><div style="float: left;">机器码： </div><div id="MACHINE_CODE" style="float: left;"></div></h5>
   </div>
   
   <div  class="list-group-item">
      <h5 class="list-group-item-heading"><div style="float: left;">版权： </div><div id="COPY_RIGHT" style="float: left;"></div></h5>
   </div>
   <div  class="list-group-item">
      <h5 class="list-group-item-heading"><div style="float: left;">注册状态： </div><div id="REG_FLAG" style="float: left;"></div></h5>
   </div>
</div>
<div><button class="btn btn-primary"  data-toggle="modal" data-target="#myModal">注册</button></div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               系统注册
            </h4>
         </div>
         <div class="modal-body">
         <form id="form1" name="form1" method="post" action="servlet/fileServlet" enctype="multipart/form-data">
            <input type="file" id="regfile" name="regfile">
         </form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default"
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary">
               注册
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->


</body>
</html>