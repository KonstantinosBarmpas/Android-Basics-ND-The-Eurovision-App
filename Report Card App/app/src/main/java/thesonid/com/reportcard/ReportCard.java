package thesonid.com.reportcard;

import java.util.ArrayList;

/**
 * Created by user on 27/5/17.
 */

public class ReportCard {

    private String mName;
    private String mDOB;
    private double mGradeOverall;

    public ReportCard (String Name, String DOB, ArrayList<Subject> subject){
        mName=Name;
        mDOB=DOB;
        int sum=0;
        for (int i=0; i<subject.size(); i++){
            sum=sum+subject.get(i).getSubjectGrade();
        }
        mGradeOverall=sum/subject.size();
    }

    public String getName (){
        return mName;
    }

    public String getDOB (){
        return mDOB;
    }

    public double getGradeOverall (){
        return mGradeOverall;
    }

}



