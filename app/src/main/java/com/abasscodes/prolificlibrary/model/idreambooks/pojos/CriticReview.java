package com.abasscodes.prolificlibrary.model.idreambooks.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by C4Q on 12/4/16.
 */

public class CriticReview {

    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("review_link")
    @Expose
    private String reviewLink;
    @SerializedName("pos_or_neg")
    @Expose
    private String posOrNeg;
    @SerializedName("star_rating")
    @Expose
    private Integer starRating;
    @SerializedName("review_date")
    @Expose
    private String reviewDate;
    @SerializedName("smiley_or_sad")
    @Expose
    private String smileyOrSad;
    @SerializedName("source_logo")
    @Expose
    private String sourceLogo;

    /**
     * @return The snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     * @param snippet The snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     * @return The source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return The reviewLink
     */
    public String getReviewLink() {
        return reviewLink;
    }

    /**
     * @param reviewLink The review_link
     */
    public void setReviewLink(String reviewLink) {
        this.reviewLink = reviewLink;
    }

    /**
     * @return The posOrNeg
     */
    public String getPosOrNeg() {
        return posOrNeg;
    }

    /**
     * @param posOrNeg The pos_or_neg
     */
    public void setPosOrNeg(String posOrNeg) {
        this.posOrNeg = posOrNeg;
    }

    /**
     * @return The starRating
     */
    public Integer getStarRating() {
        return starRating;
    }

    /**
     * @param starRating The star_rating
     */
    public void setStarRating(Integer starRating) {
        this.starRating = starRating;
    }

    /**
     * @return The reviewDate
     */
    public String getReviewDate() {
        return reviewDate;
    }

    /**
     * @param reviewDate The review_date
     */
    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    /**
     * @return The smileyOrSad
     */
    public String getSmileyOrSad() {
        return smileyOrSad;
    }

    /**
     * @param smileyOrSad The smiley_or_sad
     */
    public void setSmileyOrSad(String smileyOrSad) {
        this.smileyOrSad = smileyOrSad;
    }

    /**
     * @return The sourceLogo
     */
    public String getSourceLogo() {
        return sourceLogo;
    }

    /**
     * @param sourceLogo The source_logo
     */
    public void setSourceLogo(String sourceLogo) {
        this.sourceLogo = sourceLogo;
    }

}