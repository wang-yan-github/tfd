<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>
<%
String flowId = request.getParameter("flowId");
String prcsId = request.getParameter("prcsId");
%>
<html>
<head>
<title>
</title>
   
    <script src="<%=contextPath%>/system/workflow/flowprocess/designflow/js/writer.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<%=stylePath%>/workflow/flowprocess.css"></link>
<script type="text/javascript">
var flowId="<%=flowId%>";
var prcsId=<%=prcsId%>;
var list1;
var list2;
$(function ()
        { 
           /*  $("#listbox1").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: true,
                height: 200
            });
            $("#listbox2").ligerListBox({
                isShowCheckBox: false,
                isMultiSelect: true,
                height: 200,
                valueFieldID: 'listdata'
            }); */
            var requestUrl = "<%=contextPath%>/tfd/system/workflow/flowformitem/act/FlowFormItemAct/getAllFlowFormItemByFlowId.act";
        	$.ajax({
        		url:requestUrl,
        		dataType:"json",
        		data:{flowId:flowId,prcsId:prcsId},
        		async:false,
        		success:function(data){
        			shortMenu = data;
        			var html = "";
        			$.each(data,function(index,menu){
        				html += "<option value="+menu.id+">"+menu.text+"</option>";
        			});
        			$("#select1").html(html);
        		}
        	});
           // liger.get("listbox1").setData(list1);
//第二个列表            
            var Url = "<%=contextPath%>/tfd/system/workflow/flowprocess/act/FlowProcessAct/getWriterByFlowId.act";
			$.ajax({
				url:Url,
				dataType:"json",
				data:{flowId:flowId,prcsId:prcsId},
				async:false,
				success:function(data){
					menuData = data;
        			var html = "";
        			$.each(data,function(index,menu){
        				html += "<option value="+menu.id+">"+menu.text+"</option>";
        			});
        			$("#select2").html(html);
				}
			});
			//liger.get("listbox2").setData(list2);
//获取列表控件
/* getXlist(flowId,prcsId);
getWriterInfo(); */
        });
/**
 * 新的代码
 */
 var menuData;
 var shortMenu;
 var childHtml = "";
 function func_insert()
 {
  for (i=0; i<form1.select2.options.length; i++)
  {
    if(form1.select2.options[i].selected && form1.select2.options[i].value!="MENU_SORT")
    {
      option_text=form1.select2.options[i].text;
      option_value=form1.select2.options[i].value;
      option_style_color=form1.select2.options[i].style.color;

      var my_option = document.createElement("OPTION");
      my_option.text=option_text;
      my_option.value=option_value;
      my_option.style.color=option_style_color;

      pos=form1.select2.options.length;
      form1.select1.options.add(my_option,pos);
      form1.select2.remove(i);
      i--;
   }
  }//for
 }

 function func_delete()
 {
  for (i=0;i<form1.select1.options.length;i++)
  {
    if(form1.select1.options[i].selected)
    {
      option_text=form1.select1.options[i].text;
      option_value=form1.select1.options[i].value;

      var my_option = document.createElement("OPTION");
      my_option.text=option_text;
      my_option.value=option_value;
      pos=form1.select2.options.length;
      form1.select2.options.add(my_option,pos);
      form1.select1.remove(i);
      i--;
   }
  }//for
 }

 function func_select_all1()
 {
  for (i=form1.select1.options.length-1; i>=0; i--)
    form1.select1.options[i].selected=true;
 }

 function func_select_all2()
 {
  for (i=form1.select2.options.length-1; i>=0; i--)
    form1.select2.options[i].selected=true;
 }

 function func_up()
 {
   sel_count=0;
   for (i=form1.select1.options.length-1; i>=0; i--)
   {
     if(form1.select1.options[i].selected)
        sel_count++;
   }

   if(sel_count==0)
   {
      alert("请选择其中一项！");
      return;
   }
   else if(sel_count>1)
   {
      alert("只能选择其中一项！");
      return;
   }

   i=form1.select1.selectedIndex;

   if(i!=0)
   {
     var my_option = document.createElement("OPTION");
     my_option.text=form1.select1.options[i].text;
     my_option.value=form1.select1.options[i].value;

     form1.select1.options.add(my_option,i-1);
     form1.select1.remove(i+1);
     form1.select1.options[i-1].selected=true;
   }
 }

 function func_down()
 {
   sel_count=0;
   for (i=form1.select1.options.length-1; i>=0; i--)
   {
     if(form1.select1.options[i].selected)
        sel_count++;
   }

   if(sel_count==0)
   {
      alert("调整菜单快捷组的项目顺序时，请选择其中一项！");
      return;
   }
   else if(sel_count>1)
   {
      alert("调整菜单快捷组的项目顺序时，只能选择其中一项！");
      return;
   }

   i=form1.select1.selectedIndex;

   if(i!=form1.select1.options.length-1)
   {
     var my_option = document.createElement("OPTION");
     my_option.text=form1.select1.options[i].text;
     my_option.value=form1.select1.options[i].value;

     form1.select1.options.add(my_option,i+2);
     form1.select1.remove(i);
     form1.select1.options[i+1].selected=true;
   }
 }

</script>
</head>
<body style="height: 100%;"> 
<div class="widget-header bordered-bottom bordered-sky">
		<span class="widget-caption">可写字段设置</span>
</div>
 <!-- <table class="table table-striped  table-condensed" >
<tr >
<td align="center" width="45%"><b>备选</b></td>
<td align="center" width="10%"><b>操作</b></td>
<td align="center" width="45%"><b>已选</b></td>
</tr>
<tr>
<td align="right" >     
	<div id="listbox1"></div>  
</td>
<td  align="center">
         <button class="btn btn-info btn-xs" onclick="moveToLeft();">向左取消</button><br>
         <br>
         <button  class="btn btn-info btn-xs" onclick="moveToRight();" >向右选择</button><br>
         <br>
         <button  class="btn btn-info btn-xs"  onclick="moveAllToLeft();">全部向左</button><br>
         <br>
         <button  class="btn btn-info btn-xs"  onclick="moveAllToRight();">全部向右</button><br>
</td>
<td align="left" class="TableData">
	<div id="listbox2"></div> 
</td>
</tr>
</table>
 -->
 <form name="form1">
 <table  class="table table-striped  table-condensed">

  <tbody><tr class="">
   <td align="center" width="45%"><b>备选</b></td>
<td align="center" width="10%"><b>操作</b></td>
<td align="center" width="45%"><b>已选</b></td>
  </tr>
  <tr>
    <td valign="top" align="center"  style="text-align:center;">
    <select name="select1" id="select1" ondblclick="func_delete();" multiple="" style="width:150px;height:220px;">


    </select>
    <input type="button" value=" 全选 " onclick="func_select_all1();" class="btn btn-mini" style="margin-left: 100px;display: block;margin-top: 10px;">
    </td>
    <td align="center" style="vertical-align:middle;">
      <input type="button" class="btn btn-mini" value=" ← " onclick="func_insert();">
      <br><br>
      <input type="button" class="btn btn-mini" value=" → " onclick="func_delete();">
    </td>

    <td align="center" valign="top"  style="text-align:center;">
    <select name="select2" id="select2" ondblclick="func_insert();" multiple="" style="width:150px;height:220px;">

    </select>
    <input type="button" value=" 全选 " onclick="func_select_all2();" class="btn btn-mini" style="margin-left: 100px;display: block;margin-top: 10px;">
    </td>
  </tr>

  <tr class="TableData">
    <td align="center" valign="top" colspan="4"  style="text-align:center">
    <span>点击条目时，可以组合CTRL或SHIFT键进行多选</span>
    </td>
  </tr>
</tbody></table>
 
 </form>
 
 
 </div>
</div>
<div class="widget-header bordered-bottom bordered-sky">
		<span class="widget-caption">公共文件操作权限</span>
</div>
 <table class="table table-striped  table-condensed" >
<tr >
<td align="center">
<input type="checkbox" id="readOnly" name="readOnly"/>只读&nbsp;
<input type="checkbox" id="createUpLoad" name="createUpLoad">新建/上传&nbsp;
<input type="checkbox" id="edit" name="edit">修改/编辑&nbsp;
<input type="checkbox" id="down" name="down">下载/打印
</td>
</tr>
<tr>
<td colspan="3">
<div class="widget-header bordered-bottom bordered-sky">
		<span class="widget-caption">列表权限设置</span>
</div>
<div id="xlist"></div>
</div>
</div>
</td>
</tr>
<tr>
<td colspan="3">
<div align="center"><button onclick="updateWriterField();" class="btn btn-primary">保存</button></div>
</td>
</tr>
</table>
   </div>
</body>
</html>
