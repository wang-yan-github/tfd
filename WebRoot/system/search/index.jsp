<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/search/index.css"></link> 
<script type="text/javascript" src="<%=contextPath%>/system/search/js/index.logic.js"></script>
<title>数据检索</title>
<style>
html,body{
height: 100%;
margin:0px;
padding:0px;
}
</style>
<script>
</script>
</head>
<body onload="doinit();"  >
	<div class="search_top" >
		<div class="search_logo" >
			<img src="<%=imgPath %>/search/search.png" width="48" height="48" />
		</div>
		<div class="logo_name" >数据检索</div>
		<div class="search" >
			<input type="text" id="searchContent" placeholder="数据检索" />
			<div class="search_img_div" onclick="search();" >
				<img src="<%=imgPath %>/frame/search.png" class="search_img" />
			</div>
		</div>
	</div>
	<div class="search_main" id="search_main" >
		<div class="main_ul_div" >
			<ul>
				<li onclick="moveToModule('user',0);" >人员</li>
				<li onclick="moveToModule('dept',1);" >部门</li>
				<li onclick="moveToModule('email',2);" >邮件</li>
				<li onclick="moveToModule('workflow',3);" >工作流</li>
				<li onclick="moveToModule('folder',4);" >文件柜</li>
				<li onclick="moveToModule('news',5);" >新闻</li>
				<li onclick="moveToModule('notice',6);" >公告</li>
				<li onclick="moveToModule('diary',7);" >日志</li>
			</ul>
		</div>
		<div id="main_content1"  >
			<a id="user_content" ></a>
			<span class="content_title" onclick="closeMainContent(1);" ><img src="<%=imgPath %>/search/triangle-bottom.png" width="16" height="16" /><span>人员</span></span>
			<div class="content_main" >
			</div>
		</div>
		<div id="main_content2" >
			<a id="dept_content" ></a>
			<span class="content_title" onclick="closeMainContent(2);" ><img src="<%=imgPath %>/search/triangle-right.png" width="16" height="16" /><span>部门</span></span>
			<div class="content_main" >
			</div>
		</div>
		<div id="main_content3" >
			<a id="email_content" ></a>
			<span class="content_title" onclick="closeMainContent(3);" ><img src="<%=imgPath %>/search/triangle-right.png" width="16" height="16" /><span>邮件</span></span>
			<div class="content_main" >
			</div>
		</div>
		<div id="main_content4" >
			<a id="workflow_content" ></a>
			<span class="content_title" onclick="closeMainContent(4);" ><img src="<%=imgPath %>/search/triangle-right.png" width="16" height="16" /><span>工作流</span></span>
			<div class="content_main" >
			</div>
		</div>
		<div id="main_content5" >
			<a id="folder_content" ></a>
			<span class="content_title" onclick="closeMainContent(5);" ><img src="<%=imgPath %>/search/triangle-right.png" width="16" height="16" /><span>文件柜</span></span>
			<div class="content_main" >
			</div>
		</div>
		<div id="main_content6" >
			<a id="news_content" ></a>
			<span class="content_title" onclick="closeMainContent(6);" ><img src="<%=imgPath %>/search/triangle-right.png" width="16" height="16" /><span>新闻</span></span>
			<div class="content_main" >
			</div>
		</div>
		<div id="main_content7" >
			<a id="notice_content" ></a>
			<span class="content_title" onclick="closeMainContent(7);" ><img src="<%=imgPath %>/search/triangle-right.png" width="16" height="16" /><span>公告</span></span>
			<div class="content_main" >
			</div>
		</div>
		<div id="main_content8" >
			<a id="diary_content" ></a>
			<span class="content_title" onclick="closeMainContent(8);" ><img src="<%=imgPath %>/search/triangle-right.png" width="16" height="16" /><span>日志</span></span>
			<div class="content_main" >
			</div>
		</div>
	</div>
</body>
</html>