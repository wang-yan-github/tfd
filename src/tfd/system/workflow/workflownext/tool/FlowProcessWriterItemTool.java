package tfd.system.workflow.workflownext.tool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;

public class FlowProcessWriterItemTool {
	public BaseDao dao=new BaseDaoImpl();
//获取FlowProcess可写字段
	public String[] getFlowProcessWriterItemTool(Connection conn,String flowId,int prcsId) throws SQLException{
		PreparedStatement ps =null;
		ResultSet rs=null;
		String[] writerField=null;
		String query="SELECT WRITER_FIELD FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
		ps=conn.prepareStatement(query);
		ps.setString(1, flowId);
		ps.setInt(2, prcsId);
		rs=ps.executeQuery();
		if(rs.next())
		{
			if(!SysTool.isNullorEmpty(rs.getString("WRITER_FIELD")))
			{
			writerField=rs.getString("WRITER_FIELD").split(",");
			}
		}
		rs.close();
		ps.close();
		return writerField;
	}
//获取列表子表字段
	public String getXlistFieldNameTool(Connection conn,String flowId,int prcsId) throws Exception{
		String returnData="";
		
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			String query="SELECT CHILD_TABLE_WRITER_FIELD FROM FLOW_PROCESS WHERE FLOW_ID=? AND PRCS_ID=?";
			ps=conn.prepareStatement(query);
			ps.setString(1, flowId);
			ps.setInt(2, prcsId);
			
			rs=ps.executeQuery();
			if(rs.next()){
				if(!SysTool.isNullorEmpty(rs.getString("CHILD_TABLE_WRITER_FIELD"))){
					returnData=rs.getString("CHILD_TABLE_WRITER_FIELD");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, ps, null);
		}
		return returnData;
		
	}
}
