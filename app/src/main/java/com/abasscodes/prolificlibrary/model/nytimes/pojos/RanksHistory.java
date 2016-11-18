package com.abasscodes.prolificlibrary.model.nytimes.pojos;

/**
 * Created by C4Q on 11/18/16.
 */
public class RanksHistory {

    private String primaryIsbn10;
    private String primaryIsbn13;
    private Integer rank;
    private String listName;
    private String displayName;
    private String publishedDate;
    private String bestsellersDate;
    private Integer weeksOnList;
    private Object ranksLastWeek;
    private Integer asterisk;
    private Integer dagger;


    public String getPrimaryIsbn10() {
        return primaryIsbn10;
    }


    public void setPrimaryIsbn10(String primaryIsbn10) {
        this.primaryIsbn10 = primaryIsbn10;
    }


    public String getPrimaryIsbn13() {
        return primaryIsbn13;
    }


    public void setPrimaryIsbn13(String primaryIsbn13) {
        this.primaryIsbn13 = primaryIsbn13;
    }


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

    public String getListName() {
        return listName;
    }


    public void setListName(String listName) {
        this.listName = listName;
    }


    public String getDisplayName() {
        return displayName;
    }


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public String getPublishedDate() {
        return publishedDate;
    }


    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }


    public String getBestsellersDate() {
        return bestsellersDate;
    }


    public void setBestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }


    public Integer getWeeksOnList() {
        return weeksOnList;
    }


    public void setWeeksOnList(Integer weeksOnList) {
        this.weeksOnList = weeksOnList;
    }


    public Object getRanksLastWeek() {
        return ranksLastWeek;
    }


    public void setRanksLastWeek(Object ranksLastWeek) {
        this.ranksLastWeek = ranksLastWeek;
    }


    public Integer getAsterisk() {
        return asterisk;
    }


    public void setAsterisk(Integer asterisk) {
        this.asterisk = asterisk;
    }


    public Integer getDagger() {
        return dagger;
    }


    public void setDagger(Integer dagger) {
        this.dagger = dagger;
    }

}