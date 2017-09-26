package sysmanage.product.data;

public class Product {
	private String sn;
	private String productName;
	private String version;
	private String productTeam;
	private String productSite;
	public Product(String sn,String productName,String version,
			String productTeam,String productSite){
		this.setSn(sn);
		this.setProductName(productName);
		this.setVersion(version);
		this.setProductTeam(productTeam);
		this.setProductSite(productSite);
	}
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getProductTeam() {
		return productTeam;
	}
	public void setProductTeam(String productTeam) {
		this.productTeam = productTeam;
	}
	public String getProductSite() {
		return productSite;
	}
	public void setProductSite(String productSite) {
		this.productSite = productSite;
	}
	
}
