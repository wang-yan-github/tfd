package api.index.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import api.index.data.Index;
import api.index.data.IndexTree;

import com.component.datamodel.ATree;
import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;

/**
 * Created by Administrator on 2015/9/16.
 */
public class IndexLogic {
    public BaseDao dao=new BaseDaoImpl();
    public List<ATree> indexTree(int parentId,Connection conn) throws Exception{
        List<ATree> tree=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
        	
            String sql="select "
            		+ "id,"
            		+ "name,"
            		+ "text_diy,"
            		+ "(select count(1) from api_index where parent_id=a.id) as child_count,"
            		+ "path "
            		+ "from api_index as a where parent_id=? order by id";
            
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,parentId);
            rs=stmt.executeQuery();
            
            tree=new ArrayList<ATree>();
            
            while(rs.next()){
            	String textDiy=rs.getString("text_diy");
            	String name=rs.getString("name");
            	String html=name;
            	
            	if (textDiy!=null&&!textDiy.equals("")) {
            		html=textDiy.replace("#textDiy#", name);
				}
            	
                ATree atree=new IndexTree(
                    rs.getString("id"),
                    parentId+"",
                    name,
                    html,
                    rs.getInt("child_count")>0,
                    rs.getString("path")
                );
                tree.add(atree);
            }
        }catch (Exception e){
            throw e;
        }finally {
            dao.close(rs,stmt,null);
        }
        return tree;
    }
    public List<ATree> indexTreeFilterTextDiy(int parentId,Connection conn) throws Exception{
        List<ATree> tree=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
        	
            String sql="select "
            		+ "id,"
            		+ "name,"
            		+ "(select count(1) from api_index where parent_id=a.id) as child_count,"
            		+ "path "
            		+ "from api_index as a where parent_id=? order by id";
            
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,parentId);
            rs=stmt.executeQuery();
            
            tree=new ArrayList<ATree>();
            
            while(rs.next()){
            	String name=rs.getString("name");
            	String html=name;
            	
                ATree atree=new IndexTree(
                    rs.getString("id"),
                    parentId+"",
                    name,
                    html,
                    rs.getInt("child_count")>0,
                    rs.getString("path")
                );
                tree.add(atree);
            }
        }catch (Exception e){
            throw e;
        }finally {
            dao.close(rs,stmt,null);
        }
        return tree;
    }
    public int add(Index index,Connection conn) throws Exception{
    	int result=0;
    	PreparedStatement stmt=null;
    	try {
			String sql="insert into api_index(name,parent_id,path,api_content) values(?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, index.getName());
			stmt.setInt(2, index.getParentId());
			stmt.setString(3, index.getPath());
			stmt.setString(4, index.getApiContent());
			result=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
    	return result;
    }
    public int update(Index index,Connection conn) throws Exception{
    	int result=0;
    	PreparedStatement stmt=null;
    	try {
			String sql="update api_index set name=?,parent_id=?,path=?,api_content=? where id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, index.getName());
			stmt.setInt(2, index.getParentId());
			stmt.setString(3, index.getPath());
			stmt.setString(4, index.getApiContent());
			stmt.setInt(5, index.getId());
			result=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
    	return result;
    }
    public int delete(int id,Connection conn) throws Exception{
    	int result=0;
    	PreparedStatement stmt=null;
    	try {
			String sql="delete from api_index where id=?";
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1, id);
			result=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
    	return result;
    }
    public Index getIndexById(int id,Connection conn) throws Exception{
    	Index index=null;
    	PreparedStatement stmt=null;
    	ResultSet rs=null;
    	try {
    		String sql="select name,parent_id,path,text_diy,api_content from api_index where id=?";
    		stmt=conn.prepareStatement(sql);
    		stmt.setInt(1, id);
    		rs=stmt.executeQuery();
    		if (rs.next()) {
				index=new Index();
				index.setApiContent(rs.getString("api_content"));
				index.setId(id);
				index.setName(rs.getString("name"));
				index.setParentId(rs.getInt("parent_id"));
				index.setPath(rs.getString("path"));
				index.setTextDiy(rs.getString("text_diy"));
			}
		} catch (Exception e) {
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
    	return index;
    }
}
