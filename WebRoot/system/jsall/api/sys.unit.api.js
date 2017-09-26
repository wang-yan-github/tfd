/**
 *系统.组织机构.api 
 */
//根据名称判断unit是否存在
function sys_unit_unitExistOfOrgName(orgName){
	var exist=true;
	$.ajax({
		url:contextPath+"/tfd/system/unit/org/act/UnitAct/unitExistOfOrgName.act",
		data:{orgName:orgName},
		type:"POST",
		async:false,
		dataType:"json",
		success:function(result){
			exist=result;
		}
	});
	return exist;
}
function sys_unit_getUnitInfoAct(){
	var unit=null;
	$.ajax({
		url:contextPath+"/tfd/system/unit/org/act/UnitAct/getUnitInfoAct.act",
		type:"POST",
		async:false,
		dataType:"json",
		success:function(result){
			unit=result;
		}
	});
	return unit;
}
