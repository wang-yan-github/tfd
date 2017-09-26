var css = document.createElement('link');
    css.href= contextPath+"/system/styles/css/style1/fileupload/fileupload.css";
    css.rel="stylesheet";
    css.type="text/css";
    document.getElementsByTagName('head')[0].appendChild(css);

function upmoduleimg(module,fileId,callback){
    $.ajaxFileUpload({
         url:contextPath+'/com/system/filetool/UpImgTool/upheadimg.act?module='+module, //上传文件的服务端
         secureuri:false,  //是否启用安全提交
         async:false,
         dataType: 'json',   //数据类型  
         fileElementId:fileId, //表示文件域ID
         success: function(html,status){
                 if (callback) {
                     callback(html);
                 };
         },
    //提交失败处理函数
         error: function (html,status,e){
             alert("上传文件失败!");         
         }
    });
}


function fileUpLoad(module,divId){
	$.ajaxFileUpload({
         url:contextPath+'/com/system/filetool/UpFileTool/doFileUpload.act?module='+module+"&divId="+divId, //上传文件的服务端
         secureuri:false,  //是否启用安全提交
         async:false,
         dataType: 'json',   //数据类型  
         fileElementId:"file"+divId, //表示文件域ID
         success: function(html,status){
             var Id=html[0].attachmentId;
             var Name=html[0].attachmentName;
             var extName=Name.substring(Name.lastIndexOf(".")+1,Name.length);
             var htmlStr="<div id=\""+Id+"\" style='width:100%;height:25px;line-height:25px;'>\n"+
                                "<img style=\"float:left;width:20px;height:20px;\" src=\""+imgPath+"/filetype/file_extension_"+extName+".png\" />\n"+
                                "<span style=\"float:left;\" title=\""+Name+"\" onmouseout=\"javascript:hideMenus()\" onmouseover=\"showMenu('"+Id+"','"+extName+"','2','"+divId+"')\" >"+Name+"</span>\n"+
                                 "</div>";
                              
             var value=$("#"+divId+"Id").val()+Id+",";
             var name=$("#"+divId+"Name").val()+Name+",";
             $("#"+divId+"Id").val(value);
             $("#"+divId+"Name").val(name);
             $("#"+divId+"Div").append(htmlStr);
         },
    //提交失败处理函数
         error: function (html,status,e){
        	 alert("上传文件失败!");         
         }
    });
}

//文件上件组件
function fileUpLoadSimple(module){
	html="<div>";
		+"</div>";
		+"<div>";
		+"<input type=\"file\" id=\"file\" name=\"file\" style=\"width:30%\">  ";
		+"<a onclick='fileUpLoad(\""+module+"\")' style=\"cursor: pointer;\"><font style=\"color: blue;\">上件文件</font></a>";
		+"</div>";
		$("#attach").html(html);
}

function filesUpLoad(module,divId,pt){
	$("body").append("<div id=\"attachHtml\" ></div>");
    if(divId==undefined)
    {
     divId="attach";   
    }
    if(pt==undefined)
    {
        pt="fsUploadProgress";
    }else
    {
        pt+="Progress";
    }
	var settings = {
	   		flash_url : contextPath+"/system/jsall/swfupload/swfupload/swfupload.swf",
		    upload_url: contextPath+"/com/system/filetool/UpFileTool/doFileUpload.act;JSESSIONID="+getSession()+"?module="+module+"&divId="+divId,
			post_params:{},  
			file_size_limit : "100 MB",
			file_types : "*.*",
			file_types_description : "All Files",
			file_upload_limit : 100,
			file_queue_limit : 0,
			custom_settings : {
				progressTarget : pt,
				cancelButtonId : "btnCancel"
			},
			debug: false,
			// Button settings
			//button_image_url: contextPath+"/system/jsall/swfupload/images/upload4.png",	// Relative to the Flash file
			button_width: "100",
			button_height: "100",
			button_placeholder_id: divId,
			//button_text: "<a href='#' class='.theFont' >添加附件</a>",
			//button_text_style: ".theFont { font-weight:300;font-size: 12px;font-family:Helvetica Neue, Helvetica, Arial, sans-serif } a{color:#075aad;text-decoration:none;} a:hover{text-decoration:underline;}",
			//button_text_left_padding: 20,
			//button_text_top_padding: 2,
			//button_cursor : SWFUpload.CURSOR.HAND,
			button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
			// The event handler functions are defined in handlers.js
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,
			queue_complete_handler : queueComplete	// Queue plugin event
		};
		swfu = new SWFUpload(settings);
	}
//附件DIV生成
function delAttach(attachmentId,inputId){
    var tmpvalue=$("#"+inputId+"Id").val();
    tmpvalue=tmpvalue.replace(attachmentId+",","");
    $("#"+inputId+"Id").val(tmpvalue);
	var url=contextPath+'/com/system/filetool/UpFileTool/delAttachByIdAct.act';
	$.ajax({
		url:url,
		type:"POST",
		dataType:"text",
		data:{attachId:attachmentId},
		async:false,
		error:function(e){
			alert("失败!");
		},
		success:function(data){
			if(data=="OK"){
				$("#"+attachmentId).remove();
			}
		}
	});
}
//查看时用的附件行生成方式
function readAttachDiv(attachIds,attachDiv)
{
    if(attachIds==""||attachIds==null||attachIds=="undefined"||attachIds==undefined)
    {
        return;
    }
	var html="";
	var url=contextPath+'/com/system/filetool/UpFileTool/getAttachNameByIdAct.act';
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{attachIds:attachIds},
		async:false,
		error:function(e){
			alert("失败!");
		},
		success:function(data){
			$("body").append("<div id=\"attachHtml\" ></div>");
			for(var i=0;data.length>i;i++)
				{
					var attach=data[i];
					var attachId=attach.attachId;
					var attachName = attach.attachName.substr(18,attach.attachName.length-18);
					var extName=attachName.substring(attachName.lastIndexOf(".")+1,attachName.length);
					var name = attachName.substring(0,attachName.lastIndexOf("."));
					if(name.length > 14){
						name = attachName.substring(0,14);
					}
					name = name +"."+ extName;
					html+="<div  style='width:100%;height:25px;line-height:25px;'  id=\""+attachId+"\" >"+
					            "<img style=\"float:left;width:20px;height:20px;\" src=\""+imgPath+"/filetype/file_extension_"+extName+".png\" />"+
					            "<div style=\"float:left;cursor:pointer;\" title=\""+attachName+"\" onmouseout=\"javascript:hideMenus()\" onmouseover=\"showMenu('"+attachId+"','"+extName+"','1')\" onmousemove=\"showMenu('"+attachId+"','"+extName+"','1')\"   >"+name+"</div></div>";
				}
			}
	});
	$("#"+attachDiv+"Div").html(html);
}

//只能查看时用的附件行生成方式
function readOnlyAttachDiv(attachIds,attachDiv)
{
    if(attachIds==""||attachIds==null||attachIds=="undefined"||attachIds==undefined)
    {
        return;
    }
	var html="";
	var url=contextPath+'/com/system/filetool/UpFileTool/getAttachNameByIdAct.act';
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{attachIds:attachIds},
		async:false,
		error:function(e){
			alert("失败!");
		},
		success:function(data){
			$("body").append("<div id=\"attachHtml\" ></div>");
			for(var i=0;data.length>i;i++)
				{
					var attach=data[i];
					var attachId=attach.attachId;
					var attachName = attach.attachName.substr(18,attach.attachName.length-18);
					var extName=attachName.substring(attachName.lastIndexOf(".")+1,attachName.length);
					var name = attachName.substring(0,attachName.lastIndexOf("."));
					if(name.length > 14){
						name = attachName.substring(0,14);
					}
					name = name +"."+ extName;
					html+="<div  style='width:100%;height:25px;line-height:25px;'  id=\""+attachId+"\" >"+
					            "<img style=\"float:left;width:20px;height:20px;\" src=\""+imgPath+"/filetype/file_extension_"+extName+".png\" />"+
					            "<div style=\"float:left;cursor:pointer;\" title=\""+attachName+"\" onmouseout=\"javascript:hideMenus()\" onmouseover=\"showMenu('"+attachId+"','"+extName+"','3')\" onmousemove=\"showMenu('"+attachId+"','"+extName+"','3')\"   >"+name+"</div></div>";
				}
			}
	});
	$("#"+attachDiv+"Div").html(html);
}

//手机查看时用的附件行生成方式
function readAttachDivForMobile(attachIds,attachDiv)
{
    if(attachIds==""||attachIds==null||attachIds=="undefined"||attachIds==undefined)
    {
        return;
    }
	var html="";
	var url=contextPath+'/com/system/filetool/UpFileTool/getAttachNameByIdAct.act';
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{attachIds:attachIds},
		async:false,
		error:function(e){
			alert("失败!");
		},
		success:function(data){
			for(var i=0;data.length>i;i++)
				{
					var attach=data[i];
					var attachId=attach.attachId;
					var attachName = attach.attachName.substr(18,attach.attachName.length-18);
					var extName=attachName.substring(attachName.lastIndexOf(".")+1,attachName.lenght);
					var name = attachName.substring(0,attachName.lastIndexOf("."));
					if(name.length > 14){
						name = attachName.substring(0,14);
					}
					name = name +"."+ extName;
					html+="<div style=\"width:250px;height:20px;\"  id=\""+attachId+"\"><img style=\"float:left\" src=\""+imgPath+"/file_type/"+extName+".gif\" /><div style=\"float:left\" title=\""+attachName+"\" onmouseout=\"javascript:hideMenus()\" onmouseover=\"showMenuForMobile('"+attachId+"','"+extName+"','1')\"  >"+name+"</div></div>";
				}
			}
	});
	$("#"+attachDiv).html(html);
}

//编辑文件
function editFile(attachId,extName,privFlag)
{
    window.open(contextPath+"/system/controldoc/word.jsp?attachId="+attachId+"&privFlag="+privFlag);
}


//编辑信息时用的附件生成方式
function creatAttachDiv(attachIds,attachDiv)
{
    if(attachIds==""||attachIds==null||attachIds=="undefined"||attachIds==undefined)
    {
        return;
    }
    $("#"+attachDiv+"Id").val(attachIds);
	var html="";
	var url=contextPath+'/com/system/filetool/UpFileTool/getAttachNameByIdAct.act';
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{attachIds:attachIds},
		async:false,
		error:function(e){
			alert("失败!");
		},
		success:function(data){
			$("body").append("<div id=\"attachHtml\" ></div>");
			for(var i=0;data.length>i;i++)
				{
					var attach=data[i];
					var attachId=attach.attachId;
					var attachName = attach.attachName.substr(18,attach.attachName.length-18);
					var extName=attachName.substring(attachName.lastIndexOf(".")+1,attachName.lenght);
					var name = attachName.substring(0,attachName.lastIndexOf("."));
					if(name.length > 14){
						name = attachName.substring(0,14);
					}
					name = name +"."+ extName;
					html+="<div style=\"width:250px;height:25px;\"  id=\""+attachId+"\">\n"+
					"<img style=\"float:left;width:20px;height:20px;\" src=\""+imgPath+"/filetype/file_extension_"+extName+".png\" />\n"+
					"<div style=\"float:left;cursor:pointer;\" title=\""+attachName+"\" onmouseout=\"javascript:hideMenus()\" onmouseover=\"showMenu('"+attachId+"','"+extName+"','2','"+attachDiv+"')\"  >"+name+
					"</div></div>";
				}
			}
	});
	$("#"+attachDiv+"Div").html(html);
}

//文件柜可编辑的生成方式
function creatAttachDivForFile(attachIds,attachDiv,privId,fileId)
{
    if(attachIds==""||attachIds==null||attachIds=="undefined"||attachIds==undefined)
    {
        return;
    }
	$("body").append("<div id=\"attachHtml\" ></div>");
	var html="";
	var url=contextPath+'/com/system/filetool/UpFileTool/getAttachNameByIdAct.act';
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:{attachIds:attachIds},
		async:false,
		error:function(e){
			alert("失败!");
		},
		success:function(data){
			for(var i=0;data.length>i;i++){
				var attach=data[i];
				var attachId=attach.attachId;
				var attachName = attach.attachName.substr(18,attach.attachName.length-18);
				var extName=attachName.substring(attachName.indexOf(".")+1,attachName.lenght);
				html+="<div onmouseover=\"showMenu('"+attachId+"')\" style=\"width:250px;height:20px;\"  id=\""+attachId+"\"><div style=float:left  >"+attachName+"</div><div style=float:right >&nbsp;<a href='javascript:void(0);' onclick=\"openFileForFile('"+attachId+"','"+extName+"','"+privId+"','"+fileId+"')\">打开</a>&nbsp;<a href='javascript:void(0);'>编辑</a>&nbsp;<a href='#' onclick=\"delAttach('"+attachId+"');\">删除</a></div></div>";
			}
		}
	});
	$("#"+attachDiv+"Div").html(html);
}

//打开文件
function openFile(attachId,extName,privFlag)
{
    if(extName=="txt")
    {
        window.open(contextPath+"/system/controldoc/text.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else   if(extName=="jpg"||extName=="gif"||extName=="png"||extName=="bmp"||extName=="tif")
    {
        window.open(contextPath+"/system/controldoc/img.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else	 if(extName=="pdf")
    {
    window.open(contextPath+"/system/controldoc/pdf.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else if(extName=="doc"||extName=="docx")
    {
        window.open(contextPath+"/system/controldoc/word.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else if(extName=="xls"||extName=="xlsx")
    {
        window.open(contextPath+"/system/controldoc/excel.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else if(extName=="ppt"||extName=="pptx")
    {
        window.open(contextPath+"/system/controldoc/ppt.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else
    {
        alert("无法打开此类型附件！");
    }
}

//文件柜打开文件
function openFileForFile(attachId,extName,privFlag,fileId)
{
    if(extName=="txt")
    {
        window.open(contextPath+"/system/controldoc/text.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else   if(extName=="jpg"||extName=="gif"||extName=="png"||extName=="bmp"||extName=="tif")
    {
        window.open(contextPath+"/system/controldoc/img.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else	 if(extName=="pdf")
    {
    window.open(contextPath+"/system/controldoc/pdf.jsp?attachId="+attachId+"&privFlag="+privFlag+"&fileId="+fileId);
    }else if(extName=="doc"||extName=="docx")
    {
        window.open(contextPath+"/system/controldoc/word.jsp?attachId="+attachId+"&privFlag="+privFlag+"&fileId="+fileId);
    }else if(extName=="xls"||extName=="xlsx")
    {
        window.open(contextPath+"/system/controldoc/excel.jsp?attachId="+attachId+"&privFlag="+privFlag+"&fileId="+fileId);
    }else if(extName=="ppt"||extName=="pptx")
    {
        window.open(contextPath+"/system/controldoc/ppt.jsp?attachId="+attachId+"&privFlag="+privFlag+"&fileId="+fileId);
    }else
    {
        alert("无法打开此类型附件！");
    }
}

//显示操作菜单
function showMenu(attachId,extName,type,inputId){
	if(type == '1'){
		createReadDiv(attachId,extName);
	}else if(type == '2'){
		createEditDiv(attachId,extName,inputId);
	}else if(type=='3'){
		createReadOnlyDiv(attachId,extName);
	}
	var cityObj = $("#"+attachId);
	var cityOffset = $("#"+attachId).offset();
	ev= arguments.callee.caller.arguments[0]  || window.event; 
	var mousePos = mouseCoords(ev);
	$("#menuDiv").css({left:(ev.clientX-40) + "px", top:cityOffset.top + cityObj.outerHeight()-20 + "px"}).show();
}

//保持菜单不消失
function stillMenu(){
	$("#menuDiv").css("display","block");
}
//隐藏菜单
function hideMenus(){
	$("#menuDiv").css("display","none");
}
//下载文件
function downFile(attachId)
{
    window.location.href=contextPath+"/com/system/filetool/UpFileTool/doDownFile.act?attachId="+attachId;
}
//生成编辑菜单
function createEditDiv(attachId,extName,inputId){
	var divHtml = "<div id=\"menuDiv\" onmouseout=\"javascript:hideMenus()\"   onmouseover=\"javascript:stillMenu()\"  class=\"editmenu\" >\n"+
	"<div class=\"menuClass\" onclick=\"javascript:openFile('"+attachId+"','"+extName+"','1')\" >打开</div>\n";
	if(extName=="doc"||extName=="docx"||extName=="xls"||extName=="xlsx"||extName=="ppt"||extName=="pptx")
	{
	    divHtml+="<div class=\"menuClass\" onclick=\"javascript:openFile('"+attachId+"','"+extName+"','4');\"  >编辑</div>\n";
	}
	divHtml+="<div onclick=\"delAttach('"+attachId+"','"+inputId+"');\" class=\"menuClass\" >删除</div>"+
	"<div class=\"menuClass\" type=\"application/msword\" onclick=\"downFile('"+attachId+"');\">下载</div>\n"+
	"</div>";
	$('#attachHtml').html(divHtml);
}
//生成阅读菜单
function createReadDiv(attachId,extName){
	var divHtml = "<div id=\"menuDiv\" onmouseout=\"javascript:hideMenus()\"   onmouseover=\"javascript:stillMenu()\" class=\"readmenu\" >\n"+
	"<div class=\"menuClass\" onclick=\"javascript:openFile('"+attachId+"','"+extName+"','1')\" >打开</div>\n"+
	"<div class=\"menuClass\" type=\"application/msword\" ><a href='"+contextPath+"/com/system/filetool/UpFileTool/doDownFile.act?attachId="+attachId+"' >下载</a></div></div>";
	$('#attachHtml').html(divHtml);
}

//生成只读的菜单
function createReadOnlyDiv(attachId,extName){
	var divHtml = "<div id=\"menuDiv\" onmouseout=\"javascript:hideMenus()\"   onmouseover=\"javascript:stillMenu()\" class=\"readmenu\" >\n"+
	"<div class=\"menuClass\" onclick=\"javascript:openFile('"+attachId+"','"+extName+"','1')\" >打开</div></div>";
	$('#attachHtml').html(divHtml);
}

//显示手机操作菜单
function showMenuForMobile(attachId,extName,type){
	createReadDivForMobile(attachId,extName);
	var cityObj = $("#"+attachId);
	var cityOffset = $("#"+attachId).offset();
	$("#menuDiv").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight()-2 + "px"}).slideDown("fast");
}
//生成手机菜单
function createReadDivForMobile(attachId,extName){
	var divHtml = "<div id=\"menuDiv\" onmouseout=\"javascript:hideMenus()\"   onmouseover=\"javascript:stillMenu()\" style=\"text-align:center;line-height:30px;height:30px;width:100px;display:none;position: absolute;background-color:#F5F5F5;border:solid 1px #CCC;z-index:999999;\" ><po:MobFileLink open=\""+contextPath+"/system/mobile/android/controldoc/openfile.jsp?attachId="+attachId+"\">查看</po:MobFileLink></div>";
	$('body').append(divHtml);
}
//得到鼠标坐标
function mouseCoords(ev) 
{ 
	if(ev.pageX || ev.pageY){ 
		return {x:ev.pageX, y:ev.pageY}; 
	} 
	return { 
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft, 
		y:ev.clientY + document.body.scrollTop - document.body.clientTop 
	}; 
} 
function getSession()
{
    var returnData="";
    var url=contextPath+'/com/system/tool/SysTool/getSessionId.act';
    $.ajax({
                async: false,
                url: url,
                dataType: 'text',
                success: function (data) {
                  returnData=data;
                }
      });
    return returnData;
}
//工作流共公附件操作
function createWFAttachDiv(attachIds,attachDiv,pfprive)
{
    if(attachIds==""||attachIds==null||attachIds=="undefined"||attachIds==undefined)
    {
        return;
    }
    $("#"+attachDiv+"Id").val(attachIds);
    var pfileread =pfprive.readOnly;
    var pupload =pfprive.createUpLoad;
    var pedit = pfprive.edit;
    var pdown =pfprive.down;
    var html="";
    var url=contextPath+'/com/system/filetool/UpFileTool/getAttachNameByIdAct.act';
    $.ajax({
        url:url,
        type:"POST",
        dataType:"json",
        data:{attachIds:attachIds},
        async:false,
        error:function(e){
            alert("失败!");
        },
        success:function(data){
            $("body").append("<div id=\"attachHtml\" ></div>");
            for(var i=0;data.length>i;i++)
                {
                    var attach=data[i];
                    var attachId=attach.attachId;
                    var attachName = attach.attachName.substr(18,attach.attachName.length-18);
                    var extName=attachName.substring(attachName.lastIndexOf(".")+1,attachName.lenght);
                    var name = attachName.substring(0,attachName.lastIndexOf("."));
                    if(name.length > 14){
                        name = attachName.substring(0,14);
                    }
                    name = name +"."+ extName;
                    if(pfileread=="1"&&pedit=="0"&&pdown=="0")
                    {
                        html+="<div style=\"width:250px;height:25px;\"  id=\""+attachId+"\">\n"+
                    "<img style=\"float:left;width:20px;height:20px;\" src=\""+imgPath+"/filetype/file_extension_"+extName+".png\" />\n"+
                    "<div style=\"float:left;cursor:pointer;\" title=\""+attachName+"\" onmouseout=\"javascript:hideMenus()\" ";
                        html+="onmouseover=\"showMenu('"+attachId+"','"+extName+"','3','"+attachDiv+"')\"  >"+name;
                         html+="</div></div>";
                    }else if(pedit=="1")
                    {
                        html+="<div style=\"width:250px;height:25px;\"  id=\""+attachId+"\">\n"+
                    "<img style=\"float:left;width:20px;height:20px;\" src=\""+imgPath+"/filetype/file_extension_"+extName+".png\" />\n"+
                    "<div style=\"float:left;cursor:pointer;\" title=\""+attachName+"\" onmouseout=\"javascript:hideMenus()\" ";
                          html+="onmouseover=\"showMenu('"+attachId+"','"+extName+"','2','"+attachDiv+"')\"  >"+name;
                           html+="</div></div>";
                    }else if(pdown=="1")
                    {
                        html+="<div style=\"width:250px;height:25px;\"  id=\""+attachId+"\">\n"+
                    "<img style=\"float:left;width:20px;height:20px;\" src=\""+imgPath+"/filetype/file_extension_"+extName+".png\" />\n"+
                    "<div style=\"float:left;cursor:pointer;\" title=\""+attachName+"\" onmouseout=\"javascript:hideMenus()\" ";
                        html+="onmouseover=\"showMenu('"+attachId+"','"+extName+"','1','"+attachDiv+"')\"  >"+name;
                         html+="</div></div>";
                    }else
                    {
                        if(attachIds)
                        {
                           html="无附件查看权限";
                        }else
                        {
                        html="暂无上传附件";
                        }
                    }
                }
            }
    });
    $("#"+attachDiv+"Div").html(html);
}
