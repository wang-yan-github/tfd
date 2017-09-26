function getSysParamByNames(d) {
	var b = contextPath + "/sysPara/getSysParaList.action";
	var a = {
		paraNames : d
	};
	var c = tools.requestJsonRs(b, a);
	if (c.rtState) {
		return c.rtData
	} else {
		alert(c.rtMsg)
	}
}
function getPersonNameAndUuidByUuids(c) {
	var b = contextPath + "/personManager/getPersonNameAndUuidByUuids.action";
	var a = {
		uuid : c
	};
	var d = tools.requestJsonRs(b, a);
	if (d.rtState) {
		return d.rtData
	} else {
		alert(d.rtMsg)
	}
}
function getPersonListByUuids(c) {
	var b = contextPath + "/personManager/getPersonListByUuids.action";
	var a = {
		uuid : c
	};
	var d = tools.requestJsonRs(b, a);
	if (d.rtState) {
		return d.rtData
	} else {
		alert(d.rtMsg)
	}
}
function getSysCodeByParentCodeNo(g, d) {
	var c = contextPath + "/sysCode/getSysCodeByParentCodeNo.action";
	var a = {
		codeNo : g
	};
	var h = tools.requestJsonRs(c, a);
	if (h.rtState) {
		var f = h.rtData;
		if (d && $("#" + d)[0]) {
			var b = "";
			for ( var e = 0; e < f.length; e++) {
				b = b + "<option value='" + f[e].codeNo + "'>" + f[e].codeName
						+ "</option>"
			}
			$("#" + d).append(b)
		}
		return f
	} else {
		alert(h.rtMsg)
	}
};