package tfd.system.syslog.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.system.global.DateConst;
import com.system.tool.SysTool;


public class SysLogLogic {
	 /**
	   * 保存系统日志
	   * 
	   * @param conn
	   * @param type
	   *          类型[1登录日志|2登录密码错误|3添加部门|4编辑部门|5删除部门|6添加用户|7编辑用户|8删除用户 |9
	   *          非法IP登录|10错误用户名|11admin密码清空|12系统资源回收|13考勤数据管理|14修改登录密码|15公告通知管理 |16
	   *          公共文件柜|17网络硬盘|18软件注册|19用户批量设置|20培训课程管理|21用户KEY验证失败|22退出系统|23员工离职]
	   * @param remark
	   *          日志说明
	   * @param userId
	   *          用户Id
	   * @param ip
	   *          IP地址
	   * @throws Exception
	   */
	  public static void addSysLog(Connection conn, String type, String remark, String userId, String ip) throws Exception {
	      String sql="INSERT INTO SYS_LOG (ACCOUNT_ID,LOG_TYPE,LOG_DATE,IP,REMARK)VALUES(?,?,?,?,?)";
	      PreparedStatement ps = conn.prepareStatement(sql);
	      ps.setString(1,userId); 
	      ps.setString(2, type);
	      ps.setString(3,SysTool.getCurDateTimeStr(DateConst.VALUE_LONG_DATE24));  ;
	      ps.setString(4, ip);
	      ps.setString(5, remark);	
	      ps.executeUpdate();
	      ps.close();
	  }
}
