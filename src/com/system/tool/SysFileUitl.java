package com.system.tool;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

public class SysFileUitl implements FileFilter {
	 /** 参与比较的时间 **/
	  private Date cpTime = null;
	  /** 时间区间 **/
	  private boolean acceptNew = true;
	  
	  /**
	   * 构造函数

	   * @param cpTime               比较时间
	   * @param acceptNew            选择新文件标志true=选择日期更新的文件；false=选择日期更旧的文件

	   */
	  public SysFileUitl(Date cpTime, boolean acceptNew) {
	    this.cpTime = cpTime;
	    this.acceptNew = acceptNew;
	  }
	  
	  /**
	   * 是否选择该文件

	   */
	  public boolean accept(File file) {
	     if (acceptNew) {
	       if (file.lastModified() > cpTime.getTime()) {
	         return true;
	       }
	       return false;
	     }
	     if (file.lastModified() > cpTime.getTime()) {
	       return false;
	     }
	     return true;
	  }
}
