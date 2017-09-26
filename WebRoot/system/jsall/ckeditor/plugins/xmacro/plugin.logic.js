var flowform_plugin_xmacro=new Object();
$(function(){
	
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
	flowform_plugin_xmacro.validate=function() {
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
	
	flowform_plugin_xmacro.toDomStr=function(){
		var title = $("#title").val();
		var fieldName = $("#fieldName").val();
		var type = $("#type").val();
		var format = $("#format").val();
		var style = $("#style").val();
		var model=new Object();
		model.type=type;
		model.format=format;
		
		var html = "<input " +
		"	type='text' " +
		"	xtype='xmacro' " +
		" 	datatype='text'"+
		"	name='"+fieldName+"' " +
		" 	fieldName='"+fieldName+"'"+
		"	id='"+fieldName+"'"+
		"	value='{宏控件}' " +
		"	title='"+title+"'" +
		" 	model='"+$.json.encode(model)+"'"+
		" 	style='"+style+"'"+
		"/>";
		return html;
	}
	
	$("#type").on("change",function(){
		var formatOption="";
		switch($(this).val()){
		case "1":
			formatOption="<option value='yyyy年'>yyyy年</option>"+
						  "<option value='yyyy'>yyyy</option>"+
						  "<option value='yy年'>yy年</option>"+
						  "<option value='yy'>yy</option>";
			break;
		case "2":
			formatOption="<option value='yyyy-MM-dd'>yyyy-MM-dd</option>"+
						 "<option value='yyyy年MM月dd日'>yyyy年MM月dd日</option>"+
						 "<option value='yyyyMMdd'>yyyyMMdd</option>"+
						 "<option value='yyyy/MM/dd'>yyyy/MM/dd</option>"+
						 "<option value='yyyy.MM.dd'>yyyy.MM.dd</option>"+
						 "<option value='yyyy年MM月'>yyyy年MM月</option>"+
						 "<option value='MM月'>MM月</option>"+
						 "<option value='dd日'>dd日</option>"+
						 "<option value='MM-dd'>MM-dd</option>"+
						 "<option value='MM月dd日'>MM月dd日</option>";
			break;
		case "3":
			formatOption ="<option value='yyyy-MM-dd HH:mm'>yyyy-MM-dd HH:mm</option>"+
							"<option value='yyyy-MM-dd HH:mm:ss'>yyyy-MM-dd HH:mm:ss</option>"+
							"<option value='yyyy年MM月dd日 HH时mm分'>yyyy年MM月dd日 HH时mm分</option>"+
							"<option value='yyyy年MM月dd日 HH时mm分ss秒'>yyyy年MM月dd日 HH时mm分ss秒</option>"+
							"<option value='HH'>HH</option>"+
							"<option value='HH时'>HH时</option>"+
							"<option value='HH:mm'>HH:mm</option>"+
							"<option value='HH时mm分'>HH时mm分</option>"+
							"<option value='HH:mm:ss'>HH:mm:ss</option>"+
							"<option value='HH时mm分ss秒'>HH时mm分ss秒</option>";
			break;
		}
		$("#format").html(formatOption);
	});

	var titlejson = top.fieldList.title;
	var fieldjson = top.fieldList.fieldName;
	var oldTitle=null;
	var oldFieldName=null;
	//初始化控件数据
	var selection = top.editor.getSelection();
	var ranges = selection.getRanges();
	var element = selection.getSelectedElement();

	if(element!=null && element.getAttribute('xtype')=='xmacro'){
		if(element.getAttribute("title")){
			$("#title").val(element.getAttribute("title"));
			oldTitle=element.getAttribute('title');
		}
		if(element.getAttribute("style")){
			$("#style").val(element.getAttribute("style"));
		}
		if(element.getAttribute("fieldName")){
			$("#fieldName").val(element.getAttribute("fieldName"));
			oldFieldName=element.getAttribute('fieldname');
		}
		
		var model = element.getAttribute("model");
		model = eval("("+model+")");
		
		$("#type").val(model.type);
	}
	initFormValidation();
	$("#type").trigger("change");
})
	

	