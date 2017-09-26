var trNum = 0;
var divId;
var module;

$(function(){
	init();
});

function fileUploadByAttach(type){
	divId = "attach";
	module = type;
}

function goOpen(){
	$("#btn-open").trigger("click");
	$("#btn_opt_ok").prop("disabled","disabled");
}

function init(){
	var html = "<div class=\"modal fade\" id=\"attachModel\" tabindex=\"-1\" role=\"dialog\"  aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"z-index:9999;\">\n"+
			   "<div class=\"modal-dialog\">\n"+
			      "<div class=\"modal-content\">\n"+
			         "<div class=\"modal-header\" style=\"padding-top:10px;padding-bottom:10px;\">\n"+
			            "<button type=\"button\" class=\"close\"  data-dismiss=\"modal\" aria-hidden=\"true\">  &times;</button>\n"+
			            "<h5 class=\"modal-title\" id=\"myModalLabel\">选择文件</h5>\n"+
			         "</div>\n"+
			         "<div class=\"modal-body\" style=\"height:340px;margin:0px;padding:0px;\">\n"+
			         "<div class=\"leftMenu\">"+
			         	"<div class=\"menu-li\" onclick=\"javascript:selectStyle('selected');\" >已选文件</div>"+
			         	"<div class=\"menu-li\" onclick=\"javascript:selectStyle('publicDisk');\" >公共资源</div>"+
			         	"<div class=\"menu-li\" onclick=\"javascript:selectStyle('personFolder');\" >个人文件柜</div>"+
			         	"<div class=\"menu-li\" onclick=\"javascript:selectStyle('publicFolder');\" >公共文件柜</div>"+
			         "</div>"+
			         "<div class=\"rightBody\" id=\"selected\" >"+
			         	"<div class=\"list-group\" style=\"margin-bottom: 0px;\">"+
   							"<a class=\"list-group-item active\">"+
      							"<h5 class=\"list-group-item-heading\">已选文件</h5>"+
   							"</a>"+
   							"<table class=\"table table-striped\" style=\"width:80%;margin-left:10%;margin-top:20px;\" >"+
   								
   							"</table>"+
   						"</div>"+	
			         "</div>"+
			         "<div class=\"rightBody\" id=\"publicDisk\" >"+
			         	"<div class=\"list-group\" style=\"margin-bottom: 0px;\">"+
   							"<a class=\"list-group-item active\">"+
      							"<h5 class=\"list-group-item-heading\">公共资源</h5>"+
   							"</a>"+
   							"<table class=\"table table-striped\" style=\"width:80%;margin-left:10%;margin-top:20px;\" >"+
   								
   							"</table>"+
   						"</div>"+
			         "</div>"+
			         "<div class=\"rightBody\" id=\"personFolder\" >"+
			         	"<div class=\"list-group\" style=\"margin-bottom: 0px;\">"+
   							"<a class=\"list-group-item active\">"+
      							"<h5 class=\"list-group-item-heading\">个人文件柜</h5>"+
   							"</a>"+
   							"<table class=\"table table-striped\" style=\"width:80%;margin-left:10%;margin-top:20px;\" >"+
   								
   							"</table>"+
   						"</div>"+
			         "</div>"+
			         "<div class=\"rightBody\" id=\"publicFolder\" >"+
			         	"<div class=\"list-group\" style=\"margin-bottom: 0px;\">"+
   							"<a class=\"list-group-item active\">"+
      							"<h5 class=\"list-group-item-heading\">公共文件柜</h5>"+
   							"</a>"+
   							"<table class=\"table table-striped\" style=\"width:80%;margin-left:10%;margin-top:20px;\" >"+
   								
   							"</table>"+
   						"</div>"+
			         "</div>"+
			         "</div>\n"+
	                 "<div class=\"modal-footer\" style=\"padding-top:5px;padding-bottom:5px;\">\n"+
	                   " <button type=\"button\" class=\"btn btn-default\" id=\"opt_remove\" data-dismiss=\"modal\">取消</button>\n"+
	                    "<button type=\"button\" class=\"btn btn-primary\" id=\"btn_opt_ok\" disabled=\"disabled\" onclick=\"javascript:doupload();\" data-dismiss=\"modal\" >确定</button>\n"+
	                 "</div>\n"+      
		         "</div>\n"+
		      "</div>";
	$("body").append(html);
}

var a = 0 ,b = 0 ,c = 0;
function selectStyle(type){
	$(".rightBody").hide();
	$("#"+type).show();
	$(".menu-li").removeClass("clicked");
	if(type=='publicDisk'){
		$(".menu-li").eq(1).addClass("clicked");
		if(a==0){
			getPublicDisk();
			a++;
		}
	}else if(type=='personFolder'){
		$(".menu-li").eq(2).addClass("clicked");
		if(b==0){
			getPersonFolder();
			b++;
		}
	}else if(type=='publicFolder'){
		$(".menu-li").eq(3).addClass("clicked");
		if(c==0){
			getPublicFolder();
			c++;
		}
	}else{
		$(".menu-li").eq(0).addClass("clicked");
	}
}

var disks;
function getPublicDisk(){
	var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/getDiskList.act';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		success:function(data){
			disks = data;
			var diskHtml = "";
			$.each(data,function(index,data){
				diskHtml += "<tr onclick=\"javascript:getDiskChild('"+index+"');\" style=\"cursor:pointer;\" ><td>"+data.name+"</td></tr>";
			});
			$("#publicDisk table").html(diskHtml);
		}
	});
}

var diskFile;
function getDiskChild(i){
	var disk2;
	var diskHtml = "<tr onclick=\"getPublicDisk();\" style=\"cursor:pointer;\" ><td>返回根目录</td></tr>";
	var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/getFolder.act';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{path:disks[i].path},
		async:false,
		success:function(data){
			disk2 = data;
			$.each(data,function(index,data){
				diskHtml += "<tr onclick=\"javascript:getDiskChild("+index+")\" ><td><img width=\"24px;\" height=\"24px;\" src=\""+imgPath+"/filetype/folder.png\" /><span>"+data.name+"</span></td></tr>";
			});
		}
	});
	requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/getFile.act';
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{path:disks[i].path,searchContent:''},
		async:false,
		success:function(data){
			diskFile = data;
			$.each(data,function(index,data){
				diskHtml += "<tr onclick=\"javascript:selectFile('"+index+"',this);\" style=\"cursor:pointer;\" ><td style=\"background-color:#FFF;\" ><img width=\"24px;\" height=\"24px;\" src=\""+imgPath+"/filetype/text.png\" /><span>"+data.name+"</span></td></tr>";
			});
			
		}
	});
	$("#publicDisk table").html(diskHtml);
	disks = disk2;
}

function goBackDisk(){
	
}

function getPersonFolder(){
	var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/createFolder.act';
	$.ajax({
			url:requestUrl,
			type:'POST',
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
			}
	});
	requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/getFolderListByPriv.act';
	$.ajax({
		url:requestUrl,
		data:{isPublic:'2'},
		type:'POST',
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var personHtml = "";
			$.each(data,function(index,data){
				personHtml += "<tr onclick=\"javascript:getFolderChild('"+data.id+"',2)\" style=\"cursor:pointer;\" ><td><img width=\"24px;\" height=\"24px;\" src=\""+imgPath+"/filetype/folder.png\" /><span>"+data.name+"</span></td></tr>";
			});
			$("#personFolder table").html(personHtml);
		}
	});
}

function getPublicFolder(){
	var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/getFolderListByPriv.act';
	$.ajax({
		url:requestUrl,
		data:{isPublic:'1'},
		type:'POST',
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var publicHtml = "";
			$.each(data,function(index,data){
				publicHtml += "<tr onclick=\"javascript:getFolderChild('"+data.id+"',1)\" style=\"cursor:pointer;\" ><td><img width=\"24px;\" height=\"24px;\" src=\""+imgPath+"/filetype/folder.png\" /><span>"+data.name+"</span></td></tr>";
			});
			$("#publicFolder table").html(publicHtml);
		}
	});
}

var personFolder;
var publicFolder;
function getFolderChild(id,i){
	var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/getFolderFileListByPrivInAttach.act';
	$.ajax({
		url:requestUrl,
		data:{folderId:id,isPublic:i},
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var folderHtml = "";
			if(i=='1'){
				folderHtml = "<tr onclick=\"getPublicFolder();\" style=\"cursor:pointer;\" ><td>返回根目录</td></tr>";
				publicFolder = data;
			}else{
				folderHtml = "<tr onclick=\"getPersonFolder();\" style=\"cursor:pointer;\" ><td>返回根目录</td></tr>";
				personFolder = data;
			}
			$.each(data,function(index,data){
				if(data.isParent == 'true'){
					folderHtml += "<tr onclick=\"javascript:getFolderChild('"+data.id+"',"+i+")\" style=\"cursor:pointer;\" ><td style=\"background-color:#FFF;\" ><img width=\"24px;\" height=\"24px;\" src=\""+imgPath+"/filetype/folder.png\" /><span>"+data.name+"</span></td></tr>";
				}
			});
			$.each(data,function(index,data){
				if(data.isParent == 'false'){
					folderHtml += "<tr onclick=\"javascript:selectFileByFolder('"+index+"',this,'"+i+"')\" style=\"cursor:pointer;\" ><td style=\"background-color:#FFF;\"><img width=\"24px;\" height=\"24px;\" src=\""+imgPath+"/filetype/text.png\" /><span>"+data.name+"</span></td></tr>";
				}
			});
			if(i=='1'){
				$("#publicFolder table").html(folderHtml);
			}else{
				$("#personFolder table").html(folderHtml);
			}
			
		}
	});
}

function selectFile(i,tr){
	if($(tr).hasClass("doSelect")){
		$(tr).removeClass("doSelect");
		$(tr).find("td").css("background-color","#FFF");
		var id = $(tr).prop("id");
		id = id.substr(3,4);
		$("#tr"+id).remove();
	}else{
		$(tr).addClass("doSelect");
		$(tr).find("td").css("background-color","#F5F5F5");
		$(tr).prop("id","trs"+trNum);
		var selectHtml = "<tr onclick=\"javascript:doCancel(this);\" id=\"tr"+trNum+"\" style=\"cursor:pointer;\" ><td style=\"background-color:#FFF;\"><input type=\"hidden\" class=\"pathInput\" value=\""+diskFile[i].path+"\" /><input type=\"hidden\" class=\"typeInput\" value=\"1\" /><img width=\"24px;\" height=\"24px;\" src=\""+imgPath+"/filetype/text.png\" /><span>"+diskFile[i].name+"</span></td></tr>";
		$("#selected table").append(selectHtml);
		trNum++;
	}
	if($("#selected table tbody").html()!=""){
		$("#btn_opt_ok").removeProp("disabled");
	}else{
		$("#btn_opt_ok").prop("disabled","disabled");
	}
}

function selectFileByFolder(i,tr,isPublic){
	if($(tr).hasClass("doSelect")){
		$(tr).removeClass("doSelect");
		$(tr).find("td").css("background-color","#FFF");
		var id = $(tr).prop("id");
		id = id.substr(3,4);
		$("#tr"+id).remove();
	}else{
		$(tr).addClass("doSelect");
		$(tr).find("td").css("background-color","#F5F5F5");
		$(tr).prop("id","trs"+trNum);
		if(isPublic=='1'){
			var selectHtml = "<tr onclick=\"javascript:doCancel(this);\" id=\"tr"+trNum+"\" style=\"cursor:pointer;\" ><td style=\"background-color:#FFF;\"><input type=\"hidden\" class=\"pathInput\" value=\""+publicFolder[i].path+"\" /><input type=\"hidden\" class=\"typeInput\" value=\"2\" /><img width=\"24px;\" height=\"24px;\" src=\""+imgPath+"/filetype/text.png\" /><span>"+publicFolder[i].name+"</span></td></tr>";
		}else{
			var selectHtml = "<tr onclick=\"javascript:doCancel(this);\" id=\"tr"+trNum+"\" style=\"cursor:pointer;\" ><td style=\"background-color:#FFF;\"><input type=\"hidden\" class=\"pathInput\" value=\""+personFolder[i].path+"\" /><input type=\"hidden\" class=\"typeInput\" value=\"2\" /><img width=\"24px;\" height=\"24px;\" src=\""+imgPath+"/filetype/text.png\" /><span>"+personFolder[i].name+"</span></td></tr>";
		}
		$("#selected table").append(selectHtml);
		trNum++;
	}
	if($("#selected table tbody").html()!=""){
		$("#btn_opt_ok").removeProp("disabled");
	}else{
		$("#btn_opt_ok").prop("disabled","disabled");
	}
}

function doCancel(tr){
	var id = $(tr).prop("id");
	id = id.substr(2,3);
	$("#trs"+id).removeClass("doSelect");
	$("#trs"+id).find("td").css("background-color","#FFF");
	$(tr).remove();	
}

function doupload(){
	ajaxLoading();
	var pathStr = '{"data":[';
	var trs = $("#selected table tr");
	for(var i = 0; i < trs.size();i++){
		var path = $("#selected table tr").eq(i).find("td .pathInput").val();
		var type = $("#selected table tr").eq(i).find("td .typeInput").val();
		var name = $("#selected table tr").eq(i).find("td span").html();
		var b='\\\\';
		path = path.replace(/\\/g,b);
		pathStr += '{"path":"'+path+'","name":"'+name+'","type":"'+type+'"},';
	}
	pathStr = pathStr.substr(0,pathStr.length-1);
	pathStr += "]}";
	var requestUrl= contextPath+'/com/system/filetool/UpFileTool/doFileUploadByFile.act';
	$.ajax({
		url:requestUrl,
		data:{divId:divId,module:module,pathStr:encodeURIComponent(pathStr)},
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var html = "";
			for(var i=0;data.length>i;i++){
				var attach=data[i];
				var attachId=attach.attachmentId;
				$("#attachId").val($("#attachId").val()+attachId+",");
				var attachName = attach.attachmentName;
				$("#attachName").val($("#attachName").val()+attachName+",");
				var extName=attachName.substr(attachName.lastIndexOf(".")+1,attachName.lenght);
				var name = attachName.substr(0,attachName.lastIndexOf("."));
				if(name.length > 14){
					name = attachName.substring(0,14);
				}
				name = name +"."+ extName;
				html+="<div style=\"width:250px;height:25px;\"  id=\""+attachId+"\">\n"+
					"<img style=\"float:left;width:20px;height:20px;\" src=\""+imgPath+"/filetype/file_extension_"+extName+".png\" />\n"+
					"<div style=\"float:left;cursor:pointer;\" title=\""+attachName+"\" onmouseout=\"javascript:hideMenus()\" onmouseover=\"showMenu('"+attachId+"','"+extName+"','2')\"  >"+name+
					"</div></div>";
			}
			$("#attachDiv").append(html);
			ajaxLoadEnd();
		}
	});
}
