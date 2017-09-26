<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api-simple.jsp"%>
<%
	String him=request.getParameter("him");
	String chatType=request.getParameter("chatType");
%>
<html>
<head>
	<meta http-equiv="charset" content="utf-8">
	<title>IM</title>
    <link rel="stylesheet" href="<%=contextPath%>/system/jsall/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
    <link rel="stylesheet" href="css/pcim.css">
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery.json.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/system/jsall/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/jquery.json.js"></script>
	<script type="text/javascript" src="<%=contextPath %>/system/jsall/moment/moment.min.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/im/zeus.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/system/jsall/api/sys.unit.userinfo.api.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.10.4.min.js"></script>
	<script type="text/javascript" src="js/pcim.js"></script>
	
	<script>
		him="<%=him%>";
		chatType="<%=chatType%>";
	</script>
	
</head>
<body>
	<div id="twinkling-headimg">
		<img class="img"/>
	</div>
	<div class="unread-message">
		<div class="top"></div>
		<div class="messages">
		</div>
		<div class="bottom">
			<div class="ignore-all">忽略全部</div>
			<div class="read-all">查看全部</div>
		</div>
	</div>

	<div id="im-dialog">
		<div class="left">
			<div class="dialog-user-list">
			</div>
		</div>
		<div class="right">
			<div class="north">
				<div class="him"></div>
				<div class="dialog-option">
					<div class="dialog-min">
						<span class="glyphicon glyphicon-minus"></span>
					</div>
					<div class="dialog-close">
						<span class="glyphicon glyphicon-remove"></span>
					</div>
				</div>
				<div class="border-bottom"></div>
			</div>
			<div class="center">
				
				<div class="border-bottom"></div>
			</div>
			<div class="south">
				<div class="msg-input">
					<div class="msg-help-bar">
						<div class="help-bar bar-font"></div>
						<div class="help-bar bar-face"></div>
						<div class="help-bar bar-history">消息记录</div>
					</div>
					<div class="msg-text">
						<textarea class="msg-text-input"></textarea>
					</div>
				</div>
				<div class="current-dialog-option">
					<div class="dialog-option dialog-close">关闭</div>
					<div class="dialog-option msg-send">
						<div class="send">发送</div>
						<div class="send-setting" title="设置发送快捷键">
							<div class="border"></div>
							<img class="icon" src="img/send-opt-setting.png"/>
							<div class="setting-list">
								<div class="setting-option" data-shortcut="enter">
									<div class="ok"><div class="glyphicon glyphicon-ok"></div></div>按Enter发送消息
								</div>
								<div class="setting-option" data-shortcut="ctrl+enter">
									<div class="ok"><div class="glyphicon glyphicon-ok"></div></div>按Ctrl+Enter发送消息
								</div>
							</div>
						</div>
					</div>					
				</div>
			</div>
		</div>
	</div>


	
</body>
</html>
