package com.abasscodes.prolificlibrary.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.abasscodes.prolificlibrary.model.nytimes.pojos.RanksHistory;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a book
 */

/**
 * "author": "Jason Morris",
 * "categories": "interface, ui, android",
 * "lastCheckedOut": null,
 * "lastCheckedOutBy": null,
 * "publisher": "Dummy Publishing",
 * "title": "Android User Interface Development: Beginner's Guide",
 * "url": "/books/1"
 */

public class Book implements Parcelable, Comparable<Book> {

    private static final String TAG = Book.class.getSimpleName();


    @SerializedName("id")
    private Integer id;

    @SerializedName("author")
    private String author;

    @SerializedName("categories")
    private String categories;

    @SerializedName("lastCheckedOut")
    private String lastCheckedOut;

    @SerializedName("lastCheckedOutBy")
    private String lastCheckedOutBy;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(Result result) {
        Log.d(TAG, "Result specs were " + result);
        this.title = result.getTitle();
        this.author = result.getAuthor();
        this.publisher = result.getPublisher();
        List<RanksHistory> ranks = result.getRanksHistory();
        if (ranks.size() > 0) {
            this.categories = ranks.get(0).getDisplayName();
        } else {
            this.categories = "New York Times Best Seller";
        }

    }

    public String getAuthor() {
        return author;
    }

    public String getCategories() {
        return categories;
    }

    public String getLastCheckedOut() {
        return lastCheckedOut;
    }

    public String getLastCheckedOutBy() {
        return lastCheckedOutBy;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        if (title == null) {
            return "";
        }
        return String.valueOf(title);
    }

    public String getUrl() {
        return url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setLastCheckedOut(String lastCheckedOut) {
        this.lastCheckedOut = lastCheckedOut;
    }

    public void setLastCheckedOutBy(String lastCheckedOutBy) {
        this.lastCheckedOutBy = lastCheckedOutBy;
    }

    public void returnCheckOut() {
        this.lastCheckedOutBy = "";
        this.lastCheckedOut = null;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return TextUtils.join(" ", new String[]{
                title, author, publisher, categories
        });
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return id == book.getId() &&
                author.equals(book.author) &&
                categories.equals(book.categories) &&
                publisher.equals(book.publisher) &&
                lastCheckedOut.equals(book.lastCheckedOut) &&
                lastCheckedOutBy.equals(book.lastCheckedOutBy) &&
                url.equals(book.url);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (lastCheckedOut != null ? lastCheckedOut.hashCode() : 0);
        result = 31 * result + (lastCheckedOutBy != null ? lastCheckedOutBy.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public Book(Parcel in) {
        id = (Integer) in.readValue(Integer.class.getClassLoader());
        author = in.readString();
        categories = in.readString();
        lastCheckedOut = String.valueOf(in.readValue(Date.class.getClassLoader()));
        lastCheckedOutBy = in.readString();
        publisher = in.readString();
        title = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(id);
        parcel.writeString(author);
        parcel.writeString(categories);
        parcel.writeValue(lastCheckedOut);
        parcel.writeString(lastCheckedOutBy);
        parcel.writeString(publisher);
        parcel.writeString(title);
        parcel.writeString(url);
    }

    @Override
    public int compareTo(Book book) {
        return title.compareTo(book.getTitle());
    }


    public boolean isCheckedOut() {
        if (lastCheckedOutBy == null) return false;
        return !lastCheckedOutBy.isEmpty();
    }


}