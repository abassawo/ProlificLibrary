package com.abasscodes.prolificlibrary.model.nytimes;

import android.content.Context;
import android.util.Base64;

import com.abasscodes.prolificlibrary.Config;
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
    private String API_KEY;
    private static NYTimesAPI api;

    private NYTClient(Context context){
        API_KEY = Config.getNYTApiKey(context);
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

    public static NYTClient getInstance(Context context) {
        if(instance == null){
            instance = new NYTClient(context);
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
