<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html>
<!--
BeyondAdmin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.2.0
Version: 1.0.0
Purchase: http://wrapbootstrap.com
-->

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Dashboard" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>我的门户</title>
    <link rel="stylesheet" type="text/css"
			href="<%=contextPath%>/system/frame/info/js/style.css" />
     <script src="<%=contextPath%>/system/frame/js/portal.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/system/frame/info/js/mytable.css?t=20150521" />
<script src="<%=contextPath%>/system/frame/info/js/ispirit.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/frame/info/js/jquery.cookie.js"></script> 
<script type="text/javascript"
	src="<%=contextPath%>/system/frame/info/js/mytable.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/system/frame/info/js/utility.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=contextPath%>/system/jsall/assets/css/beyond.min.css"></link>

    <script src="<%=contextPath%>/system/frame/js/portal.js" type="text/javascript"></script>
    <link href="<%=contextPath%>/system/jsall/assets/css/typicons.min.css" rel="stylesheet" />
    <link href="<%=contextPath%>/system/jsall/assets/css/font-awesome.min.css" rel="stylesheet" />
    <script src="<%=contextPath%>/system/jsall/assets/js/skins.min.js"></script>

    <script src="<%=contextPath%>/system/jsall/assets/js/beyond.min.js"></script>
    <script src="<%=contextPath%>/system/jsall/assets/js/charts/easypiechart/jquery.easypiechart.js"></script>
    <script src="<%=contextPath%>/system/jsall/assets/js/charts/easypiechart/easypiechart-init.js"></script>
   

   
    <script type="text/javascript">
	    var g;
	    jQuery(function (){
	    	getFristWorkFlow();
			init();
			InitiateEasyPieChart.init();
	    });
	    var my_pos = true;
		var my_width = 50;
		var my_expand = true;
		/* function _edit(module_id) {
			show_msg("optionBlock");
			$("optionModuleTitle").innerText = $("module_" + module_id + "_text").innerText;
			$('display_lines').focus();
			$('display_lines').value = lines_per_page(module_id);
			$('scroll').checked = getCookie("my_scroll_12_" + module_id) == "true";
			$('col_width').value = my_width;
			$('module_id').value = module_id;
		} */

		/* function call_workflow_user_func() {
			window.location.reload();
		} */
		function _resize(module_id) {
			var module_i = $("module_" + module_id);
			var head_i = $("module_" + module_id + "_head");
			var body_i = $("module_" + module_id + "_body");
			var img_i = $("img_resize_" + module_id);
			var my_cookie = getCookie("my_expand_12");
			my_cookie = (my_cookie == null || my_cookie == "undefined") ? ""
					: my_cookie;//alert(my_cookie)
			if (body_i.style.display == "none") {
				module_i.className = module_i.className.substr(0,
						module_i.className.lastIndexOf(" "));
				head_i.className = head_i.className.substr(0, head_i.className
						.lastIndexOf(" "));
				body_i.style.display = "block";
				if (img_i.className.match("collapse_arrow"))
					img_i.className = img_i.className.replace("collapse_arrow",
							"expand_arrow");
				img_i.title = "折叠";

				if (my_cookie.indexOf(module_id + ",") == 0)
					my_cookie = my_cookie.replace(module_id + ",", "");
				else if (my_cookie.indexOf("," + module_id + ",") > 0)
					my_cookie = my_cookie.replace("," + module_id + ",", ",");

				//my_expand=true;
				setCookie("my_expand_all_12", "");
			} else {
				module_i.className = module_i.className + " listColorCollapsed";
				head_i.className = head_i.className + " moduleHeaderCollapsed";
				body_i.style.display = "none";
				if (img_i.className.match("expand_arrow"))
					img_i.className = img_i.className.replace("expand_arrow",
							"collapse_arrow");
				img_i.title = "展开";

				if (my_cookie.indexOf(module_id + ",") != 0
						&& my_cookie.indexOf("," + module_id + ",") <= 0)
					my_cookie += module_id + ",";
			}
			setCookie("my_expand_12", my_cookie);
		}
		function resize_all() {
			var img_all_resize = $("img_all_resize");
			var imgs = document.getElementsByTagName("a");
			var module_id_str = "";
			for (var i = 0; i < imgs.length; i++) {
				if (imgs[i].id.substr(0, 11) != "img_resize_")
					continue;

				var module_id = imgs[i].id.substr(11, imgs[i].id.length);
				module_id_str += module_id + ",";
				if (my_expand
						&& $("module_" + module_id + "_body").style.display != "none"
						|| !my_expand
						&& $("module_" + module_id + "_body").style.display == "none")
					_resize(module_id);
			}

			if (my_expand) {
				img_all_resize.className = img_all_resize.className.replace(
						"expand_arrow", "collapse_arrow");
				setCookie("my_expand_12", module_id_str);
			} else {
				img_all_resize.className = img_all_resize.className.replace(
						"collapse_arrow", "expand_arrow");
				setCookie("my_expand_12", "");
			}

			my_expand = !my_expand;
			setCookie("my_expand_all_12", my_expand ? "" : "0");
		}

		jQuery(document).ready(
				function() {
					jQuery('.inner12 img').removeAttr('width');
					jQuery('.inner12 img').removeAttr('height');
					jQuery('.inner12 img').removeAttr('style');
					jQuery(window).resize(
							function() {
								jQuery(colArray).find('.module, .module_right')
										.each(function() {
											this.id && fixTitleWidth(this.id);
										});

							});
					jQuery('.viewmore').delegate(
							'#content-left-close',
							'click',
							function() {
								jQuery('.content-left-toggle').removeClass(
										'content_left_show').addClass(
										'content_left_hide');
								jQuery('#content-left-wrap').hide();
								resize_left();
							});
					jQuery('#desktop_config').delegate('#content-left-toggle',
							'click', function() {
								jQuery('#content-left-wrap').toggle();
								resize_left();
							});

				}); 
				function getinfo(){
					setModulesHtml();
					 getNoreadnews(function(returnData){
			    		jQuery("#newsHtml").append(returnData);
			    	}); 
					 getCalendarList(function(returnData){
						 jQuery("#calendarHtml").append(returnData);	
				    	});
					 getnotice(function(returnData){
				    		jQuery("#notifyHtml").append(returnData);
				    	});
					 
					 getNoReadEmail(function(returnData){
						 jQuery("#emailHtml").append(returnData);
				    	});
				}
				function newsAll(){
					var url=contextPath+"/news/read/index.jsp";
					new SysFrame().tabs('update',{
						title: "新闻",
						url:url
					});
				}
				function noticeAll(){
					var url=contextPath+"/notice/readnotice/index.jsp";
					new SysFrame().tabs('update',{
						title: "通知公告",
						url:url
					});
				}
				function emailAll(){
					var url=contextPath+"/system/email/index.jsp";
					new SysFrame().tabs('update',{
						title: "电子邮件",
						url:url
					});
				}
				function setModulesHtml(){
					var requestUrl=contextPath+'/tfd/system/homepage/act/HomePageAct/getHomePage.act';
					jQuery.ajax({
						url:requestUrl,
						dataType:"json",
						async:false,
						type:"POST",
						error:function(e){
							alert(e.message);
						},
						success:function(data){
							jQuery.each(data,function(index,data){
								if(data.isOpen == '1'){
									if(data.addressTd=="1"){
										var html = getHtml(data);
										jQuery("#col_l").append(html);
									}else{
										var html = getHtml(data);
										jQuery("#col_r").append(html);
									}
								}
							});
							jQuery("#col_l").append("<div class=\"shadow\"></div>");
							jQuery("#col_r").append("<div class=\"shadow\"></div>");
						}
					});
				}
				function getHtml(data){
					var html = "";
					var spanHtml = "";
					number = data.id;
					if(data.module=="news"){
						spanHtml = "<A title=\"全部\" class=\"all_more\" href=\"javascript:newsAll();\">全部</A>";
					}else if(data.module=="notify"){
						spanHtml = "<A title=\"全部\" class=\"all_more\" href=\"javascript:noticeAll();\">全部</A>";
					}else if(data.module=="email"){
						spanHtml = "<A title=\"全部\" class=\"all_more\" href=\"javascript:emailAll();\">全部</A>";
					}
					html = "<DIV class=\"win8_module_"+data.module+" module_div color_style_0 has_module_type_tabs  module_right listColor \" id=\"module_"+number+"\">"
						+"<input type=\"hidden\" class=\"homePageId\" value="+data.id+" />"
						+"<DIV class=\"head\">"
							+"<H4 class=\"moduleHeader \" id=\"module_"+number+"_head\">"
								+"<A title=\"折叠\" class=\"expand expand_arrow\" id=\"img_resize_"+number+"\" href=\"javascript:_resize('"+number+"');\">"
								+"</A> <SPAN class=\"text\" id=\"module_"+number+"_text\" onclick=\"_resize('"+number+"');\">"+data.moduleName+"</SPAN>"
								+"<SPAN class=\"title\" id=\"module_"+number+"_title\" style=\"cursor: move;\" onclick=\"_resize('"+number+"');\"></SPAN>" 
								+"<SPAN class=\"close\" id=\"module_"+number+"_op\"> "+spanHtml
								+"&nbsp;<A title=\"关闭模块\" href=\"javascript:_del('"+number+"');\"><I class=\"module-icon-close\"></I></A>"
								+"</SPAN>"
							+"</H4>"
						+"</DIV>"
						+"<DIV class=\"module_body\" id=\"module_"+number+"_body\">"
							+"<DIV class=\"moduleType\" id=\"module_"+number+"_type\">"
							+"</DIV>"
							+"<DIV class=\"module_div\" id=\"module_"+number+"_ul\" style=\"height: 138px;\">"
								+"<UL id=\""+data.module+"Html\">"
								+"</UL>"
							+"</DIV>"
						+"</DIV>"
					+"</DIV>"
					return html;
				}

</script>
</head>
<style>
html,body{
height: 100%;
}
</style>
<body onload="doinit();" >
    <div class="page-body" style="height: 100%">
        <!-- 
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="row">
                    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                        <div class="databox radius-bordered databox-shadowed databox-graded">
                            <div class="databox-left bg-themesecondary">
                                <div class="databox-piechart">
                                    <div data-toggle="easypiechart" class="easyPieChart" data-barcolor="#fff" data-linecap="butt" data-percent="50" data-animate="500" data-linewidth="3" data-size="47" data-trackcolor="rgba(255,255,255,0.1)">
                                        <span class="white font-90">50%</span>
                                    </div>
                                </div>
                            </div>
                            <div class="databox-right">
                                <span class="databox-number themesecondary" id="flowcount"></span>
                                 <div class="databox-text darkgray">待办流程</div>
                                <div class="databox-stat themesecondary radius-bordered">
                                    <i class="stat-icon icon-lg fa fa-tasks"></i>
                            </div>

                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                        <div class="databox radius-bordered databox-shadowed databox-graded">
                            <div class="databox-left bg-themethirdcolor">
                                <div class="databox-piechart">
                                    <div data-toggle="easypiechart" id="emailPercent" class="easyPieChart" data-barcolor="#fff" data-linecap="butt" data-percent="15" data-animate="500" data-linewidth="3" data-size="47" data-trackcolor="rgba(255,255,255,0.2)"><span class="white font-90">15%</span></div>
                                </div>
                            </div>
                            <div class="databox-right">
                                <span class="databox-number themethirdcolor" id="noReadNum" ></span>
                                <div class="databox-text darkgray">内部邮件</div>
                                <div class="databox-stat themethirdcolor radius-bordered">
                                    <i class="stat-icon  icon-lg fa fa-envelope-o"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                        <div class="databox radius-bordered databox-shadowed databox-graded">
                            <div class="databox-left bg-themeprimary">
                                <div class="databox-piechart">
                                    <div id="users-pie" data-toggle="easypiechart" class="easyPieChart" data-barcolor="#fff" data-linecap="butt" data-percent="76" data-animate="500" data-linewidth="3" data-size="47" data-trackcolor="rgba(255,255,255,0.1)"><span class="white font-90">76%</span></div>
                                </div>
                            </div>
                            <div class="databox-right">
                                <span class="databox-number themeprimary">98</span>
                                <div class="databox-text darkgray">内部信息</div>
                                <div class="databox-state bg-themeprimary">
                                    <i class="fa fa-check"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                        <div class="databox radius-bordered databox-shadowed databox-graded">
                            <div class="databox-left bg-themeprimary">
                                <div class="databox-piechart">
                                    <div id="users-pie" data-toggle="easypiechart" class="easyPieChart" data-barcolor="#fff" data-linecap="butt" data-percent="45" data-animate="500" data-linewidth="3" data-size="47" data-trackcolor="rgba(255,255,255,0.1)"><span class="white font-90">45%</span></div>
                                </div>
                            </div>
                            <div class="databox-right">
                                <span class="databox-number themeprimary">98</span>
                                <div class="databox-text darkgray">文件档案</div>
                                <div class="databox-state bg-themeprimary">
                                    <i class="fa fa-check"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
           -->          
        <div class="row" style="height: 100%;">
            <div align="center" class="col-lg-9 col-md-9 col-sm-12 col-xs-12" >
                <div id="portalMain" style="margin-right: -8px;">
               
               <table style="width: 100%;height:100%;" 
		cellpadding="0" cellspacing="0" class="table-layout-fixed">
		<tr>
			<td id="col_l" class="col_l" width="50%" valign="top">
				
			</td>
			<td id="col_r" class="col_r" valign="top">
				
			</td>
		</tr>
	</table>

<SCRIPT type="text/javascript">
var interval = window.setInterval(function(){
   if(document.body.scrollWidth > 0)
   {
      window.clearInterval(interval);
      _upc(2);
   }
}, 1000);
</SCRIPT>
               
               
               
               
               
               
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" >
                <div class="orders-container">
                    <div class="orders-header">
                        <h6>待办审批</h6>
                    </div>
                    <ul id="flowList" name="flowList" class="orders-list">

                    </ul>
                    <div class="orders-footer" style="cursor: pointer;" onclick="goworklist();">
                        <a class="show-all"  ><i class="fa fa-angle-down"></i>查看更多</a>
                        <div class="help">
                            <a href=""><i class="fa fa-question"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>