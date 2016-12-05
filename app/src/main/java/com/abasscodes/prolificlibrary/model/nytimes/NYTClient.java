package com.abasscodes.prolificlibrary.model.nytimes;

import com.abasscodes.prolificlibrary.model.nytimes.pojos.NYTResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by C4Q on 11/18/16.
 */

public class NYTClient {
    private static NYTClient instance;
    public static final String API_URL = "https://api.nytimes.com/svc/books/v3/";
    private static final String API_KEY = "3cfd4c24b2734248b53aa4488568815d";
    private static NYTimesAPI api;

    private NYTClient(){
        if(api == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            api = retrofit.create(NYTimesAPI.class);
        }
    }

    public static NYTClient getInstance() {
        if(instance == null){
            instance = new NYTClient();
        }
        return instance;
    }



    public Call<NYTResponse> listBestSellers() {
        return api.getBestSellersList(API_KEY);
    }

    public Call<NYTResponse> getCategoriesList(String category) {
        return api.getCategoriesList(API_KEY, category);
    }
}
