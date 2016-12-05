package com.abasscodes.prolificlibrary.model.idreambooks;

import com.abasscodes.prolificlibrary.model.idreambooks.pojos.ReviewResponse;
import com.abasscodes.prolificlibrary.model.nytimes.NYTClient;
import com.abasscodes.prolificlibrary.model.nytimes.NYTimesAPI;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.NYTResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by C4Q on 12/4/16.
 */

public class DreamApiClient {

    public static final String API_URL = "https://idreambooks.com/api/";
    private static final String API_KEY = "9a725e0de0577ba7a7a78a9f0df286cb52a9736e";
    private static DreamApiClient instance;
    private IDreamBooksApi api;

    private DreamApiClient(){
        if(api == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            api = retrofit.create(IDreamBooksApi.class);
        }

    }

    public static DreamApiClient getInstance() {
        if(instance == null){
            instance = new DreamApiClient();
        }
        return instance;
    }


    public Call<ReviewResponse> getBookReview(String name) {
        return api.getBookReview(API_KEY, name);
    }

}
