package com.component.page;

import java.util.Map;

public class PageUtil {
	public static Page toEasyuiPage(Map<String, String[]> requestMap){
		Page page=null;
		
		String[] pageIndexStr=requestMap.get("page");
		String[] pageSizeStr=requestMap.get("rows");
		String[] sortFieldStr=requestMap.get("sort");
		String[] sortTypeStr=requestMap.get("order");
		
		int pageIndex=0;
		int pageSize=10;
		String sortField=null;
		String sortType=null;
		
		if (pageIndexStr!=null) {
			pageIndex=Integer.parseInt(pageIndexStr[0]);
		}
		if (pageSizeStr!=null) {
			pageSize=Integer.parseInt(pageSizeStr[0]);
		}
		if (sortFieldStr!=null) {
			sortField=sortFieldStr[0];
		}
		if (sortTypeStr!=null) {
			sortType=sortTypeStr[0];
		}
		
		
		page=new Page();
		page.setPageIndex(pageIndex);
		page.setPageSize(pageSize);
		page.setSortField(sortField);
		page.setSortType(sortType);
		
		return page;
	}
}
