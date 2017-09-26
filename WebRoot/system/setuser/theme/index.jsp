<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>界面管理</title>
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/layout_left.css" />
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/person_info.css">
<link rel="stylesheet" type="text/css" href="<%=stylePath%>/style.css">
</head>
<body>
<div class="PageHeader">
   <div class="title"><img src="<%=imgPath%>/theme.gif" align="absmiddle"> 界面主题</div>
</div>

<form enctype="multipart/form-data" action="update.php"  method="post" name="form1" onSubmit="return CheckForm();">
<input type="hidden" name="THEME_SELECT" value=1>
<table class="TableTop" width="100%">
   <tr>
      <td class="left"></td>
      <td class="center">界面主题与菜单</td>
      <td class="right"></td>
   </tr>
</table>
<table class="TableBlock no-top-border" width="100%">
    <tr>
      <td nowrap class="TableData" width="20%"><b>界面主题：</b></td>
      <td class="TableData">
        <select name="THEME" class="SmallSelect" onChange="ChangeTheme(this.value);">
<option value="1">2008版经典主题：儒雅深沉</option><option value="2">2008版经典主题：清新怡然</option><option value="9" selected>2009版经典主题：生命与自然之美</option><option value="10">2010版梦幻灵动主题：宁静的思考</option><option value="13">2013版梦幻灵动主题：现代或未来</option>        </select>
        <br>
        梦幻灵动主题需IE7或以上版本浏览器支持&nbsp;&nbsp;&nbsp;&nbsp;
      </td>
    </tr>
    <tr>
      <td nowrap class="TableData" width="20%">桌面背景图片：</td>
      <td class="TableData">
         <select name="BKGROUND" class="SmallSelect" onChange="bkchange(this.value)">
            <option value="" selected>系统默认</option>
            <option value="a.jpg">a.jpg(1800x822)</option>
            <option value="b.jpg">b.jpg(1024x640)</option>
            <option value="c.jpg">c.jpg(1280x800)</option>
            <option value="d.jpg">d.jpg(1280x800)</option>
            <option value="e.jpg">e.jpg(1600x1200)</option>
            <option value="0">自定义</option>
         </select>&nbsp;
      	<a id="bk_preview" href="/attachment/background/" target="_blank" style="display:none;">预览</a>
    	
      </td>
    </tr>
    <tr id="bk_image" style="display:none;">
      <td nowrap class="TableData" width="20%">桌面背景图片：</td>
      <td class="TableData">
      	<input type="file" name="BKIMAGE" class="SmallInput" size="40">
       
      </td>
    </tr>
    <tr id="tr_icon" style="display:;">
      <td nowrap class="TableData" width="20%"><b>菜单图标：</b></td>
      <td class="TableData">
      	<font color=gray>提示：选择不显示图标或单一图标会加速慢速网络时的登录速度</font><br>
         <input type="radio" name="MENU_IMAGE" value="0" id="MENU_IMAGE0" checked><label for="MENU_IMAGE0" style="cursor:hand">每个菜单使用不同图标</label>
         <input type="radio" name="MENU_IMAGE" value="1" id="MENU_IMAGE1" ><label for="MENU_IMAGE1" style="cursor:hand">不显示菜单图标</label>
         <input type="radio" name="MENU_IMAGE" value="2.gif" ><img src="<%=imgPath%>/m/2.gif" align=absmiddle>&nbsp;
         <input type="radio" name="MENU_IMAGE" value="3.gif" ><img src="<%=imgPath%>/m/3.gif" align=absmiddle>&nbsp;
         <input type="radio" name="MENU_IMAGE" value="4.gif" ><img src="<%=imgPath%>/m/4.gif" align=absmiddle>&nbsp;
         <input type="radio" name="MENU_IMAGE" value="5.png" ><img src="<%=imgPath%>/m/5.png" align=absmiddle>&nbsp;
      </td>
    </tr>
    <tr>
      <td nowrap class="TableData" width="20%"><b>默认展开菜单：</b></td>
      <td class="TableData">
        <select name="MENU_EXPAND" class="SmallSelect">
          <option></option>
  <option value="01" >个人事务</option>
  <option value="10" >工作流</option>
  <option value="20" >行政办公</option>
  <option value="30" >知识管理</option>
  <option value="40" >智能门户</option>
  <option value="45" >管理中心</option>
  <option value="50" >人力资源</option>
  <option value="65" >公文管理</option>
  <option value="70" >档案管理</option>
  <option value="90" >项目管理</option>
  <option value="a0" >报表系统</option>
  <option value="b0" >交流园地</option>
  <option value="e0" >T10超能云中心平台</option>
  <option value="y0" >附件程序</option>
  <option value="z0" >系统管理</option>
        </select>
      </td>
    </tr>
    <tr>
        <td colspan=2 class="TableHeader"> 登录选项</td>
    </tr>
    <tr>
      <td nowrap class="TableData" width="20%">登录模式：</td>
      <td class="TableData">
        <select name="MENU_TYPE" class="SmallSelect">
          <option value="1" selected>在本窗口打开OA</option>
          <option value="2" >在新窗口打开OA，显示工具栏</option>
          <option value="3" >在新窗口打开OA，无工具栏</option>
        </select>
        重新登录后生效      </td>
    </tr>
    <tr>
      <td nowrap class="TableData" width="20%">登录后显示的左侧面板：</td>
      <td class="TableData">
        <select name="PANEL" class="SmallSelect">
          <option value="1" selected>导航</option>
          <option value="2" >组织</option>
          <option value="3" >微讯</option>
          <option value="4" >便签</option>
        </select>
              </td>
    </tr>
    <tr id="intro_reshow" style="display:none;">
      <td nowrap class="TableData" width="20%">登录后显示操作提示向导：</td>
      <td class="TableData">
        <select name="INTRO_TYPE" class="SmallSelect">
          <option value="0">否</option>
          <option value="1">是</option>
        </select>
      </td>
    </tr>
    <tr>
      <td colspan=2 class="TableHeader"> 消息提醒设置</td>
      </td>
    </tr>
    <tr id="tr_sms" style="display:;">
      <td nowrap class="TableData" width="20%">消息提醒窗口弹出方式：</td>
      <td class="TableData">
        <select name="SMS_ON" class="SmallSelect">
          <option value="1" selected>自动</option>
          <option value="0" >手动</option>
        </select>
      </td>
    </tr>
    <tr>
      <td nowrap class="TableData" width="20%">消息提醒声音：</td>
      <td class="TableData">
        <select name="CALL_SOUND" class="SmallSelect" onChange="select_sound()">
          <option value="-1" >自定义</option>
          <option value="1" selected>通达OA主题音效</option>
          <option value="9" >长语音</option>
          <option value="8" >短语音</option>
          <option value="2" >激光</option>
          <option value="3" >水滴</option>
          <option value="4" >手机</option>
          <option value="5" >电话</option>
          <option value="6" >鸡叫</option>
          <option value="7" >OICQ</option>
          <option value="0" >无</option>
        </select>
        <span id="CUSTOM_CALL_SOUND" style="display:none;">
          <input type="file" name="CUSTOM_SOUND" class="BigInput" size="20">&nbsp;
          <font color="#FF0000">仅限Flash文件(swf格式)</fong>
        </span>
        <div align="right" id="sms_sound"></div>
      </td>
    </tr>
   <tr>
    <td colspan=2 class="TableHeader">天气预报</td>
   </tr>
   <tr>
    <td nowrap class="TableData" width="20%">是否显示：</td>
    <td nowrap class="TableData">
        <input type="checkbox" name="SHOW_WEATHER" id="SHOW_WEATHER" checked onClick="if(this.checked) $('area_select').style.display='';else $('area_select').style.display='none';">
        <label for="SHOW_WEATHER">显示天气预报</label>
    </td>
   </tr>
   <tr id="area_select" style="display:;">
    <td nowrap class="TableData" width="20%">默认城市：</td>
    <td nowrap class="TableData">
  <select id="w_province" class="SmallSelect" onChange="InitCity(this.value);"></select>
  <select id="w_city" class="SmallSelect" onChange="InitCounty(this.value);"></select>
  <select id="w_county" class="SmallSelect" name="WEATHER_CITY"></select>
    </td>
   </tr>
   <tbody id="tr_rss" style="display:;">
   <tr>
    <td colspan=2 class="TableHeader">今日资讯</td>
   </tr>
   <tr>
    <td nowrap class="TableData" width="20%">是否显示：</td>
    <td nowrap class="TableData">
        <input type="checkbox" name="SHOW_RSS" id="SHOW_RSS" checked>
        <label for="SHOW_RSS">显示今日资讯</label>
    </td>
   </tr>
   </tbody>
    <tr align="center" class="TableControl">
      <td colspan="2" nowrap>
        <input type="hidden" name="WEATHER_CITY_NAME" value="北京_北京_北京">
        <input type="submit" value="保存设置并应用" class="BigButton">&nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form>

<br>

</body>
</html>