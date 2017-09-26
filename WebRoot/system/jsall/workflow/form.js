//控件,列表控件[{tableName:"",writerField:"可写字段",allField:"所有字段"}]
var listControls = new Array();
var nextPrcs = null;
var ueditArr = [];
var iframeArr = [];
var form = null;
var attachId = "";
var attachName = "";
var xsql = [];
var attachIdsA = [];
var attachIdsB = [];
var publicFileId = "";
var publicFileName = "";
var nodedata;
var tmpselectflow = "";
var xupload = "";
// 提交下一步确认

// 数据校验
function formvalidator() {
	var mustField = form.mustField.split(",");
	var flag = true;
	for (var i = 0; mustField.length > i; i++) {
		var value = $("#" + mustField[i]).val();
		if (value == "") {
			var title = $("#" + mustField[i]).prop("title");
			alert(title + "不能为空！");
			flag = false;
		}
	}
	return flag;
}

// 表单序列化
function getData() {
	var formData = "";
	$("input").each(function() {
		if ($(this).prop("type") == "checkbox") {
			if ($(this).get(0).checked) {
				var name = $(this).prop("name");
				if (name != "") {
					formData += name + "=1&";
				}
			} else {
				var name = $(this).prop("name");
				if (name != "") {
					formData += name + "=0&";
				}
			}
		} else if ($(this).prop("type") == "radio") {
			if ($(this).prop("checked")) {
				var radio_name = $(this).prop("name");
				if (radio_name != "") {
					var value = $("input[name='" + radio_name + "']:checked").val();
					formData += radio_name + "="
							+ encodeURIComponent(value) + "&";
				}
			}
		} else if ($(this).prop("type") == "text") {
			var name = $(this).prop("name");
			if (name != "") {
				formData += name + "="
						+ encodeURIComponent($(this).val()) + "&";
			}
		} else if ($(this).prop("type") == "hidden") {
			var name = $(this).prop("name");
			if (name != "") {
				formData += name + "="
						+ encodeURIComponent($(this).val()) + "&";
			}
		}
	});
	$("select").each(function() {
		var name = $(this).prop("name");
		if (name != "") {
			formData += name + "=" + encodeURIComponent($(this).val()) + "&";
		}
	});
	$("textarea").each(function() {
		var name = $(this).prop("name");
		if (name != "") {
			formData += name + "=" + encodeURIComponent($(this).val()) + "&";
		}
	});
	formData = formData.length > 0 ? formData.substring(0, formData.length - 1)
			: formData;
	return formData;
}
// xsql处理
function createXsql() {
	var dbsourceid = "";
	var xsqlid = "";
	$("img[xtype='xsql']:not([disabled])").each(
			function() {
				xsqlid = $(this).prop("id");
				dbSourceId = $(this).prop("dbsourceid");
				var fieldname = $(this).prop("fieldname");
				var html = createXsqlHtml(fieldname);
				xsql.push("{\"fieldName\":\"" + xsqlid + "\",\"content\":\""
						+ encodeURIComponent(html) + "\"}");
				$("#" + xsqlid).before(html);
			});
	$("img[xtype='xsql']:not([disabled])").remove();
}

function getXsqlData(fieldname) {
	var sqlData;
	var url = contextPath
			+ "/tfd/system/workflow/flowutility/UitilitySource/getSqlQueryDataAct.act";
	$.ajax({
		url : url,
		dataType : "json",
		data : {
			flowId : flowId,
			prcsId : prcsId,
			runId : runId,
			tableName : tableName,
			dbSourceId : dbSourceId,
			fieldname : fieldname
		},
		async : false,
		error : function(e) {
		},
		success : function(data) {
			if (data.data != null) {
				sqlData = data;
			} else {
				for ( var name in form) {
					if (name == fieldname) {
						sqlData = form[name];
					}
				}
			}
		}
	});
	return sqlData;
}
// 生成XSQLhtml
function createXsqlHtml(fieldname) {
	var html = "";
	var data = getXsqlData(fieldname);
	if (data.data != null) {
		if (data.tagflag == "input") {
			var value = data.data;
			for ( var fname in value[0]) {
				html = "<input type=\"text\" id=\"" + fieldname + "\" name=\""
						+ fieldname + "\" value=\"" + value[0][fname] + "\"/>";
			}
		} else if (data.tagflag == "select") {
			var value = data.data;
			var tablefield = data.tablefield;
			html += tablefield[0] + ": ";
			html += "<select>";
			for (var i = 0; i < value.length; i++) {
				for ( var fname in value[i]) {
					html += "<option value=\"" + value[i][fname] + "\">"
							+ value[i][fname] + "</option>";
				}
			}
			html += "</select>";
		} else if (data.tagflag == "table") {
			var value = data.data;
			html += "<table  class=\"table table-bordered\">";
			var tablefield = data.tablefield;
			html += "<tr>";
			for (var k = 0; tablefield.length > k; k++) {
				html += "<td style=\"background-color: gray;\"><b>"
						+ tablefield[k] + "</b></td>";
			}
			html += "</tr>";
			for (var i = 0; i < value.length; i++) {
				html += "<tr>";
				for ( var fname in value[i]) {
					html += "<td>" + value[i][fname] + "</td>";
				}
				html += "</tr>";
			}
			html += "</table>";
		}
	} else {
		html = data;
	}

	return html;
}
// 获取富文本数据
function getueditData() {
	var content = [];
	for (var i = 0; ueditArr.length > i; i++) {
		content.push("{\"fieldName\":\"" + ueditArr[i] + "\",\"content\":\""
				+ encodeURIComponent(CKEDITOR.instances[ueditArr[i]].getData())
				+ "\"}");
	}
	return content;
}

function savaform() {
	updatePublicFile();
	var requestUrl = contextPath
			+ "/tfd/system/workflow/workflownext/act/WorkFlowNextAct/saveFormDataAct.act";
	var ideaText = $("#ideaText").val();
	var idea = $("#idea").val();
	if (idea == null) {
		idea = "";
	}
	var listControlsData = $.json.encode(getListControlsData());
	$.ajax({
		url : requestUrl,
		type : "POST",
		dataType : "text",
		traditional : true,
		data : {
			'flowLeave' : $("#flowLeave").val(),
			'flowTitleOld' : $("#flowTitleOld").val(),
			'data' : getData(),
			'flowId' : flowId,
			'prcsId' : prcsId,
			'tableName' : tableName,
			'runId' : runId,
			'runPrcsId' : runPrcsId,
			'idea' : idea,
			'ideaText' : ideaText,
			'ueditValue' : getueditData(),
			'listControlsData' : listControlsData,
			'xsql' : xsql
		},
		async : false,
		error : function(e) {
			alert(e.message);
		},
		success : function(data) {
			if (data == "1") {
				alert("保存成功！");
			}
		}
	});
}
// 返回
function returnback(title) {
	window.parent.$('#tt').tabs('close', title);
}
// 下一步
function nextprcs() {
	if (!formvalidator()) {
		return;
	}
	updatePublicFile();
	var requestUrl = contextPath
			+ "/tfd/system/workflow/workflownext/act/WorkFlowNextAct/workFlowNextAct.act";
	var ideaText = $("#ideaText").val();
	var idea = $("#idea").val();
	if (idea == null) {
		alert("请选审批结果!");
		return;
	}
	var listControlsData = $.json.encode(getListControlsData());
	$.ajax({
		url : requestUrl,
		type : "POST",
		dataType : "json",
		traditional : true,
		data : {
			data : getData(),
			flowId : flowId,
			runPrcsId : runPrcsId,
			prcsId : prcsId,
			tableName : tableName,
			runId : runId,
			runPrcsId : runPrcsId,
			idea : idea,
			ideaText : ideaText,
			ueditValue : getueditData(),
			listControlsData : listControlsData,
			xsql : xsql
		},
		async : false,
		error : function(e) {
			alert(e.message);
		},
		success : function(data) {
			if (data.isEnd == "END") {
				alert("此流程结束！");
				location.href = contextPath
						+ "/system/workflow/worklist/index.jsp";
			} else {
				nodedata = data;
				f_open();
			}
		}
	});
}
// 表单初始化
function doint() {
	$("[data-toggle='popover']").popover();
	$("img[xtype='xsql']").each(
			function() {
				var xsqlid = $(this).prop("id");
				var html = "<input type=\"hidden\" id=\"" + xsqlid
						+ "\" maxlength=\"20\" name=\"" + xsqlid + "\">";
				$("#" + xsqlid).before(html);
			});
	var requestUrl = contextPath
			+ "/tfd/system/workflow/getflowdata/act/GetWorkFlowDataAct/getWorkFlowDataAct.act";
	$
			.ajax({
				url : requestUrl,
				dataType : "json",
				data : {
					runId : runId,
					prcsId : prcsId,
					flowId : flowId,
					runPrcsId : runPrcsId
				},
				async : false,
				error : function(e) {
					alert(e.message);
				},
				success : function(data) {
					form = data;
					$("#flowTitle").append(
							"NO." + form.flowRunId + "&nbsp;&nbsp;"
									+ form.flowTitle);
					$("#flowTitle").prop("title", form.flowTitle);
					var topprcsHtml = "<div style=\"float:left;padding-top: 2px;\"><select id=\"flowLeave\" name=\"flowLeave\" class=\"form-control\" style=\"padding-top: 5px;\">\n"
							+ "<option value=\"0\" selected>普通</option>\n"
							+ "<option value=\"1\">加急</option>\n"
							+ "<option value=\"2\">紧急</option>\n"
							+ "</select></div>"
							+ "<div style=\"float:left;padding-top:10px;\" class=\"title-prcs-name\" >当前第"
							+ runPrcsId
							+ "步：(<span style=\"display: inline;color:black;\" title=\""
							+ form.prcsName
							+ "\">"
							+ form.prcsName
							+ "</span>)</div>";
					$("#topPrcsName").append(topprcsHtml);
					if (form.publicFileId != null) {
						publicFileId = form.publicFileId;
					}
					for ( var name in form) {
						// var inputEle = $("*[name='" + name + "']");
						var inputEle = $("#" + name);
						var inputEleType = inputEle.prop("type");
						var xType = inputEle.attr("xtype");
						if(xType=="ximg")
							{
							var id=inputEle.attr("id");
							var inputhtml="<input id=\""+id+"\" name=\""+id+"\" type=\"hidden\">";
							$("#"+id).click(function(){
								imgUploadDiv(id);
							});
							$("#"+id).before(inputhtml);
							}
						if (xType == "xworkflow") {
							var value = "";
							if (form[name] != "") {
								value = form[name];
							}
							var inputHtml = "<input type=\"hidden\" id=\""
									+ name + "\" name=\"" + name
									+ "\" value=\"" + value + "\">";
							$("#" + name).before(inputHtml);
							var style = inputEle.prop("style");
							if (style) {
								getSelectFlowTitleList(form[name], name, false);
							} else {
								getSelectFlowTitleList(form[name], name, true);
							}
						}
						var disabledType = inputEle.prop("disabled");
						inputEleType = inputEleType == null ? "" : inputEleType;
						if (inputEleType == "checkbox") {
							if (disabledType == true) {
								if (form[name] == "0" || form[name] == ""
										|| form[name] == null) {
									inputEle.get(0).checked = false;
								}
								if (form[name] == "1") {
									inputEle.get(0).checked = true;
								}
							} else {
								if (form[name] == "0") {
									inputEle.get(0).checked = false;
								}
								if (form[name] == "1") {
									inputEle.get(0).checked = true;
								}
							}
						} else if (inputEleType == "radio") {
							$(
									"input[name='" + name + "'][value='"
											+ form[name] + "']").prop(
									"checked", true);
						} else if (inputEleType == "file") {

						} else {
							if (form[name] != "") {
								inputEle.val(form[name]);
							}
						}
					}

					if (form.flowFollow == "1") {
						$("#follow").css("color", "rgb(255, 125, 0)");
					}
				}
			});
	if (form.goBack == "0") {
		$("#goback").hide();
	} else {
		$("#goback").show();
	}
	if (runPrcsId == 1) {
		$("#addopuser").hide();
	}
	if (form.follow == "1") {
		$("#follow").show();
	} else {
		$("#follow").hide();
	}
	if (form.mustField != "" && form.mustField != undefined) {
		var mustField = form.mustField.split(",");
		for (var i = 0; mustField.length > i; i++) {
			$("#" + mustField[i]).after("<font style=\"color:red;\">*</font>");
		}
	}
	var pfprive = form["publicFilePriv"];
	doXmacrotag();
	getIdea();
	createXlist();
	creatextextuedit();
	createXsql();
	createXifream();
	filesUpLoad("workflow", "publicFile", "publicFile");
	createWFAttachDiv(publicFileId, "publicFile", pfprive);
	createSimpleAttach();
	createxcalculate();
}
// 获取会签意见
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
						} else {
							ideaStr = "正在进行";
						}
						var ideaText = "";
						if (idea[i].ideaText == null) {
							var ideaText = "<font color=\"#FF0000\"> 未填写审批意见!</font>";
						} else {
							ideaText = idea[i].ideaText;
						}

						html += "<ul class=\"tickets-list\"><li class=\"ticket-item\"><div class=\"row\">\n"
								+ "<div class=\"ticket-user col-lg-3 col-sm-12\">\n"
								+ "<img onclick=\"javascript:showPersonal('"+idea[i].accountId+"')\" width=\"32\" height=\"32\" src=\""+contextPath+"/attachment/userinfo/"+idea[i].headImg+"\" class=\"user-avatar\">\n"
								+ "<span class=\"user-name\">"
								+ idea[i].userName
								+ "</span>\n"
								+ "<span class=\"user-at\">at</span>\n"
								+ "<span class=\"user-company\">第:"
								+ idea[i].runPrcsId
								+ "步骤:"
								+ idea[i].prcsName
								+ "</span>\n"
								+ "</div>\n"
								+ "<div class=\"ticket-time  col-lg-4 col-sm-6 col-xs-12\">\n"
								+ "<div class=\"divider hidden-md hidden-sm hidden-xs\"></div>\n"
								+ "<i class=\"fa fa-clock-o\"></i>\n"
								+ "<span class=\"time\">创建时间："
								+ idea[i].createTime
								+ "  结束时间："
								+ idea[i].endTime
								+ "</span>\n"
								+ "</div>\n"
								+ "<div class=\"ticket-type  col-lg-5 col-sm-6 col-xs-12\" id=\"div"
								+ idea[i].runPrcsId
								+ "\">\n"
								+ "<span class=\"divider hidden-xs\"></span>\n";
						if (ideaText.length > 30) {
							html += "<span class=\"type\" id=\"aideaspan"
									+ idea[i].runPrcsId
									+ "\" style=\"display:none;word-break:break-all;word-wrap:break-word;\">"
									+ ideaText
									+ "<a style=\"cursor: pointer;float:right;\" onclick=\"retract('"
									+ idea[i].runPrcsId
									+ "');\">收起</a></span>\n";
							html += "<span class=\"type\" id=\"bideaspan"
									+ idea[i].runPrcsId
									+ "\">"
									+ ideaText.substring(0, 30)
									+ "...<a  style=\"cursor: pointer;float:right;\" onclick=\"spread('"
									+ idea[i].runPrcsId
									+ "');\">展开</a></span>\n";
						} else {
							html += "<span class=\"type\" id=\"bideaspan"
									+ idea[i].runPrcsId + "\">" + ideaText
									+ "</span>\n";
						}
						"</div>\n";
						if (idea[i].idea == "0") {
							html += "<div class=\"ticket-state bg-darkorange\"><i class=\"fa fa-times\">\n"
									+ "</i>\n";
						} else if (idea[i].idea == "1") {
							html += "<div class=\"ticket-state bg-palegreen\"><i class=\"fa fa-check\">\n"
									+ "</i>\n";
						} else if (idea[i].idea == "2") {
							html += "<div class=\"ticket-state bg-yellow\"><i class=\"fa fa-info\">\n"
									+ "</i>\n";
						}
						html += "</div>\n" + "</div></li></ul>\n";
					}
				}
			});
	html += "</div>";
	$("#allIdea").html(html);
}

function spread(id) {
	$("#aideaspan" + id).show();
	$("#bideaspan" + id).hide();

	$("#div" + id).css({
		"height" : "auto"
	});
}

function retract(id) {
	$("#aideaspan" + id).hide();
	$("#bideaspan" + id).show();
}

// xlis生成
function createXlist() {
	$.ajax({
		url : contextPath+"/tfd/system/workflow/getflowdata/act/GetWorkFlowDataAct/getChildTableDataAct.act",
		data : {
			runId : runId,
			flowId : flowId,
			prcsId : prcsId
		},
		type:"POST",
		dataType : "json",
		async : false,
		success : function(data) {
			setXlistControls(data);
			initXlistControlCalculate();
		}
	});
}

//合计
function goSum(table){
	for (var i = 0; i < listControls.length; i++) {
		if (listControls[i].tableName==table) {
			var model=listControls[i].controlSetting;
			for (var j = 0; j < model.length; j++) {
				if (model[j].sum=="1") {
					var fieldNamePrefix=table+"_"+model[j].fieldname+"_";
					var sumResult=0;
					$("table[table='"+table+"'] *[name^='"+fieldNamePrefix+"']").each(function(rowIndex){
						
						if ($("*[name='"+table+"_op_"+rowIndex+"']").val()=="2") {
							return true;
						}
						var value=$.trim($(this).val());
						if (value=="") {
							return true;
						}
						if(new RegExp("[0-9]","g").test(value)){
							sumResult+=new Number(value);
						}else{
							var result=new RMBUtil().toDigital(value);
							sumResult+=result==null?0:result;
						}
					});
					sumResult=new Number(sumResult.toFixed(3));
					var sumResultRMB=RMB(sumResult);
					$(".xlist-sum-result[table='"+table+"'][fieldname='"+model[j].fieldname+"']").html(sumResultRMB);
				}
			}
		}
	}
}
function initXlistControlCalculate(){
	
	/**
	 * {
	 * 	table:{
	 * 		formulaField:{}
	 * 	}
	 * }
	 */
	var formulaMap=new Object();
	/**
	 * {
	 * 	table:{
	 * 		fieldName:{
	 * 			formulaField:{}
	 * 		}
	 * 	}
	 * }
	 */
	var calculateField=new Object();
	
	
	function findFieldByTitle(title,controlSetting){
		for (var j = 0; j < controlSetting.length; j++) {
			if (controlSetting[j].title==title) {
				return controlSetting[j];
			}
		}
	}
	
	function calculate(table,formulaField,rowIndex){
		var formula=formulaMap[table][formulaField];
		var startRegex=0;
		var convertFormula=formula;
		for (var k = 0; k < formula.length; k++) {
			if (formula[k]=="[") {
				startIndex=k;
			}else if(formula[k]=="]"){
				var fieldName=formula.substring(startIndex+1,k);
				var fieldNamePrefix=table+"_"+fieldName+"_";
				
				var fieldValue=$("*[name='"+fieldNamePrefix+rowIndex+"']").val();
				if(!new RegexUtil().REGEX_DOUBLE_0_.test(fieldValue)){
					return null;
				}
				
				convertFormula=convertFormula.replace(new RegExp("\\["+fieldName+"\\]","g"),fieldValue);
			}
		}
		return eval("("+convertFormula+")");
	}
	function initCalculateFieldKeyup($this){
		var name=$this.attr("name");
		var table=name.substring(0,name.indexOf("_")) 
		var fieldName=name.substring(name.indexOf("_")+1,name.lastIndexOf("_"));
		var rowIndex=name.substring(name.lastIndexOf("_")+1);
		
		for(var formulaField in calculateField[table][fieldName]){
			try{
				var value=calculate(table, formulaField,rowIndex);
				$("*[name='"+table+"_"+formulaField+"_"+rowIndex+"']").val(value==null?"":value);
			}catch(e){
				console.log(e.message);
			}
		}
		goSum(table);
	}
	
	
	
	for (var i = 0; i < listControls.length; i++) {
		var listControl=listControls[i];
		var controlSetting=listControl.controlSetting;
		var table=listControl.tableName;
		
		calculateField[table]=new Object();
		formulaMap[table]=new Object();
		
		for (var j = 0; j < controlSetting.length; j++) {
			var rowSetting=controlSetting[j];
			var formula=rowSetting.formula;
			
			if (formula!=null&&formula!="") {
				var convertFormula=formula;

				var startIndex=0;
				for (var k = 0; k < formula.length; k++) {
					if (formula[k]=="[") {
						startIndex=k
					}else if(formula[k]=="]"){
						var title=formula.substring(startIndex+1,k);
						var fieldName=findFieldByTitle(title,controlSetting).fieldname;
						var fieldNamePrefix=table+"_"+fieldName+"_";
						
						convertFormula=convertFormula.replace(new RegExp("\\["+title+"\\]"),"["+fieldName+"]");
						
						if (!(fieldName in calculateField[table])) {
							calculateField[table][fieldName]=new Object();
						}
						if (!(rowSetting.fieldname in calculateField[table][fieldName])) {
							calculateField[table][fieldName][rowSetting.fieldname]=new Object();
						}
						
					}
				}
				
				formulaMap[table][rowSetting.fieldname]=convertFormula;
			}
		}
	}
	
	
	for(var table in calculateField){
		for(var fieldName in calculateField[table]){
			var fieldNamePrefix=table+"_"+fieldName+"_";
			$("table[table='"+table+"']").on("keyup","*[name^='"+fieldNamePrefix+"']",function(){
				initCalculateFieldKeyup($(this));
			});
		}
	}
	
}

function deleteRow(table, index) {
	var trId = table + "_tr_" + index;
	$("#" + trId).hide();
	var tagName = table + "_op_" + index;
	$("*[name='" + tagName + "']").val("2");
	goSum(table);
}
// 增加列表行
function addRow(table, print, read) {
	var model=null;
	for (var i = 0; i < listControls.length; i++) {
		if (listControls[i].tableName==table) {
			model=listControls[i].controlSetting;
		}
	}
	
	var controlBodyId = table + "_tbody";
	var dataRowCount = $("#" + controlBodyId + " tr").length;
	var rowHtml = "<tr id=\"" + table + "_tr_" + dataRowCount + "\">";
	rowHtml += "<td style=\"display:none;\"><input name=\"" + table + "_tag_"
			+ dataRowCount + "\" value=\"1\"><input name=\"" + table + "_op_"
			+ dataRowCount + "\" value=\"1\"></td>";
	rowHtml += "<td>" + (dataRowCount + 1) + "</td>";
	var printFields = print.split(",");
	for (var i = 0; i < printFields.length; i++) {
		var type = "1";
		var selectValue = "";
		for (var j = 0; model.length > j; j++) {
			if (model[j].fieldname == printFields[i]) {
				type = model[j].type;
				selectValue = model[j].model;
			}
		}
		var inputName = table + "_" + printFields[i] + "_" + dataRowCount;
		var temp = "";
		if (type == "1") {
			temp += "<td>";
			temp += "<input name=\"" + inputName + "\"/>";
			temp += "</td>";
		} else if (type == "2") {
			temp += "<td>";
			temp += "<textarea name=\"" + inputName
					+ "\" rows=\"\" cols=\"\"></textarea>";
			temp += "</td>";
		} else if (type == "3") {
			var value = [];
			if (selectValue != "") {
				value = selectValue.split(",");
			}
			temp += "<td>";
			temp += "<select name=\"" + inputName + "\">";
			for (var k = 0; value.length > k; k++) {
				temp += "<option  value=\"" + value[k] + "\"";
				temp += ">" + value[k] + "</option>";
			}
			temp += "</select>";
			temp += "</td>";
		} else if (type == "4") {
			var value = [];
			if (selectValue != "") {
				value = selectValue.split(",");
			}
			temp += "<td>";
			for (var k = 0; value.length > k; k++) {
				temp += "<input type=\"radio\" name=\"" + inputName
						+ "\" value=\"" + value[k] + "\"";
				temp += "/>" + value[k];
			}
			temp += "</td>";
		} else if (type == "5") {
			var value = [];
			if (selectValue != "") {
				value = selectValue.split(",");
			}
			temp += "<td>";
			for (var k = 0; value.length > k; k++) {
				temp += "<input type=\"checkbox\" name=\"" + inputName
						+ "\" value=\"" + value[k] + "\"";
				temp += "/>" + value[k];
			}
			temp += "</td>";
		} else if (type = "6") {
			var dateFmt = "yyyy-MM-dd";
			if (selectValue != "") {
				dateFmt = selectValue;
			}
			temp += "<td>";
			temp += "<input readonly style=\"cursor: pointer;\" name=\""
					+ inputName + "\" onfocus=\"WdatePicker({dateFmt:'"
					+ dateFmt + "'})\" class=\"Wdate BigInput\"/>";
			temp += "</td>";
		}
		rowHtml += temp;
	}
	rowHtml += "<td><input type=\"button\" class=\"btn btn-danger btn-xs\" value=\"删除\" onclick=\"deleteRow('"
			+ table + "','" + dataRowCount + "');\"/></td>";
	rowHtml += "</tr>";
	$("#" + controlBodyId).append(rowHtml);
}
// 生成明细表所有控件
function setXlistControls(controlsSetting) {
	for (var i = 0; i < controlsSetting.length; i++) {
		var controlSetting = controlsSetting[i];
		var table = controlSetting.table;
		var model = eval('(' + $("#" + table).attr("model") + ')');
		
		var read = controlSetting.read;
		var title = controlSetting.title;
		
		//列名
		var controlHtml="<table class='table table-striped' border='1' table='"+table+"'>";
		controlHtml += "<thead>";
		controlHtml += "<tr>";
		controlHtml += "	<td style='display:none;' nowrap>tag</td>" ;
		controlHtml += "	<td align='center' style='width:40px;background: silver;' nowrap>序号</td>";
		var titleFields = title.split(",");
		for (var i = 0; i < titleFields.length; i++) {
			var styleWidth = "";
			for (var j = 0; model.length > j; j++) {
				if (model[j].title == titleFields[i]) {
					styleWidth = model[j].styleWidth;
				}
			}
			if (styleWidth!="") {
				styleWidth+="px";
			}
			
			
			controlHtml += "<td align='center' style='" + (styleWidth==""?"":"width:"+styleWidth)+ "background: silver;' nowrap>" + titleFields[i] + "</td>";
		}
		controlHtml += "	<td align='center' style='width:40px;background: silver;' nowrap>操作</td>";
		controlHtml += "</tr>";
		controlHtml += "</thead>";
		
		
		var controlBody = setXlistControl(table, read, controlSetting.print, controlSetting.data, model);
		controlHtml += controlBody;
		
		//合计
		controlHtml+="<tbody class='xlist-sum-tbody'>";
		controlHtml+="<tr>";
		controlHtml+="<td></td>";
		//[{'title':'名称','fieldname':'name','width':'10','stylewidth':'','sum':'0','formula':'','type':'1','model':''}]
		var c=0;
		for (var k = 0; k < model.length; k++) {
			if (model[k].sum=="1") {
				if (c>0) {
					controlHtml+="<td colspan='"+c+"'></td>";
					c=0;
				}
				controlHtml+="<td>合计：<span class='xlist-sum-result' table='"+table+"' fieldname='"+model[k].fieldname+"'></span></td>";
			}else{
				c++;
			}
		}
		controlHtml+="<td></td>";
		controlHtml+="</tr>";
		controlHtml+="</tbody>";
		
		var controlFoot = setXlistControlFoot(table, controlSetting.print, read);
		controlHtml +=controlFoot;
		
		controlHtml += "</table>";
		
		
		
		
		var listControl = {
			tableName : table,
			writerField : controlSetting.print,
			allField : controlSetting.print,
			controlSetting:model
		};
		listControls.push(listControl);
		
		$("#" + table).before(controlHtml).remove();
	}
}
// 列表主体
function setXlistControl(table, read, print, data, model) {
	var controlBody = "<tbody id='" + table + "_tbody'>";
	for (var i = 0; i < data.length; i++) {
		var dataObj = data[i];
		var temp = "<tr id='" + table + "_tr_" + i + "'>";
		temp += "<td style='display:none;'><input name='" + table + "_tag_"
				+ i + "' value='" + dataObj["tag"] + "'><input name='"
				+ table + "_op_" + i + "' value='0'></td>";
		temp += "<td nowrap>" + (i + 1) + "</td>";
		for ( var fieldName in dataObj) {
			var inputName = table + "_" + fieldName + "_" + i;
			var type = "1";
			var selectValue = "";
			for (var j = 0; model.length > j; j++) {
				if (model[j].fieldname == fieldName) {
					type = model[j].type;
					selectValue = model[j].model;
				}
			}
			if (type == "1") {
				if (fieldIsPrint(fieldName, print)) {
					temp += "<td nowrap>";
					if (fieldIsRead(fieldName, read)) {
						temp += "<input disabled name='" + inputName
								+ "' value='" + dataObj[fieldName] + "'/>";
					} else {
						temp += "<input name='" + inputName + "' value='"
								+ dataObj[fieldName] + "'/>";
					}
					temp += "</td>";
				}
			} else if (type == "2") {
				if (fieldIsPrint(fieldName, print)) {
					temp += "<td nowrap>";
					if (fieldIsRead(fieldName, read)) {
						temp += "<textarea disabled name='" + inputName
								+ "' rows='' cols=''>"
								+ dataObj[fieldName] + "</textarea>";
					} else {
						temp += "<textarea name='" + inputName
								+ "' rows='' cols=''>"
								+ dataObj[fieldName] + "</textarea>";
					}
					temp += "</td>";
				}
			} else if (type == "3") {
				var value = [];
				if (selectValue != "") {
					value = selectValue.split(",");
				}
				if (fieldIsPrint(fieldName, print)) {
					temp += "<td nowrap>";
					if (fieldIsRead(fieldName, read)) {
						temp += "<select disabled name='" + inputName + "'>";
						for (var k = 0; value.length > k; k++) {
							temp += "<option  value='" + value[k] + "'";
							if (value[k] == dataObj[fieldName]) {
								temp += " selected";
							}
							temp += ">" + value[k] + "</option>";
						}
						temp += "</select>";
					} else {
						temp += "<select name='" + inputName + "'>";
						for (var k = 0; value.length > k; k++) {
							temp += "<option  value='" + value[k] + "'";
							if (value[k] == dataObj[fieldName]) {
								temp += " selected";
							}
							temp += ">" + value[k] + "</option>";
						}
						temp += "</select>";
					}
					temp += "</td>";
				}
			} else if (type == "4") {
				var value = [];
				if (selectValue != "") {
					value = selectValue.split(",");
				}
				if (fieldIsPrint(fieldName, print)) {
					temp += "<td nowrap>";
					if (fieldIsRead(fieldName, read)) {
						for (var k = 0; value.length > k; k++) {
							temp += "<input type='radio' name='" + inputName
									+ "' value='" + value[k] + "'";
							if (value[k] == dataObj[fieldName]) {
								temp += "  checked='checked' ";
							}
							temp += " disabled/>" + value[k];
						}
					} else {
						for (var k = 0; value.length > k; k++) {
							temp += "<input type='radio' name='" + inputName
									+ "' value='" + value[k] + "'";
							if (value[k] == dataObj[fieldName]) {
								temp += "  checked='checked' ";
							}
							temp += "/>" + value[k];
						}
					}
					temp += "</td>";
				}
			} else if (type == "5") {
				var value = [];
				if (selectValue != "") {
					value = selectValue.split(",");
				}
				if (fieldIsPrint(fieldName, print)) {
					temp += "<td nowrap>";
					if (fieldIsRead(fieldName, read)) {
						for (var k = 0; value.length > k; k++) {
							temp += "<input type='checkbox' name='"
									+ inputName + "' value='" + value[k]
									+ "'";
							if (value[k] == dataObj[fieldName]) {
								temp += "  checked='checked' ";
							}
							temp += " disabled/>" + value[k];
						}
					} else {
						for (var k = 0; value.length > k; k++) {
							var fieldvaluestr = dataObj[fieldName].split(",");
							temp += "<input type='checkbox' name='"
									+ inputName + "' value='" + value[k]
									+ "'";
							for (var s = 0; fieldvaluestr.length > s; s++) {
								if (fieldvaluestr[s] == value[k]) {
									temp += "  checked='checked' ";
								}
							}
							temp += "/>" + value[k];
						}
					}
					temp += "</td>";
				}
			} else if (type = "6") {
				var dateFmt = "yyyy-MM-dd";
				if (selectValue != "") {
					dateFmt = selectValue;
				}
				if (fieldIsPrint(fieldName, print)) {
					temp += "<td nowrap>";
					if (fieldIsRead(fieldName, read)) {
						temp += "<input disabled name='" + inputName
								+ "' value='" + dataObj[fieldName] + "'/>";
					} else {
						temp += "<input readonly style='cursor: pointer;' name='"
								+ inputName
								+ "' value='"
								+ dataObj[fieldName]
								+ "' onfocus=\"WdatePicker({dateFmt:'"
								+ dateFmt + "'})\" class='Wdate'/>";
					}
					temp += "</td>";
				}
			}
		}
		temp += "<td style='width:40px;background: silver;' nowrap><input type='button' value='删除' class='btn btn-danger btn-xs' " +
				"onclick=\"deleteRow('"
				+ table + "','" + i + "');\"/></td>";
		temp += "</tr>";
		controlBody += temp;
	}
	controlBody += "</tbody>";
	return controlBody;
}
// 列表尾部
function setXlistControlFoot(table, print, read) {
	var controlFoot = "<tfoot></tr>";
	controlFoot += "<td align=\"right\" colspan=\""
			+ print.split(",").length
			+ 3
			+ "\"><input type=\"button\" class=\"btn btn-primary btn-xs\" value=\"添加\" onclick=\"addRow('"
			+ table + "','" + print + "','" + read + "');\"/></td>";
	controlFoot += "</tr></tfoot>";
	return controlFoot;
}
// 判断字段是否显示
function fieldIsPrint(field, print) {
	return ("," + print + ",").indexOf("," + field + ",") > -1;
}
// 判断字段是否只读
function fieldIsRead(field, read) {
	return ("," + read + ",").indexOf("," + field + ",") > -1;
}
/**
 * 2014-5-22 fzd 获取列表控件数据
 * 
 * @param tableName
 *            表名，代表列表控件
 * @returns json数据格式
 *          {\"table\":\"childtable\",\"writerfield\":\"A,B\",\"data\":[{\"tag\":\"1\",\"A\":\"1111\",\"B\":\"BBBD\"},{\"tag\":\"3\",\"A\":\"sssQWEQ\",\"B\":\"2222\"}]}
 */
function getListControlData(tableName) {
	var controlDataObj = null;
	for (var i = 0; i < listControls.length; i++) {
		var listControl = listControls[i];
		if (tableName == listControl.tableName) {
			controlDataObj = {};
			var writerField = listControl.writerField;
			var allField = listControl.allField;
			controlDataObj["table"] = tableName;
			controlDataObj["writerfield"] = allField;
			break;
		}
	}
	if (controlDataObj) {
		var data = [];
		var table = controlDataObj.table;
		var writerfield = controlDataObj.writerfield;
		var allFields = writerfield.split(",");
		var rowCount = $("#" + table + "_tbody tr").length;
		for (var i = 0; i < rowCount; i++) {
			var dataObj = null;
			for (var j = 0; j < allFields.length; j++) {
				if ($("*[name='" + table + "_" + allFields[j] + "_" + i + "']").length > 0) {
					dataObj = {};
				}
			}
			if (dataObj) {
				for (var j = 0; j < allFields.length; j++) {
					if ($(
							"*[name='" + table + "_" + allFields[j] + "_" + i
									+ "']").prop("type") == "radio") {
						dataObj[allFields[j]] = $(
								"input[name='" + table + "_" + allFields[j]
										+ "_" + i + "']:checked").val();
					} else if ($(
							"*[name='" + table + "_" + allFields[j] + "_" + i
									+ "']").prop("type") == "checkbox") {
						var checkvalue = "";
						$(
								"input[name='" + table + "_" + allFields[j]
										+ "_" + i + "']:checked").each(
								function() {
									if ($(this).prop("checked")) {
										checkvalue += $(this).val() + ",";
									}
								});
						if (checkvalue.length > 0) {
							checkvalue = checkvalue.substring(0,
									checkvalue.length - 1);
						}
						dataObj[allFields[j]] = checkvalue;
					} else {
						dataObj[allFields[j]] = $(
								"*[name='" + table + "_" + allFields[j] + "_"
										+ i + "']").val();
					}
				}
				dataObj["tag"] = $("*[name='" + table + "_tag_" + i + "']")
						.val();
				dataObj["op"] = $("*[name='" + table + "_op_" + i + "']").val();
				data.push(dataObj);
			}
		}
		controlDataObj["data"] = data;
	}
	return controlDataObj;
}
// 富文本处理

function creatextextuedit() {
	$("img[xtype='xtextuedit']:not([disabled])").each(
			function() {
				var ueditid = $(this).prop("id");
				ueditArr.push(ueditid);
				var html = "<textarea id=\"" + ueditid + "\" name=\"" + ueditid
						+ "\"></textarea>";
				$("#" + ueditid).before(html);
				CKEDITOR.replace(ueditid, {
					toolbar : [
							[ 'Source', 'NewPage', 'Preview' ],
							[ 'Cut', 'Copy', 'Paste', 'PasteText',
									'PasteFromWord', '-', 'Scayt' ],
							[ 'Undo', 'Redo', '-', 'Find', 'Replace', '-',
									'SelectAll', 'RemoveFormat' ],
							[ 'Image', 'Flash', 'Table', 'HorizontalRule',
									'Smiley', 'SpecialChar', 'PageBreak' ],
							'/',
							[ 'Styles', 'Format' ],
							[ 'Bold', 'Italic', 'Strike' ],
							[ 'NumberedList', 'BulletedList', '-', 'Outdent',
									'Indent', 'Blockquote' ] ]
				});

				CKEDITOR.document.getById(ueditid).setHtml(form[ueditid]);
			});
	$("img[xtype='xtextuedit']:not([disabled])").remove();

	$("img[xtype='xtextuedit'][disabled='disabled']").each(function() {
		var ueditid = $(this).prop("id");
		var html = "<div>" + form[ueditid] + "</div>";
		$("#" + ueditid).before(html);
		$("#" + ueditid).remove();
	});
}
function createXifream() {
	$("img[xtype='xiframe']:not([disabled])").each(
			function() {
				var iframeid = $(this).prop("id");
				var url = $(this).prop("module");
				iframeArr.push(iframeid);
				var html = "<iframe id=\"" + iframeid + "\" src=\"" + url
						+ "\" style=\"width:100%;height:100%;\"></iframe>";
				$("#" + iframeid).before(html);
			});
	$("img[xtype='xiframe']:not([disabled])").remove();

}

/**
 * 2014-5-22 fzd 获取所有列表控件数据
 * 
 * @returns json数据格式
 *          [{\"table\":\"childtable\",\"writerfield\":\"A,B\",\"data\":[{\"tag\":\"1\",\"A\":\"1111\",\"B\":\"BBBD\"},{\"tag\":\"3\",\"A\":\"sssQWEQ\",\"B\":\"2222\"}]}]
 */
function getListControlsData() {
	var controlsData = [];
	for (var i = 0; i < listControls.length; i++) {
		controlsData.push(getListControlData(listControls[i].tableName));
	}
	return controlsData;
}
// 提交下一步确认
function f_open() {
	var url = contextPath + "/system/workflow/dowork/nextprcs/index.jsp?runId="
			+ runId + "&flowId=" + flowId + "&prcsId=" + prcsId + "&prcsName="
			+ encodeURIComponent(form.prcsName) + "&runPrcsId=" + runPrcsId
			+ "&tableName=" + tableName + "&runPrcsId=" + runPrcsId
			+ "&nodedata=" + encodeURIComponent(JSON.stringify(nodedata));
	window.location.href = url;
}
// 在线打开附件
function openFile(attachId, extName, privFlag) {
	if (extName == "txt") {
		window.open(contextPath + "/system/controldoc/text.jsp?attachId="
				+ attachId + "&privFlag=" + privFlag);
	} else if (extName == "jpg" || extName == "gif" || extName == "png"
			|| extName == "bmp" || extName == "tif") {
		window.open(contextPath + "/system/controldoc/img.jsp?attachId="
				+ attachId + "&privFlag=" + privFlag);
	} else if (extName == "pdf") {
		window.open(contextPath + "/system/controldoc/pdf.jsp?attachId="
				+ attachId + "&privFlag=" + privFlag);
	} else if (extName == "doc" || extName == "docx") {
		window.open(contextPath + "/system/controldoc/word.jsp?attachId="
				+ attachId + "&privFlag=" + privFlag);
	} else if (extName == "xls" || extName == "xlsx") {
		window.open(contextPath + "/system/controldoc/excel.jsp?attachId="
				+ attachId + "&privFlag=" + privFlag);
	} else if (extName == "ppt" || extName == "pptx") {
		window.open(contextPath + "/system/controldoc/ppt.jsp?attachId="
				+ attachId + "&privFlag=" + privFlag);
	}

}

// 单附件上传
function createSimpleAttach() {
	var module = "workflow";
	var xuploadA = "";
	$("input[xtype='xupload']:not([disabled])")
			.each(
					function() {
						xuploadA = $(this).prop("id");
						html = "<div id=\""
								+ xuploadA
								+ "Div\" name=\""
								+ xuploadA
								+ "Div\"></div>"
								+ "<a class=\"addfile\"  href=\"javascript:void(0)\">单附件上传<input onchange=\"doUpload('"
								+ module
								+ "','"
								+ xuploadA
								+ "');\"  type=\"file\" id=\"file"
								+ xuploadA
								+ "\" name=\"file"
								+ xuploadA
								+ "\" hidefocus=\"true\" size=\"1\" id=\"fileattach\" name=\"fileattach\" class=\"addfile\"/></a>"
								+ "<input type=\"hidden\" id=\"" + xuploadA
								+ "\" name=\"" + xuploadA + "\" value=\"\" />";
						$("#" + xuploadA).before(html);
						$(this).remove();
						$("#" + xuploadA).val(form[xuploadA]);
						attachIdsA.push(xuploadA);
					});
	$("input[xtype='xuploads']:not([disabled])")
			.each(
					function() {
						xuploadA = $(this).prop("id");
						var html = "<div id=\""
								+ xuploadA
								+ "Div\" name=\""
								+ xuploadA
								+ "Div\"></div>"
								+ "<div style=\"display: none;\"  id=\""
								+ xuploadA
								+ "Progress\"></div>\n"
								+ "<div id=\"divStatus\"></div>\n"
								+ "<a class=\"add_swfupload\" href=\"javascript:void(0)\">多附件上传<span id=\""
								+ xuploadA
								+ "\"></span></a>\n"
								+ "<div style=\"display: none;\"><a id=\"btnCancel\" onclick=\"swfu.cancelQueue();\" disabled=\"disabled\">取消上传</a></div>\n"
								+ "<input type=\"hidden\" id=\"" + xuploadA
								+ "\" name=\"" + xuploadA
								+ "\" value=\"\" />\n";
						var fieldname = $(this).prop("fieldname");
						$("#" + xuploadA).before(html);
						$(this).remove();
						filesUpLoad("workflow", xuploadA, xuploadA);
						$("#" + xuploadA).val(form[xuploadA]);
						attachIdsA.push(xuploadA);
					});
	var xuploadB = "";
	$("input[xtype='xupload']").each(
			function() {
				xuploadB = $(this).prop("id");
				attachIdsB.push(xuploadB);
				$("#" + xuploadB).before(
						"<div style=\"width:250px;\"  id=\"" + xuploadB
								+ "Div\" name=\"" + xuploadB + "Div\"></div>");
				$(this).remove();
			});
	$("input[xtype='xuploads']").each(
			function() {
				xuploadB = $(this).prop("id");
				attachIdsB.push(xuploadB);
				$("#" + xuploadB).before(
						"<div style=\"width:250px;\"  id=\"" + xuploadB
								+ "Div\" name=\"" + xuploadB + "Div\"></div>");
				$(this).remove();
			});
	createAttachIdsDiv(form);
	// 形成附件阅读DIV
}
function doUpload(module, xupload) {
	$
			.ajaxFileUpload({
				url : contextPath
						+ "/com/system/filetool/UpFileTool/doFileUpload.act?module="
						+ module + "&divId=" + xupload, // 上传文件的服务端
				secureuri : false, // 是否启用安全提交
				dataType : 'json', // 数据类型
				fileElementId : 'file' + xupload, // 表示文件域ID
				success : function(html, status) {
					var attachId = html[0].attachmentId;
					var attachName = html[0].attachmentName;
					var extName = attachName.substring(attachName
							.lastIndexOf(".") + 1);
					var attachIds = $("#" + xupload).val();
					if (attachIds != "") {
						$("#" + xupload).val(attachIds + "," + attachId);
					} else {
						$("#" + xupload).val(attachId);
					}
					var htmlStr = "<div id=\""
							+ attachId
							+ "\" style='width:100%;height:25px;line-height:25px;'>\n"
							+ "<img style=\"float:left;width:20px;height:20px;\" src=\""
							+ imgPath + "/filetype/file_extension_" + extName
							+ ".png\" />\n";
					htmlStr += "<span style=\"float:left;\" title=\""
							+ attachName
							+ "\" onmouseout=\"javascript:hideMenus()\" onmouseover=\"showMenu('"
							+ attachId + "','" + extName + "','2')\" >"
							+ attachName + "</span>\n";
					htmlStr += "</div>";
					$("#" + xupload + "Div").append(htmlStr);
				},
				// 提交失败处理函数
				error : function(html, status, e) {
					alert("上传文件失败!");
				}
			});
}
// 根据attachIds生成附件DIV
function createAttachIdsDiv(form) {
	var fieldName = "";
	var attachIds = "";
	var html = "";
	for (var i = 0; attachIdsA.length > i; i++) {
		fieldName = attachIdsA[i];
		attachIds = form[fieldName];
		if (attachIds.substring(attachIds.length - 1, attachIds.length) == ",") {
			attachIds = attachIds.substring(0, attachIds.length - 1);
		}
		html = getAttachIdDiv(attachIds);
		$("#" + fieldName + "Div").append(html);
	}
	for (var i = 0; attachIdsB.length > i; i++) {
		fieldName = attachIdsB[i];
		attachIds = form[fieldName];
		if (attachIds.substring(attachIds.length - 1, attachIds.length) == ",") {
			attachIds = attachIds.substring(0, attachIds.length - 1);
		}
		html = getAttachIdDiv(attachIds, fieldName);
		$("#" + fieldName + "Div").append(html);
	}
}

function getAttachIdDiv(attachIds, fieldName) {
	if(attachIds==""||attachIds==null||attachIds=="undefined"||attachIds==undefined)
    {
        return;
    }
	var html = "";
	var url = contextPath
			+ '/com/system/filetool/UpFileTool/getAttachNameByIdAct.act';
	$.ajax({
				url : url,
				type : "POST",
				dataType : "json",
				data : {
					attachIds : attachIds
				},
				async : false,
				error : function(e) {
					alert("失败!");
				},
				success : function(data) {
					for (var i = 0; data.length > i; i++) {
						var attach = data[i];
						var attachId = attach.attachId;
						var attachName = attach.attachName.substr(18,
								attach.attachName.length - 18);
						var extName = attachName.substring(attachName
								.indexOf(".") + 1, attachName.lenght);
						html += "<div id=\""
								+ attachId
								+ "\" style='width:100%;height:25px;line-height:25px;'>\n"
								+ "<img style=\"float:left;width:20px;height:20px;\" src=\""
								+ imgPath + "/filetype/file_extension_"
								+ extName + ".png\" />\n";
						html += "<span style=\"float:left;\" title=\""
								+ attachName
								+ "\" onmouseout=\"javascript:hideMenus()\" onmouseover=\"showMenu('"
								+ attachId + "','" + extName + "','2')\" >"
								+ attachName + "</span>\n";
						html += "</div>";
					}
				}
			});
	return html;
}

// 计算控件处理
function createxcalculate() {
	var value = "";
	var id = "";
	var module = "";
	$("input[xtype='xcalculate']:not([disabled])").each(function() {
		id = $(this).attr("id");
		module = $(this).attr("module");
		if (module.indexOf("RMB") > -1) {
			var optstr = module.substring(4, module.length - 1);
			optstr = optstr.replace(/\(/g, "");
			optstr = optstr.replace(/\)/g, "");
			var ss = getparams(optstr);
			for (var l = 0; ss.length > l; l++) {
				$("input[title='" + ss[l] + "']").change(function() {
					createxcalculate();
				});
				var optvalue = $("input[title='" + ss[l] + "']").val();
				if (optvalue != undefined) {
					if (optvalue != "") {
						module = module.replace(ss[l], optvalue);
					} else {
						module = module.replace(ss[l], "0");
					}
				}
				module = module.replace("RMB", "RMB");
			}
			value = eval(module);
			$("#" + id).val(value);
		} else if (module.indexOf("ABS") > -1) {
			var optstr = module.substring(4, module.length - 1);
			optstr = optstr.replace(/\(/g, "");
			optstr = optstr.replace(/\)/g, "");
			var ss = getparams(optstr);
			for (var l = 0; ss.length > l; l++) {
				$("input[title='" + ss[l] + "']").change(function() {
					createxcalculate();
				});
				var optvalue = $("input[title='" + ss[l] + "']").val();
				if (optvalue != undefined) {
					if (optvalue != "") {
						module = module.replace(ss[l], optvalue);
					} else {
						module = module.replace(ss[l], "0");
					}
				}
			}
			module = module.replace("ABS", "Math.abs");
			value = eval(module);
			$("#" + id).val(value);
		} else if (module.indexOf("AVG") > -1) {
			var optstr = module.substring(4, module.length - 1);
			optstr = optstr.replace(/\(/g, "");
			optstr = optstr.replace(/\)/g, "");
			var tmpv = 0;
			if (optstr.indexOf(",") > -1) {
				var ss = optstr.split(",")
				for (var l = 0; ss.length > l; l++) {
					$("input[title='" + ss[l] + "'").change(function() {
						createxcalculate();
					});
					var optvalue = $("input[title='" + ss[l] + "'").val();
					if (optvalue != undefined) {
						var v = parseInt(optvalue);
						if (!isNaN(v)) {
							tmpv += v;
						} else {
							tmpv += 0;
						}
					}
				}
				value = tmpv / ss.length;
			} else {
				value = optstr;
			}
			$("#" + id).val(value);
		} else {
			var optstr = module;
			optstr = optstr.replace(/\(/g, "");
			optstr = optstr.replace(/\)/g, "");
			var ss = getparams(optstr);
			for (var l = 0; ss.length > l; l++) {
				$("input[title='" + ss[l] + "']").change(function() {
					createxcalculate();
				});
				var optvalue = $("input[title='" + ss[l] + "']").val();
				if (optvalue != undefined) {
					if (optvalue != "") {
						module = module.replace(ss[l], optvalue);
					} else {
						module = module.replace(ss[l], "0");
					}
				}
			}
			var value = eval(module);
			$("#" + id).val(value);
		}
	});

}

function getparams(optstr) {
	var tempa = optstr.split("+");
	var tempb = [];
	for (var i = 0; tempa.length > i; i++) {
		var temps = tempa[i].split("*");
		for (var k = 0; temps.length > k; k++) {
			tempb.push(temps[k]);
		}
	}
	tempa = [];
	for (var i = 0; tempb.length > i; i++) {
		var temps = tempb[i].split("-");
		for (var k = 0; temps.length > k; k++) {
			tempa.push(temps[k]);
		}
	}
	tempb = [];
	for (var i = 0; tempa.length > i; i++) {
		var temps = tempa[i].split("/");
		for (var k = 0; temps.length > k; k++) {
			tempb.push(temps[k]);
		}
	}
	return tempb;
}

function RMB(num) { // 转成人民币大写金额形式
	var str1 = '零壹贰叁肆伍陆柒捌玖'; // 0-9所对应的汉字
	var str2 = '万仟佰拾亿仟佰拾万仟佰拾元角分'; // 数字位所对应的汉字
	var str3; // 从原num值中取出的值
	var str4; // 数字的字符串形式
	var str5 = ''; // 人民币大写金额形式
	var i; // 循环变量
	var j; // num的值乘以100的字符串长度
	var ch1; // 数字的汉语读法
	var ch2; // 数字位的汉字读法
	var nzero = 0; // 用来计算连续的零值是几个
	num = Math.abs(num).toFixed(2); // 将num取绝对值并四舍五入取2位小数
	str4 = (num * 100).toFixed(0).toString(); // 将num乘100并转换成字符串形式
	j = str4.length; // 找出最高位
	if (j > 15) {
		return '溢出';
	}
	str2 = str2.substr(15 - j); // 取出对应位数的str2的值。如：200.55,j为5所以str2=佰拾元角分
	// 循环取出每一位需要转换的值
	for (i = 0; i < j; i++) {
		str3 = str4.substr(i, 1); // 取出需转换的某一位的值
		if (i != (j - 3) && i != (j - 7) && i != (j - 11) && i != (j - 15)) { // 当所取位数不为元、万、亿、万亿上的数字时
			if (str3 == '0') {
				ch1 = '';
				ch2 = '';
				nzero = nzero + 1;
			} else {
				if (str3 != '0' && nzero != 0) {
					ch1 = '零' + str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				} else {
					ch1 = str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				}
			}
		} else { // 该位是万亿，亿，万，元位等关键位
			if (str3 != '0' && nzero != 0) {
				ch1 = "零" + str1.substr(str3 * 1, 1);
				ch2 = str2.substr(i, 1);
				nzero = 0;
			} else {
				if (str3 != '0' && nzero == 0) {
					ch1 = str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				} else {
					if (str3 == '0' && nzero >= 3) {
						ch1 = '';
						ch2 = '';
						nzero = nzero + 1;
					} else {
						if (j >= 11) {
							ch1 = '';
							nzero = nzero + 1;
						} else {
							ch1 = '';
							ch2 = str2.substr(i, 1);
							nzero = nzero + 1;
						}
					}
				}
			}
		}
		if (i == (j - 11) || i == (j - 3)) { // 如果该位是亿位或元位，则必须写上
			ch2 = str2.substr(i, 1);
		}
		str5 = str5 + ch1 + ch2;

		if (i == j - 1 && str3 == '0') { // 最后一位（分）为0时，加上“整”
			str5 = str5 + '整';
		}
	}
	if (num == 0) {
		str5 = '零元整';
	}
	return str5;
}

// 宏标记处理
function doXmacrotag() {
	$("span[xtype='macrotag']").each(function() {
		var value = $(this).prop("value");
		if (value == "MACRO_FORM_NAME") {
			$(this).html(form.flowFormName);
		} else if (value == "MACRO_RUN_NAME") {
			$(this).html(form.flowTitle);
		} else if (value == "MACRO_NUMBERING") {
			$(this).html("流程开始编号！");
		} else if (value == "MACRO_BEGIN_TIME") {
			$(this).html(form.flowBeginTime);
		} else if (value == "MACRO_END_TIME") {
			$(this).html(form.flowEndTime);
		} else if (value == "MACRO_RUN_ID") {
			$(this).html(form.flowRunId);
		} else if (value == "MACRO_RUN_GUID") {
			$(this).html(form.flowGuId);
		} else if (value == "MACRO_BEGIN_USERNAME") {
			$(this).html(form.flowBeginUser);
		} else if (value == "MACRO_BEGIN_ACCOUNT_ID") {
			$(this).html(form.flowBeginAccountId);
		}
	});
}
// 关注
function follow() {
	if (form.flowFollow == null || form.flowFollow == "0") {
		form.flowFollow = "1";
		$("#follow").css("color", "rgb(255, 125, 0)");
	} else {
		form.flowFollow = "0";
		$("#follow").css("color", "rgb(0, 0, 125)");
	}
	var url = contextPath
			+ "/tfd/system/workflow/flowrunprcs/act/FlowRunPrcsAct/setFollowAct.act";
	$.ajax({
		url : url,
		dataType : "json",
		data : {
			flowId : flowId,
			prcsId : prcsId,
			runId : runId,
			runPrcsId : runPrcsId,
			flag : form.flowFollow
		},
		async : false,
		error : function(e) {
		},
		success : function(data) {
			if (form.flowFollow == "1") {
				alert("添加关注成功！");
			} else {
				alert("取消关注成功！");
			}
		}
	});
}
// 加签
function addopuser() {
	var em = $("#addopuser");
	var content = "<div style=\"width:250px;\">"
			+ "<div><div style=\"float: left;\"><input id=\"addOpUser\" name=\"addOpUser\" type=\"hidden\">"
			+ "<input id=\"addOpUserName\" name=\"addOpUserName\" type=\"text\" class=\"form-control\" readonly style=\"width: 120px;\"></div>"
			+ "<a style=\"margin-left: 10px;line-height: 30px;cursor:pointer;\"  onclick=\"userinit(['addOpUser','addOpUserName']);\">选择</a></div></br>"
			+ "<input type=\"radio\" name=\"addopuserType\" id=\"addopuserType\" value=\"1\" checked/>前加签"
			+ "<input type=\"radio\" name=\"addopuserType\" id=\"addopuserType\" value=\"2\"/>后加签"
			+ "</div>"
			+ "<div align=\"right\"><button class=\"btn btn-primary\" onclick=\"addOpUserSubmit();\">确认</button></div>";
	em.attr("data-content", content);
}
function addOpUserSubmit() {
	var addopuserType = $("input[name='addopuserType']:checked").val();
	$("#addopuser").popover('hide');
	var addOpUser = $("#addOpUser").val();
	var url = contextPath
			+ "/tfd/system/workflow/flowrunprcs/act/FlowRunPrcsAct/addOpUserAct.act";
	$.ajax({
		url : url,
		dataType : "text",
		data : {
			flowId : flowId,
			prcsId : prcsId,
			runId : runId,
			runPrcsId : runPrcsId,
			addOpUser : addOpUser,
			addopuserType : addopuserType
		},
		async : false,
		error : function(e) {
		},
		success : function(data) {
			if (data == "1") {
				alert("加签成功！");
			}
		}
	});
}
// 查看流程图
function flowView() {
	window.open(contextPath
			+ "/system/workflow/flowgraph/historygraph/index.jsp?runId="
			+ runId);
}

// 获取已选择流程的实例
function getSelectFlowTitleList(runIds, name, flag) {
	var paramData = {
		runIds : runIds
	};
	var requestUrl = contextPath
			+ "/tfd/system/workflow/flowrun/act/FlowRunAct/getFlowRunByIdsAct.act";
	$.ajax({
				url : requestUrl,
				dataType : "json",
				data : paramData,
				async : false,
				error : function(e) {
					alert(e.message);
				},
				success : function(data) {
					var html = "";
					for (var i = 0; data.length > i; i++) {
						var closeStyle = "padding:2px 5px;color:red;cursor:pointer;";
						html += "<div id=\"" + data[i].runId + "\">";
						html += " <a href=\"#\" onclick=\"readWorkFlow('"
								+ data[i].path + "')\">" + data[i].title
								+ "</a>";
						if (flag) {
							html += " &nbsp;<span title='删除' style='"
									+ closeStyle
									+ "' href='javascript:void(0);' onclick=\"delWorkFlow('"
									+ data[i].runId + "','" + name
									+ "');\">x</span>";
						}
						html += "</div>";
					}
					$("#" + name).before(html);
				}
			});
}
function delWorkFlow(Id, name) {
	$("#" + Id).remove();
	var tmp = $("#" + name).val() + ",";
	tmp = tmp.replace(Id + ",", "");
	tmp = tmp.substring(0, tmp.length - 1);
	$("#" + name).val(tmp);
}
function readWorkFlow(path) {
	window.open(contextPath + path);
}
function selectFlowRun(runId) {
	var tmp = $("#" + tmpselectflow).val();
	if (tmp == "") {
		$("#" + tmpselectflow).val(runId);
	} else {
		tmp = tmp + "," + runId;
		$("#" + tmpselectflow).val(tmp);
	}
	$('#modal' + tmpselectflow).modal('hide');
	getSelectFlowTitleList(runId, tmpselectflow, true);
}
function getSelectFlowList(model) {
	$("#selectFlowList")
			.datagrid(
					{
						width : 590,
						height : 'auto',
						rows : 5,
						collapsible : true,
						url : contextPath
								+ "/tfd/system/workflow/getflowdata/act/GetWorkFlowDataAct/getSelectWorkFLowAct.act",
						method : 'POST',
						queryParams : {
							model : model,
							q_runId : $("#q_runId").val(),
							q_title : $("#q_title").val()
						},
						sortName : 'ID',
						sortOrder : 'DESC',
						loadMsg : "数据加载中...",
						pagination : true,
						striped : true,
						singleSelect : true,
						remoteSort : true,
						columns : [ [ {
							title : '序号',
							field : 'ID',
							width : 50,
							align : 'center',
							sortable : true
						},
						// {title: '流水号', field: 'RUN_ID', width: 300, align:
						// 'center',sortable:true},
						{
							title : '流程标题',
							field : 'TITLE',
							width : 300,
							align : 'center',
							sortable : true
						},
						// {title: '发起人', field: 'ACCOUNT_ID', width: 100,
						// align: 'center',sortable:true},
						// {title: '发起时间', field: 'BEGIN_TIME', width: 80,
						// align: 'center',sortable:true},
						// {title: '参与人', field: 'OP_USER_STR', width: 80,
						// align: 'center',sortable:false,
						// formatter:function(value,rowData,rowIndex){
						// if(rowData.FORM_CREATE_USER!=null)
						// {
						// return getUserName(rowData.FORM_CREATE_USER);
						// }else
						// {
						// return "无结果!";
						// }
						// }
						// },
						{
							title : '操作',
							field : 'OPT',
							width : 200,
							align : 'center',
							sortable : false
						} ] ]
					});

	var p = $('#selectFlowList').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为5
		pageList : [ 10, 20, 30, 50 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
};

// 工作流选择
function selectworkflow(fieldName, model) {
	tmpselectflow = fieldName;
	var html = "<div class=\"modal fade\" id=\"modal"
			+ fieldName
			+ "\" tabindex=\"-1\" role=\"dialog\"  aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n"
			+ "<div class=\"modal-dialog\">\n"
			+ "<div class=\"modal-content\">\n"
			+ "<div class=\"modal-header\">\n"
			+ "<button type=\"button\" class=\"close\"  data-dismiss=\"modal\" aria-hidden=\"true\"> &times;</button>\n"
			+ "<h4 class=\"modal-title\" id=\"myModalLabel\">请选择流程</h4>\n"
			+ "</div>\n"
			+ "<div class=\"modal-body\" style=\"padding:0px;\">\n"
			+ "<div align=\"center\">\n"
			+ "流水号:<input id=\"q_runId\" name=\"q_runId\"> 标题:<input id=\"q_title\" name=\"q_title\">\n"
			+ "<button type=\"button\" class=\"btn btn-primary\" onclick=\"getSelectFlowList('"
			+ model
			+ "');\">查询</button>\n"
			+ "</div>\n"
			+ "<div id=\"selectFlowList\" name=\"selectFlowList\"></div>\n"
			+ "</div>\n"
			+ "<div class=\"modal-footer\" style=\"margin-top:0px;padding-top:0px\">\n"
			+ "<button type=\"button\" class=\"btn btn-default\"  data-dismiss=\"modal\">关闭</button>\n"
			+ "</div>\n" + "</div>\n" + "</div>\n";
	$("#modaldialog").html(html);
	$("#modal" + fieldName).modal({
		backdrop : 'static',
		keyboard : false
	});
	$("#modal" + fieldName).on('shown.bs.modal', function() {
		getSelectFlowList(model);
	});
}
// 回退
function doGoBack() {
	if (form.goBack == "1") {
		$
				.ajax({
					url : contextPath
							+ "/tfd/system/workflow/flowrunprcs/act/FlowRunPrcsAct/getAStepAct.act",
					dataType : "json",
					data : {
						runPrcsId : runPrcsId,
						runId : runId
					},
					async : false,
					error : function(e) {
					},
					success : function(data) {
						var em = $("#goback");
						var content = "<div style=\"width:250px;\">";
						if (runPrcsId == 1) {
							content += "无可回退步骤！";
						} else {
							content += "<input type=\"radio\"  id=\"goBackPrcsId\" name=\"goBackPrcsId\" value=\""
									+ data.Id
									+ "\" />"
									+ data.prcsName
									+ "["
									+ data.userName + "]</br>";
							content += "回退意见：<textarea class=\"form-control\" id=\"ideaTextGo\" name=\"ideaTextGo\"></textarea>";
							content += "<div align=\"right\"><button class=\"btn btn-primary\" onclick=\"goBackSetp();\">确认</button></div>";
						}
						content += "</div>";
						em.prop("data-content", content);
					}
				});
	} else if (form.goBack == "2") {
		$
				.ajax({
					url : contextPath
							+ "/tfd/system/workflow/flowrunprcs/act/FlowRunPrcsAct/getHistoryPrcsAct.act",
					dataType : "json",
					data : {
						runPrcsId : runPrcsId,
						prcsId : prcsId,
						runId : runId
					},
					async : false,
					error : function(e) {
					},
					success : function(data) {
						var em = $("#goback");
						var content = "<div style=\"width:250px;\">";
						if (runPrcsId == 1) {
							content += "无可回退步骤！";
						} else {
							for (var i = 0; data.length > i; i++) {
								content += "<input type=\"radio\"  id=\"goBackPrcsId\" name=\"goBackPrcsId\" value=\""
										+ data[i].Id
										+ "\" />"
										+ data[i].prcsName
										+ "["
										+ data[i].userName + "]</br>";
							}
							content += "回退意见：<textarea class=\"form-control\" id=\"ideaTextGo\" name=\"ideaTextGo\"></textarea>";
							content += "<div align=\"right\"><button class=\"btn btn-primary\" onclick=\"goBackSetp();\">确认</button></div>";
						}
						content += "</div>";
						em.prop("data-content", content);
					}
				});
	}
}
function goBackSetp() {
	var ideaText = $("#ideaTextGo").val();
	var Id = $("input[name='goBackPrcsId']:checked").val();
	if (Id == "" || Id == undefined) {
		alert("请选择回退步骤！");
	} else {
		if (ideaText == "") {
			alert("请填写回退意见！");
		} else {
			var url = contextPath
					+ "/tfd/system/workflow/flowrunprcs/act/FlowRunPrcsAct/goBackSeptAct.act";
			$.ajax({
				url : url,
				dataType : "text",
				data : {
					Id : Id,
					runPrcsId : runPrcsId,
					runId : runId,
					prcsId : prcsId,
					ideaText : ideaText
				},
				async : false,
				error : function(e) {
				},
				success : function(data) {
					if (data == "1") {
						new SysFrame().tabs('close',
								"<div  style=\"display:none;\" id=" + runId
										+ "></div >办理工作流");
						alert("回退成功！");
					}
				}
			});
		}
	}
}

function imgUploadDiv(id)
{
    $('#imguploaddiv').modal({backdrop: 'static', keyboard: false});
    $('#imguploaddiv').on('shown.bs.modal',function(){
    $("#imgbutton").unbind('click').click(function (){
    	var module="workflow";
		var fileId="imgfile";
		upmoduleimg(module,fileId,function(rv){
			var json=rv;
			if(json.msg=="1")
				{
				}else if(json.msg=="2")
				{
					var imghtml ="<img height=\"100\" width=\"100\" src=\""+json.webpath+"\">";
					$("#"+id).before(imghtml);
					$('#imguploaddiv').modal('hide')
				}
	});
		
 });
 });
}