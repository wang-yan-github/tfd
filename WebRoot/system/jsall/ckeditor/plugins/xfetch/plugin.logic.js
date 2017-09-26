var flowform_plugin_xfetch=new Object();
$(function(){
	var renderDatetime = (function() {
		var dom = "";
		dom += "<option value='yyyy-MM-dd'>yyyy-MM-dd</option>";
		dom += "<option value='yyyy-MM-dd HH:mm'>yyyy-MM-dd HH:mm</option>";
		dom += "<option value='yyyy-MM'>yyyy-MM</option>";
		dom += "<option value='yy-MM-dd'>yy-MM-dd</option>";
		dom += "<option value='yyyyMMdd'>yyyyMMdd</option>";
		dom += "<option value='MM-dd yyyy'>MM-dd yyyy</option>";
		dom += "<option value='yyyy年MM月'>yyyy年MM月</option>";
		dom += "<option value='yyyy年MM月dd日'>yyyy年MM月dd日</option>";
		dom += "<option value='yyyy年MM月dd日 HH:mm'>yyyy年MM月dd日 HH:mm</option>";
		dom += "<option value='MM月dd日'>MM月dd日</option>";
		dom += "<option value='yyyy.MM'>yyyy.MM</option>";
		dom += "<option value='yyyy.MM.dd'>yyyy.MM.dd</option>";
		dom += "<option value='MM.dd'>MM.dd</option>";
		return dom;
	})();
	var renderTime = (function () {
		var dom = "";
		dom += "<option value='HH时'>HH时</option>";
		dom += "<option value='HH时mm分'>HH时mm分</option>";
		dom += "<option value='HH时mm分ss秒'>HH时mm分ss秒</option>";
		dom += "<option value='HH:mm'>HH:mm</option>";
		dom += "<option value='HH:mm:ss'>HH:mm:ss</option>";
		return dom;
	})();
	
	function initFormValidation(){
		$("form").formValidation({
			//结合bootstrap
			framework:"bootstrap",
			//验证错误提示方式
			err:{
				container:"tooltip"
			},
			//验证结果图标
			icon: {
		        valid: 'glyphicon glyphicon-ok',
		        invalid: 'glyphicon glyphicon-remove',
		        validating: 'glyphicon glyphicon-refresh'
		    },
		    fields:{
		    	title:{
		    		validators:{
		    			callback:{
		    				callback:function(value,validator,$field){
		    					value=$.trim(value);
		    					$field.val(value);
		    					if (value=="") {
									return {valid:false,message:"请填写"};
								}else{
									if (oldTitle!=value&&(value in titlejson)) {
										return {valid:false,message:"标题已存在"};
									}
								}
		    					return true;
		    				}
		    			}
		    		}
		    	},
		    	fieldName:{
		    		validators:{
		    			callback:{
		    				callback:function(value,validator,$field){
		    					value=$.trim(value);
		    					$field.val(value);
		    					if (value=="") {
									return {valid:false,message:"请填写"};
								}else{
									if (oldFieldName!=value&&(value in fieldjson)) {
										return {valid:false,message:"名称已存在"};
									}else if(!new RegExp("^[a-z]*$").test(value)){
										return {valid:false,message:"字段名必须全部为小写字母"};
									}
								}
		    					return true;
		    				}
		    			}
		    		}
		    	}
		    }
		})
		.on("success.form.fv",function(e){
			e.preventDefault();//阻止自动提交
		});
	} 
	//校验，required
	flowform_plugin_xfetch.validate=function() {
		$("form").data("formValidation").destroy();
		initFormValidation();
		$("form").data("formValidation").validate();
		
		if ($("form").data("formValidation").isValid()) {
			var title=$("#title").val();
			var fieldName=$("#fieldName").val();
			
			if (oldTitle!=null) {
				delete titlejson[oldTitle];
				delete fieldjson[oldFieldName];
			}
			
			titlejson[title]=title;
			fieldjson[fieldName] = fieldName;
			
			return true;
		}
		return false;
	}

	// 转换dom节点，required
	flowform_plugin_xfetch.toDomStr=function() {
		var title = $("#title").val();
		var fieldName = $("#fieldName").val();
		var type = $("#type").val();
		var format = $("#format").val();
		var style = $("#style").val();
		var model=new Object();
		model.type=type;
		model.format=format;
		

		var typeName = "";
		if (type == "1" || type == "2") {// 时间
			typeName = "时间{选择控件}";
		} else if (type == "3" || type == "4") {// 部门
			typeName = "部门{选择控件}";
		} else if (type == "5" || type == "6") {// 角色
			typeName = "角色{选择控件}";
		} else if (type == "7" || type == "8") {// 人员
			typeName = "人员{选择控件}";
		}

		var html = "<input " +
				   "	type='text' " +
				   "	fieldName='" + fieldName+ "' " +
				   "	id='" + fieldName + "' "+
				   "	datatype='text'" +
				   " 	title='" + title+"'"+
				   "	xtype='xfetch' " +
				   "	value='" + typeName + "' " +
				   " 	style='" + style + "' " +
				   "	model='"+$.json.encode(model)+"'"+
				   "/>";

		return html;
	}
	
	
	
	
	var titlejson = top.fieldList.title;
	var fieldjson = top.fieldList.fieldName;
	var oldTitle=null;
	var oldFieldName=null;
	// 加载数据
	var selection = top.editor.getSelection();
	var ranges = selection.getRanges();
	var element = selection.getSelectedElement();

	var title = $("#title");
	var fieldName = $("#fieldName");
	var type = $("#type");
	var format = $("#format");
	var style = $("#style");

	if (element != null && element.getAttribute("xtype") == "xfetch") {
		if (element.getAttribute('title')) {
			title[0].value = element.getAttribute('title');
			oldTitle=element.getAttribute('title');
		}
		if (element.getAttribute('fieldName')) {
			fieldName[0].value = element.getAttribute('fieldName');
			oldFieldName=element.getAttribute('fieldname');
		}
		if (element.getAttribute('style')) {
			style[0].value = element.getAttribute('style');
		}

		var model = element.getAttribute('model');
		model=$.trim(model);
		if (model!="") {
			model = eval("(" + model + ")");
			switch (model.type) {
			case "1":
				type[0].value = "1";
				format.html(renderDatetime);
				break;
			case "2":
				type[0].value = "2";
				format.html(renderTime);
				break;
			default:
				type[0].value = model.type;
				break;
			}
			format[0].value = model.format;
		}
	} else {
		format.html(renderDatetime);
	}
	
	$("#type").on("change",function(){
		switch($(this).val()){
			case "1":
				$("#format").removeAttr("disabled");
				$("#format").html(renderDatetime);
				break;
			case "2":
				$("#format").removeAttr("disabled");
				$("#format").html(renderTime);
				break;
			default:
				$("#format").html("");
				$("#format").attr("disabled", "disabled");
			
		}
	});

	initFormValidation();
});



