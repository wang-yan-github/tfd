/**
 * 用户首页加载的JS代码
 */
//获得应用名
function getRootPath(){
	var p = self.location+"";	
	var pp = p.split("/");	
	return (pp[3]);
}
//显示在线用户信息
function showOnline(){   		
	var obj = document.all("idOnline");
	obj.style.display = obj.style.display==""?"none":"";
	obj.style.left = parseInt(obj.style.left) < 0 ? (event.x-156):-220;
}

//我的主页
function ckMyDesktop(id){
		document.getElementById("sysmain").src = "/"+getRootPath()+"/horizon/layout/grsz.doGetUserLayoutContent.do?flag=edit";
}
//注销
function logoff(){
	var hRemark = document.getElementById("HRemark1");
	if (hRemark) {
		try{
			var path="c:/temp/oa/myword.doc";
			hRemark.DeleteFileJava(path);
			path="c:/temp/oa/mysysword.doc";
			hRemark.DeleteFileJava(path);
			path="c:/temp/oa/myredword.doc";
			hRemark.DeleteFileJava(path);
			path="c:/temp/oa/mygzword.doc";
			hRemark.DeleteFileJava(path);
		}catch(e){}
	}
	window.document.location.assign("/"+getRootPath()+"/logout.jsp");
}
//邮件
function openMail(){
  document.getElementById("sysmain").src = "/"+getRootPath()+"/horizon/formview/view/workview.default.jsp?menuid=HZWHfxu0kVr3OBGigHs0sphkwRHbD2tw";
}
//短信
function openSMS(){
  document.getElementById("sysmain").src = "/"+getRootPath()+"/horizon/formview/view/workview.default.jsp?menuid=HZyoMdctps3o2k7NraTHyxAuFeDiaAso";
}
//在线消息
function openMessage(){
document.getElementById("sysmain").src = "/"+getRootPath()+"/horizon/formview/view/workview.default.jsp?menuid=HZCEfsidvCM2X6KhDl9cUfa0dObuI0ML";
}
//日程安排
function openCalendar(){
document.getElementById("sysmain").src = "/"+getRootPath()+"/horizon/formview/view/workview.default.jsp?menuid=HZlH59fJqOR5mWg24Alqkh1x3pxSkXKm";
}
//应用
function openOperator(){
window.open("/"+getRootPath()+"/resource/skins"+defaultPath+"/operator.jsp",'OperatorWindow','width='+(screen.availWidth-20)+',height='+(screen.availHeight-65)+',top=0,left=0,status=yes,titlebar=no,toolbar=no,scrollbars=yes,resizable=yes');
return false;
}
//管理
function openAdministrator(){
	//alert("/"+getRootPath()+"/resource/skins"+defaultPath+"/administrator.jsp",'AdministratorWindow','width='+(screen.availWidth-20)+',height='+(screen.availHeight-65)+',top=0,left=0,status=yes,titlebar=no,toolbar=no,scrollbars=yes,resizable=yes')
window.open("/"+getRootPath()+"/resource/skins"+defaultPath+"/administrator.jsp",'AdministratorWindow','width='+(screen.availWidth-20)+',height='+(screen.availHeight-65)+',top=0,left=0,status=yes,titlebar=no,toolbar=no,scrollbars=yes,resizable=yes');

return false;
}
//设计
function openDesigner(){
window.open("/"+getRootPath()+"/horizon/designer/HorizonDesigner.jsp",'DesignerWindow','width='+(screen.availWidth-20)+',height='+(screen.availHeight-65)+',top=0,left=0,status=yes,titlebar=no,toolbar=no,scrollbars=yes,resizable=yes');
return false;
}
//修改密码
function updatepassword(){
	var iHeight = 400;            
	var iWidth = 600;
	var iTop = (window.screen.availHeight-30-iHeight)/2;    //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2;      //获得窗口的水平位置;
	window.open("/"+getRootPath()+"/horizon/org/user/org.form.user.password.update.jsp",'','width=600,height=200,top='+iTop+',left='+iLeft+',status=yes,titlebar=no,toolbar=no,scrollbars=yes,resizable=yes');
	//document.getElementById("sysmain").src = "/"+getRootPath()+"/horizon/org/user/org.form.user.password.update.jsp";
}
//个人设置
function grsz(){
	var iHeight = 200;            
	var iWidth = 600;
	var iTop = (window.screen.availHeight-30-iHeight)/2;    //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2;      //获得窗口的水平位置;
	window.open("/"+getRootPath()+"/horizon/layout/grsz.doGetUserLayoutContent.do?flag=edit",'','width=600,height=180,top='+iTop+',left='+iLeft);
}
//切换服务器
function switchServer(){
	var iHeight = 500;
	var iWidth = 400;
	var iTop = (window.screen.availHeight-30-iHeight)/2;    //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2;     //获得窗口的水平位置;
	window.open("/"+getRootPath()+"/switchserver.jsp?flag=edit",'','width=400,height=500,top='+iTop+',left='+iLeft);
}
 function CLASS_MSN_MESSAGE(id,width,height,caption,title,message,target,action){  
    this.id     = id;  
    this.title  = title;  
    this.caption= caption;  
    this.message= message;  
    this.target = target;  
    this.action = action;  
    this.width    = width?width:200;  
    this.height = height?height:120;  
    this.timeout= 150;  
    this.speed    = 20; 
    this.step    = 1; 
    this.right    = screen.width -1;  
    this.bottom = screen.height; 
    this.left    = this.right - this.width; 
    this.top    = this.bottom - this.height; 
    this.timer    = 0; 
    this.pause    = false;
    this.close    = false;
    this.autoHide    = true;
} 
/**//*  
*    隐藏消息方法  
*/  
CLASS_MSN_MESSAGE.prototype.hide = function(){  
    if(this.onunload()){  
        var offset  = this.height>this.bottom-this.top?this.height:this.bottom-this.top; 
        var me  = this;  
        if(this.timer>0){   
            window.clearInterval(me.timer);  
        }  
        var fun = function(){  
            if(me.pause==false||me.close){
                var x  = me.left; 
                var y  = 0; 
                var width = me.width; 
                var height = 0; 
                if(me.offset>0){ 
                    height = me.offset; 
                } 
     
                y  = me.bottom - height; 
     
                if(y>=me.bottom){ 
                    window.clearInterval(me.timer);  
                    me.Pop.hide();  
                } else { 
                    me.offset = me.offset - me.step;  
                } 
                try{
                me.Pop.show(x,y,width,height);  
                }catch(e){}
            }             
        }  
        this.timer = window.setInterval(fun,this.speed)      
    }  
} 
/**//*  
*    消息卸载事件，可以重写  
*/  
CLASS_MSN_MESSAGE.prototype.onunload = function() {  
    return true;  
}  

/**//*  
*    消息命令事件，要实现自己的连接，请重写它  
*  
*/  
CLASS_MSN_MESSAGE.prototype.oncommand = function(){  
    this.hide();
    //点击查看时如果只有一条消息，则直接打开消息内容，否则点击查看会链接到“我收到的消息”
    if(lens>1){
    document.getElementById("sysmain").src = "/"+getRootPath()+"/horizon/formview/view/viewtemplate/view.template.normal.jsp?viewid=HZMYqkRnowhK6rVETjeSumCOSCMH72qB&cat=(%20b.RECEIVEFLAG~'%E6%9C%AA%E9%98%85%E8%AF%BB')";
    }else{
    window.open("/"+getRootPath()+"/horizon/online/sysalert.read.jsp?id="+alertid,"","width=720,height=480,top="+(screen.height-450)/2+",left="+(screen.width-710)/2+",status=no,titlebar=no,toolbar=no,scrollbars=yes,resizable=yes");
    }
} 
/**//*  
*    消息显示方法  
*/  
CLASS_MSN_MESSAGE.prototype.show = function(){  
    var oPopup = window.createPopup(); //IE5.5+  
    
    this.Pop = oPopup;  
  
    var w = this.width;  
    var h = this.height;  
  
    var str = "<DIV id='message' style='BORDER-RIGHT: #455690 1px solid; BORDER-TOP: #a6b4cf 1px solid; Z-INDEX: 99999; LEFT: 0px; BORDER-LEFT: #a6b4cf 1px solid; WIDTH: " + w + "px; BORDER-BOTTOM: #455690 1px solid; POSITION: absolute; TOP: 0px; HEIGHT: " + h + "px; BACKGROUND-COLOR: #c9d3f3'>"  
        str += "<TABLE style='BORDER-TOP: #ffffff 1px solid; BORDER-LEFT: #ffffff 1px solid' cellSpacing=0 cellPadding=0 width='100%' bgColor=#cfdef4 border=0>"  
        str += "<TR>"  
        str += "<TD style='FONT-SIZE: 12px;COLOR: #0f2c8c' width=30 height=24></TD>"  
        str += "<TD style='PADDING-LEFT: 4px; FONT-WEIGHT: normal; FONT-SIZE: 12px; COLOR: #1f336b; PADDING-TOP: 4px' vAlign=center width='100%'>" + this.caption + "</TD>"  
        str += "<TD style='PADDING-RIGHT: 2px; PADDING-TOP: 2px' vAlign=center align=right width=19>"  
        str += "<SPAN title=关闭 style='FONT-WEIGHT: bold; FONT-SIZE: 12px; CURSOR: hand; COLOR: red; MARGIN-RIGHT: 4px' id='btSysClose' >×</SPAN></TD>"  
        str += "</TR>"  
        str += "<TR>"  
        str += "<TD style='PADDING-RIGHT: 1px;PADDING-BOTTOM: 1px' colSpan=3 height=" + (h-28) + ">"  
        str += "<DIV style='BORDER-RIGHT: #b9c9ef 1px solid; PADDING-RIGHT: 8px; BORDER-TOP: #728eb8 1px solid; PADDING-LEFT: 8px; FONT-SIZE: 12px; PADDING-BOTTOM: 8px; BORDER-LEFT: #728eb8 1px solid; WIDTH: 100%; COLOR: #1f336b; PADDING-TOP: 8px; BORDER-BOTTOM: #b9c9ef 1px solid; HEIGHT: 100%'>" + this.title + "<BR><BR>"  
        //str += "<DIV style='WORD-BREAK: break-all' align=left><A href='<path:ctx/>/oa/modules/alert/sysalert.read.jsp?id="+alertid+"' hidefocus=false id='btCommand'><FONT color=#ff0000>查看</FONT></A></DIV>"  
        str += "<DIV style='WORD-BREAK: break-all' align=left><A href='<path:ctx/>/horizon/formview/view/workview.default.jsp?menuid=HZCEfsidvCM2X6KhDl9cUfa0dObuI0ML&parentId=HZ28e7fd2041f4df0120421b78160008' hidefocus=false id='btCommand'><FONT color=#ff0000>查看</FONT></A></DIV>"  
        str += "</DIV>"  
        str += "</TD>"  
        str += "</TR>"  
        str += "</TABLE>"  
        str += "</DIV>"  

    oPopup.document.body.innerHTML = str; 
    
  
    this.offset  = 0; 
    var me  = this;  
    oPopup.document.body.onmouseover = function(){me.pause=true;}
    oPopup.document.body.onmouseout = function(){me.pause=false;}
    var fun = function(){  
        var x  = me.left; 
        var y  = 0; 
        var width    = me.width; 
        var height    = me.height; 
            if(me.offset>me.height){ 
                height = me.height; 
            } else { 
                height = me.offset; 
            } 
        y  = me.bottom - me.offset; 
        if(y<=me.top){ 
            me.timeout--; 
            if(me.timeout==0){ 
                window.clearInterval(me.timer);  
                if(me.autoHide){
                    me.hide(); 
                }
            } 
        } else { 
            me.offset = me.offset + me.step; 
        } 
        try{
        me.Pop.show(x,y,width,height);
        }catch(e){}
    }   
  
    this.timer = window.setInterval(fun,this.speed)      
  
     
  
    var btClose = oPopup.document.getElementById("btSysClose");  
  
    btClose.onclick = function(){  
        me.close = true;
        me.hide();  
    }  
  
    var btCommand = oPopup.document.getElementById("btCommand");  
    btCommand.onclick = function(){  
        me.oncommand();  
    }    
  //var ommand = oPopup.document.getElementById("ommand");  
      //ommand.onclick = function(){  
       //this.close = true;
    //me.hide();  
	//window.open(ommand.href);
    //}   
}  
/**//* 
** 设置速度方法 
**/ 
CLASS_MSN_MESSAGE.prototype.speed = function(s){ 
    var t = 20; 
    try { 
        t = praseInt(s); 
    } catch(e){} 
    this.speed = t; 
} 
/**//* 
** 设置步长方法 
**/ 
CLASS_MSN_MESSAGE.prototype.step = function(s){ 
    var t = 1; 
    try { 
        t = praseInt(s); 
    } catch(e){} 
    this.step = t; 
} 
  
CLASS_MSN_MESSAGE.prototype.rect = function(left,right,top,bottom){ 
    try { 
        this.left        = left    !=null?left:this.right-this.width; 
        this.right        = right    !=null?right:this.left +this.width; 
        this.bottom        = bottom!=null?(bottom>screen.height?screen.height:bottom):screen.height; 
        this.top        = top    !=null?top:this.bottom - this.height; 
    } catch(e){} 
}
this.GetMessage = function GetMessage(){
		lens = getMessageNum();
		if(lens>0){
			//如果消息的长度等于1，需要记录alertid以便打开消息所用
			if(lens==1){
				var alertids = aids.split(";");
				alertid = alertids[0];
			}
			var MSG1 = new CLASS_MSN_MESSAGE("aa",200,120,"短消息提示：","您共有"+lens+"封未读消息","javascript仿QQ右下角消息"); 			
			MSG1.rect(null,null,null,screen.height-50); 
			MSG1.speed    = 5; 
			//MSG1.step    = 10;
				
			document.getElementById("msgmusic").src="/"+getRootPath()+"/oa/system.wav"; //声音文件			
			MSG1.show();  			
		}				
	} 

// 获取未读消息数目
function getMessageNum(){
	var listenerurl="/"+getRootPath()+"/horizon/online/popup.listener.get.jsp?Rand="+(new Date).getTime();
	aids=_GetXMLBack(listenerurl);
	if(aids!=null && aids!=""){
	    var alertids =aids.split(";");
		return alertids.length-1;
	}else{
		return 0;
	}
}

function _GetXMLBack(url){			
	var backstr="";
	var source=_GetXML(url);
	backstr=source.documentElement.firstChild.text;
	return backstr;
}

