$(function(){
	var REQUEST_URL_UNIT_SAVE=contextPath+"/infoservice/org/act/InfoserviceOrgAct/save.act";
	var REQUEST_URL_SERVICE_ITEM_SAVE=contextPath+"/infoservice/org/act/InfoserviceServiceItemAct/save.act";
	var REQUEST_URL_SERVICE_ITEM_LIST=contextPath+"/infoservice/org/act/InfoserviceServiceItemAct/search.act";
	var REQUEST_URL_SERVICE_ITEM_DELETE=contextPath+"/infoservice/org/act/InfoserviceServiceItemAct/delete.act";
	var formSetting={
			"#unit":{
				formEdit:unitEdit
			}
	}
	var unit=null;
	var itemIndex=0;
	var infoserviceOrg=null;
	var orgIntroduceEditor=null;
	var serviceAreaIndex=0;
	
	
	
	
	function saveUnit($form){
		var saveResult=false;
		$.ajax({
			url:REQUEST_URL_UNIT_SAVE,
			data:$form.serialize(),
			type:"POST",
			async:false,
			dataType:"json",
			success:function(result){
				saveResult=eval(result);
			}
		});
		return saveResult;
	}
	function bsAlert($alert,ms,callback){
		ms=ms?ms:1500;
		$alert.show()
		.animate({opacity:"0"},ms,function(){
			if (callback) {
				callback();
			}
			$alert.hide().animate({opacity:"1"},0);
		});
	}
	function loadUnit(){
		unit=sys_unit_getUnitInfoAct();
		for(var fieldName in unit){
			var $input=$("#unit").find("*[name='"+fieldName+"']");
			if ($input.length>0) {
				$input.val(unit[fieldName]);
			}
		}
	}
	function loadInfoserviceOrg(){
		infoserviceOrg=sys_infoservice_org_getOrgInfoAct();
		for(var fieldName in infoserviceOrg){
			var $input=$("#unit").find("*[name='infoserviceOrg_"+fieldName+"']");
			if ($input.length>0) {
				$input.val(infoserviceOrg[fieldName]);
			}
		}
	}
	function unitEdit(){
		$("#unit").formValidation({
			framework:"bootstrap",
			err:{
				container:"tooltip"
			},
			icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields:{
	        	orgName:{
	        		trigger:"keyup",
	        		validators:{
	        			notEmpty:{
	        				message:"请填写！"
	        			},
	        			callback:{
	        				message:"该企业已存在！",
	        				callback:function(value,validator,$field){
	        					value=$.trim(value);
	        					if (value!=unit.orgName) {
	        						return !sys_unit_unitExistOfOrgName(value);
								}
	        					return true;
	            			}
	        			}
	        		}
	        	},
	        	orgAdd:{
	        		trigger:"keyup",
	        		validators:{
	        			notEmpty:{
	        				message:"请填写！"
	        			}
	        		}
	        	},
	        	orgTel:{
	        		trigger:"blur",
	        		validators:{
	        			regexp:{
	        				message:"请填写正确的联系电话！",
	        				regexp:/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$)/
	        			}
	        		}
	        	},
	        	orgEmail:{
	        		trigger:"blur",
	        		validators:{
	        			regexp:{
	        				message:"请填写正确的邮箱！",
	        				regexp:/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
	        			}
	        		}
	        	},
	        	orgFax:{
	        		trigger:"blur",
	        		validators:{
	        			regexp:{
	        				message:"请填写正确的传真号码！",
	        				regexp:/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/
	        			}
	        		}
	        	}
	        }
		})
		.on("success.form.fv",function(e){
			e.preventDefault();
			var $form=$(e.target);
			
			updateServiceArea();
			
			$("#unit").find("*[name='infoserviceOrg_businessLicense']").val($("#infoserviceOrg_businessLicenseId").val());
			$("#unit").find("*[name='infoserviceOrg_orgCodeCertificate']").val($("#infoserviceOrg_orgCodeCertificateId").val());
			$("#unit").find("*[name='infoserviceOrg_taxRegistrationCertificate']").val($("#infoserviceOrg_taxRegistrationCertificateId").val());
			
			var result=saveUnit($("#unit"));
			if (result) {
				bsAlert($("#alert-success"), null, function(){
					loadUnit();
					openFormScreen("#unit");
				});
			}else{
				bsAlert($("#alert-danger"));
			}
		});
	}
	function openFormScreen(form){
		$(form).data("formValidation").destroy();
		$(form).find(".form-save-bar").hide();
		$(".form-screen[value='"+form+"']").show();
		
		$(".panel-form-edit[value='"+form+"']").addClass("panel-form-edit-slideup");
	}
	function servieItmeIndexReset(){
		$(".infoserviceServiceItem-form .service-item-i .i").each(function(i){
			$(this).html(i+1);
		});
		$(".infoserviceServiceItem .form-screen .service-item-i .i").each(function(i){
			$(this).html(i+1);
		});
	}
	function serviceItemEdit(form){
		$(form).formValidation({
			framework:"bootstrap",
			err:{
				container:"tooltip"
			},
			icon:{
				valid:"glyphicon glyphicon-ok",
				invalid:"glyphicon glyphicon-remove",
				validating:"glyphicon glyphicon-refresh"
			},
			fields:{
				serviceName:{
					trigger:"keyup",
					validators:{
						notEmpty:{
							message:"请填写！"
						}
					}
				},
				servicePrice:{
					trigger:"keyup",
					validators:{
						integer:{
							message:"请填写数字！"
						}
					}
				},
				servicePriceHighest:{
					trigger:"keyup",
					validators:{
						integer:{
							message:"请填写数字！"
						},
						callback:{
							message:"请填写正确的最高价格！",
							callback:function(value,validator,$field){
								value=$.trim(value);
								var servicePrice=$.trim($(form).find("*[name='servicePrice']").val());
								if (servicePrice!=""&&value!="") {
									return parseInt(value)>parseInt(servicePrice);
								}
								return true;
							}
						}
					}
				}
			}
		})
		.on("success.form.fv",function(e){
			e.preventDefault();
			var result=saveServiceItem($(form));
			if (result&&result.result) {
				bsAlert($("#alert-success"), null, function(){
					$(form).find("*[name='serviceId']").val(result.serviceId);
					openFormScreen(form);
				});
			}else{
				bsAlert($("#alert-danger"));
			}
		});
	}
	function saveServiceItem($form){
		var result=null;
		$.ajax({
			url:REQUEST_URL_SERVICE_ITEM_SAVE,
			data:$form.serialize(),
			type:"POST",
			async:false,
			dataType:"json",
			success:function(rv){
				result=rv;
			}
		});
		return result;
	}
	function loadServiceItem(){
		var items=getServiceItemList(unit.orgId);
		if (items&&items.length>0) {
			var i=0;
			function itemsLoaded(i){
				$("#service-item-add").trigger(
					"click",
					{
						callback:function(form){
							var item=items[i];
							for(var fieldName in item){
								var $input=$(form).find("*[name='"+fieldName+"']");
								if ($input.length>0) {
									$input.val(item[fieldName]);
								}
							}
							
							formSetting[form].editor.setData(item.serviceDesc);
		
							$(".panel-form-edit[value='"+form+"']").addClass("panel-form-edit-slideup");
							
							i++;
							if (i<items.length) {
								itemsLoaded(i);
							}
						},
						firstLoad:true
					}
				);
			}
			itemsLoaded(i);
		}
	}
	function serviceItemRemovePopover($itemPanel,$remove){
		var contentHtml="<div style='text-align:center;width:150px;'>" +
				"			<button type='button' class='btn btn-primary btn-item-remove-cancel'>取消</button>" +
				"			<button type='button' class='btn btn-default btn-item-remove-ok'>是的</button>" +
				"		</div>";
		
		$remove.popover({
			html:true,
			container:"#"+$itemPanel.attr("id")+" .item-delete-popover",
			content:contentHtml,
			trigger:"click",
			placement:"left"
		})
		.on("shown.bs.popover",function(e){
			$(".btn-item-remove-cancel").on("click",function(){
				$remove.popover("hide");
			});
			$(".btn-item-remove-ok").on("click",function(){
				$remove.popover("hide");
				
				var serviceId=$itemPanel.find("*[name='serviceId']").val();
				var deleteResult=deleteServiceItem(serviceId);
				if (deleteResult) {
					bsAlert(
						$("#alert-service-item-remove"),
						null,
						function(){
							$itemPanel.remove();
							servieItmeIndexReset();
						}
					);
				}else{
					bsAlert(
						$("#alert-service-item-remove-failed")
					);
				}
				
			});
		});
	}
	function deleteServiceItem(serviceId){
		if (serviceId=="") {
			return true;
		}
		var deleteResult=false;
		$.ajax({
			url:REQUEST_URL_SERVICE_ITEM_DELETE,
			data:{serviceId:serviceId},
			type:"POST",
			async:false,
			dataType:"json",
			success:function(result){
				deleteResult=eval(result);
			}
		});
		return deleteResult;
	}
	function getServiceItemList(orgId){
		var items=null;
		$.ajax({
			url:REQUEST_URL_SERVICE_ITEM_LIST,
			data:{orgId,orgId},
			type:"POST",
			async:false,
			dataType:"json",
			success:function(result){
				items=result;
			}
		});
		return items;
	}
	function updateServiceArea(){
		var serviceArea=new Array();
		$(".service-area").each(function(){
			if($.trim($(this).val())!="")
			serviceArea.push($(this).val());
		});
		$("#unit").find("*[name='infoserviceOrg_serviceArea']").val(serviceArea.join(","));
	}
	
	
	
	
	
	
	
	
	
	
	
	$("#service-area-add").click("click",function(e,callback){
		$("#service-area-all").append(
				"<div class='service-area-box' value='"+serviceAreaIndex+"'>"+
					"<input class='form-control input-sm service-area'/>"+
					"&nbsp;"+
					"<button type='button' class='btn btn-sm btn-primary service-area-remove'  value='"+serviceAreaIndex+"'>删除</button>"+
				"</div>"
		);
		
		$(".service-area-box[value='"+serviceAreaIndex+"'] .service-area").district();
		
		if(callback && (typeof callback)=="function"){
			callback(serviceAreaIndex);
		}
		
		serviceAreaIndex++;
	});
	
	$("#service-area-all").on("click",".service-area-remove",function(){
		$(".service-area-box[value='"+$(this).attr("value")+"']").remove();
		updateServiceArea();
	});
	
	
	
	
	$(document).on("click",".form-screen .form-edit",function(){
		var form=$(this).val();
		formSetting[form].formEdit(form);
		$(".form-screen[value='"+form+"']").hide();
		$(form).find(".form-save-bar").show();
		
		$(".panel-form-edit[value='"+form+"']").removeClass("panel-form-edit-slideup");
		$(form).find(".form-slide").addClass("form-slideup");
		
		$(".form-slideup[value!='"+form+"']").trigger("click");
		$(form).find("*[name='serviceName'],*[name='orgName']").focus();
	});
	$(document).on("click",".form-slideup",function(){
		openFormScreen($(this).attr("value"));
		$(this).removeClass("form-slideup");
	});
	$("#service-item-add").on("click",function(e,param){
		$.ajax({
			url:"service-item-template.txt",
			type:"POST",
			dataType:"text",
			success:function(template){
				param=param?param:new Object();
				
				template=template
					.replace(/infoserviceServiceItem-#/g,"infoserviceServiceItem-"+itemIndex)
				$("#service-items").append(template);
				
				$itemPanel=$("#infoserviceServiceItem-"+itemIndex);
				var form="#infoserviceServiceItem-"+itemIndex+"-form";
				
				servieItmeIndexReset();
				
				serviceItemRemovePopover($itemPanel,$itemPanel.find(".service-item-remove"));
				
				var editor=CKEDITOR.replace(
						$(form).find("*[name='serviceDesc']").get(0)
				);
				
				formSetting[form]={
						formEdit:serviceItemEdit,
						editor:editor
				};
				
				if(param.callback){
					param.callback(form);
				}else{
					$(".form-screen[value='"+form+"']").find(".form-edit").trigger("click");
				}
				
				itemIndex++;
			}
		});
	});
	$(document).on("click.serviceitem.delete.popover.hide",function(e){
		$(".item-delete-popover").each(function(){
			$(".service-item-remove[value='"+$(this).attr("value")+"'").popover("hide");
		});
	});
	$(document).on("click.serviceitem.delete.popover.hide",".item-delete-popover,.service-item-remove",function(e){
		e.stopPropagation();
	});
	
	
	
	loadUnit();
	loadInfoserviceOrg();
	
	$("#unit").find("*[name='orgAdd']").district();
	
	orgIntroduceEditor=CKEDITOR.replace(
			$("#unit").find("*[name='infoserviceOrg_orgIntroduce']")[0]
	);
	orgIntroduceEditor.setData(infoserviceOrg.orgIntroduce);
	
	var serviceArea=infoserviceOrg.serviceArea;
	if (serviceArea) {
		$.each(serviceArea.split(","),function(i,area){
			$("#service-area-add").trigger("click",function(serviceAreaIndex){
				$(".service-area-box[value='"+serviceAreaIndex+"'] .service-area").val(area);
			});
		});
	}
	
	$.each(
			[
			 "infoserviceOrg_businessLicense",
			 "infoserviceOrg_orgCodeCertificate",
			 "infoserviceOrg_taxRegistrationCertificate"
		    ],
			function(i,data){
				$.ajax({
					url:"edit.fileupload.template.txt",
					dataType:"text",
					success:function(text){
						$("#"+data+"-input").append(text.replace(/#module#/g,"infoservice").replace(/#attach#/g,data));
						
						var attachId=infoserviceOrg[data.replace("infoserviceOrg_","")];
						$("#"+data+"Id").val(attachId);
						
						filesUpLoad("infoservice",data);
				   		creatAttachDiv(attachId,data);
					}
				});
			}
	);
	
	
	loadServiceItem();
});