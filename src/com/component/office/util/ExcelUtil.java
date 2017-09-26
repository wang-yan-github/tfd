package com.component.office.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.component.datamodel.Record;

public class ExcelUtil {
	public static void writeExcel(OutputStream ops, List<Record> records) throws Exception {
		try {
			HSSFWorkbook wwb = new HSSFWorkbook();
			HSSFSheet ws = wwb.createSheet();
			for (int i = 0; i < records.size(); i++) {
				Record record = records.get(i);
				HSSFRow row = ws.createRow(i);
				for (int j = 0; j < record.getFieldCnt(); j++) {
					String value = "";
					if (record.getValueByIndex(j) != null) {
						value = record.getValueByIndex(j).toString();
					}
					
					HSSFCell dataCell = row.createCell(j);
					
					boolean isSet=false;
					try {
						dataCell.setCellValue(Integer.parseInt(value));
						isSet=true;
					} catch (Exception e) {
					}
					
					if (!isSet) {
						try {
							dataCell.setCellValue(Long.parseLong(value));
							isSet=true;
						} catch (Exception e) {
						}
					}
					if (!isSet) {
						try {
							dataCell.setCellValue(Double.parseDouble(value));
							isSet=true;
						} catch (Exception e) {
						}
					}

					if (!isSet) {
						dataCell.setCellValue(value);
						isSet=true;
					}
				}
			}
			wwb.write(ops);
		} catch (Exception e) {
			throw e;
		}
	}

	public static Map<String,List<Record>> readExcel(InputStream inputStream,boolean hasTitle) throws Exception {
		Map<String,List<Record>> recordsMap = new LinkedHashMap<String,List<Record>>();
		HSSFWorkbook book = new HSSFWorkbook(inputStream);

		for (int si = 0;; si++) {
			String sheetName=null;
			try {
				sheetName=book.getSheetName(si);
			} catch (Exception e) {}
			if (sheetName==null) {
				break;
			}
			List<Record> records=new ArrayList<Record>();
			
			HSSFSheet sheet = book.getSheetAt(si);
			
			HSSFRow titleRow = sheet.getRow(0);
			int rowTotal = sheet.getLastRowNum()+1;
			
			int colTotal = 0;
			for(;;colTotal++){
				HSSFCell cellTemp=titleRow.getCell(colTotal);
				if (cellTemp==null) {
					break;
				}
			}
			
			for (int i = 0; i < rowTotal; i++) {
				if (i==0&&hasTitle) {
					continue;
				}
				
				Record record = new Record();
				HSSFRow row = sheet.getRow(i);
				if (row==null) {
					continue;
				}
				for (int j = 0; j < colTotal; j++) {
					
					String title = "";
					if (hasTitle) {
						HSSFCell titleC = titleRow.getCell(j);
						title = titleC.getStringCellValue();
					} else {
						title = "cell_" + j;
					}
					
					HSSFCell cell = row.getCell(j);
					
					Object value = null;
					if (cell!=null) {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							value=cell.getNumericCellValue();
							if (value!=null) {
								NumberFormat nf=NumberFormat.getInstance();
								nf.setGroupingUsed(false);
								value=nf.format(value);
							}
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							value = cell.getStringCellValue();
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
							value = cell.getBooleanCellValue();
						}
					}
					if (value==null) {
						value="";
					}
					record.addField(title, value);
				}
				records.add(record);
			}
			recordsMap.put(sheetName, records);
		}
		
		return recordsMap;
	}
}
