package api.index.data;

import com.component.datamodel.ATree;

/**
 * Created by Administrator on 2015/9/16.
 */
public class IndexTree extends ATree {
    private String path;
    private String html;
	public IndexTree(){}
    public IndexTree(String id,String pid,String text,String html,boolean isParent,String path){
        this.setId(id);
        this.setPid(pid);
        this.setText(text);
        this.setHtml(html);
        this.setIsParent(isParent);
        this.setPath(path);
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
}
