package com.abasscodes.prolificlibrary.model.nytimes;

import com.abasscodes.prolificlibrary.model.nytimes.pojos.NYTResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by C4Q on 11/18/16.
 */

public interface NYTimesAPI {

    @GET("lists/best-sellers/history.json")
    Call<NYTResponse> getBestSellersList(@Query("api-key") String apiKey);
}
