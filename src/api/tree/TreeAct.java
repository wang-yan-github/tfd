package api.tree;

import api.testdata.GradeStudentTree;
import com.component.datamodel.ATree;
import com.system.db.BaseDao;
import com.system.db.DbPoolConnection;
import com.system.db.impl.BaseDaoImpl;
import com.system.tool.SysTool;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2015/9/16.
 */
public class TreeAct {
    BaseDao dao=new BaseDaoImpl();

    public void loadTree(HttpServletRequest request,HttpServletResponse response)throws Exception{
        Connection conn=null;
        PrintWriter writer=null;
        try{
            conn= DbPoolConnection.getInstance().getConnection();

            int parentId=request.getParameter("id")==null?0:Integer.parseInt(request.getParameter("id"));
            List<ATree> tree=null;
            if (parentId==0){
                tree=loadGrade(parentId,conn);
            }else{
                tree=loadStudent(parentId,conn);
            }
            writer=response.getWriter();
            writer.print(JSONArray.fromObject(tree).toString());
        }catch(Exception e){
            throw e;
        }finally {
            dao.close(null,null,conn);
            SysTool.closePrintWriter(writer);
        }
    }

    public List<ATree> loadGrade(int parentId,Connection conn) throws Exception{
        List<ATree> tree=new ArrayList<ATree>();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try{
            String sql="select g.id,g.name,(select count(1) from api_student where grade=g.id) as child_count" +
                        " from api_grade as g";
            stmt=conn.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                ATree atree=new GradeStudentTree(
                        rs.getString("id"),
                        parentId+"",
                        rs.getString("name"),
                        rs.getInt("child_count")>0
                );
                tree.add(atree);
            }
        }catch(Exception e){
            throw e;
        }finally {
            dao.close(rs, stmt, null);
        }
        return tree;
    }
    public List<ATree> loadStudent(int parentId,Connection conn) throws Exception{
        List<ATree> tree=new ArrayList<ATree>();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try{
            String sql="select concat('student-',id) as id,name, 0 as child_count from api_student where grade=?";
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,parentId);
            rs=stmt.executeQuery();
            while (rs.next()){
                ATree atree=new GradeStudentTree(
                        rs.getString("id"),
                        parentId+"",
                        rs.getString("name"),
                        rs.getInt("child_count")>0
                );
                tree.add(atree);
            }
        }catch(Exception e){
            throw e;
        }finally {
            dao.close(rs,stmt,null);
        }
        return tree;
    }
}
