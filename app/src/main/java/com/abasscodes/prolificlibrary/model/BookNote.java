package com.abasscodes.prolificlibrary.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/30/16.
 */
public class BookNote {

    private int pageNumber;
    private List<String> comments;

    public BookNote(int pageNumber){
        this.pageNumber = pageNumber;
    }

    public void addComment(String comment){
        if(comments == null){
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
