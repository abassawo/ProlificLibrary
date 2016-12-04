package com.abasscodes.prolificlibrary.model.nytimes.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by C4Q on 11/18/16.
 */
public class Review {

    @SerializedName("book_review_link")
    @Expose
    private String bookReviewLink;
    @SerializedName("first_chapter_link")
    @Expose
    private String firstChapterLink;
    @SerializedName("sunday_review_link")
    @Expose
    private String sundayReviewLink;
    @SerializedName("article_chapter_link")
    @Expose
    private String articleChapterLink;

    /**
     *
     * @return
     * The bookReviewLink
     */
    public String getBookReviewLink() {
        return bookReviewLink;
    }

    /**
     *
     * @param bookReviewLink
     * The book_review_link
     */
    public void setBookReviewLink(String bookReviewLink) {
        this.bookReviewLink = bookReviewLink;
    }

    /**
     *
     * @return
     * The firstChapterLink
     */
    public String getFirstChapterLink() {
        return firstChapterLink;
    }

    /**
     *
     * @param firstChapterLink
     * The first_chapter_link
     */
    public void setFirstChapterLink(String firstChapterLink) {
        this.firstChapterLink = firstChapterLink;
    }

    /**
     *
     * @return
     * The sundayReviewLink
     */
    public String getSundayReviewLink() {
        return sundayReviewLink;
    }

    /**
     *
     * @param sundayReviewLink
     * The sunday_review_link
     */
    public void setSundayReviewLink(String sundayReviewLink) {
        this.sundayReviewLink = sundayReviewLink;
    }

    /**
     *
     * @return
     * The articleChapterLink
     */
    public String getArticleChapterLink() {
        return articleChapterLink;
    }

    /**
     *
     * @param articleChapterLink
     * The article_chapter_link
     */
    public void setArticleChapterLink(String articleChapterLink) {
        this.articleChapterLink = articleChapterLink;
    }

}
