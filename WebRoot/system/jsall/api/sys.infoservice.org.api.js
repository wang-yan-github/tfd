/**
 *系统.企业信息服务.组织机构.api 
 */
function sys_infoservice_org_getOrgInfoAct(){
	var org=null;
	$.ajax({
		url:contextPath+"/infoservice/org/act/InfoserviceOrgAct/getOrgInfo.act",
		type:"POST",
		async:false,
		dataType:"json",
		success:function(rv){
			org=rv;
		}
	});
	return org;
}
