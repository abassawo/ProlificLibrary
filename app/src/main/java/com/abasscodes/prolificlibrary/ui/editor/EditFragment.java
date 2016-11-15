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

import java.text.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */
public class EditFragment extends Fragment {

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
    private static EditFragment sFragment = null;
    private String TAG = "EditFragment";
    private Book book;


    public static EditFragment newInstance(Book book) { //Edit an existing book
        Bundle args = new Bundle();
        args.putParcelable(BOOK_KEY, book);
        sFragment = new EditFragment();
        sFragment.setArguments(args);
        return sFragment;
    }

    public static EditFragment newInstance() {
        return new EditFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        client = APIClient.getInstance();
        if (getArguments() != null) {
           book = getArguments().getParcelable(BOOK_KEY);

        }

    }

    @Override
    public void onResume() {
        super.onResume();
//        bindBook(book);
    }

    public void bindBook(Book book) {
        Call<Book> call = client.getBook(book.getId());
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                //fixme - check if changes between online version of book and current.
//                book = response.body();

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d(TAG, "error retrieving record " + t);
            }
        });
    }


    public void bindEditFields(Bundle args) { //For existing books
        book = (Book) args.get(BOOK_KEY);
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryField.setText(book.getCategories());
        publisherField.setText(book.getPublisher());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_fragment, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            bindEditFields(getArguments());
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_edit, menu);
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