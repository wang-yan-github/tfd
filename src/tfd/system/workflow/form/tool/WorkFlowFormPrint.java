package tfd.system.workflow.form.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tfd.system.workflow.form.logic.WorkFlowFormLogic;

import com.system.global.SysProps;

public class WorkFlowFormPrint {
    // 生成查看模版
    public void createReadFile(Connection conn, String formId, String htmlCode) throws SQLException {
        File f = null;
        WorkFlowFormLogic workFLowFormLogic = new WorkFlowFormLogic();
        String rootPath = SysProps.getRootPath() + "/webapps/tfd/system/workflow/dowork/";
        String leavePath = workFLowFormLogic.getFormTableNameByFormIdLogic(conn, formId);
        List<String> reList = new ArrayList<String>();
        reList.add("<input(.*?)/>+");// input 标签处理正则
        reList.add("<select(.*?)></select>");// >select标签处理正则
        reList.add("<textarea(.*?)</textarea>");// textarea标签处理正则
        reList.add("<img(.*?)/>+");// 列表标签处理正则
        for (int i = 0; i < reList.size(); i++) {
            Pattern p = Pattern.compile(reList.get(i).toString());
            Matcher m = p.matcher(htmlCode);
            while (m.find()) {
                String name = "";
                String fieldnamere = "fieldname=\"(.*?)\"";
                Pattern pfieldnamep = Pattern.compile(fieldnamere);
                Matcher pfieldnamem = pfieldnamep.matcher(m.group().toString());
                while (pfieldnamem.find()) {
                    name = pfieldnamem.group().replace("\"", "").split("=")[1].toString();
                }
                String xtype = "";
                String xtypere = "xtype=\"(.*?)\"";
                Pattern xtypep = Pattern.compile(xtypere);
                Matcher xtypem = xtypep.matcher(m.group().toString());
                while (xtypem.find()) {
                    xtype = xtypem.group().replace("\"", "").split("=")[1].toString();
                }
                String module = "";
                String modulere = "module=\"(.*?)\"";
                Pattern modulep = Pattern.compile(modulere);
                Matcher modulem = modulep.matcher(m.group().toString());
                while (modulem.find()) {
                    module = modulem.group().replace("\"", "").split("=")[1].toString();
                }
                if (xtype.equals("xcheckbox")) {
                    htmlCode = htmlCode.replace(m.group(),
                            "<input disabled=\"disabled\" type=\"checkbox\" id=\"" + name + "\" xtype=\""
                                    + xtype + "\" module=\"" + module + "\" name=\"" + name + "\"/>");
                } else if (xtype.equals("xradio")) {
                    htmlCode = htmlCode.replace(m.group(),
                            "<input disabled=\"disabled\"  type=\"radio\" id=\"" + name + "\" xtype=\""
                                    + xtype + "\" module=\"" + module + "\" name=\"" + name + "\"/>");
                } else if (xtype.equals("xlist")) {
                    // htmlCode=htmlCode.replace(m.group(),
                    // "<div id=\""+name+"\" xtype=\""+xtype+"\" module=\""+module+"\" name=\""+name+"\"></div>");
                } else {
                    htmlCode = htmlCode.replace(m.group(), "<div id=\"" + name + "\" xtype=\"" + xtype
                            + "\" module=\"" + module + "\" name=\"" + name + "\"></div>");
                }
            }
        }
        htmlCode = htmlCode.replaceAll("#\\[MACRO_FORM_NAME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_FORM_NAME\"></span>");
        htmlCode = htmlCode.replaceAll("#\\[MACRO_RUN_NAME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_RUN_NAME\"></span>");
        htmlCode = htmlCode.replaceAll("#\\[MACRO_NUMBERING\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_NUMBERING\"></span>");
        htmlCode = htmlCode.replaceAll("#\\[MACRO_BEGIN_TIME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_BEGIN_TIME\"></span>");
        htmlCode = htmlCode.replaceAll("#\\[MACRO_END_TIME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_END_TIME\"></span>");
        htmlCode = htmlCode.replaceAll("#\\[MACRO_RUN_ID\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_RUN_ID\"></span>");
        htmlCode = htmlCode.replaceAll("#\\[MACRO_RUN_GUID\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_RUN_GUID\"></span>");
        htmlCode = htmlCode.replaceAll("#\\[MACRO_BEGIN_USERNAME\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_BEGIN_USERNAME\"></span>");
        htmlCode = htmlCode.replaceAll("#\\[MACRO_BEGIN_ACCOUNT_ID\\]",
                "<span xtype=\"macrotag\" value=\"MACRO_BEGIN_ACCOUNT_ID\"></span>");
        String printCode = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n"
                + "<%@ include file=\"/system/returnapi/api.jsp\" %>\n"
                + "<html>\n"
                + "<head>\n"
                + "<title>工作流表单预览\n"
                + "</title>\n"
                + "<script src=\"<%=contextPath%>/system/jsall/workflow/print.js\" type=\"text/javascript\"></script>\n"
                + "<script src=\"<%=contextPath%>/system/jsall/printtool/printtooljsall.js\" type=\"text/javascript\"></script>\n"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"<%=stylePath%>/workflow/dowork.css\"></link>\n"
                + "<%String runId=request.getParameter(\"runId\"); %>\n"
                + "<script type=\"text/javascript\">\n"
                + "var runId=\"<%=runId%>\"\n"
                + "$(function(){\n"
                + "var binfo = getBrowerinfo();\n"
                + "if(binfo==\"firefox\"||binfo==\"chrome\"||binfo==\"safari\")\n"
                + "{\n"
                + "$(\"#print1\").hide();\n"
                + "$(\"#print2\").hide();\n"
                + "}\n"
                + "			getPrintData(runId);\n"
                + "			createXlist(runId);\n"
                + "			createXifream();\n"
                + "			getIdea();\n"
                + "			doXmacrotag();\n"
                + " });\n"
                + "</script>\n"
                + "<style media=\"print\">\n"
                + ".noprint { display : none; }\n"
                + "</style>\n "
                + "</head>"
                + "<body>"
                + htmlCode
                + "<div align=\"center\">\n"
                + "<div class=\"widget\" style=\"width: 90%;\"> \n"
                + "<div class=\"widget-header bordered-bottom bordered-themesecondary\">\n"
                + "<i class=\"widget-icon fa fa-tags themesecondary\"></i>\n"
                + "<span class=\"widget-caption themesecondary\">公共附件</span>\n"
                + "</div>\n"
                + "<table class=\"table table-striped  table-condensed\" >\n"
                + "<tr>\n"
                + "<td width=\"150px;\">附件列表：</td>\n"
                + "<td><div id=\"publicFiles\" name=\"publicFiles\"></div></td>\n"
                + "</tr>\n"
                + "</table>\n"
                + "</div>\n"
                + "</div>\n"
                + "<div align=\"center\"><div class=\"widget\" style=\"width: 90%;\">\n"
                + "<div class=\"widget-header bordered-bottom bordered-themeprimary\">\n"
                + "<i class=\"widget-icon fa fa-tasks themeprimary\"></i>\n"
                + "<span class=\"widget-caption themeprimary\">会签结果</span>\n"
                + "</div>\n"
                + "<div id=\"allIdea\" name=\"allIdea\" align=\"left\"></div>\n"
                + "</div></div>\n"
                + "<div align=\"right\" class=\"noprint\" style=\"background-color: silver;position:fixed;width:100%;bottom:0px;\">\n"
                + "<button id=\"print1\" type=\"button\" onclick=\"printer(1);\" class=\"btn btn-info btn-lg\">打印设置</button>\n"
                + "<button id=\"print2\" type=\"button\" onclick=\"printer(2);\" class=\"btn btn-info btn-lg\">打印预览</button>\n"
                + "<button id=\"print3\" type=\"button\" onclick=\"printer(3);\" class=\"btn btn-info btn-lg\">直接打印</button>\n"
                + "<button id=\"print4\" type=\"button\" onclick=\"printer(4);\" class=\"btn btn-info btn-lg\">关闭窗口</button>\n"
                + "<object id=\"printWB\"  style=\"dispaly:none\" classid=\"clsid:8856F961-340A-11D0-A96B-00C04FD705A2\"  height=\"0\" width=\"0\"></object>\n"
                + "</div>\n" + "</body>\n" + "</html>\n";

        rootPath = rootPath + leavePath + "/";
        htmlCode = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n"
                + "<%@ include file=\"/system/returnapi/api.jsp\" %>\n"
                + "<html>\n"
                + "<head>\n"
                + "<title>工作流表单预览\n"
                + "</title>\n"
                + "<script src=\"<%=contextPath%>/system/jsall/workflow/print.js\" type=\"text/javascript\"></script>\n"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"<%=stylePath%>/workflow/dowork.css\"></link>\n"
                + "<%String runId=request.getParameter(\"runId\"); %>\n"
                + "<script type=\"text/javascript\">\n"
                + "var runId=\"<%=runId%>\"\n"
                + "$(function(){\n"
                + "			getPrintData(runId);\n"
                + "			createXlist(runId);\n"
                + "			createXifream();\n"
                + "			getIdea();\n" + "			doXmacrotag();\n" + " });\n" + "</script>\n" + "</head>" + "<body>"
                + htmlCode
                +

                "<div align=\"center\">\n"
                + "<div class=\"widget\" style=\"width: 90%;\"> \n"
                + "<div class=\"widget-header bordered-bottom bordered-themesecondary\">\n"
                + "<i class=\"widget-icon fa fa-tags themesecondary\"></i>\n"
                + "<span class=\"widget-caption themesecondary\">公共附件</span>\n"
                + "</div>\n"
                + "<table class=\"table table-striped  table-condensed\" >\n"
                + "<tr>\n"
                + "<td width=\"150px;\">附件列表：</td>\n"
                + "<td><div id=\"publicFiles\" name=\"publicFiles\"></div></td>\n"
                + "</tr>\n"
                + "</table>\n"
                + "</div>\n"
                + "</div>\n"
                +

                "<div align=\"center\"><div class=\"widget\" style=\"width: 90%;\">\n"
                + "<div class=\"widget-header bordered-bottom bordered-themeprimary\">\n"
                + "<i class=\"widget-icon fa fa-tasks themeprimary\"></i>\n"
                + "<span class=\"widget-caption themeprimary\">会签结果</span>\n"
                + "</div>\n"
                + "<div id=\"allIdea\" name=\"allIdea\" align=\"left\"></div>\n"
                + "</div></div>\n"
                +

                "<div align=\"center\" style=\"background-color: silver;\">\n"
                + "<button type=\"button\" onclick=\"openprintwindow(runId);\" class=\"btn btn-info btn-lg\">打印</button>"
                + "</div>\n" + "</body>\n" + "</html>\n";
        try {
            f = new File(rootPath + "print/");
            if (!f.exists()) {
                f.mkdirs();
            }
            OutputStreamWriter readout = new OutputStreamWriter(new FileOutputStream(rootPath
                    + "print/index.jsp"), "UTF-8");
            readout.write(htmlCode);
            readout.flush();
            readout.close();
            OutputStreamWriter printout = new OutputStreamWriter(new FileOutputStream(rootPath
                    + "print/print.jsp"), "UTF-8");
            printout.write(printCode);
            printout.flush();
            printout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 生成表单预览
    public void createPreviewFile(Connection conn, String formId, String htmlCode) throws SQLException {
        WorkFlowFormLogic workFLowFormLogic = new WorkFlowFormLogic();
        String rootPath = SysProps.getRootPath() + "/webapps/tfd/system/workflow/dowork/";
        String leavePath = workFLowFormLogic.getFormTableNameByFormIdLogic(conn, formId).toLowerCase();
        File f = null;
        rootPath = rootPath + leavePath + "/";
        htmlCode = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n"
                + "<%@ include file=\"/system/returnapi/api.jsp\" %>\n"
                + "<html>\n"
                + "<head>\n"
                + "<title>工作流表单预览\n"
                + "</title>\n"
                + "</head>"
                + "</body>"
                + htmlCode
                + "</body>\n"
                + "</html>\n";
        try {
            f = new File(rootPath);
            if (!f.exists()) {
                f.mkdir();
            }
            f = new File(rootPath + "preview/");
            if (!f.exists()) {
                f.mkdir();
            }
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(rootPath
                    + "preview/index.jsp"), "UTF-8");
            out.write(htmlCode);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
