
var userhtml="<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\"  aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"z-index:9999;\">\n"+
   "<div class=\"modal-dialog\">\n"+
      "<div class=\"modal-content\">\n"+
         "<div class=\"modal-header\" style=\"padding-top:10px;padding-bottom:10px;\">\n"+
            "<button type=\"button\" class=\"close\"  data-dismiss=\"modal\" aria-hidden=\"true\">  &times;</button>\n"+
            "<h5 class=\"modal-title\" id=\"myModalLabel\">人员选择</h5>\n"+
         "</div>\n"+
         "<div class=\"modal-body\" style=\"height:340px;\">\n"+
            "<div id=\"userSearch\"class=\"col-xs-5\" style=\"width:100%;margin-top:-15px; text-align: center;line-height: 40px;height:50px;\"><l style=\"float:left;margin-left: 100px;\">人员姓名：</l><l style=\"float:left;margin-top: 5px;\"><input id=\"searchName\" style=\"width:150px;\" class=\"form-control\"/></l>&nbsp;&nbsp;<button  id=\"opt_user_search\" class=\"btn btn-info\" style=\"float:left;margin-left: 5px;margin-top:5px;\">搜索</button></div>\n"+
             "<div id=\"deptTitle\" style=\"height:7%;width:100%;margin-left: 5px;margin-top:3%;\" >\n"+
                        "<div id=\"deptListTitle\" style=\"text-align: center;line-height: 40px;background-color: #F7F8BD;border: solid 1px #CCCCCC;border-bottom: none;border-top-left-radius:5px;border-top-right-radius:5px;width: 32%;height:40px;;float:left;margin-right: 5px;\">部门列表</div>\n"+
                        "<div id=\"deptUserListTitle\" style=\"width: 32%;height:40px;float:left;margin-right: 5px;text-align: center;line-height: 40px;background-color: #F7F8BD;border: solid 1px #CCCCCC;border-bottom: none;border-top-left-radius:5px;border-top-right-radius:5px;\">部门人员列表&nbsp;&nbsp;<l id=\"opt\"><a href=\"javascript:void(0);\" id=\"opt_show_user_chose_all\">全选</a><a href=\"javascript:void(0);\" id=\"opt_show_user_del_all\">/反选</a></l></div>\n"+
                        "<div id=\"userCheckedListTitle\" style=\"width: 32%;height:40px;float:left;text-align: center;line-height: 40px;background-color: #F7F8BD;border: solid 1px #CCCCCC;border-bottom: none;border-top-left-radius:5px;border-top-right-radius:5px;\">已选择人员&nbsp;&nbsp;<a href=\"javascript:void(0);\" id=\"opt_checked_user_del_all\">反选</a></div>\n"+
             "</div>\n"+
    
    
            "<div id=\"deptListContainer\" style=\"height:240px;width:100%;margin-left: 5px;margin-bottom:5px;\">\n"+
                "<div id=\"deptList\" style=\"width: 32%;height:100%;float:left;margin-right: 5px;overflow-y:auto;border: solid 1px #CCCCCC;padding: 0px;\" class=\"ztree\"></div>\n"+
                "<div id=\"deptUserList\" style=\"cursor:pointer;line-height:30px;text-indent:15px; border-bottom: solid 1px #CCCCCC; width: 32%;height:100%;float:left;margin-right: 5px;overflow-y:auto;border: solid 1px #CCCCCC;\"></div>\n"+
                "<div id=\"userCheckedList\" style=\"cursor:pointer;line-height:30px;text-indent:15px;border-bottom: solid 1px #CCCCCC;width: 32%;height:100%;float:left;overflow-y:auto;border: solid 1px #CCCCCC;\"></div>\n"+
            "</div>\n"+
    
         "</div>\n"+
                 "<div class=\"modal-footer\" style=\"padding-top:5px;padding-bottom:5px;\">\n"+
                 " <button type=\"button\" class=\"btn btn-default\" id=\"opt_all\" data-dismiss=\"modal\">全部人员</button>\n"+
                   " <button type=\"button\" class=\"btn btn-default\" id=\"opt_remove\" data-dismiss=\"modal\">取消</button>\n"+
                    "<button type=\"button\" class=\"btn btn-primary\" id=\"opt_ok\" data-dismiss=\"modal\" >确定</button>\n"+
                 "</div>\n"+      
         "</div>\n"+
      "</div></div>";
var depthtml="<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\"  aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"z-index:9999;\">\n"+
"<div class=\"modal-dialog\">\n"+
"<div class=\"modal-content\">\n"+
   "<div class=\"modal-header\" style=\"padding-top:10px;padding-bottom:10px;\">\n"+
      "<button type=\"button\" class=\"close\"  data-dismiss=\"modal\" aria-hidden=\"true\">  &times;</button>\n"+
      "<h5 class=\"modal-title\" id=\"myModalLabel\">部门选择</h5>\n"+
   "</div>\n"+
   "<div class=\"modal-body\" style=\"height:340px;\">\n"+
      "<div id=\"deptSearch\"class=\"col-xs-4\" align=\"center\" style=\"width:100%;margin-top:-15px;text-align: center;line-height: 40px;height:50px;\"><l style=\"float:left;margin-left: 100px;\">部门名称：</l><l style=\"float:left;margin-top: 5px;\"><input id=\"searchdept\" style=\"width:150px;\" class=\"form-control\"/></l>&nbsp;&nbsp;<button  id=\"opt_dept_search\" class=\"btn btn-info\" style=\"float:left;margin-left: 5px;margin-top: 5px;\">搜索</button></div>\n"+
       "<div id=\"deptTitle\" style=\"height:7%;width:100%;margin-left: 5px;\" >\n"+
                  "<div id=\"deptListTitle\" style=\"text-align: center;line-height: 40px;background-color: #F7F8BD;border: solid 1px #CCCCCC;border-bottom: none;border-top-left-radius:5px;border-top-right-radius:5px;width: 32%;height:40px;;float:left;margin-right: 5px;\">部门列表</div>\n"+
                  "<div id=\"deptChildListTitle\" style=\"width: 32%;height:40px;float:left;margin-right: 5px;text-align: center;line-height: 40px;background-color: #F7F8BD;border: solid 1px #CCCCCC;border-bottom: none;border-top-left-radius:5px;border-top-right-radius:5px;\">部门列表&nbsp;&nbsp;<l id=\"opt\"><a href=\"javascript:void(0);\" id=\"opt_show_child_chose_all\">全选</a><a href=\"javascript:void(0);\" id=\"opt_show_child_del_all\">/反选</a></l></div>\n"+
                  "<div id=\"deptCheckedListTitle\" style=\"width: 32%;height:40px;float:left;text-align: center;line-height: 40px;background-color: #F7F8BD;border: solid 1px #CCCCCC;border-bottom: none;border-top-left-radius:5px;border-top-right-radius:5px;\">已选择部门&nbsp;&nbsp;<a href=\"javascript:void(0);\" id=\"opt_checked_dept_del_all\">反选</a></div>\n"+
       "</div>\n"+


      "<div id=\"deptListContainer\" style=\"height:240px;width:100%;margin-left: 5px;margin-bottom:5px;\">\n"+
          "<div id=\"deptList\" style=\"width: 32%;height:100%;float:left;margin-right: 5px;overflow:auto;border: solid 1px #CCCCCC;padding: 0px;\" class=\"ztree\"></div>\n"+
          "<div id=\"deptChildList\" style=\"cursor:pointer;height:30px;line-height:30px;text-indent:15px; border-bottom: solid 1px #CCCCCC;width: 32%;height:100%;float:left;margin-right: 5px;overflow-y:auto;border: solid 1px #CCCCCC;\"></div>\n"+
          "<div id=\"deptCheckedList\" style=\"cursor:pointer;line-height:30px;text-indent:15px;border-bottom: solid 1px #CCCCCC;width: 32%;height:100%;float:left;overflow-y:auto;border: solid 1px #CCCCCC;\"></div>\n"+
      "</div>\n"+

   "</div>\n"+
           "<div class=\"modal-footer\" style=\"padding-top:5px;padding-bottom:5px;\">\n"+
           " <button type=\"button\" class=\"btn btn-default\" id=\"opt_all\" data-dismiss=\"modal\">全部部门</button>\n"+
             " <button type=\"button\" class=\"btn btn-default\" id=\"opt_remove\" data-dismiss=\"modal\">取消</button>\n"+
              "<button type=\"button\" class=\"btn btn-primary\" id=\"opt_ok\" data-dismiss=\"modal\" >确定</button>\n"+
           "</div>\n"+      
   "</div>\n"+
"</div></div>";
var privhtml="<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"z-index:9999;\">\n"+
   "<div class=\"modal-dialog\">\n"+
      "<div class=\"modal-content\">\n"+
         "<div class=\"modal-header\" style=\"padding-top:10px;padding-bottom:10px;\">\n"+
            "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">  &times;\n"+
            "</button><h5 class=\"modal-title\" id=\"myModalLabel\">\n"+
             "选择角色</h5>\n"+
         "</div><div class=\"modal-body\" align=\"center\" style=\"height: 300px;\">\n"+
						"<div><div style=\"width: 220px; height: 40px;float:left; margin-left:45px; text-align: center; line-height: 40px; background-color: #F7F8BD; border: solid 1px #CCCCCC; border-bottom: none; border-top-left-radius: 5px; border-top-right-radius: 5px;\">\n"+
						"角色列表</div><div style=\"width: 220px;float:left; height: 40px; margin-left: 14px; text-align: center; line-height: 40px; background-color: #F7F8BD; border: solid 1px #CCCCCC; border-bottom: none; border-top-left-radius: 5px; border-top-right-radius: 5px;\">\n"+
						"已选中的角色列表</div></div><div><div style=\"width: 220px; margin-left:45px;text-align:center; cursor:pointer;height:240px;line-height:30px;border-bottom: solid 1px #CCCCCC; float:left;overflow-y:auto;border: solid 1px #CCCCCC;\" id=\"selectPriv\"></div>\n"+
						"<div style=\"width: 220px;margin-left:14px; text-align:center; cursor:pointer;height:240px;line-height:30px;border-bottom: solid 1px #CCCCCC; float:left;overflow-y:auto;border: solid 1px #CCCCCC;\" id=\"optPriv\"></div>\n"+
						"</div></div>\n" +
						"<div class=\"modal-footer\" style=\"padding-top:5px;padding-bottom:5px;\">\n"+
						" <button type=\"button\" class=\"btn btn-default\" id=\"opt_all\" data-dismiss=\"modal\">全部角色</button>\n"+
            "<button type=\"button\" class=\"btn btn-default\" id=\"opt_remove\" data-dismiss=\"modal\">取消</button>\n"+
            "<button type=\"button\" class=\"btn btn-primary\" id=\"opt_ok\" data-dismiss=\"modal\" >确定</button>\n"+
         "</div>\n" +
         "</div>\n"+
"</div></div>";


var leavehtml="<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" style=\"z-index:9999;\">\n"+
   "<div class=\"modal-dialog\">\n"+
      "<div class=\"modal-content\">\n"+
         "<div class=\"modal-header\" style=\"padding-top:10px;padding-bottom:10px;\">\n"+
            "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">  &times;\n"+
            "</button><h5 class=\"modal-title\" id=\"myModalLabel\">\n"+
             "选择职务</h5>\n"+
         "</div><div class=\"modal-body\" align=\"center\" style=\"height: 300px;\">\n"+
						"<div><div style=\"width: 220px; height: 40px;float:left; margin-left:45px; text-align: center; line-height: 40px; background-color: #F7F8BD; border: solid 1px #CCCCCC; border-bottom: none; border-top-left-radius: 5px; border-top-right-radius: 5px;\">\n"+
						"职务列表</div><div style=\"width: 220px;float:left; height: 40px; margin-left: 14px; text-align: center; line-height: 40px; background-color: #F7F8BD; border: solid 1px #CCCCCC; border-bottom: none; border-top-left-radius: 5px; border-top-right-radius: 5px;\">\n"+
						"已选中的职务列表</div></div><div><div style=\"width: 220px; margin-left:45px;text-align:center; cursor:pointer;height:240px;line-height:30px;border-bottom: solid 1px #CCCCCC; float:left;overflow-y:auto;border: solid 1px #CCCCCC;\" id=\"selectleave\"></div>\n"+
						"<div style=\"width: 220px; margin-left:14px; text-align:center; cursor:pointer;height:240px;line-height:30px;border-bottom: solid 1px #CCCCCC; float:left;overflow-y:auto;border: solid 1px #CCCCCC;\" id=\"optleave\"></div>\n"+
						"</div></div>\n" +
						"<div class=\"modal-footer\" style=\"padding-top:5px;padding-bottom:5px;\">\n"+
            "<button type=\"button\" class=\"btn btn-default\" id=\"leaveremove\" data-dismiss=\"modal\">取消</button>\n"+
            "<button type=\"button\" class=\"btn btn-primary\" id=\"leaveok\" data-dismiss=\"modal\" >确定</button>\n"+
         "</div>\n" +
         "</div>\n"+
"</div></div>";
function usercreateDialog(singleSelect) {
  $("#modaldialog").html(userhtml);
  if(singleSelect=='true'){
  	$("#opt").hide();
  	$("#opt_all").hide();
  }
}
function deptcreateDialog(singleSelect){
	$("#modaldialog").html(depthtml);
	if(singleSelect=='true'){
  	$("#opt").hide();
  	$("#opt_all").hide();
  }
}
function privcreateDialog(singleSelect){
	$("#modaldialog").html(privhtml);
	if(singleSelect=='true'){
  	$("#opt_all").hide();
  }
}
function leavecreateDialog(){
	$("#modaldialog").html(leavehtml);
}
function adduser(singleSelect)
{
	usercreateDialog(singleSelect) ;
	$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
	}
function adddept(singleSelect)
{
	deptcreateDialog(singleSelect);
	$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
	}
function addpriv(singleSelect){
	privcreateDialog(singleSelect);
	$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
}
function addleave(){
	leavecreateDialog();
	$(document).ready(function(){ 
		$('#myModal').modal({backdrop: 'static', keyboard: false});
		$("#myModal").modal('show'); 
		});
}