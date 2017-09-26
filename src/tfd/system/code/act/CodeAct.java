package tfd.system.code.act;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tfd.system.code.data.CodeClass;
import tfd.system.code.logic.CodeLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.tool.GuId;
import com.system.tool.SysTool;

public class CodeAct {
	/**
	 * 得到代码分类
	 * Time : 2015-4-2
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCodeList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String pageStr=request.getParameter("page");
			String pageSizeStr=request.getParameter("rows");
			String sortName=request.getParameter("sort");
			String sortOrder=request.getParameter("order");
			String search = request.getParameter("searchContent");
			if(search == null){
				search = "";
			}
			int page=1;
			int pageSize=10;
			if(!SysTool.isNullorEmpty(pageStr))
			{
				page=Integer.parseInt(pageStr);
			}
			if(!SysTool.isNullorEmpty(pageSizeStr))
			{
				pageSize=Integer.parseInt(pageSizeStr);
			}
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account=(Account)request.getSession().getAttribute("ACCOUNT_ID");
			String orgId = account.getOrgId();
			List<String> pramList = new ArrayList<String>();
			pramList.add(orgId);
			returnData = new CodeLogic().getCodeList(dbConn,pramList,pageSize,page,sortOrder,sortName,search);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 添加代码分类
	 * Time:2015-4-2
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addCode(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String codeId = GuId.getGuid();
			String codePid = request.getParameter("pId");
			if(codePid==null){
				codePid = "0";
			}
			String page = request.getParameter("page");
			String name = request.getParameter("name");
			String value = request.getParameter("value");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			CodeClass c = new CodeClass(codeId,codePid,name,page,value,account.getOrgId());
			returnData = new CodeLogic().addCode(dbConn, c);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据Id查询代码分类
	 * Time:2015-4-2
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCodeById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String codeId = request.getParameter("id");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			returnData = new CodeLogic().getCodeById(dbConn,codeId,account.getOrgId() );
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 修改代码分类
	 * Time:2015-4-2
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateCode(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String codeId = request.getParameter("id");
			String codePid = request.getParameter("pId");
			if(codePid==null){
				codePid = "0";
			}
			String page = request.getParameter("page");
			String name = request.getParameter("name");
			String value = request.getParameter("value");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			CodeClass c = new CodeClass(codeId,codePid,name,page,value,account.getOrgId());
			returnData = new CodeLogic().updateCode(dbConn, c);
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据Id删除代码分类
	 * Time:2015-4-2
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delCode(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		int returnData=0;
		try{
			String codeId = request.getParameter("id");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			returnData = new CodeLogic().delCode(dbConn,codeId,account.getOrgId() );
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据Id查询子集代码分类
	 * Time:2015-4-2
	 * Author:Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void findChild(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try{
			String codeId = request.getParameter("id");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute(SysConst.LOGIN_USER);
			returnData = new CodeLogic().findChild(dbConn,codeId,account.getOrgId() );
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	
	/**
	 * 根据值得到主分类下的子类
	 * Time : 2015-4-6
	 * Author : Yzz
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getCodeAndChildByValue(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		JSONArray returnData = new JSONArray();
		try{
			String value = request.getParameter("value");
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account = (Account)request.getSession().getAttribute("ACCOUNT_ID");
			List<String> code = new CodeLogic().getCodeIdByValue(dbConn,value,account.getOrgId() );
			if(code.isEmpty()){
			}else{
				String child = new CodeLogic().findChild(dbConn, code.get(0), account.getOrgId());
				JSONObject json = new JSONObject();
				json.accumulate("value", code.get(1));
				json.accumulate("rows", child);
				returnData.add(json);
			}
			dbConn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally
		{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
