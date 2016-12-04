package com.abasscodes.prolificlibrary.ui.edit_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.MainTabsActivity;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;
import com.abasscodes.prolificlibrary.model.Book;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */
public class EditFragment extends AddBookFragment {

    APIClient client;
    public static final String BOOK_ID = "id";
    public static final String BOOK_KEY = "book_key";
    private static EditFragment sFragment = null;
    private String TAG = EditFragment.class.getSimpleName();
    private Book book;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = APIClient.getInstance();
        if (getArguments() != null) {
            book = getArguments().getParcelable(BOOK_KEY);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            bindEditFields(getArguments());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void bindEditFields(Bundle args) { //For existing books
        book = (Book) args.get(BOOK_KEY);
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryField.setText(book.getCategories());
        publisherField.setText(book.getPublisher());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_book:
                if (fieldsValid()) {
                    updateBook();
                } else {
                    Toast.makeText(getActivity(), "Please fill out the forms above", Toast.LENGTH_SHORT).show();
                }
                return true;
            case android.R.id.home:
                startActivity(new Intent(getActivity(), MainTabsActivity.class));
        }
        return true;

    }

    public void updateBook() {
        int id = book.getId();
        Log.d(TAG, "Book edit is " + book.getTitle() + " " + id);
        book.setAuthor(authorField.getText().toString());
        book.setTitle(titleField.getText().toString());
        book.setCategories(categoryField.getText().toString());
        book.setPublisher(publisherField.getText().toString());

        Call<Book> call = client.updateBook(id, book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Log.d(TAG, response.message());
                startActivity(new Intent(getActivity(), MainTabsActivity.class));
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getActivity(), "There was an error connecting to the network", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "updating retrofit failed " + t);
            }
        });

    }


}