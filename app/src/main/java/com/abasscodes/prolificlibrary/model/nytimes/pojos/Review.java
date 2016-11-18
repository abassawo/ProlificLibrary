package com.abasscodes.prolificlibrary.model.nytimes.pojos;

/**
 * Created by C4Q on 11/18/16.
 */
public class Review {

    private String bookReviewLink;
    private String firstChapterLink;
    private String sundayReviewLink;
    private String articleChapterLink;


    public String getBookReviewLink() {
        return bookReviewLink;
    }


    public void setBookReviewLink(String bookReviewLink) {
        this.bookReviewLink = bookReviewLink;
    }


    public String getFirstChapterLink() {
        return firstChapterLink;
    }


    public void setFirstChapterLink(String firstChapterLink) {
        this.firstChapterLink = firstChapterLink;
    }


    public String getSundayReviewLink() {
        return sundayReviewLink;
    }

    public void setSundayReviewLink(String sundayReviewLink) {
        this.sundayReviewLink = sundayReviewLink;
    }


    public String getArticleChapterLink() {
        return articleChapterLink;
    }


    public void setArticleChapterLink(String articleChapterLink) {
        this.articleChapterLink = articleChapterLink;
    }

}
