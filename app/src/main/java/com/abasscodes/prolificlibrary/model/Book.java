package com.abasscodes.prolificlibrary.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Class representing a book
 */
/**
 "author": "Jason Morris",
 "categories": "interface, ui, android",
 "lastCheckedOut": null,
 "lastCheckedOutBy": null,
 "publisher": "Dummy Publishing",
 "title": "Android User Interface Development: Beginner's Guide",
 "url": "/books/1"
 */

public class Book implements Parcelable, Comparable<Book> {

    private List<PageNote> pageNotes = new ArrayList<>();

    public static final Comparator<Book> COMPARATOR = new Comparator<Book>() {
        @Override
        public int compare(Book book, Book book2) {
            return book.compareTo(book2);
        }
    };


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
    private boolean isComplete = false;
    transient boolean archived;

    public boolean notesVisible = false;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
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
        if(title == null) {
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
        return TextUtils.join(" ", new String[] {
                title, author, publisher, categories
        });
    }

    public String display() {
        String pub = this.publisher == null? "" : publisher;
        String tags = this.categories == null? "": categories;

        String checkoutStatus = "";
        //If book is not checked out by, print empty text.

        String dateOut = "";
        if(this.lastCheckedOut != null) {
            String restFormat = "yyyy-MM-dd h:mm:ss";
            String humanFormat = "EEE, MMM d, yyyy";
            SimpleDateFormat df = new SimpleDateFormat(restFormat);
            Date date = null;
            try {
                date = df.parse(lastCheckedOut);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            df.applyPattern(humanFormat);
            dateOut = " @ " + df.format(date);
        }
        checkoutStatus = this.lastCheckedOutBy == null? "": lastCheckedOutBy + dateOut;
        return "Publisher: " + pub +
                "\nTags: " + tags +
                "\nLast Checked Out: " + "\n" + checkoutStatus;

    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (categories != null ? !categories.equals(book.categories) : book.categories != null)
            return false;
        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (lastCheckedOut != null ? !lastCheckedOut.equals(book.lastCheckedOut) : book.lastCheckedOut != null)
            return false;
        if (lastCheckedOutBy != null ? !lastCheckedOutBy.equals(book.lastCheckedOutBy) : book.lastCheckedOutBy != null)
            return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null)
            return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (url != null ? !url.equals(book.url) : book.url != null) return false;

        return true;
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

    public boolean isComplete() {
        return isComplete;
    }

    public boolean isCheckedOut() {
        return lastCheckedOut != null && !lastCheckedOut.equals("null");
    }

    public boolean isArchived() {
        return archived;
    }

    public List<PageNote> getPageNotes() {
        return pageNotes;
    }

    public void addPageNote(PageNote pageNote) {
        pageNote.setBookId(this.id);
        this.pageNotes.add(pageNote);
    }
}