<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人设置</title>
</head>
<style>
html,body{margin:0px;padding:0px;}
</style>
<style>#ads_c_tpc, .a_fl, .a_fr, .a_h, .a_mu, .a_pb, .a_pr, .a_pt, .ad_footerbanner, #ad_headerbanner, .ad_headerbanner, [id^="ad_thread"], .archiver_banne, .bm.a_c, .wp.a_f, #sitefocus.focus { display: none !important; }
#main{width:90%;margin-left:5%;margin-top:20px;}
</style>
<script type="text/javascript">
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
     alert("调整菜单快捷组的项目顺序时，请选择其中一项！");
     return;
  }
  else if(sel_count>1)
  {
     alert("调整菜单快捷组的项目顺序时，只能选择其中一项！");
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

function mysubmit()
{
	sysMenuId="";
   for (i=0; i< form1.select1.options.length; i++)
   {
      options_value=form1.select1.options[i].value;
      sysMenuId+=options_value+",";
    }
   sysMenuId = sysMenuId.substr(0,sysMenuId.length-1);
	var requestUrl=contextPath+"/tfd/system/menu/act/SysMenuAct/updateShortMenu.act";
	$.ajax({
		url:requestUrl,
		data:{sysMenuId:sysMenuId},
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			if(data=='1'){
				top.layer.msg("修改成功");
			}
		}
	});
}
function doinit(){
	var requestUrl=contextPath+"/tfd/system/menu/act/SysMenuAct/getSysShortMeunJson.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			shortMenu = data;
			var html = "";
			$.each(data,function(index,menu){
				html += "<option value="+menu.id+">"+menu.name+"</option>";
			});
			$("#select1").html(html);
		}
	});
	requestUrl=contextPath+"/tfd/system/menu/act/SysMenuAct/getSysMeunJson.act";
	$.ajax({
		url:requestUrl,
		dataType:"json",
		async:false,
		error:function(e){
			alert(e.message);
		},
		success:function(data){
			menuData = data;
			var menu = findChilds("0");
			var html = "";
			$.each(menu,function(index,menu){
				html += "<option value=\"MENU_SORT\">[--------["+menu.name+"]---------]</option>";
				childHtml = "";
				createChildHtml(1,menu.id);
				html += childHtml;
			});
			$("#select2").html(html);
		}
	});
}
function findChilds(id){
	var childs=[];
	for(var i=0;i<menuData.length;i++){
		if(menuData[i].pId==id){
			childs.push(menuData[i]);
		}
	}
	return childs;
}
function createChildHtml(classi,id){
	var childs=findMenuChilds(id);
	if(childs.length>0){
		classi++;	
	}
	for(var i=0;i<childs.length;i++){
		var cs=findMenuChilds(childs[i].id);
		if(cs.length==0){
			var s = 0;
			if(shortMenu.length==0){
				childHtml+="<option value="+childs[i].id+">"+childs[i].name+"</option>";
			}else{
				for(var j=0;j<shortMenu.length;j++){
					if(shortMenu[j].id != childs[i].id){
						s++;
					}
					if(s==shortMenu.length){
						childHtml+="<option value="+childs[i].id+">"+childs[i].name+"</option>";
					}
				}
			}
		}else{
			childHtml+="<option value=\"MENU_SORT\">------"+childs[i].name+"------</option>";
		}
		
		createChildHtml(classi,childs[i].id);
	}
}
function findMenuChilds(id){
	var childs=[];
	for(var i=0;i<menuData.length;i++){
		if(menuData[i].pId==id){
			childs.push(menuData[i]);
		}
	}
	return childs;
}
</script>
<body onload="doinit()" >
<form name="form1">
<div id="main">
<div class="list-group" style="margin-bottom: 0px;">
   <a class="list-group-item active">
      <h5 class="list-group-item-heading">
         菜单快捷组设置
      </h5>
   </a>
<table class="table table-bordered" width="800">

  <tbody><tr class="">
    <td align="center"><b>排序</b></td>
    <td align="center"><b>菜单快捷组项目</b></td>
    <td align="center"><b>选择</b></td>
    <td align="center" valign="top"><b>备选菜单项</b></td>
  </tr>
  <tr>
    <td align="center" class="TableData" style="vertical-align:middle;">
      <input type="button" class="btn btn-mini" value=" ↑ " onclick="func_up();">
      <br><br>
      <input type="button" class="btn btn-mini" value=" ↓ " onclick="func_down();">
    </td>
    <td valign="top" align="center" class="TableData" style="text-align:center;">
    <select name="select1" id="select1" ondblclick="func_delete();" multiple="" style="width:250px;height:300px;">


    </select>
    <input type="button" value=" 全选 " onclick="func_select_all1();" class="btn btn-mini" style="margin-left: 130px;display: block;margin-top: 10px;">
    </td>
    <td align="center" class="TableData" style="vertical-align:middle;">
      <input type="button" class="btn btn-mini" value=" ← " onclick="func_insert();">
      <br><br>
      <input type="button" class="btn btn-mini" value=" → " onclick="func_delete();">
    </td>

    <td align="center" valign="top" class="TableData" style="text-align:center;">
    <select name="select2" id="select2" ondblclick="func_insert();" multiple="" style="width:250px;height:300px;">

    </select>
    <input type="button" value=" 全选 " onclick="func_select_all2();" class="btn btn-mini" style="margin-left: 130px;display: block;margin-top: 10px;">
    </td>
  </tr>

  <tr class="TableData">
    <td align="center" valign="top" colspan="4" class="" style="text-align:center">
    <span>点击条目时，可以组合CTRL或SHIFT键进行多选</span><br>
      <input type="button" class="btn btn-primary" value="保存设置" onclick="mysubmit();">
    </td>
  </tr>
</tbody></table>
</div>
</div>
</form>


</body>
</body>
<script type="text/javascript">


</script>
</html>