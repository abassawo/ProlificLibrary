package com.abasscodes.prolificlibrary.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/30/16.
 */
public class PageNote {

    private int pageNumber;
    private List<String> comments;

    public PageNote(int pageNumber){
        this.pageNumber = pageNumber;
        comments = new ArrayList<>();
    }

    public static PageNote testBookNote(){
        PageNote pageNote = new PageNote(11);
        pageNote.addComment("test");
        return pageNote;
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
