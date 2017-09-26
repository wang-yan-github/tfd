var css = document.createElement('link');
    css.href= contextPath+"/system/styles/css/style1/fileupload/fileupload.css";
    css.rel="stylesheet";
    css.type="text/css";
    document.getElementsByTagName('head')[0].appendChild(css);
var iframeArr=[];
var form={};
function createXifream()
{
	$("div[xtype='xiframe']").each(function(){
		var iframeid=$(this).attr("id");
		var url=$(this).attr("module");
		iframeArr.push(iframeid);
		var html="<iframe id=\""+iframeid+"\" src=\""+url+"\" style=\"width:100%;height:100%;\"></iframe>";
		$("#" + iframeid).before(html);
		
	});
		$("div[xtype='xiframe']").remove();
	
}

function createAttachDiv()
{
        $("div[xtype='xupload']").each(function(){
        var xuploadId=$(this).attr("id");
        if(form[xuploadId]!="")
        {
            var htmlstr=getAttachIdDiv(form[xuploadId]);
            $("#"+xuploadId).html(htmlstr);
        }
        });
       $("div[xtype='xuploads']").each(function(){
        var xuploadId=$(this).attr("id");
        if(form[xuploadId]!="")
        {
            var htmlstr=getAttachIdDiv(form[xuploadId]);
            $("#"+xuploadId).html(htmlstr);
        }
    });
}

function getAttachIdDiv(attachIds)
{
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
                    var Id=attach.attachId;
                    var Name = attach.attachName.substr(18,attach.attachName.length-18);
                    var extName=Name.substring(Name.indexOf(".")+1,Name.length);
                    html="<div id=\""+Id+"\" style='width:100%;height:25px;line-height:25px;'>\n"+
                                "<img style=\"float:left;width:20px;height:20px;\" src=\""+imgPath+"/filetype/file_extension_"+extName+".png\" />\n"+
                                "<span style=\"float:left;\" title=\""+Name+"\" onmouseout=\"javascript:hideMenus()\" onmouseover=\"showMenu('"+Id+"','"+extName+"','1')\" >"+Name+"</span>\n"+
                                 "</div>";
                }
            }
    });
    return html;
    }
function openFile(attachId,extName,privFlag)
{
       if(extName=="txt")
    {
        window.open(contextPath+"/system/controldoc/text.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else
    if(extName=="jpg"||extName=="gif"||extName=="png"||extName=="bmp"||extName=="tif")
    {
        window.open(contextPath+"/system/controldoc/img.jsp?attachId="+attachId+"&privFlag="+privFlag);
    }else
    if(extName=="pdf")
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
    }
}


function getPrintData(runId)
{
    $("table").addClass("table table-bordered table-striped");
    $("body").append("<div id=\"attachHtml\" ></div>");
	var requestUrl =contextPath+"/tfd/system/workflow/getflowdata/act/GetWorkFlowDataAct/getPrintWorkFlowDataAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{runId:runId},
		async:false,
		success:function(data){
		    form = data;
			for(var name in form){
				var ele=$("*[name='"+name+"']");
				var eleType = ele.attr("type");
				var xtype=ele.attr("xtype");
				if(xtype=="xworkflow")
				{
				    getSelectFlowTitleList(form[name],name,false);
				    break;
				}
				if(eleType == "radio"){
                    ele.attr("checked",true);
                }else if(eleType == "checkbox")
                {
                    if (form[name]=="0") {
                                ele.get(0).checked = false;
                            }
                            if(form[name]=="1"){
                                ele.get(0).checked = true;
                            }
                }else
                {
                    ele.html(data[name]);
                }
			}
			createAttachDiv();
			var html="";
			var publicFile=[];
			if(form.publicFiles!=null)
    			{
    			     publicFile=form.publicFiles.split(",");
    			}
			for(var i=0;publicFile.length>i;i++)
			{
			    html+=getAttachIdDiv(publicFile[i]);
			}
			$("#publicFiles").html(html);
		}
		});
}
//获取子表数据
function getPrintChildTableData(runId)
{
	var childTableJson;
	var requestUrl =contextPath+"/tfd/system/workflow/getflowdata/act/GetWorkFlowDataAct/getAllChildTableDataAct.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		data:{runId:runId},
		async:false,
		success:function(data){
			childTableJson=data;
			}
		});
	return childTableJson;
}

//xlis生成
function createXlist(runId)
{
	var childTableJson=getPrintChildTableData(runId);
	for(var i=0;childTableJson.length>i;i++)
	{
		setXlistControls(childTableJson[i]);
	}
}


//生成明细表所有控件
function setXlistControls(controlsSetting){
		var table=controlsSetting.tableName;
		var title=controlsSetting.tableTitle;
		var data=controlsSetting.tableData;
		var controlHead=setXlistControlHead(table,title);
		var controlBody=setXlistControl(table,data);
		var controlHtml="<table class=\"table table-striped \">"+controlHead+controlBody+"</table>";
		$("#"+table).before(controlHtml);
		$("#"+table).remove();
}

//列表头部
function setXlistControlHead(table,title){
	var controlHead="<thead>";
	var titleFields=title.split(",");
	controlHead+="<tr>";
	controlHead+="<td style=\"display:none;\">tag</td><td style=\"width:40px;background: silver;\" nowrap  align=\"center\">序号</td>";
	for ( var i = 0; i < titleFields.length; i++) {
		controlHead+="<td style=\"width:40px;background: silver;\"  align=\"center\" nowrap>"+titleFields[i]+"</td>";
	}
	controlHead+="</tr></thead>";
	return controlHead;
}
//列表主体
function setXlistControl(table,data){
	var controlBody="<tbody id=\""+table+"_tbody\">";
	for ( var i = 0; i < data.length; i++) {
		var dataObj=data[i];
		var temp="<tr id=\""+table+"_tr_"+i+"\">";
		temp+="<td style=\"display:none;\"  align=\"center\"><input name=\""+table+"_tag_"+i+"\" value=\""+dataObj["tag"]+"\"><input name=\""+table+"_op_"+i+"\" value=\"0\"></td>";
		temp+="<td  align=\"center\">"+(i+1)+"</td>";
		for(var fieldName in dataObj){
			var inputName=table+"_"+fieldName+"_"+i;
				temp+="<td  align=\"center\" width=\"40px\">";
				temp += " <div name=\""+inputName+"\">"+dataObj[fieldName] + "</div>";
				temp+="</td>";
		}
		temp+="</tr>";
		controlBody+=temp;
	}
	controlBody+="</tbody>";
	return controlBody;
}

function getIdea() {
    var html = "<div class=\"tickets-container\">";
    var requestUrl = contextPath
            + "/tfd/system/workflow/flowrunprcs/act/FlowRunPrcsAct/getIdeaAct.act";
    $.ajax({
        url : requestUrl,
        dataType : "json",
        data : {
            runId : runId
        },
        async : false,
        success : function(data) {
            var idea = data;
            for (var i = 0; idea.length > i; i++) {
                var ideaStr = idea[i].idea;
                if (ideaStr == "0") {
                    ideaStr = '不同意';
                } else if (ideaStr == "1") {
                    ideaStr = '同意';
                } else if (ideaStr == "2") {
                    ideaStr = '基本同意';
                }else
                {
                    ideaStr="正在进行";
                }
                var ideaText="";
                if(idea[i].ideaText==null)
                {
                     var ideaText="<font color=\"#FF0000\"> 未填写审批意见!</font>";
                }else
                {
                    ideaText=idea[i].ideaText;
                }
                var headImg = "";
                if(idea[i].headImg){
                	headImg = contextPath+"/attachment/userinfo/"+idea[i].headImg;
                }else{
                	headImg = contextPath + "/system/styles/images/personal/default.jpg";
                }
                html+= "<ul class=\"tickets-list\"><li class=\"ticket-item\"><div class=\"row\">\n"+
                            "<div class=\"ticket-user col-lg-3 col-sm-12\">\n"+
                            "<img onclick=\"javascript:showPersonal('"+idea[i].accountId+"')\" width=\"32\" height=\"32\" " +
                            "src=\""+headImg+"\" " +
                            "class=\"user-avatar\">\n"+
                            "<span class=\"user-name\">"+ idea[i].userName+"</span>\n"+
                            "<span class=\"user-at\">at</span>\n"+
                            "<span class=\"user-company\">第:" + idea[i].runPrcsId + "步骤:" + idea[i].prcsName+"</span>\n"+
                            "</div>\n"+                                                                
                            "<div class=\"ticket-time  col-lg-4 col-sm-6 col-xs-12\">\n"+
                            "<div class=\"divider hidden-md hidden-sm hidden-xs\"></div>\n"+
                            "<i class=\"fa fa-clock-o\"></i>\n"+
                            "<span class=\"time\">创建时间："+idea[i].createTime+"  结束时间："+idea[i].endTime+"</span>\n"+
                            "</div>\n"+
                            "<div class=\"ticket-type  col-lg-5 col-sm-6 col-xs-12\" style=\"height:auto;\">\n"+
                            "<span class=\"divider hidden-xs\"></span>\n"+
                            "<span class=\"type\" style=\"word-break:break-all;word-wrap:break-word;\">"+ideaText+"</span>\n"+
                            "</div>\n";
                            if(idea[i].idea=="0")
                            {
                                html+="<div class=\"ticket-state bg-darkorange\"><i class=\"fa fa-times\">\n"+"</i>\n";
                            }else if(idea[i].idea=="1")
                            {
                                html+="<div class=\"ticket-state bg-palegreen\"><i class=\"fa fa-check\">\n"+"</i>\n";
                            }else if(idea[i].idea=="2")
                            {
                                html+="<div class=\"ticket-state bg-yellow\"><i class=\"fa fa-info\">\n"+"</i>\n";
                            }
                           html+= "</div>\n"+
                            "</div></li></ul>\n";
                            
            }
        }
    });
    html+="</div>";
    $("#allIdea").html(html);
}

//宏标记处理
function doXmacrotag()
{
    $("span[xtype='macrotag']").each(function(){
        var value= $(this).attr("value");
        if(value=="MACRO_FORM_NAME")
        {
            $(this).html(form.flowFormName);
        }else if(value=="MACRO_RUN_NAME")
        {
            $(this).html(form.flowTitle);
        }else if(value=="MACRO_NUMBERING")
        {
            $(this).html("流程开始编号！");
        }else if(value=="MACRO_BEGIN_TIME")
        {
            $(this).html(form.flowBeginTime);
        }else if(value=="MACRO_END_TIME")
        {
            $(this).html(form.flowEndTime);
        }else if(value=="MACRO_RUN_ID")
        {
            $(this).html(form.flowRunId);
        }else if(value=="MACRO_RUN_GUID")
        {
            $(this).html(form.flowGuId);
        } else if(value=="MACRO_BEGIN_USERNAME")
        {
            $(this).html(form.flowBeginUser);
        }else if(value=="MACRO_BEGIN_ACCOUNT_ID")
        {
            $(this).html(form.flowBeginAccountId);
        }
    });
}

 //获取已选择流程的实例
    function getSelectFlowTitleList(runIds,name,flag)
    {
        var paramData={runIds:runIds};
        var requestUrl=contextPath+"/tfd/system/workflow/flowrun/act/FlowRunAct/getFlowRunByIdsAct.act";
        $.ajax({
            url:requestUrl,
            dataType:"json",
            data:paramData,
            async:false,
            error:function(e){
                alert(e.message);
            },
            success:function(data){
                var html="";
                for(var i=0;data.length>i;i++)
                {
                    var closeStyle="padding:2px 5px;color:red;cursor:pointer;";
                    html+="<div id=\""+data[i].runId+"\">";
                    html+=" <a href=\"#\" onclick=\"readWorkFlow('"+data[i].path+"')\">"+data[i].title+"</a>";
                    if(flag)
                    {
                    html+=" &nbsp;<span title='删除' style='"+closeStyle+"' href='javascript:void(0);' onclick=\"delWorkFlow('"+data[i].runId+"','"+name+"');\">x</span>";
                    }
                    html+="</div>";
                }
                $("#"+name).before(html);
            }
        });
    }
    
 function readWorkFlow(path)
 {
     window.open(contextPath+path);
 }
 
function showMenu(attachId,extName,type){
    if(type == '1'){
        createReadDiv(attachId,extName);
    }else if(type=='2'){
        createEditDiv(attachId,extName);
    }else
    {
        
    }
    var cityObj = $("#"+attachId);
    var cityOffset = $("#"+attachId).offset();
    ev= arguments.callee.caller.arguments[0]  || window.event; 
    var mousePos = mouseCoords(ev);
    $("#menuDiv").css({left:(ev.clientX-40) + "px", top:cityOffset.top + cityObj.outerHeight()-20 + "px"}).show();
}

//生成阅读菜单
function createReadDiv(attachId,extName){
    var divHtml = "<div id=\"menuDiv\" onmouseout=\"javascript:hideMenus()\"   onmouseover=\"javascript:stillMenu()\" class=\"readmenu\" >\n"+
    "<div class=\"menuClass\" onclick=\"javascript:openFile('"+attachId+"','"+extName+"','1')\" >打开</div>\n"+
    "<div class=\"menuClass\" type=\"application/msword\" ><a href='"+contextPath+"/com/system/filetool/UpFileTool/doDownFile.act?attachId="+attachId+"' >下载</a></div></div>";
    $('#attachHtml').html(divHtml);
}


function stillMenu(){
    $("#menuDiv").css("display","block");
}
function hideMenus(){
    $("#menuDiv").css("display","none");
}
function createEditDiv(attachId,extName){
    var divHtml = "<div id=\"menuDiv\" onmouseout=\"javascript:hideMenus()\"   onmouseover=\"javascript:stillMenu()\"  class=\"editmenu\" ><div class=\"menuClass\" onclick=\"javascript:openFile('"+attachId+"','"+extName+"','4')\" >打开</div><div class=\"menuClass\"  >编辑</div><div onclick=\"delAttach('"+attachId+"');\" class=\"menuClass\" >删除</div></div>";
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


function getWord(runId)
{
    window.location.href=contextPath+"/tfd/system/workflow/flowutility/UitilityOfficeTool/doDownFile.act?runId="+runId;
}
function openprintwindow(runId)
{
    window.open("print.jsp?runId="+runId);
}
