var testNum =0;
var oldUL1;
var objList = new Array();
var objNum =0;
function getWidth(){
	var backWidth = 800;
	if(document.body.clientWidth < 950) backWidth= 800;
	else backWidth= document.body.clientWidth-150; 
	/*
	var navWidth = parseInt($("nav").style.width);
	if(backWidth != navWidth) {
		if((navWidth+"") != "NaN"){
			
			
		}
	}
	//*/
	return backWidth;
}

function LoadMenuByGroupID1(){	
	var aUrl = defaultURL +"/horizon/meun/sysmenu.getxml.jsp";
	menuGroupID = arguments[0];
	var userStyle = hz_style;
	var per = 16;
	if(userStyle.toLowerCase() == "lfont"){
		per = 20;
	}
	if(menuGroupID.toLowerCase() == "root_node_id" || menuGroupID==""){menuGroupID ="root_node_id";	}
	var para = "ln="+nowLN +"&menuid="+menuGroupID+"&roles="+userRoles+"&sub="+(arguments.length==2?arguments[1]:"-1");
	para +="&width="+parseInt($("nav").style.width)+"&per=" + per;
	
	new AjaxClass("Post",aUrl,para,null,		
				function (obj){
					makemenu(obj);
				}	//结果返回后的处理函数名称,需要自行编写
				,function(){}
				//,iIntervalID			//计时器，如果有的话				
			).StartXMLHttp();		
	return;
}


//加入对pushlet的调用
function LoadMenuByGroupID(str){	
	var aUrl = defaultURL +"/horizon/menu/sysmenu.getxml.jsp";
	menuGroupID = arguments[0];
	var userStyle = hz_style;
	var per = 16;
	if(userStyle.toLowerCase() == "lfont"){
		per = 20;
	}
	if(menuGroupID.toLowerCase() == "root_node_id" || menuGroupID==""){menuGroupID ="root_node_id";	}
	var para = "ln="+nowLN +"&menuid="+menuGroupID+"&roles="+userRoles+"&sub="+(arguments.length==2?arguments[1]:"-1");
	para +="&width=80"+"&per=" + per;
	
	new AjaxClass("Post",aUrl,para,null,		
				function (obj){
					makemenu(obj);
				}	//??????è????????????¤?????????°????§°,é??è|?è?aè????????
				,function(){}
				//,iIntervalID			//è???????¨????|??????????èˉ?				
			).StartXMLHttp();		
	if(str=='initPushlet')init();
	return;	
}


function makemenu(obj){
		try{
		var _strXml = obj.xmlHttp.responseText;
		_strXml = _strXml.replace(/\n|\r/g,"");
		if(_strXml.indexOf("document.")==0)	eval(_strXml);
		else {
			alert("会话过期.");
			self.location.reload(true);
			return;
		}
	}
	catch(E){}
}

////////////////////////////////////////////////////
function displaySubMenu() {
    var li = arguments[0];
    var subMenu = li.getElementsByTagName("ul")[0];
    hideAllSubMenu();
    hideMenuForDisplay(subMenu);
    if(subMenu == undefined){    	return;    }
    subMenu.style.display = "block";	
    //对超出边界的菜单进行处理/////////////////////////    
    
    var left = li.offsetLeft; 				//左边位置坐标li.offsetLeft,
    var width = subMenu.clientWidth; 			//下级菜单宽度 subMenu.clientWidth,
    var bodywidth = document.body.clientWidth;	//页面总的宽度document.body.clientWidth
    var right = left+width;			
    if(right>bodywidth){
    		subMenu.style.left = -(width-li.clientWidth) ;
    }  
	  else{
	  		subMenu.style.left = 0;
	  }
    ////////////////////////////////////////////////////
    //document.all("rtfTest").value += "\nClassname="+subMenu.className;
    if(arguments.length>1 && subMenu.innerHTML.toLowerCase()=="<li>loading...</li>"){   
    		subMenu.innerHTML = "<li>loading....</li>";	//避免重复加载
    		LoadMenuByGroupID(arguments[1],"1");    	//显示二级菜单
    }
}
function hideSubMenu() {
   var subMenu = arguments[0].getElementsByTagName("ul")[0];
   hideAllForLoad();
}
function hideMenuForDisplay(){	
	if(oldUL1!=undefined)   {   	oldUL1.style.display = "none";}
   	oldUL1 = arguments[0];	
}
////////////////////////////////////////////////////////
function displaySencondSubMenu() {  
    var li = arguments[0];
    var subMenu = li.getElementsByTagName("ul")[0];
    //隐藏ULObjList对象
    hideAllSubMenu();   
    subMenu.style.display = "block"; 
    //对超出边界的菜单进行处理/////////////////////////  
    //var parLi  = li.parentElement.parentElement;
    var parLi = li.parentNode;
    
    var bodywidth = document.body.clientWidth;		//页面总的宽度document.body.clientWidth
    var width=0,left = 0,offsetLeft = 0,clientWidth=0;
 
    while(true){
    		if(parLi.className =="sub"){    			//找到二级菜单时,退出
    			left += parLi.offsetLeft;    			
    			parLi = parLi.parentNode;  
    			left += parLi.offsetLeft;			
			break; 
    		}    		
    		left += parLi.offsetLeft;
    		offsetLeft = (offsetLeft==0?parLi.offsetLeft:offsetLeft);		//纠正五级菜单第一次显示时offsetLeft值和clientWidth为0的问题
    		clientWidth= (clientWidth==0?parLi.clientWidth:clientWidth);
    		parLi = parLi.parentNode;    
    }
   
    left += (subMenu.offsetLeft==0?offsetLeft:subMenu.offsetLeft);  	
    width = (subMenu.clientWidth==0?clientWidth:subMenu.clientWidth); 
    
    var right = left+width;		//确定要显示的子菜单右边坐标是否超界
    if(right>bodywidth){
    		subMenu.style.left = -width;    		
    }    
    ////////////////////////////////////////////////////   
    if(arguments.length>1 && subMenu.innerHTML.toLowerCase()=="<li>loading...</li>"){    		
    		LoadMenuByGroupID(arguments[1]);    		//显示三级以上菜单
    }
    
}
function hideSencondSubMenu() {
   var subMenu = arguments[0].getElementsByTagName("ul")[0];
   objList[objNum++] = subMenu;
}
//二级菜单有三级菜单,鼠标移出事件
function SencondHaveSubMenuOut() {
   var subMenu = arguments[0].getElementsByTagName("ul")[0];
   objList[objNum++] = subMenu;
}
//没有三级菜单的二级菜单,鼠标移上事件
function SencondNoSubMenuOver() {
   hideAllSubMenu();
}
//隐藏数组中的节点
function hideAllSubMenu(){    
    for(var i=0 ;i<objNum;i++){
    		objList[i].style.display = "none";  			
    }    
    objNum = 0;
    objList = new Array();  
}
////////二级菜单无下级菜单时隐藏其他二级菜单的下级菜单/////////////
function hideAllForLoad(){
	hideAllSubMenu();
	hideMenuForDisplay();
}

///////////////////////////
var iIntervalID;
function ckMenu(){ 
	var openmode = arguments[0];
	var URL = arguments[1].getAttribute("aURL");//aURL; modify by wangmh
	try{
		hideAllSubMenu();
		hideMenuForDisplay();
	}
	catch(e){}
	if(URL==""){
		URL="error.jsp?errorName=codrp.menu.ft.unspecifiedMenuURL";
	}
	URL = defaultURL+(URL.substring(0,1)=="/"?"":"/")+URL;
	if(openmode =="sysmain"){
	   var fr=document.all("sysmain");
	   //iIntervalID= window.setInterval("chgHeight()",1000);
	   fr.src=URL;
	}
	else	{
		window.open(URL);
	}
	return false;
}

function chgParentIframe(){
	var obj = document.all("sysmain");	//alert(obj.src);
	var divObj = obj.parentElement;
	obj.style.height =	502;//	sysmain.document.body.scrollHeight
	divObj.style.height = 502;// sysmain.document.body.scrollHeight

	//alert("clientHeight="+document.body.clientHeight +",scrollHeight="+document.body.scrollHeight+",offsetHeight="+document.body.offsetHeight);
}

function chgHeight(){
	try{
		var obj = document.getElementById("sysmain");
		var divObj = obj.parentNode;
		obj.height =		sysmain.document.body.scrollHeight;
		divObj.height =  sysmain.document.body.scrollHeight;
	}catch(e){}
}
/********3.0菜单调用方法***********/
function LoadMenuByGroupIdNew(){
	var aUrl = defaultURL +"/horizon/menu/sysmenu.xml.jsp";
	/*menuGroupID = arguments[0];
	var userStyle = hz_style;
	var per = 16;
	if(userStyle.toLowerCase() == "lfont"){
		per = 20;
	}
	if(menuGroupID.toLowerCase() == "root_node_id" || menuGroupID==""){menuGroupID ="root_node_id";	}
	var para = "ln="+nowLN +"&menuid="+menuGroupID+"&roles="+userRoles+"&sub="+(arguments.length==2?arguments[1]:"-1");
	para +="&width=80&per=" + per;
	*/
	new AjaxClass("Post",aUrl,null,null,		
				function (obj){
					makemenu(obj);
				}
				,function(){}
			).StartXMLHttp();		
	return;
}

//通过JS解析生成一级菜单
function LoadFirstLevelMenuByJs(){

	var aUrl = defaultURL +"/horizon/menu/sysmenu.xmlbyjs.jsp";
    
	var source = _GetXML(aUrl);
	var _strXml = source.documentElement;
	var _xmlObj = null;
	if((!!window.ActiveXObject )||( "ActiveXObject" in window)){	//IE
		_xmlObj=_strXml.selectSingleNode("//MenuNav");
	}else{	//Firefox
		_xmlObj=_strXml.firstElementChild;
	}
	var str = '<li class=""><a href="#" onclick="toHome()" class="active">首&nbsp;页</a></li>';
	for(var k=0;k<_xmlObj.childNodes.length;k++){
		if(_xmlObj.childNodes[k].nodeType == 1){	
			str += _recursionMenuTree(_xmlObj.childNodes[k]);
		}
	}
	document.all("navnew").innerHTML=str;

	return;
}

function _recursionMenuTree(){ //遍历生成首页菜单
	var _xmlObj = arguments[0];
	var divStr = "";
	var mtype=_getAttributeValue(_xmlObj,"type");
	//alert(type+"="+_getAttributeValue(_xmlObj,"deptid"));
	if(_xmlObj.childNodes.length>0 || mtype=="Group" || mtype.indexOf(".")!=-1){ //如果有子节点
		
	}else{ //没有子节点的时候
		divStr+='<li><a href="#" id="'+_getAttributeValue(_xmlObj,"id")+'" defaultUrl="'+_getAttributeValue(_xmlObj,"defaultUrl")+'" ';
		tmpFun = _getAttributeValue(_xmlObj,"func");
		if(tmpFun!="" && tmpFun!="''"){
			divStr+='onclick="'+tmpFun+';getSubMenuByJs(this);"';
		}else{
			divStr+='onclick="getSubMenuByJs(this);" ';
		}
		divStr+='title="'+_getAttributeValue(_xmlObj,"menuName")+'" class="nav" <span>'+_getAttributeValue(_xmlObj,"nodeName")+'</span></a></li>';
		
	}
	return divStr;
}

function _recursionSubMenuTree(){ //遍历生成子菜单二级菜单
	var _xmlObj = arguments[0];
	var divStr = "";
	var mtype=_getAttributeValue(_xmlObj,"type");
	//alert(type+"="+_getAttributeValue(_xmlObj,"deptid"));
	if(_xmlObj.childNodes.length>0 || mtype=="Group" || mtype.indexOf(".")!=-1){ //如果有子节点
		
	}else{ //没有子节点的时候
		divStr+='<div style="width:4px;"></div> <div id="'+_getAttributeValue(_xmlObj,"id")+'" class="out"  onmouseover="menuover(this)" onmouseout="menuout(this)"';
		tmpFun = _getAttributeValue(_xmlObj,"func");
		if(tmpFun!="" && tmpFun!="''"){
			divStr+='onclick="'+tmpFun+';menuclick(this)"';
		}else{
			divStr+='onclick="menuclick(this)" ';
		}
		var xys = _getAttributeValue(_xmlObj,"menuImg").split(",");
		if(xys[0]==null||xys[0]==''){
			xys[0]='0';
		}
		if(xys[1]==null||xys[1]==''){
			xys[1]='0';
		}
		divStr+='target="'+_getAttributeValue(_xmlObj,"target")+'" status="0" aUrl="'+_getAttributeValue(_xmlObj,"defaultUrl")+'" title="'+_getAttributeValue(_xmlObj,"menuName")+'"> ';
		divStr+='<div class="icon" style="background-Position:'+xys[0]+'px '+xys[1]+'px;"></div><div align="center" class="mfont">';
		divStr+=_getAttributeValue(_xmlObj,"nodeName")+'</div></div>';
	}
	return divStr;
}

function _getAttributeValue(){
	var v = "";
	try{
		//alert(arguments[0]);
		v = arguments[0].attributes.getNamedItem(arguments[1]).value;
		//alert(arguments[1]);
		//alert("V:" +v);
	}catch(E){}
		
	return (v==null|| v=="" || v=="null")?"":v;
}

/********OA5.0管理菜单调用方法***********/
function LoadMenuByGroupId4Manager(){
	var aUrl = defaultURL +"/horizon/menu/sysmenu.manager.xml.jsp";
	/*menuGroupID = arguments[0];
	var userStyle = hz_style;
	var per = 16;
	if(userStyle.toLowerCase() == "lfont"){
		per = 20;
	}
	if(menuGroupID.toLowerCase() == "root_node_id" || menuGroupID==""){menuGroupID ="root_node_id";	}
	var para = "ln="+nowLN +"&menuid="+menuGroupID+"&roles="+userRoles+"&sub="+(arguments.length==2?arguments[1]:"-1");
	para +="&width=80&per=" + per;
	*/
	new AjaxClass("Post",aUrl,null,null,		
				function (obj){
					makemenu(obj);
				}
				,function(){}
			).StartXMLHttp();		
	return;
}
function headDispaly(obj){
	if(obj.className=="callspmenu"){
		obj.className="exllspmenu";
		$("headtr").style.display="none";
	}else{
		obj.className="callspmenu";
		$("headtr").style.display="";
	}
}

//返回首页
function toHome(){
	
	if($("menuid").value!=""){
		$($("menuid").value).className="";
		$("menuid").value = "";
	}
	jq("#layout_menu_view").attr("sytle","width:100%;min-height:100px");
	//$("sysmain").src = defaultURL+"/horizon/layout/layout.doGetUserLayoutContent.do?userId=public&flag=view";
	$("sysmain").src = "indexbuttom2.jsp";
}

//点击一级菜单，展现二级菜单
function getSubMenu(obj){
	if(!obj) return;
	if($("menuid").value==obj.id) return;
	obj.parentNode.className = "on";
	if($("menuid").value!=""){
		$($("menuid").value).parentNode.className="";
	}
	$("menuid").value = obj.id;
	$("submenu").innerHTML = "";
	var menuid = obj.id;//getAttribute("menuid");
	if($("menu"+menuid)){
		$("submenu").innerHTML=$("menu"+menuid).innerHTML;
		if($("submenu").innerHTML!=""){
			$("secmenu").style.display="block";
		}else{
			$("secmenu").style.display="none";
		}
	}else{
		var aUrl = defaultURL +"/horizon/menu/sysmenu.sub.jsp";
		var para = "menuid="+menuid;
		new AjaxClass("Post",aUrl,para,null,		
					function (obj){
						makemenu(obj);
						var tmpdiv = document.createElement("div");
						tmpdiv.id = "menu"+menuid;
						tmpdiv.innerHTML = $("submenu").innerHTML;
						$("hiddenDiv").appendChild(tmpdiv);
						if($("submenu").innerHTML!=""){
							$("secmenu").style.display="block";
						}else{
							$("secmenu").style.display="none";
						}
					}	
					,function(){}
				).StartXMLHttp();
	}
	
	var defaultSrc = "";
	if(obj.getAttribute("defaultUrl")&&obj.getAttribute("defaultUrl")!=""){
		defaultSrc = defaultURL + obj.getAttribute("defaultUrl");
	}
	if(defaultSrc==""){
		$("sysmain").src="";
		return;
	}
	//验证一级菜单的默认连接是否有效
	var xHTTP = xmlhttp();
	xHTTP.onreadystatechange = function(){
		if(xHTTP.readyState==4){
			if(xHTTP.status==200){
				$("sysmain").src=defaultSrc;
			}else{
				if(confirm("该菜单默认访问地址："+defaultSrc+"\n为无效链接，是否继续访问？")){
					$("sysmain").src=defaultSrc;
				}else{
					$("sysmain").src="";
				}
			}
		}
	}
	xHTTP.open("Post",defaultSrc,true);
	xHTTP.send(null);
}

//点击一级菜单，展现二级菜单 用js解析xml生成
function getSubMenuByJs(obj){
	if(!obj) return;
	if($("menuid").value==obj.id) return;
	obj.parentNode.className = "on";
	if($("menuid").value!=""){
		$($("menuid").value).parentNode.className="";
	}
	$("menuid").value = obj.id;
	$("submenu").innerHTML = "";
	var menuid = obj.id;//getAttribute("menuid");
	if($("menu"+menuid)){
		$("submenu").innerHTML=$("menu"+menuid).innerHTML;
		if($("submenu").innerHTML!=""){
			$("secmenu").style.display="block";
		}else{
			$("secmenu").style.display="none";
		}
	}else{
		aUrl = defaultURL +"/horizon/menu/sysmenu.subbyjs.jsp?menuid="+menuid;
		var source = _GetXML(aUrl);
		var _strXml = source.documentElement;
		var _xmlObj = null;
		try{
			if((!!window.ActiveXObject )||( "ActiveXObject" in window)){	//IE
				_xmlObj=_strXml.selectSingleNode("//MenuNav");
			}else{	//Firefox
				_xmlObj=_strXml.firstElementChild;
			}
		}catch(e){//防止SESSION失效，点击按钮报错
			toHome();
			return;
		}
		var str = '';
		for(var k=0;k<_xmlObj.childNodes.length;k++){
			if(_xmlObj.childNodes[k].nodeType == 1){	
				str += _recursionSubMenuTree(_xmlObj.childNodes[k]);
			}
		}
		document.all("submenu").innerHTML = str;
		var tmpdiv = document.createElement("div");
		tmpdiv.id = "menu"+menuid;
		tmpdiv.innerHTML = $("submenu").innerHTML;
		$("hiddenDiv").appendChild(tmpdiv);
		if($("submenu").innerHTML!=""){
			$("secmenu").style.display="block";
		}else{
			$("secmenu").style.display="none";
		}
	}
	
	var defaultSrc = "";
	if(obj.getAttribute("defaultUrl")&&obj.getAttribute("defaultUrl")!=""){
		defaultSrc = defaultURL + obj.getAttribute("defaultUrl");
	}
	if(defaultSrc==""){
		$("sysmain").src="";
		return;
	}
	//验证一级菜单的默认连接是否有效
	var xHTTP = xmlhttp();
	xHTTP.onreadystatechange = function(){
		if(xHTTP.readyState==4){
			if(xHTTP.status==200){
				$("sysmain").src=defaultSrc;
			}else{
				if(confirm("该菜单默认访问地址："+defaultSrc+"\n为无效链接，是否继续访问？")){
					$("sysmain").src=defaultSrc;
				}else{
					$("sysmain").src="";
				}
			}
		}
	}
	xHTTP.open("Post",defaultSrc,true);
	xHTTP.send(null);
}

//点击一级菜单，展现二级菜单
function getSubMenu4Manager(obj){
	if(!obj) return;
	if($("menuid").value==obj.id) return;
	obj.parentNode.className = "on";
	if($("menuid").value!=""){
		$($("menuid").value).parentNode.className="";
	}
	$("menuid").value = obj.id;
	$("submenu").innerHTML = "";
	var menuid = obj.id;//getAttribute("menuid");
	if($("menu"+menuid)){
		$("submenu").innerHTML=$("menu"+menuid).innerHTML;
		if($("submenu").innerHTML!=""){
			$("secmenu").style.display="block";
		}else{
			$("secmenu").style.display="none";
		}
	}else{
		var aUrl = defaultURL +"/horizon/menu/sysmenu.manager.sub.jsp";
		var para = "menuid="+menuid;
		new AjaxClass("Post",aUrl,para,null,		
					function (obj){
						makemenu(obj);
						var tmpdiv = document.createElement("div");
						tmpdiv.id = "menu"+menuid;
						tmpdiv.innerHTML = $("submenu").innerHTML;
						$("hiddenDiv").appendChild(tmpdiv);
						if($("submenu").innerHTML!=""){
							$("secmenu").style.display="block";
						}else{
							$("secmenu").style.display="none";
						}
					}	
					,function(){}
				).StartXMLHttp();
	}
	
	var defaultSrc = "";
	if(obj.getAttribute("defaultUrl")&&obj.getAttribute("defaultUrl")!=""){
		defaultSrc = defaultURL + obj.getAttribute("defaultUrl");
	}
	if(defaultSrc==""){
		$("sysmain").src="";
		return;
	}
	//验证一级菜单的默认连接是否有效
	var xHTTP = xmlhttp();
	xHTTP.onreadystatechange = function(){
		if(xHTTP.readyState==4){
			if(xHTTP.status==200){
				$("sysmain").src=defaultSrc;
			}else{
				if(confirm("该菜单默认访问地址："+defaultSrc+"\n为无效链接，是否继续访问？")){
					$("sysmain").src=defaultSrc;
				}else{
					$("sysmain").src="";
				}
			}
		}
	}
	xHTTP.open("Post",defaultSrc,true);
	xHTTP.send(null);
}
function menuover(obj){
	if(obj.getAttribute("status")=="1"){return;}
	var icon = obj.childNodes[0];
	obj.className = "over";
	var pos = icon.style.backgroundPosition.split(" ");
	var x = parseInt(pos[0]);
	var y = parseInt(pos[1]);
	icon.style.backgroundPosition = "-30px "+y+"px";
}
function menuout(obj){
	if(obj.getAttribute("status")=="1"){return;}
	var icon = obj.childNodes[0];
	obj.className = "out";
	var pos = icon.style.backgroundPosition.split(" ");
	var x = parseInt(pos[0]);
	var y = parseInt(pos[1]);
	icon.style.backgroundPosition = "0px "+y+"px";
}
function menuclick(obj){
	
	if(obj.getAttribute("aUrl")==null||obj.getAttribute("aUrl")==""){
		alert("此菜单没有有效链接地址，请与管理员联系！");return;
	}
	if(obj.getAttribute("status")=="1"){
		//if($("sysmain").src!=(defaultURL+obj.getAttribute("aUrl"))){
		//$("sysmain").src=defaultURL+obj.getAttribute("aUrl");			
	//	}
		//为了防止连续点击菜单时不能弹出新页面，应该点击一次后obj.setAttribute("status","1");
		//sungb 2011-05-10修改
		var aUrl=obj.getAttribute("aUrl");
		var target = obj.getAttribute("target");
		if(target!=null && target!=""){
			window.open(defaultURL+aUrl,target);
		}else{
			$("sysmain").src = defaultURL+aUrl;
		}
		return;
	}
	if($("mid").value!=0&&$($("mid").value)){
		$($("mid").value).setAttribute("status","0");
		menuout($($("mid").value));
	}
	$("mid").value = obj.id;
	var icon = obj.childNodes[0];
	obj.setAttribute("status","1");
	obj.className = "on";
	var pos = icon.style.backgroundPosition.split(" ");
	var x = parseInt(pos[0]);
	var y = parseInt(pos[1]);
	icon.style.backgroundPosition = "-60px "+y+"px";
	var aUrl = obj.getAttribute("aUrl");
	if(aUrl!= null && aUrl!=""){
		var target = obj.getAttribute("target");
		if(target!=null && target!=""){
			window.open(defaultURL+aUrl,target);
		}else{
			$("sysmain").src = defaultURL+aUrl;
		}
	}
}

var isopen = false;
//弹出层
function openLayer(mybody,conId){
	if(isopen){
		closeLayer();
		return;
	}else{
		isopen = true;
	}
	//var arrayPageSize   = getPageSize();//调用getPageSize()函数
	var arrayPageScroll = getPageScroll();//调用getPageScroll()函数
	//var mybody = document.getElementById(objId);
	if (!document.getElementById("popupAddr")){
		//创建弹出内容层
		var popupDiv = document.createElement("div");
		//给这个元素设置属性与样式
		popupDiv.setAttribute("id","popupAddr")
		popupDiv.style.position = "absolute";
		popupDiv.style.border = "1px solid #ccc";
		popupDiv.style.background = "#fff";
		popupDiv.style.zIndex = 99;
		
		//实现弹出(插入到目标元素之后)
		insertAfter(popupDiv,mybody);//执行函数insertAfter()
	}
	
	//显示内容层
	var popObj=document.getElementById("popupAddr");
	popObj.innerHTML = document.getElementById(conId).innerHTML;
	popObj.style.display = "";
	
	//让弹出层在页面中垂直左右居中(个性)
	var arrayConSize=getConSize(conId);
	popObj.style.top=mybody.style.top+50;
	popObj.style.right='28px';
}

//获取内容层内容原始尺寸
function getConSize(conId){
	var conObj=document.getElementById(conId)
	conObj.style.position = "absolute";
	conObj.style.left=-1000+"px";
	conObj.style.display="";
	var arrayConSize=[conObj.offsetWidth,conObj.offsetHeight]
	conObj.style.display="none";
	return arrayConSize;
}
function insertAfter(newElement,targetElement){//插入
	var parent = targetElement.parentNode;
	if(parent.lastChild == targetElement){
		parent.appendChild(newElement);
	}else{
		parent.insertBefore(newElement,targetElement.nextSibling);
	}
}
//获取滚动条的高度
function getPageScroll(){
	var yScroll;
	if (self.pageYOffset) {
		yScroll = self.pageYOffset;
	} else if (document.documentElement  &&  document.documentElement.scrollTop){
		yScroll = document.documentElement.scrollTop;
	} else if (document.body) {
		yScroll = document.body.scrollTop;
	}
	arrayPageScroll = new Array('',yScroll)
	return arrayPageScroll;
}

//关闭弹出层
function closeLayer(){
	isopen = false;
	document.getElementById("popupAddr").style.display = "none";
	return false;
}
//对“拖动点”定义：onMousedown="StartDrag(this)" onMouseup="StopDrag(this)" onMousemove="Drag(this)"即可
var move=false,oldcolor,_X,_Y;
function StartDrags(obj){  //定义准备拖拽的函数
	obj.setCapture(); //对当前对象的鼠标动作进行跟踪
	//oldcolor=obj.style.backgroundColor;
	//obj.style.background="#999";
	move=true;
	//获取鼠标相对内容层坐标
	var parentwin=document.getElementById("popupAddr");
	_X=parentwin.offsetLeft-event.clientX
	_Y=parentwin.offsetTop-event.clientY
}
function Drags(obj){        //定义拖拽函数
	if(move){
	var parentwin=document.getElementById("popupAddr");
	parentwin.style.left=event.clientX+_X;
	parentwin.style.top=event.clientY+_Y;
	}
}
function StopDrags(obj){   //定义停止拖拽函数
	//obj.style.background=oldcolor;
	obj.releaseCapture(); //停止对当前对象的鼠标跟踪
	move=false;
} 

/************************6.0菜单调用方法***************************/
//加载一级菜单
function LoadFirstLevelMenu(){
	var aUrl = defaultURL +"/horizon/menu/loadfirstlevelmenu.jsp?menuCategory="+arguments[0];
	var source = _GetXML(aUrl);
	var _strXml = source.documentElement;
	var _xmlObj = null;
	if(_strXml == null){
		return;
	}
	if((!!window.ActiveXObject )||( "ActiveXObject" in window)){
		//IE
		
		_xmlObj=_strXml.selectSingleNode("//MenuNav");
		
	}else{	//Firefox
		_xmlObj=_strXml.firstElementChild;
	}
	var str = '';
	if(navigator.userAgent.indexOf("Chrome")>0||navigator.userAgent.indexOf("Firefox")>0){//Chrome谷歌浏览器、火狐浏览器
		firstLevelMenu_Length = _xmlObj.parentNode.childNodes.length;
		for(var k=0;k<firstLevelMenu_Length;k++){
			if(_xmlObj.parentNode.childNodes[k].nodeType == 1){
				str += recursionFirstMenu(_xmlObj.parentNode.childNodes[k],arguments[0],k);
			}
		}
	}else if(navigator.userAgent.indexOf("Safari")>0){//Safari浏览器
		firstLevelMenu_Length = _xmlObj.parentElement.childNodes.length;
		for(var k=0;k<firstLevelMenu_Length;k++){
			if(_xmlObj.parentElement.childNodes[k].nodeType == 1){
				str += recursionFirstMenu(_xmlObj.parentElement.childNodes[k],arguments[0],k);
			}
		}
	}
//	else if(navigator.userAgent.indexOf("rv:11.0")>0){//Safari浏览器
//		firstLevelMenu_Length = _xmlObj.childNodes.length;
//		for(var k=0;k<firstLevelMenu_Length;k++){
//			if(_xmlObj.parentElement.childNodes[k].nodeType == 1){
//				str += recursionFirstMenu(_xmlObj.parentElement.childNodes[k],arguments[0],k);
//			}
//    }
	
	else{
		
		firstLevelMenu_Length = _xmlObj.childNodes.length;
		
		for(var k=0;k<firstLevelMenu_Length;k++){
			if(_xmlObj.childNodes[k].nodeType == 1){
				str += recursionFirstMenu(_xmlObj.childNodes[k],arguments[0],k);
			}
		}
	}
	
	//一级菜单个数大于firstLevelMenu_showMax，需要增加向左、向右查看菜单的图标
	if(firstLevelMenu_Length > firstLevelMenu_ShowMax){
		firstLevelMenu_Pointer = firstLevelMenu_ShowMax;
		if(arguments[0]=='1'){
			str = '<li id="menuLeft" class="menuleft"><a href="#" onclick="showLeftHiddenMenu('+arguments[0]+')" title="向左"></a></li>' + str + '<li id="menuRight" class="menuright"><a href="#" onclick="showRightHiddenMenu('+arguments[0]+')" title="向右"></a></li>';
		}else{
			str = '<a href="#" id="menuLeft" class="menuleft" onclick="showLeftHiddenMenu('+arguments[0]+')" title="向左"><span style="background:url('+defaultURL+'/resource/skins'+defaultPath+'/css/img/menuleft.png) left center no-repeat"></span></a>' + str + '<a href="#" id="menuRight" class="menuright" onclick="showRightHiddenMenu('+arguments[0]+')" title="向右"><span style="background:url('+defaultURL+'/resource/skins'+defaultPath+'/css/img/menuright.png) left center no-repeat"></span></a>';
		}
	}else{
		firstLevelMenu_Pointer = firstLevelMenu_ShowMax;
		if(arguments[0]=='1'){
			str = '<li id="menuLeft" class="menuleft" style="display:none"><a href="#" onclick="showLeftHiddenMenu('+arguments[0]+')" title="向左"></a></li>' + str + '<li id="menuRight" class="menuright" style="display:none"><a href="#" onclick="showRightHiddenMenu('+arguments[0]+')" title="向右"></a></li>';
		}else{
			str = '<a href="#" id="menuLeft" class="menuleft" style="display:none" onclick="showLeftHiddenMenu('+arguments[0]+')" title="向左"><span style="background:url('+defaultURL+'/resource/skins'+defaultPath+'/css/img/menuleft.png) left center no-repeat"></span></a>' + str + '<a href="#" id="menuRight" class="menuright" style="display:none" onclick="showRightHiddenMenu('+arguments[0]+')" title="向右"><span style="background:url('+defaultURL+'/resource/skins'+defaultPath+'/css/img/menuright.png) left center no-repeat"></span></a>';
		}
	}
	//应用端：需要有首页图标
	if(arguments[0]=='1'){
		str = '<li onclick="selectLi(this)"><a class="active" href="#" onclick="toHome()" title="'+menu_lang.toHome+'">首页</a></li>' + str;
	}
	document.getElementById("navnew").innerHTML = str;
	return;
}

//一级菜单：查看左边隐藏菜单
function showLeftHiddenMenu(){
	if(firstLevelMenu_Pointer-firstLevelMenu_ShowMax > 0){
		if(arguments[0] == '1'){//应用端
			$("navnew").getElementsByTagName("li")[firstLevelMenu_Pointer+1].style.display = "none";
			$("navnew").getElementsByTagName("li")[firstLevelMenu_Pointer-firstLevelMenu_ShowMax+1].style.display = "block";
		}else{//管理端
			$("navnew").getElementsByTagName("a")[firstLevelMenu_Pointer].style.display = "none";
			$("navnew").getElementsByTagName("a")[firstLevelMenu_Pointer-firstLevelMenu_ShowMax].style.display = "block";
		}
		firstLevelMenu_Pointer = firstLevelMenu_Pointer - 1;
	}
}

//一级菜单：查看右边隐藏菜单
function showRightHiddenMenu(){
	if(firstLevelMenu_Pointer+1 <= firstLevelMenu_Length){
		firstLevelMenu_Pointer = firstLevelMenu_Pointer + 1;
		if(arguments[0] == '1'){//应用端
			$("navnew").getElementsByTagName("li")[firstLevelMenu_Pointer+1].style.display = "block";
			$("navnew").getElementsByTagName("li")[firstLevelMenu_Pointer-firstLevelMenu_ShowMax+1].style.display = "none";
		}else{//管理端
			$("navnew").getElementsByTagName("a")[firstLevelMenu_Pointer].style.display = "block";
			$("navnew").getElementsByTagName("a")[firstLevelMenu_Pointer-firstLevelMenu_ShowMax].style.display = "none";
		}
	}
}

//遍历生成一级菜单
function recursionFirstMenu(){
	var _xmlObj = arguments[0];
	var divStr = "";
	var mtype=_getAttributeValue(_xmlObj,"type");
	if(_xmlObj.childNodes.length>0 || mtype=="Group" || mtype.indexOf(".")!=-1){ //如果有子节点
		
	}else{ //没有子节点的时候
		var icon = _getAttributeValue(_xmlObj,"menuImg");
		var nodeName = _getAttributeValue(_xmlObj,"nodeName");
		if(nodeName.length>6) nodeName = nodeName.substring(0,6);
		var displayFlag = "block";
		if(arguments[2] >= firstLevelMenu_ShowMax){
			displayFlag = "none";
		}
		if(arguments[1]=='1'){//用户菜单
			divStr+='<li ismenu="1" onclick="selectLi(this)" style="display:'+displayFlag+'"><a href="#" id="'+_getAttributeValue(_xmlObj,"id")+'" defaultUrl="'+_getAttributeValue(_xmlObj,"defaultUrl")+'" ';
			tmpFun = _getAttributeValue(_xmlObj,"func");
			if(tmpFun!="" && tmpFun!="''"){
				divStr+='onclick="'+tmpFun+';ShowSecondLevelMenu(this,'+_getAttributeValue(_xmlObj,"menuCategory")+');"';
			}else{
				divStr+='onclick="ShowSecondLevelMenu(this,'+_getAttributeValue(_xmlObj,"menuCategory")+');"';
			}
			divStr+=' title="'+_getAttributeValue(_xmlObj,"menuName")+'"';
			divStr+=' targetType="'+_getAttributeValue(_xmlObj,"target")+'"';
			divStr+=' urlType="'+_getAttributeValue(_xmlObj,"urlType")+'">';
			divStr+='<span style="background:url('+defaultURL+'/resource/skins'+defaultPath+'/css/img/menu/'+icon+') left center no-repeat">';
			divStr+=nodeName+'</a></li>';
		}else if(arguments[1]=='2'){//管理菜单
			divStr+='<a style="display:'+displayFlag+'" href="#" id="'+_getAttributeValue(_xmlObj,"id")+'" defaultUrl="'+_getAttributeValue(_xmlObj,"defaultUrl")+'" ';
			tmpFun = _getAttributeValue(_xmlObj,"func");
			if(tmpFun!="" && tmpFun!="''"){
				divStr+='onclick="'+tmpFun+';ShowSecondLevelMenu(this,'+_getAttributeValue(_xmlObj,"menuCategory")+');"';
			}else{
				divStr+='onclick="ShowSecondLevelMenu(this,'+_getAttributeValue(_xmlObj,"menuCategory")+');"';
			}
			divStr+=' title="'+_getAttributeValue(_xmlObj,"menuName")+'"';
			divStr+=' targetType="'+_getAttributeValue(_xmlObj,"target")+'"';
			divStr+=' urlType="'+_getAttributeValue(_xmlObj,"urlType")+'">';
			divStr+='<span style="background:url('+defaultURL+'/resource/skins'+defaultPath+'/css/img/menu/'+icon+') left center no-repeat">';
			divStr+=nodeName+'</span></a>';
		}
	}
	return divStr;
}

//点击一级菜单后，显示workview.jsp页面（左侧为nav，右侧为content）;如果打开新窗口则调用workview.newwindow.jsp页面
function ShowSecondLevelMenu(obj,menuCategory){
	var title =jq(obj).attr("title");
    if(title =="云门户"){
    	window.open("http://sso.portal.unicom.local/eip_sso/ssoLogin.html?appid=np000&amp;success=http%3A%2F%2Fwww.portal.unicom.local%2Fuser%2Ftoken&amp;error=http%3A%2F%2Fwww.portal.unicom.local%2Fuser%2Ferror&amp;return=http%3A%2F%2Fwww.portal.unicom.local%2Fdefault%2Fhome")
    
    }
    if(title =="云门户"){
         return ;
    }
	if(document.getElementById("weizhi")){
		jq('#weizhi').val("");
		var menuName =jq(obj).attr("title")+"->";
		
		jq('#weizhi').val("首页 ->"+menuName);
	}
	
	if(!obj) return;
	if(menuCategory == "1"){//用户菜单
		obj.className="selected";
	}else if(menuCategory == "2"){//管理菜单
		obj.className="active";
	}
	if($("menuid").value!=""){
		$($("menuid").value).className="";
	}
	$("menuid").value = obj.id;
	$("layout_menu_view").style.overflowY = "hidden";
	var urlType = obj.getAttribute("urlType");
	var defaultUrl = obj.getAttribute("defaultUrl");
	var targetType = obj.getAttribute("targetType");
	
	if(urlType=="URL" && defaultUrl!=null && defaultUrl!=""){
		if(targetType == "blank"){
			window.open(defaultUrl,'','width=1024,height=768,top=0,left=0,status=yes,titlebar=no,toolbar=no,scrollbars=yes,resizable=yes');
		}else{
			$("sysmain").src = defaultUrl;
		}
	}else if(urlType=="XML" && defaultUrl!= null && defaultUrl!=""){
		if(targetType == "blank"){
			var url = defaultURL+"/horizon/formview/view/workview.newwindow.jsp?xmlclass="+defaultUrl;
			window.open(url,'','width=1024,height=768,top=0,left=0,status=yes,titlebar=no,toolbar=no,scrollbars=yes,resizable=yes');
		}else{
			var url = defaultURL+"/horizon/formview/view/workview.jsp?xmlclass="+defaultUrl;
			$("sysmain").src = url;
		}
	}else{
		if(targetType == "blank"){
			var url = defaultURL+"/horizon/formview/view/workview.newwindow.jsp?flag=1&menuidLevel1="+$("menuid").value+"&menuCategory="+menuCategory;//点击一级菜单打开新窗口
			window.open(url,'','width='+(screen.availWidth-20)+',height='+(screen.availHeight-65)+',top=0,left=0,status=yes,titlebar=no,toolbar=no,scrollbars=yes,resizable=yes');
			$("sysmain").src = defaultURL+"/horizon/layout/layout.doGetUserLayoutContent.do?userId=public&flag=view";
		}else{
			var yuanUrl=document.URL;
			if(yuanUrl.indexOf("default/operator")!=-1){
				var url = defaultURL+"/horizon/formview/view/workview.jsp?menuidLevel1="+$("menuid").value+"&menuCategory="+menuCategory;
			}else{
			    var url = defaultURL+"/horizon/formview/view/workview2.jsp?menuidLevel1="+$("menuid").value+"&menuCategory="+menuCategory;
			}
			$("sysmain").src = url;
		}
	}
}

//workview.jsp页面中，加载二级菜单
function LoadSecondLevelMenu(menuid,menuCategory){
	
	if($("menu"+menuid)){
		$("submenu").innerHTML=$("menu"+menuid).innerHTML;
		
		if($("submenu").innerHTML!=""){
			$("submenu").style.display="block";
		}else{
			$("submenu").style.display="none";
		}
	}else{
		aUrl = defaultURL +"/horizon/menu/loadsecondlevelmenu.jsp?menuid="+menuid+"&menuCategory="+menuCategory;
		var source = _GetXML(aUrl);
		var _strXml = source.documentElement;
		var _xmlObj = null;
		try{
			if((!!window.ActiveXObject )||( "ActiveXObject" in window)){	//IE
				_xmlObj=_strXml.selectSingleNode("//MenuNav");
			}else{	//Firefox
				_xmlObj=_strXml.firstElementChild;
			}
		}catch(e){//防止SESSION失效，点击按钮报错
			toHome();
			return;
		}
		var str = '';
		if(navigator.userAgent.indexOf("Chrome")>0||navigator.userAgent.indexOf("Firefox")>0){//Chrome谷歌浏览器、火狐浏览器
			for(var k=0;k<_xmlObj.parentNode.childNodes.length;k++){
				if(_xmlObj.parentNode.childNodes[k].nodeType == 1){
					str += recursionSecondMenu(_xmlObj.parentNode.childNodes[k],_xmlObj.parentNode.childNodes.length);
				}
			}
		}else if(navigator.userAgent.indexOf("Safari")>0){//Safari浏览器
			for(var k=0;k<_xmlObj.parentElement.childNodes.length;k++){
				if(_xmlObj.parentElement.childNodes[k].nodeType == 1){
					str += recursionSecondMenu(_xmlObj.parentElement.childNodes[k],_xmlObj.parentElement.childNodes.length);
				}
			}
		}else{
			for(var k=0;k<_xmlObj.childNodes.length;k++){
				if(_xmlObj.childNodes[k].nodeType == 1){
					str += recursionSecondMenu(_xmlObj.childNodes[k],_xmlObj.childNodes.length);
				}
			}
		}
		
		document.getElementById("submenu").innerHTML = str;
		var tmpdiv = document.createElement("div");
		tmpdiv.id = "menu"+menuid;
		tmpdiv.innerHTML = $("submenu").innerHTML;
		$("hiddenDiv").appendChild(tmpdiv);
		if($("submenu").innerHTML!=""){
			$("submenu").style.display="block";
		}else{
			$("submenu").style.display="none";
		}
	}
};

//
function LoadSeccendLiMenu(){
	
	var abiaoqians = jq("#navnew").find("a");
	abiaoqians.each(function(){
		var biaoqianID=jq(this).attr("id")+""
		if(biaoqianID !="undefined"){
		    //在这里添加下拉二级

			aUrl = defaultURL +"/horizon/menu/loadsecondlevelmenu.jsp?menuid="+biaoqianID+"&menuCategory=1";
			var source = _GetXML(aUrl);
			var _strXml = source.documentElement;
			var _xmlObj = null;
			try{
				if((!!window.ActiveXObject )||( "ActiveXObject" in window)){	//IE
					_xmlObj=_strXml.selectSingleNode("//MenuNav");
				}else{	//Firefox
					_xmlObj=_strXml.firstElementChild;
				}
			}catch(e){//防止SESSION失效，点击按钮报错
				toHome();
				return;
			}
			var str = '';
			if(navigator.userAgent.indexOf("Chrome")>0||navigator.userAgent.indexOf("Firefox")>0){//Chrome谷歌浏览器、火狐浏览器
				if(_xmlObj.parentNode.childNodes.length>0){
					str += recursionSecondMenuLi(_xmlObj.parentNode.childNodes[0],_xmlObj.parentNode.childNodes.length);
				}
			}else if(navigator.userAgent.indexOf("Safari")>0){//Safari浏览器
				if(_xmlObj.parentNode.childNodes.length>0){
					str += recursionSecondMenuLi(_xmlObj.parentNode.childNodes[0],_xmlObj.parentNode.childNodes.length);
				}
			}else{
				
				if(_xmlObj.parentNode.childNodes.length>0){
					str += recursionSecondMenuLi(_xmlObj.childNodes[0],_xmlObj.parentNode.childNodes.length);
				}
			}
		
	        if(str !=""){
	        	var tittleUl= jq("#"+biaoqianID).attr("title");
			jq("#"+biaoqianID).after("<ul class='s_menu' name="+tittleUl+">"+str+"</ul>");
			
	        }
		}
	  });

	
	
}

//新版遍历二级菜单下拉版
function recursionSecondMenuLi(){
	var _xmlObj = arguments[0];
	aUrl =_getAttributeValue(_xmlObj,"defaultUrl");
	//alert(aUrl);
	var divStr = "";
	var mtype=_getAttributeValue(_xmlObj,"type");
	//alert(mtype);
	//alert(_xmlObj.childNodes.length+"||"+ mtype+"||"+mtype.indexOf("."));
	if(_xmlObj.childNodes.length>0 || mtype=="Group" || mtype.indexOf(".")!=-1){ //如果有子节点
		
        var secondmenuid = aUrl.substring(aUrl.indexOf("=")+1,aUrl.indexOf("&"));
		//alert(secondmenuid);
		divStr = LoadThirdLevelMenuLi(secondmenuid,"");
		
	}else{ //没有子节点的时候
		var secondmenuid = aUrl.substring(aUrl.indexOf("=")+1,aUrl.indexOf("&"));
		//alert("wu")
		divStr = LoadThirdLevelMenuLi(secondmenuid,"");
		
		
	}
	return divStr;
	
   }
function checkUserRole2(){
	//校验当前人对菜单是否有权限查看
	
	return true;
}

function _getAttributeValue2(){
	var v = "";
	try{
		v = arguments[0].attributes.getNamedItem(arguments[1]).value;
	}catch(E){}
		
	return (v==null|| v=="" || v=="null")?"":v;
}
function LoadThirdLevelMenuLi(){
	  var _xmlObj;
	  aUrl = defaultURL +"/horizon/formview/view/loadthirdlevelmenu.jsp?menuid="+arguments[0]+"&xmlclass="+arguments[1];
		var source = _GetXML(aUrl);
		//alert(source);
	    _strXml = source.documentElement;
	    //alert(_strXml);
	    if(_strXml==null || _strXml.xml==""){
			//alert(jsview_lang.alertNavigatorLoadFailed); 于芳屏蔽
			return false;
			}
			var _xmlObj = null;
			divID = 0;
			var divStr ="";
			var isIE = ((!!window.ActiveXObject )||( "ActiveXObject" in window));
			if(isIE){//IE
				//alert(_strXml);
            	_xmlObj=_strXml.selectSingleNode("//MenuNav");
            	
            	aa =_xmlObj.childNodes[0];
            	cc=aa.childNodes[0];
            	sun_xmlObjchilds=cc.childNodes;

		        for ( var int = 0; int < sun_xmlObjchilds.length; int++) {
					
				var mtype = _getAttributeValue(sun_xmlObjchilds[int],"type");
				var def = _getAttributeValue(sun_xmlObjchilds[int],"def");
				//增加权限判断
				if(!checkUserRole2(_getAttributeValue2(sun_xmlObjchilds[int],"deptid"))) return ;//当权限不符时停止
				
				
				var link =_getAttributeValue(sun_xmlObjchilds[int],"link");
				if(link ==""){//如果此层有东西，这此层的下第一个元素链接给他
					//alert(_xmlObj.childNodes.length);
					link =_getAttributeValue(sun_xmlObjchilds[int].childNodes[0],"link");
					
				}
				
                
				divStr +="<li ><a href='#' onclick='gotoViewheh(this)' menuid='"+_getAttributeValue(sun_xmlObjchilds[int],"id")+"' linkid ='"+link+"' attle ='"+_getAttributeValue(sun_xmlObjchilds[int],"name")+"'>"
				divStr += _getAttributeValue(sun_xmlObjchilds[int],"name")+"</a>";
				divStr += "</li>"
				
		        }
            	
            	
				
			}else{	//Firefox
				_xmlObj=_strXml.firstElementChild;
					var _xmlObjchilds =_xmlObj.childNodes;
			//alert(_xmlObjchilds);
			var jutiweizhi ="";
			
			for(var k=1;k<_xmlObjchilds.length;k++){
				
				if(_xmlObjchilds[k].nodeType == 1){
			
			       var sun_xmlObjchilds =_xmlObjchilds[k].childNodes;
			       
			       
			        for ( var int = 0; int < sun_xmlObjchilds.length; int++) {
						
					var mtype = _getAttributeValue(sun_xmlObjchilds[int],"type");
					var def = _getAttributeValue(sun_xmlObjchilds[int],"def");
					//增加权限判断
					if(!checkUserRole2(_getAttributeValue2(sun_xmlObjchilds[int],"deptid"))) return ;//当权限不符时停止
					
					
					var link =_getAttributeValue(sun_xmlObjchilds[int],"link");
					if(link ==""){//如果此层有东西，这此层的下第一个元素链接给他
						//alert(_xmlObj.childNodes.length);
						link =_getAttributeValue(sun_xmlObjchilds[int].childNodes[0],"link");
						
					}
					
	                
					divStr +="<li ><a href='#' onclick='gotoViewheh(this)' menuid='"+_getAttributeValue(sun_xmlObjchilds[int],"id")+"' linkid ='"+link+"' attle ='"+_getAttributeValue(sun_xmlObjchilds[int],"name")+"'>"
					divStr += _getAttributeValue(sun_xmlObjchilds[int],"name")+"</a>";
					divStr += "</li>"
					
			        }
			        
			        
				
				}
				
				
			}
			}
		    //alert(_xmlObj);
		    //alert(_xmlObj.childNodes);
		
			return divStr;

	}
 
//跳转view页面
function gotoViewheh(obj){
	
	 var viewid = jq(obj).attr("linkid");
	 var menuid = jq(obj).attr("menuid");
	 var menuname = jq(obj).attr("attle");
	 
	var tittleMenu= jq(obj).parent().parent('.s_menu'); 
	var tittleStr =jq(tittleMenu).attr("name");
	//alert("asdd");
	 //var weizhi =jq(parent.document.getElementById("weizhi")).val();
	 
	 //weizhi +=menuname;
	 var indexOn =jq(obj).attr("indexOn")+"";
	 
	 jq(parent.document.getElementById("weizhi")).val("首页 ->"+tittleStr+"->");
	// stopPropagation(obj);
	 if(viewid.indexOf("getSaveKey.jsp")!=-1){
		 window.open(viewid);
		 return ;
	 }
	 if(viewid.indexOf("getSaveKeykufuxitong.jsp")!=-1){
		 window.open(viewid);
		 return ;
	 }
	 if(viewid.indexOf("/wo/cooperManager/query")!=-1){
		 window.open("http://122.96.25.242:7001/WoCommunity/wo/cooperManager/query");

		 return ;
	 }
	 if(viewid.indexOf("OA")!=-1){
		 $("sysmain").src=viewid;
	 }else if (viewid.indexOf("http")!=-1){
			window.open(viewid);
	 }else{
		 $("sysmain").src=defaultURL+"/horizon/formview/view/viewtemplate/view.template.normalNew.jsp?viewid="+viewid+"&menuid="+menuid+"&indexOn="+indexOn+"&attle="+menuname;
	 }
	 return ;
}

function LoadSecondLevelMenuNew(menuid,menuCategory){
	
	if($("menu"+menuid)){
		$("submenu").innerHTML=$("menu"+menuid).innerHTML;
		
		if($("submenu").innerHTML!=""){
			$("submenu").style.display="block";
		}else{
			$("submenu").style.display="none";
		}
	}else{
		aUrl = defaultURL +"/horizon/menu/loadsecondlevelmenu.jsp?menuid="+menuid+"&menuCategory="+menuCategory;
		var source = _GetXML(aUrl);
		var _strXml = source.documentElement;
		var _xmlObj = null;
		try{
			if((!!window.ActiveXObject )||( "ActiveXObject" in window)){
				//IE
				
				_xmlObj=_strXml.selectSingleNode("//MenuNav");
				
			}else{	//Firefox
				_xmlObj=_strXml.firstElementChild;
			}
		}catch(e){//防止SESSION失效，点击按钮报错
			toHome();
			return;
		}
		var str = '';
		if(navigator.userAgent.indexOf("Chrome")>0||navigator.userAgent.indexOf("Firefox")>0){//Chrome谷歌浏览器、火狐浏览器
			for(var k=0;k<_xmlObj.parentNode.childNodes.length;k++){
				if(_xmlObj.parentNode.childNodes[k].nodeType == 1){
					str += recursionSecondMenuNew(_xmlObj.parentNode.childNodes[k],_xmlObj.parentNode.childNodes.length);
				}
			}
		}else if(navigator.userAgent.indexOf("Safari")>0){//Safari浏览器
			for(var k=0;k<_xmlObj.parentElement.childNodes.length;k++){
				if(_xmlObj.parentElement.childNodes[k].nodeType == 1){
					str += recursionSecondMenuNew(_xmlObj.parentElement.childNodes[k],_xmlObj.parentElement.childNodes.length);
				}
			}
		}
//		else if(navigator.userAgent.indexOf("rv:11.0")>0){//Safari浏览器
//			alert("ie11");
//			firstLevelMenu_Length = _xmlObj.parentElement.childNodes.length;
//			for(var k=0;k<firstLevelMenu_Length;k++){
//				if(_xmlObj.parentElement.childNodes[k].nodeType == 1){
//					str += recursionFirstMenu(_xmlObj.parentElement.childNodes[k],arguments[0],k);
//				}
//	    }
//
//		}
		else{
			for(var k=0;k<_xmlObj.childNodes.length;k++){
				if(_xmlObj.childNodes[k].nodeType == 1){
					str += recursionSecondMenuNew(_xmlObj.childNodes[k],_xmlObj.childNodes.length);
				}
			}
		}
		
		document.getElementById("submenu").innerHTML = str;
		var tmpdiv = document.createElement("div");
		tmpdiv.id = "menu"+menuid;
		tmpdiv.innerHTML = $("submenu").innerHTML;
		$("hiddenDiv").appendChild(tmpdiv);
		if($("submenu").innerHTML!=""){
			$("submenu").style.display="block";
		}else{
			$("submenu").style.display="none";
		}
	}
	
}

//新版遍历生成二级菜单
function recursionSecondMenuNew(){
	var _xmlObj = arguments[0];
	var divStr = "";
	var mtype=_getAttributeValue(_xmlObj,"type");
	if(_xmlObj.childNodes.length>0 || mtype=="Group" || mtype.indexOf(".")!=-1){ //如果有子节点
		
	}else{ //没有子节点的时候
		divStr+='<div><h2 id="'+_getAttributeValue(_xmlObj,"id")+'" ';
		tmpFun = _getAttributeValue(_xmlObj,"func");
		if(tmpFun!="" && tmpFun!="''"){
			divStr+='onclick="'+tmpFun+';SecondLevelMenuClickNew(this)"';
		}else{
			divStr+='onclick="SecondLevelMenuClickNew(this)" ';
		}
		divStr+='target="'+_getAttributeValue(_xmlObj,"target")+'" aUrl="'+_getAttributeValue(_xmlObj,"defaultUrl")+'"';
		divStr+=' urlType="'+_getAttributeValue(_xmlObj,"urlType")+'">';
		divStr+=_getAttributeValue(_xmlObj,"menuName")+'<span></span></h2>';
		divStr+='<div class="con" style="overflow:auto;display:none"></div></div>';
	}

	return divStr;
}

//遍历生成二级菜单
function recursionSecondMenu(){
	var _xmlObj = arguments[0];
	var divStr = "";
	var mtype=_getAttributeValue(_xmlObj,"type");
	if(_xmlObj.childNodes.length>0 || mtype=="Group" || mtype.indexOf(".")!=-1){ //如果有子节点
		
	}else{ //没有子节点的时候
		divStr+='<div><h2 id="'+_getAttributeValue(_xmlObj,"id")+'" ';
		tmpFun = _getAttributeValue(_xmlObj,"func");
		if(tmpFun!="" && tmpFun!="''"){
			divStr+='onclick="'+tmpFun+';SecondLevelMenuClick(this)"';
		}else{
			divStr+='onclick="SecondLevelMenuClick(this)" ';
		}
		divStr+='target="'+_getAttributeValue(_xmlObj,"target")+'" aUrl="'+_getAttributeValue(_xmlObj,"defaultUrl")+'"';
		divStr+=' urlType="'+_getAttributeValue(_xmlObj,"urlType")+'">';
		divStr+=_getAttributeValue(_xmlObj,"menuName")+'<span></span></h2>';
		divStr+='<div class="con" style="overflow:auto;display:none"></div></div>';
	}

	return divStr;
}

//打开默认菜单：一级菜单
function _openDefaultNavLevel1(openmenuidlevel1){
	var arr = $("navnew").getElementsByTagName("a");
	if(!arr) return;
	for(var i=0;i<arr.length;i++){
		var obj = arr[i];
		if(obj.getAttribute("id") == openmenuidlevel1){
			if(document.all){
				obj.click();
			}else{
				var evt = document.createEvent("MouseEvents");
				evt.initEvent("click",true,true);
				obj.dispatchEvent(evt);
			}
			break;
		}
	}
}

//打开默认菜单：二级菜单
function _openDefaultNavLevel2(openmenuidlevel2){
	var arr = $("submenu").getElementsByTagName("h2");
	if(!arr) return;
	for(var i=0;i<arr.length;i++){
		var obj = arr[i];
		if(obj.getAttribute("id") == openmenuidlevel2){
			if(document.all){
				obj.click();
			}else{
				var evt = document.createEvent("MouseEvents");
				evt.initEvent("click",true,true);
				obj.dispatchEvent(evt);
			}
			return;
		}
	}
	
	//如果不能打开默认菜单，则打开首个二级菜单
	if(document.all){
		arr[0].click();
	}else{
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click",true,true);
		arr[0].dispatchEvent(evt);
	}
}

//选择横向导航栏后，标识选中的模块名称
function selectLi(obj){
	
	var  lis =jq("#navnew li");
	jq(lis).each(function(){
	   jq(this).children(":first").removeClass("active");
	  });
	
	jq(obj).children(":first").addClass("active");
	
}


