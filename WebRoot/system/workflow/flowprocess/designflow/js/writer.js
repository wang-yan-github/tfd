function getXlist(flowId,prcsId)
{
	var listUrl = contextPath+"/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getAllXlistAct.act";
	$.ajax({
		url:listUrl,
		dataType:"json",
		data:{flowId:flowId},
		async:false,
		success:function(data){
			createXlit(data,flowId,prcsId);
		}
	});
}

function createXlit(jsonList,flowId,prcsId)
{
	var html="<table class=\"table table-striped\"><tr><td align=\"center\">子表名称</td><td align=\"center\">表名</td><td align=\"center\">操作</td></tr>";
	for(var i=0;jsonList.length>i;i++)
		{
			html+="<tr>";
			html+="<td align=\"center\">"+jsonList[i].title+"</td>";
			html+="<td align=\"center\">"+jsonList[i].table+"</td>";
			html+="<td align=\"center\"><button class=\"btn btn-warning\" onclick=setXlist(\""+jsonList[i].table+"\",\""+flowId+"\",\""+prcsId+"\")>设置</button></td>" ;
			html+="<tr>";
		}
	html+="</table>";
	$("#xlist").html(html);
}

function setXlist(childTable,flowId,prcsId)
{
	location.href="setxlist.jsp?childTable="+childTable+"&prcsId="+prcsId+"&flowId="+flowId;
}

function moveToLeft()
{
    var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
    var selecteds = box2.getSelectedItems();
    if (!selecteds || !selecteds.length) return;
    box2.removeItems(selecteds);
    box1.addItems(selecteds);
}
function moveToRight()
{
    var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
    var selecteds = box1.getSelectedItems();
    if (!selecteds || !selecteds.length) return;
    box1.removeItems(selecteds);
    box2.addItems(selecteds);
}
function moveAllToLeft()
{
    var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
    var selecteds = box2.data;
    if (!selecteds || !selecteds.length) return;
    box1.addItems(selecteds);
    box2.removeItems(selecteds); 
}
function moveAllToRight()
{
    var box1 = liger.get("listbox1"), box2 = liger.get("listbox2");
    var selecteds = box1.data;
    if (!selecteds || !selecteds.length) return;
    box2.addItems(selecteds);
    box1.removeItems(selecteds);
}
function updateWriterField()
{

	var writerFieldStr="";
	   for (i=0; i< form1.select2.options.length; i++)
	   {
	      options_value=form1.select2.options[i].value;
	      writerFieldStr+=options_value+",";
	    }
	var publicFile="";
	var readOnly="0";
	   if($('#readOnly').is(':checked')) 
	{
	    readOnly="1";
	}
    var createUpLoad="0";
   if($('#createUpLoad').is(':checked')) 
    {
        createUpLoad="1";
    }
    var edit="0";
  if($('#edit').is(':checked')) 
    {
        edit="1";
    }
    var down="0";
    if($('#down').is(':checked')) 
    {
        down="1";
    }
    publicFile="{'readOnly':'"+readOnly+"','createUpLoad':'"+createUpLoad+"','edit':'"+edit+"','down':'"+down+"'}";
	var Url = contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/updateWriter.act";
	$.ajax({
		url:Url,
		dataType:"text",
		data:{flowId:flowId,prcsId:prcsId,writerFieldStr:writerFieldStr,publicFile:publicFile},
		async:false,
		success:function(data){
		    layer.msg('可写字段设置成功!');
		}
	});
}
function getWriterInfo () {
  var Url = contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/getPublicFilePrivAct.act";
    $.ajax({
        url:Url,
        dataType:"json",
        data:{flowId:flowId,prcsId:prcsId},
        async:false,
        success:function(data){
           
            if(data.readOnly=="1")
            { 
                $("#readOnly").attr("checked",true);
            }
            if(data.createUpLoad=="1")
            {
                $("#createUpLoad").attr("checked",true);
            }
            if(data.edit=="1")
            {
                $("#edit").attr("checked",true);
            }
            if(data.down=="1")
            {
                $("#down").attr("checked",true);
            }
        }
    });
}