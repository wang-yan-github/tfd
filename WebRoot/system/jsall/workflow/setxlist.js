function createXlistTable(model)
{
	var html=" <div class=\"widget-header bordered-bottom bordered-sky\"><span class=\"widget-caption\">子表权限设置</span></div>"+
			"<div class=\"panel-body\">"+
			"<table id=\"xlisttable\" name=\"xlisttable\" class=\"table table-striped  table-condensed\">" +
			"<tr>\n"+
			"<td align=\"center\">标题</td>\n"+
			"<td align=\"center\" style=\"display:none;\">字段名称</td>\n"+
			"<td align=\"center\" style=\"display:none;\">字段长度</td>\n"+
			"<td align=\"center\">合计</td>\n"+
			"<td align=\"center\">计算公式</td>\n"+
			"<td align=\"center\">类型</td>\n"+
			"<td align=\"center\">备选值</td>\n"+
			"<td align=\"center\" style=\"display:none;\">可写</td>\n"+
			"<td align=\"center\">只读</td>\n"+
			"<td align=\"center\">显示</td>\n"+
			"</tr>";
	for(var i=0;model.length>i;i++)
		{
		html+="<tr>";
		html+="<td align=\"center\">"+model[i].title+"<input id=\"title_"+i+"\" name=\"title_"+model[i].title+"\" type=\"checkbox\" value=\""+model[i].title+"\" style=\"display: none;\" ></td>";
		html+="<td align=\"center\" style=\"display:none;\">"+model[i].fieldname+"</td>";
		html+="<td align=\"center\" style=\"display:none;\">"+model[i].width+"</td>";
		if(model[i].sum=="1")
		{
		  html+="<td align=\"center\">是</td>";
		}else
		{
		   html+="<td align=\"center\">否</td>";
		}
		html+="<td align=\"center\">"+model[i].formula+"</td>";
		if(model[i].type=="1")
		{
		  html+="<td align=\"center\">单行输入框</td>";
		}else if(model[i].type=="2")
        {
         html+="<td align=\"center\">多行行输入框</td>";
        }else if(model[i].type=="3")
        {
         html+="<td align=\"center\">下拉菜单</td>";
        }else if(model[i].type=="4")
        {
         html+="<td align=\"center\">单选框</td>";
        }else if(model[i].type=="5")
        {
         html+="<td align=\"center\">多选框</td>";
        }else if(model[i].type=="6")
        {
         html+="<td align=\"center\">日期选择</td>";
        }
        
		html+="<td align=\"center\">"+model[i].model+"</td>";
		html+="<td align=\"center\" style=\"display:none;\"><input id=\"writer_"+i+"\" name=\"writer_"+model[i].fieldname+"\" type=\"checkbox\" value=\""+model[i].fieldname+"\"></td>";
		html+="<td align=\"center\"><input id=\"read_"+i+"\" name=\"read_"+model[i].fieldname+"\"type=\"checkbox\" value=\""+model[i].fieldname+"\"></td>";
		html+="<td align=\"center\"><input id=\"print_"+i+"\" name=\"print_"+model[i].fieldname+"\"type=\"checkbox\" value=\""+model[i].fieldname+"\"></td>";
		html+="</tr>";
		}
	html+="</table>";
	$("#xlist").html(html);
	
}
//获取设置
function getSetData(childTable)
{
	var wirterStr="";
	var readStr="";
	var printStr="";
	var titleStr="";
	var count=$("#xlisttable").find("tr").length;
	for(var i=0;count-1>i;i++)
		{
		var el="writer_"+i;
			if($("#"+el).prop("checked")==true)
			{
				wirterStr+=$("#"+el).attr("value")+",";
			}
		}
	for(var i=0;count-1>i;i++)
	{
	var el="read_"+i;
		if($("#"+el).prop("checked")==true)
		{
			readStr+=$("#"+el).attr("value")+",";
		}
	}
	for(var i=0;count-1>i;i++)
	{
	var el="print_"+i;
	var tel="title_"+i;
		if($("#"+el).prop("checked")==true)
		{
			printStr+=$("#"+el).attr("value")+",";
			titleStr+=$("#"+tel).attr("value")+",";
		}
	}
	var jsonStr="";
	if(!wirterStr==""||!wirterStr==null)
		{
		wirterStr=wirterStr.substr(0,wirterStr.length-1);
		}
	if(!readStr==""||!readStr==null)
	{
		readStr=readStr.substr(0,readStr.length-1);
	}
	if(!printStr==""||!printStr==null)
	{
		printStr=printStr.substr(0,printStr.length-1);
		titleStr=titleStr.substr(0,titleStr.length-1);
	}
	jsonStr="{\"table\":\""+childTable+"\",\"writerfield\":\""+wirterStr+"\",\"read\":\""+readStr+"\",\"print\":\""+printStr+"\",\"title\":\""+titleStr+"\"}";
	history.go(-1);
	return jsonStr;
}


function saveXlistSet(flowId,prcsId,model)
{
	var requestUrl = contextPath+"/tfd/system/workflow/flowprocess/act/FlowProcessAct/saveXlistSetAct.act";
	$.ajax({
 		url:requestUrl,
 		dataType:"json",
 		data:{flowId:flowId,prcsId:prcsId,model:model},
 		async:false,
 		success:function(data){
 		}
 	}); 
	
	}