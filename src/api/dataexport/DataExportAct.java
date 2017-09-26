package api.dataexport;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.component.datamodel.Record;
import com.component.office.util.ExcelUtil;
import com.system.tool.HttpUtil;
import com.system.tool.SysTool;

public class DataExportAct {
	
	public String dataExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		OutputStream ops=null;
		try {
			
			List<Record> records=new ArrayList<Record>();
			
			//加列名
			Record record=new Record();
			record.addField("字段A", "字段A");
			record.addField("字段B", "字段B");
			records.add(record);
			//加数据
			record=new Record();
			record.addField("字段A", "A");
			record.addField("字段B", "B");
			records.add(record);
			
			String exportFileName="导出信息列表.xls";
			
			String ContentDisposition=HttpUtil.getReponseHeader_ContentDisposition(exportFileName, request.getHeader("user-agent"));

			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control", "maxage=3600");
			response.setHeader("Pragma", "public");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition",ContentDisposition);
			
			ops=response.getOutputStream();
			ExcelUtil.writeExcel(ops, records);
			
		} catch (Exception e) {
			throw e;
		}finally{
			SysTool.closeOutputStream(ops);
		}
		return null;
	}
}
