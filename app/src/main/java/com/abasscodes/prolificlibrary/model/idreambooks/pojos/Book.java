package com.abasscodes.prolificlibrary.model.idreambooks.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by C4Q on 12/4/16.
 */

public class Book {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sub_title")
    @Expose
    private String subTitle;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("review_count")
    @Expose
    private Integer reviewCount;
    @SerializedName("detail_link")
    @Expose
    private String detailLink;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("critic_reviews")
    @Expose
    private List<CriticReview> criticReviews = null;

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The subTitle
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * @param subTitle The sub_title
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * @return The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return The reviewCount
     */
    public Integer getReviewCount() {
        return reviewCount;
    }

    /**
     * @param reviewCount The review_count
     */
    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    /**
     * @return The detailLink
     */
    public String getDetailLink() {
        return detailLink;
    }

    /**
     * @param detailLink The detail_link
     */
    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    /**
     * @return The genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre The genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return The pages
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * @param pages The pages
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * @return The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return The criticReviews
     */
    public List<CriticReview> getCriticReviews() {
        return criticReviews;
    }

    /**
     * @param criticReviews The critic_reviews
     */
    public void setCriticReviews(List<CriticReview> criticReviews) {
        this.criticReviews = criticReviews;
    }

}



