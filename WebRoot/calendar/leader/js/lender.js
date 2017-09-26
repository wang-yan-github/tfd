var addUpdateCalFroms =  "";
var weekArray = ['','星期一' , '星期二' ,'星期三','星期四' , '星期五' ,'星期六' ,'星期日'];


	/**
 * 获取日程byId
 * @param id
 * @returns
 */
function getCalByIds(id){
	var url =  contextPath +  "/calendar/act/CalendarAct/selectByIdAct.act";
	var para = {sid:id};
	var returnData;
	$.ajax({
		url:url,
		dataType:"json",
		data:para,
		async:false,
		success:function(data){
			if(data){
				returnData=data;
			}else{
				alert("无数据！");
				}
			}
	});
	return returnData;
}
/***
 * 设置优先级别
 */
function setLevel(){
	$("#color_menu").show();
	$("#color_menu a").click(function(event){
		var index =$(this).attr('index') ;
		if(!index || index == 0 ){
			index = "";
		}
		$("#cal_color").attr("class","color" + index);
		$("#color_menu").hide();
		$("#calLevel").val(index);
	});
}

function check() {
	var CAL_CONTENT = window["modaliframe"].$("#CAL_CONTENT");
	if(!CAL_CONTENT.val()){
		//alert("内容是必填项！");
		return false ;
	}
	var START_DATE = window["modaliframe"].$("#START_DATE");
	var END_DATE = window["modaliframe"].$("#END_DATE");
	if(START_DATE.val() == ''){
		alert("开始时间是必填项！");
		START_DATE[0].select();
		return false ;
	}
	if(END_DATE.val() == ''){
		alert("结束时间是必填项！");
		END_DATE[0].select();
		return false ;
	}

	if(END_DATE.val() != ''){
		if(START_DATE.val() >= END_DATE.val() ){
			alert("结束日期不能小于开始日期");
			//$.jBox.alert("结束日期不能小于开始日期", '日程');
			END_DATE[0].select();
			return false;
		}
	}
	
	var calAffType = window["modaliframe"].$("#calAffType")[0] ;
	if(calAffType.checked  ==  true){
		var remindTime = $("#remindTime")[0];
		// if(!isTimeTee(remindTime.value)){
			// alert("提醒时间格式不对,如(13:30:30)");
			// remindTime.focus();
			// remindTime.select();
			// return false;
		// }
	}
	return true;
}
//判断正整数  
function checkRate(num)  
{  
    if(parseInt(num)==num){
    	return true;
    }else{
    	return false;
    } 
}  
function deleteCal(calId){
	if(confirm("确定删除?")){
		var para = {sid:calId};
		var url = contextPath + "/calendar/act/CalendarAct/deleteCalAct.act";
		$.ajax({
			url:url,
			dataType:"text",
			data:para,
			async:false,
			success:function(data){
				if(data=="1"){
					top.layer.msg('删除成功');
					parent.$(".btn-close").trigger("click");
					parent.history.go(0);
					return false;
				}else{
					top.layer.msg('删除失败');
				}
				}
		});
	}
}
function addOrUpdateCal(){
	window["modaliframe"].$('#form1').bootstrapValidator('revalidateField', 'content');
	if (check()){
		var url = contextPath + "/calendar/act/CalendarAct/addOrUpdate.act";
	   var para =window["modaliframe"].$("#form1").serialize();
	   para += para +"&smsReminds="+window["modaliframe"].getsmsRemind();
		$.ajax({
			url:url,
			dataType:"json",
			data:para,
			type:"post",
			async:false,
			success:function(data){
				if(data){
					 top.layer.msg('保存成功');
					 parent.$('#myModals').modal("hide");
					 history.go(0);
					return false;
				}else{
					top.layer.msg("保存失败");
				}
			}
		});
	}
}
function setCalAffType(obj){
	if(obj.checked == true){
		$("#remindTypeTr").show();
		$("#remindTimeTr").show();
		$("#beforeRemindTr").hide();
		$("#FrequencyTr").show();
		$("#endTr").show();
	}else{
		$("#remindTypeTr").hide();
		$("#remindTimeTr").hide();
		$("#FrequencyTr").hide();
		$("#endTr").hide();
		$("#beforeRemindTr").show();//提前多少时间提醒
	}
}
function getAddUpdateCalFroms(){
	var remindDate3 = "<select style=\"width:25%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate3\" name=\"remindDate3\">";//周
	for(var i= 1 ;  i <= 7 ;i++){
		remindDate3 = remindDate3 + "<option value=\""+i+"\"  >" + weekArray[i] +"</option>";

	}
	remindDate3 = remindDate3 + "</select>";
	
	var dayStr = "";//日
	for(var i= 1 ;  i < 32 ;i++){
		dayStr = dayStr + "<option value=\""+ i +"\"  >" + i +"日</option>";

	}

	var monthStr = "";//月
	for(var i= 1 ;  i < 13 ;i++){
		monthStr = monthStr + "<option value=\""+ i +"\" >" + i +"月</option>";

	}

	
	var form = "<input   type=\"hidden\" name=\"calId\" id=\"Up_calId\" value=\"\" />"+
	"<input   type=\"hidden\" name=\"accountId\" id=\"Up_accountId\" value=\"\" />"+
	"<div class=\"panel panel-info\">"+
	"<div class=\"panel-body\" style=\"border:none;\" >"+
	 "<table class=\"table table-striped\" width=\"100%\" align=\"center\">"+
		"<tr>"+
			"<td nowrap>事务内容：</td>"+
			"<td nowrap align=\"left\">"+
				 "<textarea  name=\"content\" id=\"CAL_CONTENT\" style=\"width:75%;float:left;\" class=\"form-control\" required=\"true\" rows=\"3\" cols=\"30\"></textarea><font color=\"red\">(必填)</font>"+
         "</td>"+
		"</tr>"+
		
		"<tr>"+
			"<td nowrap>开始时间：</td>"+
			"<td nowrap align=\"left\">"+
			 	"<input type=\"text\"  name=\"startDate\" style=\"width:75%;float:left;\"  id=\"START_DATE\" class=\"form-control BigInput\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})\"/>"+
       " </td>"+
		"</tr>"+
		"<tr>"+
			"<td nowrap>结束时间：</td>"+
			"<td nowrap align=\"left\">"+
			 	"<input type=\"text\"  name=\"endDate\" style=\"width:75%;float:left;\"  id=\"END_DATE\" class=\"form-control BigInput\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})\"/>"+
        "</td>"+
		"</tr>"+
		
		"<tr>"+
			"<td nowrap>事务类型：</td>"+
			"<td nowrap align=\"left\">"+
				 "<select style=\"width:75%;float:left;\" class=\"form-control BigSelect\" name=\"calType\" id=\"calType\">"+
                   " <option value=\"1\">工作日程(领导可见)</option>"+
						"<option value=\"2\">个人日程(自己可见)</option>"+
						"<option value=\"3\">完全公开</option>"+
            " </select>"+
           "</td>"+
			"</tr>"+
		"<tr>"+
			"<td nowrap>优先级：</td>"+
			"<td nowrap align=\"left\">"+
		      "<select style=\"float:left;\" class=\"form-control BigSelect\" name=\"calLevel\" id=\"calLevel\">"+
                   " <option value=\"1\">紧急/重要</option>"+
						"<option value=\"2\">紧急/不重要</option>"+
						"<option value=\"3\">不紧急/重要</option>"+
						"<option value=\"4\">不紧急/不重要</option>"+
            " </select></div>"+
		      // "<span class=\"color_wrapper\" style=''>"+
          // "<a hideFocus=\"true\" style=\"margin-right:30px;\" id=\"cal_color\"  class='color' index=\"0\" onclick='setLevel();'></a>"+
          // "<span style=\"display: none;\" id=\"color_menu\" class=\"color_menu\" >"+
                // "<a style=\"margin-top: 0px;\" id=\"calcolor\" class=\"color\" href=\"javascript:;\" index=\"0\"></a>"+
                // "<a id=\"calcolor1\" class=\"color1\"  href=\"javascript:;\" index=\"1\"></a>"+
                // "<a id=\"calcolor2\" class=\"color2\" href=\"javascript:;\" index=\"2\"></a>"+
                // "<a id=\"calcolor3\" class=\"color3\" href=\"javascript:;\" index=\"3\"></a>"+
                // "<a id=\"calcolor4\" class=\"color4\" href=\"javascript:;\" index=\"4\"></a>"+
                // "<a id=\"calcolor5\" class=\"color5\" href=\"javascript:;\" index=\"5\"></a>"+
                // "<a id=\"calcolor6\" class=\"color6\" href=\"javascript:;\" index=\"6\"> </a>"+
           // "</span>"+
           // "<input id=\"calLevel\" name=\"calLevel\" value=\"0\" type=\"hidden\">"+
       // "</span></div>"+
			"</td>"+
		"</tr>"+
		"<tr>"+
			"<td nowrap>是否重复：</td>"+
			"<td nowrap align=\"left\">"+
			   "<input name=\"calAffType\" id='calAffType' type=\"checkbox\" value=\"1\"  onclick='setCalAffType(this);'></label for='calAffType'></label>"+
			"</td>"+
	     "</tr>"+
	     
	     "<tr id=\"remindTypeTr\" style=\"display:none;\">"+
			"<td nowrap>提醒类型：</td>"+
			"<td nowrap align=\"left\">"+
          "<select style=\"width:25%;float:left;\" class=\"form-control BigSelect\" id=\"remindType\" name='remindType' onchange=\"sel_change(this.value)\">"+
              "<option value=\"2\">按天重复</option>"+
              "<option value=\"3\">按周重复</option>"+
              "<option value=\"4\">按月重复</option>"+
              "<option value=\"5\">按年重复</option>"+
          "</select>"+    
          
           "<span id='dayShow'>&nbsp;&nbsp;<input type=\"checkbox\" name=\"isWeekend\" id=\"isWeekend\"  value='1'></input>&nbsp;选中为排除周六、日 </span>"+
       "</td>"+
	     "</tr>"+
	     "<tr id=\"remindTimeTr\" style=\"display:none;\">"+
			"<td nowrap>提醒时间：</td>"+
			"<td nowrap align=\"left\">"+
			 	"<span id='day' >"+
			 		"</span>"+
			 	"<span id='week' style='display:none;'>"+
			 	remindDate3 + "&nbsp;&nbsp;"+
             "</span>"+
             "<span id='mon' style='display:none;'>"+
             	"<select style=\"width:25%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate4\" name=\"remindDate4\" >"+
             		dayStr + 
             	"</select>&nbsp;&nbsp;"+
             "</span>"+
             
             "<span id='years' style='display:none;'>"+
	                "<select style=\"width:25%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate5Mon\" name=\"remindDate5Mon\">"+
	                	monthStr + 
					"</select>"+
					"&nbsp;&nbsp;<select style=\"width:25%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate5Day\" name=\"remindDate5Day\">"+
						dayStr + 
            	"</select>&nbsp;&nbsp;"+
             "</span>"+
         	"<input id=\"remindTime\" value=\"00:00:00\" name=\"remindTime\" value='' type=\"text\" onclick=\"setRemindTime(this);\" style=\"width:25%;float:left;\" class=\"form-control BigInput easyui-validatebox\" validType=\"time[]\"   data-placement=\"right\" data-content=\"\" data-toggle=\"popover\" data-html=\"true\">"+
           	"&nbsp;为空为当前时间"+
			"</td>"+
	    "</tr>"+
		"<tr>"+
		"<td nowrap>参与者：</td>"+
		"<td nowrap align=\"left\">"+
			"<input id=\"accountId\" name=\"accountId\" type=\"hidden\">"+
			"<textarea  name=\"userName\" id=\"userName\" style=\"width:75%;float:left;\" class=\"form-control\" required=\"true\" rows=\"2\" cols=\"35\" readonly=\"readonly\"></textarea>"+
			"&nbsp;&nbsp;<a href=\"javascript:;\" class=\"orgAdd\" onClick=\"userinit(['accountId','userName']);\">选择</a> &nbsp;&nbsp;"+
		    
       "</td>"+
       
       "</tr>"+
		"<tr id=\"beforeRemindTr\">"+
		"<td nowrap>提前时间：</td>"+
		"<td nowrap align=\"left\">"+
			"提前<input  name=\"beforeDay\" id=\"beforeDay\" style=\"width:15%;float:left;\" class=\"form-control \" type=\"text\" value='' size='3' maxlength='5'> <span style=\"float:left\" >天</span>"+
				"<input  name=\"beforeHour\" id=\"beforeHour\" style=\"width:15%;float:left;\" class=\"form-control \" type=\"text\" value='' size='3' maxlength='5'> <span style=\"float:left\" >小时</span>"+
				"<input  name=\"beforeMinute\" id=\"beforeMinute\" style=\"width:15%;float:left;\" class=\"form-control \" type=\"text\" value='' size='3' maxlength='5'> <span style=\"float:left\" >分钟提醒</span>"+
		"</td>"+
		"</tr>"+
		"</tr>"+
		"<tr>"+
		"<td nowrap>提醒：</td>"+
		"<td nowrap align=\"left\">"+
			"<input id=\"smsRemind\" name=\"smsRemind\"  type=\"checkbox\" value='1'> <label for='smsRemind'>是否使用内部短信</label>"+
		"</td>"+
		"</tr>"+
		"<tr class=\"\">"+
			"<td colspan=\"2\" align=\"center\">" +
				"<div align='center'>"+
					"<input type=\"button\" value=\"保存\" class=\"btn btn-primary\" onclick=\"addOrUpdateCal();\">&nbsp;&nbsp;"+
					"<input type=\"button\" value=\"关闭\" class=\"btn\" onclick=\"$.jBox.close();\">&nbsp;&nbsp;"+
					"<input type=\"text\" id=\"sid\" name=\"sid\" style=\"display:none;\" value='0'/>"+
					"<input type=\"text\" id=\"oldMenuId\" name=\"oldMenuId\" style=\"display:none;\"/>"+
				"</div>"+
			"</td>"+
		"</tr>"+
	"</table>"+
	"</div>"+
	"</div>";
	addUpdateCalFroms = form;
};