package com.abasscodes.prolificlibrary.model.idreambooks.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by C4Q on 12/4/16.
 */

public class ReviewResponse {

    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("book")
    @Expose
    private Book book;


    public Integer getTotalResults() {
        return totalResults;
    }


    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }


    public Book getBook() {
        return book;
    }

   
    public void setBook(Book book) {
        this.book = book;
    }

}
