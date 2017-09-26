var key=0;  //记录ctrl
$(window).keydown(function(e){
        if(e.ctrlKey){
            key=1;
        }
    }).keyup(function(){
            key=0;
    });
	 $(function(){
    	loaded();
    	filesUpLoad("personfolder");
    	fileUploadByAttach("personfolder");
    });
	function loaded(){
		if(folderId!="null"){
			$('#folderTitle').html(folderName);
			doinit();
		}else{
			$(".MessageBox").show();
			$(".msg-content").html("请选择文件夹进行浏览");
			$("#myTable").hide();
			$(".table_btn").hide();
		}
		var copyJson = $(window.parent.frames.document).find("#copyJson").val();
		if(copyJson != ""){
			var jsons = eval("(" + copyJson + ")");
			if(jsons.type==1){
				$('#newChild').after("<button id='btn_copy_paste' style=\"margin-left:5px;\" class='btn btn-success'>粘贴</button>");
			}else{
				$('#newChild').after("<button id='btn_shera_paste' style=\"margin-left:5px;\"  class='btn btn-success'>粘贴</button>");
			}
		}
		$('#go_new').click(function(){
			window.location = contextPath+"/system/personfolder/manage/addfile.jsp?folderId="+folderId;
		});
		$('#newChild').click(function(){
			window.location = contextPath+"/system/personfolder/manage/addfolder.jsp?folderId="+folderId;
		});
		$(".f_li,.file_li").mouseover(function(){
			$(this).addClass("lihover");
		});
		$(".f_li,.file_li").mouseout(function(){
			$(this).removeClass("lihover");
		});
		$(".f_li").click(function(){
			showButton(1);
			if(key==1){
				$(this).addClass("clickli");
			}else{
				$(".f_li").removeClass("clickli");
				$(".file_li").removeClass("clicklis");
				$(this).addClass("clickli");
			}
		});
		$(".file_li").click(function(){
			showButton(2);
			if(key==1){
				$(this).addClass("clicklis");
			}else{
				$(".f_li").removeClass("clickli");
				$(".file_li").removeClass("clicklis");
				$(this).addClass("clicklis");
			}
		});
		$(".f_li").dblclick(function(){
			var f_Id = $(this).find('input').val();
			var f_Name = $(this).find('span').html();
			var f_No = $(this).find('i').html();
			window.location =contextPath+"/system/personfolder/manage/manage.jsp?folderId="+encodeURIComponent(f_Id)+"&folderName="+encodeURIComponent(f_Name)+"&folderNo="+encodeURIComponent(f_No);
		});
		$(".file_li").dblclick(function(){
			var fileId = $(this).find('input').val();
			window.location =contextPath+"/system/personfolder/manage/showFile.jsp?fileId="+encodeURIComponent(fileId);
		});
		$('#btn_back').click(function(){
			history.go(-1);
			return false;
		});
		$('#btn_uploads').bind("click",function(){
			var attId = $("#attachId").val().split(",");
			var attName = $("#attachName").val().split(",");
			var j = 0;
			for(var i = 0 ; i < attId.length-1; i++){
				var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/addFile.act';
		 		$.ajax({
		 				url:requestUrl,
		 				data:{
		 					fileName:attName[i],
		 					folderId:folderId,
		 					attachId:attId[i]
		 				},
		 				dataType:"json",
		 				async:false,
		 				error:function(e){
		 					alert(e.message);
		 				},
		 				success:function(data){
		 					if(data=='1'){
		 						j++;
		 					}
		 				}
		 		});
			}
			if(j==(attId.length-1)){
				top.layer.msg("添加成功");
				parent["left"].location=contextPath+"/system/personfolder/manage/folderDir.jsp";
				$(".btn_close").trigger("click");
				$("#btn_uploads").unbind("click");
				loaded();
			}
		});
		$('#checkAll').click(function(){
			if($('#checkAll').prop("checked")){
				$(".f_li").addClass("clickli");
				$(".file_li").addClass("clicklis");
				if(checkHaveFolder()){
					showButton(1);
				}else{
					showButton(2);
				}
			}else{
				$(".f_li").removeClass("clickli");
				$(".file_li").removeClass("clicklis");
				hideButton();
			}
		});
		$('#btn_del').bind("click",function(){
			var j = 0;
			var c_li = $(".clickli");
			var cs_li = $(".clicklis");
			var str = "";
			if(c_li.size() > 0&&cs_li.size()>0){
				str = "确认删除此文件夹及文件？删除后此文件夹下的子文件夹及文件将不可恢复！";
			}else if(c_li.size()>0&&cs_li.size() == 0){
				str = "确认删除此文件夹？删除后此文件夹下的子文件夹及子文件将不可恢复！";
			}else if(c_li.size()==0&&cs_li.size()>0){
				str = "确认删除此文件？";
			}
			if(confirm(str)){
				for(var i = 0 ; i < c_li.size() ; i++){
					var folderId = c_li.find('input').eq(i).val();
					var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/deleteFolder.act';
					$.ajax({
						url:requestUrl,
						data:{folderId:folderId},
						type:"POST",
						dataType:"json",
						async:false,
						error:function(e){
							alert(e.message);
						},
						success:function(data){
							if(data=='1'){
								j++;
							}
						}
					});	
				}
				for(var i = 0 ; i < cs_li.size() ; i++){
					var fileId = cs_li.find('input').eq(i).val();
					var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/deleteFile.act';
					$.ajax({
						url:requestUrl,
						data:{fileId:fileId},
						type:"POST",
						dataType:"json",
						async:false,
						error:function(e){
							alert(e.message);
						},
						success:function(data){
							if(data=='1'){
								j++;
							}
						}
					});	
				}
				if(j==(c_li.size()+cs_li.size())){
					top.layer.msg("删除成功");
					parent["left"].location=contextPath+"/system/personfolder/manage/folderDir.jsp";
					$("#btn_del").unbind("click");
					loaded();
				}
			}
		});
		$('#btn_update').click(function(){
			var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/updateFolder.act';
	 		$.ajax({
	 				url:requestUrl,
	 				data:{
	 					folderId:$('#folderId').val(),
	 					folderNo:$('#folderNo').val(),
	 					folderName:$('#folderName').val(),
	 					isPublic:'2'
	 				},
	 				dataType:"json",
	 				async:false,
	 				error:function(e){
	 					alert(e.message);
	 				},
	 				success:function(data){
	 					if(data=='1'){
	 						top.layer.msg('修改成功');
	 						parent["left"].location=contextPath+"/system/personfolder/manage/folderDir.jsp";
	 						$(".btn_close").trigger("click");
	 					}
	 				}
	 		});
		});
		$('#btn_copy').click(function(){
			var str = '{"data":[';
			var c_li = $(".clickli");
			for(var i = 0 ; i < c_li.size() ; i++){
				str += '{"id":"' + c_li.find('input').eq(i).val() +'","folder":"1"},';
			}
			var cs_li = $(".clicklis");
			for(var i = 0 ; i < cs_li.size() ; i++){
				str += '{"id":"' + cs_li.find('input').eq(i).val() +'","folder":"2"},';
			}
			str = str.substr(0,str.length-1);
			str += '],"type":"1"}';
			$(window.parent.frames.document).find("#copyJson").val(str);
			top.layer.msg("内容已复制，到需要目录可进行粘贴操作");
		});
		$('#btn_shera').click(function(){
			var str = '{"data":[';
			var c_li = $(".clickli");
			for(var i = 0 ; i < c_li.size() ; i++){
				str += '{"id":"' + c_li.find('input').eq(i).val() +'","folder":"1"},';
			}
			var cs_li = $(".clicklis");
			for(var i = 0 ; i < cs_li.size() ; i++){
				str += '{"id":"' + cs_li.find('input').eq(i).val() +'","folder":"2"},';
			}
			str = str.substr(0,str.length-1);
			str += '],"type":"2"}';
			$(window.parent.frames.document).find("#copyJson").val(str);
			top.layer.msg("内容已剪切，到需要目录可进行粘贴操作");
		});
		$('#btn_copy_paste').bind("click",function(){
			var copyJson = $(window.parent.frames.document).find("#copyJson").val();
			if(copyJson != ""){
				var jsons = eval("(" + copyJson + ")");
				var data = jsons.data;
				var i = 0;
				$.each(data,function(index,data){
					if(data.folder == "1"){
						var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/copyFolder.act';
				 		$.ajax({
				 				url:requestUrl,
				 				data:{
				 					folderPid:$('#folderId').val(),
				 					folderId:data.id
				 				},
				 				dataType:"json",
				 				async:false,
				 				error:function(e){
				 					alert(e.message);
				 				},
				 				success:function(data){
				 					if(data=='1'){
				 						i++;
				 					}
				 				}
				 		});
					}else{
						var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/copyFile.act';
				 		$.ajax({
				 				url:requestUrl,
				 				data:{
				 					folderId:$('#folderId').val(),
				 					fileId:data.id
				 				},
				 				dataType:"json",
				 				async:false,
				 				error:function(e){
				 					alert(e.message);
				 				},
				 				success:function(data){
				 					if(data=='1'){
				 						i++;
				 					}
				 				}
				 		});
					}
				});
				$(window.parent.frames.document).find("#copyJson").val("");
				$("#btn_shera_paste").css("display","none");
				if(data.length == i){
					top.layer.msg("粘贴成功");
					parent["left"].location=contextPath+"/system/personfolder/manage/folderDir.jsp";
					$("#btn_copy_paste").unbind("click");
					loaded();
				}
			}
		});
		$('#btn_shera_paste').bind("click",function(){
			var copyJson = $(window.parent.frames.document).find("#copyJson").val();
			if(copyJson != ""){
				var jsons = eval("(" + copyJson + ")");
				var data = jsons.data;
				var i = 0;
				$.each(data,function(index,data){
					if(data.folder == "1"){
						var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/sheraFolder.act';
				 		$.ajax({
				 				url:requestUrl,
				 				data:{
				 					folderPid:$('#folderId').val(),
				 					folderId:data.id
				 				},
				 				dataType:"json",
				 				async:false,
				 				error:function(e){
				 					alert(e.message);
				 				},
				 				success:function(data){
				 					if(data=='1'){
				 						i++;
				 					}
				 				}
				 		});
					}else{
						var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/sheraFile.act';
				 		$.ajax({
				 				url:requestUrl,
				 				data:{
				 					folderId:$('#folderId').val(),
				 					fileId:data.id
				 				},
				 				dataType:"json",
				 				async:false,
				 				error:function(e){
				 					alert(e.message);
				 				},
				 				success:function(data){
				 					if(data=='1'){
				 						i++;
				 					}
				 				}
				 		});
					}
				});
				$(window.parent.frames.document).find("#copyJson").val("");
				$("#btn_shera_paste").css("display","none");
				if(data.length == i){
					top.layer.msg("粘贴成功");
					parent["left"].location=contextPath+"/system/personfolder/manage/folderDir.jsp";
					$("#btn_shera_paste").unbind("click");
					loaded();
				}
			}
		});
	};
	function doinit(){
		$('#folderId').val(folderId);
		$('#folderNo').val(folderNo);
		$('#folderName').val(folderName);
		checkPid(folderId);
		var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/getFolderFileListByPriv.act';
		$.ajax({
			url:requestUrl,
			data:{folderId:folderId,isPublic:'2'},
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
					$("#myTable").hide();
				}else{
					$(".MessageBox").hide();
					$("#myTable").show();
					var tableHtml = "<ul style='margin-left:-40px' >";
					$.each(data,function(index,data){
						if(data.isParent == 'true'){
							var name = data.name;
							if(data.name.length > 6){
								name = data.name.substr(0,5)+"...";
							}
							tableHtml += "<li class='f_li' title='"+data.name+"' ><img width='80' height='70' style='margin-left:10px;margin-top:5px;' src='"+imgPath+"/filetype/folder.png' /><br/><div style='text-align:center' >"+name+"</div><input type='hidden' value='"+data.id+"' ><span style='display:none'>"+data.name+"</span><i style='display:none;'>"+data.No+"</i></li>";
						}else{
							var name = data.name;
							if(data.name.length > 6){
								name = data.name.substr(0,6)+"...";
							}
							tableHtml += "<li class='file_li' title='"+data.name+"' ><img width='60' height='70' style='margin-top:5px;' src='"+imgPath+"/filetype/text.png' /><br/><div style='text-align:center' >"+name+"</div><input type='hidden' value='"+data.id+"' ></li>";
						}
					});
					tableHtml +="</ul>" ;
					$("#myTable").html(tableHtml);
				}
			}
		});	
	}
	function showButton(type){
		$("#btn_del").css("display","block");
		$("#btn_shera").css("display","block");
		if(type==2){
			$("#btn_copy").css("display","block");
		}else{
			$("#btn_copy").css("display","none");
		}
	}
	function hideButton(){
		$("#btn_del").css("display","none");
		$("#btn_copy").css("display","none");
		$("#btn_shera").css("display","none");
	}
	function checkPid(id){
		var requestUrl= contextPath+'/tfd/system/folder/act/FolderAct/checkFolderPid.act';
		$.ajax({
			url:requestUrl,
			data:{folderId:folderId},
			type:"POST",
			dataType:"json",
			error:function(e){
				alert(e.message);
			},
			success:function(data){
				if(data=='1'){
					$("#folderNo").prop("disabled","disabled");
					$("#folderName").prop("disabled","disabled");
				}else{
					$("#folderNo").removeProp("disabled");
					$("#folderName").removeProp("disabled");
				}
			}
		});
	}
	function checkHaveFolder(){
		var flag = false;
		var c_li = $(".clickli");
		if(c_li.size() > 0){
			flag = true;
		}
		return flag;
	}
