<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.system.tool.SysTool" %>
<%@ page import="com.system.servlet.ServletTool" %>
<%@ page import="com.system.global.SysProps" %>
<%
  String contextPath = request.getContextPath();
  if (contextPath.equals("")) {
    contextPath = "/tfd";
  }
  String limitUploadFiles  = SysProps.getProp("limitUploadFiles");
  String useSearchFunc = SysProps.getProp("useSearchFunc");
  String isDev = SysProps.getProp("isDevelopContext"); 
  if (SysTool.isNullorEmpty(isDev)) { 
    isDev = "0"; 
  }
  //获取主题的索引号
  int styleIndex = 1;
  Integer styleInSession = (Integer)request.getSession().getAttribute("STYLE_INDEX");
  if (styleInSession != null) {
    styleIndex = styleInSession;
  }
  
  
  String stylePath = contextPath + "/system/styles/css/style" + styleIndex;
  String imgPath = contextPath + "/system/styles/images";
  
  String oaStyle = (String)request.getSession().getAttribute("OA_STYLE");
  if(SysTool.isNullorEmpty(oaStyle)){
    oaStyle = "1";
  }
  String fullContextPath = ServletTool.getWebAppDir(this.getServletConfig().getServletContext());
  String ssoGPower = SysProps.getString("ssourl.gpower");
  String isOnlineEval = SysProps.getString("IS_ONLINE_EVAL");
  int maxUploadSize = SysProps.getInt("maxUploadFileSize");
  //系统信息
  String shortOrgName = SysProps.getString("shortOrgName");
  String orgName = SysProps.getString("orgName");
  String productName = SysProps.getString("productName");
  String fullOrgName = SysProps.getString("fullOrgName");
  String SysInfo = SysProps.getString("SysInfo");
  String shortProductName = SysProps.getString("shortProductName");
  String orgFirstSite = SysProps.getString("orgFirstSite");
  String orgSecondSite = SysProps.getString("orgSecondSite");
  String workflowZipDown = SysProps.getString("workflowZipDown");
%>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/easyui/themes/bootstrap/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/system/jsall/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/bootstrapvalidator/bootstrapValidator.css"/>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/main/index.css"></link> 

<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath %>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrapvalidator/bootstrapValidator.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/SysFrame.js"></script>

<script type="text/javascript" src="<%=contextPath%>/system/jsall/layer/layer.js"></script>
<script type="text/javascript" src="<%=contextPath%>/system/jsall/userinfo/index.js"></script>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/personal/personal.css"></link> 

<script type="text/javascript">
$(document).keydown(function(e){ 
    var keyEvent; 
    if(e.keyCode==8){ 
        var d=e.srcElement||e.target; 
        if(d.tagName.toUpperCase()=='INPUT'||d.tagName.toUpperCase()=='TEXTAREA'){ 
            keyEvent=d.readOnly||d.disabled; 
        }else{ 
            keyEvent=true; 
        } 
    }else{ 
        keyEvent=false; 
    } 
    if(keyEvent){ 
        e.preventDefault(); 
    } 
}); 
var indexs = null;
function ajaxLoading(){
	indexs = layer.load(0,{shade: [0.1,'#fff']}); 
} 
function ajaxLoad(){
	indexs = top.layer.load(1,{shade: false}); 
} 
function ajaxLoadEnd(){ 
 	layer.close(indexs);           
}
function ajaxLoadClose(){
	top.layer.close(indexs); 
}



function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}
// 变量定义
var contextPath = "<%=contextPath %>";
var imgPath = "<%=imgPath %>";
var stylePath="<%=stylePath%>";
var ssoUrlGPower = "<%=ssoGPower%>";
var limitUploadFiles = "<%=limitUploadFiles%>"
var isOnlineEval = "<%=isOnlineEval%>";
var useSearchFunc = "<%=useSearchFunc%>";
var maxUploadSize = <%=maxUploadSize%>;
var isDev = "<%=isDev%>";
var ostheme = "<%=oaStyle %>";
//系统信息
var shortOrgName = "<%=shortOrgName%>";
var orgName = "<%=orgName%>";
var productName = "<%=productName%>";
var fullOrgName = "<%=fullOrgName%>";
var SysInfo = "<%=SysInfo%>";
var shortProductName = "<%=shortProductName%>";
var orgFirstSite = "<%=orgFirstSite%>";
var orgSecondSite = "<%=orgSecondSite%>";
</script>