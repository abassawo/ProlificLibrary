package com.abasscodes.prolificlibrary.model.idreambooks;

import com.abasscodes.prolificlibrary.model.idreambooks.pojos.ReviewResponse;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.NYTResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by C4Q on 12/4/16.
 */

public interface IDreamBooksApi {

    @GET("books/reviews.json")
    Call<ReviewResponse> getBookReview(@Query("key") String apiKey, @Query("q") String bookTitle);

}
