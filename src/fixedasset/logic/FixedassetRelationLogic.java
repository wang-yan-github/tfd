package fixedasset.logic;

import java.sql.Connection;
import java.util.Date;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.global.DateConst;
import com.system.tool.SysTool;

import fixedasset.data.FixedassetRelation;

public class FixedassetRelationLogic {
	private BaseDao dao=new BaseDaoImpl();
	public void add(FixedassetRelation fixedassetRelation,Connection conn) throws Exception{
		String sql="insert into fixedasset_relation ";
		sql+=" (asset_id,asset_source,asset_type,net_salvage,number,posting_date,seq_id,storage_location,unit,use_dept,use_situation,use_user) ";
		sql+=" values(?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?,?,?,?,?) ";
		dao.excuteSQL(conn, sql, new Object[]{
				fixedassetRelation.getAssetId(),
				fixedassetRelation.getAssetSource(),
				fixedassetRelation.getAssetType(),
				fixedassetRelation.getNetSalvage(),
				fixedassetRelation.getNumber(),
				toPostingDate(fixedassetRelation.getPostingDate()),
				fixedassetRelation.getSeqId(),
				fixedassetRelation.getStorageLocation(),
				fixedassetRelation.getUnit(),
				fixedassetRelation.getUseDept(),
				fixedassetRelation.getUseSituation(),
				fixedassetRelation.getUseUser()
				
		});
	}
	public String toPostingDate(Date postingDate){
		String date=null;
		if (postingDate!=null) {
			date=SysTool.getDateTimeStr(postingDate, DateConst.VALUE_LONG_DATE24);
		}
		return date;
	}
	public void delete(String seqId,Connection conn) throws Exception{
		String sql="delete from fixedasset_relation where seq_id=?";
		dao.excuteSQL(conn, sql, new Object[]{
				seqId
		});
	}
	public void update(FixedassetRelation fixedassetRelation,Connection conn) throws Exception{
		String sql="update fixedasset_relation set ";
		sql+=" (asset_id=?,asset_source=?,asset_type=?,net_salvage=?,number=?,posting_date=str_to_date(?,'%Y-%m-%d %H:%i:%s'),storage_location=?,unit,use_dept=?,use_situation=?,use_user=?) ";
		sql+=" where seq_id=? ";
		dao.excuteSQL(conn, sql, new Object[]{
				fixedassetRelation.getAssetId(),
				fixedassetRelation.getAssetSource(),
				fixedassetRelation.getAssetType(),
				fixedassetRelation.getNetSalvage(),
				fixedassetRelation.getNumber(),
				toPostingDate(fixedassetRelation.getPostingDate()),
				fixedassetRelation.getStorageLocation(),
				fixedassetRelation.getUnit(),
				fixedassetRelation.getUseDept(),
				fixedassetRelation.getUseSituation(),
				fixedassetRelation.getUseUser(),
				fixedassetRelation.getSeqId()
		});
	}
}
