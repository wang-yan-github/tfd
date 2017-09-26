package tfd.system.workflow.flowprocess.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import tfd.system.workflow.flowformitem.logic.FLowFormItemLogic;
import tfd.system.workflow.flowprocess.data.FlowProcess;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;

import com.system.global.SysProps;

public class FlowProcessTool {
    public void createFlowPrcsFile(Connection conn, int prcsId, String flowId) throws Exception {
        FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
        FlowProcess flowProcess = new FlowProcess();
        flowProcess = flowProcessLogic.getFlowProcessLogic(conn, flowId, prcsId);
        String formId = flowProcess.getFormId();
        String publiFileFlag = "";
        if (flowProcess.getPublicFile() != null) {
            JSONObject publicFileJson = JSONObject.fromObject(flowProcess.getPublicFile());
            if (publicFileJson.get("createUpLoad").equals("0")) {
                publiFileFlag = "style=\"display: none;\"";
            }
        }
        String publiFileHtml = "<div class=\"widget\"> \n "
                + "<div class=\"widget-header bordered-bottom bordered-themesecondary\">\n"
                + "<i class=\"widget-icon fa fa-tags themesecondary\"></i>\n"
                + "<span class=\"widget-caption themesecondary\">公共附件</span>\n"
                + "</div>\n"
                + "<table>\n"
                + "<tr>\n"
                + " <td colspan=\"4\"><div id=\"publicFileDiv\"></div></td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<td width=\"150px\" "
                + publiFileFlag
                + ">附件上传：</td>\n"
                + " <td colspan=\"3\">\n"
                + " <div class=\"fieldset flash\" id=\"publicFileProgress\" style=\"display: none;\"></div>\n"
                + "<div id=\"divStatus\" style=\"display: none;\"></div>\n"
                + "<a class=\"addfile\"  href=\"javascript:void(0)\" "
                + publiFileFlag
                + ">单附件\n"
                + "<input type=\"file\" onchange=\"fileUpLoad('workflow','publicFile');\" hidefocus=\"true\" size=\"1\" id=\"filepublicFile\"  name=\"filepublicFile\" class=\"addfile\">\n"
                + "<input type=\"hidden\" id=\"publicFileId\" name=\"publicFileId\"/>\n"
                + "</a>\n"
                + "<a class=\"add_swfupload\" href=\"javascript:void(0)\" "
                + publiFileFlag
                + ">多附件<span id=\"publicFile\"></span></a>\n"
                + "<div style=\"display: none;\"><a id=\"btnCancel\" onclick=\"swfu.cancelQueue();\" disabled=\"disabled\">取消上传</a></div>\n"
                + " </td>\n" + "</tr>\n" + "</table>\n" + " </div>\n";

        String rootPath = SysProps.getRootPath() + "/webapps/tfd/system/workflow/dowork/";
        String leavePath = "";
        File f = null;
        String fileName = "index.jsp";
        String htmlCode = "";
        String tmpHtml = "";
        List<Map<String, String>> fieldList = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT FORM_CODE,FORM_TABLE_NAME FROM WORK_FLOW_FORM WHERE FORM_ID='" + formId + "'";
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            tmpHtml = rs.getString("FORM_CODE");
            leavePath = rs.getString("FORM_TABLE_NAME").toLowerCase();
        }
        FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
        // 处理特殊控件
        Map<String, String> fieldXtypeMap = flowProcessLogic.getFieldXtypeLogic(conn, formId);
        List<String> allFieldList = flowFormItemLogic.getFieldByFormIdList(conn, formId);
        for (int j = 0; allFieldList.size() > j; j++) {
            if (fieldXtypeMap.get(allFieldList.get(j)).equals("xfetch")) {
                String model = flowFormItemLogic.getModeLByFormIdAndFieldLogic(conn, formId,
                        allFieldList.get(j));
                JSONObject json = JSONObject.fromObject(model);
                String type = json.getString("type");
                if (type.equals("1") || type.equals("2")) {
                    String format = json.getString("format");
                    String newStr = "fieldname=\"" + allFieldList.get(j)
                            + "\" onfocus=\"WdatePicker({dateFmt:'" + format
                            + "'})\" style=\"cursor: pointer;\" ";
                    tmpHtml = tmpHtml.replaceAll("fieldname=\"" + allFieldList.get(j) + "\"", newStr);
                } else if (type.equals("3")) {
                    String newStr = "fieldname=\"" + allFieldList.get(j) + "\"  onclick=\"deptinit(['','"
                            + allFieldList.get(j) + "'],true);\" style=\"cursor: pointer;\"";
                    tmpHtml = tmpHtml.replaceAll("fieldname=\"" + allFieldList.get(j) + "\"", newStr);
                } else if (type.equals("4")) {
                    String newStr = "fieldname=\"" + allFieldList.get(j) + "\"  onclick=\"deptinit(['','"
                            + allFieldList.get(j) + "'],false);\" style=\"cursor: pointer;\"";
                    tmpHtml = tmpHtml.replaceAll("fieldname=\"" + allFieldList.get(j) + "\"", newStr);
                } else if (type.equals("5")) {
                    String newStr = "fieldname=\"" + allFieldList.get(j) + "\"  onclick=\"privinit(['','"
                            + allFieldList.get(j) + "'],true);\" style=\"cursor: pointer;\"";
                    tmpHtml = tmpHtml.replaceAll("fieldname=\"" + allFieldList.get(j) + "\"", newStr);
                } else if (type.equals("6")) {
                    String newStr = "fieldname=\"" + allFieldList.get(j) + "\"  onclick=\"privinit(['','"
                            + allFieldList.get(j) + "'],false);\" style=\"cursor: pointer;\"";
                    tmpHtml = tmpHtml.replaceAll("fieldname=\"" + allFieldList.get(j) + "\"", newStr);
                } else if (type.equals("7")) {
                    String newStr = "fieldname=\"" + allFieldList.get(j) + "\"  onclick=\"userinit(['','"
                            + allFieldList.get(j) + "'],true);\" style=\"cursor: pointer;\"";
                    tmpHtml = tmpHtml.replaceAll("fieldname=\"" + allFieldList.get(j) + "\"", newStr);
                } else if (type.equals("8")) {
                    String newStr = "fieldname=\"" + allFieldList.get(j) + "\"  onclick=\"userinit(['','"
                            + allFieldList.get(j) + "'],false);\" style=\"cursor: pointer;\"";
                    tmpHtml = tmpHtml.replaceAll("fieldname=\"" + allFieldList.get(j) + "\"", newStr);
                }
            } else if (fieldXtypeMap.get(allFieldList.get(j)).equals("xworkflow")) {
                String model = flowFormItemLogic.getModeLByFormIdAndFieldLogic(conn, formId,
                        allFieldList.get(j));
                String newStr = "fieldname=\"" + allFieldList.get(j) + "\" onclick=\"selectworkflow('"
                        + allFieldList.get(j) + "','" + model + "');\"";
                tmpHtml = tmpHtml.replaceAll("fieldname=\"" + allFieldList.get(j) + "\"", newStr);
            }
        }
        // 处理只读字段
        fieldList = flowProcessLogic.getReadOnlyField(conn, flowId, prcsId);
        if (fieldList.size() > 0) {
            for (int i = 0; i < fieldList.size(); i++) {
                Map<String, String> tmpMap = new HashMap<String, String>();
                tmpMap = fieldList.get(i);
                if (tmpMap.get("xtype").equals("xworkflow")) {
                    String filename = tmpMap.get("fieldName");
                    tmpHtml = tmpHtml.replaceAll("fieldname=\"" + filename + "\"", "fieldname=\"" + filename
                            + "\" style=\"display: none;\"");
                } else {
                    String filename = tmpMap.get("fieldName");
                    tmpHtml = tmpHtml.replaceAll("fieldname=\"" + filename + "\"", "fieldname=\"" + filename
                            + "\" disabled=\"disabled\" readonly");
                }
            }
        }
        tmpHtml = tmpHtml.replaceAll("#\\[MACRO_FORM_NAME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_FORM_NAME\"></span>");
        tmpHtml = tmpHtml.replaceAll("#\\[MACRO_RUN_NAME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_RUN_NAME\"></span>");
        tmpHtml = tmpHtml.replaceAll("#\\[MACRO_NUMBERING\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_NUMBERING\"></span>");
        tmpHtml = tmpHtml.replaceAll("#\\[MACRO_BEGIN_TIME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_BEGIN_TIME\"></span>");
        tmpHtml = tmpHtml.replaceAll("#\\[MACRO_END_TIME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_END_TIME\"></span>");
        tmpHtml = tmpHtml.replaceAll("#\\[MACRO_RUN_ID\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_RUN_ID\"></span>");
        tmpHtml = tmpHtml.replaceAll("#\\[MACRO_RUN_GUID\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_RUN_GUID\"></span>");
        tmpHtml = tmpHtml.replaceAll("#\\[MACRO_BEGIN_USERNAME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_BEGIN_USERNAME\"></span>");
        tmpHtml = tmpHtml.replaceAll("#\\[MACRO_BEGIN_ACCOUNT_ID\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_BEGIN_ACCOUNT_ID\"></span>");

        htmlCode = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n"
                + "<%@ include file=\"/system/returnapi/api.jsp\" %>\n"
                + "<html>\n"
                + "<head>\n"
                + "<title>\n"
                + "</title>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/ckeditor_standard/ckeditor.js\"></script>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/jquery.json.js\" ></script>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/My97DatePicker/WdatePicker.js\"></script>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/swfupload/swfupload/swfupload.js\"></script>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/swfupload/js/swfupload.queue.js\"></script>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/swfupload/js/fileprogress.js\"></script>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/swfupload/js/handlers.js\"></script>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/fileupload/fileupload.js\"></script>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/fileupload/ajaxfileupload.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"<%=contextPath%>/system/jsall/RegexUtil.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"<%=contextPath%>/system/jsall/sys.js\"></script>\n"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"<%=contextPath%>/system/styles/ztree/zTreeStyle/zTreeStyle.css\"></link>\n"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"<%=contextPath%>/system/jsall/easyui/themes/icon.css\"></link>\n"
                + "<script type=\"text/javascript\" src=\"<%=contextPath%>/system/jsall/ztree/jquery.ztree.core-3.5.js\"></script>\n"
                + " <script type=\"text/javascript\" src=\"<%=contextPath%>/system/jsall/selectorg/showModalDialog.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"<%=contextPath%>/system/jsall/selectorg/selectuser/selectuser.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"<%=contextPath%>/system/jsall/selectorg/selectdept/selectdept.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"<%=contextPath%>/system/jsall/selectorg/selectpriv/selectpriv.js\"></script>\n"
                + "<script type=\"text/javascript\"  charset=\"utf-8\" src=\"<%=contextPath%>/system/jsall/workflow/form.js\"></script>\n"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"<%=stylePath%>/workflow/dowork.css\"></link>\n"
                + "<%String runId=request.getParameter(\"runId\"); %>\n"
                + "<%String runPrcsId=request.getParameter(\"runPrcsId\"); %>\n"
                + "<script type=\"text/javascript\">\n"
                + "var runId=\"<%=runId%>\";\n"
                + "var runPrcsId = \"<%=runPrcsId%>\";\n" + "var flowId=\""
                + flowId
                + "\";\n"
                + "var prcsId=\""
                + prcsId
                + "\";\n"
                + "var tableName=\""
                + leavePath
                + "\";\n"
                + "$(function(){\n"
                + "$(\"table\").addClass(\"table table-bordered table-striped\");\n"
                + "$(\"select\").addClass(\"form-control\");\n"
                + "$(\"input[type='text']\").addClass(\"form-control\");\n"
                + " 			doint();\n"
                + " });\n"
                + "</script>\n"
                + "</head>\n"
                + "<body>\n"
                + "<div id=\"modaldialog\"></div>\n"
                + "<div class=\"easyui-layout\" data-options=\"fit:true\" style=\"overflow:hidden; \">\n"
                + "<div data-options=\"region:'north',height:40\" style=\"overflow: hidden;\" class=\"doworktop\">\n"
                + "<div class=\"title-flow-name\" style=\"width:60%;white-space:nowrap;overflow:hidden;\">\n"
                + "<div class=\"inlineblock\" style=\"height:40px;white-space:nowrap;padding-top: 10px;\" id=\"flowTitle\"   name=\"flowTitle\"  title=\"\"></div>\n"
                + "<input type=\"hidden\" style=\"width:200px;loat: left;\" id=\"flowTitleOld\" name=\"flowTitleOld\" value=\"\">\n"
                + "</div>\n"
                + "<div class=\"title-flow-name\" style=\"width:40%;white-space:nowrap;overflow:hidden;\">\n"
                + "<div id=\"topPrcsName\" name=\"topPrcsName\" style=\"float: right;\"></div>\n"
                + "</div>\n"
                + "</div>\n"
                + "<div data-options=\"region:'center',border:false\" style=\"overflow:auto;\">\n"
                + "<div id=\"formdata\" style=\"width:90%;padding-left:5%;\">\n"
                + tmpHtml
                + publiFileHtml
                + "<div class=\"widget\">\n"
                + "<div class=\"widget-header bordered-bottom bordered-themeprimary\">\n"
                + "<i class=\"widget-icon fa fa-tasks themeprimary\"></i>\n"
                + "<span class=\"widget-caption themeprimary\">会签结果</span>\n"
                + "</div>\n"
                + "<div id=\"allIdea\" name=\"allIdea\"></div>\n"
                + "</div>\n"
                +

                "<div class=\"widget\">\n"
                + "<div class=\"widget-header bordered-bottom bordered-themesecondary\">\n"
                + "<i class=\"widget-icon fa fa-tags themesecondary\"></i>\n"
                + "<span class=\"widget-caption themesecondary\">会签意见</span>\n"
                + "</div>\n"
                + "<table>\n"
                + "<tr>\n"
                + "<td style=\"width: 150px;\">会签意见：</td>\n"
                + "<td>\n"
                + "<select name=\"idea\" id=\"idea\" style=\"width: 200px;\">\n"
                + "<option value=\"1\">同意</option>\n"
                + "<option value=\"0\">不同意</option>\n"
                + "<option value=\"2\">基本同意</option>\n"
                + "</select>\n"
                + "</td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<td>填写意见：</td>\n"
                + "<td> <textarea rows=\"3\" name=\"ideaText\" id=\"ideaText\" class=\"form-control\"></textarea></td>\n"
                + "</tr>\n"
                + " </table>\n"
                + " </div>\n"
                +

                "</div>\n"
                + "</div>\n"
                + "<div data-options=\"region:'south',border:false\" style=\"background-color: silver;text-align:right;overflow: hidden;\">\n"
                + "<div style=\"margin-right: 20px;\">\n"
                + "<span id=\"follow\" name=\"follow\" onclick=\"follow();\" class=\"glyphicon glyphicon-eye-open\" style=\"cursor:pointer;color: rgb(0, 0, 125); text-shadow: none;margin-left: 20px;line-height: 40px;float: left;\"> <b>关注</b></span>\n"
                + "<span id=\"addopuser\" name=\"addopuser\" onclick=\"addopuser();\"class=\"glyphicon glyphicon-edit\" style=\"cursor:pointer;color: rgb(0, 0, 125); text-shadow: none;margin-left: 20px;line-height: 40px;float: left;\" data-container=\"body\" data-toggle=\"popover\" data-placement=\"top\" data-html=\"html\" title=\"加签人员选择\">\n"
                + "<b>加签</b></span>\n"
                + "<span id=\"flowView\" name =\"flowView\" onclick=\"flowView();\" class=\"glyphicon glyphicon-random\" style=\"color: rgb(0, 0, 125); text-shadow: none;margin-left: 20px;line-height: 40px;float: left;cursor:pointer;\"> <b>流程图</b></span>\n"
                + "<button onclick=\"nextprcs();\" class=\"btn btn-primary btn-lg\">提交表单</button>\n"
                + "<button onclick=\"savaform();\" class=\"btn btn-primary\">保存表单</button>\n"
                + "<button onclick=\"doGoBack();\" class=\"btn btn-warning\" id=\"goback\" name=\"goback\" data-container=\"body\" data-toggle=\"popover\" data-placement=\"top\" data-html=\"html\" title=\"回退步骤选择\">回退</button>\n"
                + "<button onclick=\"returnback();\" class=\"btn btn-danger\" >返回</button>\n"
                + "</div>\n"
                + "</div>\n" + "</body>\n" + "</html>\n";
        rootPath = rootPath + leavePath + "/" + prcsId + "/";
        try {
            f = new File(rootPath);
            if (!f.exists()) {
                f.mkdirs();
            }
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(rootPath + fileName),
                    "UTF-8");
            out.write(htmlCode);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
