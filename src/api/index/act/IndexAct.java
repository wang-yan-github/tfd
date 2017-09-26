package api.index.act;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import api.index.data.Index;
import api.index.data.IndexTree;
import api.index.logic.IndexLogic;

import com.system.db.DbPoolConnection;
import com.system.tool.AutoConvertUtil;
import com.system.tool.SysTool;




public class IndexAct {
    IndexLogic logic=new IndexLogic();
    public void indexTree(HttpServletRequest request,HttpServletResponse response)throws Exception{
        Connection conn=null;
        PrintWriter writer=null;
        try{
            conn=DbPoolConnection.getInstance().getConnection();
            int parentId=request.getParameter("id")==null?0:Integer.parseInt(request.getParameter("id"));
            writer=response.getWriter();
            writer.print(JSONArray.fromObject(logic.indexTree(parentId,conn)).toString());
        }catch(Exception e){
            throw e;
        }finally{
            logic.dao.close(null,null,conn);
            SysTool.closePrintWriter(writer);
        }
    }
    public void indexChoseTree(HttpServletRequest request,HttpServletResponse response)throws Exception{
        Connection conn=null;
        PrintWriter writer=null;
        try{
            conn=DbPoolConnection.getInstance().getConnection();
            int parentId=request.getParameter("id")==null?0:Integer.parseInt(request.getParameter("id"));
            JSONArray tree=JSONArray.fromObject(logic.indexTreeFilterTextDiy(parentId,conn));
            if (parentId==0) {
            	tree.add(0, JSONObject.fromObject(new IndexTree("0", "0", "根目录","根目录", false, "")));
			}
            writer=response.getWriter();
            writer.print(tree.toString());
        }catch(Exception e){
            throw e;
        }finally{
            logic.dao.close(null,null,conn);
            SysTool.closePrintWriter(writer);
        }
    }
    public void save(HttpServletRequest request,HttpServletResponse response)throws Exception{
        try{
        	String id=request.getParameter("id");
        	if (id==null||id.equals("")) {
				this.add(request,response);
			}else{
				this.update(request,response);
			}
        }catch(Exception e){
            throw e;
        }
    }
    public void add(HttpServletRequest request,HttpServletResponse response)throws Exception{
        Connection conn=null;
        PrintWriter writer=null;
        try{
            conn=DbPoolConnection.getInstance().getConnection();
            conn.setAutoCommit(false);
            System.out.println("apiContent:"+request.getParameter("apiContent"));
            int result=logic.add((Index)(new AutoConvertUtil().formToEntity(Index.class, request.getParameterMap())), conn);
            if (result>0) {
            	conn.commit();
			}else{
				conn.rollback();
			}
            writer=response.getWriter();
            writer.print(result>0);
        }catch(Exception e){
        	conn.rollback();
            throw e;
        }finally{
            logic.dao.close(null,null,conn);
            SysTool.closePrintWriter(writer);
        }
    }
    public void update(HttpServletRequest request,HttpServletResponse response)throws Exception{
        Connection conn=null;
        PrintWriter writer=null;
        try{
            conn=DbPoolConnection.getInstance().getConnection();
            conn.setAutoCommit(false);
            int result=logic.update((Index)(new AutoConvertUtil().formToEntity(Index.class, request.getParameterMap())), conn);
            if (result>0) {
            	conn.commit();
			}else{
				conn.rollback();
			}
            writer=response.getWriter();
            writer.print(result>0);
        }catch(Exception e){
        	conn.rollback();
            throw e;
        }finally{
            logic.dao.close(null,null,conn);
            SysTool.closePrintWriter(writer);
        }
    }
    public void delete(HttpServletRequest request,HttpServletResponse response)throws Exception{
        Connection conn=null;
        PrintWriter writer=null;
        try{
            conn=DbPoolConnection.getInstance().getConnection();
            conn.setAutoCommit(false);
            int result=logic.delete(Integer.parseInt(request.getParameter("id")), conn);
            if (result>0) {
            	conn.commit();
			}else{
				conn.rollback();
			}
            writer=response.getWriter();
            writer.print(result>0);
        }catch(Exception e){
        	conn.rollback();
            throw e;
        }finally{
            logic.dao.close(null,null,conn);
            SysTool.closePrintWriter(writer);
        }
    }
    public void getApiText(HttpServletRequest request,HttpServletResponse response)throws Exception{
        PrintWriter writer=null;
        try{
        	JSONObject data=new JSONObject();
        	data.accumulate("text", readTxt(request.getParameter("path")));
            writer=response.getWriter();
            writer.print(data.toString());
        }catch(Exception e){
            throw e;
        }finally{
            SysTool.closePrintWriter(writer);
        }
    }
    public void getIndexById(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	Connection conn=null;
        PrintWriter writer=null;
        try{
            conn=DbPoolConnection.getInstance().getConnection();
            
            int id=Integer.parseInt(request.getParameter("id"));
            
            writer=response.getWriter();
            writer.print(JSONObject.fromObject(logic.getIndexById(id, conn)).toString());
        }catch(Exception e){
            throw e;
        }finally{
            SysTool.closePrintWriter(writer);
            logic.dao.close(null, null, conn);
        }
    }
    
    
    
    
    
    
    
    
    public String readTxt(String relativePath){
		StringBuffer text=new StringBuffer();
		BufferedReader reader=null;
		try {
			String path=getSysProjectPath()+relativePath;
			File file=new File(path);
			if(file.exists()){
				reader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
				String temp=null;
				while((temp=reader.readLine())!=null){
					text.append(temp+"\r\n");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (Exception e2) {}
			}
		}
		return text.toString();
	}
    private String getSysProjectPath(){
		String path=this.getClass().getClassLoader().getResource(File.separator).getPath()+"#";
		String[] pathStrs=path.split("/");
		StringBuffer relativRootPath=new StringBuffer();
		for (int i = 0; i < pathStrs.length-3; i++) {
			relativRootPath.append(pathStrs[i]+"/");
		}
		return relativRootPath.toString();
	}
}
