package com.abasscodes.prolificlibrary.api;

import com.abasscodes.prolificlibrary.model.Book;

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

/**
 * Created by C4Q on 11/11/16.
 */

public interface BookAPI {

    @GET("/books/")
    public Call<List<Book>> listBooks();

    @GET("/books/")
    public Call<List<Book>> listCurrentlyReadingBooks();


    @GET("/books/{id}")
    public Call<Book> getBook(@Path("id") Integer id);

    @DELETE("/books/{id}/")
    public Call<Book> deleteBook(@Path("id") Integer id);

    @DELETE("/books/{id}/")
    public Call<List<Book>> deleteAllBooks(@Path("id") Integer id);


    @PUT("/books/{id}/")
    public Call<Book>  updateBook(@Path("id") Integer id, @Body Book book);


    @FormUrlEncoded
    @POST("/books/")
    public Call<Book> addBook(@Field("title") String title,
                              @Field("author") String author,
                              @Field("publisher") String publisher,
                              @Field("categories") String categories);



}
