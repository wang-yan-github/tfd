package com.system.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.system.global.SysProps;

public class DbPoolConnection {
    private static DbPoolConnection databasePool = null;
    private static DruidDataSource dds = null;
    static {
        String DbPoolProperties = "";
        if (SysProps.getWebPath().equals("\\\\")) {
            DbPoolProperties = "D:\\TFDSYS\\webroot\\tfd\\WEB-INF" + File.separator + "config"
                    + File.separator + "database.properties";
        } else {
            DbPoolProperties = SysProps.getWebPath() + File.separator + "WEB-INF" + File.separator + "config"
                    + File.separator + "database.properties";
        }
        System.out.println("DbPoolProperties====" + DbPoolProperties);
        Properties properties = loadPropertyFile(DbPoolProperties);
        try {
            dds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            System.out.println("dds======" + dds);
            System.out.println("ddsconn======" + dds.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DbPoolConnection() {
    }

    /**
     * 
     * @return
     */
    public static synchronized DbPoolConnection getInstance() {
        if (null == databasePool) {
            databasePool = new DbPoolConnection();
        }
        return databasePool;
    }

    public DruidPooledConnection getConnection() throws SQLException {
        return dds.getConnection();
    }

    public String getDbType() throws SQLException {
        return dds.getDbType();
    }

    /**
     * 
     * @param fullFile
     * @return
     */
    private static Properties loadPropertyFile(String fullFile) {
        InputStream inputStream = null;
        Properties p = null;
        try {
            inputStream = new FileInputStream(new File(fullFile));
            p = new Properties();
            p.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Properties file not found: " + fullFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("Properties file can not be loading: " + fullFile);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

}
