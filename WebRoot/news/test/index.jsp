<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/system/returnapi/api.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>财务计提 > 主数据管理 > 合同信息</title>
    <link href="css/Reset.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/new_worklistwithHold.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="css/liu_css1.css"/>
    <link rel="stylesheet" href="css/lgj_css.css" />
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script src="js/liu_script.js"></script>
    <script type="text/javascript" src="js/jsview_lang.js"></script>
    <link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" media="screen" />
    <script type="text/javascript" src="js/jquery.fancybox.pack.js"></script> 
    <script type="text/javascript" src="js/withholdingType.js"></script>
    <script type="text/javascript" src="js/contractInfo.js"></script>
    <script type="text/javascript" src="js/AnnualBudget.js"></script>
    <script src="js/jquery-form.js"></script>
   <script src="js/ajaxfileupload.js"></script>
    <style type="text/css">
        .liu_iframe_room .room_big_span{
            width: 130px;
        }
        .liu_iframe_room .liu_iframe_room_big{
            margin-bottom: 12px;
        }
        .liu_personnel .liu_personnel_name{
            width: 100%;
        }
        
            .pagging01{
            width:700px;
            margin:10px auto;
        }
         .pagging01 #pageSize{
            width:50px;
         }
         
         * {
		  /* -webkit-box-sizing: border-box; */
		     -moz-box-sizing: ;
		          
		  /* box-sizing: border-box; */
		}
    </style>
    <script type="text/javascript">
        jq(function(){
             var id = "";
                if(id != "" && id != null ){
                    jq("#"+id).addClass("on").siblings().removeClass();//removeClass就是删除当前其他类；只有当前对象有addClass("on")；siblings()意思就是当前对象的同级元素，removeClass就是删除；
                    jq(".floor_content > div").hide().eq(jq("#"+id).index()).show();
                    }
                
        });
    </script>
    
</head>
<body>
<div class="inner_wrap addressList">
    <input type="hidden" value="admin" id="userId"/>
    <input type="hidden" value="HZ2880105016d48f015016ef3087002a" id="logindeptId"/>
    <input type="hidden" value="应用分发事业部" id="logindeptName"/>
    <input type="hidden" value="" id="editWithholdId"/>
    <input type="hidden" value="" id="editContractInfoId"/>
    <input type="hidden" value="" id="editAnnualBudgetId"/>
    <div class="lai_draft liu_standard">
        <ul class="tab_title lai_title3" style=" border-top:3px solid #37ade4">
            <li class="on"><p id="contractTitle" href="javascript:void(0);" class="lai_standard_i">合同信息</p></li>
            <li id="withholdOn" class=""><p id="withholdTitle" href="javascript:void(0);" class="lai_standard_i"> 预提类别</p></li>
            <li id="annualOn" class=""><p id="budgetTile"  href="javascript:void(0);" class="lai_standard_i">年度预算 </p></li>
        </ul>
        <div class="clear"></div>

        <div class="floor_content">
            <div class="content">
                <div class="lai_standard_list">

                    <div class="device_row2 lai_dd_po">
                        <a onclick="removepath()" href="#inlineContrInfo" class="fancybox"> <button class="lai_dz_btn4">导入</button></a>
                        <a href=""  onclick="javaScript:exportExcel()"> <button class="lai_dz_btn5">导出</button></a>
                        <a href=""  onclick="deleteContractInfo()"> <button class="lai_dz_btn6">删除</button></a>
                        <div style="float:right">
                            <div class="lai_addbtn rg"><a onclick="addbtn()" href="#addContractInfo" class="fancybox">新增</a></div>
                            <span>在结果中查询</span>
                            <div class="lai_select"><input id="searchID" type="search" value="输入查询关键字" /><a href="#" onclick="resultSearch()" class="btn"></a>
                            </div>

                        </div>
                    </div>
                    <div class="device_row3 lai_dz_tab">

                        <table width="100%" border="1" cellspacing="0" cellpadding="0" style="text-align: center;">
                            <tr class="lai_row3_title">
                                <!-- <td >全选</td> -->
                                <td style="cursor: pointer;"><input id="SelectAll" name="con_chebox" type="checkbox" value="全选" onclick="selectAll();"></input>全选</td>
                                <td>ID</td>
                                <td >标题</td>
                                <td >合同类型</td>
                                <td >合同编号</td>
                                <td >经办部门</td>
                                <td >经办人</td>
                                <td>合同金额</td>
                                <td>对方单位</td>
                                <td>操作</td>
                            </tr> 
                            
                            <tr>
                                <td ><input id="4b388c8b-1612-4fd1-ba79-701c09347d38" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">1</p></td>
                                <td title="weqweqw" id="title4b388c8b-1612-4fd1-ba79-701c09347d38"><a onclick="editContractInfo('4b388c8b-1612-4fd1-ba79-701c09347d38',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">weqweqw</p></a></td>
                                <td title="框架合同" id="contracttype4b388c8b-1612-4fd1-ba79-701c09347d38"><p style="width:120px" class="infonations">框架合同</p></td>
                                <td title="XW49-0101-2016-0000010" id="contractnumber4b388c8b-1612-4fd1-ba79-701c09347d38"><p style="width:120px" class="infonations">XW49-0101-2016-0000010</p></td>
                                <td title="运营商" id="department4b388c8b-1612-4fd1-ba79-701c09347d38"><p style="width:120px" class="infonations">运营商</p></td>
                                <td title="王波" id="userincharge4b388c8b-1612-4fd1-ba79-701c09347d38"><p style="width:120px" class="infonations">王波</p></td>
                                <td title="57678598" id="contractamount4b388c8b-1612-4fd1-ba79-701c09347d38"><p style="width:120px" class="infonations">57678598</p></td>
                                <td title="apple" id="partyb4b388c8b-1612-4fd1-ba79-701c09347d38"><p style="width:120px" class="infonations">apple</p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('4b388c8b-1612-4fd1-ba79-701c09347d38',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td ><input id="d7d5a0e9-18f4-4660-8a69-237ec3e6fd6c" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">2</p></td>
                                <td title="APPLE1" id="titled7d5a0e9-18f4-4660-8a69-237ec3e6fd6c"><a onclick="editContractInfo('d7d5a0e9-18f4-4660-8a69-237ec3e6fd6c',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">APPLE1</p></a></td>
                                <td title="" id="contracttyped7d5a0e9-18f4-4660-8a69-237ec3e6fd6c"><p style="width:120px" class="infonations"></p></td>
                                <td title="XW49-0101-2016-0000010" id="contractnumberd7d5a0e9-18f4-4660-8a69-237ec3e6fd6c"><p style="width:120px" class="infonations">XW49-0101-2016-0000010</p></td>
                                <td title="运营商" id="departmentd7d5a0e9-18f4-4660-8a69-237ec3e6fd6c"><p style="width:120px" class="infonations">运营商</p></td>
                                <td title="王波" id="userincharged7d5a0e9-18f4-4660-8a69-237ec3e6fd6c"><p style="width:120px" class="infonations">王波</p></td>
                                <td title="57678598" id="contractamountd7d5a0e9-18f4-4660-8a69-237ec3e6fd6c"><p style="width:120px" class="infonations">57678598</p></td>
                                <td title="apple" id="partybd7d5a0e9-18f4-4660-8a69-237ec3e6fd6c"><p style="width:120px" class="infonations">apple</p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('d7d5a0e9-18f4-4660-8a69-237ec3e6fd6c',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td ><input id="009c586b-7e40-409e-ad02-4d348049374b" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">3</p></td>
                                <td title="APPLE2" id="title009c586b-7e40-409e-ad02-4d348049374b"><a onclick="editContractInfo('009c586b-7e40-409e-ad02-4d348049374b',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">APPLE2</p></a></td>
                                <td title="框架合同" id="contracttype009c586b-7e40-409e-ad02-4d348049374b"><p style="width:120px" class="infonations">框架合同</p></td>
                                <td title="" id="contractnumber009c586b-7e40-409e-ad02-4d348049374b"><p style="width:120px" class="infonations"></p></td>
                                <td title="运营商" id="department009c586b-7e40-409e-ad02-4d348049374b"><p style="width:120px" class="infonations">运营商</p></td>
                                <td title="王波" id="userincharge009c586b-7e40-409e-ad02-4d348049374b"><p style="width:120px" class="infonations">王波</p></td>
                                <td title="57678598" id="contractamount009c586b-7e40-409e-ad02-4d348049374b"><p style="width:120px" class="infonations">57678598</p></td>
                                <td title="apple" id="partyb009c586b-7e40-409e-ad02-4d348049374b"><p style="width:120px" class="infonations">apple</p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('009c586b-7e40-409e-ad02-4d348049374b',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td ><input id="2ebd8655-567c-4268-9558-bf90701a746c" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">4</p></td>
                                <td title="APPLE3" id="title2ebd8655-567c-4268-9558-bf90701a746c"><a onclick="editContractInfo('2ebd8655-567c-4268-9558-bf90701a746c',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">APPLE3</p></a></td>
                                <td title="框架合同" id="contracttype2ebd8655-567c-4268-9558-bf90701a746c"><p style="width:120px" class="infonations">框架合同</p></td>
                                <td title="XW49-0101-2016-0000010" id="contractnumber2ebd8655-567c-4268-9558-bf90701a746c"><p style="width:120px" class="infonations">XW49-0101-2016-0000010</p></td>
                                <td title="" id="department2ebd8655-567c-4268-9558-bf90701a746c"><p style="width:120px" class="infonations"></p></td>
                                <td title="王波" id="userincharge2ebd8655-567c-4268-9558-bf90701a746c"><p style="width:120px" class="infonations">王波</p></td>
                                <td title="57678598" id="contractamount2ebd8655-567c-4268-9558-bf90701a746c"><p style="width:120px" class="infonations">57678598</p></td>
                                <td title="apple" id="partyb2ebd8655-567c-4268-9558-bf90701a746c"><p style="width:120px" class="infonations">apple</p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('2ebd8655-567c-4268-9558-bf90701a746c',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td ><input id="713a9d07-e40b-472a-9b1e-fcf56f6e7841" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">5</p></td>
                                <td title="APPLE4" id="title713a9d07-e40b-472a-9b1e-fcf56f6e7841"><a onclick="editContractInfo('713a9d07-e40b-472a-9b1e-fcf56f6e7841',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">APPLE4</p></a></td>
                                <td title="框架合同" id="contracttype713a9d07-e40b-472a-9b1e-fcf56f6e7841"><p style="width:120px" class="infonations">框架合同</p></td>
                                <td title="XW49-0101-2016-0000010" id="contractnumber713a9d07-e40b-472a-9b1e-fcf56f6e7841"><p style="width:120px" class="infonations">XW49-0101-2016-0000010</p></td>
                                <td title="运营商" id="department713a9d07-e40b-472a-9b1e-fcf56f6e7841"><p style="width:120px" class="infonations">运营商</p></td>
                                <td title="" id="userincharge713a9d07-e40b-472a-9b1e-fcf56f6e7841"><p style="width:120px" class="infonations"></p></td>
                                <td title="57678598" id="contractamount713a9d07-e40b-472a-9b1e-fcf56f6e7841"><p style="width:120px" class="infonations">57678598</p></td>
                                <td title="apple" id="partyb713a9d07-e40b-472a-9b1e-fcf56f6e7841"><p style="width:120px" class="infonations">apple</p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('713a9d07-e40b-472a-9b1e-fcf56f6e7841',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td ><input id="3ee7364e-21ce-4ee8-9d50-96c9e8190b7b" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">6</p></td>
                                <td title="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" id="title3ee7364e-21ce-4ee8-9d50-96c9e8190b7b"><a onclick="editContractInfo('3ee7364e-21ce-4ee8-9d50-96c9e8190b7b',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p></a></td>
                                <td title="ssssssssssssssssssssssss" id="contracttype3ee7364e-21ce-4ee8-9d50-96c9e8190b7b"><p style="width:120px" class="infonations">ssssssssssssssssssssssss</p></td>
                                <td title="XW49-0101-2016-0000009" id="contractnumber3ee7364e-21ce-4ee8-9d50-96c9e8190b7b"><p style="width:120px" class="infonations">XW49-0101-2016-0000009</p></td>
                                <td title="ddddddddddddddddddddddd" id="department3ee7364e-21ce-4ee8-9d50-96c9e8190b7b"><p style="width:120px" class="infonations">ddddddddddddddddddddddd</p></td>
                                <td title="张翼飞dddddddddddddddddddddddddddddd" id="userincharge3ee7364e-21ce-4ee8-9d50-96c9e8190b7b"><p style="width:120px" class="infonations">张翼飞dddddddddddddddddddddddddddddd</p></td>
                                <td title="" id="contractamount3ee7364e-21ce-4ee8-9d50-96c9e8190b7b"><p style="width:120px" class="infonations"></p></td>
                                <td title="诚迈科技dddddddddddddddddd" id="partyb3ee7364e-21ce-4ee8-9d50-96c9e8190b7b"><p style="width:120px" class="infonations">诚迈科技dddddddddddddddddd</p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('3ee7364e-21ce-4ee8-9d50-96c9e8190b7b',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td ><input id="f0ce84fa-d9f0-4911-8cc9-64d0425e7983" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">7</p></td>
                                <td title="APPLE5" id="titlef0ce84fa-d9f0-4911-8cc9-64d0425e7983"><a onclick="editContractInfo('f0ce84fa-d9f0-4911-8cc9-64d0425e7983',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">APPLE5</p></a></td>
                                <td title="框架合同" id="contracttypef0ce84fa-d9f0-4911-8cc9-64d0425e7983"><p style="width:120px" class="infonations">框架合同</p></td>
                                <td title="XW49-0101-2016-0000010" id="contractnumberf0ce84fa-d9f0-4911-8cc9-64d0425e7983"><p style="width:120px" class="infonations">XW49-0101-2016-0000010</p></td>
                                <td title="" id="departmentf0ce84fa-d9f0-4911-8cc9-64d0425e7983"><p style="width:120px" class="infonations"></p></td>
                                <td title="王波" id="userinchargef0ce84fa-d9f0-4911-8cc9-64d0425e7983"><p style="width:120px" class="infonations">王波</p></td>
                                <td title="57678598" id="contractamountf0ce84fa-d9f0-4911-8cc9-64d0425e7983"><p style="width:120px" class="infonations">57678598</p></td>
                                <td title="" id="partybf0ce84fa-d9f0-4911-8cc9-64d0425e7983"><p style="width:120px" class="infonations"></p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('f0ce84fa-d9f0-4911-8cc9-64d0425e7983',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td ><input id="e9daf5b1-9b87-4765-bc09-dca89a4c5485" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">8</p></td>
                                <td title="APPLE" id="titlee9daf5b1-9b87-4765-bc09-dca89a4c5485"><a onclick="editContractInfo('e9daf5b1-9b87-4765-bc09-dca89a4c5485',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">APPLE</p></a></td>
                                <td title="框架合同" id="contracttypee9daf5b1-9b87-4765-bc09-dca89a4c5485"><p style="width:120px" class="infonations">框架合同</p></td>
                                <td title="XW49-0101-2016-0000010" id="contractnumbere9daf5b1-9b87-4765-bc09-dca89a4c5485"><p style="width:120px" class="infonations">XW49-0101-2016-0000010</p></td>
                                <td title="运营商" id="departmente9daf5b1-9b87-4765-bc09-dca89a4c5485"><p style="width:120px" class="infonations">运营商</p></td>
                                <td title="王波" id="userinchargee9daf5b1-9b87-4765-bc09-dca89a4c5485"><p style="width:120px" class="infonations">王波</p></td>
                                <td title="57678598" id="contractamounte9daf5b1-9b87-4765-bc09-dca89a4c5485"><p style="width:120px" class="infonations">57678598</p></td>
                                <td title="apple" id="partybe9daf5b1-9b87-4765-bc09-dca89a4c5485"><p style="width:120px" class="infonations">apple</p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('e9daf5b1-9b87-4765-bc09-dca89a4c5485',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td ><input id="a37b20fe-fe9b-4a34-9c8d-d0be861e6367" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">9</p></td>
                                <td title="APPLE" id="titlea37b20fe-fe9b-4a34-9c8d-d0be861e6367"><a onclick="editContractInfo('a37b20fe-fe9b-4a34-9c8d-d0be861e6367',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">APPLE</p></a></td>
                                <td title="框架合同" id="contracttypea37b20fe-fe9b-4a34-9c8d-d0be861e6367"><p style="width:120px" class="infonations">框架合同</p></td>
                                <td title="XW49-0101-2016-0000010" id="contractnumbera37b20fe-fe9b-4a34-9c8d-d0be861e6367"><p style="width:120px" class="infonations">XW49-0101-2016-0000010</p></td>
                                <td title="运营商" id="departmenta37b20fe-fe9b-4a34-9c8d-d0be861e6367"><p style="width:120px" class="infonations">运营商</p></td>
                                <td title="王波" id="userinchargea37b20fe-fe9b-4a34-9c8d-d0be861e6367"><p style="width:120px" class="infonations">王波</p></td>
                                <td title="57678598" id="contractamounta37b20fe-fe9b-4a34-9c8d-d0be861e6367"><p style="width:120px" class="infonations">57678598</p></td>
                                <td title="apple" id="partyba37b20fe-fe9b-4a34-9c8d-d0be861e6367"><p style="width:120px" class="infonations">apple</p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('a37b20fe-fe9b-4a34-9c8d-d0be861e6367',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                            <tr>
                                <td ><input id="9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2" mark="checkCon" class="gou" type="checkbox" name="con_chebox" /></td>
                                <td align="center"><p style="width:20px">10</p></td>
                                <td title="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" id="title9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2"><a onclick="editContractInfo('9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2',1)"  href="#editContractInfo"  class="fancybox blue_font nowap" ><p style="width:120px" class="infonations">aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p></a></td>
                                <td title="ssssssssssssssssssssssss" id="contracttype9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2"><p style="width:120px" class="infonations">ssssssssssssssssssssssss</p></td>
                                <td title="XW49-0101-2016-0000009" id="contractnumber9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2"><p style="width:120px" class="infonations">XW49-0101-2016-0000009</p></td>
                                <td title="ddddddddddddddddddddddd" id="department9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2"><p style="width:120px" class="infonations">ddddddddddddddddddddddd</p></td>
                                <td title="张翼飞dddddddddddddddddddddddddddddd" id="userincharge9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2"><p style="width:120px" class="infonations">张翼飞dddddddddddddddddddddddddddddd</p></td>
                                <td title="wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" id="contractamount9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2"><p style="width:120px" class="infonations">wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww</p></td>
                                <td title="诚迈科技dddddddddddddddddd" id="partyb9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2"><p style="width:120px" class="infonations">诚迈科技dddddddddddddddddd</p></td>
                                <td align="center">
                                <a  href="#editContractInfo"  onclick="editContractInfo('9ec7e63b-ca66-44b5-93de-cd1e3ce9dbd2',2)" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                        </table>
                        <div id="addContractInfo" style="display: none;">
                            <div class="liu_iframe_resource03">
                                <h2>新增</h2>
                                <div style="background-color: #e5e5e5;display: inline-block; padding: 15px">
                                    <table>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">ID</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#BFBFBF; border-radius:5px;">
                                                        <input name="" type="text" readonly="readonly" value="" style="width:220px;background:#BFBFBF;">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td  align="right"><span class="liu_sp_table_span1">标题</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="add_title" name="add_title" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">合同类型</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input readonly="readonly" id="add_type" name="add_type" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                        <em onclick="selectTog(this)"></em>
                                                        <ul style="display: none;">
                                                            <li>框架合同</li>
                                                            <li>定额合同</li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">合同编号</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="add_number" name="add_number" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">经办部门</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="add_department" name="add_department" type="text" value="" onfocus="selectTog(this)" style="width:220px">

                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">经办人</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="add_userInCharge" name="add_userInCharge" type="text" value="" onfocus="selectTog(this)" style="width:220px">

                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">合同金额</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="add_amount" name="add_amount" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">对方单位</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="add_partyB" name="add_partyB" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                    </table>
                                </div>

                                <div class="clear"></div>
                                <div class="liu_iframe_resource03_button">
                                    <input type="button" id="addconbtn" onclick="addContractInfo();" value="确定" class="lai_button_blue"/>
                                    <input type="button" value="取消" class="liu_background_grey2 liu_cancel"/>
                                </div>
                            </div>
                        </div>
                        
                        
                        
                       <div id="editContractInfo" style="display: none;">
                            <div class="liu_iframe_resource03">
                                <h2 id="toptitle">编辑</h2>
                                <div style="background-color: #e5e5e5;display: inline-block; padding: 15px">
                                    <table>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">ID</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="edit_id" name="" type="text" readonly="readonly" value="" style="width:220px">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td  align="right"><span class="liu_sp_table_span1">标题</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="edit_title" name="edit_title" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">合同类型</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="edit_type" name="edit_type" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                        <em onclick="selectTog(this)"></em>
                                                        <ul style="display: none;">
                                                            <li>框架合同</li>
                                                            <li>定额合同</li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">合同编号</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="edit_number" name="edit_number" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">经办部门</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="edit_department" name="edit_department" type="text" value="" onfocus="selectTog(this)" style="width:220px">

                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">经办人</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="edit_userInCharge" name="edit_userInCharge" type="text" value="" onfocus="selectTog(this)" style="width:220px">

                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">合同金额</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="edit_amount" name="edit_amount" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                        <tr><td height="10"></td></tr>
                                        <tr>
                                            <td align="right"><span class="liu_sp_table_span1">对方单位</span></td><td>
                                            <div class="addressList" style="float:right; margin-left:5px">
                                                <div class="operate_kuang input280" style="margin: 0">
                                                    <div class="select_kuang input280" style="background:#fff; border-radius:5px;">
                                                        <input id="edit_partyB" name="edit_partyB" type="text" value="" onfocus="selectTog(this)" style="width:220px">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        </tr>
                                    </table>
                                </div>

                                <div class="clear"></div>
                                <div class="liu_iframe_resource03_button">
                                    <input type="button" id="contractbtn" onclick="javaScript:editConInfo()" value="确定" class="lai_button_blue"/>
                                    <input type="button" value="取消" class="liu_background_grey2 liu_cancel"/>
                                </div>
                            </div>
                        </div>
                        
                                      
                    </div>
                </div>

                <div class="pagging01">
                    
                    <tr>
                        <td width=100% bgcolor="#eeeeee" colspan=4 align="center">
                            共 <span id="totalRecords">11</span>条记录, 共 <span id="totalPages">2</span>页，
                            每页显示<input id="pageSize" style="width: 21px;" value=10 readonly="readonly"/>条记录，当前为第 <span id="currentPage">1</span>页，
                            <a  href="finance/contractInfo.jsp?page=1&ressearch=null">首页</a>
                            <a  href="finance/contractInfo.jsp?page=0&ressearch=null">上一页</a>
                            <a  href="finance/contractInfo.jsp?page=2&ressearch=null">下一页</a>
                            <a  href="finance/contractInfo.jsp?page=2&ressearch=null">尾页</a>
                        </td>
                    </tr> 
                    
                    </div> 
            </div>
            <div class="content3 hide">
                <div class="lai_standard_list">

                    <div class="device_row2 lai_dd_po">
                        <a href="#inlineWhit"  class="fancybox"> <button class="lai_dz_btn4">导入</button></a>
                        <a   class=""> <button onclick="exportWith()" class="lai_dz_btn5">导出</button></a>
                        <a   class=""> <button onclick="deleteWith()" class="lai_dz_btn6">删除</button></a>
                        <div style="float:right">
                            <div class="lai_addbtn rg"><a  href="#inline03" onclick="addWithholdType()" class="fancybox">新增</a></div>
                            <span>在结果中查询</span>
                            <div class="lai_select"><input id="searchWith" type="search" value="输入查询关键字" /><a onclick="searchWith();" class="btn"></a>
                            </div>

                        </div>
                    </div>
                    <div class="device_row3 lai_dz_tab">

                        <table id="queryWithInfo" class="yuti_tab" width="100%" border="1" cellspacing="0" cellpadding="0" style="text-align: center;">
                            <tr class="lai_row3_title">
                                <td id="quancheckwith" style="cursor: pointer;"><input id="SelectAllWith" class="gou" name="" type="checkbox" value="全选" onclick="selectAll();"></input>全选</td>
                                <td>ID</td>
                                <td >标题</td>
                                <td >发起人</td>
                                <td >匹配合同</td>
                                <td >公司代码</td>
                                <td >部门代码</td>
                                <td>借方会计科目代码</td>
                                <td>贷方会计科目代码</td>
                                <td>类型说明</td>
                                <td>累计预提金额</td>
                                <td>累计付款金额</td>
                                <td>是否有效</td>
                                <td>操作</td>
                            </tr>
                                    
                            <tr>
                                <td colspan="14" align="center">无记录</td>
                            </tr>
                            
                            
                            
                        </table>
                        <form name="addwithhold" id="addwithhold" action="finance/withholdingType.jsp" method="post" >
                        <div id="inline03" style="width:630px;display: none;">
                            <div class="liu_iframe_room">
                                <div class="liu_iframe_room_title">
                                    <h2> 新增</h2>
                                </div>
                                <div class="liu_iframe_room_box">
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">标题</span>
                                        <div class="lf ">
                                            <input maxlength="200" name="addTitle" id="addTitle" type="text"/>
                                        </div>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">发起人</span>
                                        <div class="lf room_input_img">
                                            <input type="text" name="attendPersonId" id="attendPersonId" value="" readonly="readonly"/>
                                            <input type="hidden"name="attendPersonLoginNameId" id="attendPersonLoginNameId" value=""/>
                                            <a href="#inline04" onclick="addPersonFun('y')" class="fancybox"><img src="css/images/icon_reny.png" alt=""/></a>
                                        </div>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">匹配合同</span>
                                        <div class="lf room_input_img">
                                            <input type="text" name="attendContractId" id="attendContractId" value="" readonly="readonly"/>
                                            <input type="hidden" name="attendContractNameId" id="attendContractNameId" value=""/>
                                            <a href="#inline05" onclick="addContractFun('y')" class="fancybox"><img src="css/images/icon04.png" alt=""/></a>
                                        </div>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">公司代码</span>
                                        <input maxlength="200" id="addCUCXW_CO" name="addCUCXW_CO" type="text"/>
                                    </div>

                                     <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">部门代码</span>
                                        <input maxlength="200" id="addCUCXW_CC" name="addCUCXW_CC" type="text"/>
                                    </div>
                                    
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">借方会计科目代码</span>
                                        <input maxlength="200" id="addCUCXW_AC_Debit" name="addCUCXW_AC_Debit" type="text"/>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">贷方会计科目代码</span>
                                        <input maxlength="200" id="addCUCXW_AC_Credit" name="addCUCXW_AC_Credit" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">类型说明</span>
                                        <input maxlength="200" id="addMemo" name="addMemo" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">累计预提金额</span>
                                        <input id="addWithholdAmount" name="addWithholdAmount" type="text" value="0"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">累计付款金额</span>
                                        <input id="addPaymentAmount" name="addPaymentAmount" type="text" value="0"/>
                                    </div>
                                    <div class="switch switchPa">
                                        <span class="lf liu_sp_table_span1" style="width: 130px ; text-align: right; margin-right: 10px">是否有效</span>
                                        <div class="lf">
                                            <input  type="radio" name="choose2" id="male" checked/>
                                            <label for="male">是</label>
                                        </div>
                                        <div class="lf">
                                            <input type="radio" name="choose2"  id="male2"/>
                                            <label for="male2">否</label>
                                        </div>
                                    </div>

                                </div>
                                <div class="clear"></div>
                                <div class="room_button">
                                    <button onclick="addfunOk()" id="addwithid" class="liu_button_orange lai_bg_blue">确定</button>
                                    <button class="liu_cancel liu_button_grey">取消</button>
                                </div>

                            </div>
                        </div>
                        </form>

                    <form name="editwithhold" id="editwithhold" action="" method="post" >
                        <div id="inline03edit" style="width:630px;display: none;">
                            <div class="liu_iframe_room">
                                <div class="liu_iframe_room_title">
                                    <h2 id="editmsg"> 编辑</h2>
                                </div>
                                <div class="liu_iframe_room_box">
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">标题</span>
                                        <div class="lf ">
                                            <input maxlength="200" name="editTitle" id="editTitle" type="text"/>
                                        </div>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">发起人</span>
                                        <div class="lf room_input_img">
                                            <input type="text" name="editattendPersonId" id="editattendPersonId" value="" readonly="readonly"/>
                                            <input type="hidden"name="editattendPersonLoginNameId" id="editattendPersonLoginNameId" value=""/>
                                            <a href="#inline04" id="editAddPersonshow" onclick="editAddPersonFun()" class="fancybox"><img src="css/images/icon_reny.png" alt=""/></a>
                                        </div>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">匹配合同</span>
                                        <div class="lf room_input_img">
                                            <input type="text" name="editattendContractId" id="editattendContractId" value="" readonly="readonly"/>
                                            <input type="hidden" name="editattendContractNameId" id="editattendContractNameId" value=""/>
                                            <a href="#inline05" id="editContractshow" onclick="editContractFun()" class="fancybox"><img src="css/images/icon04.png" alt=""/></a>
                                        </div>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">公司代码</span>
                                        <input maxlength="200" id="editCUCXW_CO" name="editCUCXW_CO" type="text"/>
                                    </div>

                                     <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">部门代码</span>
                                        <input id="editCUCXW_CC" name="editCUCXW_CC" type="text"/>
                                    </div>
                                    
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">借方会计科目代码</span>
                                        <input maxlength="200" id="editCUCXW_AC_Debit" name="editCUCXW_AC_Debit" type="text"/>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">贷方会计科目代码</span>
                                        <input maxlength="200" id="editCUCXW_AC_Credit" name="editCUCXW_AC_Credit" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">类型说明</span>
                                        <input maxlength="200" id="editMemo" name="editMemo" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">累计预提金额</span>
                                        <input id="editWithholdAmount" name="editWithholdAmount" type="text" value="0"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">累计付款金额</span>
                                        <input id="editPaymentAmount" name="editPaymentAmount" type="text" value="0"/>
                                    </div>
                                    <div class="switch switchPa">
                                        <span class="lf liu_sp_table_span1" style="width: 130px ; text-align: right; margin-right: 10px">是否有效</span>
                                        <div class="lf">
                                            <input  type="radio" name="choose2" id="maleedit" checked/>
                                            <label for="male">是</label>
                                        </div>
                                        <div class="lf">
                                            <input type="radio" name="choose2"  id="male2edit"/>
                                            <label for="male2">否</label>
                                        </div>
                                    </div>

                                </div>
                                <div class="clear"></div>
                                <div class="room_button">
                                    <button onclick="editfunOk()" id="editwithid" class="liu_button_orange lai_bg_blue">确定</button>
                                    <button class="liu_cancel liu_button_grey">取消</button>
                                </div>

                            </div>
                        </div>
                        </form>

                        <div id="inline04" style="width: 910px;display: none;">
                            <div class="liu_personnel">
                                <div class="liu_iframe_room_title">
                                    <h2>用户选择</h2>
                                </div>
                                <div class="liu_personnel_box">
                                    <div class="lf">
                                        <div class="lf">
                                            <span class="lf">事业部</span>
                                            <div class="addressList lf">
                                                <div class="operate_kuang">
                                                    <div class="select_kuang" style="margin-right: 6px;">
                                                        <input name="" type="text" value="" onfocus="selectBusDept(this)">
                                                        <em onclick="selectBusDept(this)"></em>
                                                        <ul style="display: none;" id="busDeptId">
                                                        
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="lf">
                                            <span class="lf">部门</span>
                                            <div class="addressList lf">
                                                <div class="operate_kuang">
                                                    <div class="select_kuang" style="margin-right: 6px;">
                                                        <input name="" type="text" value="" onfocus="selectAddDept(this)">
                                                        <em onclick="selectAddDept(this)"></em>
                                                        <ul style="display: none;" id="addDeptId">
                                                        
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="addressList rg">
                                        <div class="operate_kuang">
                                            <div class="search_field">
                                                <div class="search_kuang"><input name="" type="text" placeholder="请输入查询关键字"/><a href="#" class="btn" onclick="searchPersonFun()"></a></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="liu_personnel_name">
                                         <ul id="personShowId">
                                        </ul>
                                    </div>

                                    <div class="liu_personnel_history">
                                    </div>
                                    <div class="liu_personnel_choice">
                                        <div class="liu_sp_table2 liu_sp_table">
                                            <div class="liu_draft ">
                                                <input type="checkbox" name="check-box">
                                                <span>仅选择在职员工</span>
                                            </div>
                                        </div>
                                        <div class="clear"></div>

                                        <div class="liu_personnel_choice_box">
                                            <div class="liu_personnel_choice_name lf">
                                                <ul id= "selectedUserId">
                            
                                                 </ul>
                                            </div>
                                            <div class="liu_personnel_choice_button rg">
                                                <input type="button" value="清除"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="clear"></div>
                                <div class="room_button">
                                    <a href="#inline03" onclick="attendPersonOkFun(this)" class="fancybox liu_button_orange  lai_bg_blue">确定</a>
                                     <input type="hidden" value="" id="addOrEditId"/>
                                    <a class="liu_cancel liu_button_grey">取消</a>
                                </div>

                            </div>

                        </div>

                        <div id="inline05" style="width: 1000px;display: none;">
                            <div class="liu_personnel">
                                <div class="liu_iframe_room_title">
                                    <h2>选择匹配合同</h2>
                                </div>
                                <div class="liu_personnel_box">

                                    <div class="addressList ">
                                        <div class="operate_kuang">
                                            <div class="search_field" style="float: left">
                                                <div class="search_kuang" style="width: 945px">
                                                    <input style="width: 880px" name="" id="queryWithContract" type="text" value="请输入查询关键字"/><a href="#" onclick="resultSearchwith();" class="btn"></a>
                                                </div>

                                            </div>
                                        </div>

                                    </div>

                                    <div class="liu_personnel_history pipei_tab queryContractInfo">
                                        <table id="queryContractInfo">
                                            <tr class="lai_font_wei">
                                                <td>标题</td>
                                                <td>合同类型</td>
                                                <td>合同编号</td>
                                                <td>经办部门</td>
                                                <td>经办人</td>
                                                <td>合同金额</td>
                                                <td>对方单位</td>
                                            </tr>
                                          
                                        </table>
                                    </div>
                                    <div class="liu_personnel_choice">

                                        <div class="clear"></div>

                                        <div class="liu_personnel_choice_box">
                                            <div class="liu_personnel_choice_name lf" style="width: 870px">
                                                <ul id="contractNameId">

                                                </ul>
                                            </div>
                                            <div class="liu_personnel_choice_button rg">
                                                <input type="button" value="清除"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="clear"></div>
                                <div class="room_button">
                                    <a href="#inline03" onclick="attendContractOkFun(this)" class="fancybox liu_button_orange lai_bg_blue">确定</a>
                                    <input type="hidden" value="" id="addOrEditIdContract"/>
                                    <a class="liu_cancel liu_button_grey">取消</a>
                                </div>

                            </div>

                        </div>
                    </div>
                </div>

                <div class="paging" id="withPage">
  
                </div>
            </div>
            
            <div class="content2 hide">
                <div class="lai_standard_list">
                   <div class="device_row2 lai_dd_po">
                        <a href="#"  > <button onclick="exportAnnualBudget()" class="lai_dz_btn5">导出</button></a>
                        <a href="#inline1" > <button class="lai_dz_btn6" onclick="deleteAnnualBudget()">删除</button></a>
                        <div style="float:right">
                            <div class="lai_addbtn rg"><a  href="#inline02" class="fancybox" onclick="addAnnualBudgetType()">新增</a></div>
                            <span>在结果中查询</span>
                            <div class="lai_select"><input type="search" id="search" value="输入查询关键字" /><a href="#" onclick="queryAnnualBudgets()" class="btn"></a>
                            </div>

                        </div>
                    </div>
                    <div class="device_row3 lai_dz_tab">

                        <table id="queryAnnualBudget" width="100%" border="1" cellspacing="0" cellpadding="0" style="text-align: center;">
                            <tr class="lai_row3_title">
                                <td id="" style="cursor: pointer;"><input id="SelectAnnual" class="gou" name="con_chebox" type="checkbox" onclick="selectAll();"></input></td>
                                <td >ID</td>
                                <td >标题</td>
                                <td >预算年度</td>
                                <td >工资预算</td>
                                <td >人力成本预算</td>
                                <td >年度累计工资成本-预提</td>
                                <td>年度累计工资成本-计提</td>
                                <td>年度累计人力成本-预提</td>
                                <td>年度累计人力成本-计提</td>
                                <td>操作</td>
                            </tr>
                             
                            <tr  mark='showwith'>
                                <td ><input class="gou" mark="checkwith" id="4719742d-e80e-4273-8b1d-2f555dc31918"  type="checkbox" name="check-box" /></td>
                                <td >1</td>
                                <td>
                                 <p style="width:90px;" class="infonations">
                                <a id="title4719742d-e80e-4273-8b1d-2f555dc31918"  title="asd" href="#inline02ck" onclick="findAnnualBudgetId('4719742d-e80e-4273-8b1d-2f555dc31918',2)" class="fancybox blue_font">asd</a>
                                </p>
                                </td>
                                <td title=“as” id="budgetyear4719742d-e80e-4273-8b1d-2f555dc31918"><p style="width:60px" class="infonations">as</p></td>
                                <td title=“23” id="salarybudget4719742d-e80e-4273-8b1d-2f555dc31918"><p style="width:70px" class="infonations">23</p></td>
                                <td title=“23” id="hrcostbudget4719742d-e80e-4273-8b1d-2f555dc31918"><p style="width:70px" class="infonations">23</p></td>
                                <td title=“32” id="salaryamount4719742d-e80e-4273-8b1d-2f555dc31918"><p style="width:110px" class="infonations">32</p></td>
                                <td title=“32” id="hrcostamount4719742d-e80e-4273-8b1d-2f555dc31918"><p style="width:110px" class="infonations">32</p></td>
                                <td title=“32” id="salaryamountaccrued4719742d-e80e-4273-8b1d-2f555dc31918"><p style="width:110px" class="infonations">32</p></td>
                                <td title=“” id="hrcostamountaccrued4719742d-e80e-4273-8b1d-2f555dc31918"><p style="width:110px" class="infonations"></p></td>
                                <td align="center">
                                <a  href="#inline02bj" onclick="editAnnualBudgetId('4719742d-e80e-4273-8b1d-2f555dc31918')" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                             
                            <tr  mark='showwith'>
                                <td ><input class="gou" mark="checkwith" id="4b85c7ae-84a6-4bcc-9fac-6119ad033e4b"  type="checkbox" name="check-box" /></td>
                                <td >2</td>
                                <td>
                                 <p style="width:90px;" class="infonations">
                                <a id="title4b85c7ae-84a6-4bcc-9fac-6119ad033e4b"  title="ttt" href="#inline02ck" onclick="findAnnualBudgetId('4b85c7ae-84a6-4bcc-9fac-6119ad033e4b',2)" class="fancybox blue_font">ttt</a>
                                </p>
                                </td>
                                <td title=“ttt” id="budgetyear4b85c7ae-84a6-4bcc-9fac-6119ad033e4b"><p style="width:60px" class="infonations">ttt</p></td>
                                <td title=“47” id="salarybudget4b85c7ae-84a6-4bcc-9fac-6119ad033e4b"><p style="width:70px" class="infonations">47</p></td>
                                <td title=“45” id="hrcostbudget4b85c7ae-84a6-4bcc-9fac-6119ad033e4b"><p style="width:70px" class="infonations">45</p></td>
                                <td title=“66” id="salaryamount4b85c7ae-84a6-4bcc-9fac-6119ad033e4b"><p style="width:110px" class="infonations">66</p></td>
                                <td title=“” id="hrcostamount4b85c7ae-84a6-4bcc-9fac-6119ad033e4b"><p style="width:110px" class="infonations"></p></td>
                                <td title=“” id="salaryamountaccrued4b85c7ae-84a6-4bcc-9fac-6119ad033e4b"><p style="width:110px" class="infonations"></p></td>
                                <td title=“” id="hrcostamountaccrued4b85c7ae-84a6-4bcc-9fac-6119ad033e4b"><p style="width:110px" class="infonations"></p></td>
                                <td align="center">
                                <a  href="#inline02bj" onclick="editAnnualBudgetId('4b85c7ae-84a6-4bcc-9fac-6119ad033e4b')" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                             
                            <tr  mark='showwith'>
                                <td ><input class="gou" mark="checkwith" id="50cc5329-4315-4247-8e4f-3511598f0f6f"  type="checkbox" name="check-box" /></td>
                                <td >3</td>
                                <td>
                                 <p style="width:90px;" class="infonations">
                                <a id="title50cc5329-4315-4247-8e4f-3511598f0f6f"  title="tt" href="#inline02ck" onclick="findAnnualBudgetId('50cc5329-4315-4247-8e4f-3511598f0f6f',2)" class="fancybox blue_font">tt</a>
                                </p>
                                </td>
                                <td title=“2016” id="budgetyear50cc5329-4315-4247-8e4f-3511598f0f6f"><p style="width:60px" class="infonations">2016</p></td>
                                <td title=“55” id="salarybudget50cc5329-4315-4247-8e4f-3511598f0f6f"><p style="width:70px" class="infonations">55</p></td>
                                <td title=“55” id="hrcostbudget50cc5329-4315-4247-8e4f-3511598f0f6f"><p style="width:70px" class="infonations">55</p></td>
                                <td title=“45” id="salaryamount50cc5329-4315-4247-8e4f-3511598f0f6f"><p style="width:110px" class="infonations">45</p></td>
                                <td title=“45” id="hrcostamount50cc5329-4315-4247-8e4f-3511598f0f6f"><p style="width:110px" class="infonations">45</p></td>
                                <td title=“56” id="salaryamountaccrued50cc5329-4315-4247-8e4f-3511598f0f6f"><p style="width:110px" class="infonations">56</p></td>
                                <td title=“65” id="hrcostamountaccrued50cc5329-4315-4247-8e4f-3511598f0f6f"><p style="width:110px" class="infonations">65</p></td>
                                <td align="center">
                                <a  href="#inline02bj" onclick="editAnnualBudgetId('50cc5329-4315-4247-8e4f-3511598f0f6f')" class="fancybox lai_btn_on" style="padding:5px">编辑</a>
                                </td>
                            </tr>
                            
                        </table>
                        <form name="addAnnualBudget " id="addAnnualBudget" method="post" >
                        <div id="inline02" style="width: 800px;display: none;">
                            <div class="liu_iframe_room">
                                <div class="liu_iframe_room_title">
                                    <h2> 新增</h2>
                                </div>
                                <div class="liu_iframe_room_box">
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">标题</span>
                                        <input name="title" id="title" type="text"/>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">预算年度</span>
                                        <input id="budgetyear" name="budgetyear" type="text"/>
                                    </div>

                                     <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">工资预算</span>
                                        <input id="salarybudget" name="salarybudget" type="text"/>
                                    </div>
                                    
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">人力成本预算</span>
                                        <input id="hrcostbudget" name="hrcostbudget" type="text"/>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度工资额预提</span>
                                        <input id="salaryamount" name="salaryamount" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度人力成本预提</span>
                                        <input id="hrcostamount" name="hrcostamount" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度工资额计提</span>
                                        <input id="salaryamountaccrued" name="salaryamountaccrued" type="text" value="0"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度人力成本计提</span>
                                        <input id="hrcostamountaccrued" name="hrcostamountaccrued" type="text" value="0"/>
                                    </div>
                                    
                                </div>
                                <div class="clear"></div>
                                <div class="room_button">
                                    <button onclick="queding()"  class="liu_button_orange lai_bg_blue">确定</button>
                                    <button class="liu_cancel liu_button_grey">取消</button>
                                </div>

                            </div>
                        </div>
                        </form>
                        <form name="editAnnualBudget " id="editAnnualBudget" method="post" >
                        <div id="inline02bj" style="width: 800px;display: none;">
                            <div class="liu_iframe_room">
                                <div class="liu_iframe_room_title">
                                    <h2>编辑</h2>
                                </div>
                                <div class="liu_iframe_room_box">
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">标题</span>
                                        <input name="edittitle" id="edittitle" type="text"/>
                                        <input name="editid" id="editid" type="hidden"/>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">预算年度</span>
                                        <input id="editbudgetyear" name="editbudgetyear" type="text"/>
                                    </div>

                                     <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">工资预算</span>
                                        <input id="editsalarybudget" name="editsalarybudget" type="text"/>
                                    </div>
                                    
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">人力成本预算</span>
                                        <input id="edithrcostbudget" name="edithrcostbudget" type="text"/>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度工资额预提</span>
                                        <input id="editsalaryamount" name="editsalaryamount" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度人力成本预提</span>
                                        <input id="edithrcostamount" name="edithrcostamount" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度工资额计提</span>
                                        <input id="editsalaryamountaccrued" name="editsalaryamountaccrued" type="text" value="0"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度人力成本计提</span>
                                        <input id="edithrcostamountaccrued" name="edithrcostamountaccrued" type="text" value="0"/>
                                    </div>
                                    
                                </div>
                                <div class="clear"></div>
                                <div class="room_button">
                                    <button onclick="editAnnualBudgetQD()" id="AnnualQD" class="liu_button_orange lai_bg_blue">确定</button>
                                    <button class="liu_cancel liu_button_grey">取消</button>
                                </div>

                            </div>
                        </div>
                        </form>
                        <div id="inline02ck" style="width: 800px;display: none;">
                            <div class="liu_iframe_room">
                                <div class="liu_iframe_room_title">
                                    <h2>查看</h2>
                                </div>
                                <div class="liu_iframe_room_box">
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">标题</span>
                                        <input name="findtitle" id="findtitle" type="text"/>
                                        <input name="findid" id="findid" type="hidden"/>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">预算年度</span>
                                        <input id="findbudgetyear" name="findbudgetyear" type="text"/>
                                    </div>

                                     <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">工资预算</span>
                                        <input id="findsalarybudget" name="findsalarybudget" type="text"/>
                                    </div>
                                    
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">人力成本预算</span>
                                        <input id="findhrcostbudget" name="findhrcostbudget" type="text"/>
                                    </div>

                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度工资额预提</span>
                                        <input id="findsalaryamount" name="findsalaryamount" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度人力成本预提</span>
                                        <input id="findhrcostamount" name="findhrcostamount" type="text"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度工资额计提</span>
                                        <input id="findsalaryamountaccrued" name="findsalaryamountaccrued" type="text" value="0"/>
                                    </div>
                                    <div class="liu_iframe_room_big">
                                        <span class="lf room_big_span">年度人力成本计提</span>
                                        <input id="findhrcostamountaccrued" name="findhrcostamountaccrued" type="text" value="0"/>
                                    </div>
                                    
                                </div>
                                <div class="clear"></div>
                                <div class="room_button">
                                    <button class="liu_cancel liu_button_grey">关闭</button>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="paging" id="withPage">
  
                    <tr>
                        <td width=100% bgcolor="#eeeeee" colspan=4 align="center">
                            共 <span id="totalRecords">3</span>条记录, 共 <span id="totalPages">1</span>页，
                            每页显示<input id="pageSizeN" style="width:21px;" value=10 readonly="readonly"/>条记录，当前为第 <span id="currentPageN">1</span>页，
                            <a id="firstPageN" href="finance/contractInfo.jsp?pagew=1&id=annualOn">首页</a>
                            <a id="prePageN" href="finance/contractInfo.jsp?pagew=0&id=annualOn">上一页</a>
                            <a id="nextPageN" href="finance/contractInfo.jsp?pagew=2&id=annualOn">下一页</a>
                            <a id="lastPageN" href="finance/contractInfo.jsp?pagew=1&id=annualOn">尾页</a>
                        </td>
                    </tr> 
                    
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer_navBg">
    <ul class="index_wrap footer_nav">
        <li>
            <h4>工会俱乐部</h4>
            <a href="#">图书借阅</a>
            <a href="#">户外活动</a>
            <a href="#">羽毛球</a>
            <a href="#">游泳</a>
        </li>
        <li>
            <h4>文档下载</h4>
            <a href="#">合同模板</a>
            <a href="#">规章制度</a>
            <a href="#">VI规范</a>
            <a href="#">HR文件</a>
        </li>
        <li>
            <h4>工会俱乐部</h4>
            <a href="#">图书借阅</a>
            <a href="#">户外活动</a>
            <a href="#">羽毛球</a>
            <a href="#">游泳</a>
        </li>
        <li>
            <h4>联通集团</h4>
            <a href="#">云门户</a>
        </li>
        <li>
            <h4>文档下载</h4>
            <a href="#">合同模板</a>
            <a href="#">规章制度</a>
            <a href="#">VI规范</a>
            <a href="#">HR文件</a>
        </li>
        <li>
            <h4>工会俱乐部</h4>
            <a href="#">图书借阅</a>
            <a href="#">户外活动</a>
            <a href="#">羽毛球</a>
            <a href="#">游泳</a>
        </li>
        <li class="last">
            <h4>文档下载</h4>
            <a href="#">合同模板</a>
            <a href="#">规章制度</a>
            <a href="#">VI规范</a>
            <a href="#">HR文件</a>
        </li>
    </ul>
</div>
<div class="footer_bg">
    <div class="index_wrap footer">&copy; 2014 联通应用商店 &middot; 运营中心</div>
</div>

<div id="inlineContrInfo" style="display: none; ">
<form id="importCintractForm"  method="post" enctype="multipart/form-data">
    <div class="liu_iframe_resource03">
        <h2>合同信息模板导入</h2>
        <div style="background-color: #e5e5e5;display: inline-block">
            <div class="liu_iframe_resource03_table">
                <div class="lai_server_box">
                    <span class="lf">选择文件</span>
                    <div class="lai_filebox lf">
                        <input type='text' name='contractInfoPath' id='contractInfoPath' class='txt' value="" />
                        <input type='button' class='btn' style="background: url(css/images/add_attached.png);height: 24px;width: 24px;position: absolute;top: 63px;left: 609px;cursor: pointer; "/>
                        <input type="file" name="fileFieldContract" class="file" id="fileFieldContract" onChange="copyVal(this)" />
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>

        <div class="clear"></div>
        <div class="liu_iframe_resource03_button">
            <input type="button" onclick="importContractFile();" id="importContId" value="确定" class="liu_background_orange" style="background-color: #37ade4"/>
            <input type="button" value="取消" class="liu_background_grey2 liu_cancel"/>
        </div>
    </div>
    <div id="result" style="display: none;color:red;margin-left:20px;"></div>
</form>
<div id="msg" style="margin-left: 35px; "></div>
</div>

<div id="inlineWhit" style="display: none; ">
<form id="importWithForm" action="wostorefinance/action/WithholdingAction.importwithhold.do" method="post" enctype="multipart/form-data">
    <div class="liu_iframe_resource03">
        <h2>预提类别模板导入</h2>
        <div style="background-color: #e5e5e5;display: inline-block">
            <div class="liu_iframe_resource03_table">

                <div class="lai_server_box">
                    <span class="lf">选择文件</span>
                    <div class="lai_filebox lf">
                        <input type='text' name='textfieldWith' id='textfieldWith' class='txt' value="" />
                        <input type='button' class='btn' style="background: url(css/images/add_attached.png);height: 24px;width: 24px;position: absolute;top: 63px;left: 609px;cursor: pointer; "/>
                        <input type="file" name="fileFieldWith" class="file" id="fileFieldWith" onChange="copyValue(this)" />
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>

        <div class="clear"></div>
        <div class="liu_iframe_resource03_button">
            <input type="button" onclick="importWithFile();" id="importwithid" value="确定" class="liu_background_orange" style="background-color: #37ade4"/>
            <input type="button" value="取消" class="liu_background_grey2 liu_cancel"/>
        </div>
    </div>
    <div id="result" style="display: none;color:red;margin-left:20px;"></div>
</form>
<div id="msg" style="margin-left: 35px; "></div>
</div>
</body>
<script>
var selUserList = "";
var selLoginnameList = "";

var selConList = "";
var selContractList = "";
    jq(document).ready(function() {
        selUserList="";
        selLoginnameList = "";     
        selConList = ""; 
        selContractList="";
        jq('.fancybox').fancybox();

        jq("#changeInfo").html(jq("#withholdTitle").html());
        
        
        jq("#withholdTitle").bind("click",function(){
        });
        
        jq("#budgetTile").bind("click",function(){
            jq("#changeInfo").html(jq("#budgetTile").html());
        });
        
        jq("#contractTitle").bind("click",function(){
            jq("#changeInfo").html(jq("#contractTitle").html());
        });
    });

     //关闭悬浮框
    jq(".liu_cancel").bind('click', function () {
        
        jq('.fancybox-close').click();
        
    });
</script>
</html>