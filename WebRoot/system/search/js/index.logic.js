function doinit(){
	var searchContent = top.$("#searchContent").val();
	$("#searchContent").val(searchContent);
	search();
	$("#searchContent").on("keydown",function(e){
	    if(e.keyCode==13) {
			search();
	    }
	});
}

function moveToModule(module,number){
	$("html,body").animate({scrollTop: $("#"+module+"_content").offset().top-90}, 800);
	$(".main_ul_div ul li").removeClass("li_clicked");
	$(".main_ul_div ul li").eq(number).addClass("li_clicked"); 
}

function search(){
	var content = $("#searchContent").val();
	if(content==""){
		return;
	}
	requestUrl=contextPath+'/tfd/system/search/act/SearchAct/getSearchData.act?';
	$.ajax({
		url:requestUrl,
		data:{content:content},
		dataType:"json",
		error:function(e){
			alert(e.message);
		},
		beforeSend:ajaxLoading,
		success:function(data){
			$.each(data,function(index,data){
				createHtml(data);
			});
			SearchHighlight("search_main",content);
			ajaxLoadEnd();
		}
	});
}

function createHtml(data){
	if(data.module=='email'){
		createEmailHtml(data.list);
	}
}

function createEmailHtml(list){
	var html = "<table id=\"emailTable\" ><tr class=\"tableTitle\" ><td>标题</td><td>发件人</td><td>收件人</td><td>来源</td><td>时间</td></tr>";
	$.each(list,function(index,data){
		html += "<tr onclick=\"goEmail('"+data.emailId+"');\" ><td>"+data.subject+"</td><td>"+data.fromName+"</td><td>"+data.toName+"</td><td>"+data.boxId+"</td><td>"+data.sendTime+"</td></tr>";
	});
	html += "</table>";
	$("#main_content3 .content_main").html(html);
}

function closeMainContent(number){
	var src = $("#main_content"+number+" .content_title img").prop("src");
	imgName = src.substr(src.lastIndexOf("/"),src.length);
	if(imgName == "/triangle-bottom.png"){
		$("#main_content"+number+" .content_title img").prop("src",imgPath+"/search/triangle-right.png");
		$("#main_content"+number+" .content_main").slideUp(600);
	}else{
		$("#main_content"+number+" .content_title img").prop("src",imgPath+"/search/triangle-bottom.png");
		$("#main_content"+number+" .content_main").slideDown(600);
	}
}

function SearchHighlight(idVal,keyword) 
 { 
     var pucl = document.getElementById(idVal); 
     if("" == keyword) return; 
     var temp=pucl.innerHTML; 
     var htmlReg = new RegExp("\<.*?\>","i"); 
     var arrA = new Array(); 
     //替换HTML标签 
     for(var i=0;true;i++) 
     { 
         var m=htmlReg.exec(temp); 
         if(m) 
         { 
             arrA[i]=m; 
         } 
         else 
         { 
             break; 
         } 
         temp=temp.replace(m,"{[("+i+")]}"); 
     } 
     words = unescape(keyword.replace(/\+/g,' ')).split(/\s+/); 
     //替换关键字 
     for (w=0;w<words.length;w++) 
     { 
         var r = new RegExp("("+words[w].replace(/[(){}.+*?^$|\\\[\]]/g, "\\$&")+")","ig"); 
         temp = temp.replace(r,"<b style='color:Red;'>$1</b>"); 
     } 
     //恢复HTML标签 
     for(var i=0;i<arrA.length;i++) 
     { 
         temp=temp.replace("{[("+i+")]}",arrA[i]); 
     } 
         pucl.innerHTML=temp; 
}

function goEmail(emailId){
	top.goUrl("电子邮件",contextPath+"/system/email/index.jsp?emailId="+emailId);
}


