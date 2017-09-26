var addUpdateCalFrom =  "";
var weekArray = ['','星期一' , '星期二' ,'星期三','星期四' , '星期五' ,'星期六' ,'星期日'];

	$(function(){
		searchCalendar();
		$("#btn_search").click(function(){
			searchCalendar();
		});
		$("#btn_back").click(function(){
			history.go(-1);
			return false;
		});
		getAddUpdateCalFrom();
	});
	function searchCalendar(){
		var par = "?searchContent="+$("#searchContent").val();
		var requestUrl = contextPath + "/calendar/act/CalendarAct/getCalendarForMeAct.act"+par;
		doSearch(requestUrl);
	}
	function doSearch(requestUrl){
		ajaxLoading();
		$('#myTable').datagrid({
			url:requestUrl,
			columns:[[
						{field:'START_DATE',title:'开始时间',width:'15%',align:'center',sortable:true,},
						{field:'END_DATE',title:'结束时间',width:'15%',align:'center',},
						{field:'USER_NAME',title:'安排人',width:'14%',align:'center',
							formatter:function(value,rowData,rowIndex){
								return "<a onclick=\"javascript:showPersonal('"+rowData.FROM_ID+"')\" href=\"javascript:void(0)\" >"+rowData.USER_NAME+"</a>";
							}
						},
						{field:'CONTENT',title:'事务内容',width:'30%',align:'center'},
						{field:'CAL_TYPE',title:'事务类型',width:'15%',align:'center',
							formatter:function(value,rowData,rowIndex){
								if (rowData.CAL_TYPE = '1') {
									return "工作日程";
								} else if(rowData.CAL_TYPE = '2'){
									return "个人日程";
								}else{
									return "公开日程";
								}
							}	
						},
						{field:'OPT',title:'操作',align:'center',width:'12%',}
					]],
			collapsible: true,
			method: 'POST',
		    sortName: 'START_DATE',
			pagination:true,
			striped: true,
		    singleSelect:true,  
		    remoteSort:true, 
		    onLoadSuccess:function(data){  
	            if(data.total == 0){
	   	  			$('#myTable').datagrid('appendRow',{START_DATE:'<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'START_DATE', colspan: 6 });
	   	  		 	$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
	        	}
	        	ajaxLoadEnd();
	        }
		});
		 var p = $('#myTable').datagrid('getPager');  
	        $(p).pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为10  
	        pageList: [5, 10, 15 ,20],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    }); 
	}
	/**
 * 获取日程byId
 * @param id
 * @returns
 */
function getCalById(id){
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
function goQuery(){
	window.location = contextPath + "/calendar/query/index.jsp";
}
function goImport(){
	window.location = contextPath + "/calendar/import/index.jsp";
}
function goPeriodicity(){
	window.location = contextPath + "/calendar/periodicity/index.jsp";
}
function goWeekManage(){
	window.location = contextPath + "/calendar/index.jsp";
} 
	function check() {
		var CAL_CONTENT = $("#CAL_CONTENT");
		if(!CAL_CONTENT.val()){
			alert("内容是必填项！");
			return false ;
		}
		var START_DATE = $("#START_DATE");
		var END_DATE = $("#END_DATE");
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
				END_DATE[0].select();
				return false;
			}
		}
		
		var calAffType = $("#calAffType")[0] ;
		if(calAffType.checked  ==  true){
			var remindTime = $("#remindTime")[0];
			// if(!isTimeTee(remindTime.value)){
				// alert("提醒时间格式不对,如(13:30:30)");
				// remindTime.focus();
				// remindTime.select();
				// return false;
			// }
		}else{
			var beforeDay = $("#beforeDay")[0];
			var beforeHour = $("#beforeHour")[0];
			var beforeMinute = $("#beforeMinute")[0];
			if(beforeDay.value != "" && !checkRate(beforeDay.value)){
				alert("请输入整数!");
				beforeDay.focus();
				beforeDay.select();
				return false;
			}
			
			if(beforeHour.value != "" && !checkRate(beforeHour.value)){
				alert("请输入整数!");
				beforeHour.focus();
				beforeHour.select();
				return false;
			}
			if(beforeMinute.value != "" && !checkRate(beforeMinute.value)){
				alert("请输入整数!");
				beforeMinute.focus();
				beforeMinute.select();
				return false;
			}
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
	function showCal(cal_id){
		
		 var url = contextPath + "/calendar/detail.jsp?id=" + cal_id+"&type=2";
    $("#modaliframe").attr("src",url);
    $("#myModalLabel").html("查看详情");
    $("#div-modal-dialog").width(455);
    $("#modaliframe").width(450);
    $("#modaliframe").height(300);
    $('#myModals').modal({backdrop: 'static', keyboard: false});
    $('#myModals').modal('show');
	}
	
function setCalAffType(obj){
	if(obj.checked == true){
		$("#remindTypeTr").show();
		$("#remindTimeTr").show();
		$("#beforeRemindTr").hide();
	}else{
		$("#remindTypeTr").hide();
		$("#remindTimeTr").hide();
		$("#beforeRemindTr").show();//提前多少时间提醒
	}
}
function getAddUpdateCalFrom(){
	// var remindDate3 = "<select style=\"width:25%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate3\" name=\"remindDate3\">";//周
	// for(var i= 1 ;  i <= 7 ;i++){
		// var selected = "";
		// // if(i == currWeek){
			// // selected = "selected";
		// // }
		// remindDate3 = remindDate3 + "<option value=\""+i+"\" " +selected +" >" + weekArray[i] +"</option>";
// 
	// }
	// remindDate3 = remindDate3 + "</select>";
	var remindDate3 = "<input style=\"width:35%;float:left;\"  type=\"text\" id=\"remindDate3\" name=\"remindDate3\" class=\"form-control BigSelect\" onclick=\"javascript:choiceWeek();\" />";
	
	var dayStr = "";//日
	for(var i= 1 ;  i < 32 ;i++){
		var selected = "";
		// if(i == currDay){
			// selected = "selected";
		// }
		dayStr = dayStr + "<option value=\""+ i +"\" " +selected +" >" + i +"日</option>";

	}

	var monthStr = "";//月
	for(var i= 1 ;  i < 13 ;i++){
		var selected = "";
		// if(i == currDay){
			// selected = "selected";
		// }
		monthStr = monthStr + "<option value=\""+ i +"\" " +selected +" >" + i +"月</option>";

	}

	
	var form =  "<input   type=\"hidden\" name=\"calId\" id=\"Up_calId\" value=\"\" />"+
	"<input   type=\"hidden\" name=\"sid\" id=\"sid\" value=\"\" />"+
	"<div class=\"panel panel-info\">"+
	"<div class=\"panel-body\" style=\"border:none;\" >"+
	 "<table class=\"table table-striped\" width=\"100%\" align=\"center\">"+
		"<tr>"+
			"<td nowrap>事务内容：</td>"+
			"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
				 "<textarea  name=\"content\" id=\"CAL_CONTENT\" style=\"float:left;\"  class=\"form-control\" required=\"true\" rows=\"3\" cols=\"30\"></textarea><font color=\"red\">(必填)</font></div>"+
         "</td>"+
		"</tr>"+
		
		"<tr>"+
			"<td nowrap>开始时间：</td>"+
			"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
			 	"<input type=\"text\" readonly=\"readonly\"  name=\"startDate\"  id=\"START_DATE\" class=\"form-control BigInput\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})\"/></div>"+
       " </td>"+
		"</tr>"+
		"<tr>"+
			"<td nowrap>结束时间：</td>"+
			"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
			 	"<input type=\"text\" readonly=\"readonly\"  name=\"endDate\" style=\"float:left;\"  id=\"END_DATE\" class=\"form-control BigInput\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})\"/></div>"+
        "</td>"+
		"</tr>"+
		
		"<tr>"+
			"<td nowrap>事务类型：</td>"+
			"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
				 "<select style=\"float:left;\" class=\"form-control BigSelect\" name=\"calType\" id=\"calType\">"+
                   " <option value=\"1\">工作日程(领导可见)</option>"+
						"<option value=\"2\">个人日程(自己可见)</option>"+
						"<option value=\"3\">完全公开</option>"+
            " </select></div>"+
           "</td>"+
			"</tr>"+
		"<tr>"+
			"<td nowrap>优先级：</td>"+
			"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
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
			"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
			   "<input name=\"calAffType\" id='calAffType' type=\"checkbox\" value=\"1\"  onclick='setCalAffType(this);'></label for='calAffType'></label></div>"+
			"</td>"+
	     "</tr>"+
	     
	     "<tr id=\"remindTypeTr\" style=\"display:none;\">"+
			"<td nowrap>提醒类型：</td>"+
			"<td nowrap align=\"left\" style=\"line-height:35px;\" ><div class=\"col-xs-8 form-group\"  >"+
          "<select style=\"width:50%;float:left;\" class=\"form-control BigSelect\" id=\"remindType\" name='remindType' onchange=\"sel_change(this.value)\">"+
              "<option value=\"2\">按天重复</option>"+
              "<option value=\"3\">按周重复</option>"+
              "<option value=\"4\">按月重复</option>"+
              "<option value=\"5\">按年重复</option>"+
          "</select>"+    
          
           "<span id='dayShow'>&nbsp;&nbsp;<input type=\"checkbox\" name=\"isWeekend\" id=\"isWeekend\"  value='1'></input>&nbsp;选中为排除周六、日 </span></div>"+
       "</td>"+
	     "</tr>"+
	     "<tr id=\"remindTimeTr\" style=\"display:none;\">"+
			"<td nowrap>提醒时间：</td>"+
			"<td nowrap align=\"left\" style=\"line-height:35px;\" ><div class=\"col-xs-12 form-group\"  >"+
			 	"<span id='day' >"+
			 		"</span>"+
			 	"<span id='week' style='display:none;'>"+
			 	remindDate3 +
             "</span>"+
             "<span id='mon' style='display:none;'>"+
             	"<input  type=\"text\" onclick=\"choiceMonth();\" style=\"width:35%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate4\" name=\"remindDate4\" >"+
             		// dayStr + 
             	// "</select>&nbsp;&nbsp;"+
             "</span>"+
             
             "<span id='years' style='display:none;'>"+
	                "<select style=\"width:25%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate5Mon\" name=\"remindDate5Mon\">"+
	                	monthStr + 
					"</select>"+
					"<select style=\"width:25%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate5Day\" name=\"remindDate5Day\">"+
						dayStr + 
            	"</select>"+
             "</span>"+
         	"<input id=\"remindTime\" name=\"remindTime\" value='0:00:00' type=\"text\" onclick=\"setRemindTime(this);\" style=\"width:25%;float:left;\" class=\"form-control BigInput easyui-validatebox\" validType=\"time[]\"   data-placement=\"right\" data-content=\"\" data-toggle=\"popover\" data-html=\"true\">"+
           	"&nbsp;为空为当前时间"+
			"</div></td>"+
	    "</tr>"+
		"<tr>"+
		"<td nowrap>参与者：</td>"+
		"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
			"<input id=\"accountId\" name=\"accountId\" type=\"hidden\">"+
			"<textarea  name=\"userName\" id=\"userName\" style=\"float:left;\" class=\"form-control easyui-validatebox BigTextarea SmallStatic\"  rows=\"2\" cols=\"35\" readonly=\"readonly\"></textarea>"+
			"&nbsp;&nbsp;<a href=\"javascript:;\" class=\"orgAdd\" onClick=\"selectUser(['accountId','userName']);\">选择</a> &nbsp;&nbsp;"+
		    
       "</div></td>"+
       
       "</tr>"+
		"<tr id=\"beforeRemindTr\">"+
		"<td nowrap>提前时间：</td>"+
		"<td nowrap align=\"left\" style=\"line-height:35px;\" >"+
			"提前<div class=\"col-xs-3 form-group\"  ><input  name=\"beforeDay\" id=\"beforeDay\" style=\"float:left;\" class=\"form-control \" type=\"text\" value='' size='3' maxlength='5'></div> <span style=\"float:left\" >天</span>"+
				"<div class=\"col-xs-3 form-group\"  ><input  name=\"beforeHour\" id=\"beforeHour\" style=\"float:left;\" class=\"form-control \" type=\"text\" value='' size='3' maxlength='5'></div> <span style=\"float:left\" >小时</span>"+
				"<div class=\"col-xs-3 form-group\"  ><input  name=\"beforeMinute\" id=\"beforeMinute\" style=\"float:left;\" class=\"form-control \" type=\"text\" value='' size='3' maxlength='5'></div> <span style=\"float:left\" >分钟提醒</span>"+
		"</td>"+
		"</tr>"+
		"</tr>"+
		"<tr>"+
		"<td nowrap>提醒：</td>"+
		"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
			//"<input id=\"smsRemind\" name=\"smsRemind\"  type=\"checkbox\" value='1'> <label for='smsRemind'>是否使用内部短信</label>"+
			"<div id=\"smsdiv\" name=\"smsdiv\"></div>"+
		"</div></td>"+
		"</tr>"+
	"</table>"+
	"</div>"+
	"</div>";
	addUpdateCalFrom = form;
};
function checkCal(){
	$('#form1').bootstrapValidator({
		message: '这不是一个有效的值',
		container: 'tooltip',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
		fields: {
			content: {
	            validators: {
	            	container: 'popover',
	                notEmpty: {
	                    message: '事务内容不能为空'
	                }
	            }
	       },
	        beforeDay: {
	            validators: {
	            	container: 'popover',
	                integer: {
	                    message: '只能为整数'
	                }
	            }
	        },
	        beforeHour: {
	            validators: {
	            	container: 'popover',
	                integer: {
	                    message: '只能为整数'
	                }
	            }
	        },
	        beforeMinute: {
	            validators: {
	            	container: 'popover',
	                integer: {
	                    message: '只能为整数'
	                }
	            }
	        }
		}
	}).on('success.form.bv',function(e){
		 e.preventDefault();
	     // Get the form instance
	     var $form = $(e.target);

	     // Get the BootstrapValidator instance
	     var bv = $form.data('bootstrapValidator');
	     addOrUpdateCal();
	});
}

function choiceWeek(){
	var week = "";//周
	 for(var i= 1 ;  i <= 7 ;i++){
	 	if($("#remindDate3").val().indexOf(weekArray[i])>-1){
	 		week = week + "<div class=\"checked checkeds\"  >" + weekArray[i] +"</div>";
	 	}else{
	 		week = week + "<div class=\"checked\"  >" + weekArray[i] +"</div>";	
	 	}
		 
	 }
	 $("#week-div-modal-dialog").width(180);
	 $("#week-div-modal-dialog").height(400);
	 $("#weekModalLabel").html("选择星期");
	 $("#week-modal-body").html(week);
	 $('#weekModal').modal({backdrop: 'static', keyboard: false});
	 $('#weekModal').modal('show');
	 docheck();
}
function choiceMonth(){
	var month = "";//周
	 for(var i= 1 ;  i <= 31 ;i++){
	 	if($("#remindDate4").val().indexOf(i+"日")>-1){
	 		month = month + "<div class=\"checked checkeds\"  >" + i +"日</div>";
	 	}else{
	 		month = month + "<div class=\"checked\"  >" + i +"日</div>";	
	 	}
		 
	 }
	 $("#week-div-modal-dialog").width(180);
	 //$("#week-div-modal-dialog").height(400);
	 $("#weekModalLabel").html("选择日期");
	 $("#week-modal-body").html(month);
	 $("#week-modal-body").height(210);
	 $("#week-modal-body").css("overflow","auto");
	 $('#weekModal').modal({backdrop: 'static', keyboard: false});
	 $('#weekModal').modal('show');
	 docheck();
}
function docheck(){
	$(".checked").click(function(){
		if($(this).hasClass("checkeds")){
			$(this).removeClass("checkeds");
		}else{
			$(this).addClass("checkeds");
		}
	});
}

function getCheck(){
	var returnData = "";
	var checks = $(".checkeds");
	for(var i = 0 ; i < checks.size(); i++){
		returnData += checks.eq(i).html()+",";
	}
	returnData = returnData.substr(0,returnData.length-1);
	if(returnData.indexOf("星期")>-1){
		$("#remindDate3").val(returnData);
	}else{
		$("#remindDate4").val(returnData);
	}
	
	$('#weekModal').modal('hide');
}
