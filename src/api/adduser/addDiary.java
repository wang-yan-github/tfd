package api.adduser;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import diary.data.Diary;

public class addDiary {

	public void addDiaryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		System.out.println("日志添加开始");
		Connection dbConn = null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			for (int i = 0; i < 1000; i++) {
				String accountId="用户"+i;;
			for (int j = 0; j < 500; j++) {			
				addDiaryAct(dbConn,accountId,j);
			}
			dbConn.commit();
			}
			System.out.println("**********************************************");
			System.out.println("**                                          **");
			System.out.println("**                日志全部创建完成                                             **");
			System.out.println("**                                          **");
			System.out.println("**********************************************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
		}
	}
	
	private static int num=1;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = DbPoolConnection.getInstance().getConnection();
		for (int i = 1; i < 1000; i++) {
			String accountId="用户"+i;
		for (int j = 1; j < 500; j++) {			
			addDiaryAct(conn,accountId,j);
		}
		conn.commit();
		}
	}
	public static void addDiaryAct(Connection conn,String accountId,int j)throws Exception{
		Diary dr=new Diary();
		String content = accountId+"的第"+j+"篇日志日志日志日志";
		dr.setAccountId(accountId);
		dr.setDiaryDatetime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		dr.setDiaryMold(0);
		dr.setDiaryName(accountId+"的日志"+num);
		dr.setDiaryContent(content);
		dr.setSharePriv("");
		dr.setOrgId("E47A163D-C2AD-34D3-2D96-05514D6EB523");
		dr.setDiaryId(GuId.getGuid());
		dr.setDiaryAnnex("");
		dr.setDiaryTitleDatetime("2015-11-08");
		dr.setShareRange(0);
		dr.setLaud("");
		dr.setLaudNum(0);
		String queryStr="INSERT INTO DIARY (ACCOUNT_ID,DIARY_NAME,DIARY_CONTENT,DIARY_ANNEX,DIARY_DATETIME,DIARY_MOLD,DIARY_ID,ORG_ID,DIARY_TITLETIME,SHARE_PRIV,SHARE_RANGE,LAUD,LAUD_NUM)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(queryStr);
		ps.setString(1, dr.getAccountId());
		ps.setString(2, dr.getDiaryName());
		ps.setString(3, dr.getDiaryContent());
		ps.setString(4, dr.getDiaryAnnex());
		ps.setString(5, dr.getDiaryDatetime());
		ps.setInt(6, dr.getDiaryMold());
		ps.setString(7, dr.getDiaryId());
		ps.setString(8,dr.getOrgId());
		ps.setString(9, dr.getDiaryTitleDatetime());
		ps.setString(10, dr.getSharePriv());
		ps.setInt(11, dr.getShareRange());
		ps.setString(12, dr.getLaud());
		ps.setInt(13, dr.getLaudNum());
		ps.executeUpdate();
		ps.close();
		num++;
	}
}
