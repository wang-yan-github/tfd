function createHtml(accountId){
	var html = "<input type=\"hidden\" id=\"btn_show\" data-toggle=\"modal\" data-target=\"#personalModel\" >"+ 
			"<div class=\"modal fade\" id=\"personalModel\" tabindex=\"-1\" role=\"dialog\"  aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"z-index:9999;margin-top:50px;\">\n"+
			   "<div class=\"modal-dialog\" style=\"width:462px;\" >\n"+
			      "<div class=\"modal-content\">\n"+
			         "<div class=\"modal-body\" style=\"height:620px;width:460px;margin:0px;padding:0px;\">\n"+
			         "<div class=\"close-div\" data-dismiss=\"modal\" aria-hidden=\"true\"></div>"+
			         	"<div class=\"class_userinfo\" >"+
			         		"<div class=\"userinfo_top\" ></div>"+
			         		"<div class=\"userinfo_bottom\" >"+
			         			"<div class=\"infobottom_left\">"+
			         			"<img height=\"80\" width=\"80\" id=\"headImg\" onerror=\"this.src='"+imgPath + "/personal/error.jpg'\" class=\"head_img\" />"+
			         			"</div>"+
			         			"<div class=\"infobottom_right\" >"+
			         				"<div class=\"person\" id=\"userName\" ></div>"+
			         				"<div class=\"department\" id=\"department\" ></div>"+
			         				"<div class=\"sign\" id=\"sign\" ></div>"+
			         			"</div>"+
			         		"</div>"+
			         	"</div>"+
			         	"<div class=\"class_center\" >"+
			         		"<div class=\"center_top\" >"+
			         			"<ul><li class=\"clickLi\" id=\"person_li\" >资料</li><li id=\"dynamic_li\" >动态</li><li id=\"otherInfo_li\" >标签</li><li id=\"knowledge_li\" >知识</li><li id=\"files_li\" >档案</li></ul>"+
			         		"</div>"+
			         		"<div class=\"center_bottom\" style=\"padding-top:0px;\">"+
			         			"<table class=\"persontable\">"+
								"<tr><td class=\"td1\" >姓&nbsp;&nbsp;&nbsp;&nbsp;名:</td><td class=\"td2\" id=\"personName\" ></td></tr>"+
								"<tr><td class=\"td1\" >性&nbsp;&nbsp;&nbsp;&nbsp;别:</td><td class=\"td2\" id=\"personSex\" ></td></tr>"+
								"<tr><td class=\"td1\" >手&nbsp;&nbsp;&nbsp;&nbsp;机:</td><td class=\"td2\" id=\"personPhone\" ></td></tr>"+
								"<tr><td class=\"td1\" ></td><td class=\"td2\"></td></tr>"+
								"<tr><td class=\"td1\" >角&nbsp;&nbsp;&nbsp;&nbsp;色:</td><td class=\"td2\" id=\"personPriv\" ></td></tr>"+
								"<tr><td class=\"td1\" >部&nbsp;&nbsp;&nbsp;&nbsp;门:</td><td class=\"td2\" id=\"personDept\" ></td></tr>"+
								"<tr><td class=\"td1\" >领&nbsp;&nbsp;&nbsp;&nbsp;导:</td><td class=\"td2\" id=\"personLead\" ></td></tr>"+
								"<tr><td class=\"td1\" ></td><td class=\"td2\"></td></tr>"+
								"<tr><td class=\"td1\" >QQ:</td><td class=\"td2\" id=\"personQQ\" ></td></tr>"+
								"<tr><td class=\"td1\" >Email:</td><td class=\"td2\" id=\"personEmail\" ></td></tr>"+
								"<tr><td class=\"td1\" >生&nbsp;&nbsp;&nbsp;&nbsp;日:</td><td class=\"td2\" id=\"personDay\" ></td></tr>"+
								"<tr><td class=\"td1\" >住&nbsp;&nbsp;&nbsp;&nbsp;址:</td><td class=\"td2\" id=\"personAdd\" ></td></tr>"+
								"<tr><td class=\"td1\" >家庭电话:</td><td class=\"td2\" id=\"personTel\" ></td></tr>"+
								"</table>"+
								"<div class=\"otherinfotable\" >"+
									"<div class=\"markstitle\" >大家给TA的标签是:</div>"+
									"<div class=\"markslist\" ></div>"+
									"<div class=\"markadd\" >"+
										"<span class=\"markaddtitle\" >给TA贴上标签:</span>"+
										"<input type=\"text\" class=\"form-control input-sm \" id=\"markContent\" />"+
										"<input type=\"button\" class=\"btn btn-default btn-sm\" onclick=\"javascript:addMark('"+accountId+"');\" id=\"btn_addMark\" value=\"贴上\" />"+
									"</div>"+
								"</div>"+
								"<div class=\"knowledge\" >"+
								"</div>"+
								"<div class=\"dynamictable\" >"+
								"</div>"+
								"<div class=\"filestable\" >"+
								"</div>"+
			         		"</div>"+
			         	"</div>"+
			         "</div>\n"+     
		         "</div>\n"+
		      "</div>";
	if(top.$("#userInfoDiv").prop("id")!="userInfoDiv"){
		top.$("body").append("<div id=\"userInfoDiv\" ></div>");
	}
	top.$("#userInfoDiv").html(html);
}

function getPersonalInfo(accountId){
	var requestUrl = contextPath + "/tfd/system/setuser/act/SetUserAct/getPersonalInfoById.act";
	$.ajax({
		url:requestUrl,
		type:"POST",
		dataType:"json",
		data:{accountId:accountId},
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data.headImg!=""&&data.headImg!=null&&data.headImg!="null"){
				//if(CheckImgExists(contextPath + "/attachment/userinfo/"+data.headImg)){
					top.$("#headImg").prop("src",contextPath + "/attachment/userinfo/"+data.headImg);
				//}else{
					//top.$("#headImg").prop("src",imgPath + "/personal/error.jpg");
				//}
			}else{
				top.$("#headImg").prop("src",imgPath + "/personal/default.jpg");
			}
			top.$("#userName").html(data.name+"<span class=\"userinfo\" >"+data.phone+"</span>");
			top.$("#department").html(data.department+"<span class=\"userinfo\" >"+data.role+"</span>");
			top.$("#sign").html(data.sign);
			top.$("#personName").html(data.name);
			top.$("#personSex").html(data.sex);
			top.$("#personPhone").html(data.phone);
			top.$("#personPriv").html(data.role);
			top.$("#personDept").html(data.department);
			top.$("#personLead").html(data.lead);
			top.$("#personQQ").html(data.qq);
			top.$("#personEmail").html(data.email);
			top.$("#personDay").html(data.birthday);
			top.$("#personAdd").html(data.homeAdd);
			top.$("#personTel").html(data.work_phone);
		}
	});
}

function showPersonal(accountId){
	createHtml(accountId);
	getPersonalInfo(accountId);
	top.$("#btn_show").trigger("click");
	top.$(".center_top ul li").click(function(){
		top.$(".center_top ul li").removeClass("clickLi");
		$(this).addClass("clickLi");
	});
	top.$("#person_li").click(function(){
		top.$(".persontable").show();
		top.$(".otherinfotable,.knowledge").hide();
	});
	top.$("#otherInfo_li").click(function(){
		top.$(".persontable,.knowledge").hide();
		top.$(".otherinfotable").show();
		getOtherInfo(accountId);
	});
	top.$("#knowledge_li").click(function(){
		top.$(".persontable,.otherinfotable").hide();
		top.$(".knowledge").show();
	});
	top.$("#dynamic_li").click(function(){
		top.$(".persontable,.otherinfotable,.knowledge,.filestable").hide();
		top.$(".dynamictable").show();
	});
	top.$("#files_li").click(function(){
		top.$(".persontable,.otherinfotable,.knowledge,.dynamictable").hide();
		top.$(".filestable").show();
	});
}

function getOtherInfo(accountId){
	var requestUrl = contextPath + "/tfd/system/setuser/act/SetUserAct/getMarksById.act";
	$.ajax({
		url:requestUrl,
		type:"POST",
		dataType:"json",
		data:{accountId:accountId},
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			var markHtml = "<ul>";
			$.each(data,function(index,data){
				if(data.islike=='1'){
					markHtml += "<li onclick=\"javascript:doMarkGood('"+accountId+"','"+data.mark_id+"','0');\" class=\"likes\" >"+data.content+"<span class=\"badge\">"+data.likes+"</span></li>";
				}else{
					markHtml += "<li onclick=\"javascript:doMarkGood('"+accountId+"','"+data.mark_id+"','1');\" >"+data.content+"<span class=\"badge\">"+data.likes+"</span></li>";
				}
			});
			markHtml += "</ul>";
			if(markHtml == "<ul></ul>"){
				markHtml = "<div style=\"width:100%;text-align:center;\" >暂无标签</div>";
			}
			top.$(".markslist").html(markHtml);
		}
	});
}

function doMarkGood(accountId,markId,type){
	var requestUrl = contextPath + "/tfd/system/setuser/act/SetUserAct/doMarkGood.act";
	$.ajax({
		url:requestUrl,
		type:"POST",
		dataType:"json",
		data:{markId:markId,islike:type},
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=='-1'){
				top.layer.msg("不能对自己的标签点赞",function(){});
			}else if(data=='0'){
				top.layer.msg("点赞失败");
			}else{
				if(type=='0'){
					top.layer.msg("取消点赞成功");
				}else{
					top.layer.msg("点赞成功");
				}
			};
			getOtherInfo(accountId);
		}
	});
}

function addMark(accountId){
	var content = $("#markContent").val();
	if(content == ""){
		top.layer.msg("请填写标签内容",function(){});
		return false;
	}
	var requestUrl = contextPath + "/tfd/system/setuser/act/SetUserAct/createMark.act";
	$.ajax({
		url:requestUrl,
		type:"POST",
		dataType:"json",
		data:{accountId:accountId,content:content},
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=='-1'){
				top.layer.msg("不能给自己添加标签");
			}else if(data=='0'){
				top.layer.msg("添加标签失败");
			}else{
				top.layer.msg("添加标签成功");
				$("#markContent").val("");
			}
			getOtherInfo(accountId);
		}
	});
}

function getknowledge(accountId){
	
}

