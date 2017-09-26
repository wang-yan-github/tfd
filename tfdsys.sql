/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : tfdsys

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2017-02-22 15:22:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '系统账号',
  `PASS_WORD` varchar(150) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `PASSWORD_TYPE` varchar(6) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码类型',
  `THMEM` varchar(6) CHARACTER SET utf8 DEFAULT NULL COMMENT '系统主题',
  `USER_ID` varchar(33) CHARACTER SET utf8 DEFAULT NULL,
  `USER_PRIV` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户权限',
  `PAGE_PRIV` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `NOT_LOGIN` varchar(3) CHARACTER SET utf8 DEFAULT NULL COMMENT '账号状态 默认0，离职为1',
  `ON_LINE` int(11) DEFAULT NULL COMMENT '在线状态',
  `LAST_VISIT_TIME` datetime DEFAULT NULL COMMENT '最后在线时间',
  `LANGUAGE` varchar(6) CHARACTER SET utf8 DEFAULT NULL COMMENT '语言',
  `BY_NAME` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户别名',
  `LOGIN_NUM` int(11) DEFAULT '0' COMMENT '登陆次数',
  `LAST_LOGIN_TIME` varchar(150) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后登录时间',
  `UPDATE_PASSWORD_TIME` varchar(150) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后修改密码时间',
  `ORG_ID` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `APP_ICON` text CHARACTER SET utf8 COMMENT 'app 模块权限',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4898 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'admin', '96E79218965EB72C92A549DD5A330112', '1', '1', null, '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '0', '3233520', null, '1', 'administrator', '0', '2017-01-23 17:04:55', '2016-10-19 22:12:48', '8EADB678-A646-1E51-3E87-75A547B8AF19', '[{\"id\":\"1\",\"name\":\"日程安排\",\"selected\":\"1\",\"sort\":1},{\"id\":\"2\",\"name\":\"工作日志\",\"selected\":\"1\",\"sort\":2},{\"id\":\"4\",\"name\":\"定位考勤\",\"selected\":\"1\",\"sort\":3},{\"id\":\"5\",\"name\":\"工作审批\",\"selected\":\"1\",\"sort\":4},{\"id\":\"6\",\"name\":\"客户管理\",\"selected\":\"1\",\"sort\":5},{\"id\":\"1\",\"name\":\"日程安排\",\"selected\":\"1\",\"sort\":1},{\"id\":\"2\",\"name\":\"工作日志\",\"selected\":\"1\",\"sort\":2},{\"id\":\"1\",\"name\":\"日程安排\",\"selected\":\"1\",\"sort\":1},{\"id\":\"2\",\"name\":\"工作日志\",\"selected\":\"1\",\"sort\":2}]');
INSERT INTO `account` VALUES ('4894', 'aa', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '1', '', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '0', null, null, '1', '', '0', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19', '[{\"id\":\"1\",\"name\":\"日程安排\",\"selected\":\"1\",\"sort\":1},{\"id\":\"2\",\"name\":\"工作日志\",\"selected\":\"1\",\"sort\":2}]');
INSERT INTO `account` VALUES ('4895', 'bb', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '1', '', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '0', null, null, '1', '', '0', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19', null);
INSERT INTO `account` VALUES ('4896', 'cc', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '1', '', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '0', null, null, '1', '', '0', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19', null);
INSERT INTO `account` VALUES ('4897', 'dd', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '1', '', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '0', null, null, '1', '', '0', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19', null);

-- ----------------------------
-- Table structure for affair
-- ----------------------------
DROP TABLE IF EXISTS `affair`;
CREATE TABLE `affair` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AFFAIR_ID` varchar(50) DEFAULT NULL,
  `CALENDAR_ID` varchar(50) DEFAULT NULL,
  `CAL_AFF_TYPE` varchar(50) DEFAULT NULL,
  `REMIND_TYPE` varchar(50) DEFAULT NULL COMMENT '提醒类型',
  `REMIND_DATE` varchar(50) DEFAULT NULL COMMENT '提醒日期()',
  `REMIND_TIME` varchar(50) DEFAULT NULL COMMENT '提醒时间',
  `IS_WEEK` varchar(50) DEFAULT NULL COMMENT '是否排除周末',
  `FREQUENCY` varchar(50) DEFAULT NULL COMMENT '间隔',
  `END_WHILE` varchar(50) DEFAULT NULL COMMENT '结束日期',
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of affair
-- ----------------------------

-- ----------------------------
-- Table structure for affair_copy
-- ----------------------------
DROP TABLE IF EXISTS `affair_copy`;
CREATE TABLE `affair_copy` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AFFAIR_ID` varchar(50) DEFAULT NULL,
  `CALENDAR_ID` varchar(50) DEFAULT NULL,
  `CAL_AFF_TYPE` varchar(50) DEFAULT NULL,
  `REMIND_TYPE` varchar(50) DEFAULT NULL,
  `REMIND_DATE` varchar(50) DEFAULT NULL,
  `REMIND_TIME` varchar(50) DEFAULT NULL,
  `BEFORE_TIME` varchar(50) DEFAULT NULL,
  `IS_WEEK` varchar(50) DEFAULT NULL,
  `FREQUENCY` varchar(50) DEFAULT NULL,
  `END_WHILE` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of affair_copy
-- ----------------------------

-- ----------------------------
-- Table structure for api_grade
-- ----------------------------
DROP TABLE IF EXISTS `api_grade`;
CREATE TABLE `api_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of api_grade
-- ----------------------------
INSERT INTO `api_grade` VALUES ('1', '一年级');
INSERT INTO `api_grade` VALUES ('2', '二年级');

-- ----------------------------
-- Table structure for api_index
-- ----------------------------
DROP TABLE IF EXISTS `api_index`;
CREATE TABLE `api_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `path` varchar(200) DEFAULT NULL,
  `text_diy` varchar(500) DEFAULT NULL,
  `api_content` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of api_index
-- ----------------------------
INSERT INTO `api_index` VALUES ('1', '添加API', '0', '/api/api-manage/edit.jsp', '<span class=\'glyphicon glyphicon-pencil\'></span>#textDiy#', null);
INSERT INTO `api_index` VALUES ('3', '地区选择', '0', '/api/district/index.jsp', null, null);
INSERT INTO `api_index` VALUES ('5', '多处文件上传', '0', '/api/fileupload/files-upload.jsp', null, null);
INSERT INTO `api_index` VALUES ('6', 'easyui 分页', '0', '/api/paging/easyui.jsp', null, null);
INSERT INTO `api_index` VALUES ('8', '老师 - 学生 的ztree树', '0', '/api/tree/ztree-grade-student.jsp', null, null);
INSERT INTO `api_index` VALUES ('10', '数据导入', '0', '/api/dataimport/index.jsp', null, null);
INSERT INTO `api_index` VALUES ('11', '数据导出', '0', '/api/dataexport/index.jsp', null, null);
INSERT INTO `api_index` VALUES ('34', '系统页签API', '0', null, null, '<p>/system/jsall/SysFrame.js</p>\r\n\r\n<p><strong>调用方式：</strong></p>\r\n\r\n<p><strong>&nbsp;&nbsp; &nbsp;1.添加页签</strong><br />\r\n&nbsp; &nbsp; new SysFrame().tabs(&#39;add&#39;,{<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;title: &quot;标题&quot;,<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;url:&ldquo;地址&rdquo;,<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;content:&ldquo;页签内容，非iframe方式&rdquo;<br />\r\n&nbsp;&nbsp; &nbsp;});</p>\r\n\r\n<div>\r\n<div>&nbsp; &nbsp;&nbsp;<strong>2.更新页签，如果不存在创建新的，否在更新页签地址或内容</strong><br />\r\n&nbsp; &nbsp; new SysFrame().tabs(&#39;update&#39;,{<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;title: &quot;标题&quot;,<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;url:&ldquo;地址&rdquo;,<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;content:&ldquo;页签内容，非iframe方式&rdquo;<br />\r\n&nbsp;&nbsp; &nbsp;});</div>\r\n</div>\r\n\r\n<p>&nbsp; &nbsp; <strong>3.关闭页签</strong><br />\r\n&nbsp; &nbsp; new SysFrame().tabs(&#39;close&#39;,&#39;页签标题&#39;);</p>\r\n\r\n<div>&nbsp; &nbsp;&nbsp;<strong>4.页签选中</strong><br />\r\n&nbsp; &nbsp; new SysFrame().tabs(&#39;select&#39;,&#39;页签标题&#39;);\r\n<div>&nbsp;</div>\r\n\r\n<div>&nbsp; &nbsp;&nbsp;<strong>5.页签是否存在</strong><br />\r\n&nbsp; &nbsp; new SysFrame().tabs(&#39;exists&#39;,&#39;页签标题&#39;);</div>\r\n</div>\r\n\r\n<p>&nbsp; &nbsp;&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n');
INSERT INTO `api_index` VALUES ('14', '表单验证', '0', '/api/bootstrap/formvalidation/index.jsp', null, null);
INSERT INTO `api_index` VALUES ('32', 'js 正则库、验证工具', '0', null, null, '<h4>/system/jsall/RegexUtil.js</h4>\r\n\r\n<h2>调用方式:</h2>\r\n\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp; var regexUtil=new RegexUtil();<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;var str=&quot;0551-85956003&quot;;<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;var check=regexUtil.test(str,regexUtil.REGEX_TELEPHONE);<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;if(check){<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;<br />\r\n&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;}</p>\r\n');
INSERT INTO `api_index` VALUES ('33', 'java 正则库、验证工具', '0', null, null, '<h4>/com/system/tool/RegexUtil.java</h4>\r\n\r\n<h2>调用方式:</h2>\r\n\r\n<p>&nbsp; &nbsp; boolean check=RegexUtil.getInstance(RegexUtil.REGEX_INT_0_).test(sort);</p>\r\n\r\n<p>&nbsp; &nbsp; if (!check) { }</p>\r\n');
INSERT INTO `api_index` VALUES ('35', 'Web IM', '0', '/api/im/index.jsp', null, null);

-- ----------------------------
-- Table structure for api_student
-- ----------------------------
DROP TABLE IF EXISTS `api_student`;
CREATE TABLE `api_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL COMMENT '所在年级',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of api_student
-- ----------------------------

-- ----------------------------
-- Table structure for approval_meeting
-- ----------------------------
DROP TABLE IF EXISTS `approval_meeting`;
CREATE TABLE `approval_meeting` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APPROVAL_ID` varchar(100) DEFAULT NULL,
  `MEETING_ID` varchar(100) DEFAULT NULL,
  `APPROVAL_CONTENT` text,
  `ACCOUNT_ID` varchar(200) DEFAULT NULL,
  `APPROVAL_TIME` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of approval_meeting
-- ----------------------------

-- ----------------------------
-- Table structure for approval_notice
-- ----------------------------
DROP TABLE IF EXISTS `approval_notice`;
CREATE TABLE `approval_notice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APPROVAL_ID` varchar(100) DEFAULT NULL,
  `NOTICE_ID` varchar(100) DEFAULT NULL,
  `APPROVAL_CONTENT` text,
  `ACCOUNT_ID` varchar(100) DEFAULT NULL,
  `APPROVAL_TIME` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of approval_notice
-- ----------------------------

-- ----------------------------
-- Table structure for approval_notice_power
-- ----------------------------
DROP TABLE IF EXISTS `approval_notice_power`;
CREATE TABLE `approval_notice_power` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `POWER_ID` varchar(100) DEFAULT NULL,
  `NOTICE_TYPE` varchar(50) DEFAULT NULL,
  `APPROVAL_STAFF` text,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of approval_notice_power
-- ----------------------------
INSERT INTO `approval_notice_power` VALUES ('5', 'DA21CA63-69F8-22B9-248C-3FA506D7DFA2', '部门公告', 'gj,admin', '1');
INSERT INTO `approval_notice_power` VALUES ('6', '07859FBB-3B33-20DA-507E-F9C98F690D21', '通知', 'caocao,zhaoyang', '1');
INSERT INTO `approval_notice_power` VALUES ('7', 'B36ACEFC-B25D-5ECB-F771-6B9719887530', '决定', 'liubei', '1');
INSERT INTO `approval_notice_power` VALUES ('8', 'DCBE9571-FA75-CD6F-4DD5-34ED5BD0735E', '其他', 'liubei', '1');
INSERT INTO `approval_notice_power` VALUES ('9', 'E91D67F7-34B9-29F1-40D9-EF5712C61EF1', '部门公告', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `approval_notice_power` VALUES ('10', 'FBFA6FB1-412D-4BC1-FAF0-20E1EC7CA75B', '通知', '刘哥133', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `approval_notice_power` VALUES ('11', 'C5382A27-4E5D-ACDF-099D-AD18018C6640', '公告', 'cst', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for app_icon
-- ----------------------------
DROP TABLE IF EXISTS `app_icon`;
CREATE TABLE `app_icon` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ICON_ID` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODULE_NAME` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORG_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SORT` int(11) DEFAULT NULL,
  `SELECTED` int(1) DEFAULT NULL,
  KEY `ID` (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of app_icon
-- ----------------------------
INSERT INTO `app_icon` VALUES ('1', '1', '日程安排', '8EADB678-A646-1E51-3E87-75A547B8AF19', '1', '1');
INSERT INTO `app_icon` VALUES ('2', '2', '工作日志', '8EADB678-A646-1E51-3E87-75A547B8AF19', '2', '1');
INSERT INTO `app_icon` VALUES ('4', '4', '定位考勤', '8EADB678-A646-1E51-3E87-75A547B8AF19', '3', '1');
INSERT INTO `app_icon` VALUES ('5', '5', '工作审批', '8EADB678-A646-1E51-3E87-75A547B8AF19', '4', '1');
INSERT INTO `app_icon` VALUES ('6', '6', '客户管理', '8EADB678-A646-1E51-3E87-75A547B8AF19', '5', '1');
INSERT INTO `app_icon` VALUES ('17', '1', '日程安排', '528A83B2-473C-8E78-0938-2056B0299E29', '1', '1');
INSERT INTO `app_icon` VALUES ('18', '2', '工作日志', '528A83B2-473C-8E78-0938-2056B0299E29', '2', '1');
INSERT INTO `app_icon` VALUES ('19', '3', '定位考勤', '528A83B2-473C-8E78-0938-2056B0299E29', '3', '1');
INSERT INTO `app_icon` VALUES ('20', '4', '工作审批', '528A83B2-473C-8E78-0938-2056B0299E29', '4', '1');
INSERT INTO `app_icon` VALUES ('21', '5', '客户管理', '528A83B2-473C-8E78-0938-2056B0299E29', '5', '1');
INSERT INTO `app_icon` VALUES ('22', '1', '日程安排', '86263DFD-D1F1-13DD-DA73-515B451D6C69', '1', '1');
INSERT INTO `app_icon` VALUES ('23', '2', '工作日志', '86263DFD-D1F1-13DD-DA73-515B451D6C69', '2', '1');
INSERT INTO `app_icon` VALUES ('24', '3', '定位考勤', '86263DFD-D1F1-13DD-DA73-515B451D6C69', '3', '1');
INSERT INTO `app_icon` VALUES ('25', '4', '工作审批', '86263DFD-D1F1-13DD-DA73-515B451D6C69', '4', '1');
INSERT INTO `app_icon` VALUES ('26', '5', '客户管理', '86263DFD-D1F1-13DD-DA73-515B451D6C69', '5', '1');
INSERT INTO `app_icon` VALUES ('27', '1', '日程安排', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E', '1', '1');
INSERT INTO `app_icon` VALUES ('28', '2', '工作日志', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E', '2', '1');
INSERT INTO `app_icon` VALUES ('29', '3', '定位考勤', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E', '3', '1');
INSERT INTO `app_icon` VALUES ('30', '4', '工作审批', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E', '4', '1');
INSERT INTO `app_icon` VALUES ('31', '5', '客户管理', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E', '5', '1');
INSERT INTO `app_icon` VALUES ('32', '1', '日程安排', '36972402-A043-6A64-775F-308D06FDC242', '1', '1');
INSERT INTO `app_icon` VALUES ('33', '2', '工作日志', '36972402-A043-6A64-775F-308D06FDC242', '2', '1');
INSERT INTO `app_icon` VALUES ('34', '3', '定位考勤', '36972402-A043-6A64-775F-308D06FDC242', '3', '1');
INSERT INTO `app_icon` VALUES ('35', '4', '工作审批', '36972402-A043-6A64-775F-308D06FDC242', '4', '1');
INSERT INTO `app_icon` VALUES ('36', '5', '客户管理', '36972402-A043-6A64-775F-308D06FDC242', '5', '1');
INSERT INTO `app_icon` VALUES ('37', '1', '日程安排', '2A096A25-4E62-8426-F80F-6522BB06FF3F', '1', '1');
INSERT INTO `app_icon` VALUES ('38', '2', '工作日志', '2A096A25-4E62-8426-F80F-6522BB06FF3F', '2', '1');
INSERT INTO `app_icon` VALUES ('39', '3', '定位考勤', '2A096A25-4E62-8426-F80F-6522BB06FF3F', '3', '1');
INSERT INTO `app_icon` VALUES ('40', '4', '工作审批', '2A096A25-4E62-8426-F80F-6522BB06FF3F', '4', '1');
INSERT INTO `app_icon` VALUES ('41', '5', '客户管理', '2A096A25-4E62-8426-F80F-6522BB06FF3F', '5', '1');

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ATTACHMENT_ID` varchar(2000) DEFAULT NULL,
  `ATTACHMENT_NAME` varchar(2000) DEFAULT NULL,
  `UP_TIME` varchar(20) DEFAULT NULL,
  `CREATE_ACCOUNT_ID` varchar(2000) DEFAULT NULL,
  `USE_COUNT` int(11) DEFAULT NULL,
  `USE_USER` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `PATH` varchar(1000) DEFAULT NULL COMMENT '服务器文件路径',
  `MODULES` varchar(2000) DEFAULT NULL,
  `DEL_FLAG` varchar(2) DEFAULT NULL,
  `PRIV` varchar(255) DEFAULT NULL,
  `CONFIG_NO` varchar(2) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  `FILE_SIZE` int(11) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `ID` (`Id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=2392 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attachment
-- ----------------------------

-- ----------------------------
-- Table structure for attend
-- ----------------------------
DROP TABLE IF EXISTS `attend`;
CREATE TABLE `attend` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATTEND_ID` varchar(50) DEFAULT NULL,
  `REGIST_TIME` varchar(100) DEFAULT NULL COMMENT '登记时间',
  `REGIST_TYPE` varchar(50) DEFAULT NULL COMMENT '登记类型',
  `REMARK` varchar(400) DEFAULT NULL COMMENT '说明情况',
  `PICTRUE` varchar(400) DEFAULT NULL COMMENT '图片',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '考勤状态(1-正常，2-迟到，3-早退)',
  `LON` varchar(200) DEFAULT NULL COMMENT '经度',
  `LAT` varchar(200) DEFAULT NULL COMMENT '纬度',
  `ADDRESS` varchar(200) DEFAULT NULL COMMENT '地址',
  `DETAIL` varchar(500) DEFAULT NULL,
  `FROM_TYPE` varchar(50) DEFAULT NULL COMMENT '考勤来源(1-PC端，2-手机端，3-考勤机)',
  `IS_VALID` varchar(50) DEFAULT NULL COMMENT '是否有效(0-无效，1-有效)',
  `ATTEND_TIME_ID` varchar(50) DEFAULT NULL COMMENT '考勤时间表Id',
  `ACCOUNT_ID` varchar(50) DEFAULT NULL COMMENT '用户id',
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=279 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attend
-- ----------------------------

-- ----------------------------
-- Table structure for attend_config
-- ----------------------------
DROP TABLE IF EXISTS `attend_config`;
CREATE TABLE `attend_config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATTEND_CONFIG_ID` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  `CONFIG_NAME` varchar(150) CHARACTER SET utf8 DEFAULT NULL COMMENT '排版类型名称',
  `CONFIG_TYPE` varchar(150) CHARACTER SET utf8 DEFAULT NULL COMMENT '排班班制 1-两班制，2-四班制，3-六班制',
  `ATTEND_TIME_ID` varchar(150) CHARACTER SET utf8 DEFAULT NULL COMMENT '考勤时间表Id1',
  `ATTEND_TIME_ID2` varchar(150) CHARACTER SET utf8 DEFAULT NULL COMMENT '考勤时间表Id2',
  `ATTEND_TIME_ID3` varchar(150) CHARACTER SET utf8 DEFAULT NULL COMMENT '考勤时间表Id3',
  `PUBLIC_DAY` varchar(400) CHARACTER SET utf8 DEFAULT NULL COMMENT '公休日',
  `ORG_ID` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of attend_config
-- ----------------------------
INSERT INTO `attend_config` VALUES ('23', 'F7F39009-B2B9-5BE1-B30B-957496ABB005', '正常班', '1', '927D6461-FCEC-C37F-1E60-138B60CACEDF', '', '', '6,7', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `attend_config` VALUES ('24', '8DD5EC1B-DBDB-1965-1CF8-00F7EA34A9C7', '正常班2', '2', '70F934F0-94FD-18A2-32A7-B441DB50A144', '9852F9CF-6DCC-DC31-7316-A481B7DDEB9B', '', '6,7', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `attend_config` VALUES ('25', '7F5C637F-16EE-40DA-8401-DC7A941C0A81', '全天班', '3', '22E215A8-8CC3-1211-CECC-9125186FF42F', '757FE87E-3222-F657-F5AB-BB119700E6DE', '099C2A96-DF3F-8C92-E205-AB522F92E2C2', '6,7', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `attend_config` VALUES ('26', '25F3202D-C4AD-96A9-D3EB-9CD1839CF57E', '正常班', '1', 'D7997CBC-16FF-0D0A-6193-7DCA7B89FC4E', null, null, null, '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `attend_config` VALUES ('27', 'FC026163-8731-EA20-CC62-CCA2D56D4A5A', '正常班', '1', 'D1E0E322-9D40-BCE8-4DEB-6EF417A69B68', null, null, null, '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `attend_config` VALUES ('28', '37F2B84F-3B4F-46DE-55BC-8FEC8D89B3BD', '正常班', '1', '6C5F1D2F-B0BB-D7C0-A6ED-422828237B43', null, null, null, '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `attend_config` VALUES ('29', '65E669E6-65A2-6A60-B68A-844C1CA6EDB3', '正常班', '1', 'DDBCFCF0-7A8B-837A-222A-DBC2C056E207', null, null, null, '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `attend_config` VALUES ('30', 'B6175A87-B99F-43AC-4625-E096803B80E5', '正常班', '1', '84429DC0-06A1-0D63-F6D8-0EC37BEBBE8B', null, null, null, '2A096A25-4E62-8426-F80F-6522BB06FF3F');

-- ----------------------------
-- Table structure for attend_holiday
-- ----------------------------
DROP TABLE IF EXISTS `attend_holiday`;
CREATE TABLE `attend_holiday` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `HOLIDAY_ID` varchar(50) DEFAULT NULL,
  `HOLIDAY_NAME` varchar(50) DEFAULT NULL,
  `START_DATE` varchar(200) DEFAULT NULL,
  `END_DATE` varchar(200) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attend_holiday
-- ----------------------------

-- ----------------------------
-- Table structure for attend_regist
-- ----------------------------
DROP TABLE IF EXISTS `attend_regist`;
CREATE TABLE `attend_regist` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATTEND_REGIST_ID` varchar(50) DEFAULT NULL,
  `BEFORE_WORK` varchar(50) DEFAULT NULL COMMENT '上班多久前可以考勤',
  `AFTER_WORK` varchar(50) DEFAULT NULL COMMENT '上班多久后可以考勤',
  `BEFORE_BACK` varchar(50) DEFAULT NULL COMMENT '下班多久前可以考勤',
  `AFTER_BACK` varchar(50) DEFAULT NULL COMMENT '上班多久后可以考勤',
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attend_regist
-- ----------------------------
INSERT INTO `attend_regist` VALUES ('3', '80ACD43F-9B1F-3E0E-2E20-C4752BCCDD55', '10', '30', '10', '15', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for attend_time
-- ----------------------------
DROP TABLE IF EXISTS `attend_time`;
CREATE TABLE `attend_time` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATTEND_TIME_ID` varchar(150) DEFAULT NULL,
  `ATTEND_CONFIG_ID` varchar(50) DEFAULT NULL COMMENT '排班类型Id',
  `TIME1` varchar(150) DEFAULT NULL COMMENT '时间1',
  `TIME2` varchar(150) DEFAULT NULL COMMENT '时间2',
  `TIME_TYPE1` varchar(150) DEFAULT NULL COMMENT '时间类型1',
  `TIME_TYPE2` varchar(150) DEFAULT NULL COMMENT '时间类型2',
  `ORG_ID` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attend_time
-- ----------------------------

-- ----------------------------
-- Table structure for bgypsld
-- ----------------------------
DROP TABLE IF EXISTS `bgypsld`;
CREATE TABLE `bgypsld` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RUN_ID` varchar(50) DEFAULT NULL,
  `CHILD_RUN_ID` varchar(200) DEFAULT NULL,
  `PARENT_RUN_ID` varchar(50) DEFAULT NULL,
  `BM` text,
  `NAME` text,
  `ZHIWEI` text,
  `SQWP` varchar(100) DEFAULT NULL,
  `SHULIANG` varchar(20) DEFAULT NULL,
  `SQTIME` text,
  `SYR` varchar(10) DEFAULT NULL,
  `SFSXS` text,
  `BMLDQM` text,
  `XZLDQM` text,
  `SQRESONE` text,
  `BEIZHU` text,
  `BMSPYI` text,
  `XZSPYJ` text,
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bgypsld
-- ----------------------------
INSERT INTO `bgypsld` VALUES ('1', '76246F59-3945-443F-84CE-15BF5BC26FCD', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for boardroom
-- ----------------------------
DROP TABLE IF EXISTS `boardroom`;
CREATE TABLE `boardroom` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOARDROOM_ID` varchar(100) DEFAULT NULL,
  `BOARDROOM_NAME` varchar(500) DEFAULT NULL,
  `BOARDROOM_DEPICT` text,
  `BOARDROOM_STAFF` varchar(100) DEFAULT NULL,
  `DEPT_PRIV` text,
  `USER_PRIV` text,
  `BOARDROOM_NUM` varchar(50) DEFAULT NULL,
  `ALLOW_TIME` varchar(50) DEFAULT NULL,
  `EQUIPMENT` text,
  `BOARDROOM_ADDRESS` varchar(200) DEFAULT NULL,
  `BOARDROOM_SYSTEM` text,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of boardroom
-- ----------------------------

-- ----------------------------
-- Table structure for boardroomdevice
-- ----------------------------
DROP TABLE IF EXISTS `boardroomdevice`;
CREATE TABLE `boardroomdevice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOARDROOMDEVICE_ID` varchar(100) DEFAULT NULL,
  `DEVICE_ID` varchar(200) DEFAULT NULL,
  `DEVICE_NAME` varchar(200) DEFAULT NULL,
  `BOARDROOM_ID` varchar(100) DEFAULT NULL,
  `DEVICE_STATUS` varchar(1) DEFAULT NULL,
  `DEVICE_SIMILAR` varchar(1) DEFAULT NULL,
  `DEVICE_DESCRIPTION` text,
  `ORG_ID` varchar(50) DEFAULT NULL,
  `DEVICE_TYPE` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of boardroomdevice
-- ----------------------------

-- ----------------------------
-- Table structure for calendar
-- ----------------------------
DROP TABLE IF EXISTS `calendar`;
CREATE TABLE `calendar` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CALENDAR_ID` varchar(150) DEFAULT NULL,
  `CALENDAR_PID` varchar(50) DEFAULT NULL,
  `START_DATE` varchar(600) DEFAULT NULL,
  `END_DATE` varchar(600) DEFAULT NULL,
  `CONTENT` varchar(6000) DEFAULT NULL,
  `CAL_TYPE` varchar(765) DEFAULT NULL,
  `CAL_LEVEL` varchar(765) DEFAULT NULL,
  `CAL_AFF_TYPE` varchar(765) DEFAULT NULL,
  `ACCOUNT_ID` varchar(60) DEFAULT NULL,
  `BEFORE_TIME` varchar(50) DEFAULT NULL,
  `IS_SMS` varchar(200) DEFAULT NULL,
  `USER_ID` text,
  `FROM_ID` varchar(50) DEFAULT NULL,
  `DIARY_ID` varchar(50) DEFAULT NULL,
  `FROM_TYPE_ID` varchar(50) DEFAULT NULL,
  `FROM_TYPE` varchar(50) DEFAULT NULL,
  `STATUS` varchar(6) DEFAULT NULL,
  `AFFAIR_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=6975 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of calendar
-- ----------------------------

-- ----------------------------
-- Table structure for change_org
-- ----------------------------
DROP TABLE IF EXISTS `change_org`;
CREATE TABLE `change_org` (
  `CHANGE_ORG_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  `ACCOUNT_FLAG` varchar(50) DEFAULT NULL,
  `PASS_WORD` varchar(50) DEFAULT NULL,
  `BY_NAME` varchar(50) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of change_org
-- ----------------------------
INSERT INTO `change_org` VALUES ('8EADB678-A646-1E51-3E87-75A547B8AFDD', '8EADB678-A646-1E51-3E87-75A547B8AF19', 'admin,1,', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin', '1');
INSERT INTO `change_org` VALUES ('8EADB678-A646-1E51-3E87-75A547B8AFSS', 'BD0FA045-4584-E5B4-917A-3DAE47A0B5E4', 'admin,1,', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '1');

-- ----------------------------
-- Table structure for charts
-- ----------------------------
DROP TABLE IF EXISTS `charts`;
CREATE TABLE `charts` (
  `ID` int(11) DEFAULT NULL,
  `CHARTS_ID` varchar(150) DEFAULT NULL,
  `CHARTS_MODULE` varchar(600) DEFAULT NULL,
  `CHARTS_NAME` varchar(600) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of charts
-- ----------------------------
INSERT INTO `charts` VALUES ('6', '8EADB678-A646-1E51-3E87-75A547B8AF21', 'email', '邮件');
INSERT INTO `charts` VALUES ('7', '8EADB678-A646-1E51-3E87-75A547B8AF22', 'attach', '附件');
INSERT INTO `charts` VALUES ('8', '8EADB678-A646-1E51-3E87-75A547B8AF23', 'workflow', '工作流');
INSERT INTO `charts` VALUES ('9', '8EADB678-A646-1E51-3E87-75A547B8AF24', 'calendar', '日程');
INSERT INTO `charts` VALUES ('10', '8EADB678-A646-1E51-3E87-75A547B8AF25', 'attendance', '考勤');
INSERT INTO `charts` VALUES ('11', '8EADB678-A646-1E51-3E87-75A547B8AF26', 'folder', '文件柜');
INSERT INTO `charts` VALUES ('12', '8EADB678-A646-1E51-3E87-75A547B8AF27', 'diary', '日志');

-- ----------------------------
-- Table structure for charts_config
-- ----------------------------
DROP TABLE IF EXISTS `charts_config`;
CREATE TABLE `charts_config` (
  `ID` int(11) DEFAULT NULL,
  `CHARTS_CONFIG_ID` varchar(150) DEFAULT NULL,
  `CHARTS_ID` varchar(150) DEFAULT NULL,
  `DEPT_ID` text,
  `ACCOUNT_ID` text,
  `PRIV_ID` text,
  `ORG_ID` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of charts_config
-- ----------------------------
INSERT INTO `charts_config` VALUES ('1', '2A0DA905-C939-B529-1F87-5B38A5EE8990', '8EADB678-A646-1E51-3E87-75A547B8AF21', '', 'userAll', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `charts_config` VALUES ('2', '06E85A80-419E-141F-64E1-0523CF1FA494', '8EADB678-A646-1E51-3E87-75A547B8AF22', '', 'userAll', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `charts_config` VALUES ('3', 'AC783602-FF5C-3BB9-64C0-E1DB2AAD510F', '8EADB678-A646-1E51-3E87-75A547B8AF23', '', 'userAll', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `charts_config` VALUES ('4', 'D3FEC4CE-97D8-FE93-2C91-64E3FF0D57A7', '8EADB678-A646-1E51-3E87-75A547B8AF24', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `charts_config` VALUES ('5', 'F588B369-25EE-BD6A-35FD-F69245A2EE0C', '8EADB678-A646-1E51-3E87-75A547B8AF26', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `charts_config` VALUES ('6', '37A1DEE0-FE5E-74B3-0ACD-1CD99082C9D8', '8EADB678-A646-1E51-3E87-75A547B8AF25', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CLIENT_ID` varchar(50) DEFAULT NULL,
  `CLIENT_TYPE` varchar(50) DEFAULT NULL,
  `CLIENT_NO` varchar(50) DEFAULT NULL,
  `CLIENT_PATH` varchar(400) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', null, 'android', '1.0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `client` VALUES ('2', null, 'ios', '1.0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for code_class
-- ----------------------------
DROP TABLE IF EXISTS `code_class`;
CREATE TABLE `code_class` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE_ID` varchar(50) DEFAULT NULL,
  `CODE_PID` varchar(50) DEFAULT NULL,
  `CODE_NAME` varchar(200) DEFAULT NULL,
  `PAGE_CODE` varchar(200) DEFAULT NULL,
  `CODE_VALUE` varchar(200) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=184 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_class
-- ----------------------------
INSERT INTO `code_class` VALUES ('21', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '0', '新闻分类', null, 'news', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('24', '24716807-9F02-B284-B229-54C89C97353E', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '公司新闻', null, '公司新闻', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('25', 'F2EBBD7C-3D0D-9F12-0BD4-63BED149C04F', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '部门新闻', null, '部门新闻', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('26', 'E430FA5A-989B-E24E-1C6C-E388AD92DC60', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '行业新闻', null, '行业新闻', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('27', '8D13BDBA-505E-4BD0-685B-D48F81BF2F16', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '图片新闻', null, '图片新闻', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('28', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '0', '工作流分类', null, 'workflowtype', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('29', 'A2D28954-7EE8-83BC-B3EC-9F1F8078D013', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '项目管理', null, 'project', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('30', '2FA62B63-9972-4FC5-D908-2E34F4BE56EA', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '系统工作流', null, 'workflow', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('31', 'A4B51548-6B0F-261A-8221-35409DEED328', '0', '公告分类', null, 'notice', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('38', 'D4156023-6C7A-9F5A-AA3F-9378ACF6D2A6', 'A4B51548-6B0F-261A-8221-35409DEED328', '部门公告', null, '部门公告', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('39', 'E636536E-4681-484B-4C6E-8B089B42A99A', 'A4B51548-6B0F-261A-8221-35409DEED328', '通知', null, '通知', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('40', '31B0264F-D758-88BD-8ADE-D972C26FEF8A', 'A4B51548-6B0F-261A-8221-35409DEED328', '决定', null, '决定', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('41', 'CF08C591-A38C-0C46-1C9D-769F0D321D4C', 'A4B51548-6B0F-261A-8221-35409DEED328', '其他', null, '其他', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('42', '8C07AE92-7FD1-1165-C052-54BA2149E50E', 'A4B51548-6B0F-261A-8221-35409DEED328', '公告', null, '公告', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('43', '167EC5C3-F91B-FC44-4612-044832D2BA06', '0', '客户行业类型', null, 'customer', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('45', '36F594E0-1AFA-4006-6C53-9B77C816F6C7', '167EC5C3-F91B-FC44-4612-044832D2BA06', '建筑行业', null, '建筑行业', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('46', 'F806C1EC-FE60-B7C2-573D-150A98BC7806', '167EC5C3-F91B-FC44-4612-044832D2BA06', '科研行业', null, '科研行业', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('47', '48E60FA0-6404-7C38-517E-7E2AB2E0C330', '167EC5C3-F91B-FC44-4612-044832D2BA06', '软件行业', null, '软件行业', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('49', 'D9E22569-E8E6-284D-A102-0BCB7F6AA61E', '167EC5C3-F91B-FC44-4612-044832D2BA06', '教育行业', null, '教育行业', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('50', 'E0DAA36E-84F7-A410-5144-E55AFA6593B6', '167EC5C3-F91B-FC44-4612-044832D2BA06', '媒体/广告/文化', null, '媒体/广告/文化', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `code_class` VALUES ('51', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '0', '新闻分类', null, 'news', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('52', '24716807-9F02-B284-B229-54C89C97353E', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '公司新闻', null, '公司新闻', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('53', 'F2EBBD7C-3D0D-9F12-0BD4-63BED149C04F', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '部门新闻', null, '部门新闻', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('54', 'E430FA5A-989B-E24E-1C6C-E388AD92DC60', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '行业新闻', null, '行业新闻', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('55', '8D13BDBA-505E-4BD0-685B-D48F81BF2F16', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '图片新闻', null, '图片新闻', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('56', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '0', '工作流分类', null, 'workflowtype', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('57', 'A2D28954-7EE8-83BC-B3EC-9F1F8078D013', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '项目管理', null, 'project', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('58', '2FA62B63-9972-4FC5-D908-2E34F4BE56EA', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '系统工作流', null, 'workflow', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('59', 'A4B51548-6B0F-261A-8221-35409DEED328', '0', '公告分类', null, 'notice', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('60', 'D4156023-6C7A-9F5A-AA3F-9378ACF6D2A6', 'A4B51548-6B0F-261A-8221-35409DEED328', '部门公告', null, '部门公告', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('61', 'E636536E-4681-484B-4C6E-8B089B42A99A', 'A4B51548-6B0F-261A-8221-35409DEED328', '通知', null, '通知', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('62', '31B0264F-D758-88BD-8ADE-D972C26FEF8A', 'A4B51548-6B0F-261A-8221-35409DEED328', '决定', null, '决定', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('63', 'CF08C591-A38C-0C46-1C9D-769F0D321D4C', 'A4B51548-6B0F-261A-8221-35409DEED328', '其他', null, '其他', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `code_class` VALUES ('64', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '0', '新闻分类', null, 'news', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('65', '24716807-9F02-B284-B229-54C89C97353E', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '公司新闻', null, '公司新闻', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('66', 'F2EBBD7C-3D0D-9F12-0BD4-63BED149C04F', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '部门新闻', null, '部门新闻', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('67', 'E430FA5A-989B-E24E-1C6C-E388AD92DC60', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '行业新闻', null, '行业新闻', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('68', '8D13BDBA-505E-4BD0-685B-D48F81BF2F16', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '图片新闻', null, '图片新闻', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('69', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '0', '工作流分类', null, 'workflowtype', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('70', 'A2D28954-7EE8-83BC-B3EC-9F1F8078D013', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '项目管理', null, 'project', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('71', '2FA62B63-9972-4FC5-D908-2E34F4BE56EA', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '系统工作流', null, 'workflow', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('72', 'A4B51548-6B0F-261A-8221-35409DEED328', '0', '公告分类', null, 'notice', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('73', 'D4156023-6C7A-9F5A-AA3F-9378ACF6D2A6', 'A4B51548-6B0F-261A-8221-35409DEED328', '部门公告', null, '部门公告', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('74', 'E636536E-4681-484B-4C6E-8B089B42A99A', 'A4B51548-6B0F-261A-8221-35409DEED328', '通知', null, '通知', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('75', '31B0264F-D758-88BD-8ADE-D972C26FEF8A', 'A4B51548-6B0F-261A-8221-35409DEED328', '决定', null, '决定', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('76', 'CF08C591-A38C-0C46-1C9D-769F0D321D4C', 'A4B51548-6B0F-261A-8221-35409DEED328', '其他', null, '其他', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('77', '8C07AE92-7FD1-1165-C052-54BA2149E50E', 'A4B51548-6B0F-261A-8221-35409DEED328', '公告', null, '公告', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('78', '167EC5C3-F91B-FC44-4612-044832D2BA06', '0', '客户行业类型', null, 'customer', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('79', '36F594E0-1AFA-4006-6C53-9B77C816F6C7', '167EC5C3-F91B-FC44-4612-044832D2BA06', '建筑行业', null, '建筑行业', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('80', 'F806C1EC-FE60-B7C2-573D-150A98BC7806', '167EC5C3-F91B-FC44-4612-044832D2BA06', '科研行业', null, '科研行业', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('81', '48E60FA0-6404-7C38-517E-7E2AB2E0C330', '167EC5C3-F91B-FC44-4612-044832D2BA06', '软件行业', null, '软件行业', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('82', 'D9E22569-E8E6-284D-A102-0BCB7F6AA61E', '167EC5C3-F91B-FC44-4612-044832D2BA06', '教育行业', null, '教育行业', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('83', 'E0DAA36E-84F7-A410-5144-E55AFA6593B6', '167EC5C3-F91B-FC44-4612-044832D2BA06', '媒体/广告/文化', null, '媒体/广告/文化', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `code_class` VALUES ('84', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '0', '新闻分类', null, 'news', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('85', '24716807-9F02-B284-B229-54C89C97353E', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '公司新闻', null, '公司新闻', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('86', 'F2EBBD7C-3D0D-9F12-0BD4-63BED149C04F', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '部门新闻', null, '部门新闻', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('87', 'E430FA5A-989B-E24E-1C6C-E388AD92DC60', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '行业新闻', null, '行业新闻', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('88', '8D13BDBA-505E-4BD0-685B-D48F81BF2F16', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '图片新闻', null, '图片新闻', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('89', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '0', '工作流分类', null, 'workflowtype', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('90', 'A2D28954-7EE8-83BC-B3EC-9F1F8078D013', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '项目管理', null, 'project', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('91', '2FA62B63-9972-4FC5-D908-2E34F4BE56EA', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '系统工作流', null, 'workflow', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('92', 'A4B51548-6B0F-261A-8221-35409DEED328', '0', '公告分类', null, 'notice', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('93', 'D4156023-6C7A-9F5A-AA3F-9378ACF6D2A6', 'A4B51548-6B0F-261A-8221-35409DEED328', '部门公告', null, '部门公告', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('94', 'E636536E-4681-484B-4C6E-8B089B42A99A', 'A4B51548-6B0F-261A-8221-35409DEED328', '通知', null, '通知', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('95', '31B0264F-D758-88BD-8ADE-D972C26FEF8A', 'A4B51548-6B0F-261A-8221-35409DEED328', '决定', null, '决定', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('96', 'CF08C591-A38C-0C46-1C9D-769F0D321D4C', 'A4B51548-6B0F-261A-8221-35409DEED328', '其他', null, '其他', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('97', '8C07AE92-7FD1-1165-C052-54BA2149E50E', 'A4B51548-6B0F-261A-8221-35409DEED328', '公告', null, '公告', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('98', '167EC5C3-F91B-FC44-4612-044832D2BA06', '0', '客户行业类型', null, 'customer', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('99', '36F594E0-1AFA-4006-6C53-9B77C816F6C7', '167EC5C3-F91B-FC44-4612-044832D2BA06', '建筑行业', null, '建筑行业', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('100', 'F806C1EC-FE60-B7C2-573D-150A98BC7806', '167EC5C3-F91B-FC44-4612-044832D2BA06', '科研行业', null, '科研行业', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('101', '48E60FA0-6404-7C38-517E-7E2AB2E0C330', '167EC5C3-F91B-FC44-4612-044832D2BA06', '软件行业', null, '软件行业', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('102', 'D9E22569-E8E6-284D-A102-0BCB7F6AA61E', '167EC5C3-F91B-FC44-4612-044832D2BA06', '教育行业', null, '教育行业', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('103', 'E0DAA36E-84F7-A410-5144-E55AFA6593B6', '167EC5C3-F91B-FC44-4612-044832D2BA06', '媒体/广告/文化', null, '媒体/广告/文化', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `code_class` VALUES ('104', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '0', '新闻分类', null, 'news', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('105', '24716807-9F02-B284-B229-54C89C97353E', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '公司新闻', null, '公司新闻', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('106', 'F2EBBD7C-3D0D-9F12-0BD4-63BED149C04F', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '部门新闻', null, '部门新闻', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('107', 'E430FA5A-989B-E24E-1C6C-E388AD92DC60', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '行业新闻', null, '行业新闻', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('108', '8D13BDBA-505E-4BD0-685B-D48F81BF2F16', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '图片新闻', null, '图片新闻', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('109', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '0', '工作流分类', null, 'workflowtype', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('110', 'A2D28954-7EE8-83BC-B3EC-9F1F8078D013', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '项目管理', null, 'project', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('111', '2FA62B63-9972-4FC5-D908-2E34F4BE56EA', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '系统工作流', null, 'workflow', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('112', 'A4B51548-6B0F-261A-8221-35409DEED328', '0', '公告分类', null, 'notice', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('113', 'D4156023-6C7A-9F5A-AA3F-9378ACF6D2A6', 'A4B51548-6B0F-261A-8221-35409DEED328', '部门公告', null, '部门公告', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('114', 'E636536E-4681-484B-4C6E-8B089B42A99A', 'A4B51548-6B0F-261A-8221-35409DEED328', '通知', null, '通知', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('115', '31B0264F-D758-88BD-8ADE-D972C26FEF8A', 'A4B51548-6B0F-261A-8221-35409DEED328', '决定', null, '决定', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('116', 'CF08C591-A38C-0C46-1C9D-769F0D321D4C', 'A4B51548-6B0F-261A-8221-35409DEED328', '其他', null, '其他', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('117', '8C07AE92-7FD1-1165-C052-54BA2149E50E', 'A4B51548-6B0F-261A-8221-35409DEED328', '公告', null, '公告', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('118', '167EC5C3-F91B-FC44-4612-044832D2BA06', '0', '客户行业类型', null, 'customer', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('119', '36F594E0-1AFA-4006-6C53-9B77C816F6C7', '167EC5C3-F91B-FC44-4612-044832D2BA06', '建筑行业', null, '建筑行业', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('120', 'F806C1EC-FE60-B7C2-573D-150A98BC7806', '167EC5C3-F91B-FC44-4612-044832D2BA06', '科研行业', null, '科研行业', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('121', '48E60FA0-6404-7C38-517E-7E2AB2E0C330', '167EC5C3-F91B-FC44-4612-044832D2BA06', '软件行业', null, '软件行业', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('122', 'D9E22569-E8E6-284D-A102-0BCB7F6AA61E', '167EC5C3-F91B-FC44-4612-044832D2BA06', '教育行业', null, '教育行业', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('123', 'E0DAA36E-84F7-A410-5144-E55AFA6593B6', '167EC5C3-F91B-FC44-4612-044832D2BA06', '媒体/广告/文化', null, '媒体/广告/文化', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `code_class` VALUES ('124', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '0', '新闻分类', null, 'news', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('125', '24716807-9F02-B284-B229-54C89C97353E', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '公司新闻', null, '公司新闻', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('126', 'F2EBBD7C-3D0D-9F12-0BD4-63BED149C04F', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '部门新闻', null, '部门新闻', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('127', 'E430FA5A-989B-E24E-1C6C-E388AD92DC60', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '行业新闻', null, '行业新闻', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('128', '8D13BDBA-505E-4BD0-685B-D48F81BF2F16', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '图片新闻', null, '图片新闻', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('129', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '0', '工作流分类', null, 'workflowtype', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('130', 'A2D28954-7EE8-83BC-B3EC-9F1F8078D013', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '项目管理', null, 'project', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('131', '2FA62B63-9972-4FC5-D908-2E34F4BE56EA', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '系统工作流', null, 'workflow', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('132', 'A4B51548-6B0F-261A-8221-35409DEED328', '0', '公告分类', null, 'notice', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('133', 'D4156023-6C7A-9F5A-AA3F-9378ACF6D2A6', 'A4B51548-6B0F-261A-8221-35409DEED328', '部门公告', null, '部门公告', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('134', 'E636536E-4681-484B-4C6E-8B089B42A99A', 'A4B51548-6B0F-261A-8221-35409DEED328', '通知', null, '通知', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('135', '31B0264F-D758-88BD-8ADE-D972C26FEF8A', 'A4B51548-6B0F-261A-8221-35409DEED328', '决定', null, '决定', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('136', 'CF08C591-A38C-0C46-1C9D-769F0D321D4C', 'A4B51548-6B0F-261A-8221-35409DEED328', '其他', null, '其他', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('137', '8C07AE92-7FD1-1165-C052-54BA2149E50E', 'A4B51548-6B0F-261A-8221-35409DEED328', '公告', null, '公告', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('138', '167EC5C3-F91B-FC44-4612-044832D2BA06', '0', '客户行业类型', null, 'customer', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('139', '36F594E0-1AFA-4006-6C53-9B77C816F6C7', '167EC5C3-F91B-FC44-4612-044832D2BA06', '建筑行业', null, '建筑行业', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('140', 'F806C1EC-FE60-B7C2-573D-150A98BC7806', '167EC5C3-F91B-FC44-4612-044832D2BA06', '科研行业', null, '科研行业', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('141', '48E60FA0-6404-7C38-517E-7E2AB2E0C330', '167EC5C3-F91B-FC44-4612-044832D2BA06', '软件行业', null, '软件行业', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('142', 'D9E22569-E8E6-284D-A102-0BCB7F6AA61E', '167EC5C3-F91B-FC44-4612-044832D2BA06', '教育行业', null, '教育行业', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('143', 'E0DAA36E-84F7-A410-5144-E55AFA6593B6', '167EC5C3-F91B-FC44-4612-044832D2BA06', '媒体/广告/文化', null, '媒体/广告/文化', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `code_class` VALUES ('144', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '0', '新闻分类', null, 'news', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('145', '24716807-9F02-B284-B229-54C89C97353E', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '公司新闻', null, '公司新闻', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('146', 'F2EBBD7C-3D0D-9F12-0BD4-63BED149C04F', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '部门新闻', null, '部门新闻', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('147', 'E430FA5A-989B-E24E-1C6C-E388AD92DC60', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '行业新闻', null, '行业新闻', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('148', '8D13BDBA-505E-4BD0-685B-D48F81BF2F16', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '图片新闻', null, '图片新闻', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('149', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '0', '工作流分类', null, 'workflowtype', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('150', 'A2D28954-7EE8-83BC-B3EC-9F1F8078D013', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '项目管理', null, 'project', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('151', '2FA62B63-9972-4FC5-D908-2E34F4BE56EA', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '系统工作流', null, 'workflow', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('152', 'A4B51548-6B0F-261A-8221-35409DEED328', '0', '公告分类', null, 'notice', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('153', 'D4156023-6C7A-9F5A-AA3F-9378ACF6D2A6', 'A4B51548-6B0F-261A-8221-35409DEED328', '部门公告', null, '部门公告', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('154', 'E636536E-4681-484B-4C6E-8B089B42A99A', 'A4B51548-6B0F-261A-8221-35409DEED328', '通知', null, '通知', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('155', '31B0264F-D758-88BD-8ADE-D972C26FEF8A', 'A4B51548-6B0F-261A-8221-35409DEED328', '决定', null, '决定', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('156', 'CF08C591-A38C-0C46-1C9D-769F0D321D4C', 'A4B51548-6B0F-261A-8221-35409DEED328', '其他', null, '其他', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('157', '8C07AE92-7FD1-1165-C052-54BA2149E50E', 'A4B51548-6B0F-261A-8221-35409DEED328', '公告', null, '公告', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('158', '167EC5C3-F91B-FC44-4612-044832D2BA06', '0', '客户行业类型', null, 'customer', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('159', '36F594E0-1AFA-4006-6C53-9B77C816F6C7', '167EC5C3-F91B-FC44-4612-044832D2BA06', '建筑行业', null, '建筑行业', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('160', 'F806C1EC-FE60-B7C2-573D-150A98BC7806', '167EC5C3-F91B-FC44-4612-044832D2BA06', '科研行业', null, '科研行业', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('161', '48E60FA0-6404-7C38-517E-7E2AB2E0C330', '167EC5C3-F91B-FC44-4612-044832D2BA06', '软件行业', null, '软件行业', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('162', 'D9E22569-E8E6-284D-A102-0BCB7F6AA61E', '167EC5C3-F91B-FC44-4612-044832D2BA06', '教育行业', null, '教育行业', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('163', 'E0DAA36E-84F7-A410-5144-E55AFA6593B6', '167EC5C3-F91B-FC44-4612-044832D2BA06', '媒体/广告/文化', null, '媒体/广告/文化', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `code_class` VALUES ('164', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '0', '新闻分类', null, 'news', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('165', '24716807-9F02-B284-B229-54C89C97353E', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '公司新闻', null, '公司新闻', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('166', 'F2EBBD7C-3D0D-9F12-0BD4-63BED149C04F', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '部门新闻', null, '部门新闻', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('167', 'E430FA5A-989B-E24E-1C6C-E388AD92DC60', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '行业新闻', null, '行业新闻', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('168', '8D13BDBA-505E-4BD0-685B-D48F81BF2F16', '0F49664D-AD8B-F632-B868-D2FAAEEB4E53', '图片新闻', null, '图片新闻', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('169', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '0', '工作流分类', null, 'workflowtype', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('170', 'A2D28954-7EE8-83BC-B3EC-9F1F8078D013', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '项目管理', null, 'project', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('171', '2FA62B63-9972-4FC5-D908-2E34F4BE56EA', 'A335D2E5-11EF-65EC-62BD-11AD40A330AC', '系统工作流', null, 'workflow', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('172', 'A4B51548-6B0F-261A-8221-35409DEED328', '0', '公告分类', null, 'notice', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('173', 'D4156023-6C7A-9F5A-AA3F-9378ACF6D2A6', 'A4B51548-6B0F-261A-8221-35409DEED328', '部门公告', null, '部门公告', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('174', 'E636536E-4681-484B-4C6E-8B089B42A99A', 'A4B51548-6B0F-261A-8221-35409DEED328', '通知', null, '通知', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('175', '31B0264F-D758-88BD-8ADE-D972C26FEF8A', 'A4B51548-6B0F-261A-8221-35409DEED328', '决定', null, '决定', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('176', 'CF08C591-A38C-0C46-1C9D-769F0D321D4C', 'A4B51548-6B0F-261A-8221-35409DEED328', '其他', null, '其他', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('177', '8C07AE92-7FD1-1165-C052-54BA2149E50E', 'A4B51548-6B0F-261A-8221-35409DEED328', '公告', null, '公告', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('178', '167EC5C3-F91B-FC44-4612-044832D2BA06', '0', '客户行业类型', null, 'customer', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('179', '36F594E0-1AFA-4006-6C53-9B77C816F6C7', '167EC5C3-F91B-FC44-4612-044832D2BA06', '建筑行业', null, '建筑行业', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('180', 'F806C1EC-FE60-B7C2-573D-150A98BC7806', '167EC5C3-F91B-FC44-4612-044832D2BA06', '科研行业', null, '科研行业', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('181', '48E60FA0-6404-7C38-517E-7E2AB2E0C330', '167EC5C3-F91B-FC44-4612-044832D2BA06', '软件行业', null, '软件行业', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('182', 'D9E22569-E8E6-284D-A102-0BCB7F6AA61E', '167EC5C3-F91B-FC44-4612-044832D2BA06', '教育行业', null, '教育行业', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `code_class` VALUES ('183', 'E0DAA36E-84F7-A410-5144-E55AFA6593B6', '167EC5C3-F91B-FC44-4612-044832D2BA06', '媒体/广告/文化', null, '媒体/广告/文化', '2A096A25-4E62-8426-F80F-6522BB06FF3F');

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` varchar(300) DEFAULT NULL,
  `ACCOUNT_ID` varchar(300) DEFAULT NULL,
  `JOIN_STAFF` text,
  `CUSTOMER_NAME` varchar(300) DEFAULT NULL,
  `TEL_NUMBER` varchar(36) DEFAULT NULL,
  `FAX_NUMBER` varchar(150) DEFAULT NULL,
  `URL_NAME` varchar(300) DEFAULT NULL,
  `E_MAIL` varchar(300) DEFAULT NULL,
  `AREA_NAME` varchar(300) DEFAULT NULL,
  `ADD_NAME` text,
  `CUSTOMER_TYPE` varchar(300) DEFAULT NULL,
  `CUSTOMER_ORIGIN` varchar(300) DEFAULT NULL,
  `TRADE_TYPE` varchar(300) DEFAULT NULL,
  `FIRM_NATURE` varchar(300) DEFAULT NULL,
  `FIRM_SUMMARY` text,
  `REMARK` text,
  `ORG_ID` varchar(300) DEFAULT NULL,
  `TOP` int(2) DEFAULT NULL,
  `KEEP_ER` varchar(50) DEFAULT NULL,
  `CUSTOMER_STATUS` varchar(20) DEFAULT NULL,
  `CUSTOMER_TIME` varchar(20) DEFAULT NULL,
  `DEL_STATUS` int(2) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=183 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info
-- ----------------------------

-- ----------------------------
-- Table structure for customer_linkman
-- ----------------------------
DROP TABLE IF EXISTS `customer_linkman`;
CREATE TABLE `customer_linkman` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LINKMAN_ID` varchar(300) DEFAULT NULL,
  `CUSTOMER_ID` varchar(300) DEFAULT NULL,
  `LINKMAN_NAME` varchar(300) DEFAULT NULL,
  `LINKMAN_JOB` varchar(300) DEFAULT NULL,
  `LINKMAN_SEX` varchar(6) DEFAULT NULL,
  `LINKMAN_CALL` varchar(300) DEFAULT NULL,
  `HOME_PHONE` varchar(60) DEFAULT NULL,
  `CELL_PHONE` varchar(60) DEFAULT NULL,
  `QQ_NUMBER` varchar(60) DEFAULT NULL,
  `EMAIL` varchar(300) DEFAULT NULL,
  `REMARK` text,
  `ADD_NAME` text,
  `ORG_ID` varchar(300) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_linkman
-- ----------------------------

-- ----------------------------
-- Table structure for customer_power
-- ----------------------------
DROP TABLE IF EXISTS `customer_power`;
CREATE TABLE `customer_power` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `POWER_ID` varchar(100) DEFAULT NULL,
  `ACCOUNT_ID` varchar(100) DEFAULT NULL,
  `ORG_ID` varchar(100) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_power
-- ----------------------------

-- ----------------------------
-- Table structure for customer_record
-- ----------------------------
DROP TABLE IF EXISTS `customer_record`;
CREATE TABLE `customer_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECORD_ID` varchar(300) DEFAULT NULL COMMENT '记录ID',
  `CUSTOMER_ID` varchar(300) DEFAULT NULL COMMENT '客户ID',
  `LINKMAN_ID` varchar(300) DEFAULT NULL COMMENT '联系人ID',
  `RECORD_CONTENT` text COMMENT '联系内容',
  `RECORD_WARN` varchar(60) DEFAULT NULL COMMENT '联系提醒时间',
  `RECORD_LINKTYPE` varchar(300) DEFAULT NULL COMMENT '联系类型',
  `RECORD_TIME` varchar(20) DEFAULT NULL COMMENT '记录时间',
  `ORG_ID` varchar(300) DEFAULT NULL,
  `ACCOUNT_ID` varchar(20) DEFAULT NULL COMMENT '创建人员',
  `ADDRESS` varchar(200) DEFAULT NULL COMMENT '地址',
  `LON` varchar(100) DEFAULT NULL COMMENT '经度',
  `LAT` varchar(100) DEFAULT NULL COMMENT '纬度',
  `DETAIL` varchar(200) DEFAULT NULL COMMENT '详细地址',
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_record
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '顺序号',
  `DEPT_ID` varchar(50) NOT NULL COMMENT '部门id',
  `DEPT_NAME` varchar(100) NOT NULL COMMENT '部门名称',
  `ORG_LEAVE_ID` varchar(50) NOT NULL COMMENT '父级id',
  `DEPT_LONG_ID` varchar(1000) NOT NULL COMMENT '层级id',
  `DEPT_LONG_NAME` varchar(1000) NOT NULL COMMENT '层级名称',
  `DEPT_TEL` varchar(20) DEFAULT NULL COMMENT '部门电话',
  `DEPT_EMAIL` varchar(50) DEFAULT NULL COMMENT '部门邮件',
  `DEPT_FAX` varchar(20) DEFAULT NULL COMMENT '部门传真',
  `DEPT_LEAD` varchar(20) DEFAULT NULL COMMENT '部门领导，领导账号',
  `DEPT_FUNCTION` varchar(4000) DEFAULT NULL COMMENT '部门职能',
  `ORG_ID` varchar(50) NOT NULL COMMENT '组织机构id',
  PRIMARY KEY (`DEPT_ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=1014 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '信息中心', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '0/3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7/93CC5B14-7D22-FAA3-EECB-5ED97F4691B7/FB6E0084-7A78-9C2A-9BA3-3522C6967002', '南京慧客软件科技有限公司/总经办/信息中心', '1234567', '68311718@qq.com', '1234567', 'admin', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('89', '9984FED2-E018-87FF-5479-7C89A2BA6341', '销售部', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '0/3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7/93CC5B14-7D22-FAA3-EECB-5ED97F4691B7/9984FED2-E018-87FF-5479-7C89A2BA6341', '南京慧客软件科技有限公司/总经办/销售部', '', '', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('81', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '总经办', '0', '0/3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7/93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '总经办', '', '', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('82', '30CA0AAA-DAD3-E00A-3499-235C83F2B805', '行政部', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '0/3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7/93CC5B14-7D22-FAA3-EECB-5ED97F4691B7/30CA0AAA-DAD3-E00A-3499-235C83F2B805', '南京慧客软件科技有限公司/总经办/行政部', '', '', '', 'liushaoquan', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('83', 'ECE57B00-ACE8-4482-6C58-F7FC85A8DC81', '财务部', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '0/3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7/93CC5B14-7D22-FAA3-EECB-5ED97F4691B7/ECE57B00-ACE8-4482-6C58-F7FC85A8DC81', '总经办/财务部', '', '', '', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('84', '26AA686C-AF98-02DD-8325-22C73835A36A', '人力资源部', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '0/3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7/93CC5B14-7D22-FAA3-EECB-5ED97F4691B7/26AA686C-AF98-02DD-8325-22C73835A36A', '总经办/人力资源部', '', '', '', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('85', '0E93D1EE-32BA-49DF-D571-01718D0EA844', '采购部', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '0/3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7/93CC5B14-7D22-FAA3-EECB-5ED97F4691B7/0E93D1EE-32BA-49DF-D571-01718D0EA844', '总经办/采购部', '', '', '', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('88', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '领导室', '0', '0/3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '领导室', '', '', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('92', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '信息科', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '0/3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7/93CC5B14-7D22-FAA3-EECB-5ED97F4691B7/FB6E0084-7A78-9C2A-9BA3-3522C6967002/B75E54BD-7AD1-8E97-C588-F4571595B9B9', '南京慧客软件科技有限公司/总经办/信息中心/信息科', '', '', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('997', 'F2106E85-F716-11F1-3556-1059684DA3B7', '董事会', '0', '0/F2106E85-F716-11F1-3556-1059684DA3B7', '董事会', null, null, null, null, null, '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `department` VALUES ('998', 'D10CB90A-A12E-3F93-7215-05D1B8773B2A', '董事会', '0', '0/D10CB90A-A12E-3F93-7215-05D1B8773B2A', '董事会', null, null, null, null, null, '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `department` VALUES ('999', '89C33041-73B6-4DFC-2E76-EA12363BD97F', '董事会', '0', '0/89C33041-73B6-4DFC-2E76-EA12363BD97F', '董事会', null, null, null, null, null, '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `department` VALUES ('1000', '8C9FD5A8-F3E6-7470-7894-D44CD78449D9', '董事会', '0', '0/8C9FD5A8-F3E6-7470-7894-D44CD78449D9', '董事会', null, null, null, null, null, '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `department` VALUES ('1001', '54081B63-6FF0-FB63-09DA-B835762BB37F', '董事会', '0', '0/54081B63-6FF0-FB63-09DA-B835762BB37F', '董事会', null, null, null, null, null, '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `department` VALUES ('1009', '64005ACD-7705-11FC-B896-7CA139E0EA38', '部门100', '0', '0/64005ACD-7705-11FC-B896-7CA139E0EA38', '部门100', '', '', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('1011', 'F39E5A2A-1794-1CBE-29FC-6D40A35CCB83', '部门110', '64005ACD-7705-11FC-B896-7CA139E0EA38', '0/64005ACD-7705-11FC-B896-7CA139E0EA38/F39E5A2A-1794-1CBE-29FC-6D40A35CCB83', '部门100/部门110', '', '', '', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('1012', '8F1306D9-ADC3-9D84-39F8-51F7A4180A70', '部门120', '64005ACD-7705-11FC-B896-7CA139E0EA38', '0/64005ACD-7705-11FC-B896-7CA139E0EA38/8F1306D9-ADC3-9D84-39F8-51F7A4180A70', '部门100/部门120', '', '', '', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `department` VALUES ('1013', '559E4A0C-9A55-2269-9A0C-F90C39957F02', '部门121', '8F1306D9-ADC3-9D84-39F8-51F7A4180A70', '0/64005ACD-7705-11FC-B896-7CA139E0EA38/8F1306D9-ADC3-9D84-39F8-51F7A4180A70/559E4A0C-9A55-2269-9A0C-F90C39957F02', '部门100/部门120/部门121', '', '', '', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for diary
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` varchar(20) DEFAULT NULL,
  `DIARY_NAME` varchar(50) DEFAULT NULL,
  `DIARY_CONTENT` text,
  `DIARY_ANNEX` text,
  `DIARY_DATETIME` varchar(20) DEFAULT NULL,
  `DIARY_MOLD` int(11) DEFAULT NULL,
  `DIARY_ID` varchar(100) DEFAULT NULL,
  `LOOK_STAFF` text,
  `SHARE_PRIV` text,
  `DIARY_TITLETIME` varchar(20) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  `SHARE_RANGE` int(2) DEFAULT NULL,
  `LAUD` text,
  `LAUD_NUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of diary
-- ----------------------------

-- ----------------------------
-- Table structure for diary_comments
-- ----------------------------
DROP TABLE IF EXISTS `diary_comments`;
CREATE TABLE `diary_comments` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMM_ID` varchar(100) DEFAULT NULL,
  `COMM_PID` varchar(100) DEFAULT NULL,
  `COMM_CONTECT` text,
  `COMM_TIME` varchar(50) DEFAULT NULL,
  `DIARY_ID` varchar(100) DEFAULT NULL,
  `ACCOUNT_ID` varchar(20) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=429 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of diary_comments
-- ----------------------------

-- ----------------------------
-- Table structure for diary_fit
-- ----------------------------
DROP TABLE IF EXISTS `diary_fit`;
CREATE TABLE `diary_fit` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIT_ID` varchar(150) DEFAULT NULL,
  `START_TIME` varchar(60) DEFAULT NULL,
  `END_TIME` varchar(60) DEFAULT NULL,
  `LOCK_DAY` int(11) DEFAULT NULL,
  `COMM_STATUS` int(11) DEFAULT NULL,
  `SHARE_STATUS` int(11) DEFAULT NULL,
  `ORG_ID` varchar(150) DEFAULT NULL,
  KEY `Id` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of diary_fit
-- ----------------------------
INSERT INTO `diary_fit` VALUES ('1', 'DB31A94D-E682-EBFA-BA30-5A61743EA133', '2015-09-14', '2015-09-16', '10', '1', '1', '1');
INSERT INTO `diary_fit` VALUES ('2', '720D5F66-CF72-BFB2-2BA1-DB821D7621F0', '', '', '0', '1', '0', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for directory
-- ----------------------------
DROP TABLE IF EXISTS `directory`;
CREATE TABLE `directory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DIR_ID` varchar(50) DEFAULT NULL,
  `DIR_NAME` varchar(40) DEFAULT NULL,
  `TOP_ID` varchar(50) DEFAULT NULL,
  `ALL_DIR` varchar(200) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SEQ_ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of directory
-- ----------------------------
INSERT INTO `directory` VALUES ('40', 'B341A0C1-E69B-0E98-E625-9A95E91EF915', '财务制度', '', 'null/财务制度', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `directory` VALUES ('41', '85F765A8-3220-39DF-C389-03735677ACAB', '人事制度', '', '人事制度', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `directory` VALUES ('42', '18632442-0A4A-AC15-57AF-45B1791459C1', '生产制度', '', '生产制度', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `id` smallint(5) DEFAULT NULL,
  `name` varchar(90) DEFAULT NULL,
  `parentid` smallint(5) DEFAULT NULL,
  `initial` char(9) DEFAULT NULL,
  `initials` char(90) DEFAULT NULL,
  `pinyin` varchar(270) DEFAULT NULL,
  `extra` varchar(135) DEFAULT NULL,
  `suffix` varchar(45) DEFAULT NULL,
  `code` char(63) DEFAULT NULL,
  `area_code` char(54) DEFAULT NULL,
  `order` tinyint(2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of district
-- ----------------------------
INSERT INTO `district` VALUES ('1', '北京', '0', 'b', 'bj', 'beijing', '', '市', '110000', '010', '1');
INSERT INTO `district` VALUES ('2', '天津', '0', 't', 'tj', 'tianjin', '', '市', '120000', '022', '2');
INSERT INTO `district` VALUES ('3', '上海', '0', 's', 'sh', 'shanghai', '', '市', '310000', '021', '3');
INSERT INTO `district` VALUES ('4', '重庆', '0', 'z', 'zq', 'zhongqing', '', '市', '500000', '023', '4');
INSERT INTO `district` VALUES ('5', '河北', '0', 'h', 'hb', 'hebei', '', '省', '130000', '', '5');
INSERT INTO `district` VALUES ('6', '山西', '0', 's', 'sx', 'shanxi', '', '省', '140000', '', '6');
INSERT INTO `district` VALUES ('7', '内蒙古', '0', 'n', 'nmg', 'neimenggu', '', '自治区', '150000', '', '7');
INSERT INTO `district` VALUES ('8', '辽宁', '0', 'l', 'ln', 'liaoning', '', '省', '210000', '', '8');
INSERT INTO `district` VALUES ('9', '吉林', '0', 'j', 'jl', 'jilin', '', '省', '220000', '', '9');
INSERT INTO `district` VALUES ('10', '黑龙江', '0', 'h', 'hlj', 'heilongjiang', '', '省', '230000', '', '10');
INSERT INTO `district` VALUES ('11', '江苏', '0', 'j', 'js', 'jiangsu', '', '省', '320000', '', '11');
INSERT INTO `district` VALUES ('12', '浙江', '0', 'z', 'zj', 'zhejiang', '', '省', '330000', '', '12');
INSERT INTO `district` VALUES ('13', '安徽', '0', 'a', 'ah', 'anhui', '', '省', '340000', '', '13');
INSERT INTO `district` VALUES ('14', '福建', '0', 'f', 'fj', 'fujian', '', '省', '350000', '', '14');
INSERT INTO `district` VALUES ('15', '江西', '0', 'j', 'jx', 'jiangxi', '', '省', '360000', '', '15');
INSERT INTO `district` VALUES ('16', '山东', '0', 's', 'sd', 'shandong', '', '省', '370000', '', '16');
INSERT INTO `district` VALUES ('17', '河南', '0', 'h', 'hn', 'henan', '', '省', '410000', '', '17');
INSERT INTO `district` VALUES ('18', '湖北', '0', 'h', 'hb', 'hubei', '', '省', '420000', '', '18');
INSERT INTO `district` VALUES ('19', '湖南', '0', 'h', 'hn', 'hunan', '', '省', '430000', '', '19');
INSERT INTO `district` VALUES ('20', '广东', '0', 'g', 'gd', 'guangdong', '', '省', '440000', '', '20');
INSERT INTO `district` VALUES ('21', '广西', '0', 'g', 'gx', 'guangxi', '壮族', '自治区', '450000', '', '21');
INSERT INTO `district` VALUES ('22', '海南', '0', 'h', 'hn', 'hainan', '', '省', '460000', '', '22');
INSERT INTO `district` VALUES ('23', '四川', '0', 's', 'sc', 'sichuan', '', '省', '510000', '', '23');
INSERT INTO `district` VALUES ('24', '贵州', '0', 'g', 'gz', 'guizhou', '', '省', '520000', '', '24');
INSERT INTO `district` VALUES ('25', '云南', '0', 'y', 'yn', 'yunnan', '', '省', '530000', '', '25');
INSERT INTO `district` VALUES ('26', '西藏', '0', 'x', 'xz', 'xizang', '', '自治区', '540000', '', '26');
INSERT INTO `district` VALUES ('27', '陕西', '0', 's', 'sx', 'shanxi', '', '省', '610000', '', '27');
INSERT INTO `district` VALUES ('28', '甘肃', '0', 'g', 'gs', 'gansu', '', '省', '620000', '', '28');
INSERT INTO `district` VALUES ('29', '青海', '0', 'q', 'qh', 'qinghai', '', '省', '630000', '', '29');
INSERT INTO `district` VALUES ('30', '宁夏', '0', 'n', 'nx', 'ningxia', '回族', '自治区', '640000', '', '30');
INSERT INTO `district` VALUES ('31', '新疆', '0', 'x', 'xj', 'xinjiang', '维吾尔', '自治区', '650000', '', '31');
INSERT INTO `district` VALUES ('32', '台湾', '0', 't', 'tw', 'taiwan', '', '省', '710000', '', '32');
INSERT INTO `district` VALUES ('33', '香港', '0', 'x', 'xg', 'xianggang', '', '特别行政区', '810000', '852', '33');
INSERT INTO `district` VALUES ('34', '澳门', '0', 'a', 'am', 'aomen', '', '特别行政区', '820000', '853', '34');
INSERT INTO `district` VALUES ('35', '海外', '0', 'h', 'hw', 'haiwai', '', '', '', '', '35');
INSERT INTO `district` VALUES ('36', '东城', '1', 'd', 'dc', 'dongcheng', '', '区', '110101', '010', '1');
INSERT INTO `district` VALUES ('37', '西城', '1', 'x', 'xc', 'xicheng', '', '区', '110102', '010', '2');
INSERT INTO `district` VALUES ('38', '崇文', '1', 'c', 'cw', 'chongwen', '', '区', '110103', '010', '3');
INSERT INTO `district` VALUES ('39', '宣武', '1', 'x', 'xw', 'xuanwu', '', '区', '110104', '010', '4');
INSERT INTO `district` VALUES ('40', '朝阳', '1', 'c', 'cy', 'chaoyang', '', '区', '110105', '010', '5');
INSERT INTO `district` VALUES ('41', '丰台', '1', 'f', 'ft', 'fengtai', '', '区', '110106', '010', '6');
INSERT INTO `district` VALUES ('42', '石景山', '1', 's', 'sjs', 'shijingshan', '', '区', '110107', '010', '7');
INSERT INTO `district` VALUES ('43', '海淀', '1', 'h', 'hd', 'haidian', '', '区', '110108', '010', '8');
INSERT INTO `district` VALUES ('44', '门头沟', '1', 'm', 'mtg', 'mentougou', '', '区', '110109', '010', '9');
INSERT INTO `district` VALUES ('45', '房山', '1', 'f', 'fs', 'fangshan', '', '区', '110111', '010', '10');
INSERT INTO `district` VALUES ('46', '通州', '1', 't', 'tz', 'tongzhou', '', '区', '110112', '010', '11');
INSERT INTO `district` VALUES ('47', '顺义', '1', 's', 'sy', 'shunyi', '', '区', '110113', '010', '12');
INSERT INTO `district` VALUES ('48', '昌平', '1', 'c', 'cp', 'changping', '', '区', '110114', '010', '13');
INSERT INTO `district` VALUES ('49', '大兴', '1', 'd', 'dx', 'daxing', '', '区', '110115', '010', '14');
INSERT INTO `district` VALUES ('50', '怀柔', '1', 'h', 'hr', 'huairou', '', '区', '110116', '010', '15');
INSERT INTO `district` VALUES ('51', '平谷', '1', 'p', 'pg', 'pinggu', '', '区', '110117', '010', '16');
INSERT INTO `district` VALUES ('52', '密云', '1', 'm', 'my', 'miyun', '', '县', '110228', '010', '17');
INSERT INTO `district` VALUES ('53', '延庆', '1', 'y', 'yq', 'yanqing', '', '县', '110229', '010', '18');
INSERT INTO `district` VALUES ('54', '和平', '2', 'h', 'hp', 'heping', '', '区', '120101', '022', '1');
INSERT INTO `district` VALUES ('55', '河东', '2', 'h', 'hd', 'hedong', '', '区', '120102', '022', '2');
INSERT INTO `district` VALUES ('56', '河西', '2', 'h', 'hx', 'hexi', '', '区', '120103', '022', '3');
INSERT INTO `district` VALUES ('57', '南开', '2', 'n', 'nk', 'nankai', '', '区', '120104', '022', '4');
INSERT INTO `district` VALUES ('58', '河北', '2', 'h', 'hb', 'hebei', '', '区', '120105', '022', '5');
INSERT INTO `district` VALUES ('59', '红桥', '2', 'h', 'hq', 'hongqiao', '', '区', '120106', '022', '6');
INSERT INTO `district` VALUES ('60', '东丽', '2', 'd', 'dl', 'dongli', '', '区', '120110', '022', '7');
INSERT INTO `district` VALUES ('61', '西青', '2', 'x', 'xq', 'xiqing', '', '区', '120111', '022', '8');
INSERT INTO `district` VALUES ('62', '津南', '2', 'j', 'jn', 'jinnan', '', '区', '120112', '022', '9');
INSERT INTO `district` VALUES ('63', '北辰', '2', 'b', 'bc', 'beichen', '', '区', '120113', '022', '10');
INSERT INTO `district` VALUES ('64', '武清', '2', 'w', 'wq', 'wuqing', '', '区', '120114', '022', '11');
INSERT INTO `district` VALUES ('65', '宝坻', '2', 'b', 'bc', 'baochi', '', '区', '120115', '022', '12');
INSERT INTO `district` VALUES ('66', '滨海新区', '2', 'b', 'bhxq', 'binhaixinqu', '', '', '120116', '022', '13');
INSERT INTO `district` VALUES ('67', '宁河', '2', 'n', 'nh', 'ninghe', '', '县', '120221', '022', '14');
INSERT INTO `district` VALUES ('68', '蓟县', '2', 'j', 'jx', 'jixian', '', '', '120223', '022', '15');
INSERT INTO `district` VALUES ('69', '静海', '2', 'j', 'jh', 'jinghai', '', '县', '120225', '022', '16');
INSERT INTO `district` VALUES ('70', '黄浦', '3', 'h', 'hp', 'huangpu', '', '区', '310101', '021', '1');
INSERT INTO `district` VALUES ('71', '徐汇', '3', 'x', 'xh', 'xuhui', '', '区', '310104', '021', '2');
INSERT INTO `district` VALUES ('72', '长宁', '3', 'z', 'zn', 'zhangning', '', '区', '310105', '021', '3');
INSERT INTO `district` VALUES ('73', '静安', '3', 'j', 'ja', 'jingan', '', '区', '310106', '021', '4');
INSERT INTO `district` VALUES ('74', '普陀', '3', 'p', 'pt', 'putuo', '', '区', '310107', '021', '5');
INSERT INTO `district` VALUES ('75', '闸北', '3', 'z', 'zb', 'zhabei', '', '区', '310108', '021', '6');
INSERT INTO `district` VALUES ('76', '虹口', '3', 'h', 'hk', 'hongkou', '', '区', '310109', '021', '7');
INSERT INTO `district` VALUES ('77', '杨浦', '3', 'y', 'yp', 'yangpu', '', '区', '310110', '021', '8');
INSERT INTO `district` VALUES ('78', '闵行', '3', 'm', 'mx', 'minxing', '', '区', '310112', '021', '9');
INSERT INTO `district` VALUES ('79', '宝山', '3', 'b', 'bs', 'baoshan', '', '区', '310113', '021', '10');
INSERT INTO `district` VALUES ('80', '嘉定', '3', 'j', 'jd', 'jiading', '', '区', '310114', '021', '11');
INSERT INTO `district` VALUES ('81', '浦东新区', '3', 'p', 'pdxq', 'pudongxinqu', '', '', '310115', '021', '12');
INSERT INTO `district` VALUES ('82', '金山', '3', 'j', 'js', 'jinshan', '', '区', '310116', '021', '13');
INSERT INTO `district` VALUES ('83', '松江', '3', 's', 'sj', 'songjiang', '', '区', '310117', '021', '14');
INSERT INTO `district` VALUES ('84', '青浦', '3', 'q', 'qp', 'qingpu', '', '区', '310118', '021', '15');
INSERT INTO `district` VALUES ('85', '奉贤', '3', 'f', 'fx', 'fengxian', '', '区', '310120', '021', '16');
INSERT INTO `district` VALUES ('86', '崇明', '3', 'c', 'cm', 'chongming', '', '县', '310230', '021', '17');
INSERT INTO `district` VALUES ('87', '万州', '4', 'w', 'wz', 'wanzhou', '', '区', '500101', '023', '1');
INSERT INTO `district` VALUES ('88', '涪陵', '4', 'f', 'fl', 'fuling', '', '区', '500102', '023', '2');
INSERT INTO `district` VALUES ('89', '渝中', '4', 'y', 'yz', 'yuzhong', '', '区', '500103', '023', '3');
INSERT INTO `district` VALUES ('90', '大渡口', '4', 'd', 'ddk', 'dadukou', '', '区', '500104', '023', '4');
INSERT INTO `district` VALUES ('91', '江北', '4', 'j', 'jb', 'jiangbei', '', '区', '500105', '023', '5');
INSERT INTO `district` VALUES ('92', '沙坪坝', '4', 's', 'spb', 'shapingba', '', '区', '500106', '023', '6');
INSERT INTO `district` VALUES ('93', '九龙坡', '4', 'j', 'jlp', 'jiulongpo', '', '区', '500107', '023', '7');
INSERT INTO `district` VALUES ('94', '南岸', '4', 'n', 'na', 'nanan', '', '区', '500108', '023', '8');
INSERT INTO `district` VALUES ('95', '北碚', '4', 'b', 'bb', 'beibei', '', '区', '500109', '023', '9');
INSERT INTO `district` VALUES ('96', '綦江', '4', 'q', 'qj', 'qijiang', '', '区', '500110', '023', '10');
INSERT INTO `district` VALUES ('97', '大足', '4', 'd', 'dz', 'dazu', '', '区', '500111', '023', '11');
INSERT INTO `district` VALUES ('98', '渝北', '4', 'y', 'yb', 'yubei', '', '区', '500112', '023', '12');
INSERT INTO `district` VALUES ('99', '巴南', '4', 'b', 'bn', 'banan', '', '区', '500113', '023', '13');
INSERT INTO `district` VALUES ('100', '黔江', '4', 'q', 'qj', 'qianjiang', '', '区', '500114', '023', '14');
INSERT INTO `district` VALUES ('101', '长寿', '4', 'z', 'zs', 'zhangshou', '', '区', '500115', '023', '15');
INSERT INTO `district` VALUES ('102', '江津', '4', 'j', 'jj', 'jiangjin', '', '区', '500116', '023', '16');
INSERT INTO `district` VALUES ('103', '合川', '4', 'h', 'hc', 'hechuan', '', '区', '500117', '023', '17');
INSERT INTO `district` VALUES ('104', '永川', '4', 'y', 'yc', 'yongchuan', '', '区', '500118', '023', '18');
INSERT INTO `district` VALUES ('105', '南川', '4', 'n', 'nc', 'nanchuan', '', '区', '500119', '023', '19');
INSERT INTO `district` VALUES ('106', '潼南', '4', 't', 'tn', 'tongnan', '', '县', '500223', '023', '22');
INSERT INTO `district` VALUES ('107', '铜梁', '4', 't', 'tl', 'tongliang', '', '区', '500151', '023', '21');
INSERT INTO `district` VALUES ('108', '荣昌', '4', 'r', 'rc', 'rongchang', '', '县', '500226', '023', '23');
INSERT INTO `district` VALUES ('109', '璧山', '4', 'b', 'bs', 'bishan', '', '区', '500120', '023', '20');
INSERT INTO `district` VALUES ('110', '梁平', '4', 'l', 'lp', 'liangping', '', '县', '500228', '023', '24');
INSERT INTO `district` VALUES ('111', '城口', '4', 'c', 'ck', 'chengkou', '', '县', '500229', '023', '25');
INSERT INTO `district` VALUES ('112', '丰都', '4', 'f', 'fd', 'fengdou', '', '县', '500230', '023', '26');
INSERT INTO `district` VALUES ('113', '垫江', '4', 'd', 'dj', 'dianjiang', '', '县', '500231', '023', '27');
INSERT INTO `district` VALUES ('114', '武隆', '4', 'w', 'wl', 'wulong', '', '县', '500232', '023', '28');
INSERT INTO `district` VALUES ('115', '忠县', '4', 'z', 'zx', 'zhongxian', '', '', '500233', '023', '29');
INSERT INTO `district` VALUES ('116', '开县', '4', 'k', 'kx', 'kaixian', '', '', '500234', '023', '30');
INSERT INTO `district` VALUES ('117', '云阳', '4', 'y', 'yy', 'yunyang', '', '县', '500235', '023', '31');
INSERT INTO `district` VALUES ('118', '奉节', '4', 'f', 'fj', 'fengjie', '', '县', '500236', '023', '32');
INSERT INTO `district` VALUES ('119', '巫山', '4', 'w', 'ws', 'wushan', '', '县', '500237', '023', '33');
INSERT INTO `district` VALUES ('120', '巫溪', '4', 'w', 'wx', 'wuxi', '', '县', '500238', '023', '34');
INSERT INTO `district` VALUES ('121', '石柱', '4', 's', 'sz', 'shizhu', '土家族', '自治县', '500240', '023', '35');
INSERT INTO `district` VALUES ('122', '秀山', '4', 'x', 'xs', 'xiushan', '土家族苗族', '自治县', '500241', '023', '36');
INSERT INTO `district` VALUES ('123', '酉阳', '4', 'y', 'yy', 'youyang', '土家族苗族', '自治县', '500242', '023', '37');
INSERT INTO `district` VALUES ('124', '彭水', '4', 'p', 'ps', 'pengshui', '苗族土家族', '自治县', '500243', '023', '38');
INSERT INTO `district` VALUES ('125', '石家庄', '5', 's', 'sjz', 'shijiazhuang', '', '市', '130100', '0311', '1');
INSERT INTO `district` VALUES ('126', '唐山', '5', 't', 'ts', 'tangshan', '', '市', '130200', '0315', '2');
INSERT INTO `district` VALUES ('127', '秦皇岛', '5', 'q', 'qhd', 'qinhuangdao', '', '市', '130300', '0335', '3');
INSERT INTO `district` VALUES ('128', '邯郸', '5', 'h', 'hd', 'handan', '', '市', '130400', '0310', '4');
INSERT INTO `district` VALUES ('129', '邢台', '5', 'x', 'xt', 'xingtai', '', '市', '130500', '0319', '5');
INSERT INTO `district` VALUES ('130', '保定', '5', 'b', 'bd', 'baoding', '', '市', '130600', '0312', '6');
INSERT INTO `district` VALUES ('131', '张家口', '5', 'z', 'zjk', 'zhangjiakou', '', '市', '130700', '0313', '7');
INSERT INTO `district` VALUES ('132', '承德', '5', 'c', 'cd', 'chengde', '', '市', '130800', '0314', '8');
INSERT INTO `district` VALUES ('133', '沧州', '5', 'c', 'cz', 'cangzhou', '', '市', '130900', '0317', '9');
INSERT INTO `district` VALUES ('134', '廊坊', '5', 'l', 'lf', 'langfang', '', '市', '131000', '0316', '10');
INSERT INTO `district` VALUES ('135', '衡水', '5', 'h', 'hs', 'hengshui', '', '市', '131100', '0318', '11');
INSERT INTO `district` VALUES ('136', '太原', '6', 't', 'ty', 'taiyuan', '', '市', '140100', '0351', '1');
INSERT INTO `district` VALUES ('137', '大同', '6', 'd', 'dt', 'datong', '', '市', '140200', '0352', '2');
INSERT INTO `district` VALUES ('138', '阳泉', '6', 'y', 'yq', 'yangquan', '', '市', '140300', '0353', '3');
INSERT INTO `district` VALUES ('139', '长治', '6', 'z', 'zz', 'zhangzhi', '', '市', '140400', '0355', '4');
INSERT INTO `district` VALUES ('140', '晋城', '6', 'j', 'jc', 'jincheng', '', '市', '140500', '0356', '5');
INSERT INTO `district` VALUES ('141', '朔州', '6', 's', 'sz', 'shuozhou', '', '市', '140600', '0349', '6');
INSERT INTO `district` VALUES ('142', '晋中', '6', 'j', 'jz', 'jinzhong', '', '市', '140700', '0354', '7');
INSERT INTO `district` VALUES ('143', '运城', '6', 'y', 'yc', 'yuncheng', '', '市', '140800', '0359', '8');
INSERT INTO `district` VALUES ('144', '忻州', '6', 'x', 'xz', 'xinzhou', '', '市', '140900', '0350', '9');
INSERT INTO `district` VALUES ('145', '临汾', '6', 'l', 'lf', 'linfen', '', '市', '141000', '0357', '10');
INSERT INTO `district` VALUES ('146', '吕梁', '6', 'l', 'll', 'lu:liang', '', '市', '141100', '0358', '11');
INSERT INTO `district` VALUES ('147', '呼和浩特', '7', 'h', 'hhht', 'huhehaote', '', '市', '150100', '0471', '1');
INSERT INTO `district` VALUES ('148', '包头', '7', 'b', 'bt', 'baotou', '', '市', '150200', '0472', '2');
INSERT INTO `district` VALUES ('149', '乌海', '7', 'w', 'wh', 'wuhai', '', '市', '150300', '0473', '3');
INSERT INTO `district` VALUES ('150', '赤峰', '7', 'c', 'cf', 'chifeng', '', '市', '150400', '0476', '4');
INSERT INTO `district` VALUES ('151', '通辽', '7', 't', 'tl', 'tongliao', '', '市', '150500', '0475', '5');
INSERT INTO `district` VALUES ('152', '鄂尔多斯', '7', 'e', 'eeds', 'eerduosi', '', '市', '150600', '0477', '6');
INSERT INTO `district` VALUES ('153', '呼伦贝尔', '7', 'h', 'hlbe', 'hulunbeier', '', '市', '150700', '0470', '7');
INSERT INTO `district` VALUES ('154', '巴彦淖尔', '7', 'b', 'byne', 'bayannaoer', '', '市', '150800', '0478', '8');
INSERT INTO `district` VALUES ('155', '乌兰察布', '7', 'w', 'wlcb', 'wulanchabu', '', '市', '150900', '0474', '9');
INSERT INTO `district` VALUES ('156', '兴安', '7', 'x', 'xa', 'xingan', '', '盟', '152200', '0482', '10');
INSERT INTO `district` VALUES ('157', '锡林郭勒', '7', 'x', 'xlgl', 'xilinguole', '', '盟', '152500', '0479', '11');
INSERT INTO `district` VALUES ('158', '阿拉善', '7', 'a', 'als', 'alashan', '', '盟', '152900', '0483', '12');
INSERT INTO `district` VALUES ('159', '沈阳', '8', 's', 'sy', 'shenyang', '', '市', '210100', '024', '1');
INSERT INTO `district` VALUES ('160', '大连', '8', 'd', 'dl', 'dalian', '', '市', '210200', '0411', '2');
INSERT INTO `district` VALUES ('161', '鞍山', '8', 'a', 'as', 'anshan', '', '市', '210300', '0412', '3');
INSERT INTO `district` VALUES ('162', '抚顺', '8', 'f', 'fs', 'fushun', '', '市', '210400', '0413', '4');
INSERT INTO `district` VALUES ('163', '本溪', '8', 'b', 'bx', 'benxi', '', '市', '210500', '0414', '5');
INSERT INTO `district` VALUES ('164', '丹东', '8', 'd', 'dd', 'dandong', '', '市', '210600', '0415', '6');
INSERT INTO `district` VALUES ('165', '锦州', '8', 'j', 'jz', 'jinzhou', '', '市', '210700', '0416', '7');
INSERT INTO `district` VALUES ('166', '营口', '8', 'y', 'yk', 'yingkou', '', '市', '210800', '0417', '8');
INSERT INTO `district` VALUES ('167', '阜新', '8', 'f', 'fx', 'fuxin', '', '市', '210900', '0418', '9');
INSERT INTO `district` VALUES ('168', '辽阳', '8', 'l', 'ly', 'liaoyang', '', '市', '211000', '0419', '10');
INSERT INTO `district` VALUES ('169', '盘锦', '8', 'p', 'pj', 'panjin', '', '市', '211100', '0427', '11');
INSERT INTO `district` VALUES ('170', '铁岭', '8', 't', 'tl', 'tieling', '', '市', '211200', '0410', '12');
INSERT INTO `district` VALUES ('171', '朝阳', '8', 'c', 'cy', 'chaoyang', '', '市', '211300', '0421', '13');
INSERT INTO `district` VALUES ('172', '葫芦岛', '8', 'h', 'hld', 'huludao', '', '市', '211400', '0429', '14');
INSERT INTO `district` VALUES ('173', '长春', '9', 'z', 'zc', 'zhangchun', '', '市', '220100', '0431', '1');
INSERT INTO `district` VALUES ('174', '吉林', '9', 'j', 'jl', 'jilin', '', '市', '220200', '0432', '2');
INSERT INTO `district` VALUES ('175', '四平', '9', 's', 'sp', 'siping', '', '市', '220300', '0434', '3');
INSERT INTO `district` VALUES ('176', '辽源', '9', 'l', 'ly', 'liaoyuan', '', '市', '220400', '0437', '4');
INSERT INTO `district` VALUES ('177', '通化', '9', 't', 'th', 'tonghua', '', '市', '220500', '0435', '5');
INSERT INTO `district` VALUES ('178', '白山', '9', 'b', 'bs', 'baishan', '', '市', '220600', '0439', '6');
INSERT INTO `district` VALUES ('179', '松原', '9', 's', 'sy', 'songyuan', '', '市', '220700', '0438', '7');
INSERT INTO `district` VALUES ('180', '白城', '9', 'b', 'bc', 'baicheng', '', '市', '220800', '0436', '8');
INSERT INTO `district` VALUES ('181', '延边', '9', 'y', 'yb', 'yanbian', '朝鲜族', '自治州', '222400', '0433', '9');
INSERT INTO `district` VALUES ('182', '哈尔滨', '10', 'h', 'heb', 'haerbin', '', '市', '230100', '0451', '1');
INSERT INTO `district` VALUES ('183', '齐齐哈尔', '10', 'q', 'qqhe', 'qiqihaer', '', '市', '230200', '0452', '2');
INSERT INTO `district` VALUES ('184', '鸡西', '10', 'j', 'jx', 'jixi', '', '市', '230300', '0467', '3');
INSERT INTO `district` VALUES ('185', '鹤岗', '10', 'h', 'hg', 'hegang', '', '市', '230400', '0468', '4');
INSERT INTO `district` VALUES ('186', '双鸭山', '10', 's', 'sys', 'shuangyashan', '', '市', '230500', '0469', '5');
INSERT INTO `district` VALUES ('187', '大庆', '10', 'd', 'dq', 'daqing', '', '市', '230600', '0459', '6');
INSERT INTO `district` VALUES ('188', '伊春', '10', 'y', 'yc', 'yichun', '', '市', '230700', '0458', '7');
INSERT INTO `district` VALUES ('189', '佳木斯', '10', 'j', 'jms', 'jiamusi', '', '市', '230800', '0454', '8');
INSERT INTO `district` VALUES ('190', '七台河', '10', 'q', 'qth', 'qitaihe', '', '市', '230900', '0464', '9');
INSERT INTO `district` VALUES ('191', '牡丹江', '10', 'm', 'mdj', 'mudanjiang', '', '市', '231000', '0453', '10');
INSERT INTO `district` VALUES ('192', '黑河', '10', 'h', 'hh', 'heihe', '', '市', '231100', '0456', '11');
INSERT INTO `district` VALUES ('193', '绥化', '10', 's', 'sh', 'suihua', '', '市', '231200', '0455', '12');
INSERT INTO `district` VALUES ('194', '大兴安岭', '10', 'd', 'dxal', 'daxinganling', '', '地区', '232700', '0457', '13');
INSERT INTO `district` VALUES ('195', '南京', '11', 'n', 'nj', 'nanjing', '', '市', '320100', '', '1');
INSERT INTO `district` VALUES ('196', '无锡', '11', 'w', 'wx', 'wuxi', '', '市', '320200', '0510', '2');
INSERT INTO `district` VALUES ('197', '徐州', '11', 'x', 'xz', 'xuzhou', '', '市', '320300', '0516', '3');
INSERT INTO `district` VALUES ('198', '常州', '11', 'c', 'cz', 'changzhou', '', '市', '320400', '0519', '4');
INSERT INTO `district` VALUES ('199', '苏州', '11', 's', 'sz', 'suzhou', '', '市', '320500', '0512', '5');
INSERT INTO `district` VALUES ('200', '南通', '11', 'n', 'nt', 'nantong', '', '市', '320600', '0513', '6');
INSERT INTO `district` VALUES ('201', '连云港', '11', 'l', 'lyg', 'lianyungang', '', '市', '320700', '0518', '7');
INSERT INTO `district` VALUES ('202', '淮安', '11', 'h', 'ha', 'huaian', '', '市', '320800', '0517', '8');
INSERT INTO `district` VALUES ('203', '盐城', '11', 'y', 'yc', 'yancheng', '', '市', '320900', '0515', '9');
INSERT INTO `district` VALUES ('204', '扬州', '11', 'y', 'yz', 'yangzhou', '', '市', '321000', '0514', '10');
INSERT INTO `district` VALUES ('205', '镇江', '11', 'z', 'zj', 'zhenjiang', '', '市', '321100', '0511', '11');
INSERT INTO `district` VALUES ('206', '泰州', '11', 't', 'tz', 'taizhou', '', '市', '321200', '0523', '12');
INSERT INTO `district` VALUES ('207', '宿迁', '11', 's', 'sq', 'suqian', '', '市', '321300', '0527', '13');
INSERT INTO `district` VALUES ('208', '杭州', '12', 'h', 'hz', 'hangzhou', '', '市', '330100', '0571', '1');
INSERT INTO `district` VALUES ('209', '宁波', '12', 'n', 'nb', 'ningbo', '', '市', '330200', '0574', '2');
INSERT INTO `district` VALUES ('210', '温州', '12', 'w', 'wz', 'wenzhou', '', '市', '330300', '0577', '3');
INSERT INTO `district` VALUES ('211', '嘉兴', '12', 'j', 'jx', 'jiaxing', '', '市', '330400', '0573', '4');
INSERT INTO `district` VALUES ('212', '湖州', '12', 'h', 'hz', 'huzhou', '', '市', '330500', '0572', '5');
INSERT INTO `district` VALUES ('213', '绍兴', '12', 's', 'sx', 'shaoxing', '', '市', '330600', '0575', '6');
INSERT INTO `district` VALUES ('214', '金华', '12', 'j', 'jh', 'jinhua', '', '市', '330700', '0579', '7');
INSERT INTO `district` VALUES ('215', '衢州', '12', 'q', 'qz', 'quzhou', '', '市', '330800', '0570', '8');
INSERT INTO `district` VALUES ('216', '舟山', '12', 'z', 'zs', 'zhoushan', '', '市', '330900', '0580', '9');
INSERT INTO `district` VALUES ('217', '台州', '12', 't', 'tz', 'taizhou', '', '市', '331000', '0576', '10');
INSERT INTO `district` VALUES ('218', '丽水', '12', 'l', 'ls', 'lishui', '', '市', '331100', '0578', '11');
INSERT INTO `district` VALUES ('219', '合肥', '13', 'h', 'hf', 'hefei', '', '市', '340100', '0551', '1');
INSERT INTO `district` VALUES ('220', '芜湖', '13', 'w', 'wh', 'wuhu', '', '市', '340200', '0553', '2');
INSERT INTO `district` VALUES ('221', '蚌埠', '13', 'b', 'bb', 'bangbu', '', '市', '340300', '0552', '3');
INSERT INTO `district` VALUES ('222', '淮南', '13', 'h', 'hn', 'huainan', '', '市', '340400', '0554', '4');
INSERT INTO `district` VALUES ('223', '马鞍山', '13', 'm', 'mas', 'maanshan', '', '市', '340500', '0555', '5');
INSERT INTO `district` VALUES ('224', '淮北', '13', 'h', 'hb', 'huaibei', '', '市', '340600', '0561', '6');
INSERT INTO `district` VALUES ('225', '铜陵', '13', 't', 'tl', 'tongling', '', '市', '340700', '0562', '7');
INSERT INTO `district` VALUES ('226', '安庆', '13', 'a', 'aq', 'anqing', '', '市', '340800', '0556', '8');
INSERT INTO `district` VALUES ('227', '黄山', '13', 'h', 'hs', 'huangshan', '', '市', '341000', '0559', '9');
INSERT INTO `district` VALUES ('228', '滁州', '13', 'c', 'cz', 'chuzhou', '', '市', '341100', '0550', '10');
INSERT INTO `district` VALUES ('229', '阜阳', '13', 'f', 'fy', 'fuyang', '', '市', '341200', '0558', '11');
INSERT INTO `district` VALUES ('230', '宿州', '13', 's', 'sz', 'suzhou', '', '市', '341300', '0557', '12');
INSERT INTO `district` VALUES ('231', '六安', '13', 'l', 'la', 'liuan', '', '市', '341500', '0564', '13');
INSERT INTO `district` VALUES ('232', '亳州', '13', 'b', 'bz', 'bozhou', '', '市', '341600', '0558', '14');
INSERT INTO `district` VALUES ('233', '池州', '13', 'c', 'cz', 'chizhou', '', '市', '341700', '0566', '15');
INSERT INTO `district` VALUES ('234', '宣城', '13', 'x', 'xc', 'xuancheng', '', '市', '341800', '0563', '16');
INSERT INTO `district` VALUES ('235', '福州', '14', 'f', 'fz', 'fuzhou', '', '市', '350100', '0591', '1');
INSERT INTO `district` VALUES ('236', '厦门', '14', 's', 'sm', 'shamen', '', '市', '350200', '0592', '2');
INSERT INTO `district` VALUES ('237', '莆田', '14', 'p', 'pt', 'putian', '', '市', '350300', '0594', '3');
INSERT INTO `district` VALUES ('238', '三明', '14', 's', 'sm', 'sanming', '', '市', '350400', '0598', '4');
INSERT INTO `district` VALUES ('239', '泉州', '14', 'q', 'qz', 'quanzhou', '', '市', '350500', '0595', '5');
INSERT INTO `district` VALUES ('240', '漳州', '14', 'z', 'zz', 'zhangzhou', '', '市', '350600', '0596', '6');
INSERT INTO `district` VALUES ('241', '南平', '14', 'n', 'np', 'nanping', '', '市', '350700', '0599', '7');
INSERT INTO `district` VALUES ('242', '龙岩', '14', 'l', 'ly', 'longyan', '', '市', '350800', '0597', '8');
INSERT INTO `district` VALUES ('243', '宁德', '14', 'n', 'nd', 'ningde', '', '市', '350900', '0593', '9');
INSERT INTO `district` VALUES ('244', '南昌', '15', 'n', 'nc', 'nanchang', '', '市', '360100', '0791', '1');
INSERT INTO `district` VALUES ('245', '景德镇', '15', 'j', 'jdz', 'jingdezhen', '', '市', '360200', '0798', '2');
INSERT INTO `district` VALUES ('246', '萍乡', '15', 'p', 'px', 'pingxiang', '', '市', '360300', '0799', '3');
INSERT INTO `district` VALUES ('247', '九江', '15', 'j', 'jj', 'jiujiang', '', '市', '360400', '0792', '4');
INSERT INTO `district` VALUES ('248', '新余', '15', 'x', 'xy', 'xinyu', '', '市', '360500', '0790', '5');
INSERT INTO `district` VALUES ('249', '鹰潭', '15', 'y', 'yt', 'yingtan', '', '市', '360600', '0701', '6');
INSERT INTO `district` VALUES ('250', '赣州', '15', 'g', 'gz', 'ganzhou', '', '市', '360700', '0797', '7');
INSERT INTO `district` VALUES ('251', '吉安', '15', 'j', 'ja', 'jian', '', '市', '360800', '0796', '8');
INSERT INTO `district` VALUES ('252', '宜春', '15', 'y', 'yc', 'yichun', '', '市', '360900', '0795', '9');
INSERT INTO `district` VALUES ('253', '抚州', '15', 'f', 'fz', 'fuzhou', '', '市', '361000', '0794', '10');
INSERT INTO `district` VALUES ('254', '上饶', '15', 's', 'sr', 'shangrao', '', '市', '361100', '0793', '11');
INSERT INTO `district` VALUES ('255', '济南', '16', 'j', 'jn', 'jinan', '', '市', '370100', '0531', '1');
INSERT INTO `district` VALUES ('256', '青岛', '16', 'q', 'qd', 'qingdao', '', '市', '370200', '0532', '2');
INSERT INTO `district` VALUES ('257', '淄博', '16', 'z', 'zb', 'zibo', '', '市', '370300', '0533', '3');
INSERT INTO `district` VALUES ('258', '枣庄', '16', 'z', 'zz', 'zaozhuang', '', '市', '370400', '0632', '4');
INSERT INTO `district` VALUES ('259', '东营', '16', 'd', 'dy', 'dongying', '', '市', '370500', '0546', '5');
INSERT INTO `district` VALUES ('260', '烟台', '16', 'y', 'yt', 'yantai', '', '市', '370600', '0535', '6');
INSERT INTO `district` VALUES ('261', '潍坊', '16', 'w', 'wf', 'weifang', '', '市', '370700', '0536', '7');
INSERT INTO `district` VALUES ('262', '济宁', '16', 'j', 'jn', 'jining', '', '市', '370800', '0537', '8');
INSERT INTO `district` VALUES ('263', '泰安', '16', 't', 'ta', 'taian', '', '市', '370900', '0538', '9');
INSERT INTO `district` VALUES ('264', '威海', '16', 'w', 'wh', 'weihai', '', '市', '371000', '0631', '10');
INSERT INTO `district` VALUES ('265', '日照', '16', 'r', 'rz', 'rizhao', '', '市', '371100', '0633', '11');
INSERT INTO `district` VALUES ('266', '莱芜', '16', 'l', 'lw', 'laiwu', '', '市', '371200', '0634', '12');
INSERT INTO `district` VALUES ('267', '临沂', '16', 'l', 'ly', 'linyi', '', '市', '371300', '0539', '13');
INSERT INTO `district` VALUES ('268', '德州', '16', 'd', 'dz', 'dezhou', '', '市', '371400', '0534', '14');
INSERT INTO `district` VALUES ('269', '聊城', '16', 'l', 'lc', 'liaocheng', '', '市', '371500', '0635', '15');
INSERT INTO `district` VALUES ('270', '滨州', '16', 'b', 'bz', 'binzhou', '', '市', '371600', '0543', '16');
INSERT INTO `district` VALUES ('271', '菏泽', '16', 'h', 'hz', 'heze', '', '市', '371700', '0530', '17');
INSERT INTO `district` VALUES ('272', '郑州', '17', 'z', 'zz', 'zhengzhou', '', '市', '410100', '0371', '1');
INSERT INTO `district` VALUES ('273', '开封', '17', 'k', 'kf', 'kaifeng', '', '市', '410200', '0378', '2');
INSERT INTO `district` VALUES ('274', '洛阳', '17', 'l', 'ly', 'luoyang', '', '市', '410300', '0376', '3');
INSERT INTO `district` VALUES ('275', '平顶山', '17', 'p', 'pds', 'pingdingshan', '', '市', '410400', '0375', '4');
INSERT INTO `district` VALUES ('276', '安阳', '17', 'a', 'ay', 'anyang', '', '市', '410500', '0372', '5');
INSERT INTO `district` VALUES ('277', '鹤壁', '17', 'h', 'hb', 'hebi', '', '市', '410600', '0392', '6');
INSERT INTO `district` VALUES ('278', '新乡', '17', 'x', 'xx', 'xinxiang', '', '市', '410700', '0373', '7');
INSERT INTO `district` VALUES ('279', '焦作', '17', 'j', 'jz', 'jiaozuo', '', '市', '410800', '0391', '8');
INSERT INTO `district` VALUES ('280', '濮阳', '17', 'p', 'py', 'puyang', '', '市', '410900', '', '9');
INSERT INTO `district` VALUES ('281', '许昌', '17', 'x', 'xc', 'xuchang', '', '市', '411000', '0374', '10');
INSERT INTO `district` VALUES ('282', '漯河', '17', 'l', 'lh', 'luohe', '', '市', '411100', '0395', '11');
INSERT INTO `district` VALUES ('283', '三门峡', '17', 's', 'smx', 'sanmenxia', '', '市', '411200', '0398', '12');
INSERT INTO `district` VALUES ('284', '南阳', '17', 'n', 'ny', 'nanyang', '', '市', '411300', '0377', '13');
INSERT INTO `district` VALUES ('285', '商丘', '17', 's', 'sq', 'shangqiu', '', '市', '411400', '0370', '14');
INSERT INTO `district` VALUES ('286', '信阳', '17', 'x', 'xy', 'xinyang', '', '市', '411500', '0376', '15');
INSERT INTO `district` VALUES ('287', '周口', '17', 'z', 'zk', 'zhoukou', '', '市', '411600', '0394', '16');
INSERT INTO `district` VALUES ('288', '驻马店', '17', 'z', 'zmd', 'zhumadian', '', '市', '411700', '0396', '17');
INSERT INTO `district` VALUES ('289', '济源', '17', 'j', 'jy', 'jiyuan', '', '市', '419001', '0391', '18');
INSERT INTO `district` VALUES ('290', '武汉', '18', 'w', 'wh', 'wuhan', '', '市', '420100', '027', '1');
INSERT INTO `district` VALUES ('291', '黄石', '18', 'h', 'hs', 'huangshi', '', '市', '420200', '0714', '2');
INSERT INTO `district` VALUES ('292', '十堰', '18', 's', 'sy', 'shiyan', '', '市', '420300', '0719', '3');
INSERT INTO `district` VALUES ('293', '宜昌', '18', 'y', 'yc', 'yichang', '', '市', '420500', '0717', '4');
INSERT INTO `district` VALUES ('294', '襄阳', '18', 'x', 'xy', 'xiangyang', '', '市', '420600', '0710', '5');
INSERT INTO `district` VALUES ('295', '鄂州', '18', 'e', 'ez', 'ezhou', '', '市', '420700', '', '6');
INSERT INTO `district` VALUES ('296', '荆门', '18', 'j', 'jm', 'jingmen', '', '市', '420800', '0724', '7');
INSERT INTO `district` VALUES ('297', '孝感', '18', 'x', 'xg', 'xiaogan', '', '市', '420900', '0712', '8');
INSERT INTO `district` VALUES ('298', '荆州', '18', 'j', 'jz', 'jingzhou', '', '市', '421000', '0716', '9');
INSERT INTO `district` VALUES ('299', '黄冈', '18', 'h', 'hg', 'huanggang', '', '市', '421100', '0713', '10');
INSERT INTO `district` VALUES ('300', '咸宁', '18', 'x', 'xn', 'xianning', '', '市', '421200', '0715', '11');
INSERT INTO `district` VALUES ('301', '随州', '18', 's', 'sz', 'suizhou', '', '市', '421300', '0722', '12');
INSERT INTO `district` VALUES ('302', '恩施', '18', 'e', 'es', 'enshi', '土家族苗族', '自治州', '422800', '0718', '13');
INSERT INTO `district` VALUES ('303', '仙桃', '18', 'x', 'xt', 'xiantao', '', '市', '429004', '0728', '14');
INSERT INTO `district` VALUES ('304', '潜江', '18', 'q', 'qj', 'qianjiang', '', '市', '429005', '0728', '15');
INSERT INTO `district` VALUES ('305', '天门', '18', 't', 'tm', 'tianmen', '', '市', '429006', '0728', '16');
INSERT INTO `district` VALUES ('306', '神农架林区', '18', 's', 'snjlq', 'shennongjialinqu', '', '', '429021', '0719', '17');
INSERT INTO `district` VALUES ('307', '长沙', '19', 'z', 'zs', 'zhangsha', '', '市', '430100', '0731', '1');
INSERT INTO `district` VALUES ('308', '株洲', '19', 'z', 'zz', 'zhuzhou', '', '市', '430200', '0731', '2');
INSERT INTO `district` VALUES ('309', '湘潭', '19', 'x', 'xt', 'xiangtan', '', '市', '430300', '0731', '3');
INSERT INTO `district` VALUES ('310', '衡阳', '19', 'h', 'hy', 'hengyang', '', '市', '430400', '0734', '4');
INSERT INTO `district` VALUES ('311', '邵阳', '19', 's', 'sy', 'shaoyang', '', '市', '430500', '0739', '5');
INSERT INTO `district` VALUES ('312', '岳阳', '19', 'y', 'yy', 'yueyang', '', '市', '430600', '0730', '6');
INSERT INTO `district` VALUES ('313', '常德', '19', 'c', 'cd', 'changde', '', '市', '430700', '0736', '7');
INSERT INTO `district` VALUES ('314', '张家界', '19', 'z', 'zjj', 'zhangjiajie', '', '市', '430800', '0744', '8');
INSERT INTO `district` VALUES ('315', '益阳', '19', 'y', 'yy', 'yiyang', '', '市', '430900', '0737', '9');
INSERT INTO `district` VALUES ('316', '郴州', '19', 'c', 'cz', 'chenzhou', '', '市', '431000', '0735', '10');
INSERT INTO `district` VALUES ('317', '永州', '19', 'y', 'yz', 'yongzhou', '', '市', '431100', '0746', '11');
INSERT INTO `district` VALUES ('318', '怀化', '19', 'h', 'hh', 'huaihua', '', '市', '431200', '0745', '12');
INSERT INTO `district` VALUES ('319', '娄底', '19', 'l', 'ld', 'loudi', '', '市', '431300', '0738', '13');
INSERT INTO `district` VALUES ('320', '湘西', '19', 'x', 'xx', 'xiangxi', '土家族苗族', '自治州', '433100', '0743', '14');
INSERT INTO `district` VALUES ('321', '广州', '20', 'g', 'gz', 'guangzhou', '', '市', '440100', '020', '1');
INSERT INTO `district` VALUES ('322', '韶关', '20', 's', 'sg', 'shaoguan', '', '市', '440200', '0751', '2');
INSERT INTO `district` VALUES ('323', '深圳', '20', 's', 'sz', 'shenzhen', '', '市', '440300', '0755', '3');
INSERT INTO `district` VALUES ('324', '珠海', '20', 'z', 'zh', 'zhuhai', '', '市', '440400', '0756', '4');
INSERT INTO `district` VALUES ('325', '汕头', '20', 's', 'st', 'shantou', '', '市', '440500', '0754', '5');
INSERT INTO `district` VALUES ('326', '佛山', '20', 'f', 'fs', 'foshan', '', '市', '440600', '0757', '6');
INSERT INTO `district` VALUES ('327', '江门', '20', 'j', 'jm', 'jiangmen', '', '市', '440700', '0750', '7');
INSERT INTO `district` VALUES ('328', '湛江', '20', 'z', 'zj', 'zhanjiang', '', '市', '440800', '0759', '8');
INSERT INTO `district` VALUES ('329', '茂名', '20', 'm', 'mm', 'maoming', '', '市', '440900', '0668', '9');
INSERT INTO `district` VALUES ('330', '肇庆', '20', 'z', 'zq', 'zhaoqing', '', '市', '441200', '0758', '10');
INSERT INTO `district` VALUES ('331', '惠州', '20', 'h', 'hz', 'huizhou', '', '市', '441300', '0752', '11');
INSERT INTO `district` VALUES ('332', '梅州', '20', 'm', 'mz', 'meizhou', '', '市', '441400', '0753', '12');
INSERT INTO `district` VALUES ('333', '汕尾', '20', 's', 'sw', 'shanwei', '', '市', '441500', '0660', '13');
INSERT INTO `district` VALUES ('334', '河源', '20', 'h', 'hy', 'heyuan', '', '市', '441600', '0762', '14');
INSERT INTO `district` VALUES ('335', '阳江', '20', 'y', 'yj', 'yangjiang', '', '市', '441700', '0662', '15');
INSERT INTO `district` VALUES ('336', '清远', '20', 'q', 'qy', 'qingyuan', '', '市', '441800', '0763', '16');
INSERT INTO `district` VALUES ('337', '东莞', '20', 'd', 'dg', 'dongguan', '', '市', '441900', '0769', '17');
INSERT INTO `district` VALUES ('338', '中山', '20', 'z', 'zs', 'zhongshan', '', '市', '442000', '0760', '18');
INSERT INTO `district` VALUES ('339', '潮州', '20', 'c', 'cz', 'chaozhou', '', '市', '445100', '0768', '19');
INSERT INTO `district` VALUES ('340', '揭阳', '20', 'j', 'jy', 'jieyang', '', '市', '445200', '0663', '20');
INSERT INTO `district` VALUES ('341', '云浮', '20', 'y', 'yf', 'yunfu', '', '市', '445300', '0766', '21');
INSERT INTO `district` VALUES ('342', '南宁', '21', 'n', 'nn', 'nanning', '', '市', '450100', '0771', '1');
INSERT INTO `district` VALUES ('343', '柳州', '21', 'l', 'lz', 'liuzhou', '', '市', '450200', '0772', '2');
INSERT INTO `district` VALUES ('344', '桂林', '21', 'g', 'gl', 'guilin', '', '市', '450300', '0773', '3');
INSERT INTO `district` VALUES ('345', '梧州', '21', 'w', 'wz', 'wuzhou', '', '市', '450400', '0774', '4');
INSERT INTO `district` VALUES ('346', '北海', '21', 'b', 'bh', 'beihai', '', '市', '450500', '0779', '5');
INSERT INTO `district` VALUES ('347', '防城港', '21', 'f', 'fcg', 'fangchenggang', '', '市', '450600', '0770', '6');
INSERT INTO `district` VALUES ('348', '钦州', '21', 'q', 'qz', 'qinzhou', '', '市', '450700', '0777', '7');
INSERT INTO `district` VALUES ('349', '贵港', '21', 'g', 'gg', 'guigang', '', '市', '450800', '0775', '8');
INSERT INTO `district` VALUES ('350', '玉林', '21', 'y', 'yl', 'yulin', '', '市', '450900', '0775', '9');
INSERT INTO `district` VALUES ('351', '百色', '21', 'b', 'bs', 'baise', '', '市', '451000', '0776', '10');
INSERT INTO `district` VALUES ('352', '贺州', '21', 'h', 'hz', 'hezhou', '', '市', '451100', '0774', '11');
INSERT INTO `district` VALUES ('353', '河池', '21', 'h', 'hc', 'hechi', '', '市', '451200', '0778', '12');
INSERT INTO `district` VALUES ('354', '来宾', '21', 'l', 'lb', 'laibin', '', '市', '451300', '0772', '13');
INSERT INTO `district` VALUES ('355', '崇左', '21', 'c', 'cz', 'chongzuo', '', '市', '451400', '0771', '14');
INSERT INTO `district` VALUES ('356', '海口', '22', 'h', 'hk', 'haikou', '', '市', '460100', '0898', '1');
INSERT INTO `district` VALUES ('357', '三亚', '22', 's', 'sy', 'sanya', '', '市', '460200', '0898', '2');
INSERT INTO `district` VALUES ('358', '三沙', '22', 's', 'ss', 'sansha', '', '市', '460300', '0898', '3');
INSERT INTO `district` VALUES ('359', '五指山', '22', 'w', 'wzs', 'wuzhishan', '', '市', '469001', '0898', '4');
INSERT INTO `district` VALUES ('360', '琼海', '22', 'q', 'qh', 'qionghai', '', '市', '469002', '0898', '5');
INSERT INTO `district` VALUES ('361', '儋州', '22', 'd', 'dz', 'danzhou', '', '市', '460400', '0898', '6');
INSERT INTO `district` VALUES ('362', '文昌', '22', 'w', 'wc', 'wenchang', '', '市', '469005', '0898', '7');
INSERT INTO `district` VALUES ('363', '万宁', '22', 'w', 'wn', 'wanning', '', '市', '469006', '0898', '8');
INSERT INTO `district` VALUES ('364', '东方', '22', 'd', 'df', 'dongfang', '', '市', '469007', '0898', '9');
INSERT INTO `district` VALUES ('365', '定安', '22', 'd', 'da', 'dingan', '', '县', '469025', '0898', '10');
INSERT INTO `district` VALUES ('366', '屯昌', '22', 't', 'tc', 'tunchang', '', '县', '469026', '0898', '11');
INSERT INTO `district` VALUES ('367', '澄迈', '22', 'c', 'cm', 'chengmai', '', '县', '469027', '0898', '12');
INSERT INTO `district` VALUES ('368', '临高', '22', 'l', 'lg', 'lingao', '', '县', '469028', '0898', '13');
INSERT INTO `district` VALUES ('369', '白沙', '22', 'b', 'bs', 'baisha', '黎族', '自治县', '469030', '0898', '14');
INSERT INTO `district` VALUES ('370', '昌江', '22', 'c', 'cj', 'changjiang', '黎族', '自治县', '469031', '0898', '15');
INSERT INTO `district` VALUES ('371', '乐东', '22', 'l', 'ld', 'ledong', '黎族', '自治县', '469033', '0898', '16');
INSERT INTO `district` VALUES ('372', '陵水', '22', 'l', 'ls', 'lingshui', '黎族', '自治县', '469034', '0898', '17');
INSERT INTO `district` VALUES ('373', '保亭', '22', 'b', 'bt', 'baoting', '黎族苗族', '自治县', '469035', '0898', '18');
INSERT INTO `district` VALUES ('374', '琼中', '22', 'q', 'qz', 'qiongzhong', '黎族苗族', '自治县', '469036', '0898', '19');
INSERT INTO `district` VALUES ('375', '成都', '23', 'c', 'cd', 'chengdou', '', '市', '510100', '028', '1');
INSERT INTO `district` VALUES ('376', '自贡', '23', 'z', 'zg', 'zigong', '', '市', '510300', '0813', '2');
INSERT INTO `district` VALUES ('377', '攀枝花', '23', 'p', 'pzh', 'panzhihua', '', '市', '510400', '0812', '3');
INSERT INTO `district` VALUES ('378', '泸州', '23', 'l', 'lz', 'luzhou', '', '市', '510500', '0830', '4');
INSERT INTO `district` VALUES ('379', '德阳', '23', 'd', 'dy', 'deyang', '', '市', '510600', '0838', '5');
INSERT INTO `district` VALUES ('380', '绵阳', '23', 'm', 'my', 'mianyang', '', '市', '510700', '0816', '6');
INSERT INTO `district` VALUES ('381', '广元', '23', 'g', 'gy', 'guangyuan', '', '市', '510800', '0839', '7');
INSERT INTO `district` VALUES ('382', '遂宁', '23', 's', 'sn', 'suining', '', '市', '510900', '0825', '8');
INSERT INTO `district` VALUES ('383', '内江', '23', 'n', 'nj', 'neijiang', '', '市', '511000', '', '9');
INSERT INTO `district` VALUES ('384', '乐山', '23', 'l', 'ls', 'leshan', '', '市', '511100', '0833', '10');
INSERT INTO `district` VALUES ('385', '南充', '23', 'n', 'nc', 'nanchong', '', '市', '511300', '0817', '11');
INSERT INTO `district` VALUES ('386', '眉山', '23', 'm', 'ms', 'meishan', '', '市', '511400', '028', '12');
INSERT INTO `district` VALUES ('387', '宜宾', '23', 'y', 'yb', 'yibin', '', '市', '511500', '0831', '13');
INSERT INTO `district` VALUES ('388', '广安', '23', 'g', 'ga', 'guangan', '', '市', '511600', '0826', '14');
INSERT INTO `district` VALUES ('389', '达州', '23', 'd', 'dz', 'dazhou', '', '市', '511700', '0818', '15');
INSERT INTO `district` VALUES ('390', '雅安', '23', 'y', 'ya', 'yaan', '', '市', '511800', '0835', '16');
INSERT INTO `district` VALUES ('391', '巴中', '23', 'b', 'bz', 'bazhong', '', '市', '511900', '0827', '17');
INSERT INTO `district` VALUES ('392', '资阳', '23', 'z', 'zy', 'ziyang', '', '市', '512000', '028', '18');
INSERT INTO `district` VALUES ('393', '阿坝', '23', 'a', 'ab', 'aba', '藏族羌族', '自治州', '513200', '0837', '19');
INSERT INTO `district` VALUES ('394', '甘孜', '23', 'g', 'gz', 'ganzi', '藏族', '自治州', '513300', '0836', '20');
INSERT INTO `district` VALUES ('395', '凉山', '23', 'l', 'ls', 'liangshan', '彝族', '自治州', '615000', '0834', '21');
INSERT INTO `district` VALUES ('396', '贵阳', '24', 'g', 'gy', 'guiyang', '', '市', '520100', '0851', '1');
INSERT INTO `district` VALUES ('397', '六盘水', '24', 'l', 'lps', 'liupanshui', '', '市', '520200', '0858', '2');
INSERT INTO `district` VALUES ('398', '遵义', '24', 'z', 'zy', 'zunyi', '', '市', '520300', '0852', '3');
INSERT INTO `district` VALUES ('399', '安顺', '24', 'a', 'as', 'anshun', '', '市', '520400', '0853', '4');
INSERT INTO `district` VALUES ('400', '毕节', '24', 'b', 'bj', 'bijie', '', '市', '520500', '0857', '5');
INSERT INTO `district` VALUES ('401', '铜仁', '24', 't', 'tr', 'tongren', '', '市', '520600', '0856', '6');
INSERT INTO `district` VALUES ('402', '黔西南', '24', 'q', 'qxn', 'qianxinan', '布依族苗族', '自治州', '522300', '0859', '7');
INSERT INTO `district` VALUES ('403', '黔东南', '24', 'q', 'qdn', 'qiandongnan', '苗族侗族', '自治州', '522600', '0855', '8');
INSERT INTO `district` VALUES ('404', '黔南', '24', 'q', 'qn', 'qiannan', '布依族苗族', '自治州', '522700', '0854', '9');
INSERT INTO `district` VALUES ('405', '昆明', '25', 'k', 'km', 'kunming', '', '市', '530100', '0871', '1');
INSERT INTO `district` VALUES ('406', '曲靖', '25', 'q', 'qj', 'qujing', '', '市', '530300', '0874', '2');
INSERT INTO `district` VALUES ('407', '玉溪', '25', 'y', 'yx', 'yuxi', '', '市', '530400', '0877', '3');
INSERT INTO `district` VALUES ('408', '昭通', '25', 'z', 'zt', 'zhaotong', '', '市', '530600', '0870', '4');
INSERT INTO `district` VALUES ('409', '丽江', '25', 'l', 'lj', 'lijiang', '', '市', '530700', '0888', '5');
INSERT INTO `district` VALUES ('410', '普洱', '25', 'p', 'pe', 'puer', '', '市', '530800', '0879', '6');
INSERT INTO `district` VALUES ('411', '临沧', '25', 'l', 'lc', 'lincang', '', '市', '530900', '0883', '7');
INSERT INTO `district` VALUES ('412', '楚雄', '25', 'c', 'cx', 'chuxiong', '彝族', '自治州', '532300', '0878', '8');
INSERT INTO `district` VALUES ('413', '红河', '25', 'h', 'hh', 'honghe', '哈尼族彝族', '自治州', '532500', '0873', '9');
INSERT INTO `district` VALUES ('414', '文山', '25', 'w', 'ws', 'wenshan', '壮族苗族', '自治州', '532600', '0876', '10');
INSERT INTO `district` VALUES ('415', '西双版纳', '25', 'x', 'xsbn', 'xishuangbanna', '傣族', '自治州', '532800', '0691', '11');
INSERT INTO `district` VALUES ('416', '大理', '25', 'd', 'dl', 'dali', '白族', '自治州', '532900', '0872', '12');
INSERT INTO `district` VALUES ('417', '德宏', '25', 'd', 'dh', 'dehong', '傣族景颇族', '自治州', '533100', '0692', '13');
INSERT INTO `district` VALUES ('418', '怒江', '25', 'n', 'nj', 'nujiang', '傈僳族', '自治州', '533300', '0886', '14');
INSERT INTO `district` VALUES ('419', '迪庆', '25', 'd', 'dq', 'diqing', '藏族', '自治州', '533400', '0887', '15');
INSERT INTO `district` VALUES ('420', '保山', '25', 'b', 'bs', 'baoshan', '', '市', '678000', '0875', '16');
INSERT INTO `district` VALUES ('421', '拉萨', '26', 'l', 'ls', 'lasa', '', '市', '540100', '0891', '1');
INSERT INTO `district` VALUES ('422', '昌都', '26', 'c', 'cd', 'changdou', '', '市', '542100', '0895', '2');
INSERT INTO `district` VALUES ('423', '山南', '26', 's', 'sn', 'shannan', '', '地区', '542200', '0893', '3');
INSERT INTO `district` VALUES ('424', '日喀则', '26', 'r', 'rkz', 'rikaze', '', '市', '542300', '0892', '4');
INSERT INTO `district` VALUES ('425', '那曲', '26', 'n', 'nq', 'neiqu', '', '地区', '542400', '0896', '5');
INSERT INTO `district` VALUES ('426', '阿里', '26', 'a', 'al', 'ali', '', '地区', '542500', '0897', '6');
INSERT INTO `district` VALUES ('427', '林芝', '26', 'l', 'lz', 'linzhi', '', '市', '540400', '0894', '7');
INSERT INTO `district` VALUES ('428', '西安', '27', 'x', 'xa', 'xian', '', '市', '610100', '029', '1');
INSERT INTO `district` VALUES ('429', '铜川', '27', 't', 'tc', 'tongchuan', '', '市', '610200', '0919', '2');
INSERT INTO `district` VALUES ('430', '宝鸡', '27', 'b', 'bj', 'baoji', '', '市', '610300', '0917', '3');
INSERT INTO `district` VALUES ('431', '咸阳', '27', 'x', 'xy', 'xianyang', '', '市', '610400', '029', '4');
INSERT INTO `district` VALUES ('432', '渭南', '27', 'w', 'wn', 'weinan', '', '市', '610500', '0913', '5');
INSERT INTO `district` VALUES ('433', '延安', '27', 'y', 'ya', 'yanan', '', '市', '610600', '0911', '6');
INSERT INTO `district` VALUES ('434', '汉中', '27', 'h', 'hz', 'hanzhong', '', '市', '610700', '0916', '7');
INSERT INTO `district` VALUES ('435', '榆林', '27', 'y', 'yl', 'yulin', '', '市', '610800', '0912', '8');
INSERT INTO `district` VALUES ('436', '安康', '27', 'a', 'ak', 'ankang', '', '市', '610900', '0915', '9');
INSERT INTO `district` VALUES ('437', '商洛', '27', 's', 'sl', 'shangluo', '', '市', '611000', '0914', '10');
INSERT INTO `district` VALUES ('438', '兰州', '28', 'l', 'lz', 'lanzhou', '', '市', '620100', '0931', '1');
INSERT INTO `district` VALUES ('439', '嘉峪关', '28', 'j', 'jyg', 'jiayuguan', '', '市', '620200', '0937', '2');
INSERT INTO `district` VALUES ('440', '金昌', '28', 'j', 'jc', 'jinchang', '', '市', '620300', '0935', '3');
INSERT INTO `district` VALUES ('441', '白银', '28', 'b', 'by', 'baiyin', '', '市', '620400', '0943', '4');
INSERT INTO `district` VALUES ('442', '天水', '28', 't', 'ts', 'tianshui', '', '市', '620500', '0938', '5');
INSERT INTO `district` VALUES ('443', '武威', '28', 'w', 'ww', 'wuwei', '', '市', '620600', '0935', '6');
INSERT INTO `district` VALUES ('444', '张掖', '28', 'z', 'zy', 'zhangye', '', '市', '620700', '0936', '7');
INSERT INTO `district` VALUES ('445', '平凉', '28', 'p', 'pl', 'pingliang', '', '市', '620800', '0933', '8');
INSERT INTO `district` VALUES ('446', '酒泉', '28', 'j', 'jq', 'jiuquan', '', '市', '620900', '0937', '9');
INSERT INTO `district` VALUES ('447', '庆阳', '28', 'q', 'qy', 'qingyang', '', '市', '621000', '0934', '10');
INSERT INTO `district` VALUES ('448', '定西', '28', 'd', 'dx', 'dingxi', '', '市', '621100', '0932', '11');
INSERT INTO `district` VALUES ('449', '陇南', '28', 'l', 'ln', 'longnan', '', '市', '621200', '0939', '12');
INSERT INTO `district` VALUES ('450', '临夏', '28', 'l', 'lx', 'linxia', '回族', '自治州', '622900', '0930', '13');
INSERT INTO `district` VALUES ('451', '甘南', '28', 'g', 'gn', 'gannan', '藏族', '自治州', '623000', '0941', '14');
INSERT INTO `district` VALUES ('452', '西宁', '29', 'x', 'xn', 'xining', '', '市', '630100', '0971', '1');
INSERT INTO `district` VALUES ('453', '海东', '29', 'h', 'hd', 'haidong', '', '市', '632100', '0972', '2');
INSERT INTO `district` VALUES ('454', '海北', '29', 'h', 'hb', 'haibei', '藏族', '自治州', '632200', '0970', '3');
INSERT INTO `district` VALUES ('455', '黄南', '29', 'h', 'hn', 'huangnan', '藏族', '自治州', '632300', '0973', '4');
INSERT INTO `district` VALUES ('456', '海南', '29', 'h', 'hn', 'hainan', '藏族', '自治州', '632500', '0974', '5');
INSERT INTO `district` VALUES ('457', '果洛', '29', 'g', 'gl', 'guoluo', '藏族', '自治州', '632600', '0975', '6');
INSERT INTO `district` VALUES ('458', '玉树', '29', 'y', 'ys', 'yushu', '藏族', '自治州', '632700', '0976', '7');
INSERT INTO `district` VALUES ('459', '海西', '29', 'h', 'hx', 'haixi', '蒙古族藏族', '自治州', '632800', '0977', '8');
INSERT INTO `district` VALUES ('460', '银川', '30', 'y', 'yc', 'yinchuan', '', '市', '640100', '0951', '1');
INSERT INTO `district` VALUES ('461', '石嘴山', '30', 's', 'szs', 'shizuishan', '', '市', '640200', '0952', '2');
INSERT INTO `district` VALUES ('462', '吴忠', '30', 'w', 'wz', 'wuzhong', '', '市', '640300', '0953', '3');
INSERT INTO `district` VALUES ('463', '固原', '30', 'g', 'gy', 'guyuan', '', '市', '640400', '0954', '4');
INSERT INTO `district` VALUES ('464', '中卫', '30', 'z', 'zw', 'zhongwei', '', '市', '640500', '', '5');
INSERT INTO `district` VALUES ('465', '乌鲁木齐', '31', 'w', 'wlmq', 'wulumuqi', '', '市', '650100', '0991', '1');
INSERT INTO `district` VALUES ('466', '克拉玛依', '31', 'k', 'klmy', 'kelamayi', '', '市', '650200', '0990', '2');
INSERT INTO `district` VALUES ('467', '吐鲁番', '31', 't', 'tlf', 'tulufan', '', '市', '652100', '0995', '3');
INSERT INTO `district` VALUES ('468', '哈密', '31', 'h', 'hm', 'hami', '回族', '地区', '652200', '0902', '4');
INSERT INTO `district` VALUES ('469', '昌吉', '31', 'c', 'cj', 'changji', '', '自治州', '652300', '0994', '5');
INSERT INTO `district` VALUES ('470', '博尔塔拉', '31', 'b', 'betl', 'boertala', '蒙古', '自治州', '652700', '0909', '6');
INSERT INTO `district` VALUES ('471', '巴音郭楞', '31', 'b', 'bygl', 'bayinguoleng', '蒙古', '自治州', '652800', '0996', '7');
INSERT INTO `district` VALUES ('472', '阿克苏', '31', 'a', 'aks', 'akesu', '柯尔克孜', '地区', '652900', '0997', '8');
INSERT INTO `district` VALUES ('473', '克孜勒苏', '31', 'k', 'kzls', 'kezilesu', '', '自治州', '653000', '0908', '9');
INSERT INTO `district` VALUES ('474', '喀什', '31', 'k', 'ks', 'kashi', '', '地区', '653100', '0998', '10');
INSERT INTO `district` VALUES ('475', '和田', '31', 'h', 'ht', 'hetian', '哈萨克', '地区', '653200', '0903', '11');
INSERT INTO `district` VALUES ('476', '伊犁', '31', 'y', 'yl', 'yili', '', '自治州', '654000', '0999', '12');
INSERT INTO `district` VALUES ('477', '塔城', '31', 't', 'tc', 'tacheng', '', '地区', '654200', '0901', '13');
INSERT INTO `district` VALUES ('478', '阿勒泰', '31', 'a', 'alt', 'aletai', '', '地区', '654300', '0906', '14');
INSERT INTO `district` VALUES ('479', '石河子', '31', 's', 'shz', 'shihezi', '', '市', '659001', '0993', '15');
INSERT INTO `district` VALUES ('480', '阿拉尔', '31', 'a', 'ale', 'alaer', '', '市', '659002', '0997', '16');
INSERT INTO `district` VALUES ('481', '图木舒克', '31', 't', 'tmsk', 'tumushuke', '', '市', '659003', '0998', '17');
INSERT INTO `district` VALUES ('482', '五家渠', '31', 'w', 'wjq', 'wujiaqu', '', '市', '659004', '0994', '18');
INSERT INTO `district` VALUES ('483', '北屯', '31', 'b', 'bt', 'beitun', '', '市', '659005', '0906', '19');
INSERT INTO `district` VALUES ('484', '铁门关', '31', 't', 'tmg', 'tiemenguan', '', '市', '659006', '0996', '20');
INSERT INTO `district` VALUES ('485', '台北', '32', 't', 'tb', 'taibei', '', '市', '63', '02', '1');
INSERT INTO `district` VALUES ('486', '高雄', '32', 'g', 'gx', 'gaoxiong', '', '市', '64', '07', '2');
INSERT INTO `district` VALUES ('487', '基隆', '32', 'j', 'jl', 'jilong', '', '市', '10017', '02', '3');
INSERT INTO `district` VALUES ('488', '台中', '32', 't', 'tz', 'taizhong', '', '市', '10019', '04', '4');
INSERT INTO `district` VALUES ('489', '台南', '32', 't', 'tn', 'tainan', '', '市', '10021', '06', '5');
INSERT INTO `district` VALUES ('490', '新竹', '32', 'x', 'xz', 'xinzhu', '', '市', '10018', '', '6');
INSERT INTO `district` VALUES ('491', '嘉义', '32', 'j', 'jy', 'jiayi', '', '市', '10020', '05', '7');
INSERT INTO `district` VALUES ('492', '新北', '32', 'x', 'xb', 'xinbei', '', '市', '', '', '8');
INSERT INTO `district` VALUES ('493', '宜兰', '32', 'y', 'yl', 'yilan', '', '县', '10002', '', '9');
INSERT INTO `district` VALUES ('494', '桃园', '32', 't', 'ty', 'taoyuan', '', '县', '10003', '', '10');
INSERT INTO `district` VALUES ('495', '新竹', '32', 'x', 'xz', 'xinzhu', '', '县', '10004', '', '11');
INSERT INTO `district` VALUES ('496', '苗栗', '32', 'm', 'ml', 'miaoli', '', '县', '10005', '', '12');
INSERT INTO `district` VALUES ('497', '彰化', '32', 'z', 'zh', 'zhanghua', '', '县', '10007', '', '13');
INSERT INTO `district` VALUES ('498', '南投', '32', 'n', 'nt', 'nantou', '', '县', '10008', '', '14');
INSERT INTO `district` VALUES ('499', '云林', '32', 'y', 'yl', 'yunlin', '', '县', '10009', '', '15');
INSERT INTO `district` VALUES ('500', '嘉义', '32', 'j', 'jy', 'jiayi', '', '县', '10020', '', '16');
INSERT INTO `district` VALUES ('501', '屏东', '32', 'p', 'pd', 'pingdong', '', '县', '10013', '', '17');
INSERT INTO `district` VALUES ('502', '台东', '32', 't', 'td', 'taidong', '', '县', '10014', '', '18');
INSERT INTO `district` VALUES ('503', '花莲', '32', 'h', 'hl', 'hualian', '', '县', '10015', '', '19');
INSERT INTO `district` VALUES ('504', '澎湖', '32', 'p', 'ph', 'penghu', '', '县', '10016', '07', '20');
INSERT INTO `district` VALUES ('505', '连江', '32', 'l', 'lj', 'lianjiang', '', '县', '', '', '21');
INSERT INTO `district` VALUES ('506', '金门', '32', 'j', 'jm', 'jinmen', '', '县', '', '', '22');
INSERT INTO `district` VALUES ('507', '中西', '33', 'z', 'zx', 'zhongxi', '', '区', '', '852', '1');
INSERT INTO `district` VALUES ('508', '葵青', '33', 'k', 'kq', 'kuiqing', '', '区', '', '852', '2');
INSERT INTO `district` VALUES ('509', '元朗', '33', 'y', 'yl', 'yuanlang', '', '区', '', '852', '3');
INSERT INTO `district` VALUES ('510', '屯门', '33', 't', 'tm', 'tunmen', '', '区', '', '852', '4');
INSERT INTO `district` VALUES ('511', '荃湾', '33', 'q', 'qw', 'quanwan', '', '区', '', '852', '5');
INSERT INTO `district` VALUES ('512', '西贡', '33', 'x', 'xg', 'xigong', '', '区', '', '852', '6');
INSERT INTO `district` VALUES ('513', '沙田', '33', 's', 'st', 'shatian', '', '区', '', '852', '7');
INSERT INTO `district` VALUES ('514', '大埔', '33', 'd', 'dp', 'dapu', '', '区', '', '852', '8');
INSERT INTO `district` VALUES ('515', '北区', '33', 'b', 'bq', 'beiqu', '', '', '', '852', '9');
INSERT INTO `district` VALUES ('516', '观塘', '33', 'g', 'gt', 'guantang', '', '区', '', '852', '10');
INSERT INTO `district` VALUES ('517', '黄大仙', '33', 'h', 'hdx', 'huangdaxian', '', '区', '', '852', '11');
INSERT INTO `district` VALUES ('518', '深水埗', '33', 's', 'ssb', 'shenshuibu', '', '区', '', '852', '12');
INSERT INTO `district` VALUES ('519', '油尖旺', '33', 'y', 'yjw', 'youjianwang', '', '区', '', '852', '13');
INSERT INTO `district` VALUES ('520', '九龙城', '33', 'j', 'jlc', 'jiulongcheng', '', '区', '', '852', '14');
INSERT INTO `district` VALUES ('521', '南区', '33', 'n', 'nq', 'nanqu', '', '', '', '852', '15');
INSERT INTO `district` VALUES ('522', '东区', '33', 'd', 'dq', 'dongqu', '', '', '', '852', '16');
INSERT INTO `district` VALUES ('523', '湾仔', '33', 'w', 'wz', 'wanzi', '', '区', '', '852', '17');
INSERT INTO `district` VALUES ('524', '离岛', '33', 'l', 'ld', 'lidao', '', '区', '', '852', '18');
INSERT INTO `district` VALUES ('525', '花地玛', '34', 'h', 'hdm', 'huadima', '', '堂区', '', '853', '1');
INSERT INTO `district` VALUES ('526', '圣安多', '34', 's', 'sad', 'shenganduo', '', '堂区', '', '853', '2');
INSERT INTO `district` VALUES ('527', '大堂区', '34', 'd', 'dtq', 'datangqu', '', '堂区', '', '853', '3');
INSERT INTO `district` VALUES ('528', '望德', '34', 'w', 'wd', 'wangde', '', '堂区', '', '853', '4');
INSERT INTO `district` VALUES ('529', '风顺', '34', 'f', 'fs', 'fengshun', '', '堂区', '', '853', '5');
INSERT INTO `district` VALUES ('530', '嘉模', '34', 'j', 'jm', 'jiamo', '', '堂区', '', '853', '6');
INSERT INTO `district` VALUES ('531', '圣方济各', '34', 's', 'sfjg', 'shengfangjige', '', '堂区', '', '853', '7');
INSERT INTO `district` VALUES ('532', '路氹城', '34', 'l', 'ldc', 'ludangcheng', '', '', '', '853', '8');
INSERT INTO `district` VALUES ('533', '长安', '125', 'z', 'za', 'zhangan', '', '区', '130102', '0311', '1');
INSERT INTO `district` VALUES ('535', '桥西', '125', 'q', 'qx', 'qiaoxi', '', '区', '130104', '0311', '2');
INSERT INTO `district` VALUES ('536', '新华', '125', 'x', 'xh', 'xinhua', '', '区', '130105', '0311', '3');
INSERT INTO `district` VALUES ('537', '井陉矿', '125', 'j', 'jxk', 'jingxingkuang', '', '区', '130107', '0311', '4');
INSERT INTO `district` VALUES ('538', '裕华', '125', 'y', 'yh', 'yuhua', '', '区', '130108', '0311', '5');
INSERT INTO `district` VALUES ('539', '井陉', '125', 'j', 'jx', 'jingxing', '', '县', '130121', '0311', '9');
INSERT INTO `district` VALUES ('540', '正定', '125', 'z', 'zd', 'zhengding', '', '县', '130123', '0311', '10');
INSERT INTO `district` VALUES ('541', '栾城', '125', 'l', 'lc', 'luancheng', '', '区', '130111', '0311', '8');
INSERT INTO `district` VALUES ('542', '行唐', '125', 'x', 'xt', 'xingtang', '', '县', '130125', '0311', '11');
INSERT INTO `district` VALUES ('543', '灵寿', '125', 'l', 'ls', 'lingshou', '', '县', '130126', '0311', '12');
INSERT INTO `district` VALUES ('544', '高邑', '125', 'g', 'gy', 'gaoyi', '', '县', '130127', '0311', '13');
INSERT INTO `district` VALUES ('545', '深泽', '125', 's', 'sz', 'shenze', '', '县', '130128', '0311', '14');
INSERT INTO `district` VALUES ('546', '赞皇', '125', 'z', 'zh', 'zanhuang', '', '县', '130129', '0311', '15');
INSERT INTO `district` VALUES ('547', '无极', '125', 'w', 'wj', 'wuji', '', '县', '130130', '0311', '16');
INSERT INTO `district` VALUES ('548', '平山', '125', 'p', 'ps', 'pingshan', '', '县', '130131', '0311', '17');
INSERT INTO `district` VALUES ('549', '元氏', '125', 'y', 'ys', 'yuanshi', '', '县', '130132', '0311', '18');
INSERT INTO `district` VALUES ('550', '赵县', '125', 'z', 'zx', 'zhaoxian', '', '', '130133', '0311', '19');
INSERT INTO `district` VALUES ('551', '辛集', '125', 'x', 'xj', 'xinji', '', '市', '130181', '0311', '20');
INSERT INTO `district` VALUES ('552', '藁城', '125', 'g', 'gc', 'gaocheng', '', '区', '130109', '0311', '6');
INSERT INTO `district` VALUES ('553', '晋州', '125', 'j', 'jz', 'jinzhou', '', '市', '130183', '0311', '21');
INSERT INTO `district` VALUES ('554', '新乐', '125', 'x', 'xl', 'xinle', '', '市', '130184', '0311', '22');
INSERT INTO `district` VALUES ('555', '鹿泉', '125', 'l', 'lq', 'luquan', '', '区', '130110', '0311', '7');
INSERT INTO `district` VALUES ('556', '路南', '126', 'l', 'ln', 'lunan', '', '区', '130202', '0315', '1');
INSERT INTO `district` VALUES ('557', '路北', '126', 'l', 'lb', 'lubei', '', '区', '130203', '0315', '2');
INSERT INTO `district` VALUES ('558', '古冶', '126', 'g', 'gy', 'guye', '', '区', '130204', '0315', '3');
INSERT INTO `district` VALUES ('559', '开平', '126', 'k', 'kp', 'kaiping', '', '区', '130205', '0315', '4');
INSERT INTO `district` VALUES ('560', '丰南', '126', 'f', 'fn', 'fengnan', '', '区', '130207', '0315', '5');
INSERT INTO `district` VALUES ('561', '丰润', '126', 'f', 'fr', 'fengrun', '', '区', '130208', '0315', '6');
INSERT INTO `district` VALUES ('562', '滦县', '126', 'l', 'lx', 'luanxian', '', '', '130223', '0315', '7');
INSERT INTO `district` VALUES ('563', '滦南', '126', 'l', 'ln', 'luannan', '', '县', '130224', '0315', '8');
INSERT INTO `district` VALUES ('564', '乐亭', '126', 'l', 'lt', 'leting', '', '县', '130225', '0315', '9');
INSERT INTO `district` VALUES ('565', '迁西', '126', 'q', 'qx', 'qianxi', '', '县', '130227', '0315', '10');
INSERT INTO `district` VALUES ('566', '玉田', '126', 'y', 'yt', 'yutian', '', '县', '130229', '0315', '11');
INSERT INTO `district` VALUES ('567', '唐海', '126', 't', 'th', 'tanghai', '', '县', '130230', '0315', '12');
INSERT INTO `district` VALUES ('568', '遵化', '126', 'z', 'zh', 'zunhua', '', '市', '130281', '0315', '13');
INSERT INTO `district` VALUES ('569', '迁安', '126', 'q', 'qa', 'qianan', '', '市', '130283', '0315', '14');
INSERT INTO `district` VALUES ('570', '海港', '127', 'h', 'hg', 'haigang', '', '区', '130302', '0335', '1');
INSERT INTO `district` VALUES ('571', '山海关', '127', 's', 'shg', 'shanhaiguan', '', '区', '130303', '0335', '2');
INSERT INTO `district` VALUES ('572', '北戴河', '127', 'b', 'bdh', 'beidaihe', '', '区', '130304', '0335', '3');
INSERT INTO `district` VALUES ('573', '青龙', '127', 'q', 'ql', 'qinglong', '满族', '自治县', '130321', '0335', '4');
INSERT INTO `district` VALUES ('574', '昌黎', '127', 'c', 'cl', 'changli', '', '县', '130322', '0335', '5');
INSERT INTO `district` VALUES ('575', '抚宁', '127', 'f', 'fn', 'funing', '', '县', '130323', '0335', '6');
INSERT INTO `district` VALUES ('576', '卢龙', '127', 'l', 'll', 'lulong', '', '县', '130324', '0335', '7');
INSERT INTO `district` VALUES ('577', '邯山', '128', 'h', 'hs', 'hanshan', '', '区', '130402', '0310', '1');
INSERT INTO `district` VALUES ('578', '丛台', '128', 'c', 'ct', 'congtai', '', '区', '130403', '0310', '2');
INSERT INTO `district` VALUES ('579', '复兴', '128', 'f', 'fx', 'fuxing', '', '区', '130404', '0310', '3');
INSERT INTO `district` VALUES ('580', '峰峰矿', '128', 'f', 'ffk', 'fengfengkuang', '', '区', '130406', '0310', '4');
INSERT INTO `district` VALUES ('581', '邯郸', '128', 'h', 'hd', 'handan', '', '县', '130421', '0310', '5');
INSERT INTO `district` VALUES ('582', '临漳', '128', 'l', 'lz', 'linzhang', '', '县', '130423', '0310', '6');
INSERT INTO `district` VALUES ('583', '成安', '128', 'c', 'ca', 'chengan', '', '县', '130424', '0310', '7');
INSERT INTO `district` VALUES ('584', '大名', '128', 'd', 'dm', 'daming', '', '县', '130425', '0310', '8');
INSERT INTO `district` VALUES ('585', '涉县', '128', 's', 'sx', 'shexian', '', '', '130426', '0310', '9');
INSERT INTO `district` VALUES ('586', '磁县', '128', 'c', 'cx', 'cixian', '', '', '130427', '0310', '10');
INSERT INTO `district` VALUES ('587', '肥乡', '128', 'f', 'fx', 'feixiang', '', '县', '130428', '0310', '11');
INSERT INTO `district` VALUES ('588', '永年', '128', 'y', 'yn', 'yongnian', '', '县', '130429', '0310', '12');
INSERT INTO `district` VALUES ('589', '邱县', '128', 'q', 'qx', 'qiuxian', '', '', '130430', '0310', '13');
INSERT INTO `district` VALUES ('590', '鸡泽', '128', 'j', 'jz', 'jize', '', '县', '130431', '0310', '14');
INSERT INTO `district` VALUES ('591', '广平', '128', 'g', 'gp', 'guangping', '', '县', '130432', '0310', '15');
INSERT INTO `district` VALUES ('592', '馆陶', '128', 'g', 'gt', 'guantao', '', '县', '130433', '0310', '16');
INSERT INTO `district` VALUES ('593', '魏县', '128', 'w', 'wx', 'weixian', '', '', '130434', '0310', '17');
INSERT INTO `district` VALUES ('594', '曲周', '128', 'q', 'qz', 'quzhou', '', '县', '130435', '0310', '18');
INSERT INTO `district` VALUES ('595', '武安', '128', 'w', 'wa', 'wuan', '', '市', '130481', '0310', '19');
INSERT INTO `district` VALUES ('596', '桥东', '129', 'q', 'qd', 'qiaodong', '', '区', '130502', '0319', '1');
INSERT INTO `district` VALUES ('597', '桥西', '129', 'q', 'qx', 'qiaoxi', '', '区', '130503', '0319', '2');
INSERT INTO `district` VALUES ('598', '邢台', '129', 'x', 'xt', 'xingtai', '', '县', '130521', '0319', '3');
INSERT INTO `district` VALUES ('599', '临城', '129', 'l', 'lc', 'lincheng', '', '县', '130522', '0319', '4');
INSERT INTO `district` VALUES ('600', '内丘', '129', 'n', 'nq', 'neiqiu', '', '县', '130523', '0319', '5');
INSERT INTO `district` VALUES ('601', '柏乡', '129', 'b', 'bx', 'boxiang', '', '县', '130524', '0319', '6');
INSERT INTO `district` VALUES ('602', '隆尧', '129', 'l', 'ly', 'longyao', '', '县', '130525', '0319', '7');
INSERT INTO `district` VALUES ('603', '任县', '129', 'r', 'rx', 'renxian', '', '', '130526', '0319', '8');
INSERT INTO `district` VALUES ('604', '南和', '129', 'n', 'nh', 'nanhe', '', '县', '130527', '0319', '9');
INSERT INTO `district` VALUES ('605', '宁晋', '129', 'n', 'nj', 'ningjin', '', '县', '130528', '0319', '10');
INSERT INTO `district` VALUES ('606', '巨鹿', '129', 'j', 'jl', 'julu', '', '县', '130529', '0319', '11');
INSERT INTO `district` VALUES ('607', '新河', '129', 'x', 'xh', 'xinhe', '', '县', '130530', '0319', '12');
INSERT INTO `district` VALUES ('608', '广宗', '129', 'g', 'gz', 'guangzong', '', '县', '130531', '0319', '13');
INSERT INTO `district` VALUES ('609', '平乡', '129', 'p', 'px', 'pingxiang', '', '县', '130532', '0319', '14');
INSERT INTO `district` VALUES ('610', '威县', '129', 'w', 'wx', 'weixian', '', '', '130533', '0319', '15');
INSERT INTO `district` VALUES ('611', '清河', '129', 'q', 'qh', 'qinghe', '', '县', '130534', '0319', '16');
INSERT INTO `district` VALUES ('612', '临西', '129', 'l', 'lx', 'linxi', '', '县', '130535', '0319', '17');
INSERT INTO `district` VALUES ('613', '南宫', '129', 'n', 'ng', 'nangong', '', '市', '130581', '0319', '18');
INSERT INTO `district` VALUES ('614', '沙河', '129', 's', 'sh', 'shahe', '', '市', '130582', '0319', '19');
INSERT INTO `district` VALUES ('615', '新市', '130', 'x', 'xs', 'xinshi', '', '区', '130602', '0312', '1');
INSERT INTO `district` VALUES ('616', '北市', '130', 'b', 'bs', 'beishi', '', '区', '130603', '0312', '2');
INSERT INTO `district` VALUES ('617', '南市', '130', 'n', 'ns', 'nanshi', '', '区', '130604', '0312', '3');
INSERT INTO `district` VALUES ('618', '满城', '130', 'm', 'mc', 'mancheng', '', '县', '130621', '0312', '4');
INSERT INTO `district` VALUES ('619', '清苑', '130', 'q', 'qy', 'qingyuan', '', '县', '130622', '0312', '5');
INSERT INTO `district` VALUES ('620', '涞水', '130', 'l', 'ls', 'laishui', '', '县', '130623', '0312', '6');
INSERT INTO `district` VALUES ('621', '阜平', '130', 'f', 'fp', 'fuping', '', '县', '130624', '0312', '7');
INSERT INTO `district` VALUES ('622', '徐水', '130', 'x', 'xs', 'xushui', '', '县', '130625', '0312', '8');
INSERT INTO `district` VALUES ('623', '定兴', '130', 'd', 'dx', 'dingxing', '', '县', '130626', '0312', '9');
INSERT INTO `district` VALUES ('624', '唐县', '130', 't', 'tx', 'tangxian', '', '', '130627', '0312', '10');
INSERT INTO `district` VALUES ('625', '高阳', '130', 'g', 'gy', 'gaoyang', '', '县', '130628', '0312', '11');
INSERT INTO `district` VALUES ('626', '容城', '130', 'r', 'rc', 'rongcheng', '', '县', '130629', '0312', '12');
INSERT INTO `district` VALUES ('627', '涞源', '130', 'l', 'ly', 'laiyuan', '', '县', '130630', '0312', '13');
INSERT INTO `district` VALUES ('628', '望都', '130', 'w', 'wd', 'wangdou', '', '县', '130631', '0312', '14');
INSERT INTO `district` VALUES ('629', '安新', '130', 'a', 'ax', 'anxin', '', '县', '130632', '0312', '15');
INSERT INTO `district` VALUES ('630', '易县', '130', 'y', 'yx', 'yixian', '', '', '130633', '0312', '16');
INSERT INTO `district` VALUES ('631', '曲阳', '130', 'q', 'qy', 'quyang', '', '县', '130634', '0312', '17');
INSERT INTO `district` VALUES ('632', '蠡县', '130', 'l', 'lx', 'lixian', '', '', '130635', '0312', '18');
INSERT INTO `district` VALUES ('633', '顺平', '130', 's', 'sp', 'shunping', '', '县', '130636', '0312', '19');
INSERT INTO `district` VALUES ('634', '博野', '130', 'b', 'by', 'boye', '', '县', '130637', '0312', '20');
INSERT INTO `district` VALUES ('635', '雄县', '130', 'x', 'xx', 'xiongxian', '', '', '130638', '0312', '21');
INSERT INTO `district` VALUES ('636', '涿州', '130', 'z', 'zz', 'zhuozhou', '', '市', '130681', '0312', '22');
INSERT INTO `district` VALUES ('637', '定州', '130', 'd', 'dz', 'dingzhou', '', '市', '130682', '0312', '23');
INSERT INTO `district` VALUES ('638', '安国', '130', 'a', 'ag', 'anguo', '', '市', '130683', '0312', '24');
INSERT INTO `district` VALUES ('639', '高碑店', '130', 'g', 'gbd', 'gaobeidian', '', '市', '130684', '0312', '25');
INSERT INTO `district` VALUES ('640', '桥东', '131', 'q', 'qd', 'qiaodong', '', '区', '130702', '0313', '1');
INSERT INTO `district` VALUES ('641', '桥西', '131', 'q', 'qx', 'qiaoxi', '', '区', '130703', '0313', '2');
INSERT INTO `district` VALUES ('642', '宣化', '131', 'x', 'xh', 'xuanhua', '', '区', '130705', '0313', '3');
INSERT INTO `district` VALUES ('643', '下花园', '131', 'x', 'xhy', 'xiahuayuan', '', '区', '130706', '0313', '4');
INSERT INTO `district` VALUES ('644', '宣化', '131', 'x', 'xh', 'xuanhua', '', '县', '130721', '0313', '5');
INSERT INTO `district` VALUES ('645', '张北', '131', 'z', 'zb', 'zhangbei', '', '县', '130722', '0313', '6');
INSERT INTO `district` VALUES ('646', '康保', '131', 'k', 'kb', 'kangbao', '', '县', '130723', '0313', '7');
INSERT INTO `district` VALUES ('647', '沽源', '131', 'g', 'gy', 'guyuan', '', '县', '130724', '0313', '8');
INSERT INTO `district` VALUES ('648', '尚义', '131', 's', 'sy', 'shangyi', '', '县', '130725', '0313', '9');
INSERT INTO `district` VALUES ('649', '蔚县', '131', 'y', 'yx', 'yuxian', '', '', '130726', '0313', '10');
INSERT INTO `district` VALUES ('650', '阳原', '131', 'y', 'yy', 'yangyuan', '', '县', '130727', '0313', '11');
INSERT INTO `district` VALUES ('651', '怀安', '131', 'h', 'ha', 'huaian', '', '县', '130728', '0313', '12');
INSERT INTO `district` VALUES ('652', '万全', '131', 'w', 'wq', 'wanquan', '', '县', '130729', '0313', '13');
INSERT INTO `district` VALUES ('653', '怀来', '131', 'h', 'hl', 'huailai', '', '县', '130730', '0313', '14');
INSERT INTO `district` VALUES ('654', '涿鹿', '131', 'z', 'zl', 'zhuolu', '', '县', '130731', '0313', '15');
INSERT INTO `district` VALUES ('655', '赤城', '131', 'c', 'cc', 'chicheng', '', '县', '130732', '0313', '16');
INSERT INTO `district` VALUES ('656', '崇礼', '131', 'c', 'cl', 'chongli', '', '县', '130733', '0313', '17');
INSERT INTO `district` VALUES ('657', '双桥', '132', 's', 'sq', 'shuangqiao', '', '区', '130802', '0314', '1');
INSERT INTO `district` VALUES ('658', '双滦', '132', 's', 'sl', 'shuangluan', '', '区', '130803', '0314', '2');
INSERT INTO `district` VALUES ('659', '鹰手营子', '132', 'y', 'ysyz', 'yingshouyingzi', '矿', '区', '130804', '0314', '3');
INSERT INTO `district` VALUES ('660', '承德', '132', 'c', 'cd', 'chengde', '', '县', '130821', '0314', '4');
INSERT INTO `district` VALUES ('661', '兴隆', '132', 'x', 'xl', 'xinglong', '', '县', '130822', '0314', '5');
INSERT INTO `district` VALUES ('662', '平泉', '132', 'p', 'pq', 'pingquan', '', '县', '130823', '0314', '6');
INSERT INTO `district` VALUES ('663', '滦平', '132', 'l', 'lp', 'luanping', '', '县', '130824', '0314', '7');
INSERT INTO `district` VALUES ('664', '隆化', '132', 'l', 'lh', 'longhua', '', '县', '130825', '0314', '8');
INSERT INTO `district` VALUES ('665', '丰宁', '132', 'f', 'fn', 'fengning', '满族', '自治县', '130826', '0314', '9');
INSERT INTO `district` VALUES ('666', '宽城', '132', 'k', 'kc', 'kuancheng', '满族', '自治县', '130827', '0314', '10');
INSERT INTO `district` VALUES ('667', '围场', '132', 'w', 'wc', 'weichang', '满族蒙古族', '自治县', '130828', '0314', '11');
INSERT INTO `district` VALUES ('668', '新华', '133', 'x', 'xh', 'xinhua', '', '区', '130902', '0317', '1');
INSERT INTO `district` VALUES ('669', '运河', '133', 'y', 'yh', 'yunhe', '', '区', '130903', '0317', '2');
INSERT INTO `district` VALUES ('670', '沧县', '133', 'c', 'cx', 'cangxian', '', '', '130921', '0317', '3');
INSERT INTO `district` VALUES ('671', '青县', '133', 'q', 'qx', 'qingxian', '', '', '130922', '0317', '4');
INSERT INTO `district` VALUES ('672', '东光', '133', 'd', 'dg', 'dongguang', '', '县', '130923', '0317', '5');
INSERT INTO `district` VALUES ('673', '海兴', '133', 'h', 'hx', 'haixing', '', '县', '130924', '0317', '6');
INSERT INTO `district` VALUES ('674', '盐山', '133', 'y', 'ys', 'yanshan', '', '县', '130925', '0317', '7');
INSERT INTO `district` VALUES ('675', '肃宁', '133', 's', 'sn', 'suning', '', '县', '130926', '0317', '8');
INSERT INTO `district` VALUES ('676', '南皮', '133', 'n', 'np', 'nanpi', '', '县', '130927', '0317', '9');
INSERT INTO `district` VALUES ('677', '吴桥', '133', 'w', 'wq', 'wuqiao', '', '县', '130928', '0317', '10');
INSERT INTO `district` VALUES ('678', '献县', '133', 'x', 'xx', 'xianxian', '', '', '130929', '0317', '11');
INSERT INTO `district` VALUES ('679', '孟村', '133', 'm', 'mc', 'mengcun', '回族', '自治县', '130930', '0317', '12');
INSERT INTO `district` VALUES ('680', '泊头', '133', 'b', 'bt', 'botou', '', '市', '130981', '0317', '13');
INSERT INTO `district` VALUES ('681', '任丘', '133', 'r', 'rq', 'renqiu', '', '市', '130982', '0317', '14');
INSERT INTO `district` VALUES ('682', '黄骅', '133', 'h', 'hh', 'huanghua', '', '市', '130983', '0317', '15');
INSERT INTO `district` VALUES ('683', '河间', '133', 'h', 'hj', 'hejian', '', '市', '130984', '0317', '16');
INSERT INTO `district` VALUES ('684', '安次', '134', 'a', 'ac', 'anci', '', '区', '131002', '0316', '1');
INSERT INTO `district` VALUES ('685', '广阳', '134', 'g', 'gy', 'guangyang', '', '区', '131003', '0316', '2');
INSERT INTO `district` VALUES ('686', '固安', '134', 'g', 'ga', 'guan', '', '县', '131022', '0316', '3');
INSERT INTO `district` VALUES ('687', '永清', '134', 'y', 'yq', 'yongqing', '', '县', '131023', '0316', '4');
INSERT INTO `district` VALUES ('688', '香河', '134', 'x', 'xh', 'xianghe', '', '县', '131024', '0316', '5');
INSERT INTO `district` VALUES ('689', '大城', '134', 'd', 'dc', 'dacheng', '', '县', '131025', '0316', '6');
INSERT INTO `district` VALUES ('690', '文安', '134', 'w', 'wa', 'wenan', '', '县', '131026', '0316', '7');
INSERT INTO `district` VALUES ('691', '大厂', '134', 'd', 'dc', 'dachang', '回族', '自治县', '131028', '0316', '8');
INSERT INTO `district` VALUES ('692', '霸州', '134', 'b', 'bz', 'bazhou', '', '市', '131081', '0316', '9');
INSERT INTO `district` VALUES ('693', '三河', '134', 's', 'sh', 'sanhe', '', '市', '131082', '0316', '10');
INSERT INTO `district` VALUES ('694', '桃城', '135', 't', 'tc', 'taocheng', '', '区', '131102', '0318', '1');
INSERT INTO `district` VALUES ('695', '枣强', '135', 'z', 'zq', 'zaoqiang', '', '县', '131121', '0318', '2');
INSERT INTO `district` VALUES ('696', '武邑', '135', 'w', 'wy', 'wuyi', '', '县', '131122', '0318', '3');
INSERT INTO `district` VALUES ('697', '武强', '135', 'w', 'wq', 'wuqiang', '', '县', '131123', '0318', '4');
INSERT INTO `district` VALUES ('698', '饶阳', '135', 'r', 'ry', 'raoyang', '', '县', '131124', '0318', '5');
INSERT INTO `district` VALUES ('699', '安平', '135', 'a', 'ap', 'anping', '', '县', '131125', '0318', '6');
INSERT INTO `district` VALUES ('700', '故城', '135', 'g', 'gc', 'gucheng', '', '县', '131126', '0318', '7');
INSERT INTO `district` VALUES ('701', '景县', '135', 'j', 'jx', 'jingxian', '', '', '131127', '0318', '8');
INSERT INTO `district` VALUES ('702', '阜城', '135', 'f', 'fc', 'fucheng', '', '县', '131128', '0318', '9');
INSERT INTO `district` VALUES ('703', '冀州', '135', 'j', 'jz', 'jizhou', '', '市', '131181', '0318', '10');
INSERT INTO `district` VALUES ('704', '深州', '135', 's', 'sz', 'shenzhou', '', '市', '131182', '0318', '11');
INSERT INTO `district` VALUES ('705', '小店', '136', 'x', 'xd', 'xiaodian', '', '区', '140105', '0351', '1');
INSERT INTO `district` VALUES ('706', '迎泽', '136', 'y', 'yz', 'yingze', '', '区', '140106', '0351', '2');
INSERT INTO `district` VALUES ('707', '杏花岭', '136', 'x', 'xhl', 'xinghualing', '', '区', '140107', '0351', '3');
INSERT INTO `district` VALUES ('708', '尖草坪', '136', 'j', 'jcp', 'jiancaoping', '', '区', '140108', '0351', '4');
INSERT INTO `district` VALUES ('709', '万柏林', '136', 'w', 'wbl', 'wanbolin', '', '区', '140109', '0351', '5');
INSERT INTO `district` VALUES ('710', '晋源', '136', 'j', 'jy', 'jinyuan', '', '区', '140110', '0351', '6');
INSERT INTO `district` VALUES ('711', '清徐', '136', 'q', 'qx', 'qingxu', '', '县', '140121', '0351', '7');
INSERT INTO `district` VALUES ('712', '阳曲', '136', 'y', 'yq', 'yangqu', '', '县', '140122', '0351', '8');
INSERT INTO `district` VALUES ('713', '娄烦', '136', 'l', 'lf', 'loufan', '', '县', '140123', '0351', '9');
INSERT INTO `district` VALUES ('714', '古交', '136', 'g', 'gj', 'gujiao', '', '市', '140181', '0351', '10');
INSERT INTO `district` VALUES ('715', '城区', '137', 'c', 'cq', 'chengqu', '', '', '140202', '0352', '1');
INSERT INTO `district` VALUES ('716', '矿区', '137', 'k', 'kq', 'kuangqu', '', '', '140203', '0352', '2');
INSERT INTO `district` VALUES ('717', '南郊', '137', 'n', 'nj', 'nanjiao', '', '区', '140211', '0352', '3');
INSERT INTO `district` VALUES ('718', '新荣', '137', 'x', 'xr', 'xinrong', '', '区', '140212', '0352', '4');
INSERT INTO `district` VALUES ('719', '阳高', '137', 'y', 'yg', 'yanggao', '', '县', '140221', '0352', '5');
INSERT INTO `district` VALUES ('720', '天镇', '137', 't', 'tz', 'tianzhen', '', '县', '140222', '0352', '6');
INSERT INTO `district` VALUES ('721', '广灵', '137', 'g', 'gl', 'guangling', '', '县', '140223', '0352', '7');
INSERT INTO `district` VALUES ('722', '灵丘', '137', 'l', 'lq', 'lingqiu', '', '县', '140224', '0352', '8');
INSERT INTO `district` VALUES ('723', '浑源', '137', 'h', 'hy', 'hunyuan', '', '县', '140225', '0352', '9');
INSERT INTO `district` VALUES ('724', '左云', '137', 'z', 'zy', 'zuoyun', '', '县', '140226', '0352', '10');
INSERT INTO `district` VALUES ('725', '大同', '137', 'd', 'dt', 'datong', '', '县', '140227', '0352', '11');
INSERT INTO `district` VALUES ('726', '城区', '138', 'c', 'cq', 'chengqu', '', '', '140302', '0353', '1');
INSERT INTO `district` VALUES ('727', '矿区', '138', 'k', 'kq', 'kuangqu', '', '', '140303', '0353', '2');
INSERT INTO `district` VALUES ('728', '郊区', '138', 'j', 'jq', 'jiaoqu', '', '', '140311', '0353', '3');
INSERT INTO `district` VALUES ('729', '平定', '138', 'p', 'pd', 'pingding', '', '县', '140321', '0353', '4');
INSERT INTO `district` VALUES ('730', '盂县', '138', 'y', 'yx', 'yuxian', '', '', '140322', '0353', '5');
INSERT INTO `district` VALUES ('731', '城区', '139', 'c', 'cq', 'chengqu', '', '', '140402', '0355', '1');
INSERT INTO `district` VALUES ('732', '郊区', '139', 'j', 'jq', 'jiaoqu', '', '', '140411', '0355', '2');
INSERT INTO `district` VALUES ('733', '长治', '139', 'z', 'zz', 'zhangzhi', '', '县', '140421', '0355', '3');
INSERT INTO `district` VALUES ('734', '襄垣', '139', 'x', 'xy', 'xiangyuan', '', '县', '140423', '0355', '4');
INSERT INTO `district` VALUES ('735', '屯留', '139', 't', 'tl', 'tunliu', '', '县', '140424', '0355', '5');
INSERT INTO `district` VALUES ('736', '平顺', '139', 'p', 'ps', 'pingshun', '', '县', '140425', '0355', '6');
INSERT INTO `district` VALUES ('737', '黎城', '139', 'l', 'lc', 'licheng', '', '县', '140426', '0355', '7');
INSERT INTO `district` VALUES ('738', '壶关', '139', 'h', 'hg', 'huguan', '', '县', '140427', '0355', '8');
INSERT INTO `district` VALUES ('739', '长子', '139', 'z', 'zz', 'zhangzi', '', '县', '140428', '0355', '9');
INSERT INTO `district` VALUES ('740', '武乡', '139', 'w', 'wx', 'wuxiang', '', '县', '140429', '0355', '10');
INSERT INTO `district` VALUES ('741', '沁县', '139', 'q', 'qx', 'qinxian', '', '', '140430', '0355', '11');
INSERT INTO `district` VALUES ('742', '沁源', '139', 'q', 'qy', 'qinyuan', '', '县', '140431', '0355', '12');
INSERT INTO `district` VALUES ('743', '潞城', '139', 'l', 'lc', 'lucheng', '', '县', '140481', '0355', '13');
INSERT INTO `district` VALUES ('744', '城区', '140', 'c', 'cq', 'chengqu', '', '', '140502', '0356', '1');
INSERT INTO `district` VALUES ('745', '沁水', '140', 'q', 'qs', 'qinshui', '', '县', '140521', '0356', '2');
INSERT INTO `district` VALUES ('746', '阳城', '140', 'y', 'yc', 'yangcheng', '', '县', '140522', '0356', '3');
INSERT INTO `district` VALUES ('747', '陵川', '140', 'l', 'lc', 'lingchuan', '', '县', '140524', '0356', '4');
INSERT INTO `district` VALUES ('748', '泽州', '140', 'z', 'zz', 'zezhou', '', '县', '140525', '0356', '5');
INSERT INTO `district` VALUES ('749', '高平', '140', 'g', 'gp', 'gaoping', '', '市', '140581', '0356', '6');
INSERT INTO `district` VALUES ('750', '朔城', '141', 's', 'sc', 'shuocheng', '', '区', '140602', '0349', '1');
INSERT INTO `district` VALUES ('751', '平鲁', '141', 'p', 'pl', 'pinglu', '', '区', '140603', '0349', '2');
INSERT INTO `district` VALUES ('752', '山阴', '141', 's', 'sy', 'shanyin', '', '县', '140621', '0349', '3');
INSERT INTO `district` VALUES ('753', '应县', '141', 'y', 'yx', 'yingxian', '', '', '140622', '0349', '4');
INSERT INTO `district` VALUES ('754', '右玉', '141', 'y', 'yy', 'youyu', '', '县', '140623', '0349', '5');
INSERT INTO `district` VALUES ('755', '怀仁', '141', 'h', 'hr', 'huairen', '', '县', '140624', '0349', '6');
INSERT INTO `district` VALUES ('756', '榆次', '142', 'y', 'yc', 'yuci', '', '区', '030600', '0354', '1');
INSERT INTO `district` VALUES ('757', '榆社', '142', 'y', 'ys', 'yushe', '', '县', '140721', '0354', '2');
INSERT INTO `district` VALUES ('758', '左权', '142', 'z', 'zq', 'zuoquan', '', '县', '140722', '0354', '3');
INSERT INTO `district` VALUES ('759', '和顺', '142', 'h', 'hs', 'heshun', '', '县', '140723', '0354', '4');
INSERT INTO `district` VALUES ('760', '昔阳', '142', 'x', 'xy', 'xiyang', '', '县', '140724', '0354', '5');
INSERT INTO `district` VALUES ('761', '寿阳', '142', 's', 'sy', 'shouyang', '', '县', '140725', '0354', '6');
INSERT INTO `district` VALUES ('762', '太谷', '142', 't', 'tg', 'taigu', '', '县', '140726', '0354', '7');
INSERT INTO `district` VALUES ('763', '祁县', '142', 'q', 'qx', 'qixian', '', '', '140727', '0354', '8');
INSERT INTO `district` VALUES ('764', '平遥', '142', 'p', 'py', 'pingyao', '', '县', '140728', '0354', '9');
INSERT INTO `district` VALUES ('765', '灵石', '142', 'l', 'ls', 'lingshi', '', '县', '140729', '0354', '10');
INSERT INTO `district` VALUES ('766', '介休', '142', 'j', 'jx', 'jiexiu', '', '市', '140781', '0354', '11');
INSERT INTO `district` VALUES ('767', '盐湖', '143', 'y', 'yh', 'yanhu', '', '区', '140802', '0359', '1');
INSERT INTO `district` VALUES ('768', '临猗', '143', 'l', 'ly', 'linyi', '', '县', '140821', '0359', '2');
INSERT INTO `district` VALUES ('769', '万荣', '143', 'w', 'wr', 'wanrong', '', '县', '140822', '0359', '3');
INSERT INTO `district` VALUES ('770', '闻喜', '143', 'w', 'wx', 'wenxi', '', '县', '140823', '0359', '4');
INSERT INTO `district` VALUES ('771', '稷山', '143', 'j', 'js', 'jishan', '', '县', '140824', '0359', '5');
INSERT INTO `district` VALUES ('772', '新绛', '143', 'x', 'xj', 'xinjiang', '', '县', '140825', '0359', '6');
INSERT INTO `district` VALUES ('773', '绛县', '143', 'j', 'jx', 'jiangxian', '', '', '140826', '0359', '7');
INSERT INTO `district` VALUES ('774', '垣曲', '143', 'y', 'yq', 'yuanqu', '', '县', '140827', '0359', '8');
INSERT INTO `district` VALUES ('775', '夏县', '143', 'x', 'xx', 'xiaxian', '', '', '140828', '0359', '9');
INSERT INTO `district` VALUES ('776', '平陆', '143', 'p', 'pl', 'pinglu', '', '县', '140829', '0359', '10');
INSERT INTO `district` VALUES ('777', '芮城', '143', 'r', 'rc', 'ruicheng', '', '县', '140830', '0359', '11');
INSERT INTO `district` VALUES ('778', '永济', '143', 'y', 'yj', 'yongji', '', '市', '140881', '0359', '12');
INSERT INTO `district` VALUES ('779', '河津', '143', 'h', 'hj', 'hejin', '', '市', '140882', '0359', '13');
INSERT INTO `district` VALUES ('780', '忻府', '144', 'x', 'xf', 'xinfu', '', '区', '140902', '0350', '1');
INSERT INTO `district` VALUES ('781', '定襄', '144', 'd', 'dx', 'dingxiang', '', '县', '140921', '0350', '2');
INSERT INTO `district` VALUES ('782', '五台', '144', 'w', 'wt', 'wutai', '', '县', '140922', '0350', '3');
INSERT INTO `district` VALUES ('783', '代县', '144', 'd', 'dx', 'daixian', '', '', '140923', '0350', '4');
INSERT INTO `district` VALUES ('784', '繁峙', '144', 'f', 'fz', 'fanzhi', '', '县', '140924', '0350', '5');
INSERT INTO `district` VALUES ('785', '宁武', '144', 'n', 'nw', 'ningwu', '', '县', '140925', '0350', '6');
INSERT INTO `district` VALUES ('786', '静乐', '144', 'j', 'jl', 'jingle', '', '县', '140926', '0350', '7');
INSERT INTO `district` VALUES ('787', '神池', '144', 's', 'sc', 'shenchi', '', '县', '140927', '0350', '8');
INSERT INTO `district` VALUES ('788', '五寨', '144', 'w', 'wz', 'wuzhai', '', '县', '140928', '0350', '9');
INSERT INTO `district` VALUES ('789', '岢岚', '144', 'k', 'kl', 'kelan', '', '县', '140929', '0350', '10');
INSERT INTO `district` VALUES ('790', '河曲', '144', 'h', 'hq', 'hequ', '', '县', '140930', '0350', '11');
INSERT INTO `district` VALUES ('791', '保德', '144', 'b', 'bd', 'baode', '', '县', '140931', '0350', '12');
INSERT INTO `district` VALUES ('792', '偏关', '144', 'p', 'pg', 'pianguan', '', '县', '140932', '0350', '13');
INSERT INTO `district` VALUES ('793', '原平', '144', 'y', 'yp', 'yuanping', '', '市', '140981', '0350', '14');
INSERT INTO `district` VALUES ('794', '尧都', '145', 'y', 'yd', 'yaodou', '', '区', '141002', '0357', '1');
INSERT INTO `district` VALUES ('795', '曲沃', '145', 'q', 'qw', 'quwo', '', '县', '141021', '0357', '2');
INSERT INTO `district` VALUES ('796', '翼城', '145', 'y', 'yc', 'yicheng', '', '县', '141022', '0357', '3');
INSERT INTO `district` VALUES ('797', '襄汾', '145', 'x', 'xf', 'xiangfen', '', '县', '141023', '0357', '4');
INSERT INTO `district` VALUES ('798', '洪洞', '145', 'h', 'hd', 'hongdong', '', '县', '141024', '0357', '5');
INSERT INTO `district` VALUES ('799', '古县', '145', 'g', 'gx', 'guxian', '', '', '141025', '0357', '6');
INSERT INTO `district` VALUES ('800', '安泽', '145', 'a', 'az', 'anze', '', '县', '141026', '0357', '7');
INSERT INTO `district` VALUES ('801', '浮山', '145', 'f', 'fs', 'fushan', '', '县', '141027', '0357', '8');
INSERT INTO `district` VALUES ('802', '吉县', '145', 'j', 'jx', 'jixian', '', '', '141028', '0357', '9');
INSERT INTO `district` VALUES ('803', '乡宁', '145', 'x', 'xn', 'xiangning', '', '县', '141029', '0357', '10');
INSERT INTO `district` VALUES ('804', '大宁', '145', 'd', 'dn', 'daning', '', '县', '141030', '0357', '11');
INSERT INTO `district` VALUES ('805', '隰县', '145', 'x', 'xx', 'xixian', '', '', '141031', '0357', '12');
INSERT INTO `district` VALUES ('806', '永和', '145', 'y', 'yh', 'yonghe', '', '县', '141032', '0357', '13');
INSERT INTO `district` VALUES ('807', '蒲县', '145', 'p', 'px', 'puxian', '', '', '141033', '0357', '14');
INSERT INTO `district` VALUES ('808', '汾西', '145', 'f', 'fx', 'fenxi', '', '县', '141034', '0357', '15');
INSERT INTO `district` VALUES ('809', '侯马', '145', 'h', 'hm', 'houma', '', '市', '141081', '0357', '16');
INSERT INTO `district` VALUES ('810', '霍州', '145', 'h', 'hz', 'huozhou', '', '市', '141082', '0357', '17');
INSERT INTO `district` VALUES ('811', '离石', '146', 'l', 'ls', 'lishi', '', '区', '141102', '0358', '1');
INSERT INTO `district` VALUES ('812', '文水', '146', 'w', 'ws', 'wenshui', '', '县', '141121', '0358', '2');
INSERT INTO `district` VALUES ('813', '交城', '146', 'j', 'jc', 'jiaocheng', '', '县', '141122', '0358', '3');
INSERT INTO `district` VALUES ('814', '兴县', '146', 'x', 'xx', 'xingxian', '', '', '141123', '0358', '4');
INSERT INTO `district` VALUES ('815', '临县', '146', 'l', 'lx', 'linxian', '', '', '141124', '0358', '5');
INSERT INTO `district` VALUES ('816', '柳林', '146', 'l', 'll', 'liulin', '', '县', '141125', '0358', '6');
INSERT INTO `district` VALUES ('817', '石楼', '146', 's', 'sl', 'shilou', '', '县', '141126', '0358', '7');
INSERT INTO `district` VALUES ('818', '岚县', '146', 'l', 'lx', 'lanxian', '', '', '141127', '0358', '8');
INSERT INTO `district` VALUES ('819', '方山', '146', 'f', 'fs', 'fangshan', '', '县', '141128', '0358', '9');
INSERT INTO `district` VALUES ('820', '中阳', '146', 'z', 'zy', 'zhongyang', '', '县', '141129', '0358', '10');
INSERT INTO `district` VALUES ('821', '交口', '146', 'j', 'jk', 'jiaokou', '', '县', '141130', '0358', '11');
INSERT INTO `district` VALUES ('822', '孝义', '146', 'x', 'xy', 'xiaoyi', '', '市', '141181', '0358', '12');
INSERT INTO `district` VALUES ('823', '汾阳', '146', 'f', 'fy', 'fenyang', '', '市', '141182', '0358', '13');
INSERT INTO `district` VALUES ('824', '新城', '147', 'x', 'xc', 'xincheng', '', '区', '150102', '0471', '1');
INSERT INTO `district` VALUES ('825', '回民', '147', 'h', 'hm', 'huimin', '', '区', '150103', '0471', '2');
INSERT INTO `district` VALUES ('826', '玉泉', '147', 'y', 'yq', 'yuquan', '', '区', '150104', '0471', '3');
INSERT INTO `district` VALUES ('827', '赛罕', '147', 's', 'sh', 'saihan', '', '区', '150105', '0471', '4');
INSERT INTO `district` VALUES ('828', '土默特左', '147', 't', 'tmtz', 'tumotezuo', '', '旗', '150121', '0471', '5');
INSERT INTO `district` VALUES ('829', '托克托', '147', 't', 'tkt', 'tuoketuo', '', '县', '150122', '0471', '6');
INSERT INTO `district` VALUES ('830', '和林格尔', '147', 'h', 'hlge', 'helingeer', '', '县', '150123', '0471', '7');
INSERT INTO `district` VALUES ('831', '清水河', '147', 'q', 'qsh', 'qingshuihe', '', '县', '150124', '0471', '8');
INSERT INTO `district` VALUES ('832', '武川', '147', 'w', 'wc', 'wuchuan', '', '县', '150125', '0471', '9');
INSERT INTO `district` VALUES ('833', '东河', '148', 'd', 'dh', 'donghe', '', '区', '150202', '0472', '1');
INSERT INTO `district` VALUES ('834', '昆都仑', '148', 'k', 'kdl', 'kundoulun', '', '区', '150203', '0472', '2');
INSERT INTO `district` VALUES ('835', '青山', '148', 'q', 'qs', 'qingshan', '', '区', '150204', '0472', '3');
INSERT INTO `district` VALUES ('836', '石拐', '148', 's', 'sg', 'shiguai', '', '区', '150205', '0472', '4');
INSERT INTO `district` VALUES ('837', '白云', '148', 'b', 'by', 'baiyun', '矿', '区', '150206', '0472', '5');
INSERT INTO `district` VALUES ('838', '九原', '148', 'j', 'jy', 'jiuyuan', '', '区', '150207', '0472', '6');
INSERT INTO `district` VALUES ('839', '土默特右', '148', 't', 'tmty', 'tumoteyou', '', '旗', '150221', '0472', '7');
INSERT INTO `district` VALUES ('840', '固阳', '148', 'g', 'gy', 'guyang', '', '县', '150222', '0472', '8');
INSERT INTO `district` VALUES ('841', '达尔罕茂明安', '148', 'd', 'dehmma', 'daerhanmaomingan', '联合', '旗', '150223', '0472', '9');
INSERT INTO `district` VALUES ('842', '海勃湾', '149', 'h', 'hbw', 'haibowan', '', '区', '150302', '0473', '1');
INSERT INTO `district` VALUES ('843', '海南', '149', 'h', 'hn', 'hainan', '', '区', '150303', '0473', '2');
INSERT INTO `district` VALUES ('844', '乌达', '149', 'w', 'wd', 'wuda', '', '区', '150304', '0473', '3');
INSERT INTO `district` VALUES ('845', '红山', '150', 'h', 'hs', 'hongshan', '', '区', '150402', '0476', '1');
INSERT INTO `district` VALUES ('846', '元宝山', '150', 'y', 'ybs', 'yuanbaoshan', '', '区', '150403', '0476', '2');
INSERT INTO `district` VALUES ('847', '松山', '150', 's', 'ss', 'songshan', '', '区', '150404', '0476', '3');
INSERT INTO `district` VALUES ('848', '阿鲁科尔沁', '150', 'a', 'alkeq', 'alukeerqin', '', '旗', '150421', '0476', '4');
INSERT INTO `district` VALUES ('849', '巴林左', '150', 'b', 'blz', 'balinzuo', '', '旗', '150422', '0476', '5');
INSERT INTO `district` VALUES ('850', '巴林右', '150', 'b', 'bly', 'balinyou', '', '旗', '150423', '0476', '6');
INSERT INTO `district` VALUES ('851', '林西', '150', 'l', 'lx', 'linxi', '', '县', '150424', '0476', '7');
INSERT INTO `district` VALUES ('852', '克什克腾', '150', 'k', 'kskt', 'keshenketeng', '', '旗', '150425', '0476', '8');
INSERT INTO `district` VALUES ('853', '翁牛特', '150', 'w', 'wnt', 'wengniute', '', '旗', '150426', '0476', '9');
INSERT INTO `district` VALUES ('854', '喀喇沁', '150', 'k', 'klq', 'kalaqin', '', '旗', '150428', '0476', '10');
INSERT INTO `district` VALUES ('855', '宁城', '150', 'n', 'nc', 'ningcheng', '', '县', '150429', '0476', '11');
INSERT INTO `district` VALUES ('856', '敖汉', '150', 'a', 'ah', 'aohan', '', '旗', '150430', '0476', '12');
INSERT INTO `district` VALUES ('857', '科尔沁', '151', 'k', 'keq', 'keerqin', '', '区', '150502', '0475', '1');
INSERT INTO `district` VALUES ('858', '科尔沁左翼中', '151', 'k', 'keqzyz', 'keerqinzuoyizhong', '', '旗', '150521', '0475', '2');
INSERT INTO `district` VALUES ('859', '科尔沁左翼后', '151', 'k', 'keqzyh', 'keerqinzuoyihou', '', '旗', '150522', '0475', '3');
INSERT INTO `district` VALUES ('860', '开鲁', '151', 'k', 'kl', 'kailu', '', '县', '150523', '0475', '4');
INSERT INTO `district` VALUES ('861', '库伦', '151', 'k', 'kl', 'kulun', '', '旗', '150524', '0475', '5');
INSERT INTO `district` VALUES ('862', '奈曼', '151', 'n', 'nm', 'naiman', '', '旗', '150525', '0475', '6');
INSERT INTO `district` VALUES ('863', '扎鲁特', '151', 'z', 'zlt', 'zhalute', '', '旗', '150526', '0475', '7');
INSERT INTO `district` VALUES ('864', '霍林郭勒', '151', 'h', 'hlgl', 'huolinguole', '', '市', '150581', '0475', '8');
INSERT INTO `district` VALUES ('865', '东胜', '152', 'd', 'ds', 'dongsheng', '', '区', '150602', '0477', '1');
INSERT INTO `district` VALUES ('866', '达拉特', '152', 'd', 'dlt', 'dalate', '', '旗', '150621', '0477', '2');
INSERT INTO `district` VALUES ('867', '准格尔', '152', 'z', 'zge', 'zhungeer', '', '旗', '150622', '0477', '3');
INSERT INTO `district` VALUES ('868', '鄂托克前', '152', 'e', 'etkq', 'etuokeqian', '', '旗', '150623', '0477', '4');
INSERT INTO `district` VALUES ('869', '鄂托克', '152', 'e', 'etk', 'etuoke', '', '旗', '150624', '0477', '5');
INSERT INTO `district` VALUES ('870', '杭锦', '152', 'h', 'hj', 'hangjin', '', '旗', '150625', '0477', '6');
INSERT INTO `district` VALUES ('871', '乌审', '152', 'w', 'ws', 'wushen', '', '旗', '150626', '0477', '7');
INSERT INTO `district` VALUES ('872', '伊金霍洛', '152', 'y', 'yjhl', 'yijinhuoluo', '', '旗', '150627', '0477', '8');
INSERT INTO `district` VALUES ('873', '海拉尔', '153', 'h', 'hle', 'hailaer', '', '区', '150702', '0470', '1');
INSERT INTO `district` VALUES ('874', '阿荣', '153', 'a', 'ar', 'arong', '', '旗', '150721', '0470', '2');
INSERT INTO `district` VALUES ('875', '莫力达瓦', '153', 'm', 'mldw', 'molidawa', '达斡尔族', '自治旗', '150722', '0470', '3');
INSERT INTO `district` VALUES ('876', '鄂伦春', '153', 'e', 'elc', 'elunchun', '', '自治旗', '150723', '0470', '4');
INSERT INTO `district` VALUES ('877', '鄂温克族', '153', 'e', 'ewkz', 'ewenkezu', '', '自治旗', '150724', '0470', '5');
INSERT INTO `district` VALUES ('878', '陈巴尔虎', '153', 'c', 'cbeh', 'chenbaerhu', '', '旗', '150725', '0470', '6');
INSERT INTO `district` VALUES ('879', '新巴尔虎左', '153', 'x', 'xbehz', 'xinbaerhuzuo', '', '旗', '150726', '0470', '7');
INSERT INTO `district` VALUES ('880', '新巴尔虎右', '153', 'x', 'xbehy', 'xinbaerhuyou', '', '旗', '150727', '0470', '8');
INSERT INTO `district` VALUES ('881', '满洲里', '153', 'm', 'mzl', 'manzhouli', '', '市', '150781', '0470', '9');
INSERT INTO `district` VALUES ('882', '牙克石', '153', 'y', 'yks', 'yakeshi', '', '市', '150782', '0470', '10');
INSERT INTO `district` VALUES ('883', '扎兰屯', '153', 'z', 'zlt', 'zhalantun', '', '市', '150783', '0470', '11');
INSERT INTO `district` VALUES ('884', '额尔古纳', '153', 'e', 'eegn', 'eerguna', '', '市', '150784', '0470', '12');
INSERT INTO `district` VALUES ('885', '根河', '153', 'g', 'gh', 'genhe', '', '市', '150785', '0470', '13');
INSERT INTO `district` VALUES ('886', '临河', '154', 'l', 'lh', 'linhe', '', '区', '150802', '0478', '1');
INSERT INTO `district` VALUES ('887', '五原', '154', 'w', 'wy', 'wuyuan', '', '县', '150821', '0478', '2');
INSERT INTO `district` VALUES ('888', '磴口', '154', 'd', 'dk', 'dengkou', '', '县', '150822', '0478', '3');
INSERT INTO `district` VALUES ('889', '乌拉特前', '154', 'w', 'wltq', 'wulateqian', '', '旗', '150823', '0478', '4');
INSERT INTO `district` VALUES ('890', '乌拉特中', '154', 'w', 'wltz', 'wulatezhong', '', '旗', '150824', '0478', '5');
INSERT INTO `district` VALUES ('891', '乌拉特后', '154', 'w', 'wlth', 'wulatehou', '', '旗', '150825', '0478', '6');
INSERT INTO `district` VALUES ('892', '杭锦后', '154', 'h', 'hjh', 'hangjinhou', '', '旗', '150826', '0478', '7');
INSERT INTO `district` VALUES ('893', '集宁', '155', 'j', 'jn', 'jining', '', '区', '150902', '0474', '1');
INSERT INTO `district` VALUES ('894', '卓资', '155', 'z', 'zz', 'zhuozi', '', '县', '150921', '0474', '2');
INSERT INTO `district` VALUES ('895', '化德', '155', 'h', 'hd', 'huade', '', '县', '150922', '0474', '3');
INSERT INTO `district` VALUES ('896', '商都', '155', 's', 'sd', 'shangdou', '', '县', '150923', '0474', '4');
INSERT INTO `district` VALUES ('897', '兴和', '155', 'x', 'xh', 'xinghe', '', '县', '150924', '0474', '5');
INSERT INTO `district` VALUES ('898', '凉城', '155', 'l', 'lc', 'liangcheng', '', '县', '150925', '0474', '6');
INSERT INTO `district` VALUES ('899', '察哈尔右翼前', '155', 'c', 'cheyyq', 'chahaeryouyiqian', '', '旗', '150926', '0474', '7');
INSERT INTO `district` VALUES ('900', '察哈尔右翼中', '155', 'c', 'cheyyz', 'chahaeryouyizhong', '', '旗', '150927', '0474', '8');
INSERT INTO `district` VALUES ('901', '察哈尔右翼后', '155', 'c', 'cheyyh', 'chahaeryouyihou', '', '旗', '150928', '0474', '9');
INSERT INTO `district` VALUES ('902', '四子王', '155', 's', 'szw', 'siziwang', '', '旗', '150929', '0474', '10');
INSERT INTO `district` VALUES ('903', '丰镇', '155', 'f', 'fz', 'fengzhen', '', '市', '150981', '0474', '11');
INSERT INTO `district` VALUES ('904', '乌兰浩特', '156', 'w', 'wlht', 'wulanhaote', '', '市', '152201', '0482', '1');
INSERT INTO `district` VALUES ('905', '阿尔山', '156', 'a', 'aes', 'aershan', '', '市', '152202', '0482', '2');
INSERT INTO `district` VALUES ('906', '科尔沁右翼前', '156', 'k', 'keqyyq', 'keerqinyouyiqian', '', '旗', '152221', '0482', '3');
INSERT INTO `district` VALUES ('907', '科尔沁右翼中', '156', 'k', 'keqyyz', 'keerqinyouyizhong', '', '旗', '152222', '0482', '4');
INSERT INTO `district` VALUES ('908', '扎赉特', '156', 'z', 'zlt', 'zhalaite', '', '旗', '152223', '0482', '5');
INSERT INTO `district` VALUES ('909', '突泉', '156', 't', 'tq', 'tuquan', '', '县', '152224', '0482', '6');
INSERT INTO `district` VALUES ('910', '二连浩特', '157', 'e', 'elht', 'erlianhaote', '', '市', '152501', '0479', '1');
INSERT INTO `district` VALUES ('911', '锡林浩特', '157', 'x', 'xlht', 'xilinhaote', '', '市', '152502', '0479', '2');
INSERT INTO `district` VALUES ('912', '阿巴嘎', '157', 'a', 'abg', 'abaga', '', '旗', '152522', '0479', '3');
INSERT INTO `district` VALUES ('913', '苏尼特左', '157', 's', 'sntz', 'sunitezuo', '', '旗', '152523', '0479', '4');
INSERT INTO `district` VALUES ('914', '苏尼特右', '157', 's', 'snty', 'suniteyou', '', '旗', '152524', '0479', '5');
INSERT INTO `district` VALUES ('915', '东乌珠穆沁', '157', 'd', 'dwzmq', 'dongwuzhumuqin', '', '旗', '152525', '0479', '6');
INSERT INTO `district` VALUES ('916', '西乌珠穆沁', '157', 'x', 'xwzmq', 'xiwuzhumuqin', '', '旗', '152526', '0479', '7');
INSERT INTO `district` VALUES ('917', '太仆寺', '157', 't', 'tps', 'taipusi', '', '旗', '152527', '0479', '8');
INSERT INTO `district` VALUES ('918', '镶黄', '157', 'x', 'xh', 'xianghuang', '', '旗', '152528', '0479', '9');
INSERT INTO `district` VALUES ('919', '正镶白', '157', 'z', 'zxb', 'zhengxiangbai', '', '旗', '152529', '0479', '10');
INSERT INTO `district` VALUES ('920', '正蓝', '157', 'z', 'zl', 'zhenglan', '', '旗', '152530', '0479', '11');
INSERT INTO `district` VALUES ('921', '多伦', '157', 'd', 'dl', 'duolun', '', '县', '152531', '0479', '12');
INSERT INTO `district` VALUES ('922', '阿拉善左', '158', 'a', 'alsz', 'alashanzuo', '', '旗', '152921', '0483', '1');
INSERT INTO `district` VALUES ('923', '阿拉善右', '158', 'a', 'alsy', 'alashanyou', '', '旗', '152922', '0483', '2');
INSERT INTO `district` VALUES ('924', '额济纳', '158', 'e', 'ejn', 'ejina', '', '旗', '152923', '0483', '3');
INSERT INTO `district` VALUES ('925', '和平', '159', 'h', 'hp', 'heping', '', '区', '210102', '024', '1');
INSERT INTO `district` VALUES ('926', '沈河', '159', 's', 'sh', 'shenhe', '', '区', '210103', '024', '2');
INSERT INTO `district` VALUES ('927', '大东', '159', 'd', 'dd', 'dadong', '', '区', '210104', '024', '3');
INSERT INTO `district` VALUES ('928', '皇姑', '159', 'h', 'hg', 'huanggu', '', '区', '210105', '024', '4');
INSERT INTO `district` VALUES ('929', '铁西', '159', 't', 'tx', 'tiexi', '', '区', '210106', '024', '5');
INSERT INTO `district` VALUES ('930', '苏家屯', '159', 's', 'sjt', 'sujiatun', '', '区', '210111', '024', '6');
INSERT INTO `district` VALUES ('931', '浑南', '159', 'h', 'hn', 'hunnan', '', '区', '210112', '024', '7');
INSERT INTO `district` VALUES ('932', '沈北新区', '159', 's', 'sbxq', 'shenbeixinqu', '', '', '210113', '024', '8');
INSERT INTO `district` VALUES ('933', '于洪', '159', 'y', 'yh', 'yuhong', '', '区', '210114', '024', '9');
INSERT INTO `district` VALUES ('934', '辽中', '159', 'l', 'lz', 'liaozhong', '', '县', '210122', '024', '10');
INSERT INTO `district` VALUES ('935', '康平', '159', 'k', 'kp', 'kangping', '', '县', '210123', '024', '11');
INSERT INTO `district` VALUES ('936', '法库', '159', 'f', 'fk', 'faku', '', '县', '210124', '024', '12');
INSERT INTO `district` VALUES ('937', '新民', '159', 'x', 'xm', 'xinmin', '', '市', '210181', '024', '13');
INSERT INTO `district` VALUES ('938', '中山', '160', 'z', 'zs', 'zhongshan', '', '区', '210202', '0411', '1');
INSERT INTO `district` VALUES ('939', '西岗', '160', 'x', 'xg', 'xigang', '', '区', '210203', '0411', '2');
INSERT INTO `district` VALUES ('940', '沙河口', '160', 's', 'shk', 'shahekou', '', '区', '210204', '0411', '3');
INSERT INTO `district` VALUES ('941', '甘井子', '160', 'g', 'gjz', 'ganjingzi', '', '区', '210211', '0411', '4');
INSERT INTO `district` VALUES ('942', '旅顺口', '160', 'l', 'lsk', 'lu:shunkou', '', '区', '210212', '0411', '5');
INSERT INTO `district` VALUES ('943', '金州', '160', 'j', 'jz', 'jinzhou', '', '区', '210213', '0411', '6');
INSERT INTO `district` VALUES ('944', '长海', '160', 'z', 'zh', 'zhanghai', '', '县', '210224', '0411', '7');
INSERT INTO `district` VALUES ('945', '瓦房店', '160', 'w', 'wfd', 'wafangdian', '', '市', '210281', '0411', '8');
INSERT INTO `district` VALUES ('946', '普兰店', '160', 'p', 'pld', 'pulandian', '', '市', '210282', '0411', '9');
INSERT INTO `district` VALUES ('947', '庄河', '160', 'z', 'zh', 'zhuanghe', '', '市', '210283', '0411', '10');
INSERT INTO `district` VALUES ('948', '铁东', '161', 't', 'td', 'tiedong', '', '区', '210302', '0412', '1');
INSERT INTO `district` VALUES ('949', '铁西', '161', 't', 'tx', 'tiexi', '', '区', '210303', '0412', '2');
INSERT INTO `district` VALUES ('950', '立山', '161', 'l', 'ls', 'lishan', '', '区', '210304', '0412', '3');
INSERT INTO `district` VALUES ('951', '千山', '161', 'q', 'qs', 'qianshan', '', '区', '210311', '0412', '4');
INSERT INTO `district` VALUES ('952', '台安', '161', 't', 'ta', 'taian', '', '县', '210321', '0412', '5');
INSERT INTO `district` VALUES ('953', '岫岩', '161', 'x', 'xy', 'xiuyan', '满族', '自治县', '210323', '0412', '6');
INSERT INTO `district` VALUES ('954', '海城', '161', 'h', 'hc', 'haicheng', '', '市', '210381', '0412', '7');
INSERT INTO `district` VALUES ('955', '新抚', '162', 'x', 'xf', 'xinfu', '', '区', '210402', '0413', '1');
INSERT INTO `district` VALUES ('956', '东洲', '162', 'd', 'dz', 'dongzhou', '', '区', '210403', '0413', '2');
INSERT INTO `district` VALUES ('957', '望花', '162', 'w', 'wh', 'wanghua', '', '区', '210404', '0413', '3');
INSERT INTO `district` VALUES ('958', '顺城', '162', 's', 'sc', 'shuncheng', '', '区', '210411', '0413', '4');
INSERT INTO `district` VALUES ('959', '抚顺', '162', 'f', 'fs', 'fushun', '', '县', '210421', '0413', '5');
INSERT INTO `district` VALUES ('960', '新宾', '162', 'x', 'xb', 'xinbin', '满族', '自治县', '210422', '0413', '6');
INSERT INTO `district` VALUES ('961', '清原', '162', 'q', 'qy', 'qingyuan', '满族', '自治县', '210423', '0413', '7');
INSERT INTO `district` VALUES ('962', '平山', '163', 'p', 'ps', 'pingshan', '', '区', '210502', '0414', '1');
INSERT INTO `district` VALUES ('963', '溪湖', '163', 'x', 'xh', 'xihu', '', '区', '210503', '0414', '2');
INSERT INTO `district` VALUES ('964', '明山', '163', 'm', 'ms', 'mingshan', '', '区', '210504', '0414', '3');
INSERT INTO `district` VALUES ('965', '南芬', '163', 'n', 'nf', 'nanfen', '', '区', '210505', '0414', '4');
INSERT INTO `district` VALUES ('966', '本溪', '163', 'b', 'bx', 'benxi', '满族', '自治县', '210521', '0414', '5');
INSERT INTO `district` VALUES ('967', '桓仁', '163', 'h', 'hr', 'huanren', '满族', '自治县', '210522', '0414', '6');
INSERT INTO `district` VALUES ('968', '元宝', '164', 'y', 'yb', 'yuanbao', '', '区', '210602', '0415', '1');
INSERT INTO `district` VALUES ('969', '振兴', '164', 'z', 'zx', 'zhenxing', '', '区', '210603', '0415', '2');
INSERT INTO `district` VALUES ('970', '振安', '164', 'z', 'za', 'zhenan', '', '区', '210604', '0415', '3');
INSERT INTO `district` VALUES ('971', '宽甸', '164', 'k', 'kd', 'kuandian', '满族', '自治县', '210624', '0415', '4');
INSERT INTO `district` VALUES ('972', '东港', '164', 'd', 'dg', 'donggang', '', '市', '210681', '0415', '5');
INSERT INTO `district` VALUES ('973', '凤城', '164', 'f', 'fc', 'fengcheng', '', '市', '210682', '0415', '6');
INSERT INTO `district` VALUES ('974', '古塔', '165', 'g', 'gt', 'guta', '', '区', '210702', '0416', '1');
INSERT INTO `district` VALUES ('975', '凌河', '165', 'l', 'lh', 'linghe', '', '区', '210703', '0416', '2');
INSERT INTO `district` VALUES ('976', '太和', '165', 't', 'th', 'taihe', '', '区', '210711', '0416', '3');
INSERT INTO `district` VALUES ('977', '黑山', '165', 'h', 'hs', 'heishan', '', '县', '210726', '0416', '4');
INSERT INTO `district` VALUES ('978', '义县', '165', 'y', 'yx', 'yixian', '', '', '210727', '0416', '5');
INSERT INTO `district` VALUES ('979', '凌海', '165', 'l', 'lh', 'linghai', '', '市', '210781', '0416', '6');
INSERT INTO `district` VALUES ('980', '北镇', '165', 'b', 'bz', 'beizhen', '', '市', '210782', '0416', '7');
INSERT INTO `district` VALUES ('981', '站前', '166', 'z', 'zq', 'zhanqian', '', '区', '210802', '0417', '1');
INSERT INTO `district` VALUES ('982', '西市', '166', 'x', 'xs', 'xishi', '', '区', '210803', '0417', '2');
INSERT INTO `district` VALUES ('983', '鲅鱼圈', '166', 'b', 'byq', 'bayuquan', '', '区', '210804', '0417', '3');
INSERT INTO `district` VALUES ('984', '老边', '166', 'l', 'lb', 'laobian', '', '区', '210811', '0417', '4');
INSERT INTO `district` VALUES ('985', '盖州', '166', 'g', 'gz', 'gaizhou', '', '市', '210881', '0417', '5');
INSERT INTO `district` VALUES ('986', '大石桥', '166', 'd', 'dsq', 'dashiqiao', '', '市', '210882', '0417', '6');
INSERT INTO `district` VALUES ('987', '海州', '167', 'h', 'hz', 'haizhou', '', '区', '210902', '0418', '1');
INSERT INTO `district` VALUES ('988', '新邱', '167', 'x', 'xq', 'xinqiu', '', '区', '210903', '0418', '2');
INSERT INTO `district` VALUES ('989', '太平', '167', 't', 'tp', 'taiping', '', '区', '210904', '0418', '3');
INSERT INTO `district` VALUES ('990', '清河门', '167', 'q', 'qhm', 'qinghemen', '', '区', '210905', '0418', '4');
INSERT INTO `district` VALUES ('991', '细河', '167', 'x', 'xh', 'xihe', '', '区', '210911', '0418', '5');
INSERT INTO `district` VALUES ('992', '阜新', '167', 'f', 'fx', 'fuxin', '蒙古族', '自治县', '210921', '0418', '6');
INSERT INTO `district` VALUES ('993', '彰武', '167', 'z', 'zw', 'zhangwu', '', '县', '210922', '0418', '7');
INSERT INTO `district` VALUES ('994', '白塔', '168', 'b', 'bt', 'baita', '', '区', '211002', '0419', '1');
INSERT INTO `district` VALUES ('995', '文圣', '168', 'w', 'ws', 'wensheng', '', '区', '211003', '0419', '2');
INSERT INTO `district` VALUES ('996', '宏伟', '168', 'h', 'hw', 'hongwei', '', '区', '211004', '0419', '3');
INSERT INTO `district` VALUES ('997', '弓长岭', '168', 'g', 'gzl', 'gongzhangling', '', '区', '211005', '0419', '4');
INSERT INTO `district` VALUES ('998', '辽阳', '168', 'l', 'ly', 'liaoyang', '', '县', '211021', '0419', '5');
INSERT INTO `district` VALUES ('999', '灯塔', '168', 'd', 'dt', 'dengta', '', '市', '211081', '0419', '6');
INSERT INTO `district` VALUES ('1000', '太子河', '168', 't', 'tzh', 'taizihe', '', '区', '211110', '0419', '7');
INSERT INTO `district` VALUES ('1001', '双台子', '169', 's', 'stz', 'shuangtaizi', '', '区', '211103', '0427', '1');
INSERT INTO `district` VALUES ('1002', '兴隆台', '169', 'x', 'xlt', 'xinglongtai', '', '区', '211103', '0427', '2');
INSERT INTO `district` VALUES ('1003', '大洼', '169', 'd', 'dw', 'dawa', '', '县', '211121', '0427', '3');
INSERT INTO `district` VALUES ('1004', '盘山', '169', 'p', 'ps', 'panshan', '', '县', '211122', '0427', '4');
INSERT INTO `district` VALUES ('1005', '银州', '170', 'y', 'yz', 'yinzhou', '', '区', '211202', '0410', '1');
INSERT INTO `district` VALUES ('1006', '清河', '170', 'q', 'qh', 'qinghe', '', '区', '211204', '0410', '2');
INSERT INTO `district` VALUES ('1007', '铁岭', '170', 't', 'tl', 'tieling', '', '县', '211221', '0410', '3');
INSERT INTO `district` VALUES ('1008', '西丰', '170', 'x', 'xf', 'xifeng', '', '县', '211223', '0410', '4');
INSERT INTO `district` VALUES ('1009', '昌图', '170', 'c', 'ct', 'changtu', '', '县', '211224', '0410', '5');
INSERT INTO `district` VALUES ('1010', '调兵山', '170', 'd', 'dbs', 'diaobingshan', '', '市', '211281', '0410', '6');
INSERT INTO `district` VALUES ('1011', '开原', '170', 'k', 'ky', 'kaiyuan', '', '市', '211282', '0410', '7');
INSERT INTO `district` VALUES ('1012', '双塔', '171', 's', 'st', 'shuangta', '', '区', '211302', '0421', '1');
INSERT INTO `district` VALUES ('1013', '龙城', '171', 'l', 'lc', 'longcheng', '', '区', '211303', '0421', '2');
INSERT INTO `district` VALUES ('1014', '朝阳', '171', 'c', 'cy', 'chaoyang', '', '县', '211321', '0421', '3');
INSERT INTO `district` VALUES ('1015', '建平', '171', 'j', 'jp', 'jianping', '', '县', '211322', '0421', '4');
INSERT INTO `district` VALUES ('1016', '喀喇沁左翼', '171', 'k', 'klqzy', 'kalaqinzuoyi', '蒙古族', '自治县', '211324', '0421', '5');
INSERT INTO `district` VALUES ('1017', '北票', '171', 'b', 'bp', 'beipiao', '', '市', '211381', '0421', '6');
INSERT INTO `district` VALUES ('1018', '凌源', '171', 'l', 'ly', 'lingyuan', '', '市', '211382', '0421', '7');
INSERT INTO `district` VALUES ('1019', '连山', '172', 'l', 'ls', 'lianshan', '', '区', '211402', '0429', '1');
INSERT INTO `district` VALUES ('1020', '龙港', '172', 'l', 'lg', 'longgang', '', '区', '211403', '0429', '2');
INSERT INTO `district` VALUES ('1021', '南票', '172', 'n', 'np', 'nanpiao', '', '区', '211404', '0429', '3');
INSERT INTO `district` VALUES ('1022', '绥中', '172', 's', 'sz', 'suizhong', '', '县', '211421', '0429', '4');
INSERT INTO `district` VALUES ('1023', '建昌', '172', 'j', 'jc', 'jianchang', '', '县', '211422', '0429', '5');
INSERT INTO `district` VALUES ('1024', '兴城', '172', 'x', 'xc', 'xingcheng', '', '市', '211481', '0429', '6');
INSERT INTO `district` VALUES ('1025', '南关', '173', 'n', 'ng', 'nanguan', '', '区', '220102', '0431', '1');
INSERT INTO `district` VALUES ('1026', '宽城', '173', 'k', 'kc', 'kuancheng', '', '区', '220103', '0431', '2');
INSERT INTO `district` VALUES ('1027', '朝阳', '173', 'c', 'cy', 'chaoyang', '', '区', '220104', '0431', '3');
INSERT INTO `district` VALUES ('1028', '二道', '173', 'e', 'ed', 'erdao', '', '区', '220105', '0431', '4');
INSERT INTO `district` VALUES ('1029', '绿园', '173', 'l', 'ly', 'lu:yuan', '', '区', '220106', '0431', '5');
INSERT INTO `district` VALUES ('1030', '双阳', '173', 's', 'sy', 'shuangyang', '', '区', '220112', '0431', '6');
INSERT INTO `district` VALUES ('1031', '农安', '173', 'n', 'na', 'nongan', '', '县', '220122', '0431', '8');
INSERT INTO `district` VALUES ('1032', '九台', '173', 'j', 'jt', 'jiutai', '', '区', '220113', '0431', '7');
INSERT INTO `district` VALUES ('1033', '榆树', '173', 'y', 'ys', 'yushu', '', '市', '220182', '0431', '9');
INSERT INTO `district` VALUES ('1034', '德惠', '173', 'd', 'dh', 'dehui', '', '市', '220183', '0431', '10');
INSERT INTO `district` VALUES ('1035', '昌邑', '174', 'c', 'cy', 'changyi', '', '区', '220202', '0432', '1');
INSERT INTO `district` VALUES ('1036', '龙潭', '174', 'l', 'lt', 'longtan', '', '区', '220203', '0432', '2');
INSERT INTO `district` VALUES ('1037', '船营', '174', 'c', 'cy', 'chuanying', '', '区', '220204', '0432', '3');
INSERT INTO `district` VALUES ('1038', '丰满', '174', 'f', 'fm', 'fengman', '', '区', '220211', '0432', '4');
INSERT INTO `district` VALUES ('1039', '永吉', '174', 'y', 'yj', 'yongji', '', '县', '220221', '0432', '5');
INSERT INTO `district` VALUES ('1040', '桦甸', '174', 'h', 'hd', 'huadian', '', '市', '220281', '0432', '6');
INSERT INTO `district` VALUES ('1041', '蛟河', '174', 'j', 'jh', 'jiaohe', '', '市', '220282', '0432', '7');
INSERT INTO `district` VALUES ('1042', '舒兰', '174', 's', 'sl', 'shulan', '', '市', '220283', '0432', '8');
INSERT INTO `district` VALUES ('1043', '磐石', '174', 'p', 'ps', 'panshi', '', '市', '220284', '0432', '9');
INSERT INTO `district` VALUES ('1044', '铁西', '175', 't', 'tx', 'tiexi', '', '区', '220302', '0434', '1');
INSERT INTO `district` VALUES ('1045', '铁东', '175', 't', 'td', 'tiedong', '', '区', '220303', '0434', '2');
INSERT INTO `district` VALUES ('1046', '梨树', '175', 'l', 'ls', 'lishu', '', '县', '220322', '0434', '3');
INSERT INTO `district` VALUES ('1047', '伊通', '175', 'y', 'yt', 'yitong', '满族', '自治县', '220323', '0434', '4');
INSERT INTO `district` VALUES ('1048', '公主岭', '175', 'g', 'gzl', 'gongzhuling', '', '市', '220381', '0434', '5');
INSERT INTO `district` VALUES ('1049', '双辽', '175', 's', 'sl', 'shuangliao', '', '市', '220382', '0434', '6');
INSERT INTO `district` VALUES ('1050', '龙山', '176', 'l', 'ls', 'longshan', '', '区', '220402', '0437', '1');
INSERT INTO `district` VALUES ('1051', '西安', '176', 'x', 'xa', 'xian', '', '区', '220403', '0437', '2');
INSERT INTO `district` VALUES ('1052', '东丰', '176', 'd', 'df', 'dongfeng', '', '县', '220421', '0437', '3');
INSERT INTO `district` VALUES ('1053', '东辽', '176', 'd', 'dl', 'dongliao', '', '县', '220422', '0437', '4');
INSERT INTO `district` VALUES ('1054', '东昌', '177', 'd', 'dc', 'dongchang', '', '区', '220502', '0435', '1');
INSERT INTO `district` VALUES ('1055', '二道江', '177', 'e', 'edj', 'erdaojiang', '', '区', '220503', '0435', '2');
INSERT INTO `district` VALUES ('1056', '通化', '177', 't', 'th', 'tonghua', '', '县', '220521', '0435', '3');
INSERT INTO `district` VALUES ('1057', '辉南', '177', 'h', 'hn', 'huinan', '', '县', '220523', '0435', '4');
INSERT INTO `district` VALUES ('1058', '柳河', '177', 'l', 'lh', 'liuhe', '', '县', '220524', '0435', '5');
INSERT INTO `district` VALUES ('1059', '梅河口', '177', 'm', 'mhk', 'meihekou', '', '市', '220581', '0435', '6');
INSERT INTO `district` VALUES ('1060', '集安', '177', 'j', 'ja', 'jian', '', '市', '220582', '0435', '7');
INSERT INTO `district` VALUES ('1061', '浑江', '178', 'h', 'hj', 'hunjiang', '', '区', '220602', '0439', '1');
INSERT INTO `district` VALUES ('1062', '江源', '178', 'j', 'jy', 'jiangyuan', '', '区', '220604', '0439', '2');
INSERT INTO `district` VALUES ('1063', '抚松', '178', 'f', 'fs', 'fusong', '', '县', '220621', '0439', '3');
INSERT INTO `district` VALUES ('1064', '靖宇', '178', 'j', 'jy', 'jingyu', '', '县', '220622', '0439', '4');
INSERT INTO `district` VALUES ('1065', '长白', '178', 'z', 'zb', 'zhangbai', '朝鲜族', '自治县', '220623', '0439', '5');
INSERT INTO `district` VALUES ('1066', '临江', '178', 'l', 'lj', 'linjiang', '', '市', '220681', '0439', '6');
INSERT INTO `district` VALUES ('1067', '宁江', '179', 'n', 'nj', 'ningjiang', '', '区', '220702', '0438', '1');
INSERT INTO `district` VALUES ('1068', '前郭尔罗斯', '179', 'q', 'qgels', 'qianguoerluosi', '蒙古族', '自治县', '220721', '0438', '2');
INSERT INTO `district` VALUES ('1069', '长岭', '179', 'z', 'zl', 'zhangling', '', '县', '220722', '0438', '3');
INSERT INTO `district` VALUES ('1070', '乾安', '179', 'q', 'qa', 'qianan', '', '县', '220723', '0438', '4');
INSERT INTO `district` VALUES ('1071', '扶余', '179', 'f', 'fy', 'fuyu', '', '市', '220724', '0438', '5');
INSERT INTO `district` VALUES ('1072', '洮北', '180', 't', 'tb', 'taobei', '', '区', '220802', '0436', '1');
INSERT INTO `district` VALUES ('1073', '镇赉', '180', 'z', 'zl', 'zhenlai', '', '县', '220821', '0436', '2');
INSERT INTO `district` VALUES ('1074', '洮南', '180', 't', 'tn', 'taonan', '', '市', '220881', '0436', '4');
INSERT INTO `district` VALUES ('1075', '大安', '180', 'd', 'da', 'daan', '', '市', '220882', '0436', '5');
INSERT INTO `district` VALUES ('1076', '通榆', '180', 't', 'ty', 'tongyu', '', '县', '220822', '0436', '3');
INSERT INTO `district` VALUES ('1077', '延吉', '181', 'y', 'yj', 'yanji', '', '市', '222401', '0433', '1');
INSERT INTO `district` VALUES ('1078', '图们', '181', 't', 'tm', 'tumen', '', '市', '222402', '0433', '2');
INSERT INTO `district` VALUES ('1079', '敦化', '181', 'd', 'dh', 'dunhua', '', '市', '222403', '0433', '3');
INSERT INTO `district` VALUES ('1080', '珲春', '181', 'h', 'hc', 'hunchun', '', '市', '222404', '0433', '4');
INSERT INTO `district` VALUES ('1081', '龙井', '181', 'l', 'lj', 'longjing', '', '市', '222405', '0433', '5');
INSERT INTO `district` VALUES ('1082', '和龙', '181', 'h', 'hl', 'helong', '', '市', '222406', '0433', '6');
INSERT INTO `district` VALUES ('1083', '汪清', '181', 'w', 'wq', 'wangqing', '', '县', '222424', '0433', '7');
INSERT INTO `district` VALUES ('1084', '安图', '181', 'a', 'at', 'antu', '', '县', '222426', '0433', '8');
INSERT INTO `district` VALUES ('1085', '道里', '182', 'd', 'dl', 'daoli', '', '区', '230102', '0451', '1');
INSERT INTO `district` VALUES ('1086', '南岗', '182', 'n', 'ng', 'nangang', '', '区', '230103', '0451', '2');
INSERT INTO `district` VALUES ('1087', '道外', '182', 'd', 'dw', 'daowai', '', '区', '230104', '0451', '3');
INSERT INTO `district` VALUES ('1088', '平房', '182', 'p', 'pf', 'pingfang', '', '区', '230108', '0451', '4');
INSERT INTO `district` VALUES ('1089', '松北', '182', 's', 'sb', 'songbei', '', '区', '230109', '0451', '5');
INSERT INTO `district` VALUES ('1090', '香坊', '182', 'x', 'xf', 'xiangfang', '', '区', '230110', '0451', '6');
INSERT INTO `district` VALUES ('1091', '呼兰', '182', 'h', 'hl', 'hulan', '', '区', '230111', '0451', '7');
INSERT INTO `district` VALUES ('1092', '阿城', '182', 'a', 'ac', 'acheng', '', '区', '230112', '0451', '8');
INSERT INTO `district` VALUES ('1093', '依兰', '182', 'y', 'yl', 'yilan', '', '县', '230123', '0451', '9');
INSERT INTO `district` VALUES ('1094', '方正', '182', 'f', 'fz', 'fangzheng', '', '县', '230124', '0451', '10');
INSERT INTO `district` VALUES ('1095', '宾县', '182', 'b', 'bx', 'binxian', '', '', '230125', '0451', '11');
INSERT INTO `district` VALUES ('1096', '巴彦', '182', 'b', 'by', 'bayan', '', '县', '230126', '0451', '12');
INSERT INTO `district` VALUES ('1097', '木兰', '182', 'm', 'ml', 'mulan', '', '县', '230127', '0451', '13');
INSERT INTO `district` VALUES ('1098', '通河', '182', 't', 'th', 'tonghe', '', '县', '230128', '0451', '14');
INSERT INTO `district` VALUES ('1099', '延寿', '182', 'y', 'ys', 'yanshou', '', '县', '230129', '0451', '15');
INSERT INTO `district` VALUES ('1100', '双城', '182', 's', 'sc', 'shuangcheng', '', '区', '230182', '0451', '16');
INSERT INTO `district` VALUES ('1101', '尚志', '182', 's', 'sz', 'shangzhi', '', '市', '230183', '0451', '17');
INSERT INTO `district` VALUES ('1102', '五常', '182', 'w', 'wc', 'wuchang', '', '市', '230184', '0451', '18');
INSERT INTO `district` VALUES ('1103', '龙沙', '183', 'l', 'ls', 'longsha', '', '区', '230202', '0452', '1');
INSERT INTO `district` VALUES ('1104', '建华', '183', 'j', 'jh', 'jianhua', '', '区', '230203', '0452', '2');
INSERT INTO `district` VALUES ('1105', '铁锋', '183', 't', 'tf', 'tiefeng', '', '区', '230204', '0452', '3');
INSERT INTO `district` VALUES ('1106', '昂昂溪', '183', 'a', 'aax', 'angangxi', '', '区', '230205', '0452', '4');
INSERT INTO `district` VALUES ('1107', '富拉尔基', '183', 'f', 'flej', 'fulaerji', '', '区', '230206', '0452', '5');
INSERT INTO `district` VALUES ('1108', '碾子山', '183', 'n', 'nzs', 'nianzishan', '', '区', '230207', '0452', '6');
INSERT INTO `district` VALUES ('1109', '梅里斯', '183', 'm', 'mls', 'meilisi', '达斡尔族', '区', '230208', '0452', '7');
INSERT INTO `district` VALUES ('1110', '龙江', '183', 'l', 'lj', 'longjiang', '', '县', '230221', '0452', '8');
INSERT INTO `district` VALUES ('1111', '依安', '183', 'y', 'ya', 'yian', '', '县', '230223', '0452', '9');
INSERT INTO `district` VALUES ('1112', '泰来', '183', 't', 'tl', 'tailai', '', '县', '230224', '0452', '10');
INSERT INTO `district` VALUES ('1113', '甘南', '183', 'g', 'gn', 'gannan', '', '县', '230225', '0452', '11');
INSERT INTO `district` VALUES ('1114', '富裕', '183', 'f', 'fy', 'fuyu', '', '县', '230227', '0452', '12');
INSERT INTO `district` VALUES ('1115', '克山', '183', 'k', 'ks', 'keshan', '', '县', '230229', '0452', '13');
INSERT INTO `district` VALUES ('1116', '克东', '183', 'k', 'kd', 'kedong', '', '县', '230230', '0452', '14');
INSERT INTO `district` VALUES ('1117', '拜泉', '183', 'b', 'bq', 'baiquan', '', '县', '230231', '0452', '15');
INSERT INTO `district` VALUES ('1118', '讷河', '183', 'n', 'nh', 'nehe', '', '市', '230281', '0452', '16');
INSERT INTO `district` VALUES ('1119', '鸡冠', '184', 'j', 'jg', 'jiguan', '', '区', '230302', '0467', '1');
INSERT INTO `district` VALUES ('1120', '恒山', '184', 'h', 'hs', 'hengshan', '', '区', '230303', '0467', '2');
INSERT INTO `district` VALUES ('1121', '滴道', '184', 'd', 'dd', 'didao', '', '区', '230304', '0467', '3');
INSERT INTO `district` VALUES ('1122', '梨树', '184', 'l', 'ls', 'lishu', '', '区', '230305', '0467', '4');
INSERT INTO `district` VALUES ('1123', '城子河', '184', 'c', 'czh', 'chengzihe', '', '区', '230306', '0467', '5');
INSERT INTO `district` VALUES ('1124', '麻山', '184', 'm', 'ms', 'mashan', '', '区', '230307', '0467', '6');
INSERT INTO `district` VALUES ('1125', '鸡东', '184', 'j', 'jd', 'jidong', '', '县', '230321', '0467', '7');
INSERT INTO `district` VALUES ('1126', '虎林', '184', 'h', 'hl', 'hulin', '', '市', '230381', '0467', '8');
INSERT INTO `district` VALUES ('1127', '密山', '184', 'm', 'ms', 'mishan', '', '市', '230382', '0467', '9');
INSERT INTO `district` VALUES ('1128', '向阳', '185', 'x', 'xy', 'xiangyang', '', '区', '230402', '0468', '1');
INSERT INTO `district` VALUES ('1129', '工农', '185', 'g', 'gn', 'gongnong', '', '区', '230403', '0468', '2');
INSERT INTO `district` VALUES ('1130', '南山', '185', 'n', 'ns', 'nanshan', '', '区', '230404', '0468', '3');
INSERT INTO `district` VALUES ('1131', '兴安', '185', 'x', 'xa', 'xingan', '', '区', '230405', '0468', '4');
INSERT INTO `district` VALUES ('1132', '东山', '185', 'd', 'ds', 'dongshan', '', '区', '230406', '0468', '5');
INSERT INTO `district` VALUES ('1133', '兴山', '185', 'x', 'xs', 'xingshan', '', '区', '230407', '0468', '6');
INSERT INTO `district` VALUES ('1134', '萝北', '185', 'l', 'lb', 'luobei', '', '县', '230421', '0468', '7');
INSERT INTO `district` VALUES ('1135', '绥滨', '185', 's', 'sb', 'suibin', '', '县', '230422', '0468', '8');
INSERT INTO `district` VALUES ('1136', '尖山', '186', 'j', 'js', 'jianshan', '', '区', '230502', '0469', '1');
INSERT INTO `district` VALUES ('1137', '岭东', '186', 'l', 'ld', 'lingdong', '', '区', '230503', '0469', '2');
INSERT INTO `district` VALUES ('1138', '四方台', '186', 's', 'sft', 'sifangtai', '', '区', '230505', '0469', '3');
INSERT INTO `district` VALUES ('1139', '宝山', '186', 'b', 'bs', 'baoshan', '', '区', '230506', '0469', '4');
INSERT INTO `district` VALUES ('1140', '集贤', '186', 'j', 'jx', 'jixian', '', '县', '230521', '0469', '5');
INSERT INTO `district` VALUES ('1141', '友谊', '186', 'y', 'yy', 'youyi', '', '县', '230522', '0469', '6');
INSERT INTO `district` VALUES ('1142', '宝清', '186', 'b', 'bq', 'baoqing', '', '县', '230523', '0469', '7');
INSERT INTO `district` VALUES ('1143', '饶河', '186', 'r', 'rh', 'raohe', '', '县', '230524', '0469', '8');
INSERT INTO `district` VALUES ('1144', '萨尔图', '187', 's', 'set', 'saertu', '', '区', '230602', '0459', '1');
INSERT INTO `district` VALUES ('1145', '龙凤', '187', 'l', 'lf', 'longfeng', '', '区', '230603', '0459', '2');
INSERT INTO `district` VALUES ('1146', '让胡路', '187', 'r', 'rhl', 'ranghulu', '', '区', '230604', '0459', '3');
INSERT INTO `district` VALUES ('1147', '红岗', '187', 'h', 'hg', 'honggang', '', '区', '230605', '0459', '4');
INSERT INTO `district` VALUES ('1148', '大同', '187', 'd', 'dt', 'datong', '', '区', '230606', '0459', '5');
INSERT INTO `district` VALUES ('1149', '肇州', '187', 'z', 'zz', 'zhaozhou', '', '县', '230621', '0459', '6');
INSERT INTO `district` VALUES ('1150', '肇源', '187', 'z', 'zy', 'zhaoyuan', '', '县', '230622', '0459', '7');
INSERT INTO `district` VALUES ('1151', '林甸', '187', 'l', 'ld', 'lindian', '', '县', '230623', '0459', '8');
INSERT INTO `district` VALUES ('1152', '杜尔伯特', '187', 'd', 'debt', 'duerbote', '蒙古族', '自治县', '230624', '0459', '9');
INSERT INTO `district` VALUES ('1153', '伊春', '188', 'y', 'yc', 'yichun', '', '区', '230702', '0458', '1');
INSERT INTO `district` VALUES ('1154', '南岔', '188', 'n', 'nc', 'nancha', '', '区', '230703', '0458', '2');
INSERT INTO `district` VALUES ('1155', '友好', '188', 'y', 'yh', 'youhao', '', '区', '230704', '0458', '3');
INSERT INTO `district` VALUES ('1156', '西林', '188', 'x', 'xl', 'xilin', '', '区', '230705', '0458', '4');
INSERT INTO `district` VALUES ('1157', '翠峦', '188', 'c', 'cl', 'cuiluan', '', '区', '230706', '0458', '5');
INSERT INTO `district` VALUES ('1158', '新青', '188', 'x', 'xq', 'xinqing', '', '区', '230707', '0458', '6');
INSERT INTO `district` VALUES ('1159', '美溪', '188', 'm', 'mx', 'meixi', '', '区', '230708', '0458', '7');
INSERT INTO `district` VALUES ('1160', '金山屯', '188', 'j', 'jst', 'jinshantun', '', '区', '230709', '0458', '8');
INSERT INTO `district` VALUES ('1161', '五营', '188', 'w', 'wy', 'wuying', '', '区', '230710', '0458', '9');
INSERT INTO `district` VALUES ('1162', '乌马河', '188', 'w', 'wmh', 'wumahe', '', '区', '230711', '0458', '10');
INSERT INTO `district` VALUES ('1163', '汤旺河', '188', 't', 'twh', 'tangwanghe', '', '区', '230712', '0458', '11');
INSERT INTO `district` VALUES ('1164', '带岭', '188', 'd', 'dl', 'dailing', '', '区', '230713', '0458', '12');
INSERT INTO `district` VALUES ('1165', '乌伊岭', '188', 'w', 'wyl', 'wuyiling', '', '区', '230714', '0458', '13');
INSERT INTO `district` VALUES ('1166', '红星', '188', 'h', 'hx', 'hongxing', '', '区', '230715', '0458', '14');
INSERT INTO `district` VALUES ('1167', '上甘岭', '188', 's', 'sgl', 'shangganling', '', '区', '230716', '0458', '15');
INSERT INTO `district` VALUES ('1168', '嘉荫', '188', 'j', 'jy', 'jiayin', '', '县', '230722', '0458', '16');
INSERT INTO `district` VALUES ('1169', '铁力', '188', 't', 'tl', 'tieli', '', '市', '230781', '0458', '17');
INSERT INTO `district` VALUES ('1170', '向阳', '189', 'x', 'xy', 'xiangyang', '', '区', '230803', '0454', '1');
INSERT INTO `district` VALUES ('1171', '前进', '189', 'q', 'qj', 'qianjin', '', '区', '230804', '0454', '2');
INSERT INTO `district` VALUES ('1172', '东风', '189', 'd', 'df', 'dongfeng', '', '区', '230805', '0454', '3');
INSERT INTO `district` VALUES ('1173', '郊区', '189', 'j', 'jq', 'jiaoqu', '', '', '230811', '0454', '4');
INSERT INTO `district` VALUES ('1174', '桦南', '189', 'h', 'hn', 'huanan', '', '县', '230822', '0454', '5');
INSERT INTO `district` VALUES ('1175', '桦川', '189', 'h', 'hc', 'huachuan', '', '县', '230826', '0454', '6');
INSERT INTO `district` VALUES ('1176', '汤原', '189', 't', 'ty', 'tangyuan', '', '县', '230828', '0454', '7');
INSERT INTO `district` VALUES ('1177', '抚远', '189', 'f', 'fy', 'fuyuan', '', '县', '230833', '0454', '8');
INSERT INTO `district` VALUES ('1178', '同江', '189', 't', 'tj', 'tongjiang', '', '市', '230881', '0454', '9');
INSERT INTO `district` VALUES ('1179', '富锦', '189', 'f', 'fj', 'fujin', '', '市', '230882', '0454', '10');
INSERT INTO `district` VALUES ('1180', '新兴', '190', 'x', 'xx', 'xinxing', '', '区', '230902', '0464', '1');
INSERT INTO `district` VALUES ('1181', '桃山', '190', 't', 'ts', 'taoshan', '', '区', '230903', '0464', '2');
INSERT INTO `district` VALUES ('1182', '茄子河', '190', 'q', 'qzh', 'qiezihe', '', '区', '230904', '0464', '3');
INSERT INTO `district` VALUES ('1183', '勃利', '190', 'b', 'bl', 'boli', '', '县', '230921', '0464', '4');
INSERT INTO `district` VALUES ('1184', '东安', '191', 'd', 'da', 'dongan', '', '区', '231002', '0453', '1');
INSERT INTO `district` VALUES ('1185', '阳明', '191', 'y', 'ym', 'yangming', '', '区', '231003', '0453', '2');
INSERT INTO `district` VALUES ('1186', '爱民', '191', 'a', 'am', 'aimin', '', '区', '231004', '0453', '3');
INSERT INTO `district` VALUES ('1187', '西安', '191', 'x', 'xa', 'xian', '', '区', '231005', '0453', '4');
INSERT INTO `district` VALUES ('1188', '东宁', '191', 'd', 'dn', 'dongning', '', '县', '231024', '0453', '5');
INSERT INTO `district` VALUES ('1189', '林口', '191', 'l', 'lk', 'linkou', '', '县', '231025', '0453', '6');
INSERT INTO `district` VALUES ('1190', '绥芬河', '191', 's', 'sfh', 'suifenhe', '', '市', '231081', '0453', '7');
INSERT INTO `district` VALUES ('1191', '海林', '191', 'h', 'hl', 'hailin', '', '市', '231083', '0453', '8');
INSERT INTO `district` VALUES ('1192', '宁安', '191', 'n', 'na', 'ningan', '', '市', '231084', '0453', '9');
INSERT INTO `district` VALUES ('1193', '穆棱', '191', 'm', 'ml', 'muleng', '', '市', '231085', '0453', '10');
INSERT INTO `district` VALUES ('1194', '爱辉', '192', 'a', 'ah', 'aihui', '', '区', '231102', '0456', '1');
INSERT INTO `district` VALUES ('1195', '嫩江', '192', 'n', 'nj', 'nenjiang', '', '县', '231121', '0456', '2');
INSERT INTO `district` VALUES ('1196', '逊克', '192', 'x', 'xk', 'xunke', '', '县', '231123', '0456', '3');
INSERT INTO `district` VALUES ('1197', '孙吴', '192', 's', 'sw', 'sunwu', '', '县', '231124', '0456', '4');
INSERT INTO `district` VALUES ('1198', '北安', '192', 'b', 'ba', 'beian', '', '市', '231181', '0456', '5');
INSERT INTO `district` VALUES ('1199', '五大连池', '192', 'w', 'wdlc', 'wudalianchi', '', '市', '231182', '0456', '6');
INSERT INTO `district` VALUES ('1200', '北林', '193', 'b', 'bl', 'beilin', '', '区', '231202', '0455', '1');
INSERT INTO `district` VALUES ('1201', '望奎', '193', 'w', 'wk', 'wangkui', '', '县', '231221', '0455', '2');
INSERT INTO `district` VALUES ('1202', '兰西', '193', 'l', 'lx', 'lanxi', '', '县', '231222', '0455', '3');
INSERT INTO `district` VALUES ('1203', '青冈', '193', 'q', 'qg', 'qinggang', '', '县', '231223', '0455', '4');
INSERT INTO `district` VALUES ('1204', '庆安', '193', 'q', 'qa', 'qingan', '', '县', '231224', '0455', '5');
INSERT INTO `district` VALUES ('1205', '明水', '193', 'm', 'ms', 'mingshui', '', '县', '231225', '0455', '6');
INSERT INTO `district` VALUES ('1206', '绥棱', '193', 's', 'sl', 'suileng', '', '县', '231226', '0455', '7');
INSERT INTO `district` VALUES ('1207', '安达', '193', 'a', 'ad', 'anda', '', '市', '231281', '0455', '8');
INSERT INTO `district` VALUES ('1208', '肇东', '193', 'z', 'zd', 'zhaodong', '', '市', '231282', '0455', '9');
INSERT INTO `district` VALUES ('1209', '海伦', '193', 'h', 'hl', 'hailun', '', '市', '231283', '0455', '10');
INSERT INTO `district` VALUES ('1210', '加格达奇', '194', 'j', 'jgdq', 'jiagedaqi', '', '区', '232701', '0457', '1');
INSERT INTO `district` VALUES ('1211', '松岭', '194', 's', 'sl', 'songling', '', '区', '232702', '0457', '2');
INSERT INTO `district` VALUES ('1212', '新林', '194', 'x', 'xl', 'xinlin', '', '区', '232703', '0457', '3');
INSERT INTO `district` VALUES ('1213', '呼中', '194', 'h', 'hz', 'huzhong', '', '区', '232704', '0457', '4');
INSERT INTO `district` VALUES ('1214', '呼玛', '194', 'h', 'hm', 'huma', '', '县', '232721', '0457', '5');
INSERT INTO `district` VALUES ('1215', '塔河', '194', 't', 'th', 'tahe', '', '县', '232722', '0457', '6');
INSERT INTO `district` VALUES ('1216', '漠河', '194', 'm', 'mh', 'mohe', '', '县', '232723', '0457', '7');
INSERT INTO `district` VALUES ('1217', '玄武', '195', 'x', 'xw', 'xuanwu', '', '区', '320102', '', '1');
INSERT INTO `district` VALUES ('1218', '秦淮', '195', 'q', 'qh', 'qinhuai', '', '区', '320104', '', '2');
INSERT INTO `district` VALUES ('1219', '建邺', '195', 'j', 'jy', 'jianye', '', '区', '320105', '', '3');
INSERT INTO `district` VALUES ('1220', '鼓楼', '195', 'g', 'gl', 'gulou', '', '区', '320106', '', '4');
INSERT INTO `district` VALUES ('1221', '浦口', '195', 'p', 'pk', 'pukou', '', '区', '320111', '', '5');
INSERT INTO `district` VALUES ('1222', '栖霞', '195', 'q', 'qx', 'qixia', '', '区', '320113', '', '6');
INSERT INTO `district` VALUES ('1223', '雨花台', '195', 'y', 'yht', 'yuhuatai', '', '区', '320114', '', '7');
INSERT INTO `district` VALUES ('1224', '江宁', '195', 'j', 'jn', 'jiangning', '', '区', '320115', '', '8');
INSERT INTO `district` VALUES ('1225', '六合', '195', 'l', 'lh', 'liuhe', '', '区', '320116', '', '9');
INSERT INTO `district` VALUES ('1226', '溧水', '195', 'l', 'ls', 'lishui', '', '区', '320124', '', '10');
INSERT INTO `district` VALUES ('1227', '高淳', '195', 'g', 'gc', 'gaochun', '', '区', '320125', '', '11');
INSERT INTO `district` VALUES ('1228', '崇安', '196', 'c', 'ca', 'chongan', '', '区', '320202', '0510', '1');
INSERT INTO `district` VALUES ('1229', '南长', '196', 'n', 'nz', 'nanzhang', '', '区', '320203', '0510', '2');
INSERT INTO `district` VALUES ('1230', '北塘', '196', 'b', 'bt', 'beitang', '', '区', '320204', '0510', '3');
INSERT INTO `district` VALUES ('1231', '锡山', '196', 'x', 'xs', 'xishan', '', '区', '320205', '0510', '4');
INSERT INTO `district` VALUES ('1232', '惠山', '196', 'h', 'hs', 'huishan', '', '区', '320206', '0510', '5');
INSERT INTO `district` VALUES ('1233', '滨湖', '196', 'b', 'bh', 'binhu', '', '区', '320211', '0510', '6');
INSERT INTO `district` VALUES ('1234', '江阴', '196', 'j', 'jy', 'jiangyin', '', '市', '320281', '0510', '7');
INSERT INTO `district` VALUES ('1235', '宜兴', '196', 'y', 'yx', 'yixing', '', '市', '320282', '0510', '8');
INSERT INTO `district` VALUES ('1236', '鼓楼', '197', 'g', 'gl', 'gulou', '', '区', '320302', '0516', '1');
INSERT INTO `district` VALUES ('1237', '云龙', '197', 'y', 'yl', 'yunlong', '', '区', '320303', '0516', '2');
INSERT INTO `district` VALUES ('1238', '贾汪', '197', 'j', 'jw', 'jiawang', '', '区', '320305', '0516', '3');
INSERT INTO `district` VALUES ('1239', '泉山', '197', 'q', 'qs', 'quanshan', '', '区', '320311', '0516', '4');
INSERT INTO `district` VALUES ('1240', '铜山', '197', 't', 'ts', 'tongshan', '', '区', '320312', '0516', '5');
INSERT INTO `district` VALUES ('1241', '丰县', '197', 'f', 'fx', 'fengxian', '', '', '320321', '0516', '6');
INSERT INTO `district` VALUES ('1242', '沛县', '197', 'p', 'px', 'peixian', '', '', '320322', '0516', '7');
INSERT INTO `district` VALUES ('1243', '睢宁', '197', 's', 'sn', 'suining', '', '县', '320324', '0516', '8');
INSERT INTO `district` VALUES ('1244', '新沂', '197', 'x', 'xy', 'xinyi', '', '市', '320381', '0516', '9');
INSERT INTO `district` VALUES ('1245', '邳州', '197', 'p', 'pz', 'pizhou', '', '市', '320382', '0516', '10');
INSERT INTO `district` VALUES ('1246', '天宁', '198', 't', 'tn', 'tianning', '', '区', '320402', '0519', '1');
INSERT INTO `district` VALUES ('1247', '钟楼', '198', 'z', 'zl', 'zhonglou', '', '区', '320404', '0519', '2');
INSERT INTO `district` VALUES ('1248', '戚墅堰', '198', 'q', 'qsy', 'qishuyan', '', '区', '320405', '0519', '3');
INSERT INTO `district` VALUES ('1249', '新北', '198', 'x', 'xb', 'xinbei', '', '区', '320411', '0519', '4');
INSERT INTO `district` VALUES ('1250', '武进', '198', 'w', 'wj', 'wujin', '', '区', '320412', '0519', '5');
INSERT INTO `district` VALUES ('1251', '溧阳', '198', 'l', 'ly', 'liyang', '', '市', '320481', '0519', '6');
INSERT INTO `district` VALUES ('1252', '金坛', '198', 'j', 'jt', 'jintan', '', '市', '320482', '0519', '7');
INSERT INTO `district` VALUES ('1253', '虎丘', '199', 'h', 'hq', 'huqiu', '', '区', '320505', '0512', '1');
INSERT INTO `district` VALUES ('1254', '吴中', '199', 'w', 'wz', 'wuzhong', '', '区', '320506', '0512', '2');
INSERT INTO `district` VALUES ('1255', '相城', '199', 'x', 'xc', 'xiangcheng', '', '区', '320507', '0512', '3');
INSERT INTO `district` VALUES ('1256', '姑苏', '199', 'g', 'gs', 'gusu', '', '区', '320508', '0512', '4');
INSERT INTO `district` VALUES ('1257', '吴江', '199', 'w', 'wj', 'wujiang', '', '市', '320509', '0512', '5');
INSERT INTO `district` VALUES ('1258', '常熟', '199', 'c', 'cs', 'changshu', '', '市', '320581', '0512', '6');
INSERT INTO `district` VALUES ('1259', '张家港', '199', 'z', 'zjg', 'zhangjiagang', '', '市', '320582', '0512', '7');
INSERT INTO `district` VALUES ('1260', '昆山', '199', 'k', 'ks', 'kunshan', '', '市', '320583', '0512', '8');
INSERT INTO `district` VALUES ('1261', '太仓', '199', 't', 'tc', 'taicang', '', '市', '320585', '0512', '9');
INSERT INTO `district` VALUES ('1262', '崇川', '200', 'c', 'cc', 'chongchuan', '', '区', '320602', '0513', '1');
INSERT INTO `district` VALUES ('1263', '港闸', '200', 'g', 'gz', 'gangzha', '', '区', '320611', '0513', '2');
INSERT INTO `district` VALUES ('1264', '通州', '200', 't', 'tz', 'tongzhou', '', '区', '320612', '0513', '3');
INSERT INTO `district` VALUES ('1265', '海安', '200', 'h', 'ha', 'haian', '', '县', '320621', '0513', '4');
INSERT INTO `district` VALUES ('1266', '如东', '200', 'r', 'rd', 'rudong', '', '县', '320623', '0513', '5');
INSERT INTO `district` VALUES ('1267', '启东', '200', 'q', 'qd', 'qidong', '', '市', '320681', '0513', '6');
INSERT INTO `district` VALUES ('1268', '如皋', '200', 'r', 'rg', 'rugao', '', '市', '320682', '0513', '7');
INSERT INTO `district` VALUES ('1269', '海门', '200', 'h', 'hm', 'haimen', '', '市', '320684', '0513', '8');
INSERT INTO `district` VALUES ('1270', '连云', '201', 'l', 'ly', 'lianyun', '', '区', '320703', '0518', '1');
INSERT INTO `district` VALUES ('1272', '海州', '201', 'h', 'hz', 'haizhou', '', '区', '320706', '0518', '2');
INSERT INTO `district` VALUES ('1273', '赣榆', '201', 'g', 'gy', 'ganyu', '', '区', '320721', '0518', '3');
INSERT INTO `district` VALUES ('1274', '东海', '201', 'd', 'dh', 'donghai', '', '县', '320722', '0518', '4');
INSERT INTO `district` VALUES ('1275', '灌云', '201', 'g', 'gy', 'guanyun', '', '县', '320723', '0518', '5');
INSERT INTO `district` VALUES ('1276', '灌南', '201', 'g', 'gn', 'guannan', '', '县', '320724', '0518', '6');
INSERT INTO `district` VALUES ('1277', '清河', '202', 'q', 'qh', 'qinghe', '', '区', '320802', '0517', '1');
INSERT INTO `district` VALUES ('1278', '淮安', '202', 'h', 'ha', 'huaian', '', '区', '320803', '0517', '2');
INSERT INTO `district` VALUES ('1279', '淮阴', '202', 'h', 'hy', 'huaiyin', '', '区', '320804', '0517', '3');
INSERT INTO `district` VALUES ('1280', '清浦', '202', 'q', 'qp', 'qingpu', '', '区', '320811', '0517', '4');
INSERT INTO `district` VALUES ('1281', '涟水', '202', 'l', 'ls', 'lianshui', '', '县', '320826', '0517', '5');
INSERT INTO `district` VALUES ('1282', '洪泽', '202', 'h', 'hz', 'hongze', '', '县', '320829', '0517', '6');
INSERT INTO `district` VALUES ('1283', '盱眙', '202', 'x', 'xy', 'xuyi', '', '县', '320830', '0517', '7');
INSERT INTO `district` VALUES ('1284', '金湖', '202', 'j', 'jh', 'jinhu', '', '县', '320831', '0517', '8');
INSERT INTO `district` VALUES ('1285', '亭湖', '203', 't', 'th', 'tinghu', '', '区', '320902', '0515', '1');
INSERT INTO `district` VALUES ('1286', '盐都', '203', 'y', 'yd', 'yandou', '', '区', '320903', '0515', '2');
INSERT INTO `district` VALUES ('1287', '响水', '203', 'x', 'xs', 'xiangshui', '', '县', '320921', '0515', '3');
INSERT INTO `district` VALUES ('1288', '滨海', '203', 'b', 'bh', 'binhai', '', '县', '320922', '0515', '4');
INSERT INTO `district` VALUES ('1289', '阜宁', '203', 'f', 'fn', 'funing', '', '县', '320923', '0515', '5');
INSERT INTO `district` VALUES ('1290', '射阳', '203', 's', 'sy', 'sheyang', '', '县', '320924', '0515', '6');
INSERT INTO `district` VALUES ('1291', '建湖', '203', 'j', 'jh', 'jianhu', '', '县', '320925', '0515', '7');
INSERT INTO `district` VALUES ('1292', '东台', '203', 'd', 'dt', 'dongtai', '', '市', '320981', '0515', '8');
INSERT INTO `district` VALUES ('1293', '大丰', '203', 'd', 'df', 'dafeng', '', '市', '320982', '0515', '9');
INSERT INTO `district` VALUES ('1294', '广陵', '204', 'g', 'gl', 'guangling', '', '区', '321002', '0514', '1');
INSERT INTO `district` VALUES ('1295', '邗江', '204', 'h', 'hj', 'hanjiang', '', '区', '321003', '0514', '2');
INSERT INTO `district` VALUES ('1296', '江都', '204', 'j', 'jd', 'jiangdou', '', '区', '321012', '0514', '3');
INSERT INTO `district` VALUES ('1297', '宝应', '204', 'b', 'by', 'baoying', '', '县', '321023', '0514', '4');
INSERT INTO `district` VALUES ('1298', '仪征', '204', 'y', 'yz', 'yizheng', '', '市', '321081', '0514', '5');
INSERT INTO `district` VALUES ('1299', '高邮', '204', 'g', 'gy', 'gaoyou', '', '市', '321084', '0514', '6');
INSERT INTO `district` VALUES ('1300', '京口', '205', 'j', 'jk', 'jingkou', '', '区', '321102', '0511', '1');
INSERT INTO `district` VALUES ('1301', '润州', '205', 'r', 'rz', 'runzhou', '', '区', '321111', '0511', '2');
INSERT INTO `district` VALUES ('1302', '丹徒', '205', 'd', 'dt', 'dantu', '', '区', '321112', '0511', '3');
INSERT INTO `district` VALUES ('1303', '丹阳', '205', 'd', 'dy', 'danyang', '', '市', '321181', '0511', '4');
INSERT INTO `district` VALUES ('1304', '扬中', '205', 'y', 'yz', 'yangzhong', '', '市', '321182', '0511', '5');
INSERT INTO `district` VALUES ('1305', '句容', '205', 'j', 'jr', 'jurong', '', '市', '321183', '0511', '6');
INSERT INTO `district` VALUES ('1306', '海陵', '206', 'h', 'hl', 'hailing', '', '区', '321202', '0523', '1');
INSERT INTO `district` VALUES ('1307', '高港', '206', 'g', 'gg', 'gaogang', '', '区', '321203', '0523', '2');
INSERT INTO `district` VALUES ('1308', '兴化', '206', 'x', 'xh', 'xinghua', '', '市', '321281', '0523', '3');
INSERT INTO `district` VALUES ('1309', '靖江', '206', 'j', 'jj', 'jingjiang', '', '市', '321282', '0523', '4');
INSERT INTO `district` VALUES ('1310', '泰兴', '206', 't', 'tx', 'taixing', '', '市', '321283', '0523', '5');
INSERT INTO `district` VALUES ('1311', '姜堰', '206', 'j', 'jy', 'jiangyan', '', '区', '321284', '0523', '6');
INSERT INTO `district` VALUES ('1312', '宿城', '207', 's', 'sc', 'sucheng', '', '区', '321302', '0527', '1');
INSERT INTO `district` VALUES ('1313', '宿豫', '207', 's', 'sy', 'suyu', '', '区', '321311', '0527', '2');
INSERT INTO `district` VALUES ('1314', '沭阳', '207', 's', 'sy', 'shuyang', '', '县', '321322', '0527', '3');
INSERT INTO `district` VALUES ('1315', '泗阳', '207', 's', 'sy', 'siyang', '', '县', '321323', '0527', '4');
INSERT INTO `district` VALUES ('1316', '泗洪', '207', 's', 'sh', 'sihong', '', '县', '321324', '0527', '5');
INSERT INTO `district` VALUES ('1317', '上城', '208', 's', 'sc', 'shangcheng', '', '区', '330102', '0571', '1');
INSERT INTO `district` VALUES ('1318', '下城', '208', 'x', 'xc', 'xiacheng', '', '区', '330103', '0571', '2');
INSERT INTO `district` VALUES ('1319', '江干', '208', 'j', 'jg', 'jianggan', '', '区', '330104', '0571', '3');
INSERT INTO `district` VALUES ('1320', '拱墅', '208', 'g', 'gs', 'gongshu', '', '区', '330105', '0571', '4');
INSERT INTO `district` VALUES ('1321', '西湖', '208', 'x', 'xh', 'xihu', '', '区', '330106', '0571', '5');
INSERT INTO `district` VALUES ('1322', '滨江', '208', 'b', 'bj', 'binjiang', '', '区', '330108', '0571', '6');
INSERT INTO `district` VALUES ('1323', '萧山', '208', 'x', 'xs', 'xiaoshan', '', '区', '330109', '0571', '7');
INSERT INTO `district` VALUES ('1324', '余杭', '208', 'y', 'yh', 'yuhang', '', '区', '330110', '0571', '8');
INSERT INTO `district` VALUES ('1325', '桐庐', '208', 't', 'tl', 'tonglu', '', '县', '330122', '0571', '9');
INSERT INTO `district` VALUES ('1326', '淳安', '208', 'c', 'ca', 'chunan', '', '县', '330127', '0571', '11');
INSERT INTO `district` VALUES ('1327', '建德', '208', 'j', 'jd', 'jiande', '', '市', '330182', '0571', '12');
INSERT INTO `district` VALUES ('1328', '富阳', '208', 'f', 'fy', 'fuyang', '', '区', '330183', '0571', '10');
INSERT INTO `district` VALUES ('1329', '临安', '208', 'l', 'la', 'linan', '', '市', '330185', '0571', '13');
INSERT INTO `district` VALUES ('1330', '海曙', '209', 'h', 'hs', 'haishu', '', '区', '330203', '0574', '1');
INSERT INTO `district` VALUES ('1331', '江东', '209', 'j', 'jd', 'jiangdong', '', '区', '330204', '0574', '2');
INSERT INTO `district` VALUES ('1332', '江北', '209', 'j', 'jb', 'jiangbei', '', '区', '330205', '0574', '3');
INSERT INTO `district` VALUES ('1333', '北仑', '209', 'b', 'bl', 'beilun', '', '区', '330206', '0574', '4');
INSERT INTO `district` VALUES ('1334', '镇海', '209', 'z', 'zh', 'zhenhai', '', '区', '330211', '0574', '5');
INSERT INTO `district` VALUES ('1335', '鄞州', '209', 'y', 'yz', 'yinzhou', '', '区', '330212', '0574', '6');
INSERT INTO `district` VALUES ('1336', '象山', '209', 'x', 'xs', 'xiangshan', '', '县', '330225', '0574', '7');
INSERT INTO `district` VALUES ('1337', '宁海', '209', 'n', 'nh', 'ninghai', '', '县', '330226', '0574', '8');
INSERT INTO `district` VALUES ('1338', '余姚', '209', 'y', 'yy', 'yuyao', '', '市', '330281', '0574', '9');
INSERT INTO `district` VALUES ('1339', '慈溪', '209', 'c', 'cx', 'cixi', '', '市', '330282', '0574', '10');
INSERT INTO `district` VALUES ('1340', '奉化', '209', 'f', 'fh', 'fenghua', '', '市', '330283', '0574', '11');
INSERT INTO `district` VALUES ('1341', '鹿城', '210', 'l', 'lc', 'lucheng', '', '区', '330302', '0577', '1');
INSERT INTO `district` VALUES ('1342', '龙湾', '210', 'l', 'lw', 'longwan', '', '区', '330303', '0577', '2');
INSERT INTO `district` VALUES ('1343', '瓯海', '210', 'o', 'oh', 'ouhai', '', '区', '330304', '0577', '3');
INSERT INTO `district` VALUES ('1344', '洞头', '210', 'd', 'dt', 'dongtou', '', '县', '330322', '0577', '4');
INSERT INTO `district` VALUES ('1345', '永嘉', '210', 'y', 'yj', 'yongjia', '', '县', '330324', '0577', '5');
INSERT INTO `district` VALUES ('1346', '平阳', '210', 'p', 'py', 'pingyang', '', '县', '330326', '0577', '6');
INSERT INTO `district` VALUES ('1347', '苍南', '210', 'c', 'cn', 'cangnan', '', '县', '330327', '0577', '7');
INSERT INTO `district` VALUES ('1348', '文成', '210', 'w', 'wc', 'wencheng', '', '县', '330328', '0577', '8');
INSERT INTO `district` VALUES ('1349', '泰顺', '210', 't', 'ts', 'taishun', '', '县', '330329', '0577', '9');
INSERT INTO `district` VALUES ('1350', '瑞安', '210', 'r', 'ra', 'ruian', '', '市', '330381', '0577', '10');
INSERT INTO `district` VALUES ('1351', '乐清', '210', 'l', 'lq', 'leqing', '', '市', '330382', '0577', '11');
INSERT INTO `district` VALUES ('1352', '南湖', '211', 'n', 'nh', 'nanhu', '', '区', '330402', '0573', '1');
INSERT INTO `district` VALUES ('1353', '秀洲', '211', 'x', 'xz', 'xiuzhou', '', '区', '330411', '0573', '2');
INSERT INTO `district` VALUES ('1354', '嘉善', '211', 'j', 'js', 'jiashan', '', '县', '330421', '0573', '3');
INSERT INTO `district` VALUES ('1355', '海盐', '211', 'h', 'hy', 'haiyan', '', '县', '330424', '0573', '4');
INSERT INTO `district` VALUES ('1356', '海宁', '211', 'h', 'hn', 'haining', '', '市', '330481', '0573', '5');
INSERT INTO `district` VALUES ('1357', '平湖', '211', 'p', 'ph', 'pinghu', '', '市', '330482', '0573', '6');
INSERT INTO `district` VALUES ('1358', '桐乡', '211', 't', 'tx', 'tongxiang', '', '市', '330483', '0573', '7');
INSERT INTO `district` VALUES ('1359', '吴兴', '212', 'w', 'wx', 'wuxing', '', '区', '330502', '0572', '1');
INSERT INTO `district` VALUES ('1360', '南浔', '212', 'n', 'nx', 'nanxun', '', '区', '330503', '0572', '2');
INSERT INTO `district` VALUES ('1361', '德清', '212', 'd', 'dq', 'deqing', '', '县', '330521', '0572', '3');
INSERT INTO `district` VALUES ('1362', '长兴', '212', 'z', 'zx', 'zhangxing', '', '县', '330522', '0572', '4');
INSERT INTO `district` VALUES ('1363', '安吉', '212', 'a', 'aj', 'anji', '', '县', '330523', '0572', '5');
INSERT INTO `district` VALUES ('1364', '越城', '213', 'y', 'yc', 'yuecheng', '', '区', '330602', '0575', '1');
INSERT INTO `district` VALUES ('1365', '柯桥', '213', 'k', 'kq', 'keqiao', '', '区', '330603', '0575', '2');
INSERT INTO `district` VALUES ('1366', '新昌', '213', 'x', 'xc', 'xinchang', '', '县', '330624', '0575', '4');
INSERT INTO `district` VALUES ('1367', '诸暨', '213', 'z', 'zj', 'zhuji', '', '市', '330681', '0575', '5');
INSERT INTO `district` VALUES ('1368', '上虞', '213', 's', 'sy', 'shangyu', '', '区', '330604', '0575', '3');
INSERT INTO `district` VALUES ('1369', '嵊州', '213', 's', 'sz', 'shengzhou', '', '市', '330683', '0575', '6');
INSERT INTO `district` VALUES ('1370', '婺城', '214', 'w', 'wc', 'wucheng', '', '区', '330702', '0579', '1');
INSERT INTO `district` VALUES ('1371', '金东', '214', 'j', 'jd', 'jindong', '', '区', '330703', '0579', '2');
INSERT INTO `district` VALUES ('1372', '武义', '214', 'w', 'wy', 'wuyi', '', '县', '330723', '0579', '3');
INSERT INTO `district` VALUES ('1373', '浦江', '214', 'p', 'pj', 'pujiang', '', '县', '330726', '0579', '4');
INSERT INTO `district` VALUES ('1374', '磐安', '214', 'p', 'pa', 'panan', '', '县', '330727', '0579', '5');
INSERT INTO `district` VALUES ('1375', '兰溪', '214', 'l', 'lx', 'lanxi', '', '市', '330781', '0579', '6');
INSERT INTO `district` VALUES ('1376', '义乌', '214', 'y', 'yw', 'yiwu', '', '市', '330782', '0579', '7');
INSERT INTO `district` VALUES ('1377', '东阳', '214', 'd', 'dy', 'dongyang', '', '市', '330783', '0579', '8');
INSERT INTO `district` VALUES ('1378', '永康', '214', 'y', 'yk', 'yongkang', '', '市', '330784', '0579', '9');
INSERT INTO `district` VALUES ('1379', '柯城', '215', 'k', 'kc', 'kecheng', '', '区', '330802', '0570', '1');
INSERT INTO `district` VALUES ('1380', '衢江', '215', 'q', 'qj', 'qujiang', '', '区', '330803', '0570', '2');
INSERT INTO `district` VALUES ('1381', '常山', '215', 'c', 'cs', 'changshan', '', '县', '330822', '0570', '3');
INSERT INTO `district` VALUES ('1382', '开化', '215', 'k', 'kh', 'kaihua', '', '县', '330824', '0570', '4');
INSERT INTO `district` VALUES ('1383', '龙游', '215', 'l', 'ly', 'longyou', '', '县', '330825', '0570', '5');
INSERT INTO `district` VALUES ('1384', '江山', '215', 'j', 'js', 'jiangshan', '', '市', '330881', '0570', '6');
INSERT INTO `district` VALUES ('1385', '定海', '216', 'd', 'dh', 'dinghai', '', '区', '330902', '0580', '1');
INSERT INTO `district` VALUES ('1386', '普陀', '216', 'p', 'pt', 'putuo', '', '区', '330903', '0580', '2');
INSERT INTO `district` VALUES ('1387', '岱山', '216', 'd', 'ds', 'daishan', '', '县', '330921', '0580', '3');
INSERT INTO `district` VALUES ('1388', '嵊泗', '216', 's', 'ss', 'shengsi', '', '县', '330922', '0580', '4');
INSERT INTO `district` VALUES ('1389', '椒江', '217', 'j', 'jj', 'jiaojiang', '', '区', '331002', '0576', '1');
INSERT INTO `district` VALUES ('1390', '黄岩', '217', 'h', 'hy', 'huangyan', '', '区', '331003', '0576', '2');
INSERT INTO `district` VALUES ('1391', '路桥', '217', 'l', 'lq', 'luqiao', '', '区', '331004', '0576', '3');
INSERT INTO `district` VALUES ('1392', '玉环', '217', 'y', 'yh', 'yuhuan', '', '县', '331021', '0576', '4');
INSERT INTO `district` VALUES ('1393', '三门', '217', 's', 'sm', 'sanmen', '', '县', '331022', '0576', '5');
INSERT INTO `district` VALUES ('1394', '天台', '217', 't', 'tt', 'tiantai', '', '县', '331023', '0576', '6');
INSERT INTO `district` VALUES ('1395', '仙居', '217', 'x', 'xj', 'xianju', '', '县', '331024', '0576', '7');
INSERT INTO `district` VALUES ('1396', '温岭', '217', 'w', 'wl', 'wenling', '', '市', '331081', '0576', '8');
INSERT INTO `district` VALUES ('1397', '临海', '217', 'l', 'lh', 'linhai', '', '市', '331082', '0576', '9');
INSERT INTO `district` VALUES ('1398', '莲都', '218', 'l', 'ld', 'liandou', '', '区', '331102', '0578', '1');
INSERT INTO `district` VALUES ('1399', '青田', '218', 'q', 'qt', 'qingtian', '', '县', '331121', '0578', '2');
INSERT INTO `district` VALUES ('1400', '缙云', '218', 'j', 'jy', 'jinyun', '', '县', '331122', '0578', '3');
INSERT INTO `district` VALUES ('1401', '遂昌', '218', 's', 'sc', 'suichang', '', '县', '331123', '0578', '4');
INSERT INTO `district` VALUES ('1402', '松阳', '218', 's', 'sy', 'songyang', '', '县', '331124', '0578', '5');
INSERT INTO `district` VALUES ('1403', '云和', '218', 'y', 'yh', 'yunhe', '', '县', '331125', '0578', '6');
INSERT INTO `district` VALUES ('1404', '庆元', '218', 'q', 'qy', 'qingyuan', '', '县', '331126', '0578', '7');
INSERT INTO `district` VALUES ('1405', '景宁', '218', 'j', 'jn', 'jingning', '畲族', '自治县', '331127', '0578', '8');
INSERT INTO `district` VALUES ('1406', '龙泉', '218', 'l', 'lq', 'longquan', '', '市', '331181', '0578', '9');
INSERT INTO `district` VALUES ('1407', '瑶海', '219', 'y', 'yh', 'yaohai', '', '区', '340102', '0551', '1');
INSERT INTO `district` VALUES ('1408', '庐阳', '219', 'l', 'ly', 'luyang', '', '区', '340103', '0551', '2');
INSERT INTO `district` VALUES ('1409', '蜀山', '219', 's', 'ss', 'shushan', '', '区', '340104', '0551', '3');
INSERT INTO `district` VALUES ('1410', '包河', '219', 'b', 'bh', 'baohe', '', '区', '340111', '0551', '4');
INSERT INTO `district` VALUES ('1411', '长丰', '219', 'z', 'zf', 'zhangfeng', '', '县', '340121', '0551', '5');
INSERT INTO `district` VALUES ('1412', '肥东', '219', 'f', 'fd', 'feidong', '', '县', '340122', '0551', '6');
INSERT INTO `district` VALUES ('1413', '肥西', '219', 'f', 'fx', 'feixi', '', '县', '340123', '0551', '7');
INSERT INTO `district` VALUES ('1414', '庐江', '219', 'l', 'lj', 'lujiang', '', '县', '340124', '0551', '8');
INSERT INTO `district` VALUES ('1415', '巢湖', '219', 'c', 'ch', 'chaohu', '', '市', '340181', '0551', '9');
INSERT INTO `district` VALUES ('1416', '镜湖', '220', 'j', 'jh', 'jinghu', '', '区', '340202', '0553', '1');
INSERT INTO `district` VALUES ('1417', '弋江', '220', 'y', 'yj', 'yijiang', '', '区', '340203', '0553', '2');
INSERT INTO `district` VALUES ('1418', '鸠江', '220', 'j', 'jj', 'jiujiang', '', '区', '340207', '0553', '3');
INSERT INTO `district` VALUES ('1419', '三山', '220', 's', 'ss', 'sanshan', '', '区', '340208', '0553', '4');
INSERT INTO `district` VALUES ('1420', '芜湖', '220', 'w', 'wh', 'wuhu', '', '县', '340221', '0553', '5');
INSERT INTO `district` VALUES ('1421', '繁昌', '220', 'f', 'fc', 'fanchang', '', '县', '340222', '0553', '6');
INSERT INTO `district` VALUES ('1422', '南陵', '220', 'n', 'nl', 'nanling', '', '县', '340223', '0553', '7');
INSERT INTO `district` VALUES ('1423', '无为', '220', 'w', 'ww', 'wuwei', '', '县', '340225', '0553', '8');
INSERT INTO `district` VALUES ('1424', '龙子湖', '221', 'l', 'lzh', 'longzihu', '', '区', '340302', '0552', '1');
INSERT INTO `district` VALUES ('1425', '蚌山', '221', 'b', 'bs', 'bangshan', '', '区', '340303', '0552', '2');
INSERT INTO `district` VALUES ('1426', '禹会', '221', 'y', 'yh', 'yuhui', '', '区', '340304', '0552', '3');
INSERT INTO `district` VALUES ('1427', '淮上', '221', 'h', 'hs', 'huaishang', '', '区', '340311', '0552', '4');
INSERT INTO `district` VALUES ('1428', '怀远', '221', 'h', 'hy', 'huaiyuan', '', '县', '340321', '0552', '5');
INSERT INTO `district` VALUES ('1429', '五河', '221', 'w', 'wh', 'wuhe', '', '县', '340322', '0552', '6');
INSERT INTO `district` VALUES ('1430', '固镇', '221', 'g', 'gz', 'guzhen', '', '县', '340323', '0552', '7');
INSERT INTO `district` VALUES ('1431', '大通', '222', 'd', 'dt', 'datong', '', '区', '340402', '0554', '1');
INSERT INTO `district` VALUES ('1432', '田家庵', '222', 't', 'tja', 'tianjiaan', '', '区', '340403', '0554', '2');
INSERT INTO `district` VALUES ('1433', '谢家集', '222', 'x', 'xjj', 'xiejiaji', '', '区', '340404', '0554', '3');
INSERT INTO `district` VALUES ('1434', '八公山', '222', 'b', 'bgs', 'bagongshan', '', '区', '340405', '0554', '4');
INSERT INTO `district` VALUES ('1435', '潘集', '222', 'p', 'pj', 'panji', '', '区', '340406', '0554', '5');
INSERT INTO `district` VALUES ('1436', '凤台', '222', 'f', 'ft', 'fengtai', '', '县', '340421', '0554', '6');
INSERT INTO `district` VALUES ('1437', '花山', '223', 'h', 'hs', 'huashan', '', '区', '340503', '0555', '1');
INSERT INTO `district` VALUES ('1438', '雨山', '223', 'y', 'ys', 'yushan', '', '区', '340504', '0555', '2');
INSERT INTO `district` VALUES ('1439', '博望', '223', 'b', 'bw', 'bowang', '', '区', '340506', '0555', '3');
INSERT INTO `district` VALUES ('1440', '当涂', '223', 'd', 'dt', 'dangtu', '', '县', '340521', '0555', '4');
INSERT INTO `district` VALUES ('1441', '含山', '223', 'h', 'hs', 'hanshan', '', '县', '340522', '0555', '5');
INSERT INTO `district` VALUES ('1442', '和县', '223', 'h', 'hx', 'hexian', '', '', '340523', '0555', '6');
INSERT INTO `district` VALUES ('1443', '杜集', '224', 'd', 'dj', 'duji', '', '区', '340602', '0561', '1');
INSERT INTO `district` VALUES ('1444', '相山', '224', 'x', 'xs', 'xiangshan', '', '区', '340603', '0561', '2');
INSERT INTO `district` VALUES ('1445', '烈山', '224', 'l', 'ls', 'lieshan', '', '区', '340604', '0561', '3');
INSERT INTO `district` VALUES ('1446', '濉溪', '224', 's', 'sx', 'suixi', '', '县', '340621', '0561', '4');
INSERT INTO `district` VALUES ('1447', '铜官山', '225', 't', 'tgs', 'tongguanshan', '', '区', '340702', '0562', '1');
INSERT INTO `district` VALUES ('1448', '狮子山', '225', 's', 'szs', 'shizishan', '', '区', '340703', '0562', '2');
INSERT INTO `district` VALUES ('1449', '郊区', '225', 'j', 'jq', 'jiaoqu', '', '', '340711', '0562', '3');
INSERT INTO `district` VALUES ('1450', '铜陵', '225', 't', 'tl', 'tongling', '', '县', '340721', '0562', '4');
INSERT INTO `district` VALUES ('1451', '迎江', '226', 'y', 'yj', 'yingjiang', '', '区', '340802', '0556', '1');
INSERT INTO `district` VALUES ('1452', '大观', '226', 'd', 'dg', 'daguan', '', '区', '340803', '0556', '2');
INSERT INTO `district` VALUES ('1453', '宜秀', '226', 'y', 'yx', 'yixiu', '', '区', '340811', '0556', '3');
INSERT INTO `district` VALUES ('1454', '怀宁', '226', 'h', 'hn', 'huaining', '', '县', '340822', '0556', '4');
INSERT INTO `district` VALUES ('1455', '枞阳', '226', 'z', 'zy', 'zongyang', '', '县', '340823', '0556', '5');
INSERT INTO `district` VALUES ('1456', '潜山', '226', 'q', 'qs', 'qianshan', '', '县', '340824', '0556', '6');
INSERT INTO `district` VALUES ('1457', '太湖', '226', 't', 'th', 'taihu', '', '县', '340825', '0556', '7');
INSERT INTO `district` VALUES ('1458', '宿松', '226', 's', 'ss', 'susong', '', '县', '340826', '0556', '8');
INSERT INTO `district` VALUES ('1459', '望江', '226', 'w', 'wj', 'wangjiang', '', '县', '340827', '0556', '9');
INSERT INTO `district` VALUES ('1460', '岳西', '226', 'y', 'yx', 'yuexi', '', '县', '340828', '0556', '10');
INSERT INTO `district` VALUES ('1461', '桐城', '226', 't', 'tc', 'tongcheng', '', '市', '340881', '0556', '11');
INSERT INTO `district` VALUES ('1462', '屯溪', '227', 't', 'tx', 'tunxi', '', '区', '341002', '0559', '1');
INSERT INTO `district` VALUES ('1463', '黄山', '227', 'h', 'hs', 'huangshan', '', '区', '341003', '0559', '2');
INSERT INTO `district` VALUES ('1464', '徽州', '227', 'h', 'hz', 'huizhou', '', '区', '341004', '0559', '3');
INSERT INTO `district` VALUES ('1465', '歙县', '227', 's', 'sx', 'shexian', '', '', '341021', '0559', '4');
INSERT INTO `district` VALUES ('1466', '休宁', '227', 'x', 'xn', 'xiuning', '', '县', '341022', '0559', '5');
INSERT INTO `district` VALUES ('1467', '黟县', '227', 'y', 'yx', 'yixian', '', '', '341023', '0559', '6');
INSERT INTO `district` VALUES ('1468', '祁门', '227', 'q', 'qm', 'qimen', '', '县', '341024', '0559', '7');
INSERT INTO `district` VALUES ('1469', '琅玡', '228', 'l', 'ly', 'langya', '', '区', '341102', '0550', '1');
INSERT INTO `district` VALUES ('1470', '南谯', '228', 'n', 'nq', 'nanqiao', '', '区', '341103', '0550', '2');
INSERT INTO `district` VALUES ('1471', '来安', '228', 'l', 'la', 'laian', '', '县', '341122', '0550', '3');
INSERT INTO `district` VALUES ('1472', '全椒', '228', 'q', 'qj', 'quanjiao', '', '县', '341124', '0550', '4');
INSERT INTO `district` VALUES ('1473', '定远', '228', 'd', 'dy', 'dingyuan', '', '县', '341125', '0550', '5');
INSERT INTO `district` VALUES ('1474', '凤阳', '228', 'f', 'fy', 'fengyang', '', '县', '341126', '0550', '6');
INSERT INTO `district` VALUES ('1475', '天长', '228', 't', 'tz', 'tianzhang', '', '市', '341181', '0550', '7');
INSERT INTO `district` VALUES ('1476', '明光', '228', 'm', 'mg', 'mingguang', '', '市', '341182', '0550', '8');
INSERT INTO `district` VALUES ('1477', '颍州', '229', 'y', 'yz', 'yingzhou', '', '区', '341202', '0558', '1');
INSERT INTO `district` VALUES ('1478', '颍东', '229', 'y', 'yd', 'yingdong', '', '区', '341203', '0558', '2');
INSERT INTO `district` VALUES ('1479', '颍泉', '229', 'y', 'yq', 'yingquan', '', '区', '341204', '0558', '3');
INSERT INTO `district` VALUES ('1480', '临泉', '229', 'l', 'lq', 'linquan', '', '县', '341221', '0558', '4');
INSERT INTO `district` VALUES ('1481', '太和', '229', 't', 'th', 'taihe', '', '县', '341222', '0558', '5');
INSERT INTO `district` VALUES ('1482', '阜南', '229', 'f', 'fn', 'funan', '', '县', '341225', '0558', '6');
INSERT INTO `district` VALUES ('1483', '颖上', '229', 'y', 'ys', 'yingshang', '', '县', '341226', '0558', '7');
INSERT INTO `district` VALUES ('1484', '界首', '229', 'j', 'js', 'jieshou', '', '市', '341282', '0558', '8');
INSERT INTO `district` VALUES ('1485', '埇桥', '230', 'y', 'yq', 'yongqiao', '', '区', '341302', '0557', '1');
INSERT INTO `district` VALUES ('1486', '砀山', '230', 'd', 'ds', 'dangshan', '', '县', '341321', '0557', '2');
INSERT INTO `district` VALUES ('1487', '萧县', '230', 'x', 'xx', 'xiaoxian', '', '', '341322', '0557', '3');
INSERT INTO `district` VALUES ('1488', '灵璧', '230', 'l', 'lb', 'lingbi', '', '县', '341323', '0557', '4');
INSERT INTO `district` VALUES ('1489', '泗县', '230', 's', 'sx', 'sixian', '', '', '341324', '0557', '5');
INSERT INTO `district` VALUES ('1490', '金安', '231', 'j', 'ja', 'jinan', '', '区', '341502', '0564', '1');
INSERT INTO `district` VALUES ('1491', '裕安', '231', 'y', 'ya', 'yuan', '', '区', '341503', '0564', '2');
INSERT INTO `district` VALUES ('1492', '寿县', '231', 's', 'sx', 'shouxian', '', '', '341521', '0564', '3');
INSERT INTO `district` VALUES ('1493', '霍邱', '231', 'h', 'hq', 'huoqiu', '', '县', '341522', '0564', '4');
INSERT INTO `district` VALUES ('1494', '舒城', '231', 's', 'sc', 'shucheng', '', '县', '341523', '0564', '5');
INSERT INTO `district` VALUES ('1495', '金寨', '231', 'j', 'jz', 'jinzhai', '', '县', '341524', '0564', '6');
INSERT INTO `district` VALUES ('1496', '霍山', '231', 'h', 'hs', 'huoshan', '', '县', '341525', '0564', '7');
INSERT INTO `district` VALUES ('1497', '谯城', '232', 'q', 'qc', 'qiaocheng', '', '区', '341602', '0558', '1');
INSERT INTO `district` VALUES ('1498', '涡阳', '232', 'w', 'wy', 'woyang', '', '县', '341621', '0558', '2');
INSERT INTO `district` VALUES ('1499', '蒙城', '232', 'm', 'mc', 'mengcheng', '', '县', '341622', '0558', '3');
INSERT INTO `district` VALUES ('1500', '利辛', '232', 'l', 'lx', 'lixin', '', '县', '341623', '0558', '4');
INSERT INTO `district` VALUES ('1501', '贵池', '233', 'g', 'gc', 'guichi', '', '区', '341702', '0566', '1');
INSERT INTO `district` VALUES ('1502', '东至', '233', 'd', 'dz', 'dongzhi', '', '县', '341721', '0566', '2');
INSERT INTO `district` VALUES ('1503', '石台', '233', 's', 'st', 'shitai', '', '县', '341722', '0566', '3');
INSERT INTO `district` VALUES ('1504', '青阳', '233', 'q', 'qy', 'qingyang', '', '县', '341723', '0566', '4');
INSERT INTO `district` VALUES ('1505', '宣州', '234', 'x', 'xz', 'xuanzhou', '', '区', '341802', '0563', '1');
INSERT INTO `district` VALUES ('1506', '郎溪', '234', 'l', 'lx', 'langxi', '', '县', '341821', '0563', '2');
INSERT INTO `district` VALUES ('1507', '广德', '234', 'g', 'gd', 'guangde', '', '县', '341822', '0563', '3');
INSERT INTO `district` VALUES ('1508', '泾县', '234', 'j', 'jx', 'jingxian', '', '', '341823', '0563', '4');
INSERT INTO `district` VALUES ('1509', '绩溪', '234', 'j', 'jx', 'jixi', '', '县', '341824', '0563', '5');
INSERT INTO `district` VALUES ('1510', '旌德', '234', 'j', 'jd', 'jingde', '', '县', '341825', '0563', '6');
INSERT INTO `district` VALUES ('1511', '宁国', '234', 'n', 'ng', 'ningguo', '', '市', '341881', '0563', '7');
INSERT INTO `district` VALUES ('1512', '鼓楼', '235', 'g', 'gl', 'gulou', '', '区', '350102', '0591', '1');
INSERT INTO `district` VALUES ('1513', '台江', '235', 't', 'tj', 'taijiang', '', '区', '350103', '0591', '2');
INSERT INTO `district` VALUES ('1514', '仓山', '235', 'c', 'cs', 'cangshan', '', '区', '350104', '0591', '3');
INSERT INTO `district` VALUES ('1515', '马尾', '235', 'm', 'mw', 'mawei', '', '区', '350105', '0591', '4');
INSERT INTO `district` VALUES ('1516', '晋安', '235', 'j', 'ja', 'jinan', '', '区', '350111', '0591', '5');
INSERT INTO `district` VALUES ('1517', '闽侯', '235', 'm', 'mh', 'minhou', '', '县', '350121', '0591', '6');
INSERT INTO `district` VALUES ('1518', '连江', '235', 'l', 'lj', 'lianjiang', '', '县', '350122', '0591', '7');
INSERT INTO `district` VALUES ('1519', '罗源', '235', 'l', 'ly', 'luoyuan', '', '县', '350123', '0591', '8');
INSERT INTO `district` VALUES ('1520', '闽清', '235', 'm', 'mq', 'minqing', '', '县', '350124', '0591', '9');
INSERT INTO `district` VALUES ('1521', '永泰', '235', 'y', 'yt', 'yongtai', '', '县', '350125', '0591', '10');
INSERT INTO `district` VALUES ('1522', '平潭', '235', 'p', 'pt', 'pingtan', '', '县', '350128', '0591', '11');
INSERT INTO `district` VALUES ('1523', '福清', '235', 'f', 'fq', 'fuqing', '', '市', '350181', '0591', '12');
INSERT INTO `district` VALUES ('1524', '长乐', '235', 'z', 'zl', 'zhangle', '', '市', '350182', '0591', '13');
INSERT INTO `district` VALUES ('1525', '思明', '236', 's', 'sm', 'siming', '', '区', '350203', '0592', '1');
INSERT INTO `district` VALUES ('1526', '海沧', '236', 'h', 'hc', 'haicang', '', '区', '350205', '0592', '2');
INSERT INTO `district` VALUES ('1527', '湖里', '236', 'h', 'hl', 'huli', '', '区', '350206', '0592', '3');
INSERT INTO `district` VALUES ('1528', '集美', '236', 'j', 'jm', 'jimei', '', '区', '350211', '0592', '4');
INSERT INTO `district` VALUES ('1529', '同安', '236', 't', 'ta', 'tongan', '', '区', '350212', '0592', '5');
INSERT INTO `district` VALUES ('1530', '翔安', '236', 'x', 'xa', 'xiangan', '', '区', '350213', '0592', '6');
INSERT INTO `district` VALUES ('1531', '城厢', '237', 'c', 'cx', 'chengxiang', '', '区', '350302', '0594', '1');
INSERT INTO `district` VALUES ('1532', '涵江', '237', 'h', 'hj', 'hanjiang', '', '区', '350303', '0594', '2');
INSERT INTO `district` VALUES ('1533', '荔城', '237', 'l', 'lc', 'licheng', '', '区', '350304', '0594', '3');
INSERT INTO `district` VALUES ('1534', '秀屿', '237', 'x', 'xy', 'xiuyu', '', '区', '350305', '0594', '4');
INSERT INTO `district` VALUES ('1535', '仙游', '237', 'x', 'xy', 'xianyou', '', '县', '350322', '0594', '5');
INSERT INTO `district` VALUES ('1536', '梅列', '238', 'm', 'ml', 'meilie', '', '区', '350402', '0598', '1');
INSERT INTO `district` VALUES ('1537', '三元', '238', 's', 'sy', 'sanyuan', '', '区', '350403', '0598', '2');
INSERT INTO `district` VALUES ('1538', '明溪', '238', 'm', 'mx', 'mingxi', '', '县', '350421', '0598', '3');
INSERT INTO `district` VALUES ('1539', '清流', '238', 'q', 'ql', 'qingliu', '', '县', '350423', '0598', '4');
INSERT INTO `district` VALUES ('1540', '宁化', '238', 'n', 'nh', 'ninghua', '', '县', '350424', '0598', '5');
INSERT INTO `district` VALUES ('1541', '大田', '238', 'd', 'dt', 'datian', '', '县', '350425', '0598', '6');
INSERT INTO `district` VALUES ('1542', '尤溪', '238', 'y', 'yx', 'youxi', '', '县', '350426', '0598', '7');
INSERT INTO `district` VALUES ('1543', '沙县', '238', 's', 'sx', 'shaxian', '', '', '350427', '0598', '8');
INSERT INTO `district` VALUES ('1544', '将乐', '238', 'j', 'jl', 'jiangle', '', '县', '350428', '0598', '9');
INSERT INTO `district` VALUES ('1545', '泰宁', '238', 't', 'tn', 'taining', '', '县', '350429', '0598', '10');
INSERT INTO `district` VALUES ('1546', '建宁', '238', 'j', 'jn', 'jianning', '', '县', '350430', '0598', '11');
INSERT INTO `district` VALUES ('1547', '永安', '238', 'y', 'ya', 'yongan', '', '市', '350481', '0598', '12');
INSERT INTO `district` VALUES ('1548', '鲤城', '239', 'l', 'lc', 'licheng', '', '区', '350502', '0595', '1');
INSERT INTO `district` VALUES ('1549', '丰泽', '239', 'f', 'fz', 'fengze', '', '区', '350503', '0595', '2');
INSERT INTO `district` VALUES ('1550', '洛江', '239', 'l', 'lj', 'luojiang', '', '区', '350504', '0595', '3');
INSERT INTO `district` VALUES ('1551', '泉港', '239', 'q', 'qg', 'quangang', '', '区', '350505', '0595', '4');
INSERT INTO `district` VALUES ('1552', '惠安', '239', 'h', 'ha', 'huian', '', '县', '350521', '0595', '5');
INSERT INTO `district` VALUES ('1553', '安溪', '239', 'a', 'ax', 'anxi', '', '县', '350524', '0595', '6');
INSERT INTO `district` VALUES ('1554', '永春', '239', 'y', 'yc', 'yongchun', '', '县', '350525', '0595', '7');
INSERT INTO `district` VALUES ('1555', '德化', '239', 'd', 'dh', 'dehua', '', '县', '350526', '0595', '8');
INSERT INTO `district` VALUES ('1556', '金门', '239', 'j', 'jm', 'jinmen', '', '县', '350527', '0595', '9');
INSERT INTO `district` VALUES ('1557', '石狮', '239', 's', 'ss', 'shishi', '', '市', '350581', '0595', '10');
INSERT INTO `district` VALUES ('1558', '晋江', '239', 'j', 'jj', 'jinjiang', '', '市', '350582', '0595', '11');
INSERT INTO `district` VALUES ('1559', '南安', '239', 'n', 'na', 'nanan', '', '市', '350583', '0595', '12');
INSERT INTO `district` VALUES ('1560', '芗城', '240', 'x', 'xc', 'xiangcheng', '', '区', '350602', '0596', '1');
INSERT INTO `district` VALUES ('1561', '龙文', '240', 'l', 'lw', 'longwen', '', '区', '350603', '0596', '2');
INSERT INTO `district` VALUES ('1562', '云霄', '240', 'y', 'yx', 'yunxiao', '', '县', '350622', '0596', '3');
INSERT INTO `district` VALUES ('1563', '漳浦', '240', 'z', 'zp', 'zhangpu', '', '县', '350623', '0596', '4');
INSERT INTO `district` VALUES ('1564', '诏安', '240', 'z', 'za', 'zhaoan', '', '县', '350624', '0596', '5');
INSERT INTO `district` VALUES ('1565', '长泰', '240', 'z', 'zt', 'zhangtai', '', '县', '350625', '0596', '6');
INSERT INTO `district` VALUES ('1566', '东山', '240', 'd', 'ds', 'dongshan', '', '县', '350626', '0596', '7');
INSERT INTO `district` VALUES ('1567', '南靖', '240', 'n', 'nj', 'nanjing', '', '县', '350627', '0596', '8');
INSERT INTO `district` VALUES ('1568', '平和', '240', 'p', 'ph', 'pinghe', '', '县', '350628', '0596', '9');
INSERT INTO `district` VALUES ('1569', '华安', '240', 'h', 'ha', 'huaan', '', '县', '350629', '0596', '10');
INSERT INTO `district` VALUES ('1570', '龙海', '240', 'l', 'lh', 'longhai', '', '市', '350681', '0596', '11');
INSERT INTO `district` VALUES ('1571', '延平', '241', 'y', 'yp', 'yanping', '', '区', '350702', '0599', '1');
INSERT INTO `district` VALUES ('1572', '顺昌', '241', 's', 'sc', 'shunchang', '', '县', '350721', '0599', '3');
INSERT INTO `district` VALUES ('1573', '浦城', '241', 'p', 'pc', 'pucheng', '', '县', '350722', '0599', '4');
INSERT INTO `district` VALUES ('1574', '光泽', '241', 'g', 'gz', 'guangze', '', '县', '350723', '0599', '5');
INSERT INTO `district` VALUES ('1575', '松溪', '241', 's', 'sx', 'songxi', '', '县', '350724', '0599', '6');
INSERT INTO `district` VALUES ('1576', '政和', '241', 'z', 'zh', 'zhenghe', '', '县', '350725', '0599', '7');
INSERT INTO `district` VALUES ('1577', '邵武', '241', 's', 'sw', 'shaowu', '', '市', '350781', '0599', '8');
INSERT INTO `district` VALUES ('1578', '武夷山', '241', 'w', 'wys', 'wuyishan', '', '市', '350782', '0599', '9');
INSERT INTO `district` VALUES ('1579', '建瓯', '241', 'j', 'jo', 'jianou', '', '市', '350783', '0599', '10');
INSERT INTO `district` VALUES ('1580', '建阳', '241', 'j', 'jy', 'jianyang', '', '区', '350703', '0599', '2');
INSERT INTO `district` VALUES ('1581', '新罗', '242', 'x', 'xl', 'xinluo', '', '区', '350802', '0597', '1');
INSERT INTO `district` VALUES ('1582', '长汀', '242', 'z', 'zt', 'zhangting', '', '县', '350821', '0597', '3');
INSERT INTO `district` VALUES ('1583', '永定', '242', 'y', 'yd', 'yongding', '', '区', '350803', '0597', '2');
INSERT INTO `district` VALUES ('1584', '上杭', '242', 's', 'sh', 'shanghang', '', '县', '350823', '0597', '4');
INSERT INTO `district` VALUES ('1585', '武平', '242', 'w', 'wp', 'wuping', '', '县', '350824', '0597', '5');
INSERT INTO `district` VALUES ('1586', '连城', '242', 'l', 'lc', 'liancheng', '', '县', '350825', '0597', '6');
INSERT INTO `district` VALUES ('1587', '漳平', '242', 'z', 'zp', 'zhangping', '', '市', '350881', '0597', '7');
INSERT INTO `district` VALUES ('1588', '蕉城', '243', 'j', 'jc', 'jiaocheng', '', '区', '350902', '0593', '1');
INSERT INTO `district` VALUES ('1589', '霞浦', '243', 'x', 'xp', 'xiapu', '', '县', '350921', '0593', '2');
INSERT INTO `district` VALUES ('1590', '古田', '243', 'g', 'gt', 'gutian', '', '县', '350922', '0593', '3');
INSERT INTO `district` VALUES ('1591', '屏南', '243', 'p', 'pn', 'pingnan', '', '县', '350923', '0593', '4');
INSERT INTO `district` VALUES ('1592', '寿宁', '243', 's', 'sn', 'shouning', '', '县', '350924', '0593', '5');
INSERT INTO `district` VALUES ('1593', '周宁', '243', 'z', 'zn', 'zhouning', '', '县', '350925', '0593', '6');
INSERT INTO `district` VALUES ('1594', '柘荣', '243', 'z', 'zr', 'zherong', '', '县', '350926', '0593', '7');
INSERT INTO `district` VALUES ('1595', '福安', '243', 'f', 'fa', 'fuan', '', '市', '350981', '0593', '8');
INSERT INTO `district` VALUES ('1596', '福鼎', '243', 'f', 'fd', 'fuding', '', '市', '350982', '0593', '9');
INSERT INTO `district` VALUES ('1597', '东湖', '244', 'd', 'dh', 'donghu', '', '区', '360102', '0791', '1');
INSERT INTO `district` VALUES ('1598', '西湖', '244', 'x', 'xh', 'xihu', '', '区', '360103', '0791', '2');
INSERT INTO `district` VALUES ('1599', '青云谱', '244', 'q', 'qyp', 'qingyunpu', '', '区', '360104', '0791', '3');
INSERT INTO `district` VALUES ('1600', '湾里', '244', 'w', 'wl', 'wanli', '', '区', '360105', '0791', '4');
INSERT INTO `district` VALUES ('1601', '青山湖', '244', 'q', 'qsh', 'qingshanhu', '', '区', '360111', '0791', '5');
INSERT INTO `district` VALUES ('1602', '南昌', '244', 'n', 'nc', 'nanchang', '', '县', '360121', '0791', '6');
INSERT INTO `district` VALUES ('1603', '新建', '244', 'x', 'xj', 'xinjian', '', '县', '360122', '0791', '7');
INSERT INTO `district` VALUES ('1604', '安义', '244', 'a', 'ay', 'anyi', '', '县', '360123', '0791', '8');
INSERT INTO `district` VALUES ('1605', '进贤', '244', 'j', 'jx', 'jinxian', '', '县', '360124', '0791', '9');
INSERT INTO `district` VALUES ('1606', '昌江', '245', 'c', 'cj', 'changjiang', '', '区', '360202', '0798', '1');
INSERT INTO `district` VALUES ('1607', '珠山', '245', 'z', 'zs', 'zhushan', '', '区', '360203', '0798', '2');
INSERT INTO `district` VALUES ('1608', '浮梁', '245', 'f', 'fl', 'fuliang', '', '县', '360222', '0798', '3');
INSERT INTO `district` VALUES ('1609', '乐平', '245', 'l', 'lp', 'leping', '', '市', '360281', '0798', '4');
INSERT INTO `district` VALUES ('1610', '安源', '246', 'a', 'ay', 'anyuan', '', '区', '360302', '0799', '1');
INSERT INTO `district` VALUES ('1611', '湘东', '246', 'x', 'xd', 'xiangdong', '', '区', '360313', '0799', '2');
INSERT INTO `district` VALUES ('1612', '莲花', '246', 'l', 'lh', 'lianhua', '', '县', '360321', '0799', '3');
INSERT INTO `district` VALUES ('1613', '上栗', '246', 's', 'sl', 'shangli', '', '县', '360322', '0799', '4');
INSERT INTO `district` VALUES ('1614', '芦溪', '246', 'l', 'lx', 'luxi', '', '县', '360323', '0799', '5');
INSERT INTO `district` VALUES ('1615', '庐山', '247', 'l', 'ls', 'lushan', '', '区', '360402', '0792', '1');
INSERT INTO `district` VALUES ('1616', '浔阳', '247', 'x', 'xy', 'xunyang', '', '区', '360403', '0792', '2');
INSERT INTO `district` VALUES ('1617', '九江', '247', 'j', 'jj', 'jiujiang', '', '县', '360421', '0792', '3');
INSERT INTO `district` VALUES ('1618', '武宁', '247', 'w', 'wn', 'wuning', '', '县', '360423', '0792', '4');
INSERT INTO `district` VALUES ('1619', '修水', '247', 'x', 'xs', 'xiushui', '', '县', '360424', '0792', '5');
INSERT INTO `district` VALUES ('1620', '永修', '247', 'y', 'yx', 'yongxiu', '', '县', '360425', '0792', '6');
INSERT INTO `district` VALUES ('1621', '德安', '247', 'd', 'da', 'dean', '', '县', '360426', '0792', '7');
INSERT INTO `district` VALUES ('1622', '星子', '247', 'x', 'xz', 'xingzi', '', '县', '360427', '0792', '8');
INSERT INTO `district` VALUES ('1623', '都昌', '247', 'd', 'dc', 'douchang', '', '县', '360428', '0792', '9');
INSERT INTO `district` VALUES ('1624', '湖口', '247', 'h', 'hk', 'hukou', '', '县', '360429', '0792', '10');
INSERT INTO `district` VALUES ('1625', '彭泽', '247', 'p', 'pz', 'pengze', '', '县', '360430', '0792', '11');
INSERT INTO `district` VALUES ('1626', '瑞昌', '247', 'r', 'rc', 'ruichang', '', '市', '360481', '0792', '12');
INSERT INTO `district` VALUES ('1627', '共青城', '247', 'g', 'gqc', 'gongqingcheng', '', '市', '360482', '0792', '13');
INSERT INTO `district` VALUES ('1628', '渝水', '248', 'y', 'ys', 'yushui', '', '区', '360502', '0790', '1');
INSERT INTO `district` VALUES ('1629', '分宜', '248', 'f', 'fy', 'fenyi', '', '县', '360521', '0790', '2');
INSERT INTO `district` VALUES ('1630', '月湖', '249', 'y', 'yh', 'yuehu', '', '区', '360602', '0701', '1');
INSERT INTO `district` VALUES ('1631', '余江', '249', 'y', 'yj', 'yujiang', '', '县', '360622', '0701', '2');
INSERT INTO `district` VALUES ('1632', '贵溪', '249', 'g', 'gx', 'guixi', '', '市', '360681', '0701', '3');
INSERT INTO `district` VALUES ('1633', '章贡', '250', 'z', 'zg', 'zhanggong', '', '区', '360702', '0797', '1');
INSERT INTO `district` VALUES ('1634', '赣县', '250', 'g', 'gx', 'ganxian', '', '', '360721', '0797', '2');
INSERT INTO `district` VALUES ('1635', '信丰', '250', 'x', 'xf', 'xinfeng', '', '县', '360722', '0797', '3');
INSERT INTO `district` VALUES ('1636', '大余', '250', 'd', 'dy', 'dayu', '', '县', '360723', '0797', '4');
INSERT INTO `district` VALUES ('1637', '上犹', '250', 's', 'sy', 'shangyou', '', '县', '360724', '0797', '5');
INSERT INTO `district` VALUES ('1638', '崇义', '250', 'c', 'cy', 'chongyi', '', '县', '360725', '0797', '6');
INSERT INTO `district` VALUES ('1639', '安远', '250', 'a', 'ay', 'anyuan', '', '县', '360726', '0797', '7');
INSERT INTO `district` VALUES ('1640', '龙南', '250', 'l', 'ln', 'longnan', '', '县', '360727', '0797', '8');
INSERT INTO `district` VALUES ('1641', '定南', '250', 'd', 'dn', 'dingnan', '', '县', '360728', '0797', '9');
INSERT INTO `district` VALUES ('1642', '全南', '250', 'q', 'qn', 'quannan', '', '县', '360729', '0797', '10');
INSERT INTO `district` VALUES ('1643', '宁都', '250', 'n', 'nd', 'ningdou', '', '县', '360730', '0797', '11');
INSERT INTO `district` VALUES ('1644', '于都', '250', 'y', 'yd', 'yudou', '', '县', '360731', '0797', '12');
INSERT INTO `district` VALUES ('1645', '兴国', '250', 'x', 'xg', 'xingguo', '', '县', '360732', '0797', '13');
INSERT INTO `district` VALUES ('1646', '会昌', '250', 'h', 'hc', 'huichang', '', '县', '360733', '0797', '14');
INSERT INTO `district` VALUES ('1647', '寻乌', '250', 'x', 'xw', 'xunwu', '', '县', '360734', '0797', '15');
INSERT INTO `district` VALUES ('1648', '石城', '250', 's', 'sc', 'shicheng', '', '县', '360735', '0797', '16');
INSERT INTO `district` VALUES ('1649', '瑞金', '250', 'r', 'rj', 'ruijin', '', '市', '360781', '0797', '17');
INSERT INTO `district` VALUES ('1650', '南康', '250', 'n', 'nk', 'nankang', '', '区', '360782', '0797', '18');
INSERT INTO `district` VALUES ('1651', '吉州', '251', 'j', 'jz', 'jizhou', '', '区', '360802', '0796', '1');
INSERT INTO `district` VALUES ('1652', '青原', '251', 'q', 'qy', 'qingyuan', '', '区', '360803', '0796', '2');
INSERT INTO `district` VALUES ('1653', '吉安', '251', 'j', 'ja', 'jian', '', '县', '360821', '0796', '3');
INSERT INTO `district` VALUES ('1654', '吉水', '251', 'j', 'js', 'jishui', '', '县', '360822', '0796', '4');
INSERT INTO `district` VALUES ('1655', '峡江', '251', 'x', 'xj', 'xiajiang', '', '县', '360823', '0796', '5');
INSERT INTO `district` VALUES ('1656', '新干', '251', 'x', 'xg', 'xingan', '', '县', '360824', '0796', '6');
INSERT INTO `district` VALUES ('1657', '永丰', '251', 'y', 'yf', 'yongfeng', '', '县', '360825', '0796', '7');
INSERT INTO `district` VALUES ('1658', '泰和', '251', 't', 'th', 'taihe', '', '县', '360826', '0796', '8');
INSERT INTO `district` VALUES ('1659', '遂川', '251', 's', 'sc', 'suichuan', '', '县', '360827', '0796', '9');
INSERT INTO `district` VALUES ('1660', '万安', '251', 'w', 'wa', 'wanan', '', '县', '360828', '0796', '10');
INSERT INTO `district` VALUES ('1661', '安福', '251', 'a', 'af', 'anfu', '', '县', '360829', '0796', '11');
INSERT INTO `district` VALUES ('1662', '永新', '251', 'y', 'yx', 'yongxin', '', '县', '360830', '0796', '12');
INSERT INTO `district` VALUES ('1663', '井冈山', '251', 'j', 'jgs', 'jinggangshan', '', '市', '360881', '0796', '13');
INSERT INTO `district` VALUES ('1664', '袁州', '252', 'y', 'yz', 'yuanzhou', '', '区', '360902', '0795', '1');
INSERT INTO `district` VALUES ('1665', '奉新', '252', 'f', 'fx', 'fengxin', '', '县', '360921', '0795', '2');
INSERT INTO `district` VALUES ('1666', '万载', '252', 'w', 'wz', 'wanzai', '', '县', '360922', '0795', '3');
INSERT INTO `district` VALUES ('1667', '上高', '252', 's', 'sg', 'shanggao', '', '县', '360923', '0795', '4');
INSERT INTO `district` VALUES ('1668', '宜丰', '252', 'y', 'yf', 'yifeng', '', '县', '360924', '0795', '5');
INSERT INTO `district` VALUES ('1669', '靖安', '252', 'j', 'ja', 'jingan', '', '县', '360925', '0795', '6');
INSERT INTO `district` VALUES ('1670', '铜鼓', '252', 't', 'tg', 'tonggu', '', '县', '360926', '0795', '7');
INSERT INTO `district` VALUES ('1671', '丰城', '252', 'f', 'fc', 'fengcheng', '', '市', '360981', '0795', '8');
INSERT INTO `district` VALUES ('1672', '樟树', '252', 'z', 'zs', 'zhangshu', '', '市', '360982', '0795', '9');
INSERT INTO `district` VALUES ('1673', '高安', '252', 'g', 'ga', 'gaoan', '', '市', '360983', '0795', '10');
INSERT INTO `district` VALUES ('1674', '临川', '253', 'l', 'lc', 'linchuan', '', '区', '361002', '0794', '1');
INSERT INTO `district` VALUES ('1675', '南城', '253', 'n', 'nc', 'nancheng', '', '县', '361021', '0794', '2');
INSERT INTO `district` VALUES ('1676', '黎川', '253', 'l', 'lc', 'lichuan', '', '县', '361022', '0794', '3');
INSERT INTO `district` VALUES ('1677', '南丰', '253', 'n', 'nf', 'nanfeng', '', '县', '361023', '0794', '4');
INSERT INTO `district` VALUES ('1678', '崇仁', '253', 'c', 'cr', 'chongren', '', '县', '361024', '0794', '5');
INSERT INTO `district` VALUES ('1679', '乐安', '253', 'l', 'la', 'lean', '', '县', '361025', '0794', '6');
INSERT INTO `district` VALUES ('1680', '宜黄', '253', 'y', 'yh', 'yihuang', '', '县', '361026', '0794', '7');
INSERT INTO `district` VALUES ('1681', '金溪', '253', 'j', 'jx', 'jinxi', '', '县', '361027', '0794', '8');
INSERT INTO `district` VALUES ('1682', '资溪', '253', 'z', 'zx', 'zixi', '', '县', '361028', '0794', '9');
INSERT INTO `district` VALUES ('1683', '东乡', '253', 'd', 'dx', 'dongxiang', '', '县', '361029', '0794', '10');
INSERT INTO `district` VALUES ('1684', '广昌', '253', 'g', 'gc', 'guangchang', '', '县', '361030', '0794', '11');
INSERT INTO `district` VALUES ('1685', '信州', '254', 'x', 'xz', 'xinzhou', '', '区', '361102', '0793', '1');
INSERT INTO `district` VALUES ('1686', '上饶', '254', 's', 'sr', 'shangrao', '', '县', '361121', '0793', '3');
INSERT INTO `district` VALUES ('1687', '广丰', '254', 'g', 'gf', 'guangfeng', '', '区', '361122', '0793', '2');
INSERT INTO `district` VALUES ('1688', '玉山', '254', 'y', 'ys', 'yushan', '', '县', '361123', '0793', '4');
INSERT INTO `district` VALUES ('1689', '铅山', '254', 'q', 'qs', 'qianshan', '', '县', '361124', '0793', '5');
INSERT INTO `district` VALUES ('1690', '横峰', '254', 'h', 'hf', 'hengfeng', '', '县', '361125', '0793', '6');
INSERT INTO `district` VALUES ('1691', '弋阳', '254', 'y', 'yy', 'yiyang', '', '县', '361126', '0793', '7');
INSERT INTO `district` VALUES ('1692', '余干', '254', 'y', 'yg', 'yugan', '', '县', '361127', '0793', '8');
INSERT INTO `district` VALUES ('1693', '鄱阳', '254', 'p', 'py', 'poyang', '', '县', '361128', '0793', '9');
INSERT INTO `district` VALUES ('1694', '万年', '254', 'w', 'wn', 'wannian', '', '县', '361129', '0793', '10');
INSERT INTO `district` VALUES ('1695', '婺源', '254', 'w', 'wy', 'wuyuan', '', '县', '361130', '0793', '11');
INSERT INTO `district` VALUES ('1696', '德兴', '254', 'd', 'dx', 'dexing', '', '市', '361181', '0793', '12');
INSERT INTO `district` VALUES ('1697', '历下', '255', 'l', 'lx', 'lixia', '', '区', '370102', '0531', '1');
INSERT INTO `district` VALUES ('1698', '市中', '255', 's', 'sz', 'shizhong', '', '区', '370103', '0531', '2');
INSERT INTO `district` VALUES ('1699', '槐荫', '255', 'h', 'hy', 'huaiyin', '', '区', '370104', '0531', '3');
INSERT INTO `district` VALUES ('1700', '天桥', '255', 't', 'tq', 'tianqiao', '', '区', '370105', '0531', '4');
INSERT INTO `district` VALUES ('1701', '历城', '255', 'l', 'lc', 'licheng', '', '区', '370112', '0531', '5');
INSERT INTO `district` VALUES ('1702', '长清', '255', 'z', 'zq', 'zhangqing', '', '区', '370113', '0531', '6');
INSERT INTO `district` VALUES ('1703', '平阴', '255', 'p', 'py', 'pingyin', '', '县', '370124', '0531', '7');
INSERT INTO `district` VALUES ('1704', '济阳', '255', 'j', 'jy', 'jiyang', '', '县', '370125', '0531', '8');
INSERT INTO `district` VALUES ('1705', '商河', '255', 's', 'sh', 'shanghe', '', '县', '370126', '0531', '9');
INSERT INTO `district` VALUES ('1706', '章丘', '255', 'z', 'zq', 'zhangqiu', '', '市', '370181', '0531', '10');
INSERT INTO `district` VALUES ('1707', '市南', '256', 's', 'sn', 'shinan', '', '区', '370202', '0532', '1');
INSERT INTO `district` VALUES ('1708', '市北', '256', 's', 'sb', 'shibei', '', '区', '370203', '0532', '2');
INSERT INTO `district` VALUES ('1709', '黄岛', '256', 'h', 'hd', 'huangdao', '', '区', '370211', '0532', '3');
INSERT INTO `district` VALUES ('1710', '崂山', '256', 'l', 'ls', 'laoshan', '', '区', '370212', '0532', '4');
INSERT INTO `district` VALUES ('1711', '李沧', '256', 'l', 'lc', 'licang', '', '区', '370213', '0532', '5');
INSERT INTO `district` VALUES ('1712', '城阳', '256', 'c', 'cy', 'chengyang', '', '区', '370214', '0532', '6');
INSERT INTO `district` VALUES ('1713', '胶州', '256', 'j', 'jz', 'jiaozhou', '', '市', '370281', '0532', '7');
INSERT INTO `district` VALUES ('1714', '即墨', '256', 'j', 'jm', 'jimo', '', '市', '370282', '0532', '8');
INSERT INTO `district` VALUES ('1715', '平度', '256', 'p', 'pd', 'pingdu', '', '市', '370283', '0532', '9');
INSERT INTO `district` VALUES ('1716', '莱西', '256', 'l', 'lx', 'laixi', '', '市', '370285', '0532', '10');
INSERT INTO `district` VALUES ('1717', '淄川', '257', 'z', 'zc', 'zichuan', '', '区', '370302', '0533', '1');
INSERT INTO `district` VALUES ('1718', '张店', '257', 'z', 'zd', 'zhangdian', '', '区', '370303', '0533', '2');
INSERT INTO `district` VALUES ('1719', '博山', '257', 'b', 'bs', 'boshan', '', '区', '370304', '0533', '3');
INSERT INTO `district` VALUES ('1720', '临淄', '257', 'l', 'lz', 'linzi', '', '区', '370305', '0533', '4');
INSERT INTO `district` VALUES ('1721', '周村', '257', 'z', 'zc', 'zhoucun', '', '区', '370306', '0533', '5');
INSERT INTO `district` VALUES ('1722', '桓台', '257', 'h', 'ht', 'huantai', '', '县', '370321', '0533', '6');
INSERT INTO `district` VALUES ('1723', '高青', '257', 'g', 'gq', 'gaoqing', '', '县', '370322', '0533', '7');
INSERT INTO `district` VALUES ('1724', '沂源', '257', 'y', 'yy', 'yiyuan', '', '县', '370323', '0533', '8');
INSERT INTO `district` VALUES ('1725', '市中', '258', 's', 'sz', 'shizhong', '', '区', '370402', '0632', '1');
INSERT INTO `district` VALUES ('1726', '薛城', '258', 'x', 'xc', 'xuecheng', '', '区', '370403', '0632', '2');
INSERT INTO `district` VALUES ('1727', '峄城', '258', 'y', 'yc', 'yicheng', '', '区', '370404', '0632', '3');
INSERT INTO `district` VALUES ('1728', '台儿庄', '258', 't', 'tez', 'taierzhuang', '', '区', '370405', '0632', '4');
INSERT INTO `district` VALUES ('1729', '山亭', '258', 's', 'st', 'shanting', '', '区', '370406', '0632', '5');
INSERT INTO `district` VALUES ('1730', '滕州', '258', 't', 'tz', 'tengzhou', '', '市', '370481', '0632', '6');
INSERT INTO `district` VALUES ('1731', '东营', '259', 'd', 'dy', 'dongying', '', '区', '370502', '0546', '1');
INSERT INTO `district` VALUES ('1732', '河口', '259', 'h', 'hk', 'hekou', '', '区', '370503', '0546', '2');
INSERT INTO `district` VALUES ('1733', '垦利', '259', 'k', 'kl', 'kenli', '', '县', '370521', '0546', '3');
INSERT INTO `district` VALUES ('1734', '利津', '259', 'l', 'lj', 'lijin', '', '县', '370522', '0546', '4');
INSERT INTO `district` VALUES ('1735', '广饶', '259', 'g', 'gr', 'guangrao', '', '县', '370523', '0546', '5');
INSERT INTO `district` VALUES ('1736', '芝罘', '260', 'z', 'zf', 'zhifu', '', '区', '370602', '0535', '1');
INSERT INTO `district` VALUES ('1737', '福山', '260', 'f', 'fs', 'fushan', '', '区', '370611', '0535', '2');
INSERT INTO `district` VALUES ('1738', '牟平', '260', 'm', 'mp', 'mouping', '', '区', '370612', '0535', '3');
INSERT INTO `district` VALUES ('1739', '莱山', '260', 'l', 'ls', 'laishan', '', '区', '370613', '0535', '4');
INSERT INTO `district` VALUES ('1740', '长岛', '260', 'z', 'zd', 'zhangdao', '', '县', '370634', '0535', '5');
INSERT INTO `district` VALUES ('1741', '龙口', '260', 'l', 'lk', 'longkou', '', '市', '370681', '0535', '6');
INSERT INTO `district` VALUES ('1742', '莱阳', '260', 'l', 'ly', 'laiyang', '', '市', '370682', '0535', '7');
INSERT INTO `district` VALUES ('1743', '莱州', '260', 'l', 'lz', 'laizhou', '', '市', '370683', '0535', '8');
INSERT INTO `district` VALUES ('1744', '蓬莱', '260', 'p', 'pl', 'penglai', '', '市', '370684', '0535', '9');
INSERT INTO `district` VALUES ('1745', '招远', '260', 'z', 'zy', 'zhaoyuan', '', '市', '370685', '0535', '10');
INSERT INTO `district` VALUES ('1746', '栖霞', '260', 'q', 'qx', 'qixia', '', '市', '370686', '0535', '11');
INSERT INTO `district` VALUES ('1747', '海阳', '260', 'h', 'hy', 'haiyang', '', '市', '370687', '0535', '12');
INSERT INTO `district` VALUES ('1748', '潍城', '261', 'w', 'wc', 'weicheng', '', '区', '370702', '0536', '1');
INSERT INTO `district` VALUES ('1749', '寒亭', '261', 'h', 'ht', 'hanting', '', '区', '370703', '0536', '2');
INSERT INTO `district` VALUES ('1750', '坊子', '261', 'f', 'fz', 'fangzi', '', '区', '370704', '0536', '3');
INSERT INTO `district` VALUES ('1751', '奎文', '261', 'k', 'kw', 'kuiwen', '', '区', '370705', '0536', '4');
INSERT INTO `district` VALUES ('1752', '临朐', '261', 'l', 'lq', 'linqu', '', '县', '370724', '0536', '5');
INSERT INTO `district` VALUES ('1753', '昌乐', '261', 'c', 'cl', 'changle', '', '县', '370725', '0536', '6');
INSERT INTO `district` VALUES ('1754', '青州', '261', 'q', 'qz', 'qingzhou', '', '市', '370781', '0536', '7');
INSERT INTO `district` VALUES ('1755', '诸城', '261', 'z', 'zc', 'zhucheng', '', '市', '370782', '0536', '8');
INSERT INTO `district` VALUES ('1756', '寿光', '261', 's', 'sg', 'shouguang', '', '市', '370783', '0536', '9');
INSERT INTO `district` VALUES ('1757', '安丘', '261', 'a', 'aq', 'anqiu', '', '市', '370784', '0536', '10');
INSERT INTO `district` VALUES ('1758', '高密', '261', 'g', 'gm', 'gaomi', '', '市', '370785', '0536', '11');
INSERT INTO `district` VALUES ('1759', '昌邑', '261', 'c', 'cy', 'changyi', '', '市', '370786', '0536', '12');
INSERT INTO `district` VALUES ('1761', '任城', '262', 'r', 'rc', 'rencheng', '', '区', '370811', '0537', '1');
INSERT INTO `district` VALUES ('1762', '微山', '262', 'w', 'ws', 'weishan', '', '县', '370826', '0537', '3');
INSERT INTO `district` VALUES ('1763', '鱼台', '262', 'y', 'yt', 'yutai', '', '县', '370827', '0537', '4');
INSERT INTO `district` VALUES ('1764', '金乡', '262', 'j', 'jx', 'jinxiang', '', '县', '370828', '0537', '5');
INSERT INTO `district` VALUES ('1765', '嘉祥', '262', 'j', 'jx', 'jiaxiang', '', '县', '370829', '0537', '6');
INSERT INTO `district` VALUES ('1766', '汶上', '262', 'w', 'ws', 'wenshang', '', '县', '370830', '0537', '7');
INSERT INTO `district` VALUES ('1767', '泗水', '262', 's', 'ss', 'sishui', '', '县', '370831', '0537', '8');
INSERT INTO `district` VALUES ('1768', '梁山', '262', 'l', 'ls', 'liangshan', '', '县', '370832', '0537', '9');
INSERT INTO `district` VALUES ('1769', '曲阜', '262', 'q', 'qf', 'qufu', '', '市', '370881', '0537', '10');
INSERT INTO `district` VALUES ('1770', '兖州', '262', 'y', 'yz', 'yanzhou', '', '区', '370812', '0537', '2');
INSERT INTO `district` VALUES ('1771', '邹城', '262', 'z', 'zc', 'zoucheng', '', '市', '370883', '0537', '11');
INSERT INTO `district` VALUES ('1772', '泰山', '263', 't', 'ts', 'taishan', '', '区', '370902', '0538', '1');
INSERT INTO `district` VALUES ('1773', '岱岳', '263', 'd', 'dy', 'daiyue', '', '区', '370911', '0538', '2');
INSERT INTO `district` VALUES ('1774', '宁阳', '263', 'n', 'ny', 'ningyang', '', '县', '370921', '0538', '3');
INSERT INTO `district` VALUES ('1775', '东平', '263', 'd', 'dp', 'dongping', '', '县', '370923', '0538', '4');
INSERT INTO `district` VALUES ('1776', '新泰', '263', 'x', 'xt', 'xintai', '', '市', '370982', '0538', '5');
INSERT INTO `district` VALUES ('1777', '肥城', '263', 'f', 'fc', 'feicheng', '', '市', '370983', '0538', '6');
INSERT INTO `district` VALUES ('1778', '环翠', '264', 'h', 'hc', 'huancui', '', '区', '371002', '0631', '1');
INSERT INTO `district` VALUES ('1779', '文登', '264', 'w', 'wd', 'wendeng', '', '区', '371003', '0631', '2');
INSERT INTO `district` VALUES ('1780', '荣成', '264', 'r', 'rc', 'rongcheng', '', '市', '371082', '0631', '3');
INSERT INTO `district` VALUES ('1781', '乳山', '264', 'r', 'rs', 'rushan', '', '市', '371083', '0631', '4');
INSERT INTO `district` VALUES ('1782', '东港', '265', 'd', 'dg', 'donggang', '', '区', '371102', '0633', '1');
INSERT INTO `district` VALUES ('1783', '岚山', '265', 'l', 'ls', 'lanshan', '', '区', '371103', '0633', '2');
INSERT INTO `district` VALUES ('1784', '五莲', '265', 'w', 'wl', 'wulian', '', '县', '371121', '0633', '3');
INSERT INTO `district` VALUES ('1785', '莒县', '265', 'j', 'jx', 'juxian', '', '', '371122', '0633', '4');
INSERT INTO `district` VALUES ('1786', '莱城', '266', 'l', 'lc', 'laicheng', '', '区', '371202', '0634', '1');
INSERT INTO `district` VALUES ('1787', '钢城', '266', 'g', 'gc', 'gangcheng', '', '区', '371203', '0634', '2');
INSERT INTO `district` VALUES ('1788', '兰山', '267', 'l', 'ls', 'lanshan', '', '区', '371302', '0539', '1');
INSERT INTO `district` VALUES ('1789', '罗庄', '267', 'l', 'lz', 'luozhuang', '', '区', '371311', '0539', '2');
INSERT INTO `district` VALUES ('1790', '河东', '267', 'h', 'hd', 'hedong', '', '区', '371312', '0539', '3');
INSERT INTO `district` VALUES ('1791', '沂南', '267', 'y', 'yn', 'yinan', '', '县', '371321', '0539', '4');
INSERT INTO `district` VALUES ('1792', '郯城', '267', 't', 'tc', 'tancheng', '', '县', '371322', '0539', '5');
INSERT INTO `district` VALUES ('1793', '沂水', '267', 'y', 'ys', 'yishui', '', '县', '371323', '0539', '6');
INSERT INTO `district` VALUES ('1794', '兰陵', '267', 'l', 'll', 'lanling', '', '县', '371324', '0539', '7');
INSERT INTO `district` VALUES ('1795', '费县', '267', 'f', 'fx', 'feixian', '', '', '371325', '0539', '8');
INSERT INTO `district` VALUES ('1796', '平邑', '267', 'p', 'py', 'pingyi', '', '县', '371326', '0539', '9');
INSERT INTO `district` VALUES ('1797', '莒南', '267', 'j', 'jn', 'junan', '', '县', '371327', '0539', '10');
INSERT INTO `district` VALUES ('1798', '蒙阴', '267', 'm', 'my', 'mengyin', '', '县', '371328', '0539', '11');
INSERT INTO `district` VALUES ('1799', '临沭', '267', 'l', 'ls', 'linshu', '', '县', '371329', '0539', '12');
INSERT INTO `district` VALUES ('1800', '德城', '268', 'd', 'dc', 'decheng', '', '区', '371402', '0534', '1');
INSERT INTO `district` VALUES ('1801', '陵城', '268', 'l', 'lc', 'lingcheng', '', '区', '371403', '0534', '2');
INSERT INTO `district` VALUES ('1802', '宁津', '268', 'n', 'nj', 'ningjin', '', '县', '371422', '0534', '3');
INSERT INTO `district` VALUES ('1803', '庆云', '268', 'q', 'qy', 'qingyun', '', '县', '371423', '0534', '4');
INSERT INTO `district` VALUES ('1804', '临邑', '268', 'l', 'ly', 'linyi', '', '县', '371424', '0534', '5');
INSERT INTO `district` VALUES ('1805', '齐河', '268', 'q', 'qh', 'qihe', '', '县', '371425', '0534', '6');
INSERT INTO `district` VALUES ('1806', '平原', '268', 'p', 'py', 'pingyuan', '', '县', '371426', '0534', '7');
INSERT INTO `district` VALUES ('1807', '夏津', '268', 'x', 'xj', 'xiajin', '', '县', '371427', '0534', '8');
INSERT INTO `district` VALUES ('1808', '武城', '268', 'w', 'wc', 'wucheng', '', '县', '371428', '0534', '9');
INSERT INTO `district` VALUES ('1809', '乐陵', '268', 'l', 'll', 'leling', '', '市', '371481', '0534', '10');
INSERT INTO `district` VALUES ('1810', '禹城', '268', 'y', 'yc', 'yucheng', '', '市', '371482', '0534', '11');
INSERT INTO `district` VALUES ('1811', '东昌府', '269', 'd', 'dcf', 'dongchangfu', '', '区', '371502', '0635', '1');
INSERT INTO `district` VALUES ('1812', '阳谷', '269', 'y', 'yg', 'yanggu', '', '县', '371521', '0635', '2');
INSERT INTO `district` VALUES ('1813', '莘县', '269', 'x', 'xx', 'xinxian', '', '', '371522', '0635', '3');
INSERT INTO `district` VALUES ('1814', '茌平', '269', 'c', 'cp', 'chiping', '', '县', '371523', '0635', '4');
INSERT INTO `district` VALUES ('1815', '东阿', '269', 'd', 'da', 'donga', '', '县', '371524', '0635', '5');
INSERT INTO `district` VALUES ('1816', '冠县', '269', 'g', 'gx', 'guanxian', '', '', '371525', '0635', '6');
INSERT INTO `district` VALUES ('1817', '高唐', '269', 'g', 'gt', 'gaotang', '', '县', '371526', '0635', '7');
INSERT INTO `district` VALUES ('1818', '临清', '269', 'l', 'lq', 'linqing', '', '市', '371581', '0635', '8');
INSERT INTO `district` VALUES ('1819', '滨城', '270', 'b', 'bc', 'bincheng', '', '区', '371602', '0543', '1');
INSERT INTO `district` VALUES ('1820', '惠民', '270', 'h', 'hm', 'huimin', '', '县', '371621', '0543', '2');
INSERT INTO `district` VALUES ('1821', '阳信', '270', 'y', 'yx', 'yangxin', '', '县', '371622', '0543', '3');
INSERT INTO `district` VALUES ('1822', '无棣', '270', 'w', 'wd', 'wudi', '', '县', '371623', '0543', '4');
INSERT INTO `district` VALUES ('1823', '沾化', '270', 'z', 'zh', 'zhanhua', '', '区', '371624', '0543', '5');
INSERT INTO `district` VALUES ('1824', '博兴', '270', 'b', 'bx', 'boxing', '', '县', '371625', '0543', '6');
INSERT INTO `district` VALUES ('1825', '邹平', '270', 'z', 'zp', 'zouping', '', '县', '371626', '0543', '7');
INSERT INTO `district` VALUES ('1826', '牡丹', '271', 'm', 'md', 'mudan', '', '区', '371702', '0530', '1');
INSERT INTO `district` VALUES ('1827', '曹县', '271', 'c', 'cx', 'caoxian', '', '', '371721', '0530', '2');
INSERT INTO `district` VALUES ('1828', '单县', '271', 'd', 'dx', 'danxian', '', '', '371722', '0530', '3');
INSERT INTO `district` VALUES ('1829', '成武', '271', 'c', 'cw', 'chengwu', '', '县', '371723', '0530', '4');
INSERT INTO `district` VALUES ('1830', '巨野', '271', 'j', 'jy', 'juye', '', '县', '371724', '0530', '5');
INSERT INTO `district` VALUES ('1831', '郓城', '271', 'y', 'yc', 'yuncheng', '', '县', '371725', '0530', '6');
INSERT INTO `district` VALUES ('1832', '鄄城', '271', 'j', 'jc', 'juancheng', '', '县', '371726', '0530', '7');
INSERT INTO `district` VALUES ('1833', '定陶', '271', 'd', 'dt', 'dingtao', '', '县', '371727', '0530', '8');
INSERT INTO `district` VALUES ('1834', '东明', '271', 'd', 'dm', 'dongming', '', '县', '371728', '0530', '9');
INSERT INTO `district` VALUES ('1835', '中原', '272', 'z', 'zy', 'zhongyuan', '', '区', '410102', '0371', '1');
INSERT INTO `district` VALUES ('1836', '二七', '272', 'e', 'eq', 'erqi', '', '区', '410103', '0371', '2');
INSERT INTO `district` VALUES ('1837', '管城', '272', 'g', 'gc', 'guancheng', '回族', '区', '410104', '0371', '3');
INSERT INTO `district` VALUES ('1838', '金水', '272', 'j', 'js', 'jinshui', '', '区', '410105', '0371', '4');
INSERT INTO `district` VALUES ('1839', '上街', '272', 's', 'sj', 'shangjie', '', '区', '410106', '0371', '5');
INSERT INTO `district` VALUES ('1840', '惠济', '272', 'h', 'hj', 'huiji', '', '区', '410108', '0371', '6');
INSERT INTO `district` VALUES ('1841', '中牟', '272', 'z', 'zm', 'zhongmou', '', '县', '410122', '0371', '7');
INSERT INTO `district` VALUES ('1842', '巩义', '272', 'g', 'gy', 'gongyi', '', '市', '410181', '0371', '8');
INSERT INTO `district` VALUES ('1843', '荥阳', '272', 'y', 'yy', 'yingyang', '', '市', '410182', '0371', '9');
INSERT INTO `district` VALUES ('1844', '新密', '272', 'x', 'xm', 'xinmi', '', '市', '410183', '0371', '10');
INSERT INTO `district` VALUES ('1845', '新郑', '272', 'x', 'xz', 'xinzheng', '', '市', '410184', '0371', '11');
INSERT INTO `district` VALUES ('1846', '登封', '272', 'd', 'df', 'dengfeng', '', '市', '410185', '0371', '12');
INSERT INTO `district` VALUES ('1847', '龙亭', '273', 'l', 'lt', 'longting', '', '区', '410202', '0378', '1');
INSERT INTO `district` VALUES ('1848', '顺河', '273', 's', 'sh', 'shunhe', '回族', '区', '410203', '0378', '2');
INSERT INTO `district` VALUES ('1849', '鼓楼', '273', 'g', 'gl', 'gulou', '', '区', '410204', '0378', '3');
INSERT INTO `district` VALUES ('1850', '禹王台', '273', 'y', 'ywt', 'yuwangtai', '', '区', '410205', '0378', '4');
INSERT INTO `district` VALUES ('1852', '杞县', '273', 'q', 'qx', 'qixian', '', '', '410221', '0378', '6');
INSERT INTO `district` VALUES ('1853', '通许', '273', 't', 'tx', 'tongxu', '', '县', '410222', '0378', '7');
INSERT INTO `district` VALUES ('1854', '尉氏', '273', 'w', 'ws', 'weishi', '', '县', '410223', '0378', '8');
INSERT INTO `district` VALUES ('1855', '祥符', '273', 'x', 'xf', 'kaifeng', '', '区', '410212', '0378', '5');
INSERT INTO `district` VALUES ('1856', '兰考', '273', 'l', 'lk', 'lankao', '', '县', '410225', '0378', '9');
INSERT INTO `district` VALUES ('1857', '老城', '274', 'l', 'lc', 'laocheng', '', '区', '410302', '0376', '1');
INSERT INTO `district` VALUES ('1858', '西工', '274', 'x', 'xg', 'xigong', '', '区', '410303', '0376', '2');
INSERT INTO `district` VALUES ('1859', '瀍河', '274', 'c', 'ch', 'chanhe', '回族', '区', '410304', '0376', '3');
INSERT INTO `district` VALUES ('1860', '涧西', '274', 'j', 'jx', 'jianxi', '', '区', '410305', '0376', '4');
INSERT INTO `district` VALUES ('1861', '吉利', '274', 'j', 'jl', 'jili', '', '区', '410306', '0376', '5');
INSERT INTO `district` VALUES ('1862', '洛龙', '274', 'l', 'll', 'luolong', '', '区', '410311', '0376', '6');
INSERT INTO `district` VALUES ('1863', '孟津', '274', 'm', 'mj', 'mengjin', '', '县', '410322', '0376', '7');
INSERT INTO `district` VALUES ('1864', '新安', '274', 'x', 'xa', 'xinan', '', '县', '410323', '0376', '8');
INSERT INTO `district` VALUES ('1865', '栾川', '274', 'l', 'lc', 'luanchuan', '', '县', '410324', '0376', '9');
INSERT INTO `district` VALUES ('1866', '嵩县', '274', 's', 'sx', 'songxian', '', '', '410325', '0376', '10');
INSERT INTO `district` VALUES ('1867', '汝阳', '274', 'r', 'ry', 'ruyang', '', '县', '410326', '0376', '11');
INSERT INTO `district` VALUES ('1868', '宜阳', '274', 'y', 'yy', 'yiyang', '', '县', '410327', '0376', '12');
INSERT INTO `district` VALUES ('1869', '洛宁', '274', 'l', 'ln', 'luoning', '', '县', '410328', '0376', '13');
INSERT INTO `district` VALUES ('1870', '伊川', '274', 'y', 'yc', 'yichuan', '', '县', '410329', '0376', '14');
INSERT INTO `district` VALUES ('1871', '偃师', '274', 'y', 'ys', 'yanshi', '', '市', '410381', '0376', '15');
INSERT INTO `district` VALUES ('1872', '新华', '275', 'x', 'xh', 'xinhua', '', '区', '410402', '0375', '1');
INSERT INTO `district` VALUES ('1873', '卫东', '275', 'w', 'wd', 'weidong', '', '区', '410403', '0375', '2');
INSERT INTO `district` VALUES ('1874', '石龙', '275', 's', 'sl', 'shilong', '', '区', '410404', '0375', '3');
INSERT INTO `district` VALUES ('1875', '湛河', '275', 'z', 'zh', 'zhanhe', '', '区', '410411', '0375', '4');
INSERT INTO `district` VALUES ('1876', '宝丰', '275', 'b', 'bf', 'baofeng', '', '县', '410421', '0375', '5');
INSERT INTO `district` VALUES ('1877', '叶县', '275', 'y', 'yx', 'yexian', '', '', '410422', '0375', '6');
INSERT INTO `district` VALUES ('1878', '鲁山', '275', 'l', 'ls', 'lushan', '', '县', '410423', '0375', '7');
INSERT INTO `district` VALUES ('1879', '郏县', '275', 'j', 'jx', 'jiaxian', '', '', '410425', '0375', '8');
INSERT INTO `district` VALUES ('1880', '舞钢', '275', 'w', 'wg', 'wugang', '', '市', '410481', '0375', '9');
INSERT INTO `district` VALUES ('1881', '汝州', '275', 'r', 'rz', 'ruzhou', '', '市', '410482', '0375', '10');
INSERT INTO `district` VALUES ('1882', '文峰', '276', 'w', 'wf', 'wenfeng', '', '区', '410502', '0372', '1');
INSERT INTO `district` VALUES ('1883', '北关', '276', 'b', 'bg', 'beiguan', '', '区', '410503', '0372', '2');
INSERT INTO `district` VALUES ('1884', '殷都', '276', 'y', 'yd', 'yindou', '', '区', '410505', '0372', '3');
INSERT INTO `district` VALUES ('1885', '龙安', '276', 'l', 'la', 'longan', '', '区', '410506', '0372', '4');
INSERT INTO `district` VALUES ('1886', '安阳', '276', 'a', 'ay', 'anyang', '', '县', '410522', '0372', '5');
INSERT INTO `district` VALUES ('1887', '汤阴', '276', 't', 'ty', 'tangyin', '', '县', '410523', '0372', '6');
INSERT INTO `district` VALUES ('1888', '滑县', '276', 'h', 'hx', 'huaxian', '', '', '410526', '0372', '7');
INSERT INTO `district` VALUES ('1889', '内黄', '276', 'n', 'nh', 'neihuang', '', '县', '410527', '0372', '8');
INSERT INTO `district` VALUES ('1890', '林州', '276', 'l', 'lz', 'linzhou', '', '市', '410581', '0372', '9');
INSERT INTO `district` VALUES ('1891', '鹤山', '277', 'h', 'hs', 'heshan', '', '区', '410602', '0392', '1');
INSERT INTO `district` VALUES ('1892', '山城', '277', 's', 'sc', 'shancheng', '', '区', '410603', '0392', '2');
INSERT INTO `district` VALUES ('1893', '淇滨', '277', 'q', 'qb', 'qibin', '', '区', '410611', '0392', '3');
INSERT INTO `district` VALUES ('1894', '浚县', '277', 'j', 'jx', 'junxian', '', '', '410621', '0392', '4');
INSERT INTO `district` VALUES ('1895', '淇县', '277', 'q', 'qx', 'qixian', '', '', '410622', '0392', '5');
INSERT INTO `district` VALUES ('1896', '红旗', '278', 'h', 'hq', 'hongqi', '', '区', '410702', '0373', '1');
INSERT INTO `district` VALUES ('1897', '卫滨', '278', 'w', 'wb', 'weibin', '', '区', '410703', '0373', '2');
INSERT INTO `district` VALUES ('1898', '凤泉', '278', 'f', 'fq', 'fengquan', '', '区', '410704', '0373', '3');
INSERT INTO `district` VALUES ('1899', '牧野', '278', 'm', 'my', 'muye', '', '区', '410711', '0373', '4');
INSERT INTO `district` VALUES ('1900', '新乡', '278', 'x', 'xx', 'xinxiang', '', '县', '410721', '0373', '5');
INSERT INTO `district` VALUES ('1901', '获嘉', '278', 'h', 'hj', 'huojia', '', '县', '410724', '0373', '6');
INSERT INTO `district` VALUES ('1902', '原阳', '278', 'y', 'yy', 'yuanyang', '', '县', '410725', '0373', '7');
INSERT INTO `district` VALUES ('1903', '延津', '278', 'y', 'yj', 'yanjin', '', '县', '410726', '0373', '8');
INSERT INTO `district` VALUES ('1904', '封丘', '278', 'f', 'fq', 'fengqiu', '', '县', '410727', '0373', '9');
INSERT INTO `district` VALUES ('1905', '长垣', '278', 'z', 'zy', 'zhangyuan', '', '县', '410728', '0373', '10');
INSERT INTO `district` VALUES ('1906', '卫辉', '278', 'w', 'wh', 'weihui', '', '市', '410781', '0373', '11');
INSERT INTO `district` VALUES ('1907', '辉县', '278', 'h', 'hx', 'huixian', '', '市', '410782', '0373', '12');
INSERT INTO `district` VALUES ('1908', '解放', '279', 'j', 'jf', 'jiefang', '', '区', '410802', '0391', '1');
INSERT INTO `district` VALUES ('1909', '中站', '279', 'z', 'zz', 'zhongzhan', '', '区', '410803', '0391', '2');
INSERT INTO `district` VALUES ('1910', '马村', '279', 'm', 'mc', 'macun', '', '区', '410804', '0391', '3');
INSERT INTO `district` VALUES ('1911', '山阳', '279', 's', 'sy', 'shanyang', '', '区', '410811', '0391', '4');
INSERT INTO `district` VALUES ('1912', '修武', '279', 'x', 'xw', 'xiuwu', '', '县', '410821', '0391', '5');
INSERT INTO `district` VALUES ('1913', '博爱', '279', 'b', 'ba', 'boai', '', '县', '410822', '0391', '6');
INSERT INTO `district` VALUES ('1914', '武陟', '279', 'w', 'wz', 'wuzhi', '', '县', '410823', '0391', '7');
INSERT INTO `district` VALUES ('1915', '温县', '279', 'w', 'wx', 'wenxian', '', '', '410825', '0391', '8');
INSERT INTO `district` VALUES ('1916', '沁阳', '279', 'q', 'qy', 'qinyang', '', '市', '410882', '0391', '9');
INSERT INTO `district` VALUES ('1917', '孟州', '279', 'm', 'mz', 'mengzhou', '', '市', '410883', '0391', '10');
INSERT INTO `district` VALUES ('1918', '华龙', '280', 'h', 'hl', 'hualong', '', '区', '410902', '', '1');
INSERT INTO `district` VALUES ('1919', '清丰', '280', 'q', 'qf', 'qingfeng', '', '县', '410922', '', '2');
INSERT INTO `district` VALUES ('1920', '南乐', '280', 'n', 'nl', 'nanle', '', '县', '410923', '', '3');
INSERT INTO `district` VALUES ('1921', '范县', '280', 'f', 'fx', 'fanxian', '', '', '410926', '', '4');
INSERT INTO `district` VALUES ('1922', '台前', '280', 't', 'tq', 'taiqian', '', '县', '410927', '', '5');
INSERT INTO `district` VALUES ('1923', '濮阳', '280', 'p', 'py', 'puyang', '', '县', '410928', '', '6');
INSERT INTO `district` VALUES ('1924', '魏都', '281', 'w', 'wd', 'weidou', '', '区', '411002', '0374', '1');
INSERT INTO `district` VALUES ('1925', '许昌', '281', 'x', 'xc', 'xuchang', '', '县', '411023', '0374', '2');
INSERT INTO `district` VALUES ('1926', '鄢陵', '281', 'y', 'yl', 'yanling', '', '县', '411024', '0374', '3');
INSERT INTO `district` VALUES ('1927', '襄城', '281', 'x', 'xc', 'xiangcheng', '', '县', '411025', '0374', '4');
INSERT INTO `district` VALUES ('1928', '禹州', '281', 'y', 'yz', 'yuzhou', '', '市', '411081', '0374', '5');
INSERT INTO `district` VALUES ('1929', '长葛', '281', 'z', 'zg', 'zhangge', '', '市', '411082', '0374', '6');
INSERT INTO `district` VALUES ('1930', '源汇', '282', 'y', 'yh', 'yuanhui', '', '区', '411102', '0395', '1');
INSERT INTO `district` VALUES ('1931', '郾城', '282', 'y', 'yc', 'yancheng', '', '区', '411103', '0395', '2');
INSERT INTO `district` VALUES ('1932', '召陵', '282', 'z', 'zl', 'zhaoling', '', '区', '411104', '0395', '3');
INSERT INTO `district` VALUES ('1933', '舞阳', '282', 'w', 'wy', 'wuyang', '', '县', '411121', '0395', '4');
INSERT INTO `district` VALUES ('1934', '临颍', '282', 'l', 'ly', 'linying', '', '县', '411122', '0395', '5');
INSERT INTO `district` VALUES ('1935', '湖滨', '283', 'h', 'hb', 'hubin', '', '区', '411202', '0398', '1');
INSERT INTO `district` VALUES ('1936', '渑池', '283', 'm', 'mc', 'mianchi', '', '县', '411221', '0398', '3');
INSERT INTO `district` VALUES ('1937', '陕州', '283', 's', 'sz', 'shanzhou', '', '区', '411222', '0398', '2');
INSERT INTO `district` VALUES ('1938', '卢氏', '283', 'l', 'ls', 'lushi', '', '县', '411224', '0398', '4');
INSERT INTO `district` VALUES ('1939', '义马', '283', 'y', 'ym', 'yima', '', '市', '411281', '0398', '5');
INSERT INTO `district` VALUES ('1940', '灵宝', '283', 'l', 'lb', 'lingbao', '', '市', '411282', '0398', '6');
INSERT INTO `district` VALUES ('1941', '宛城', '284', 'w', 'wc', 'wancheng', '', '区', '411302', '0377', '1');
INSERT INTO `district` VALUES ('1942', '卧龙', '284', 'w', 'wl', 'wolong', '', '区', '411303', '0377', '2');
INSERT INTO `district` VALUES ('1943', '南召', '284', 'n', 'nz', 'nanzhao', '', '县', '411321', '0377', '3');
INSERT INTO `district` VALUES ('1944', '方城', '284', 'f', 'fc', 'fangcheng', '', '县', '411322', '0377', '4');
INSERT INTO `district` VALUES ('1945', '西峡', '284', 'x', 'xx', 'xixia', '', '县', '411323', '0377', '5');
INSERT INTO `district` VALUES ('1946', '镇平', '284', 'z', 'zp', 'zhenping', '', '县', '411324', '0377', '6');
INSERT INTO `district` VALUES ('1947', '内乡', '284', 'n', 'nx', 'neixiang', '', '县', '411325', '0377', '7');
INSERT INTO `district` VALUES ('1948', '淅川', '284', 'x', 'xc', 'xichuan', '', '县', '411326', '0377', '8');
INSERT INTO `district` VALUES ('1949', '社旗', '284', 's', 'sq', 'sheqi', '', '县', '411327', '0377', '9');
INSERT INTO `district` VALUES ('1950', '唐河', '284', 't', 'th', 'tanghe', '', '县', '411328', '0377', '10');
INSERT INTO `district` VALUES ('1951', '新野', '284', 'x', 'xy', 'xinye', '', '县', '411329', '0377', '11');
INSERT INTO `district` VALUES ('1952', '桐柏', '284', 't', 'tb', 'tongbo', '', '县', '411330', '0377', '12');
INSERT INTO `district` VALUES ('1953', '邓州', '284', 'd', 'dz', 'dengzhou', '', '市', '411381', '0377', '13');
INSERT INTO `district` VALUES ('1954', '粱园', '285', 'l', 'ly', 'liangyuan', '', '区', '411402', '0370', '1');
INSERT INTO `district` VALUES ('1955', '睢阳', '285', 's', 'sy', 'suiyang', '', '区', '411403', '0370', '2');
INSERT INTO `district` VALUES ('1956', '民权', '285', 'm', 'mq', 'minquan', '', '县', '411421', '0370', '3');
INSERT INTO `district` VALUES ('1957', '睢县', '285', 's', 'sx', 'suixian', '', '', '411422', '0370', '4');
INSERT INTO `district` VALUES ('1958', '宁陵', '285', 'n', 'nl', 'ningling', '', '县', '411423', '0370', '5');
INSERT INTO `district` VALUES ('1959', '柘城', '285', 'z', 'zc', 'zhecheng', '', '县', '411424', '0370', '6');
INSERT INTO `district` VALUES ('1960', '虞城', '285', 'y', 'yc', 'yucheng', '', '县', '411425', '0370', '7');
INSERT INTO `district` VALUES ('1961', '夏邑', '285', 'x', 'xy', 'xiayi', '', '县', '411426', '0370', '8');
INSERT INTO `district` VALUES ('1962', '永城', '285', 'y', 'yc', 'yongcheng', '', '市', '411481', '0370', '9');
INSERT INTO `district` VALUES ('1963', '浉河', '286', 's', 'sh', 'shihe', '', '区', '411502', '0376', '1');
INSERT INTO `district` VALUES ('1964', '平桥', '286', 'p', 'pq', 'pingqiao', '', '区', '411503', '0376', '2');
INSERT INTO `district` VALUES ('1965', '罗山', '286', 'l', 'ls', 'luoshan', '', '县', '411521', '0376', '3');
INSERT INTO `district` VALUES ('1966', '光山', '286', 'g', 'gs', 'guangshan', '', '县', '411522', '0376', '4');
INSERT INTO `district` VALUES ('1967', '新县', '286', 'x', 'xx', 'xinxian', '', '', '411523', '0376', '5');
INSERT INTO `district` VALUES ('1968', '商城', '286', 's', 'sc', 'shangcheng', '', '县', '411524', '0376', '6');
INSERT INTO `district` VALUES ('1969', '固始', '286', 'g', 'gs', 'gushi', '', '县', '411525', '0376', '7');
INSERT INTO `district` VALUES ('1970', '潢川', '286', 'h', 'hc', 'huangchuan', '', '县', '411526', '0376', '8');
INSERT INTO `district` VALUES ('1971', '淮滨', '286', 'h', 'hb', 'huaibin', '', '县', '411527', '0376', '9');
INSERT INTO `district` VALUES ('1972', '息县', '286', 'x', 'xx', 'xixian', '', '', '411528', '0376', '10');
INSERT INTO `district` VALUES ('1973', '川汇', '287', 'c', 'ch', 'chuanhui', '', '区', '411602', '0394', '1');
INSERT INTO `district` VALUES ('1974', '扶沟', '287', 'f', 'fg', 'fugou', '', '县', '411621', '0394', '2');
INSERT INTO `district` VALUES ('1975', '西华', '287', 'x', 'xh', 'xihua', '', '县', '411622', '0394', '3');
INSERT INTO `district` VALUES ('1976', '商水', '287', 's', 'ss', 'shangshui', '', '县', '411623', '0394', '4');
INSERT INTO `district` VALUES ('1977', '沈丘', '287', 's', 'sq', 'shenqiu', '', '县', '411624', '0394', '5');
INSERT INTO `district` VALUES ('1978', '郸城', '287', 'd', 'dc', 'dancheng', '', '县', '411625', '0394', '6');
INSERT INTO `district` VALUES ('1979', '淮阳', '287', 'h', 'hy', 'huaiyang', '', '县', '411626', '0394', '7');
INSERT INTO `district` VALUES ('1980', '太康', '287', 't', 'tk', 'taikang', '', '县', '411627', '0394', '8');
INSERT INTO `district` VALUES ('1981', '鹿邑', '287', 'l', 'ly', 'luyi', '', '县', '411628', '0394', '9');
INSERT INTO `district` VALUES ('1982', '项城', '287', 'x', 'xc', 'xiangcheng', '', '市', '411681', '0394', '10');
INSERT INTO `district` VALUES ('1983', '驿城', '288', 'y', 'yc', 'yicheng', '', '区', '411702', '0396', '1');
INSERT INTO `district` VALUES ('1984', '西平', '288', 'x', 'xp', 'xiping', '', '县', '411721', '0396', '2');
INSERT INTO `district` VALUES ('1985', '上蔡', '288', 's', 'sc', 'shangcai', '', '县', '411722', '0396', '3');
INSERT INTO `district` VALUES ('1986', '平舆', '288', 'p', 'py', 'pingyu', '', '县', '411723', '0396', '4');
INSERT INTO `district` VALUES ('1987', '正阳', '288', 'z', 'zy', 'zhengyang', '', '县', '411724', '0396', '5');
INSERT INTO `district` VALUES ('1988', '确山', '288', 'q', 'qs', 'queshan', '', '县', '411725', '0396', '6');
INSERT INTO `district` VALUES ('1989', '泌阳', '288', 'm', 'my', 'miyang', '', '县', '411726', '0396', '7');
INSERT INTO `district` VALUES ('1990', '汝南', '288', 'r', 'rn', 'runan', '', '县', '411727', '0396', '8');
INSERT INTO `district` VALUES ('1991', '遂平', '288', 's', 'sp', 'suiping', '', '县', '411728', '0396', '9');
INSERT INTO `district` VALUES ('1992', '新蔡', '288', 'x', 'xc', 'xincai', '', '县', '411729', '0396', '10');
INSERT INTO `district` VALUES ('1993', '江岸', '290', 'j', 'ja', 'jiangan', '', '区', '420102', '027', '1');
INSERT INTO `district` VALUES ('1994', '江汉', '290', 'j', 'jh', 'jianghan', '', '区', '420103', '027', '2');
INSERT INTO `district` VALUES ('1995', '硚口', '290', 'q', 'qk', 'qiaokou', '', '区', '420104', '027', '3');
INSERT INTO `district` VALUES ('1996', '汉阳', '290', 'h', 'hy', 'hanyang', '', '区', '420105', '027', '4');
INSERT INTO `district` VALUES ('1997', '武昌', '290', 'w', 'wc', 'wuchang', '', '区', '420106', '027', '5');
INSERT INTO `district` VALUES ('1998', '青山', '290', 'q', 'qs', 'qingshan', '', '区', '420107', '027', '6');
INSERT INTO `district` VALUES ('1999', '洪山', '290', 'h', 'hs', 'hongshan', '', '区', '420111', '027', '7');
INSERT INTO `district` VALUES ('2000', '东西湖', '290', 'd', 'dxh', 'dongxihu', '', '区', '420112', '027', '8');
INSERT INTO `district` VALUES ('2001', '汉南', '290', 'h', 'hn', 'hannan', '', '区', '420113', '027', '9');
INSERT INTO `district` VALUES ('2002', '蔡甸', '290', 'c', 'cd', 'caidian', '', '区', '420114', '027', '10');
INSERT INTO `district` VALUES ('2003', '江夏', '290', 'j', 'jx', 'jiangxia', '', '区', '420115', '027', '11');
INSERT INTO `district` VALUES ('2004', '黄陂', '290', 'h', 'hp', 'huangpo', '', '区', '420116', '027', '12');
INSERT INTO `district` VALUES ('2005', '新洲', '290', 'x', 'xz', 'xinzhou', '', '区', '420117', '027', '13');
INSERT INTO `district` VALUES ('2006', '黄石港', '291', 'h', 'hsg', 'huangshigang', '', '区', '420202', '0714', '1');
INSERT INTO `district` VALUES ('2007', '西塞山', '291', 'x', 'xss', 'xisaishan', '', '区', '420203', '0714', '2');
INSERT INTO `district` VALUES ('2008', '下陆', '291', 'x', 'xl', 'xialu', '', '区', '420204', '0714', '3');
INSERT INTO `district` VALUES ('2009', '铁山', '291', 't', 'ts', 'tieshan', '', '区', '420205', '0714', '4');
INSERT INTO `district` VALUES ('2010', '阳新', '291', 'y', 'yx', 'yangxin', '', '县', '420222', '0714', '5');
INSERT INTO `district` VALUES ('2011', '大冶', '291', 'd', 'dy', 'daye', '', '市', '420281', '0714', '6');
INSERT INTO `district` VALUES ('2012', '茅箭', '292', 'm', 'mj', 'maojian', '', '区', '420302', '0719', '1');
INSERT INTO `district` VALUES ('2013', '张湾', '292', 'z', 'zw', 'zhangwan', '', '区', '420303', '0719', '2');
INSERT INTO `district` VALUES ('2014', '郧阳', '292', 'y', 'yy', 'yunyang', '', '区', '420304', '0719', '3');
INSERT INTO `district` VALUES ('2015', '郧西', '292', 'y', 'yx', 'yunxi', '', '县', '420322', '0719', '4');
INSERT INTO `district` VALUES ('2016', '竹山', '292', 'z', 'zs', 'zhushan', '', '县', '420323', '0719', '5');
INSERT INTO `district` VALUES ('2017', '竹溪', '292', 'z', 'zx', 'zhuxi', '', '县', '420324', '0719', '6');
INSERT INTO `district` VALUES ('2018', '房县', '292', 'f', 'fx', 'fangxian', '', '', '420325', '0719', '7');
INSERT INTO `district` VALUES ('2019', '丹江口', '292', 'd', 'djk', 'danjiangkou', '', '市', '420381', '0719', '8');
INSERT INTO `district` VALUES ('2020', '西陵', '293', 'x', 'xl', 'xiling', '', '区', '420502', '0717', '1');
INSERT INTO `district` VALUES ('2021', '伍家岗', '293', 'w', 'wjg', 'wujiagang', '', '区', '420503', '0717', '2');
INSERT INTO `district` VALUES ('2022', '点军', '293', 'd', 'dj', 'dianjun', '', '区', '420504', '0717', '3');
INSERT INTO `district` VALUES ('2023', '虢亭', '293', 'g', 'gt', 'guoting', '', '区', '420505', '0717', '4');
INSERT INTO `district` VALUES ('2024', '夷陵', '293', 'y', 'yl', 'yiling', '', '区', '420506', '0717', '5');
INSERT INTO `district` VALUES ('2025', '远安', '293', 'y', 'ya', 'yuanan', '', '县', '420525', '0717', '6');
INSERT INTO `district` VALUES ('2026', '兴山', '293', 'x', 'xs', 'xingshan', '', '县', '420526', '0717', '7');
INSERT INTO `district` VALUES ('2027', '秭归', '293', 'z', 'zg', 'zigui', '', '县', '420527', '0717', '8');
INSERT INTO `district` VALUES ('2028', '长阳', '293', 'z', 'zy', 'zhangyang', '土家族', '自治县', '420528', '0717', '9');
INSERT INTO `district` VALUES ('2029', '五峰', '293', 'w', 'wf', 'wufeng', '土家族', '自治县', '420529', '0717', '10');
INSERT INTO `district` VALUES ('2030', '宜都', '293', 'y', 'yd', 'yidou', '', '市', '420581', '0717', '11');
INSERT INTO `district` VALUES ('2031', '当阳', '293', 'd', 'dy', 'dangyang', '', '市', '420582', '0717', '12');
INSERT INTO `district` VALUES ('2032', '枝江', '293', 'z', 'zj', 'zhijiang', '', '市', '420583', '0717', '13');
INSERT INTO `district` VALUES ('2033', '襄城', '294', 'x', 'xc', 'xiangcheng', '', '区', '420602', '0710', '1');
INSERT INTO `district` VALUES ('2034', '樊城', '294', 'f', 'fc', 'fancheng', '', '区', '420606', '0710', '2');
INSERT INTO `district` VALUES ('2035', '襄州', '294', 'x', 'xz', 'xiangzhou', '', '区', '420607', '0710', '3');
INSERT INTO `district` VALUES ('2036', '南漳', '294', 'n', 'nz', 'nanzhang', '', '县', '420624', '0710', '4');
INSERT INTO `district` VALUES ('2037', '谷城', '294', 'g', 'gc', 'gucheng', '', '县', '420625', '0710', '5');
INSERT INTO `district` VALUES ('2038', '保康', '294', 'b', 'bk', 'baokang', '', '县', '420626', '0710', '6');
INSERT INTO `district` VALUES ('2039', '老河口', '294', 'l', 'lhk', 'laohekou', '', '市', '420682', '0710', '7');
INSERT INTO `district` VALUES ('2040', '枣阳', '294', 'z', 'zy', 'zaoyang', '', '市', '420683', '0710', '8');
INSERT INTO `district` VALUES ('2041', '宜城', '294', 'y', 'yc', 'yicheng', '', '市', '420684', '0710', '9');
INSERT INTO `district` VALUES ('2042', '粱子湖', '295', 'l', 'lzh', 'liangzihu', '', '区', '420702', '', '1');
INSERT INTO `district` VALUES ('2043', '华容', '295', 'h', 'hr', 'huarong', '', '区', '420703', '', '2');
INSERT INTO `district` VALUES ('2044', '鄂城', '295', 'e', 'ec', 'echeng', '', '区', '420704', '', '3');
INSERT INTO `district` VALUES ('2045', '东宝', '296', 'd', 'db', 'dongbao', '', '区', '420802', '0724', '1');
INSERT INTO `district` VALUES ('2046', '掇刀', '296', 'd', 'dd', 'duodao', '', '区', '420804', '0724', '2');
INSERT INTO `district` VALUES ('2047', '京山', '296', 'j', 'js', 'jingshan', '', '县', '420821', '0724', '3');
INSERT INTO `district` VALUES ('2048', '沙洋', '296', 's', 'sy', 'shayang', '', '县', '420822', '0724', '4');
INSERT INTO `district` VALUES ('2049', '钟祥', '296', 'z', 'zx', 'zhongxiang', '', '市', '420881', '0724', '5');
INSERT INTO `district` VALUES ('2050', '孝南', '297', 'x', 'xn', 'xiaonan', '', '区', '420902', '0712', '1');
INSERT INTO `district` VALUES ('2051', '大悟', '297', 'd', 'dw', 'dawu', '', '县', '420922', '0712', '2');
INSERT INTO `district` VALUES ('2052', '云梦', '297', 'y', 'ym', 'yunmeng', '', '县', '420923', '0712', '3');
INSERT INTO `district` VALUES ('2053', '应城', '297', 'y', 'yc', 'yingcheng', '', '市', '420981', '0712', '4');
INSERT INTO `district` VALUES ('2054', '安陆', '297', 'a', 'al', 'anlu', '', '市', '420982', '0712', '5');
INSERT INTO `district` VALUES ('2055', '汉川', '297', 'h', 'hc', 'hanchuan', '', '市', '420984', '0712', '6');
INSERT INTO `district` VALUES ('2056', '沙市', '298', 's', 'ss', 'shashi', '', '区', '421002', '0716', '1');
INSERT INTO `district` VALUES ('2057', '荆州', '298', 'j', 'jz', 'jingzhou', '', '区', '421003', '0716', '2');
INSERT INTO `district` VALUES ('2058', '公安', '298', 'g', 'ga', 'gongan', '', '县', '421022', '0716', '3');
INSERT INTO `district` VALUES ('2059', '监利', '298', 'j', 'jl', 'jianli', '', '县', '421023', '0716', '4');
INSERT INTO `district` VALUES ('2060', '江陵', '298', 'j', 'jl', 'jiangling', '', '县', '421024', '0716', '5');
INSERT INTO `district` VALUES ('2061', '石首', '298', 's', 'ss', 'shishou', '', '市', '421081', '0716', '6');
INSERT INTO `district` VALUES ('2062', '洪湖', '298', 'h', 'hh', 'honghu', '', '市', '421083', '0716', '7');
INSERT INTO `district` VALUES ('2063', '松滋', '298', 's', 'sz', 'songzi', '', '市', '421087', '0716', '8');
INSERT INTO `district` VALUES ('2064', '黄州', '299', 'h', 'hz', 'huangzhou', '', '区', '421102', '0713', '1');
INSERT INTO `district` VALUES ('2065', '团风', '299', 't', 'tf', 'tuanfeng', '', '县', '421121', '0713', '2');
INSERT INTO `district` VALUES ('2066', '红安', '299', 'h', 'ha', 'hongan', '', '县', '421122', '0713', '3');
INSERT INTO `district` VALUES ('2067', '罗田', '299', 'l', 'lt', 'luotian', '', '县', '421123', '0713', '4');
INSERT INTO `district` VALUES ('2068', '英山', '299', 'y', 'ys', 'yingshan', '', '县', '421124', '0713', '5');
INSERT INTO `district` VALUES ('2069', '浠水', '299', 'x', 'xs', 'xishui', '', '县', '421125', '0713', '6');
INSERT INTO `district` VALUES ('2070', '蕲春', '299', 'q', 'qc', 'qichun', '', '县', '421126', '0713', '7');
INSERT INTO `district` VALUES ('2071', '黄梅', '299', 'h', 'hm', 'huangmei', '', '县', '421127', '0713', '8');
INSERT INTO `district` VALUES ('2072', '麻城', '299', 'm', 'mc', 'macheng', '', '市', '421181', '0713', '9');
INSERT INTO `district` VALUES ('2073', '武穴', '299', 'w', 'wx', 'wuxue', '', '市', '421182', '0713', '10');
INSERT INTO `district` VALUES ('2074', '咸安', '300', 'x', 'xa', 'xianan', '', '区', '421202', '0715', '1');
INSERT INTO `district` VALUES ('2075', '嘉鱼', '300', 'j', 'jy', 'jiayu', '', '县', '421221', '0715', '2');
INSERT INTO `district` VALUES ('2076', '通城', '300', 't', 'tc', 'tongcheng', '', '县', '421222', '0715', '3');
INSERT INTO `district` VALUES ('2077', '崇阳', '300', 'c', 'cy', 'chongyang', '', '县', '421223', '0715', '4');
INSERT INTO `district` VALUES ('2078', '通山', '300', 't', 'ts', 'tongshan', '', '县', '421224', '0715', '5');
INSERT INTO `district` VALUES ('2079', '赤壁', '300', 'c', 'cb', 'chibi', '', '市', '421281', '0715', '6');
INSERT INTO `district` VALUES ('2080', '曾都', '301', 'c', 'cd', 'cengdou', '', '区', '421303', '0722', '1');
INSERT INTO `district` VALUES ('2081', '随县', '301', 's', 'sx', 'suixian', '', '', '421321', '0722', '2');
INSERT INTO `district` VALUES ('2082', '广水', '301', 'g', 'gs', 'guangshui', '', '市', '421381', '0722', '3');
INSERT INTO `district` VALUES ('2083', '恩施', '302', 'e', 'es', 'enshi', '', '市', '422801', '0718', '1');
INSERT INTO `district` VALUES ('2084', '利川', '302', 'l', 'lc', 'lichuan', '', '市', '422802', '0718', '2');
INSERT INTO `district` VALUES ('2085', '建始', '302', 'j', 'js', 'jianshi', '', '县', '422822', '0718', '3');
INSERT INTO `district` VALUES ('2086', '巴东', '302', 'b', 'bd', 'badong', '', '县', '422823', '0718', '4');
INSERT INTO `district` VALUES ('2087', '宣恩', '302', 'x', 'xe', 'xuanen', '', '县', '422825', '0718', '5');
INSERT INTO `district` VALUES ('2088', '咸丰', '302', 'x', 'xf', 'xianfeng', '', '县', '422826', '0718', '6');
INSERT INTO `district` VALUES ('2089', '来凤', '302', 'l', 'lf', 'laifeng', '', '县', '422827', '0718', '7');
INSERT INTO `district` VALUES ('2090', '鹤峰', '302', 'h', 'hf', 'hefeng', '', '县', '422828', '0718', '8');
INSERT INTO `district` VALUES ('2091', '芙蓉', '307', 'f', 'fr', 'furong', '', '区', '430102', '0731', '1');
INSERT INTO `district` VALUES ('2092', '天心', '307', 't', 'tx', 'tianxin', '', '区', '430103', '0731', '2');
INSERT INTO `district` VALUES ('2093', '岳麓', '307', 'y', 'yl', 'yuelu', '', '区', '430104', '0731', '3');
INSERT INTO `district` VALUES ('2094', '开福', '307', 'k', 'kf', 'kaifu', '', '区', '430105', '0731', '4');
INSERT INTO `district` VALUES ('2095', '雨花', '307', 'y', 'yh', 'yuhua', '', '区', '430111', '0731', '5');
INSERT INTO `district` VALUES ('2096', '望城', '307', 'w', 'wc', 'wangcheng', '', '区', '430112', '0731', '6');
INSERT INTO `district` VALUES ('2097', '长沙', '307', 'z', 'zs', 'zhangsha', '', '县', '430121', '0731', '7');
INSERT INTO `district` VALUES ('2098', '宁乡', '307', 'n', 'nx', 'ningxiang', '', '县', '430124', '0731', '8');
INSERT INTO `district` VALUES ('2099', '浏阳', '307', 'l', 'ly', 'liuyang', '', '市', '430181', '0731', '9');
INSERT INTO `district` VALUES ('2100', '荷塘', '308', 'h', 'ht', 'hetang', '', '区', '430202', '0731', '1');
INSERT INTO `district` VALUES ('2101', '芦淞', '308', 'l', 'ls', 'lusong', '', '区', '430203', '0731', '2');
INSERT INTO `district` VALUES ('2102', '石峰', '308', 's', 'sf', 'shifeng', '', '区', '430204', '0731', '3');
INSERT INTO `district` VALUES ('2103', '天元', '308', 't', 'ty', 'tianyuan', '', '区', '430211', '0731', '4');
INSERT INTO `district` VALUES ('2104', '株洲', '308', 'z', 'zz', 'zhuzhou', '', '县', '430221', '0731', '5');
INSERT INTO `district` VALUES ('2105', '攸县', '308', 'y', 'yx', 'youxian', '', '', '430223', '0731', '6');
INSERT INTO `district` VALUES ('2106', '茶陵', '308', 'c', 'cl', 'chaling', '', '县', '430224', '0731', '7');
INSERT INTO `district` VALUES ('2107', '炎陵', '308', 'y', 'yl', 'yanling', '', '县', '430225', '0731', '8');
INSERT INTO `district` VALUES ('2108', '醴陵', '308', 'l', 'll', 'liling', '', '市', '430281', '0731', '9');
INSERT INTO `district` VALUES ('2109', '雨湖', '309', 'y', 'yh', 'yuhu', '', '区', '430302', '0731', '1');
INSERT INTO `district` VALUES ('2110', '岳塘', '309', 'y', 'yt', 'yuetang', '', '区', '430304', '0731', '2');
INSERT INTO `district` VALUES ('2111', '湘潭', '309', 'x', 'xt', 'xiangtan', '', '县', '430321', '0731', '3');
INSERT INTO `district` VALUES ('2112', '湘乡', '309', 'x', 'xx', 'xiangxiang', '', '市', '430381', '0731', '4');
INSERT INTO `district` VALUES ('2113', '韶山', '309', 's', 'ss', 'shaoshan', '', '市', '430382', '0731', '5');
INSERT INTO `district` VALUES ('2114', '珠晖', '310', 'z', 'zh', 'zhuhui', '', '区', '430405', '0734', '1');
INSERT INTO `district` VALUES ('2115', '雁峰', '310', 'y', 'yf', 'yanfeng', '', '区', '430406', '0734', '2');
INSERT INTO `district` VALUES ('2116', '石鼓', '310', 's', 'sg', 'shigu', '', '区', '430407', '0734', '3');
INSERT INTO `district` VALUES ('2117', '蒸湘', '310', 'z', 'zx', 'zhengxiang', '', '区', '430408', '0734', '4');
INSERT INTO `district` VALUES ('2118', '南岳', '310', 'n', 'ny', 'nanyue', '', '区', '430412', '0734', '5');
INSERT INTO `district` VALUES ('2119', '衡阳', '310', 'h', 'hy', 'hengyang', '', '县', '430421', '0734', '6');
INSERT INTO `district` VALUES ('2120', '衡南', '310', 'h', 'hn', 'hengnan', '', '县', '430422', '0734', '7');
INSERT INTO `district` VALUES ('2121', '衡山', '310', 'h', 'hs', 'hengshan', '', '县', '430423', '0734', '8');
INSERT INTO `district` VALUES ('2122', '衡东', '310', 'h', 'hd', 'hengdong', '', '县', '430424', '0734', '9');
INSERT INTO `district` VALUES ('2123', '祁东', '310', 'q', 'qd', 'qidong', '', '县', '430426', '0734', '10');
INSERT INTO `district` VALUES ('2124', '耒阳', '310', 'l', 'ly', 'leiyang', '', '市', '430481', '0734', '11');
INSERT INTO `district` VALUES ('2125', '常宁', '310', 'c', 'cn', 'changning', '', '市', '430482', '0734', '12');
INSERT INTO `district` VALUES ('2126', '双清', '311', 's', 'sq', 'shuangqing', '', '区', '430502', '0739', '1');
INSERT INTO `district` VALUES ('2127', '大祥', '311', 'd', 'dx', 'daxiang', '', '区', '430503', '0739', '2');
INSERT INTO `district` VALUES ('2128', '北塔', '311', 'b', 'bt', 'beita', '', '区', '430511', '0739', '3');
INSERT INTO `district` VALUES ('2129', '邵东', '311', 's', 'sd', 'shaodong', '', '县', '430521', '0739', '4');
INSERT INTO `district` VALUES ('2130', '新邵', '311', 'x', 'xs', 'xinshao', '', '县', '430522', '0739', '5');
INSERT INTO `district` VALUES ('2131', '邵阳', '311', 's', 'sy', 'shaoyang', '', '县', '430523', '0739', '6');
INSERT INTO `district` VALUES ('2132', '隆回', '311', 'l', 'lh', 'longhui', '', '县', '430524', '0739', '7');
INSERT INTO `district` VALUES ('2133', '洞口', '311', 'd', 'dk', 'dongkou', '', '县', '430525', '0739', '8');
INSERT INTO `district` VALUES ('2134', '绥宁', '311', 's', 'sn', 'suining', '', '县', '430527', '0739', '9');
INSERT INTO `district` VALUES ('2135', '新宁', '311', 'x', 'xn', 'xinning', '', '县', '430528', '0739', '10');
INSERT INTO `district` VALUES ('2136', '城步', '311', 'c', 'cb', 'chengbu', '苗族', '自治县', '430529', '0739', '11');
INSERT INTO `district` VALUES ('2137', '武冈', '311', 'w', 'wg', 'wugang', '', '市', '430581', '0739', '12');
INSERT INTO `district` VALUES ('2138', '岳阳楼', '312', 'y', 'yyl', 'yueyanglou', '', '区', '430602', '0730', '1');
INSERT INTO `district` VALUES ('2139', '云溪', '312', 'y', 'yx', 'yunxi', '', '区', '430603', '0730', '2');
INSERT INTO `district` VALUES ('2140', '君山', '312', 'j', 'js', 'junshan', '', '区', '430611', '0730', '3');
INSERT INTO `district` VALUES ('2141', '岳阳', '312', 'y', 'yy', 'yueyang', '', '县', '430621', '0730', '4');
INSERT INTO `district` VALUES ('2142', '华容', '312', 'h', 'hr', 'huarong', '', '县', '430623', '0730', '5');
INSERT INTO `district` VALUES ('2143', '湘阴', '312', 'x', 'xy', 'xiangyin', '', '县', '430624', '0730', '6');
INSERT INTO `district` VALUES ('2144', '平江', '312', 'p', 'pj', 'pingjiang', '', '县', '430626', '0730', '7');
INSERT INTO `district` VALUES ('2145', '汨罗', '312', 'm', 'ml', 'miluo', '', '市', '430681', '0730', '8');
INSERT INTO `district` VALUES ('2146', '临湘', '312', 'l', 'lx', 'linxiang', '', '市', '430682', '0730', '9');
INSERT INTO `district` VALUES ('2147', '武陵', '313', 'w', 'wl', 'wuling', '', '区', '430702', '0736', '1');
INSERT INTO `district` VALUES ('2148', '鼎城', '313', 'd', 'dc', 'dingcheng', '', '区', '430703', '0736', '2');
INSERT INTO `district` VALUES ('2149', '安乡', '313', 'a', 'ax', 'anxiang', '', '县', '430721', '0736', '3');
INSERT INTO `district` VALUES ('2150', '汉寿', '313', 'h', 'hs', 'hanshou', '', '县', '430722', '0736', '4');
INSERT INTO `district` VALUES ('2151', '澧县', '313', 'l', 'lx', 'lixian', '', '', '430723', '0736', '5');
INSERT INTO `district` VALUES ('2152', '临澧', '313', 'l', 'll', 'linli', '', '县', '430724', '0736', '6');
INSERT INTO `district` VALUES ('2153', '桃源', '313', 't', 'ty', 'taoyuan', '', '县', '430725', '0736', '7');
INSERT INTO `district` VALUES ('2154', '石门', '313', 's', 'sm', 'shimen', '', '县', '430726', '0736', '8');
INSERT INTO `district` VALUES ('2155', '津市', '313', 'j', 'js', 'jinshi', '', '市', '430781', '0736', '9');
INSERT INTO `district` VALUES ('2156', '永定', '314', 'y', 'yd', 'yongding', '', '区', '430802', '0744', '1');
INSERT INTO `district` VALUES ('2157', '武陵源', '314', 'w', 'wly', 'wulingyuan', '', '区', '430811', '0744', '2');
INSERT INTO `district` VALUES ('2158', '慈利', '314', 'c', 'cl', 'cili', '', '县', '430821', '0744', '3');
INSERT INTO `district` VALUES ('2159', '桑植', '314', 's', 'sz', 'sangzhi', '', '县', '430822', '0744', '4');
INSERT INTO `district` VALUES ('2160', '资阳', '315', 'z', 'zy', 'ziyang', '', '区', '430902', '0737', '1');
INSERT INTO `district` VALUES ('2161', '赫山', '315', 'h', 'hs', 'heshan', '', '区', '430903', '0737', '2');
INSERT INTO `district` VALUES ('2162', '南县', '315', 'n', 'nx', 'nanxian', '', '', '430921', '0737', '3');
INSERT INTO `district` VALUES ('2163', '桃江', '315', 't', 'tj', 'taojiang', '', '县', '430922', '0737', '4');
INSERT INTO `district` VALUES ('2164', '安化', '315', 'a', 'ah', 'anhua', '', '县', '430923', '0737', '5');
INSERT INTO `district` VALUES ('2165', '沅江', '315', 'y', 'yj', 'yuanjiang', '', '市', '430981', '0737', '6');
INSERT INTO `district` VALUES ('2166', '北湖', '316', 'b', 'bh', 'beihu', '', '区', '431002', '0735', '1');
INSERT INTO `district` VALUES ('2167', '苏仙', '316', 's', 'sx', 'suxian', '', '区', '431003', '0735', '2');
INSERT INTO `district` VALUES ('2168', '桂阳', '316', 'g', 'gy', 'guiyang', '', '县', '431021', '0735', '3');
INSERT INTO `district` VALUES ('2169', '宜章', '316', 'y', 'yz', 'yizhang', '', '县', '431022', '0735', '4');
INSERT INTO `district` VALUES ('2170', '永兴', '316', 'y', 'yx', 'yongxing', '', '县', '431023', '0735', '5');
INSERT INTO `district` VALUES ('2171', '嘉禾', '316', 'j', 'jh', 'jiahe', '', '县', '431024', '0735', '6');
INSERT INTO `district` VALUES ('2172', '临武', '316', 'l', 'lw', 'linwu', '', '县', '431025', '0735', '7');
INSERT INTO `district` VALUES ('2173', '汝城', '316', 'r', 'rc', 'rucheng', '', '县', '431026', '0735', '8');
INSERT INTO `district` VALUES ('2174', '桂东', '316', 'g', 'gd', 'guidong', '', '县', '431027', '0735', '9');
INSERT INTO `district` VALUES ('2175', '安仁', '316', 'a', 'ar', 'anren', '', '县', '431028', '0735', '10');
INSERT INTO `district` VALUES ('2176', '资兴', '316', 'z', 'zx', 'zixing', '', '市', '431081', '0735', '11');
INSERT INTO `district` VALUES ('2177', '零陵', '317', 'l', 'll', 'lingling', '', '区', '431102', '0746', '1');
INSERT INTO `district` VALUES ('2178', '冷水滩', '317', 'l', 'lst', 'lengshuitan', '', '区', '431103', '0746', '2');
INSERT INTO `district` VALUES ('2179', '祁阳', '317', 'q', 'qy', 'qiyang', '', '县', '431121', '0746', '3');
INSERT INTO `district` VALUES ('2180', '东安', '317', 'd', 'da', 'dongan', '', '县', '431122', '0746', '4');
INSERT INTO `district` VALUES ('2181', '双牌', '317', 's', 'sp', 'shuangpai', '', '县', '431123', '0746', '5');
INSERT INTO `district` VALUES ('2182', '道县', '317', 'd', 'dx', 'daoxian', '', '', '431124', '0746', '6');
INSERT INTO `district` VALUES ('2183', '江永', '317', 'j', 'jy', 'jiangyong', '', '县', '431125', '0746', '7');
INSERT INTO `district` VALUES ('2184', '宁远', '317', 'n', 'ny', 'ningyuan', '', '县', '431126', '0746', '8');
INSERT INTO `district` VALUES ('2185', '蓝山', '317', 'l', 'ls', 'lanshan', '', '县', '431127', '0746', '9');
INSERT INTO `district` VALUES ('2186', '新田', '317', 'x', 'xt', 'xintian', '', '县', '431128', '0746', '10');
INSERT INTO `district` VALUES ('2187', '江华', '317', 'j', 'jh', 'jianghua', '瑶族', '自治县', '431129', '0746', '11');
INSERT INTO `district` VALUES ('2188', '鹤城', '318', 'h', 'hc', 'hecheng', '', '区', '431202', '0745', '1');
INSERT INTO `district` VALUES ('2189', '中方', '318', 'z', 'zf', 'zhongfang', '', '县', '431221', '0745', '2');
INSERT INTO `district` VALUES ('2190', '沅陵', '318', 'y', 'yl', 'yuanling', '', '县', '431222', '0745', '3');
INSERT INTO `district` VALUES ('2191', '辰溪', '318', 'c', 'cx', 'chenxi', '', '县', '431223', '0745', '4');
INSERT INTO `district` VALUES ('2192', '溆浦', '318', 'x', 'xp', 'xupu', '', '县', '431224', '0745', '5');
INSERT INTO `district` VALUES ('2193', '会同', '318', 'h', 'ht', 'huitong', '', '县', '431225', '0745', '6');
INSERT INTO `district` VALUES ('2194', '麻阳', '318', 'm', 'my', 'mayang', '苗族', '自治县', '431226', '0745', '7');
INSERT INTO `district` VALUES ('2195', '新晃', '318', 'x', 'xh', 'xinhuang', '侗族', '自治县', '431227', '0745', '8');
INSERT INTO `district` VALUES ('2196', '芷江', '318', 'z', 'zj', 'zhijiang', '侗族', '自治县', '431228', '0745', '9');
INSERT INTO `district` VALUES ('2197', '靖州', '318', 'j', 'jz', 'jingzhou', '苗族侗族', '自治县', '431229', '0745', '10');
INSERT INTO `district` VALUES ('2198', '通道', '318', 't', 'td', 'tongdao', '侗族', '自治县', '431230', '0745', '11');
INSERT INTO `district` VALUES ('2199', '洪江', '318', 'h', 'hj', 'hongjiang', '', '市', '431281', '0745', '12');
INSERT INTO `district` VALUES ('2200', '娄星', '319', 'l', 'lx', 'louxing', '', '区', '431302', '0738', '1');
INSERT INTO `district` VALUES ('2201', '双峰', '319', 's', 'sf', 'shuangfeng', '', '县', '431321', '0738', '2');
INSERT INTO `district` VALUES ('2202', '新化', '319', 'x', 'xh', 'xinhua', '', '县', '431322', '0738', '3');
INSERT INTO `district` VALUES ('2203', '冷水江', '319', 'l', 'lsj', 'lengshuijiang', '', '市', '431381', '0738', '4');
INSERT INTO `district` VALUES ('2204', '涟源', '319', 'l', 'ly', 'lianyuan', '', '市', '431382', '0738', '5');
INSERT INTO `district` VALUES ('2205', '吉首', '320', 'j', 'js', 'jishou', '', '市', '433101', '0743', '1');
INSERT INTO `district` VALUES ('2206', '泸溪', '320', 'l', 'lx', 'luxi', '', '县', '433122', '0743', '2');
INSERT INTO `district` VALUES ('2207', '凤凰', '320', 'f', 'fh', 'fenghuang', '', '县', '433123', '0743', '3');
INSERT INTO `district` VALUES ('2208', '花垣', '320', 'h', 'hy', 'huayuan', '', '县', '433124', '0743', '4');
INSERT INTO `district` VALUES ('2209', '保靖', '320', 'b', 'bj', 'baojing', '', '县', '433125', '0743', '5');
INSERT INTO `district` VALUES ('2210', '古丈', '320', 'g', 'gz', 'guzhang', '', '县', '433126', '0743', '6');
INSERT INTO `district` VALUES ('2211', '永顺', '320', 'y', 'ys', 'yongshun', '', '县', '433127', '0743', '7');
INSERT INTO `district` VALUES ('2212', '龙山', '320', 'l', 'ls', 'longshan', '', '县', '433130', '0743', '8');
INSERT INTO `district` VALUES ('2213', '荔湾', '321', 'l', 'lw', 'liwan', '', '区', '440103', '020', '1');
INSERT INTO `district` VALUES ('2214', '越秀', '321', 'y', 'yx', 'yuexiu', '', '区', '440104', '020', '2');
INSERT INTO `district` VALUES ('2215', '海珠', '321', 'h', 'hz', 'haizhu', '', '区', '440105', '020', '3');
INSERT INTO `district` VALUES ('2216', '天河', '321', 't', 'th', 'tianhe', '', '区', '440106', '020', '4');
INSERT INTO `district` VALUES ('2217', '白云', '321', 'b', 'by', 'baiyun', '', '区', '440111', '020', '5');
INSERT INTO `district` VALUES ('2218', '黄埔', '321', 'h', 'hp', 'huangpu', '', '区', '440112', '020', '6');
INSERT INTO `district` VALUES ('2219', '番禺', '321', 'f', 'fy', 'fanyu', '', '区', '440113', '020', '7');
INSERT INTO `district` VALUES ('2220', '花都', '321', 'h', 'hd', 'huadou', '', '区', '440114', '020', '8');
INSERT INTO `district` VALUES ('2221', '南沙', '321', 'n', 'ns', 'nansha', '', '区', '440115', '020', '9');
INSERT INTO `district` VALUES ('2223', '增城', '321', 'z', 'zc', 'zengcheng', '', '区', '440118', '020', '12');
INSERT INTO `district` VALUES ('2224', '从化', '321', 'c', 'ch', 'conghua', '', '区', '440117', '020', '11');
INSERT INTO `district` VALUES ('2225', '武江', '322', 'w', 'wj', 'wujiang', '', '区', '440203', '0751', '1');
INSERT INTO `district` VALUES ('2226', '浈江', '322', 'z', 'zj', 'zhenjiang', '', '区', '440204', '0751', '2');
INSERT INTO `district` VALUES ('2227', '曲江', '322', 'q', 'qj', 'qujiang', '', '区', '440205', '0751', '3');
INSERT INTO `district` VALUES ('2228', '始兴', '322', 's', 'sx', 'shixing', '', '县', '440222', '0751', '4');
INSERT INTO `district` VALUES ('2229', '仁化', '322', 'r', 'rh', 'renhua', '', '县', '440224', '0751', '5');
INSERT INTO `district` VALUES ('2230', '翁源', '322', 'w', 'wy', 'wengyuan', '', '县', '440229', '0751', '6');
INSERT INTO `district` VALUES ('2231', '乳源', '322', 'r', 'ry', 'ruyuan', '瑶族', '自治县', '440232', '0751', '7');
INSERT INTO `district` VALUES ('2232', '新丰', '322', 'x', 'xf', 'xinfeng', '', '县', '440233', '0751', '8');
INSERT INTO `district` VALUES ('2233', '乐昌', '322', 'l', 'lc', 'lechang', '', '市', '440281', '0751', '9');
INSERT INTO `district` VALUES ('2234', '南雄', '322', 'n', 'nx', 'nanxiong', '', '市', '440282', '0751', '10');
INSERT INTO `district` VALUES ('2235', '罗湖', '323', 'l', 'lh', 'luohu', '', '区', '440303', '0755', '1');
INSERT INTO `district` VALUES ('2236', '福田', '323', 'f', 'ft', 'futian', '', '区', '440304', '0755', '2');
INSERT INTO `district` VALUES ('2237', '南山', '323', 'n', 'ns', 'nanshan', '', '区', '440305', '0755', '3');
INSERT INTO `district` VALUES ('2238', '宝安', '323', 'b', 'ba', 'baoan', '', '区', '440306', '0755', '4');
INSERT INTO `district` VALUES ('2239', '龙岗', '323', 'l', 'lg', 'longgang', '', '区', '440307', '0755', '5');
INSERT INTO `district` VALUES ('2240', '盐田', '323', 'y', 'yt', 'yantian', '', '区', '440308', '0755', '6');
INSERT INTO `district` VALUES ('2241', '香洲', '324', 'x', 'xz', 'xiangzhou', '', '区', '440402', '0756', '1');
INSERT INTO `district` VALUES ('2242', '斗门', '324', 'd', 'dm', 'doumen', '', '区', '440403', '0756', '2');
INSERT INTO `district` VALUES ('2243', '金湾', '324', 'j', 'jw', 'jinwan', '', '区', '440404', '0756', '3');
INSERT INTO `district` VALUES ('2244', '龙湖', '325', 'l', 'lh', 'longhu', '', '区', '440507', '0754', '1');
INSERT INTO `district` VALUES ('2245', '金平', '325', 'j', 'jp', 'jinping', '', '区', '440511', '0754', '2');
INSERT INTO `district` VALUES ('2246', '濠江', '325', 'h', 'hj', 'haojiang', '', '区', '440512', '0754', '3');
INSERT INTO `district` VALUES ('2247', '潮阳', '325', 'c', 'cy', 'chaoyang', '', '区', '440513', '0754', '4');
INSERT INTO `district` VALUES ('2248', '潮南', '325', 'c', 'cn', 'chaonan', '', '区', '440514', '0754', '5');
INSERT INTO `district` VALUES ('2249', '澄海', '325', 'c', 'ch', 'chenghai', '', '区', '440515', '0754', '6');
INSERT INTO `district` VALUES ('2250', '南澳', '325', 'n', 'na', 'nanao', '', '县', '440523', '0754', '7');
INSERT INTO `district` VALUES ('2251', '禅城', '326', 's', 'sc', 'shancheng', '', '区', '440604', '0757', '1');
INSERT INTO `district` VALUES ('2252', '南海', '326', 'n', 'nh', 'nanhai', '', '区', '440605', '0757', '2');
INSERT INTO `district` VALUES ('2253', '顺德', '326', 's', 'sd', 'shunde', '', '区', '440606', '0757', '3');
INSERT INTO `district` VALUES ('2254', '三水', '326', 's', 'ss', 'sanshui', '', '区', '440607', '0757', '4');
INSERT INTO `district` VALUES ('2255', '高明', '326', 'g', 'gm', 'gaoming', '', '区', '440608', '0757', '5');
INSERT INTO `district` VALUES ('2256', '蓬江', '327', 'p', 'pj', 'pengjiang', '', '区', '440703', '0750', '1');
INSERT INTO `district` VALUES ('2257', '江海', '327', 'j', 'jh', 'jianghai', '', '区', '440704', '0750', '2');
INSERT INTO `district` VALUES ('2258', '新会', '327', 'x', 'xh', 'xinhui', '', '区', '440705', '0750', '3');
INSERT INTO `district` VALUES ('2259', '台山', '327', 't', 'ts', 'taishan', '', '市', '440781', '0750', '4');
INSERT INTO `district` VALUES ('2260', '开平', '327', 'k', 'kp', 'kaiping', '', '市', '440783', '0750', '5');
INSERT INTO `district` VALUES ('2261', '鹤山', '327', 'h', 'hs', 'heshan', '', '市', '440784', '0750', '6');
INSERT INTO `district` VALUES ('2262', '恩平', '327', 'e', 'ep', 'enping', '', '市', '440785', '0750', '7');
INSERT INTO `district` VALUES ('2263', '赤坎', '328', 'c', 'ck', 'chikan', '', '区', '440802', '0759', '1');
INSERT INTO `district` VALUES ('2264', '霞山', '328', 'x', 'xs', 'xiashan', '', '区', '440803', '0759', '2');
INSERT INTO `district` VALUES ('2265', '坡头', '328', 'p', 'pt', 'potou', '', '区', '440804', '0759', '3');
INSERT INTO `district` VALUES ('2266', '麻章', '328', 'm', 'mz', 'mazhang', '', '区', '440811', '0759', '4');
INSERT INTO `district` VALUES ('2267', '遂溪', '328', 's', 'sx', 'suixi', '', '县', '440823', '0759', '5');
INSERT INTO `district` VALUES ('2268', '徐闻', '328', 'x', 'xw', 'xuwen', '', '县', '440825', '0759', '6');
INSERT INTO `district` VALUES ('2269', '廉江', '328', 'l', 'lj', 'lianjiang', '', '市', '440881', '0759', '7');
INSERT INTO `district` VALUES ('2270', '雷州', '328', 'l', 'lz', 'leizhou', '', '市', '440882', '0759', '8');
INSERT INTO `district` VALUES ('2271', '吴川', '328', 'w', 'wc', 'wuchuan', '', '市', '440883', '0759', '9');
INSERT INTO `district` VALUES ('2272', '茂南', '329', 'm', 'mn', 'maonan', '', '区', '440902', '0668', '1');
INSERT INTO `district` VALUES ('2274', '电白', '329', 'd', 'db', 'dianbai', '', '区', '440904', '0668', '2');
INSERT INTO `district` VALUES ('2275', '高州', '329', 'g', 'gz', 'gaozhou', '', '市', '440981', '0668', '3');
INSERT INTO `district` VALUES ('2276', '化州', '329', 'h', 'hz', 'huazhou', '', '市', '440982', '0668', '4');
INSERT INTO `district` VALUES ('2277', '信宜', '329', 'x', 'xy', 'xinyi', '', '市', '440983', '0668', '5');
INSERT INTO `district` VALUES ('2278', '端州', '330', 'd', 'dz', 'duanzhou', '', '区', '441202', '0758', '1');
INSERT INTO `district` VALUES ('2279', '鼎湖', '330', 'd', 'dh', 'dinghu', '', '区', '441203', '0758', '2');
INSERT INTO `district` VALUES ('2280', '广宁', '330', 'g', 'gn', 'guangning', '', '县', '441223', '0758', '3');
INSERT INTO `district` VALUES ('2281', '怀集', '330', 'h', 'hj', 'huaiji', '', '县', '441224', '0758', '4');
INSERT INTO `district` VALUES ('2282', '封开', '330', 'f', 'fk', 'fengkai', '', '县', '441225', '0758', '5');
INSERT INTO `district` VALUES ('2283', '德庆', '330', 'd', 'dq', 'deqing', '', '县', '441226', '0758', '6');
INSERT INTO `district` VALUES ('2284', '高要', '330', 'g', 'gy', 'gaoyao', '', '市', '441283', '0758', '7');
INSERT INTO `district` VALUES ('2285', '四会', '330', 's', 'sh', 'sihui', '', '市', '441284', '0758', '8');
INSERT INTO `district` VALUES ('2286', '惠城', '331', 'h', 'hc', 'huicheng', '', '区', '441302', '0752', '1');
INSERT INTO `district` VALUES ('2287', '惠阳', '331', 'h', 'hy', 'huiyang', '', '区', '441303', '0752', '2');
INSERT INTO `district` VALUES ('2288', '博罗', '331', 'b', 'bl', 'boluo', '', '县', '441322', '0752', '3');
INSERT INTO `district` VALUES ('2289', '惠东', '331', 'h', 'hd', 'huidong', '', '县', '441323', '0752', '4');
INSERT INTO `district` VALUES ('2290', '龙门', '331', 'l', 'lm', 'longmen', '', '县', '441324', '0752', '5');
INSERT INTO `district` VALUES ('2291', '梅江', '332', 'm', 'mj', 'meijiang', '', '区', '441402', '0753', '1');
INSERT INTO `district` VALUES ('2292', '梅县', '332', 'm', 'mx', 'meixian', '', '区', '441403', '0753', '2');
INSERT INTO `district` VALUES ('2293', '大埔', '332', 'd', 'dp', 'dapu', '', '县', '441422', '0753', '3');
INSERT INTO `district` VALUES ('2294', '丰顺', '332', 'f', 'fs', 'fengshun', '', '县', '441423', '0753', '4');
INSERT INTO `district` VALUES ('2295', '五华', '332', 'w', 'wh', 'wuhua', '', '县', '441424', '0753', '5');
INSERT INTO `district` VALUES ('2296', '平远', '332', 'p', 'py', 'pingyuan', '', '县', '441426', '0753', '6');
INSERT INTO `district` VALUES ('2297', '蕉岭', '332', 'j', 'jl', 'jiaoling', '', '县', '441427', '0753', '7');
INSERT INTO `district` VALUES ('2298', '兴宁', '332', 'x', 'xn', 'xingning', '', '市', '441481', '0753', '8');
INSERT INTO `district` VALUES ('2299', '城区', '333', 'c', 'cq', 'chengqu', '', '区', '441502', '0660', '1');
INSERT INTO `district` VALUES ('2300', '海丰', '333', 'h', 'hf', 'haifeng', '', '县', '441521', '0660', '2');
INSERT INTO `district` VALUES ('2301', '陆河', '333', 'l', 'lh', 'luhe', '', '县', '441523', '0660', '3');
INSERT INTO `district` VALUES ('2302', '陆丰', '333', 'l', 'lf', 'lufeng', '', '市', '441581', '0660', '4');
INSERT INTO `district` VALUES ('2303', '源城', '334', 'y', 'yc', 'yuancheng', '', '区', '441602', '0762', '1');
INSERT INTO `district` VALUES ('2304', '紫金', '334', 'z', 'zj', 'zijin', '', '县', '441621', '0762', '2');
INSERT INTO `district` VALUES ('2305', '龙川', '334', 'l', 'lc', 'longchuan', '', '县', '441622', '0762', '3');
INSERT INTO `district` VALUES ('2306', '连平', '334', 'l', 'lp', 'lianping', '', '县', '441623', '0762', '4');
INSERT INTO `district` VALUES ('2307', '和平', '334', 'h', 'hp', 'heping', '', '县', '441624', '0762', '5');
INSERT INTO `district` VALUES ('2308', '东源', '334', 'd', 'dy', 'dongyuan', '', '县', '441625', '0762', '6');
INSERT INTO `district` VALUES ('2309', '江城', '335', 'j', 'jc', 'jiangcheng', '', '区', '441702', '0662', '1');
INSERT INTO `district` VALUES ('2310', '阳西', '335', 'y', 'yx', 'yangxi', '', '县', '441721', '0662', '2');
INSERT INTO `district` VALUES ('2311', '阳东', '335', 'y', 'yd', 'yangdong', '', '区', '441723', '0662', '3');
INSERT INTO `district` VALUES ('2312', '阳春', '335', 'y', 'yc', 'yangchun', '', '市', '441781', '0662', '4');
INSERT INTO `district` VALUES ('2313', '清城', '336', 'q', 'qc', 'qingcheng', '', '区', '441802', '0763', '1');
INSERT INTO `district` VALUES ('2314', '佛冈', '336', 'f', 'fg', 'fogang', '', '县', '441821', '0763', '2');
INSERT INTO `district` VALUES ('2315', '阳山', '336', 'y', 'ys', 'yangshan', '', '县', '441823', '0763', '3');
INSERT INTO `district` VALUES ('2316', '连山', '336', 'l', 'ls', 'lianshan', '壮族瑶族', '自治县', '441825', '0763', '4');
INSERT INTO `district` VALUES ('2317', '连南', '336', 'l', 'ln', 'liannan', '瑶族', '自治县', '441826', '0763', '5');
INSERT INTO `district` VALUES ('2318', '清新', '336', 'q', 'qx', 'qingxin', '', '县', '441827', '0763', '6');
INSERT INTO `district` VALUES ('2319', '英德', '336', 'y', 'yd', 'yingde', '', '市', '441881', '0763', '7');
INSERT INTO `district` VALUES ('2320', '连州', '336', 'l', 'lz', 'lianzhou', '', '市', '441882', '0763', '8');
INSERT INTO `district` VALUES ('2321', '湘桥', '339', 'x', 'xq', 'xiangqiao', '', '区', '445102', '0768', '1');
INSERT INTO `district` VALUES ('2322', '潮安', '339', 'c', 'ca', 'chaoan', '', '区', '445121', '0768', '2');
INSERT INTO `district` VALUES ('2323', '饶平', '339', 'r', 'rp', 'raoping', '', '县', '445122', '0768', '3');
INSERT INTO `district` VALUES ('2324', '榕城', '340', 'r', 'rc', 'rongcheng', '', '区', '445202', '0663', '1');
INSERT INTO `district` VALUES ('2325', '揭东', '340', 'j', 'jd', 'jiedong', '', '县', '445221', '0663', '2');
INSERT INTO `district` VALUES ('2326', '揭西', '340', 'j', 'jx', 'jiexi', '', '县', '445222', '0663', '3');
INSERT INTO `district` VALUES ('2327', '惠来', '340', 'h', 'hl', 'huilai', '', '县', '445224', '0663', '4');
INSERT INTO `district` VALUES ('2328', '普宁', '340', 'p', 'pn', 'puning', '', '市', '445281', '0663', '5');
INSERT INTO `district` VALUES ('2329', '云城', '341', 'y', 'yc', 'yuncheng', '', '区', '445302', '0766', '1');
INSERT INTO `district` VALUES ('2330', '新兴', '341', 'x', 'xx', 'xinxing', '', '县', '445321', '0766', '3');
INSERT INTO `district` VALUES ('2331', '郁南', '341', 'y', 'yn', 'yunan', '', '县', '445322', '0766', '4');
INSERT INTO `district` VALUES ('2332', '云安', '341', 'y', 'ya', 'yunan', '', '区', '445302', '0766', '2');
INSERT INTO `district` VALUES ('2333', '罗定', '341', 'l', 'ld', 'luoding', '', '市', '445381', '0766', '5');
INSERT INTO `district` VALUES ('2334', '兴宁', '342', 'x', 'xn', 'xingning', '', '区', '450102', '0771', '1');
INSERT INTO `district` VALUES ('2335', '青秀', '342', 'q', 'qx', 'qingxiu', '', '区', '450103', '0771', '2');
INSERT INTO `district` VALUES ('2336', '江南', '342', 'j', 'jn', 'jiangnan', '', '区', '450105', '0771', '3');
INSERT INTO `district` VALUES ('2337', '西乡塘', '342', 'x', 'xxt', 'xixiangtang', '', '区', '450107', '0771', '4');
INSERT INTO `district` VALUES ('2338', '良庆', '342', 'l', 'lq', 'liangqing', '', '区', '450108', '0771', '5');
INSERT INTO `district` VALUES ('2339', '邕宁', '342', 'y', 'yn', 'yongning', '', '区', '450109', '0771', '6');
INSERT INTO `district` VALUES ('2340', '武鸣', '342', 'w', 'wm', 'wuming', '', '区', '450122', '0771', '7');
INSERT INTO `district` VALUES ('2341', '隆安', '342', 'l', 'la', 'longan', '', '县', '450123', '0771', '8');
INSERT INTO `district` VALUES ('2342', '马山', '342', 'm', 'ms', 'mashan', '', '县', '450124', '0771', '9');
INSERT INTO `district` VALUES ('2343', '上林', '342', 's', 'sl', 'shanglin', '', '县', '450125', '0771', '10');
INSERT INTO `district` VALUES ('2344', '宾阳', '342', 'b', 'by', 'binyang', '', '县', '450126', '0771', '11');
INSERT INTO `district` VALUES ('2345', '横县', '342', 'h', 'hx', 'hengxian', '', '', '450127', '0771', '12');
INSERT INTO `district` VALUES ('2346', '城中', '343', 'c', 'cz', 'chengzhong', '', '区', '450202', '0772', '1');
INSERT INTO `district` VALUES ('2347', '鱼峰', '343', 'y', 'yf', 'yufeng', '', '区', '450203', '0772', '2');
INSERT INTO `district` VALUES ('2348', '柳南', '343', 'l', 'ln', 'liunan', '', '区', '450204', '0772', '3');
INSERT INTO `district` VALUES ('2349', '柳北', '343', 'l', 'lb', 'liubei', '', '区', '450205', '0772', '4');
INSERT INTO `district` VALUES ('2350', '柳江', '343', 'l', 'lj', 'liujiang', '', '县', '450221', '0772', '5');
INSERT INTO `district` VALUES ('2351', '柳城', '343', 'l', 'lc', 'liucheng', '', '县', '450222', '0772', '6');
INSERT INTO `district` VALUES ('2352', '鹿寨', '343', 'l', 'lz', 'luzhai', '', '县', '450223', '0772', '7');
INSERT INTO `district` VALUES ('2353', '融安', '343', 'r', 'ra', 'rongan', '', '县', '450224', '0772', '8');
INSERT INTO `district` VALUES ('2354', '融水', '343', 'r', 'rs', 'rongshui', '苗族', '自治县', '450225', '0772', '9');
INSERT INTO `district` VALUES ('2355', '三江', '343', 's', 'sj', 'sanjiang', '侗族', '自治县', '450226', '0772', '10');
INSERT INTO `district` VALUES ('2356', '秀峰', '344', 'x', 'xf', 'xiufeng', '', '区', '450302', '0773', '1');
INSERT INTO `district` VALUES ('2357', '叠彩', '344', 'd', 'dc', 'diecai', '', '区', '450303', '0773', '2');
INSERT INTO `district` VALUES ('2358', '象山', '344', 'x', 'xs', 'xiangshan', '', '区', '450304', '0773', '3');
INSERT INTO `district` VALUES ('2359', '七星', '344', 'q', 'qx', 'qixing', '', '区', '450305', '0773', '4');
INSERT INTO `district` VALUES ('2360', '雁山', '344', 'y', 'ys', 'yanshan', '', '区', '450311', '0773', '5');
INSERT INTO `district` VALUES ('2361', '阳朔', '344', 'y', 'ys', 'yangshuo', '', '县', '450321', '0773', '6');
INSERT INTO `district` VALUES ('2362', '临桂', '344', 'l', 'lg', 'lingui', '', '区', '450322', '0773', '7');
INSERT INTO `district` VALUES ('2363', '灵川', '344', 'l', 'lc', 'lingchuan', '', '县', '450323', '0773', '8');
INSERT INTO `district` VALUES ('2364', '全州', '344', 'q', 'qz', 'quanzhou', '', '县', '450324', '0773', '9');
INSERT INTO `district` VALUES ('2365', '兴安', '344', 'x', 'xa', 'xingan', '', '县', '450325', '0773', '10');
INSERT INTO `district` VALUES ('2366', '永福', '344', 'y', 'yf', 'yongfu', '', '县', '450326', '0773', '11');
INSERT INTO `district` VALUES ('2367', '灌阳', '344', 'g', 'gy', 'guanyang', '', '县', '450327', '0773', '12');
INSERT INTO `district` VALUES ('2368', '龙胜', '344', 'l', 'ls', 'longsheng', '各族', '自治县', '450328', '0773', '13');
INSERT INTO `district` VALUES ('2369', '资源', '344', 'z', 'zy', 'ziyuan', '', '县', '450329', '0773', '14');
INSERT INTO `district` VALUES ('2370', '平乐', '344', 'p', 'pl', 'pingle', '', '县', '450330', '0773', '15');
INSERT INTO `district` VALUES ('2371', '荔浦', '344', 'l', 'lp', 'lipu', '', '县', '450331', '0773', '16');
INSERT INTO `district` VALUES ('2372', '恭城', '344', 'g', 'gc', 'gongcheng', '瑶族', '自治县', '450332', '0773', '17');
INSERT INTO `district` VALUES ('2373', '龙圩', '345', 'l', 'lw', 'longwei', '', '区', '', '0774', '1');
INSERT INTO `district` VALUES ('2374', '万秀', '345', 'w', 'wx', 'wanxiu', '', '区', '450403', '0774', '2');
INSERT INTO `district` VALUES ('2375', '长洲', '345', 'z', 'zz', 'zhangzhou', '', '区', '450405', '0774', '3');
INSERT INTO `district` VALUES ('2376', '苍梧', '345', 'c', 'cw', 'cangwu', '', '县', '450421', '0774', '4');
INSERT INTO `district` VALUES ('2377', '藤县', '345', 't', 'tx', 'tengxian', '', '', '450422', '0774', '5');
INSERT INTO `district` VALUES ('2378', '蒙山', '345', 'm', 'ms', 'mengshan', '', '县', '450423', '0774', '6');
INSERT INTO `district` VALUES ('2379', '岑溪', '345', 'c', 'cx', 'cenxi', '', '市', '450481', '0774', '7');
INSERT INTO `district` VALUES ('2380', '海城', '346', 'h', 'hc', 'haicheng', '', '区', '450502', '0779', '1');
INSERT INTO `district` VALUES ('2381', '银海', '346', 'y', 'yh', 'yinhai', '', '区', '450503', '0779', '2');
INSERT INTO `district` VALUES ('2382', '铁山港', '346', 't', 'tsg', 'tieshangang', '', '区', '450512', '0779', '3');
INSERT INTO `district` VALUES ('2383', '合浦', '346', 'h', 'hp', 'hepu', '', '县', '450521', '0779', '4');
INSERT INTO `district` VALUES ('2384', '港口', '347', 'g', 'gk', 'gangkou', '', '区', '450602', '0770', '1');
INSERT INTO `district` VALUES ('2385', '防城', '347', 'f', 'fc', 'fangcheng', '', '区', '450603', '0770', '2');
INSERT INTO `district` VALUES ('2386', '上思', '347', 's', 'ss', 'shangsi', '', '县', '450621', '0770', '3');
INSERT INTO `district` VALUES ('2387', '东兴', '347', 'd', 'dx', 'dongxing', '', '市', '450681', '0770', '4');
INSERT INTO `district` VALUES ('2388', '钦南', '348', 'q', 'qn', 'qinnan', '', '区', '450702', '0777', '1');
INSERT INTO `district` VALUES ('2389', '钦北', '348', 'q', 'qb', 'qinbei', '', '区', '450703', '0777', '2');
INSERT INTO `district` VALUES ('2390', '灵山', '348', 'l', 'ls', 'lingshan', '', '县', '450721', '0777', '3');
INSERT INTO `district` VALUES ('2391', '浦北', '348', 'p', 'pb', 'pubei', '', '县', '450722', '0777', '4');
INSERT INTO `district` VALUES ('2392', '港北', '349', 'g', 'gb', 'gangbei', '', '区', '450802', '0775', '1');
INSERT INTO `district` VALUES ('2393', '港南', '349', 'g', 'gn', 'gangnan', '', '区', '450803', '0775', '2');
INSERT INTO `district` VALUES ('2394', '覃塘', '349', 't', 'tt', 'tantang', '', '区', '450804', '0775', '3');
INSERT INTO `district` VALUES ('2395', '桂平', '349', 'g', 'gp', 'guiping', '', '市', '450821', '0775', '4');
INSERT INTO `district` VALUES ('2396', '平南', '349', 'p', 'pn', 'pingnan', '', '县', '450881', '0775', '5');
INSERT INTO `district` VALUES ('2397', '玉州', '350', 'y', 'yz', 'yuzhou', '', '区', '450902', '0775', '1');
INSERT INTO `district` VALUES ('2398', '容县', '350', 'r', 'rx', 'rongxian', '', '', '450921', '0775', '3');
INSERT INTO `district` VALUES ('2399', '陆川', '350', 'l', 'lc', 'luchuan', '', '县', '450922', '0775', '4');
INSERT INTO `district` VALUES ('2400', '博白', '350', 'b', 'bb', 'bobai', '', '县', '450923', '0775', '5');
INSERT INTO `district` VALUES ('2401', '兴业', '350', 'x', 'xy', 'xingye', '', '县', '450924', '0775', '6');
INSERT INTO `district` VALUES ('2402', '北流', '350', 'b', 'bl', 'beiliu', '', '市', '450981', '0775', '7');
INSERT INTO `district` VALUES ('2403', '右江', '351', 'y', 'yj', 'youjiang', '', '区', '451002', '0776', '1');
INSERT INTO `district` VALUES ('2404', '田阳', '351', 't', 'ty', 'tianyang', '', '县', '451021', '0776', '2');
INSERT INTO `district` VALUES ('2405', '田东', '351', 't', 'td', 'tiandong', '', '县', '451022', '0776', '3');
INSERT INTO `district` VALUES ('2406', '平果', '351', 'p', 'pg', 'pingguo', '', '县', '451023', '0776', '4');
INSERT INTO `district` VALUES ('2407', '德保', '351', 'd', 'db', 'debao', '', '县', '451024', '0776', '5');
INSERT INTO `district` VALUES ('2408', '靖西', '351', 'j', 'jx', 'jingxi', '', '县', '451025', '0776', '6');
INSERT INTO `district` VALUES ('2409', '那坡', '351', 'n', 'np', 'neipo', '', '县', '451026', '0776', '7');
INSERT INTO `district` VALUES ('2410', '凌云', '351', 'l', 'ly', 'lingyun', '', '县', '451027', '0776', '8');
INSERT INTO `district` VALUES ('2411', '乐业', '351', 'l', 'ly', 'leye', '', '县', '451028', '0776', '9');
INSERT INTO `district` VALUES ('2412', '田林', '351', 't', 'tl', 'tianlin', '', '县', '451029', '0776', '10');
INSERT INTO `district` VALUES ('2413', '西林', '351', 'x', 'xl', 'xilin', '', '县', '451030', '0776', '11');
INSERT INTO `district` VALUES ('2414', '隆林', '351', 'l', 'll', 'longlin', '各族', '自治县', '451031', '0776', '12');
INSERT INTO `district` VALUES ('2415', '八步', '352', 'b', 'bb', 'babu', '', '区', '451102', '0774', '1');
INSERT INTO `district` VALUES ('2416', '昭平', '352', 'z', 'zp', 'zhaoping', '', '县', '451121', '0774', '2');
INSERT INTO `district` VALUES ('2417', '钟山', '352', 'z', 'zs', 'zhongshan', '', '县', '451122', '0774', '3');
INSERT INTO `district` VALUES ('2418', '富川', '352', 'f', 'fc', 'fuchuan', '瑶族', '自治县', '451123', '0774', '4');
INSERT INTO `district` VALUES ('2419', '金城江', '353', 'j', 'jcj', 'jinchengjiang', '', '区', '451202', '0778', '1');
INSERT INTO `district` VALUES ('2420', '南丹', '353', 'n', 'nd', 'nandan', '', '县', '451221', '0778', '2');
INSERT INTO `district` VALUES ('2421', '天峨', '353', 't', 'te', 'tiane', '', '县', '451222', '0778', '3');
INSERT INTO `district` VALUES ('2422', '凤山', '353', 'f', 'fs', 'fengshan', '', '县', '451223', '0778', '4');
INSERT INTO `district` VALUES ('2423', '东兰', '353', 'd', 'dl', 'donglan', '', '县', '451224', '0778', '5');
INSERT INTO `district` VALUES ('2424', '罗城', '353', 'l', 'lc', 'luocheng', '仫佬族', '自治县', '451225', '0778', '6');
INSERT INTO `district` VALUES ('2425', '环江', '353', 'h', 'hj', 'huanjiang', '毛南族', '自治县', '451226', '0778', '7');
INSERT INTO `district` VALUES ('2426', '巴马', '353', 'b', 'bm', 'bama', '瑶族', '自治县', '451227', '0778', '8');
INSERT INTO `district` VALUES ('2427', '都安', '353', 'd', 'da', 'douan', '瑶族', '自治县', '451228', '0778', '9');
INSERT INTO `district` VALUES ('2428', '大化', '353', 'd', 'dh', 'dahua', '瑶族', '自治县', '451229', '0778', '10');
INSERT INTO `district` VALUES ('2429', '宜州', '353', 'y', 'yz', 'yizhou', '', '市', '451281', '0778', '11');
INSERT INTO `district` VALUES ('2430', '兴宾', '354', 'x', 'xb', 'xingbin', '', '区', '451302', '0772', '1');
INSERT INTO `district` VALUES ('2431', '忻城', '354', 'x', 'xc', 'xincheng', '', '县', '451321', '0772', '2');
INSERT INTO `district` VALUES ('2432', '象州', '354', 'x', 'xz', 'xiangzhou', '', '县', '451322', '0772', '3');
INSERT INTO `district` VALUES ('2433', '武宣', '354', 'w', 'wx', 'wuxuan', '', '县', '451323', '0772', '4');
INSERT INTO `district` VALUES ('2434', '金秀', '354', 'j', 'jx', 'jinxiu', '瑶族', '自治县', '451324', '0772', '5');
INSERT INTO `district` VALUES ('2435', '合山', '354', 'h', 'hs', 'heshan', '', '市', '451381', '0772', '6');
INSERT INTO `district` VALUES ('2436', '江州', '355', 'j', 'jz', 'jiangzhou', '', '区', '451402', '0771', '1');
INSERT INTO `district` VALUES ('2437', '扶绥', '355', 'f', 'fs', 'fusui', '', '县', '451421', '0771', '2');
INSERT INTO `district` VALUES ('2438', '宁明', '355', 'n', 'nm', 'ningming', '', '县', '451422', '0771', '3');
INSERT INTO `district` VALUES ('2439', '龙州', '355', 'l', 'lz', 'longzhou', '', '县', '451423', '0771', '4');
INSERT INTO `district` VALUES ('2440', '大新', '355', 'd', 'dx', 'daxin', '', '县', '451424', '0771', '5');
INSERT INTO `district` VALUES ('2441', '天等', '355', 't', 'td', 'tiandeng', '', '县', '451425', '0771', '6');
INSERT INTO `district` VALUES ('2442', '凭祥', '355', 'p', 'px', 'pingxiang', '', '市', '451481', '0771', '7');
INSERT INTO `district` VALUES ('2443', '秀英', '356', 'x', 'xy', 'xiuying', '', '区', '460105', '0898', '1');
INSERT INTO `district` VALUES ('2444', '龙华', '356', 'l', 'lh', 'longhua', '', '区', '460106', '0898', '2');
INSERT INTO `district` VALUES ('2445', '琼山', '356', 'q', 'qs', 'qiongshan', '', '区', '460107', '0898', '3');
INSERT INTO `district` VALUES ('2446', '美兰', '356', 'm', 'ml', 'meilan', '', '区', '460108', '0898', '4');
INSERT INTO `district` VALUES ('2447', '锦江', '375', 'j', 'jj', 'jinjiang', '', '区', '510104', '028', '1');
INSERT INTO `district` VALUES ('2448', '青羊', '375', 'q', 'qy', 'qingyang', '', '区', '510105', '028', '2');
INSERT INTO `district` VALUES ('2449', '金牛', '375', 'j', 'jn', 'jinniu', '', '区', '510106', '028', '3');
INSERT INTO `district` VALUES ('2450', '武侯', '375', 'w', 'wh', 'wuhou', '', '区', '510107', '028', '4');
INSERT INTO `district` VALUES ('2451', '成华', '375', 'c', 'ch', 'chenghua', '', '区', '510108', '028', '5');
INSERT INTO `district` VALUES ('2452', '龙泉驿', '375', 'l', 'lqy', 'longquanyi', '', '区', '510112', '028', '6');
INSERT INTO `district` VALUES ('2453', '青白江', '375', 'q', 'qbj', 'qingbaijiang', '', '区', '510113', '028', '7');
INSERT INTO `district` VALUES ('2454', '新都', '375', 'x', 'xd', 'xindou', '', '区', '510114', '028', '8');
INSERT INTO `district` VALUES ('2455', '温江', '375', 'w', 'wj', 'wenjiang', '', '区', '510115', '028', '9');
INSERT INTO `district` VALUES ('2456', '金堂', '375', 'j', 'jt', 'jintang', '', '县', '510121', '028', '10');
INSERT INTO `district` VALUES ('2457', '双流', '375', 's', 'sl', 'shuangliu', '', '县', '510122', '028', '11');
INSERT INTO `district` VALUES ('2458', '郫县', '375', 'p', 'px', 'pixian', '', '', '510124', '028', '12');
INSERT INTO `district` VALUES ('2459', '大邑', '375', 'd', 'dy', 'dayi', '', '县', '510129', '028', '13');
INSERT INTO `district` VALUES ('2460', '蒲江', '375', 'p', 'pj', 'pujiang', '', '县', '510131', '028', '14');
INSERT INTO `district` VALUES ('2461', '新津', '375', 'x', 'xj', 'xinjin', '', '县', '510132', '028', '15');
INSERT INTO `district` VALUES ('2462', '都江堰', '375', 'd', 'djy', 'doujiangyan', '', '市', '510181', '028', '16');
INSERT INTO `district` VALUES ('2463', '彭州', '375', 'p', 'pz', 'pengzhou', '', '市', '510182', '028', '17');
INSERT INTO `district` VALUES ('2464', '邛崃', '375', 'q', 'ql', 'qionglai', '', '市', '510183', '028', '18');
INSERT INTO `district` VALUES ('2465', '崇州', '375', 'c', 'cz', 'chongzhou', '', '市', '510184', '028', '19');
INSERT INTO `district` VALUES ('2466', '自流井', '376', 'z', 'zlj', 'ziliujing', '', '区', '510302', '0813', '1');
INSERT INTO `district` VALUES ('2467', '贡井', '376', 'g', 'gj', 'gongjing', '', '区', '510303', '0813', '2');
INSERT INTO `district` VALUES ('2468', '大安', '376', 'd', 'da', 'daan', '', '区', '510304', '0813', '3');
INSERT INTO `district` VALUES ('2469', '沿滩', '376', 'y', 'yt', 'yantan', '', '区', '510311', '0813', '4');
INSERT INTO `district` VALUES ('2470', '荣县', '376', 'r', 'rx', 'rongxian', '', '', '510321', '0813', '5');
INSERT INTO `district` VALUES ('2471', '富顺', '376', 'f', 'fs', 'fushun', '', '县', '510322', '0813', '6');
INSERT INTO `district` VALUES ('2472', '东区', '377', 'd', 'dq', 'dongqu', '', '', '510402', '0812', '1');
INSERT INTO `district` VALUES ('2473', '西区', '377', 'x', 'xq', 'xiqu', '', '', '510403', '0812', '2');
INSERT INTO `district` VALUES ('2474', '仁和', '377', 'r', 'rh', 'renhe', '', '区', '510411', '0812', '3');
INSERT INTO `district` VALUES ('2475', '米易', '377', 'm', 'my', 'miyi', '', '县', '510421', '0812', '4');
INSERT INTO `district` VALUES ('2476', '盐边', '377', 'y', 'yb', 'yanbian', '', '县', '510422', '0812', '5');
INSERT INTO `district` VALUES ('2477', '江阳', '378', 'j', 'jy', 'jiangyang', '', '区', '510502', '0830', '1');
INSERT INTO `district` VALUES ('2478', '纳溪', '378', 'n', 'nx', 'naxi', '', '区', '510503', '0830', '2');
INSERT INTO `district` VALUES ('2479', '龙马潭', '378', 'l', 'lmt', 'longmatan', '', '区', '510504', '0830', '3');
INSERT INTO `district` VALUES ('2480', '泸县', '378', 'l', 'lx', 'luxian', '', '', '510521', '0830', '4');
INSERT INTO `district` VALUES ('2481', '合江', '378', 'h', 'hj', 'hejiang', '', '县', '510522', '0830', '5');
INSERT INTO `district` VALUES ('2482', '叙永', '378', 'x', 'xy', 'xuyong', '', '县', '510524', '0830', '6');
INSERT INTO `district` VALUES ('2483', '古蔺', '378', 'g', 'gl', 'gulin', '', '县', '510525', '0830', '7');
INSERT INTO `district` VALUES ('2484', '旌阳', '379', 'j', 'jy', 'jingyang', '', '区', '510603', '0838', '1');
INSERT INTO `district` VALUES ('2485', '中江', '379', 'z', 'zj', 'zhongjiang', '', '县', '510623', '0838', '2');
INSERT INTO `district` VALUES ('2486', '罗江', '379', 'l', 'lj', 'luojiang', '', '县', '510626', '0838', '3');
INSERT INTO `district` VALUES ('2487', '广汉', '379', 'g', 'gh', 'guanghan', '', '市', '510681', '0838', '4');
INSERT INTO `district` VALUES ('2488', '什邡', '379', 's', 'sf', 'shenfang', '', '市', '510682', '0838', '5');
INSERT INTO `district` VALUES ('2489', '绵竹', '379', 'm', 'mz', 'mianzhu', '', '市', '510683', '0838', '6');
INSERT INTO `district` VALUES ('2490', '涪城', '380', 'f', 'fc', 'fucheng', '', '区', '510703', '0816', '1');
INSERT INTO `district` VALUES ('2491', '游仙', '380', 'y', 'yx', 'youxian', '', '区', '510704', '0816', '2');
INSERT INTO `district` VALUES ('2492', '三台', '380', 's', 'st', 'santai', '', '县', '510722', '0816', '3');
INSERT INTO `district` VALUES ('2493', '盐亭', '380', 'y', 'yt', 'yanting', '', '县', '510723', '0816', '4');
INSERT INTO `district` VALUES ('2494', '安县', '380', 'a', 'ax', 'anxian', '', '', '510724', '0816', '5');
INSERT INTO `district` VALUES ('2495', '梓潼', '380', 'z', 'zt', 'zitong', '', '县', '510725', '0816', '6');
INSERT INTO `district` VALUES ('2496', '北川', '380', 'b', 'bc', 'beichuan', '羌族', '自治县', '510726', '0816', '7');
INSERT INTO `district` VALUES ('2497', '平武', '380', 'p', 'pw', 'pingwu', '', '县', '510727', '0816', '8');
INSERT INTO `district` VALUES ('2498', '江油', '380', 'j', 'jy', 'jiangyou', '', '市', '510781', '0816', '9');
INSERT INTO `district` VALUES ('2499', '利州', '381', 'l', 'lz', 'lizhou', '', '区', '510802', '0839', '1');
INSERT INTO `district` VALUES ('2500', '昭化', '381', 'z', 'zh', 'zhaohua', '', '区', '510811', '0839', '2');
INSERT INTO `district` VALUES ('2501', '朝天', '381', 'c', 'ct', 'chaotian', '', '区', '510812', '0839', '3');
INSERT INTO `district` VALUES ('2502', '旺苍', '381', 'w', 'wc', 'wangcang', '', '县', '510821', '0839', '4');
INSERT INTO `district` VALUES ('2503', '青川', '381', 'q', 'qc', 'qingchuan', '', '县', '510822', '0839', '5');
INSERT INTO `district` VALUES ('2504', '剑阁', '381', 'j', 'jg', 'jiange', '', '县', '510823', '0839', '6');
INSERT INTO `district` VALUES ('2505', '苍溪', '381', 'c', 'cx', 'cangxi', '', '县', '510824', '0839', '7');
INSERT INTO `district` VALUES ('2506', '船山', '382', 'c', 'cs', 'chuanshan', '', '区', '510903', '0825', '1');
INSERT INTO `district` VALUES ('2507', '安居', '382', 'a', 'aj', 'anju', '', '区', '510904', '0825', '2');
INSERT INTO `district` VALUES ('2508', '蓬溪', '382', 'p', 'px', 'pengxi', '', '县', '510921', '0825', '3');
INSERT INTO `district` VALUES ('2509', '射洪', '382', 's', 'sh', 'shehong', '', '县', '510922', '0825', '4');
INSERT INTO `district` VALUES ('2510', '大英', '382', 'd', 'dy', 'daying', '', '县', '510923', '0825', '5');
INSERT INTO `district` VALUES ('2511', '市中', '383', 's', 'sz', 'shizhong', '', '区', '511002', '', '1');
INSERT INTO `district` VALUES ('2512', '东兴', '383', 'd', 'dx', 'dongxing', '', '区', '511011', '', '2');
INSERT INTO `district` VALUES ('2513', '威远', '383', 'w', 'wy', 'weiyuan', '', '县', '511024', '', '3');
INSERT INTO `district` VALUES ('2514', '资中', '383', 'z', 'zz', 'zizhong', '', '县', '511025', '', '4');
INSERT INTO `district` VALUES ('2515', '隆昌', '383', 'l', 'lc', 'longchang', '', '县', '511028', '', '5');
INSERT INTO `district` VALUES ('2516', '市中', '384', 's', 'sz', 'shizhong', '', '区', '511102', '0833', '1');
INSERT INTO `district` VALUES ('2517', '沙湾', '384', 's', 'sw', 'shawan', '', '区', '511111', '0833', '2');
INSERT INTO `district` VALUES ('2518', '五通桥', '384', 'w', 'wtq', 'wutongqiao', '', '区', '511112', '0833', '3');
INSERT INTO `district` VALUES ('2519', '金口河', '384', 'j', 'jkh', 'jinkouhe', '', '区', '511113', '0833', '4');
INSERT INTO `district` VALUES ('2520', '犍为', '384', 'j', 'jw', 'jianwei', '', '县', '511123', '0833', '5');
INSERT INTO `district` VALUES ('2521', '井研', '384', 'j', 'jy', 'jingyan', '', '县', '511124', '0833', '6');
INSERT INTO `district` VALUES ('2522', '夹江', '384', 'j', 'jj', 'jiajiang', '', '县', '511126', '0833', '7');
INSERT INTO `district` VALUES ('2523', '沐川', '384', 'm', 'mc', 'muchuan', '', '县', '511129', '0833', '8');
INSERT INTO `district` VALUES ('2524', '峨边', '384', 'e', 'eb', 'ebian', '彝族', '自治县', '511132', '0833', '9');
INSERT INTO `district` VALUES ('2525', '马边', '384', 'm', 'mb', 'mabian', '彝族', '自治县', '511133', '0833', '10');
INSERT INTO `district` VALUES ('2526', '峨眉山', '384', 'e', 'ems', 'emeishan', '', '市', '511181', '0833', '11');
INSERT INTO `district` VALUES ('2527', '顺庆', '385', 's', 'sq', 'shunqing', '', '区', '511302', '0817', '1');
INSERT INTO `district` VALUES ('2528', '高坪', '385', 'g', 'gp', 'gaoping', '', '区', '511303', '0817', '2');
INSERT INTO `district` VALUES ('2529', '嘉陵', '385', 'j', 'jl', 'jialing', '', '区', '511304', '0817', '3');
INSERT INTO `district` VALUES ('2530', '南部', '385', 'n', 'nb', 'nanbu', '', '县', '511321', '0817', '4');
INSERT INTO `district` VALUES ('2531', '营山', '385', 'y', 'ys', 'yingshan', '', '县', '511322', '0817', '5');
INSERT INTO `district` VALUES ('2532', '蓬安', '385', 'p', 'pa', 'pengan', '', '县', '511323', '0817', '6');
INSERT INTO `district` VALUES ('2533', '仪陇', '385', 'y', 'yl', 'yilong', '', '县', '511324', '0817', '7');
INSERT INTO `district` VALUES ('2534', '西充', '385', 'x', 'xc', 'xichong', '', '县', '511325', '0817', '8');
INSERT INTO `district` VALUES ('2535', '阆中', '385', 'l', 'lz', 'langzhong', '', '市', '511381', '0817', '9');
INSERT INTO `district` VALUES ('2536', '东坡', '386', 'd', 'dp', 'dongpo', '', '区', '511402', '028', '1');
INSERT INTO `district` VALUES ('2537', '仁寿', '386', 'r', 'rs', 'renshou', '', '县', '511421', '028', '2');
INSERT INTO `district` VALUES ('2538', '彭山', '386', 'p', 'ps', 'pengshan', '', '区', '511422', '028', '3');
INSERT INTO `district` VALUES ('2539', '洪雅', '386', 'h', 'hy', 'hongya', '', '县', '511423', '028', '4');
INSERT INTO `district` VALUES ('2540', '丹棱', '386', 'd', 'dl', 'danleng', '', '县', '511424', '028', '5');
INSERT INTO `district` VALUES ('2541', '青神', '386', 'q', 'qs', 'qingshen', '', '县', '511425', '028', '6');
INSERT INTO `district` VALUES ('2542', '翠屏', '387', 'c', 'cp', 'cuiping', '', '区', '511502', '0831', '1');
INSERT INTO `district` VALUES ('2543', '南溪', '387', 'n', 'nx', 'nanxi', '', '区', '511503', '0831', '2');
INSERT INTO `district` VALUES ('2544', '宜宾', '387', 'y', 'yb', 'yibin', '', '县', '511521', '0831', '3');
INSERT INTO `district` VALUES ('2545', '江安', '387', 'j', 'ja', 'jiangan', '', '县', '511523', '0831', '4');
INSERT INTO `district` VALUES ('2546', '长宁', '387', 'z', 'zn', 'zhangning', '', '县', '511524', '0831', '5');
INSERT INTO `district` VALUES ('2547', '高县', '387', 'g', 'gx', 'gaoxian', '', '', '511525', '0831', '6');
INSERT INTO `district` VALUES ('2548', '珙县', '387', 'g', 'gx', 'gongxian', '', '', '511526', '0831', '7');
INSERT INTO `district` VALUES ('2549', '筠连', '387', 'y', 'yl', 'yunlian', '', '县', '511527', '0831', '8');
INSERT INTO `district` VALUES ('2550', '兴文', '387', 'x', 'xw', 'xingwen', '', '县', '511528', '0831', '9');
INSERT INTO `district` VALUES ('2551', '屏山', '387', 'p', 'ps', 'pingshan', '', '县', '511529', '0831', '10');
INSERT INTO `district` VALUES ('2552', '广安', '388', 'g', 'ga', 'guangan', '', '区', '511602', '0826', '1');
INSERT INTO `district` VALUES ('2553', '岳池', '388', 'y', 'yc', 'yuechi', '', '县', '511621', '0826', '3');
INSERT INTO `district` VALUES ('2554', '武胜', '388', 'w', 'ws', 'wusheng', '', '县', '511622', '0826', '4');
INSERT INTO `district` VALUES ('2555', '邻水', '388', 'l', 'ls', 'linshui', '', '县', '511623', '0826', '5');
INSERT INTO `district` VALUES ('2556', '华蓥', '388', 'h', 'hy', 'huaying', '', '市', '511681', '0826', '6');
INSERT INTO `district` VALUES ('2557', '通川', '389', 't', 'tc', 'tongchuan', '', '区', '511702', '0818', '1');
INSERT INTO `district` VALUES ('2558', '达川', '389', 'd', 'dc', 'dachuan', '', '区', '511721', '0818', '2');
INSERT INTO `district` VALUES ('2559', '宣汉', '389', 'x', 'xh', 'xuanhan', '', '县', '511722', '0818', '3');
INSERT INTO `district` VALUES ('2560', '开江', '389', 'k', 'kj', 'kaijiang', '', '县', '511723', '0818', '4');
INSERT INTO `district` VALUES ('2561', '大竹', '389', 'd', 'dz', 'dazhu', '', '县', '511724', '0818', '5');
INSERT INTO `district` VALUES ('2562', '渠县', '389', 'q', 'qx', 'quxian', '', '', '511725', '0818', '6');
INSERT INTO `district` VALUES ('2563', '万源', '389', 'w', 'wy', 'wanyuan', '', '市', '511781', '0818', '7');
INSERT INTO `district` VALUES ('2564', '雨城', '390', 'y', 'yc', 'yucheng', '', '区', '511802', '0835', '1');
INSERT INTO `district` VALUES ('2565', '名山', '390', 'm', 'ms', 'mingshan', '', '区', '511803', '0835', '2');
INSERT INTO `district` VALUES ('2566', '荥经', '390', 'y', 'yj', 'yingjing', '', '县', '511822', '0835', '3');
INSERT INTO `district` VALUES ('2567', '汉源', '390', 'h', 'hy', 'hanyuan', '', '县', '511823', '0835', '4');
INSERT INTO `district` VALUES ('2568', '石棉', '390', 's', 'sm', 'shimian', '', '县', '511824', '0835', '5');
INSERT INTO `district` VALUES ('2569', '天全', '390', 't', 'tq', 'tianquan', '', '县', '511825', '0835', '6');
INSERT INTO `district` VALUES ('2570', '芦山', '390', 'l', 'ls', 'lushan', '', '县', '511826', '0835', '7');
INSERT INTO `district` VALUES ('2571', '宝兴', '390', 'b', 'bx', 'baoxing', '', '县', '511827', '0835', '8');
INSERT INTO `district` VALUES ('2572', '巴州', '391', 'b', 'bz', 'bazhou', '', '区', '511902', '0827', '1');
INSERT INTO `district` VALUES ('2573', '通江', '391', 't', 'tj', 'tongjiang', '', '县', '511921', '0827', '2');
INSERT INTO `district` VALUES ('2574', '南江', '391', 'n', 'nj', 'nanjiang', '', '县', '511922', '0827', '3');
INSERT INTO `district` VALUES ('2575', '平昌', '391', 'p', 'pc', 'pingchang', '', '县', '511923', '0827', '4');
INSERT INTO `district` VALUES ('2576', '雁江', '392', 'y', 'yj', 'yanjiang', '', '区', '512002', '028', '1');
INSERT INTO `district` VALUES ('2577', '安岳', '392', 'a', 'ay', 'anyue', '', '县', '512021', '028', '2');
INSERT INTO `district` VALUES ('2578', '乐至', '392', 'l', 'lz', 'lezhi', '', '县', '512022', '028', '3');
INSERT INTO `district` VALUES ('2579', '简阳', '392', 'j', 'jy', 'jianyang', '', '市', '512081', '028', '4');
INSERT INTO `district` VALUES ('2580', '马尔康', '393', 'm', 'mek', 'maerkang', '', '县', '513229', '0837', '1');
INSERT INTO `district` VALUES ('2581', '汶川', '393', 'w', 'wc', 'wenchuan', '', '县', '513221', '0837', '2');
INSERT INTO `district` VALUES ('2582', '理县', '393', 'l', 'lx', 'lixian', '', '', '513222', '0837', '3');
INSERT INTO `district` VALUES ('2583', '茂县', '393', 'm', 'mx', 'maoxian', '', '', '513223', '0837', '4');
INSERT INTO `district` VALUES ('2584', '松潘', '393', 's', 'sp', 'songpan', '', '县', '513224', '0837', '5');
INSERT INTO `district` VALUES ('2585', '九寨沟', '393', 'j', 'jzg', 'jiuzhaigou', '', '县', '513225', '0837', '6');
INSERT INTO `district` VALUES ('2586', '金川', '393', 'j', 'jc', 'jinchuan', '', '县', '513226', '0837', '7');
INSERT INTO `district` VALUES ('2587', '小金', '393', 'x', 'xj', 'xiaojin', '', '县', '513227', '0837', '8');
INSERT INTO `district` VALUES ('2588', '黑水', '393', 'h', 'hs', 'heishui', '', '县', '513228', '0837', '9');
INSERT INTO `district` VALUES ('2589', '壤塘', '393', 'r', 'rt', 'rangtang', '', '县', '513230', '0837', '10');
INSERT INTO `district` VALUES ('2590', '阿坝', '393', 'a', 'ab', 'aba', '', '县', '513231', '0837', '11');
INSERT INTO `district` VALUES ('2591', '若尔盖', '393', 'r', 'reg', 'ruoergai', '', '县', '513232', '0837', '12');
INSERT INTO `district` VALUES ('2592', '红原', '393', 'h', 'hy', 'hongyuan', '', '县', '513233', '0837', '13');
INSERT INTO `district` VALUES ('2593', '康定', '394', 'k', 'kd', 'kangding', '', '市', '513301', '0836', '1');
INSERT INTO `district` VALUES ('2594', '泸定', '394', 'l', 'ld', 'luding', '', '县', '513322', '0836', '2');
INSERT INTO `district` VALUES ('2595', '丹巴', '394', 'd', 'db', 'danba', '', '县', '513323', '0836', '3');
INSERT INTO `district` VALUES ('2596', '九龙', '394', 'j', 'jl', 'jiulong', '', '县', '513324', '0836', '4');
INSERT INTO `district` VALUES ('2597', '雅江', '394', 'y', 'yj', 'yajiang', '', '县', '513325', '0836', '5');
INSERT INTO `district` VALUES ('2598', '道孚', '394', 'd', 'df', 'daofu', '', '县', '513326', '0836', '6');
INSERT INTO `district` VALUES ('2599', '炉霍', '394', 'l', 'lh', 'luhuo', '', '县', '513327', '0836', '7');
INSERT INTO `district` VALUES ('2600', '甘孜', '394', 'g', 'gz', 'ganzi', '', '县', '513328', '0836', '8');
INSERT INTO `district` VALUES ('2601', '新龙', '394', 'x', 'xl', 'xinlong', '', '县', '513329', '0836', '9');
INSERT INTO `district` VALUES ('2602', '德格', '394', 'd', 'dg', 'dege', '', '县', '513330', '0836', '10');
INSERT INTO `district` VALUES ('2603', '白玉', '394', 'b', 'by', 'baiyu', '', '县', '513331', '0836', '11');
INSERT INTO `district` VALUES ('2604', '石渠', '394', 's', 'sq', 'shiqu', '', '县', '513332', '0836', '12');
INSERT INTO `district` VALUES ('2605', '色达', '394', 's', 'sd', 'seda', '', '县', '513333', '0836', '13');
INSERT INTO `district` VALUES ('2606', '理塘', '394', 'l', 'lt', 'litang', '', '县', '513334', '0836', '14');
INSERT INTO `district` VALUES ('2607', '巴塘', '394', 'b', 'bt', 'batang', '', '县', '513335', '0836', '15');
INSERT INTO `district` VALUES ('2608', '乡城', '394', 'x', 'xc', 'xiangcheng', '', '县', '513336', '0836', '16');
INSERT INTO `district` VALUES ('2609', '稻城', '394', 'd', 'dc', 'daocheng', '', '县', '513337', '0836', '17');
INSERT INTO `district` VALUES ('2610', '得荣', '394', 'd', 'dr', 'derong', '', '县', '513338', '0836', '18');
INSERT INTO `district` VALUES ('2611', '西昌', '395', 'x', 'xc', 'xichang', '', '市', '513401', '0834', '1');
INSERT INTO `district` VALUES ('2612', '木里', '395', 'm', 'ml', 'muli', '藏族', '自治县', '513422', '0834', '2');
INSERT INTO `district` VALUES ('2613', '盐源', '395', 'y', 'yy', 'yanyuan', '', '县', '513423', '0834', '3');
INSERT INTO `district` VALUES ('2614', '德昌', '395', 'd', 'dc', 'dechang', '', '县', '513424', '0834', '4');
INSERT INTO `district` VALUES ('2615', '会理', '395', 'h', 'hl', 'huili', '', '县', '513425', '0834', '5');
INSERT INTO `district` VALUES ('2616', '会东', '395', 'h', 'hd', 'huidong', '', '县', '513426', '0834', '6');
INSERT INTO `district` VALUES ('2617', '宁南', '395', 'n', 'nn', 'ningnan', '', '县', '513427', '0834', '7');
INSERT INTO `district` VALUES ('2618', '普格', '395', 'p', 'pg', 'puge', '', '县', '513428', '0834', '8');
INSERT INTO `district` VALUES ('2619', '布拖', '395', 'b', 'bt', 'butuo', '', '县', '513429', '0834', '9');
INSERT INTO `district` VALUES ('2620', '金阳', '395', 'j', 'jy', 'jinyang', '', '县', '513430', '0834', '10');
INSERT INTO `district` VALUES ('2621', '昭觉', '395', 'z', 'zj', 'zhaojue', '', '县', '513431', '0834', '11');
INSERT INTO `district` VALUES ('2622', '喜德', '395', 'x', 'xd', 'xide', '', '县', '513432', '0834', '12');
INSERT INTO `district` VALUES ('2623', '冕宁', '395', 'm', 'mn', 'mianning', '', '县', '513433', '0834', '13');
INSERT INTO `district` VALUES ('2624', '越西', '395', 'y', 'yx', 'yuexi', '', '县', '513434', '0834', '14');
INSERT INTO `district` VALUES ('2625', '甘洛', '395', 'g', 'gl', 'ganluo', '', '县', '513435', '0834', '15');
INSERT INTO `district` VALUES ('2626', '美姑', '395', 'm', 'mg', 'meigu', '', '县', '513436', '0834', '16');
INSERT INTO `district` VALUES ('2627', '雷波', '395', 'l', 'lb', 'leibo', '', '县', '513437', '0834', '17');
INSERT INTO `district` VALUES ('2628', '观山湖', '396', 'g', 'gsh', 'guanshanhu', '', '区', '', '0851', '1');
INSERT INTO `district` VALUES ('2629', '南明', '396', 'n', 'nm', 'nanming', '', '区', '520102', '0851', '2');
INSERT INTO `district` VALUES ('2630', '云岩', '396', 'y', 'yy', 'yunyan', '', '区', '520103', '0851', '3');
INSERT INTO `district` VALUES ('2631', '花溪', '396', 'h', 'hx', 'huaxi', '', '区', '520111', '0851', '4');
INSERT INTO `district` VALUES ('2632', '乌当', '396', 'w', 'wd', 'wudang', '', '区', '520112', '0851', '5');
INSERT INTO `district` VALUES ('2633', '白云', '396', 'b', 'by', 'baiyun', '', '区', '520113', '0851', '6');
INSERT INTO `district` VALUES ('2634', '开阳', '396', 'k', 'ky', 'kaiyang', '', '县', '520121', '0851', '7');
INSERT INTO `district` VALUES ('2635', '息烽', '396', 'x', 'xf', 'xifeng', '', '县', '520122', '0851', '8');
INSERT INTO `district` VALUES ('2636', '修文', '396', 'x', 'xw', 'xiuwen', '', '县', '520123', '0851', '9');
INSERT INTO `district` VALUES ('2637', '清镇', '396', 'q', 'qz', 'qingzhen', '', '市', '520181', '0851', '10');
INSERT INTO `district` VALUES ('2638', '钟山', '397', 'z', 'zs', 'zhongshan', '', '区', '520201', '0858', '1');
INSERT INTO `district` VALUES ('2639', '六枝特', '397', 'l', 'lzt', 'liuzhite', '', '区', '520203', '0858', '2');
INSERT INTO `district` VALUES ('2640', '水城', '397', 's', 'sc', 'shuicheng', '', '县', '520221', '0858', '3');
INSERT INTO `district` VALUES ('2641', '盘县', '397', 'p', 'px', 'panxian', '', '', '520222', '0858', '4');
INSERT INTO `district` VALUES ('2642', '红花岗', '398', 'h', 'hhg', 'honghuagang', '', '区', '520302', '0852', '1');
INSERT INTO `district` VALUES ('2643', '汇川', '398', 'h', 'hc', 'huichuan', '', '区', '520303', '0852', '2');
INSERT INTO `district` VALUES ('2644', '遵义', '398', 'z', 'zy', 'zunyi', '', '县', '520321', '0852', '3');
INSERT INTO `district` VALUES ('2645', '桐梓', '398', 't', 'tz', 'tongzi', '', '县', '520322', '0852', '4');
INSERT INTO `district` VALUES ('2646', '绥阳', '398', 's', 'sy', 'suiyang', '', '县', '520323', '0852', '5');
INSERT INTO `district` VALUES ('2647', '正安', '398', 'z', 'za', 'zhengan', '', '县', '520324', '0852', '6');
INSERT INTO `district` VALUES ('2648', '道真', '398', 'd', 'dz', 'daozhen', '仡佬族苗族', '自治县', '520325', '0852', '7');
INSERT INTO `district` VALUES ('2649', '务川', '398', 'w', 'wc', 'wuchuan', '仡佬族苗族', '自治县', '520326', '0852', '8');
INSERT INTO `district` VALUES ('2650', '凤冈', '398', 'f', 'fg', 'fenggang', '', '县', '520327', '0852', '9');
INSERT INTO `district` VALUES ('2651', '湄潭', '398', 'm', 'mt', 'meitan', '', '县', '520328', '0852', '10');
INSERT INTO `district` VALUES ('2652', '余庆', '398', 'y', 'yq', 'yuqing', '', '县', '520329', '0852', '11');
INSERT INTO `district` VALUES ('2653', '习水', '398', 'x', 'xs', 'xishui', '', '县', '520330', '0852', '12');
INSERT INTO `district` VALUES ('2654', '赤水', '398', 'c', 'cs', 'chishui', '', '市', '520381', '0852', '13');
INSERT INTO `district` VALUES ('2655', '仁怀', '398', 'r', 'rh', 'renhuai', '', '市', '520382', '0852', '14');
INSERT INTO `district` VALUES ('2656', '西秀', '399', 'x', 'xx', 'xixiu', '', '区', '520402', '0853', '1');
INSERT INTO `district` VALUES ('2657', '平坝', '399', 'p', 'pb', 'pingba', '', '区', '520403', '0853', '2');
INSERT INTO `district` VALUES ('2658', '普定', '399', 'p', 'pd', 'puding', '', '县', '520422', '0853', '3');
INSERT INTO `district` VALUES ('2659', '镇宁', '399', 'z', 'zn', 'zhenning', '布依族苗族', '自治县', '520423', '0853', '4');
INSERT INTO `district` VALUES ('2660', '关岭', '399', 'g', 'gl', 'guanling', '布依族苗族', '自治县', '520424', '0853', '5');
INSERT INTO `district` VALUES ('2661', '紫云', '399', 'z', 'zy', 'ziyun', '苗族布依族', '自治县', '520425', '0853', '6');
INSERT INTO `district` VALUES ('2662', '七星关', '400', 'q', 'qxg', 'qixingguan', '', '区', '520502', '0857', '1');
INSERT INTO `district` VALUES ('2663', '大方', '400', 'd', 'df', 'dafang', '', '县', '520521', '0857', '2');
INSERT INTO `district` VALUES ('2664', '黔西', '400', 'q', 'qx', 'qianxi', '', '县', '520522', '0857', '3');
INSERT INTO `district` VALUES ('2665', '金沙', '400', 'j', 'js', 'jinsha', '', '县', '520523', '0857', '4');
INSERT INTO `district` VALUES ('2666', '织金', '400', 'z', 'zj', 'zhijin', '', '县', '520524', '0857', '5');
INSERT INTO `district` VALUES ('2667', '纳雍', '400', 'n', 'ny', 'nayong', '', '县', '520525', '0857', '6');
INSERT INTO `district` VALUES ('2668', '威宁', '400', 'w', 'wn', 'weining', '彝族回族苗族', '自治县', '520526', '0857', '7');
INSERT INTO `district` VALUES ('2669', '赫章', '400', 'h', 'hz', 'hezhang', '', '县', '520527', '0857', '8');
INSERT INTO `district` VALUES ('2670', '碧江', '401', 'b', 'bj', 'bijiang', '', '区', '520602', '0856', '1');
INSERT INTO `district` VALUES ('2671', '万山', '401', 'w', 'ws', 'wanshan', '', '区', '520603', '0856', '2');
INSERT INTO `district` VALUES ('2672', '江口', '401', 'j', 'jk', 'jiangkou', '', '县', '520621', '0856', '3');
INSERT INTO `district` VALUES ('2673', '玉屏', '401', 'y', 'yp', 'yuping', '侗族', '自治县', '520622', '0856', '4');
INSERT INTO `district` VALUES ('2674', '石阡', '401', 's', 'sq', 'shiqian', '', '县', '520623', '0856', '5');
INSERT INTO `district` VALUES ('2675', '思南', '401', 's', 'sn', 'sinan', '', '县', '520624', '0856', '6');
INSERT INTO `district` VALUES ('2676', '印江', '401', 'y', 'yj', 'yinjiang', '土家族苗族', '自治县', '520625', '0856', '7');
INSERT INTO `district` VALUES ('2677', '德江', '401', 'd', 'dj', 'dejiang', '', '县', '520626', '0856', '8');
INSERT INTO `district` VALUES ('2678', '沿河', '401', 'y', 'yh', 'yanhe', '土家族', '自治县', '520627', '0856', '9');
INSERT INTO `district` VALUES ('2679', '松桃', '401', 's', 'st', 'songtao', '苗族', '自治县', '520628', '0856', '10');
INSERT INTO `district` VALUES ('2680', '兴义', '402', 'x', 'xy', 'xingyi', '', '市', '522301', '0859', '1');
INSERT INTO `district` VALUES ('2681', '兴仁', '402', 'x', 'xr', 'xingren', '', '县', '522322', '0859', '2');
INSERT INTO `district` VALUES ('2682', '普安', '402', 'p', 'pa', 'puan', '', '县', '522323', '0859', '3');
INSERT INTO `district` VALUES ('2683', '晴隆', '402', 'q', 'ql', 'qinglong', '', '县', '522324', '0859', '4');
INSERT INTO `district` VALUES ('2684', '贞丰', '402', 'z', 'zf', 'zhenfeng', '', '县', '522325', '0859', '5');
INSERT INTO `district` VALUES ('2685', '望谟', '402', 'w', 'wm', 'wangmo', '', '县', '522326', '0859', '6');
INSERT INTO `district` VALUES ('2686', '册亨', '402', 'c', 'ch', 'ceheng', '', '县', '522327', '0859', '7');
INSERT INTO `district` VALUES ('2687', '安龙', '402', 'a', 'al', 'anlong', '', '县', '522328', '0859', '8');
INSERT INTO `district` VALUES ('2688', '凯里', '403', 'k', 'kl', 'kaili', '', '市', '522601', '0855', '1');
INSERT INTO `district` VALUES ('2689', '黄平', '403', 'h', 'hp', 'huangping', '', '县', '522622', '0855', '2');
INSERT INTO `district` VALUES ('2690', '施秉', '403', 's', 'sb', 'shibing', '', '县', '522623', '0855', '3');
INSERT INTO `district` VALUES ('2691', '三穗', '403', 's', 'ss', 'sansui', '', '县', '522624', '0855', '4');
INSERT INTO `district` VALUES ('2692', '镇远', '403', 'z', 'zy', 'zhenyuan', '', '县', '522625', '0855', '5');
INSERT INTO `district` VALUES ('2693', '岑巩', '403', 'c', 'cg', 'cengong', '', '县', '522626', '0855', '6');
INSERT INTO `district` VALUES ('2694', '天柱', '403', 't', 'tz', 'tianzhu', '', '县', '522627', '0855', '7');
INSERT INTO `district` VALUES ('2695', '锦屏', '403', 'j', 'jp', 'jinping', '', '县', '522628', '0855', '8');
INSERT INTO `district` VALUES ('2696', '剑河', '403', 'j', 'jh', 'jianhe', '', '县', '522629', '0855', '9');
INSERT INTO `district` VALUES ('2697', '台江', '403', 't', 'tj', 'taijiang', '', '县', '522630', '0855', '10');
INSERT INTO `district` VALUES ('2698', '黎平', '403', 'l', 'lp', 'liping', '', '县', '522631', '0855', '11');
INSERT INTO `district` VALUES ('2699', '榕江', '403', 'r', 'rj', 'rongjiang', '', '县', '522632', '0855', '12');
INSERT INTO `district` VALUES ('2700', '从江', '403', 'c', 'cj', 'congjiang', '', '县', '522633', '0855', '13');
INSERT INTO `district` VALUES ('2701', '雷山', '403', 'l', 'ls', 'leishan', '', '县', '522634', '0855', '14');
INSERT INTO `district` VALUES ('2702', '麻江', '403', 'm', 'mj', 'majiang', '', '县', '522635', '0855', '15');
INSERT INTO `district` VALUES ('2703', '丹寨', '403', 'd', 'dz', 'danzhai', '', '县', '522636', '0855', '16');
INSERT INTO `district` VALUES ('2704', '都匀', '404', 'd', 'dy', 'douyun', '', '市', '522701', '0854', '1');
INSERT INTO `district` VALUES ('2705', '福泉', '404', 'f', 'fq', 'fuquan', '', '市', '522702', '0854', '2');
INSERT INTO `district` VALUES ('2706', '荔波', '404', 'l', 'lb', 'libo', '', '县', '522722', '0854', '3');
INSERT INTO `district` VALUES ('2707', '贵定', '404', 'g', 'gd', 'guiding', '', '县', '522723', '0854', '4');
INSERT INTO `district` VALUES ('2708', '瓮安', '404', 'w', 'wa', 'wengan', '', '县', '522725', '0854', '5');
INSERT INTO `district` VALUES ('2709', '独山', '404', 'd', 'ds', 'dushan', '', '县', '522726', '0854', '6');
INSERT INTO `district` VALUES ('2710', '平塘', '404', 'p', 'pt', 'pingtang', '', '县', '522727', '0854', '7');
INSERT INTO `district` VALUES ('2711', '罗甸', '404', 'l', 'ld', 'luodian', '', '县', '522728', '0854', '8');
INSERT INTO `district` VALUES ('2712', '长顺', '404', 'z', 'zs', 'zhangshun', '', '县', '522729', '0854', '9');
INSERT INTO `district` VALUES ('2713', '龙里', '404', 'l', 'll', 'longli', '', '县', '522730', '0854', '10');
INSERT INTO `district` VALUES ('2714', '惠水', '404', 'h', 'hs', 'huishui', '', '县', '522731', '0854', '11');
INSERT INTO `district` VALUES ('2715', '三都', '404', 's', 'sd', 'sandou', '水族', '自治县', '522732', '0854', '12');
INSERT INTO `district` VALUES ('2716', '五华', '405', 'w', 'wh', 'wuhua', '', '区', '530102', '0871', '1');
INSERT INTO `district` VALUES ('2717', '盘龙', '405', 'p', 'pl', 'panlong', '', '区', '530103', '0871', '2');
INSERT INTO `district` VALUES ('2718', '官渡', '405', 'g', 'gd', 'guandu', '', '区', '530111', '0871', '3');
INSERT INTO `district` VALUES ('2719', '西山', '405', 'x', 'xs', 'xishan', '', '区', '530112', '0871', '4');
INSERT INTO `district` VALUES ('2720', '东川', '405', 'd', 'dc', 'dongchuan', '', '区', '530113', '0871', '5');
INSERT INTO `district` VALUES ('2721', '呈贡', '405', 'c', 'cg', 'chenggong', '', '区', '530114', '0871', '6');
INSERT INTO `district` VALUES ('2722', '晋宁', '405', 'j', 'jn', 'jinning', '', '县', '530122', '0871', '7');
INSERT INTO `district` VALUES ('2723', '富民', '405', 'f', 'fm', 'fumin', '', '县', '530124', '0871', '8');
INSERT INTO `district` VALUES ('2724', '宜良', '405', 'y', 'yl', 'yiliang', '', '县', '530125', '0871', '9');
INSERT INTO `district` VALUES ('2725', '石林', '405', 's', 'sl', 'shilin', '彝族', '自治县', '530126', '0871', '10');
INSERT INTO `district` VALUES ('2726', '嵩明', '405', 's', 'sm', 'songming', '', '县', '530127', '0871', '11');
INSERT INTO `district` VALUES ('2727', '禄劝', '405', 'l', 'lq', 'luquan', '彝族苗族', '自治县', '530128', '0871', '12');
INSERT INTO `district` VALUES ('2728', '寻甸', '405', 'x', 'xd', 'xundian', '回族彝族', '自治县', '530129', '0871', '13');
INSERT INTO `district` VALUES ('2729', '安宁', '405', 'a', 'an', 'anning', '', '市', '530181', '0871', '14');
INSERT INTO `district` VALUES ('2730', '麒麟', '406', 'q', 'ql', 'qilin', '', '区', '530302', '0874', '1');
INSERT INTO `district` VALUES ('2731', '马龙', '406', 'm', 'ml', 'malong', '', '县', '530321', '0874', '2');
INSERT INTO `district` VALUES ('2732', '陆良', '406', 'l', 'll', 'luliang', '', '县', '530322', '0874', '3');
INSERT INTO `district` VALUES ('2733', '师宗', '406', 's', 'sz', 'shizong', '', '县', '530323', '0874', '4');
INSERT INTO `district` VALUES ('2734', '罗平', '406', 'l', 'lp', 'luoping', '', '县', '530324', '0874', '5');
INSERT INTO `district` VALUES ('2735', '富源', '406', 'f', 'fy', 'fuyuan', '', '县', '530325', '0874', '6');
INSERT INTO `district` VALUES ('2736', '会泽', '406', 'h', 'hz', 'huize', '', '县', '530326', '0874', '7');
INSERT INTO `district` VALUES ('2737', '沾益', '406', 'z', 'zy', 'zhanyi', '', '县', '530328', '0874', '8');
INSERT INTO `district` VALUES ('2738', '宣威', '406', 'x', 'xw', 'xuanwei', '', '市', '530381', '0874', '9');
INSERT INTO `district` VALUES ('2739', '红塔', '407', 'h', 'ht', 'hongta', '', '区', '530402', '0877', '1');
INSERT INTO `district` VALUES ('2740', '江川', '407', 'j', 'jc', 'jiangchuan', '', '县', '530421', '0877', '2');
INSERT INTO `district` VALUES ('2741', '澄江', '407', 'c', 'cj', 'chengjiang', '', '县', '530422', '0877', '3');
INSERT INTO `district` VALUES ('2742', '通海', '407', 't', 'th', 'tonghai', '', '县', '530423', '0877', '4');
INSERT INTO `district` VALUES ('2743', '华宁', '407', 'h', 'hn', 'huaning', '', '县', '530424', '0877', '5');
INSERT INTO `district` VALUES ('2744', '易门', '407', 'y', 'ym', 'yimen', '', '县', '530425', '0877', '6');
INSERT INTO `district` VALUES ('2745', '峨山', '407', 'e', 'es', 'eshan', '彝族', '自治县', '530426', '0877', '7');
INSERT INTO `district` VALUES ('2746', '新平', '407', 'x', 'xp', 'xinping', '彝族傣族', '自治县', '530427', '0877', '8');
INSERT INTO `district` VALUES ('2747', '元江', '407', 'y', 'yj', 'yuanjiang', '哈尼族彝族傣族', '自治县', '530428', '0877', '9');
INSERT INTO `district` VALUES ('2748', '昭阳', '408', 'z', 'zy', 'zhaoyang', '', '区', '530602', '0870', '1');
INSERT INTO `district` VALUES ('2749', '鲁甸', '408', 'l', 'ld', 'ludian', '', '县', '530621', '0870', '2');
INSERT INTO `district` VALUES ('2750', '巧家', '408', 'q', 'qj', 'qiaojia', '', '县', '530622', '0870', '3');
INSERT INTO `district` VALUES ('2751', '盐津', '408', 'y', 'yj', 'yanjin', '', '县', '530623', '0870', '4');
INSERT INTO `district` VALUES ('2752', '大关', '408', 'd', 'dg', 'daguan', '', '县', '530624', '0870', '5');
INSERT INTO `district` VALUES ('2753', '永善', '408', 'y', 'ys', 'yongshan', '', '县', '530625', '0870', '6');
INSERT INTO `district` VALUES ('2754', '绥江', '408', 's', 'sj', 'suijiang', '', '县', '530626', '0870', '7');
INSERT INTO `district` VALUES ('2755', '镇雄', '408', 'z', 'zx', 'zhenxiong', '', '县', '530627', '0870', '8');
INSERT INTO `district` VALUES ('2756', '彝良', '408', 'y', 'yl', 'yiliang', '', '县', '530628', '0870', '9');
INSERT INTO `district` VALUES ('2757', '威信', '408', 'w', 'wx', 'weixin', '', '县', '530629', '0870', '10');
INSERT INTO `district` VALUES ('2758', '水富', '408', 's', 'sf', 'shuifu', '', '县', '530630', '0870', '11');
INSERT INTO `district` VALUES ('2759', '古城', '409', 'g', 'gc', 'gucheng', '', '区', '530702', '0888', '1');
INSERT INTO `district` VALUES ('2760', '玉龙', '409', 'y', 'yl', 'yulong', '纳西族', '自治县', '530721', '0888', '2');
INSERT INTO `district` VALUES ('2761', '永胜', '409', 'y', 'ys', 'yongsheng', '', '县', '530722', '0888', '3');
INSERT INTO `district` VALUES ('2762', '华坪', '409', 'h', 'hp', 'huaping', '', '县', '530723', '0888', '4');
INSERT INTO `district` VALUES ('2763', '宁蒗', '409', 'n', 'nl', 'ninglang', '彝族', '自治县', '530724', '0888', '5');
INSERT INTO `district` VALUES ('2764', '思茅', '410', 's', 'sm', 'simao', '', '区', '530802', '0879', '1');
INSERT INTO `district` VALUES ('2765', '宁洱', '410', 'n', 'ne', 'ninger', '哈尼族彝族', '县', '530821', '0879', '2');
INSERT INTO `district` VALUES ('2766', '墨江', '410', 'm', 'mj', 'mojiang', '哈尼族', '县', '530822', '0879', '3');
INSERT INTO `district` VALUES ('2767', '景东', '410', 'j', 'jd', 'jingdong', '彝族', '县', '530823', '0879', '4');
INSERT INTO `district` VALUES ('2768', '景谷', '410', 'j', 'jg', 'jinggu', '傣族彝族', '县', '530824', '0879', '5');
INSERT INTO `district` VALUES ('2769', '镇沅', '410', 'z', 'zy', 'zhenyuan', '彝族哈尼族拉祜族', '县', '530825', '0879', '6');
INSERT INTO `district` VALUES ('2770', '江城', '410', 'j', 'jc', 'jiangcheng', '哈尼族彝族', '县', '530826', '0879', '7');
INSERT INTO `district` VALUES ('2771', '孟连', '410', 'm', 'ml', 'menglian', '傣族拉祜族佤族', '县', '530827', '0879', '8');
INSERT INTO `district` VALUES ('2772', '澜沧', '410', 'l', 'lc', 'lancang', '拉祜族', '县', '530828', '0879', '9');
INSERT INTO `district` VALUES ('2773', '西盟', '410', 'x', 'xm', 'ximeng', '佤族', '县', '530829', '0879', '10');
INSERT INTO `district` VALUES ('2774', '临翔', '411', 'l', 'lx', 'linxiang', '', '区', '530902', '0883', '1');
INSERT INTO `district` VALUES ('2775', '凤庆', '411', 'f', 'fq', 'fengqing', '', '县', '530921', '0883', '2');
INSERT INTO `district` VALUES ('2776', '云县', '411', 'y', 'yx', 'yunxian', '', '', '530922', '0883', '3');
INSERT INTO `district` VALUES ('2777', '永德', '411', 'y', 'yd', 'yongde', '', '县', '530923', '0883', '4');
INSERT INTO `district` VALUES ('2778', '镇康', '411', 'z', 'zk', 'zhenkang', '', '县', '530924', '0883', '5');
INSERT INTO `district` VALUES ('2779', '双江', '411', 's', 'sj', 'shuangjiang', '拉祜族佤族布朗族傣族', '自治县', '530925', '0883', '6');
INSERT INTO `district` VALUES ('2780', '耿马', '411', 'g', 'gm', 'gengma', '傣族佤族', '自治县', '530926', '0883', '7');
INSERT INTO `district` VALUES ('2781', '沧源', '411', 'c', 'cy', 'cangyuan', '佤族', '自治县', '530927', '0883', '8');
INSERT INTO `district` VALUES ('2782', '楚雄', '412', 'c', 'cx', 'chuxiong', '', '市', '532301', '0878', '1');
INSERT INTO `district` VALUES ('2783', '双柏', '412', 's', 'sb', 'shuangbo', '', '县', '532322', '0878', '2');
INSERT INTO `district` VALUES ('2784', '牟定', '412', 'm', 'md', 'mouding', '', '县', '532323', '0878', '3');
INSERT INTO `district` VALUES ('2785', '南华', '412', 'n', 'nh', 'nanhua', '', '县', '532324', '0878', '4');
INSERT INTO `district` VALUES ('2786', '姚安', '412', 'y', 'ya', 'yaoan', '', '县', '532325', '0878', '5');
INSERT INTO `district` VALUES ('2787', '大姚', '412', 'd', 'dy', 'dayao', '', '县', '532326', '0878', '6');
INSERT INTO `district` VALUES ('2788', '永仁', '412', 'y', 'yr', 'yongren', '', '县', '532327', '0878', '7');
INSERT INTO `district` VALUES ('2789', '元谋', '412', 'y', 'ym', 'yuanmou', '', '县', '532328', '0878', '8');
INSERT INTO `district` VALUES ('2790', '武定', '412', 'w', 'wd', 'wuding', '', '县', '532329', '0878', '9');
INSERT INTO `district` VALUES ('2791', '禄丰', '412', 'l', 'lf', 'lufeng', '', '县', '532331', '0878', '10');
INSERT INTO `district` VALUES ('2792', '个旧', '413', 'g', 'gj', 'gejiu', '', '市', '532501', '0873', '1');
INSERT INTO `district` VALUES ('2793', '开远', '413', 'k', 'ky', 'kaiyuan', '', '市', '532502', '0873', '2');
INSERT INTO `district` VALUES ('2794', '蒙自', '413', 'm', 'mz', 'mengzi', '', '市', '532503', '0873', '3');
INSERT INTO `district` VALUES ('2795', '屏边', '413', 'p', 'pb', 'pingbian', '苗族', '自治县', '532523', '0873', '4');
INSERT INTO `district` VALUES ('2796', '建水', '413', 'j', 'js', 'jianshui', '', '县', '532524', '0873', '5');
INSERT INTO `district` VALUES ('2797', '石屏', '413', 's', 'sp', 'shiping', '', '县', '532525', '0873', '6');
INSERT INTO `district` VALUES ('2798', '弥勒', '413', 'm', 'ml', 'mile', '', '市', '532526', '0873', '7');
INSERT INTO `district` VALUES ('2799', '泸西', '413', 'l', 'lx', 'luxi', '', '县', '532527', '0873', '8');
INSERT INTO `district` VALUES ('2800', '元阳', '413', 'y', 'yy', 'yuanyang', '', '县', '532528', '0873', '9');
INSERT INTO `district` VALUES ('2801', '红河', '413', 'h', 'hh', 'honghe', '', '县', '532529', '0873', '10');
INSERT INTO `district` VALUES ('2802', '金平', '413', 'j', 'jp', 'jinping', '苗族瑶族傣族', '自治县', '532530', '0873', '11');
INSERT INTO `district` VALUES ('2803', '绿春', '413', 'l', 'lc', 'lu:chun', '', '县', '532531', '0873', '12');
INSERT INTO `district` VALUES ('2804', '河口', '413', 'h', 'hk', 'hekou', '瑶族', '自治县', '532532', '0873', '13');
INSERT INTO `district` VALUES ('2805', '文山', '414', 'w', 'ws', 'wenshan', '', '县', '532601', '0876', '1');
INSERT INTO `district` VALUES ('2806', '砚山', '414', 'y', 'ys', 'yanshan', '', '县', '532622', '0876', '2');
INSERT INTO `district` VALUES ('2807', '西畴', '414', 'x', 'xc', 'xichou', '', '县', '532623', '0876', '3');
INSERT INTO `district` VALUES ('2808', '麻栗坡', '414', 'm', 'mlp', 'malipo', '', '县', '532624', '0876', '4');
INSERT INTO `district` VALUES ('2809', '马关', '414', 'm', 'mg', 'maguan', '', '县', '532625', '0876', '5');
INSERT INTO `district` VALUES ('2810', '丘北', '414', 'q', 'qb', 'qiubei', '', '县', '532626', '0876', '6');
INSERT INTO `district` VALUES ('2811', '广南', '414', 'g', 'gn', 'guangnan', '', '县', '532627', '0876', '7');
INSERT INTO `district` VALUES ('2812', '富宁', '414', 'f', 'fn', 'funing', '', '县', '532628', '0876', '8');
INSERT INTO `district` VALUES ('2813', '景洪', '415', 'j', 'jh', 'jinghong', '', '市', '532801', '0691', '1');
INSERT INTO `district` VALUES ('2814', '勐海', '415', 'm', 'mh', 'menghai', '', '县', '532822', '0691', '2');
INSERT INTO `district` VALUES ('2815', '勐腊', '415', 'm', 'ml', 'mengla', '', '县', '532823', '0691', '3');
INSERT INTO `district` VALUES ('2816', '大理', '416', 'd', 'dl', 'dali', '', '市', '532901', '0872', '1');
INSERT INTO `district` VALUES ('2817', '漾濞', '416', 'y', 'yb', 'yangbi', '彝族', '自治县', '532922', '0872', '2');
INSERT INTO `district` VALUES ('2818', '祥云', '416', 'x', 'xy', 'xiangyun', '', '县', '532923', '0872', '3');
INSERT INTO `district` VALUES ('2819', '宾川', '416', 'b', 'bc', 'binchuan', '', '县', '532924', '0872', '4');
INSERT INTO `district` VALUES ('2820', '弥渡', '416', 'm', 'md', 'midu', '', '县', '532925', '0872', '5');
INSERT INTO `district` VALUES ('2821', '南涧', '416', 'n', 'nj', 'nanjian', '彝族', '自治县', '532926', '0872', '6');
INSERT INTO `district` VALUES ('2822', '巍山', '416', 'w', 'ws', 'weishan', '彝族回族', '自治县', '532927', '0872', '7');
INSERT INTO `district` VALUES ('2823', '永平', '416', 'y', 'yp', 'yongping', '', '县', '532928', '0872', '8');
INSERT INTO `district` VALUES ('2824', '云龙', '416', 'y', 'yl', 'yunlong', '', '县', '532929', '0872', '9');
INSERT INTO `district` VALUES ('2825', '洱源', '416', 'e', 'ey', 'eryuan', '', '县', '532930', '0872', '10');
INSERT INTO `district` VALUES ('2826', '剑川', '416', 'j', 'jc', 'jianchuan', '', '县', '532931', '0872', '11');
INSERT INTO `district` VALUES ('2827', '鹤庆', '416', 'h', 'hq', 'heqing', '', '县', '532932', '0872', '12');
INSERT INTO `district` VALUES ('2828', '瑞丽', '417', 'r', 'rl', 'ruili', '', '市', '533102', '0692', '1');
INSERT INTO `district` VALUES ('2829', '芒市', '417', 'm', 'ms', 'mangshi', '', '', '533103', '0692', '2');
INSERT INTO `district` VALUES ('2830', '梁河', '417', 'l', 'lh', 'lianghe', '', '县', '533122', '0692', '3');
INSERT INTO `district` VALUES ('2831', '盈江', '417', 'y', 'yj', 'yingjiang', '', '县', '533123', '0692', '4');
INSERT INTO `district` VALUES ('2832', '陇川', '417', 'l', 'lc', 'longchuan', '', '县', '533124', '0692', '5');
INSERT INTO `district` VALUES ('2833', '泸水', '418', 'l', 'ls', 'lushui', '', '县', '533321', '0886', '1');
INSERT INTO `district` VALUES ('2834', '福贡', '418', 'f', 'fg', 'fugong', '', '县', '533323', '0886', '2');
INSERT INTO `district` VALUES ('2835', '贡山', '418', 'g', 'gs', 'gongshan', '独龙族怒族', '县', '533324', '0886', '3');
INSERT INTO `district` VALUES ('2836', '兰坪', '418', 'l', 'lp', 'lanping', '白族普米族', '县', '533325', '0886', '4');
INSERT INTO `district` VALUES ('2837', '香格里拉', '419', 'x', 'xgll', 'xianggelila', '', '市', '533421', '0887', '1');
INSERT INTO `district` VALUES ('2838', '德钦', '419', 'd', 'dq', 'deqin', '', '县', '533422', '0887', '2');
INSERT INTO `district` VALUES ('2839', '维西', '419', 'w', 'wx', 'weixi', '', '县', '533423', '0887', '3');
INSERT INTO `district` VALUES ('2840', '隆阳', '420', 'l', 'ly', 'longyang', '', '区', '530502', '0875', '1');
INSERT INTO `district` VALUES ('2841', '施甸', '420', 's', 'sd', 'shidian', '', '县', '530521', '0875', '2');
INSERT INTO `district` VALUES ('2842', '腾冲', '420', 't', 'tc', 'tengchong', '', '县', '530522', '0875', '3');
INSERT INTO `district` VALUES ('2843', '龙陵', '420', 'l', 'll', 'longling', '', '县', '530523', '0875', '4');
INSERT INTO `district` VALUES ('2844', '昌宁', '420', 'c', 'cn', 'changning', '', '县', '530524', '0875', '5');
INSERT INTO `district` VALUES ('2845', '城关', '421', 'c', 'cg', 'chengguan', '', '区', '540102', '0891', '1');
INSERT INTO `district` VALUES ('2846', '林周', '421', 'l', 'lz', 'linzhou', '', '县', '540121', '0891', '2');
INSERT INTO `district` VALUES ('2847', '当雄', '421', 'd', 'dx', 'dangxiong', '', '县', '540122', '0891', '3');
INSERT INTO `district` VALUES ('2848', '尼木', '421', 'n', 'nm', 'nimu', '', '县', '540123', '0891', '4');
INSERT INTO `district` VALUES ('2849', '曲水', '421', 'q', 'qs', 'qushui', '', '县', '540124', '0891', '5');
INSERT INTO `district` VALUES ('2850', '堆龙德庆', '421', 'd', 'dldq', 'duilongdeqing', '', '县', '540125', '0891', '6');
INSERT INTO `district` VALUES ('2851', '达孜', '421', 'd', 'dz', 'dazi', '', '县', '540126', '0891', '7');
INSERT INTO `district` VALUES ('2852', '墨竹工卡', '421', 'm', 'mzgk', 'mozhugongka', '', '县', '540127', '0891', '8');
INSERT INTO `district` VALUES ('2853', '卡若', '422', 'k', 'kr', 'karuo', '', '区', '542121', '0895', '1');
INSERT INTO `district` VALUES ('2854', '江达', '422', 'j', 'jd', 'jiangda', '', '县', '542122', '0895', '2');
INSERT INTO `district` VALUES ('2855', '贡觉', '422', 'g', 'gj', 'gongjue', '', '县', '542123', '0895', '3');
INSERT INTO `district` VALUES ('2856', '类乌齐', '422', 'l', 'lwq', 'leiwuqi', '', '县', '542124', '0895', '4');
INSERT INTO `district` VALUES ('2857', '丁青', '422', 'd', 'dq', 'dingqing', '', '县', '542125', '0895', '5');
INSERT INTO `district` VALUES ('2858', '察雅', '422', 'c', 'cy', 'chaya', '', '县', '542126', '0895', '6');
INSERT INTO `district` VALUES ('2859', '八宿', '422', 'b', 'bs', 'basu', '', '县', '542127', '0895', '7');
INSERT INTO `district` VALUES ('2860', '左贡', '422', 'z', 'zg', 'zuogong', '', '县', '542128', '0895', '8');
INSERT INTO `district` VALUES ('2861', '芒康', '422', 'm', 'mk', 'mangkang', '', '县', '542129', '0895', '9');
INSERT INTO `district` VALUES ('2862', '洛隆', '422', 'l', 'll', 'luolong', '', '县', '542132', '0895', '10');
INSERT INTO `district` VALUES ('2863', '边坝', '422', 'b', 'bb', 'bianba', '', '县', '542133', '0895', '11');
INSERT INTO `district` VALUES ('2864', '乃东', '423', 'n', 'nd', 'naidong', '', '县', '542221', '0893', '1');
INSERT INTO `district` VALUES ('2865', '扎囊', '423', 'z', 'zn', 'zhanang', '', '县', '542222', '0893', '2');
INSERT INTO `district` VALUES ('2866', '贡嘎', '423', 'g', 'gg', 'gongga', '', '县', '542223', '0893', '3');
INSERT INTO `district` VALUES ('2867', '桑日', '423', 's', 'sr', 'sangri', '', '县', '542224', '0893', '4');
INSERT INTO `district` VALUES ('2868', '琼结', '423', 'q', 'qj', 'qiongjie', '', '县', '542225', '0893', '5');
INSERT INTO `district` VALUES ('2869', '曲松', '423', 'q', 'qs', 'qusong', '', '县', '542226', '0893', '6');
INSERT INTO `district` VALUES ('2870', '措美', '423', 'c', 'cm', 'cuomei', '', '县', '542227', '0893', '7');
INSERT INTO `district` VALUES ('2871', '洛扎', '423', 'l', 'lz', 'luozha', '', '县', '542228', '0893', '8');
INSERT INTO `district` VALUES ('2872', '加查', '423', 'j', 'jc', 'jiacha', '', '县', '542229', '0893', '9');
INSERT INTO `district` VALUES ('2873', '隆子', '423', 'l', 'lz', 'longzi', '', '县', '542231', '0893', '10');
INSERT INTO `district` VALUES ('2874', '错那', '423', 'c', 'cn', 'cuonei', '', '县', '542232', '0893', '11');
INSERT INTO `district` VALUES ('2875', '浪卡子', '423', 'l', 'lkz', 'langkazi', '', '县', '542233', '0893', '12');
INSERT INTO `district` VALUES ('2876', '桑珠孜', '424', 's', 'szz', 'sangzhuzi', '', '区', '542301', '0892', '1');
INSERT INTO `district` VALUES ('2877', '南木林', '424', 'n', 'nml', 'nanmulin', '', '县', '542322', '0892', '2');
INSERT INTO `district` VALUES ('2878', '江孜', '424', 'j', 'jz', 'jiangzi', '', '县', '542323', '0892', '3');
INSERT INTO `district` VALUES ('2879', '定日', '424', 'd', 'dr', 'dingri', '', '县', '542324', '0892', '4');
INSERT INTO `district` VALUES ('2880', '萨迦', '424', 's', 'sj', 'sajia', '', '县', '542325', '0892', '5');
INSERT INTO `district` VALUES ('2881', '拉孜', '424', 'l', 'lz', 'lazi', '', '县', '542326', '0892', '6');
INSERT INTO `district` VALUES ('2882', '昂仁', '424', 'a', 'ar', 'angren', '', '县', '542327', '0892', '7');
INSERT INTO `district` VALUES ('2883', '谢通门', '424', 'x', 'xtm', 'xietongmen', '', '县', '542328', '0892', '8');
INSERT INTO `district` VALUES ('2884', '白朗', '424', 'b', 'bl', 'bailang', '', '县', '542329', '0892', '9');
INSERT INTO `district` VALUES ('2885', '仁布', '424', 'r', 'rb', 'renbu', '', '县', '542330', '0892', '10');
INSERT INTO `district` VALUES ('2886', '康马', '424', 'k', 'km', 'kangma', '', '县', '542331', '0892', '11');
INSERT INTO `district` VALUES ('2887', '定结', '424', 'd', 'dj', 'dingjie', '', '县', '542332', '0892', '12');
INSERT INTO `district` VALUES ('2888', '仲巴', '424', 'z', 'zb', 'zhongba', '', '县', '542333', '0892', '13');
INSERT INTO `district` VALUES ('2889', '亚东', '424', 'y', 'yd', 'yadong', '', '县', '542334', '0892', '14');
INSERT INTO `district` VALUES ('2890', '吉隆', '424', 'j', 'jl', 'jilong', '', '县', '542335', '0892', '15');
INSERT INTO `district` VALUES ('2891', '聂拉木', '424', 'n', 'nlm', 'nielamu', '', '县', '542336', '0892', '16');
INSERT INTO `district` VALUES ('2892', '萨嘎', '424', 's', 'sg', 'saga', '', '县', '542337', '0892', '17');
INSERT INTO `district` VALUES ('2893', '岗巴', '424', 'g', 'gb', 'gangba', '', '县', '542338', '0892', '18');
INSERT INTO `district` VALUES ('2894', '双湖', '425', 's', 'sh', 'shuanghu', '', '县', '542431', '0896', '1');
INSERT INTO `district` VALUES ('2895', '那曲', '425', 'n', 'nq', 'neiqu', '', '县', '542421', '0896', '2');
INSERT INTO `district` VALUES ('2896', '嘉黎', '425', 'j', 'jl', 'jiali', '', '县', '542421', '0896', '3');
INSERT INTO `district` VALUES ('2897', '比如', '425', 'b', 'br', 'biru', '', '县', '542423', '0896', '4');
INSERT INTO `district` VALUES ('2898', '聂荣', '425', 'n', 'nr', 'nierong', '', '县', '542424', '0896', '5');
INSERT INTO `district` VALUES ('2899', '安多', '425', 'a', 'ad', 'anduo', '', '县', '542425', '0896', '6');
INSERT INTO `district` VALUES ('2900', '申扎', '425', 's', 'sz', 'shenzha', '', '县', '542426', '0896', '7');
INSERT INTO `district` VALUES ('2901', '索县', '425', 's', 'sx', 'suoxian', '', '', '542427', '0896', '8');
INSERT INTO `district` VALUES ('2902', '班戈', '425', 'b', 'bg', 'bange', '', '县', '542428', '0896', '9');
INSERT INTO `district` VALUES ('2903', '巴青', '425', 'b', 'bq', 'baqing', '', '县', '542429', '0896', '10');
INSERT INTO `district` VALUES ('2904', '尼玛', '425', 'n', 'nm', 'nima', '', '县', '542430', '0896', '11');
INSERT INTO `district` VALUES ('2905', '普兰', '426', 'p', 'pl', 'pulan', '', '县', '542521', '0897', '1');
INSERT INTO `district` VALUES ('2906', '札达', '426', 'z', 'zd', 'zhada', '', '县', '542522', '0897', '2');
INSERT INTO `district` VALUES ('2907', '噶尔', '426', 'g', 'ge', 'gaer', '', '县', '542523', '0897', '3');
INSERT INTO `district` VALUES ('2908', '日土', '426', 'r', 'rt', 'ritu', '', '县', '542524', '0897', '4');
INSERT INTO `district` VALUES ('2909', '革吉', '426', 'g', 'gj', 'geji', '', '县', '542525', '0897', '5');
INSERT INTO `district` VALUES ('2910', '改则', '426', 'g', 'gz', 'gaize', '', '县', '542526', '0897', '6');
INSERT INTO `district` VALUES ('2911', '措勤', '426', 'c', 'cq', 'cuoqin', '', '县', '542527', '0897', '7');
INSERT INTO `district` VALUES ('2912', '巴宜', '427', 'b', 'by', 'bayi', '', '县', '540402', '0894', '1');
INSERT INTO `district` VALUES ('2913', '工布江达', '427', 'g', 'gbjd', 'gongbujiangda', '', '县', '542621', '0894', '2');
INSERT INTO `district` VALUES ('2914', '米林', '427', 'm', 'ml', 'milin', '', '县', '542621', '0894', '3');
INSERT INTO `district` VALUES ('2915', '墨脱', '427', 'm', 'mt', 'motuo', '', '县', '542621', '0894', '4');
INSERT INTO `district` VALUES ('2916', '波密', '427', 'b', 'bm', 'bomi', '', '县', '542621', '0894', '5');
INSERT INTO `district` VALUES ('2917', '察隅', '427', 'c', 'cy', 'chayu', '', '县', '542621', '0894', '6');
INSERT INTO `district` VALUES ('2918', '朗县', '427', 'l', 'lx', 'langxian', '', '', '542621', '0894', '7');
INSERT INTO `district` VALUES ('2919', '新城', '428', 'x', 'xc', 'xincheng', '', '区', '610102', '029', '1');
INSERT INTO `district` VALUES ('2920', '碑林', '428', 'b', 'bl', 'beilin', '', '区', '610103', '029', '2');
INSERT INTO `district` VALUES ('2921', '莲湖', '428', 'l', 'lh', 'lianhu', '', '区', '610104', '029', '3');
INSERT INTO `district` VALUES ('2922', '灞桥', '428', 'b', 'bq', 'baqiao', '', '区', '610111', '029', '4');
INSERT INTO `district` VALUES ('2923', '未央', '428', 'w', 'wy', 'weiyang', '', '区', '610112', '029', '5');
INSERT INTO `district` VALUES ('2924', '雁塔', '428', 'y', 'yt', 'yanta', '', '区', '610113', '029', '6');
INSERT INTO `district` VALUES ('2925', '阎良', '428', 'y', 'yl', 'yanliang', '', '区', '610114', '029', '7');
INSERT INTO `district` VALUES ('2926', '临潼', '428', 'l', 'lt', 'lintong', '', '区', '610115', '029', '8');
INSERT INTO `district` VALUES ('2927', '长安', '428', 'z', 'za', 'zhangan', '', '区', '610116', '029', '9');
INSERT INTO `district` VALUES ('2928', '蓝田', '428', 'l', 'lt', 'lantian', '', '县', '610122', '029', '11');
INSERT INTO `district` VALUES ('2929', '周至', '428', 'z', 'zz', 'zhouzhi', '', '县', '610124', '029', '12');
INSERT INTO `district` VALUES ('2930', '户县', '428', 'h', 'hx', 'huxian', '', '', '610125', '029', '13');
INSERT INTO `district` VALUES ('2931', '高陵', '428', 'g', 'gl', 'gaoling', '', '区', '610117', '029', '10');
INSERT INTO `district` VALUES ('2932', '王益', '429', 'w', 'wy', 'wangyi', '', '区', '610202', '0919', '1');
INSERT INTO `district` VALUES ('2933', '印台', '429', 'y', 'yt', 'yintai', '', '区', '610203', '0919', '2');
INSERT INTO `district` VALUES ('2934', '耀州', '429', 'y', 'yz', 'yaozhou', '', '区', '610204', '0919', '3');
INSERT INTO `district` VALUES ('2935', '宜君', '429', 'y', 'yj', 'yijun', '', '县', '610222', '0919', '4');
INSERT INTO `district` VALUES ('2936', '渭滨', '430', 'w', 'wb', 'weibin', '', '区', '610302', '0917', '1');
INSERT INTO `district` VALUES ('2937', '金台', '430', 'j', 'jt', 'jintai', '', '区', '610303', '0917', '2');
INSERT INTO `district` VALUES ('2938', '陈仓', '430', 'c', 'cc', 'chencang', '', '区', '610304', '0917', '3');
INSERT INTO `district` VALUES ('2939', '凤翔', '430', 'f', 'fx', 'fengxiang', '', '县', '610322', '0917', '4');
INSERT INTO `district` VALUES ('2940', '岐山', '430', 'q', 'qs', 'qishan', '', '县', '610323', '0917', '5');
INSERT INTO `district` VALUES ('2941', '扶风', '430', 'f', 'ff', 'fufeng', '', '县', '610324', '0917', '6');
INSERT INTO `district` VALUES ('2942', '眉县', '430', 'm', 'mx', 'meixian', '', '', '610326', '0917', '7');
INSERT INTO `district` VALUES ('2943', '陇县', '430', 'l', 'lx', 'longxian', '', '', '610327', '0917', '8');
INSERT INTO `district` VALUES ('2944', '千阳', '430', 'q', 'qy', 'qianyang', '', '县', '610328', '0917', '9');
INSERT INTO `district` VALUES ('2945', '麟游', '430', 'l', 'ly', 'linyou', '', '县', '610329', '0917', '10');
INSERT INTO `district` VALUES ('2946', '凤县', '430', 'f', 'fx', 'fengxian', '', '', '610330', '0917', '11');
INSERT INTO `district` VALUES ('2947', '太白', '430', 't', 'tb', 'taibai', '', '县', '610331', '0917', '12');
INSERT INTO `district` VALUES ('2948', '秦都', '431', 'q', 'qd', 'qindou', '', '区', '610402', '029', '1');
INSERT INTO `district` VALUES ('2949', '杨陵', '431', 'y', 'yl', 'yangling', '', '区', '610403', '029', '2');
INSERT INTO `district` VALUES ('2950', '渭城', '431', 'w', 'wc', 'weicheng', '', '区', '610404', '029', '3');
INSERT INTO `district` VALUES ('2951', '三原', '431', 's', 'sy', 'sanyuan', '', '县', '610422', '029', '4');
INSERT INTO `district` VALUES ('2952', '泾阳', '431', 'j', 'jy', 'jingyang', '', '县', '610423', '029', '5');
INSERT INTO `district` VALUES ('2953', '乾县', '431', 'q', 'qx', 'qianxian', '', '', '610424', '029', '6');
INSERT INTO `district` VALUES ('2954', '礼泉', '431', 'l', 'lq', 'liquan', '', '县', '610425', '029', '7');
INSERT INTO `district` VALUES ('2955', '永寿', '431', 'y', 'ys', 'yongshou', '', '县', '610426', '029', '8');
INSERT INTO `district` VALUES ('2956', '彬县', '431', 'b', 'bx', 'binxian', '', '', '610427', '029', '9');
INSERT INTO `district` VALUES ('2957', '长武', '431', 'z', 'zw', 'zhangwu', '', '县', '610428', '029', '10');
INSERT INTO `district` VALUES ('2958', '旬邑', '431', 'x', 'xy', 'xunyi', '', '县', '610429', '029', '11');
INSERT INTO `district` VALUES ('2959', '淳化', '431', 'c', 'ch', 'chunhua', '', '县', '610430', '029', '12');
INSERT INTO `district` VALUES ('2960', '武功', '431', 'w', 'wg', 'wugong', '', '县', '610431', '029', '13');
INSERT INTO `district` VALUES ('2961', '兴平', '431', 'x', 'xp', 'xingping', '', '市', '610481', '029', '14');
INSERT INTO `district` VALUES ('2962', '临渭', '432', 'l', 'lw', 'linwei', '', '区', '610502', '0913', '1');
INSERT INTO `district` VALUES ('2963', '华县', '432', 'h', 'hx', 'huaxian', '', '', '610521', '0913', '2');
INSERT INTO `district` VALUES ('2964', '潼关', '432', 't', 'tg', 'tongguan', '', '县', '610522', '0913', '3');
INSERT INTO `district` VALUES ('2965', '大荔', '432', 'd', 'dl', 'dali', '', '县', '610523', '0913', '4');
INSERT INTO `district` VALUES ('2966', '合阳', '432', 'h', 'hy', 'heyang', '', '县', '610524', '0913', '5');
INSERT INTO `district` VALUES ('2967', '澄城', '432', 'c', 'cc', 'chengcheng', '', '县', '610525', '0913', '6');
INSERT INTO `district` VALUES ('2968', '蒲城', '432', 'p', 'pc', 'pucheng', '', '县', '610526', '0913', '7');
INSERT INTO `district` VALUES ('2969', '白水', '432', 'b', 'bs', 'baishui', '', '县', '610527', '0913', '8');
INSERT INTO `district` VALUES ('2970', '富平', '432', 'f', 'fp', 'fuping', '', '县', '610528', '0913', '9');
INSERT INTO `district` VALUES ('2971', '韩城', '432', 'h', 'hc', 'hancheng', '', '市', '610581', '0913', '10');
INSERT INTO `district` VALUES ('2972', '华阴', '432', 'h', 'hy', 'huayin', '', '市', '610582', '0913', '11');
INSERT INTO `district` VALUES ('2973', '宝塔', '433', 'b', 'bt', 'baota', '', '区', '610602', '0911', '1');
INSERT INTO `district` VALUES ('2974', '延长', '433', 'y', 'yz', 'yanzhang', '', '县', '610621', '0911', '2');
INSERT INTO `district` VALUES ('2975', '延川', '433', 'y', 'yc', 'yanchuan', '', '县', '610622', '0911', '3');
INSERT INTO `district` VALUES ('2976', '子长', '433', 'z', 'zz', 'zizhang', '', '县', '610623', '0911', '4');
INSERT INTO `district` VALUES ('2977', '安塞', '433', 'a', 'as', 'ansai', '', '县', '610624', '0911', '5');
INSERT INTO `district` VALUES ('2978', '志丹', '433', 'z', 'zd', 'zhidan', '', '县', '610625', '0911', '6');
INSERT INTO `district` VALUES ('2979', '吴起', '433', 'w', 'wq', 'wuqi', '', '县', '610626', '0911', '7');
INSERT INTO `district` VALUES ('2980', '甘泉', '433', 'g', 'gq', 'ganquan', '', '县', '610627', '0911', '8');
INSERT INTO `district` VALUES ('2981', '富县', '433', 'f', 'fx', 'fuxian', '', '', '610628', '0911', '9');
INSERT INTO `district` VALUES ('2982', '洛川', '433', 'l', 'lc', 'luochuan', '', '县', '610629', '0911', '10');
INSERT INTO `district` VALUES ('2983', '宜川', '433', 'y', 'yc', 'yichuan', '', '县', '610630', '0911', '11');
INSERT INTO `district` VALUES ('2984', '黄龙', '433', 'h', 'hl', 'huanglong', '', '县', '610631', '0911', '12');
INSERT INTO `district` VALUES ('2985', '黄陵', '433', 'h', 'hl', 'huangling', '', '县', '610632', '0911', '13');
INSERT INTO `district` VALUES ('2986', '汉台', '434', 'h', 'ht', 'hantai', '', '区', '610702', '0916', '1');
INSERT INTO `district` VALUES ('2987', '南郑', '434', 'n', 'nz', 'nanzheng', '', '县', '610721', '0916', '2');
INSERT INTO `district` VALUES ('2988', '城固', '434', 'c', 'cg', 'chenggu', '', '县', '610722', '0916', '3');
INSERT INTO `district` VALUES ('2989', '洋县', '434', 'y', 'yx', 'yangxian', '', '', '610723', '0916', '4');
INSERT INTO `district` VALUES ('2990', '西乡', '434', 'x', 'xx', 'xixiang', '', '县', '610724', '0916', '5');
INSERT INTO `district` VALUES ('2991', '勉县', '434', 'm', 'mx', 'mianxian', '', '', '610725', '0916', '6');
INSERT INTO `district` VALUES ('2992', '宁强', '434', 'n', 'nq', 'ningqiang', '', '县', '610726', '0916', '7');
INSERT INTO `district` VALUES ('2993', '略阳', '434', 'l', 'ly', 'lu:eyang', '', '县', '610727', '0916', '8');
INSERT INTO `district` VALUES ('2994', '镇巴', '434', 'z', 'zb', 'zhenba', '', '县', '610728', '0916', '9');
INSERT INTO `district` VALUES ('2995', '留坝', '434', 'l', 'lb', 'liuba', '', '县', '610729', '0916', '10');
INSERT INTO `district` VALUES ('2996', '佛坪', '434', 'f', 'fp', 'foping', '', '县', '610730', '0916', '11');
INSERT INTO `district` VALUES ('2997', '榆阳', '435', 'y', 'yy', 'yuyang', '', '区', '610802', '0912', '1');
INSERT INTO `district` VALUES ('2998', '神木', '435', 's', 'sm', 'shenmu', '', '县', '610821', '0912', '2');
INSERT INTO `district` VALUES ('2999', '府谷', '435', 'f', 'fg', 'fugu', '', '县', '610822', '0912', '3');
INSERT INTO `district` VALUES ('3000', '横山', '435', 'h', 'hs', 'hengshan', '', '县', '610823', '0912', '4');
INSERT INTO `district` VALUES ('3001', '靖边', '435', 'j', 'jb', 'jingbian', '', '县', '610824', '0912', '5');
INSERT INTO `district` VALUES ('3002', '定边', '435', 'd', 'db', 'dingbian', '', '县', '610825', '0912', '6');
INSERT INTO `district` VALUES ('3003', '绥德', '435', 's', 'sd', 'suide', '', '县', '610826', '0912', '7');
INSERT INTO `district` VALUES ('3004', '米脂', '435', 'm', 'mz', 'mizhi', '', '县', '610827', '0912', '8');
INSERT INTO `district` VALUES ('3005', '佳县', '435', 'j', 'jx', 'jiaxian', '', '', '610828', '0912', '9');
INSERT INTO `district` VALUES ('3006', '吴堡', '435', 'w', 'wb', 'wubao', '', '县', '610829', '0912', '10');
INSERT INTO `district` VALUES ('3007', '清涧', '435', 'q', 'qj', 'qingjian', '', '县', '610830', '0912', '11');
INSERT INTO `district` VALUES ('3008', '子洲', '435', 'z', 'zz', 'zizhou', '', '县', '610831', '0912', '12');
INSERT INTO `district` VALUES ('3009', '汉滨', '436', 'h', 'hb', 'hanbin', '', '区', '610902', '0915', '1');
INSERT INTO `district` VALUES ('3010', '汉阴', '436', 'h', 'hy', 'hanyin', '', '县', '610921', '0915', '2');
INSERT INTO `district` VALUES ('3011', '石泉', '436', 's', 'sq', 'shiquan', '', '县', '610922', '0915', '3');
INSERT INTO `district` VALUES ('3012', '宁陕', '436', 'n', 'ns', 'ningshan', '', '县', '610923', '0915', '4');
INSERT INTO `district` VALUES ('3013', '紫阳', '436', 'z', 'zy', 'ziyang', '', '县', '610924', '0915', '5');
INSERT INTO `district` VALUES ('3014', '岚皋', '436', 'l', 'lg', 'langao', '', '县', '610925', '0915', '6');
INSERT INTO `district` VALUES ('3015', '平利', '436', 'p', 'pl', 'pingli', '', '县', '610926', '0915', '7');
INSERT INTO `district` VALUES ('3016', '镇坪', '436', 'z', 'zp', 'zhenping', '', '县', '610927', '0915', '8');
INSERT INTO `district` VALUES ('3017', '旬阳', '436', 'x', 'xy', 'xunyang', '', '县', '610928', '0915', '9');
INSERT INTO `district` VALUES ('3018', '白河', '436', 'b', 'bh', 'baihe', '', '县', '610929', '0915', '10');
INSERT INTO `district` VALUES ('3019', '商州', '437', 's', 'sz', 'shangzhou', '', '区', '611002', '0914', '1');
INSERT INTO `district` VALUES ('3020', '洛南', '437', 'l', 'ln', 'luonan', '', '县', '611021', '0914', '2');
INSERT INTO `district` VALUES ('3021', '丹凤', '437', 'd', 'df', 'danfeng', '', '县', '611022', '0914', '3');
INSERT INTO `district` VALUES ('3022', '商南', '437', 's', 'sn', 'shangnan', '', '县', '611023', '0914', '4');
INSERT INTO `district` VALUES ('3023', '山阳', '437', 's', 'sy', 'shanyang', '', '县', '611024', '0914', '5');
INSERT INTO `district` VALUES ('3024', '镇安', '437', 'z', 'za', 'zhenan', '', '县', '611025', '0914', '6');
INSERT INTO `district` VALUES ('3025', '柞水', '437', 'z', 'zs', 'zuoshui', '', '县', '611026', '0914', '7');
INSERT INTO `district` VALUES ('3026', '城关', '438', 'c', 'cg', 'chengguan', '', '区', '620102', '0931', '1');
INSERT INTO `district` VALUES ('3027', '七里河', '438', 'q', 'qlh', 'qilihe', '', '区', '620103', '0931', '2');
INSERT INTO `district` VALUES ('3028', '西固', '438', 'x', 'xg', 'xigu', '', '区', '620104', '0931', '3');
INSERT INTO `district` VALUES ('3029', '安宁', '438', 'a', 'an', 'anning', '', '区', '620105', '0931', '4');
INSERT INTO `district` VALUES ('3030', '红古', '438', 'h', 'hg', 'honggu', '', '区', '620111', '0931', '5');
INSERT INTO `district` VALUES ('3031', '永登', '438', 'y', 'yd', 'yongdeng', '', '县', '620121', '0931', '6');
INSERT INTO `district` VALUES ('3032', '皋兰', '438', 'g', 'gl', 'gaolan', '', '县', '620122', '0931', '7');
INSERT INTO `district` VALUES ('3033', '榆中', '438', 'y', 'yz', 'yuzhong', '', '县', '620123', '0931', '8');
INSERT INTO `district` VALUES ('3034', '镜铁', '439', 'j', 'jt', 'jingtie', '', '区', '620201', '0937', '1');
INSERT INTO `district` VALUES ('3035', '长城', '439', 'z', 'zc', 'zhangcheng', '', '区', '620201', '0937', '2');
INSERT INTO `district` VALUES ('3036', '雄关', '439', 'x', 'xg', 'xiongguan', '', '区', '620201', '0937', '3');
INSERT INTO `district` VALUES ('3037', '金川', '440', 'j', 'jc', 'jinchuan', '', '区', '620302', '0935', '1');
INSERT INTO `district` VALUES ('3038', '永昌', '440', 'y', 'yc', 'yongchang', '', '县', '620321', '0935', '2');
INSERT INTO `district` VALUES ('3039', '白银', '441', 'b', 'by', 'baiyin', '', '区', '620402', '0943', '1');
INSERT INTO `district` VALUES ('3040', '平川', '441', 'p', 'pc', 'pingchuan', '', '区', '620403', '0943', '2');
INSERT INTO `district` VALUES ('3041', '靖远', '441', 'j', 'jy', 'jingyuan', '', '县', '620421', '0943', '3');
INSERT INTO `district` VALUES ('3042', '会宁', '441', 'h', 'hn', 'huining', '', '县', '620422', '0943', '4');
INSERT INTO `district` VALUES ('3043', '景泰', '441', 'j', 'jt', 'jingtai', '', '县', '620423', '0943', '5');
INSERT INTO `district` VALUES ('3044', '秦州', '442', 'q', 'qz', 'qinzhou', '', '区', '620502', '0938', '1');
INSERT INTO `district` VALUES ('3045', '麦积', '442', 'm', 'mj', 'maiji', '', '区', '620503', '0938', '2');
INSERT INTO `district` VALUES ('3046', '清水', '442', 'q', 'qs', 'qingshui', '', '县', '620521', '0938', '3');
INSERT INTO `district` VALUES ('3047', '秦安', '442', 'q', 'qa', 'qinan', '', '县', '620522', '0938', '4');
INSERT INTO `district` VALUES ('3048', '甘谷', '442', 'g', 'gg', 'gangu', '', '县', '620523', '0938', '5');
INSERT INTO `district` VALUES ('3049', '武山', '442', 'w', 'ws', 'wushan', '', '县', '620524', '0938', '6');
INSERT INTO `district` VALUES ('3050', '张家川', '442', 'z', 'zjc', 'zhangjiachuan', '回族', '自治县', '620525', '0938', '7');
INSERT INTO `district` VALUES ('3051', '凉州', '443', 'l', 'lz', 'liangzhou', '', '区', '620602', '0935', '1');
INSERT INTO `district` VALUES ('3052', '民勤', '443', 'm', 'mq', 'minqin', '', '县', '620621', '0935', '2');
INSERT INTO `district` VALUES ('3053', '古浪', '443', 'g', 'gl', 'gulang', '', '县', '620622', '0935', '3');
INSERT INTO `district` VALUES ('3054', '天祝', '443', 't', 'tz', 'tianzhu', '藏族', '自治县', '620623', '0935', '4');
INSERT INTO `district` VALUES ('3055', '甘州', '444', 'g', 'gz', 'ganzhou', '', '区', '620702', '0936', '1');
INSERT INTO `district` VALUES ('3056', '肃南', '444', 's', 'sn', 'sunan', '裕固族', '自治县', '620721', '0936', '2');
INSERT INTO `district` VALUES ('3057', '民乐', '444', 'm', 'ml', 'minle', '', '县', '620722', '0936', '3');
INSERT INTO `district` VALUES ('3058', '临泽', '444', 'l', 'lz', 'linze', '', '县', '620723', '0936', '4');
INSERT INTO `district` VALUES ('3059', '高台', '444', 'g', 'gt', 'gaotai', '', '县', '620724', '0936', '5');
INSERT INTO `district` VALUES ('3060', '山丹', '444', 's', 'sd', 'shandan', '', '县', '620725', '0936', '6');
INSERT INTO `district` VALUES ('3061', '崆峒', '445', 'k', 'kt', 'kongtong', '', '区', '620802', '0933', '1');
INSERT INTO `district` VALUES ('3062', '泾川', '445', 'j', 'jc', 'jingchuan', '', '县', '620821', '0933', '2');
INSERT INTO `district` VALUES ('3063', '灵台', '445', 'l', 'lt', 'lingtai', '', '县', '620822', '0933', '3');
INSERT INTO `district` VALUES ('3064', '崇信', '445', 'c', 'cx', 'chongxin', '', '县', '620823', '0933', '4');
INSERT INTO `district` VALUES ('3065', '华亭', '445', 'h', 'ht', 'huating', '', '县', '620824', '0933', '5');
INSERT INTO `district` VALUES ('3066', '庄浪', '445', 'z', 'zl', 'zhuanglang', '', '县', '620825', '0933', '6');
INSERT INTO `district` VALUES ('3067', '静宁', '445', 'j', 'jn', 'jingning', '', '县', '620826', '0933', '7');
INSERT INTO `district` VALUES ('3068', '肃州', '446', 's', 'sz', 'suzhou', '', '区', '620902', '0937', '1');
INSERT INTO `district` VALUES ('3069', '金塔', '446', 'j', 'jt', 'jinta', '', '县', '620921', '0937', '2');
INSERT INTO `district` VALUES ('3070', '瓜州', '446', 'g', 'gz', 'guazhou', '', '县', '620922', '0937', '3');
INSERT INTO `district` VALUES ('3071', '肃北', '446', 's', 'sb', 'subei', '蒙古族', '自治县', '620923', '0937', '4');
INSERT INTO `district` VALUES ('3072', '阿克塞', '446', 'a', 'aks', 'akesai', '哈萨克族', '自治县', '620924', '0937', '5');
INSERT INTO `district` VALUES ('3073', '玉门', '446', 'y', 'ym', 'yumen', '', '市', '620981', '0937', '6');
INSERT INTO `district` VALUES ('3074', '敦煌', '446', 'd', 'dh', 'dunhuang', '', '市', '620982', '0937', '7');
INSERT INTO `district` VALUES ('3075', '西峰', '447', 'x', 'xf', 'xifeng', '', '区', '621002', '0934', '1');
INSERT INTO `district` VALUES ('3076', '庆城', '447', 'q', 'qc', 'qingcheng', '', '县', '621021', '0934', '2');
INSERT INTO `district` VALUES ('3077', '环县', '447', 'h', 'hx', 'huanxian', '', '', '621022', '0934', '3');
INSERT INTO `district` VALUES ('3078', '华池', '447', 'h', 'hc', 'huachi', '', '县', '621023', '0934', '4');
INSERT INTO `district` VALUES ('3079', '合水', '447', 'h', 'hs', 'heshui', '', '县', '621024', '0934', '5');
INSERT INTO `district` VALUES ('3080', '正宁', '447', 'z', 'zn', 'zhengning', '', '县', '621025', '0934', '6');
INSERT INTO `district` VALUES ('3081', '宁县', '447', 'n', 'nx', 'ningxian', '', '', '621026', '0934', '7');
INSERT INTO `district` VALUES ('3082', '镇原', '447', 'z', 'zy', 'zhenyuan', '', '县', '621027', '0934', '8');
INSERT INTO `district` VALUES ('3083', '安定', '448', 'a', 'ad', 'anding', '', '区', '621102', '0932', '1');
INSERT INTO `district` VALUES ('3084', '通渭', '448', 't', 'tw', 'tongwei', '', '县', '621121', '0932', '2');
INSERT INTO `district` VALUES ('3085', '陇西', '448', 'l', 'lx', 'longxi', '', '县', '621122', '0932', '3');
INSERT INTO `district` VALUES ('3086', '渭源', '448', 'w', 'wy', 'weiyuan', '', '县', '621123', '0932', '4');
INSERT INTO `district` VALUES ('3087', '临洮', '448', 'l', 'lt', 'lintao', '', '县', '621124', '0932', '5');
INSERT INTO `district` VALUES ('3088', '漳县', '448', 'z', 'zx', 'zhangxian', '', '', '621125', '0932', '6');
INSERT INTO `district` VALUES ('3089', '岷县', '448', 'm', 'mx', 'minxian', '', '', '621126', '0932', '7');
INSERT INTO `district` VALUES ('3090', '武都', '449', 'w', 'wd', 'wudou', '', '区', '621202', '0939', '1');
INSERT INTO `district` VALUES ('3091', '成县', '449', 'c', 'cx', 'chengxian', '', '', '621221', '0939', '2');
INSERT INTO `district` VALUES ('3092', '文县', '449', 'w', 'wx', 'wenxian', '', '', '621222', '0939', '3');
INSERT INTO `district` VALUES ('3093', '宕昌', '449', 'd', 'dc', 'dangchang', '', '县', '621223', '0939', '4');
INSERT INTO `district` VALUES ('3094', '康县', '449', 'k', 'kx', 'kangxian', '', '', '621224', '0939', '5');
INSERT INTO `district` VALUES ('3095', '西和', '449', 'x', 'xh', 'xihe', '', '县', '621225', '0939', '6');
INSERT INTO `district` VALUES ('3096', '礼县', '449', 'l', 'lx', 'lixian', '', '', '621226', '0939', '7');
INSERT INTO `district` VALUES ('3097', '徽县', '449', 'h', 'hx', 'huixian', '', '', '621227', '0939', '8');
INSERT INTO `district` VALUES ('3098', '两当', '449', 'l', 'ld', 'liangdang', '', '县', '621228', '0939', '9');
INSERT INTO `district` VALUES ('3099', '临夏', '450', 'l', 'lx', 'linxia', '', '市', '622901', '0930', '1');
INSERT INTO `district` VALUES ('3100', '临夏', '450', 'l', 'lx', 'linxia', '', '县', '622921', '0930', '2');
INSERT INTO `district` VALUES ('3101', '康乐', '450', 'k', 'kl', 'kangle', '', '县', '622922', '0930', '3');
INSERT INTO `district` VALUES ('3102', '永靖', '450', 'y', 'yj', 'yongjing', '', '县', '622923', '0930', '4');
INSERT INTO `district` VALUES ('3103', '广河', '450', 'g', 'gh', 'guanghe', '', '县', '622924', '0930', '5');
INSERT INTO `district` VALUES ('3104', '和政', '450', 'h', 'hz', 'hezheng', '', '县', '622925', '0930', '6');
INSERT INTO `district` VALUES ('3105', '东乡族', '450', 'd', 'dxz', 'dongxiangzu', '', '自治县', '622926', '0930', '7');
INSERT INTO `district` VALUES ('3106', '积石山', '450', 'j', 'jss', 'jishishan', '保安族东乡族撒拉族', '自治县', '622927', '0930', '8');
INSERT INTO `district` VALUES ('3107', '合作', '451', 'h', 'hz', 'hezuo', '', '市', '623001', '0941', '1');
INSERT INTO `district` VALUES ('3108', '临潭', '451', 'l', 'lt', 'lintan', '', '县', '623021', '0941', '2');
INSERT INTO `district` VALUES ('3109', '卓尼', '451', 'z', 'zn', 'zhuoni', '', '县', '623022', '0941', '3');
INSERT INTO `district` VALUES ('3110', '舟曲', '451', 'z', 'zq', 'zhouqu', '', '县', '623023', '0941', '4');
INSERT INTO `district` VALUES ('3111', '迭部', '451', 'd', 'db', 'diebu', '', '县', '623024', '0941', '5');
INSERT INTO `district` VALUES ('3112', '玛曲', '451', 'm', 'mq', 'maqu', '', '县', '623025', '0941', '6');
INSERT INTO `district` VALUES ('3113', '碌曲', '451', 'l', 'lq', 'liuqu', '', '县', '623026', '0941', '7');
INSERT INTO `district` VALUES ('3114', '夏河', '451', 'x', 'xh', 'xiahe', '', '县', '623027', '0941', '8');
INSERT INTO `district` VALUES ('3115', '城东', '452', 'c', 'cd', 'chengdong', '', '区', '630102', '0971', '1');
INSERT INTO `district` VALUES ('3116', '城中', '452', 'c', 'cz', 'chengzhong', '', '区', '630103', '0971', '2');
INSERT INTO `district` VALUES ('3117', '城西', '452', 'c', 'cx', 'chengxi', '', '区', '630104', '0971', '3');
INSERT INTO `district` VALUES ('3118', '城北', '452', 'c', 'cb', 'chengbei', '', '区', '630105', '0971', '4');
INSERT INTO `district` VALUES ('3119', '大通', '452', 'd', 'dt', 'datong', '回族土族', '自治县', '630121', '0971', '5');
INSERT INTO `district` VALUES ('3120', '湟中', '452', 'h', 'hz', 'huangzhong', '', '县', '630122', '0971', '6');
INSERT INTO `district` VALUES ('3121', '湟源', '452', 'h', 'hy', 'huangyuan', '', '县', '630123', '0971', '7');
INSERT INTO `district` VALUES ('3122', '乐都', '453', 'l', 'ld', 'ledou', '', '区', '630202', '0972', '1');
INSERT INTO `district` VALUES ('3123', '平安', '453', 'p', 'pa', 'pingan', '', '区', '632121', '0972', '2');
INSERT INTO `district` VALUES ('3124', '民和', '453', 'm', 'mh', 'minhe', '回族土族', '自治县', '632122', '0972', '3');
INSERT INTO `district` VALUES ('3125', '互助', '453', 'h', 'hz', 'huzhu', '土族', '自治县', '632126', '0972', '4');
INSERT INTO `district` VALUES ('3126', '化隆', '453', 'h', 'hl', 'hualong', '回族', '自治县', '632127', '0972', '5');
INSERT INTO `district` VALUES ('3127', '循化', '453', 'x', 'xh', 'xunhua', '撒拉族', '自治县', '632128', '0972', '6');
INSERT INTO `district` VALUES ('3128', '门源', '454', 'm', 'my', 'menyuan', '回族', '自治县', '632221', '0970', '1');
INSERT INTO `district` VALUES ('3129', '祁连', '454', 'q', 'ql', 'qilian', '', '县', '632222', '0970', '2');
INSERT INTO `district` VALUES ('3130', '海晏', '454', 'h', 'hy', 'haiyan', '', '县', '632223', '0970', '3');
INSERT INTO `district` VALUES ('3131', '刚察', '454', 'g', 'gc', 'gangcha', '', '县', '632224', '0970', '4');
INSERT INTO `district` VALUES ('3132', '同仁', '455', 't', 'tr', 'tongren', '', '县', '632321', '0973', '1');
INSERT INTO `district` VALUES ('3133', '尖扎', '455', 'j', 'jz', 'jianzha', '', '县', '632322', '0973', '2');
INSERT INTO `district` VALUES ('3134', '泽库', '455', 'z', 'zk', 'zeku', '', '县', '632323', '0973', '3');
INSERT INTO `district` VALUES ('3135', '河南', '455', 'h', 'hn', 'henan', '蒙古族', '自治县', '632324', '0973', '4');
INSERT INTO `district` VALUES ('3136', '共和', '456', 'g', 'gh', 'gonghe', '', '县', '632521', '0974', '1');
INSERT INTO `district` VALUES ('3137', '同德', '456', 't', 'td', 'tongde', '', '县', '632522', '0974', '2');
INSERT INTO `district` VALUES ('3138', '贵德', '456', 'g', 'gd', 'guide', '', '县', '632523', '0974', '3');
INSERT INTO `district` VALUES ('3139', '兴海', '456', 'x', 'xh', 'xinghai', '', '县', '632524', '0974', '4');
INSERT INTO `district` VALUES ('3140', '贵南', '456', 'g', 'gn', 'guinan', '', '县', '632525', '0974', '5');
INSERT INTO `district` VALUES ('3141', '玛沁', '457', 'm', 'mq', 'maqin', '', '县', '632621', '0975', '1');
INSERT INTO `district` VALUES ('3142', '班玛', '457', 'b', 'bm', 'banma', '', '县', '632622', '0975', '2');
INSERT INTO `district` VALUES ('3143', '甘德', '457', 'g', 'gd', 'gande', '', '县', '632623', '0975', '3');
INSERT INTO `district` VALUES ('3144', '达日', '457', 'd', 'dr', 'dari', '', '县', '632624', '0975', '4');
INSERT INTO `district` VALUES ('3145', '久治', '457', 'j', 'jz', 'jiuzhi', '', '县', '632625', '0975', '5');
INSERT INTO `district` VALUES ('3146', '玛多', '457', 'm', 'md', 'maduo', '', '县', '632626', '0975', '6');
INSERT INTO `district` VALUES ('3147', '玉树', '458', 'y', 'ys', 'yushu', '', '市', '632721', '0976', '1');
INSERT INTO `district` VALUES ('3148', '杂多', '458', 'z', 'zd', 'zaduo', '', '县', '632722', '0976', '2');
INSERT INTO `district` VALUES ('3149', '治多', '458', 'z', 'zd', 'zhiduo', '', '县', '632724', '0976', '3');
INSERT INTO `district` VALUES ('3150', '囊谦', '458', 'n', 'nq', 'nangqian', '', '县', '632725', '0976', '4');
INSERT INTO `district` VALUES ('3151', '曲麻莱', '458', 'q', 'qml', 'qumalai', '', '县', '632726', '0976', '5');
INSERT INTO `district` VALUES ('3152', '大柴旦', '459', 'd', 'dcd', 'dachaidan', '', '行委', '', '0977', '1');
INSERT INTO `district` VALUES ('3153', '冷湖', '459', 'l', 'lh', 'lenghu', '', '行委', '', '0977', '2');
INSERT INTO `district` VALUES ('3154', '茫崖', '459', 'm', 'my', 'mangya', '', '行委', '', '0977', '3');
INSERT INTO `district` VALUES ('3155', '格尔木', '459', 'g', 'gem', 'geermu', '', '市', '632801', '0977', '4');
INSERT INTO `district` VALUES ('3156', '德令哈', '459', 'd', 'dlh', 'delingha', '', '市', '632802', '0977', '5');
INSERT INTO `district` VALUES ('3157', '乌兰', '459', 'w', 'wl', 'wulan', '', '县', '632821', '0977', '6');
INSERT INTO `district` VALUES ('3158', '都兰', '459', 'd', 'dl', 'doulan', '', '县', '632822', '0977', '7');
INSERT INTO `district` VALUES ('3159', '天峻', '459', 't', 'tj', 'tianjun', '', '县', '632823', '0977', '8');
INSERT INTO `district` VALUES ('3160', '兴庆', '460', 'x', 'xq', 'xingqing', '', '区', '640104', '0951', '1');
INSERT INTO `district` VALUES ('3161', '西夏', '460', 'x', 'xx', 'xixia', '', '区', '640105', '0951', '2');
INSERT INTO `district` VALUES ('3162', '金凤', '460', 'j', 'jf', 'jinfeng', '', '区', '640106', '0951', '3');
INSERT INTO `district` VALUES ('3163', '永宁', '460', 'y', 'yn', 'yongning', '', '县', '640121', '0951', '4');
INSERT INTO `district` VALUES ('3164', '贺兰', '460', 'h', 'hl', 'helan', '', '县', '640122', '0951', '5');
INSERT INTO `district` VALUES ('3165', '灵武', '460', 'l', 'lw', 'lingwu', '', '市', '640181', '0951', '6');
INSERT INTO `district` VALUES ('3166', '大武口', '461', 'd', 'dwk', 'dawukou', '', '区', '640202', '0952', '1');
INSERT INTO `district` VALUES ('3167', '惠农', '461', 'h', 'hn', 'huinong', '', '区', '640205', '0952', '2');
INSERT INTO `district` VALUES ('3168', '平罗', '461', 'p', 'pl', 'pingluo', '', '县', '640221', '0952', '3');
INSERT INTO `district` VALUES ('3169', '红寺堡', '462', 'h', 'hsb', 'hongsibao', '', '区', '', '0953', '1');
INSERT INTO `district` VALUES ('3170', '利通', '462', 'l', 'lt', 'litong', '', '区', '640302', '0953', '2');
INSERT INTO `district` VALUES ('3171', '盐池', '462', 'y', 'yc', 'yanchi', '', '县', '640323', '0953', '3');
INSERT INTO `district` VALUES ('3172', '同心', '462', 't', 'tx', 'tongxin', '', '县', '640324', '0953', '4');
INSERT INTO `district` VALUES ('3173', '青铜峡', '462', 'q', 'qtx', 'qingtongxia', '', '市', '640381', '0953', '5');
INSERT INTO `district` VALUES ('3174', '原州', '463', 'y', 'yz', 'yuanzhou', '', '区', '640402', '0954', '1');
INSERT INTO `district` VALUES ('3175', '西吉', '463', 'x', 'xj', 'xiji', '', '县', '640422', '0954', '2');
INSERT INTO `district` VALUES ('3176', '隆德', '463', 'l', 'ld', 'longde', '', '县', '640423', '0954', '3');
INSERT INTO `district` VALUES ('3177', '泾源', '463', 'j', 'jy', 'jingyuan', '', '县', '640424', '0954', '4');
INSERT INTO `district` VALUES ('3178', '彭阳', '463', 'p', 'py', 'pengyang', '', '县', '640425', '0954', '5');
INSERT INTO `district` VALUES ('3179', '沙坡头', '464', 's', 'spt', 'shapotou', '', '区', '640502', '', '1');
INSERT INTO `district` VALUES ('3180', '中宁', '464', 'z', 'zn', 'zhongning', '', '县', '640521', '', '2');
INSERT INTO `district` VALUES ('3181', '海原', '464', 'h', 'hy', 'haiyuan', '', '县', '640522', '', '3');
INSERT INTO `district` VALUES ('3182', '天山', '465', 't', 'ts', 'tianshan', '', '区', '650102', '0991', '1');
INSERT INTO `district` VALUES ('3183', '沙依巴克', '465', 's', 'sybk', 'shayibake', '', '区', '650103', '0991', '2');
INSERT INTO `district` VALUES ('3184', '新市', '465', 'x', 'xs', 'xinshi', '', '区', '650104', '0991', '3');
INSERT INTO `district` VALUES ('3185', '水磨沟', '465', 's', 'smg', 'shuimogou', '', '区', '650105', '0991', '4');
INSERT INTO `district` VALUES ('3186', '头屯河', '465', 't', 'tth', 'toutunhe', '', '区', '650106', '0991', '5');
INSERT INTO `district` VALUES ('3187', '达坂城', '465', 'd', 'dbc', 'dabancheng', '', '区', '650107', '0991', '6');
INSERT INTO `district` VALUES ('3188', '米东', '465', 'm', 'md', 'midong', '', '区', '650109', '0991', '7');
INSERT INTO `district` VALUES ('3189', '乌鲁木齐', '465', 'w', 'wlmq', 'wulumuqi', '', '县', '650121', '0991', '8');
INSERT INTO `district` VALUES ('3190', '独山子', '466', 'd', 'dsz', 'dushanzi', '', '区', '650202', '0990', '1');
INSERT INTO `district` VALUES ('3191', '克拉玛依', '466', 'k', 'klmy', 'kelamayi', '', '区', '650203', '0990', '2');
INSERT INTO `district` VALUES ('3192', '白碱滩', '466', 'b', 'bjt', 'baijiantan', '', '区', '650204', '0990', '3');
INSERT INTO `district` VALUES ('3193', '乌尔禾', '466', 'w', 'weh', 'wuerhe', '', '区', '650205', '0990', '4');
INSERT INTO `district` VALUES ('3194', '高昌', '467', 'g', 'gc', 'gaochang', '', '区', '652101', '0995', '1');
INSERT INTO `district` VALUES ('3195', '鄯善', '467', 's', 'ss', 'shanshan', '', '县', '652122', '0995', '2');
INSERT INTO `district` VALUES ('3196', '托克逊', '467', 't', 'tkx', 'tuokexun', '', '县', '652123', '0995', '3');
INSERT INTO `district` VALUES ('3197', '哈密', '468', 'h', 'hm', 'hami', '', '市', '652201', '0902', '1');
INSERT INTO `district` VALUES ('3198', '巴里坤', '468', 'b', 'blk', 'balikun', '哈萨克', '自治县', '652222', '0902', '2');
INSERT INTO `district` VALUES ('3199', '伊吾', '468', 'y', 'yw', 'yiwu', '', '县', '652223', '0902', '3');
INSERT INTO `district` VALUES ('3200', '昌吉', '469', 'c', 'cj', 'changji', '', '市', '652301', '0994', '1');
INSERT INTO `district` VALUES ('3201', '阜康', '469', 'f', 'fk', 'fukang', '', '市', '652302', '0994', '2');
INSERT INTO `district` VALUES ('3202', '呼图壁', '469', 'h', 'htb', 'hutubi', '', '县', '652323', '0994', '3');
INSERT INTO `district` VALUES ('3203', '玛纳斯', '469', 'm', 'mns', 'manasi', '', '县', '652324', '0994', '4');
INSERT INTO `district` VALUES ('3204', '奇台', '469', 'q', 'qt', 'qitai', '', '县', '652325', '0994', '5');
INSERT INTO `district` VALUES ('3205', '吉木萨尔', '469', 'j', 'jmse', 'jimusaer', '', '县', '652327', '0994', '6');
INSERT INTO `district` VALUES ('3206', '木垒', '469', 'm', 'ml', 'mulei', '哈萨克', '自治县', '652328', '0994', '7');
INSERT INTO `district` VALUES ('3207', '阿拉山口', '470', 'a', 'alsk', 'alashankou', '', '市', '', '0909', '1');
INSERT INTO `district` VALUES ('3208', '博乐', '470', 'b', 'bl', 'bole', '', '市', '652701', '0909', '2');
INSERT INTO `district` VALUES ('3209', '精河', '470', 'j', 'jh', 'jinghe', '', '县', '652722', '0909', '3');
INSERT INTO `district` VALUES ('3210', '温泉', '470', 'w', 'wq', 'wenquan', '', '县', '652723', '0909', '4');
INSERT INTO `district` VALUES ('3211', '库尔勒', '471', 'k', 'kel', 'kuerle', '', '市', '652801', '0996', '1');
INSERT INTO `district` VALUES ('3212', '轮台', '471', 'l', 'lt', 'luntai', '', '县', '652822', '0996', '2');
INSERT INTO `district` VALUES ('3213', '尉犁', '471', 'w', 'wl', 'weili', '', '县', '652823', '0996', '3');
INSERT INTO `district` VALUES ('3214', '若羌', '471', 'r', 'rq', 'ruoqiang', '', '县', '652824', '0996', '4');
INSERT INTO `district` VALUES ('3215', '且末', '471', 'q', 'qm', 'qiemo', '', '县', '652825', '0996', '5');
INSERT INTO `district` VALUES ('3216', '焉耆', '471', 'y', 'yq', 'yanqi', '回族', '自治县', '652826', '0996', '6');
INSERT INTO `district` VALUES ('3217', '和静', '471', 'h', 'hj', 'hejing', '', '县', '652827', '0996', '7');
INSERT INTO `district` VALUES ('3218', '和硕', '471', 'h', 'hs', 'heshuo', '', '县', '652828', '0996', '8');
INSERT INTO `district` VALUES ('3219', '博湖', '471', 'b', 'bh', 'bohu', '', '县', '652829', '0996', '9');
INSERT INTO `district` VALUES ('3220', '阿克苏', '472', 'a', 'aks', 'akesu', '', '市', '652901', '', '1');
INSERT INTO `district` VALUES ('3221', '温宿', '472', 'w', 'ws', 'wensu', '', '县', '652922', '', '2');
INSERT INTO `district` VALUES ('3222', '库车', '472', 'k', 'kc', 'kuche', '', '县', '652923', '', '3');
INSERT INTO `district` VALUES ('3223', '沙雅', '472', 's', 'sy', 'shaya', '', '县', '652924', '', '4');
INSERT INTO `district` VALUES ('3224', '新和', '472', 'x', 'xh', 'xinhe', '', '县', '652925', '', '5');
INSERT INTO `district` VALUES ('3225', '拜城', '472', 'b', 'bc', 'baicheng', '', '县', '652926', '', '6');
INSERT INTO `district` VALUES ('3226', '乌什', '472', 'w', 'ws', 'wushen', '', '县', '652927', '', '7');
INSERT INTO `district` VALUES ('3227', '阿瓦提', '472', 'a', 'awt', 'awati', '', '县', '652928', '', '8');
INSERT INTO `district` VALUES ('3228', '柯坪', '472', 'k', 'kp', 'keping', '', '县', '652929', '', '9');
INSERT INTO `district` VALUES ('3229', '阿图什', '473', 'a', 'ats', 'atushen', '', '市', '653001', '0908', '1');
INSERT INTO `district` VALUES ('3230', '阿克陶', '473', 'a', 'akt', 'aketao', '', '县', '653022', '0908', '2');
INSERT INTO `district` VALUES ('3231', '阿合奇', '473', 'a', 'ahq', 'aheqi', '', '县', '653023', '0997', '3');
INSERT INTO `district` VALUES ('3232', '乌恰', '473', 'w', 'wq', 'wuqia', '', '县', '653024', '0908', '4');
INSERT INTO `district` VALUES ('3233', '喀什', '474', 'k', 'ks', 'kashen', '', '市', '653101', '0998', '1');
INSERT INTO `district` VALUES ('3234', '疏附', '474', 's', 'sf', 'shufu', '', '县', '653121', '0998', '2');
INSERT INTO `district` VALUES ('3235', '疏勒', '474', 's', 'sl', 'shule', '', '县', '653122', '0998', '3');
INSERT INTO `district` VALUES ('3236', '英吉沙', '474', 'y', 'yjs', 'yingjisha', '', '县', '653123', '0998', '4');
INSERT INTO `district` VALUES ('3237', '泽普', '474', 'z', 'zp', 'zepu', '', '县', '653124', '0998', '5');
INSERT INTO `district` VALUES ('3238', '莎车', '474', 's', 'sc', 'shache', '', '县', '653125', '0998', '6');
INSERT INTO `district` VALUES ('3239', '叶城', '474', 'y', 'yc', 'yecheng', '', '县', '653126', '0998', '7');
INSERT INTO `district` VALUES ('3240', '麦盖提', '474', 'm', 'mgt', 'maigaiti', '', '县', '653127', '0998', '8');
INSERT INTO `district` VALUES ('3241', '岳普湖', '474', 'y', 'yph', 'yuepuhu', '', '县', '653128', '0998', '9');
INSERT INTO `district` VALUES ('3242', '伽师', '474', 'j', 'js', 'jiashi', '', '县', '653129', '0998', '10');
INSERT INTO `district` VALUES ('3243', '巴楚', '474', 'b', 'bc', 'bachu', '', '县', '653130', '0998', '11');
INSERT INTO `district` VALUES ('3244', '塔什库尔干', '474', 't', 'tskeg', 'tashenkuergan', '塔吉克', '自治县', '653131', '0998', '12');
INSERT INTO `district` VALUES ('3245', '和田', '475', 'h', 'ht', 'hetian', '', '市', '653201', '0903', '1');
INSERT INTO `district` VALUES ('3246', '和田', '475', 'h', 'ht', 'hetian', '', '县', '653221', '0903', '2');
INSERT INTO `district` VALUES ('3247', '墨玉', '475', 'm', 'my', 'moyu', '', '县', '653222', '0903', '3');
INSERT INTO `district` VALUES ('3248', '皮山', '475', 'p', 'ps', 'pishan', '', '县', '653223', '0903', '4');
INSERT INTO `district` VALUES ('3249', '洛浦', '475', 'l', 'lp', 'luopu', '', '县', '653224', '0903', '5');
INSERT INTO `district` VALUES ('3250', '策勒', '475', 'c', 'cl', 'cele', '', '县', '653225', '0903', '6');
INSERT INTO `district` VALUES ('3251', '于田', '475', 'y', 'yt', 'yutian', '', '县', '653226', '0903', '7');
INSERT INTO `district` VALUES ('3252', '民丰', '475', 'm', 'mf', 'minfeng', '', '县', '653227', '0903', '8');
INSERT INTO `district` VALUES ('3253', '伊宁', '476', 'y', 'yn', 'yining', '', '市', '654002', '0999', '1');
INSERT INTO `district` VALUES ('3254', '奎屯', '476', 'k', 'kt', 'kuitun', '', '市', '654003', '0999', '2');
INSERT INTO `district` VALUES ('3255', '伊宁', '476', 'y', 'yn', 'yining', '', '县', '654021', '0999', '4');
INSERT INTO `district` VALUES ('3256', '察布查尔锡伯', '476', 'c', 'cbcexb', 'chabuchaerxibo', '', '自治县', '654022', '0999', '5');
INSERT INTO `district` VALUES ('3257', '霍城', '476', 'h', 'hc', 'huocheng', '', '县', '654023', '0999', '6');
INSERT INTO `district` VALUES ('3258', '巩留', '476', 'g', 'gl', 'gongliu', '', '县', '654024', '0999', '7');
INSERT INTO `district` VALUES ('3259', '新源', '476', 'x', 'xy', 'xinyuan', '', '县', '654025', '0999', '8');
INSERT INTO `district` VALUES ('3260', '昭苏', '476', 'z', 'zs', 'zhaosu', '', '县', '654026', '0999', '9');
INSERT INTO `district` VALUES ('3261', '特克斯', '476', 't', 'tks', 'tekesi', '', '县', '654027', '0999', '10');
INSERT INTO `district` VALUES ('3262', '尼勒克', '476', 'n', 'nlk', 'nileke', '', '县', '654028', '0999', '11');
INSERT INTO `district` VALUES ('3263', '塔城', '477', 't', 'tc', 'tacheng', '', '市', '654201', '0901', '1');
INSERT INTO `district` VALUES ('3264', '乌苏', '477', 'w', 'ws', 'wusu', '', '市', '654202', '0901', '2');
INSERT INTO `district` VALUES ('3265', '额敏', '477', 'e', 'em', 'emin', '', '县', '654221', '0901', '3');
INSERT INTO `district` VALUES ('3266', '沙湾', '477', 's', 'sw', 'shawan', '', '县', '654223', '0901', '4');
INSERT INTO `district` VALUES ('3267', '托里', '477', 't', 'tl', 'tuoli', '', '县', '654224', '0901', '5');
INSERT INTO `district` VALUES ('3268', '裕民', '477', 'y', 'ym', 'yumin', '', '县', '654225', '0901', '6');
INSERT INTO `district` VALUES ('3269', '和布克赛尔', '477', 'h', 'hbkse', 'hebukesaier', '蒙古', '自治县', '654226', '0901', '7');
INSERT INTO `district` VALUES ('3270', '阿勒泰', '478', 'a', 'alt', 'aletai', '', '市', '654301', '0906', '1');
INSERT INTO `district` VALUES ('3271', '布尔津', '478', 'b', 'bej', 'buerjin', '', '县', '654321', '0906', '2');
INSERT INTO `district` VALUES ('3272', '富蕴', '478', 'f', 'fy', 'fuyun', '', '县', '654322', '0906', '3');
INSERT INTO `district` VALUES ('3273', '福海', '478', 'f', 'fh', 'fuhai', '', '县', '654323', '0906', '4');
INSERT INTO `district` VALUES ('3274', '哈巴河', '478', 'h', 'hbh', 'habahe', '', '县', '654324', '0906', '5');
INSERT INTO `district` VALUES ('3275', '青河', '478', 'q', 'qh', 'qinghe', '', '县', '654325', '0906', '6');
INSERT INTO `district` VALUES ('3276', '吉木乃', '478', 'j', 'jmn', 'jimunai', '', '县', '654326', '0906', '7');
INSERT INTO `district` VALUES ('3277', '松山', '485', 's', 'ss', 'songshan', '', '区', '6300100', '02', '1');
INSERT INTO `district` VALUES ('3278', '信义', '485', 'x', 'xy', 'xinyi', '', '区', '6300200', '02', '2');
INSERT INTO `district` VALUES ('3279', '大安', '485', 'd', 'da', 'daan', '', '区', '6300300', '02', '3');
INSERT INTO `district` VALUES ('3280', '中山', '485', 'z', 'zs', 'zhongshan', '', '区', '6300400', '02', '4');
INSERT INTO `district` VALUES ('3281', '中正', '485', 'z', 'zz', 'zhongzheng', '', '区', '6300500', '02', '5');
INSERT INTO `district` VALUES ('3282', '大同', '485', 'd', 'dt', 'datong', '', '区', '6300600', '02', '6');
INSERT INTO `district` VALUES ('3283', '万华', '485', 'w', 'wh', 'wanhua', '', '区', '6300700', '02', '7');
INSERT INTO `district` VALUES ('3284', '文山', '485', 'w', 'ws', 'wenshan', '', '区', '6300800', '02', '8');
INSERT INTO `district` VALUES ('3285', '南港', '485', 'n', 'ng', 'nangang', '', '区', '6300900', '02', '9');
INSERT INTO `district` VALUES ('3286', '内湖', '485', 'n', 'nh', 'neihu', '', '区', '6301000', '02', '10');
INSERT INTO `district` VALUES ('3287', '士林', '485', 's', 'sl', 'shilin', '', '区', '6301100', '02', '11');
INSERT INTO `district` VALUES ('3288', '北投', '485', 'b', 'bt', 'beitou', '', '区', '6301200', '02', '12');
INSERT INTO `district` VALUES ('3289', '盐埕', '486', 'y', 'yc', 'yancheng', '', '区', '6400100', '07', '1');
INSERT INTO `district` VALUES ('3290', '鼓山', '486', 'g', 'gs', 'gushan', '', '区', '6400200', '07', '2');
INSERT INTO `district` VALUES ('3291', '左营', '486', 'z', 'zy', 'zuoying', '', '区', '6400300', '07', '3');
INSERT INTO `district` VALUES ('3292', '楠梓', '486', 'n', 'nz', 'nanzi', '', '区', '6400400', '07', '4');
INSERT INTO `district` VALUES ('3293', '三民', '486', 's', 'sm', 'sanmin', '', '区', '6400500', '07', '5');
INSERT INTO `district` VALUES ('3294', '新兴', '486', 'x', 'xx', 'xinxing', '', '区', '6400600', '07', '6');
INSERT INTO `district` VALUES ('3295', '前金', '486', 'q', 'qj', 'qianjin', '', '区', '6400700', '07', '7');
INSERT INTO `district` VALUES ('3296', '苓雅', '486', 'l', 'ly', 'lingya', '', '区', '6400800', '07', '8');
INSERT INTO `district` VALUES ('3297', '前镇', '486', 'q', 'qz', 'qianzhen', '', '区', '6400900', '07', '9');
INSERT INTO `district` VALUES ('3298', '旗津', '486', 'q', 'qj', 'qijin', '', '区', '6401000', '07', '10');
INSERT INTO `district` VALUES ('3299', '小港', '486', 'x', 'xg', 'xiaogang', '', '区', '6401100', '07', '11');
INSERT INTO `district` VALUES ('3300', '中正', '487', 'z', 'zz', 'zhongzheng', '', '区', '1001701', '02', '1');
INSERT INTO `district` VALUES ('3301', '七堵', '487', 'q', 'qd', 'qidu', '', '区', '1001702', '02', '2');
INSERT INTO `district` VALUES ('3302', '暖暖', '487', 'n', 'nn', 'nuannuan', '', '区', '1001703', '02', '3');
INSERT INTO `district` VALUES ('3303', '仁爱', '487', 'r', 'ra', 'renai', '', '区', '1001704', '02', '4');
INSERT INTO `district` VALUES ('3304', '中山', '487', 'z', 'zs', 'zhongshan', '', '区', '1001705', '02', '5');
INSERT INTO `district` VALUES ('3305', '安乐', '487', 'a', 'al', 'anle', '', '区', '1001706', '02', '6');
INSERT INTO `district` VALUES ('3306', '信义', '487', 'x', 'xy', 'xinyi', '', '区', '1001707', '02', '7');
INSERT INTO `district` VALUES ('3307', '大安', '488', 'd', 'da', 'daan', '', '区', '', '04', '1');
INSERT INTO `district` VALUES ('3308', '神冈', '488', 's', 'sg', 'shengang', '', '区', '', '04', '2');
INSERT INTO `district` VALUES ('3309', '石冈', '488', 's', 'sg', 'shigang', '', '区', '', '04', '3');
INSERT INTO `district` VALUES ('3310', '东势', '488', 'd', 'ds', 'dongshi', '', '区', '', '04', '4');
INSERT INTO `district` VALUES ('3311', '新社', '488', 'x', 'xs', 'xinshe', '', '区', '', '04', '5');
INSERT INTO `district` VALUES ('3312', '和平', '488', 'h', 'hp', 'heping', '', '区', '', '04', '6');
INSERT INTO `district` VALUES ('3313', '大肚', '488', 'd', 'dd', 'dadu', '', '区', '', '04', '7');
INSERT INTO `district` VALUES ('3314', '沙鹿', '488', 's', 'sl', 'shalu', '', '区', '', '04', '8');
INSERT INTO `district` VALUES ('3315', '龙井', '488', 'l', 'lj', 'longjing', '', '区', '', '04', '9');
INSERT INTO `district` VALUES ('3316', '梧栖', '488', 'w', 'wq', 'wuqi', '', '区', '', '04', '10');
INSERT INTO `district` VALUES ('3317', '清水', '488', 'q', 'qs', 'qingshui', '', '区', '', '04', '11');
INSERT INTO `district` VALUES ('3318', '大甲', '488', 'd', 'dj', 'dajia', '', '区', '', '04', '12');
INSERT INTO `district` VALUES ('3319', '外埔', '488', 'w', 'wp', 'waipu', '', '区', '', '04', '13');
INSERT INTO `district` VALUES ('3320', '大雅', '488', 'd', 'dy', 'daya', '', '区', '', '04', '14');
INSERT INTO `district` VALUES ('3321', '潭子', '488', 't', 'tz', 'tanzi', '', '区', '', '04', '15');
INSERT INTO `district` VALUES ('3322', '后里', '488', 'h', 'hl', 'houli', '', '区', '', '04', '16');
INSERT INTO `district` VALUES ('3323', '丰原', '488', 'f', 'fy', 'fengyuan', '', '区', '', '04', '17');
INSERT INTO `district` VALUES ('3324', '乌日', '488', 'w', 'wr', 'wuri', '', '区', '', '04', '18');
INSERT INTO `district` VALUES ('3325', '雾峰', '488', 'w', 'wf', 'wufeng', '', '区', '', '04', '19');
INSERT INTO `district` VALUES ('3326', '大里', '488', 'd', 'dl', 'dali', '', '区', '', '04', '20');
INSERT INTO `district` VALUES ('3327', '太平', '488', 't', 'tp', 'taiping', '', '区', '', '04', '21');
INSERT INTO `district` VALUES ('3328', '中区', '488', 'z', 'zq', 'zhongqu', '', '', '1001901', '04', '22');
INSERT INTO `district` VALUES ('3329', '东区', '488', 'd', 'dq', 'dongqu', '', '', '1001902', '04', '23');
INSERT INTO `district` VALUES ('3330', '南区', '488', 'n', 'nq', 'nanqu', '', '', '1001903', '04', '24');
INSERT INTO `district` VALUES ('3331', '西区', '488', 'x', 'xq', 'xiqu', '', '', '1001904', '04', '25');
INSERT INTO `district` VALUES ('3332', '北区', '488', 'b', 'bq', 'beiqu', '', '', '1001905', '04', '26');
INSERT INTO `district` VALUES ('3333', '西屯', '488', 'x', 'xt', 'xitun', '', '区', '1001906', '04', '27');
INSERT INTO `district` VALUES ('3334', '南屯', '488', 'n', 'nt', 'nantun', '', '区', '1001907', '04', '28');
INSERT INTO `district` VALUES ('3335', '北屯', '488', 'b', 'bt', 'beitun', '', '区', '1001908', '04', '29');
INSERT INTO `district` VALUES ('3336', '东区', '489', 'd', 'dq', 'dongqu', '', '', '1002101', '06', '1');
INSERT INTO `district` VALUES ('3337', '南区', '489', 'n', 'nq', 'nanqu', '', '', '1002102', '06', '2');
INSERT INTO `district` VALUES ('3338', '北区', '489', 'b', 'bq', 'beiqu', '', '', '1002104', '06', '3');
INSERT INTO `district` VALUES ('3339', '安南', '489', 'a', 'an', 'annan', '', '区', '1002106', '06', '4');
INSERT INTO `district` VALUES ('3340', '安平', '489', 'a', 'ap', 'anping', '', '区', '1002107', '06', '5');
INSERT INTO `district` VALUES ('3341', '中西', '489', 'z', 'zx', 'zhongxi', '', '区', '1002108', '06', '6');
INSERT INTO `district` VALUES ('3342', '东区', '490', 'd', 'dq', 'dongqu', '', '', '1001801', '', '1');
INSERT INTO `district` VALUES ('3343', '北区', '490', 'b', 'bq', 'beiqu', '', '', '1001802', '', '2');
INSERT INTO `district` VALUES ('3344', '香山', '490', 'x', 'xs', 'xiangshan', '', '区', '1001803', '', '3');
INSERT INTO `district` VALUES ('3345', '东区', '491', 'd', 'dq', 'dongqu', '', '', '1002001', '05', '1');
INSERT INTO `district` VALUES ('3346', '西区', '491', 'x', 'xq', 'xiqu', '', '', '1002002', '05', '2');
INSERT INTO `district` VALUES ('3347', '板桥', '492', 'b', 'bq', 'banqiao', '', '区', '', '', '1');
INSERT INTO `district` VALUES ('3348', '瑞芳', '492', 'r', 'rf', 'ruifang', '', '区', '', '', '2');
INSERT INTO `district` VALUES ('3349', '八里', '492', 'b', 'bl', 'bali', '', '区', '', '', '3');
INSERT INTO `district` VALUES ('3350', '深坑', '492', 's', 'sk', 'shenkeng', '', '区', '', '', '4');
INSERT INTO `district` VALUES ('3351', '三芝', '492', 's', 'sz', 'sanzhi', '', '区', '', '', '5');
INSERT INTO `district` VALUES ('3352', '金山', '492', 'j', 'js', 'jinshan', '', '区', '', '', '6');
INSERT INTO `district` VALUES ('3353', '万里', '492', 'w', 'wl', 'wanli', '', '区', '', '', '7');
INSERT INTO `district` VALUES ('3354', '贡寮', '492', 'g', 'gl', 'gongliao', '', '区', '', '', '8');
INSERT INTO `district` VALUES ('3355', '石门', '492', 's', 'sm', 'shimen', '', '区', '', '', '9');
INSERT INTO `district` VALUES ('3356', '双溪', '492', 's', 'sx', 'shuangxi', '', '区', '', '', '10');
INSERT INTO `district` VALUES ('3357', '石碇', '492', 's', 'sd', 'shiding', '', '区', '', '', '11');
INSERT INTO `district` VALUES ('3358', '坪林', '492', 'p', 'pl', 'pinglin', '', '区', '', '', '12');
INSERT INTO `district` VALUES ('3359', '乌来', '492', 'w', 'wl', 'wulai', '', '区', '', '', '13');
INSERT INTO `district` VALUES ('3360', '泰山', '492', 't', 'ts', 'taishan', '', '区', '', '', '14');
INSERT INTO `district` VALUES ('3361', '五股', '492', 'w', 'wg', 'wugu', '', '区', '', '', '15');
INSERT INTO `district` VALUES ('3362', '莺歌', '492', 'y', 'yg', 'yingge', '', '区', '', '', '16');
INSERT INTO `district` VALUES ('3363', '中和', '492', 'z', 'zh', 'zhonghe', '', '区', '', '', '17');
INSERT INTO `district` VALUES ('3364', '新庄', '492', 'x', 'xz', 'xinzhuang', '', '区', '', '', '18');
INSERT INTO `district` VALUES ('3365', '三重', '492', 's', 'sz', 'sanzhong', '', '区', '', '', '19');
INSERT INTO `district` VALUES ('3366', '新店', '492', 'x', 'xd', 'xindian', '', '区', '', '', '20');
INSERT INTO `district` VALUES ('3367', '土城', '492', 't', 'tc', 'tucheng', '', '区', '', '', '21');
INSERT INTO `district` VALUES ('3368', '永和', '492', 'y', 'yh', 'yonghe', '', '区', '', '', '22');
INSERT INTO `district` VALUES ('3369', '芦洲', '492', 'l', 'lz', 'luzhou', '', '区', '', '', '23');
INSERT INTO `district` VALUES ('3370', '汐止', '492', 'x', 'xz', 'xizhi', '', '区', '', '', '24');
INSERT INTO `district` VALUES ('3371', '树林', '492', 's', 'sl', 'shulin', '', '区', '', '', '25');
INSERT INTO `district` VALUES ('3372', '淡水', '492', 'd', 'ds', 'danshui', '', '区', '', '', '26');
INSERT INTO `district` VALUES ('3373', '三峡', '492', 's', 'sx', 'sanxia', '', '区', '', '', '27');
INSERT INTO `district` VALUES ('3374', '林口', '492', 'l', 'lk', 'linkou', '', '区', '', '', '28');
INSERT INTO `district` VALUES ('3375', '平溪', '492', 'p', 'px', 'pingxi', '', '区', '', '', '29');
INSERT INTO `district` VALUES ('3376', '宜兰', '493', 'y', 'yl', 'yilan', '', '市', '1000201', '', '1');
INSERT INTO `district` VALUES ('3377', '罗东', '493', 'l', 'ld', 'luodong', '', '镇', '1000202', '', '2');
INSERT INTO `district` VALUES ('3378', '苏澳', '493', 's', 'sa', 'suao', '', '镇', '1000203', '', '3');
INSERT INTO `district` VALUES ('3379', '头城', '493', 't', 'tc', 'toucheng', '', '乡', '1000204', '', '4');
INSERT INTO `district` VALUES ('3380', '礁溪', '493', 'j', 'jx', 'jiaoxi', '', '乡', '1000205', '', '5');
INSERT INTO `district` VALUES ('3381', '壮围', '493', 'z', 'zw', 'zhuangwei', '', '乡', '1000206', '', '6');
INSERT INTO `district` VALUES ('3382', '员山', '493', 'y', 'ys', 'yuanshan', '', '乡', '1000207', '', '7');
INSERT INTO `district` VALUES ('3383', '冬山', '493', 'd', 'ds', 'dongshan', '', '乡', '1000208', '', '8');
INSERT INTO `district` VALUES ('3384', '五结', '493', 'w', 'wj', 'wujie', '', '乡', '1000209', '', '9');
INSERT INTO `district` VALUES ('3385', '三星', '493', 's', 'sx', 'sanxing', '', '乡', '1000210', '', '10');
INSERT INTO `district` VALUES ('3386', '大同', '493', 'd', 'dt', 'datong', '', '乡', '1000211', '', '11');
INSERT INTO `district` VALUES ('3387', '南澳', '493', 'n', 'na', 'nanao', '', '乡', '1000212', '', '12');
INSERT INTO `district` VALUES ('3388', '桃园', '494', 't', 'ty', 'taoyuan', '', '市', '1000301', '', '1');
INSERT INTO `district` VALUES ('3389', '中坜', '494', 'z', 'zl', 'zhongli', '', '市', '1000302', '', '2');
INSERT INTO `district` VALUES ('3390', '大溪', '494', 'd', 'dx', 'daxi', '', '镇', '1000303', '', '3');
INSERT INTO `district` VALUES ('3391', '杨梅', '494', 'y', 'ym', 'yangmei', '', '镇', '1000304', '', '4');
INSERT INTO `district` VALUES ('3392', '芦竹', '494', 'l', 'lz', 'luzhu', '', '乡', '1000305', '', '5');
INSERT INTO `district` VALUES ('3393', '大园', '494', 'd', 'dy', 'dayuan', '', '乡', '1000306', '', '6');
INSERT INTO `district` VALUES ('3394', '龟山', '494', 'g', 'gs', 'guishan', '', '乡', '1000307', '', '7');
INSERT INTO `district` VALUES ('3395', '八德', '494', 'b', 'bd', 'bade', '', '市', '1000308', '', '8');
INSERT INTO `district` VALUES ('3396', '龙潭', '494', 'l', 'lt', 'longtan', '', '乡', '1000309', '', '9');
INSERT INTO `district` VALUES ('3397', '平镇', '494', 'p', 'pz', 'pingzhen', '', '市', '1000310', '', '10');
INSERT INTO `district` VALUES ('3398', '新屋', '494', 'x', 'xw', 'xinwu', '', '乡', '1000311', '', '11');
INSERT INTO `district` VALUES ('3399', '观音', '494', 'g', 'gy', 'guanyin', '', '乡', '1000312', '', '12');
INSERT INTO `district` VALUES ('3400', '复兴', '494', 'f', 'fx', 'fuxing', '', '乡', '1000313', '', '13');
INSERT INTO `district` VALUES ('3401', '竹北', '495', 'z', 'zb', 'zhubei', '', '市', '1000401', '', '1');
INSERT INTO `district` VALUES ('3402', '竹东', '495', 'z', 'zd', 'zhudong', '', '镇', '1000402', '', '2');
INSERT INTO `district` VALUES ('3403', '新埔', '495', 'x', 'xp', 'xinpu', '', '镇', '1000403', '', '3');
INSERT INTO `district` VALUES ('3404', '关西', '495', 'g', 'gx', 'guanxi', '', '镇', '1000404', '', '4');
INSERT INTO `district` VALUES ('3405', '湖口', '495', 'h', 'hk', 'hukou', '', '乡', '1000405', '', '5');
INSERT INTO `district` VALUES ('3406', '新丰', '495', 'x', 'xf', 'xinfeng', '', '乡', '1000406', '', '6');
INSERT INTO `district` VALUES ('3407', '芎林', '495', 'x', 'xl', 'xionglin', '', '乡', '1000407', '', '7');
INSERT INTO `district` VALUES ('3408', '横山', '495', 'h', 'hs', 'hengshan', '', '乡', '1000408', '', '8');
INSERT INTO `district` VALUES ('3409', '北埔', '495', 'b', 'bp', 'beipu', '', '乡', '1000409', '', '9');
INSERT INTO `district` VALUES ('3410', '宝山', '495', 'b', 'bs', 'baoshan', '', '乡', '1000410', '', '10');
INSERT INTO `district` VALUES ('3411', '峨眉', '495', 'e', 'em', 'emei', '', '乡', '1000411', '', '11');
INSERT INTO `district` VALUES ('3412', '尖石', '495', 'j', 'js', 'jianshi', '', '乡', '1000412', '', '12');
INSERT INTO `district` VALUES ('3413', '五峰', '495', 'w', 'wf', 'wufeng', '', '乡', '1000413', '', '13');
INSERT INTO `district` VALUES ('3414', '苗栗', '496', 'm', 'ml', 'miaoli', '', '市', '1000501', '', '1');
INSERT INTO `district` VALUES ('3415', '苑里', '496', 'y', 'yl', 'yuanli', '', '镇', '1000502', '', '2');
INSERT INTO `district` VALUES ('3416', '通霄', '496', 't', 'tx', 'tongxiao', '', '镇', '1000503', '', '3');
INSERT INTO `district` VALUES ('3417', '竹南', '496', 'z', 'zn', 'zhunan', '', '镇', '1000504', '', '4');
INSERT INTO `district` VALUES ('3418', '头份', '496', 't', 'tf', 'toufen', '', '镇', '1000505', '', '5');
INSERT INTO `district` VALUES ('3419', '后龙', '496', 'h', 'hl', 'houlong', '', '镇', '1000506', '', '6');
INSERT INTO `district` VALUES ('3420', '卓兰', '496', 'z', 'zl', 'zhuolan', '', '镇', '1000507', '', '7');
INSERT INTO `district` VALUES ('3421', '大湖', '496', 'd', 'dh', 'dahu', '', '乡', '1000508', '', '8');
INSERT INTO `district` VALUES ('3422', '公馆', '496', 'g', 'gg', 'gongguan', '', '乡', '1000509', '', '9');
INSERT INTO `district` VALUES ('3423', '铜锣', '496', 't', 'tl', 'tongluo', '', '乡', '1000510', '', '10');
INSERT INTO `district` VALUES ('3424', '南庄', '496', 'n', 'nz', 'nanzhuang', '', '乡', '1000511', '', '11');
INSERT INTO `district` VALUES ('3425', '头屋', '496', 't', 'tw', 'touwu', '', '乡', '1000512', '', '12');
INSERT INTO `district` VALUES ('3426', '三义', '496', 's', 'sy', 'sanyi', '', '乡', '1000513', '', '13');
INSERT INTO `district` VALUES ('3427', '西湖', '496', 'x', 'xh', 'xihu', '', '乡', '1000514', '', '14');
INSERT INTO `district` VALUES ('3428', '造桥', '496', 'z', 'zq', 'zaoqiao', '', '乡', '1000515', '', '15');
INSERT INTO `district` VALUES ('3429', '三湾', '496', 's', 'sw', 'sanwan', '', '乡', '1000516', '', '16');
INSERT INTO `district` VALUES ('3430', '狮潭', '496', 's', 'st', 'shitan', '', '乡', '1000517', '', '17');
INSERT INTO `district` VALUES ('3431', '泰安', '496', 't', 'ta', 'taian', '', '乡', '1000518', '', '18');
INSERT INTO `district` VALUES ('3432', '彰化', '497', 'z', 'zh', 'zhanghua', '', '市', '1000701', '', '1');
INSERT INTO `district` VALUES ('3433', '鹿港', '497', 'l', 'lg', 'lugang', '', '镇', '1000702', '', '2');
INSERT INTO `district` VALUES ('3434', '和美', '497', 'h', 'hm', 'hemei', '', '镇', '1000703', '', '3');
INSERT INTO `district` VALUES ('3435', '线西', '497', 'x', 'xx', 'xianxi', '', '乡', '1000704', '', '4');
INSERT INTO `district` VALUES ('3436', '伸港', '497', 's', 'sg', 'shengang', '', '乡', '1000705', '', '5');
INSERT INTO `district` VALUES ('3437', '福兴', '497', 'f', 'fx', 'fuxing', '', '乡', '1000706', '', '6');
INSERT INTO `district` VALUES ('3438', '秀水', '497', 'x', 'xs', 'xiushui', '', '乡', '1000707', '', '7');
INSERT INTO `district` VALUES ('3439', '花坛', '497', 'h', 'ht', 'huatan', '', '乡', '1000708', '', '8');
INSERT INTO `district` VALUES ('3440', '芬园', '497', 'f', 'fy', 'fenyuan', '', '乡', '1000709', '', '9');
INSERT INTO `district` VALUES ('3441', '员林', '497', 'y', 'yl', 'yuanlin', '', '镇', '1000710', '', '10');
INSERT INTO `district` VALUES ('3442', '溪湖', '497', 'x', 'xh', 'xihu', '', '镇', '1000711', '', '11');
INSERT INTO `district` VALUES ('3443', '田中', '497', 't', 'tz', 'tianzhong', '', '镇', '1000712', '', '12');
INSERT INTO `district` VALUES ('3444', '大村', '497', 'd', 'dc', 'dacun', '', '乡', '1000713', '', '13');
INSERT INTO `district` VALUES ('3445', '埔盐', '497', 'p', 'py', 'puyan', '', '乡', '1000714', '', '14');
INSERT INTO `district` VALUES ('3446', '埔心', '497', 'p', 'px', 'puxin', '', '乡', '1000715', '', '15');
INSERT INTO `district` VALUES ('3447', '永靖', '497', 'y', 'yj', 'yongjing', '', '乡', '1000716', '', '16');
INSERT INTO `district` VALUES ('3448', '社头', '497', 's', 'st', 'shetou', '', '乡', '1000717', '', '17');
INSERT INTO `district` VALUES ('3449', '二水', '497', 'e', 'es', 'ershui', '', '乡', '1000718', '', '18');
INSERT INTO `district` VALUES ('3450', '北斗', '497', 'b', 'bd', 'beidou', '', '镇', '1000719', '', '19');
INSERT INTO `district` VALUES ('3451', '二林', '497', 'e', 'el', 'erlin', '', '镇', '1000720', '', '20');
INSERT INTO `district` VALUES ('3452', '田尾', '497', 't', 'tw', 'tianwei', '', '乡', '1000721', '', '21');
INSERT INTO `district` VALUES ('3453', '埤头', '497', 'p', 'pt', 'pitou', '', '乡', '1000722', '', '22');
INSERT INTO `district` VALUES ('3454', '芳苑', '497', 'f', 'fy', 'fangyuan', '', '乡', '1000723', '', '23');
INSERT INTO `district` VALUES ('3455', '大城', '497', 'd', 'dc', 'dacheng', '', '乡', '1000724', '', '24');
INSERT INTO `district` VALUES ('3456', '竹塘', '497', 'z', 'zt', 'zhutang', '', '乡', '1000725', '', '25');
INSERT INTO `district` VALUES ('3457', '溪州', '497', 'x', 'xz', 'xizhou', '', '乡', '1000726', '', '26');
INSERT INTO `district` VALUES ('3458', '南投', '498', 'n', 'nt', 'nantou', '', '市', '1000801', '', '1');
INSERT INTO `district` VALUES ('3459', '南投', '498', 'n', 'nt', 'nantou', '', '镇', '1000802', '', '2');
INSERT INTO `district` VALUES ('3460', '草屯', '498', 'c', 'ct', 'caotun', '', '镇', '1000803', '', '3');
INSERT INTO `district` VALUES ('3461', '竹山', '498', 'z', 'zs', 'zhushan', '', '镇', '1000804', '', '4');
INSERT INTO `district` VALUES ('3462', '集集', '498', 'j', 'jj', 'jiji', '', '镇', '1000805', '', '5');
INSERT INTO `district` VALUES ('3463', '名间', '498', 'm', 'mj', 'mingjian', '', '乡', '1000806', '', '6');
INSERT INTO `district` VALUES ('3464', '鹿谷', '498', 'l', 'lg', 'lugu', '', '乡', '1000807', '', '7');
INSERT INTO `district` VALUES ('3465', '中寮', '498', 'z', 'zl', 'zhongliao', '', '乡', '1000808', '', '8');
INSERT INTO `district` VALUES ('3466', '鱼池', '498', 'y', 'yc', 'yuchi', '', '乡', '1000809', '', '9');
INSERT INTO `district` VALUES ('3467', '国姓', '498', 'g', 'gx', 'guoxing', '', '乡', '1000810', '', '10');
INSERT INTO `district` VALUES ('3468', '水里', '498', 's', 'sl', 'shuili', '', '乡', '1000811', '', '11');
INSERT INTO `district` VALUES ('3469', '信义', '498', 'x', 'xy', 'xinyi', '', '乡', '1000812', '', '12');
INSERT INTO `district` VALUES ('3470', '仁爱', '498', 'r', 'ra', 'renai', '', '乡', '1000813', '', '13');
INSERT INTO `district` VALUES ('3471', '斗六', '499', 'd', 'dl', 'douliu', '', '市', '1000901', '', '1');
INSERT INTO `district` VALUES ('3472', '斗南', '499', 'd', 'dn', 'dounan', '', '镇', '1000902', '', '2');
INSERT INTO `district` VALUES ('3473', '虎尾', '499', 'h', 'hw', 'huwei', '', '镇', '1000903', '', '3');
INSERT INTO `district` VALUES ('3474', '西螺', '499', 'x', 'xl', 'xiluo', '', '镇', '1000904', '', '4');
INSERT INTO `district` VALUES ('3475', '土库', '499', 't', 'tk', 'tuku', '', '镇', '1000905', '', '5');
INSERT INTO `district` VALUES ('3476', '北港', '499', 'b', 'bg', 'beigang', '', '镇', '1000906', '', '6');
INSERT INTO `district` VALUES ('3477', '古坑', '499', 'g', 'gk', 'gukeng', '', '乡', '1000907', '', '7');
INSERT INTO `district` VALUES ('3478', '大埤', '499', 'd', 'dp', 'dapi', '', '乡', '1000908', '', '8');
INSERT INTO `district` VALUES ('3479', '莿桐', '499', 'c', 'ct', 'citong', '', '乡', '1000909', '', '9');
INSERT INTO `district` VALUES ('3480', '林内', '499', 'l', 'ln', 'linnei', '', '乡', '1000910', '', '10');
INSERT INTO `district` VALUES ('3481', '二仑', '499', 'e', 'el', 'erlun', '', '乡', '1000911', '', '11');
INSERT INTO `district` VALUES ('3482', '仑背', '499', 'l', 'lb', 'lunbei', '', '乡', '1000912', '', '12');
INSERT INTO `district` VALUES ('3483', '麦寮', '499', 'm', 'ml', 'mailiao', '', '乡', '1000913', '', '13');
INSERT INTO `district` VALUES ('3484', '东势', '499', 'd', 'ds', 'dongshi', '', '乡', '1000914', '', '14');
INSERT INTO `district` VALUES ('3485', '褒忠', '499', 'b', 'bz', 'baozhong', '', '乡', '1000915', '', '15');
INSERT INTO `district` VALUES ('3486', '台西', '499', 't', 'tx', 'taixi', '', '乡', '1000916', '', '16');
INSERT INTO `district` VALUES ('3487', '元长', '499', 'y', 'yz', 'yuanzhang', '', '乡', '1000917', '', '17');
INSERT INTO `district` VALUES ('3488', '四湖', '499', 's', 'sh', 'sihu', '', '乡', '1000918', '', '18');
INSERT INTO `district` VALUES ('3489', '口湖', '499', 'k', 'kh', 'kouhu', '', '乡', '1000919', '', '19');
INSERT INTO `district` VALUES ('3490', '水林', '499', 's', 'sl', 'shuilin', '', '乡', '1000920', '', '20');
INSERT INTO `district` VALUES ('3491', '太保', '500', 't', 'tb', 'taibao', '', '市', '', '', '1');
INSERT INTO `district` VALUES ('3492', '朴子', '500', 'p', 'pz', 'pozi', '', '市', '', '', '2');
INSERT INTO `district` VALUES ('3493', '布袋', '500', 'b', 'bd', 'budai', '', '镇', '', '', '3');
INSERT INTO `district` VALUES ('3494', '大林', '500', 'd', 'dl', 'dalin', '', '镇', '10010', '', '4');
INSERT INTO `district` VALUES ('3495', '民雄', '500', 'm', 'mx', 'minxiong', '', '乡', '1001001', '', '5');
INSERT INTO `district` VALUES ('3496', '溪口', '500', 'x', 'xk', 'xikou', '', '乡', '1001002', '', '6');
INSERT INTO `district` VALUES ('3497', '新港', '500', 'x', 'xg', 'xingang', '', '乡', '1001003', '', '7');
INSERT INTO `district` VALUES ('3498', '六脚', '500', 'l', 'lj', 'liujiao', '', '乡', '1001004', '', '8');
INSERT INTO `district` VALUES ('3499', '东石', '500', 'd', 'ds', 'dongshi', '', '乡', '1001005', '', '9');
INSERT INTO `district` VALUES ('3500', '义竹', '500', 'y', 'yz', 'yizhu', '', '乡', '1001006', '', '10');
INSERT INTO `district` VALUES ('3501', '鹿草', '500', 'l', 'lc', 'lucao', '', '乡', '1001007', '', '11');
INSERT INTO `district` VALUES ('3502', '水上', '500', 's', 'ss', 'shuishang', '', '乡', '1001008', '', '12');
INSERT INTO `district` VALUES ('3503', '中埔', '500', 'z', 'zp', 'zhongpu', '', '乡', '1001009', '', '13');
INSERT INTO `district` VALUES ('3504', '竹崎', '500', 'z', 'zq', 'zhuqi', '', '乡', '1001010', '', '14');
INSERT INTO `district` VALUES ('3505', '梅山', '500', 'm', 'ms', 'meishan', '', '乡', '1001011', '', '15');
INSERT INTO `district` VALUES ('3506', '番路', '500', 'f', 'fl', 'fanlu', '', '乡', '1001012', '', '16');
INSERT INTO `district` VALUES ('3507', '大埔', '500', 'd', 'dp', 'dapu', '', '乡', '1001013', '', '17');
INSERT INTO `district` VALUES ('3508', '阿里山', '500', 'a', 'als', 'alishan', '', '乡', '1001014', '', '18');
INSERT INTO `district` VALUES ('3509', '屏东', '501', 'p', 'pd', 'pingdong', '', '市', '1001301', '', '1');
INSERT INTO `district` VALUES ('3510', '潮州', '501', 'c', 'cz', 'chaozhou', '', '镇', '1001302', '', '2');
INSERT INTO `district` VALUES ('3511', '东港', '501', 'd', 'dg', 'donggang', '', '镇', '1001303', '', '3');
INSERT INTO `district` VALUES ('3512', '恒春', '501', 'h', 'hc', 'hengchun', '', '镇', '1001304', '', '4');
INSERT INTO `district` VALUES ('3513', '万丹', '501', 'w', 'wd', 'wandan', '', '乡', '1001305', '', '5');
INSERT INTO `district` VALUES ('3514', '长治', '501', 'z', 'zz', 'zhangzhi', '', '乡', '1001306', '', '6');
INSERT INTO `district` VALUES ('3515', '麟洛', '501', 'l', 'll', 'linluo', '', '乡', '1001307', '', '7');
INSERT INTO `district` VALUES ('3516', '九如', '501', 'j', 'jr', 'jiuru', '', '乡', '1001308', '', '8');
INSERT INTO `district` VALUES ('3517', '里港', '501', 'l', 'lg', 'ligang', '', '乡', '1001309', '', '9');
INSERT INTO `district` VALUES ('3518', '盐埔', '501', 'y', 'yp', 'yanpu', '', '乡', '1001310', '', '10');
INSERT INTO `district` VALUES ('3519', '高树', '501', 'g', 'gs', 'gaoshu', '', '乡', '1001311', '', '11');
INSERT INTO `district` VALUES ('3520', '万峦', '501', 'w', 'wl', 'wanluan', '', '乡', '1001312', '', '12');
INSERT INTO `district` VALUES ('3521', '内埔', '501', 'n', 'np', 'neipu', '', '乡', '1001313', '', '13');
INSERT INTO `district` VALUES ('3522', '竹田', '501', 'z', 'zt', 'zhutian', '', '乡', '1001314', '', '14');
INSERT INTO `district` VALUES ('3523', '新埤', '501', 'x', 'xp', 'xinpi', '', '乡', '1001315', '', '15');
INSERT INTO `district` VALUES ('3524', '枋寮', '501', 'f', 'fl', 'fangliao', '', '乡', '1001316', '', '16');
INSERT INTO `district` VALUES ('3525', '新园', '501', 'x', 'xy', 'xinyuan', '', '乡', '1001317', '', '17');
INSERT INTO `district` VALUES ('3526', '崁顶', '501', 'k', 'kd', 'kanding', '', '乡', '1001318', '', '18');
INSERT INTO `district` VALUES ('3527', '林边', '501', 'l', 'lb', 'linbian', '', '乡', '1001319', '', '19');
INSERT INTO `district` VALUES ('3528', '南州', '501', 'n', 'nz', 'nanzhou', '', '乡', '1001320', '', '20');
INSERT INTO `district` VALUES ('3529', '佳冬', '501', 'j', 'jd', 'jiadong', '', '乡', '1001321', '', '21');
INSERT INTO `district` VALUES ('3530', '琉球', '501', 'l', 'lq', 'liuqiu', '', '乡', '1001322', '', '22');
INSERT INTO `district` VALUES ('3531', '车城', '501', 'c', 'cc', 'checheng', '', '乡', '1001323', '', '23');
INSERT INTO `district` VALUES ('3532', '满州', '501', 'm', 'mz', 'manzhou', '', '乡', '1001324', '', '24');
INSERT INTO `district` VALUES ('3533', '枋山', '501', 'f', 'fs', 'fangshan', '', '乡', '1001325', '', '25');
INSERT INTO `district` VALUES ('3534', '三地门', '501', 's', 'sdm', 'sandimen', '', '乡', '1001326', '', '26');
INSERT INTO `district` VALUES ('3535', '雾台', '501', 'w', 'wt', 'wutai', '', '乡', '1001327', '', '27');
INSERT INTO `district` VALUES ('3536', '玛家', '501', 'm', 'mj', 'majia', '', '乡', '1001328', '', '28');
INSERT INTO `district` VALUES ('3537', '泰武', '501', 't', 'tw', 'taiwu', '', '乡', '1001329', '', '29');
INSERT INTO `district` VALUES ('3538', '来义', '501', 'l', 'ly', 'laiyi', '', '乡', '1001330', '', '30');
INSERT INTO `district` VALUES ('3539', '春日', '501', 'c', 'cr', 'chunri', '', '乡', '1001331', '', '31');
INSERT INTO `district` VALUES ('3540', '狮子', '501', 's', 'sz', 'shizi', '', '乡', '1001332', '', '32');
INSERT INTO `district` VALUES ('3541', '牡丹', '501', 'm', 'md', 'mudan', '', '乡', '1001333', '', '33');
INSERT INTO `district` VALUES ('3542', '卑南', '502', 'b', 'bn', 'beinan', '', '乡', '', '', '1');
INSERT INTO `district` VALUES ('3543', '台东', '502', 't', 'td', 'taidong', '', '市', '1001401', '', '2');
INSERT INTO `district` VALUES ('3544', '成功', '502', 'c', 'cg', 'chenggong', '', '镇', '1001402', '', '3');
INSERT INTO `district` VALUES ('3545', '关山', '502', 'g', 'gs', 'guanshan', '', '镇', '1001403', '', '4');
INSERT INTO `district` VALUES ('3546', '鹿野', '502', 'l', 'ly', 'luye', '', '乡', '1001405', '', '5');
INSERT INTO `district` VALUES ('3547', '池上', '502', 'c', 'cs', 'chishang', '', '乡', '1001406', '', '6');
INSERT INTO `district` VALUES ('3548', '东河', '502', 'd', 'dh', 'donghe', '', '乡', '1001407', '', '7');
INSERT INTO `district` VALUES ('3549', '长滨', '502', 'z', 'zb', 'zhangbin', '', '乡', '1001408', '', '8');
INSERT INTO `district` VALUES ('3550', '太麻里', '502', 't', 'tml', 'taimali', '', '乡', '1001409', '', '9');
INSERT INTO `district` VALUES ('3551', '大武', '502', 'd', 'dw', 'dawu', '', '乡', '1001410', '', '10');
INSERT INTO `district` VALUES ('3552', '绿岛', '502', 'l', 'ld', 'lu:dao', '', '乡', '1001411', '', '11');
INSERT INTO `district` VALUES ('3553', '海端', '502', 'h', 'hd', 'haiduan', '', '乡', '1001412', '', '12');
INSERT INTO `district` VALUES ('3554', '延平', '502', 'y', 'yp', 'yanping', '', '乡', '1001413', '', '13');
INSERT INTO `district` VALUES ('3555', '金峰', '502', 'j', 'jf', 'jinfeng', '', '乡', '1001414', '', '14');
INSERT INTO `district` VALUES ('3556', '达仁', '502', 'd', 'dr', 'daren', '', '乡', '1001415', '', '15');
INSERT INTO `district` VALUES ('3557', '兰屿', '502', 'l', 'ly', 'lanyu', '', '乡', '1001416', '', '16');
INSERT INTO `district` VALUES ('3558', '花莲', '503', 'h', 'hl', 'hualian', '', '市', '1001501', '', '1');
INSERT INTO `district` VALUES ('3559', '凤林', '503', 'f', 'fl', 'fenglin', '', '镇', '1001502', '', '2');
INSERT INTO `district` VALUES ('3560', '玉里', '503', 'y', 'yl', 'yuli', '', '镇', '1001503', '', '3');
INSERT INTO `district` VALUES ('3561', '新城', '503', 'x', 'xc', 'xincheng', '', '乡', '1001504', '', '4');
INSERT INTO `district` VALUES ('3562', '吉安', '503', 'j', 'ja', 'jian', '', '乡', '1001505', '', '5');
INSERT INTO `district` VALUES ('3563', '寿丰', '503', 's', 'sf', 'shoufeng', '', '乡', '1001506', '', '6');
INSERT INTO `district` VALUES ('3564', '光复', '503', 'g', 'gf', 'guangfu', '', '乡', '1001507', '', '7');
INSERT INTO `district` VALUES ('3565', '丰滨', '503', 'f', 'fb', 'fengbin', '', '乡', '1001508', '', '8');
INSERT INTO `district` VALUES ('3566', '瑞穗', '503', 'r', 'rs', 'ruisui', '', '乡', '1001509', '', '9');
INSERT INTO `district` VALUES ('3567', '富里', '503', 'f', 'fl', 'fuli', '', '乡', '1001510', '', '10');
INSERT INTO `district` VALUES ('3568', '秀林', '503', 'x', 'xl', 'xiulin', '', '乡', '1001511', '', '11');
INSERT INTO `district` VALUES ('3569', '万荣', '503', 'w', 'wr', 'wanrong', '', '乡', '1001512', '', '12');
INSERT INTO `district` VALUES ('3570', '卓溪', '503', 'z', 'zx', 'zhuoxi', '', '乡', '1001513', '', '13');
INSERT INTO `district` VALUES ('3571', '马公', '504', 'm', 'mg', 'magong', '', '市', '1001601', '07', '1');
INSERT INTO `district` VALUES ('3572', '湖西', '504', 'h', 'hx', 'huxi', '', '乡', '1001602', '07', '2');
INSERT INTO `district` VALUES ('3573', '白沙', '504', 'b', 'bs', 'baisha', '', '乡', '1001603', '07', '3');
INSERT INTO `district` VALUES ('3574', '西屿', '504', 'x', 'xy', 'xiyu', '', '乡', '1001604', '07', '4');
INSERT INTO `district` VALUES ('3575', '望安', '504', 'w', 'wa', 'wangan', '', '乡', '1001605', '07', '5');
INSERT INTO `district` VALUES ('3576', '七美', '504', 'q', 'qm', 'qimei', '', '乡', '1001606', '07', '6');
INSERT INTO `district` VALUES ('3577', '双河', '31', 's', 'sh', 'shuanghe', '', '市', '659007', '0909', '21');
INSERT INTO `district` VALUES ('3578', '海棠', '357', 'h', 'ht', 'haitang', '', '区', '', '0898', '1');
INSERT INTO `district` VALUES ('3579', '吉阳', '357', 'j', 'jy', 'jiyang', '', '区', '', '0898', '2');
INSERT INTO `district` VALUES ('3580', '天涯', '357', 't', 'ty', 'tianya', '', '区', '', '0898', '3');
INSERT INTO `district` VALUES ('3581', '崖州', '357', 'y', 'yz', 'yazhou', '', '区', '', '0898', '4');
INSERT INTO `district` VALUES ('3582', '霍尔果斯', '476', 'h', 'hegs', 'huoerguosi', '', '市', '654004', '0999', '3');
INSERT INTO `district` VALUES ('3583', '前锋', '388', 'q', 'qf', 'qianfeng', '', '区', '', '0826', '2');
INSERT INTO `district` VALUES ('3584', '福绵', '350', 'f', 'fm', 'fumian', '', '区', '450903', '0775', '2');
INSERT INTO `district` VALUES ('3585', '可克达拉', '31', 'k', 'kdkl', 'kedakela', '', '市', '659008', '0999', '22');

-- ----------------------------
-- Table structure for email
-- ----------------------------
DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `TO_ID` text,
  `READ_FLAG` char(1) DEFAULT NULL,
  `DELETE_FLAG` char(1) DEFAULT NULL,
  `BOX_ID` varchar(50) DEFAULT NULL,
  `BODY_ID` varchar(50) DEFAULT NULL,
  `RECEIPT` char(1) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=3355 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of email
-- ----------------------------

-- ----------------------------
-- Table structure for email_body
-- ----------------------------
DROP TABLE IF EXISTS `email_body`;
CREATE TABLE `email_body` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BODY_ID` varchar(50) DEFAULT NULL,
  `FROM_ID` varchar(50) DEFAULT NULL,
  `TO_ID` text,
  `COPY_TO_ID` varchar(50) DEFAULT NULL,
  `SUBJECT` varchar(200) DEFAULT NULL,
  `CONTENT` text,
  `SEND_TIME` datetime DEFAULT NULL,
  `ATTACHMENT_ID` text,
  `ATTACHMENT_NAME` text,
  `SEND_FLAG` varchar(10) DEFAULT NULL,
  `SMS_REMIND` varchar(100) DEFAULT NULL,
  `IMPORTANT` varchar(10) DEFAULT '0',
  `WEBMAIL_FLAG` varchar(20) DEFAULT NULL,
  `FROM_WEBMAIL` varchar(200) DEFAULT NULL,
  `FROM_WEBMAIL_ID` varchar(500) DEFAULT NULL,
  `WEBMAIL_CONTENT` mediumtext,
  `IS_WEBMAIL` char(1) DEFAULT NULL,
  `TO_WEBMAIL` text,
  `TO_WEBMAIL_COPY` text,
  `ORG_ID` varchar(50) DEFAULT NULL,
  UNIQUE KEY `SEQ_ID` (`ID`) USING BTREE,
  KEY `email_from_id` (`FROM_ID`) USING BTREE,
  KEY `email_send_time` (`SEND_TIME`) USING BTREE,
  KEY `email_is_webmail` (`IS_WEBMAIL`) USING BTREE,
  KEY `email_from_webmail` (`FROM_WEBMAIL`(191)) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of email_body
-- ----------------------------

-- ----------------------------
-- Table structure for email_box
-- ----------------------------
DROP TABLE IF EXISTS `email_box`;
CREATE TABLE `email_box` (
  `ID` int(11) DEFAULT NULL,
  `BOX_ID` varchar(150) DEFAULT NULL,
  `BOX_PID` varchar(150) DEFAULT NULL,
  `BOX_NAME` varchar(600) DEFAULT NULL,
  `ACCOUNT_ID` varchar(150) DEFAULT NULL,
  `SORT_ID` int(11) DEFAULT NULL,
  `ORG_ID` varchar(60) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of email_box
-- ----------------------------

-- ----------------------------
-- Table structure for email_config
-- ----------------------------
DROP TABLE IF EXISTS `email_config`;
CREATE TABLE `email_config` (
  `ID` int(11) DEFAULT NULL,
  `CONFIG_ID` varchar(150) DEFAULT NULL,
  `EMAIL_SERVER` varchar(150) DEFAULT NULL,
  `SERVER_PORT` varchar(150) DEFAULT NULL,
  `EMAIL_USER` varchar(150) DEFAULT NULL,
  `EMAIL_PWD` varchar(150) DEFAULT NULL,
  `ACCOUNT_ID` varchar(150) DEFAULT NULL,
  `ORG_ID` varchar(60) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of email_config
-- ----------------------------

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FEEDBACK_ID` varchar(50) DEFAULT NULL,
  `CONTENT` text,
  `ATTACH_ID` varchar(2000) DEFAULT NULL,
  `CLIENT_TYPE` varchar(50) DEFAULT NULL,
  `VERSION` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for file_record
-- ----------------------------
DROP TABLE IF EXISTS `file_record`;
CREATE TABLE `file_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RECORD_ID` varchar(150) DEFAULT NULL,
  `RECORD_NO` int(11) DEFAULT NULL,
  `FILE_ID` varchar(150) DEFAULT NULL,
  `ATTACH_ID` text,
  `RECORD_TIME` varchar(150) DEFAULT NULL,
  `ACCOUNT_ID` varchar(150) DEFAULT NULL,
  `ORG_ID` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file_record
-- ----------------------------

-- ----------------------------
-- Table structure for fixedasset_attr
-- ----------------------------
DROP TABLE IF EXISTS `fixedasset_attr`;
CREATE TABLE `fixedasset_attr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq_id` varchar(50) CHARACTER SET gbk NOT NULL,
  `asset_no` varchar(200) DEFAULT NULL,
  `asset_name` varchar(200) DEFAULT NULL,
  `standard` varchar(4000) DEFAULT NULL,
  `manufacture` varchar(4000) DEFAULT NULL,
  `ex_factory_date` date DEFAULT NULL,
  `service_life` int(3) DEFAULT NULL,
  `unit_price` double(13,2) DEFAULT NULL,
  `remark` text,
  `image` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`seq_id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fixedasset_attr
-- ----------------------------

-- ----------------------------
-- Table structure for fixedasset_attr_type
-- ----------------------------
DROP TABLE IF EXISTS `fixedasset_attr_type`;
CREATE TABLE `fixedasset_attr_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq_id` varchar(50) NOT NULL,
  `type` varchar(200) DEFAULT NULL,
  `table` varchar(200) DEFAULT NULL,
  `table_field` varchar(200) DEFAULT NULL,
  `field_sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`seq_id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fixedasset_attr_type
-- ----------------------------

-- ----------------------------
-- Table structure for fixedasset_record
-- ----------------------------
DROP TABLE IF EXISTS `fixedasset_record`;
CREATE TABLE `fixedasset_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq_id` varchar(50) NOT NULL,
  `asset_id` varchar(50) DEFAULT NULL,
  `flow_msg` varchar(4000) DEFAULT NULL,
  `registration_date` datetime DEFAULT NULL,
  `registration_user` varchar(50) DEFAULT NULL,
  `record_type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`seq_id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fixedasset_record
-- ----------------------------

-- ----------------------------
-- Table structure for fixedasset_relation
-- ----------------------------
DROP TABLE IF EXISTS `fixedasset_relation`;
CREATE TABLE `fixedasset_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq_id` varchar(50) NOT NULL,
  `asset_id` varchar(50) DEFAULT NULL,
  `asset_type` varchar(50) DEFAULT NULL,
  `asset_source` varchar(50) DEFAULT NULL,
  `use_dept` varchar(50) DEFAULT NULL,
  `use_situation` varchar(50) DEFAULT NULL,
  `storage_location` varchar(50) DEFAULT NULL,
  `use_user` varchar(50) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `posting_date` datetime DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `net_salvage` double(3,3) DEFAULT NULL,
  PRIMARY KEY (`seq_id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fixedasset_relation
-- ----------------------------

-- ----------------------------
-- Table structure for fixedasset_source
-- ----------------------------
DROP TABLE IF EXISTS `fixedasset_source`;
CREATE TABLE `fixedasset_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq_id` varchar(50) NOT NULL,
  `source` varchar(500) DEFAULT NULL,
  `sort` int(10) DEFAULT '0',
  PRIMARY KEY (`seq_id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fixedasset_source
-- ----------------------------

-- ----------------------------
-- Table structure for fixedasset_type
-- ----------------------------
DROP TABLE IF EXISTS `fixedasset_type`;
CREATE TABLE `fixedasset_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq_id` varchar(50) NOT NULL,
  `parent_id` varchar(50) DEFAULT NULL,
  `level_id` varchar(4000) DEFAULT NULL,
  `type_name` varchar(500) DEFAULT NULL,
  `unit` varchar(100) DEFAULT NULL,
  `net_salvage` double(3,3) DEFAULT NULL,
  PRIMARY KEY (`seq_id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=234 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fixedasset_type
-- ----------------------------

-- ----------------------------
-- Table structure for flow_form_item
-- ----------------------------
DROP TABLE IF EXISTS `flow_form_item`;
CREATE TABLE `flow_form_item` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FORM_ID` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  `FORM_ITEM_ID` varchar(20) CHARACTER SET gbk DEFAULT NULL,
  `TITLE` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  `XTYPE` varchar(20) CHARACTER SET gbk DEFAULT NULL,
  `FIELD_NAME` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  `DATA_TYPE` varchar(10) CHARACTER SET gbk DEFAULT NULL,
  `MAX_LENGTH` varchar(10) CHARACTER SET gbk DEFAULT NULL,
  `MODEL` varchar(2000) DEFAULT NULL,
  `CHILD_TABLE` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=5818 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_form_item
-- ----------------------------
INSERT INTO `flow_form_item` VALUES ('4852', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'uedit', '富文本', 'xtextuedit', 'uedit', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4853', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'selworkflow', '流程选择', 'xworkflow', 'selworkflow', 'text', null, '1', null, null);
INSERT INTO `flow_form_item` VALUES ('4854', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'listTestmingxi', 'mingxi', 'xlist', 'listTestmingxi', null, null, '[{\'title\':\'a\',\'fieldname\':\'A\',\'width\':\'20\',\'stylewidth\':\'\',\'sum\':\'0\',\'formula\':\'\',\'type\':\'1\',\'model\':\'\'},{\'title\':\'B\',\'fieldname\':\'B\',\'width\':\'20\',\'stylewidth\':\'\',\'sum\':\'0\',\'formula\':\'\',\'type\':\'2\',\'model\':\'\'},{\'title\':\'C\',\'fieldname\':\'C\',\'width\':\'20\',\'stylewidth\':\'\',\'sum\':\'0\',\'formula\':\'\',\'type\':\'6\',\'model\':\'\'},{\'title\':\'D\',\'fieldname\':\'D\',\'width\':\'20\',\'stylewidth\':\'\',\'sum\':\'0\',\'formula\':\'\',\'type\':\'3\',\'model\':\'a,b,c\'}]', null, null);
INSERT INTO `flow_form_item` VALUES ('4028', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'loveaaa', '列表控件aaaa', 'xlist', 'loveaaa', null, null, '[{\'title\':\'名称\',\'fieldname\':\'name\',\'width\':\'10\',\'stylewidth\':\'\',\'sum\':\'0\',\'formula\':\'\',\'type\':\'1\',\'model\':\'\'},{\'title\':\'单价\',\'fieldname\':\'danjia\',\'width\':\'10\',\'stylewidth\':\'\',\'sum\':\'0\',\'formula\':\'\',\'type\':\'1\',\'model\':\'\'},{\'title\':\'数量\',\'fieldname\':\'shuliang\',\'width\':\'10\',\'stylewidth\':\'\',\'sum\':\'0\',\'formula\':\'\',\'type\':\'1\',\'model\':\'\'},{\'title\':\'型号\',\'fieldname\':\'xinghao\',\'width\':\'10\',\'stylewidth\':\'\',\'sum\':\'0\',\'formula\':\'\',\'type\':\'1\',\'model\':\'\'}]', null, null);
INSERT INTO `flow_form_item` VALUES ('4027', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'pictureupload', '图片上传', 'ximg', 'pictureupload', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4022', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'duofujian', '多附件', 'xuploads', 'duofujian', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4023', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'xialakuang', '下拉框', 'xselect', 'xialakuang', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4024', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'duohangkuang', '多行框', 'xtextarea', 'duohangkuang', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4025', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'duohangkuan', '多行框2', 'xtextarea', 'duohangkuan', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4019', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'xuanzeqi', '选择器', 'xfetch', 'xuanzeqi', 'text', null, '{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('4026', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'fuwenben', '富文本', 'xtextuedit', 'fuwenben', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4021', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'danfujian', '单附件', 'xupload', 'danfujian', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4020', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'A', '单选框', 'xradio', 'A', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4018', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'danhangkuan', '单行框', 'xinput', 'danhangkuan', 'varchar', '30', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4017', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'bumen', '部门', 'xmacro', 'bumen', 'text', null, '{type:9,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4016', '555C5000-74D1-928B-88A4-E645A1DB7F11', 'name', '姓名', 'xmacro', 'name', 'text', null, '{type:8,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4851', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'sselect', '下拉列表', 'xselect', 'sselect', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4850', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'raido3', 'testraido11', 'xradio', 'raido3', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4849', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'attachup', '附件上传', 'xupload', 'attachup', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4848', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'B', '复选1', 'xcheckbox', 'B', 'text', '2', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4847', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'A', '复选', 'xcheckbox', 'A', 'text', '2', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4846', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'aradio', '单选', 'xradio', 'aradio', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4845', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'sinput', '单行输入', 'xinput', 'sinput', 'varchar', '20', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4844', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'dept', '部门', 'xmacro', 'dept', 'text', null, '{type:14,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4843', '76255EB4-66C6-AF72-980E-56F2CDFC2C26', 'name', '姓名', 'xmacro', 'name', 'text', null, '{type:8,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4884', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'xzzjyj', '行政总监意见', 'xtextarea', 'xzzjyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4883', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'bmjlyj', '部门经理意见', 'xtextarea', 'bmjlyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4897', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'zjlyj', '总经理意见', 'xtextarea', 'zjlyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4896', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'fzyj', '副总意见', 'xtextarea', 'fzyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4895', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'xzzjyj', '行政总监意见', 'xtextarea', 'xzzjyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4894', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'bmdjlyj', '部门的经理意见', 'xtextarea', 'bmdjlyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4893', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'resones', '用车原因', 'xtextarea', 'resones', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4892', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'overtime', '结束时间', 'xfetch', 'overtime', 'text', null, '{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('4891', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'firsttime', '开始时间', 'xfetch', 'firsttime', 'text', null, '{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('4890', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'place', '目的地', 'xinput', 'place', 'varchar', '40', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4889', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'peson', '随行人', 'xinput', 'peson', 'varchar', '40', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4888', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'name', '姓名', 'xmacro', 'name', 'text', null, '{type:8,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4887', 'F627FA1D-87F4-CF34-0A62-F2C5143C13B0', 'bm', '部门', 'xmacro', 'bm', 'text', null, '{type:9,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4909', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'xzzjyj', '行政总监意见', 'xtextarea', 'xzzjyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4908', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'bmjlyj', '部门经理意见', 'xtextarea', 'bmjlyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4907', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'resons', '请假事由', 'xtextarea', 'resons', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4906', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'bmjlqm', '部门经理签名', 'xmacro', 'bmjlqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4905', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'zts', '总天数', 'xinput', 'zts', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4904', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'secondtime', '请假结束时间', 'xfetch', 'secondtime', 'text', null, '{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('4903', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'firsttimes', '请假开始时间', 'xfetch', 'firsttimes', 'text', null, '{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('4885', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'zjlyj', '总经理意见', 'xtextarea', 'zjlyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4881', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'rzbqm', '人资部签名', 'xmacro', 'rzbqm', 'text', null, '{type:12,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4882', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'reason', '事由及任务', 'xtextarea', 'reason', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4879', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'xzzjyjqm', '行政总监意见签名', 'xmacro', 'xzzjyjqm', 'text', null, '{type:12,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4880', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'zjlqm', '总经理签名', 'xmacro', 'zjlqm', 'text', null, '{type:12,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4878', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'bmjlqm', '部门经理签名', 'xmacro', 'bmjlqm', 'text', null, '{type:12,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4877', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'backtime', '回来日期', 'xfetch', 'backtime', 'text', null, '{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('4942', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'lll', 'LLL', 'xtextarea', 'lll', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4941', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'kkk', 'KKK', 'xtextarea', 'kkk', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4940', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'qqqq', 'QQQq', 'xselect', 'qqqq', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4939', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'xc', 'xc', 'xcalculate', 'xc', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4938', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'www', 'WWW', 'xfetch', 'www', 'text', null, '{\'type\':8,\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('4937', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'ppp', 'PPP', 'xuploads', 'ppp', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4936', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'ooo', 'OOO', 'xupload', 'ooo', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4935', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'jjj', 'JJJ', 'xinput', 'jjj', 'varchar', '11', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4934', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'iii', 'III', 'xinput', 'iii', 'varchar', '11', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4876', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'firsttime', '出发日期', 'xfetch', 'firsttime', 'text', null, '{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('4875', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'sxry', '随行人员', 'xinput', 'sxry', 'varchar', '50', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4874', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'jtgj', '交通工具', 'xradio', 'jtgj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4873', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'zw', '职务', 'xmacro', 'zw', 'text', null, '{type:10,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4933', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'hhh', 'HHH', 'xinput', 'hhh', 'varchar', '11', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4932', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'ggg', 'GGG', 'xinput', 'ggg', 'varchar', '11', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4931', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'fff', 'FFF', 'xinput', 'fff', 'varchar', '11', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4930', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'eee', 'EEE', 'xinput', 'eee', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4929', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'ddd', 'DDD', 'xinput', 'ddd', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4928', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'ccc', 'CCC', 'xinput', 'ccc', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4927', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'bbb', 'BBB', 'xinput', 'bbb', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4923', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'cwzjyj', '财务总监意见', 'xtextarea', 'cwzjyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4922', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'xzzjyj', '行政总监意见', 'xtextarea', 'xzzjyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4921', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'bmldyj', '部门领导意见', 'xtextarea', 'bmldyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4920', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'smsx', '说明事项', 'xtextarea', 'smsx', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4919', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'capslock', '大写金额', 'xinput', 'capslock', 'varchar', '20', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4918', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'bxje', '报销金额', 'xinput', 'bxje', 'varchar', '20', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4917', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'fjzs', '附件张数', 'xinput', 'fjzs', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4916', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'djzs', '单据张数', 'xinput', 'djzs', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4926', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'aaa', 'AAA', 'xinput', 'aaa', 'varchar', '20', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4915', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'kzxm', '开支项目', 'xinput', 'kzxm', 'varchar', '50', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4914', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'bxsj', '报销时间', 'xmacro', 'bxsj', 'text', null, '{type:2,format:\'yyyy-MM-dd\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4913', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'name', '姓名', 'xmacro', 'name', 'text', null, '{type:12,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4912', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'bm', '部门', 'xmacro', 'bm', 'text', null, '{type:13,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4902', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'qjlb', '请假类别', 'xradio', 'qjlb', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4901', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'bumen', '部门', 'xmacro', 'bumen', 'text', null, '{type:13,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4900', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'wangwei', '岗位', 'xmacro', 'wangwei', 'text', null, '{type:10,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4886', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'rzbqr', '人资部确认', 'xtextarea', 'rzbqr', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4871', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'usename', '姓 名', 'xmacro', 'usename', 'text', null, '{type:8,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4872', '2D61CF77-F7A8-38AB-A3DB-5CA3E5312AC3', 'bm', '部门', 'xmacro', 'bm', 'text', null, '{type:9,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4899', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'name', '姓名', 'xmacro', 'name', 'text', null, '{type:8,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('4898', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'abc', '计数', 'xdocnum', 'abc', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4910', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'fzyj', '副总意见', 'xtextarea', 'fzyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4911', 'D3B89F27-C173-ABF1-8E4A-3483156011B2', 'dszyj', '董事长意见', 'xtextarea', 'dszyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4924', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'zjlyj', '总经理意见', 'xtextarea', 'zjlyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4925', '13A5BF4D-8298-FA74-B0F4-E9B8755A6238', 'dszyj', '董事长意见', 'xtextarea', 'dszyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4943', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'mmm', 'MMM', 'xtextuedit', 'mmm', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4944', 'A8A6B310-C125-3B0E-78F6-C233E8062021', 'nnn', 'NNN', 'xiframe', 'nnn', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5816', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'bmspyi', '部门审批意见', 'xtextarea', 'bmspyi', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5817', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'xzspyj', '行政审批意见', 'xtextarea', 'xzspyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5815', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'beizhu', '备注', 'xtextarea', 'beizhu', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5813', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'xzldqm', '行政领导签名', 'xmacro', 'xzldqm', 'text', null, '{\'type\':\'12\',\'format\':null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5814', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'sqresone', '申请原因', 'xtextarea', 'sqresone', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5812', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'bmldqm', '部门领导签名', 'xmacro', 'bmldqm', 'text', null, '{\'type\':\'12\',\'format\':null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5811', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', null, '是否实习生', 'xradio', 'sfsxs', 'text', null, '[null,null]', null, null);
INSERT INTO `flow_form_item` VALUES ('5809', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'sqtime', '申请时间', 'xfetch', 'sqtime', 'text', null, '{\'type\':\'1\',\'format\':\'yyyy-MM-dd\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('5810', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'syr', '使用人姓名', 'xinput', 'syr', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5808', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'shuliang', '数量', 'xinput', 'shuliang', 'varchar', '20', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5807', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'sqwp', '申请物品', 'xinput', 'sqwp', 'varchar', '100', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4983', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'qkreason', '请款事由', 'xtextarea', 'qkreason', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4982', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'cnqr', '出纳确认', 'xmacro', 'cnqr', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4981', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'cwqm', '财务签名', 'xmacro', 'cwqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4980', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'ceoqm', '董事长签名', 'xmacro', 'ceoqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4979', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'bmzjqm', '部门总监签名', 'xmacro', 'bmzjqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4978', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'xxje', '小写金额', 'xinput', 'xxje', 'varchar', '30', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4977', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'qljedx', '请款金额大写', 'xinput', 'qljedx', 'varchar', '40', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4976', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'qkxm', '请款项目', 'xinput', 'qkxm', 'varchar', '30', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4975', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'qktime', '请款时间', 'xfetch', 'qktime', 'text', null, '{\"type\":\"1\",\"format\":\"yyyy-MM-dd\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('4974', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'qkr', '请款人', 'xinput', 'qkr', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4973', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'bm', '部门', 'xmacro', 'bm', 'text', null, '{\"type\":\"13\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4984', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'bmzjsp', '部门总监审批', 'xtextarea', 'bmzjsp', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4985', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'ceosp', '董事长审批', 'xtextarea', 'ceosp', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4986', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', 'cwsh', '财务审核', 'xtextarea', 'cwsh', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('4987', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'bm', '部门', 'xmacro', 'bm', 'text', null, '{\"type\":\"13\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4988', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'bxren', '报销人', 'xinput', 'bxren', 'varchar', '16', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4989', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'hjjedx', '合计金额大写', 'xinput', 'hjjedx', 'varchar', '52', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4990', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'jexx', '金额小写', 'xinput', 'jexx', 'varchar', '20', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4991', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'djzs', '单据张数', 'xinput', 'djzs', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('4992', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'bmzjqz', '部门总监签字', 'xmacro', 'bmzjqz', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4993', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'tbren', '填报人', 'xmacro', 'tbren', 'text', null, '{\"type\":\"8\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4994', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'cwzgqz', '财务主管签字', 'xmacro', 'cwzgqz', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4995', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'kjqz', '会计签字', 'xmacro', 'kjqz', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4996', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'cnqz', '出纳签字', 'xmacro', 'cnqz', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4997', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'ceoqz', '董事长签字', 'xmacro', 'ceoqz', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('4998', '08B9C566-58E5-2035-7617-A62E0644BFCC', 'baoxiaoshiyou', '报销事由', 'xtextarea', 'baoxiaoshiyou', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5225', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'syjrw', '事由及任务', 'xtextarea', 'syjrw', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5224', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'sxryxx', '随行人员信息', 'xtextarea', 'sxryxx', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5223', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'rzbqr', '人资部确认', 'xmacro', 'rzbqr', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5222', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'ceoqm', '董事长签名', 'xmacro', 'ceoqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5221', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'bmzjqm', '部门总监签名', 'xmacro', 'bmzjqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5220', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'cctime', '出差时间', 'xfetch', 'cctime', 'text', null, '{\"type\":\"1\",\"format\":\"yyyy-MM-dd\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5219', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'mdd', '目的地', 'xinput', 'mdd', 'varchar', '30', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5218', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'jtgjjbc', '交通工具及班次', 'xinput', 'jtgjjbc', 'varchar', '100', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5217', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'zw', '职务', 'xmacro', 'zw', 'text', null, '{\"type\":\"10\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5216', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'bm', '部门', 'xmacro', 'bm', 'text', null, '{\"type\":\"13\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5215', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'fzrxm', '负责人姓名', 'xinput', 'fzrxm', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5014', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'name', '姓名', 'xmacro', 'name', 'text', null, '{type:8,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('5015', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'wangwei', '岗位', 'xmacro', 'wangwei', 'text', null, '{type:10,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('5016', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'bumen', '部门', 'xmacro', 'bumen', 'text', null, '{type:13,format:\'null\'}', null, null);
INSERT INTO `flow_form_item` VALUES ('5017', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'qjlb', '请假类别', 'xradio', 'qjlb', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5018', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'firsttimes', '请假开始时间', 'xfetch', 'firsttimes', 'text', null, '{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('5019', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'secondtime', '请假结束时间', 'xfetch', 'secondtime', 'text', null, '{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}', null, null);
INSERT INTO `flow_form_item` VALUES ('5020', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'zts', '总天数', 'xinput', 'zts', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5021', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'bmjlqm', '部门经理签名', 'xmacro', 'bmjlqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5022', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'bmzjqm', '部门总监签名', 'xmacro', 'bmzjqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5023', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'ceoqm', '董事长签名', 'xmacro', 'ceoqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5024', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'resons', '请假事由', 'xtextarea', 'resons', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5025', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'bmjlyj', '部门经理意见', 'xtextarea', 'bmjlyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5026', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'xzjybm', '部门总监意见', 'xtextarea', 'xzjybm', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5027', '14E366B1-F3CB-F3B6-02F1-39195CC33446', 'dszyj', '董事长意见', 'xtextarea', 'dszyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5028', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'bm', '部门', 'xmacro', 'bm', 'text', null, '{\"type\":\"13\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5029', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'name', '姓名', 'xmacro', 'name', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5030', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'zhiwei', '职位', 'xmacro', 'zhiwei', 'text', null, '{\"type\":\"10\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5031', '43F0F395-5FD2-C20C-921D-85B6162FFB60', null, '申请类型', 'xradio', 'sqlx', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5032', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'firsttime', '开始时间', 'xfetch', 'firsttime', 'text', null, '{\"type\":\"1\",\"format\":\"yyyy-MM-dd HH:mm\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5033', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'overtime', '结束时间', 'xfetch', 'overtime', 'text', null, '{\"type\":\"1\",\"format\":\"yyyy-MM-dd HH:mm\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5034', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'bmjlqm', '部门经理签名', 'xmacro', 'bmjlqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5035', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'bmzjqm', '部门总监签名', 'xmacro', 'bmzjqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5036', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'ceoqm', '董事长签名', 'xmacro', 'ceoqm', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5037', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'rzbqr', '人资部签名', 'xmacro', 'rzbqr', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5038', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'sqyy', '申请原因', 'xtextarea', 'sqyy', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5039', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'bmjlyj', '部门经理意见', 'xtextarea', 'bmjlyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5040', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'bmzjyj', '部门总监意见', 'xtextarea', 'bmzjyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5041', '43F0F395-5FD2-C20C-921D-85B6162FFB60', 'ceoyj', '董事长意见', 'xtextarea', 'ceoyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5226', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'zsxx', '住宿信息', 'xtextarea', 'zsxx', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5214', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'ximg', '图片上传', 'ximg', 'ximg', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5213', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xworkflow', '流程选择', 'xworkflow', 'xworkflow', 'text', null, '1', null, null);
INSERT INTO `flow_form_item` VALUES ('5212', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xuedit', '富文本框', 'xtextuedit', 'xuedit', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5211', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xtextarea', '多行输入框', 'xtextarea', 'xtextarea', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5210', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xselect', '下拉框', 'xselect', 'xselect', 'text', null, '[{\"value\":\"xselect1\",\"desc\":\"xselect1\"},{\"value\":\"xselect2\",\"desc\":\"xselect2\"}]', null, null);
INSERT INTO `flow_form_item` VALUES ('5209', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xuploads', '多附件上传', 'xuploads', 'xuploads', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5208', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xupload', '单附件上传', 'xupload', 'xupload', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5207', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xfetchusers', '人员多选', 'xfetch', 'xfetchusers', 'text', null, '{\"type\":\"8\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5202', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xfetchdept', '部门单选', 'xfetch', 'xfetchdept', 'text', null, '{\"type\":\"3\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5203', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xfetchdepts', '部门多选', 'xfetch', 'xfetchdepts', 'text', null, '{\"type\":\"4\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5204', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xfetchpriv', '角色单选', 'xfetch', 'xfetchpriv', 'text', null, '{\"type\":\"5\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5205', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xfetchprivs', '角色多选', 'xfetch', 'xfetchprivs', 'text', null, '{\"type\":\"6\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5206', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xfetchuser', '人员单选', 'xfetch', 'xfetchuser', 'text', null, '{\"type\":\"7\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5201', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xfetchtime', '时间选择', 'xfetch', 'xfetchtime', 'text', null, '{\"type\":\"2\",\"format\":\"HH时mm分\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5200', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xfetchdate', '日期选择', 'xfetch', 'xfetchdate', 'text', null, '{\"type\":\"1\",\"format\":\"yyyy-MM-dd HH:mm\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5198', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'moneyb', '金额B', 'xinput', 'moneyb', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5199', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xcalculat', '计算控件', 'xcalculate', 'xcalculat', 'text', null, '{\"module\":\"[金额A]+[金额B]\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5197', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'moneya', '金额A', 'xinput', 'moneya', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5196', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', null, '复选框', 'xcheckbox', 'xcheckbox', 'text', null, '[{\"value\":\"复选1\",\"desc\":\"复选1\"},{\"value\":\"复选2\",\"desc\":\"复选2\"}]', null, null);
INSERT INTO `flow_form_item` VALUES ('5194', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', 'xinput', '单行输入框', 'xinput', 'xinput', 'varchar', '100', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5195', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', null, '单选框', 'xradio', 'xradio', 'text', null, '[{\"value\":\"单选1\",\"desc\":\"单选1\"},{\"value\":\"单选2\",\"desc\":\"单选2\"}]', null, null);
INSERT INTO `flow_form_item` VALUES ('5227', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'ysmx', '预算明细', 'xtextarea', 'ysmx', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5228', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'bmzjyj', '部门总监意见', 'xtextarea', 'bmzjyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5229', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'ceoyj', '董事长意见', 'xtextarea', 'ceoyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5230', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', 'fasdfas', 'asdfasd', 'ximg', 'fasdfas', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5231', '087D4061-B429-6B3B-DA22-B2C67CB6797C', 'fqr', 'fqr', 'xmacro', 'fqr', 'text', null, '{\"type\":\"8\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5232', '087D4061-B429-6B3B-DA22-B2C67CB6797C', 'bm', 'bm', 'xmacro', 'bm', 'text', null, '{\"type\":\"9\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5233', '320D9A41-1D47-9682-FA49-6E7090170508', 'test', 'test', 'xinput', 'test', 'varchar', '4', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5234', '15B298BA-9022-1BF9-02CF-DF6ABFD0E68C', 'rq', '日期', 'xmacro', 'rq', 'text', null, '{\"type\":\"2\",\"format\":\"yyyy-MM-dd\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5235', '15B298BA-9022-1BF9-02CF-DF6ABFD0E68C', 'xm', '姓名', 'xmacro', 'xm', 'text', null, '{\"type\":\"8\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5236', '15B298BA-9022-1BF9-02CF-DF6ABFD0E68C', 'sy', '事由', 'xtextarea', 'sy', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5237', 'EFBDBA10-B66F-B40C-365B-E0646FF392F0', 'bxr', '报销人', 'xmacro', 'bxr', 'text', null, '{\"type\":\"8\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5238', 'EFBDBA10-B66F-B40C-365B-E0646FF392F0', 'bxrq', '报销日期', 'xmacro', 'bxrq', 'text', null, '{\"type\":\"2\",\"format\":\"yyyy-MM-dd\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5239', 'EFBDBA10-B66F-B40C-365B-E0646FF392F0', 'bxmx', '报销明细', 'xlist', 'bxmx', null, null, '[{\"title\":\"日期\",\"fieldname\":\"rq\",\"width\":\"100\",\"stylewidth\":\"100\",\"sum\":\"on\",\"formula\":\"\",\"type\":\"6\",\"model\":\"\"},{\"title\":\"车费\",\"fieldname\":\"cf\",\"width\":\"100\",\"stylewidth\":\"100\",\"sum\":\"on\",\"formula\":\"\",\"type\":\"1\",\"model\":\"\"},{\"title\":\"住宿费\",\"fieldname\":\"zsf\",\"width\":\"100\",\"stylewidth\":\"100\",\"sum\":\"on\",\"formula\":\"\",\"type\":\"1\",\"model\":\"\"},{\"title\":\"市内交通\",\"fieldname\":\"snjt\",\"width\":\"100\",\"stylewidth\":\"100\",\"sum\":\"on\",\"formula\":\"\",\"type\":\"1\",\"model\":\"\"},{\"title\":\"餐费\",\"fieldname\":\"cfei\",\"width\":\"100\",\"stylewidth\":\"100\",\"sum\":\"on\",\"formula\":\"\",\"type\":\"1\",\"model\":\"\"},{\"title\":\"合计\",\"fieldname\":\"hj\",\"width\":\"100\",\"stylewidth\":\"100\",\"sum\":\"on\",\"formula\":\"cf+zsf+snjt+cfei\",\"type\":\"1\",\"model\":\"\"}]', null, null);
INSERT INTO `flow_form_item` VALUES ('5577', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', 'beizhu', '备注', 'xtextarea', 'beizhu', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5576', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', 'yijian', '经理意见', 'xtextarea', 'yijian', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5575', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', 'bumenyj', '部门意见', 'xtextarea', 'bumenyj', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5574', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', 'shixiang', '申请事项', 'xtextarea', 'shixiang', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5573', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', 'jinglishenpishijian', '经理审批时间', 'xmacro', 'jinglishenpishijian', 'text', null, '{\"type\":\"1\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5572', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', null, '经理意见按钮', 'xradio', 'jingliyijian', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5570', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', null, '部门意见按钮', 'xradio', 'bumenyijian', 'text', null, null, null, null);
INSERT INTO `flow_form_item` VALUES ('5571', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', 'bumenshenpishijian', '部门审批时间', 'xmacro', 'bumenshenpishijian', 'text', null, '{\"type\":\"3\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5569', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', 'dangqianshijian', '当前时间', 'xmacro', 'dangqianshijian', 'text', null, '{\"type\":\"2\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}', null, null);
INSERT INTO `flow_form_item` VALUES ('5568', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', 'xingmingkj', '姓名控件', 'xmacro', 'xingmingkj', 'text', null, '{\"type\":\"12\",\"format\":null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5579', '0E379538-A7F4-E947-9319-CB2C9B195014', 'testname', 'test001', 'xinput', 'testname', 'varchar', '10', null, null, null);
INSERT INTO `flow_form_item` VALUES ('5806', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'zhiwei', '职位', 'xmacro', 'zhiwei', 'text', null, '{\'type\':\'10\',\'format\':null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5805', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'name', '申请人', 'xmacro', 'name', 'text', null, '{\'type\':\'8\',\'format\':null}', null, null);
INSERT INTO `flow_form_item` VALUES ('5804', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', 'bm', '部门', 'xmacro', 'bm', 'text', null, '{\'type\':\'13\',\'format\':null}', null, null);

-- ----------------------------
-- Table structure for flow_pass_leave
-- ----------------------------
DROP TABLE IF EXISTS `flow_pass_leave`;
CREATE TABLE `flow_pass_leave` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FLOW_PASS_LEAVE_ID` varchar(50) DEFAULT NULL,
  `PASS_LEAVE_ID` varchar(50) DEFAULT NULL,
  `PASS_LEAVE_NAME` varchar(50) DEFAULT NULL,
  `PASS_LEAVE_NEXT` varchar(500) DEFAULT NULL,
  `FLOW_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_pass_leave
-- ----------------------------
INSERT INTO `flow_pass_leave` VALUES ('13', '9D1B4C3D-A4DA-DAD0-F53B-FB27928A2969', '99', '董事长', '88', 'A436C211-38C8-10E8-6569-020B45D7AD13', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_pass_leave` VALUES ('14', '151EB1A5-0CD8-5AD8-8814-30A89D3893EC', '1', '职员', '0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_pass_leave` VALUES ('15', '595E3BE1-581D-3C4D-17CE-1AC630CB1E7B', '2', '部门经理', '0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_pass_leave` VALUES ('16', '6A994BDA-D0D8-8675-A1CD-B0466D0B9633', '3', '部门经理', '0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for flow_process
-- ----------------------------
DROP TABLE IF EXISTS `flow_process`;
CREATE TABLE `flow_process` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FLOW_ID` varchar(50) DEFAULT '',
  `FORM_ID` varchar(50) DEFAULT '',
  `PRCS_ID` int(5) DEFAULT NULL,
  `PRCS_NAME` varchar(40) DEFAULT '',
  `LEAD_PRCS_LEAVE` varchar(50) DEFAULT '0' COMMENT '与人员的行政级别一样',
  `FORCE_AGGREGATION` varchar(2) DEFAULT '0',
  `FORCE_PARALLEL` varchar(2) DEFAULT '0',
  `NEXT_PRCS` varchar(800) DEFAULT '',
  `PRCS_TYPE` varchar(2) DEFAULT NULL,
  `PRINT_X` varchar(4) DEFAULT '',
  `PRINT_Y` varchar(4) DEFAULT '',
  `PATH` varchar(255) DEFAULT NULL,
  `FILE_NAME` varchar(255) DEFAULT NULL,
  `USER_PRIV` text,
  `DEPT_PRIV` text,
  `PRIV_ID` text,
  `WRITER_FIELD` text,
  `CHILD_TABLE_WRITER_FIELD` text,
  `PRCS_CONDITION` text,
  `MEMO` text,
  `AUTO_USER_MODLE` text,
  `S_PRCS_AUTO` varchar(2) DEFAULT NULL,
  `CHILD_FLOW` varchar(50) DEFAULT NULL COMMENT '关联的子流程ID',
  `WAIT_PRCS_ID` varchar(2) DEFAULT '',
  `PARENT_WAIT` varchar(2) DEFAULT NULL,
  `COPY_TO_CHILD` varchar(500) DEFAULT NULL COMMENT '从父流程向子流程中写数据',
  `COPY_TO_PARENT` varchar(500) DEFAULT NULL COMMENT '从子流程中向父流程回写数据',
  `SHARE_FLOW_DOC` varchar(2) DEFAULT NULL COMMENT '父子流程附件共享',
  `PUBLIC_FILE` varchar(100) DEFAULT NULL,
  `GO_BACK` varchar(2) DEFAULT '0',
  `FOLLOW` varchar(2) DEFAULT '0',
  `PASS_TIME` int(11) DEFAULT '0',
  `MUST_FIELD` varchar(2000) DEFAULT NULL,
  `HIDDEN_FIELD` varchar(2000) DEFAULT NULL,
  `SMS_CONFIG` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=518 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_process
-- ----------------------------
INSERT INTO `flow_process` VALUES ('414', '7518C25D-9938-0253-4EBA-50752A1D1D6E', '933D26C7-74E3-0ED5-EDB9-CAF53B17BD7E', '1', '请假申请', '0', '0', '0', '', '1', '139', '70', null, null, 'userAll', '', '', 'resons,zts,firsttimes,secondtime,qjlb,bumen,wangwei,name,xingm', null, '[{\"prcsTo\":\"2\",\"condition\":[{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"专员\",\"oper\":\"等于\"},{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"系统管理员\",\"oper\":\"等于\"}],\"exp\":\"{1}or{2}\"},{\"prcsTo\":\"3\",\"condition\":[{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"部门经理\",\"oper\":\"等于\"},{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"组长\",\"oper\":\"等于\"}],\"exp\":\"{1}or{2}\"},{\"prcsTo\":\"4\",\"condition\":[{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"总监\",\"oper\":\"等于\"},{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"副总监\",\"oper\":\"等于\"},{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"董事会秘书\",\"oper\":\"等于\"}],\"exp\":\"{1}or{2}or{3}\"}]', '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('487', '7F4FF6DC-2EE3-C410-5628-2F759F68623C', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '4', '并发结点4', '0', '0', '0', '', '4', '200', '401', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('415', '7518C25D-9938-0253-4EBA-50752A1D1D6E', '933D26C7-74E3-0ED5-EDB9-CAF53B17BD7E', '0', '结束', '0', '0', '0', '', '2', '725', '429', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('416', '7518C25D-9938-0253-4EBA-50752A1D1D6E', '933D26C7-74E3-0ED5-EDB9-CAF53B17BD7E', '2', '2、部门经理审批', '0', '0', '0', '', '3', '131', '410', null, null, 'userAll', '', '', 'bmjlqm,bmjlyj', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('417', '7518C25D-9938-0253-4EBA-50752A1D1D6E', '933D26C7-74E3-0ED5-EDB9-CAF53B17BD7E', '3', '3、部门总监审批', '0', '0', '0', '5', '3', '443', '62', null, null, 'userAll', '', '', 'bmzjqm,xzzjyj', null, '[{\"prcsTo\":\"4\",\"condition\":[{\"fieldName\":\"zts\",\"name\":\"总天数\",\"value\":\"3\",\"oper\":\"大于\"}],\"exp\":\"{1}\"},{\"prcsTo\":\"5\",\"condition\":[{\"fieldName\":\"zts\",\"name\":\"总天数\",\"value\":\"3\",\"oper\":\"小于\"}],\"exp\":\"{1}\"}]', '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('488', 'C8445FFF-A7B4-4163-4CB7-61E9826A96AE', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '1', '开始', '0', '0', '0', '0', '1', '200', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('418', '7518C25D-9938-0253-4EBA-50752A1D1D6E', '933D26C7-74E3-0ED5-EDB9-CAF53B17BD7E', '4', '4、董事长审批', '0', '0', '0', '5', '3', '436', '417', null, null, 'userAll', '', '', 'dszqm,dszyj', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('419', '7518C25D-9938-0253-4EBA-50752A1D1D6E', '933D26C7-74E3-0ED5-EDB9-CAF53B17BD7E', '5', '5、人事备案', '0', '0', '0', '0', '3', '728', '66', null, null, 'userAll', '', '', null, null, null, '', null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('420', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', '1', '发起申请', '0', '0', '0', '', '1', '272', '112', null, null, 'userAll', '', '', 'beizhu,sqresone,sqtime,srsxs,sfsxs,username,shuliang,sqwp,bm,name,zhiwei,syr', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('421', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', '0', '结束', '0', '0', '0', '', '2', '210', '545', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('422', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', '2', '部门领导意见', '0', '0', '0', '', '3', '135', '289', null, null, 'userAll', '', '', 'bmspyi,bmldqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('423', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', '3', '行政部意见', '0', '0', '0', '0', '3', '474', '191', null, null, 'userAll', '', '', 'xzspyj,xzldqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('424', '4951F0EA-3C36-8447-B515-D2B8C1807D0B', 'E371345F-1843-46B8-4E8F-1C7612B02733', '1', '申请报销', '0', '0', '0', '', '1', '121', '59', null, null, 'userAll', '', '', 'bxyy,tbren,jexx,djzs,bxrenname,jedx,bm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('484', '7F4FF6DC-2EE3-C410-5628-2F759F68623C', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '0', '结束', '0', '0', '0', '', '2', '800', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('485', '7F4FF6DC-2EE3-C410-5628-2F759F68623C', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '2', '普通步骤2', '0', '0', '0', '', '3', '200', '317', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('425', '4951F0EA-3C36-8447-B515-D2B8C1807D0B', 'E371345F-1843-46B8-4E8F-1C7612B02733', '0', '结束', '0', '0', '0', '', '2', '924', '108', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('426', '4951F0EA-3C36-8447-B515-D2B8C1807D0B', 'E371345F-1843-46B8-4E8F-1C7612B02733', '2', '部门总监签字', '0', '0', '0', '', '3', '122', '416', null, null, 'userAll', '', '', 'bmzjqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('427', '4951F0EA-3C36-8447-B515-D2B8C1807D0B', 'E371345F-1843-46B8-4E8F-1C7612B02733', '3', '会计审核', '0', '0', '0', '', '3', '381', '65', null, null, 'userAll', '', '', 'kjqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('428', '4951F0EA-3C36-8447-B515-D2B8C1807D0B', 'E371345F-1843-46B8-4E8F-1C7612B02733', '4', '财务主管签字', '0', '0', '0', '5,6', '3', '374', '416', null, null, 'userAll', '', '', 'cwzgqm', null, '[{\"prcsTo\":\"5\",\"condition\":[],\"exp\":\"\"},{\"prcsTo\":\"6\",\"condition\":[],\"exp\":\"\"}]', '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('429', '4951F0EA-3C36-8447-B515-D2B8C1807D0B', 'E371345F-1843-46B8-4E8F-1C7612B02733', '5', '董事长签字', '0', '0', '0', '6', '3', '635', '72', null, null, 'userAll', '', '', 'ceoqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('430', '4951F0EA-3C36-8447-B515-D2B8C1807D0B', 'E371345F-1843-46B8-4E8F-1C7612B02733', '6', '出纳签字', '0', '0', '0', '0', '3', '649', '433', null, null, 'userAll', '', '', 'cnqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('431', 'EA76E820-59FE-457C-8F1C-1D2368735844', '53CE8F25-B321-11A7-CB4B-35898B280955', '1', '出差申请', '0', '0', '0', '', '1', '116', '95', null, null, 'userAll', '', '', 'bm,usename,zw,jtgj,sxry,firsttime,backtime,reason,zhusu,yusuan,sxryxx,place,ccsj,jtgjjbc', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('483', '7F4FF6DC-2EE3-C410-5628-2F759F68623C', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '1', '开始', '0', '0', '0', '0', '1', '200', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('432', 'EA76E820-59FE-457C-8F1C-1D2368735844', '53CE8F25-B321-11A7-CB4B-35898B280955', '0', '结束', '0', '0', '0', '', '2', '597', '230', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('434', 'EA76E820-59FE-457C-8F1C-1D2368735844', '53CE8F25-B321-11A7-CB4B-35898B280955', '3', '部门总监意见', '0', '0', '0', '', '3', '116', '410', null, null, 'userAll', '', '', 'xzzjyjqm,xzzjyj,bmzjqm,bmzjyj', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('435', 'EA76E820-59FE-457C-8F1C-1D2368735844', '53CE8F25-B321-11A7-CB4B-35898B280955', '4', '董事长审批', '0', '0', '0', '5', '3', '392', '99', null, null, 'userAll', '', '', 'ceoqm,ceoyj', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('436', 'EA76E820-59FE-457C-8F1C-1D2368735844', '53CE8F25-B321-11A7-CB4B-35898B280955', '5', '人资部确认', '0', '0', '0', '0', '3', '396', '402', null, null, 'userAll', '', '', 'rzbqr,rzbqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('437', 'BE5A39DE-2D06-7E32-322B-A97B9F93CD38', '988DD7F4-6E93-2C98-FB13-C867B7757E02', '1', '请款申请', '0', '0', '0', '', '1', '145', '62', null, null, 'userAll', '', '', 'qkjedx,xxje,bm,name,qdkiem,qkxm,qkreason', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('482', '7D5FE9CA-FB34-8FE2-FDF1-5C01B04E8A95', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '0', '结束', '0', '0', '0', '', '2', '800', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('438', 'BE5A39DE-2D06-7E32-322B-A97B9F93CD38', '988DD7F4-6E93-2C98-FB13-C867B7757E02', '0', '结束', '0', '0', '0', '', '2', '719', '363', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('444', '83524B7A-F541-CF35-4464-B10D93AAE996', '43F0F395-5FD2-C20C-921D-85B6162FFB60', '1', '发起申请', '0', '0', '0', '', '1', '210', '55', null, null, 'userAll', '', '', 'name,bm,firsttime,overtime,bxyy,zhiwei,sqyy,sqlx', null, '[{\"prcsTo\":\"2\",\"condition\":[],\"exp\":\"\"},{\"prcsTo\":\"3\",\"condition\":[],\"exp\":\"\"},{\"prcsTo\":\"4\",\"condition\":[],\"exp\":\"\"}]', '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('481', '7D5FE9CA-FB34-8FE2-FDF1-5C01B04E8A95', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '1', '开始', '0', '0', '0', '0', '1', '200', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('440', 'BE5A39DE-2D06-7E32-322B-A97B9F93CD38', '988DD7F4-6E93-2C98-FB13-C867B7757E02', '3', '部门总监审批', '0', '0', '0', '', '3', '152', '364', null, null, 'userAll', '', '', 'bmzjsp,bmzjqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('441', 'BE5A39DE-2D06-7E32-322B-A97B9F93CD38', '988DD7F4-6E93-2C98-FB13-C867B7757E02', '4', '董事长审批', '0', '0', '0', '5', '3', '426', '61', null, null, 'userAll', '', '', 'ceosp,ceoqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('442', 'BE5A39DE-2D06-7E32-322B-A97B9F93CD38', '988DD7F4-6E93-2C98-FB13-C867B7757E02', '5', '财务审批', '0', '0', '0', '6', '3', '427', '370', null, null, 'userAll', '', '', 'cwqm,cwsh', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('443', 'BE5A39DE-2D06-7E32-322B-A97B9F93CD38', '988DD7F4-6E93-2C98-FB13-C867B7757E02', '6', '出纳确认', '0', '0', '0', '0', '3', '715', '66', null, null, 'userAll', '', '', 'cnqr', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('445', '83524B7A-F541-CF35-4464-B10D93AAE996', '43F0F395-5FD2-C20C-921D-85B6162FFB60', '0', '结束', '0', '0', '0', '', '2', '697', '360', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('446', '83524B7A-F541-CF35-4464-B10D93AAE996', '43F0F395-5FD2-C20C-921D-85B6162FFB60', '2', '部门经理审批', '0', '0', '0', '', '3', '217', '319', null, null, 'userAll', '', '', 'bmjlqm,bmjlyj', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('480', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', '6', '子流程节点6', '0', '0', '0', '', '6', '397', '211', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('447', '83524B7A-F541-CF35-4464-B10D93AAE996', '43F0F395-5FD2-C20C-921D-85B6162FFB60', '3', '部门总监审批', '0', '0', '0', '', '3', '450', '49', null, null, 'userAll', '', '', 'bmzjqm,bmzjyj', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('448', '83524B7A-F541-CF35-4464-B10D93AAE996', '43F0F395-5FD2-C20C-921D-85B6162FFB60', '4', '董事长审批', '0', '0', '0', '5', '3', '459', '333', null, null, 'userAll', '', '', 'ceoyj,ceoqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('449', '83524B7A-F541-CF35-4464-B10D93AAE996', '43F0F395-5FD2-C20C-921D-85B6162FFB60', '5', '人事备案', '0', '0', '0', '0', '3', '671', '63', null, null, 'userAll', '', '', 'rzbqr', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('479', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', '6', '子流程节点6', '0', '0', '0', '', '6', '657', '343', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('450', 'C9175369-B468-37E9-4E10-C128C27BE59E', '08B9C566-58E5-2035-7617-A62E0644BFCC', '1', '申请报销', '0', '0', '0', '', '1', '147', '83', null, null, 'userAll', '', '', 'tbren,djzs,jexx,hjjedx,bxren,bm,baoxiaoshiyou', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('451', 'C9175369-B468-37E9-4E10-C128C27BE59E', '08B9C566-58E5-2035-7617-A62E0644BFCC', '0', '结束', '0', '0', '0', '', '2', '948', '82', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('452', 'C9175369-B468-37E9-4E10-C128C27BE59E', '08B9C566-58E5-2035-7617-A62E0644BFCC', '2', '部门总监意见', '0', '0', '0', '', '3', '150', '340', null, null, 'userAll', '', '', 'bmzjqz', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('478', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', '5', '普通步骤5', '0', '0', '0', '0', '3', '531', '537', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('453', 'C9175369-B468-37E9-4E10-C128C27BE59E', '08B9C566-58E5-2035-7617-A62E0644BFCC', '3', '会计意见', '0', '0', '0', '', '3', '363', '102', null, null, 'userAll', '', '', 'kjqz', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('454', 'C9175369-B468-37E9-4E10-C128C27BE59E', '08B9C566-58E5-2035-7617-A62E0644BFCC', '4', '财务主管意见', '0', '0', '0', '5', '3', '411', '354', null, null, 'userAll', '', '', 'cwzgqz', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('455', 'C9175369-B468-37E9-4E10-C128C27BE59E', '08B9C566-58E5-2035-7617-A62E0644BFCC', '5', '董事长审核', '0', '0', '0', '6', '3', '684', '83', null, null, 'userAll', '', '', 'ceoqz', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('456', 'C9175369-B468-37E9-4E10-C128C27BE59E', '08B9C566-58E5-2035-7617-A62E0644BFCC', '6', '出纳签字', '0', '0', '0', '0', '3', '676', '350', null, null, 'userAll', '', '', 'cnqz', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('457', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', '1', '请款申请', '0', '0', '0', '', '1', '33', '142', null, null, 'userAll', '', '', 'qkreason,qkr,qktime,qkxm,qljedx,xxje,bm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('458', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', '0', '结束', '0', '0', '0', '', '2', '983', '377', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('461', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', '4', '财务部审核', '0', '0', '0', '5', '3', '582', '458', null, null, 'userAll', '', '', 'cwsh,cwqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('459', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', '2', '部门总监审核', '0', '0', '0', '', '3', '209', '160', null, null, 'userAll', '', '', 'bmzjsp,bmzjqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('460', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', '3', '董事长审核', '0', '0', '0', '6', '3', '575', '160', null, null, 'userAll', '', '', 'ceosp,ceoqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('462', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', '5', '出纳签字', '0', '0', '0', '0', '3', '912', '169', null, null, 'userAll', '', '', 'cnqr', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('463', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '14E366B1-F3CB-F3B6-02F1-39195CC33446', '1', '请假申请', '0', '0', '0', '', '1', '175', '57', null, null, 'userAll', '', '', 'zts,secondtime,qjlb,firsttimes,bumen,wangwei,name,resons', null, '[{\"prcsTo\":\"2\",\"condition\":[{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"专员\",\"oper\":\"等于\"},{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"系统管理员\",\"oper\":\"等于\"}],\"exp\":\"{1}or{2}\"},{\"prcsTo\":\"3\",\"condition\":[{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"部门经理\",\"oper\":\"等于\"},{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"组长\",\"oper\":\"等于\"}],\"exp\":\"{1}or{2}\"},{\"prcsTo\":\"4\",\"condition\":[{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"总监\",\"oper\":\"等于\"},{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"副总监\",\"oper\":\"等于\"},{\"fieldName\":\"wangwei\",\"name\":\"岗位\",\"value\":\"董事会秘书\",\"oper\":\"等于\"}],\"exp\":\"{1}or{2}or{3}\"}]', '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('464', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '14E366B1-F3CB-F3B6-02F1-39195CC33446', '0', '结束', '0', '0', '0', '', '2', '737', '428', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('465', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '14E366B1-F3CB-F3B6-02F1-39195CC33446', '2', '2、部门经理审批', '0', '0', '0', '', '3', '174', '387', null, null, 'userAll', '', '', 'bmjlyj,bmjlqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('466', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '14E366B1-F3CB-F3B6-02F1-39195CC33446', '3', '部门总监审核', '0', '0', '0', '5', '3', '470', '50', null, null, 'userAll', '', '', 'xzjybm,bmzjqm', null, '[{\"prcsTo\":\"4\",\"condition\":[{\"fieldName\":\"zts\",\"name\":\"总天数\",\"value\":\"3\",\"oper\":\"大于等于\"}],\"exp\":\"{1}\"},{\"prcsTo\":\"5\",\"condition\":[{\"fieldName\":\"zts\",\"name\":\"总天数\",\"value\":\"3\",\"oper\":\"小于\"}],\"exp\":\"{1}\"}]', '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('467', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '14E366B1-F3CB-F3B6-02F1-39195CC33446', '4', '董事长审核', '0', '0', '0', '5', '3', '462', '402', null, null, 'userAll', '', '', 'dszyj,ceoqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('468', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '14E366B1-F3CB-F3B6-02F1-39195CC33446', '5', '人事备案', '0', '0', '0', '0', '3', '725', '48', null, null, 'userAll', '', '', null, null, null, '', null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('469', '9137AC48-7432-F5D6-CAB0-C14546C63049', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', '1', '出差申请', '0', '0', '0', '', '1', '200', '64', null, null, 'userAll', '', '', 'ysmx,zsxx,sxryxx,syjrw,cctime,mdd,zw,jtgjjbc,bm,fzrxm', null, '[{\"prcsTo\":\"2\",\"condition\":[{\"fieldName\":\"zw\",\"name\":\"职务\",\"value\":\"系统管理员\",\"oper\":\"等于\"},{\"fieldName\":\"zw\",\"name\":\"职务\",\"value\":\"专员\",\"oper\":\"等于\"},{\"fieldName\":\"zw\",\"name\":\"职务\",\"value\":\"组长\",\"oper\":\"等于\"}],\"exp\":\"{1}or{2}or{3}\"},{\"prcsTo\":\"3\",\"condition\":[{\"fieldName\":\"zw\",\"name\":\"职务\",\"value\":\"副总监\",\"oper\":\"等于\"},{\"fieldName\":\"zw\",\"name\":\"职务\",\"value\":\"总监\",\"oper\":\"等于\"},{\"fieldName\":\"zw\",\"name\":\"职务\",\"value\":\"董事会秘书\",\"oper\":\"等于\"}],\"exp\":\"{1}or{2}or{3}\"}]', '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('476', '2214A329-42B1-49F3-0A30-0FBB857FECCD', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', '2', '审批', '0', '0', '0', '0', '3', '330', '211', null, null, 'userAll', '', '', 'xinput,xradio,xcheckbox,moneya,moneyb,xcalculate,xfetchdate,xfetchtime,xfetchdept,xfetchdepts,xfetchpriv,xfetchprivs,xfetchuser,xfetchusers,xupload,xuploads,xselect,xtextarea,xuedit,xworkflow,ximg', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('475', '2214A329-42B1-49F3-0A30-0FBB857FECCD', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', '0', '结束', '0', '0', '0', '', '2', '400', '410', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('470', '9137AC48-7432-F5D6-CAB0-C14546C63049', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', '0', '结束', '0', '0', '0', '', '2', '800', '200', null, null, 'userAll', '', '', null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('471', '9137AC48-7432-F5D6-CAB0-C14546C63049', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', '2', '部门总监审核', '0', '0', '0', '', '3', '207', '354', null, null, 'userAll', '', '', 'bmzjyj,bmzjqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('472', '9137AC48-7432-F5D6-CAB0-C14546C63049', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', '3', '董事长审核', '0', '0', '0', '', '3', '494', '65', null, null, 'userAll', '', '', 'ceoyj,ceoqm', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('473', '9137AC48-7432-F5D6-CAB0-C14546C63049', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', '4', '人事部确认', '0', '0', '0', '0', '3', '490', '339', null, null, 'userAll', '', '', 'rzbqr', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('477', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', '4', '普通步骤4', '0', '0', '0', '5,6', '3', '433', '437', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('474', '2214A329-42B1-49F3-0A30-0FBB857FECCD', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', '1', '开始', '0', '0', '0', '', '1', '79', '73', null, null, 'userAll', '', '', 'xinput,xradio,xcheckbox,moneya,moneyb,xcalculate,xfetchdate,xfetchtime,xfetchdept,xfetchdepts,xfetchpriv,xfetchprivs,xfetchuser,xfetchusers,xupload,xuploads,xselect,xtextarea,xuedit,xworkflow,ximg', null, null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('489', 'C8445FFF-A7B4-4163-4CB7-61E9826A96AE', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '0', '结束', '0', '0', '0', '', '2', '800', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('490', 'C8445FFF-A7B4-4163-4CB7-61E9826A96AE', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '2', '普通步骤2', '0', '0', '0', '', '3', '200', '317', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('491', 'C8445FFF-A7B4-4163-4CB7-61E9826A96AE', '087D4061-B429-6B3B-DA22-B2C67CB6797C', '3', '并发结点3', '0', '0', '0', '', '4', '200', '401', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('492', 'E4E5528E-6889-AE4C-564D-DFBEF0EAF6BE', 'E37BBB32-FF47-6924-F4FA-FEB54732D62E', '1', '开始', '0', '0', '0', '0', '1', '200', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('493', 'E4E5528E-6889-AE4C-564D-DFBEF0EAF6BE', 'E37BBB32-FF47-6924-F4FA-FEB54732D62E', '0', '结束', '0', '0', '0', '', '2', '800', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('494', 'E4E5528E-6889-AE4C-564D-DFBEF0EAF6BE', 'E37BBB32-FF47-6924-F4FA-FEB54732D62E', '2', '普通步骤2', '0', '0', '0', '', '3', '200', '317', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('495', 'E4E5528E-6889-AE4C-564D-DFBEF0EAF6BE', 'E37BBB32-FF47-6924-F4FA-FEB54732D62E', '3', '子流程节点3', '0', '0', '0', '', '6', '200', '401', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('496', '2214A329-42B1-49F3-0A30-0FBB857FECCD', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', '3', '普通步骤3', '0', '0', '0', '', '3', '79', '190', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}');
INSERT INTO `flow_process` VALUES ('497', '2214A329-42B1-49F3-0A30-0FBB857FECCD', 'B7DB0FD1-DCD6-86D4-D9D7-E20082996380', '4', '并发结点4', '0', '0', '0', '', '4', '50', '334', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('498', '07A13F1B-F180-82F8-DD97-703DBBD1166C', '15B298BA-9022-1BF9-02CF-DF6ABFD0E68C', '1', '开始', '0', '0', '0', '0', '1', '200', '200', null, null, '', 'deptAll', '', 'rq,xm,sy', null, '[{\"prcsTo\":\"0\",\"condition\":[{\"fieldName\":\"sy\",\"name\":\"事由\",\"value\":\"44\",\"oper\":\"等于\"}],\"exp\":\"{1}\"},{\"prcsTo\":\"2\",\"condition\":[{\"fieldName\":\"sy\",\"name\":\"事由\",\"value\":\"55\",\"oper\":\"等于\"}],\"exp\":\"{1}\"}]', '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '2', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('499', '07A13F1B-F180-82F8-DD97-703DBBD1166C', '15B298BA-9022-1BF9-02CF-DF6ABFD0E68C', '0', '结束', '0', '0', '0', '', '2', '451', '211', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('500', '07A13F1B-F180-82F8-DD97-703DBBD1166C', '15B298BA-9022-1BF9-02CF-DF6ABFD0E68C', '2', '普通步骤2', '0', '0', '0', '', '3', '200', '317', null, null, '2', '', '', null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('501', '11169EC6-F406-0433-B877-81D9F455BDDD', 'EFBDBA10-B66F-B40C-365B-E0646FF392F0', '1', '开始', '0', '0', '0', '', '1', '200', '200', null, null, 'tgh,fxd,zrh', '', '', 'bxr,bxrq', '[{\"table\":\"bxmx\",\"writerfield\":\"\",\"read\":\"\",\"print\":\"rq,cf,zsf,snjt,cfei,hj\",\"title\":\"日期,车费,住宿费,市内交通,餐费,合计\"}]', null, '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('502', '11169EC6-F406-0433-B877-81D9F455BDDD', 'EFBDBA10-B66F-B40C-365B-E0646FF392F0', '0', '结束', '0', '0', '0', '', '2', '800', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('503', '11169EC6-F406-0433-B877-81D9F455BDDD', 'EFBDBA10-B66F-B40C-365B-E0646FF392F0', '2', '普通步骤2', '0', '0', '0', '0', '3', '200', '317', null, null, 'tgh', '', '', null, null, null, '', null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('504', '3A7F5F45-D404-F04A-0C42-C0BE2292EEC1', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', '1', '开始', '0', '0', '0', '', '1', '199', '162', null, null, 'cst,dd', '1ACBDC2F-C52A-9E02-2714-B93810938358,13265125-F623-F7F7-0233-CFD9A795369F,71E83FF5-81E2-36F0-9003-D45796753D3D', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', 'shixiang,xingmingkj,dangqianshijian,beizhu', null, null, '', '{\"autoUserType\":\",\",\"autoUserRule\":\"1\",\"autoFormField\":\"\",\"autoDeptId\":\"13265125-F623-F7F7-0233-CFD9A795369F\",\"defUserId\":\"\"}', '1', null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}');
INSERT INTO `flow_process` VALUES ('505', '3A7F5F45-D404-F04A-0C42-C0BE2292EEC1', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', '0', '结束', '0', '0', '0', '', '2', '200', '635', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('509', '3A7F5F45-D404-F04A-0C42-C0BE2292EEC1', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', '2', '普通步骤2', '0', '0', '0', '3', '3', '200', '317', null, null, null, null, null, 'bumenyj,bumenyijian,bumenshenpishijian', null, '[{\"prcsTo\":\"3\",\"condition\":[{\"fieldName\":\"bumenyijian\",\"name\":\"部门意见按钮\",\"value\":\"同意\",\"oper\":\"等于\"}],\"exp\":\"\"}]', null, null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('510', '3A7F5F45-D404-F04A-0C42-C0BE2292EEC1', '4B94BA80-C465-8CDC-B971-DCFE7200A68D', '3', '普通步骤3', '0', '0', '0', '0,0', '3', '200', '451', null, null, '', '', '1408244A-2653-52D7-4853-3F48EC302298', 'yijian,jinglishenpishijian,jingliyijian', null, '[{\"prcsTo\":\"0\",\"condition\":[{\"fieldName\":\"jingliyijian\",\"name\":\"经理意见按钮\",\"value\":\"同意\",\"oper\":\"等于\"}],\"exp\":\"\"}]', '', null, null, null, '', null, null, null, null, '{\'readOnly\':\'1\',\'createUpLoad\':\'1\',\'edit\':\'1\',\'down\':\'1\'}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('511', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '0E379538-A7F4-E947-9319-CB2C9B195014', '1', '开始', '0', '0', '0', '2', '1', '206', '197', null, null, 'admin', '', '', null, null, null, '', null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('512', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '0E379538-A7F4-E947-9319-CB2C9B195014', '0', '结束', '0', '0', '0', '', '2', '800', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('514', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '0E379538-A7F4-E947-9319-CB2C9B195014', '2', '普通步骤2', '0', '0', '0', '0', '3', '480', '201', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('515', '45ADC2E6-4FA6-189C-A7DB-DEC62436F437', 'E37BBB32-FF47-6924-F4FA-FEB54732D62E', '1', '开始', '0', '0', '0', '2', '1', '200', '200', null, null, null, null, null, null, null, null, '', null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('516', '45ADC2E6-4FA6-189C-A7DB-DEC62436F437', 'E37BBB32-FF47-6924-F4FA-FEB54732D62E', '0', '结束', '0', '0', '0', '', '2', '800', '200', null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, '0', '0', '0', null, null, null);
INSERT INTO `flow_process` VALUES ('517', '45ADC2E6-4FA6-189C-A7DB-DEC62436F437', 'E37BBB32-FF47-6924-F4FA-FEB54732D62E', '2', '普通步骤2', '0', '0', '0', '0', '3', '413', '278', null, null, null, null, null, null, null, null, '', null, null, null, '', null, null, null, null, '{\"readOnly\":\"1\",\"createUpLoad\":\"1\",\"edit\":\"1\",\"down\":\"1\"}', '0', '0', '0', null, null, null);

-- ----------------------------
-- Table structure for flow_run
-- ----------------------------
DROP TABLE IF EXISTS `flow_run`;
CREATE TABLE `flow_run` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RUN_ID` varchar(50) DEFAULT NULL COMMENT '流水号',
  `TITLE` varchar(50) DEFAULT NULL COMMENT '流程标题',
  `BEGIN_TIME` varchar(50) DEFAULT NULL COMMENT '流程发起时间',
  `END_TIME` varchar(50) DEFAULT NULL COMMENT '流程结束时间',
  `DEL_FLAG` varchar(2) DEFAULT NULL COMMENT '逻辑 删除 标识',
  `OP_USER_STR` varchar(2000) DEFAULT NULL COMMENT '参与人员',
  `STATUS` varchar(2) DEFAULT NULL COMMENT '结束状态',
  `FLOW_ID` varchar(50) DEFAULT NULL COMMENT '流程ID',
  `PATH` varchar(500) DEFAULT NULL COMMENT '查看流程的url路径',
  `MODULE` varchar(10) DEFAULT NULL COMMENT '流程类型，如WORKFLOW,PROJECT等',
  `ATTACH_ID` text COMMENT '流程的共公附件',
  `WAIT_PRCS_ID` varchar(2) DEFAULT '' COMMENT '流程的等待步骤标记',
  `PARENT_WAIT` varchar(2) DEFAULT '0' COMMENT '是否等待',
  `FLOW_LEAVE` varchar(2) DEFAULT '0' COMMENT '优先级别0，1，2，',
  `ORG_ID` varchar(50) DEFAULT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '流程发起人',
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=352 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_run
-- ----------------------------
INSERT INTO `flow_run` VALUES ('344', 'DEA2FC19-EABA-7A2C-E606-2A54E3FFEA07', '超级管理员-办公用品申请-2016年11月21日', '2016-11-21 12:47:25', null, '0', 'admin', '0', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '/system/workflow/dowork/bgypsld/print/index.jsp?runId=DEA2FC19-EABA-7A2C-E606-2A54E3FFEA07', 'workflow', null, '', '0', '0', '8EADB678-A646-1E51-3E87-75A547B8AF19', 'admin');
INSERT INTO `flow_run` VALUES ('345', '32858CFA-C500-BC64-3BFE-CA2BB3F5E263', '', '2016-11-21 12:48:46', null, '0', 'admin', '0', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '/system/workflow/dowork/testtab/print/index.jsp?runId=32858CFA-C500-BC64-3BFE-CA2BB3F5E263', 'workflow', null, '', '0', '0', '8EADB678-A646-1E51-3E87-75A547B8AF19', 'admin');
INSERT INTO `flow_run` VALUES ('346', '66C83791-6160-25CC-AD56-78B5F765D4DB', '', '2016-11-21 12:48:54', null, '0', 'admin', '0', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '/system/workflow/dowork/testtab/print/index.jsp?runId=66C83791-6160-25CC-AD56-78B5F765D4DB', 'workflow', null, '', '0', '0', '8EADB678-A646-1E51-3E87-75A547B8AF19', 'admin');
INSERT INTO `flow_run` VALUES ('347', '6633ECD6-F93F-9B08-F56E-D37B9A059AF3', '111', '2016-11-21 12:48:59', null, '0', 'admin', '0', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '/system/workflow/dowork/testtab/print/index.jsp?runId=6633ECD6-F93F-9B08-F56E-D37B9A059AF3', 'workflow', null, '', '0', '0', '8EADB678-A646-1E51-3E87-75A547B8AF19', 'admin');
INSERT INTO `flow_run` VALUES ('348', '075A540D-FB5B-1964-58DB-C04862C15C87', '', '2016-11-21 12:51:24', null, '0', 'admin', '0', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '/system/workflow/dowork/testtab/print/index.jsp?runId=075A540D-FB5B-1964-58DB-C04862C15C87', 'workflow', null, '', '0', '0', '8EADB678-A646-1E51-3E87-75A547B8AF19', 'admin');
INSERT INTO `flow_run` VALUES ('349', '0D24A3E6-328C-FE21-4390-F32D201258FE', 'ddd', '2016-11-21 12:51:27', null, '0', 'admin', '0', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '/system/workflow/dowork/testtab/print/index.jsp?runId=0D24A3E6-328C-FE21-4390-F32D201258FE', 'workflow', null, '', '0', '0', '8EADB678-A646-1E51-3E87-75A547B8AF19', 'admin');
INSERT INTO `flow_run` VALUES ('350', 'D455D952-EDAA-8260-49D3-28FA3FF6DC44', '超级管理员-办公用品申请-2016年11月21日', '2016-11-21 12:51:38', null, '0', 'admin', '0', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '/system/workflow/dowork/bgypsld/print/index.jsp?runId=D455D952-EDAA-8260-49D3-28FA3FF6DC44', 'workflow', null, '', '0', '0', '8EADB678-A646-1E51-3E87-75A547B8AF19', 'admin');
INSERT INTO `flow_run` VALUES ('351', '76246F59-3945-443F-84CE-15BF5BC26FCD', '超级管理员-办公用品申请-2017年01月23日', '2017-01-23 17:05:15', null, '0', 'admin', '0', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '/system/workflow/dowork/bgypsld/print/index.jsp?runId=76246F59-3945-443F-84CE-15BF5BC26FCD', 'workflow', null, '', '0', '0', '8EADB678-A646-1E51-3E87-75A547B8AF19', 'admin');

-- ----------------------------
-- Table structure for flow_run_prcs
-- ----------------------------
DROP TABLE IF EXISTS `flow_run_prcs`;
CREATE TABLE `flow_run_prcs` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RUN_ID` varchar(50) DEFAULT NULL,
  `FLOW_ID` varchar(50) DEFAULT NULL,
  `RUN_PRCS_ID` int(3) DEFAULT NULL COMMENT '流程实际走的步骤号',
  `PRCS_ID` int(3) DEFAULT NULL COMMENT '系统设计的步骤号',
  `OP_FLAG` varchar(2) DEFAULT '0' COMMENT '0允许转交一步，1直接完成',
  `TABLE_NAME` varchar(20) DEFAULT NULL,
  `PRCS_NAME` varchar(40) DEFAULT NULL,
  `ACCOUNT_ID` varchar(20) DEFAULT NULL,
  `USER_NAME` varchar(20) DEFAULT NULL,
  `DEPT_ID` varchar(50) DEFAULT NULL,
  `USER_PRIV_ID` varchar(50) DEFAULT NULL,
  `LEAD_ID` varchar(50) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `END_TIME` varchar(20) DEFAULT NULL,
  `PASS` varchar(2) DEFAULT '0',
  `IDEA` text,
  `STATUS` varchar(2) DEFAULT '0' COMMENT '步骤状态',
  `IDEA_TEXT` varchar(2000) DEFAULT NULL,
  `FOLLOW` varchar(2) DEFAULT NULL,
  `ATTACH_ID` text,
  `TRANSFER_USER` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=742 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flow_run_prcs
-- ----------------------------
INSERT INTO `flow_run_prcs` VALUES ('75', '1F913C4E-6F62-3E86-9E27-7574A866A67A', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('71', '0C6C6884-406C-4A98-4D78-2DBC23E8742C', '87F22A0B-6B39-5D34-3E3C-0B11D6A0595D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('72', 'D082A506-034B-FF9A-EF61-226CE6CCE662', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:29', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('73', 'EE183214-C603-D880-EA56-4C9D57FF31B6', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('74', '31412AD6-1E36-B28E-B361-00F23F5F326A', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('138', 'E2F87B56-80BD-5E58-EEE8-1FEF30DC3438', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:35', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('137', '3DA5116D-39AB-E90C-883D-F4B31F06EB22', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:35', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('136', 'C051616A-1F91-84D8-8969-88C0F1475358', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:35', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('135', '78791436-A6DA-DC26-7550-7E16DC714762', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:35', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('134', 'ED3CF7B6-9844-C6BE-3819-4ED63225F37F', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:35', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('133', '7AB76FF3-D6FE-0FFE-0C1E-299D0F8DFBB3', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:35', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('130', '1904E680-CB8F-3A57-64C6-ED19490C6532', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('131', '409BBB3A-2D9D-7577-1CAE-944BA57221BF', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('132', '10F33680-19CF-ACA7-A562-6B5851D9B24E', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('76', '766F5AFF-9DFF-0905-33B4-4F9AEFB303EE', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('77', '38A9A2AA-DD56-0161-F27B-68001D72EE50', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('78', '7F56ECCE-47B3-4A61-2471-D8F9753AF80B', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('79', 'F0C5045D-258B-C50A-993D-C2EC761BE048', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('80', '8E13D322-6678-F51A-035E-C0AF0A805E7E', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('81', '27C5BCD9-2417-A538-FCF5-DBBD0B9A6382', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('82', '1C846A74-1F63-155A-5E9B-01026BF3E837', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('83', 'CAE4F308-427A-6429-263B-2FAC7FA298D4', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('84', '47FED1D8-C950-6BB8-6221-1270B3C3C698', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('85', '654F7E4C-0AB8-2A81-4375-42D696CBD3CA', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('86', '27350C23-AD4B-C5A9-11CB-84F762FC4F9A', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('87', '497570DA-1D1D-9299-A922-EC2A270B040F', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('88', '4F0D16CC-345A-B83B-5937-270EE2A80543', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('89', 'CBC1F12A-8523-52E4-4F4C-A9F07950F841', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('90', '9EE85ADC-2DE2-BD12-BE1D-7E5DBF3DE078', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('91', 'C7DC2B6A-E205-1BEF-5559-FED4EB739DA5', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('92', 'F6AE2CE5-E624-FE8E-39D1-C80EA76DFEDE', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('93', 'AD945E7E-CE68-76E1-B846-25EFFCE68B53', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('94', 'B4784068-E415-CA87-43A3-14571CDCEED6', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('95', '6FA9B66B-87E9-6DB0-8589-9D7560880825', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('96', '2C0774DE-BBE7-BF7F-1BBF-CCAEC5799C38', 'A7C381E9-7C95-2E8E-4A84-EF3DAB4B078D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:31:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('129', '6AF58ED6-4AEE-EC90-DA35-5374F90719AC', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('128', '53200090-2A35-F34E-EA67-9F296BBACEBC', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('127', 'F2537097-6D9A-6AEF-B376-ABC1562F0903', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('126', '32931538-7786-89EC-A30B-1487048957D8', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('125', 'F7126A45-EA1F-97FF-0E08-DF8C5300DDBB', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('124', '40FCCABD-783E-5954-8BDD-E52C5B20107B', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('123', '11AE4461-0A36-FA2E-C16B-C299D26A9B9A', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('122', '38DA9D46-FC0F-CF59-9084-36042E311A88', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('121', '00769E15-7209-ADB8-2FBA-C504A07CCDF5', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('120', '65E7CB50-72B2-0BF6-61F3-8F9AB5A47FAB', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('119', '7E4CD819-E73C-418F-1F38-5F1E1992FB01', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('118', '009A1AD6-579B-AD8A-3078-9CA4F5867759', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:12', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('117', '7BEF7656-4858-82FF-40E4-B69533D4DF21', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:10', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('116', '919F068B-E2E6-D84D-A70D-CAD14AC2156F', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('115', 'FAD94BC8-8DEA-8895-B82B-A110CB376E26', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:04', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('114', 'E9679890-9F28-F010-7F6E-0B93F2D61C1C', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:02', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('139', '282C5BDC-C84D-9B0B-7431-E5C3CB669EEE', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:36', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('140', '20EE2A50-0155-C739-01AB-54447FF7898E', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:36', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('141', '31395A2A-3A74-5303-D4A9-045A7526BBF7', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:36', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('142', '203610F0-B53E-748A-53CE-833EC71CA659', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:36', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('143', '6832E3DD-78DF-F0EE-7A9C-994667E2DFF3', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:36', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('144', '2A526C95-195B-D98A-567D-CB5121C44C47', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:36', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('145', '968430C5-DED7-D652-06A5-5DD84C10463F', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:37', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('146', 'BF8C21F4-AA60-4E51-A89B-DE46523D5AF1', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:34:37', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('147', '2958BE96-0701-2C52-B87C-13272FB2CB8C', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:08', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('148', '7B335EDC-C56F-160D-7EC0-5ED4499B6F29', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:09', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('149', 'FEFCC0BC-F227-35A4-B026-A3CA3F938CE6', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:09', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('150', '8BAC4E0A-8CE9-7DBE-C6DE-C83C5B8E0445', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:09', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('151', '29C70057-53DE-9D95-AE36-E1749E4F8E7D', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:09', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('152', '59D1870D-59E5-D86F-D2BA-9A9FD610877A', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:09', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('153', '14FF75B2-1451-0249-4F66-4BE86E17A4F1', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:10', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('154', '44459676-09D2-7F1C-FE40-F59D98477268', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('155', 'F96DD261-53D2-923D-9F8C-AE782B947892', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('156', '1D5E4A3F-2339-C340-7E46-43192304B4DD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('157', '15318B70-4C3E-442C-E05C-E141CAB8206F', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('158', '545A1DFE-2532-ED5E-7E8E-DEDD2DBEAEA6', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('159', 'C790E834-D0B3-A517-31F9-0B5D38FA53D1', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:35:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('160', 'CEF8A334-03BB-D592-C377-D603C47768F7', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-09-29 16:53:50', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('161', 'FD9F2C84-F8CD-E61A-E394-AB1FF8660202', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 10:40:12', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('162', 'CDCD724D-5E09-D3EE-33E5-9C2DC00150B1', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 10:52:17', '2015-10-10 14:31:42', '1', '1', '1', 'ASDFADSFASDFASFASDF', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('163', 'B1960021-B3AE-9337-EFF2-A5763D4A3AD0', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 10:57:08', '2015-10-10 11:33:34', '1', '1', '1', 'wretwrtwertwert', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('164', 'A63C26DC-B7F8-9CC4-9F44-919C31812FFF', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:05:28', '2015-10-10 11:05:46', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('165', 'A63C26DC-B7F8-9CC4-9F44-919C31812FFF', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:05:46', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('166', '8045D83B-1F9B-1B60-7F2D-2D1A513FA227', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:05:47', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('167', '694CE33B-E710-270F-DC92-28C359F02E0F', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:06:13', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('168', 'D3E12C62-6041-2CA7-BBD3-4CA56A418BB3', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:07:13', '2015-10-10 11:17:03', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('169', '247A5C39-F712-C5CA-0252-15515AFCF849', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:10:02', '2015-10-10 11:16:13', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('170', '247A5C39-F712-C5CA-0252-15515AFCF849', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:16:13', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('171', 'D3E12C62-6041-2CA7-BBD3-4CA56A418BB3', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:17:03', null, '0', null, '1', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('172', '7D94CECE-B791-EB00-4420-E73AEBD42072', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:24:27', '2015-10-10 11:24:40', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('173', '7D94CECE-B791-EB00-4420-E73AEBD42072', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:24:40', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('174', 'B25773B3-B462-22AB-4FD0-84D8DAB789D7', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:31:42', '2015-10-10 11:31:58', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('175', 'B25773B3-B462-22AB-4FD0-84D8DAB789D7', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:31:58', null, '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('176', 'B1960021-B3AE-9337-EFF2-A5763D4A3AD0', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 11:33:34', null, '0', '1', '1', 'rgsfgsgsfdgrwradsfa', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('177', 'B3F0D498-0597-E388-A6D4-02A01139DAFD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 14:27:07', '2015-10-10 14:29:29', '1', '1', '1', '是发放史蒂夫vsdferggbdsfvsfv示范区热风管上个网天后宫', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('178', 'B3F0D498-0597-E388-A6D4-02A01139DAFD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 14:29:29', '2015-10-10 14:30:37', '0', '1', '1', '企鹅夫妻付全额发的是发的发的发的说法发的是发的身份冯其福', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('179', 'B3F0D498-0597-E388-A6D4-02A01139DAFD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '3', '3', '0', 'QQQQQ', '普通步骤3', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 14:30:37', '2015-10-10 17:42:29', '0', '1', '1', '安然噶人给', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('180', 'CDCD724D-5E09-D3EE-33E5-9C2DC00150B1', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 14:31:42', '2015-10-10 16:47:20', '0', '1', '1', '深入骨髓弗格森的风格生日歌深入骨髓乳沟日啊发发日非法钱二分俺的沙发的沙发的说法', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('181', 'CDCD724D-5E09-D3EE-33E5-9C2DC00150B1', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '3', '3', '0', 'QQQQQ', '普通步骤3', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 16:47:20', '2015-10-10 17:35:00', '0', '1', '1', '日日日日日日日日日日日日日日日日日日日日日日日', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('182', 'CDCD724D-5E09-D3EE-33E5-9C2DC00150B1', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '4', '4', '0', 'QQQQQ', '普通步骤4', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 17:35:00', null, '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('183', 'B3F0D498-0597-E388-A6D4-02A01139DAFD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '3', '3', '1', 'QQQQQ', '普通步骤3', '', '', '', '', '', '2015-10-10 17:41:42', '', '', '', '1', '', null, '', null, null);
INSERT INTO `flow_run_prcs` VALUES ('184', 'B3F0D498-0597-E388-A6D4-02A01139DAFD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '3', '3', '1', 'QQQQQ', '普通步骤3', 'weipeng', '魏鹏', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '4B389256-461A-7ABC-CBC5-BD79847E3BCD', '', '2015-10-10 17:42:19', '', '', '', '1', '', null, '', null, null);
INSERT INTO `flow_run_prcs` VALUES ('185', 'B3F0D498-0597-E388-A6D4-02A01139DAFD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '4', '4', '0', 'QQQQQ', '普通步骤4', 'weipeng', '魏鹏', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '4B389256-461A-7ABC-CBC5-BD79847E3BCD', '', '2015-10-10 17:42:29', null, '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('186', '755A3090-F1A5-2C55-4882-8CD249AA45CE', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 19:30:22', '2015-10-10 19:30:46', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('187', '755A3090-F1A5-2C55-4882-8CD249AA45CE', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-10 19:30:46', '2015-10-12 19:48:08', '0', '1', '1', 'sdD', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('188', '0D1D0C5E-65A8-562D-141B-EAAB6D3E45BE', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-12 15:54:56', '2015-10-28 11:28:13', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('189', 'EEC990D9-586B-D328-1081-672C830E1D72', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-12 15:58:59', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('190', '5FE17931-FF5A-C912-8273-791B54BE33D5', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-12 15:59:30', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('191', '672710CB-28E0-B9F8-07EB-DAA32360CD89', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-12 16:09:44', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('192', '9CFF1BAD-6981-F8C4-E4E0-33D87D9684DD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-12 16:11:41', '2015-10-20 17:27:06', '1', '1', '1', '科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('193', 'F237B335-1A89-0C6E-C2D2-C15810C7B67E', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-12 16:22:21', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('194', '755A3090-F1A5-2C55-4882-8CD249AA45CE', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '1', 'QQQQQ', '普通步骤2', 'zhaoyang', '赵阳', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '9BB768A9-35AC-A456-9078-9885E43E496D', 'admin', '2015-10-12 19:47:56', '', '', '1', '', '是打发打发地方', null, '', null, null);
INSERT INTO `flow_run_prcs` VALUES ('195', '755A3090-F1A5-2C55-4882-8CD249AA45CE', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '3', '3', '0', 'QQQQQ', '普通步骤3', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-12 19:48:08', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('196', '3908F872-CF20-DAE4-C0F2-2B621C113517', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-14 11:41:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('197', '364DDE35-01FB-D514-D6B2-7DCF94BEBC7E', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-14 11:41:09', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('198', 'FE6B2182-5B31-7043-DD5E-8A1ED4D5CAD3', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', 'zhaoyang', '2015-10-16 14:39:38', '2015-10-16 14:40:34', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('199', 'FE6B2182-5B31-7043-DD5E-8A1ED4D5CAD3', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'liuyifei', '刘亦菲', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1408244A-2653-52D7-4853-3F48EC302298', 'zhaowei', '2015-10-16 14:40:34', '2015-10-16 15:36:48', '0', '1', '1', 'aefaefqefqewf', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('200', '304B054B-A09E-179D-8040-05B95F01449D', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', 'zhaoyang', '2015-10-16 14:49:49', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('201', 'FE6B2182-5B31-7043-DD5E-8A1ED4D5CAD3', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '3', '3', '0', 'QQQQQ', '普通步骤3', 'zhaoyun', '赵云', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '689AE679-437E-4028-2784-9AD950516122', '', '2015-10-16 15:36:48', '2015-10-16 15:37:29', '0', '1', '1', '非常棒', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('202', 'FE6B2182-5B31-7043-DD5E-8A1ED4D5CAD3', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '4', '4', '0', 'QQQQQ', '普通步骤4', 'zhugeliang', '诸葛亮', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', 'E07C070C-C131-7778-0EC9-91E85796F682', '', '2015-10-16 15:37:29', null, '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('203', 'E228C64E-1B8F-7136-0BE3-1FFDBFC8B839', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', 'zhaoyang', '2015-10-16 15:45:52', '2015-10-16 15:46:59', '1', '1', '1', '色噶尔噶', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('204', 'E228C64E-1B8F-7136-0BE3-1FFDBFC8B839', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'gaoyuanyuan', '高圆圆', '74B8A1F7-A5B6-A425-10B0-096B53961713', 'A55D5899-D853-58AF-2F54-E8995C64F7F9', 'zhaoyang', '2015-10-16 15:46:59', null, '0', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('205', '7477E718-F29A-DFF9-8182-D5255ED6615A', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-19 10:25:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('206', 'C1A62A07-1A80-F22E-6115-A886BA87EE74', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-19 10:26:01', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('207', '40903193-B799-0BD9-2DAC-626571D655FC', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2015-10-19 10:49:48', '2015-10-19 10:50:13', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('208', '40903193-B799-0BD9-2DAC-626571D655FC', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'gaoyuanyuan', '高圆圆', '74B8A1F7-A5B6-A425-10B0-096B53961713', 'A55D5899-D853-58AF-2F54-E8995C64F7F9', '', '2015-10-19 10:50:13', '2015-10-19 10:55:06', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('209', '40903193-B799-0BD9-2DAC-626571D655FC', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '3', '3', '0', 'QQQQQ', '普通步骤3', 'zhaoyun', '赵云', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '689AE679-437E-4028-2784-9AD950516122', '', '2015-10-19 10:55:06', null, '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('210', 'B28F1207-3CC2-3228-8B7A-28B3D5CFF025', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-20 14:28:52', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('211', '0B111EFF-926D-B7D1-4700-BF28444DD21F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-20 14:31:13', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('212', '7D67C3FC-B2AC-1E4E-511F-A355C8574A97', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-20 14:31:38', '2015-10-20 17:48:41', '1', '1', '1', 'cvcv顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('213', '4AA476C5-779B-3E87-6EA7-492E910F9E10', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-20 14:32:23', '2015-10-20 14:34:26', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('214', '4AA476C5-779B-3E87-6EA7-492E910F9E10', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '2', '2', '0', 'qjsqd', '部门经理意见', 'zhaowei', '赵薇', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1408244A-2653-52D7-4853-3F48EC302298', '', '2015-10-20 14:34:26', '2015-10-20 14:35:17', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('215', '4AA476C5-779B-3E87-6EA7-492E910F9E10', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '3', '3', '0', 'qjsqd', '行政总监意见', 'liushaoquan', '刘绍全', '30CA0AAA-DAD3-E00A-3499-235C83F2B805', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2015-10-20 14:35:17', '2015-10-20 14:36:30', '0', '1', '1', '很客观九个', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('216', '4AA476C5-779B-3E87-6EA7-492E910F9E10', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '4', '4', '0', 'qjsqd', '副总意见', 'zhugeliang', '诸葛亮', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', 'E07C070C-C131-7778-0EC9-91E85796F682', 'guanyu', '2015-10-20 14:36:30', '2015-10-20 14:39:58', '0', '1', '1', 'hgchgcvjhv,jhvjh,vb', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('217', '4AA476C5-779B-3E87-6EA7-492E910F9E10', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '5', '5', '0', 'qjsqd', '董事长意见', 'zhaoyang', '赵阳', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '9BB768A9-35AC-A456-9078-9885E43E496D', '', '2015-10-20 14:39:58', null, '0', '1', '1', '爱的发的复制到v字现场v字', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('218', '541C3A17-1A1F-4217-D3BA-F8360BA86B1F', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-20 14:52:07', '2015-10-20 14:52:31', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('219', '541C3A17-1A1F-4217-D3BA-F8360BA86B1F', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '2', '2', '0', 'usecar', '普通步骤2', 'liuyifei', '刘亦菲', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1408244A-2653-52D7-4853-3F48EC302298', '', '2015-10-20 14:52:31', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('220', '28D9A53F-8241-07E5-70C6-0E7B83CFB6DD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-20 14:54:06', '2015-10-20 14:54:29', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('221', '28D9A53F-8241-07E5-70C6-0E7B83CFB6DD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-20 14:54:29', null, '0', null, '1', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('222', '9CFF1BAD-6981-F8C4-E4E0-33D87D9684DD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '2', '2', '0', 'QQQQQ', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-20 17:27:06', '2015-10-20 17:27:45', '0', '0', '1', '科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她科技园预计活了金河田天天没kid有读后感jhkgji8t想法告诉她', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('223', '9CFF1BAD-6981-F8C4-E4E0-33D87D9684DD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '3', '3', '0', 'QQQQQ', '普通步骤3', 'zhaoyun', '赵云', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '689AE679-437E-4028-2784-9AD950516122', 'zhugeliang', '2015-10-20 17:27:45', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('224', '7D67C3FC-B2AC-1E4E-511F-A355C8574A97', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '2', '2', '0', 'qjsqd', '部门经理意见', 'zhaowei', '赵薇', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1408244A-2653-52D7-4853-3F48EC302298', '', '2015-10-20 17:48:41', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('225', '927BAED9-9B87-1607-7AAE-24A12CC756D6', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-21 10:21:20', '2015-10-21 10:22:16', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('226', '927BAED9-9B87-1607-7AAE-24A12CC756D6', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '2', '2', '0', 'usecar', '普通步骤2', 'zhaowei', '赵薇', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1408244A-2653-52D7-4853-3F48EC302298', '', '2015-10-21 10:22:16', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('227', '1A736E2D-AACF-57E0-F2BF-8612E15E76D0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'zhugeliang', '诸葛亮', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', 'E07C070C-C131-7778-0EC9-91E85796F682', 'guanyu', '2015-10-21 14:05:42', null, '1', '1', '0', '爱的撒旦法师', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('228', 'DEBBA68A-AAC6-D9D6-D8FA-2BB626054C4B', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'zhaoyang', '赵阳', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '9BB768A9-35AC-A456-9078-9885E43E496D', '', '2015-10-21 14:10:28', null, '1', '1', '0', '爱的是发送到发送到', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('229', '61BF9319-69E8-A1FA-D0CA-FECE45238CEC', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'guanyu', '关羽', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '7DDBA6F7-CED5-0819-9890-D37340EA51F3', 'zhaoyang', '2015-10-21 14:12:25', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('230', '963A5743-37AC-A186-8698-1E1537574BAA', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-21 14:36:02', null, '1', '1', '0', '灌灌灌灌灌哥哥哥哥哥哥', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('231', '9E410BEC-2E6E-5DC6-F9C9-FD48615640B2', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-21 14:37:50', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('232', 'D7AD5A4D-7637-D95E-27D2-BC0128851879', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-21 14:39:19', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('233', '5E7FEB4B-A248-D23C-1903-725EA42D1221', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2015-10-21 14:41:44', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('234', 'E2909FA1-4C80-5A82-F4CF-731D94D54169', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2015-10-21 14:43:55', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('235', '3C70C226-0D81-2F09-775C-A8F0959EF4B2', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2015-10-21 14:45:02', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('236', '4B8D3BEB-4DEE-496B-38B1-3321CFD16AA0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2015-10-21 14:45:59', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('237', 'FFFF3427-F37C-017A-3F91-2425BA82213A', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-21 14:47:19', '2015-10-21 14:47:49', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('238', 'FFFF3427-F37C-017A-3F91-2425BA82213A', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '2', '2', '0', 'usecar', '普通步骤2', 'zhaowei', '赵薇', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1408244A-2653-52D7-4853-3F48EC302298', '', '2015-10-21 14:47:49', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('239', '356D84A8-6279-62DE-3B0B-55E1B967F512', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-21 14:47:58', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('240', '9F0E3C56-BACB-831E-BE15-192968A24B08', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2015-10-21 15:02:58', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('241', 'EDC01DF8-CAE7-635D-D8E5-51C23C204DD6', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-22 10:52:39', null, '1', '1', '0', '所发生的弗格森的风格', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('242', '9AEE23F8-33D7-5745-3419-4B6FB545D66F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'zhaoyang', '赵阳', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '9BB768A9-35AC-A456-9078-9885E43E496D', '', '2015-10-22 10:56:25', null, '1', '1', '0', '爱的发的发地方', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('243', '805DD286-C36C-09E6-6964-C515E8C726E7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'yangying', '杨颖', '74B8A1F7-A5B6-A425-10B0-096B53961713', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2015-10-22 10:57:00', null, '1', '1', '1', 'adfadsfadfasfadsfadsfadf', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('244', '69F7123D-C88A-B266-92ED-0405D899F2D0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:13:29', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('245', 'D4C7A79F-A18B-1A60-AB2F-2D7E6CA80EA7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:29:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('246', '752A58C8-8392-30EC-2FD5-7DC7CABA823A', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:29:29', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('247', '7823A88B-338F-986C-9004-ED645980DB94', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:30:06', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('248', '8AEB6794-7E1A-E689-7EEE-0B7AEE4CB86B', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:30:13', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('249', 'A4D5A1C5-BCD8-664D-AEAA-D48B0606CE79', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:30:22', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('250', '76DE928B-A105-0893-483F-8B7E7B7A810E', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:31:02', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('251', '65CE0CD8-BFA1-012A-A7B0-92E8487AAA63', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:32:38', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('252', '7020FDDE-4A3E-4CFB-8D4A-C554221E04E9', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:39:11', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('253', 'E88F5EFD-6EDB-ED9F-D717-FEF85568EABC', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 14:39:32', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('254', 'AE5F9A57-976B-6819-85E7-6E82C394D3BB', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 15:27:19', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('255', '999932A6-88CF-A57D-66A0-BBBD6649A1C3', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 15:28:33', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('256', '8431C13D-6A65-7C16-8833-58CB29B2CE2A', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 15:29:51', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('257', 'AAF61516-6DA3-B46B-DF22-EBB542FC1DD5', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 15:30:27', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('258', 'C22313C3-AAA1-B106-1C44-53286A171A7A', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 15:30:38', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('259', '117BF047-EEFB-B1AE-695F-726DB3CC4E4B', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 15:31:08', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('260', '30A397B7-E565-4611-7E60-3DD8DE147E11', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 15:32:37', null, '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('261', 'B43D7E08-7B84-71F5-B0B5-8C91D2F61788', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 15:32:49', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('262', '40CB5ED0-7A41-FF0C-D389-1208B2DA458E', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 16:00:24', '2015-10-26 16:01:10', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('263', '40CB5ED0-7A41-FF0C-D389-1208B2DA458E', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '2', '2', '0', 'qjsqd', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 16:01:10', '2015-10-26 16:01:47', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('264', '40CB5ED0-7A41-FF0C-D389-1208B2DA458E', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '3', '3', '0', 'qjsqd', '普通步骤3', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 16:01:47', '2015-10-26 16:02:31', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('265', '40CB5ED0-7A41-FF0C-D389-1208B2DA458E', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '4', '4', '0', 'qjsqd', '普通步骤4', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 16:02:31', '2015-10-26 16:04:08', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('266', '40CB5ED0-7A41-FF0C-D389-1208B2DA458E', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '5', '5', '0', 'qjsqd', '普通步骤5', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-26 16:04:08', null, '0', null, '1', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('267', '0D1D0C5E-65A8-562D-141B-EAAB6D3E45BE', 'A436C211-38C8-10E8-6569-020B45D7AD13', '2', '2', '0', 'BPMTEST', '普通步骤2', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-28 11:28:13', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('268', 'D86C1213-7553-496D-726A-ACBBF653CCF9', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-28 15:15:02', null, '1', null, '1', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('269', '5A59149B-EA14-74EE-B5CF-2223A55D51C2', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-29 20:29:53', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('270', 'C6DE852F-9A9D-3F5E-A7CA-5EF238E9FEF8', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-29 20:32:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('271', 'E1546135-67BE-C9C1-6855-933750A4F0D2', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-10-29 20:32:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('272', 'C44C54BB-3369-116B-1FEA-2BF5B6711202', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', 'Super管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-02 11:35:05', '2015-11-02 11:36:06', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('273', 'C44C54BB-3369-116B-1FEA-2BF5B6711202', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '2', '2', '0', 'test', '普通步骤2', 'zhaowei', '赵薇', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1408244A-2653-52D7-4853-3F48EC302298', '', '2015-11-02 11:36:06', '2015-11-02 11:39:35', '0', '1', '1', '同意', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('274', 'C44C54BB-3369-116B-1FEA-2BF5B6711202', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '3', '3', '0', 'test', '普通步骤3', 'liushaoquan', '刘绍全', '30CA0AAA-DAD3-E00A-3499-235C83F2B805', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2015-11-02 11:39:35', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('275', '0FFE7B43-2561-3E26-DF92-E4A90C544834', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-02 13:57:13', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('276', '1192023D-83E5-699C-528E-29AEE9D5AB59', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-02 13:58:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('277', '940CD263-84CB-52AF-3567-BAD3FF1D53BD', 'DC1F031B-C660-13C3-068A-7B180F323811', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-02 14:04:04', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('278', '906F6BB6-3CB4-C781-5580-3D3ECA092D59', 'DC1F031B-C660-13C3-068A-7B180F323811', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-02 14:31:35', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('279', 'D604D561-5349-EC3A-365D-AC76FFEF2973', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-02 14:40:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('280', '951C551E-68B0-C30F-442C-DF62EFEED155', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-02 14:41:08', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('281', 'B774A56E-78A8-213A-0D01-C2FD74701F7B', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-02 14:41:12', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('282', '6002E55E-76D8-B2EB-509B-8E04FE412C71', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-02 14:41:46', '2015-11-02 14:42:18', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('283', '6002E55E-76D8-B2EB-509B-8E04FE412C71', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '2', '2', '0', 'test', '普通步骤2', 'zhaowei', '赵薇', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1408244A-2653-52D7-4853-3F48EC302298', '', '2015-11-02 14:42:18', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('284', 'DA94FC3E-A387-A74C-9261-3EE8CA20A63C', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-05 13:28:29', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('285', 'C9BB6A1D-618E-D1D7-F084-B89189C6AD5F', 'DC1F031B-C660-13C3-068A-7B180F323811', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 10:01:31', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('286', '86DC8C1B-2E7F-7A31-A439-9D408DA6F21B', 'DC1F031B-C660-13C3-068A-7B180F323811', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 10:04:08', '2015-11-06 10:14:03', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('287', '86DC8C1B-2E7F-7A31-A439-9D408DA6F21B', 'DC1F031B-C660-13C3-068A-7B180F323811', '2', '2', '0', 'test', '普通步骤2', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 10:14:03', '2015-11-06 10:16:38', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('288', '86DC8C1B-2E7F-7A31-A439-9D408DA6F21B', 'DC1F031B-C660-13C3-068A-7B180F323811', '3', '3', '0', 'test', '普通步骤3', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 10:16:38', null, '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('289', '6C7C59DF-ACBD-C945-E148-054B9EE6144C', 'DC1F031B-C660-13C3-068A-7B180F323811', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 10:39:53', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('290', '4BFCF930-9AEC-E6BB-0C63-1A07994CDACC', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', '钱海', '钱海', '3298B539-E64C-5941-1158-4F285220C0C1', '1408244A-2653-52D7-4853-3F48EC302298', '', '2015-11-06 11:48:51', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('291', 'C2B38FBC-D4CB-6189-CF64-714858DBF354', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 14:39:47', '2015-11-06 14:40:27', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('292', 'C2B38FBC-D4CB-6189-CF64-714858DBF354', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '2', '2', '0', 'qjsqd', '普通步骤2', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 14:40:27', '2015-11-06 14:41:17', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('293', 'C2B38FBC-D4CB-6189-CF64-714858DBF354', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '3', '3', '0', 'qjsqd', '普通步骤3', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 14:41:17', '2015-11-06 15:08:44', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('294', '27DD8B8C-E085-31A2-9918-BF381F530BFA', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'FB6E0084-7A78-9C2A-9BA3-3522C6967002', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 14:41:55', '2015-11-06 14:42:27', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('295', '27DD8B8C-E085-31A2-9918-BF381F530BFA', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '2', '2', '0', 'qjsqd', '普通步骤2', 'zhaoyun', '赵云', '93CC5B14-7D22-FAA3-EECB-5ED97F4691B7', '689AE679-437E-4028-2784-9AD950516122', 'zhugeliang', '2015-11-06 14:42:27', null, '0', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('296', 'C2B38FBC-D4CB-6189-CF64-714858DBF354', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '4', '4', '0', 'qjsqd', '普通步骤4', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 15:08:44', '2015-11-10 11:31:58', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('297', '10745920-00F5-3F48-B4B1-724A572C6788', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 15:08:52', '2015-11-06 15:13:02', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('298', '10745920-00F5-3F48-B4B1-724A572C6788', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '2', '2', '0', 'qjsqd', '普通步骤2', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-06 15:13:02', '2015-11-10 11:31:28', '0', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('299', '10745920-00F5-3F48-B4B1-724A572C6788', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '3', '4', '0', 'qjsqd', '普通步骤4', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-10 11:31:28', null, '0', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('300', 'C2B38FBC-D4CB-6189-CF64-714858DBF354', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '5', '5', '0', 'qjsqd', '普通步骤5', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-10 11:31:58', null, '0', '1', '1', 'aethasrthnsartnjszrym', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('301', '384E8AE3-9B76-F7B5-1BB3-D5530CCB92EF', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', '钱海', '钱海', '3298B539-E64C-5941-1158-4F285220C0C1', '1408244A-2653-52D7-4853-3F48EC302298', '', '2015-11-10 16:49:46', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('302', '7ED4611E-BE5C-B33E-273A-DA69C8035D6A', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-17 14:32:36', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('303', '79E4B868-193B-4D2D-94CF-6247EAC0D16C', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-17 14:32:40', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('304', 'DC0C8EA5-5BF6-CEF1-9FDA-736F10A7ECC8', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-17 14:32:44', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('305', '77DA57CF-C232-E7A0-9763-9E4350793A2B', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-17 14:33:07', '2015-11-17 14:35:20', '1', '1', '1', '3333', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('306', 'C494F884-4EB6-FB6B-C3E4-7B6C5831FAA6', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-17 14:44:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('307', '0A1E44A3-E44C-F67D-7D6E-C1F22F9F9A5E', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-17 14:44:55', '2015-11-17 14:49:43', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('308', 'AD160C7E-6B87-40BE-FA2A-AC147A9D224F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-17 16:17:33', '2015-11-17 16:18:01', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('309', 'EB9289F8-0F1A-19BF-616A-AF94833E34F2', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('310', 'BDFE6176-DC25-2CF4-9D39-4747A6E41F79', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('311', 'E888354C-9339-E2F6-EB90-2CBC1DF79F30', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('312', 'D11AD13B-8669-0E7B-1A2F-CD5CACE20BBF', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('313', '177C4C0D-34D9-AC02-DE09-B6193531F91F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:35', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('314', '2B4B2107-73F5-B6F0-ABA3-9BA9057C9D58', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:36', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('315', '14D541EF-6F97-552D-6AF6-581080A3E621', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:37', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('316', '61A82067-19EC-94BD-81B5-9B0061E9DB9A', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:37', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('317', '092B9ABA-31C9-D088-74C8-CBEE51C6999A', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:38', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('318', '9DB56B58-F4B6-AFC7-9154-39876D648FFE', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:38', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('319', '016522E2-FD6C-D06B-89F3-C7AAB274D9E1', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:38', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('320', 'E4D9F514-62E5-8D6C-F6D2-AE4A8A33E697', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:38', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('321', '50D1F75E-D01B-4F81-9993-578C9A38D90F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:38', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('322', '9784E47A-D787-6B9A-2FC3-6835F7B16D2B', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:38', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('323', '5990B560-ACF2-86FE-3BBE-547FBE8524C4', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:39', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('324', '9CC13840-1F68-3217-D24C-CCEEBDDA8453', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:39', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('325', 'FCFE86C3-DD06-3DFC-F7A0-85D7E82D3B80', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:40', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('326', 'B339BCC8-F4BD-C68C-CCDA-993515380997', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:57', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('327', '5D31D146-2EEA-AF8D-9030-D93AD55228CE', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:14:59', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('328', '2326DA7E-B6A5-7BC1-10D7-6191A375A56C', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('329', '5423253D-7002-47CA-FF99-9EF27DA99185', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('330', '0131D0B7-C975-85DD-3A8D-8204673CEBA7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('331', '02B1A006-693A-D509-1E79-7CDE27DB584D', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('332', '17BE2745-F804-44EE-1907-872A1F5B6C50', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('333', '81F88436-63DD-5F5D-45C8-A87B09655C75', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('334', 'EC3CFA57-0357-C4EC-B9B6-2AC9042AF8D0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:21', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('335', 'D9950E8B-20DB-3CE6-593F-ADC66A0C8B75', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:21', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('336', '83D7F6E8-26E7-A874-BEB9-FEF8B290BD9B', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:22', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('337', '72969661-28AC-3EF2-F3A0-66B936E73A36', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:22', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('338', 'D1284DBC-2A04-8CF2-FB1E-FE4D96F09AE6', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:22', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('339', '2B440D2F-A86E-023F-02E0-BBF6EC793827', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:22', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('340', 'E453902C-B2C8-050A-C3B3-41C15AC4F883', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:22', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('341', '778BBAFE-A14C-F1C9-F4A7-33B75258FC18', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:22', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('342', '5EA3D69D-67F1-19FD-D368-8E9FFB162017', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:23', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('343', 'F688790A-1E8D-1CE6-7C46-CD8743A812EE', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:23', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('344', 'DD8DC1F5-1039-EAA5-4EC5-F50830EA0BE5', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:23', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('345', '41FBBCDE-8254-13D1-64AB-2EF53BFF5C7E', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('346', '35CFACA9-A9A4-C8CD-F655-735023DBEF4C', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('347', 'E2490A72-C461-8CE6-04FD-B7EC0BF5F4E3', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('348', '68C0163C-A330-48DE-3E80-3B04F26D9466', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('349', '72B0D2A9-88CC-3328-2D13-D73165D35637', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('350', '7E813714-C25C-1E0C-49A0-048501CFD755', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('351', '32833049-B7D6-F0E3-5599-933C1A8A4C35', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('352', '3E1FE800-F838-D6B6-407F-8866E269BE06', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('353', '95A3FBF5-1ECB-3472-476D-FFF0CFCC2BDF', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('354', '6C81C62D-5C8B-0752-DDF7-904AAB3F1538', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('355', '764DEC54-1D7D-76D5-C4A0-D4E4DD55865F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('356', '74DF5A9C-8CDA-6CAB-1603-B3A955D9CDD7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('357', 'A52A3442-C497-BB87-FA2F-1A4B28262931', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('358', '694A9D3A-2D42-B191-0F94-07AD8DA8D0E9', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('359', '95884F57-896E-5C51-BC52-716FC39DA1B1', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('360', 'FF5E1104-955C-0004-1014-6C15130C11A4', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('361', 'BDAF966F-DF85-F365-9E1A-1B3163189800', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('362', '7DF15556-70B6-1974-7049-72DBD4B2DA3A', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('363', 'ED6E9F96-2C8D-80E0-9135-5A24CCDB7965', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('364', '3E775361-E63E-D04F-FF49-22BFD8509989', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('365', '8193C090-15C3-5CE3-7FB6-578D955B8ADC', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:15:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('366', '9EEEC9CC-1911-C033-4F23-1C164F255E98', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('367', '73B7A982-362D-241C-D6D7-6FD151F62D64', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('368', '99CCBC61-51B8-37B5-7F06-027889BB8F57', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('369', '81CEEEFC-373D-2F1C-F819-0D386AD3931D', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('370', '7F3D1257-519D-1F40-D4AC-42F559DA8309', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('371', '80D67975-ED35-A7CA-06BA-2851D8959096', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('372', 'F7525469-2D23-67B9-BC51-2DDFADEEC8A8', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('373', '9D2DDA58-F5DD-0D1A-9414-0AF72B0087B7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:28', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('374', '141F0D9A-63F8-0F80-2F58-A372D320E679', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:28', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('375', '38F59AED-949E-AFC8-B72C-21B68B9DC263', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:28', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('376', '1DFA909A-28A3-72EC-3E2E-34C780DCB915', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:28', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('377', '5AF69D34-BEC3-88A3-0F0D-AB5C1A3F9EFB', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:28', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('378', '2538B0A2-D13E-6E28-0ADC-83B663F608A6', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:29', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('379', 'A7E2B52B-85F5-F0EB-E8A3-968270C3E3E9', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:29', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('380', '9A72CB17-DC1A-B596-105E-2CEA7C542064', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:29', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('381', '393F98D7-5C43-ABBC-6778-B31715D789D0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:29', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('382', 'DFA8391F-D2F1-C2E6-35AB-8A465F3C5847', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:29', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('383', '1142C318-375A-BEE5-B0DD-192F471E7C6F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:29', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('384', 'E1A917DA-4DCF-C7F9-5FF2-EE135AAF9DA2', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('385', '0AB5DE7D-B967-06F5-2ABB-95F55939BA05', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('386', '0DF443E7-6F80-CED3-2015-BC7C26BE5423', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('387', '64D8DC15-215F-E926-EF1F-CEAEAB66F9E6', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('388', '1359A33B-4039-3F1E-24AC-679E30BC2DB6', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('389', '8CB3237C-A5C5-24F4-B940-DAD6C5EB65CF', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('390', '02465924-99A5-1DCB-AFA2-7B94E9B093E8', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('391', 'A7447DC9-105D-E65B-4C91-681FB9782143', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('392', '1B23C918-5A6E-FE87-4B83-1EFC01EDC357', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('393', '04BC8F0C-303B-D59A-BEC1-D850825691C9', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:31', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('394', 'ACDE4F8F-B485-D91A-7608-58E43848CF3F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('395', '26B2075D-9F5D-6633-B5CC-8901E86B6C15', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('396', 'EDEFC026-40E0-3AA8-AF62-01E2930A10C2', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('397', 'D4EDBD54-43F4-F619-5C7D-CBEA36D82AFB', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('398', '0BD8D40C-242A-E06A-3B26-8FA1129F7EC7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('399', '6C9A271B-7CEE-6B08-7D1C-EF8BD282EFE5', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('400', '669F0D78-791A-C585-08B1-4EC8BFD37607', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('401', 'E3711043-B2E4-D621-3436-DDE67F1EAE75', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('402', 'CAA81FAD-F629-9674-7284-54EF01D353A3', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('403', '9D39D9A3-4F4B-0645-FDB6-F3AB7BF8DE59', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('404', 'A65AFC1A-DF68-7B38-7CDD-C3055AD5AF6F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('405', 'FF93ABFC-9452-E2E8-DEB3-670CC18B9389', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('406', '993749D0-BCB8-E8F5-7DFB-E3EBE8B3855B', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:16:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('407', '36D85D72-452A-E89C-6D12-DEBF37AB0061', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:18:45', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('408', '59D4D1BF-2ED6-CC30-D19F-EF58455287D3', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:18:45', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('409', 'A4DB198A-75CF-9C42-ACDC-A1FFE9BAB4A9', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:23', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('410', '0EF5CB21-04B3-5CE6-6057-24ED3A0D4FE0', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('411', 'B883A23E-9DE2-6BEB-0F53-FEB02F516E92', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('412', '81FDDF1E-CCCF-A5E4-C36D-3C2BACD8365E', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('413', '48BF0EDE-70A5-8C61-F831-23730EA0CE16', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('414', '8CC6C32D-D5A1-F8DB-FE68-7D6C0EFF8FDD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('415', '40617C4E-888B-0B3D-4ADC-81EFEC304932', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('416', 'A8796CEA-E3C2-57B5-A49F-2FC6F7BAA47A', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('417', '6CB54897-0C1D-65DF-FB03-B5AD927FFC67', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('418', 'ACDDF336-E8E1-CF13-87A8-19E16A8A03CF', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('419', '08AF4E27-586D-611B-5864-B5F2BEA1F7D9', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('420', 'A9812278-8A63-B6F6-A78A-AEADF1BF8506', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('421', 'D57C9A1C-722F-300B-EA14-E26F994FF87F', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('422', 'E3849BC2-3042-7A8E-8752-9E007FE3FD93', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('423', '68FF6ACC-70C5-2B21-2B93-FD7DE19FD65C', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('424', '75A8CBC5-4BDD-2053-EC10-7564C6A945F8', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('425', '07E39157-D379-ACAE-DBCC-8168B1C4F119', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('426', '14F0E24A-4F87-1E52-8A69-31A7DBD68D7F', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('427', '600D1B4A-D1BD-B187-BC5A-5835BA806383', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('428', '1512EE20-728F-CCE6-4963-EBB748FA421E', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('429', 'BFB014AD-2D31-7222-E390-D6509907D02F', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('430', '7F8D3950-6D08-DB55-8037-6BE248C568C7', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('431', 'E9FD6EC6-1626-7C8E-A44A-A1D5B151353E', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('432', 'D1D3A2FF-2C14-6308-7428-759CDCABFAFC', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('433', '874A3CC8-B2F9-35F5-76A8-BF38F4673CDC', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:19:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('434', 'D3DF1E26-59AA-1B72-877A-28A7A5739B76', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:25:56', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('435', 'DE884FAE-1B9B-7E0F-6B30-718FE160C471', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:25:59', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('436', 'B3FE0279-3ABB-B29E-A1AE-B827E4514870', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:25:59', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('437', '4B1BE034-31AD-1C54-B7D9-8F58DDA20ED0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('438', 'D3D11BFF-EAC0-A396-44AF-B6DF598888BF', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('439', '7A5E7008-8D72-B94B-53B6-DEB5544393EC', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('440', 'D80FE86C-0896-9672-D5C5-C6EC3E53DD53', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('441', 'F84DD0EF-4B0F-BA89-281A-15A9AA86329E', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('442', '347D4488-4427-5313-605D-6EC91026D21F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('443', '35417E69-D5D3-794B-B100-BE1C352037BE', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('444', '8E39CEFB-EB23-3C93-D331-871D9A807E9F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('445', 'C842634E-9A6D-F647-185C-33E76EBDB6DB', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('446', '73B63EC1-5566-759A-DF15-43CCAEED25DA', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('447', '498EAD43-4420-8E9C-5DB0-83252C1D67EA', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('448', '84D9FE64-6BE0-C131-203E-F987AB47B91A', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('449', 'E93F3095-CC73-E8F7-2824-AE05073F7F86', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:02', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('450', '44444270-C8E5-E623-2AD3-8866CD7E6032', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:02', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('451', '904C364B-6411-EE0E-3919-AE1E4A9CE417', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:02', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('452', '2BCBA10B-7430-D64D-CB38-13A1136A9AA7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:02', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('453', '268A7CEC-08F8-A099-45CE-83FD58E04D05', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:03', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('454', 'CB008276-DB3B-9589-95B2-3675C75941CB', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:03', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('455', '6B2A950E-32C0-DE13-BDD0-07FFB7B1D240', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:03', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('456', '0A7337B2-97E0-AE73-C4A0-7B3813B429B7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('457', 'E8151AE0-E69B-1EA9-2FBD-A3023F97EA57', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('458', 'D5D49FD8-446E-0855-DC94-263527887366', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('459', '2D4F12BB-FF91-1280-E522-9781C926B069', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('460', '2E342692-D46D-8042-9F47-4E9F43ED5783', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:26:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('461', '9CA573DD-7A35-2451-5D6B-8703354BD89E', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:27:08', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('462', '7B4BDEF1-C09F-21F5-A9B7-3BD55BEC2194', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:27:20', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('463', 'DEA9DEAC-CF1E-1F48-C92E-39269EA5B1BB', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-23 15:28:02', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('464', 'C103880A-0278-5445-91C9-32D98BC64E6B', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-25 16:33:59', '2015-11-25 17:05:08', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('465', '8A328695-9D1B-6AAC-4F3E-0049C6177DD5', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:06:45', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('466', 'FE0F0E2F-677F-207C-4498-5B25A7FF34B3', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:07:40', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('467', 'D3066524-6FD9-8753-5EB9-2E2DCD2F10BE', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:16:05', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('468', 'F3B09B1E-6EAA-83AA-7921-F9AD09E55568', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:16:08', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('469', '4D69254D-1B3A-6970-0C3F-9AEB703696A1', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:17:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('470', '02641A20-577E-31EC-53C1-EB399A8F3DF2', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:19:52', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('471', '13F6F32D-0C17-73C1-FE43-106041F11719', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:21:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('472', 'BA0A86DF-371D-ABC7-EC69-45A3862ACEB0', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:22:48', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('473', 'B833B731-5357-282D-B762-D16EAE3511B7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:35:13', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('474', '048D98A5-C331-319A-3188-C0B3E39B4BC9', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:37:19', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('475', '0ADB9BF5-BD5C-1082-BD58-ED21CEF3070A', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:37:39', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('476', 'E7224DD6-3463-9F9F-93BB-C37961D0069D', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:39:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('477', '3CD9B3D7-C0A0-1488-7ED5-1226CA93C2AD', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:40:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('478', '15B9133F-1FD4-5674-E012-13D8023AA5FB', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:43:20', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('479', '29AE75F8-6F30-5286-E9C1-013AA4A56444', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:43:39', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('480', 'D41A4454-CB22-E5D4-6F64-D9233670A337', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:44:02', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('481', 'FF73D1CF-CE0F-84BC-4B6D-40D34C3E790F', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:44:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('482', '9DC2CFEA-5BB0-4A23-839F-800DBC586391', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-11-30 16:44:58', '2015-11-30 17:04:32', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('483', 'A693CB26-19D6-6994-AE05-0BFA1E27B6C8', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '3D159430-CD93-9CEF-15A8-C5573DAA080F', '', '2015-12-07 14:03:48', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('484', '910847ED-96D9-14B7-9EC7-F983F943E3B1', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-08 17:01:51', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('485', '63B9A69F-DF8B-E4D7-0E6A-8E55CA0B3F65', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-09 11:46:46', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('486', 'A6B36E4C-5CE7-1C61-A2AF-41D113FEE749', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-09 11:47:29', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('487', '28D8651C-852A-763E-9D95-1787C62270F2', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-09 11:48:55', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('488', '3CD51A65-70CB-408C-2BF4-3DB1F30203AE', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-09 11:51:13', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('489', 'B4ECB733-9883-185B-1214-483F21D083A2', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-09 11:52:34', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('490', 'F09F7E55-3F5D-414C-A8E7-874D935BF58D', 'DC1F031B-C660-13C3-068A-7B180F323811', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-09 13:45:52', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('491', '0BB76283-0555-C319-45A7-A0C2341781D7', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-10 14:33:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('492', '2EF33D39-1782-390D-A452-07DAAE7BF61B', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-10 14:33:46', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('493', '84A1BF63-5089-B004-0A83-44CF6C526C4F', 'DC1F031B-C660-13C3-068A-7B180F323811', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-11 13:55:55', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('494', 'BDB33293-F4DA-D7FD-F77E-051191F7185C', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-18 15:57:51', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('495', '4A56A611-CC6E-74C2-FB8C-F51C2B639DB1', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-18 15:59:44', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('496', '8F4B1CD8-9962-CBBE-4169-547C2061CAE3', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-18 16:04:40', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('497', '28784C94-B5FA-9CDA-495F-3C045BE5002E', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-18 16:11:45', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('498', '93AD13C2-990E-7425-7E16-2588F814A5F7', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-18 16:19:19', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('499', 'BA778A94-79EF-DFE5-841A-95FC96632A59', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-18 16:29:43', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('500', '48745F62-FA67-B193-F498-7BB0C7C2C693', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-29 12:00:54', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('501', 'B0BC8FB0-8259-41E8-4456-413B9B61A60B', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-29 13:54:44', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('502', 'A7112F26-CFE1-5964-A084-4E4FF51C6017', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-29 13:55:05', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('503', '833E5743-587C-C5D2-98E1-E15B0BD9761B', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-29 13:55:13', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('504', '1052389E-1B5F-0DC1-E40C-4627D53AC5ED', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-29 13:55:40', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('505', 'B492638B-9E0A-0312-C67D-B3EBBD018EFC', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2015-12-29 15:37:38', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('506', 'F641983A-FFFE-1EFA-7B71-7FB19D5368AA', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-14 13:01:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('507', 'CC9409C2-39EC-849B-AFC3-1194D917492E', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-01-14 20:16:56', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('508', '1CD42D67-FAE0-5F69-4DF6-BFDB0577E991', 'B38208B9-5C5F-38A2-1181-9FF3A7565BDF', '1', '1', '0', 'test', '开始', '13951855386', '王林金', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-14 20:17:38', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('509', '65D89103-EBB6-A50A-2E89-C9146FAECF1A', 'DC1F031B-C660-13C3-068A-7B180F323811', '1', '1', '0', 'test', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-16 16:06:30', null, '1', null, '1', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('510', '3DB86395-7931-341C-4616-681650A85384', 'A436C211-38C8-10E8-6569-020B45D7AD13', '1', '1', '0', 'BPMTEST', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:20:26', null, '1', '1', '0', 'gfvrtbtr', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('511', 'C39DB774-0133-A9B1-DC36-58179D3609D2', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:20:52', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('512', '65CC3BCA-3E42-0000-96B1-DF0AA264E669', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:21:18', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('513', 'B8B92F46-8E3E-C382-02EC-FD558250B85D', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:21:37', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('514', '2542A138-FDBE-EC60-74CC-22EAA169C78C', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:22:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('515', '6DFDA4FC-E271-2C1D-3666-773F4B0E597A', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:26:37', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('516', '94910D6D-D087-997E-BA9E-6771C3CA83D5', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:28:53', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('517', 'F417E650-977A-C28F-7CBF-01BC8EC390FB', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:34:04', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('518', '0C7C0840-7CFE-844E-1B45-220E009B15EB', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:36:05', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('519', '329DF709-44E9-9534-D249-A89E57A5B0EB', '5E7ACD87-B5EB-A793-2866-AC49FFA62EA8', '1', '1', '0', 'qjsqd', '发起人发起', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:36:16', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('520', 'BB22A692-8347-788E-CA46-2F7E6369B4BD', 'E63FBB34-7762-9A63-8F76-131330D0B26D', '1', '1', '0', 'QQQQQ', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:36:43', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('521', 'EB15299D-496C-E6D1-BD3C-3FD24E9B83BA', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:37:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('522', '8F8A8BAD-BB3E-7509-765A-59AB0403244C', '7526DCF4-D499-8A39-530E-2AB5E49D9F91', '1', '1', '0', 'usecar', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 10:37:51', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('523', 'B2006248-529F-AB1D-BD4E-5A72D642C0C7', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 13:54:04', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('524', '466365F5-2CF8-C430-46DC-25BCEFEB8243', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'workflow1', '工作流测试1', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:01:46', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('525', 'C2BA54AF-3FA8-0C27-53DB-0239BFA96106', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', 'workflow1', '工作流测试1', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:01:56', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('526', '434A51A8-B335-D605-A6DC-561AE1B9C254', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', 'workflow1', '工作流测试1', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:02:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('527', '3ADF279A-8016-A73F-F718-E77A671613C3', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'workflow1', '工作流测试1', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:02:06', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('528', 'B8FAFBD4-89FC-47CE-578A-B4C903053061', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '1', '1', '0', 'qjsqd', '请假申请', 'workflow1', '工作流测试1', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:02:12', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('529', 'B3AE8677-696C-0192-1BCF-4162C143904A', '9137AC48-7432-F5D6-CAB0-C14546C63049', '1', '1', '0', 'ccsqd', '出差申请', 'workflow1', '工作流测试1', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:02:22', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('530', '4DA4D4C4-C34B-1880-7132-624858433881', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', 'workflow2', '工作流测试2', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:03:20', '2016-01-18 18:18:53', '1', '1', '1', '58ygg', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('531', '9297A64E-DBA6-44E5-1543-F36D939671D6', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'workflow2', '工作流测试2', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:03:24', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('532', '5E4DEDFD-2925-8B35-FD4A-BB4ED24D2390', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'workflow3', '工作流测试3', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:03:48', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('533', 'A9696294-CC10-AF39-B4E2-23CBEFD64371', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'workflow3', '工作流测试3', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:03:52', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('534', 'BFED260A-27BA-A432-7582-677339E884B4', '9137AC48-7432-F5D6-CAB0-C14546C63049', '1', '1', '0', 'ccsqd', '出差申请', 'workflow3', '工作流测试3', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:03:55', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('535', 'ED5AF045-FF5F-21A3-5C1B-EEADC2FE4118', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '1', '1', '0', 'qjsqd', '请假申请', 'workflow3', '工作流测试3', '3298B539-E64C-5941-1158-4F285220C0C1', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 14:04:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('536', '59CE6A18-F0B3-DD55-F2C5-8EBE7CE49DCF', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '1', '1', '0', 'qjsqd', '请假申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 15:00:42', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('537', '4DA4D4C4-C34B-1880-7132-624858433881', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '0', 'bxsqd', '部门总监意见', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 18:18:53', null, '0', null, '0', null, null, null, 'workflow2', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('538', '4DA4D4C4-C34B-1880-7132-624858433881', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '1', 'bxsqd', '部门总监意见', 'caocao', '曹操', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 18:18:53', null, '0', null, '0', null, null, null, 'workflow2', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('539', '4DA4D4C4-C34B-1880-7132-624858433881', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '1', 'bxsqd', '部门总监意见', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-01-18 18:18:53', null, '0', null, '0', null, null, null, 'workflow2', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('540', '4DA4D4C4-C34B-1880-7132-624858433881', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '1', 'bxsqd', '部门总监意见', 'zhaowei', '', '', '', '', '2016-01-18 18:18:53', null, '0', null, '0', null, null, null, 'workflow2', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('541', '642B6C02-CE9E-1CBF-1795-15704221024D', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 19:39:20', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('542', 'E12C9262-3BB5-1090-56B0-7235FBF0DCF2', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 19:39:24', '2016-01-18 22:26:01', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('543', '6C84CFBD-CFF7-ACFF-B984-BFC22B3A92EE', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 19:39:36', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('544', 'FF20E70C-5C0D-F33C-E1DB-0D4F420B7F01', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 20:01:01', '2016-01-18 22:32:43', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('545', '26A9A10D-AD5B-D1FF-859B-732FC8CBBCAE', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 20:06:12', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('546', '2313654F-5F7F-0121-ACF4-F6F4F9D1DA17', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 20:27:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('547', 'E12C9262-3BB5-1090-56B0-7235FBF0DCF2', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '2', '2', '0', 'qksqd', '部门总监审核', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 22:26:01', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('548', 'FF20E70C-5C0D-F33C-E1DB-0D4F420B7F01', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '2', '2', '0', 'bgypsld', '部门领导意见', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-18 22:32:43', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('549', '5E347D2B-85A8-63CB-D837-E28C51D962E8', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 22:35:34', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('550', '57CA9A6A-0301-B635-5592-44403CD72100', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 22:36:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('551', 'AE4D8A8A-2FA4-2C96-1149-075212E2AEF0', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-18 22:37:55', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('565', '2492B02C-7826-8FB5-7F26-7F8A8AEE191E', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'zhaoyang', '赵阳', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '7DDBA6F7-CED5-0819-9890-D37340EA51F3', '', '2016-01-19 18:47:23', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('566', '9F6890C7-0D1A-69EA-AEFB-28470597CA75', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'zhaoyang', '赵阳', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '7DDBA6F7-CED5-0819-9890-D37340EA51F3', '', '2016-01-19 18:47:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('567', 'B3E98F97-82C1-2098-90EE-4FB371DF5233', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'zhaoyang', '赵阳', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '7DDBA6F7-CED5-0819-9890-D37340EA51F3', '', '2016-01-20 09:35:00', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('568', '2CAEE772-7E26-B67F-2ADE-F0C2E1D7A295', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'weipeng', '魏鹏', 'CED9D550-94A4-62F4-5436-4987C35D92EF', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '用户1', '2016-01-20 09:35:16', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('569', '0E8A607B-7CD3-718B-AC2B-15B8995E54B5', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'weipeng', '魏鹏', 'CED9D550-94A4-62F4-5436-4987C35D92EF', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '用户1', '2016-01-20 09:35:20', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('570', '2A56F8A5-0814-FA58-05F7-3C669EF122CC', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 10:12:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('571', '04E9C809-312C-74EE-4C48-DD5A2540AAED', '9137AC48-7432-F5D6-CAB0-C14546C63049', '1', '1', '0', 'ccsqd', '出差申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 10:12:06', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('573', '325F82FB-FE8F-67BE-DB59-89704BF390A3', '9137AC48-7432-F5D6-CAB0-C14546C63049', '1', '1', '0', 'ccsqd', '出差申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 10:12:21', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('574', 'AC8350BB-18D3-1ED8-1AE6-F27C73A9FD1B', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 10:12:47', '2016-01-20 10:14:54', '1', 'null', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('575', '798C0CB5-B5A3-3561-2605-DFD0689F7351', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 10:12:53', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('576', '3D3D5DD4-79EC-4213-E1B9-0BB5EC5291C4', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 10:13:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('577', '572C48F7-AB19-56F3-8458-3C643D88FF87', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 10:13:06', null, '1', 'null', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('578', 'AC8350BB-18D3-1ED8-1AE6-F27C73A9FD1B', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '2', '2', '0', 'bgypsld', '部门领导意见', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-20 10:14:54', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('581', 'C399ACDE-9B40-1227-AE78-C1F632D0A086', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 15:11:50', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('582', '660256FC-BCB0-2FA5-931A-4BF39FD8D59A', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 15:15:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('583', '117D57AE-620C-C0CE-1637-E1321D133012', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 15:15:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('584', 'BB089CE1-9B1D-7495-0203-536DD36EF8F0', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 15:15:37', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('585', '2F1ACEE0-E553-0664-5A8B-645A35FE9490', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 15:21:57', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('586', '8B5BDEBA-B10F-A65D-5536-72ADF9EF98A1', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 15:23:02', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('587', 'B3882715-AD42-D9C4-ED85-EE0503042213', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-20 18:13:29', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('590', 'A67E71BA-B0E9-6C9F-CE70-CDF3010EE972', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', '刘哥133', '刘经理', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-21 11:07:12', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('591', '7D0FB036-626E-B2DC-70B1-526E92564481', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-21 16:20:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('592', '832961CF-FB3B-CBDB-9B3B-F5A75BEF719B', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-21 16:20:16', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('593', 'D477046F-DD9E-0497-9873-19D5DD2083AD', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-21 16:42:18', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('594', '28783C54-9EB2-232B-07C7-A79BFA881CA8', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '1', '1', '0', 'qjsqd', '请假申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-21 16:44:05', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('595', 'E56EB05A-C931-FBC3-A768-E49038E12569', '', '1', '1', '0', '', '', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 09:32:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('596', 'A9859759-5233-255C-6757-DBAB047BE390', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13951855386', '王林金', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 09:45:45', null, '1', '1', '0', '楼图我', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('597', 'D184CE1B-2BDE-7E43-3B5E-73A5B9CD0435', '', '1', '1', '0', '', '', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 10:00:21', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('599', 'DAABD401-56FE-43A2-3C82-050DA5F19BEA', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 10:23:56', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('600', '9604DBE3-0792-53BA-C59C-2F19D3DBB5AE', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 10:24:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('601', '780146D5-D94A-F798-8590-FB50DE44F06D', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 10:25:22', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('603', '4B6256FB-8782-F2CD-8EFC-F2FBC298425B', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 10:25:30', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('604', '700EA025-85EB-799E-A100-5B2F9E1DB3A6', '9137AC48-7432-F5D6-CAB0-C14546C63049', '1', '1', '0', 'ccsqd', '出差申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 10:25:32', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('605', 'A54E9164-AE15-BDA0-A042-8E361E244B6B', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 11:18:23', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('606', '44E87CE0-784F-E2F7-8E80-87EBDFC1C26E', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 11:19:39', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('607', '0B0BB920-B551-48EC-EF67-D84CC192E1E2', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 11:46:52', null, '1', '', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('608', 'B5B0B43C-8323-7E96-7709-B90D3DBBF325', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 14:07:21', null, '1', '2', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('609', '8C6BA8BE-1C73-2FE0-CDB4-47F01440B108', '9137AC48-7432-F5D6-CAB0-C14546C63049', '1', '1', '0', 'ccsqd', '出差申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 14:35:26', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('610', '0CC81429-A6A8-F21F-9682-32E2ECEAB9C6', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 14:58:18', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('611', 'DB6A274A-407D-5C85-20D5-47D1FAC21DFC', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 14:59:45', '2016-01-22 16:56:44', '1', 'null', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('612', '3623B669-8923-D56F-6366-250F6E57A29C', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 15:02:46', '2016-01-22 16:54:53', '1', '0', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('613', '3623B669-8923-D56F-6366-250F6E57A29C', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '2', '2', '0', 'qksqd', '部门总监审核', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 16:54:53', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('614', '55CAD557-4FEA-D699-FB6A-772753C48E43', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 16:55:35', '2016-01-22 16:56:12', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('615', '55CAD557-4FEA-D699-FB6A-772753C48E43', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '2', '0', 'bxsqd', '部门经理审批', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 16:56:12', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('616', '55CAD557-4FEA-D699-FB6A-772753C48E43', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '3', '0', 'bxsqd', '部门总监审批', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-01-22 16:56:12', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('617', '55CAD557-4FEA-D699-FB6A-772753C48E43', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '4', '0', 'bxsqd', '董事长审批', 'caocao', '曹操', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 16:56:12', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('618', 'DB6A274A-407D-5C85-20D5-47D1FAC21DFC', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '2', '0', 'bxsqd', '部门经理审批', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 16:56:44', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('619', 'DB6A274A-407D-5C85-20D5-47D1FAC21DFC', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '3', '0', 'bxsqd', '部门总监审批', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 16:56:44', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('620', 'DB6A274A-407D-5C85-20D5-47D1FAC21DFC', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '4', '0', 'bxsqd', '董事长审批', 'caocao', '曹操', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 16:56:44', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('621', 'DB6A274A-407D-5C85-20D5-47D1FAC21DFC', '83524B7A-F541-CF35-4464-B10D93AAE996', '3', '2', '0', 'bxsqd', '部门经理审批', '方泽东186', '方泽东', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:08:11', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('622', '9D75447A-9D36-9E33-C65A-2CA3EAAB2F3E', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:11:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('623', '4B54346F-CB6B-2ADC-C3D1-08AEFBEB5A59', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:11:32', '2016-01-22 17:11:44', '1', 'null', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('624', '4B54346F-CB6B-2ADC-C3D1-08AEFBEB5A59', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '0', 'bxsqd', '部门总监意见', 'caocao', '曹操', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:11:44', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('625', '93B080B0-782D-C6F1-FA69-6137BE81901C', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:21:36', '2016-01-22 17:21:43', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('626', '93B080B0-782D-C6F1-FA69-6137BE81901C', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '0', 'bxsqd', '部门总监意见', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:21:43', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('627', '9C044D4A-0EC7-3885-42AD-29A93CABD6E9', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:23:40', '2016-01-22 17:25:04', '1', 'null', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('628', '6B81F720-8ED7-DA4A-5937-CC2A2A4C5A5E', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:23:44', '2016-01-22 17:23:51', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('629', '6B81F720-8ED7-DA4A-5937-CC2A2A4C5A5E', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '2', '0', 'bxsqd', '部门经理审批', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:23:51', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('630', '9C044D4A-0EC7-3885-42AD-29A93CABD6E9', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '2', '0', 'bxsqd', '部门经理审批', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:25:04', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('633', 'D41BBB49-0FCD-E22E-728E-B485BB804CE3', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:34:28', '2016-01-22 17:34:40', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('634', 'D41BBB49-0FCD-E22E-728E-B485BB804CE3', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '4', '0', 'bxsqd', '董事长审批', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:34:40', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('635', 'FEB05208-E380-8652-9850-92E6A4FC7D96', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:35:05', '2016-01-22 17:35:12', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('636', 'FEB05208-E380-8652-9850-92E6A4FC7D96', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '0', 'bxsqd', '部门总监意见', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:35:12', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('637', 'FEB05208-E380-8652-9850-92E6A4FC7D96', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '1', 'bxsqd', '部门总监意见', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-01-22 17:35:12', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('638', 'FACA7452-4E76-5443-2B28-15D2E6C1A926', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:35:30', '2016-01-22 17:35:46', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('639', 'FACA7452-4E76-5443-2B28-15D2E6C1A926', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '4', '0', 'bxsqd', '董事长审批', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-01-22 17:35:46', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('640', '8FB8AEFF-3114-3B10-65C2-394F1229728F', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:36:52', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('647', 'D9D88153-1F92-B240-B5E0-C07C7C51BA6D', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:42:05', '2016-01-22 17:42:15', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('648', 'D9D88153-1F92-B240-B5E0-C07C7C51BA6D', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '2', '0', 'bxsqd', '部门经理审批', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:42:15', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('649', '79514DF0-32A8-AF9E-BF2B-63BE627C0DDC', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:43:12', '2016-01-22 17:43:19', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('650', '79514DF0-32A8-AF9E-BF2B-63BE627C0DDC', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '2', '0', 'bxsqd', '部门经理审批', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:43:19', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('653', '5AB22091-3D0C-F266-4524-4D1D1CB9C3F6', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:53:38', '2016-01-22 17:53:45', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('654', '5AB22091-3D0C-F266-4524-4D1D1CB9C3F6', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '3', '0', 'bxsqd', '部门总监审批', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:53:45', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('655', 'F648249B-6F8F-C051-97FB-A0368EE413ED', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:53:55', '2016-01-22 17:54:12', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('656', 'F648249B-6F8F-C051-97FB-A0368EE413ED', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '3', '0', 'bxsqd', '部门总监审批', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:54:12', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('657', 'E25F1B43-47F1-D60E-87D1-186F9E7384F7', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 17:59:26', '2016-01-22 17:59:32', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('658', 'E25F1B43-47F1-D60E-87D1-186F9E7384F7', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '2', '0', 'bxsqd', '部门经理审批', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-22 17:59:32', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('659', 'D5D9AF83-357B-422E-84F8-C772A29F3357', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 18:00:10', '2016-01-22 18:00:24', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('660', 'D5D9AF83-357B-422E-84F8-C772A29F3357', '83524B7A-F541-CF35-4464-B10D93AAE996', '2', '3', '0', 'bxsqd', '部门总监审批', '18795960465', '金', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-22 18:00:24', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('661', 'E094CAE9-005F-EC79-45BE-60B1BCD786A0', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-25 09:52:44', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('662', 'D4447B02-5EDA-9DF0-B203-F9702BC74CDB', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-25 09:53:44', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('663', 'D010DEAD-7461-A453-1FF7-FF05194C2914', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-26 12:48:19', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('664', '36B7D2C9-29D9-7D6E-257E-BEBF1BC91253', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-01-26 16:51:50', '2016-01-26 16:53:09', '1', '', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('665', '36B7D2C9-29D9-7D6E-257E-BEBF1BC91253', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '0', 'bxsqd', '部门总监意见', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-26 16:53:09', null, '0', null, '0', null, null, null, '15380775642', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('666', '36B7D2C9-29D9-7D6E-257E-BEBF1BC91253', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '1', 'bxsqd', '部门总监意见', 'caocao', '曹操', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-26 16:53:09', null, '0', null, '0', null, null, null, '15380775642', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('667', 'AD08AFA2-BE3A-0689-62C1-E55E2A8598F0', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-28 15:39:00', '2016-02-24 15:35:49', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('668', 'B1759EA6-10C2-BBFB-803B-F066CF55A051', '9137AC48-7432-F5D6-CAB0-C14546C63049', '1', '1', '0', 'ccsqd', '出差申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-28 15:39:28', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('669', 'B9F16C24-8B61-4E9D-806A-155BC981FEBF', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-28 15:42:52', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('670', '2531CC5E-6740-AF53-34FF-8EED3299C698', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-28 15:44:36', '2016-01-28 16:03:06', '1', '2', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('671', 'E88E7EA7-2D66-1A42-5C53-D84D40A49408', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-28 15:44:40', '2016-01-28 15:57:19', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('672', 'E88E7EA7-2D66-1A42-5C53-D84D40A49408', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '2', '2', '0', 'qksqd', '部门总监审核', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-01-28 15:57:19', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('673', '2531CC5E-6740-AF53-34FF-8EED3299C698', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '0', 'bxsqd', '部门总监意见', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-01-28 16:03:06', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('674', '2531CC5E-6740-AF53-34FF-8EED3299C698', 'C9175369-B468-37E9-4E10-C128C27BE59E', '3', '2', '0', 'bxsqd', '部门总监意见', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-01-28 16:04:24', null, '0', null, '0', null, null, null, '15380775642', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('675', '2531CC5E-6740-AF53-34FF-8EED3299C698', 'C9175369-B468-37E9-4E10-C128C27BE59E', '4', '2', '0', 'bxsqd', '部门总监意见', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-01-28 16:04:45', null, '0', null, '0', null, null, null, '15380775642', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('676', '14221BF1-9926-8D2F-873B-914AC2ACA83F', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-02-01 11:17:51', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('677', '6CECBE0B-CCBB-0C8B-EEBA-E403FFAE20C0', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '刘哥133', '刘经理', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-02-21 18:07:08', '2016-03-12 12:33:56', '1', '2', '1', 'hhhhj', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('678', '691326FC-C71C-77CA-5AD4-130C1A4BB1F6', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-02-24 14:57:40', null, '1', '', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('679', 'DC0DBC7D-E657-BD93-0384-2D0DC3299E4A', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-02-24 14:59:16', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('682', 'FCEF97E1-2463-E06B-A760-9F9F5E4D8B80', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-02-24 15:30:41', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('683', 'AD08AFA2-BE3A-0689-62C1-E55E2A8598F0', 'C9175369-B468-37E9-4E10-C128C27BE59E', '2', '2', '0', 'bxsqd', '部门总监意见', '刘哥133', '刘经理', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-02-24 15:35:49', null, '0', null, '0', null, null, null, '13057528770', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('684', 'CD0BF524-5F5C-26EA-979F-8326CF982F8D', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-02-24 15:41:39', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('685', 'EAF5B81F-52A4-4C8D-6D65-D557523FFA29', '9137AC48-7432-F5D6-CAB0-C14546C63049', '1', '1', '0', 'ccsqd', '出差申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-02-24 15:52:01', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('687', '8056C163-8D25-D63E-4CFA-B01FC2DE306B', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '1', '0', 'bxsqd', '发起申请', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-02-29 16:14:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('688', '92D8F27E-47E0-B045-35DE-F9BA68EB5C73', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-03-02 17:24:06', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('689', '2FB93B7E-9CD5-7301-4A8A-6932D2929E0D', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-03-02 17:25:00', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('690', '15CBDCC7-FC31-7017-C118-5F6D1904A2E1', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-03-11 16:22:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('691', '6CECBE0B-CCBB-0C8B-EEBA-E403FFAE20C0', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '2', '2', '0', 'bgypsld', '部门领导意见', 'caocao', '曹操', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-03-12 12:33:56', null, '0', null, '0', null, null, null, '刘哥133', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('692', '6CECBE0B-CCBB-0C8B-EEBA-E403FFAE20C0', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '2', '2', '1', 'bgypsld', '部门领导意见', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-03-12 12:33:56', null, '0', null, '0', null, null, null, '刘哥133', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('693', 'AD08AFA2-BE3A-0689-62C1-E55E2A8598F0', 'C9175369-B468-37E9-4E10-C128C27BE59E', '3', '2', '0', 'bxsqd', '部门总监意见', '15380775642', 'Cc', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '18795960465', '2016-03-12 12:34:26', null, '0', null, '0', null, null, null, '刘哥133', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('694', 'AD08AFA2-BE3A-0689-62C1-E55E2A8598F0', 'C9175369-B468-37E9-4E10-C128C27BE59E', '3', '2', '1', 'bxsqd', '部门总监意见', '13057528770', '徐', '3298B539-E64C-5941-1158-4F285220C0C1', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-03-12 12:34:26', null, '0', null, '0', null, null, null, '刘哥133', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('695', 'AD08AFA2-BE3A-0689-62C1-E55E2A8598F0', 'C9175369-B468-37E9-4E10-C128C27BE59E', '4', '2', '0', 'bxsqd', '部门总监意见', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-03-20 22:32:07', null, '0', null, '0', null, null, null, '刘哥133', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('697', 'D585B14D-4609-0D32-CC05-641066C9E9B2', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-03-22 14:01:45', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('698', '5AD80DD2-B01B-B89A-817F-C2EAE33420D5', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-03-31 12:54:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('699', '01829DFD-75B1-10F3-67CB-EB3BFC6FAD99', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-03-31 12:54:38', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('700', '15CA7FEA-85BA-F09B-A4F4-7F57C84C0785', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-03-31 17:31:22', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('701', '15C34204-F74F-FF17-BD6A-7CCDF473C937', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-22 09:09:15', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('702', 'CF3C9182-093E-3030-98F1-E027247B0EDD', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-24 16:44:05', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('703', '361D595B-518A-373A-8F0C-73ADC9C606C2', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-24 16:45:28', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('704', '4434FFD0-04CA-2769-A435-0BB6DDBAE7A5', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-24 16:49:03', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('705', '73F0B77E-6A59-1A23-9A71-1F46D9D13AB9', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-28 15:13:46', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('706', '49409C3B-9FE4-ED45-0AA5-0F9A798FDAC6', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '1', '1', '0', 'qjsqd', '请假申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-28 15:34:11', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('707', 'A1E10350-7D53-19F6-58E7-F335C68A9CB9', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-30 12:41:53', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('708', '74115521-D7BE-9D2E-140E-969F4E3F552D', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-30 12:42:50', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('709', 'F2B33940-9893-956D-81CB-6BE7FA578475', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-30 12:42:56', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('710', '79138365-0C1D-538C-4B43-C4C452A4F523', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-30 12:43:20', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('711', '85704BF9-4A10-4079-9D18-D406613F4A91', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-30 12:44:06', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('712', '98FE2B33-F8AD-E4D9-E470-7C0EFEE7E546', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-30 12:48:06', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('713', 'ADBE9E5F-4AEB-E8BA-0DE2-ABA6E089AC8F', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-30 12:48:18', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('714', '34B937CF-2768-371B-E322-BA76B7DDB065', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '1', '0', 'qksqd', '请款申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-06-30 12:48:23', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('733', 'C8C6BC9B-685D-6607-85C6-56A3EA6BA349', '3A7F5F45-D404-F04A-0C42-C0BE2292EEC1', '1', '1', '0', 'testa', '开始', 'cst', '陈声涛', '13265125-F623-F7F7-0233-CFD9A795369F', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-09-09 10:41:38', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('716', 'D1AB2146-5871-BC83-3168-F71893700DDE', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-08-05 10:55:23', '2016-08-15 12:49:13', '1', '1', '1', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('717', '1A3F2563-AED6-7D7F-7883-744889EDF446', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-08-05 10:57:50', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('718', '196C23F0-6D6F-82D5-59B0-A7FC78933EFD', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-08-11 17:15:56', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('719', '6F42B490-87C5-7F4F-155C-B24DF41D422F', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-08-11 17:38:33', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('720', '6F1DDC34-D0CD-C5BD-4A0E-2BE1DFC2FB88', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-08-13 17:39:19', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('721', 'EB5EF5CE-B90D-4A3F-5083-3FF2E6B17A93', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-08-13 17:54:15', null, '1', null, '1', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('722', 'D1AB2146-5871-BC83-3168-F71893700DDE', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '2', '2', '0', 'bgypsld', '部门领导意见', '用户103', '用户103', 'F09568E9-3DD5-6591-6A64-2B83B2FC7A71', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '用户101', '2016-08-15 12:49:13', null, '0', null, '0', null, null, null, 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `flow_run_prcs` VALUES ('723', '3CCE0C8F-671C-D7BC-33D3-305CEB6A7B37', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '1', '0', 'bxsqd', '申请报销', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-08-18 14:11:07', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('726', 'F11A59FC-F875-D0DC-792A-5746464F4FF1', '11169EC6-F406-0433-B877-81D9F455BDDD', '1', '1', '0', 'nbbx', '开始', 'tgh', '唐广辉', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '1408244A-2653-52D7-4853-3F48EC302298', '', '2016-09-06 17:31:43', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('727', '47DA38AD-942D-9D2C-C33F-37AEE570FCD9', '11169EC6-F406-0433-B877-81D9F455BDDD', '1', '1', '0', 'nbbx', '开始', 'tgh', '唐广辉', '3A5FDC22-04D4-5365-E2D7-1EEAD5F7D5D7', '1408244A-2653-52D7-4853-3F48EC302298', '', '2016-09-06 17:33:44', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('732', '1A9E6274-CB68-CC7F-3779-AD6A70920BD0', '3A7F5F45-D404-F04A-0C42-C0BE2292EEC1', '1', '1', '0', 'testa', '开始', 'cst', '陈声涛', '13265125-F623-F7F7-0233-CFD9A795369F', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-09-09 10:40:46', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('731', 'B7A16181-1122-0E38-C4E9-89ABD4ABB3A9', '3A7F5F45-D404-F04A-0C42-C0BE2292EEC1', '1', '1', '0', 'testa', '开始', 'cst', '陈声涛', '13265125-F623-F7F7-0233-CFD9A795369F', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '2016-09-09 10:15:27', null, '1', '1', '0', '', null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('734', 'DEA2FC19-EABA-7A2C-E606-2A54E3FFEA07', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-11-21 12:47:25', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('735', '32858CFA-C500-BC64-3BFE-CA2BB3F5E263', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '1', '1', '0', 'testTab', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-11-21 12:48:46', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('736', '66C83791-6160-25CC-AD56-78B5F765D4DB', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '1', '1', '0', 'testTab', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-11-21 12:48:54', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('737', '6633ECD6-F93F-9B08-F56E-D37B9A059AF3', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '1', '1', '0', 'testTab', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-11-21 12:48:59', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('738', '075A540D-FB5B-1964-58DB-C04862C15C87', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '1', '1', '0', 'testTab', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-11-21 12:51:24', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('739', '0D24A3E6-328C-FE21-4390-F32D201258FE', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '1', '1', '0', 'testTab', '开始', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-11-21 12:51:27', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('740', 'D455D952-EDAA-8260-49D3-28FA3FF6DC44', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2016-11-21 12:51:38', null, '1', null, '0', null, null, null, null, null);
INSERT INTO `flow_run_prcs` VALUES ('741', '76246F59-3945-443F-84CE-15BF5BC26FCD', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '1', '0', 'bgypsld', '发起申请', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '2017-01-23 17:05:15', null, '1', null, '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for folder
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FOLDER_ID` varchar(150) DEFAULT NULL,
  `FOLDER_NO` int(11) DEFAULT NULL,
  `FOLDER_PID` varchar(150) DEFAULT NULL,
  `FOLDER_NAME` varchar(600) DEFAULT NULL,
  `NEW_USER` text,
  `NEW_DEPT` text,
  `NEW_PRIV` text,
  `EDIT_USER` text,
  `EDIT_DEPT` text,
  `EDIT_PRIV` text,
  `ACCESS_USER` text,
  `ACCESS_DEPT` text,
  `ACCESS_PRIV` text,
  `DOWN_USER` text,
  `DOWN_DEPT` text,
  `DOWN_PRIV` text,
  `DEL_USER` text,
  `DEL_DEPT` text,
  `DEL_PRIV` text,
  `COMM_USER` text,
  `COMM_DEPT` text,
  `COMM_PRIV` text,
  `ACCOUNT_ID` varchar(150) DEFAULT NULL,
  `IS_PUBLIC` varchar(60) DEFAULT NULL,
  `ORG_ID` varchar(60) DEFAULT NULL,
  KEY `Id` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of folder
-- ----------------------------
INSERT INTO `folder` VALUES ('37', '73A98658-DE9E-9BC6-E1BB-E7B81D72D0DF', '1', '0', '测试', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `folder` VALUES ('38', '0B7CE8F9-950D-E604-EA12-93FB54831759', '0', '0', '我的文件柜', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'admin', '2', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for folder_file
-- ----------------------------
DROP TABLE IF EXISTS `folder_file`;
CREATE TABLE `folder_file` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FILE_ID` varchar(150) DEFAULT NULL,
  `FILE_NO` int(11) DEFAULT NULL,
  `FILE_NAME` varchar(150) DEFAULT NULL,
  `FOLDER_ID` varchar(150) DEFAULT NULL,
  `FILE_CONTENT` text,
  `ATTACH_ID` text,
  `ORG_ID` varchar(60) DEFAULT NULL,
  KEY `Id` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of folder_file
-- ----------------------------

-- ----------------------------
-- Table structure for home_page
-- ----------------------------
DROP TABLE IF EXISTS `home_page`;
CREATE TABLE `home_page` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `HOME_PAGE_ID` varchar(50) DEFAULT NULL,
  `MODULE` varchar(50) DEFAULT NULL,
  `MODULE_NAME` varchar(50) DEFAULT NULL,
  `ADDRESS_TD` varchar(50) DEFAULT NULL,
  `ADDRESS_TR` varchar(50) DEFAULT NULL,
  `IS_OPEN` varchar(50) DEFAULT NULL,
  `ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of home_page
-- ----------------------------
INSERT INTO `home_page` VALUES ('5', 'EB1609BD-10F1-0701-F80A-239AB16442FA', 'news', '新闻', '1', '1', '1', 'yzz', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `home_page` VALUES ('6', 'C32A6359-92AC-B250-BCCE-2DF5B624D392', 'notify', '通知公告', '1', '2', '1', 'yzz', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `home_page` VALUES ('7', '0981701C-C403-B245-5E80-3B8776C44CB0', 'calendar', '个人日程', '2', '1', '1', 'yzz', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `home_page` VALUES ('8', 'E6DA17FD-4AFA-974B-31C2-C268D163ED40', 'email', '内部邮件', '2', '2', '1', 'yzz', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `home_page` VALUES ('25', 'F602F953-F6EC-7047-2A75-3A32C71317F4', 'news', '新闻', '1', '1', '1', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `home_page` VALUES ('26', '45ECE12A-BC33-D4CC-4B84-2FBEC73BE7E8', 'notify', '通知公告', '1', '2', '1', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `home_page` VALUES ('27', 'B2DE0B7B-652A-E9FB-CF19-E22736DEA176', 'calendar', '个人日程', '2', '1', '1', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `home_page` VALUES ('28', 'F655EA15-ED03-3788-A9DF-D0456324BAF0', 'email', '内部邮件', '2', '2', '1', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for im_user
-- ----------------------------
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE `im_user` (
  `account_id` varchar(100) NOT NULL,
  `key` varchar(200) DEFAULT NULL,
  `mid` varchar(200) DEFAULT NULL,
  `defined` varchar(4000) DEFAULT '{"send":{"shortcut":"enter"}}',
  PRIMARY KEY (`account_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of im_user
-- ----------------------------
INSERT INTO `im_user` VALUES ('imtest1', null, '14538704710218842', '{\"send\":{\"shortcut\":\"enter\"}}');
INSERT INTO `im_user` VALUES ('imtest2', null, '14538702365562196', '{\"send\":{\"shortcut\":\"enter\"}}');

-- ----------------------------
-- Table structure for infoservice_classfication
-- ----------------------------
DROP TABLE IF EXISTS `infoservice_classfication`;
CREATE TABLE `infoservice_classfication` (
  `id` varchar(50) CHARACTER SET gbk NOT NULL,
  `name` varchar(500) NOT NULL,
  `parent` varchar(50) NOT NULL DEFAULT '0',
  `navigation` text NOT NULL,
  `sort` int(11) NOT NULL DEFAULT '0',
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of infoservice_classfication
-- ----------------------------
INSERT INTO `infoservice_classfication` VALUES ('4ECFC2A7-5E59-2DBB-DB85-4042E09A20C9', 'APP开发', '66F97787-88ED-6385-E6E3-B2D63C1BD375', '0.66F97787-88ED-6385-E6E3-B2D63C1BD375.4ECFC2A7-5E59-2DBB-DB85-4042E09A20C9', '0', null);
INSERT INTO `infoservice_classfication` VALUES ('B7FA9FBB-D2E7-9596-3E45-D6802D6E3624', '网站建设', '66F97787-88ED-6385-E6E3-B2D63C1BD375', '0.66F97787-88ED-6385-E6E3-B2D63C1BD375.B7FA9FBB-D2E7-9596-3E45-D6802D6E3624', '0', null);
INSERT INTO `infoservice_classfication` VALUES ('AF80FB08-3BBD-2DD0-2FFD-010E92418A78', '技术外包', '66F97787-88ED-6385-E6E3-B2D63C1BD375', '0.66F97787-88ED-6385-E6E3-B2D63C1BD375.AF80FB08-3BBD-2DD0-2FFD-010E92418A78', '0', null);
INSERT INTO `infoservice_classfication` VALUES ('935B4E6F-A4B2-064E-564F-BFF29EF6E21C', '专利申请', '6FC5BA90-380A-F5B2-13B7-3FAF9B550906', '0.6FC5BA90-380A-F5B2-13B7-3FAF9B550906.935B4E6F-A4B2-064E-564F-BFF29EF6E21C', '0', null);
INSERT INTO `infoservice_classfication` VALUES ('66F97787-88ED-6385-E6E3-B2D63C1BD375', '技术服务', '0', '0.66F97787-88ED-6385-E6E3-B2D63C1BD375', '0', null);
INSERT INTO `infoservice_classfication` VALUES ('54AA299C-11B3-FDC8-6A07-8CC2D82CBF69', '法律服务', '0', '0.54AA299C-11B3-FDC8-6A07-8CC2D82CBF69', '0', null);
INSERT INTO `infoservice_classfication` VALUES ('6FC5BA90-380A-F5B2-13B7-3FAF9B550906', '知识产权', '0', '0.6FC5BA90-380A-F5B2-13B7-3FAF9B550906', '0', null);
INSERT INTO `infoservice_classfication` VALUES ('1072AD0B-BA24-4842-67F5-E97C282A9A5D', '商标注册', '6FC5BA90-380A-F5B2-13B7-3FAF9B550906', '0.6FC5BA90-380A-F5B2-13B7-3FAF9B550906.1072AD0B-BA24-4842-67F5-E97C282A9A5D', '0', null);

-- ----------------------------
-- Table structure for infoservice_org
-- ----------------------------
DROP TABLE IF EXISTS `infoservice_org`;
CREATE TABLE `infoservice_org` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `org_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `visits` int(10) NOT NULL DEFAULT '0',
  `org_introduce` text CHARACTER SET utf8,
  `service_area` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `business_license` varchar(200) CHARACTER SET utf8 NOT NULL,
  `org_code_certificate` varchar(200) CHARACTER SET utf8 NOT NULL,
  `tax_registration_certificate` varchar(200) CHARACTER SET utf8 NOT NULL,
  `submit_date` datetime NOT NULL,
  `audit_status` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `audit_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `audit_date` datetime DEFAULT NULL,
  `audit_remark` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`org_id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of infoservice_org
-- ----------------------------
INSERT INTO `infoservice_org` VALUES ('2', '1', '0', '<p>南京慧客软件技术有限公司</p>', '', '9A9A5740-43A3-E4ED-900C-078506453F95,', '', '', '2015-09-10 00:49:49', '0', null, null, null);

-- ----------------------------
-- Table structure for infoservice_service_item
-- ----------------------------
DROP TABLE IF EXISTS `infoservice_service_item`;
CREATE TABLE `infoservice_service_item` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `org_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `service_name` varchar(500) CHARACTER SET utf8 NOT NULL,
  `service_desc` text CHARACTER SET utf8,
  `service_price` int(10) DEFAULT NULL,
  `service_price_highest` int(10) DEFAULT NULL,
  `submit_date` datetime NOT NULL,
  `audit_status` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `audit_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `audit_date` datetime DEFAULT NULL,
  `audit_remark` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of infoservice_service_item
-- ----------------------------
INSERT INTO `infoservice_service_item` VALUES ('13', '5A5EBA82-BB4B-67C4-7EFF-F6BEF38A1493', '1', 'OA软件', '<p>OA软件。。。</p><p>&nbsp;</p>', '10000', '1000000', '2015-09-10 00:50:53', '0', null, null, null);

-- ----------------------------
-- Table structure for institution
-- ----------------------------
DROP TABLE IF EXISTS `institution`;
CREATE TABLE `institution` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `INST_ID` varchar(50) DEFAULT NULL,
  `INST_NAME` varchar(40) CHARACTER SET gbk DEFAULT NULL,
  `INST_CONTENT` text,
  `CREATE_TIME` datetime DEFAULT NULL,
  `DIR_ID` varchar(50) DEFAULT NULL,
  `ATTACH_ID` text,
  `ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=1039 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of institution
-- ----------------------------

-- ----------------------------
-- Table structure for inst_comment
-- ----------------------------
DROP TABLE IF EXISTS `inst_comment`;
CREATE TABLE `inst_comment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COM_ID` varchar(50) DEFAULT NULL,
  `COM_PID` varchar(50) DEFAULT NULL,
  `COM_CONTENT` varchar(500) DEFAULT NULL,
  `COM_TIME` datetime DEFAULT NULL,
  `INST_ID` varchar(50) DEFAULT NULL,
  `ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inst_comment
-- ----------------------------

-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `INTERFACE_ID` varchar(50) DEFAULT NULL,
  `TOP_TITLE` varchar(50) DEFAULT NULL,
  `TOP_IMG` varchar(50) DEFAULT NULL,
  `BOTTOM_TITLE` varchar(50) DEFAULT NULL,
  `BROWSER_TITLE` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interface
-- ----------------------------
INSERT INTO `interface` VALUES ('5', '44621D2D-EE99-DCB4-15C7-880F1534B051', 'OA办公平台', '', 'Copyright @ ', '欢迎使用慧客OAV1.04', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for listtestmingxi
-- ----------------------------
DROP TABLE IF EXISTS `listtestmingxi`;
CREATE TABLE `listtestmingxi` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RUN_ID` varchar(50) DEFAULT NULL,
  `TAG` varchar(50) DEFAULT NULL,
  `A` varchar(20) DEFAULT NULL,
  `B` varchar(20) DEFAULT NULL,
  `C` varchar(20) DEFAULT NULL,
  `D` varchar(20) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of listtestmingxi
-- ----------------------------

-- ----------------------------
-- Table structure for loveaa
-- ----------------------------
DROP TABLE IF EXISTS `loveaa`;
CREATE TABLE `loveaa` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RUN_ID` varchar(50) DEFAULT NULL,
  `TAG` varchar(50) DEFAULT NULL,
  `NAME` varchar(10) DEFAULT NULL,
  `DANJIA` varchar(10) DEFAULT NULL,
  `SHULIANG` varchar(10) DEFAULT NULL,
  `XINGHAO` varchar(10) DEFAULT NULL,
  KEY `ID` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loveaa
-- ----------------------------

-- ----------------------------
-- Table structure for loveaaa
-- ----------------------------
DROP TABLE IF EXISTS `loveaaa`;
CREATE TABLE `loveaaa` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RUN_ID` varchar(50) DEFAULT NULL,
  `TAG` varchar(50) DEFAULT NULL,
  `NAME` varchar(10) DEFAULT NULL,
  `DANJIA` varchar(10) DEFAULT NULL,
  `SHULIANG` varchar(10) DEFAULT NULL,
  `XINGHAO` varchar(10) DEFAULT NULL,
  KEY `ID` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loveaaa
-- ----------------------------

-- ----------------------------
-- Table structure for mark
-- ----------------------------
DROP TABLE IF EXISTS `mark`;
CREATE TABLE `mark` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MARK_ID` varchar(50) DEFAULT NULL,
  `CONTENT` varchar(200) DEFAULT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `GOOD` text,
  `ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mark
-- ----------------------------

-- ----------------------------
-- Table structure for meetingrequest
-- ----------------------------
DROP TABLE IF EXISTS `meetingrequest`;
CREATE TABLE `meetingrequest` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `REQUEST_ID` varchar(100) DEFAULT NULL,
  `ATTEND_STAFF` text,
  `SELECT_DEPT` text,
  `MEETING_NAME` varchar(200) DEFAULT NULL,
  `MEETING_THEME` varchar(200) DEFAULT NULL,
  `BOARDROOM_ID` varchar(100) DEFAULT NULL,
  `BOARDOOM_STAFF` varchar(100) DEFAULT NULL,
  `MEETING_TYPE` varchar(1) DEFAULT NULL,
  `MEETING_STARTTIME` varchar(50) DEFAULT NULL,
  `MEETING_ENDTIME` varchar(50) DEFAULT NULL,
  `MEETING_DEVICE` text,
  `MEETING_SUMMAN` varchar(50) DEFAULT NULL,
  `ATTACH_ID` text,
  `MEETING_DESCRIPTION` text,
  `MEETING_STATUS` int(11) DEFAULT NULL,
  `CREATE_USER` varchar(100) DEFAULT NULL,
  `SUMMARY_STATUS` varchar(1) DEFAULT '0',
  `ORG_ID` varchar(50) DEFAULT NULL,
  `WARN_TIME` varchar(50) DEFAULT NULL,
  `IS_SMS` varchar(200) DEFAULT NULL,
  `CYC_TYPE` varchar(10) DEFAULT NULL,
  `CYC_ENDTIME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meetingrequest
-- ----------------------------

-- ----------------------------
-- Table structure for meetingsummary
-- ----------------------------
DROP TABLE IF EXISTS `meetingsummary`;
CREATE TABLE `meetingsummary` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUMMARY_ID` varchar(100) DEFAULT NULL,
  `REQUEST_ID` varchar(100) DEFAULT NULL,
  `MEETING_NAME` varchar(200) DEFAULT NULL,
  `LOOK_STAFF` text,
  `SUMMARY_STAFF` varchar(200) DEFAULT NULL,
  `ASK_STAFF` varchar(100) DEFAULT NULL,
  `REALITY_STAFF` text,
  `SUMMARY_CONTENT` text,
  `ATTACH_ID` varchar(100) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meetingsummary
-- ----------------------------

-- ----------------------------
-- Table structure for message_config
-- ----------------------------
DROP TABLE IF EXISTS `message_config`;
CREATE TABLE `message_config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `M_CONFIG_ID` varchar(50) DEFAULT NULL,
  `MODULE` varchar(50) DEFAULT NULL,
  `VALUE` varchar(500) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_config
-- ----------------------------
INSERT INTO `message_config` VALUES ('1', null, 'workNext', '{\"sms1\":\"0\",\"sms2\":\"1\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('2', null, 'passTime', '{\"sms1\":\"2\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('3', null, 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"2\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('4', null, 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"1\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('5', null, 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"1\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('6', null, 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('7', null, 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('8', null, 'meet', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('9', null, 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('10', null, 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '1');
INSERT INTO `message_config` VALUES ('11', '', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('12', '', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('13', '', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('14', '', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('15', '', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('16', '', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('17', '', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('18', '', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('19', '', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('20', '', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '7DB01C98-4DA0-D332-4608-6F27EA40E5B2');
INSERT INTO `message_config` VALUES ('21', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'workNext', '{\"sms1\":\"1\",\"sms2\":\"1\",\"sms3\":\"1\",\"sms4\":\"1\",\"sms5\":\"1\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('22', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('23', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('24', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'workFollow', '{\"sms1\":\"1\",\"sms2\":\"1\",\"sms3\":\"1\",\"sms4\":\"1\",\"sms5\":\"1\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('25', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('26', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'news', '{\"sms1\":\"1\",\"sms2\":\"1\",\"sms3\":\"1\",\"sms4\":\"1\",\"sms5\":\"1\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('27', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('28', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('29', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('30', 'EAA2962B-42EF-75D1-13C7-49CCA8CA9E9D', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"0\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `message_config` VALUES ('31', 'E555F28E-5B40-094E-8914-1DFF0958CCDB', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('32', '89CF6CC2-820B-E539-8E58-78B1F3AD540E', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('33', '952AF48F-1D4B-69D5-F24E-5552F082F3BC', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('34', 'C2344002-1994-661E-2CBA-5BD45A3ABB9B', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('35', '7FA1675D-85B9-75C7-31FD-FA71B0514912', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('36', '0B44B191-228E-820C-38AD-024CCBAC6E2E', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('37', 'BEEF7B4A-E542-41B4-D8AD-240E83E0B9DF', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('38', '7B929A31-13C8-347A-0604-1765BBE26901', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('39', 'E89384DD-FD57-DF44-4C5F-BD818AA3D0E8', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('40', '93A8563F-66CA-5254-05F1-B8B6EE10B760', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `message_config` VALUES ('41', '9C39416A-41BC-FF2A-D152-9C48FCE1199A', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('42', '02912032-C99A-2207-8607-7D8F4884CC3F', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('43', '330B7F74-874A-597D-638F-B688F7E1BD9D', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('44', '49A1917E-8B2B-A06A-61F3-C697A2A5D191', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('45', 'E15BF943-5D77-8998-6C8A-621F97768161', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('46', '65A6F7A4-2D6D-EBFA-5DB6-FEE394ADFA4F', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('47', 'C98D3D4B-EC03-7EBE-E9FA-AEBD0760F553', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('48', '7B211D68-92F4-E285-F454-64B43CBB7ED2', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('49', 'D46EDB01-2364-95E8-1674-3A1D405CBAAA', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('50', 'CE2B9C3E-193C-E9A6-698D-71C9DD128751', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `message_config` VALUES ('51', '2D9E56FC-FA14-30C2-59A4-19D338BBDE8A', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('52', '7859E9AA-DA52-7301-69A1-A8843B0381FD', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('53', '14312B97-7390-23CB-D735-626080368DA6', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('54', '6122BF4F-984D-5DCD-0374-97EB79911AB0', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('55', '920D90E0-2235-5070-960F-35C28C401BE4', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('56', '7168E6B7-08DF-A5B3-219D-C26C4A5D4860', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('57', 'AA1BB6D8-CB13-789C-5A71-2CFD63DC3188', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('58', '526E8293-4C43-25D2-872F-F5CC873B9878', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('59', 'EC6181AF-E972-8DC2-C059-8864F98E3BFE', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('60', 'DFC0C762-133D-7DB8-6508-4AEDF612D029', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `message_config` VALUES ('61', 'FC156E3A-0505-C5EB-ACF3-5C514140784B', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('62', 'C0CDE512-5BE5-E019-83F0-81D6D3C32FFF', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('63', 'D7CDE7AB-0820-4393-6EED-59810C359090', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('64', 'C344B3B5-3029-FA9E-FB29-A854C88037A3', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('65', '7BBF0D5C-5BD7-9E6E-4514-BD9D523C0E6A', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('66', '85EE56D4-8392-B628-3044-6CAB05188BAF', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('67', '4690976A-6310-DF27-52A2-6328D4C6859F', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('68', 'DA429AB9-E67A-CE6F-F0B8-FEFA8051E0BD', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('69', 'D758E056-7595-FDD9-78FE-BAE990765CEC', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('70', 'D36CBE93-D8E2-1E52-79C4-225A8DB613D4', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `message_config` VALUES ('71', '857E2C35-1C25-ED7F-B429-054D1746B177', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('72', 'CC170BEC-15EC-850E-794C-1757AB4BDA80', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('73', 'AC0C367D-8B80-E5AB-E116-27EBAB4A9B70', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('74', 'AA8B2D3B-D155-B656-2823-FCC2EB8F2DE6', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('75', '33517229-77FC-A4B5-9DDB-470CA62BE66A', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('76', 'D3E14EDC-CF1B-9A4F-C53A-1C03B9E6322C', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('77', '46600E69-45F5-DDC0-E634-132E1581A7A0', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('78', '2574B466-136F-3C27-70DD-0FD2B19C3342', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('79', 'AA42F491-9DAC-2F52-DA15-13BA8D84F7D2', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('80', '6222E626-A29F-6D65-9A6E-7E370C1F7217', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `message_config` VALUES ('81', '046F8F41-865B-21F2-613D-D214A4954FEC', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('82', '7757BD0B-ED7E-9A7C-3716-B909AECE0B64', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('83', '27D3DBC5-9E28-E625-EF0D-9D1BE60173BC', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('84', '0F3DCD1B-1886-DC10-1AEC-DC4E7C4802F2', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('85', '01724233-3149-606C-567D-565725F56DF2', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('86', '6C8D6D1B-B7DA-4822-2AE3-99C57DF595BB', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('87', '39C5EDF3-3EA3-9A7F-92C8-2AD4ECCFD519', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('88', '4666D7C0-D2AF-5908-34A9-F39B72BCC2C9', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('89', '01119FB4-F0F7-F078-E144-F4F8254C5967', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('90', 'C960590E-B5B1-A76A-97F0-EFDF49D99A09', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `message_config` VALUES ('91', '59D57C55-DC1D-C926-CF17-E32E7B8FBEA5', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('92', 'AB432130-DFE2-1AF2-D8BC-0339504F4D2C', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('93', '4B4022A2-8E8F-2FC0-BA9A-A4345E03206A', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('94', 'BCE20246-728C-A0DF-54C0-74A209FF69E0', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('95', '944B7152-8B1B-E6F1-ADF9-A593D1319726', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('96', 'A3245D49-6291-43F2-548C-80E662F1631D', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('97', 'AC280862-F261-94F0-5C52-8185C8177FFC', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('98', '17C9B7C8-5284-BB73-36DE-10DC7561219A', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('99', 'FD4A9A95-ABD9-345A-1CC5-F667BA85957C', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('100', 'FB831B94-38CE-6EAE-6C36-1BC28E522CD6', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `message_config` VALUES ('101', 'CE401EBD-7972-E6E4-1AC4-91F1C67FFE8E', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('102', '82CF5FCE-9A92-C5F1-0562-2CA5F5FA9C3D', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('103', '2AAE836A-6E10-83C7-3CFC-0434B8C0D6B5', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('104', 'BE50FCFD-3315-4963-1E1C-214BB1B6BE2E', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('105', 'F6B3AAB4-4E59-EE4D-F7C5-FF39FAF7173B', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('106', '94609EC8-847A-83CE-E091-473BA88DE17C', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('107', '6BDF7EC6-239E-F324-743E-6A042ED98AAB', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('108', '5CE7D425-20C2-F7BD-B5D9-EC02B8034484', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('109', '1520FE42-8ED7-8C85-208E-BD8C6B0279C8', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('110', 'B538EC28-02D5-77C6-C0AD-A305E23F316E', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `message_config` VALUES ('111', '245DB14B-3F3C-0AEF-A427-614D98FC3F52', 'workNext', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `message_config` VALUES ('112', 'BB0BD84E-976C-540F-676C-25821140E01A', 'passTime', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `message_config` VALUES ('113', '05F7207E-7280-DE85-C5B4-0B9635A3BB51', 'workEnd', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `message_config` VALUES ('114', '819C6627-2C57-95C3-5C93-8581D507957E', 'workFollow', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `message_config` VALUES ('115', '1278C7E2-B4EE-6EEE-D7B1-DD062A68DC25', 'notice', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `message_config` VALUES ('116', '4C944A38-F08E-B307-3493-935506657455', 'news', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `message_config` VALUES ('117', 'F2708C18-D001-88EE-52C6-4A0F8C9DCF55', 'calendar', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `message_config` VALUES ('118', 'F5BDB68E-C935-09DC-FE62-C5A24B4B68F3', 'diary', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `message_config` VALUES ('119', '554176B6-B2EF-A16C-022D-C77D61A1ACAA', 'meeting', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');
INSERT INTO `message_config` VALUES ('120', 'F6A8016B-F26A-4619-8289-3507237C5B69', 'email', '{\"sms1\":\"0\",\"sms2\":\"0\",\"sms3\":\"2\",\"sms4\":\"0\",\"sms5\":\"0\"}', '2A096A25-4E62-8426-F80F-6522BB06FF3F');

-- ----------------------------
-- Table structure for mobile_sms
-- ----------------------------
DROP TABLE IF EXISTS `mobile_sms`;
CREATE TABLE `mobile_sms` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MOBILE_SMS_ID` varchar(50) DEFAULT NULL,
  `SEND_ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `SEND_CONTENT` text,
  `REV_MOBLIE_NUMBER` varchar(20) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `SEND_TIME` varchar(20) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL,
  `DEL_FLAG` varchar(2) DEFAULT NULL,
  `SEND_COUNT` varchar(4) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mobile_sms
-- ----------------------------
INSERT INTO `mobile_sms` VALUES ('1', '04CEEEF7-AC73-B0D8-BFBD-3BD3BF0B62D0', 'admin', '请您处理一下标题为：7777的流程！', '13382036127', '2015-09-08 16:36:32', '2015-09-08 16:36:32', '', '', '', '1');
INSERT INTO `mobile_sms` VALUES ('2', 'F30D63F1-F4B8-233C-2E1A-1933FD956511', 'admin', '请您处理一下标题为：66666的流程！', '13382036127', '2015-09-08 16:37:04', '2015-09-08 16:37:04', '', '', '', '1');
INSERT INTO `mobile_sms` VALUES ('3', '16C0F977-CEAD-4A74-8BF2-CA74F85E35D2', 'admin', '请您处理一下标题为：5555555555的流程！', '13666669988', '2015-10-16 15:35:41', '2015-10-16 15:35:41', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('4', '05DA23AE-3B8F-5F0D-8EDC-62282576384C', 'admin', '请您处理一下标题为：111111111111111的流程！', '13577778888', '2015-10-16 15:36:03', '2015-10-16 15:36:03', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('5', '3FDCE296-62F3-6250-C10E-6F3808D24BB7', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:30:13', '2015-12-18 09:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('6', '28878EF9-7FFC-E0A8-61FF-B279212CAA09', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:32:54', '2015-12-25 09:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('7', '0C4BFA9F-74EF-8D62-E1E7-C1CDC6FEEE61', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:58:58', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('8', '8257D872-A2F4-B26D-FDE2-56537BE446C7', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:59:03', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('9', 'FBBABBE6-D71D-8E46-E9C9-8509531188AD', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:59:07', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('10', '0E3525FF-8382-CE02-54D2-FB0B8DE4C75D', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:59:15', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('11', 'B3D5476E-A04D-F3E1-105A-1B4999318342', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:59:20', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('12', 'F6CEC40E-E88D-D239-D293-430D37A9B4E6', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:59:24', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('13', '612AE2A8-DA9D-7C44-CAEC-C1EDABC8A8EB', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:59:29', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('14', 'DB0F2760-8F32-FAC0-384C-0B295FDD7269', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 15:59:34', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('15', '88F63B8A-EF63-DBBD-FB68-B33358A57D9F', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:00:07', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('16', 'A2E0419F-958B-6F8B-5097-6B2A4543A848', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:00:21', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('17', '841A7D95-FE64-2A7A-A20C-9D219DB72D4F', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:00:28', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('18', 'C7443CE0-A287-F595-2B40-DAB691A665A4', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:00:57', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('19', 'A655EEF5-9576-A3DB-8871-69191FAC0233', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:01:01', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('20', '24FD0DAB-065D-8DFB-781E-D887311F81C6', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:01:07', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('21', '73868045-8079-211A-907F-1587AFF28D4A', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:01:11', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('22', '26FCF7C4-DE23-9A7B-3512-E05DADE3EAE0', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:01:17', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('23', '5A92779F-FC12-36BB-A3D2-4D2FDFFEC11F', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:01:22', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('24', 'DEF1B538-9AFF-4477-0382-21EE1F961A8F', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:01:27', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('25', '5AE961EB-2820-114B-A058-92AA8BAEC348', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:02:26', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('26', '2A00425C-F261-52C5-D530-C9122A706CC7', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:02:31', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('27', '3DABA175-71AF-1BE4-7A1B-668BEAB78211', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:03:04', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('28', '0EB9CA8C-A8C6-E5B7-7C42-3D36939EA969', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:03:08', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('29', '78FD3A31-7547-4B6F-4374-175CB915AD1F', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:03:13', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('30', 'C68B27BD-FDD9-DB68-8F4D-F0862C40F9D6', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:03:18', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('31', '0FC7C7F6-33E8-A839-241A-2681F097B899', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:03:23', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('32', '15934C5D-127A-60F1-E2DD-83F7BE173854', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:03:28', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('33', 'BB6BFF37-8B63-1B99-991E-513F783FA14B', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:03:32', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('34', 'AB20832D-7680-9B3E-BE10-3ADBA3404902', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:06:13', '2015-12-08 10:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('35', '8E1C6A44-A8E5-86F7-1D00-EC5FB4744A18', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:06:17', '2015-12-08 10:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('36', 'B34EFFF0-4BC3-DD0A-8227-D9E7928AA29B', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:06:21', '2015-12-08 10:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('37', 'A47E8747-B7C4-483A-005A-5FAEC14ED460', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:06:28', '2015-12-08 10:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('38', '97B96462-EB00-5E14-DCFA-6A5A57ED7BA7', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:06:32', '2015-12-08 10:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('39', '0040BD77-F163-1A49-25D1-9EB789BE1E04', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:06:37', '2015-12-08 10:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('40', '698AB3D3-9EE5-AD6A-0283-01190889F92C', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:06:43', '2015-12-08 10:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('41', 'C2BC9B3D-4D01-D6AA-9788-82A7D2B85816', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:06:50', '2015-12-08 11:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('42', '69CFE9D5-1A94-4F3F-066A-A1AB6F3AE4EE', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:06:58', '2015-12-08 11:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('43', 'BC90F51C-3514-33F8-2606-072D19CEFAE7', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:02', '2015-12-08 11:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('44', '2394C8C4-9369-D6C3-9488-590361B03B5C', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:06', '2015-12-08 11:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('45', '5ECC15B5-9E4C-6E20-CBDD-9969E9BD732F', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:10', '2015-12-08 11:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('46', '7B7B9719-59B0-4E7A-F124-EE487BACE362', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:15', '2015-12-08 11:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('47', '5B53F50B-1AD2-3EC3-5775-954F875A8F0D', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:19', '2015-12-08 11:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('48', '0EAEE02C-DB73-EEB2-2FDA-603A2E16267C', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:25', '2015-12-08 11:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('49', '5BC9471A-4186-6EBC-5929-8C5FEB180274', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:31', '2015-12-08 11:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('50', '426433C7-CEDD-A91A-7A71-C68CFAA450EB', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:36', '2015-12-08 11:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('51', '6CF00C64-E35F-75BD-FCFB-31EA739FEC44', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:40', '2015-12-08 11:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('52', '8E9B9301-17D4-86D2-AB2A-7C7E2A0786D8', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:44', '2015-12-08 11:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('53', '96D65B04-BFEB-A032-687F-39901928DDF3', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:47', '2015-12-08 11:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('54', 'D5439874-7548-4282-8694-2E69C8649355', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:07:51', '2015-12-08 11:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('55', 'ABA2893D-188F-E667-0C18-A33A8FA35096', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:08:05', '2015-12-08 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('56', '325621AF-0A5B-9257-DD95-F0C8009BB3C0', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:08:09', '2015-12-08 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('57', '6413BB57-2E87-2DD9-6173-876575C020BE', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:08:13', '2015-12-08 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('58', '8142CB7D-2643-6885-0006-4C5458DBE865', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:08:17', '2015-12-08 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('59', '9DA5FF7E-D096-2DBA-A18B-44EE479BA2A4', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:08:27', '2015-12-08 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('60', 'CA76EDF6-A258-61BD-4F6E-72A190363E90', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:08:32', '2015-12-08 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('61', 'C63348EE-16CA-5FDD-F35C-80AE9CD8AA54', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:08:36', '2015-12-08 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('62', '63CF48CB-0813-22EF-0E34-C7C05C6CBF09', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:11:32', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('63', '23BE4B3E-6EFC-A71F-8906-FF725EBD50A0', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:11:37', '2015-12-08 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('64', 'F69F973F-AE04-A833-2140-EB7BF4A820B6', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:11:42', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('65', '1792838A-282B-93B0-97D5-C201D32D7256', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:11:46', '2015-12-08 09:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('66', 'CFA4F81D-3C15-DF3D-2951-EBDDB2A64047', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:11:51', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('67', 'F7B0C748-8234-3A0E-1EDD-1585825BB260', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:11:55', '2015-12-08 10:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('68', '0D5DD5AC-573D-886B-BA99-EAA074FD2FB3', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:12:03', '2015-12-08 10:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('69', '3AD7EBC6-BD51-5AAF-2BA3-9F026CCAC141', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:12:07', '2015-12-08 10:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('70', 'E6A1D43F-2AFD-0008-EAE4-EDD0D8E6064C', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:12:12', '2015-12-08 11:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('71', 'B3B7D745-FB86-88C3-AA5C-0DFD2A8EFCF5', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:12:17', '2015-12-08 11:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('72', '2265170F-6E33-EF9A-0BF7-21F3F0D6E35B', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:12:22', '2015-12-08 11:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('73', 'CBAC57AA-90F2-1A23-3CC7-257E961F71C0', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:12:55', '2015-12-08 11:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('74', '08F65F7C-71BE-D44A-2177-B91D63B8F1B5', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:12:59', '2015-12-08 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('75', '8F0DA4BA-2066-147B-3BF7-E52BCFC86E2B', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-09 16:13:04', '2015-12-08 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('76', 'FE46950F-A64D-B103-BEFC-16271A277A17', 'admin', '您有新的日程，请注意查看。', '15380775642', '2015-12-10 10:44:51', '2015-12-07 00:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('77', '02FD962B-6B4B-CAE2-A974-8215272E1D69', 'admin', '您有新的日程，请注意查看。', '15005146118', '2015-12-10 10:46:31', '2015-12-11 00:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('78', 'D904C0B5-3B55-5BA0-C075-42D44AADC8E2', 'admin', '您有新的日程，请注意查看。', '', '2015-12-10 11:14:17', '2015-12-07 00:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('79', 'D904C0B5-3B55-5BA0-C075-42D44AADC8E2', 'admin', '您有新的日程，请注意查看。', '', '2015-12-10 11:14:17', '2015-12-07 00:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('80', 'D904C0B5-3B55-5BA0-C075-42D44AADC8E2', 'admin', '您有新的日程，请注意查看。', '', '2015-12-10 11:14:17', '2015-12-07 00:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('81', 'D904C0B5-3B55-5BA0-C075-42D44AADC8E2', 'admin', '您有新的日程，请注意查看。', '', '2015-12-10 11:14:17', '2015-12-07 00:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('82', 'D904C0B5-3B55-5BA0-C075-42D44AADC8E2', 'admin', '您有新的日程，请注意查看。', '', '2015-12-10 11:14:17', '2015-12-07 00:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('83', '6048FCF2-A198-D497-22A3-323273035342', 'admin', '您有新的日程，请注意查看。', '13253514712', '2015-12-10 17:29:03', '2015-12-09 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('84', 'F90F3D75-E774-CFC6-1404-63903C15E44E', 'yzz', '您有新的日程，请注意查看。', '13382036126', '2016-01-07 17:28:26', '2016-01-05 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('85', 'F90F3D75-E774-CFC6-1404-63903C15E44E', 'yzz', '您有新的日程，请注意查看。', '15951884250', '2016-01-07 17:28:26', '2016-01-05 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('86', '4EC4E507-A9A0-868C-0F24-8AC8B31EE99D', 'yzz', '您有新的日程，请注意查看。', '15951884250', '2016-01-08 16:42:17', '2016-01-07 12:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('87', 'C2317E4A-D7A4-C0C2-EB9F-1EBCA09DF2D1', 'yzz', '您有新的日程，请注意查看。', '15951884250', '2016-01-25 15:19:19', '2016-01-30 13:30:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('88', '2AC15B88-DDA0-8EE9-2B79-188002C527BB', 'admin', '您有新的日程，请注意查看。', '13382036126', '2016-09-08 19:25:41', '2016-09-05 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('89', '2E0CCD0D-F114-D44F-532F-B01CA6AE9EF2', 'admin', '您有新的日程，请注意查看。', '13382036126', '2016-09-08 19:29:13', '2016-09-05 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `mobile_sms` VALUES ('90', '2E0CCD0D-F114-D44F-532F-B01CA6AE9EF2', 'admin', '您有新的日程，请注意查看。', '13382036126', '2016-09-08 19:29:13', '2016-09-05 09:00:00', '', '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for nbbx
-- ----------------------------
DROP TABLE IF EXISTS `nbbx`;
CREATE TABLE `nbbx` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RUN_ID` varchar(50) DEFAULT NULL,
  `CHILD_RUN_ID` varchar(200) DEFAULT NULL,
  `PARENT_RUN_ID` varchar(50) DEFAULT NULL,
  `BXR` text,
  `BXRQ` text,
  `BXMX` tinytext,
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nbbx
-- ----------------------------

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NEWS_ID` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  `TITLE` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  `TYPE` varchar(20) CHARACTER SET gbk DEFAULT NULL,
  `ACCOUNT_ID` text CHARACTER SET gbk,
  `DEPT_PRIV` text,
  `USER_PRIV` text,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `END_TIME` varchar(20) DEFAULT NULL,
  `ATTACH_ID` varchar(2000) DEFAULT NULL,
  `CONTECT` text,
  `ONCLICK_COUNT` int(5) DEFAULT '0',
  `DEL_FLAG` varchar(1) DEFAULT '0',
  `TOP` varchar(1) DEFAULT '0',
  `READER` text,
  `STATUS` varchar(2) DEFAULT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `APPROVAL_STAFF` varchar(50) DEFAULT NULL,
  `IS_SMS` varchar(400) DEFAULT NULL,
  `COMMENT_STATUS` varchar(20) DEFAULT NULL,
  `APPROVAL_STATUS` varchar(2) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE,
  KEY `NEWS_ID` (`NEWS_ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=172 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for news_comments
-- ----------------------------
DROP TABLE IF EXISTS `news_comments`;
CREATE TABLE `news_comments` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMM_ID` varchar(150) DEFAULT NULL,
  `COMM_PID` varchar(150) DEFAULT NULL,
  `COMM_CONTECT` text,
  `COMM_TIME` varchar(60) DEFAULT NULL,
  `NEWS_ID` varchar(150) DEFAULT NULL,
  `ACCOUNT_ID` varchar(150) DEFAULT NULL,
  `ORG_ID` varchar(150) DEFAULT NULL,
  `COMM_NAME` varchar(150) DEFAULT NULL,
  KEY `Id` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_comments
-- ----------------------------

-- ----------------------------
-- Table structure for news_power
-- ----------------------------
DROP TABLE IF EXISTS `news_power`;
CREATE TABLE `news_power` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `POWER_ID` varchar(100) DEFAULT NULL,
  `ACCOUNT_ID` varchar(100) DEFAULT NULL,
  `POWER_STATUS` int(1) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_power
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOTICE_ID` varchar(50) DEFAULT NULL,
  `NOTICE_TITLE` varchar(255) DEFAULT NULL,
  `NOTICE_TYPE` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` text,
  `DEPT_PRIV` text,
  `USER_PRIV` text,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `ATTACH_ID` text,
  `ATTACH_PRIV` text,
  `NOTICE_CONTENT` text,
  `ONCLICK_COUNT` int(5) DEFAULT '0',
  `DEL_FLAG` varchar(1) DEFAULT '0',
  `TOP` varchar(1) DEFAULT '0',
  `READER` text,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `APPROVAL_STATUS` varchar(1) DEFAULT '0',
  `NOTICE_STATUS` varchar(1) DEFAULT '0',
  `IS_SMS` varchar(400) DEFAULT NULL,
  `APPROVAL_STAFF` varchar(50) DEFAULT NULL,
  `END_TIME` varchar(20) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  `ATTACH_POWER` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for off_allot
-- ----------------------------
DROP TABLE IF EXISTS `off_allot`;
CREATE TABLE `off_allot` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ALLOT_ID` varchar(100) DEFAULT NULL,
  `RES_ID` varchar(100) DEFAULT NULL,
  `LIBRARY_ID` varchar(100) DEFAULT NULL,
  `CLASSIFY_ID` varchar(100) DEFAULT NULL,
  `ALLOT_NUM` int(11) DEFAULT NULL,
  `ALLOT_STATUS` int(11) DEFAULT '0',
  `APPROVAL_STAFF` varchar(100) DEFAULT NULL,
  `ALLOT_STAFF` varchar(100) DEFAULT NULL,
  `DEPOT_STAFF` varchar(100) DEFAULT NULL,
  `ALLOT_TIME` varchar(20) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of off_allot
-- ----------------------------

-- ----------------------------
-- Table structure for off_library
-- ----------------------------
DROP TABLE IF EXISTS `off_library`;
CREATE TABLE `off_library` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LIBRARY_ID` varchar(100) DEFAULT NULL,
  `LIBRARY_NAME` varchar(200) DEFAULT NULL,
  `BELONG_DEPT` text,
  `LIBRARY_STAFF` varchar(100) DEFAULT NULL,
  `DISPATCH_STAFF` varchar(100) DEFAULT NULL,
  `DEL_STATUS` int(1) DEFAULT '0',
  `TOP_ID` varchar(100) DEFAULT NULL,
  `SORT_ID` int(11) DEFAULT '0',
  `ORG_ID` varchar(50) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of off_library
-- ----------------------------
INSERT INTO `off_library` VALUES ('24', '6BFC2F59-A2BC-0BF0-B682-CA4C1036A9BC', '五金仓库', 'A2CE8224-06C1-4AF2-750A-FB6F75DF9191', 'liuqian', 'lisi', '1', null, '0', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_library` VALUES ('27', '8F754777-74E8-443A-F4AA-1DC913AD13C0', '辅材', 'A2CE8224-06C1-4AF2-750A-FB6F75DF9191', 'liuqian', 'lisi', '0', '6BFC2F59-A2BC-0BF0-B682-CA4C1036A9BC', '3', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_library` VALUES ('25', '63408CC2-F243-192E-5A6A-7F507AF64DF1', '五金配件', 'A2CE8224-06C1-4AF2-750A-FB6F75DF9191', 'liuqian', 'lisi', '0', '6BFC2F59-A2BC-0BF0-B682-CA4C1036A9BC', '1', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_library` VALUES ('26', 'D9CCCB35-F937-4166-8EF3-8C9C9A22B3B2', '主材', 'A2CE8224-06C1-4AF2-750A-FB6F75DF9191', 'liuqian', 'lisi', '0', '6BFC2F59-A2BC-0BF0-B682-CA4C1036A9BC', '2', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_library` VALUES ('28', 'D0321982-7114-B642-5990-AB1E1BE363F6', '铅笔、水笔', '3298B539-E64C-5941-1158-4F285220C0C1', 'admin', '白景琦', '1', null, null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_library` VALUES ('29', 'A755EA03-1A33-71AF-ABA8-B96ED8B35D5D', '纸笔类', '3298B539-E64C-5941-1158-4F285220C0C1', 'admin', '白景琦', '0', 'D0321982-7114-B642-5990-AB1E1BE363F6', '1', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_library` VALUES ('30', 'AC146BA4-2431-74EF-1C0D-25B43B7E1C15', '办公纸笔', '3298B539-E64C-5941-1158-4F285220C0C1', 'admin', '白景琦', '0', null, '0', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_library` VALUES ('31', 'AD8471E9-4AF5-DBAA-6999-DBBBDA28203B', '纸', '3298B539-E64C-5941-1158-4F285220C0C1', 'admin', '白景琦', '0', 'AC146BA4-2431-74EF-1C0D-25B43B7E1C15', '1', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_library` VALUES ('32', '9C785DBC-7EEC-C662-054A-F789E68F59FC', '笔', '3298B539-E64C-5941-1158-4F285220C0C1', 'admin', '白景琦', '0', 'AC146BA4-2431-74EF-1C0D-25B43B7E1C15', '2', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for off_maint
-- ----------------------------
DROP TABLE IF EXISTS `off_maint`;
CREATE TABLE `off_maint` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MAINT_ID` varchar(100) DEFAULT NULL,
  `MAINT_TYPE` varchar(50) DEFAULT NULL,
  `LIBRARY_ID` varchar(100) DEFAULT NULL,
  `CLASSIFY_ID` varchar(100) DEFAULT NULL,
  `RES_ID` varchar(100) DEFAULT NULL,
  `RES_PRICE` varchar(100) DEFAULT NULL,
  `MAINT_NUM` int(11) DEFAULT NULL,
  `MAINT_REMARY` text,
  `MAINT_TIME` varchar(20) DEFAULT NULL,
  `CREATE_USER` varchar(100) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of off_maint
-- ----------------------------
INSERT INTO `off_maint` VALUES ('16', 'CE535464-36F1-9B9D-BB85-D3B9DC90FE66', '采购入库', '6BFC2F59-A2BC-0BF0-B682-CA4C1036A9BC', '63408CC2-F243-192E-5A6A-7F507AF64DF1', '请选择', '1.00', '10', '螺丝', '2015-10-16 11:24:59', 'liuqian', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_maint` VALUES ('15', 'FC097FBF-55E4-68F6-3660-D84662571F72', '采购入库', '6BFC2F59-A2BC-0BF0-B682-CA4C1036A9BC', '63408CC2-F243-192E-5A6A-7F507AF64DF1', '请选择', '1.00', '10', '螺丝', '2015-10-16 11:23:10', 'liuqian', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `off_maint` VALUES ('14', '3F344A28-54BE-6CE5-6510-F575A3C0E724', '采购入库', '6BFC2F59-A2BC-0BF0-B682-CA4C1036A9BC', '63408CC2-F243-192E-5A6A-7F507AF64DF1', '请选择', '1.00', '10', '螺丝', '2015-10-16 11:23:07', 'liuqian', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for off_res
-- ----------------------------
DROP TABLE IF EXISTS `off_res`;
CREATE TABLE `off_res` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RES_ID` varchar(100) DEFAULT NULL,
  `RES_NAME` varchar(100) DEFAULT NULL,
  `RES_TYPE` varchar(100) DEFAULT NULL,
  `LIBRARY_ID` varchar(100) DEFAULT NULL,
  `CLASSIFY_ID` varchar(100) DEFAULT NULL,
  `BEFORESTOCK` varchar(100) DEFAULT NULL,
  `MINSTOCK` varchar(100) DEFAULT NULL,
  `MAXSTOCK` varchar(100) DEFAULT NULL,
  `APPROVE_STAFF` varchar(100) DEFAULT NULL,
  `DISPATCH_STAFF` varchar(100) DEFAULT NULL,
  `RES_FORMAT` varchar(1000) DEFAULT NULL,
  `ATTACH_ID` text,
  `RES_UNIT` varchar(100) DEFAULT NULL,
  `RES_PRICE` varchar(100) DEFAULT NULL,
  `RES_SUPPLIER` varchar(200) DEFAULT NULL,
  `DEPT_PRIV` text,
  `USER_PRIV` text,
  `ORG_ID` varchar(50) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of off_res
-- ----------------------------

-- ----------------------------
-- Table structure for off_resapply
-- ----------------------------
DROP TABLE IF EXISTS `off_resapply`;
CREATE TABLE `off_resapply` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `APPLY_ID` varchar(100) DEFAULT NULL,
  `LIBRARY_ID` varchar(100) DEFAULT NULL,
  `CLASSIFY_ID` varchar(100) DEFAULT NULL,
  `RES_ID` varchar(100) DEFAULT NULL,
  `RES_TYPE` varchar(20) DEFAULT NULL,
  `APPLY_NUM` varchar(11) DEFAULT NULL,
  `APPROVAL_STAFF` varchar(100) DEFAULT NULL,
  `DISPATCH_STAFF` varchar(100) DEFAULT NULL,
  `APPLY_REMARY` text,
  `CREATE_USER` varchar(100) DEFAULT NULL,
  `APPLY_TIME` varchar(50) DEFAULT NULL,
  `APPLY_STATUS` int(11) DEFAULT '0',
  `GIVE_STATUS` int(1) DEFAULT '0',
  `ORG_ID` varchar(50) DEFAULT NULL,
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of off_resapply
-- ----------------------------

-- ----------------------------
-- Table structure for org_config
-- ----------------------------
DROP TABLE IF EXISTS `org_config`;
CREATE TABLE `org_config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORG_NAME` varchar(50) DEFAULT NULL,
  `ORG_ADMIN` varchar(50) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `ATTACH_MAX` int(11) DEFAULT '4',
  `TYPE` varchar(10) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_config
-- ----------------------------
INSERT INTO `org_config` VALUES ('7', '', 'admin', '2015-10-01 00:00:00', '4', 'PC', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHOTO_ID` varchar(50) DEFAULT NULL,
  `SORT_ID` int(11) DEFAULT NULL,
  `PHOTO_NAME` varchar(50) DEFAULT NULL,
  `PHOTO_PATH` varchar(2000) DEFAULT NULL,
  `CREATE_ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `CREATE_DATE` varchar(20) DEFAULT NULL,
  `READ_ACCOUNT_ID` text,
  `READ_DEPT_ID` text,
  `READ_USER_PRIV_ID` text,
  `MANAGE_ACCOUNT_ID` text,
  `MANAGE_DEPT_ID` text,
  `MANAGE_USER_PRIV_ID` text,
  `PHOTO_COVER` varchar(2000) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------

-- ----------------------------
-- Table structure for photo_info
-- ----------------------------
DROP TABLE IF EXISTS `photo_info`;
CREATE TABLE `photo_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHOTO_INFO_ID` varchar(50) DEFAULT NULL,
  `PHOTO_INFO_NAME` varchar(200) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_ACCOUNT_ID` varchar(200) DEFAULT NULL,
  `GOOD` text,
  `PHOTO_PATH` varchar(2000) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=182 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo_info
-- ----------------------------

-- ----------------------------
-- Table structure for public_disk
-- ----------------------------
DROP TABLE IF EXISTS `public_disk`;
CREATE TABLE `public_disk` (
  `ID` int(11) DEFAULT NULL,
  `DISK_ID` varchar(150) DEFAULT NULL,
  `DISK_NO` int(11) DEFAULT NULL,
  `DISK_NAME` text,
  `DISK_PATH` text,
  `NEW_USER` text,
  `MANAGE_USER` text,
  `ACCESS_USER` text,
  `DOWN_USER` text,
  `NEW_DEPT` text,
  `MANAGE_DEPT` text,
  `ACCESS_DEPT` text,
  `DOWN_DEPT` text,
  `NEW_PRIV` text,
  `MANAGE_PRIV` text,
  `ACCESS_PRIV` text,
  `DOWN_PRIV` text,
  `SPACE_LIMIT` int(11) DEFAULT NULL,
  `ORDER_BY` varchar(60) DEFAULT NULL,
  `ASC_DESC` varchar(60) DEFAULT NULL,
  `ORG_ID` varchar(60) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of public_disk
-- ----------------------------

-- ----------------------------
-- Table structure for secretary
-- ----------------------------
DROP TABLE IF EXISTS `secretary`;
CREATE TABLE `secretary` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SECRETARY_ID` varchar(50) DEFAULT NULL,
  `CONTENT` varchar(200) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of secretary
-- ----------------------------
INSERT INTO `secretary` VALUES ('1', '1', '默认铃声', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `secretary` VALUES ('2', '2', '系统男声', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for sms
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '顺序号',
  `SMS_ID` varchar(50) DEFAULT NULL COMMENT '消息的GUID标识',
  `SMS_FROM` varchar(20) DEFAULT NULL COMMENT '发送者账号',
  `SMS_TO` varchar(4000) DEFAULT NULL COMMENT '接收者账号',
  `SMS_SEND_TIME` datetime DEFAULT NULL COMMENT '要求发送的时间',
  `SMS_CONTENT` varchar(8000) DEFAULT NULL COMMENT '发送的内容',
  `SMS_FLAG` varchar(2) DEFAULT NULL COMMENT '删除标记，0删除，1没有删除',
  `SMS_TYPE` varchar(50) DEFAULT NULL COMMENT '消息源模块',
  `SMS_URL` varchar(200) DEFAULT NULL COMMENT '关联的信息的URL',
  `SMS_ATTACH_ID` varchar(2000) DEFAULT NULL COMMENT '消息的附件ID',
  `SMS_ATTACH_PRIV` varchar(200) CHARACTER SET gbk DEFAULT NULL COMMENT '附件的权限',
  `SMS_STATUS` varchar(2) DEFAULT NULL COMMENT '消息的发送状态',
  `ORG_ID` varchar(50) DEFAULT NULL COMMENT '机构ID',
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms
-- ----------------------------

-- ----------------------------
-- Table structure for sysm_product
-- ----------------------------
DROP TABLE IF EXISTS `sysm_product`;
CREATE TABLE `sysm_product` (
  `sn` varchar(50) NOT NULL COMMENT '产品序列号',
  `product_name` varchar(200) NOT NULL,
  `version` varchar(100) NOT NULL,
  `product_team` varchar(500) NOT NULL,
  `product_site` varchar(200) NOT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysm_product
-- ----------------------------
INSERT INTO `sysm_product` VALUES ('57F950A8-977A-0457-513F-860D5FB78471', '慧客OA', 'v1.1', '慧客精英', 'http://oa.huike.com/product/v1.1');
INSERT INTO `sysm_product` VALUES ('A62806B9-9EDA-C3F1-BCB8-6A16ACE60A17', '慧客OA', 'v1.2', '慧客精英', 'http://oa.huike.com/product/v1.2');

-- ----------------------------
-- Table structure for sysm_regist
-- ----------------------------
DROP TABLE IF EXISTS `sysm_regist`;
CREATE TABLE `sysm_regist` (
  `id` varchar(50) NOT NULL,
  `regist_unit` varchar(200) NOT NULL,
  `product_sn` varchar(50) NOT NULL,
  `regist_disk_sn` varchar(200) NOT NULL,
  `regist_time` date NOT NULL,
  `regist_deadline` date NOT NULL,
  `regist_user_count` int(11) NOT NULL,
  `regist_im_user_count` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysm_regist
-- ----------------------------
INSERT INTO `sysm_regist` VALUES ('EA3263E4-2CB7-C2A7-C1EB-6ED3058E8E2D', '慧客', 'A62806B9-9EDA-C3F1-BCB8-6A16ACE60A17', '727551', '2016-10-19', '2022-01-01', '1000', '1000');

-- ----------------------------
-- Table structure for sys_about
-- ----------------------------
DROP TABLE IF EXISTS `sys_about`;
CREATE TABLE `sys_about` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ABOUT_ID` varchar(50) DEFAULT NULL,
  `DOWN_URL` varchar(500) DEFAULT NULL,
  `SERVICE_PHONE` varchar(50) DEFAULT NULL,
  `WEB_SITE` varchar(500) DEFAULT NULL,
  `COPYRIGHT` varchar(200) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_about
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SYS_CONFIG_ID` varchar(50) DEFAULT NULL,
  `UPDATE_NAME` varchar(50) DEFAULT NULL,
  `INIT_PWD` varchar(50) DEFAULT NULL,
  `PWD_CYCLE` varchar(50) DEFAULT NULL,
  `OUT_PWD` varchar(50) DEFAULT NULL,
  `PWD_WIDTH` varchar(50) DEFAULT NULL,
  `IS_ABC` varchar(50) DEFAULT NULL,
  `MISS_NUM` varchar(50) DEFAULT NULL,
  `MISS_TIME` varchar(50) DEFAULT NULL,
  `MISS_PWD` varchar(50) DEFAULT NULL,
  `FIND_PWD` varchar(50) DEFAULT NULL,
  `REMBER_USER` varchar(50) DEFAULT NULL,
  `MORE_LOGIN` varchar(50) DEFAULT NULL,
  `COM_WITH_PHONE` varchar(50) DEFAULT NULL,
  `USE_KEY` varchar(50) DEFAULT NULL,
  `DOMAIN_LOGIN` varchar(50) DEFAULT NULL,
  `USER_IP` varchar(50) DEFAULT NULL,
  `REMBER_STATUS` varchar(50) DEFAULT NULL,
  `CHAT_HOST` varchar(400) DEFAULT NULL,
  `LISTENNER_PORT` varchar(50) DEFAULT NULL,
  `CHAT_PORT` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '1', '1', '1', '180', '1', '6-20', '2', '3', '10', '2', '1', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_config` VALUES ('2', '1', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', 'BD0FA045-4584-E5B4-917A-3DAE47A0B5E4');
INSERT INTO `sys_config` VALUES ('3', '2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', '94F14508-B887-9F47-0A68-780924FF464B');
INSERT INTO `sys_config` VALUES ('4', '2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', '5E29CA84-5A7C-97C6-7B4E-88CA3A83E647');
INSERT INTO `sys_config` VALUES ('5', '2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', '8FB0C895-BAD7-3B7A-BA7A-6086C7236963');
INSERT INTO `sys_config` VALUES ('6', '2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', 'DF148637-B144-DC1C-AAE0-42F4AD8A3E02');
INSERT INTO `sys_config` VALUES ('7', '2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `sys_config` VALUES ('8', '2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `sys_config` VALUES ('9', '2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `sys_config` VALUES ('10', '2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `sys_config` VALUES ('11', '2', '1', '2', '180', '2', '6-20', '2', '3', '10', '2', '2', '1', '2', '1', '2', '2', '3', '2', 'http://115.29.170.136', '8090', '8080', '2A096A25-4E62-8426-F80F-6522BB06FF3F');

-- ----------------------------
-- Table structure for sys_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_info`;
CREATE TABLE `sys_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UNIT` varchar(40) DEFAULT NULL,
  `MACHINE_CODE` varchar(40) DEFAULT NULL,
  `SN` varchar(50) DEFAULT NULL,
  `USER_COUNT` varchar(5) DEFAULT NULL,
  `COPY_RIGHT` varchar(100) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_info
-- ----------------------------
INSERT INTO `sys_info` VALUES ('1', '南京慧客软件科技有限公司', 'ADBCDE', 'ASDF-1234-QWER-PPSD', '1000', '南京慧客软件科技有限公司', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` varchar(20) DEFAULT NULL,
  `LOG_TYPE` varchar(20) DEFAULT NULL,
  `LOG_DATE` varchar(20) DEFAULT NULL,
  `IP` varchar(20) DEFAULT NULL,
  `REMARK` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=38378 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('38350', 'admin', '1', '2016-09-17 10:02:28', '127.0.0.1', '登录成功');
INSERT INTO `sys_log` VALUES ('38351', 'admin', '22', '2016-09-17 10:08:07', '127.0.0.1', '退出系统');
INSERT INTO `sys_log` VALUES ('38352', 'admin', '1', '2016-09-17 10:08:07', '127.0.0.1', '登录成功');
INSERT INTO `sys_log` VALUES ('38353', 'admin', '1', '2016-09-17 10:09:37', '127.0.0.1', '登录成功');
INSERT INTO `sys_log` VALUES ('38354', 'admin', '1', '2016-09-17 10:23:13', '127.0.0.1', '登录成功');
INSERT INTO `sys_log` VALUES ('38355', 'admin', '1', '2016-10-19 22:12:14', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38356', 'admin', '1', '2016-10-22 19:32:41', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38357', 'admin', '1', '2016-10-23 13:50:25', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38358', 'admin', '1', '2016-11-09 21:12:18', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38359', 'admin', '22', '2016-11-09 21:16:27', '0:0:0:0:0:0:0:1', '退出系统');
INSERT INTO `sys_log` VALUES ('38360', 'admin', '1', '2016-11-09 21:41:02', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38361', 'admin', '22', '2016-11-09 22:35:28', '0:0:0:0:0:0:0:1', '退出系统');
INSERT INTO `sys_log` VALUES ('38362', 'admin', '1', '2016-11-09 22:35:28', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38363', 'admin', '22', '2016-11-09 22:36:25', '0:0:0:0:0:0:0:1', '退出系统');
INSERT INTO `sys_log` VALUES ('38364', 'admin', '1', '2016-11-09 22:48:32', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38365', 'admin', '22', '2016-11-09 22:53:26', '0:0:0:0:0:0:0:1', '退出系统');
INSERT INTO `sys_log` VALUES ('38366', 'admin', '1', '2016-11-09 22:53:26', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38367', 'admin', '22', '2016-11-09 22:53:57', '0:0:0:0:0:0:0:1', '退出系统');
INSERT INTO `sys_log` VALUES ('38368', 'aa', '1', '2016-11-09 22:53:57', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38369', 'admin', '1', '2016-11-21 10:05:50', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38370', 'admin', '1', '2016-11-21 11:05:26', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38371', 'admin', '1', '2016-11-21 11:22:08', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38372', 'admin', '1', '2016-11-21 12:47:15', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38373', 'admin', '1', '2016-12-01 15:31:26', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38374', 'admin', '1', '2016-12-01 15:55:03', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38375', 'admin', '22', '2016-12-01 17:30:41', '0:0:0:0:0:0:0:1', '退出系统');
INSERT INTO `sys_log` VALUES ('38376', 'admin', '1', '2016-12-01 17:30:41', '0:0:0:0:0:0:0:1', '登录成功');
INSERT INTO `sys_log` VALUES ('38377', 'admin', '1', '2017-01-23 17:04:55', '0:0:0:0:0:0:0:1', '登录成功');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SYS_MENU_ID` varchar(50) DEFAULT NULL,
  `SYS_MENU_NAME` varchar(20) DEFAULT NULL,
  `SYS_MENU_URL` varchar(200) DEFAULT NULL,
  `SYS_MENU_CODE` varchar(200) DEFAULT NULL,
  `SYS_MENU_PARM` varchar(200) DEFAULT NULL,
  `SYS_MENU_OPEN` varchar(2) DEFAULT NULL,
  `SYS_MENU_LEAVE` varchar(20) DEFAULT NULL,
  `SYS_MENU_PIC` varchar(20) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('80', '80', 'SMS配置', '/tfd/system/mssageconfig/index.jsp', 'setsys', '', '1', '69', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('78', '78', '分类码管理', '/tfd/system/code/index.jsp', 'code', '', '1', '69', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('77', '77', '菜单管理', '/tfd/system/menu/index.jsp', 'menu', '', '1', '69', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('76', '76', '机构设置', '/tfd/system/unit/orgconfig/index.jsp', 'orgconfig', '', '1', '70', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('75', '75', '行政级别设置', '/tfd/system/unit/leadleave/index.jsp', 'leadleave', '', '1', '70', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('74', '74', '权限组管理', '/tfd/system/unit/userpriv/index.jsp', 'userpriv', '', '1', '70', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('73', '73', '用户管理', '/tfd/system/unit/userinfo/index.jsp', 'person', '', '1', '70', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('72', '72', '部门管理', '/tfd/system/unit/dept/index.jsp', 'dept', '', '1', '70', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('71', '71', '单位管理', '/tfd/system/unit/org/index.jsp', 'unit', '', '1', '70', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('70', '70', '组织机构', '', '', '', '1', '69', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('69', '69', '系统设置', '', '', '', '1', '0', 'system.png', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('66', '66', '客户列表', '/tfd/customermanage/customerinfo/customerinfo.jsp', 'customer', '', '1', '64', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('63', '63', '会议室设置', '/tfd/metting/boardroom/lookboardroom.jsp', 'metting', '', '1', '56', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('62', '62', '会议设备管理', '/tfd/metting/boardroomdevice/index.jsp', 'metting', '', '1', '56', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('60', '60', '查看会议纪要', '/tfd/metting/summary/index.jsp', 'metting', '', '1', '56', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('58', '58', '会议查看', '/tfd/metting/requestmetting/index.jsp', 'metting', '', '1', '56', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('57', '57', '会议申请', '/tfd/metting/requestmetting/selectboardroom.jsp', 'metting', '', '1', '56', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('55', '55', '办公用品库管理', '/tfd/officesupplies/officelibrary/looklibrary.jsp', 'office', '', '1', '49', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('54', '54', '办公用品库存管理', '/tfd/officesupplies/offmaint/index.jsp', 'office', '', '1', '49', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('53', '53', '办公用品信息管理', '/tfd/officesupplies/offres/index.jsp', 'office', '', '1', '49', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('52', '52', '办公用品发放', '/tfd/officesupplies/offgrant/index.jsp', 'office', '', '1', '49', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('51', '51', '办公用品审批', '/tfd/officesupplies/approveapply/index.jsp', 'office', '', '1', '49', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('49', '49', '办公用品', '', 'office', '', '1', '46', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('48', '48', '考勤设置', '/tfd/system/attend/setting/index.jsp', 'attend', '', '1', '46', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('46', '46', '行政办公', '', '', '', '1', '0', 'knowledge.png', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('1', '1', '个人办公桌', '', '', '11', '', '0', 'mytable.png', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('43', '43', '流程分类', '/tfd/system/workflow/flowsort/index.jsp', 'workflow', '', '1', '40', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('41', '41', '设计表单', '/tfd/system/workflow/form/index.jsp', 'workflowform', '', '1', '40', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('39', '39', '流程维护', '/tfd/system/workflow/maintenance/index.jsp', 'workflow', '', '1', '33', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('38', '38', '时效分析', '/tfd/system/workflow/passtime/index.jsp', 'passtime', '', '1', '33', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('36', '36', '流程查询', '/tfd/system/workflow/search/index.jsp', 'workflow', '', '1', '33', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('35', '35', '待办流程', '/tfd/system/workflow/worklist/index.jsp', 'workflow', '', '1', '33', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('30', '30', '规则制度搜索', '/tfd/institution/search/index.jsp', 'institution', '', '1', '83', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('29', '29', '规则制度', '/tfd/institution/index.jsp', 'institution', '', '1', '83', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('28', '28', '图片设置', '/tfd/system/photo/setting/index.jsp', 'photoedit', '', '1', '24', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('27', '27', '图片管理', '/tfd/system/photo/index.jsp', 'photo', '', '1', '24', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('26', '26', '文件柜设置', '/tfd/system/publicfilefolder/index.jsp', 'publicfilefolder', '', '1', '24', 'publicfilefolder.png', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('22', '22', '工作日程', '/tfd/calendar/leader/index.jsp', 'calendar', '', '1', '21', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('21', '21', '领导管控', '', '', '', '1', '0', 'lender.png', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('19', '19', '通知公告审批', '/tfd/notice/approvalnotice/index.jsp', 'notice', '', '1', '12', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('18', '18', '通知公告维护', '/tfd/notice/manage/index.jsp', 'notice', '', '1', '12', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('9', '9', '个人文件柜', '/tfd/system/personfolder/manage/index.jsp', 'personfloder', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('8', '8', '工作日志', '/tfd/diary/index.jsp', 'diary', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('7', '7', '个人日程', '/tfd/calendar/index.jsp', 'calendar', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('5', '5', '新闻', '/tfd/news/read/index.jsp', 'news', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('6', '6', '通知公告', '/tfd/notice/readnotice/index.jsp', 'notice', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('79', '79', 'App权限设置', '/tfd/system/apppower/index.jsp', 'app', '', '1', '69', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('68', '68', '客户权限设置', '/tfd/customermanage/customerpower/customerpower.jsp', 'customer', '', '1', '64', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('67', '67', '合作伙伴列表', '/tfd/customermanage/workcustomer/customerinfo.jsp', 'customer', '', '1', '64', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('65', '65', '新建客户', '/tfd/customermanage/customerinfo/Newcustomer.jsp', 'customer', '', '1', '64', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('64', '64', '客户管理', '', 'customer', '', '1', '0', 'share.png', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('61', '61', '记录会议纪要', '/tfd/metting/summary/summaryindex.jsp', 'metting', '', '1', '56', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('59', '59', '会议管理', '/tfd/metting/approvalmetting/index.jsp', 'metting', '', '1', '56', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('56', '56', '会议申请与安排', '', 'metting', '', '1', '46', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('50', '50', '办公用品申领', '/tfd/officesupplies/offresapply/resapplyindex.jsp', 'office', '', '1', '49', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('47', '47', '考勤统计', '/tfd/system/attend/count/index.jsp', 'attend', '', '1', '46', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('45', '45', '流程审批级别', '/tfd/system/workflow/leadleave/index.jsp', 'flowpassleave', '', '1', '40', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('81', '81', '系统参数设置', '/tfd/system/sysinfo/index.jsp', 'sysconfig', '', '1', '69', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('44', '44', '第三方数据源', '/tfd/system/workflow/datasource/index.jsp', 'workflow', '', '1', '40', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('42', '42', '设计流程', '/tfd/system/workflow/workflow/index.jsp', 'workflow', '', '1', '40', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('40', '40', '流程设置', '', '', '', '1', '33', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('37', '37', '流程监控', '/tfd/system/workflow/monitor/index.jsp', 'monitor', '', '1', '33', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('34', '34', '新建流程', '/tfd/system/workflow/newwork/index.jsp', 'workflow', '', '1', '33', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('33', '33', '审批流程', '', '', '', '1', '0', 'workflow.png', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('32', '32', '制度目录设置', '/tfd/institution/dirManager/index.jsp', 'institution', '', '1', '83', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('31', '31', '规则制度管理', '/tfd/institution/manager/index.jsp', 'institution', '', '1', '83', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('4', '4', '内部短信', '/tfd/system/sms/send/index.jsp', 'sms', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('25', '25', '公共文件柜', '/tfd/system/publicfilefolder/manage/index.jsp', 'publicfilefolder', '', '1', '24', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('24', '24', '知识管理', '', '', '', '1', '0', 'root.png', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('23', '23', '工作日志设置', '/tfd/diary/diaryFit/diaryFit.jsp', 'diary', '', '1', '21', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('20', '20', '公告审批人员设置', '/tfd/notice/approvalnoticestaff/approvalstaff.jsp', 'approvalnoticestaff', '', '1', '12', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('17', '17', '通知公告发布', '/tfd/notice/sendnotice/Newnotice.jsp', 'notice', '', '1', '12', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('16', '16', '新闻审批权限设置', '/tfd/news/power/newspower.jsp', 'news', '', '1', '12', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('15', '15', '新闻审批', '/tfd/news/newsapproval/index.jsp', 'news', '', '1', '12', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('14', '14', '新闻维护', '/tfd/news/manage/index.jsp', 'news', '', '1', '12', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('13', '13', '新闻发布', '/tfd/news/send/index.jsp', 'news', '', '1', '12', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('12', '12', '信息发布', '', '', '', '1', '0', 'filemanage.png', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('11', '11', '控制面板', '/tfd/system/setuser/index.jsp', 'setuser', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('10', '10', '个人考勤', '/tfd/system/attend/index.jsp', 'attend', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('3', '3', '手机短信', '/tfd/system/mobilesms/index.jsp', 'mobilesms', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('2', '2', '电子邮件', '/tfd/system/email/index.jsp', 'email', '', '1', '1', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('82', '82', '界面设置', '/tfd/system/interface/index.jsp', 'interface', '', '1', '69', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('83', '83', '制度中心', '', 'institution', '', '1', '24', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('84', '84', '图表分析', '/tfd/system/charts/index.jsp', 'charts', '', '1', '21', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('85', '85', '图表设置', '/tfd/system/charts/setting.jsp', 'charts', '', '1', '21', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_menu` VALUES ('86', '86', '首页设置', '/tfd/system/homepage/index.jsp', 'homepage', null, '1', '69', 'a1.gif', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for sys_short_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_short_menu`;
CREATE TABLE `sys_short_menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SYS_MENU_ID` varchar(50) DEFAULT NULL,
  `SYS_MENU_NAME` varchar(200) DEFAULT NULL,
  `SYS_MENU_URL` varchar(200) DEFAULT NULL,
  `SYS_MENU_CODE` varchar(50) DEFAULT NULL,
  `SYS_MENU_PARM` varchar(50) DEFAULT NULL,
  `SYS_MENU_OPEN` varchar(2) DEFAULT NULL,
  `SYS_MENU_LEAVE` varchar(200) DEFAULT NULL,
  `SYS_MENU_PIC` varchar(50) DEFAULT NULL,
  `ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_short_menu
-- ----------------------------
INSERT INTO `sys_short_menu` VALUES ('125', '22', '工作日程', '/tfd/calendar/leader/index.jsp', 'calendar', null, '1', '21', 'a1.gif', 'zhaoyang', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('124', '34', '新建流程', '/tfd/system/workflow/newwork/index.jsp', 'workflow', '', '1', '33', 'a1.gif', 'zhaoyang', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('123', '2', '电子邮件', '/tfd/system/email/index.jsp', 'email', '', '1', '1', 'a1.gif', 'zhaoyang', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('132', '26', '文件柜设置', '/tfd/system/publicfilefolder/index.jsp', 'publicfilefolder', '', '1', '24', 'publicfilefolder.png', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('131', '25', '公共文件柜', '/tfd/system/publicfilefolder/manage/index.jsp', 'publicfilefolder', '', '1', '24', 'a1.gif', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('130', '22', '工作日程', '/tfd/calendar/leader/index.jsp', 'calendar', '', '1', '21', 'a1.gif', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('129', '10', '个人考勤', '/tfd/system/attend/index.jsp', 'attend', '', '1', '1', 'a1.gif', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('128', '7', '个人日程', '/tfd/calendar/index.jsp', 'calendar', '', '1', '1', 'a1.gif', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('127', '9', '个人文件柜', '/tfd/system/personfolder/manage/index.jsp', 'personfloder', '', '1', '1', 'a1.gif', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('126', '2', '电子邮件', '/tfd/system/email/index.jsp', 'email', '', '1', '1', 'a1.gif', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('133', '82', '界面设置', '/tfd/system/interface/index.jsp', 'interface', '', '1', '69', 'a1.gif', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('134', '11', '控制面板', '/tfd/system/setuser/index.jsp', 'setuser', '', '1', '1', 'a1.gif', 'admin', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('135', '73', '用户管理', '/tfd/system/unit/userinfo/index.jsp', 'person', '', '1', '70', 'a1.gif', 'cst', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('136', '86', '首页设置', '/tfd/system/homepage/index.jsp', 'homepage', null, '1', '69', 'a1.gif', 'cst', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('137', '81', '系统参数设置', '/tfd/system/sysinfo/index.jsp', 'sysconfig', '', '1', '69', 'a1.gif', 'cst', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `sys_short_menu` VALUES ('138', '82', '界面设置', '/tfd/system/interface/index.jsp', 'interface', '', '1', '69', 'a1.gif', 'cst', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for target
-- ----------------------------
DROP TABLE IF EXISTS `target`;
CREATE TABLE `target` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TARGET_ID` varchar(50) DEFAULT NULL,
  `CREATE_TIME` varchar(200) DEFAULT NULL,
  `CONTENT` varchar(400) DEFAULT NULL,
  `IS_PUBLIC` varchar(20) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `GOOD` text,
  `ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=222 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of target
-- ----------------------------

-- ----------------------------
-- Table structure for unit
-- ----------------------------
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORG_NAME` varchar(40) DEFAULT NULL,
  `ORG_ADD` varchar(100) DEFAULT NULL,
  `ORG_TEL` varchar(20) DEFAULT NULL,
  `ORG_FAX` varchar(20) DEFAULT NULL,
  `ORG_POST` varchar(10) DEFAULT NULL,
  `ORG_EMAIL` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unit
-- ----------------------------

-- ----------------------------
-- Table structure for usecar
-- ----------------------------
DROP TABLE IF EXISTS `usecar`;
CREATE TABLE `usecar` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RUN_ID` varchar(50) DEFAULT NULL,
  `CHILD_RUN_ID` varchar(200) DEFAULT NULL,
  `PARENT_RUN_ID` varchar(50) DEFAULT NULL,
  `BM` text,
  `NAME` text,
  `PESON` varchar(40) DEFAULT NULL,
  `PLACE` varchar(40) DEFAULT NULL,
  `FIRSTTIME` text,
  `OVERTIME` text,
  `RESONES` text,
  `BMJLYJ` text,
  `XZZJYJ` text,
  `FZYJ` text,
  `ZJLYJ` text,
  `BMJLQM` text,
  `BMDJLYJ` text,
  KEY `ID` (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usecar
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(50) DEFAULT NULL,
  `ACCOUNT_ID` varchar(20) DEFAULT NULL COMMENT '账号',
  `USER_NAME` varchar(20) DEFAULT NULL COMMENT '名字',
  `DEPT_ID` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `LEAD_ID` varchar(20) DEFAULT NULL COMMENT '领导ID',
  `OTHER_PRIV` varchar(500) DEFAULT NULL COMMENT '表示兼职',
  `POST_PRIV` varchar(500) DEFAULT NULL COMMENT '角色权限',
  `HOME_ADD` varchar(50) DEFAULT NULL COMMENT '家庭住址',
  `HOME_TEL` varchar(12) DEFAULT NULL COMMENT '家庭电话',
  `MOBILE_NO` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `QQ` varchar(12) DEFAULT NULL COMMENT 'QQ号码',
  `E_MAILE` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `BIRTHDAY` varchar(50) DEFAULT NULL COMMENT '生日',
  `SIGN` varchar(500) DEFAULT NULL COMMENT '签名',
  `CODE` varchar(200) DEFAULT NULL COMMENT '验证码',
  `WORK_ID` varchar(20) DEFAULT NULL COMMENT '工号',
  `SEX` varchar(2) DEFAULT '男' COMMENT '性别',
  `SORT_ID` varchar(3) DEFAULT NULL COMMENT '排序号',
  `MANAGE_DEPT` varchar(500) DEFAULT '1' COMMENT '2表示管理本部门，1表示管理全体',
  `OTHER_DEPT` varchar(500) DEFAULT NULL COMMENT '管理指定部门',
  `LEAD_LEAVE` varchar(50) DEFAULT NULL COMMENT '职务',
  `NICK` varchar(200) DEFAULT NULL,
  `AVATAR` varchar(200) DEFAULT NULL,
  `TRENDS` varchar(200) DEFAULT NULL,
  `AGE` varchar(2) DEFAULT NULL COMMENT '年龄',
  `LEV` varchar(200) DEFAULT NULL,
  `HEAD_IMG` varchar(200) DEFAULT NULL COMMENT '用户图像',
  `ATTEND_CONFIG_ID` varchar(50) DEFAULT '1' COMMENT '排班类型',
  `SECRETARY_ID` varchar(50) DEFAULT '1' COMMENT '小秘书Id',
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ID` (`ACCOUNT_ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4841 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('22', '81D14ED2-0ED7-0C59-8A00-779B68021835', 'admin', '超级管理员', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '', '', '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '025888888885', '13382036126', '39919934', '399199345@qq.com', '2012-12-30', '链链----为企业链接一切！', '{\"time\":1451099975,\"code\":\"351532\"}', '', '男', null, '2', 'deptAll', '2407F970-AC1F-2226-8409-FD0FAEA639E4', null, null, null, null, null, '20160122163758559_userInfoHeader.jpg', 'F7F39009-B2B9-5BE1-B30B-957496ABB005', '2', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_info` VALUES ('4837', '7A48CA8D-E471-318C-922A-21CE6F70B847', 'aa', '测试001', '64005ACD-7705-11FC-B896-7CA139E0EA38', 'admin', null, '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '', '', '', '', null, null, null, '', '男', null, '2', '', '2407F970-AC1F-2226-8409-FD0FAEA639E4', null, null, null, null, null, null, '1', '1', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_info` VALUES ('4838', '9863CBF7-0F14-838F-4BC4-BEC251AF0F57', 'bb', '测试002', '8F1306D9-ADC3-9D84-39F8-51F7A4180A70', 'admin', null, '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '', '', '', '', null, null, null, '', '男', null, '2', '', '2407F970-AC1F-2226-8409-FD0FAEA639E4', null, null, null, null, null, null, '1', '1', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_info` VALUES ('4839', 'F110D8C5-53F4-966A-F308-EA35C3DD4CD4', 'cc', '测试003', '559E4A0C-9A55-2269-9A0C-F90C39957F02', 'admin', null, '9759ACA6-0518-733E-7CE8-17F365E83C63', '', '', '', '', '', null, null, null, '', '男', null, '2', '', '2407F970-AC1F-2226-8409-FD0FAEA639E4', null, null, null, null, null, null, '1', '1', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_info` VALUES ('4840', '6D1AD38C-BF38-D380-BC90-7B126D5E35A6', 'dd', '测试004', 'F39E5A2A-1794-1CBE-29FC-6D40A35CCB83', 'admin', null, '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '', '', '', '', '', null, null, null, '', '女', null, '1', '', '6C771064-C41C-8F6F-9823-C70B1F2A9348', null, null, null, null, null, null, '1', '1', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for user_leave
-- ----------------------------
DROP TABLE IF EXISTS `user_leave`;
CREATE TABLE `user_leave` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LEAVE_ID` varchar(50) DEFAULT NULL,
  `LEAVE_NO_ID` varchar(50) DEFAULT NULL,
  `LEAVE_NAME` varchar(50) DEFAULT NULL,
  `MIDDING` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_leave
-- ----------------------------
INSERT INTO `user_leave` VALUES ('2', '2407F970-AC1F-2226-8409-FD0FAEA639E4', '9', '管理员', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_leave` VALUES ('8', '5382DD36-C84A-1101-1179-C35090E1F54F', '7', '副总经理', '455A906D-2F7B-650D-B723-82120A11AEA5', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_leave` VALUES ('7', '455A906D-2F7B-650D-B723-82120A11AEA5', '7', '总经理', 'C039AEB7-D9FB-A814-7D11-476144628B6D', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_leave` VALUES ('6', 'C039AEB7-D9FB-A814-7D11-476144628B6D', '8', '董事长', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_leave` VALUES ('9', '4980A343-348F-DA0B-0E19-23A86468B525', '6', '总监', '5382DD36-C84A-1101-1179-C35090E1F54F', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_leave` VALUES ('10', '01E2A01C-888C-5349-37A7-686E562F2B63', '5', '部门经理', '4980A343-348F-DA0B-0E19-23A86468B525', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_leave` VALUES ('11', '73BB6155-4DF8-9919-EED6-73690C57A22C', '4', '部门主管', '01E2A01C-888C-5349-37A7-686E562F2B63', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_leave` VALUES ('12', '6C771064-C41C-8F6F-9823-C70B1F2A9348', '3', '职员', '73BB6155-4DF8-9919-EED6-73690C57A22C', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_leave` VALUES ('13', 'C76AA3E1-1311-0DD5-F897-A0526145FFBE', '999', '管理员', '', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `user_leave` VALUES ('14', '1919F8A4-8AB7-26FF-51CF-98ED4162F70B', '999', '管理员', '', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `user_leave` VALUES ('15', '3E0FDB2F-05BB-E4C7-1087-9C3236BD2FF7', '999', '管理员', '', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `user_leave` VALUES ('16', '437F36BA-C4AB-7698-3486-4C8D414F61DE', '999', '管理员', '', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `user_leave` VALUES ('17', 'E9D1DD12-2205-0013-E813-7DE1F1722921', '999', '管理员', '', '2A096A25-4E62-8426-F80F-6522BB06FF3F');

-- ----------------------------
-- Table structure for user_online
-- ----------------------------
DROP TABLE IF EXISTS `user_online`;
CREATE TABLE `user_online` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` varchar(200) NOT NULL COMMENT '账号ID',
  `LOGIN_TIME` datetime DEFAULT NULL COMMENT '登陆时间',
  `STATE` varchar(1) DEFAULT '0' COMMENT '在线状态',
  `SESSION_TOKEN` varchar(50) DEFAULT NULL,
  `IP` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=12197 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_online
-- ----------------------------
INSERT INTO `user_online` VALUES ('12196', 'admin', '2017-01-23 17:04:55', '1', 'B16EE4392ACC8B6DEB2ADA64EA46E722', '0:0:0:0:0:0:0:1', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for user_priv
-- ----------------------------
DROP TABLE IF EXISTS `user_priv`;
CREATE TABLE `user_priv` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_PRIV_ID` varchar(50) DEFAULT NULL,
  `USER_PRIV_LEAVE` varchar(10) DEFAULT NULL COMMENT '权限大小 按数值大小来',
  `USER_PRIV_NAME` varchar(255) DEFAULT NULL,
  `USER_PRIV_STR` varchar(2000) DEFAULT NULL,
  `ORG_ID` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_priv
-- ----------------------------
INSERT INTO `user_priv` VALUES ('13', '1196C6E7-3D6B-1D0D-C5CA-D0B3ED58703C', '992', '职员', '46,56,60,58,57,61,1,9,8,7,5,6,4,11,10,2,21,22,23,84,85,64,66,68,67,65,33,39,38,36,35,43,41,45,44,42,37,34,24,28,27,26,25,83,30,32,31,12,19,18,20,17,16,15,14,13', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_priv` VALUES ('12', 'A55D5899-D853-58AF-2F54-E8995C64F7F9', '993', '部门主管', '1,2,16,23,25,27,28,50,53,67,109,13,14,15,19,37,84,93,17,21,18,22,86,26,46,47,48,51,58,59,60,61,62,66,63,64,65,75,76,77,78,79,80,81,82,32,33,34,35,54', 'BD0FA045-4584-E5B4-917A-3DAE47A0B5E4');
INSERT INTO `user_priv` VALUES ('8', '7DDBA6F7-CED5-0819-9890-D37340EA51F3', '997', '总经理', '80,78,77,76,75,79,81,82,46,54,53,52,51,50,48,63,62,60,58,57,61,59,47,1,9,8,7,5,6,4,11,10,3,2,21,22,23,84,64,66,67,65,33,38,35,43,44,42,37,34,28,27,26,25,32,19,18,17,16,15,14,13', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_priv` VALUES ('9', 'E07C070C-C131-7778-0EC9-91E85796F682', '996', '副总经理', '1,2,16,23,25,27,28,50,53,67,109,13,14,15,19,37,84,93,17,21,18,22,86,26,46,47,48,51,58,59,60,61,62,66,63,64,65,75,76,77,78,79,80,81,82,32,33,34,35,54,38,42,43,44,52,57,88,92', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_priv` VALUES ('10', '689AE679-437E-4028-2784-9AD950516122', '995', '总监', '1,2,16,23,25,27,28,50,53,67,109,13,14,15,19,37,84,93,17,21,18,22,86,26,46,47,48,51,58,59,60,61,62,66,63,64,65,75,76,77,78,79,80,81,82', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_priv` VALUES ('11', '1408244A-2653-52D7-4853-3F48EC302298', '994', '部门经理', '1,2,16,23,25,27,28,50,53,67,109,13,14,15,19,37,84,93,17,21,18,22,86,26,46,47,48,51,58,59,60,61,62,66,63,64,65,75,76,77,78,79,80,81,82,110,32,33,34,35,54,38,42,57,88,92,94,9,10,11,12,31,68,107,108,95,96,97,98,99', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_priv` VALUES ('7', '9BB768A9-35AC-A456-9078-9885E43E496D', '998', '董事长', '69,77,70,76,75,72,71,46,49,55,54,53,52,51,50,48,56,63,62,60,58,57,61,59,47,1,9,8,7,5,6,4,11,10,3,2,21,22,23,84,85,64,66,68,67,65,33,39,38,36,35,40,43,41,45,44,42,37,34,24,28,27,26,25,83,30,29,32,31,12,19,18,20,17,16,15,14,13', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_priv` VALUES ('17', '9759ACA6-0518-733E-7CE8-17F365E83C63', '999', '超级管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `user_priv` VALUES ('19', '014F379A-CF58-E555-3721-2AF3ED2E711B', '999', '管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85', '528A83B2-473C-8E78-0938-2056B0299E29');
INSERT INTO `user_priv` VALUES ('20', 'DD4E625C-601B-17DA-675C-1818691DEF3E', '999', '管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85', '86263DFD-D1F1-13DD-DA73-515B451D6C69');
INSERT INTO `user_priv` VALUES ('21', '2414DB02-FBCC-4A34-4512-661A94BB9013', '999', '管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85', '2D3C94CC-1E3F-E975-BFB5-B73474A0FE1E');
INSERT INTO `user_priv` VALUES ('22', '26B6D0CC-9592-F509-500F-9E1A3783BFBC', '999', '管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85', '36972402-A043-6A64-775F-308D06FDC242');
INSERT INTO `user_priv` VALUES ('23', '95FD205B-0483-4D95-C0B3-6738F541BE91', '999', '管理员', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85', '2A096A25-4E62-8426-F80F-6522BB06FF3F');

-- ----------------------------
-- Table structure for work_flow
-- ----------------------------
DROP TABLE IF EXISTS `work_flow`;
CREATE TABLE `work_flow` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '顺序号',
  `FLOW_TYPE_ID` varchar(50) DEFAULT NULL COMMENT '主键id GUID',
  `SORT_ID` int(5) DEFAULT NULL COMMENT '排序号',
  `FLOW_NAME` varchar(40) DEFAULT NULL COMMENT '流程名称',
  `FORM_ID` varchar(40) DEFAULT NULL COMMENT '表单id',
  `FLOW_TYPE` varchar(2) DEFAULT NULL COMMENT '流程类型，workflow,project,等',
  `WORK_FLOW_TYPE_ID` varchar(50) DEFAULT NULL COMMENT '流程层级关联ID',
  `FLOW_CREATE_USER` varchar(20) DEFAULT NULL COMMENT '流程的创建人',
  `MODULE_ID` varchar(20) DEFAULT NULL COMMENT '流程类型，固定，自由',
  `FLOW_CREATE_TIME` datetime DEFAULT NULL COMMENT '流程创建时间',
  `FLOW_ID` varchar(50) DEFAULT NULL COMMENT '流程ID',
  `LEAVE_PASS` varchar(2) DEFAULT NULL COMMENT '是否启用行政级别审批',
  `FLOW_LOCK` varchar(2) DEFAULT NULL COMMENT '锁定是不否可以更新缓存',
  `FREE_TO_OTHER` varchar(2) DEFAULT NULL COMMENT '委托规则',
  `FLOW_DOC` varchar(2) DEFAULT NULL COMMENT '工作流中是否可以使用附件',
  `AUTO_CODE` varchar(200) DEFAULT NULL COMMENT '工作流文号',
  `AUTO_NUM` varchar(10) DEFAULT '0' COMMENT '工作流计数',
  `SEND_TO_USER` varchar(1000) DEFAULT NULL COMMENT '流程结束是传阅人员',
  `SEND_TO_MODULE` varchar(50) DEFAULT NULL COMMENT '流程结束是发布到指定的模块',
  `SAVE_PATH` varchar(100) DEFAULT NULL COMMENT '工作流结时自动存档的物理路径',
  `SAVE_FILE` varchar(2) DEFAULT NULL,
  `FLOW_WAIT` varchar(2) DEFAULT '0',
  `ALL_QUERY_USER` text,
  `DEPT_QUERY_USER` text,
  `OTHER_DEPT_QUERY_USER` text,
  `LEAVE_QUERY_USER` text,
  `ALL_MANAGE_USER` text,
  `DEPT_MANAGE_USER` text,
  `OTHER_DEPT_MANAGE_USER` text,
  `FLOW_FUNCTION` text,
  `PRINT_FIELD` varchar(2000) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_flow
-- ----------------------------
INSERT INTO `work_flow` VALUES ('78', '2B969F8D-07EE-5E4F-8DB1-2307472132BB', '1', '请款流程', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', '1', '6A700178-D07E-97AC-63B0-5C1D4F190A08', 'admin', 'workflow', '2015-12-29 13:00:24', 'FEEB7545-8A51-2B06-D70F-27BCE37B83C9', '0', '0', '1', '1', '[U]-[F]-[yyyy]年[MM]月[dd]日', '', '', null, '', '0', '', null, null, null, null, null, null, null, '', 'bmzjsp,ceosp,cwsh,qkreason,cwqm,cnqr,ceoqm,qkr,qktime,qkxm,qljedx,xxje,bmzjqm,bm', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow` VALUES ('73', 'DE6B6BE4-2824-1655-493F-6889A6C0C145', '1', '办公用品申请', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', '1', '0E6A3B7D-7C00-D3F6-12FA-C84B3FC6D524', 'admin', 'workflow', '2015-12-24 13:02:34', '3DC52D35-550E-4180-BD93-78A84F82243C', '0', '0', '1', '1', '[U]-[F]-[yyyy]年[MM]月[dd]日', '', '', null, '', '0', '', null, null, null, null, null, null, null, '', 'beizhu,bmspyi,xzspyj,syr,sfsxs,bmldqm,xzldqm,sqresone,bm,name,zhiwei,sqwp,shuliang,sqtime', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow` VALUES ('79', '3E1DE851-E31B-D81D-A841-AD52A5EA71F9', '2', '请假申请流程', '14E366B1-F3CB-F3B6-02F1-39195CC33446', '1', '89011F58-0243-73E2-C289-DB9C4E050510', 'admin', 'workflow', '2015-12-29 13:06:48', '4489B7D2-E79A-D34C-183F-A17C2FAAB02E', '0', '0', '1', '1', '[U]-[F]-[yyyy]年[MM]月[dd]日', '', '', null, '', '0', '', null, null, null, null, null, null, null, '', 'dszyj,xzjybm,resons,bmjlyj,ceoqm,bmjlqm,bmzjqm,zts,secondtime,qjlb,firsttimes,bumen,wangwei,name', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow` VALUES ('80', '9137AC48-7432-F5D6-CAB0-C14546C63049', '3', '出差申请流程', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', '1', '89011F58-0243-73E2-C289-DB9C4E050510', 'admin', 'workflow', '2015-12-29 13:19:57', 'A1967185-9D40-6CFC-41E8-81C0F7EB1850', '0', '0', '1', '1', '[U]-[F]-[yyyy]年[MM]月[dd]日', '', '', null, '', '0', '', null, null, null, null, null, null, null, '', 'ceoyj,bmzjyj,ysmx,zsxx,sxryxx,syjrw,rzbqr,cctime,bmzjqm,ceoqm,mdd,zw,jtgjjbc,bm,fzrxm', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow` VALUES ('77', 'C9175369-B468-37E9-4E10-C128C27BE59E', '1', '报销流程', '08B9C566-58E5-2035-7617-A62E0644BFCC', '1', '6A700178-D07E-97AC-63B0-5C1D4F190A08', 'admin', 'workflow', '2015-12-29 12:44:07', 'DE4B62DA-A438-7958-3B23-A500CBF5F112', '0', '0', '1', '1', '[U]-[F]-[yyyy]年[MM]月[dd]日', '', '', null, '', '0', '', null, null, null, null, null, null, null, '', 'baoxiaoshiyou,cnqz,ceoqz,cwzgqz,kjqz,tbren,bmzjqz,djzs,jexx,hjjedx,bxren,bm', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow` VALUES ('76', '83524B7A-F541-CF35-4464-B10D93AAE996', '1', '补休、串休、加班申请', '43F0F395-5FD2-C20C-921D-85B6162FFB60', '1', '89011F58-0243-73E2-C289-DB9C4E050510', 'admin', 'workflow', '2015-12-25 09:43:44', 'DE8B7914-EECD-E254-8640-A23D79FA8FE8', '0', '0', '1', '1', '[U]-[F]-[yyyy]年[MM]月[dd]日', '', '', null, '', '0', '', null, null, null, null, null, null, null, '', 'ceoyj,bmjlyj,bxyy,rzbqr,name,firsttime,overtime,bmjlqm,bmzjqm,ceoqm,bmzjyj,bm', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow` VALUES ('87', '11169EC6-F406-0433-B877-81D9F455BDDD', '2222', '内部报销', 'EFBDBA10-B66F-B40C-365B-E0646FF392F0', '1', '6A700178-D07E-97AC-63B0-5C1D4F190A08', 'admin', 'workflow', '2016-09-06 17:28:02', '6D2488BB-DDEE-D35E-16D8-D13F87AE3525', '0', '0', '1', '1', '', '', '', null, '', '0', '', null, null, null, null, null, null, null, '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow` VALUES ('89', 'B65BC06C-E7B5-FE8B-8CB8-DB4165661FC5', '2', '测试流程001', '0E379538-A7F4-E947-9319-CB2C9B195014', '1', '5098117B-83F1-C5D2-315B-2049BDF9ECC4', 'admin', 'workflow', '2016-11-21 10:15:54', 'C1F65143-DF06-1BC9-437C-55CFBC95CC86', '0', '0', '1', '1', '', '', '', null, '', '0', '0', null, null, null, null, null, null, null, '', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow` VALUES ('90', '45ADC2E6-4FA6-189C-A7DB-DEC62436F437', '1', '测试级别审批', 'E37BBB32-FF47-6924-F4FA-FEB54732D62E', '1', '1C6A8E82-0952-1C0B-EB89-D06DB9D0547D', 'admin', 'workflow', '2016-12-01 16:52:58', 'A4C4CC2F-4A30-1157-06A5-8BCFD9C08C84', '1', '0', '1', '1', '', '', '', null, '', '0', '', null, null, null, null, null, null, null, '', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for work_flow_data_source
-- ----------------------------
DROP TABLE IF EXISTS `work_flow_data_source`;
CREATE TABLE `work_flow_data_source` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DB_SOURCE_ID` varchar(50) DEFAULT NULL,
  `DB_SOURCE_NAME` varchar(200) DEFAULT NULL,
  `DB_SOURCE_TYPE` varchar(30) DEFAULT NULL,
  `DB_LINK` varchar(200) DEFAULT NULL,
  `DB_USER_NAME` varchar(200) DEFAULT NULL,
  `DB_USER_PASSWD` varchar(50) DEFAULT NULL,
  `DB_NAME` varchar(100) DEFAULT NULL,
  `CREATE_ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_flow_data_source
-- ----------------------------
INSERT INTO `work_flow_data_source` VALUES ('16', '6A63221B-724A-75D6-C5B3-E534B06DD5C3', '进销存', 'MSSQL', '127.0.0.1:1433', 'sa', 'myoa888', 'tfdsys', 'admin', '', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for work_flow_form
-- ----------------------------
DROP TABLE IF EXISTS `work_flow_form`;
CREATE TABLE `work_flow_form` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FORM_ID` varchar(50) DEFAULT NULL,
  `FORM_NAME` varchar(50) DEFAULT NULL,
  `FORM_TABLE_NAME` varchar(50) DEFAULT NULL,
  `FORM_CREATE_USER` varchar(20) DEFAULT NULL,
  `FORM_DEPT_ID` varchar(50) DEFAULT NULL,
  `FORM_CREATE_TIME` date DEFAULT NULL,
  `FORM_TYPE` varchar(20) DEFAULT NULL,
  `FORM_CODE` text,
  `FORM_STYLE` text,
  `WORK_FLOW_TYPE_ID` varchar(50) DEFAULT NULL,
  `SCRIPT` text,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_flow_form
-- ----------------------------
INSERT INTO `work_flow_form` VALUES ('65', 'D6919F7E-1CEF-D77F-4CF3-22792B258A22', '出差申请单', 'ccsqd', 'admin', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '2015-12-29', 'workflow', '<p style=\"text-align: center;\"><span style=\"font-size: 24px;\">出差申请单</span></p>\n\n<table align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 635px;\">\n	<tbody>\n		<tr>\n			<td>负责人姓名</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"fzrxm\" id=\"fzrxm\" maxlength=\"10\" name=\"fzrxm\" title=\"负责人姓名\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n			<td>部门</td>\n			<td><input datatype=\"text\" fieldname=\"bm\" id=\"bm\" model=\"{&quot;type&quot;:&quot;13&quot;,&quot;format&quot;:null}\" name=\"bm\" title=\"部门\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n		<tr>\n			<td>职务</td>\n			<td><input datatype=\"text\" fieldname=\"zw\" id=\"zw\" model=\"{&quot;type&quot;:&quot;10&quot;,&quot;format&quot;:null}\" name=\"zw\" style=\"\" title=\"职务\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td>交通工具及班次</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"jtgjjbc\" id=\"jtgjjbc\" maxlength=\"100\" name=\"jtgjjbc\" title=\"交通工具及班次\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n		</tr>\n		<tr>\n			<td>目的地</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"mdd\" id=\"mdd\" maxlength=\"30\" name=\"mdd\" title=\"目的地\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n			<td>出差时间</td>\n			<td><input datatype=\"text\" fieldname=\"cctime\" id=\"cctime\" model=\"{&quot;type&quot;:&quot;1&quot;,&quot;format&quot;:&quot;yyyy-MM-dd&quot;}\" title=\"出差时间\" type=\"text\" value=\"时间{选择控件}\" xtype=\"xfetch\" /></td>\n		</tr>\n		<tr>\n			<td>随行人员信息</td>\n			<td colspan=\"3\" rowspan=\"1\"><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"sxryxx\" id=\"sxryxx\" name=\"sxryxx\" style=\"width: 389px; height: 60px;\" title=\"随行人员信息\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td>事由及任务</td>\n			<td colspan=\"3\" rowspan=\"1\"><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"syjrw\" id=\"syjrw\" name=\"syjrw\" style=\"width: 389px; height: 36px;\" title=\"事由及任务\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td>住宿信息</td>\n			<td colspan=\"3\" rowspan=\"1\"><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"zsxx\" id=\"zsxx\" name=\"zsxx\" style=\"width: 389px; height:50px;\" title=\"住宿信息\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td>预算明细</td>\n			<td colspan=\"3\" rowspan=\"1\"><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"ysmx\" id=\"ysmx\" name=\"ysmx\" style=\"width: 389px; height: 60px;\" title=\"预算明细\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td>部门总监意见</td>\n			<td colspan=\"3\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"bmzjyj\" id=\"bmzjyj\" name=\"bmzjyj\" style=\"width: 389px; height: 36px;\" title=\"部门总监意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><img datatype=\"text\" fieldname=\"fasdfas\" height=\"20\" id=\"fasdfas\" name=\"fasdfas\" src=\"/tfd/system/styles/images/workflow/imgup.png\" title=\"asdfasd\" width=\"10\" xtype=\"ximg\" /><input datatype=\"text\" fieldname=\"bmzjqm\" id=\"bmzjqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"bmzjqm\" style=\"\" title=\"部门总监签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td>董事长意见</td>\n			<td colspan=\"3\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"ceoyj\" id=\"ceoyj\" name=\"ceoyj\" style=\"width: 389px; height: 36px;\" title=\"董事长意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"ceoqm\" id=\"ceoqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"ceoqm\" style=\"\" title=\"董事长签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td>人资部确认</td>\n			<td colspan=\"3\" rowspan=\"1\"><input datatype=\"text\" fieldname=\"rzbqr\" id=\"rzbqr\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"rzbqr\" style=\"\" title=\"人资部确认\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n	</tbody>\n</table>\n\n<p>&nbsp;</p>\n', null, '89011F58-0243-73E2-C289-DB9C4E050510', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_form` VALUES ('58', 'D5FC7924-BF93-CF1E-19EB-54A1BF38D83D', '办公用品申领单', 'bgypsld', 'admin', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '2015-12-24', 'workflow', '<p style=\"text-align: center;\"><span style=\"font-size: 24px;\">办公用品申领单</span></p>\n\n<table align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 720px;\">\n	<tbody>\n		<tr>\n			<td style=\"text-align: center;\">部门</td>\n			<td><input datatype=\"text\" fieldname=\"bm\" id=\"bm\" model=\"{\'type\':\'13\',\'format\':null}\" name=\"bm\" title=\"部门\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td style=\"text-align: center;\">申请人</td>\n			<td><input datatype=\"text\" fieldname=\"name\" id=\"name\" model=\"{\'type\':\'8\',\'format\':null}\" name=\"name\" title=\"申请人\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td style=\"text-align: center;\">职位</td>\n			<td><input datatype=\"text\" fieldname=\"zhiwei\" id=\"zhiwei\" model=\"{\'type\':\'10\',\'format\':null}\" name=\"zhiwei\" title=\"职位\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n		<tr>\n			<td style=\"text-align: center;\">申请物品</td>\n			<td colspan=\"3\" rowspan=\"1\"><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"sqwp\" id=\"sqwp\" maxlength=\"100\" name=\"sqwp\" title=\"申请物品\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n			<td style=\"text-align: center;\">数量</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"shuliang\" id=\"shuliang\" maxlength=\"20\" name=\"shuliang\" title=\"数量\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n		</tr>\n		<tr>\n			<td style=\"text-align: center;\">申请时间</td>\n			<td><input datatype=\"text\" fieldname=\"sqtime\" id=\"sqtime\" model=\"{\'type\':\'1\',\'format\':\'yyyy-MM-dd\'}\" title=\"申请时间\" type=\"text\" value=\"时间{选择控件}\" xtype=\"xfetch\" /></td>\n			<td style=\"text-align: center;\">使用人</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"syr\" id=\"syr\" maxlength=\"10\" name=\"syr\" title=\"使用人姓名\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n			<td style=\"text-align: center;\">是否实习生</td>\n			<td><input datatype=\"text\" fieldname=\"sfsxs\" name=\"sfsxs\" title=\"是否实习生\" type=\"radio\" value=\"\" xtype=\"xradio\" />是 <input datatype=\"text\" fieldname=\"sfsxs\" name=\"sfsxs\" title=\"是否实习生\" type=\"radio\" value=\"\" xtype=\"xradio\" />否</td>\n		</tr>\n		<tr>\n			<td style=\"text-align: center;\">申请原因</td>\n			<td colspan=\"5\" rowspan=\"1\"><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"sqresone\" id=\"sqresone\" name=\"sqresone\" style=\"width: 434px; height: 84px;\" title=\"申请原因\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td style=\"text-align: center;\">备注</td>\n			<td colspan=\"5\" rowspan=\"1\"><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"beizhu\" id=\"beizhu\" name=\"beizhu\" style=\"width: 434px; height: 50px;\" title=\"备注\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td style=\"text-align: center;\">部门审批</td>\n			<td colspan=\"5\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"bmspyi\" id=\"bmspyi\" name=\"bmspyi\" style=\"width: 434px; height: 84px;\" title=\"部门审批意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"bmldqm\" id=\"bmldqm\" model=\"{\'type\':\'12\',\'format\':null}\" name=\"bmldqm\" title=\"部门领导签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td>行政审批</td>\n			<td colspan=\"5\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"xzspyj\" id=\"xzspyj\" name=\"xzspyj\" style=\"width: 434px; height: 84px;\" title=\"行政审批意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"xzldqm\" id=\"xzldqm\" model=\"{\'type\':\'12\',\'format\':null}\" name=\"xzldqm\" title=\"行政领导签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n	</tbody>\n</table>\n\n<p style=\"text-align: center;\">&nbsp;</p>\n', null, '0E6A3B7D-7C00-D3F6-12FA-C84B3FC6D524', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_form` VALUES ('64', 'BD9CD167-0BF9-CA23-EC6D-C6DFD810039C', '请款申请单', 'qksqd', 'admin', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '2015-12-29', 'workflow', '<p style=\"text-align: center;\"><span style=\"font-size: 24px;\">请款申请单</span></p>\n\n<table align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 630px;\">\n	<tbody>\n		<tr>\n			<td>部门</td>\n			<td><input datatype=\"text\" fieldname=\"bm\" id=\"bm\" model=\"{&quot;type&quot;:&quot;13&quot;,&quot;format&quot;:null}\" name=\"bm\" title=\"部门\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td>请款人</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"qkr\" id=\"qkr\" maxlength=\"10\" name=\"qkr\" title=\"请款人\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n		</tr>\n		<tr>\n			<td>请款时间</td>\n			<td><input datatype=\"text\" fieldname=\"qktime\" id=\"qktime\" model=\"{&quot;type&quot;:&quot;1&quot;,&quot;format&quot;:&quot;yyyy-MM-dd&quot;}\" title=\"请款时间\" type=\"text\" value=\"时间{选择控件}\" xtype=\"xfetch\" /></td>\n			<td>请款项目</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"qkxm\" id=\"qkxm\" maxlength=\"30\" name=\"qkxm\" title=\"请款项目\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n		</tr>\n		<tr>\n			<td>请款金额大写</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"qljedx\" id=\"qljedx\" maxlength=\"40\" name=\"qljedx\" title=\"请款金额大写\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n			<td>小写</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"xxje\" id=\"xxje\" maxlength=\"30\" name=\"xxje\" title=\"小写金额\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n		</tr>\n		<tr>\n			<td>请款事由</td>\n			<td colspan=\"3\" rowspan=\"1\"><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"qkreason\" id=\"qkreason\" name=\"qkreason\" style=\"width: 449px; height: 75px;\" title=\"请款事由\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td>部门总监审批</td>\n			<td colspan=\"3\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"bmzjsp\" id=\"bmzjsp\" name=\"bmzjsp\" style=\"width: 449px; height: 50px;\" title=\"部门总监审批\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"bmzjqm\" id=\"bmzjqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"bmzjqm\" title=\"部门总监签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td>董事长审批</td>\n			<td colspan=\"3\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"ceosp\" id=\"ceosp\" name=\"ceosp\" style=\"width: 449px; height: 50px;\" title=\"董事长审批\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"ceoqm\" id=\"ceoqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"ceoqm\" title=\"董事长签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td>财务审核</td>\n			<td colspan=\"3\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"cwsh\" id=\"cwsh\" name=\"cwsh\" style=\"width: 449px; height: 50px;\" title=\"财务审核\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"cwqm\" id=\"cwqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"cwqm\" title=\"财务签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td>出纳确认</td>\n			<td colspan=\"3\" rowspan=\"1\"><input datatype=\"text\" fieldname=\"cnqr\" id=\"cnqr\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"cnqr\" title=\"出纳确认\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n	</tbody>\n</table>\n\n<p>&nbsp;</p>\n', null, '6A700178-D07E-97AC-63B0-5C1D4F190A08', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_form` VALUES ('62', '14E366B1-F3CB-F3B6-02F1-39195CC33446', '请假申请单', 'qjsqd', 'admin', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '2015-12-29', 'workflow', '<p style=\"text-align: center;\"><span style=\"font-size: 32px;\">请假申请单</span></p>\n\n<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 685px;\" width=\"684\">\n	<tbody>\n		<tr>\n			<td style=\"height: 38px;\" width=\"129\">\n			<p align=\"center\">姓名</p>\n			</td>\n			<td style=\"height: 38px;\" width=\"91\"><input datatype=\"text\" fieldname=\"name\" id=\"name\" model=\"{type:8,format:\'null\'}\" name=\"name\" title=\"姓名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td style=\"height: 38px;\" width=\"82\">\n			<p align=\"center\">岗位</p>\n			</td>\n			<td style=\"height: 38px;\" width=\"133\"><input datatype=\"text\" fieldname=\"wangwei\" id=\"wangwei\" model=\"{type:10,format:\'null\'}\" name=\"wangwei\" title=\"岗位\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td style=\"height: 38px;\" width=\"73\">\n			<p align=\"center\">部门</p>\n			</td>\n			<td style=\"height: 38px;\" width=\"163\"><input datatype=\"text\" fieldname=\"bumen\" id=\"bumen\" model=\"{type:13,format:\'null\'}\" name=\"bumen\" title=\"部门\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n		<tr>\n			<td style=\"height: 110px;\" width=\"129\">\n			<p align=\"center\">请假事由</p>\n			</td>\n			<td colspan=\"5\" style=\"height: 110px;\" width=\"542\"><textarea datatype=\"text\" fieldname=\"resons\" id=\"resons\" name=\"resons\" style=\"margin: 0px; width: 552px; height: 82px;\" title=\"请假事由\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td style=\"height: 66px;\" width=\"129\">\n			<p align=\"center\">请假类别</p>\n			</td>\n			<td colspan=\"5\" style=\"height: 66px;\" width=\"542\">\n			<p><input datatype=\"text\" fieldname=\"qjlb\" id=\"qjlb\" name=\"qjlb\" title=\"请假类别\" type=\"radio\" value=\"病假\" xtype=\"xradio\" />病假&nbsp;<input datatype=\"text\" fieldname=\"qjlb\" id=\"qjlb\" name=\"qjlb\" title=\"请假类别\" type=\"radio\" value=\"事假\" xtype=\"xradio\" />事假&nbsp;<input datatype=\"text\" fieldname=\"qjlb\" id=\"qjlb\" name=\"qjlb\" title=\"请假类别\" type=\"radio\" value=\"婚假\" xtype=\"xradio\" />婚假&nbsp;<input datatype=\"text\" fieldname=\"qjlb\" id=\"qjlb\" name=\"qjlb\" title=\"请假类别\" type=\"radio\" value=\"产假\" xtype=\"xradio\" />产假&nbsp;<input datatype=\"text\" fieldname=\"qjlb\" name=\"qjlb\" title=\"请假类别\" type=\"radio\" value=\"\" xtype=\"xradio\" />丧假&nbsp;<input datatype=\"text\" fieldname=\"qjlb\" name=\"qjlb\" title=\"请假类别\" type=\"radio\" value=\"\" xtype=\"xradio\" />陪产假</p>\n			</td>\n		</tr>\n		<tr>\n			<td style=\"height: 126px;\" width=\"129\">\n			<p align=\"center\">请假日期</p>\n			</td>\n			<td colspan=\"5\" style=\"height: 126px;\" width=\"542\">\n			<p>从<input datatype=\"text\" fieldname=\"firsttimes\" id=\"firsttimes\" model=\"{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}\" name=\"firsttimes\" title=\"请假开始时间\" type=\"text\" value=\"时间{选择控件}\" xtype=\"xfetch\" />到<input datatype=\"text\" fieldname=\"secondtime\" id=\"secondtime\" model=\"{\'type\':1,\'format\':\'yyyy-MM-dd\',\'def\':1}\" name=\"secondtime\" title=\"请假结束时间\" type=\"text\" value=\"时间{选择控件}\" xtype=\"xfetch\" />总计<input datatype=\"varchar\" fieldname=\"zts\" id=\"zts\" maxlength=\"10\" name=\"zts\" title=\"总天数\" type=\"text\" xtype=\"xinput\" />天</p>\n			</td>\n		</tr>\n		<tr>\n			<td style=\"height: 76px;\" width=\"129\">\n			<p align=\"center\">部门经理意见</p>\n			</td>\n			<td colspan=\"5\" style=\"height: 76px;\" width=\"542\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"bmjlyj\" id=\"bmjlyj\" name=\"bmjlyj\" style=\"margin: 0px; width: 552px; height: 82px;\" title=\"部门经理意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"bmjlqm\" id=\"bmjlqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"bmjlqm\" title=\"部门经理签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td style=\"height: 76px;\" width=\"129\">\n			<p align=\"center\">部门总监意见</p>\n			</td>\n			<td colspan=\"5\" style=\"height: 76px;\" width=\"542\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"xzjybm\" id=\"xzjybm\" name=\"xzjybm\" style=\"margin: 0px; width: 552px; height: 82px;\" title=\"部门总监意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"bmzjqm\" id=\"bmzjqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"bmzjqm\" title=\"部门总监签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td style=\"height: 76px;\" width=\"129\">\n			<p align=\"center\">董事长意见</p>\n			</td>\n			<td colspan=\"5\" style=\"height: 76px;\" width=\"542\">\n			<p><textarea datatype=\"text\" fieldname=\"dszyj\" id=\"dszyj\" name=\"dszyj\" style=\"margin: 0px; width: 552px; height: 82px;\" title=\"董事长意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"ceoqm\" id=\"ceoqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"ceoqm\" title=\"董事长签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td style=\"height: 105px;\" width=\"129\">\n			<p align=\"center\">备 注</p>\n			</td>\n			<td colspan=\"5\" style=\"height: 105px;\" width=\"542\">\n			<p>请假三天以内的，由部门总监批准结束；</p>\n\n			<p>请假三天（含）以上的，应报董事长批准；</p>\n			</td>\n		</tr>\n	</tbody>\n</table>\n', null, '89011F58-0243-73E2-C289-DB9C4E050510', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_form` VALUES ('63', '08B9C566-58E5-2035-7617-A62E0644BFCC', '报销申请单', 'bxsqd', 'admin', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '2015-12-29', 'workflow', '<p style=\"text-align: center;\"><span style=\"font-size: 24px;\">报销申请单</span></p>\n\n<table align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 635px;\">\n	<tbody>\n		<tr>\n			<td>部门</td>\n			<td><input datatype=\"text\" fieldname=\"bm\" id=\"bm\" model=\"{&quot;type&quot;:&quot;13&quot;,&quot;format&quot;:null}\" name=\"bm\" title=\"部门\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td>报销人</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"bxren\" id=\"bxren\" maxlength=\"16\" name=\"bxren\" title=\"报销人\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n		</tr>\n		<tr>\n			<td>报销事由</td>\n			<td colspan=\"3\" rowspan=\"1\"><textarea cols=\"1\" datatype=\"text\" defaultvalue=\"\" fieldname=\"baoxiaoshiyou\" id=\"baoxiaoshiyou\" name=\"baoxiaoshiyou\" rows=\"1\" style=\"width: 364px; height: 36px;\" title=\"报销事由\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td>合计金额大写</td>\n			<td colspan=\"3\" rowspan=\"1\"><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"hjjedx\" id=\"hjjedx\" maxlength=\"52\" name=\"hjjedx\" title=\"合计金额大写\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n		</tr>\n		<tr>\n			<td>金额小写</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"jexx\" id=\"jexx\" maxlength=\"20\" name=\"jexx\" title=\"金额小写\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n			<td>单据张数</td>\n			<td><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"djzs\" id=\"djzs\" maxlength=\"10\" name=\"djzs\" title=\"单据张数\" type=\"text\" value=\"\" xtype=\"xinput\" /></td>\n		</tr>\n		<tr>\n			<td>部门总监签字</td>\n			<td><input datatype=\"text\" fieldname=\"bmzjqz\" id=\"bmzjqz\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"bmzjqz\" title=\"部门总监签字\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td>填报人</td>\n			<td><input datatype=\"text\" fieldname=\"tbren\" id=\"tbren\" model=\"{&quot;type&quot;:&quot;8&quot;,&quot;format&quot;:null}\" name=\"tbren\" title=\"填报人\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n		<tr>\n			<td>财务主管签字</td>\n			<td><input datatype=\"text\" fieldname=\"cwzgqz\" id=\"cwzgqz\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"cwzgqz\" title=\"财务主管签字\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td>会计签字</td>\n			<td><input datatype=\"text\" fieldname=\"kjqz\" id=\"kjqz\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"kjqz\" title=\"会计签字\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n		<tr>\n			<td>出纳签字</td>\n			<td><input datatype=\"text\" fieldname=\"cnqz\" id=\"cnqz\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"cnqz\" title=\"出纳签字\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td>董事长签字</td>\n			<td><input datatype=\"text\" fieldname=\"ceoqz\" id=\"ceoqz\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"ceoqz\" title=\"董事长签字\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n	</tbody>\n</table>\n\n<p>&nbsp;</p>\n', null, '6A700178-D07E-97AC-63B0-5C1D4F190A08', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_form` VALUES ('61', '43F0F395-5FD2-C20C-921D-85B6162FFB60', '补休、串休、加班申请单', 'bxsqd', 'admin', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '2015-12-24', 'workflow', '<p style=\"text-align: center;\"><span style=\"font-size: 22px;\">补休、加班、串休申请单</span></p>\n\n<table align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 700px;\">\n	<tbody>\n		<tr>\n			<td>部门</td>\n			<td><input datatype=\"text\" fieldname=\"bm\" id=\"bm\" model=\"{&quot;type&quot;:&quot;13&quot;,&quot;format&quot;:null}\" name=\"bm\" title=\"部门\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td>姓名</td>\n			<td><input datatype=\"text\" fieldname=\"name\" id=\"name\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"name\" title=\"姓名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td>角色</td>\n			<td><input datatype=\"text\" fieldname=\"zhiwei\" id=\"zhiwei\" model=\"{&quot;type&quot;:&quot;10&quot;,&quot;format&quot;:null}\" name=\"zhiwei\" style=\"\" title=\"职位\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n		<tr>\n			<td>申请类型</td>\n			<td colspan=\"5\"><input datatype=\"text\" fieldname=\"sqlx\" name=\"sqlx\" title=\"申请类型\" type=\"radio\" value=\"加班\" xtype=\"xradio\" />加班&nbsp;<input datatype=\"text\" fieldname=\"sqlx\" name=\"sqlx\" title=\"申请类型\" type=\"radio\" value=\"串休\" xtype=\"xradio\" />串休&nbsp;<input datatype=\"text\" fieldname=\"sqlx\" name=\"sqlx\" title=\"申请类型\" type=\"radio\" value=\"补休\" xtype=\"xradio\" />补休</td>\n		</tr>\n		<tr>\n			<td>申请原因</td>\n			<td colspan=\"5\" rowspan=\"1\"><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"sqyy\" id=\"sqyy\" name=\"sqyy\" style=\"width: 334px; height: 42px;\" title=\"申请原因\" xtype=\"xtextarea\"></textarea></td>\n		</tr>\n		<tr>\n			<td>开始时间</td>\n			<td><input datatype=\"text\" fieldname=\"firsttime\" id=\"firsttime\" model=\"{&quot;type&quot;:&quot;1&quot;,&quot;format&quot;:&quot;yyyy-MM-dd HH:mm&quot;}\" style=\"\" title=\"开始时间\" type=\"text\" value=\"时间{选择控件}\" xtype=\"xfetch\" /></td>\n			<td>结束时间</td>\n			<td colspan=\"3\"><input datatype=\"text\" fieldname=\"overtime\" id=\"overtime\" model=\"{&quot;type&quot;:&quot;1&quot;,&quot;format&quot;:&quot;yyyy-MM-dd HH:mm&quot;}\" style=\"\" title=\"结束时间\" type=\"text\" value=\"时间{选择控件}\" xtype=\"xfetch\" /></td>\n		</tr>\n		<tr>\n			<td>部门经理意见</td>\n			<td colspan=\"5\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"bmjlyj\" id=\"bmjlyj\" name=\"bmjlyj\" style=\"width: 500px; height: 42px;\" title=\"部门经理意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"bmjlqm\" id=\"bmjlqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"bmjlqm\" title=\"部门经理签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td>部门总监意见</td>\n			<td colspan=\"5\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"bmzjyj\" id=\"bmzjyj\" name=\"bmzjyj\" style=\"width: 500px; height: 42px;\" title=\"部门总监意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"bmzjqm\" id=\"bmzjqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"bmzjqm\" title=\"部门总监签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td>董事长意见</td>\n			<td colspan=\"5\" rowspan=\"1\">\n			<p><textarea datatype=\"text\" defaultvalue=\"\" fieldname=\"ceoyj\" id=\"ceoyj\" name=\"ceoyj\" style=\"width: 500px; height: 42px;\" title=\"董事长意见\" xtype=\"xtextarea\"></textarea></p>\n\n			<p>签名：</p>\n\n			<p><input datatype=\"text\" fieldname=\"ceoqm\" id=\"ceoqm\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"ceoqm\" title=\"董事长签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></p>\n			</td>\n		</tr>\n		<tr>\n			<td>人资部确认</td>\n			<td colspan=\"5\" rowspan=\"1\"><input datatype=\"text\" fieldname=\"rzbqr\" id=\"rzbqr\" model=\"{&quot;type&quot;:&quot;12&quot;,&quot;format&quot;:null}\" name=\"rzbqr\" title=\"人资部签名\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n	</tbody>\n</table>\n\n<p>&nbsp;</p>\n', null, '89011F58-0243-73E2-C289-DB9C4E050510', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_form` VALUES ('67', 'E37BBB32-FF47-6924-F4FA-FEB54732D62E', '我的表单', 'mytabel', 'admin', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '2016-03-11', 'workflow', null, null, '5098117B-83F1-C5D2-315B-2049BDF9ECC4', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_form` VALUES ('73', 'EFBDBA10-B66F-B40C-365B-E0646FF392F0', '内部报销单', 'nbbx', 'admin', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '2016-09-06', 'workflow', '<meta http-equiv=\"Content-Language\" content=\"zh-cn\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">\n<title></title>\n<p align=\"center\"><b><font size=\"5\">报&nbsp; 销&nbsp; 单</font></b></p>\n\n<p align=\"center\">&nbsp;</p>\n\n<div align=\"center\">\n<table border=\"1\" bordercolor=\"#000000\" cellpadding=\"6\" cellspacing=\"0\" width=\"72%\">\n	<tbody>\n		<tr>\n			<td width=\"136\">\n			<p align=\"center\">报销人</p>\n			</td>\n			<td><input datatype=\"text\" fieldname=\"bxr\" id=\"bxr\" model=\"{&quot;type&quot;:&quot;8&quot;,&quot;format&quot;:null}\" name=\"bxr\" title=\"报销人\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n			<td width=\"131\">\n			<p align=\"center\">报销日期</p>\n			</td>\n			<td width=\"274\"><input datatype=\"text\" fieldname=\"bxrq\" id=\"bxrq\" model=\"{&quot;type&quot;:&quot;2&quot;,&quot;format&quot;:&quot;yyyy-MM-dd&quot;}\" name=\"bxrq\" title=\"报销日期\" type=\"text\" value=\"{宏控件}\" xtype=\"xmacro\" /></td>\n		</tr>\n		<tr>\n			<td bgcolor=\"#c0c0c0\" colspan=\"4\">\n			<p align=\"center\"><b>报&nbsp; 销&nbsp; 明&nbsp; 细</b></p>\n			</td>\n		</tr>\n		<tr>\n			<td colspan=\"4\"><img childtable=\"bxmx\" data=\"\" header=\"\" id=\"bxmx\" model=\"[{&quot;title&quot;:&quot;日期&quot;,&quot;fieldname&quot;:&quot;rq&quot;,&quot;width&quot;:&quot;100&quot;,&quot;stylewidth&quot;:&quot;100&quot;,&quot;sum&quot;:&quot;on&quot;,&quot;formula&quot;:&quot;&quot;,&quot;type&quot;:&quot;6&quot;,&quot;model&quot;:&quot;&quot;},{&quot;title&quot;:&quot;车费&quot;,&quot;fieldname&quot;:&quot;cf&quot;,&quot;width&quot;:&quot;100&quot;,&quot;stylewidth&quot;:&quot;100&quot;,&quot;sum&quot;:&quot;on&quot;,&quot;formula&quot;:&quot;&quot;,&quot;type&quot;:&quot;1&quot;,&quot;model&quot;:&quot;&quot;},{&quot;title&quot;:&quot;住宿费&quot;,&quot;fieldname&quot;:&quot;zsf&quot;,&quot;width&quot;:&quot;100&quot;,&quot;stylewidth&quot;:&quot;100&quot;,&quot;sum&quot;:&quot;on&quot;,&quot;formula&quot;:&quot;&quot;,&quot;type&quot;:&quot;1&quot;,&quot;model&quot;:&quot;&quot;},{&quot;title&quot;:&quot;市内交通&quot;,&quot;fieldname&quot;:&quot;snjt&quot;,&quot;width&quot;:&quot;100&quot;,&quot;stylewidth&quot;:&quot;100&quot;,&quot;sum&quot;:&quot;on&quot;,&quot;formula&quot;:&quot;&quot;,&quot;type&quot;:&quot;1&quot;,&quot;model&quot;:&quot;&quot;},{&quot;title&quot;:&quot;餐费&quot;,&quot;fieldname&quot;:&quot;cfei&quot;,&quot;width&quot;:&quot;100&quot;,&quot;stylewidth&quot;:&quot;100&quot;,&quot;sum&quot;:&quot;on&quot;,&quot;formula&quot;:&quot;&quot;,&quot;type&quot;:&quot;1&quot;,&quot;model&quot;:&quot;&quot;},{&quot;title&quot;:&quot;合计&quot;,&quot;fieldname&quot;:&quot;hj&quot;,&quot;width&quot;:&quot;100&quot;,&quot;stylewidth&quot;:&quot;100&quot;,&quot;sum&quot;:&quot;on&quot;,&quot;formula&quot;:&quot;cf+zsf+snjt+cfei&quot;,&quot;type&quot;:&quot;1&quot;,&quot;model&quot;:&quot;&quot;}]\" name=\"bxmx\" rows=\"800\" src=\"/tfd/system/styles/images/workflow/xlist.png\" title=\"报销明细\" xtype=\"xlist\" /></td>\n		</tr>\n		<tr>\n			<td width=\"136\">\n			<p align=\"center\">合计金额</p>\n			</td>\n			<td>&nbsp;</td>\n			<td width=\"131\">\n			<p align=\"center\">大写</p>\n			</td>\n			<td width=\"274\">&nbsp;</td>\n		</tr>\n		<tr>\n			<td width=\"136\">\n			<p align=\"center\">审核</p>\n			</td>\n			<td colspan=\"3\">&nbsp;</td>\n		</tr>\n	</tbody>\n</table>\n</div>\n', null, '6A700178-D07E-97AC-63B0-5C1D4F190A08', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_form` VALUES ('75', '0E379538-A7F4-E947-9319-CB2C9B195014', '测试表单001', 'testTab', 'admin', 'B75E54BD-7AD1-8E97-C588-F4571595B9B9', '2016-11-21', 'workflow', '<p>qqqqqqqqqq</p>\n\n<p><input datatype=\"varchar\" defaultvalue=\"\" fieldname=\"testname\" id=\"testname\" maxlength=\"10\" name=\"testname\" title=\"test001\" type=\"text\" value=\"\" xtype=\"xinput\" /></p>\n', null, '1C6A8E82-0952-1C0B-EB89-D06DB9D0547D', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for work_flow_query_set
-- ----------------------------
DROP TABLE IF EXISTS `work_flow_query_set`;
CREATE TABLE `work_flow_query_set` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUERY_ID` varchar(50) DEFAULT NULL,
  `QUERY_FIELD` varchar(50) DEFAULT NULL,
  `TITLE` varchar(40) DEFAULT NULL,
  `MODULE` text,
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `CREATE_ACCOUNT_ID` varchar(50) DEFAULT NULL,
  `FLOW_ID` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_flow_query_set
-- ----------------------------

-- ----------------------------
-- Table structure for work_flow_type
-- ----------------------------
DROP TABLE IF EXISTS `work_flow_type`;
CREATE TABLE `work_flow_type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FLOW_TYPE_ID` varchar(50) DEFAULT NULL,
  `FLOW_TYPE_NAME` varchar(50) DEFAULT NULL,
  `MODULE_ID` varchar(20) DEFAULT NULL,
  `MANAGE_ACCOUNT_ID` varchar(20) DEFAULT NULL,
  `LEAVE_ID` varchar(50) DEFAULT NULL,
  `TOP_FLAG` varchar(50) DEFAULT NULL,
  `SORT_ID` int(3) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_flow_type
-- ----------------------------
INSERT INTO `work_flow_type` VALUES ('38', '0E6A3B7D-7C00-D3F6-12FA-C84B3FC6D524', '行政', 'workflow', 'admin', '', null, '1', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_type` VALUES ('39', '6A700178-D07E-97AC-63B0-5C1D4F190A08', '财务', 'workflow', 'admin', '', null, '2', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_type` VALUES ('40', '89011F58-0243-73E2-C289-DB9C4E050510', '人事', 'workflow', 'admin', '', null, '3', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_type` VALUES ('41', '1C6A8E82-0952-1C0B-EB89-D06DB9D0547D', '测试', 'workflow', 'admin', '', null, '4', '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_flow_type` VALUES ('42', '5098117B-83F1-C5D2-315B-2049BDF9ECC4', '项目', 'project', 'admin', '', null, '10', '8EADB678-A646-1E51-3E87-75A547B8AF19');

-- ----------------------------
-- Table structure for work_list
-- ----------------------------
DROP TABLE IF EXISTS `work_list`;
CREATE TABLE `work_list` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(100) DEFAULT NULL,
  `RUN_ID` varchar(50) DEFAULT NULL,
  `MODULE` varchar(30) DEFAULT NULL,
  `ACCOUNT_ID` varchar(20) DEFAULT NULL,
  `READ_URL` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `STATUS` varchar(2) DEFAULT NULL COMMENT '0是待办，１过程中，３已办结',
  `CREATE_TIME` varchar(20) DEFAULT NULL,
  `END_TIME` varchar(20) DEFAULT NULL,
  `PRCS_ID` int(2) DEFAULT NULL,
  `RUN_PRCS_ID` int(2) DEFAULT NULL,
  `DEL_FLAG` varchar(2) DEFAULT '0' COMMENT '删除标记，1删除',
  `PRCS_NAME` varchar(50) DEFAULT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=592 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_list
-- ----------------------------
INSERT INTO `work_list` VALUES ('584', '超级管理员-办公用品申请-2016年11月21日', 'DEA2FC19-EABA-7A2C-E606-2A54E3FFEA07', 'workflow', 'admin', '/system/workflow/dowork/bgypsld/print/index.jsp?runId=DEA2FC19-EABA-7A2C-E606-2A54E3FFEA07', '/system/workflow/dowork/bgypsld/1/index.jsp?runId=DEA2FC19-EABA-7A2C-E606-2A54E3FFEA07&runPrcsId=1', '0', '2016-11-21 12:47:25', null, '1', '1', '0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_list` VALUES ('585', '', '32858CFA-C500-BC64-3BFE-CA2BB3F5E263', 'workflow', 'admin', '/system/workflow/dowork/testtab/print/index.jsp?runId=32858CFA-C500-BC64-3BFE-CA2BB3F5E263', '/system/workflow/dowork/testtab/1/index.jsp?runId=32858CFA-C500-BC64-3BFE-CA2BB3F5E263&runPrcsId=1', '0', '2016-11-21 12:48:46', null, '1', '1', '0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_list` VALUES ('586', '', '66C83791-6160-25CC-AD56-78B5F765D4DB', 'workflow', 'admin', '/system/workflow/dowork/testtab/print/index.jsp?runId=66C83791-6160-25CC-AD56-78B5F765D4DB', '/system/workflow/dowork/testtab/1/index.jsp?runId=66C83791-6160-25CC-AD56-78B5F765D4DB&runPrcsId=1', '0', '2016-11-21 12:48:54', null, '1', '1', '0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_list` VALUES ('587', '111', '6633ECD6-F93F-9B08-F56E-D37B9A059AF3', 'workflow', 'admin', '/system/workflow/dowork/testtab/print/index.jsp?runId=6633ECD6-F93F-9B08-F56E-D37B9A059AF3', '/system/workflow/dowork/testtab/1/index.jsp?runId=6633ECD6-F93F-9B08-F56E-D37B9A059AF3&runPrcsId=1', '0', '2016-11-21 12:48:59', null, '1', '1', '0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_list` VALUES ('588', '', '075A540D-FB5B-1964-58DB-C04862C15C87', 'workflow', 'admin', '/system/workflow/dowork/testtab/print/index.jsp?runId=075A540D-FB5B-1964-58DB-C04862C15C87', '/system/workflow/dowork/testtab/1/index.jsp?runId=075A540D-FB5B-1964-58DB-C04862C15C87&runPrcsId=1', '0', '2016-11-21 12:51:24', null, '1', '1', '0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_list` VALUES ('589', 'ddd', '0D24A3E6-328C-FE21-4390-F32D201258FE', 'workflow', 'admin', '/system/workflow/dowork/testtab/print/index.jsp?runId=0D24A3E6-328C-FE21-4390-F32D201258FE', '/system/workflow/dowork/testtab/1/index.jsp?runId=0D24A3E6-328C-FE21-4390-F32D201258FE&runPrcsId=1', '0', '2016-11-21 12:51:27', null, '1', '1', '0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_list` VALUES ('590', '超级管理员-办公用品申请-2016年11月21日', 'D455D952-EDAA-8260-49D3-28FA3FF6DC44', 'workflow', 'admin', '/system/workflow/dowork/bgypsld/print/index.jsp?runId=D455D952-EDAA-8260-49D3-28FA3FF6DC44', '/system/workflow/dowork/bgypsld/1/index.jsp?runId=D455D952-EDAA-8260-49D3-28FA3FF6DC44&runPrcsId=1', '0', '2016-11-21 12:51:38', null, '1', '1', '0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
INSERT INTO `work_list` VALUES ('591', '超级管理员-办公用品申请-2017年01月23日', '76246F59-3945-443F-84CE-15BF5BC26FCD', 'workflow', 'admin', '/system/workflow/dowork/bgypsld/print/index.jsp?runId=76246F59-3945-443F-84CE-15BF5BC26FCD', '/system/workflow/dowork/bgypsld/1/index.jsp?runId=76246F59-3945-443F-84CE-15BF5BC26FCD&runPrcsId=1', '0', '2017-01-23 17:05:15', null, '1', '1', '0', null, '8EADB678-A646-1E51-3E87-75A547B8AF19');
