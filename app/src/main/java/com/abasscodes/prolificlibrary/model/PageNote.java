package com.abasscodes.prolificlibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by C4Q on 11/30/16.
 */
public class PageNote implements Parcelable, Comparable {

    public List<String> comments;
    private int bookId;
    private int pageNumber;
    private int id;

    protected PageNote(Parcel in) {
        comments = in.readArrayList(String.class.getClassLoader());
        bookId = in.readInt();
        pageNumber = in.readInt();
        id = in.readInt();
    }

    public static final Creator<PageNote> CREATOR = new Creator<PageNote>() {
        @Override
        public PageNote createFromParcel(Parcel in) {
            return new PageNote(in);
        }

        @Override
        public PageNote[] newArray(int size) {
            return new PageNote[size];
        }
    };

    public List<String> getComments() {
        return comments;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }


    public PageNote(int pageNumber, List<String> comments) {
        this.pageNumber = pageNumber;
        this.comments = comments;
    }

    public void setComments(List<String> comments) {
       this.comments = comments;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int compareTo(Object o) {
        PageNote pageNote = (PageNote) o;
        int otherPage = pageNote.pageNumber;
        if (bookId == pageNote.bookId && pageNumber == otherPage)
            return 0;
        return pageNumber > otherPage ? 1 : -1; //fixme
    }



    public int getBookId() {
        return bookId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(id);
        parcel.writeValue(bookId);
        parcel.writeValue(id);
        parcel.writeValue(pageNumber);
        parcel.writeStringList(comments);
    }
}
