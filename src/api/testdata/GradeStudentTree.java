package api.testdata;

import com.component.datamodel.ATree;

/**
 * Created by Administrator on 2015/9/16.
 */
public class GradeStudentTree extends ATree {
    public GradeStudentTree(){}
    public GradeStudentTree(String id, String pid, String text, boolean isParent){
        this.setId(id);
        this.setPid(pid);
        this.setText(text);
        this.setIsParent(isParent);
    }
}
