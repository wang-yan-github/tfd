package sysmanage.product.act;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import sysmanage.product.data.Product;
import sysmanage.product.logic.ProductLogic;

import com.system.db.DbPoolConnection;
import com.system.tool.SysTool;

public class ProductAct {
	ProductLogic logic=new ProductLogic();
	public void productMap(HttpServletRequest request,HttpServletResponse response)throws Exception{
		PrintWriter writer=null;
		Connection conn=null;
		try {
			conn=DbPoolConnection.getInstance().getConnection();
			Map<String,List<Product>> productMap=logic.productListToMap(logic.productList(conn));
			writer=response.getWriter();
			writer.print(JSONObject.fromObject(productMap).toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			SysTool.closePrintWriter(writer);
			logic.dao.close(null, null, conn);
		}
	}
}
