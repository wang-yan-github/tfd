var managePriv;
var downPriv;

function doinit(orderBy,ascDesc,searchContent){
		var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/getFile.act?searchContent='+searchContent;
		$.ajax({
			url:requestUrl,
			data:{path:path},
			type:"POST",
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if($.isEmptyObject(data)){
					$(".MessageBox").show();
					$(".msg-content").html("该目录下没有文件");
					$('#showPath').html("文件数量(0)");
					$('.panel-title').html(path);
					$("#myTable").hide();
					$("#checkAll").prop("disabled","true");
				}else{
					$(".MessageBox").hide();
					$("#myTable").show();
					$('#sortName,#sortType,#sortSize,#sortTime,#sortAsc,#sortDesc').prop("style","background-color:#FFF;color:#333333;");
					if(orderBy=="名称"){
						$('#sortName').prop("style","background-color:#347cee;color:#FFF;");
						if(ascDesc == "升序"){
							$('#sortAsc').prop("style","background-color:#347cee;color:#FFF;");
							for(var i = 0 ; i < data.length ; i++){
								for(var j = 0 ; j < data.length - 1 ; j++){
									if(data[j].name < data[j+1].name){
										var tmp = data[j];
										data[j] = data[j+1];
										data[j+1] = tmp;
									}
								}
							}
						}else{
							$('#sortDesc').prop("style","background-color:#347cee;color:#FFF;");
							for(var i = 0 ; i < data.length ; i++){
								for(var j = 0 ; j < data.length - 1 ; j++){
									if(data[j].name > data[j+1].name){
										var tmp = data[j];
										data[j] = data[j+1];
										data[j+1] = tmp;
									}
								}
							}
						}
						
					}else if(orderBy=="类型"){
						$('#sortType').prop("style","background-color:#347cee;color:#FFF;");
						if(ascDesc == "升序"){
							$('#sortAsc').prop("style","background-color:#347cee;color:#FFF;");
							for(var i = 0 ; i < data.length ; i++){
								for(var j = 0 ; j < data.length - 1 ; j++){
									if(data[j].type < data[j+1].type){
										var tmp = data[j];
										data[j] = data[j+1];
										data[j+1] = tmp;
									}
								}
							}
						}else{
							$('#sortDesc').prop("style","background-color:#347cee;color:#FFF;");
							for(var i = 0 ; i < data.length ; i++){
								for(var j = 0 ; j < data.length - 1 ; j++){
									if(data[j].type > data[j+1].type){
										var tmp = data[j];
										data[j]= data[j+1];
										data[j+1] = tmp;
									}
								}
							}
						}
					}else if(orderBy=="大小"){
						$('#sortSize').prop("style","background-color:#347cee;color:#FFF;");
						if(ascDesc == "升序"){
							$('#sortAsc').prop("style","background-color:#347cee;color:#FFF;");
							for(var i = 0 ; i < data.length ; i++){
								for(var j = 0 ; j < data.length - 1 ; j++){
									if(data[j].size < data[j+1].size){
										var tmp = data[j];
										data[j] = data[j+1];
										data[j+1] = tmp;
									}
								}
							}
						}else{
							$('#sortDesc').prop("style","background-color:#347cee;color:#FFF;");
							for(var i = 0 ; i < data.length ; i++){
								for(var j = 0 ; j < data.length - 1 ; j++){
									if(data[j].size > data[j+1].size){
										var tmp = data[j];
										data[j] = data[j+1];
										data[j+1] = tmp;
									}
								}
							}
						}
					}else if(orderBy=="时间"){
						$('#sortTime').prop("style","background-color:#347cee;color:#FFF;");
						if(ascDesc == "升序"){
							$('#sortAsc').prop("style","background-color:#347cee;color:#FFF;");
							for(var i = 0 ; i < data.length ; i++){
								for(var j = 0 ; j < data.length - 1 ; j++){
									if(data[j].time < data[j+1].time){
										var tmp = data[j];
										data[j] = data[j+1];
										data[j+1] = tmp;
									}
								}
							}
						}else{
							$('#sortDesc').prop("style","background-color:#347cee;color:#FFF;");
							for(var i = 0 ; i < data.length ; i++){
								for(var j = 0 ; j < data.length - 1 ; j++){
									if(data[j].time > data[j+1].time){
										var tmp = data[j];
										data[j] = data[j+1];
										data[j+1] = tmp;
									}
								}
							}
						}
					}
					var titleHtml = "文件数量("+data.length+")";
					var tableHtml = "";
					diskIds = data;
					$.each(data,function(index,data){
						tableHtml += "<tr id=\"tr"+index+"\" onclick=\"javascript:findOne('"+index+"')\" ><td width=\"12%\" style=\"\"  ><input type='checkbox' style='margin-left:30%;'   class='checkbox'  /></td><td id=\"disk"+index+"\" width=\"46%\"  onmouseover=\"javascript:showDiskMenu('"+index+"');\" onmousemove=\"javascript:showDiskMenu('"+index+"');\" onmouseout=\"javascript:hideDiskMenus()\" >"+data.name+"</td><td width=\"12%\">"+data.type+"</td><td width=\"15%\" >"+data.size+"</td><td width=\"15%\" >"+data.time+"</td></tr>";
					});
					$('#showPath').html(titleHtml);
					$('#myTable').html(tableHtml);
					$('#folderName').html(path);
				}
			}
		});	
	}
	function checkAll(){
		if($('#checkAll').prop("checked")){
			$(".checkbox").prop("checked",true);
			if(managePriv == 1){
				$("#btn_copy").css("display","block");
				$("#btn_cut").css("display","block");
				$("#btn_del").css("display","block");
			}
			if(downPriv == 1){
				$("#btn_download").css("display","block");
			}
		}else{
			$(".checkbox").prop("checked",false);
			$("#btn_copy").css("display","none");
			$("#btn_cut").css("display","none");
			$("#btn_del").css("display","none");
			$("#btn_download").css("display","none");
		}
	}
	function changeSort(i){
		if(i=='1'){
			var color = $('#sortAsc').css("backgroundColor");
			if(color == 'rgb(52, 124, 238)' ){
				searchDisk("名称","升序");
			}else{
				searchDisk("名称","降序");
			}
		}else if(i=='2'){
			var color = $('#sortAsc').css("backgroundColor");
			if(color == 'rgb(52, 124, 238)' ){
				searchDisk("类型","升序");
			}else{
				searchDisk("类型","降序");
			}
		}else if(i=='3'){
			var color = $('#sortAsc').css("backgroundColor");
			if(color == 'rgb(52, 124, 238)' ){
				searchDisk("大小","升序");
			}else{
				searchDisk("大小","降序");
			}
		}else if(i=='4'){
			var color = $('#sortAsc').css("backgroundColor");
			if(color == 'rgb(52, 124, 238)' ){
				searchDisk("时间","升序");
			}else{
				searchDisk("时间","降序");
			}
		}else if(i=='5'){
			var color1 = $('#sortName').css("backgroundColor");
			var color2 = $('#sortType').css("backgroundColor");
			var color3 = $('#sortSize').css("backgroundColor");
			if(color1 == 'rgb(52, 124, 238)' ){
				searchDisk("名称","升序");
			}else if(color2 == 'rgb(52, 124, 238)'){
				searchDisk("类型","升序");
			}else if(color3 == 'rgb(52, 124, 238)'){
				searchDisk("大小","升序");
			}else{
				searchDisk("时间","升序");
			}
		}else if(i=='6'){
			var color1 = $('#sortName').css("backgroundColor");
			var color2 = $('#sortType').css("backgroundColor");
			var color3 = $('#sortSize').css("backgroundColor");
			if(color1 == 'rgb(52, 124, 238)' ){
				searchDisk("名称","降序");
			}else if(color2 == 'rgb(52, 124, 238)'){
				searchDisk("类型","降序");
			}else if(color3 == 'rgb(52, 124, 238)'){
				searchDisk("大小","降序");
			}else{
				searchDisk("时间","降序");
			}
		}
	}
	function checkPriv(){
		var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/checkPriv.act';
		$.ajax({
			url:requestUrl,
			data:{diskId:diskId,privId:'2'},
			type:"POST",
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data == '1'){
					$("#newFolder").css("display","block");
					$("#editFolder").css("display","block");
					$("#delFolder").css("display","block");
					managePriv = 1;
				}else{
					managePriv = 0;
				}
			}
		});
		var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/checkPriv.act';
		$.ajax({
			url:requestUrl,
			data:{diskId:diskId,privId:'3'},
			type:"POST",
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data == '1'){
					$("#go_upload").css("display","block");
				}
			}
		});
		var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/checkPriv.act';
		$.ajax({
			url:requestUrl,
			data:{diskId:diskId,privId:'4'},
			type:"POST",
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data == '1'){
					downPriv = 1;
				}else{
					downPriv = 0;
				}
			}
		});
	}
	function deleteFloder(){
		if(confirm("确定删除?")){
			var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/deleteFloder.act';
			$.ajax({
				url:requestUrl,
				data:{path:path},
				type:"POST",
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					if(data=='1'){
						top.layer.msg("删除成功!");
						path = path.substr(0,path.lastIndexOf("/"));
						parent["right"].location=contextPath+"/system/publicdisk/manage/manage.jsp?path="+encodeURIComponent(path)+"&orderBy="+orderBy+"&ascDesc="+ascDesc+"&diskId="+diskId;
						parent["left"].location = contextPath+"/system/publicdisk/manage/diskDir.jsp";
					}
				}
			});
		}
	}
	function delFiles(){
		if(confirm("确定删除?")){
			var files = $(".checkbox");
			var k = 0;
			var j = 0;
			for(var i = 0 ; i < files.size(); i++){
				if(files.eq(i).prop("checked")){
					j++;
					var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/delFile.act';
					$.ajax({
						url:requestUrl,
						data:{path:diskIds[i].id},
						type:"POST",
						dataType:"json",
						async:false,
						error:function(e){
							alert(e.message);
						},
						success:function(data){
							if(data==1){
								k++;
							}
						}
					});
				}
			}
			if(k==j){
				top.layer.msg("删除成功");
				parent["right"].location=contextPath+"/system/publicdisk/manage/manage.jsp?path="+encodeURIComponent(path)+"&orderBy="+orderBy+"&ascDesc="+ascDesc+"&diskId="+diskId;
			}
		}
	}
	function delFile(id){
		if(confirm("确定删除?")){
			var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/delFile.act';
			$.ajax({
				url:requestUrl,
				data:{path:id},
				type:"POST",
				dataType:"json",
				async:false,
				error:function(e){
					alert(e.message);
				},
				success:function(data){
					if(data==1){
						top.layer.msg("删除成功");
						parent["right"].location=contextPath+"/system/publicdisk/manage/manage.jsp?path="+encodeURIComponent(path)+"&orderBy="+orderBy+"&ascDesc="+ascDesc+"&diskId="+diskId;
					}
				}
			});
		}
	}
	function copyFile(){
		var files = $(".checkbox");
		var str = "{data:[";
		for(var i = 0 ; i < files.size(); i++){
			if(files.eq(i).prop("checked")){
				var id = diskIds[i].id;
				var b='\\\\';
				id = id.replace(/\\/g,b);
				str += '{"id":"'+id+'"},';
			}
		}
		str = str.substr(0,str.length-1);
		str += '],"type":"1"}';
		$(window.parent.frames.document).find("#copyJson").val(str);
		top.layer.msg("内容已复制，到需要目录可进行粘贴操作");
	}
	function cutFile(){
		var files = $(".checkbox");
		var str = "{data:[";
		for(var i = 0 ; i < files.size(); i++){
			if(files.eq(i).prop("checked")){
				var id = diskIds[i].id;
				var b='\\\\';
				id = id.replace(/\\/g,b);
				str += '{"id":"'+id+'"},';
			}
		}
		str = str.substr(0,str.length-1);
		str += '],"type":"2"}';
		$(window.parent.frames.document).find("#copyJson").val(str);
		top.layer.msg("内容已剪切，到需要目录可进行粘贴操作");
	}
	function pasteCopyFile(){
		var copyJson = $(window.parent.frames.document).find("#copyJson").val();
		if(copyJson != ""){
			var jsons = eval("(" + copyJson + ")");
			var data = jsons.data;
			var num = 0;
			$.each(data,function(index,data){
				var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/copyFile.act';
				$.ajax({
					url:requestUrl,
					data:{path:path,diskId:data.id},
					type:"POST",
					dataType:"json",
					async:false,
					error:function(e){
						alert(e.message);
					},
					success:function(data){
						if(data==1){
							num++;
						}
					}
				});
			});
			$(window.parent.frames.document).find("#copyJson").val("");
			if(num==data.length){
				top.layer.msg("复制成功");
				parent["right"].location=contextPath+"/system/publicdisk/manage/manage.jsp?path="+encodeURIComponent(path)+"&orderBy="+orderBy+"&ascDesc="+ascDesc+"&diskId="+diskId;
			}
		}
	}
	function pasteCutFile(){
		var copyJson = $(window.parent.frames.document).find("#copyJson").val();
		if(copyJson != ""){
			var jsons = eval("(" + copyJson + ")");
			var data = jsons.data;
			var num = 0;
			$.each(data,function(index,data){
				var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/cutFile.act';
				$.ajax({
					url:requestUrl,
					data:{path:path,diskId:data.id},
					type:"POST",
					dataType:"json",
					async:false,
					error:function(e){
						alert(e.message);
					},
					success:function(data){
						if(data==1){
							num++;
						}
					}
				});
			});
			$(window.parent.frames.document).find("#copyJson").val("");
			if(num==data.length){
				top.layer.msg("剪切成功");
				parent["right"].location=contextPath+"/system/publicdisk/manage/manage.jsp?path="+encodeURIComponent(path)+"&orderBy="+orderBy+"&ascDesc="+ascDesc+"&diskId="+diskId;
			}
		}
	}

	function downloadFiles(){
		var files = $(".checkbox");
		var ids = "";
		for(var i = 0 ; i < files.size(); i++){
			if(files.eq(i).prop("checked")){
				ids += diskIds[i].id+",";
			}
		}
		window.location.href=contextPath+"/com/system/filetool/UpFileTool/downloadFiles.act?path="+ids;
	}
	
	function setFloderName(){
		var floderName = path.substr(path.lastIndexOf("/")+1,path.length);
		if(floderName==''){
			var newPath = path.substr(0,path.lastIndexOf("/"));
			floderName = newPath.substr(newPath.lastIndexOf("/")+1,newPath.length);
		}
		$("#folderName").val(floderName);
	}
	function createFolder(){
		var folderName = $("#newFloderName").val();
		var newPath = path + "/" + folderName;
		var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/createFolder.act';
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
					top.layer.msg("新建成功");
					parent["right"].location=contextPath+"/system/publicdisk/manage/manage.jsp?path="+encodeURIComponent(path)+"&orderBy="+orderBy+"&ascDesc="+ascDesc+"&diskId="+diskId;
					parent["left"].location = contextPath+"/system/publicdisk/manage/diskDir.jsp";
				}
			}
		});
	}
	function editFolder(){
		var newName = $("#folderName").val();
		var folderName = path.substr(path.lastIndexOf("/")+1,path.length);
		path = path.substr(0,path.lastIndexOf("/"));
		var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/changeFolderName.act';
		$.ajax({
			url:requestUrl,
			data:{path:path,folderName:folderName,newName:newName},
			type:"POST",
			dataType:"json",
			async:false,
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					top.layer.msg("修改成功");
					parent["right"].location=contextPath+"/system/publicdisk/manage/manage.jsp?path="+encodeURIComponent(path)+"&orderBy="+orderBy+"&ascDesc="+ascDesc+"&diskId="+diskId;
					parent["left"].location = contextPath+"/system/publicdisk/manage/diskDir.jsp";
				}
			}
		});
	}
	function uploadfiles(){
		 var requestUrl= contextPath+'/tfd/system/publicdisk/act/PublicDiskAct/uploadfiles.act';
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
			 		top.layer.msg("上传成功");
					parent["right"].location=contextPath+"/system/publicdisk/manage/manage.jsp?path="+encodeURIComponent(path)+"&orderBy="+orderBy+"&ascDesc="+ascDesc+"&diskId="+diskId;
					parent["left"].location = contextPath+"/system/publicdisk/manage/diskDir.jsp";
				 }
			 }
		 });
	}
	
	function searchDisk(orderBy,ascDesc){
		var searchContent = encodeURIComponent($('#searchContent').val());
		doinit(orderBy,ascDesc,searchContent);
		var copyJson = $(window.parent.frames.document).find("#copyJson").val();
		if(copyJson != ""){
			var jsons = eval("(" + copyJson + ")");
			if(jsons.type==1){
				$('#btn_copy_paste').css("display","block");
			}else{
				$('#btn_cut_paste').css("display","block");
			}
		}
		var jsitems = $(".checkbox");
		for (var i = 0; i < jsitems.length; i++) {
        	jsitems[i].onclick = function(e){
            findOne(i);
   			//阻止冒泡,避免行点击事件中,直接选择选框无效
            e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble=true;
        };
    }
	}
	
	function clearUser(){
		$("#accountId").val("");
		$("#userName").val("");
	}
	function clearDept(){
		$("#deptPriv").val("");
		$("#deptName").val("");
	}
	function clearPriv(){
		$("#userPriv").val("");
		$("#userPrivName").val("");
	}
	function showDiskMenu(i){
		var extName = diskIds[i].type;
		createEditDivs(i,extName);
		var cityObj = $("#disk"+i);
		var cityOffset = $("#disk"+i).offset();
		ev= arguments.callee.caller.arguments[0]  || window.event; 
		var mousePos = mouseCoords(ev); 
		$("#menuDiv").css({left:(mousePos.x-40) + "px", top:cityOffset.top + cityObj.outerHeight()-20 + "px"}).show();
	}
	function stillDiskMenu(){
		$("#menuDiv").css("display","block");
	}
	function hideDiskMenus(){
		$("#menuDiv").css("display","none");
	}
	function createEditDivs(i,extName){
		var path = diskIds[i].id;
		var b='\\\\';
		path = path.replace(/\\/g,b);
		var divHtml = "<div id=\"menuDiv\" onmouseout=\"javascript:hideDiskMenus()\"   onmouseover=\"javascript:stillDiskMenu()\"  class=\"editmenu\" >"+
		"<div class=\"menuClass\" onclick=\"javascript:openDiskFile('"+path+"','"+extName+"','1');\"  >打开</div>";
		if(extName=="doc"||extName=="docx"||extName=="xls"||extName=="xlsx"||extName=="ppt"||extName=="pptx"){
	    	divHtml+="<div class=\"menuClass\" onclick=\"javascript:openDiskFile('"+path+"','"+extName+"','4');\"  >编辑</div>\n";
		}
		divHtml+="<div  class=\"menuClass\" onclick=\"javascript:delFile('"+path+"')\" >删除</div>"+
		"<a type=\"application/msword\" href='"+contextPath+"/com/system/filetool/UpFileTool/doDiskDownFile.act?path="+path+"' ><div class=\"menuClass\">下载</div></a></div>";
		$('#attachHtml').html(divHtml);
	}
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
	
	function findOne(i){
		if($("#tr"+i).find("input[type=checkbox]").prop("checked")){
			$("#tr"+i).find("input[type=checkbox]").prop("checked",false);
		}else{
			$("#tr"+i).find("input[type=checkbox]").prop("checked",true);
		}
		var files = $(".checkbox");
		var flag = false;
		for(var i = 0 ; i < files.size(); i++){
			if(files.eq(i).prop("checked")){
				flag = true;
				break;
			}
		}
		if(flag){
			if(managePriv == 1){
				$("#btn_copy").css("display","block");
				$("#btn_cut").css("display","block");
				$("#btn_del").css("display","block");
			}
			if(downPriv == 1){
				$("#btn_download").css("display","block");
			}
		}else{
			$("#btn_copy").css("display","none");
			$("#btn_cut").css("display","none");
			$("#btn_del").css("display","none");
			$("#btn_download").css("display","none");
		}
	}
	function openDiskFile(path,extName,privFlag){
		var b='\\\\';
		path = path.replace(/\\/g,b);
		if(extName=="txt")
	    {
	        window.open(contextPath+"/system/publicdisk/controldoc/text.jsp?path="+path+"&privFlag="+privFlag);
	    }else   if(extName=="jpg"||extName=="gif"||extName=="png"||extName=="bmp"||extName=="tif")
	    {
	        window.open(contextPath+"/system/publicdisk/controldoc/img.jsp?path="+path+"&privFlag="+privFlag);
	    }else if(extName=="pdf")
		{
			window.open(contextPath+"/system/publicdisk/controldoc/pdf.jsp?path="+path+"&extName="+extName+"&privFlag="+privFlag);
		}else if(extName=="doc"||extName=="docx")
		{
		    window.open(contextPath+"/system/publicdisk/controldoc/word.jsp?path="+path+"&extName="+extName+"&privFlag="+privFlag);
		}else if(extName=="xls"||extName=="xlsx")
		{
		    window.open(contextPath+"/system/publicdisk/controldoc/excel.jsp?path="+path+"&extName="+extName+"&privFlag="+privFlag);
		}else if(extName=="ppt"||extName=="pptx")
		{
		    window.open(contextPath+"/system/publicdisk/controldoc/ppt.jsp?path="+path+"&extName="+extName+"&privFlag="+privFlag);
		}else{
			top.layer.msg("暂时无法打开该类型的文档");
		}
	}
	
