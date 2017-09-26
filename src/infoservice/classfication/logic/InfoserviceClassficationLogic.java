package infoservice.classfication.logic;

import infoservice.classfication.data.InfoserviceClassfication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.db.injection.DynamicQuery;
import com.system.db.injection.Parameter;
import com.system.tool.AutoConvertUtil;

public class InfoserviceClassficationLogic {
	private AutoConvertUtil autoConvertUtil=new AutoConvertUtil();
	private BaseDao dao=new BaseDaoImpl();
	
	public int add(InfoserviceClassfication classfication,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		try {
			String sql="insert into infoservice_classfication(id,name,parent,navigation,sort,remark) values(?,?,?,?,?,?)";
			
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, classfication.getId());
			stmt.setString(2, classfication.getName());
			stmt.setString(3, classfication.getParent());
			stmt.setString(4, classfication.getNavigation());
			stmt.setInt(5, classfication.getSort());
			stmt.setString(6, classfication.getRemark());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}
	
	public int delete(String navigation,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		try {
			String sql="delete from infoservice_classfication where navigation like ?";
			
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, navigation+"%");
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}
	
	public int update(InfoserviceClassfication classfication,Connection conn) throws Exception{
		PreparedStatement stmt=null;
		try {
			String sql="update infoservice_classfication set ";
			sql+=" name=?,";
			sql+=" parent=?,";
			sql+=" navigation=?,";
			sql+=" sort=?,";
			sql+=" remark=?";
			sql+=" where id=?";
			
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, classfication.getName());
			stmt.setString(2, classfication.getParent());
			stmt.setString(3, classfication.getNavigation());
			stmt.setInt(4, classfication.getSort());
			stmt.setString(5, classfication.getRemark());
			stmt.setString(6, classfication.getId());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(null, stmt, null);
		}
	}
	
	public List<InfoserviceClassfication> search(Map<String,String[]> requestMap,Connection conn) throws Exception{
		List<InfoserviceClassfication> classfications=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			String baseSql="select id,name,parent,navigation,sort,remark from infoservice_classfication where 1=1 ";
			
			DynamicQuery query=new DynamicQuery();
			query.setBaseSql(baseSql);
			query.setParameters(this.getSearchParam(requestMap));
			String sql=query.generateSql();
			
			stmt=conn.prepareStatement(sql);
			query.fillPreparedStatement(stmt);
			
			rs=stmt.executeQuery();
			classfications=new ArrayList<InfoserviceClassfication>();
			InfoserviceClassfication classfication=null;
			while(rs.next()){
				String id=rs.getString("id");
				String name=rs.getString("name");
				String parent=rs.getString("parent");
				String navigation=rs.getString("navigation");
				int sort=rs.getInt("sort");
				String remark=rs.getString("remark");
				
				classfication=new InfoserviceClassfication(id, name, parent, navigation, sort, remark);
				classfications.add(classfication);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return classfications;
	}
	public List<Parameter> getSearchParam(Map<String,String[]> requestMap){
		DynamicQuery query=new DynamicQuery();
		Parameter param=null;
		
		String name=requestMap.get("name")==null?null:requestMap.get("name")[0];
		String parent=requestMap.get("parent")==null?null:requestMap.get("parent")[0];
		String navigation=requestMap.get("navigation")==null?null:requestMap.get("navigation")[0];
		
		if (name!=null&&!name.trim().equals("")) {
			param=new Parameter("name", "like", name);
			query.addParameter(param);
		}
		
		if (parent!=null&&!parent.trim().equals("")) {
			param=new Parameter("parent", "=", parent);
			query.addParameter(param);
		}
		
		if (navigation!=null&&!navigation.trim().equals("")) {
			param=new Parameter("navigation", "like", navigation+".%");
			query.addParameter(param);
		}
		return query.getParameters();
	}
	
	public boolean haveChildren(String id,Connection conn)throws Exception{
		String sql="select count(1) child_count from infoservice_classfication where parent=?";
		Map dataMap=dao.getMapById(conn, sql,new Object[]{
				id
		});
		if (dataMap!=null&&dataMap.size()>0) {
			return Integer.parseInt(dataMap.get("child_count").toString())>0;
		}
		return false;
	}
}
