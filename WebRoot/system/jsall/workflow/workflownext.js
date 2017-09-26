function showNextPrcsInfo(nextList) {
    var arrHtml = [];
    if (nextList.length > 0) {
        for (var i = 0; nextList.length > i; i++) {
            var tmp = {};
            var html = "";
            if (nextList[i].prcsId == "0") {
            	html+="<div class=\"list-group\">\n"+
				      "		<div class=\"list-group-item\" style=\"height:55px;\">"+
                      "			<input type=\"checkbox\" name=\"prcsinfo\"id=\"prcsinfo_" + nextList[i].prcsId + "\" value =\"" + nextList[i].prcsId + "\">" + nextList[i].prcsName + "<br>";
				      "		</div>\n";
            } else {
            	if(i>0){
            		html+="<div style=\"display: none;\" id=\"div"+nextList[i].prcsId+"\" name=\"divcneter\">\n";
            	}else{
            		html+="<div  id=\"div"+nextList[i].prcsId+"\" name=\"divcneter\">\n";
    			}
                html+="<div class=\"list-group\">\n"+
                                    "<div class=\"list-group-item panel-default\" style=\"height:42px;\">\n <div style=\"float:left;\"><b>下一步名称："+nextList[i].prcsName +"</b></div>\n"+ 
                                    "<div style=\"float:right;\"><b>当前步骤："+prcsName+"</b></div>\n"+
                                    "</div>\n"+
                                "<div class=\"list-group-item\" style=\"height:80px;\">"+
                                "<table><tr>" +
                                "<td width='100px'><span style='width:100px;float:left;line-height: 30px;'>主办人员：</span>" +
                                "</td>" +
                                "<td width='100%'>" +
                                "	<div id=\"opdiv"+nextList[i].prcsId+"\" name=\"opdiv"+nextList[i].prcsId+"\"></div>" +
                        		"	<a class='workflow-selectuser' " +
                        		"			data-select-setting=\"{container:['opAccount_" + nextList[i].prcsId + "','opUser_" + nextList[i].prcsId + "'],multiple:false,prcsId:'"+nextList[i].prcsId +"',flowId:'"+flowId +"'}\" " +
                				"			style='cursor: pointer;left;line-height: 30px;' data-toggle=\"modal\"  data-target=\"#myModal\">添加人员</a>" +
                                "</td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td><span style='width:100px;float:left;line-height: 30px;'>知会人员：</span>" +
                                "</td>" +
                                "<td><div id=\"zhdiv"+nextList[i].prcsId+"\" name=\"zhdiv"+nextList[i].prcsId+"\"></div>" +
                                "</td>" +
                                "</tr>" +
                                "</table>\n"+
                                            "<input type=\"hidden\" name=\"opAccount_" + nextList[i].prcsId + "\" id=\"opAccount_" + nextList[i].prcsId + "\">"+
                                            "<input type=\"hidden\" name=\"opUser_" + nextList[i].prcsId + "\" id=\"opUser_" + nextList[i].prcsId + "\">"+
                               "</div></div>\n";
            }
            html+=   "<div href=\"#\" class=\"list-group-item\" style=\"height:55px;\">\n"+
                                    "<span style='width:100px;float:left;line-height: 30px;'>提醒内容：</span><input type=\"text\" class=\"form-control \" style=\"width:300px;\"  name=\"messagecontent\" id=\"messagecontent\" placeholder=\"请输入提醒内容！！\"/>" + 
                                    "</div>\n"+
                                    "</div>\n";
            tmp.prcsId = nextList[i].prcsId;
            tmp.prcsName = nextList[i].prcsName;
            tmp.htmlStr = html;
            arrHtml.push(tmp);
        }
    }
    return arrHtml;
}
//检查本步骤相关设置
function checkNext(id) {
    var forceParallel = nodedata.forceParallel;
    var nextList = nodedata.nextnode;
    if (forceParallel == "2") {
        for (var i = 0; nextList.length > i; i++) {
            if (id == "prcsinfo_" + nextList[i].prcsId) {
                $("#prcsinfo_" + nextList[i].prcsId).attr("checked", true);
            } else {
                $("#prcsinfo_" + nextList[i].prcsId).attr("checked", false);
            }
        }
    }
}