package sysmanage.product.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.db.BaseDao;
import com.system.db.impl.BaseDaoImpl;

import sysmanage.product.data.Product;

public class ProductLogic {
	public BaseDao dao=new BaseDaoImpl();
	public List<Product> productList(Connection conn) throws Exception{
		List<Product> productList=new ArrayList<Product>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			
			String sql="select * from sysm_product";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()){
				productList.add(
					new Product(
						rs.getString("sn"), 
						rs.getString("product_name"), 
						rs.getString("version"), 
						rs.getString("product_team"),
						rs.getString("product_site")
					)
				);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			dao.close(rs, stmt, null);
		}
		return productList;
	}
	public Map<String,List<Product>> productListToMap(List<Product> productList){
		Map<String,List<Product>> productMap=new HashMap<String, List<Product>>();
		for (Product product : productList) {
			if (!productMap.containsKey(product.getProductName())) {
				productMap.put(product.getProductName(), new ArrayList<Product>());
			}
			productMap.get(product.getProductName()).add(product);
		}
		return productMap;
	}
}
