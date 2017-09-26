<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>制度中心</title>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.min.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/easyui/themes/icon.css" type="text/css" />
<link rel="stylesheet" href="<%=contextPath%>/system/jsall/easyui/themes/default/easyui.css" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/swfupload/js/handlers.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/fileupload/fileupload.js"></script>

<script type="text/javascript" src="<%=contextPath%>/institution/search/js/index.logic.js"></script>

<style type="text/css">
	html,body{width: 100%;height: 100%;margin: 0px;padding: 0px;}
	.top{width: 100%;height: 10%;border-bottom:solid #ADD8E6 1px; }
	.main{width: 100%;height: 89%;border: none;}
	#search_content2,#search_content{width: 100%;height: 30px;position: absolute;top: 15px;}
	.changeButton{width:120px;height:35px;border:solid #CCC 1px;background-color:#F5F5F5;font-family:Simsun, Arial, sans-serif;font-weight:bold;color:#333;cursor:pointer;}
	#search_content2 span{font-family:Simsun, Arial, sans-serif;font-weight:bold;color:#333;font-size:12px;margin-left:10px;}
	.fontStyle{margin-left:25px;float:left;margin-top:10px;}
	.textStyle{width:150px;height:24px;border:solid #CCC 1px;float:left;margin-top:5px;}
	#dirContent{width: 250px;height: 300px;overflow: auto;display: none;position: absolute;
		border: solid #CCC 1px;background-color:white; z-index: 999;}
	#info{position: absolute;bottom: 0px;right: 0px;}
	.tops{width: 94%;height: 40px;position: absolute;text-align: center;font-size: 23px;font: bolder;}
	.mains{width: 94%;min-height:84%;position: absolute;margin-top: 40px;}
	#attachDiv{margin-left:2%;min-height:40px;
		padding:10px 0px 10px 10px;width:96%;margin-top:5px;}
	#contents{width:100%;margin-left:2%;padding:10px 0px 10px 10px;min-height:220px;}
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="dirContent" >
		<table class="TableBlock no-top-border" style="width: 100%;">
			<tr>
				<td class="TableData"><div><ul id="treeDemo" class="ztree"></ul></div></td>
			</tr>
		</table>
	</div>
	<div class="top">
		<div id="search_content" >
			<input type="text" id="search_inst" class="form-control" style="float:left;width:20%;margin-left:40%;" placeholder="请输入要查询的制度名称" />
			<input type="button" class="btn btn-primary" id="search_ok" value="查        询" />
			<input type="button" class="changeButton btn btn-default" value="切换至高级查询" />
		</div>
		<div id="search_content2" style="display:none;line-height:30px;" >
			<span class="fontStyle" >所属目录:</span>
			<input type="hidden" id="directory_id" />
			<input type="text" class="textStyle form-control" style="width:250px;" placeholder="请选择目录" id="directory_Name" onclick="javascript:showDir()" />
			<span class="fontStyle" >修订人:</span>
			<input type="text" class="textStyle form-control" id="userName" placeholder="请输入修订人" >
			<span class="fontStyle" >修订时间:</span>
			<input type="text" name="beginTime" id="beginTime" size="20" placeholder="起始时间"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="Wdate BigInput textStyle form-control">
				<span class="fontStyle" >至</span>
			<input type="text" name="endTime" id="endTime" size="20" placeholder="结束时间" style="margin-left:10px;"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="Wdate BigInput textStyle form-control">
			<input type="button" id="search_ok2" class="btn btn-primary" style="margin-left:50px;" value="查        询" />
			<input type="button" class="changeButton btn btn-default" value="切换至普通查询" />
		</div>
	</div>
	<div class="main">
		<div id="myTable" >
			
		</div>
	</div>
	<input type="hidden" id="openModal" data-toggle="modal" data-target="#myModal_update" />
	<div class="modal fade" id="myModal_update" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true"  >
   <div class="modal-dialog" style="height:400px;width:800px;" >
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
              查看
            </h4>
         </div>
         <div class="modal-body" style="height:400px;" >
         <div class="tops" >
		</div>
		<div class="mains" >
			<div id="contents" ></div>
			<div id='attachDiv' name='attachDiv' ></div>
			<div id="info"></div>
		</div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
         </div>
      </div>
</div>
</div>
</body>
</html>