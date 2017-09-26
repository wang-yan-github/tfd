$(function(){
	filesUpLoad("photo");
	fileUploadByAttach("photo");
	doinit();
});
function doinit(){
	ajaxLoading();
	var reg = '/';
	var newPath = path.replace(/\\/g,reg);
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/getPhotoFileListAct.act";
	$.ajax({
		type:'POST',
		url:requestUrl,
		dataType:"json",
		data:{path:newPath},
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if($.isEmptyObject(data)){
				$(".MessageBox").show();
				$(".msg-content").html("该相册下没有照片");
				$("#photosDemo").hide();
				$("#folderName").html(folderName+"("+0+")");
			}else{
				paths = data;
				$(".MessageBox").hide();
				$("#photosDemo").show();
				var html="";
				for(var i=0;data.length>i;i++){
					if(data[i].pathtype=="folder"){
						html+="<div onclick=\"goFolder('"+i+"')\" class=\"photo-bg\" ><div class=\"photo-div\" ><div class=\"floder-div\" ></div></div><div style=\"text-align:center\" >"+data[i].name+"</div></div>";
					}
				}
				var num = 0;
				for(var i=0;data.length>i;i++){
					/* if(data[i].pathtype=="file"&&data[i].id!=""){ */
					if(data[i].pathtype=="file"){
						newPath = data[i].path;
						var name = "";
						if(data[i].name.length > 12){
							name = data[i].name.substr(0,12)+"...";
						}else{
							name = data[i].name;
						}
						if(data[i].good=='true'){
							html+="<div class=\"photo-bg\"  ><div class=\"photo-div\" ><img alt="+data[i].name+
							" class=\"photo-img\" src=\""+contextPath+"/tfd/system/photo/act/PhotoAct/getPhotoAct.act?photoPath="+
							data[i].path+"\"/></div><div style=\"text-align:center;\"  >"+name+
							"<div onclick=\"setCover('"+i+"')\" class=\"cover-div\" id=\"cover"+i+"\" title=\"设为封面\" ></div><div title=\"取消赞\" onclick=\"javascript:toGood('"+i+"')\" id=\"good"+i+"\" class=\"good-ok-div\"></div></div><div class=\"photo-info\" ><div>名称："+data[i].name+"</div><div>人员："+data[i].accountId+"</div><div>已赞："+data[i].goodNum+"</div><div>时间："+data[i].time+"</div></div></div>";
						}else{
							html+="<div class=\"photo-bg\"  ><div class=\"photo-div\" ><img alt="+data[i].name+
							" class=\"photo-img\" src=\""+contextPath+"/tfd/system/photo/act/PhotoAct/getPhotoAct.act?photoPath="+
							data[i].path+"\"/></div><div style=\"text-align:center;\"  >"+name+
							"<div onclick=\"setCover('"+i+"')\" class=\"cover-div\" id=\"cover"+i+"\" title=\"设为封面\" ></div><div title=\"赞\" onclick=\"javascript:toGood('"+i+"')\" id=\"good"+i+"\" class=\"good-div\"></div></div><div class=\"photo-info\" ><div>名称："+data[i].name+"</div><div>人员："+data[i].accountId+"</div><div>已赞："+data[i].goodNum+"</div><div>时间："+data[i].time+"</div></div></div>";
						}
						num++;
					}
				}
				$("#folderName").html(folderName+"("+num+")");
				$("#photosDemo").html(html);
			}
			ajaxLoadEnd();
		}
	});
	checkPriv();
	//加载扩展模块
	layer.config({
	    extend: 'extend/layer.ext.js'
	});

	//页面一打开就执行，放入ready是为了layer所需配件（css、扩展模块）加载完毕
	layer.ready(function(){ 
	    //使用相册
	    layer.photos({
	        photos: '#photosDemo'
	    });
	});
	$('#btn_back').click(function(){
		history.go(-1);
		return false;
	});
}
function goFolder(i){
	var newPath = paths[i].path;
	var reg = '/';
	newPath = newPath.replace(/\\/g,reg);
	window.location = contextPath+"/system/photo/manage.jsp?photoId="+photoId+"&folderName="+paths[i].name+"&path="+newPath;
}
function toGood(i){
	var id = paths[i].id;
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/updatePhotoInfoGoodAct.act";
	$.ajax({
		type:'POST',
		url:requestUrl,
		dataType:"text",
		data:{photoInfoId:id},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			doinit();
		}
	});
}
function checkPriv(){
	var requestUrl=contextPath+"/tfd/system/photo/act/PhotoAct/checkPrivAct.act";
	$.ajax({
		type:'POST',
		url:requestUrl,
		dataType:"text",
		data:{photoId:photoId},
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data == '1'){
				$("#go_upload").css("display","block");
				$("#newFolder").css("display","block");
			}else{
				$("#go_upload").css("display","none");
				$("#newFolder").css("display","none");
			}
		}
	});
}
function createFolder(){
	var folderName = $("#newFloderName").val();
	var newPath = path + "/" + folderName;
	var requestUrl= contextPath+'/tfd/system/photo/act/PhotoAct/createFolder.act';
	$.ajax({
		url:requestUrl,
		data:{path:newPath},
		type:"POST",
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=='1'){
				top.layer.msg("新建成功!");
				$(".btn-close").trigger("click");
				doinit();
			}
		}
	});
}
function uploadfiles(){
	 var requestUrl= contextPath+'/tfd/system/photo/act/PhotoAct/uploadfiles.act';
	 $.ajax({
		 url:requestUrl,
		 data:{path:path,attachId:$("#attachId").val()},
		 type:"POST",
		 dataType:"json",
		 async:false,
		 error:function(e){
		 	alert(e.message);
		 },
		 success:function(data){
		 	if(data=='1'){
		 		top.layer.msg("上传成功!");
		 		$(".btn-close").trigger("click");
		 		doinit();
		 		
			 }
		 }
	 });
}
function setCover(i){
	var newPath = paths[i].path;
	 var requestUrl= contextPath+'/tfd/system/photo/act/PhotoAct/setPhotoCoverAct.act';
	 $.ajax({
		 url:requestUrl,
		 data:{coverPath:newPath,photoId:photoId},
		 type:"POST",
		 dataType:"json",
		 async:false,
		 error:function(e){
		 	alert(e.message);
		 },
		 success:function(data){
		 	if(data=='1'){
		 		top.layer.msg("设置成功");
		 		doinit();
			 }
		 }
	 });
}