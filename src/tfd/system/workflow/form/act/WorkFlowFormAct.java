package tfd.system.workflow.form.act;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tfd.system.unit.account.data.Account;
import tfd.system.workflow.flowformitem.data.FlowFormItem;
import tfd.system.workflow.flowformitem.logic.FLowFormItemLogic;
import tfd.system.workflow.form.data.WorkFlowForm;
import tfd.system.workflow.form.logic.WorkFlowFormLogic;
import tfd.system.workflow.form.tool.WorkFlowFormPrint;
import tfd.system.workflow.form.tool.WorkFlowFormTool;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.global.SysPropKey;
import com.system.global.SysProps;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class WorkFlowFormAct {
    // 获取表单树
    public void getWorkFlowFormTypeTree(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String returnData = "";
        Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
        Connection dbConn = null;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            returnData = workFlowFormLogic.getFlowFormTreeJson(dbConn, account);
            dbConn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
        }
    }

    // 新建工作表单信息
    public String AddWorkFlowFormAct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        WorkFlowForm workFlowForm = new WorkFlowForm();
        String formId = GuId.getGuid();
        workFlowForm.setFormId(formId);
        workFlowForm.setFormName(request.getParameter("formName"));
        workFlowForm.setFormTableName(request.getParameter("formTableName"));
        Account account = (Account) request.getSession().getAttribute(SysConst.LOGIN_USER);
        workFlowForm.setFormCreateUser(account.getAccountId());
        workFlowForm.setFormDeptId(request.getParameter("formDeptId"));
        workFlowForm.setFormType(request.getParameter("formType"));
        workFlowForm.setWorkFlowTypeId(request.getParameter("workFlowTypeId"));
        workFlowForm.setFormCreatTime(new Date());
        workFlowForm.setFormType("workflow");
        workFlowForm.setOrgId(account.getOrgId());
        Connection dbConn = null;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            workFlowFormLogic.addWorkFlowFormLogic(dbConn, workFlowForm);
            dbConn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
        }
        request.setAttribute(SysConst.RET_STATE, SysConst.RETURN_OK);
        return "/system/workflow/form/manage.jsp?formId=" + formId;
    }

    // 更新工作表单信息
    public String updateWorkFlowFormAct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String formId = request.getParameter("formId");
        String formName = request.getParameter("formName");
        String formTableName = request.getParameter("formTableName");
        String formDeptId = request.getParameter("formDeptId");
        String formType = request.getParameter("formType");
        String formCreateUser = request.getParameter("formCreateUser");
        String workFlowTypeId = request.getParameter("workFlowTypeId");
        Connection dbConn = null;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            WorkFlowForm workFlowForm = new WorkFlowForm();
            workFlowForm.setFormId(formId);
            workFlowForm.setFormName(formName);
            workFlowForm.setFormTableName(formTableName);
            workFlowForm.setFormDeptId(formDeptId);
            workFlowForm.setFormCreateUser(formCreateUser);
            workFlowForm.setFormType(formType);
            workFlowForm.setWorkFlowTypeId(workFlowTypeId);
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            workFlowFormLogic.updateWorkFlowFormLogic(dbConn, workFlowForm);
            // 生成空预览表单
            WorkFlowFormPrint workFlowFormPrint = new WorkFlowFormPrint();
            workFlowFormPrint.createPreviewFile(dbConn, formId, "表单暂时没有设计");
            dbConn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
        }
        return "/system/workflow/form/manage.jsp?formId=" + formId;
    }

    public void getWorkFlowFormByFormIdAct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            String formId = request.getParameter("formId");
            returnData = workFlowFormLogic.getWorkFlowFormByFormId(dbConn, formId);
            dbConn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
        }
    }

    // 新建工作流表
    public void greateFormTableAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        PrintWriter writer = null;
        String result = "false";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String htmlCode = request.getParameter("htmlCode");
            String formId = request.getParameter("formId");

            // 保存HTMLCODE
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            workFlowFormLogic.savaFormCode(dbConn, formId, htmlCode);
            // 生成流表单预览
            WorkFlowFormPrint workFlowFormPrint = new WorkFlowFormPrint();
            workFlowFormPrint.createPreviewFile(dbConn, formId, htmlCode);
            // 生成工作流表单数据查看
            workFlowFormPrint.createReadFile(dbConn, formId, htmlCode);
            // 保存FORMITEM
            List<FlowFormItem> itemList = new ArrayList<FlowFormItem>();
            WorkFlowFormTool workFlowFormTool = new WorkFlowFormTool();
            itemList = workFlowFormTool.getFlowFormItemList(htmlCode);
            FLowFormItemLogic flowFormItemLogic = new FLowFormItemLogic();
            flowFormItemLogic.insterFlowFormItemLogic(dbConn, formId, itemList);
            // 生成数据库表结构
            workFlowFormTool.createFormDateLogic(dbConn, formId, itemList);
            dbConn.commit();
            result = "true";
        } catch (Exception ex) {
            if (dbConn != null) {
                dbConn.rollback();
            }
            throw ex;
        } finally {
            writer = response.getWriter();
            writer.print(result);
            SysTool.closePrintWriter(writer);

            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
        }
    }

    public void getFormCodeByFormIdAct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String formId = request.getParameter("formId");
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            returnData = workFlowFormLogic.getFormCode(dbConn, formId);
            dbConn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    public void getFormStyleByFormIdAct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String formId = request.getParameter("formId");
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            returnData = workFlowFormLogic.getFormStyle(dbConn, formId);
            dbConn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    public void updateFormStyleByFormIdAct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection dbConn = null;
        int returnData = 0;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String formId = request.getParameter("formId");
            String Style = request.getParameter("css");
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            returnData = workFlowFormLogic.updateFormStyle(dbConn, formId, Style);
            dbConn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    // 获取分类下表单列表
    public void getFormListByWorkFlowTypeIdAct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("rows");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        int page = 1;
        int pageSize = 10;
        if (!SysTool.isNullorEmpty(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if (!SysTool.isNullorEmpty(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
        List<String> pramList = new ArrayList<String>();
        pramList.add(request.getParameter("workFlowTypeId"));
        pramList.add(account.getAccountId());
        pramList.add(account.getOrgId());
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            returnData = workFlowFormLogic.getFormListByWorkFlowTypeIdLogic(dbConn, pramList, pageSize, page,
                    sortOrder, sortName);
            dbConn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    // 获取表单JS脚本
    public void getFormScriptAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String formId = request.getParameter("formId");
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            returnData = workFlowFormLogic.getFormScriptLogic(dbConn, formId);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    // 更新表单JS脚本
    public void updateFormsScriptAct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection dbConn = null;
        int returnData = 0;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String formId = request.getParameter("formId");
            String script = request.getParameter("script");
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            returnData = workFlowFormLogic.updateFormScriptLogic(dbConn, script, formId);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    // 物理删除工作流表单，连同数据表一起删除
    public void delFormByFormIdAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection dbConn = null;
        int returnData = 0;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            String formId = request.getParameter("formId");
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            returnData = workFlowFormLogic.delFormByFormIdLogic(dbConn, formId);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }

    // 工作流表单导入
    public String importFormAct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String formId = request.getParameter("formId");
        String leavePath = request.getParameter("leavePath");
        InputStream in = null;
        StringBuffer sb = new StringBuffer();
        String str = "";
        Connection dbConn = null;
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            int maxSize = SysProps.getInt(SysPropKey.MAX_UPLOAD_FILE_SIZE);
            String tmpPath = SysProps.getUploadCatchPath();
            DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4, new File(tmpPath));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setSizeMax(1024 * 1024 * maxSize);
            List<FileItem> fileItems = upload.parseRequest(request);
            Iterator<FileItem> iter = fileItems.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    in = item.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
                    while ((str = reader.readLine()) != null) {
                        sb.append(str).append("\n");
                    }
                }
            }
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            workFlowFormLogic.updateFormCodeLogic(dbConn, sb.toString(), formId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
        }
        request.setAttribute(SysConst.RET_STATE, SysConst.RETURN_OK);
        return "/system/workflow/form/design/designform.jsp?formId=" + formId + "&leavePath=" + leavePath;

    }

    // 按表单名称查询
    public void getFormListByFormNameAct(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("rows");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        int page = 1;
        int pageSize = 10;
        if (!SysTool.isNullorEmpty(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if (!SysTool.isNullorEmpty(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        Account account = (Account) request.getSession().getAttribute("ACCOUNT_ID");
        List<String> pramList = new ArrayList<String>();
        pramList.add("%" + request.getParameter("formName") + "%");
        pramList.add(account.getAccountId());
        pramList.add(account.getOrgId());
        Connection dbConn = null;
        String returnData = "";
        try {
            dbConn = DbPoolConnection.getInstance().getConnection();
            WorkFlowFormLogic workFlowFormLogic = new WorkFlowFormLogic();
            returnData = workFlowFormLogic.getFormListByFormNameLogic(dbConn, pramList, pageSize, page,
                    sortOrder, sortName);
            dbConn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbOp dbop = new DbOp();
            dbop.connClose(dbConn);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(returnData);
            response.getWriter().flush();
        }
    }
}
