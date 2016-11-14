package com.abasscodes.prolificlibrary.ui.editor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/12/16.
 */

public class AddBookFragment extends Fragment{

    @Bind(R.id.edittext_book_title)
    EditText titleField;
    @Bind(R.id.edittext_book_author)
    EditText authorField;
    @Bind(R.id.edittext_categories)
    EditText categoryField;
    @Bind(R.id.edittext_publisher)
    EditText publisherField;

    APIClient client;
    //Extras for editing saved Book
    private String title;
    private String author;
    private String pubs;
    private String tags;

    //Arguments for fragment
    public static final String BOOK_ID = "id";
    public static final String BOOK_KEY = "book_key";
    private static int bookId;

    private String TAG = EditFragment.class.getSimpleName();
    public Book book;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        client = APIClient.getInstance();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (book != null) {
            bindEditFields(book);
        }
    }

    public void bindEditFields(Book book) {
        String disp = book.display();
        Log.d(TAG, disp);
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryField.setText(book.getCategories());
        publisherField.setText(book.getPublisher());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_book:
                if (fieldsValid()) {
                    if (bookId < 0) {
                        addBook(title, author, pubs, tags);
                    }
                } else {
                    Toast.makeText(getActivity(), "Please fill out the forms above", Toast.LENGTH_SHORT).show();
                }
                break;
            case android.R.id.home:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    private boolean fieldsValid() {
        return hasText(titleField) && hasText(authorField) && hasText(publisherField) && hasText(categoryField);
    }

    private boolean hasText(EditText editText) {
        return editText.getText().length() > 0;
    }


    public void addBook(String title, String author, String publisher, String categories) {
        Call<Book> call = client.addBook(title, author, publisher, categories);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Toast.makeText(getActivity(), "New Book added : " + book.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d(TAG, "error " + t);
            }
        });
    }
}