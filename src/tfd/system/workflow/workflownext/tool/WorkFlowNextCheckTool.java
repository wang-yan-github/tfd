package tfd.system.workflow.workflownext.tool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.workflow.flowprocess.logic.FlowProcessLogic;
import tfd.system.workflow.getflowdata.logic.WorkFlowDataLogic;
import bsh.EvalError;
import bsh.Interpreter;

import com.system.tool.SysTool;

public class WorkFlowNextCheckTool {
    public String workFlowNextCheckLogic(Connection conn, String runId, int prcsId, String flowId,
            String tableName) throws SQLException, EvalError {
        // 获取流程表单具体数据
        String toNextPrcs = "";
        FlowProcessLogic flowProcessLogic = new FlowProcessLogic();
        String formId = flowProcessLogic.getFormIdbyFlowIdLogic(conn, flowId, prcsId);
        Map<String, String> formData = new HashMap<String, String>();
        WorkFlowDataLogic workFlowData = new WorkFlowDataLogic();
        formData = workFlowData.getFormDateMapLogic(conn, flowId, runId, formId, tableName);
        // 获取流程相关信息
        // 获取步骤条件
        String prcsConditionStr = flowProcessLogic.getPrcsConditionLoigc(conn, flowId, prcsId);
        if (SysTool.isNullorEmpty(prcsConditionStr)) {
            toNextPrcs = toNextPrcs + flowProcessLogic.getFlorProcessNextLogic(conn, flowId, prcsId);
            return toNextPrcs;
        }
        JSONArray conditionJsonArr = JSONArray.fromObject(prcsConditionStr);
        List<PrcsCondition> prcsConditionList = new ArrayList<PrcsCondition>();// 所有下一步的条件列表
        JSONObject conditionJsonObj = new JSONObject();
        for (int i = 0; conditionJsonArr.size() > i; i++) {
            conditionJsonObj = (JSONObject) conditionJsonArr.get(i);
            if (SysTool.isNullorEmpty(conditionJsonObj.getString("exp"))) {
                toNextPrcs = toNextPrcs + conditionJsonObj.getString("prcsTo") + ",";
                continue;
            }
            PrcsCondition prcsCondition = new PrcsCondition();
            prcsCondition.setPrcsTo(conditionJsonObj.getString("prcsTo"));
            prcsCondition.setCondition(conditionJsonObj.getString("condition"));
            prcsCondition.setExp(conditionJsonObj.getString("exp"));
            prcsConditionList.add(prcsCondition);
        }
        // 获取NEXTPRCS每一个步骤的跳转条件列表
        if (prcsConditionList.size() <= 0) {
            // toNextPrcs=toNextPrcs+flowProcessLogic.getFlorProcessNextLogic(conn,
            // flowId, prcsId);
            return toNextPrcs;
        }
        for (int i = 0; prcsConditionList.size() > i; i++) {
            List<Boolean> logicCpuList = new ArrayList<Boolean>();
            String prcsTo = prcsConditionList.get(i).getPrcsTo();
            String exp = prcsConditionList.get(i).getExp();
            List<Condition> conditionList = new ArrayList<Condition>();
            JSONArray conditionArr = JSONArray.fromObject(prcsConditionList.get(i).getCondition());
            for (int j = 0; conditionArr.size() > j; j++) {
                conditionJsonObj = (JSONObject) conditionArr.get(j);
                Condition condition = new Condition();
                condition.setFieldName(conditionJsonObj.getString("fieldName"));
                condition.setName(conditionJsonObj.getString("name"));
                condition.setValue(conditionJsonObj.getString("value"));
                condition.setOper(conditionJsonObj.getString("oper"));
                conditionList.add(condition);
            }
            // 对每一步中的每一个条件进行运算
            if (conditionList.size() > 0) {
                for (int k = 0; conditionList.size() > k; k++) {
                    Condition cdt = new Condition();
                    cdt = conditionList.get(k);
                    String fieldName = cdt.getFieldName();
                    String value = cdt.getValue();
                    String oper = cdt.getOper();
                    String leftValue = formData.get(fieldName);
                    logicCPU(leftValue, value, oper);
                    logicCpuList.add(logicCPU(leftValue, value, oper));
                }
            }
            int no = 0;
            exp = exp.toUpperCase();
            exp = exp.replaceAll("AND", "&&");
            exp = exp.replaceAll("OR", "||");
            Pattern p = Pattern.compile("\\{(.+?)\\}");
            Matcher m = p.matcher(exp);
            List<String> expList = new ArrayList<String>();
            while (m.find()) {
                expList.add(m.group());
            }
            if (expList.size() > 0) {
                for (int e = 0; expList.size() > e; e++) {
                    no = Integer.parseInt(expList.get(e).replace("{", "").replace("}", ""));

                    exp = exp.replaceAll("\\{" + no + "\\}", logicCpuList.get(no - 1).toString());
                }
            }
            Interpreter goEvel = new Interpreter();
            String pass = goEvel.eval(exp).toString();
            if (pass.equals("true")) {
                toNextPrcs = toNextPrcs + prcsTo + ",";
            }
        }
        if (toNextPrcs.endsWith(",")) {
            toNextPrcs = toNextPrcs.substring(0, toNextPrcs.length() - 1);
        }
        return toNextPrcs;
    }

    // 逻辑运算单元
    public boolean logicCPU(String leftValue, String rightValue, String oper) {
        leftValue = leftValue == null ? "" : leftValue;
        rightValue = rightValue == null ? "" : rightValue;
        oper = oper == null ? "" : oper;

        boolean returnData = false;
        if (oper.equals("等于")) {
            if (leftValue.equals(rightValue)) {
                returnData = true;
            }
        } else if (oper.equals("不等于")) {
            if (!leftValue.equals(rightValue)) {
                returnData = true;
            }

        } else if (oper.equals("大于")) {
            if (leftValue.compareTo(rightValue) > 0) {
                returnData = true;
            } else {
                returnData = false;
            }

        } else if (oper.equals("大于等于")) {
            if (leftValue.compareTo(rightValue) >= 0) {
                returnData = true;
            } else {
                returnData = false;
            }

        } else if (oper.equals("小于")) {
            if (leftValue.compareTo(rightValue) < 0) {
                returnData = true;
            } else {
                returnData = false;
            }
        } else if (oper.equals("小于等于")) {
            if (leftValue.compareTo(rightValue) <= 0) {
                returnData = true;
            } else {
                returnData = false;
            }

        } else if (oper.equals("以字符开头")) {
            if (leftValue.startsWith("rightValue")) {
                returnData = true;
            } else {
                returnData = false;
            }

        } else if (oper.equals("以字符结尾")) {
            if (leftValue.endsWith("rightValue")) {
                returnData = true;
            } else {
                returnData = false;
            }

        } else if (oper.equals("包含")) {
            if (leftValue.indexOf("rightValue") > 0) {
                returnData = true;
            } else {
                returnData = false;
            }

        } else if (oper.equals("不包含")) {
            if (leftValue.indexOf("rightValue") <= 0) {
                returnData = true;
            } else {
                returnData = false;
            }

        }
        return returnData;
    }
}
