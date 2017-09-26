package fixedasset.logic;

import java.sql.Connection;
import java.util.Map;

import net.sf.json.JSONObject;

import com.component.page.Page;
import com.component.page.PageUtil;
import com.system.db.BaseDao;
import com.system.db.PageTool;
import com.system.db.impl.BaseDaoImpl;

import fixedasset.data.FixedassetSource;

public class FixedassetSourceLogic {
	private BaseDao dao=new BaseDaoImpl();
	public void add(FixedassetSource fixedassetSource,Connection conn) throws Exception{
		String sql="insert into fixedasset_source(seq_id,source,sort) values(?,?,?)";
		dao.excuteSQL(conn, sql, new Object[]{
				fixedassetSource.getSeqId(),
				fixedassetSource.getSource(),
				fixedassetSource.getSort()
		});
	}
	public void delete(String seqId,Connection conn) throws Exception{
		String sql="delete from fixedasset_source where seq_id=?";
		dao.excuteSQL(conn, sql, new Object[]{
				seqId
		});
	}
	public void update(FixedassetSource fixedassetSource,Connection conn) throws Exception{
		String sql="update fixedasset_source set source=?,sort=? where seq_id=?";
		dao.excuteSQL(conn, sql, new Object[]{
				fixedassetSource.getSource(),
				fixedassetSource.getSort(),
				fixedassetSource.getSeqId()
		});
	}
	public JSONObject sourceList(Map<String,String[]> requestMap,Connection conn) throws Exception{
		JSONObject data=null;;
		
		String sql="select id,seq_id,sort,source from fixedasset_source order by sort";
		
		Page page=PageUtil.toEasyuiPage(requestMap);
		PageTool pageTool=new PageTool();
		data= pageTool.getPageData(conn, sql, page);
		
		return data;
	}
}
