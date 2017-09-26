package officesupplies.act;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import officesupplies.data.Offlibrary;
import officesupplies.logic.OfflibraryLogic;
import tfd.system.unit.account.data.Account;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;

public class OfflibraryAct {
	/**
	 * 添加办公用品库
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addlibraryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn=null;
		int data=0;
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
			Offlibrary library=new Offlibrary();
			OfflibraryLogic librarylogic=new OfflibraryLogic();
			library.setLibraryId(GuId.getGuid());
			library.setBelongDept(request.getParameter("belongDept"));
			library.setLibraryStaff(request.getParameter("libraryStaff"));
			library.setDispatchStaff(request.getParameter("dispatchStaff"));
			library.setLibraryName(request.getParameter("libraryName"));
			library.setDelStatus("0");
			library.setOrgId(account.getOrgId());
			data = librarylogic.addlibrarylogic(dbConn, library);
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
	 * 根据办公用品Id 修改办公用品库信息
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
		public void updatelibraryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			int data=0;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				Offlibrary library=new Offlibrary();
				OfflibraryLogic librarylogic=new OfflibraryLogic();
				library.setLibraryId(request.getParameter("libraryId"));
				library.setBelongDept(request.getParameter("belongDept"));
				library.setLibraryStaff(request.getParameter("libraryStaff"));
				library.setDispatchStaff(request.getParameter("dispatchStaff"));
				library.setLibraryName(request.getParameter("libraryName"));
				if(request.getParameter("sortId")==""){
					library.setSortId("0");
				}else{
				library.setSortId(request.getParameter("sortId"));
				}
				library.setOrgId(account.getOrgId());
				data = librarylogic.updatelibrarylogic(dbConn, library);
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
		 * 根据Id 改变办公用品库的删除状态
		 * Author:Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void dellibraryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			int data=0;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				Offlibrary library=new Offlibrary();
				OfflibraryLogic librarylogic=new OfflibraryLogic();
				library.setLibraryId(request.getParameter("libraryId"));
				library.setDelStatus("1");
				library.setOrgId(account.getOrgId());
				data = librarylogic.dellibrarylogic(dbConn, library);
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
		 * 根据Id查看办公用品库的信息
		 * Author:Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void getIdlibraryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				Offlibrary library=new Offlibrary();
				OfflibraryLogic librarylogic=new OfflibraryLogic();
				library.setLibraryId(request.getParameter("libraryId"));
				library.setOrgId(account.getOrgId());
				data = librarylogic.getIdlibrarylogic(dbConn, library);
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
		 * 获取办公用品库的列表信息
		 * Author:Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void looklibraryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				OfflibraryLogic librarylogic=new OfflibraryLogic();
				data = librarylogic.looklibrarylogic(dbConn, account);
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
		 * 根据approvalStaff获取办公用品库信息
		 * Author:Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
				public void getlibraryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
					Connection dbConn=null;
					String data=null;
					try {
						dbConn = DbPoolConnection.getInstance().getConnection();
						Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
						OfflibraryLogic librarylogic=new OfflibraryLogic();
						data = librarylogic.getlibrarylogic(dbConn, account);
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
				 * 根据DISPATCH_STAFF获取办公用品名称
				 * Author:Wp
				 * @param request
				 * @param response
				 * @throws Exception
				 */
				public void getdislibraryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
					Connection dbConn=null;
					String data=null;
					try {
						dbConn = DbPoolConnection.getInstance().getConnection();
						Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
						OfflibraryLogic librarylogic=new OfflibraryLogic();
						data = librarylogic.getdislibrarylogic(dbConn, account);
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
				 * 添加分类办公用品库
				 * Author:Wp
				 * @param request
				 * @param response
				 * @throws Exception
				 */
		public void addlibclassifyAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			int data=0;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				Offlibrary library=new Offlibrary();
				OfflibraryLogic librarylogic=new OfflibraryLogic();
				library.setLibraryId(GuId.getGuid());
				library.setBelongDept(request.getParameter("belongDept"));
				library.setLibraryStaff(request.getParameter("libraryStaff"));
				library.setDispatchStaff(request.getParameter("dispatchStaff"));
				library.setLibraryName(request.getParameter("libraryName"));
				library.setTopId(request.getParameter("topId"));
				library.setDelStatus("0");
				if(request.getParameter("sortId")==""){
					library.setSortId("0");
				}else{
				library.setSortId(request.getParameter("sortId"));
				}
				library.setOrgId(account.getOrgId());
				data = librarylogic.addlibclassifylogic(dbConn, library);
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
		 * 根据topId 获取分类库列表
		 * Author:Wp
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		public void gettopIdAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
			Connection dbConn=null;
			String data=null;
			try {
				dbConn = DbPoolConnection.getInstance().getConnection();
				Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
				Offlibrary library=new Offlibrary();
				OfflibraryLogic librarylogic=new OfflibraryLogic();
				library.setTopId(request.getParameter("topId"));
				library.setOrgId(account.getOrgId());
				data = librarylogic.gettopIdlogic(dbConn, library);
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
		 * 根据topId获取分类库名称
		 * @param request
		 * @param response
		 * @throws Exception
		 */
				public void gettopIdNameAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
					Connection dbConn=null;
					String data=null;
					try {
						dbConn = DbPoolConnection.getInstance().getConnection();
						Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
						Offlibrary library=new Offlibrary();
						OfflibraryLogic librarylogic=new OfflibraryLogic();
						library.setTopId(request.getParameter("topId"));
						library.setOrgId(account.getOrgId());
						data = librarylogic.gettopIdNamelogic(dbConn, library);
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
				 * 获取所有办公用品库和物品的信息
				 * Author:Wp
				 * @param request
				 * @param response
				 * @throws Exception
				 */
				public void alllibraryAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
					Connection dbConn=null;
					String data=null;
					try {
						dbConn = DbPoolConnection.getInstance().getConnection();
						Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
						OfflibraryLogic librarylogic=new OfflibraryLogic();
						String deptId=(String) request.getSession().getAttribute("DEPT_ID");
						data = librarylogic.alllibrarylogic(dbConn, account,deptId);
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
				 * 获取办公用品库的名称
				 * Author:Wp
				 * @param request
				 * @param response
				 * @throws Exception
				 */
				public void getlibraryNameAct(HttpServletRequest request,HttpServletResponse response)throws Exception{
					Connection dbConn=null;
					String data=null;
					try {
						dbConn = DbPoolConnection.getInstance().getConnection();
						Account account =(Account)request.getSession().getAttribute("ACCOUNT_ID");
						OfflibraryLogic librarylogic=new OfflibraryLogic();
						data = librarylogic.getlibraryNamelogic(dbConn, account);
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
