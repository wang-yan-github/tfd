package tfd.system.mobile.customer.act;

import java.net.URLDecoder;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tfd.system.mobile.customer.logic.CustomerInfoLogic;
import tfd.system.mobile.system.util.SystemUtil;
import tfd.system.unit.account.data.Account;
import tfd.system.unit.account.logic.AccountLogic;

import com.system.db.DbOp;
import com.system.db.DbPoolConnection;
import com.system.tool.GuId;
import com.system.tool.SysTool;

import customermanage.data.CustomerLinkman;
import customermanage.data.CustomerRecord;
import customermanage.data.Customerinfo;

public class CustomerInfoAct {

	/**
	 * 获取客户资料列表
	 * Time:2015-10-15
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void customerlist(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			String type=sysUtil.checkParam(response,"type",request.getParameter("type"));
			int page=Integer.parseInt(sysUtil.checkParam(response,"page",request.getParameter("page")));
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			if(type.equals("1")){
				returnData=customerInfoLogic.getcustomerLogic(dbConn, page, account.getAccountId(), account.getOrgId());
			}else if(type.equals("2")){
				returnData=customerInfoLogic.getbranchcustomerLogic(dbConn, account, page);
			}
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 客户信息查询
	 * Time:2015-10-15
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void customersearch(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			String keyword=sysUtil.checkParam(response,"keyword",request.getParameter("keyword"));
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.customersearchLogic(dbConn, account.getAccountId(), account.getOrgId(), keyword);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 客户置顶
	 * Time:2015-10-15
	 * Author:Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void customertop(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			String customerId=sysUtil.checkParam(response,"id",request.getParameter("id"));
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.customertopLogic(dbConn, account.getOrgId(), customerId);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 客户详情
	 * Time 2015-10-15
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void customerdetail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			String customerId=sysUtil.checkParam(response,"id",request.getParameter("id"));
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.customerdetailLogic(dbConn, customerId, account.getOrgId(),request);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 新增客户
	 * Time 2015-10-16
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addcustomer(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			Customerinfo customerinfo=new Customerinfo();
			customerinfo.setCustomerId(GuId.getGuid());
			customerinfo.setCustomerName(sysUtil.checkParam(response,"name",request.getParameter("name")));
			customerinfo.setCustomerStatus(sysUtil.checkParam(response,"state",request.getParameter("state")));
			customerinfo.setKeeper(sysUtil.checkParam(response,"managerid",request.getParameter("managerid")));
			customerinfo.setJoinStaff(sysUtil.checkParam(response,"partner",request.getParameter("partner")));
			customerinfo.setAddName(sysUtil.checkParam(response,"address",request.getParameter("address")));
			customerinfo.setUrlName(sysUtil.checkParam(response,"website",request.getParameter("website")));
			customerinfo.setTelNumber(sysUtil.checkParam(response,"mobile",request.getParameter("mobile")));
			customerinfo.setFaxNumber(sysUtil.checkParam(response,"fax",request.getParameter("fax")));
			customerinfo.seteMail(sysUtil.checkParam(response,"email",request.getParameter("email")));
			customerinfo.setTradeType(sysUtil.checkParam(response,"profession",request.getParameter("profession")));
			customerinfo.setAreaName(sysUtil.checkParam(response,"area",request.getParameter("area")));
			customerinfo.setCustomerType(sysUtil.checkParam(response,"type",request.getParameter("type")));
			customerinfo.setAccountId(account.getAccountId());
			customerinfo.setOrgId(account.getOrgId());
			customerinfo.setTop("0");
			customerinfo.setCustomerTime(SysTool.getCurDateTimeStr(  "yyyy-MM-dd HH:mm:ss"));
			customerinfo.setDelStatus(0);
			String contacts=URLDecoder.decode(request.getParameter("contacts"), "utf-8");
			String contactrecord=URLDecoder.decode(request.getParameter("contactrecord"), "utf-8");
			JSONArray jsonlinkman=JSONArray.fromObject(contacts);
			JSONArray jsonrecord=JSONArray.fromObject(contactrecord);
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.addcustomerLogic(dbConn, customerinfo);
			if(jsonlinkman.size()>0){
				CustomerLinkman customerLinkman=new CustomerLinkman();
				CustomerRecord customerRecord=new CustomerRecord();
				for (int i = 0; i < jsonlinkman.size(); i++) {
					customerLinkman.setLinkmanId(GuId.getGuid());
					customerLinkman.setCustomerId(customerinfo.getCustomerId());
					customerLinkman.setLinkmanName(sysUtil.checkParam(response,"name",jsonlinkman.getJSONObject(i).getString("name")));
					customerLinkman.setLinkmanJob(sysUtil.checkParam(response,"role",jsonlinkman.getJSONObject(i).getString("role")));
					customerLinkman.setLinkmanCall(sysUtil.checkParam(response,"appellation",jsonlinkman.getJSONObject(i).getString("appellation")));
					customerLinkman.setCellPhone(sysUtil.checkParam(response,"mobile",jsonlinkman.getJSONObject(i).getString("mobile")));
					customerLinkman.setHomePhone(sysUtil.checkParam(response,"tel",jsonlinkman.getJSONObject(i).getString("tel")));
					customerLinkman.setQqNumber(sysUtil.checkParam(response,"qq",jsonlinkman.getJSONObject(i).getString("qq")));
					customerLinkman.seteMail(sysUtil.checkParam(response,"email",jsonlinkman.getJSONObject(i).getString("email")));
					customerLinkman.setRemark(sysUtil.checkParam(response,"remark",jsonlinkman.getJSONObject(i).getString("remark")));
					customerLinkman.setOrgId(account.getOrgId());
					customerInfoLogic.addcontactLogic(dbConn, customerLinkman);
					if(jsonrecord.size()>0){
					for (int j = 0; j < jsonrecord.size(); j++) {
						if(sysUtil.checkParam(response,"contactid",jsonlinkman.getJSONObject(i).getString("contactid")).equals(sysUtil.checkParam(response,"contactid",jsonrecord.getJSONObject(j).getString("contactid")))){
							customerRecord.setRecordId(GuId.getGuid());
							customerRecord.setCustomerId(customerinfo.getCustomerId());
							customerRecord.setLinkmanId(customerLinkman.getLinkmanId());
							customerRecord.setRecordContent(sysUtil.checkParam(response,"content",jsonrecord.getJSONObject(j).getString("content")));
							customerRecord.setLon(sysUtil.checkParam(response,"lon",jsonrecord.getJSONObject(j).getString("lon")));
							customerRecord.setLat(sysUtil.checkParam(response,"lat",jsonrecord.getJSONObject(j).getString("lat")));
							customerRecord.setAddress(sysUtil.checkParam(response,"address",jsonrecord.getJSONObject(j).getString("address")));
							customerRecord.setDetail(sysUtil.checkParam(response,"detail",jsonrecord.getJSONObject(j).getString("detail")));
							customerRecord.setRecordWarn(sysUtil.checkParam(response,"remindtime",jsonrecord.getJSONObject(j).getString("remindtime")));
							customerRecord.setRecordlinkType(sysUtil.checkParam(response,"type",jsonrecord.getJSONObject(j).getString("type")));
							customerRecord.setRecordTime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
							customerRecord.setAccountId(account.getAccountId());
							customerRecord.setOrgId(account.getOrgId());
							returnData=customerInfoLogic.addcontactrecordLogic(dbConn, customerRecord);
						}
					}
					}
				}
			}
			if(jsonrecord.size()>0){
				CustomerRecord customerRecord=new CustomerRecord();
				for (int i = 0; i < jsonrecord.size(); i++) {
					if(sysUtil.checkParam(response,"contactid",jsonrecord.getJSONObject(i).getString("contactid")).equals("")){
						
						customerRecord.setRecordId(GuId.getGuid());
						customerRecord.setCustomerId(customerinfo.getCustomerId());
						customerRecord.setLinkmanId(jsonrecord.getJSONObject(i).getString("contactid"));
						customerRecord.setRecordContent(sysUtil.checkParam(response,"content",jsonrecord.getJSONObject(i).getString("content")));
						customerRecord.setLon(sysUtil.checkParam(response,"lon",jsonrecord.getJSONObject(i).getString("lon")));
						customerRecord.setLat(sysUtil.checkParam(response,"lat",jsonrecord.getJSONObject(i).getString("lat")));
						customerRecord.setAddress(sysUtil.checkParam(response,"address",jsonrecord.getJSONObject(i).getString("address")));
						customerRecord.setDetail(sysUtil.checkParam(response,"detail",jsonrecord.getJSONObject(i).getString("detail")));
						customerRecord.setRecordWarn(sysUtil.checkParam(response,"remindtime",jsonrecord.getJSONObject(i).getString("remindtime")));
						customerRecord.setRecordlinkType(sysUtil.checkParam(response,"type",jsonrecord.getJSONObject(i).getString("type")));
						customerRecord.setRecordTime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
						customerRecord.setAccountId(account.getAccountId());
						customerRecord.setOrgId(account.getOrgId());
						returnData=customerInfoLogic.addcontactrecordLogic(dbConn, customerRecord);
					}
				}
			}
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 新增联系人
	 * Time 2015-10-16
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addcontact(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			CustomerLinkman customerLinkman=new CustomerLinkman();
			customerLinkman.setLinkmanId(GuId.getGuid());
			customerLinkman.setCustomerId(sysUtil.checkParam(response,"id",request.getParameter("id")));
			customerLinkman.setLinkmanName(sysUtil.checkParam(response,"name",request.getParameter("name")));
			customerLinkman.setLinkmanJob(sysUtil.checkParam(response,"role",request.getParameter("role")));
			customerLinkman.setLinkmanCall(sysUtil.checkParam(response,"appellation",request.getParameter("appellation")));
			customerLinkman.setCellPhone(sysUtil.checkParam(response,"mobile",request.getParameter("mobile")));
			customerLinkman.setHomePhone(sysUtil.checkParam(response,"tel",request.getParameter("tel")));
			customerLinkman.setQqNumber(sysUtil.checkParam(response,"qq",request.getParameter("qq")));
			customerLinkman.seteMail(sysUtil.checkParam(response,"email",request.getParameter("email")));
			customerLinkman.setRemark(sysUtil.checkParam(response,"remark",request.getParameter("remark")));
			customerLinkman.setOrgId(account.getOrgId());
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.addcontactLogic(dbConn, customerLinkman);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 添加联系记录
	 * Time 2015-10-16
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addcontactrecord(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			
			CustomerRecord customerRecord=new CustomerRecord();
			customerRecord.setRecordId(GuId.getGuid());
			customerRecord.setCustomerId(sysUtil.checkParam(response,"id",request.getParameter("id")));
			customerRecord.setLinkmanId(sysUtil.checkParam(response,"contactid",request.getParameter("contactid")));
			customerRecord.setRecordContent(sysUtil.checkParam(response,"content",request.getParameter("content")));
			customerRecord.setLon(sysUtil.checkParam(response,"lon",request.getParameter("lon")));
			customerRecord.setLat(sysUtil.checkParam(response,"lat",request.getParameter("lat")));
			customerRecord.setAddress(sysUtil.checkParam(response,"address",request.getParameter("address")));
			customerRecord.setDetail(sysUtil.checkParam(response,"detail",request.getParameter("detail")));
			customerRecord.setRecordWarn(sysUtil.checkParam(response,"remind",request.getParameter("remind")));
			customerRecord.setRecordlinkType(sysUtil.checkParam(response,"type",request.getParameter("type")));
			customerRecord.setRecordTime(SysTool.getCurDateTimeStr("yyyy-MM-dd HH:mm:ss"));
			customerRecord.setAccountId(account.getAccountId());
			customerRecord.setOrgId(account.getOrgId());
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.addcontactrecordLogic(dbConn, customerRecord);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 编辑客户
	 * Time 2015-10-19
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void customeredit(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			Customerinfo customerinfo=new Customerinfo();
			customerinfo.setCustomerId(sysUtil.checkParam(response,"id",request.getParameter("id")));
			customerinfo.setCustomerName(sysUtil.checkParam(response,"name",request.getParameter("name")));
			customerinfo.setCustomerStatus(sysUtil.checkParam(response,"state",request.getParameter("state")));
			customerinfo.setKeeper(sysUtil.checkParam(response,"managerid",request.getParameter("managerid")));
			customerinfo.setJoinStaff(sysUtil.checkParam(response,"partner",request.getParameter("partner")));
			customerinfo.setAddName(sysUtil.checkParam(response,"address",request.getParameter("address")));
			customerinfo.setUrlName(sysUtil.checkParam(response,"website",request.getParameter("website")));
			customerinfo.setTelNumber(sysUtil.checkParam(response,"mobile",request.getParameter("mobile")));
			customerinfo.setFaxNumber(sysUtil.checkParam(response,"fax",request.getParameter("fax")));
			customerinfo.seteMail(sysUtil.checkParam(response,"email",request.getParameter("email")));
			customerinfo.setTradeType(sysUtil.checkParam(response,"profession",request.getParameter("profession")));
			customerinfo.setAreaName(sysUtil.checkParam(response,"area",request.getParameter("area")));
			customerinfo.setCustomerType(sysUtil.checkParam(response,"type",request.getParameter("type")));
			customerinfo.setOrgId(account.getOrgId());
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.customereditLogic(dbConn, customerinfo);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 获取所属行业
	 * Time 2015-10-19
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getProfession(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.getProfessionLogic(dbConn, account.getOrgId());
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 编辑联系人信息
	 * Time 2015-11-03
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void contactedit(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			CustomerLinkman customerLinkman=new CustomerLinkman();
			customerLinkman.setLinkmanId(sysUtil.checkParam(response,"id",request.getParameter("id")));
			customerLinkman.setLinkmanName(sysUtil.checkParam(response,"name",request.getParameter("name")));
			customerLinkman.setLinkmanJob(sysUtil.checkParam(response,"role",request.getParameter("role")));
			customerLinkman.setLinkmanCall(sysUtil.checkParam(response,"appellation",request.getParameter("appellation")));
			customerLinkman.setCellPhone(sysUtil.checkParam(response,"mobile",request.getParameter("mobile")));
			customerLinkman.setHomePhone(sysUtil.checkParam(response,"tel",request.getParameter("tel")));
			customerLinkman.setQqNumber(sysUtil.checkParam(response,"qq",request.getParameter("qq")));
			customerLinkman.seteMail(sysUtil.checkParam(response,"email",request.getParameter("email")));
			customerLinkman.setRemark(sysUtil.checkParam(response,"remark",request.getParameter("remark")));
			customerLinkman.setOrgId(account.getOrgId());
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.contacteditLogic(dbConn, customerLinkman);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
	/**
	 * 编辑聊系记录
	 * Time 2015-11-03
	 * Author Wp
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void contactrecordedit(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Connection dbConn = null;
		String returnData="";
		try {
			dbConn = DbPoolConnection.getInstance().getConnection();
			SystemUtil sysUtil = new SystemUtil();
			String phone = sysUtil.checkParam(response,"phone",request.getParameter("phone"));
			phone = SystemUtil.getAccountIdByPhone(dbConn, phone);
			Account account = new AccountLogic().getAccountByAccountId(dbConn, phone);
			String token = sysUtil.checkParam(response,"access_token",request.getParameter("access_token"));
			sysUtil.checkAccessToken(request, response, token, phone);
			String version=request.getParameter("version");
			String platform=request.getParameter("platform");
			CustomerRecord customerRecord=new CustomerRecord();
			customerRecord.setCustomerId(sysUtil.checkParam(response,"id",request.getParameter("id")));
			customerRecord.setLinkmanId(sysUtil.checkParam(response,"contactid",request.getParameter("contactid")));
			customerRecord.setRecordContent(sysUtil.checkParam(response,"content",request.getParameter("content")));
			customerRecord.setLon(sysUtil.checkParam(response,"lon",request.getParameter("lon")));
			customerRecord.setLat(sysUtil.checkParam(response,"lat",request.getParameter("lat")));
			customerRecord.setAddress(sysUtil.checkParam(response,"address",request.getParameter("address")));
			customerRecord.setDetail(sysUtil.checkParam(response,"detail",request.getParameter("detail")));
			customerRecord.setRecordlinkType(sysUtil.checkParam(response,"type",request.getParameter("type")));
			customerRecord.setRecordWarn(sysUtil.checkParam(response,"remind",request.getParameter("remind")));
			customerRecord.setOrgId(account.getOrgId());
			CustomerInfoLogic customerInfoLogic=new CustomerInfoLogic();
			returnData=customerInfoLogic.contactrecordeditLogic(dbConn, customerRecord);
			dbConn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbOp dbop = new DbOp();
			dbop.connClose(dbConn);
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(returnData);
			response.getWriter().flush();
		}
	}
}
