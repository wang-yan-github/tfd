//**** JS for China Constuction co-drp ****
//**** auth changcq ******
//**** First created on 2008.04.06 ****

function shiftTag(e, tagName) {
    var tags = document.getElementsByName(tagName);
    for (var i=0, len=tags.length; i<len; i++) {
        tags[i].className = "menu1_off";
    }
    var obj = event.srcElement || e.target;
    obj.className = "menu1_on";
    obj.blur();
}


//弹出窗口
function popupDialog(url, width, height) {
  if (url==null) {
    alert("[public.js][popupDialog(url, width, height)] 请提供 url 参数。");
    return false;
  }
  if (width==null) {
    width = 500;
  }
  if (height==null) {
    height = 400;
  }
	var x = parseInt(screen.width / 2.0) - (width / 2.0);  
	var y = parseInt(screen.height / 2.0) - (height / 2.0);
	var isMSIE= (navigator.appName == "Microsoft Internet Explorer");//判断浏览器
	
	if (isMSIE) {
		retval = window.showModalDialog(url, window, "dialogWidth:"+width+"px; dialogHeight:"+height+"px; dialogLeft:"+x+"px; dialogTop:"+y+"px; center:yes;help:no;scrollbars:no;resizable=yes;status:no;");
		return retval;
	} else {
		var win = window.open(url, "", "top=" + y + ",left=" + x + ",scrollbars=no,dialog=yes,modal=yes,width=" + width + ",height=" + height + ",resizable=yes" );  
		//try { win.resizeTo(width, height); } catch(e) { }
		win.focus();
		return true;
	}
	return false;
}

//翻页输入框数字判断
function is_pagenum(form_value,MAX_NUM){
  if(((event.keyCode>57)||(event.keyCode<48))&&(event.keyCode!=13)){
      alert("你输入的不是数字！");
      return false;
   }
   if((form_value=="")&&((event.keyCode-48)>parseInt(MAX_NUM))){
     alert("你输入的数字超出范围");
      return false;
   }
   if((form_value!="")&&(parseInt(form_value+""+(event.keyCode-48).toString())>parseInt(MAX_NUM))){
     alert("你输入的数字超出范围");
      return false;
	
   }
   return true;
}



//控制text域的最大输入长度
//判断字符串长度，加入了汉字的长度判断
function getLength(str){
	var templen=str.length;
	if(navigator.appName=='Netscape') return templen;
	for(var i=0;i<str.length;i++){
    		var rstr=escape(str.substring(i,i+1)); 
    		if (rstr.substring(0,2)=="%u"){ 
       			templen++;
    		} 
  	}
	return templen;
}

//功能:显示提示信息msg,光标焦点落在obj上,返回false
//     主要用于检查必要字段是否正确
//示例:showMsg("用户名不能为空.",myform.username)
//输入参数:msg(提示信息) obj(光标焦点对象)
//输出参数:false
function showMsg(msg, obj)
{
	alert(msg);
    if (obj) {
        var objType = obj.getAttribute("type");
        if (objType!="hidden" && obj.style && obj.style.display!="none" && obj.style.visibility!="hidden") {
        	try{
            	obj.focus();
            }catch(e){}
        }
    }
    return false;
}
//是否为手机号码
function isMobile(obj,isMust)
{

	obj.value=trim(obj.value);
	slen=getLength(obj.value);
	if(slen==0){
		if(isMust){
			return showMsg("必须输入手机号码！",obj);
		}
		return true;
	}
	
	for (i=0; i<slen; i++){
		cc = obj.value.charAt(i);
		if ((cc <"0" || cc >"9") && cc != "-" && cc!="+" && cc!="(" && cc !=")" && cc !="/")
		{
			return showMsg("手机号码含有非法字符！",obj);
		}
	}
	if(slen != 11)
        return showMsg("手机号码长度不正确！",obj);
	tempNo = obj.value.substring(0, 4)*1;

	if (tempNo >= 1300 && tempNo <= 1399)
			return true;
	else
       return showMsg("手机号码不正确！",obj);
	return true;
}
// 是否为Email
function isEmail(obj,isMust)
{
	obj.value=trim(obj.value);
	slen=getLength(obj.value);
	if(slen==0){
		if(isMust)
			return showMsg("必须输入Email！",obj);
		return true;
	}

  var bValidate = RegExp(/^\w+[\-\.\w]*\@[A-Za-z0-9\-]{1,63}(\.[A-Za-z]{2,3}){1,2}$/);
	if (!bValidate.test(obj.value))
	{
		return showMsg("电子邮件书写有误！",obj);
	}
	return true;
}
//是否为电话号码
function isPhone(obj,isMust)
{

	obj.value=trim(obj.value);
	slen=getLength(obj.value);
	if(slen==0){
		if(isMust){
			return showMsg("必须输入电话号码！",obj);
		}
		return true;
	}
	
	for (i=0; i<slen; i++){
		cc = obj.value.charAt(i);
		if ((cc <"0" || cc >"9") && cc != "-" && cc!="+" && cc!="(" && cc !=")" && cc !="/")
		{
			return showMsg("电话号码含有非法字符！",obj);
		}
	}
	return true;
}
//功能:验证邮政编码
//输入参数:str 字符串
//输出参数:boolean
function isZipCode(obj,isMust){
	obj.value=trim(obj.value);
	slen=getLength(obj.value);
	if(slen==0){
		if(isMust){
			return showMsg("必须输入邮政编码！",obj);
		}
		return true;
	}
	var myReg = /^[1-9]\d{6}$/;
	if(!myReg.test(obj.value)){
		return showMsg("邮政编码书写有误！",obj);
	}
	return true;
}
/**
* 检查字符串是否是整数
* @param {String} 对象
* @return {bool} 是否是整数
*/
function isInteger(obj, objName, isMust)
{ 
	obj.value=trim(obj.value);	
	slen=getLength(obj.value);
	if(slen==0){
		if(isMust){
			return showMsg(objName + "必须输入！",obj);
		}
		return true;
	}

	var isInteger = RegExp(/^[0-9]+$/);
	if(!isInteger.test(obj.value)){
			return showMsg(objName + "输入的不是数字！",obj);
	
	}
	return true;
}

//判断标题输入框不能全为空格或数字
function isBlankNum(obj, objName, isMust)
{
	isfind=true;
	obj.value=obj.value.replace(/　/g," ");
	slen=getLength(obj.value);
	if(slen==0){
		if(isMust){
			return showMsg("必须输入！",obj);
		}
		return true;
	}
	for(i=0;i<slen;i++)
	{
		 
		 s=obj.value.substring(i,i+1)
		 if(s!=" "&&s!="　"){
			   if(parseInt(s,10).toString()=="NaN")
				{			 
					 isfind=true;
					 return isfind;
				}
		 }
		 if(s==" "||(s>=0&&s<=9)||s=="　"){
			
				isfind=false;
		 }
		 else
		 {
				  isfind==true;
				  return isfind;
		 }
	}
	if(!isfind){
		return showMsg(objName + "不能全为数字或空格！",obj);
		
	}
	return isfind;       
}
//功能:去掉字符串前后的空格并返回
//输入参数:inputStr(待处理的字符串)theForm.mobile
//输出参数:inputStr(处理后的字符串)
function trim(inputStr) {
	var result = inputStr;
	while (result.substring(0,1) == " ") {
		result = result.substring(1,result.length);
	}
	
	while (result.substring(result.length-1, result.length) == " ") {
		result = result.substring(0, result.length-1);
	}
		
	return result;
}
//----------------------------------------------------------
//控制textarea域的最大输入长度
//----------------------------------------------------------
function getAreaLength(str){
 var s= str;
 var permitlen = 0;
 var temlen=0;
 var len = 0;
 for(i=0;i<s.length;i++){
     var c = s.substr(i,1);
     var ts = escape(c);

     if(ts.substring(0,2) == "%u"){
      len+=2;
      len+=temlen;
      temlen=0;
     }
     else if(ts.substring(0,3) == "%0D"){
      temlen+=1;
     }
     else if(ts.substring(0,3) == "%0A"){
         temlen+=1;
     }
     else if(ts.substring(0,3) == "%20"){
      temlen+=1;
     }
      else{
      len+=1;
      len+=temlen;
      temlen=0;
     }
 }
  return len;
}

// add mawr
function getHzPageTag(divName) {
	var myarray = new Array(2);
    var formChildNodes = getHzPageTagByForm(divName).childNodes;
	for (j = 0; j < formChildNodes.length; j++) {
		var vFormChildTag = formChildNodes[j].tagName;
		if (vFormChildTag != null && vFormChildTag.toLowerCase() == "input") {
			myarray[0] = "HZ_PAGE_NO="+formChildNodes[j].form.HZ_PAGE_NO.value;
			myarray[1] = "HZ_PAGE_SIZE="+formChildNodes[j].form.HZ_PAGE_SIZE.value;
			break;
		}
	}
	return myarray;
}

function getHzPageTagByUrl(divName){
	var formObject = getHzPageTagByForm(divName);
	var formElements = formObject.elements;
	var action = formObject.action + "?";
	for(i = 0; i < formElements.length; i++){
		action += formElements[i].name+"="+formElements[i].value+"&";
	}
	action = action.substring(0,action.lastIndexOf("&"));
	return action;
}

function getHzPageTagByForm(divName){
	if(divName == null || divName.length == 0){
		divName = "HZ_PAGE_OBJECT";
	}
	var vDiv = getTagDivName(divName);
	var vElements = vDiv.childNodes;
	for (i = 0; i < vElements.length; i++) {
		if (vElements[i].tagName != null) {
			vForm = vElements[i].tagName.toLowerCase();
			if (vForm == "form") {
				return vElements[i];
			}
		}
	}
}

function getTagDivName(name){
	var divName = document.getElementsByTagName("div");
	for(i=0;i<divName.length;i++){
		if(divName[i].getAttribute("name") == name)
			return divName[i];
	}
}
// and


function  checkBoxIsCheck(objName){  
      var obj = document.getElementsByName(objName);             //这个是以标签的name来取控件 
           for(i  = 0;   i   <   obj.length;   i++)    {  

            if(obj[i].checked)    {  
                    return   true;  
            }  
        } 
       alert("请先选择数据");
       return false;        
}  


function autoDivHeight(divId,reduceHeight){
	  document.getElementById(divId).style.height=document.documentElement.clientHeight-reduceHeight+"px";
}
function loadNav(){
	document.getElementById("tdNavText").innerHTML = "&nbsp;"+jsview_lang.locationHint+"&nbsp;"+parent.document.getElementById("navText").innerHTML +parent.document.getElementById("eventText").value;
}

function getIEVersion(){   
	   var bro = navigator.userAgent.toLowerCase(); 
	   if(/msie/.test(bro)) return 'IE' + bro.match(/msie ([\d.]*);/)[1];
	   else if(/navigator/.test(bro)) return 'NS' + bro.match(/navigator\/([\d.]*)/)[1];
	   else if(/chrome/.test(bro)) return 'CR' + bro.match(/chrome\/([\d]*)/)[1];
	   else if(/safari/.test(bro)) return 'SF' + bro.match(/version\/([\d]*)/)[1];
	   else if(/opera/.test(bro)) return 'OP' + bro.match(/version\/([\d]*)/)[1];
	   else if(/firefox/.test(bro)) return 'FF' + bro.match(/firefox\/([\d]*)/)[1];
	  }

function ckTab(id,len,activeClass,noActiveClassName){
	for(var i=0;i<len;i++){
		if("tab"+i==id){
			document.getElementById(id).className=activeClass;
			document.getElementById("tabDiv"+i).style.display="block";
		}else{
			document.getElementById("tab"+i).className=noActiveClassName;
			document.getElementById("tabDiv"+i).style.display="none";
		}
	}
}


function SetCookie(name,value)//两个参数，一个是cookie的名子，一个是值
{
    //var Days = 30; //此 cookie 将被保存 30 天
    //var exp  = new Date();    //new Date("December 31, 9998");
    //exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value);
}
function SetCookie2(name,value,exptime){ 
    try{ 
        if(arguments.length == 2) return arguments.callee(name,value,30*24*60*60*1000); 
                         
        var exp　= new Date(); 
        exp.setTime(exp.getTime() + exptime);  
        document.cookie = name + "="+ escape(value) +";expires="+ exp.toGMTString(); 
    } 
    catch(e){ 
       throw new Error("SetCookies: " + e.message); 
        return false; 
    } 
} 
function getCookie(name)//取cookies函数        
{
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;

}
function delCookie(name)//删除cookie
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString()+"; path=C://" ;
}


