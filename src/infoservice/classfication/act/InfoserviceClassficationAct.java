package infoservice.classfication.act;

import infoservice.classfication.data.ClassficationTree;
import infoservice.classfication.data.InfoserviceClassfication;
import infoservice.classfication.logic.InfoserviceClassficationLogic;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.AutoConvertUtil;
import com.system.tool.GuId;
import com.system.tool.JsonConfigDefault;
import com.system.tool.SysTool;

public class InfoserviceClassficationAct {
	private AutoConvertUtil autoConvertUtil=new AutoConvertUtil();
	private InfoserviceClassficationLogic logic=new InfoserviceClassficationLogic();
	private BaseDao dao=new BaseDaoImpl();
	
	public String add(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		int result=-1;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			InfoserviceClassfication classfication=
					(InfoserviceClassfication) autoConvertUtil.formToEntity(InfoserviceClassfication.class, request.getParameterMap());
			classfication.setId(GuId.getGuid());
			classfication.setNavigation(classfication.getNavigation()+"."+classfication.getId());
			
			result=logic.add(classfication, conn);
			
			if (result>0) {
				conn.commit();
			}
			
			writer=response.getWriter();
			writer.print(result);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			if (result<=0) {
				dao.rollback(conn);
			}
		    SysTool.closePrintWriter(writer);
		    dao.close(null, null, conn);
		}
		return null;
	}
	
	public String delete(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		int result=-1;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			String navigation=request.getParameter("navigation");
			result=logic.delete(navigation, conn);
			
			if (result>0) {
				conn.commit();
			}
			
			writer=response.getWriter();
			writer.print(result);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			if (result<=0) {
				dao.rollback(conn);
			}
		    SysTool.closePrintWriter(writer);
		    dao.close(null, null, conn);
		}
		return null;
	}
	
	public String update(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		int result=-1;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			conn.setAutoCommit(false);
			
			InfoserviceClassfication classfication=
					(InfoserviceClassfication) autoConvertUtil.formToEntity(InfoserviceClassfication.class, request.getParameterMap());
			result=logic.update(classfication, conn);
			
			if (result>0) {
				conn.commit();
			}
			
			writer=response.getWriter();
			writer.print(result);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			if (result<=0) {
				dao.rollback(conn);
			}
		    SysTool.closePrintWriter(writer);
		    dao.close(null, null, conn);
		}
		return null;
	}
	
	public String search(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			
			List<InfoserviceClassfication> classfications=logic.search(request.getParameterMap(), conn);
			JSONArray classficationArray=null;
			if (classfications!=null) {
				classficationArray=JSONArray.fromObject(classfications,JsonConfigDefault.getJsonConfig());
			}else{
				classficationArray=new JSONArray();
			}
			
			writer=response.getWriter();
			writer.print(classficationArray.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
		    SysTool.closePrintWriter(writer);
		    dao.close(null, null, conn);
		}
		return null;
	}
	
	public String tree(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			
			List<InfoserviceClassfication> classfications=logic.search(request.getParameterMap(), conn);
			
			List<ClassficationTree> tree=new ArrayList<ClassficationTree>();
			ClassficationTree atree=null;
			atree=new ClassficationTree("0", "0", "根目录", "根目录", true, "0", 0, "");
			tree.add(atree);
			
			if (classfications!=null) {
				for (InfoserviceClassfication classfication : classfications) {
					atree=new ClassficationTree(
							classfication.getId(),
							classfication.getParent(),
							classfication.getName(),
							classfication.getName(),
							logic.haveChildren(classfication.getId(), conn), 
							classfication.getNavigation(), 
							classfication.getSort(), 
							classfication.getRemark()
					);
					tree.add(atree);
				}
			}
			
			
			JSONArray treeArray=JSONArray.fromObject(tree,JsonConfigDefault.getJsonConfig());
			
			writer=response.getWriter();
			writer.print(treeArray.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
		    SysTool.closePrintWriter(writer);
		    dao.close(null, null, conn);
		}
		return null;
	}
	public String treeList(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection conn=null;
		PrintWriter writer=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			
			Map<String,String[]> requestMap=new HashMap<String, String[]>(request.getParameterMap());
			if(requestMap.get("parent")==null){
				requestMap.put("parent",new String[]{"0"});
			}
			
			List<InfoserviceClassfication> classfications=logic.search(requestMap, conn);
			
			List<ClassficationTree> tree=new ArrayList<ClassficationTree>();
			ClassficationTree atree=null;
			
			if (classfications!=null) {
				for (InfoserviceClassfication classfication : classfications) {
					boolean haveChildren=logic.haveChildren(classfication.getId(), conn);
					int classIndent=20*(classfication.getNavigation().split("\\.").length-2);
					String iconClass=haveChildren?"icon icon-folder glyphicon glyphicon-folder-close":"icon icon-file glyphicon glyphicon-file";
					String html="<span style='padding-left:"+classIndent+"px;'></span>";
					html+="<span class='"+iconClass+"' aria-hidden='true' value='"+classfication.getId()+"'></span>&nbsp;";
					html+="<span class='text' value='"+classfication.getId()+"'>"+classfication.getName()+"</span>";
					
					atree=new ClassficationTree(
							classfication.getId(),
							classfication.getParent(),
							classfication.getName(),
							html,
							haveChildren, 
							classfication.getNavigation(), 
							classfication.getSort(), 
							classfication.getRemark()
					);
					tree.add(atree);
				}
			}
			
			
			JSONArray treeArray=JSONArray.fromObject(tree,JsonConfigDefault.getJsonConfig());
			
			writer=response.getWriter();
			writer.print(treeArray.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
		    SysTool.closePrintWriter(writer);
		    dao.close(null, null, conn);
		}
		return null;
	}
}
