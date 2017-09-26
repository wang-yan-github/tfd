var im={
		user:{},
		me:null,
		him:null,
		consts:{
			timestamp_interval:10,
			timeDisplayFormat:"YYYY/MM/DD HH:mm:ss",
			timeValueFormat:"YYYY-MM-DD HH:mm:ss",
			MSG_UNREAD_LOADED:"unread-loaded",
			MSG_HAVE_READ:"have-read",
			MSG_UNREAD_NOT_LOADED:"unread-not-loaded",
			CHAT_TYPE_PRIVATE:"private_chat"
		},
		img:{
			twinkling:contextPath+"/api/im/img/head-msg.png",
			manHead:contextPath+"/api/im/img/head-man.png",
			womanHead:contextPath+"/api/im/img/head-woman.png",
			type:{
				HEAD_IMG:"headImg",
				TWINKLING:"twinkling"
			}
		},
		webCurrTime:null,//2015-10-22 13:02:01
		serverCurrTime:null,
		ui:{
			dialog:"#im-dialog",
			send_setting_option:"#im-dialog .south .current-dialog-option .msg-send .setting-list .setting-option",
			msg_text_input:"#im-dialog .south .msg-text .msg-text-input",
			msg_send:"#im-dialog .msg-send .send",
			msg_panel:"#im-dialog .right .center .msg-panel",
			_msg_more:".right .center .msg-panel .msg-more",
			dialog_user:"#im-dialog .left .dialog-user",
			dialog_top_him:"#im-dialog .right .north .him",
			dialog_close:"#im-dialog .dialog-close",
			unread_msg_twinkling_img:"#twinkling-headimg",
			unread_msg_alert_box:".unread-message"
		},
		server:{
			mid:null,
			init:function(){
				var initData=null;
				$.ajax({
					url:contextPath+"/tfd/system/im/act/ImAct/init.act",
					type:"POST",
					dataType:"json",
					async:false,
					error:function(e){
						console.log("im:服务器初始化信息失败");
					},
					success:function(returnData){
						if (returnData.result) {
							initData=returnData;
						}else{
							console.log("request success but im:服务器初始化信息失败");
						}
					}
				});
				return initData;
			},
			send:function(fromId,toId,content,callback){
				$.ajax({
					url:contextPath+"/tfd/system/im/act/ImAct/send.act",
					data:{"terminalId":fromId,"toTerminalId":toId,"content":content},
					type:"POST",
					dataType:"json",
					error:function(e){
						console.log("后台发送消息失败："+e);
					},
					success:function(returnData){
						callback(returnData);
					}
				});
			},
			getUserInfo:function (accountId){
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
			},
			updateDefined:function(){
				$.ajax({
					url:contextPath+"/tfd/system/im/act/ImAct/updateUserDefined.act",
					data:{
						accountId:im.me,
						defined:$.json.encode(im.user[im.me].defined)
					},
					type:"POST",
					dataType:"json",
					error:function(){
						console.log("updateDefined error!");
					},
					success:function(returnData){
						if(returnData.result>0){
							console.log("updateDefined successful!");
						}
					}
				});
			},
			privateHistory:function(me,him,limit,msgId){
				var historyMsgs=[];
				$.ajax({
					url:contextPath+"/tfd/system/im/act/ImAct/privateHistory.act",
					data:{
						me:me,
						him:him,
						limit:limit,
						msgId:msgId
					},
					type:"POST",
					dataType:"json",
					async:false,
					error:function(){
						console.log("privateHistory error!");
					},
					success:function(returnData){
						if(returnData.ret==0){
							historyMsgs=returnData.msgs;
						}
					}
				});
				return historyMsgs;
			},
			updateMid:function(){
				$.ajax({
					url:contextPath+"/tfd/system/im/act/ImAct/updateUserMid.act",
					data:{
						accountId:im.me,
						mid:im.client.mid,
					},
					type:"POST",
					dataType:"json",
					error:function(){
						console.log("updateMid error!");
					},
					success:function(returnData){
						if(returnData.result){
							console.log("updateMid "+im.client.mid+" success!");	
							im.server.mid=returnData.mid;
						}
					}
				});
			}
		},
		client:{
			mid:null,
			twinklingHeadimgInterval:null,
			channel:null,
			history:{
				limit:30
			},
			init:function(){
				var initResult=false;
				var initData=im.server.init();
				if (initData!=null) {
					im.serverCurrTime=initData.time;
					im.webCurrTime=new Date().pattern(im.consts.timeValueFormat);
					
					var userInfo=initData.userInfo;
					if (userInfo==null) {
						console.log("request error im:无法获取登录用户的信息");
					}else{
						initResult=true;
						im.me=userInfo.accountId;
						im.client.setUser(userInfo);
						
						$(im.ui.send_setting_option+"[data-shortcut='"+im.user[im.me].defined.send.shortcut+"']").addClass("actived");
					}
				}
				return initResult;
			},
			getCurrTime:function(){
				var timeInterval=strToDate(im.webCurrTime).getTime()-strToDate(im.serverCurrTime).getTime();
				return new Date(new Date().getTime()-timeInterval).pattern(im.consts.timeValueFormat);
			},
			handshake:function(){
				var handshakeResult=false;
				$.ajax({
					url:contextPath+"/tfd/system/im/act/ImAct/handshake.act",
					data:"accountId="+im.me,
					type:"POST",
					dataType:"text",
					async:false,
					success:function(key){
						if (key=="") {
							console.log("当前登录用户无法与服务器握手");
						}else{
							console.log("当前登录已与服务器握手 <-->");
							console.log("key:"+key);
							handshakeResult=true;
							im.user[im.me].key=key;
						}
					}
				});
				return handshakeResult;
			},
			createChannel:function(){
				im.client.channel=new Zeus({
					host:"115.29.170.136",
					port:"8090",
					key:im.user[im.me].key,
					mid:im.user[im.me].mid,
					onOfflineMessage:function(data){
						//离线消息
						console.log("离线消息:");
						console.log($.json.encode(data));
						im.client.comeMessageDoWith(data);
					},
					onOnlineMessage:function(data){
						//在线消息
						console.log("在线消息:");
						console.log($.json.encode(data));
						im.client.comeMessageDoWith(data);
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
			},
			imgLoadError:function(e,type){
				var $img=$(e.target);
				var userId=$img.attr("data-user-id");
				var errorImg="";
				if (type==im.img.type.HEAD_IMG) {
					errorImg=im.user[userId].sex=="男"?im.img.manHead:im.img.womanHead;
				}else if(type==im.img.type.TWINKLING){
					if (userId) {
						errorImg=im.user[userId].sex=="男"?im.img.manHead:im.img.womanHead;
					}else{
						errorImg=im.img.twinkling;
					}
				}
				$img.attr("src",errorImg);
			},
			comeMessageDoWith:function(data){
				im.client.mid=data.mid;
				var msg=eval("("+data.msg+")");
				if (msg.E==im.consts.CHAT_TYPE_PRIVATE) {
					var him=msg.D.from_terminal_id;
					
					if (!(him in im.user)) {
						var userInfo=im.server.getUserInfo(him);
						im.client.setUser(userInfo);
					}
					var historyMsg=im.client.readMsg(msg.D,im.consts.CHAT_TYPE_PRIVATE);
					im.user[him].history.msgs.unshift(historyMsg);
					if(msg.D.msg_type=="msg"){
						
						var imMsg=im.client.readMsg(msg.D,im.consts.CHAT_TYPE_PRIVATE);
						var unread=false;
						var unreadStatus="";
						if (im.user[him].inDialog) {
							if (him==im.him) {
								im.client.showMsgs([imMsg]);
							}else{
								unread=true;
								unreadStatus=im.consts.MSG_UNREAD_LOADED;
							}
						}else{
							unread=true;
							unreadStatus=im.consts.MSG_UNREAD_NOT_LOADED;
						}
						
						if (unread) {
							if(im.user[him].unread==null){
								im.user[him].unread={};
								im.user[him].unread.msgs=[];
							}
							im.user[him].unread.status=unreadStatus;
							im.user[him].unread.alert=msg.D.alert.substring(msg.D.alert.indexOf("：")+1);
							im.user[him].unread.msgs.push(imMsg);
							
							if (unreadStatus==im.consts.MSG_UNREAD_LOADED) {
								var $unreadNum=$(im.ui.dialog_user+"[data-user-id='"+him+"'] .unread-message-num");
								if (!$unreadNum.hasClass("have")) {
									$unreadNum.addClass("have");
								}
								$unreadNum.find(".text").html(im.user[him].unread.msgs.length);
							}else if(unreadStatus==im.consts.MSG_UNREAD_NOT_LOADED){
								im.client.loadUnreadMessage();
							}
						}
					}
				}
			},
			getHeadImg:function(user){
				return user.headImg==""?
						user.sex=="男"?im.img.manHead:im.img.womanHead
					:user.headImg;
			},
			readMsg:function(msg,chatType){
				if (!msg) {
					return null;
				}
				var msgId=null;
				if (chatType==im.consts.CHAT_TYPE_PRIVATE) {
					msgId=msg.pm_id
				}
				return {fromId:msg.from_terminal_id,toId:msg.to_terminal_id,content:msg.content,time:msg.created,msgId:msgId};
			},
			createMsg:function(fromId,toId,content,time,msgId){
				return {fromId:fromId,toId:toId,content:content,time:time,msgId:msgId};
			},
			resetUser:function(userId){
				im.user[userId].inDialog=false;
				im.user[userId].history={
						msgId:"-1",
						msgs:[]
				};
			},
			resetImDialog:function(){
				$(im.ui.dialog).hide(200,function(){
					for(var id in im.user){
						im.client.resetUser(id);
					}
					
					$(im.ui.dialog+" .left .dialog-user-list").html("");
					$(im.ui.dialog+" .left").css({"left":"-175px"});
					$(im.ui.dialog+" .right").css({"left":"0px"});
					$(im.ui.msg_panel).remove();
					$(im.ui.msg_text_input).val("");
				});
			},
			closeDialog:function(userId){
				$(im.ui.dialog_user+"[data-user-id='"+userId+"']").slideUp(200,function(){
					im.client.resetUser(userId);
					
					$(this).remove();
					$(im.ui.msg_panel+"[data-user-id='"+userId+"']").remove();
					
					var dialogCount=im.client.inDialogUserCount();
					if (dialogCount==1) {
						im.client.hideDialogUserList();
					}
					
					if($(im.ui.dialog_user+".actived").length==0){
						var him=$(im.ui.dialog_user+":first").attr("data-user-id");
						im.client.openDialog(him);
					}
				});
			},
			hideDialogUserList:function(){
				$(im.ui.dialog+" .left").animate({left:"-175px"});
				$(im.ui.dialog+" .right").animate({left:"0px"});
			},
			showDialogUserList:function(){
				$(im.ui.dialog+" .left").animate({left:"0px"});
				$(im.ui.dialog+" .right").animate({left:"175px"});
			},
			setUser:function(userInfo){
				var userId=userInfo.accountId;
				im.user[userId]={};
				im.user[userId].id=userId;
				im.user[userId].name=userInfo.userName;
				im.user[userId].sex=userInfo.sex;
				im.user[userId].headImg=userInfo.headImg;
				im.user[userId].key=userInfo.key;
				im.user[userId].mid=userInfo.mid;
				im.user[userId].defined=userInfo.defined;
				im.user[userId].inDialog=false;
				im.user[userId].unread=null;
				im.user[userId].history={
						msgId:"-1",
						msgs:[]
				};
			},
			inDialogUserCount:function(){
				var count=0;
				for(var id in im.user){
					if(im.user[id].inDialog){
						count++;
					}
				}
				return count;
			},
			openDialog:function(userId){
				im.him=userId;
				
				if (!(im.him in im.user)) {
					var userInfo=im.server.getUserInfo(im.him);
					im.client.setUser(userInfo);
				}
				
				var headImg=im.client.getHeadImg(im.user[im.him],false);
				if (!im.user[im.him].inDialog) {
					var dialogUserHtml=
						"<div class='dialog-user' data-user-id='"+im.him+"'>" +
						"	<div class='dialog-user-head'>" +
						"		<img class='img' src='"+headImg+"' data-user-id='"+im.him+"'/>" +
						"	</div>"+
						"	<div class='dialog-user-name'>"+im.user[im.him].name+"</div>"+	
						"	<div class='current-dialog-close' data-user-id='"+im.him+"'></div>"+
						"	<div class='unread-message-num' data-unread-num=''>"+
						"		<div class='shading'></div>"+
						"		<div class='circle-left'></div>"+
						"		<div class='circle-right'></div>"+
						"		<div class='text'></div>"+
						"	</div>"+
						"</div>";
					
					$(im.ui.dialog+" .left .dialog-user-list").append(dialogUserHtml);
					
					$(im.ui.dialog_user+" .dialog-user-head .img")
					.off("error")
					.on("error",function(e){
						im.client.imgLoadError(e,im.img.type.HEAD_IMG);
					});
					
					var msgPanelHtml=
						"<div class='msg-panel' data-user-id='"+im.him+"'>" +
						"	<div class='top-point'></div>"+
						"	<div class='msg-more'>"+
						"		<span class='icon'></span>"+
						"		<span class='text'>查看更多消息</span>"+
						"	</div>"+
						"	<div class='msg-list'></div>"+
						"	<div class='bottom-point'></div>"+
						"</div>";
					$(im.ui.dialog+" .right .center").append(msgPanelHtml);
				}
				$(im.ui.dialog_user).removeClass("actived");
				$(im.ui.dialog_user+"[data-user-id='"+im.him+"']").addClass("actived");
				$(im.ui.msg_panel).removeClass("actived");
				$(im.ui.msg_panel+"[data-user-id='"+im.him+"']").addClass("actived");
				$(im.ui.dialog_top_him).html(im.user[im.him].name);
				im.user[im.him].inDialog=true;
				
				var dialogCount=im.client.inDialogUserCount();
				if(dialogCount==1){
					$(im.ui.dialog).show(200);
				}else if (dialogCount==2) {
					im.client.showDialogUserList();
				}
				
				if (im.user[im.him].unread!=null) {
					im.client.showMsgs(im.user[im.him].unread.msgs);
					im.client.clearUnread(im.him);
				}

				
			},
			showMsgs:function(msgs,isHistory){
				var $msgPanel=$(im.ui.msg_panel+"[data-user-id='"+im.him+"']");
				for (var i = 0; i < msgs.length; i++) {
					var htmlContent=msgs[i].content.replace(/\r\n/g,"<br/>").replace(/\n/g,"<br/>");
					var msgCssClass=msgs[i].fromId==im.me?" my-msg":" him-msg";
					var headImg=im.client.getHeadImg(im.user[msgs[i].fromId]);
					var msgsHtml=
						"<div class='msg"+msgCssClass+"' data-msg-id='"+msgs[i].msgId+"'>"+
						"	<div class='msg-text'>"+
							htmlContent+
						"	</div>"+
						"	<div class='face-icon'>" +
						"		<img class='img' src='"+headImg+"' data-user-id='"+msgs[i].fromId+"'/>" +
						"	</div>"+
						"	<div class='arrow'></div>"+
						"</div>";
					
					if (isHistory) {
						$msgPanel.find(".msg-list").prepend(msgsHtml);
					}else{
						$msgPanel.find(".msg-list").append(msgsHtml);
					}
				}
				
				if (isHistory) {
					var historyMsgs=im.user[im.him].history.msgs;
					var endPointMsgId=historyMsgs[historyMsgs.length-im.client.history.limit].msgId;
					var endPointTop=$msgPanel.find(".msg-list .msg[data-msg-id='"+endPointMsgId+"']").offset().top;
					var startPointTop=$msgPanel.find(".msg-list .msg[data-msg-id='"+historyMsgs[historyMsgs.length-1].msgId+"']").offset().top;
					$msgPanel.animate({
						"scrollTop":endPointTop-startPointTop+"px"
					},200);
				}else{
					var scrollTop=$msgPanel.find(".bottom-point").offset().top-$msgPanel.find(".top-point").offset().top-$msgPanel.height();
					if (scrollTop>0) {
						$msgPanel.animate({
							"scrollTop":scrollTop+"px"
						},100);
					}
				}
				
				
				$(im.ui.msg_panel+" .msg-list .my-msg .face-icon .img")
				.off("error")
				.on("error",function(e){
					im.client.imgLoadError(e,im.img.type.HEAD_IMG);
				});
				$(im.ui.msg_panel+" .msg-list .him-msg .face-icon .img")
				.off("error")
				.on("error",function(e){
					im.client.imgLoadError(e,im.img.type.HEAD_IMG);
				});
				
				if (im.client.mid!=null&&im.client.mid!=im.server.mid) {
					im.server.updateMid();
				}
			},
			unreadUserCount:function(status){
				var count=0;
				for(var id in im.user){
					if(im.user[id].unread!=null){
						if (im.user[id].unread.status==status) {
							count++;
						}
					}
				}
				return count;
			},
			clearUnread:function(userId){
				im.user[userId].unread=null;
				
				$(im.ui.unread_msg_alert_box+" .message[data-user-id='"+userId+"']").slideUp(200,function(){
					$(this).remove();
				});
				
				var count=im.client.unreadUserCount(im.consts.MSG_UNREAD_NOT_LOADED);
				if (count==0) {
					window.clearInterval(im.client.twinklingHeadimgInterval);
					$(im.ui.unread_msg_twinkling_img).hide();
					$(im.ui.unread_msg_alert_box).hide();
				}
			},
			clearAllUnread:function(){
				for(var id in im.user){
					im.user[id].unread=null;
				}
				
				$(im.ui.unread_msg_alert_box+" .messages").html("");
				
				window.clearInterval(im.client.twinklingHeadimgInterval);
				$(im.ui.unread_msg_twinkling_img).hide();
				$(im.ui.unread_msg_alert_box).hide();
			},
			loadUnreadMessage:function(){
				if (im.client.twinklingHeadimgInterval!=null) {
					window.clearInterval(im.client.twinklingHeadimgInterval);
				}
				
				var msgHtml="";
				for(var id in im.user){
					if(im.user[id].unread==null||im.user[id].unread.status!=im.consts.MSG_UNREAD_NOT_LOADED){
						continue;
					}
					
					var headImg=im.client.getHeadImg(im.user[id]);
					
					msgHtml+="<div class='message' data-user-id='"+id+"'>" +
					"	<div class='user-headimg' data-user-id='"+id+"'>"+
					"		<img src='"+headImg+"' data-user-id='"+id+"'/>"+
					"	</div>"+
					"	<div class='username'>"+im.user[id].name+"</div>"+
					"	<div class='message-text'>"+im.user[id].unread.alert+"</div>"+
					"	<div class='message-num'>"+
					"		<div class='shading'></div>"+
					"		<div class='circle-left'></div>"+
					"		<div class='circle-right'></div>"+
					"		<div class='text'>"+(im.user[id].unread.msgs.length)+"</div>"+
					"	</div>"+
					"	<div class='ignore' title='忽略' data-user-id='"+id+"'></div>" +
					"	<div class='hover-skin' data-user-id='"+id+"'></div>"+
					"</div>";
					
				}
				$(im.ui.unread_msg_alert_box+" .messages").html(msgHtml);
				
				$(im.ui.unread_msg_alert_box+" .message .user-headimg img")
				.off("error")
				.on("error",function(e){
					im.client.imgLoadError(e,im.img.type.HEAD_IMG);
				});
				
				var count=im.client.unreadUserCount(im.consts.MSG_UNREAD_NOT_LOADED);
				if (count>0) {
					$(im.ui.unread_msg_twinkling_img).show();
					im.client.unreadStartTwinkling();
				}
			},
			unreadStartTwinkling:function(){
				
				var twinklingHeadImg=im.img.twinkling;
				var count=im.client.unreadUserCount(im.consts.MSG_UNREAD_NOT_LOADED);
				if(count==1){
					for(var id in im.user){
						if (im.user[id].unread!=null&&im.user[id].unread.status==im.consts.MSG_UNREAD_NOT_LOADED) {
							twinklingHeadImg=im.client.getHeadImg(im.user[id],false);
							$(im.ui.unread_msg_twinkling_img+" .img").attr("data-user-id",id);
							break;
						}
					}
				}
				$(im.ui.unread_msg_twinkling_img+" .img")
				.attr("src",twinklingHeadImg)
				.off("error")
				.on("error",function(e){
					im.client.imgLoadError(e, im.img.type.TWINKLING);
				});
				
				im.client.twinklingHeadimgInterval=window.setInterval(function(){
					$(im.ui.unread_msg_twinkling_img).toggle();
				},500);
			},
			uiInit:function(){
				$(im.ui.msg_text_input).on("keydown",function(e){
					if(im.user[im.me].defined.send.shortcut=="enter"){
						if (e.ctrlKey&&e.keyCode==13) {
							$(this).val($(this).val()+"\r\n");
						}else if(e.keyCode==13){
							$(im.ui.msg_send).trigger("click");
							e.preventDefault();
						}
					}else if(im.user[im.me].defined.send.shortcut=="ctrl+enter"){
						if (e.ctrlKey&&e.keyCode==13) {
							$(im.ui.msg_send).trigger("click");
						}
					}
				});
				
				$(im.ui.msg_send).on("click",function(){
					var content=$(im.ui.msg_text_input).val();
					if(content=="") return false;
					
					var msg=im.client.createMsg(im.me, im.him, content, im.client.getCurrTime(), "-"+new Date().getTime());
					im.user[im.him].history.msgs.unshift(msg);
					im.client.showMsgs([msg]);
					
					$(im.ui.msg_text_input).val("");
					
					im.server.send(im.me, im.him, content,function(returnData){
						console.log("发送消息："+content);
						if (returnData.ret==0) {
							console.log("发送成功!");
							var historyMsgs=im.user[im.him].history.msgs;
						}else{
							console.log("发送失败!"+$.json.encode(returnData));
						}
					});
				});
				
				$(im.ui.dialog).on("click",".left .current-dialog-close",function(e){
					e.stopPropagation();
					var userId=$(this).attr("data-user-id");
					im.client.closeDialog(userId);
				});
				
				$(im.ui.dialog).on("click",".left .dialog-user",function(){
					var userId=$(this).attr("data-user-id");
					im.him=userId;
					if (!$(this).hasClass("actived")) {
						$(im.ui.dialog_user).removeClass("actived");
						$(this).addClass("actived");
						$(im.ui.msg_panel).removeClass("actived");
						$(im.ui.msg_panel+"[data-user-id='"+im.him+"']").addClass("actived");
						$(im.ui.dialog_top_him).html(im.user[im.him].name);
						
						if (im.user[im.him].unread!=null&&im.user[im.him].unread.status==im.consts.MSG_UNREAD_LOADED) {
							im.client.showMsgs(im.user[im.him].unread.msgs);
							
							var $unreadNum=$(im.ui.dialog_user+"[data-user-id='"+im.him+"'] .unread-message-num");
							$unreadNum.removeClass("have");
							$unreadNum.find(".text").html("");
							
							im.user[im.him].unread=null;
						}
					}
				});
				
				$(im.ui.dialog_close).on("click",function(){
					im.client.resetImDialog();
				});
				
				
				$(im.ui.unread_msg_twinkling_img).on("mouseover",function(){
					$(im.ui.unread_msg_alert_box).show();
				});
				
				$(im.ui.unread_msg_alert_box).on("click",".message .hover-skin",function(){
					var userId=$(this).attr("data-user-id");
					im.client.openDialog(userId);
				});
				$(im.ui.unread_msg_alert_box).on("click",".read-all",function(){
					$(im.ui.unread_msg_alert_box+" .messages .message").each(function(){
						var userId=$(this).attr("data-user-id");
						im.client.openDialog(userId);
					});
				});
				
				$(document).on("click",function(){
					$(im.ui.unread_msg_alert_box).hide();
					$(im.ui.dialog+" .current-dialog-option .msg-send .send-setting .setting-list").hide();
				});
				
				
				$(im.ui.unread_msg_alert_box).on("click",function(e){
					e.stopPropagation();
				});
				
				$(im.ui.unread_msg_alert_box).on("click",".ignore",function(){
					var userId=$(this).attr("data-user-id");
					im.client.clearUnread(userId);
				});
				
				$(im.ui.unread_msg_alert_box).on("click",".ignore-all",function(){
					im.client.clearAllUnread();
				});
				
				$(im.ui.dialog+" .current-dialog-option .msg-send .send-setting").on("click",function(e){
					e.stopPropagation();
					$(this).find(".setting-list").show();
				});
				$(im.ui.dialog+" .current-dialog-option .msg-send .send-setting .setting-list").on("click",function(e){
					e.stopPropagation();
				});
				$(im.ui.dialog+" .current-dialog-option .msg-send .send-setting .setting-list .setting-option").on("click",function(){
					if($(this).hasClass(".actived")){
						$(this).removeClass("actived");
					}else{
						$(im.ui.dialog+" .current-dialog-option .msg-send .send-setting .setting-list .setting-option").removeClass("actived");
						$(this).addClass("actived");
					}
					$(im.ui.dialog+" .current-dialog-option .msg-send .send-setting .setting-list").hide();
					
					im.user[im.me].defined.send.shortcut=$(this).attr("data-shortcut");
					im.server.updateDefined();
				});
				$(im.ui.dialog).on("click",im.ui._msg_more,function(){
					if(im.user[im.him].history.msgs.length>0){
						var historyMsgs=im.user[im.him].history.msgs;
						im.user[im.him].history.msgId=historyMsgs[historyMsgs.length-1].msgId;
					}
					var history=im.server.privateHistory(im.me, im.him, im.client.history.limit, im.user[im.him].history.msgId);
					console.log("获取历史消息：");
					console.log($.json.encode(history));
					if (history!=null&&history.length>0) {
						var msgs=[];
						for (var i = 0; i < history.length; i++) {
							if(history[i].msg_type=="msg"){
								var historyMsg=im.client.readMsg(history[i],im.consts.CHAT_TYPE_PRIVATE);
								msgs.push(historyMsg);
								im.user[im.him].history.msgs.push(historyMsg);
							}
						}
						im.client.showMsgs(msgs, true);
					}
				});
			}
		}
};


$(function(){
	$("#address .to-him").on("click",function(){
		var accountId=$(this).attr("data-id");
		im.client.openDialog(accountId);
	});
	
	
	
	
	var initResult=im.client.init();
	if (initResult) {
		var handshakeResult=im.client.handshake();
		if (handshakeResult) {
			im.client.createChannel();
			im.client.channel.start();
			im.client.uiInit();
			
			$("#address .to-him[data-id='"+im.me+"']").hide();
		}
	}
	
});

