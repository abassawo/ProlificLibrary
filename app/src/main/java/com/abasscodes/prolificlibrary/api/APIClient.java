package com.abasscodes.prolificlibrary.api;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by C4Q on 11/11/16.
 */

public class APIClient {

    public static final String API_URL = "http://prolific-interview.herokuapp.com/5697d53d18f8ff000917b40b/";
    private static APIClient instance;
    private final BookAPI api;

    private APIClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(BookAPI.class);

    }

    public static APIClient getInstance(){
        if(instance == null){
            instance = new APIClient();
        }
        return  instance;
    }

    public Call<List<Book>> getBooks(){
        return api.listBooks();
    }

    public Call<List<Book>> listCheckedOuttBooks(){
        return api.listCurrentlyReadingBooks();
    }


    public void listCompletedBooks() {
//        return api.listBooks();
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
}
