package meeting.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meeting.data.Boardroom;
import meeting.logic.BoardroomLogic;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.userinfo.logic.UserInfoLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

public class BoardroomAct {

	/**
	 * 添加会议室
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addboardroomAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Boardroom boardroom=new Boardroom();
			BoardroomLogic boardroomLogic=new BoardroomLogic();
			boardroom.setBoardroomId(GuId.getGuid());
			boardroom.setBoardroomName(request.getParameter("boardroomName"));
			boardroom.setBoardroomDepict(request.getParameter("boardroomDepict"));
			boardroom.setBoardroomStaff(request.getParameter("boardroomStaff"));
			boardroom.setDeptPriv(request.getParameter("deptPriv"));
			boardroom.setUserPriv(request.getParameter("userPriv"));
			boardroom.setBoardroomNum(request.getParameter("boardroomNum"));
			boardroom.setAllowTime(request.getParameter("allowTime"));
			boardroom.setEquipment(request.getParameter("equipment"));
			boardroom.setBoardroomAddress(request.getParameter("boardroomAddress"));
			boardroom.setBoardroomSystem(request.getParameter("boardroomSystem"));
			boardroom.setOrgId(account.getOrgId());
			data = boardroomLogic.addboardroomlogic(dbConn, boardroom);
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
	 * 根据Id删除会议室信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delboardroomAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			BoardroomLogic boardroomLogic=new BoardroomLogic();
			String orgId=account.getOrgId();
			String boardroomId=request.getParameter("boardroomId");
			data = boardroomLogic.delboardroomlogic(dbConn, boardroomId,orgId);
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
	 * 修改会议室信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateboardroomAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Boardroom boardroom=new Boardroom();
			BoardroomLogic boardroomLogic=new BoardroomLogic();
			boardroom.setBoardroomId(request.getParameter("boardroomId"));
			boardroom.setBoardroomName(request.getParameter("boardroomName"));
			boardroom.setBoardroomDepict(request.getParameter("boardroomDepict"));
			boardroom.setBoardroomStaff(request.getParameter("boardroomStaff"));
			boardroom.setDeptPriv(request.getParameter("deptPriv"));
			boardroom.setUserPriv(request.getParameter("userPriv"));
			boardroom.setBoardroomNum(request.getParameter("boardroomNum"));
			boardroom.setAllowTime(request.getParameter("allowTime"));
			boardroom.setEquipment(request.getParameter("equipment"));
			boardroom.setBoardroomAddress(request.getParameter("boardroomAddress"));
			boardroom.setBoardroomSystem(request.getParameter("boardroomSystem"));
			boardroom.setOrgId(account.getOrgId());
			data = boardroomLogic.updateboardroomlogic(dbConn, boardroom);
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
	 * 根据ID查询会议室信息
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getIdboardroomAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			BoardroomLogic boardroomLogic=new BoardroomLogic();
			String boardroomId=request.getParameter("boardroomId");
			String orgId=account.getOrgId();
			data = boardroomLogic.getIdboardroomAct(dbConn, boardroomId, orgId);
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
	 * 查询会议室信息列表
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void selectboardroomAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			BoardroomLogic boardroomLogic=new BoardroomLogic();
			String orgId=account.getOrgId();
			data = boardroomLogic.selectboardroomlogic(dbConn, orgId);
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
	 * 根据用户的权限查询会议室列表
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getaccountAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		String data=null;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			BoardroomLogic boardroomLogic=new BoardroomLogic();
			UserInfoLogic userInfoLogic = new UserInfoLogic();
			Account account= (Account)request.getSession().getAttribute("ACCOUNT_ID");
			String accountId=","+account.getAccountId()+",";
			String orgId=account.getOrgId();
			String deptId=","+(String) request.getSession().getAttribute("DEPT_ID")+",";
			String otherdept=","+userInfoLogic.getUserInfoByAccountId(dbConn, account.getAccountId()).getOtherDept()+",";
			data = boardroomLogic.getaccountlogic(dbConn, accountId, orgId, deptId, otherdept);
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
