
var im_api=new Object();

var	him=null;
var	chatType=null;
function sendmsg(){
	im_api.openDialog(him);
}
$(function(){
	
	
	
	//me 默认 连上 zeus 实时接收消息
	//	获取  key 实例化Zeus 并 start() 无法连接 提示显示于对话框
	//发消息 状态：正在发 发送失败  点击消息可重发
	
	var im={
			me:{
				id:"",
				name:"",
				sex:"",
				headImg:"",
				key:"",
				mid:""
			},
			him:null,
			dialog:{
				userList:{
					count:0,
					list:new Object()
				}
			},
			unread:{
				messages:new Object(),
				count:0,
				haveNew:false
			},
			user:new Object(),
			mids:[],
			midIndex:0
	};
	var twinklingHeadimgInterval=null;
	
	$.ajax({
		url:contextPath+"/tfd/system/im/userinfo/act/UserInfoAct/getLoginUserInfo.act",
		type:"POST",
		dataType:"text",
		async:false,
		error:function(e){
			console.log("im:获取登录用户信息失败");
		},
		success:function(returnData){
			if (returnData=="") {
				console.log("im:无法获取登录用户信息");
			}else{
				var userInfo=eval("("+returnData+")");
				im.me.id=userInfo.accountId;
				im.me.name=userInfo.userName;
				im.me.sex=userInfo.sex;
				im.me.headImg=userInfo.headImg;
				im.me.key="";//userInfo.key;
				im.me.mid=userInfo.mid;
				
				$("#address .to-him[data-id='"+im.me.id+"']").hide();
			}
			
		}
	});
	
	
	if(im.me.key==""){
		$.ajax({
			url:contextPath+"/tfd/system/im/act/ImAct/getKey.act",
			data:"accountId="+im.me.id,
			type:"POST",
			dataType:"text",
			async:false,
			success:function(key){
				im.me.key=key;
			}
		});
	}
	
	var chanel=new Zeus({
		host:"115.29.170.136",
		port:"8090",
		key:im.me.key,
		mid:im.me.mid,
		onOfflineMessage:function(data){
			//离线消息
			console.log("离线消息:");
			console.log($.json.encode(data));
			comeMessageDoWidth(data);
		},
		onOnlineMessage:function(data){
			//在线消息
			console.log("在线消息:");
			console.log($.json.encode(data));
			comeMessageDoWidth(data);
		},
		onError:function(message){
			//连接中的错误
			console.log("连接中的错误：\n"+message);
		},
		onOpen:function(message){
			//初始化成功
			console.log("初始化成功");
		},
		onClose:function(message){
			//连接断开
			console.log("连接断开");
		}
	});
	chanel.start();
	
	
	
	$("#im-dialog .msg-send").on("click",function(){
		
		var content=$("#im-dialog .msg-text-input").val();
		var msgHtml=
			"<div class='msg my-msg'>"+
			"	<div class='msg-text'>"+
					content+
			"	</div>"+
			"	<div class='face-icon'>" +
			"		<img class='img' src='"+getHeadImg(im.me)+"'/>" +
			"	</div>"+
			"	<div class='arrow'></div>"+
			"</div>";
		var $msgList=$("#im-dialog .right .msg-panel[data-user-id='"+im.him+"'] .msg-list");
		$msgList.append(msgHtml);
		$msgList.scrollTop($msgList.height()-$("#im-dialog .right .center").height());
		
		$("#im-dialog .msg-text-input").val("");
		
		$.ajax({
			url:contextPath+"/tfd/system/im/act/ImAct/send.act",
			data:{"terminalId":im.me.id,"toTerminalId":im.him,"content":content},
			type:"POST",
			dataType:"text",
			error:function(e){
				console.log("发送消息："+e);
			},
			success:function(returnData){
				console.log(content);
				console.log("已发送："+returnData);
			}
		});
	});
	
	$("#im-dialog").on("click",".left .current-dialog-close",function(){
		var userId=$(this).attr("data-user-id");
		$("#im-dialog .left .dialog-user[data-user-id='"+userId+"']").slideUp(200,function(){
			$(this).remove();
			
			delete im.dialog.userList.list[userId];
			im.dialog.userList.count-=1;
			
			if (im.dialog.userList.count==1) {
				hideDialogUserList();
			}
			
			var him=$("#im-dialog .left .dialog-user:first").attr("data-user-id");
			$("#im-dialog .right .north .him").html(im.dialog.userList.list[him].name);
			im.him=him;
			
			$("#im-dialog .right .msg-panel[data-user-id='"+him+"']").remove();
			
			var userId=$(this).attr("data-user-id");
			$("#im-dialog .left .dialog-user-list .dialog-user").removeClass("actived");
			$(this).addClass("actived");
			$("#im-dialog .right .msg-panel").removeClass("actived");
			$("#im-dialog .right .msg-panel[data-user-id='"+userId+"']").addClass("actived");
			$("#im-dialog .right .north .him").html(im.dialog.userList.list[userId].name);
			
			im.him=userId;
			
			if($("#im-dialog .left .dialog-user.actived").length==0){
				$("#im-dialog .left .dialog-user:first").trigger("click");
			}
		});
		
		
	});
	
	$("#im-dialog").on("click",".left .dialog-user",function(){
		if (!$(this).hasClass("actived")) {
			var userId=$(this).attr("data-user-id");
			$("#im-dialog .left .dialog-user-list .dialog-user").removeClass("actived");
			$(this).addClass("actived");
			$("#im-dialog .right .msg-panel").removeClass("actived");
			$("#im-dialog .right .msg-panel[data-user-id='"+userId+"']").addClass("actived");
			$("#im-dialog .right .north .him").html(im.dialog.userList.list[userId].name);
			
			im.him=userId;
			
			var unreadNumObj=$("#im-dialog .left .dialog-user[data-user-id='"+im.him+"'] .unread-message-num");
			unreadNumObj.attr("data-unread-num","");
			if (unreadNumObj.hasClass("have")) {
				unreadNumObj.removeClass("have");
			}
			unreadNumObj.find(".text").html("");
		}
	});
	
	$("#im-dialog .dialog-close").on("click",function(){
		$("#im-dialog").hide(200,function(){
			resetImDialog();
		});
	});
	
	
	$("#twinkling-headimg").on("mouseover",function(){
		$(".unread-message").show();
	});
	
	$(".unread-message").on("click",".message .hover-skin",function(){
		var userId=$(this).attr("data-user-id");
		openDialog(userId);
	});
	$(".unread-message").on("click",".read-all",function(){
		$(".unread-message .messages .message").each(function(){
			var userId=$(this).attr("data-user-id");
			openDialog(userId);
		});
	});
	
	$(document).on("click",function(){
		$(".unread-message").hide();
	});
	
	
	$(".unread-message").on("click",function(e){
		e.stopPropagation();
	});
	
	$(".unread-message").on("click",".ignore",function(){
		var userId=$(this).attr("data-user-id");
		clearUnread(userId);
	});
	
	$(".unread-message").on("click",".ignore-all",function(){
		clearAllUnread();
	});
	
	
	
	
	function comeMessageDoWidth(data){
		im.mids.push(data.mid);
		if (im.midIndex<im.mids.length-3) {
			$.ajax({
				url:contextPath+"/tfd/system/im/act/ImAct/updateMid.act",
				data:{
						accountId:im.me.id,
						mid:im.mids[im.mids.length-1],
						midIndex:im.mids.length-1
				},
				type:"POST",
				dataType:"json",
				error:function(){
					
				},
				success:function(returnData){
					if(returnData.result>0){
						im.midIndex=returnData.midIndex;
					}
				}
			});
		}
		
		var msg=eval("("+data.msg+")");
		if (msg.E=="private_chat") {
			if(msg.D.msg_type=="msg"){
				var him=msg.D.from_terminal_id;
				
				if (!(him in im.user)) {
					var userInfo=getUserInfo(him);
					
					im.user[him]=new Object();
					im.user[him].id=him;
					im.user[him].name=userInfo.userName;
					im.user[him].sex=userInfo.sex;
					im.user[him].headImg=userInfo.headImg;
				}
				
				var inDialogUser=him in im.dialog.userList.list
				if (inDialogUser) {
					showMsgs(him,[msg.D.content]);
					if (him!=im.him) {
						var unreadNum=0;
						var unreadNumObj=$("#im-dialog .left .dialog-user[data-user-id='"+him+"'] .unread-message-num");
						if (unreadNumObj.attr("data-unread-num")!="") {
							unreadNum=parseInt(unreadNumObj.attr("data-unread-num"));
						}
						unreadNum++;
						unreadNumObj.attr("data-unread-num",unreadNum);
						if (!unreadNumObj.hasClass("have")) {
							unreadNumObj.addClass("have");
						}
						unreadNumObj.find(".text").html(unreadNum);
					}
				}else{
					if (!(him in im.unread.messages)) {
						im.unread.messages[him]=new Object();
						im.unread.messages[him].msgs=new Array();
						
						im.unread.messages[him].id=im.user[him].id;
						im.unread.messages[him].name=im.user[him].name;
						im.unread.messages[him].sex=im.user[him].sex;
						im.unread.messages[him].headImg=im.user[him].headImg;
						
						im.unread.count++;
					}
					im.unread.messages[him].msg=msg.D.alert;
					im.unread.messages[him].msgs.push(msg.D.content);
					im.unread.haveNew=true;
					
					loadUnreadMessage();
				}
				
			}
			
		}
	}
	function getUserInfo(accountId){
		var userInfo=null;
		$.ajax({
			url:contextPath+"/tfd/system/im/userinfo/act/UserInfoAct/getUserInfo.act",
			data:{accountId:accountId},
			type:"POST",
			dataType:"json",
			async:false,
			error:function(){
			},
			success:function(returnData){
				userInfo=returnData;
			}
		});
		return userInfo;
	}
	function getHeadImg(user){
		return user.headImg==""?
				user.sex=="男"?"img/head-man.png":"img/head-woman.png"
			:user.headImg;
	}
	function resetImDialog(){
		im.dialog.userList.count=0;
		im.dialog.userList.list=new Object();
		
		$("#im-dialog .left .dialog-user-list").html("");
		$("#im-dialog .left").css({"left":"-175px"});
		$("#im-dialog .right").css({"left":"0px"});
		$("#im-dialog .right .center .msg-panel").remove();
		$("#im-dialog .msg-text-input").val("");
	}
	function hideDialogUserList(){
		$("#im-dialog .left").animate({left:"-175px"});
		$("#im-dialog .right").animate({left:"0px"});
	}
	function showDialogUserList(){
		$("#im-dialog .left").animate({left:"0px"});
		$("#im-dialog .right").animate({left:"175px"});
	}
	function openDialog(userId){
		
		im.him=userId;
		
		var user=new Object();
		if (userId in im.user) {
			user.id=im.user[userId].id;
			user.name=im.user[userId].name;
			user.sex=im.user[userId].sex;
			user.headImg=im.user[userId].headImg;
		}else{
			var userInfo=getUserInfo(userId);
			user.id=userInfo.userId;
			user.name=userInfo.userName;
			user.sex=userInfo.sex;
			user.headImg=userInfo.headImg;
			
			im.user[userId]=new Object();
			im.user[userId].id=userId;
			im.user[userId].name=userInfo.userName;
			im.user[userId].sex=userInfo.sex;
			im.user[userId].headImg=userInfo.headImg;
		}
		
		var inDialogOfUser=userId in im.dialog.userList.list;
		if (!inDialogOfUser) {
			im.dialog.userList.list[userId]=user;
			im.dialog.userList.count++;
		}
		
		var headImg=getHeadImg(user,false);
		if (!inDialogOfUser) {
			var dialogUserHtml=
				"<div class='dialog-user' data-user-id='"+userId+"'>" +
				"	<div class='dialog-user-head'>" +
				"		<img class='img' src='"+headImg+"'/>" +
				"	</div>"+
				"	<div class='dialog-user-name'>"+user.name+"</div>"+	
				"	<div class='current-dialog-close' data-user-id='"+userId+"'></div>"+
				"	<div class='unread-message-num' data-unread-num=''>"+
				"		<div class='shading'></div>"+
				"		<div class='circle-left'></div>"+
				"		<div class='circle-right'></div>"+
				"		<div class='text'></div>"+
				"	</div>"+
				"</div>";
			
			$("#im-dialog .left .dialog-user-list").append(dialogUserHtml);
			
			var msgPanelHtml=
				"<div class='msg-panel' data-user-id='"+userId+"'>"+
				"	<div class='msg-more'>"+
				"		<span class='icon'></span>"+
				"		<span class='text'>查看更多消息</span>"+
				"	</div>"+
				"	<div class='msg-list'></div>"+
				"	<div class='msg-history-line'>"+
				"		<span class='line'>——————</span>&nbsp;&nbsp;以上是历史消息&nbsp;&nbsp;<span class='line'>——————</span>"+
				"	</div>";
			$("#im-dialog .right .center").prepend(msgPanelHtml);
		}
		$("#im-dialog .left .dialog-user-list .dialog-user").removeClass("actived");
		$("#im-dialog .left .dialog-user-list .dialog-user[data-user-id='"+userId+"']").addClass("actived");
		$("#im-dialog .right .msg-panel").removeClass("actived");
		$("#im-dialog .right .msg-panel[data-user-id='"+userId+"']").addClass("actived");
		$("#im-dialog .right .north .him").html(user.name);
		
		
		if(im.dialog.userList.count==1){
			$("#im-dialog").show();
		}else if (im.dialog.userList.count==2) {
			showDialogUserList();
		}
		
		var msgs=[];
		if (userId in im.unread.messages) {
			msgs=im.unread.messages[userId].msgs;
		}
		showMsgs(userId,msgs);
		
		clearUnread(userId);
	}
	function showMsgs(userId,msgs){
		var headImg=getHeadImg(im.user[userId]);
		var msgsHtml="";
		for (var i = 0; i < msgs.length; i++) {
			msgsHtml+=
				"<div class='msg him-msg'>"+
				"	<div class='msg-text'>"+
						msgs[i]+
				"	</div>"+
				"	<div class='face-icon'>" +
				"		<img class='img' src='"+headImg+"'/>" +
				"	</div>"+
				"	<div class='arrow'></div>"+
				"</div>";
		}
		$("#im-dialog .right .msg-panel[data-user-id='"+userId+"'] .msg-list").append(msgsHtml);
	}
	function clearUnread(userId){
		im.unread.count-=1;
		delete im.unread.messages[userId];
		
		$(".unread-message .message[data-user-id='"+userId+"']").slideUp(200,function(){
			$(this).remove();
		});
		
		if (im.unread.count==0) {
			window.clearInterval(twinklingHeadimgInterval);
			$("#twinkling-headimg").hide();
			$(".unread-message").hide();
		}
	}
	function clearAllUnread(){
		im.unread.count=0;
		im.unread.messages=null;
		
		$(".unread-message .messages").html("");
		
		window.clearInterval(twinklingHeadimgInterval);
		$("#twinkling-headimg").hide();
		$(".unread-message").hide();
	}
	function loadUnreadMessage(){
		if (twinklingHeadimgInterval!=null) {
			window.clearInterval(twinklingHeadimgInterval);
		}
		
		var messages=im.unread.messages;
		if (messages==null) {
			return;
		}
		var msgHtml="";
		for(var userId in messages){
			
			var headImg=getHeadImg(messages[userId]);
			
			msgHtml+="<div class='message' data-user-id='"+userId+"'>" +
			"	<div class='user-headimg' data-user-id='"+userId+"'>"+
			"		<img src='"+headImg+"'/>"+
			"	</div>"+
			"	<div class='username'>"+messages[userId].name+"</div>"+
			"	<div class='message-text'>"+messages[userId].msg+"</div>"+
			"	<div class='message-num'>"+
			"		<div class='shading'></div>"+
			"		<div class='circle-left'></div>"+
			"		<div class='circle-right'></div>"+
			"		<div class='text'>"+(messages[userId].msgs.length)+"</div>"+
			"	</div>"+
			"	<div class='ignore' title='忽略' data-user-id='"+userId+"'></div>" +
			"	<div class='hover-skin' data-user-id='"+userId+"'></div>"+
			"</div>";
			
		}
		$(".unread-message .messages").html(msgHtml);
		
		if (im.unread.count>0) {
			$("#twinkling-headimg").show();
			unreadStartTwinkling();
		}
		
		im.unread.haveNew=false;
	}
	function unreadStartTwinkling(){
		var twinklingHeadImg="img/head-msg.png";
		if(im.unread.count==1){
			for(var userId in im.unread.messages){
				twinklingHeadImg=getHeadImg(im.unread.messages[userId],false);
				break;
			}
		}
		$("#twinkling-headimg .img").attr("src",twinklingHeadImg);
		
		twinklingHeadimgInterval=window.setInterval(function(){
			$("#twinkling-headimg").toggle();
		},500);
	}
	
	
	im_api.openDialog=openDialog;
	sendmsg();
});

