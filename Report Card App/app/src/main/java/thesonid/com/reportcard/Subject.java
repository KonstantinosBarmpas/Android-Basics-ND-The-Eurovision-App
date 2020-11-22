package thesonid.com.reportcard;

/**
 * Created by user on 27/5/17.
 */

public class Subject {

    private String mName;
    private int mGrade;

    public Subject (String Name, int Grade){
        mName=Name;
        mGrade=Grade;
    }

    public String getSubjectName (){
        return mName;
    }

    public int getSubjectGrade (){
        return mGrade;
    }

}
