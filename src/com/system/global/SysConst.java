package com.system.global;

import java.util.Map;

public class SysConst {
	  /***秒 */
	  public static final long DT_S = 1000;
	  /***分*/
	  public static final long DT_MINIT = DT_S * 60;
	  /***时*/
	  public static final long DT_H = DT_MINIT * 60;
	  /***天*/
	  public static final long DT_D = DT_H * 24;
	  /***月*/
	  public static final long DT_MONTH = DT_D * 30;
	  /***年 */
	  public static final long DT_Y = DT_D * 365;
	  /***1k*/
	  public static final int K = 1024;
	  /***1M*/
	  public static final int M = K * K;
	  /***1G*/
	  public static final int G = M * M;
	  /***1T*/
	  public static final int T = G * G;
	  /***64K*/
	  public static final int K64 = K * 64;
	  /***最大一个2字节无符号数字 */
	  public static final int MAX_2_BYTES = K64 - 1;
	  /***中文编码名称*/
	  public static final String CHINA_CODE_NAME = "GBK";
	  /*** 缺省编码 */
	  public static final String DEFAULT_CODE = "UTF-8";
	  /***通用代码维护path前缀 */
	  public static final String COMMON_CODE_PATH_PREFIX = "basecode";
	  /***错误返回*/
	  public static final String RETURN_ERROR = "1";
	  /***正确返回*/
	  public static final String RETURN_OK = "0";
	  /***第三类数值返回(半对)(正确执行,但是未完全执行)*/
	  public static final String RETURN_MIDDLE = "-1";
	  /***一般返回 */
	  public static final String RETURNCHECK = "returnCheck";
	  /*** AJAX返回*/
	  public static final String RETURN_JSON = "returnJson";
	  /***json返回 */
	  public static final String RET_TYPE_JSON = "json";
	  /***XML返回 */
	  public static final String RET_TYPE_XML = "xml";
	  /***常量-是 */
	  public static final String YES = "1";
	  /***常量-否 */
	  public static final String NO = "0";
	  /*** 默认输入输出缓冲大小
	   */
	  public static final int DEFAULT_IO_BUFF_SIZE = K * 10;
	 
	  /**
	   * 打开方式定义-默认，到主客户区
	   */
	  public static final String OPEN_MANNER_CLIENT_MAIN = "0";
	  /**
	   * 打开方式定义-默认，弹开新窗口

	   */
	  public static final String OPEN_MANNER_POPNEW = "1";
	  /**
	   * 是否用作北方海外系统-是

	   */
	  public static final String USED_FOR_NORINCO_YES = "1";
	  /**
	   * 终端业务是否与总帐集成-是 0=不生成凭证;1=工具栏显示生成凭证/调阅凭证按钮;2=工具栏不显示生成凭证/调阅凭证按钮
	   */
	  public static final String RUN_WITH_VOUCH_DISPONTOOLBAR = "1";
	  /**
	   * 是否允许文件上传-允许
	   */
	  public static final String ALLOW_FILE_UPLOAD_NOT_YES = "0";
	  /**
	   * 是否允许文件上传-显示控件，但不允许上传

	   */
	  public static final String ALLOW_FILE_UPLOAD_NOT_NO = "1";
	  /**
	   * 是否允许文件上传-不显示控件

	   */
	  public static final String ALLOW_FILE_UPLOAD_NOT_DISP = "2";
	  
	  /**
	   * 系统登陆用户session里的关键字 --- add by lh 1月22日

	   */
	  public static final String LOGIN_USER = "ACCOUNT_ID";
	  
	  /**
	   * UsbKey 的系统识别码
	   */
	  public static final String KEY_TD_SIGN = "C2C238A0";
	  /**
	   * 系统参数对象键名-session
	   */
	  public static final String SYS_PARAM_KEY = "sysParamBean";
	  /**
	   * FORMBEAN-request
	   */
	  public static final String ACTION_FORM_BEAN = "actionFormBean";
	  /**
	   * 系统活动资源-request
	   */
	  public static final String ACTION_RESOURCES = "actionResources";
	  /**
	   * 系统用户信息对象键名-session
	   */
	  public static final String USER_INFO_KEY = "userInfoBean";
	  /**
	   * i18n工具对象-request
	   */
	  public static final String I18N_UTILITY = "i18nUtilityBean";
	  /**
	   * 辅助核算项目类型集合-session
	   */
	  public static final String ACCT_ITEM_TYPE_SET = "acctItemTypeSet";
	  
	  /**
	   * 结算方式-session
	   */
	  public static final String RP_MODE_SET = "rpModeSet";
	  /**
	   * 业务处理类型-session
	   */
	  public static final String OPTS_TYPE_SET = "optsTypeSet";
	  /**
	   * 币种列表-session
	   */
	  public static final String CURR_LIST = "currList";
	  /**
	   * 汇率列表-session
	   */
	  public static final String EXCH_RATE_LIST = "exchRateList";
	  /**
	   * 活动消息列表对象键名-request
	   */
	  public static final String ACTION_MSRG_LIST = "actionMsrgList";
	  /**
	   * 数据库连接管理器-request 
	   */
	  public static final String REQUEST_DB_CONN_MGR = "requestDbConnMgr";
	  /**
	   * Request
	   */
	  public static final String CURR_REQUEST = "currRequest";
	  public static final String CURR_REQUEST_FLAG = "currRequest";
	  public static final String CURR_REQUEST_ADDRESS = "currRequestAddress";
	  /**
	   * 嵌套调用其他Action过程中参数传递哈希表
	   * 为了处理多层嵌套，请按如下算法设置

	   * oldParamMap = request.getAttribute(TDCBeanKeys.PARAM_MAP_INNER);
	   * if (oldParamMap != null) {request.removeAttribute(TDCBeanKeys.PARAM_MAP_INNER);}
	   * 设置 request.setAttribute(TDCBeanKeys.PARAM_MAP_INNER, newParamMap);
	   * 执行新Action，并返回
	   * if (oldParamMap != null) {request.setAttribute(TDCBeanKeys.PARAM_MAP_INNER, oldParamMap);}
	   */
	  public static final String PARAM_MAP_INNER = "paramMapInner";
	  /**
	   * 系统活动集合对象键名-App
	   */
	  public static final String ACTION_SET = "actionSet";
	  /**
	   * 数据库表表列表-App
	   */
	  public static final String TABLE_LIST = "tableList";
	  /**
	   * 报表数据列表控件-request
	   */
	  public static final String REPT_LIST_CNTRL = "reptListCntrl";
	  /**
	   * 附加信息-session
	   */
	  public static final String ADD_INF_SET = "addInfSet";
	  /**
	   * 临时表清理-App
	   */
	  public static final String RELEASE_THREAD = "releaseThread";
	  /**
	   * 科目扩展属性列表-session
	   */
	  public static final String SUB_EXT_SORT_LIST = "subExtSortList";
	  /**
	   * 数据权限-session
	   */
	  public static final String DATA_PRIV_LIST = "dataPrivateList";
	  /**
	   * 固定资产设置-session
	   */
	  public static final String FIXASST_SETTINGS = "fixAsstSettings";
	  /**
	   * 报表查询参数列表-session
	   */
	  public static final String REPT_QUERY_PARAM_LIST = "reptQueryParamList";
	  /**
	   * 凭证字-session
	   */
	  public static final String VOUCH_WORD_LIST = "vouchWordList";
	  /**
	   * 凭证摘要-session
	   */
	  public static final String VOUCH_SUMMARY_LIST = "vouchSummaryList";
	  
	  /**
	   * 通用数据加载对象名称-request
	   */
	  public static final String DATA_OBJ_KEY = "dataObjectKey";
	  /**
	   * 辅助核算项目数据对象-request
	   */
	  public static final String ACCT_ITEM_DATA_LIST = "acctItemDataList";
	  /**
	   * 科目数据对象-request
	   */
	  public static final String SUBJECT_DATA_LIST = "subjectDataList";
	  /**
	   * 期间定义列表-request
	   */
	  public static final String PERIOAD_DATA_LIST = "periodDataList";
	  /** 活动处理类 **/
	  public static final String ACT_CLASS = "actClass";  
	  /** 活动处理方法 **/
	  public static final String ACT_METHOD = "actMethod";
	  /** Web应用的路径 **/
	  public static final String ACT_CTX_PATH = "act.ctxPath";
	  /** 活动处理返回对象 **/
	  public static final String ACT_RET_OBJ = "act.retobj";
	  /** 返回类别 **/
	  public static final String RET_TYPE = "actRettype";
	  /** 返回路径 **/
	  public static final String FORWARD_PATH = "act.retpath";
	  /** 返回方法 **/
	  public static final String RET_METHOD = "act.retmethod";
	  /** 返回方法-FORWARD **/
	  public static final String RET_METHOD_FORWARD = "forward";
	  /** 返回方法-REDIRECT **/
	  public static final String RET_METHOD_REDIRECT = "redirect";
	  /** 返回状态 **/
	  public static final String RET_STATE = "act.retstate";
	  /** 返回消息 **/
	  public static final String RET_MSRG = "act.retmsrg";
	  /** 返回数据 **/
	  public static final String RET_DATA = "act.retdata";
	  /** Form提交后生成的数据结构类 **/
	  public static final String DTO_CLASS = "dtoClass";
	  //上传文件源文件名称;
	  public static final java.lang.String UPLOAD_FILE_NAME_ORGN = "uploadFileNameOrgn";
	  //上传文件
	  public static final java.lang.String UPLOAD_FILE_NAME_SERVER = "uploadFileNameServer";
	  //sessionId
	  public static final String USER_SESSION_ID = "sessionId";
	  /** Windows文件路径分隔符 **/
	  public static final String PATH_SPLIT_FILE_WIN = "\\";
	  /** Unix文件路径分隔符 **/
	  public static final String PATH_SPLIT_FILE_UNIX = "/";
	  /** 网络路径分隔符 **/
	  public static final String PATH_SPLIT_URL = "/";
	  /** 扩展名分隔符 **/
	  public static final String PATH_POINT = ".";
	  /**
	   * 机器码

	   */
	  public static final String MACHINE_CODE = "machineCode";
	  /**
	   * 软件唯一标识序列号，技术支持用于检查客户的合法性

	   */
	  public static final String SERIAL_ID = "serial.id";
	  /**
	   * 系统名称
	   */
	  public static final String SYS_NAME = "sysname";
	  /**
	   * T9平台安装时间
	   */
	  public static final String INSTALL_TIME = "install.time";
	  /**
	   * 注册给机构名称

	   */
	  public static final String REGIST_ORG = "regist.org";
	  /**
	   * 系统用户数

	   */
	  public static final String USER_CNT = "user.cnt";
	  /**
	   * 系统-T9
	   */
	  public static final String SYS_T9 = "t9";

	  /**
	   * 属性签名名称-用于注册
	   */
	  public static final String REGIST_PROPKEY_DIGIST_POSTFIX = ".Didgist";
	  /**
	   * 取得加密密码
	   * @param params     用于取得密码的参数，用于扩展用

	   * @return
	   */
	  public static char[] getPassword(Map params) {
	    return "BLMDfSiSEUSeRnwxL89HnBbCUgBsYBjDbvHJGA==".toCharArray();
	  }
	  
	  /**
	   * 取得MD5算法干扰字节长度
	   * @param params     用于取得MD5算法干扰字节长度，用于扩展用
	   * @return
	   */
	  public static int getMD5SaltLength(Map params) {
	    return 12;
	  }
	  
	  /**
	   * 取得Salt
	   * @param params     用于取得Salt的参数，用于扩展用

	   * @return
	   */
	  public static byte[] getSalt(Map params) { 
	    byte[] salt = new byte[]{
	        (byte)1, (byte)2, (byte)3, (byte)4, 
	        (byte)5, (byte)6, (byte)7, (byte)8};
	    return salt;
	  }
	  /**
	   * 取得迭代次数
	   * @param params     用于取得迭代次数的参数，用于扩展用

	   * @return
	   */
	  public static int getItCnt(Map params) {
	    return 3;
	  }
	  /**
	   * 取得最大试用天数

	   * @param params     用于最大试用天数的参数，用于扩展用
	   * @return
	   */
	  public static int getMaxEvalueDayCnt(Map params) {
	    return 100;
	  }
	  public static final String DSPARAM_DRIVER_CLASS_NAME = "driverClassName";
	  public static final String DSPARAM_URL = "url";
	  public static final String DSPARAM_USER_NAME = "username";
	  public static final String DSPARAM_PASS_WORD = "password";
	  public static final String DSPARAM_MAX_ACTIVE = "maxActive";
	  public static final String DSPARAM_MAX_IDLE = "maxIdle";
	  public static final String DSPARAM_MAX_WAIT = "maxWait";
	  public static final String DSPARAM_DEFAULT_AUTO_COMMIT = "defaultAutoCommit";
	  public static final String DSPARAM_DEFAULT_READ_ONLY = "defaultReadOnly";
	 
	  /**
	   * 系统数据库名称

	   */
	  public static final String SYS_DB_NAME = "sysDatabaseName";
	  /**
	   * 帐套库键值

	   */
	  public static final String ACSET_DB_NO = "acsetDatabaseNo";
	  /**
	   * 系统数据库连接

	   */
	  public static final String SYS_DB_CONN = "sysDbConnection";
	  /**
	   * 帐套数据库连接

	   */
	  public static final String ACSET_DB_CONN = "acsetDbConnection";
	  /**
	   * 数据库类型-系统库

	   */
	  public static final String DB_TYPE_SYS = "S";
	  /**
	   * 数据库类型-帐套库

	   */
	  public static final String DB_TYPE_ACSET = "A";
	  /**
	   * 数据库类型-其他系统库

	   */
	  public static final String DB_TYPE_OTHER = "O";
	  /**
	   * 系统是否已注册，该值在第一次验证完成后已记录在session中
	   */
	  public static final String SYS_IS_REGISTERED = "sysIsRegistered";
}
