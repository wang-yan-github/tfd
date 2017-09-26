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
		var requestUrl = contextPath + "/calendar/act/CalendarAct/getCalendarFromMeAct.act"+par;
		doSearch(requestUrl);
	}
	function doSearch(requestUrl){
		ajaxLoading();
		$('#myTable').datagrid({
			url:requestUrl,
			columns:[[
						{field:'START_DATE',title:'开始时间',width:'15%',align:'center',sortable:true,},
						{field:'END_DATE',title:'结束时间',width:'15%',align:'center',},
						{field:'USER_NAME',title:'人员',width:'15%',align:'center',
							formatter:function(value,rowData,rowIndex){
								return "<a onclick=\"javascript:showPersonal('"+rowData.ACCOUNT_ID+"')\" href=\"javascript:void(0)\" >"+rowData.USER_NAME+"</a>";
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
	function updateCal(cal_id){
	    $("#div-modal-dialog").width(600);
                    $("#modaliframe").width(595);
                    $("#modal-body").html("<div  style=\"width:595px; height:400px; overflow:auto; solid #000000;\">"+addUpdateCalFrom+"</div>");
                    $('#myModals').modal({backdrop: 'static', keyboard: false});
                    $('#myModals').modal('show');
	    
	    
	    
	    
		//$.jBox(addUpdateCalFrom,{title:"编辑",width:500,height:400,edibuttons: {}});
    	  //setRemindTimePopover();//
    	  var calendarObj = getCalById(cal_id);//获取日程
    	  checkCal();
    	  getMessagePriv("calendar");
          //$("#CAL_CONTENT").val($("#calendar-quick-detail h5").text());
          if(calendarObj){
          	$("#CAL_CONTENT").val(calendarObj.title);
          	  $("#Up_calId").val(calendarObj.id);
          	  $("#Up_accountId").val(calendarObj.accountId);
              $("#START_DATE").val(calendarObj.startTimeStr);
              $("#END_DATE").val(calendarObj.endTimeStr);
        	  $("#calLevel").val(calendarObj.calLevel);
        	  if(calendarObj.calLevel && calendarObj.calLevel != 0){
        		  $("#cal_color").attr("class","color" + calendarObj.calLevel);
        	  }
        	 // alert(calendarObj.isWeekend )
        	  if(calendarObj.isWeekend == 1){
        		  $("#isWeekend")[0].checked = true;
        	  }
        	  if(calendarObj.isWeekend == 1){
        		  $("#smsRemind")[0].checked = true;
        	  }
        	  $("#calType").val(calendarObj.calType);//工作类型
        	  $("#accountId").val(calendarObj.userId);//参与者
        	  var userName = calendarObj.userName;
		  	  var name = "";
		  	  $.each(userName,function(index,userName){
		  	  	name += userName.name+",";
		  	  });
		  	  $("#userName").val(name);
        	  $("#affairId").val(calendarObj.affairId);
        	  if(calendarObj.calAffType == '1'){//周期性事务
        		  $("#calAffType")[0].checked = true;
        		  $("#remindType").val(calendarObj.remindType);
        		  setCalAffType($("#calAffType")[0]);
        		  /*if(calendarObj.remindType == 3 || calendarObj.remindType == 4 || calendarObj.remindType == 5){//周、月、年
  					  $("#remindTime" + calendarObj.remindType).val(calendarObj.remindTimeStr);
  				  }else{*/
  				  //}
  				 	sel_change(calendarObj.remindType);
  				    $("#remindTime").val(calendarObj.remindTime);
  				    $("#freDay").val(calendarObj.frequency);
  				  if(calendarObj.remindType == 3){
  					  $("#remindDate3").val(calendarObj.remindDate);
  					  $("#freWeek").val(calendarObj.frequency);
  				  }else if(calendarObj.remindType == 4){
  					  $("#remindDate4" ).val(calendarObj.remindDate);
  					  $("#freMon").val(calendarObj.frequency);
  				  }else if(calendarObj.remindType == 5){
  					  var remindDateArray = calendarObj.remindDate.split("-");
  					  $("#remindDate5Mon").val(remindDateArray[0]);
  					  $("#remindDate5Day").val(remindDateArray[1]);
  					  $("#freYear").val(calendarObj.frequency);
  				  } 
  				  $("#endWhile").val(calendarObj.endWhile);//结束重复时间
        	  }
        	  if(calendarObj.beforeTime!=null){
        	  	 $("#remindmins").val(calendarObj.beforeTime);
        	  }	
        	  setSms(calendarObj.isSms);
          }
          $("#sid").val(cal_id);
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
						 searchCalendar();
					}else{
						top.layer.msg('删除失败');
					}
					}
			});
		}
	}
function addOrUpdateCal(){
	if (check()){
		var url = contextPath + "/calendar/act/CalendarAct/addOrUpdate.act";
		//var para =  tools.formToJson($("#form1")) ;
		var para =$("#form1").serialize();
		// 处理获取所有的周期性事务 有效
		//para['startTimeStr']  = $.fullCalendar.formatDate( view.start,'yyyy-MM-dd HH:mm');
		//para['endTimeStr'] = $.fullCalendar.formatDate( view.end,'yyyy-MM-dd HH:mm');
		para = para +"&smsReminds="+getsmsRemind();
		$.ajax({
			url:url,
			dataType:"json",
			data:para,
			async:false,
			success:function(data){
				$('#myModals').modal("hide");
				if(data){
					top.layer.msg('保存成功');
					searchCalendar();
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
		$("#FrequencyTr").show();
		$("#endTr").show();
		$("#beforeRemindTr").hide();
	}else{
		$("#remindTypeTr").hide();
		$("#remindTimeTr").hide();
		$("#FrequencyTr").hide();
		$("#endTr").hide();
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
	var remindDate3 = "<input style=\"width:35%;float:left;\" disabled=\"disabled\"  type=\"text\" id=\"remindDate3\" name=\"remindDate3\" class=\"form-control BigSelect\" onclick=\"javascript:choiceWeek();\" />";
	
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

	var nowDate = formatDate(new Date());
	var form = "<input   type=\"hidden\" name=\"calId\" id=\"Up_calId\" value=\"\" />"+
	"<input   type=\"hidden\" name=\"sid\" id=\"sid\" value=\"\" />"+
	"<input   type=\"hidden\" name=\"accountIds\" id=\"Up_accountId\" />"+
	"<input   type=\"hidden\" name=\"affairId\" id=\"affairId\" value=\"\" />"+
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
						//"<option value=\"2\">个人日程(自己可见)</option>"+
						"<option value=\"3\">完全公开</option>"+
            " </select></div>"+
           "</td>"+
			"</tr>"+
		"<tr>"+
			"<td nowrap>优先级：</td>"+
			"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
		      "<select style=\"float:left;\" class=\"form-control BigSelect\" name=\"calLevel\" id=\"calLevel\">"+
		      		" <option value=\"0\">不指定</option>"+
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
			   "<input name=\"calAffType\" id='calAffType' disabled=\"disabled\" type=\"checkbox\" value=\"1\"  onclick='setCalAffType(this);'></label for='calAffType'></label></div>"+
			"</td>"+
	     "</tr>"+
	      "<tr id=\"FrequencyTr\" style=\"display:none;\" >"+
		"<td nowrap>频率：</td>"+
			"<td nowrap align=\"left\" style=\"line-height:35px;\" ><div class=\"col-xs-8 form-group\"  >"+
			 	"<span id='forday' >"+
			 	"<input name=\"freDay\" disabled=\"disabled\" id=\"freDay\" value=\"1\" class=\"form-control\" style=\"width:50%;float:left;\" type=\"text\" />天提醒一次"+
			 	"</span>"+
			 	"<span id='forweek' style='display:none;'>"+
			 	"<input name=\"freWeek\" disabled=\"disabled\" id=\"freWeek\" value=\"1\" class=\"form-control\" style=\"width:50%;float:left;\" type=\"text\" />周提醒一次"+
             "</span>"+
             "<span id='formon' style='display:none;'>"+
				"<input name=\"freMon\" disabled=\"disabled\" id=\"freMon\" value=\"1\" class=\"form-control\" style=\"width:50%;float:left;\" type=\"text\" />月提醒一次"+
             "</span>"+
             "<span id='foryears' style='display:none;'>"+
	                "<input name=\"freYear\" disabled=\"disabled\" id=\"freYear\" value=\"1\" class=\"form-control\" style=\"width:50%;float:left;\" type=\"text\" />年提醒一次"+
             "</span>"+
			"</div></td>"+
		"</div></td>"+
		"</tr>"+
	     "<tr id=\"remindTypeTr\" style=\"display:none;\">"+
			"<td nowrap>提醒类型：</td>"+
			"<td nowrap align=\"left\" style=\"line-height:35px;\" ><div class=\"col-xs-8 form-group\"  >"+
          "<select style=\"width:50%;float:left;\" disabled=\"disabled\" class=\"form-control BigSelect\" id=\"remindType\" name='remindType' onchange=\"sel_change(this.value)\">"+
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
             	"<input  type=\"text\" onclick=\"choiceMonth();\" disabled=\"disabled\" style=\"width:35%;float:left;\" class=\"form-control BigSelect\" id=\"remindDate4\" name=\"remindDate4\" >"+
             		// dayStr + 
             	// "</select>&nbsp;&nbsp;"+
             "</span>"+
             
             "<span id='years' style='display:none;'>"+
	                "<select style=\"width:25%;float:left;\" disabled=\"disabled\" class=\"form-control BigSelect\" id=\"remindDate5Mon\" name=\"remindDate5Mon\">"+
	                	monthStr + 
					"</select>"+
					"<select style=\"width:25%;float:left;\" disabled=\"disabled\" class=\"form-control BigSelect\" id=\"remindDate5Day\" name=\"remindDate5Day\">"+
						dayStr + 
            	"</select>"+
             "</span>"+
         	"<input id=\"remindTime\" disabled=\"disabled\" name=\"remindTime\" value='0:00:00' type=\"text\" onclick=\"setRemindTime(this);\" style=\"width:25%;float:left;\" class=\"form-control BigInput easyui-validatebox\" validType=\"time[]\"   data-placement=\"right\" data-content=\"\" data-toggle=\"popover\" data-html=\"true\">"+
           	"&nbsp;为空为当前时间"+
			"</div></td>"+
	    "</tr>"+
	    "<tr id=\"endTr\" style=\"display:none;\" >"+
			"<td nowrap>结束重复：</td>"+
			"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
			 	"<input type=\"text\" readonly=\"readonly\" disabled=\"disabled\"  name=\"endWhile\" style=\"float:left;\" value="+nowDate+"  id=\"endWhile\" class=\"form-control BigInput\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd '})\"/></div>"+
        "</td>"+
		"</tr>"+
		"<tr>"+
		"<td nowrap>参与者：</td>"+
		"<td nowrap align=\"left\"><div class=\"col-xs-8 form-group\"  >"+
			"<input id=\"accountId\" name=\"accountId\" type=\"hidden\">"+
			"<textarea  name=\"userName\" id=\"userName\" style=\"float:left;\" onClick=\"userinit(['accountId','userName']);\" class=\"form-control easyui-validatebox BigTextarea SmallStatic\" rows=\"2\" cols=\"35\" readonly=\"readonly\"></textarea>"+
       "</div></td>"+
       "</tr>"+
		"<tr id=\"beforeRemindTr\">"+
		"<td nowrap>提前时间：</td>"+
		"<td nowrap align=\"left\" style=\"line-height:35px;\" >"+
			"<div class=\"col-xs-8 form-group\"  ><select style=\"float:left;\" class=\"form-control BigSelect\" id=\"remindmins\" name='remindmins' >"+
              "<option value=\"0\">事件发生时</option>"+
              "<option value=\"15\">15分钟前</option>"+
              "<option value=\"30\">30分钟前</option>"+
              "<option value=\"60\">1个小时前</option>"+
              "<option value=\"120\">2个小时前</option>"+
              "<option value=\"1440\">1天前</option>"+
              "<option value=\"10080\">1周前</option>"+
          "</select></div>"+
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
function formatDate(date) 
{ 
	var myyear = date.getFullYear(); 
	var mymonth = date.getMonth()+1; 
	var myweekday = date.getDate(); 
	if(mymonth < 10)
	{ 
		mymonth = "0" + mymonth; 
		} 
	if(myweekday < 10)
	{ 
		myweekday = "0" + myweekday; 
		} 
	return (myyear+"-"+mymonth + "-" + myweekday); 
}
function setSms(isSms){
	if(isSms.sitesms==1){
		$("#sitesms").prop("checked",true);
	}
	if(isSms.mobilesms==1){
		$("#mobilesms").prop("checked",true);
	}
	if(isSms.emailsms==1){
		$("#emailsms").prop("checked",true);
	}
	if(isSms.webemilesms==1){
		$("#webemilesms").prop("checked",true);
	}
	if(isSms.wxsms==1){
		$("#wxsms").prop("checked",true);
	}
}