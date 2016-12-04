package com.abasscodes.prolificlibrary.model.nytimes.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by C4Q on 11/18/16.
 */
public class RanksHistory {

    @SerializedName("primary_isbn10")
    @Expose
    private String primaryIsbn10;
    @SerializedName("primary_isbn13")
    @Expose
    private String primaryIsbn13;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("list_name")
    @Expose
    private String listName;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("bestsellers_date")
    @Expose
    private String bestsellersDate;
    @SerializedName("weeks_on_list")
    @Expose
    private Integer weeksOnList;
    @SerializedName("ranks_last_week")
    @Expose
    private Object ranksLastWeek;
    @SerializedName("asterisk")
    @Expose
    private Integer asterisk;
    @SerializedName("dagger")
    @Expose
    private Integer dagger;

    /**
     *
     * @return
     * The primaryIsbn10
     */
    public String getPrimaryIsbn10() {
        return primaryIsbn10;
    }

    /**
     *
     * @param primaryIsbn10
     * The primary_isbn10
     */
    public void setPrimaryIsbn10(String primaryIsbn10) {
        this.primaryIsbn10 = primaryIsbn10;
    }

    /**
     *
     * @return
     * The primaryIsbn13
     */
    public String getPrimaryIsbn13() {
        return primaryIsbn13;
    }

    /**
     *
     * @param primaryIsbn13
     * The primary_isbn13
     */
    public void setPrimaryIsbn13(String primaryIsbn13) {
        this.primaryIsbn13 = primaryIsbn13;
    }

    /**
     *
     * @return
     * The rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     *
     * @param rank
     * The rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     *
     * @return
     * The listName
     */
    public String getListName() {
        return listName;
    }

    /**
     *
     * @param listName
     * The list_name
     */
    public void setListName(String listName) {
        this.listName = listName;
    }

    /**
     *
     * @return
     * The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The publishedDate
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     *
     * @param publishedDate
     * The published_date
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     *
     * @return
     * The bestsellersDate
     */
    public String getBestsellersDate() {
        return bestsellersDate;
    }

    /**
     *
     * @param bestsellersDate
     * The bestsellers_date
     */
    public void setBestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }

    /**
     *
     * @return
     * The weeksOnList
     */
    public Integer getWeeksOnList() {
        return weeksOnList;
    }

    /**
     *
     * @param weeksOnList
     * The weeks_on_list
     */
    public void setWeeksOnList(Integer weeksOnList) {
        this.weeksOnList = weeksOnList;
    }

    /**
     *
     * @return
     * The ranksLastWeek
     */
    public Object getRanksLastWeek() {
        return ranksLastWeek;
    }

    /**
     *
     * @param ranksLastWeek
     * The ranks_last_week
     */
    public void setRanksLastWeek(Object ranksLastWeek) {
        this.ranksLastWeek = ranksLastWeek;
    }

    /**
     *
     * @return
     * The asterisk
     */
    public Integer getAsterisk() {
        return asterisk;
    }

    /**
     *
     * @param asterisk
     * The asterisk
     */
    public void setAsterisk(Integer asterisk) {
        this.asterisk = asterisk;
    }

    /**
     *
     * @return
     * The dagger
     */
    public Integer getDagger() {
        return dagger;
    }

    /**
     *
     * @param dagger
     * The dagger
     */
    public void setDagger(Integer dagger) {
        this.dagger = dagger;
    }

}
