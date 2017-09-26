<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%@page import="com.system.filetool.UpFileTool"%>
<%@ page import="com.zhuozhengsoft.pageoffice.*, com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
String attachId=request.getParameter("attachId");
String privFlag = request.getParameter("privFlag");
UpFileTool fileTool = new UpFileTool();
String path = fileTool.getAttachPath(attachId);
String attachName = path.substring(path.lastIndexOf("\\")+19,path.length());
PDFCtrl poCtrl1 = new PDFCtrl(request);
poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //此行必须
poCtrl1.setCaption(attachName);
	if(privFlag.equals("1"))
	{
		poCtrl1.setAllowCopy(false);
	}else if(privFlag.equals("2"))
	{
		poCtrl1.setAllowCopy(false);
		poCtrl1.addCustomToolButton("打印", "Print()", 6);
	}else
	{
		poCtrl1.setAllowCopy(true);
		poCtrl1.addCustomToolButton("打印", "Print()", 6);
		poCtrl1.addCustomToolButton("另存为", "SaveDocument()", 1);
	}

// Create custom toolbar
poCtrl1.setMenubar(true);
poCtrl1.addCustomToolButton("隐藏/显示书签", "SetBookmarks()", 0);
poCtrl1.addCustomToolButton("-", "", 0);
poCtrl1.addCustomToolButton("实际大小", "SetPageReal()", 16);
poCtrl1.addCustomToolButton("适合页面", "SetPageFit()", 17);
poCtrl1.addCustomToolButton("适合宽度", "SetPageWidth()", 18);
poCtrl1.addCustomToolButton("-", "", 0);
poCtrl1.addCustomToolButton("首页", "FirstPage()", 8);
poCtrl1.addCustomToolButton("上一页", "PreviousPage()", 9);
poCtrl1.addCustomToolButton("下一页", "NextPage()", 10);
poCtrl1.addCustomToolButton("尾页", "LastPage()", 11);
poCtrl1.addCustomToolButton("-", "", 0);
poCtrl1.webOpen(path);
poCtrl1.setTagId("PDFCtrl1"); //此行必须
%>
<html>
  <head>
    <title>PDF在线工具文件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <style >
	body,html{margin:0px;padding:0px;height:100%;width:100%;}
	html{overflow:hidden;}
</style>
  <body>
  <!--**************   卓正 PageOffice 客户端代码开始    ************************-->
	<script language="javascript" type="text/javascript">
	    function SetBookmarks() {
	        document.getElementById("PDFCtrl1").BookmarksVisible = !document.getElementById("PDFCtrl1").BookmarksVisible;
	    }
	    
	    function Print() {
	        document.getElementById("PDFCtrl1").ShowDialog(4);
	    }
	    function SwitchFullScreen() {
	        document.getElementById("PDFCtrl1").FullScreen = !document.getElementById("PDFCtrl1").FullScreen;
	    }
	    function SetPageReal() {
	        document.getElementById("PDFCtrl1").SetPageFit(1);
	    }
	    function SetPageFit() {
	        document.getElementById("PDFCtrl1").SetPageFit(2);
	    }
	    function SetPageWidth() {
	        document.getElementById("PDFCtrl1").SetPageFit(3);
	    }
	    function ZoomIn() {
	        document.getElementById("PDFCtrl1").ZoomIn();
	    }
	    function ZoomOut() {
	        document.getElementById("PDFCtrl1").ZoomOut();
	    }
	    function FirstPage() {
	        document.getElementById("PDFCtrl1").GoToFirstPage();
	    }
	    function PreviousPage() {
	        document.getElementById("PDFCtrl1").GoToPreviousPage();
	    }
	    function NextPage() {
	        document.getElementById("PDFCtrl1").GoToNextPage();
	    }
	    function LastPage() {
	        document.getElementById("PDFCtrl1").GoToLastPage();
	    }
	    function RotateRight() {
	        document.getElementById("PDFCtrl1").RotateRight();
	    }
	    function RotateLeft() {
	        document.getElementById("PDFCtrl1").RotateLeft();
	    }
	    
	    function SaveDocument(){
		    document.getElementById("PDFCtrl1").ShowDialog( 3 ); 
	    }
	</script>
    <!--**************   卓正 PageOffice 客户端代码结束    ************************-->
  <form id="form1"  style="height:100%;" >
      <po:PDFCtrl id="PDFCtrl1" /></po>
 </form>
  </body>
</html>