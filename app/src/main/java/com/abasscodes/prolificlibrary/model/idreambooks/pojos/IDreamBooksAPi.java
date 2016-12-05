package com.abasscodes.prolificlibrary.model.idreambooks.pojos;

import com.abasscodes.prolificlibrary.model.nytimes.pojos.NYTResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by C4Q on 12/4/16.
 */

public class IDreamBooksAPi {

    public interface NYTimesAPI {

        @GET("lists/best-sellers/history.json")
        Call<NYTResponse> getBookReviews(@Query("api-key") String apiKey);

    }
}
