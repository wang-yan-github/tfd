package fixedasset.logic;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;
import com.system.global.DateConst;
import com.system.tool.SysTool;

import fixedasset.data.FixedassetRecord;

public class FixedassetRecordLogic {
	private BaseDao dao=new BaseDaoImpl();
	public void add(FixedassetRecord fixedassetRecord,Connection conn) throws Exception{
		String sql="insert into fixedasset_record  ";
		sql+=" (asset_id,flow_msg,record_type,registration_date,registration_user,seq_id) ";
		sql+=" values(?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?)";
		dao.excuteSQL(conn, sql, new Object[]{
				fixedassetRecord.getAssetId(),
				fixedassetRecord.getFlowMsg(),
				fixedassetRecord.getRecordType(),
				toRegistrationDate(fixedassetRecord.getRegistrationDate()),
				fixedassetRecord.getRegistrationUser(),
				fixedassetRecord.getSeqId()
		});
	}

	public String toRegistrationDate(Date registrationDate){
		String date=null;
		if (registrationDate!=null) {
			date=SysTool.getDateTimeStr(registrationDate, DateConst.VALUE_LONG_DATE24);
		}
		return date;
	}
	public void delete(String seqId,Connection conn) throws Exception{
		String sql="delete from fixedasset_record where seq_id=?";
		dao.excuteSQL(conn, sql, new Object[]{
				seqId
		});
	}
	public List<FixedassetRecord> recordList(String assetId,Connection conn) throws Exception{
		List<FixedassetRecord> records=new ArrayList<FixedassetRecord>();
		String sql="select flow_msg,record_type,registration_date,registration_user from fixedasset_record where asset_id=? order by retistration_date desc";
		List list=dao.getListAll(conn, sql,new Object[]{
				assetId
		});
		if (list!=null&&list.size()>0) {
			for (Object o : list) {
				Map map=(Map) o;
				String flowMsg=map.get("flow_msg")==null?null:map.get("flow_msg").toString();
				String recordType=map.get("record_type")==null?null:map.get("record_type").toString();
				Timestamp registrationDate=(Timestamp) (map.get("registration_date")==null?null:map.get("registration_date"));
				String registrationUser=map.get("registration_user")==null?null:map.get("registration_user").toString();
				
				FixedassetRecord record=new FixedassetRecord();
				record.setAssetId(assetId);
				record.setFlowMsg(flowMsg);
				record.setRecordType(recordType);
				record.setRegistrationDate(registrationDate==null?null:new Date(registrationDate.getTime()));
				record.setRegistrationUser(registrationUser);
				
				records.add(record);
			}
		}
		return records;
	}
}
