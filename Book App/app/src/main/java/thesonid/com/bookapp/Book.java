package thesonid.com.bookapp;

/**
 * Created by user on 5/7/17.
 */

public class Book {

    private String mAuthor, mTitle, mURL;

    public Book(String author, String title, String url) {

        mAuthor = author;

        mTitle = title;

        mURL = url;

    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getURL() {
        return mURL;
    }

}
