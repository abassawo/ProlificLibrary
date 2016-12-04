package com.abasscodes.prolificlibrary.model.prolific;

import android.content.Context;
import android.util.Log;

import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import com.abasscodes.prolificlibrary.model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by C4Q on 11/11/16.
 */

public class APIClient {

    public static final String API_URL = "http://prolific-interview.herokuapp.com/5697d53d18f8ff000917b40b/";
    private static APIClient instance;
    private static BookAPI api;



    private APIClient(){
        if(api == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            api = retrofit.create(BookAPI.class);
        }

    }



    public static APIClient getInstance(){
        if(instance == null){
            instance = new APIClient();
        }
        return  instance;
    }

    public Call<ArrayList<Book>> getBooks(){
        return api.listBooks();
    }

    public Call<ArrayList<Book>> listCheckedOutBooks(){
        return api.listBooks();
    }


    public Call<Book> getBook(int bookId) {
        return api.getBook(bookId);
    }

    public Call<Book> updateBook(int id, Book book) {
        return api.updateBook(id, book);
    }

    public Call<Book> addBook(String title, String author, String publisher, String categories) {
        return api.addBook(title, author, publisher, categories);
    }

    public Call<Book> deleteBook(int id) {
        return api.deleteBook(id);
    }

    public void deleteBook(Book book){
        final String title = book.getTitle();
        Call<Book> call = deleteBook(book.getId());
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d("Deleting Book", "failure " + t);
            }
        });
    }

    public Call<List<Book>> getCheckedOutBooks(Context context) {
        return api.getCheckedOutBooks(PreferenceHelper.getUserName(context));
    }

    public Call<Void> deleteAll() {
         return api.deleteAll();
    }

}
