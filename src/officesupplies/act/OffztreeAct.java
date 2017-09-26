package officesupplies.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import officesupplies.logic.Offztreelogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;

public class OffztreeAct {
	/**
	 * 获取办公用品管理的ztree树
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
			public void getztreeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
				Connection dbConn=null;
				String data=null;
				try {
					dbConn = DbPoolConnection.getInstance().getConnection();
					Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
					Offztreelogic ztreelogic=new Offztreelogic(); 
					data = ztreelogic.getztreeAct(dbConn, account);
					dbConn.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					DbOp dbop = new DbOp();
					dbop.connClose(dbConn);
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print(data);
					response.getWriter().flush();
					}
			}
			/**
			 * 获取办公用品申领的ztree树
			 * Author:Wp
			 * @param request
			 * @param response
			 * @throws Exception
			 */
			public void getapplyztreeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
				Connection dbConn=null;
				String data=null;
				try {
					dbConn = DbPoolConnection.getInstance().getConnection();
					Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
					String deptId=(String) request.getSession().getAttribute("DEPT_ID");
					UserInfoLogic userInfoLogic = new UserInfoLogic();
					String otherDept=userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept();
					Offztreelogic ztreelogic=new Offztreelogic(); 
					data = ztreelogic.getapplyztreeAct(dbConn, account, deptId, otherDept);
					dbConn.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					DbOp dbop = new DbOp();
					dbop.connClose(dbConn);
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print(data);
					response.getWriter().flush();
					}
			}
			/**
			 * 获取所有物品的信息的ztree树
			 * Author:Wp
			 * @param request
			 * @param response
			 * @throws Exception
			 */
			public void getallotztreeAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
				Connection dbConn=null;
				String data=null;
				try {
					dbConn = DbPoolConnection.getInstance().getConnection();
					Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
					Offztreelogic ztreelogic=new Offztreelogic(); 
					data = ztreelogic.getallotztreelogic(dbConn, account);
					dbConn.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					DbOp dbop = new DbOp();
					dbop.connClose(dbConn);
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print(data);
					response.getWriter().flush();
					}
			}
}
