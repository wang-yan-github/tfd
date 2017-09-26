package infoservice.classfication.data;

public class InfoserviceClassfication {
	public static final String DEFAULT_ID="0";
	public static final String DEFAULT_NAME="根目录";
	
	private String id;
	private String name;
	private String parent;
	private String navigation;
	private Integer sort=0;
	private String remark;
	
	public InfoserviceClassfication(){
		
	}
	public InfoserviceClassfication(String id,String name,String parent,String navigation,Integer sort,String remark){
		this.id=id;
		this.name=name;
		this.parent=parent;
		this.navigation=navigation;
		this.sort=sort;
		this.remark=remark;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getNavigation() {
		return navigation;
	}
	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
