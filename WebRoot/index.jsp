<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title><%=productName %></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="<%=contextPath %>/system/jsall/loginjs/css/reset.css">
        <link rel="stylesheet" href="<%=contextPath %>/system/jsall/loginjs/css/supersized.css">
        <link rel="stylesheet" href="<%=contextPath %>/system/jsall/loginjs/css/style.css">
	        <!-- Javascript -->
		<script src="<%=contextPath %>/system/jsall/jquery-1.9/jquery-1.9.0.js"></script>
        <script src="<%=contextPath %>/system/jsall/loginjs/js/supersized.3.2.7.min.js"></script>
        <script src="<%=contextPath %>/system/jsall/loginjs/js/supersized-init.js"></script>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body oncontextmenu="return false">

        <div class="page-container">
            <h1>Login</h1>
            <form action="" method="post" id="login-form">
				<div>
					<input type="text" id="username" name="username" class="username" placeholder="Username" value="admin" autocomplete="off" onclick="select()"/>
				</div>
                <div>
					<input type="password" id="password" name="password" class="password" placeholder="Password" value="123456" oncontextmenu="return false" onpaste="return false" />
                </div>
                <button id="submit" type="button">Sign in</button>
            </form>
            <div class="connect">
                <p>If we can only encounter each other rather than stay with each other,then I wish we had never encountered.</p>
				<p style="margin-top:20px;">如果只是遇见，不能停留，不如不遇见。</p>
            </div>
        </div>

    </body>
	<script>
		var num = 0;
		var flag = true;
		var reFlag = false;
		$(function(){
			GetPwdAndChk();
		});
		function login() {
			checkConfig();
			//flag=true;
			if(flag){
				var username=$("#username").val();
				var password=$("#password").val();
				  var url = contextPath + "/com/system/login/act/DologinAct/LoginInTo.act";
				  $.ajax({
						url:url,
						dataType:"text",
						data:{username:username,password:password},	
						async:false,
						type:"POST",
						success:function(data){
							if(data=="pass"){
								if(reFlag){
									setCookie($('#username').val(),$('#password').val(),30);
								}
								location.href=contextPath+"/system/frame/index.jsp";
								num = 0;
							}else if(data=="isNotRegistered"){
								location.href=contextPath+"/system/regist/regist.jsp";
							}else{
								location.href=contextPath+"/system/returnapi/loginerr.jsp";
							}
						 }
				  });
			}
		}
		function checkConfig(){
			var requestUrl = contextPath + "/tfd/system/sysinfo/copyright/act/SysInfoAct/getSysInfoListByLogin.act";
			$.ajax({
					url:requestUrl,
					dataType:"json",
					data:{userName:$("#username").val()},
					async:false,
					error:function(e){
						alert(e.message);
					},
					success:function(data){
						var missNum = data.missNum;
						var missTime = data.missTime;
						if(data.missPwd=='1'){
							var loginNum = getLoginNum();
							if(loginNum==missNum){
								var lastTime = getLastLoginTime();
								lastTime = lastTime.replace(/-/g,"/");
								var date = new Date(lastTime);
    							var time = date.getTime();
    							var newDate1 = new Date();
    							var time1 = newDate1.getTime();
    							if((time1-time)>(missTime*60000)){
    								flag = true;
    								num = 0;
    							}else{
    								alert((missTime*60000-(time1-time))/1000+"秒后才能重新登陆!");
    								flag = false;
    							}
							}else{
								flag = true;
							}
						}else{
							flag = true;
						}
						if(data.remberUser == '1'){
							reFlag = true;
						}else{
							reFlag = false;
						}
					}
			});
		}
		function getLastLoginTime(){
			var lastTime = "";
			var requestUrl =contextPath + "/com/system/login/act/DologinAct/getLastLoginTime.act";
			var username=$("#username").val();
			var password=$("#password").val();
			$.ajax({
				url:requestUrl,
				type:'POST',
				data:{username:username,password:password},
				dataType:"text",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					lastTime = data;
				}
			});
			return lastTime;
		}
		function getLoginNum(){
			var loginNum = 0;
			var requestUrl =contextPath + "/com/system/login/act/DologinAct/getLoginNum.act";
			var username=$("#username").val();
			$.ajax({
				url:requestUrl,
				type:'POST',
				async:false,
				data:{username:username},
				dataType:"text",
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					loginNum = data;
				}
			});
			return loginNum;
		}
		//获取Cookie
		function getCookie(c_name){
			if(document.cookie.length>0){
				c_start = document.cookie.indexOf(c_name + "=");
				if(c_start != -1){
				   c_start = c_start + c_name.length + 1;
				   c_end = document.cookie.indexOf(";",c_start);
				   if(c_end == -1){
						c_end = document.cookie.length;
				   }
				   return unescape(document.cookie.substring(c_start,c_end));
				}
			}
			return "";
		}
		function setCookie(uname,upwd,expiredays){
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + expiredays);
			var name = getCookie("usernamelast");
			var pwd = getCookie("userpwdlast");
			document.cookie = "username" + escape(name) + "=" + escape(name) + ((expiredays==null) ? "" : ";expires=" + exdate.toGMTString());
			document.cookie = "userpwd" + escape(name) + "=" + escape(pwd) + ((expiredays==null) ? "" : ";expires=" + exdate.toGMTString());	
			document.cookie = "usernamelast=" + escape(uname) + ((expiredays==null) ? "" : ";expires=" + exdate.toGMTString());
			document.cookie = "userpwdlast=" + escape(upwd) + ((expiredays==null) ? "" : ";expires=" + exdate.toGMTString());	
		}
		function getUserCookie(){
			if(document.getElementById("username").value == ""){
				document.getElementById("password").value = "";
			}else{
				var uname=document.getElementById("username").value;
				uname=escape(uname);
				var pwd = getCookieByName("userpwd",uname );
				document.getElementById("password").value = pwd;
				if($.trim($("#password").val())!=""){
					$("#passwordAlert").focus();
				}
			}
			
		}
		function GetPwdAndChk(){
			var name = getCookie("usernamelast");
			var pwd = getCookie("userpwdlast");
			document.getElementById("username").value = name;
			document.getElementById("password").value = pwd;
		}
		function getCookieByName(c_name,uname){
			if(document.cookie.length>0){
				c_start = document.cookie.indexOf(c_name + uname + "=");
				if(c_start != -1){
				   c_start = c_start + c_name.length + 1 + uname.length;
				   c_end = document.cookie.indexOf(";",c_start);
				   if(c_end == -1){
						c_end = document.cookie.length;
				   }
				   return unescape(document.cookie.substring(c_start,c_end));
				}
			}
			return "";
		}
		$(".btn").click(function(){
			is_hide();
		})
		var u = $("#username");
		var p = $("#password");
		$("#submit").click(function (){
			if(u.val() == '' || p.val() ==''){
				alert("用户名或密码不能为空~");
				is_show();
				return false;
			}else{
				login();
			}
		});
		window.onload = function()
		{
			$(".connect p").eq(0).animate({"left":"0%"}, 600);
			$(".connect p").eq(1).animate({"left":"0%"}, 400);
		}
		function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
		function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
		
		var $username=$("#login-form input[name='username']");
		var $password=$("#login-form input[name='password']");
		var $submit=$("#submit");
		if($username.val()==""){
			$username.focus();
		}else if($password.val()==""){
			$password.focus();
		}else{
			$submit.focus();
		}
		$("#login-form input").on("keydown",function(e){
			if(e.keyCode==13){
				login();
			}
		});
	</script>
</html>

