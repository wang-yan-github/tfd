package com.system.servers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;




import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.global.SysProps;
import com.system.tool.SysFileTool;
import com.system.tool.SysTool;

public class AutoThreadTool extends Thread {
  //log
  //相邻两次运行的时间间隔 单位是秒
  private int sleepTime = 1;
  //后台服务对象链
  private static Connection dbConn;
  public static Connection getDbConn()
  {
	  return dbConn;
  }
  private Map<String, AutoServer> autoRuns = new LinkedHashMap<String, AutoServer>();
  //运行标志
  private boolean runFlag = true;
  //运行实例
  private static AutoThreadTool mainService = null;
  
  /**
   * 加载后台线程配置
 * @throws SQLException 
   */
  private void loadAutoRunConfig() throws SQLException {
	  dbConn= DbPoolConnection.getInstance().getConnection();
	  String confFile = SysProps.getWebInfoPath() + File.separator + "config" + File.separator + "autoserver.properties";
	  Map<String, String> rawConfMap = new HashMap<String, String>();
	  try
	  {
		  SysFileTool.load2Map(confFile, rawConfMap);
	      Map<String, String> confMap = SysTool.startsWithMap(rawConfMap, "autoRunTask");
	      Iterator<String> iKeys = confMap.keySet().iterator();
	      while (iKeys.hasNext()) {
	        String key = iKeys.next();
	        String confJson = confMap.get(key);
	        if (SysTool.isNullorEmpty(confJson)) {
	          continue;
	        }
	        	AutoServer autoRun = AutoServer.buildAutoRun(confJson);
	          if (!autoRun.isPause()) {
	            autoRuns.put(key, autoRun);
	          }
	      }
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
  }

  //防止用户使用默认的构造函数
  private AutoThreadTool() {
  }

  /**
   * 构造函数 相邻两次运行的时间间隔 单位是分钟
   * @param sleepTime
 * @throws SQLException 
   */
  private AutoThreadTool(int sleepTime) throws SQLException {
    this.sleepTime = sleepTime;
    loadAutoRunConfig();
  }
  
  /**
   * 取得当前运行实例
   * @return
   */
  public static AutoThreadTool currInstance() {
    return mainService;
  }

  /**
   * 注册释放资源的对象
   * @param releasor
   */
  public synchronized void registAutoRun(String key, AutoServer autoRun) {
    autoRuns.put(key, autoRun);
  }
  /**
   * 注册服务
   * @param releasor
   */
  public synchronized void registAutoRun(String key, String configJson) {
    try {
      this.registAutoRun(key, AutoServer.buildAutoRun(configJson));
    }catch(Exception ex) {      
    }
  }
  /**
   * 删除服务
   * @param key
   */
  public synchronized void removeAutoRun(String key) {
	  AutoServer autoRun = this.autoRuns.get(key);
    if (autoRun != null) {
      autoRun.stopRun();
      autoRuns.remove(key);
    }
  }
  /**
   * 清除服务
   * @param key
   */
  public synchronized void clearAutoRun() {
    Iterator<String> iKeys = autoRuns.keySet().iterator();
    while (iKeys.hasNext()) {
    	AutoServer autoRun = (AutoServer)autoRuns.get(iKeys.next());
      autoRun.stopRun();
    }
    autoRuns.clear();
  }
  /**
   * 手工启动服务
   * @param key
   * @return 1=没有找到该服务；2=该服务正在运行；0=正常启动
   */
  public synchronized int manuStartAutoRun(String key) {
	  AutoServer autoRun = this.autoRuns.get(key);
    if (autoRun == null) {
      return 1;
    }
    if (autoRun.isRunning()) {
      return 2;
    }
    try {
      autoRun.menuStartRun();
      
    }catch(Exception ex) {
    		ex.printStackTrace();
    }finally {
    }
    return 0;
  }

  /**
   * 释放资源
   */
  private void doRun() {
    try {
      Iterator<String> iKeys = autoRuns.keySet().iterator();
      while (iKeys.hasNext()) {
    	 AutoServer autoRun = (AutoServer)autoRuns.get(iKeys.next());
        try {
          autoRun.startRun();
          autoRun.stopRun();
        }catch (Throwable ex) {
          try {
        	  	ex.printStackTrace();
          }catch(Throwable t) {          
          }
        }
      }
    }catch(Exception ex) {
    	ex.printStackTrace();
    }finally {
    }
  }

  /**
   * 启动该线程
 * @throws SQLException 
   */
  public static void startAutoRun(int sleepTime) throws SQLException {
    stopRun();
    mainService = new AutoThreadTool(sleepTime);
    mainService.setRunFlag(true);
    mainService.start();
  }
  
  /**
   * 终止线程
   */
  public static void stopRun() {
    if (mainService == null) {
      return;
    }
    try {
      mainService.setRunFlag(false);
      mainService.interrupt();
      mainService.clearAutoRun();
    }catch(Exception ex) {      
    }finally {
      mainService = null;
    }
  }

  /**
   * 重载父类方法run
   */
  public void run() {
    while (runFlag) {
      try { 
        sleep(sleepTime * SysConst.DT_S);
        doRun();
      }catch (Throwable ex) {
        try {
        	ex.printStackTrace();
        }catch(Throwable t) {          
        }
      }
    }
    System.out.println("后台线程停止运行.");
  }

  /**
   * 设置runFlag
   * @param runFlag
   */
  public void setRunFlag(boolean runFlag) {
    this.runFlag = runFlag;
  }
}
