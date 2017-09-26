<%@page import="tfd.system.unit.account.data.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
	String userName =request.getSession().getAttribute("USER_NAME").toString();
	Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
	String userId = account.getAccountId();
%>
<html>
<head>
	<title>欢迎使用V1.0</title>
	<link rel="stylesheet" type="text/css" href="<%=stylePath%>/frame/index.css"></link> 
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" type="text/css" href="<%=stylePath%>/frame/index-ztree/zTreeStyle.css"/>
    
  	<script type="text/javascript" src="<%=contextPath%>/system/jsall/ztree/jquery.ztree.all-3.5.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/moment.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/locale/zh-cn.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/LunarCalendarUtil.js"></script>
    
	<script type="text/javascript" src="<%=contextPath%>/system/frame/js/index.logic.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/frame/js/notice.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/frame/js/notices.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/frame/js/desktop-info.logic.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/frame/js/desktop-change-org.logic.js"></script>
	
	
</head>
<script type="text/javascript">
var userId = "<%=userId%>";
</script>
<body>
	<div class="easyui-layout" id="body" data-options="fit:true">
		<div id="north" data-options="region:'north',border:false,height:40">
			<img id="topImg" style="width:40px;height:24px;float:left;margin-top:23px;margin-left:20px;display:none;" />
			<div class="title" style="float:left;" >智能业务平台 v1.0</div>
			<div class="top-bar">
				<div class="right-bar">
					<span id="personSet" title="个人设置" class="glyphicon glyphicon-briefcase"></span>
					<span id="desktop-info-toggle" title="动态" class="glyphicon glyphicon-th-large span-margin-left"></span>
					<span id="desktop-change-org" title="切换机构" class="glyphicon glyphicon-random span-margin-left"></span>
					<span id="logout" title="注销" class="glyphicon glyphicon-log-out span-margin-left"></span>
				</div>
				<div class="right-top" >
					<div class="personHead" >
						<img id="personHeadImg" onerror="this.src='<%=imgPath %>/personal/error.jpg'" onclick="javascript:showPersonal('<%=userId %>');"  />
					</div>
					<span class="frame-user-name">
						<a href="javascript:void(0);" onclick="javascript:showPersonal('<%=userId %>');" style="color:#FFF;font-weight:bolder;"  ></a>
					</span>
				</div>
			</div>
			<div class="search" >
				<input type="text" id="searchContent" placeholder="数据检索" />
				<div class="search_img_div" onclick="search();" >
					<img src="<%=imgPath %>/frame/search.png" class="search_img" />
				</div>
			</div>
		</div>
		<div id="west" data-options="region:'west',width:200">
			<div class="west-container">
				<div class="tab">
					<div id="menu_left" >主菜单</div>
					<div id="menu_right">快捷菜单</div>
				</div>
				<div class="menu_body">
					<div class="content" id="menu_content"></div>
				</div>
				<div class="shortMenu">
					<div class="content" id="shortMenu_content"></div>
				</div>

			</div>
		</div>
		<div id="center" data-options="region:'center',border:false">
		
			<div id="tabs-bar" class="easyui-tabs" data-options="region:'center',border:false,fit:true"></div>
			
			<div class="list-group document-click-hide" id="change-org"  style="width:300px;display: none;float: right;">
			  <a class="list-group-item active" >请选择需切换的机构</a>
			</div>
			
			<div id="desktop-info" class="document-click-hide">
				<button type="button" class="close nav-close" title="关闭">&times;</button>
				<div class="nav-container">
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active">
							<a href="#internet-info" aria-controls="home" role="tab" data-toggle="tab">今日资讯</a>
						</li>
						<li role="presentation">
							<a href="#enterprise-info" aria-controls="profile" role="tab" data-toggle="tab">企业资讯</a>
						</li>
						<li role="presentation">
							<a href="#org-info" aria-controls="messages" role="tab" data-toggle="tab">组织机构</a>
						</li>
					</ul>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="internet-info">
							<div class="overflow-container">
								<div class="overflow-container-right0">
									<iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=48" width="100%" height="100px" frameborder="0" scrolling="no"></iframe>
									<div id="calendar"></div>
									<div class="h4">&nbsp;今日头条</div>
									<div id="news"></div>
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="enterprise-info">
							<div class="tab-panel-container">
								<div class="h4" style="text-align:center;">暂无信息</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="org-info">
							<div class="tab-panel-container">
								<ul class="nav nav-pills" role="tablist">
									<li role="presentation" class="active">
										<a href="#org-online" aria-controls="home" role="tab" data-toggle="tab">在线</a>
									</li>
									<li role="presentation">
										<a href="#org-all" aria-controls="profile" role="tab" data-toggle="tab">全部</a>
									</li>
								</ul>
								<div class="tab-content">
									<div role="tabpanel" class="tab-pane active" id="org-online">
										<div class="overflow-container">
											<div class="overflow-container-right0">
												<ul class="ztree org-tree" id="user-online-tree"></ul>
											</div>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane active" id="org-all">
										<div class="overflow-container">
											<div class="overflow-container-right0">
												<ul class="ztree org-tree" id="org-tree"></ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<div id="south" data-options="region:'south',height:25,border:false">
			<div class="south-text">
				<div id="copyright">Copyright @ 2011-2016</div>
				<div id="online-user-count">在线人数:</div>
			</div>
		</div>
	</div>
	<div id="Notice"></div>
	
	<div id="userinfo-popover" class="document-click-hide"></div>
	<div id="userInfoDiv"></div>
 </body>
 </html>

