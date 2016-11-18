package com.abasscodes.prolificlibrary.model.nytimes.pojos;

/**
 * Created by C4Q on 11/18/16.
 */

import java.util.ArrayList;
import java.util.List;

public class NYTResponse {

    private String status;
    private String copyright;
    private Integer numResults;
    private List<Result> results = new ArrayList<Result>();


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getCopyright() {
        return copyright;
    }


    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }


    public Integer getNumResults() {
        return numResults;
    }


    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }


    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
