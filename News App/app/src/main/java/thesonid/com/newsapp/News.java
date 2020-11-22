package thesonid.com.newsapp;

/**
 * Created by user on 5/7/17.
 */

public class News {

    private String mTitle,mSection,mDate,mURL;


    public News (String title, String section,String date,String url){
        mTitle=title;
        mSection=section;
        mDate=date;
        mURL=url;
    }

    public String getTitle (){
        return mTitle;
    }

    public String getSection (){
        return mSection;
    }

    public String getDate (){
        return mDate;
    }

    public String getURL(){
        return mURL;
    }

}
