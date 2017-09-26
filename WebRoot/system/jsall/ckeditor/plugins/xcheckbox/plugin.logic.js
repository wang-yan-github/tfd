
var flowform_plugin_xcheckbox=new Object();
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
									for(var fieldName in detailjson){
										var xtype=detailjson[fieldName].xtype;
										var title=detailjson[fieldName].title;
										if (xtype!="xcheckbox"&&value==title) {
											return {valid:false,message:"标题已存在"};
										}
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
								}else if(!new RegExp("^[a-z]*$").test(value)){
									return {valid:false,message:"字段名必须全部为小写字母"};
								}else{
									for(var fieldName in detailjson){
										var xtype=detailjson[fieldName].xtype;
										if (xtype!="xcheckbox"&&value==fieldName) {
											return {valid:false,message:"名称已存在"};
										}
									}
								}
		    					return true;
		    				}
		    			}
		    		}
		    	},
		    	displayValue:{
		    		validators:{
		    			callback:{
		    				callback:function(value,validator,$field){
		    					value=$.trim(value);
		    					$field.val(value);
		    					if (value=="") {
									return {valid:false,message:"请填写"};
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
	flowform_plugin_xcheckbox.validate=function() {
		$("form").data("formValidation").destroy();
		initFormValidation();
		$("form").data("formValidation").validate();
		
		if ($("form").data("formValidation").isValid()) {
			var title=$("#title").val();
			var fieldName=$("#fieldName").val();
			
			titlejson[title]=title;
			fieldjson[fieldName] = title;
			return true;
		}
		return false;
	}

	// 转换dom节点，required
	flowform_plugin_xcheckbox.toDomStr=function() {
		var title = $("#title").val();
		var fieldName = $("#fieldName").val();
		var displayvalue = $("#displayvalue").val();
		var def = $("#def").val();
		var model=new Array();
		model.push({value:displayvalue,desc:displayvalue});

		var html = "<input " +
				   "	type='checkbox' " +
				   "	xtype='xcheckbox' " +
				   "	datatype='text'  " +
				   "	title='" + title + "'"+
				   "	value='" + displayvalue + "' " +
				   "	fieldName='" + fieldName + "'" +
				   "	name='" + fieldName + "' " +
				   "	"+(def=="true"?"checked='checked'":"")+
				   "	model='"+$.json.encode(model)+"'"+
				   "/>"+ displayvalue;
		return html;
	}
	
	
	
	var titlejson = top.fieldList.title;
	var fieldjson = top.fieldList.fieldName;
	var detailjson=top.fieldList.detail;
	
	var selection = top.editor.getSelection();
	var ranges = selection.getRanges();
	var element = selection.getSelectedElement();
	
	if (element != null && element.getAttribute('xtype') == "xcheckbox") {
		var title = $("#title");
		var fieldName = $("#fieldName");
		if (element.getAttribute('title')) {
			title.val(element.getAttribute('title'));
		}
		if (element.getAttribute('fieldName')) {
			fieldName.val(element.getAttribute('fieldName'));
		}
		if (element.getAttribute("value")) {
			$("#displayvalue").prop("value", element.getAttribute("value"));
		}
		if (element.getAttribute('checked')) {
			$("#def").val("true");
		}
	}
	initFormValidation();
});



