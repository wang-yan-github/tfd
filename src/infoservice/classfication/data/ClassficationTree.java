package infoservice.classfication.data;

import com.component.datamodel.ATree;

public class ClassficationTree extends ATree{ 
	
	private String navigation;
	private Integer sort;
	private String remark;
	private String html;
	public ClassficationTree(){
		
	}
	public ClassficationTree(String id,String pid,String text,String html,boolean isParent,String navigation,Integer sort,String remark){
		super.setId(id);
		super.setPid(pid);
		super.setText(text);
		this.setHtml(html);
		super.setIsParent(isParent);
		this.setNavigation(navigation);
		this.setSort(sort);
		this.setRemark(remark);
	}
	
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getNavigation() {
		return navigation;
	}
	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
