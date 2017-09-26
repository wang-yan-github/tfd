<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.system.filetool.UpFileTool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%@ page language="java" import="com.zhuozhengsoft.pageoffice.*" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
	
		String attachId=request.getParameter("attachId");
		String privFlag = request.getParameter("privFlag");
		UpFileTool fileTool = new UpFileTool();
		String path = fileTool.getAttachPath(attachId);
		String attachName = path.substring(path.lastIndexOf("\\")+19,path.length());
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
		poCtrl1.setCaption(attachName);
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
		if(privFlag.equals("1"))
		{
			poCtrl1.setOfficeToolbars(false);
		}else if(privFlag.equals("2"))
		{
			poCtrl1.setAllowCopy(false);
		}else if(privFlag.equals("3"))
		{
			poCtrl1.setAllowCopy(true);
		}else if(privFlag.equals("4"))
		{
			//添加自定义按钮
			//poCtrl1.addCustomToolButton("保存","Save",1);
			//设置保存页面
			poCtrl1.setSaveFilePage("saveFile.jsp?attachId="+attachId);
		}
		poCtrl1.setCustomToolbar(false);
		poCtrl1.setMenubar(true);
		poCtrl1.setJsFunction_AfterDocumentOpened("AfterDocumentOpened");
		poCtrl1.setJsFunction_AfterDocumentClosed("SaveCopy");
		poCtrl1.webOpen(path, OpenModeType.docNormalEdit, "admin");
		poCtrl1.setTagId("PageOfficeCtrl1"); //此行必须	
		
		
		String fileId = request.getParameter("fileId");
		
		%>
<html>
<head>
<title>OFFICE在线阅读工具</title>
<style >
	body,html{margin:0px;padding:0px;height:100%;width:100%;}
	html{overflow:hidden;}
</style>
</head>
<body>
 <script type="text/javascript">
 //另存为可以理解为下载，可以另存到本地
 //privFlag="1" 只可以查看，不可以打印
 //privFlag="2" 可以查看，也可以打印
 //privFlag="3" 可以查看，也可以打印和复制
 //privFlag="4" 可以查看，也可以打印和复制，也可以编辑保存
 var privFlag = "<%=privFlag%>";
 var attachId = "<%=attachId%>";
 var fileId = "<%=fileId%>";
 window.history.pushState("","","index.jsp");
 var fielName = "";
        function AfterDocumentOpened() {
        	if(privFlag=="1")
        		{
        			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(1, false); // 打开菜单
	            	document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(2, false); // 关闭菜单
		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // 禁止保存
		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // 禁止另存
		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, false); //禁止打印
		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, false); // 禁止页打印预览
		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(7, false); // 禁止页面设置
		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(8, false); // 禁止页面设置
        		}else if(privFlag=="2")
        			{
        			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // 禁止保存
		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // 禁止另存
		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(7, false); // 禁止页面设置
		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(8, false); // 禁止页面设置
        			}else if(privFlag=="3"){
    		            document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, false); // 禁止另存
        			}else if(privFlag=="4"){
        				document.getElementById("PageOfficeCtrl1").ShowRevisions = true;
        			}
        };
        function SaveCopy(){
        	if(fileId!="undefined"||fileId!=null){
        		if(confirm("是否修订版本?")){
            		var requestUrl= contextPath+"/tfd/system/folder/act/FolderAct/createHistoryFileLogic.act";
            		$.ajax({
            			url:requestUrl,
            			data:{attachId:attachId},
            			dataType:"text",
            			async:false,
            			error:function(e){
            				alert(e.message);
            			},
            			success:function(data){
            				fileName = data;
            				getUpdate();
            			}
            		});
            	}
        	}
        }
        function getUpdate(){
        	/* var updateFlag = document.getElementById("PageOfficeCtrl1").ShowRevisions;
        	if(updateFlag){ */
        		var requestUrl= contextPath+"/tfd/system/folder/act/FolderAct/addRecord.act";
        		$.ajax({
        			url:requestUrl,
        			data:{fileId:fileId,attachId:attachId,fileName:fileName},
        			dataType:"json",
        			async:false,
        			error:function(e){
        				alert(e.message);
        			},
        			success:function(data){
        				alert("修订成功");
        			}
        		});
        	/* } */
        }
        
        
        
        $(document).ready(function(e) {	
    		var counter = 0;
    		if (window.history && window.history.pushState) {
    			$(window).on('popstate', function () {
    				window.history.pushState('forward', null, '#');
    				window.history.forward(1);
    				$("#label").html("第" + (++counter) + "次单击后退按钮。");
    			});
    		}
    		window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
    		window.history.forward(1);
    	});
        
        $(document).bind("keydown",function(e){   
            e=window.event||e;
            if(e.keyCode==116){
            e.keyCode = 0;
            return false; //屏蔽F5刷新键   
            }
         });
    </script>
   <form id="form1"  style="height:100%;" >
       <po:PageOfficeCtrl id="PageOfficeCtrl1" ></po:PageOfficeCtrl>
   </form>
</body>
</html>
 