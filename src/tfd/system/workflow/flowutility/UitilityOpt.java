package tfd.system.workflow.flowutility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UitilityOpt {
//逻辑删除
	public int delWorkFlowFlase(Connection conn,String runId) throws SQLException
	{
		String queryStr="UPDATE FLOW_RUN SET DEL_FLAG=? WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, "1");
		ps.setString(2, runId);
		ps.executeUpdate();
		queryStr="UPDATE WORK_LIST SET DEL_FLAG=? WHERE RUN_ID=?";
		ps.setString(1, "1");
		ps.setString(2, runId);
		int i=ps.executeUpdate();
		ps.close();
		return i;
	}
//物理删除
	public void delWorkFlowTrue(Connection conn,String runId) throws SQLException
	{
		String queryStr="DELETE FROM FLOW_RUN WHERE RUN_ID=?";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ps.executeUpdate();
		queryStr="DELETE FROM WORK_LIST WHERE RUN_ID=?";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ps.executeUpdate();
		queryStr="DELETE FROM FLOW_RUN_PRCS WHERE RUN_ID=?";
		ps = conn.prepareStatement(queryStr);
		ps.setString(1, runId);
		ps.executeUpdate();
		ps.close();
	}
	public void delWorkFlowLogic(Connection conn,String runId,boolean flag) throws SQLException
	{
		if(flag)
		{
			this.delWorkFlowTrue(conn, runId);
		}else
		{
			this.delWorkFlowFlase(conn, runId);
		}
	}
}
