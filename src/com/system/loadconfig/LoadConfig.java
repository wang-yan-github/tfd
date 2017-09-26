package com.system.loadconfig;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.system.db.DbPoolConnection;
import com.system.global.SysConst;
import com.system.global.SysPropKey;
import com.system.global.SysProps;
import com.system.servers.AutoThreadTool;
import com.system.servers.SysServers;
import com.system.sessionpool.SessionPoolList;
import com.system.tool.SysAuthenticator;
import com.system.tool.SysTool;

public class LoadConfig {
    public static Properties loadSysProps(String sysPropsFile) {
        return loadSysProps(new File(sysPropsFile));
    }

    /**
     * 从系统web-info配置文件中加载系统配置
     * 
     * @param sysPropsFile
     *            系统配置文件
     * @return
     */
    public static Properties loadSysProps(File sysPropsFile) {
        Properties props = new Properties();
        if (!sysPropsFile.exists()) {
            return props;
        }
        InputStream inProps = null;
        try {
            inProps = new BufferedInputStream(new FileInputStream(sysPropsFile));
            props.load(inProps);
        } catch (IOException ex) {
        } finally {
            try {
                if (inProps != null) {
                    inProps.close();
                }
            } catch (IOException ex) {
            }
        }
        return props;
    }

    /**
     * 系统初始化
     * 
     * @param installPath
     *            系统的安装路径
     * @return
     */
    public static void loadInit(String rootPath) throws Exception {
        SessionPoolList.stopReleaseThread();
        AutoThreadTool.stopRun();
        File rootPathFile = new File(rootPath);
        String installPath = null;
        String webRoot = null;
        String ctx = null;
        try {
            String realRootPath = rootPathFile.getCanonicalPath();
            int p1 = realRootPath.lastIndexOf(File.separator);
            int p2 = realRootPath.substring(0, p1).lastIndexOf(File.separator);
            installPath = realRootPath.substring(0, p2);
            webRoot = realRootPath.substring(p2 + 1, p1);
            ctx = realRootPath.substring(p1 + 1);
            System.out.println("安装路径：" + installPath);
        } catch (Exception ex) {
        }

        // 从配置文件中加载系统配置
        String sysConfFile = rootPath + "WEB-INF" + File.separator + "config" + File.separator
                + "sysconfig.properties";
        SysProps.setProps(LoadConfig.loadSysProps(sysConfFile));
        // 设置安装路径、Webroot、contextPath
        Map<String, String> pathMap = new HashMap<String, String>();
        pathMap.put(SysPropKey.ROOT_DIR, installPath);
        pathMap.put(SysPropKey.WEB_ROOT_DIR, webRoot);
        pathMap.put(SysPropKey.JSP_ROOT_DIR, ctx);
        SysProps.addProps(pathMap);
        processSysInfo();
        // 构建数据源
        Connection dbConn = DbPoolConnection.getInstance().getConnection();
        Statement stmt = null;
        try {
            String sql = "DELETE FROM USER_ONLINE";
            synchronized (SysServers.onlineSync) {
                stmt = dbConn.createStatement();
                stmt.executeUpdate(sql);
                dbConn.commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (dbConn != null) {
                    dbConn.rollback();
                }
            } catch (Exception ex2) {
            }
        } finally {
            stmt.close();
            dbConn.close();
        }
        // 启动后台线程
        try {
            int sleepTime = SysProps.getInt(SysPropKey.BACK_THREAD_SLEEP_TIME);
            if (sleepTime < 1) {
                sleepTime = 100;
            }
            AutoThreadTool.startAutoRun(sleepTime);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 处理系统信息
     */
    private static void processSysInfo() throws Exception {
        String[] keyArray = new String[] { "shortOrgName", "orgName", "productName", "fullOrgName",
                "SysInfo", "shortProductName", "orgFirstSite", "orgSecondSite", "workflowZipDown" };
        String[] defaultValue = new String[] { "", "协同软件", "协同办公系统", "", "系统信息", "V1.0", "", "", "" };
        Map<String, String> sysInfoMap = new HashMap<String, String>();
        for (int i = 0; i < keyArray.length; i++) {
            String key = keyArray[i];
            String value = SysProps.getString(key);
            String pass = SysProps.getString(key + "Pass");

            if (!SysTool.isNullorEmpty(value)) {
                if (SysAuthenticator.isValidRegist(SysConst.getMD5SaltLength(null), value, pass)) {
                    sysInfoMap.put(key, value);
                } else {
                    sysInfoMap.put(key, defaultValue[i]);
                }
            } else {
                sysInfoMap.put(key, defaultValue[i]);
            }
        }
        SysProps.addProps(sysInfoMap);
    }

}
