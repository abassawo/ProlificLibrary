package com.abasscodes.prolificlibrary.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Class representing a book
 */
public class Book {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("categories")
    @Expose
    private String categories;

    @SerializedName("lastCheckedOut")
    @Expose
    private String lastCheckedOut;

    @SerializedName("lastCheckedOutBy")
    @Expose
    private String lastCheckedOutBy;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url")
    @Expose
    private String url;


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategories() {
        return categories;
    }


    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Object getLastCheckedOut() {
        return lastCheckedOut;
    }

    public void setLastCheckedOut(String lastCheckedOut) {
        this.lastCheckedOut = lastCheckedOut;
    }


    public Object getLastCheckedOutBy() {
        return lastCheckedOutBy;
    }

    public void setLastCheckedOutBy(String lastCheckedOutBy) {
        this.lastCheckedOutBy = lastCheckedOutBy;
    }


    public String getPublisher() {
        return publisher;
    }


    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return this.id;
    }


    public String display() throws ParseException {
        String pub = this.publisher == null? "" : publisher;
        String tags = this.categories == null? "": categories;

        String checkoutStatus = ""; //If book is not checked out by, print empty text.

        String dateOut = "";
        if(this.lastCheckedOut != null) {
            String restFormat = "yyyy-MM-dd h:mm:ss";
            String humanFormat = "EEE, MMM d, yyyy";
            SimpleDateFormat df = new SimpleDateFormat(restFormat);
            Date date = df.parse(lastCheckedOut);
            df.applyPattern(humanFormat);
            dateOut = " @ " + df.format(date);
        }
        checkoutStatus = this.lastCheckedOutBy == null? "": lastCheckedOutBy + dateOut;
        return "Publisher: " + pub +
                "\nTags: " + tags +
                "\nLast Checked Out: " + "\n" + checkoutStatus;

    }
}
