package com.abasscodes.prolificlibrary.ui.editor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */
public class EditFragment extends AddBookFragment {
    private static final String TAG = AddBookFragment.class.getSimpleName();
    private static EditFragment sFragment = null;
    private static Book book;



    public static EditFragment newInstance(Book book) {
        Bundle args = new Bundle();
        args.putParcelable(BOOK_ID, book);
        sFragment = new EditFragment();
        sFragment.setArguments(args);
        return sFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle args = getArguments();
        if(args != null)
        this.book = args.getParcelable(BOOK_ID);
    }

    public void getBookInfo(Book book) {
//        Call<Book> call = client.getBook(book.getId());
//        call.enqueue(new Callback<Book>() {
//            @Override
//            public void onResponse(Call<Book> call, Response<Book> response) {
//                //fixme - check if changes between online version of book and current.
//                EditFragment.book = response.body();
//
//            }
//
//            @Override
//            public void onFailure(Call<Book> call, Throwable t) {
//                Log.d(TAG, "error retrieving record " + t);
//            }
//        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getBookInfo(book);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (book != null) {
            bindEditFields(book);
        }
    }

    public void updateBook() {
        int id = book.getId();
        book.setAuthor(authorField.getText().toString());
        book.setTitle(titleField.getText().toString());
        book.setCategories(categoryField.getText().toString());
        book.setPublisher(publisherField.getText().toString());

        Call<Book> call = client.updateBook(id, book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getActivity(), "There was an error connecting to the network", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "updating retrofit failed " + t);
            }
        });

    }


}