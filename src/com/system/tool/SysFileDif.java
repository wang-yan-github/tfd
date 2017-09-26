package com.system.tool;
import java.io.File;
import java.io.FileFilter;
public class SysFileDif implements FileFilter {
	/**
	   * 新目录

	   */
	  private String newDir = null;
	  /**
	   * 原目录

	   */
	  private String oldDir = null;
	  
	  /**
	   * 构造函数

	   * @param newDir
	   * @param oldDir
	   */
	  public SysFileDif(String newDir, String oldDir) {
	    this.newDir = newDir;
	    this.oldDir = oldDir;
	  }
	  
	  /**
	   * 是否选择该文件

	   */
	  public boolean accept(File file) {
	    try {
	      File oldFile= new File(
	          oldDir + file.getAbsolutePath().substring(newDir.length()));
	      if (!oldFile.exists()) {
	        return true;
	      }
	      return !SysFileTool.isFileEqual(file, oldFile);
	    }catch(Exception ex) {
	      return false;
	    }
	  }
}
