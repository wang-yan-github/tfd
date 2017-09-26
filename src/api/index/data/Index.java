package api.index.data;

/**
 * Created by Administrator on 2015/9/16.
 */
public class Index {
    private int id;
    private String name;
    private int parentId;
    private String path;
    private String textDiy;
    private String apiContent;
    
    

    public String getApiContent() {
		return apiContent;
	}

	public void setApiContent(String apiContent) {
		this.apiContent = apiContent;
	}

	public String getTextDiy() {
		return textDiy;
	}

	public void setTextDiy(String textDiy) {
		this.textDiy = textDiy;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
