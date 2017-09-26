var menuData;
$(function(){
	$("#logout").on("click",function(){
		location.href=contextPath+"/com/system/login/act/DologinAct/doLogout.act";
	});
	$("#personSet").on("click",function(){
		goUrl('个人设置','/tfd/system/setuser/index.jsp');
	});
	$(document).on("click.userinfo.popover.hide",function(e){
		$("#userinfo-popover").hide();
	})
	.on("click.userinfo.popover.hide.stop","#userinfo-toggle",function(e){
		$(".document-click-hide").trigger("click.to.hide",true);
		$("#userinfo-popover").show();
		e.stopPropagation();
	})
	.on("click.userinfo.popover.hide.stop","#userinfo-popover",function(e){
		e.stopPropagation();
	})
	.on("click.to.hide","#userinfo-popover",function(e,confirm){
		if (confirm) {
			$(this).hide();
		}
	});
	
	
	
	$("#tabs-bar").tabs({
            height: 40
    }); 
    goUrl("门户首页", contextPath+"/system/frame/portal.jsp");
    
	$.ajax({
		url:contextPath+"/tfd/system/menu/act/SysMenuAct/getSysMeunJson.act",
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			menuData=data;
			initMenu(menuData);
			initMenuOpt();
		}
	});
	$.ajax({
		url:contextPath+"/tfd/system/menu/act/SysMenuAct/getSysShortMeunJson.act",
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			initShortMenu(data);
		}
	});
	checkConfig();
	$("#menu_left").click(function(){
		$("#menu_left").css("background-image","linear-gradient(to right, #ddd, #fff)");
		$("#menu_right").css("background-image","linear-gradient(to right, #ddd,#fff)");
		$(".menu_body").css("display","block");
		$(".shortMenu").css("display","none");
	});
	$("#menu_right").click(function(){
	    $("#menu_left").css("background-image","linear-gradient(to right, #fff, #ddd)");
		$("#menu_right").css("background-image","linear-gradient(to right, #fff,#ddd)");
		$(".menu_body").css("display","none");
		$(".shortMenu").css("display","block");
	});
	$("#searchContent").on("keydown",function(e){
	    if(e.keyCode==13) {
			search();
	    }
	});
	setMenu();
	initInterface();
	initPersonal();
	getEmail();
	getCalendarNum();
});
function setMenu(){
    $("#menu_left").css("background-image","linear-gradient(to right, #ddd,#fff)");
    $("#menu_right").css("background-image","linear-gradient(to right, #ddd,#fff)");
	$(".menu_body").css("display","block");
	$(".shortMenu").css("display","none");
}
function checkConfig(){
	var requestUrl = contextPath + "/tfd/system/sysinfo/copyright/act/SysInfoAct/getSysInfoList.act";
    	$.ajax({
    			url:requestUrl,
    			dataType:"json",
    			async:false,
    			error:function(e){
    				alert(e.message);
    			},
    			success:function(data){
    				if(data.initPwd=='1'){
    					var pwd = getPwd();
						if(pwd == 'E10ADC3949BA59ABBE56E057F20F883E'){
							alert("您的密码为初始密码，请马上修改密码");
							goUrl('个人设置','/tfd/system/setuser/index.jsp?type=1');
						}
    				}
    				if(data.outPwd=='1'){
						var lastTime = getLastUpdateTime();
						lastTime = lastTime.replace(/-/g,"/");
						var date = new Date(lastTime);
						var time = date.getTime();
						var newDate1 = new Date();
						var time1 = newDate1.getTime();
						if(time1-time>data.pwdCycle*3600*24*1000){
							alert("您的密码已过期，请马上修改密码");
							goUrl('个人设置','/tfd/system/setuser/index.jsp?type=1');
						}
					}
    			}
    	});
}
function getPwd(){
	var pwd = "";
	var requestUrl=contextPath+'/tfd/system/setuser/act/SetUserAct/getPassword.act?';
	$.ajax({
			url:requestUrl,
			dataType:"text",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				pwd = data;
			}
	});
	return pwd;
}
function getLastUpdateTime(){
	var lastTime = "";
	var requestUrl=contextPath+'/tfd/system/setuser/act/SetUserAct/getLastUpdateTime.act';
	$.ajax({
			url:requestUrl,
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
function goUrl(name,urls){
	if ($('#tabs-bar').tabs('exists', name)){
		$('#tabs-bar').tabs('select', name); 
	    $("iframe[tabname='"+name+"']")[0].contentWindow.location=urls;
	}else{
		var content=null;
	    if(IsPC()){
	    	content="<div style='width:100%; height:100%;'>";
	    	content+="	<iframe scrolling='auto' frameborder='0' tabname='"+name+"'  src='"+urls+"' style='width:100%;height:100%;'></iframe>";
	    	content+="</div>";
        }else{
            content="<div style='-webkit-overflow-scrolling:touch; overflow:auto; width:100%; height:100%;'>";
            content+="	<iframe scrolling='auto' frameborder='0' tabname='"+name+"'  src='"+urls+"' style='width:100%;height:100%;'></iframe>";
            content+="</div>";
        }
	    if(name=="门户首页"){	    	
	    	$('#tabs-bar').tabs('add',{
				title: name,
				content:content,
				closable: false
			});
	    }else{
		$('#tabs-bar').tabs('add',{
			title: name,
			content:content,
			closable: true
		});
	    }
		$("#tabs-bar .tabs-header")
		.on("click.parent.document.click.hide",function(){
			$(".document-click-hide").trigger("click.to.hide",true);
		});
		$("iframe[tabname='"+name+"']").on("load",function(){
			$($(this)[0].contentWindow.document)
			.on("click.parent.document.click.hide",function(){
				$(".document-click-hide").trigger("click.to.hide",true);
			});
		});
	}
} 
function getEmail(){
	var requestUrl=contextPath+'/tfd/system/email/act/EmailAct/getEmailInCount.act?';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data.noReadNum == '0'){
			}else{
				new jBox('Notice', {
   		   		    content: "<div onclick='javascript:goEmailOrCal(1)' class=\"notice-div\" ><span class=\"notice-name\" >未读邮件</span><div class=\"notice-num\">"+data.noReadNum+"</div></div>",
   		   			width:200,
   		   			height:50
	    	 	}); 
			}
		}
	});
}
function getCalendarNum(){
	var requestUrl=contextPath+'/calendar/act/CalendarAct/getCalendarNum.act';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data == '0'){
			}else{
				new jBox('Notice', {
   		   		    content: "<div onclick='javascript:goEmailOrCal(2)' class=\"notice-div\" ><span class=\"notice-name\" >未完成日程</span><div class=\"notice-num\">"+data+"</div></div>",
   		   			width:200,
   		   			height:50
	    	 	}); 
			}
		}
	});
}
function goEmailOrCal(type){
	if(type==1){
		goUrl("电子邮件",contextPath+"/system/email/index.jsp");
	}else if(type==2){
		goUrl("日程安排",contextPath+"/calendar/index.jsp");
	}
}
 
 
function initMenu(menuData){
	var menu = findChilds("0");	
	var menuHtml = "";
	$.each(menu,function(index,menu){
		menuHtml += "<div class='menu_list'>";
		menuHtml += "<div class='menu_li' id='menu"+menu.id+"' ><div class='menu_icon'><img width='24' height='24' src='"+menu.icon+"'/></div>";
		menuHtml +="<div class='menu_name'>"+menu.name+"</div></div>";
		menuHtml +="<div id='menu_child"+menu.id+"' class='menu_child' style='display:none;'></div>";
		menuHtml +="</div>";
	});
	$("#menu_content").html(menuHtml);
}
function initShortMenu(data){
	var menuHtml = "";
	$.each(data,function(index,menu){
		menuHtml += "<div class='menu_list'>";
		menuHtml += "<div class='menu_li' id='menu"+menu.id+"' onclick=\"goUrl('"+menu.name+"','"+menu.urls+"')\" ><div class='menu_icon'><img width='24' height='24' src='"+menu.icon+"'/></div>";
		menuHtml +="<div class='menu_name'>"+menu.name+"</div></div>";
		menuHtml +="<div id='menu_child"+menu.id+"' class='menu_child' style='display:none;'></div>";
		menuHtml +="</div>";
	});
	$("#shortMenu_content").html(menuHtml);
}

function findChilds(id){
	var childs=[];
	for(var i=0;i<menuData.length;i++){
		if(menuData[i].pId==id){
			childs.push(menuData[i]);
		}
	}
	return childs;
}
function initMenuOpt(){
	$('.menu_li').click(function(){
		var id=$(this).attr("id").replace("menu","");
		if(document.getElementById("menu_child"+id).style.display == "block"){
			$("#menu_child"+id).slideUp(500);
		}else{
			var menu = findChilds("0");
			$.each(menu,function(index,menu){
				if(menu.id == id){
				}else{
					$("#menu_child"+menu.id).slideUp(500);
					$("#menu"+menu.id).attr("style","background-color:#F5F5F5;");
				}
			});
			var menu = findChilds(id);
			if(menu != ""){
				var ChildHtml = "";
				$.each(menu,function(index,menu){
					ChildHtml += "<div class='menu_list2'>";
					ChildHtml += "<div class='menu_li2' id='menu"+menu.id+"' ><div class='menu_icon2'></div>";
					ChildHtml +="<input type='hidden' class='menu_urls' value='"+menu.urls+"' ><div class='menu_name2'>"+menu.name+"</div>";
					if(findChilds(menu.id).length>0){
						ChildHtml+="<img id='icon"+menu.id+"' style='width:40px;height:35px;' src='/tfd/system/styles/images/menu/icontop.png'/>";
					}
					ChildHtml +="</div>";
					ChildHtml +="<div id='menu_child"+menu.id+"' class='menu_child' style='display:none;' ></div>";
					ChildHtml +="</div>";
				});
				$("#menu_child"+id).html(ChildHtml);
				$("#menu_child"+id).slideDown(500);
				$("#menu"+id).attr("style","background-color:#FFF;");
				ChildClick();
			}
		}
	});
	
}

function ChildClick(){
	$('.menu_li2').click(function(){
		var id=$(this).attr("id").replace("menu","");
		if(document.getElementById("menu_child"+id).style.display == "block"){
			$("#menu_child"+id).slideUp(500);
			$("#menu"+id).attr("style","background-color:#F1F7FD;");
			$("#icon"+id).prop("src","/tfd/system/styles/images/menu/icontop.png");
		}else{
			var menu = findChilds(id);
			if(menu != ""){
				var ChildHtml = "";
				$.each(menu,function(index,menu){
					ChildHtml += "<div class='menu_list2'>";
					ChildHtml += "<div class='menu_li3' id='menu"+menu.id+"' onclick=javascript:goUrl('"+menu.name+"','"+menu.urls+"') ><div class='menu_icon3'></div>";
					ChildHtml +="<div class='menu_name3'>"+menu.name+"</div></div>";
					ChildHtml +="<div id='menu_child"+menu.id+"' class='menu_child' style='display:none;' ></div>";
					ChildHtml +="</div>";
				});				
				$("#icon"+id).prop("src","/tfd/system/styles/images/menu/icondown.png");
				$("#menu"+id).attr("style","background-color:#B8D6FB;");
				$("#menu_child"+id).html(ChildHtml);
				$("#menu_child"+id).slideDown(500);
			}else{
				goUrl($("#menu"+id+" .menu_name2").html(),$("#menu"+id+" .menu_urls").val());
			}
		}
	});
}

function initInterface(){
	var requestUrl = contextPath + "/tfd/system/interfaces/act/InterfaceAct/getInterface.act";
 	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			$(".title").html(data.topTitle);
			if(data.topImg!=""){
				var srcPath = contextPath + "/attachment/sysinfo/"+data.topImg;
				$("#topImg").show();
				$("#topImg").prop("src",srcPath);
				$(".title").css("text-indent","10px");
			}
			$("#copyright").html(data.bottomTitle);
			document.title = data.browserTitle;
		}
 	});
}

function initPersonal(){
	var requestUrl = contextPath + "/tfd/system/setuser/act/SetUserAct/getPersonalInfoById.act";
 	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{accountId:userId},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data.headImg!=""&&data.headImg!=null&&data.headImg!="null"){
				$("#personHeadImg").prop("src",contextPath + "/attachment/userinfo/"+data.headImg);
			}else{
				$("#personHeadImg").prop("src",imgPath + "/personal/default.jpg");
			}
			$(".frame-user-name a").html(data.name);
		}
 	});
}

function search(){
	var searchContent = $("#searchContent").val();
	goUrl("数据检索",contextPath+"/system/search/index.jsp");
}

