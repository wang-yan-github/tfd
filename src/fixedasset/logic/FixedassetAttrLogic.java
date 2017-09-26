package fixedasset.logic;

import java.sql.Connection;
import java.util.Date;
import java.util.Map;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.global.DateConst;
import com.system.tool.SysTool;

import fixedasset.data.FixedassetAttr;

public class FixedassetAttrLogic {
	private BaseDao dao=new BaseDaoImpl();
	public void add(FixedassetAttr fixedassetAttr,Connection conn) throws Exception{
		String sql="insert into fixedasset_attr ";
		sql+=" (asset_name,asset_no,ex_factory_date,image,manufacture,remark,seq_id,service_life,standard,unit_price) ";
		sql+=" values(?,?,str_to_date(?,'%Y-%m-%d'),?,?,?,?,?,?,?) ";
		dao.excuteSQL(conn, sql, new Object[]{
				fixedassetAttr.getAssetName(),
				fixedassetAttr.getAssetNo(),
				toExFactoryDate(fixedassetAttr.getExFactoryDate()),
				fixedassetAttr.getImage(),
				fixedassetAttr.getManufacture(),
				fixedassetAttr.getRemark(),
				fixedassetAttr.getSeqId(),
				fixedassetAttr.getServiceLife(),
				fixedassetAttr.getStandard(),
				fixedassetAttr.getUnitPrice()
		});
	}
	public String toExFactoryDate(Date exFactoryDate){
		String date=null;
		if (exFactoryDate!=null) {
			date=SysTool.getDateTimeStr(exFactoryDate, DateConst.VALUE_SHORT_DATE);
		}
		return date;
	}
	public void delete(String seqId,Connection conn) throws Exception{
		String sql="delete from fixedasset_attr where seq_id=?";
		dao.excuteSQL(conn, sql, new Object[]{
				seqId
		});
	}
	public void update(FixedassetAttr fixedassetAttr,Connection conn) throws Exception{
		String sql="update fixedasset_attr set ";
		sql+=" (asset_name=?,asset_no=?,ex_factory_date=str_to_date(?,'%Y-%m-%d'),image=?,remark=?,service_life=?,standard=?,unit_price=?) ";
		sql+=" where seq_id=? ";
		dao.excuteSQL(conn, sql, new Object[]{
				fixedassetAttr.getAssetName(),
				fixedassetAttr.getAssetNo(),
				toExFactoryDate(fixedassetAttr.getExFactoryDate()),
				fixedassetAttr.getImage(),
				fixedassetAttr.getManufacture(),
				fixedassetAttr.getRemark(),
				fixedassetAttr.getServiceLife(),
				fixedassetAttr.getStandard(),
				fixedassetAttr.getUnitPrice(),
				fixedassetAttr.getSeqId()
		});
	}
	public String getSeqIdByAssetNo(String assetNo,Connection conn) throws Exception{
		String sql="select seq_id from fixedasset_attr where asset_no=?";
		Map dataMap=dao.getMapById(conn, sql, new Object[]{assetNo});
		if (dataMap!=null&&dataMap.size()>0) {
			return dataMap.get("seq_id").toString();
		}
		return null;
	}
}
