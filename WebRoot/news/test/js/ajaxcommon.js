	/*
功能：Trim
创建：李勇20080310
*/
String.prototype._Trim = function() {	
	return String(this)._LTrim()._RTrim();
}
String.prototype._LTrim = function() {	
	var strTmp=String(this);		//0A 0D
	while(escape(strTmp.substring(0,1))=="%A0" || escape(strTmp.substring(0,1))=="%20" 
			||escape(strTmp.substring(0,1))=="%0A" || escape(strTmp.substring(0,1))=="%0D" 
	 ){
		strTmp=strTmp.substring(1);
	}	
	return strTmp;
}
String.prototype._RTrim = function() {	
	var strTmp=String(this);
	while(escape(strTmp.substring(strTmp.length-1,strTmp.length))=="%A0" || escape(strTmp.substring(strTmp.length-1,strTmp.length))=="%20"){
		strTmp=strTmp.substring(0,strTmp.length-1);
	}	
	return strTmp;
}
//=============================================	
	function lib_bwcheck(){ //Browsercheck (needed)
	this.ver=navigator.appVersion
	this.agent=navigator.userAgent
	this.dom=document.getElementById?1:0
	this.opera5=this.agent.indexOf("Opera 5")>-1
	this.ie5=(this.ver.indexOf("MSIE 5")>-1 && this.dom && !this.opera5)?1:0; 
	this.ie6=(this.ver.indexOf("MSIE 6")>-1 && this.dom && !this.opera5)?1:0;
	this.ie4=(document.all && !this.dom && !this.opera5)?1:0;
	this.ie=this.ie4||this.ie5||this.ie6
	this.mac=this.agent.indexOf("Mac")>-1
	this.ns6=(this.dom && parseInt(this.ver) >= 5) ?1:0; 
	this.ns4=(document.layers && !this.dom)?1:0;
	this.bw=(this.ie6 || this.ie5 || this.ie4 || this.ns4 || this.ns6 || this.opera5)
	return this
}

//====================================================	
	//获取body上的对象
	function $(){
		var obj = document;
		if(arguments.length>1){
			var obj = arguments[1]
		}
		var bObj = obj.getElementById(arguments[0]);
		if(bObj){
			if(bObj.tagName == "INPUT"){ // && (bObj.type.toUpperCase()=="RADIO" || bObj.type.toUpperCase()=="CHECKBOX")
				var tmpObj = obj.getElementsByName(arguments[0]);
				if(tmpObj.length == 1){
					 bObj = tmpObj[0];
				}else if(tmpObj.length >1){
					bObj = tmpObj;
				}
			}
		}
		else{
			bObj = obj.getElementsByName(arguments[0]);
			if(bObj.length==0) return null;
			if(bObj.length==1) bObj = bObj[0];
		}
		
		return bObj;
	}
	/*获取应用路径*/
	function getRootPath(){
		var p = self.location+"";	
		var pp = p.split("/");	
		return (pp[3]);
	}
	function getbasepath(){
		var basepath;
				var p = self.location+"";	
				var pp = p.split("/");	
				basepath='http://'+pp[2]+"/"+pp[3];
		return basepath;
	}
	//获取游览器的类型,为解决onreadystatechange不兼容的问题 
//详细出处参考：http://www.jb51.net/article/21410.htm
function getOs() 
{ 
	var OsObject = ""; 
	if(navigator.userAgent.indexOf("MSIE")>0) { 
		return "MSIE"; //IE浏览器 
	} 
	if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ 
		return "Firefox"; //Firefox浏览器 
	} 
	if(isSafari=navigator.userAgent.indexOf("Safari")>0) { 
		return "Safari"; //Safan浏览器 
	} 
	if(isCamino=navigator.userAgent.indexOf("Camino")>0){ 
		return "Camino"; //Camino浏览器 
	} 
	if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){ 
		return "Gecko"; //Gecko浏览器 
	} 
} 

/**
功能：前台读取后台数据,简单封装,支持多个调用，同步或者异步
创建：李勇
日期：20061205
修改历史：
修改日期：20080310
修改内容：解决同一页面上不同的AjaxClass对象名称不能相同的问题，改为匿名的方式
修改日期:  20081118
修改内容:  增加从表单获取所有域值的功能,
使用范例说明：
function Test(){			
	var iIntervalID = window.setInterval("changevalue()",1000);
	//采用匿名的方式调用
	new AjaxClass("Get",url,parastr,null,function (obj){doWithResultTest1(obj);}	//结果返回后的处理函数名称,需要自行编写
				,iIntervalID			//计时器，如果有的话
	).StartXMLHttp();
}
function doWithResultTest1(obj){
	strBack=(obj.xmlHttp.responseXML.documentElement.firstChild.text);
	alert(strBack);
}
**/
function  AjaxClass (model,url,parastr,formObj,funName,errFunName,iIntervalID,async){
	this.model 		= model;					//请求方法，GET 或 POST
	this.url 				= url;							//要请求的Url
	this.parastr		= parastr;					//要发送的内容
	this.formObj	  	=formObj;					//指定表单(发送此表单的所有内容)
	this.doWithResult	= funName;			//返回处理
	this.errorAction		= errFunName;
	this.iIntervalID	= iIntervalID;				//计时器
	if(async==undefined || async==null){
		this.async = true;
	}
	else 	this.async = async;
	var obj = this ;
	//创建对象，并返回对象
//*
	this.xmlHttp = xmlhttp();
//*/	
/*	
	if(window.ActiveXObject){
		this.xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if(window.XMLHttpRequest){
		this.xmlHttp=new XMLHttpRequest();
	}	
//*/	
	//入口函数
	this.StartXMLHttp = function (){
		this.GetXMLHttpRequest();
	}
	//跟后台数据交互
	this.GetXMLHttpRequest = function (){			
		//var btype=getOs(); 
		this.xmlHttp.onreadystatechange =this.handleAddStateChange; // 	this.funName;
		if(typeof(this.model)=="undefined") this.model=(typeof(this.parastr) == "undefined"?"Get": "Post");	
		this.xmlHttp.open(this.model,this.url,this.async);
		if(this.model == "Post" ){
			this.xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		}
		var content = formObj!=null && typeof(formObj) == "object"  ? this.formToStr(formObj):"";
		content = content==""?(this.parastr==""? null : (this.parastr)):content;
		this.xmlHttp.send(content); 
		//为兼容firefox
		//this.xmlHttp.onreadystatechange =(btype=="Firefox")?this.handleAddStateChange():this.handleAddStateChange; // 	this.funName;
	}
	this.handleAddStateChange = function (){
		
		if(obj.xmlHttp.readyState == 4){
			if(obj.xmlHttp.status == 200){	
				window.clearInterval(obj.iIntervalID);		
				obj.doWithResult(obj);
				//window.status +="OK";
			}
			else{
				obj.errorAction();
				window.clearInterval(obj.iIntervalID);	
				//alert("服务器响应失败");
			}
		}
		else{
			//window.status +="..2..";
		}
	}
		/**
	 * 将表单对象转换化UrlEncode的字符串
	 * @author	SurfChen <surfchen@gmail.com>
	 * @link	http://www.surfchen.org/
	 * @param	{Object} formObject
	 * @returns {String} 表单字符串
	 * @see		AJAXRequest#postf
	 * @ignore
	 */
	this.formToStr=function(fc) {
		var i,qs="",and="",ev="";
		for(i=0;i<fc.length;i++) {
				e=fc[i];
				if (e.name!='') {
					if(e.type == "button" || e.type=="file"){
						continue;	
					}
					else if (e.type=='select-one'&&e.selectedIndex>-1) ev=e.options[e.selectedIndex].value;
					else if (e.type=='checkbox' || e.type=='radio') {
						if (e.checked==false) continue;
						ev=e.value;
					}
					else ev=e.value;
					//ev = ev.indexOf("; "
					//alert(ev);
					ev=encode(ev); 
					qs+=and+e.name+'='+ev; and="&";
				}
			}
			return qs;
	}
}

function xmlhttp(){
	var i,x=["Msxml5.XMLHTTP","Msxml4.XMLHTTP","Msxml3.XMLHTTP","Msxml2.XMLHTTP","Microsoft.XMLHTTP"];
	for(i=0;i<x.length;i++){try{return new ActiveXObject(x[i]);}catch(e){}};
	return new XMLHttpRequest();
}
//function xmlhttp(){
	
//	var  XMLHttpReq ="";
//	 try {  
//	        XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");//IE高版本创建XMLHTTP  
//	    }  
//	    catch(E) {  
//	        try {  
//	            XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");//IE低版本创建XMLHTTP  
//	        }  
//	        catch(E) {  
//	            XMLHttpReq = new XMLHttpRequest();//兼容非IE浏览器，直接创建XMLHTTP对象  
//	        }  
//	    } 
//	    return XMLHttpReq;
//}

//使用代理的方式后台取值，前台返回
function _GetXMLBack(url){			
	var backstr="";
	var source=_GetXML((url)); 
	if(navigator.userAgent.indexOf("MSIE")>0){
		backstr=source.documentElement.firstChild.text;
	}else{
		backstr = source.firstChild.textContent.trim();
	}
	return backstr;
}
function _GetXML(url){
var xmlDoc = xmlhttp();
if(xmlDoc.overrideMimeType){ 
	xmlDoc.overrideMimeType("text/xml"); 
} 
xmlDoc.open("GET",_TransformCgiURL(url),false); 
xmlDoc.send(null); 
var xmlDoc1 = xmlDoc.responseXML; 
return xmlDoc1;

}
//function _GetXML(url){
//	//if(navigator.userAgent.indexOf("MSIE")<0){
//		var xmlDoc = xmlhttp();
//		
//		//alert("asdd");
//		if(xmlDoc.overrideMimeType){ 
//			//alert("sdsadsas");
//			xmlDoc.overrideMimeType("text/xml"); 
//		} 
//		//xmlDoc.overrideMimeType("text/xml"); 
//		xmlDoc.open("GET",_TransformCgiURL(url),false); 
//		xmlDoc.send(null); 
//		if(navigator.userAgent.indexOf("rv:11.0")>0){
//			
//			//alert("asdd");
//			return xmlDoc.responseText;
//		}
//		if(window.ActiveXObject){
//		
//			 var mydata = new ActiveXObject("Microsoft.XMLDOM");
//			 //alert("asddddddddd");
//             mydata.async = false;
//             var txt =xmlDoc.responseText;
//            
//             var cc =mydata.loadXML(xmlDoc.responseText);
//             //alert(cc);
//             //alert(mydata);
//             return mydata;
//		}else{
////			var xmlDoc1 = xmlDoc.responseXML;
//			var txt =xmlDoc.responseText;
//			//alert(txt);
//			var parser = new DOMParser();
//			var xmlDoc1 = parser.parseFromString(xmlDoc.responseText, "text/xml");
//			//alert(xmlDoc1);
//			//var a=jQuery(xmlDoc1).find('Menu');
//			//alert(a.length);
//			
//			return xmlDoc1;
//		}
//		
//		 
//		
//		
//	/*}else{
//		if(window.ActiveXObject){// IE
//			source = new ActiveXObject("Microsoft.XMLDOM");
//		}else{// Firefox  modified by wangmh 2010-1-12
//			source = document.implementation.createDocument("","",null);
//		}	
//		source.async = false;
//		source.validateOnParse = false;
//		source.resolveExternals = false;
//		source.preserveWhiteSpace = false;
//		source.load(_TransformCgiURL(url));
//		return source;		
//	}*/
//}

function _GetURLText(surl)
{  
	try{
		objHTTP = new ActiveXObject("Microsoft.XMLHTTP");
		objHTTP.open("GET", surl, false, "", "");
		objHTTP.setRequestHeader("If-Modified-Since","0");
		objHTTP.setRequestHeader( "connectionTimeout" , "15000" ) ;   //调用不能超时15秒
		objHTTP.send(false);
		 resp = objHTTP.responseXML;
	  }catch(e){resp=null;}
  return resp;
}

function _TransformCgiURL(url){
	var strUNC="";
	for(i=0;i<=url.length-1;i++)
	   //如果字符串中包含数字，则转换为UNICODE
	   if (/[^\x00-\xff]/g.test(url.substring(i,i+1))) 
	      strUNC+=escape(url.substring(i,i+1))
	else strUNC+=url.substring(i,i+1)
	return strUNC;
}
/**
编码转换
*/
var encode=$GEC("UTF-8");
function $GEC(cs){
		if(cs.toUpperCase()=="UTF-8") return(encodeURIComponent);
		else return($SRP(escape,[/\+/g],["%2B"]));
}
function $SRP(f,r,p){return(function(s){s=f(s);for(var i=0;i<r.length;i++) s=s.replace(r[i],p[i]);return(s);});}
/**
用于解析Java中StringUtil.encode字符串的还原
*/
function uncodeJava(cs){
	cs = cs.replace(/&nbsp;/g," ");//空格的显示处理
	return cs;
}
//获取单选框的值
function _GetRadioValue(obj){
	if(typeof(obj.length)=="undefined"){
		if(obj.checked) return obj.value;
	}
	else{
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked){
				return obj[i].value;
			}
		}
	}
	return "";
}

	
//设置单选框的值
function _SetRadioValue(){
	var obj = arguments[0];
	if(typeof(obj.length)=="undefined"){
		if(obj.value == arguments[1])		obj.checked = true;
	}
	else{
		for(var i=0;i<obj.length;i++){
			if(arguments[1].indexOf(obj[i].value)>-1){
				return obj[i].checked = true; return;
			}
		}
	}
}

/*
功能：得到CheckBox类型域值,按para分隔默认为逗号
*/
function _GetCheckBoxValue(obj,para){
	var backstr="";
	var splitflag=",";	//默认按照逗号分隔
	if(obj == null ) return "";	//防止不存在时出现错误
	if(typeof(para)!="undefined" && para!=""){
		splitflag=para;
	}
	if(typeof(obj.length)=="undefined"){
		if(obj.checked){	return obj.value;}
		else{ return "";}
	}
	else{
		for(var i=0;i<obj.length;i++){
			if(obj[i].checked){
				backstr+=obj[i].value+splitflag;
			}
		}
		return backstr.substring(0,backstr.length-1);
	}
}
//设置多选框
function _SetCheckBoxValue(){
	var obj = arguments[0];
	var allValue = ";"+arguments[1]+";";
	if(typeof(obj.length)=="undefined"){
		if(obj.value == arguments[1])		obj.checked = true;
		else obj.checked = false;
	}
	else{
		for(var i=0;i<obj.length;i++){
			obj[i].checked = false;
		}
		for(var i=0;i<obj.length;i++){
			if(allValue.indexOf(";"+obj[i].value+";")>-1){
				obj[i].checked = true;
			}
		}
	}
}

function _SetListValue(){
	var obj = arguments[0];
	var allValue = ";"+arguments[1]+";";
	if(obj.multiple){
		for(var i=0,n=obj.length;i<n;i++){
			if(allValue.indexOf(";"+obj.options[i].value+";")>-1) obj.options[i].selected = true;
		}
	}else{
		for(var i=0,n=obj.length;i<n;i++){
			if(arguments[1]== obj.options[i].value){
				 obj.options[i].selected = true;return;
			}
		}
	}
}

/**
	*说明:获取下拉框中已经选中的value值
	*参数:下拉框对象
	*返回:value值使用分号连接
	*/
function _GetListValue(){
	var obj = arguments[0];
	if(obj.multiple){
		var tmpValue="";
		for(var i=0,n=obj.length;i<n;i++){
			if(obj.options[i].selected) tmpValue +=";"+obj.options[i].value;
		}
		tmpValue = tmpValue.length >0 ?tmpValue.substring(1):"";
		return tmpValue;
		
	}else{
		return obj.value;
	}
}

//提示某个域必须要填写,把域的背景改成黄色,
function _SetFieldAlert(){
	try{
		arguments[0].focus();
 	}
 	catch(e){}
}
function btnOver(){
	arguments[0].className='CheckTriggerSpring'
}
function btnOut(){
	arguments[0].className='CheckTrigger'
}



/*
	function hideTopMenu(){
		try{
			if (top!=null ){	
				if(top.document.all("tet")!=null)			top.document.all("tet").click();
				}
		
		}
		catch(E){}
	}
	
	var objMenuWin = window;
	if (objMenuWin && typeof objMenuWin.attachEvent != "undefined") {
		objMenuWin.document.onclick = function(){
			hideTopMenu();
		}
	}
//*/