package com.abasscodes.prolificlibrary.presenter;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.data.BookRepository;
import com.abasscodes.prolificlibrary.helpers.ConnectionUtil;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.ui.detail.DetailActivity;
import com.abasscodes.prolificlibrary.ui.detail.DetailFragment;
import com.abasscodes.prolificlibrary.ui.editor.EditActivity;
import com.abasscodes.prolificlibrary.ui.tabbed_ui.TabAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */
public abstract class AbstractPresenterActivity extends AppCompatActivity implements Presenter{

    public static final String BOOK_ID = "Book_id";
    public static final String BOOK_KEY = "Book_TItle";
    private static final String TAG = AbstractPresenterActivity.class.getSimpleName() ;
    public static final int DELETED_ITEM_CODE = 999;
    public Fragment currentFragment;
    private BookRepository bookRepo;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        bookRepo = new BookRepository();
    }

    @Override
    public void showBookDetail(Book book) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(BOOK_ID, book.getId());
        intent.putExtra(BOOK_KEY, book.getTitle());
        startActivity(intent);
    }

    @Override
    public void updateUI(){
        if(ConnectionUtil.isConnected()) {
                new BookRepository().fetchBooks();
        } else{
            onConnectionFailure();
        }
    }

    @Override
    public void editBook(int id, Book book) {
        Intent intent = EditActivity.createEditIntent(this, id, book);
        startActivity(intent);
    }

    @Override
    public void fillOutNewBookForm() {
        Intent intent = EditActivity.fillOutNewBook(this);
        startActivity(intent);
    }

    @Override
    public void showNetworkSettings(){
        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
    }

    public void deleteBook(Book book) {
        final String title = book.getTitle();
        Call<Book> call = APIClient.getInstance().deleteBook(book.getId());
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Toast.makeText(AbstractPresenterActivity.this, "1 item deleted : " + title, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d(TAG, "failure " + t);
            }
        });
        Intent homeIntent = new Intent(this, MainActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(homeIntent, DELETED_ITEM_CODE);
    }
}

