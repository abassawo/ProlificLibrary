package com.abasscodes.prolificlibrary.model.api;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by C4Q on 11/11/16.
 */

public interface BookAPI {

    @GET("books")
    Call<ArrayList<Book>> listBooks();


    @GET("books/{id}/")
    Call<Book> getBook(@Path("id") int bookId);

    @DELETE("books/{id}/")
    Call<Book> deleteBook(@Path("id") int bookId);

    @PUT("books/{id}/")
    Call<Book> updateBook(@Path("id") int bookId, @Body Book book);

    @DELETE("clean/")
    Call<Void> deleteAll();


    @POST("books")
    Call<Book> addBook(@Body Book book);

    @FormUrlEncoded
    @POST("books")
    public Call<Book> addBook(@Field("title") String title,
                              @Field("author") String author,
                              @Field("publisher") String publisher,
                              @Field("categories") String categories);


    @GET("books")
    Call<List<Book>> getCheckedOutBooks(@Query("lastCheckedOutBy") String lastBy);
}
