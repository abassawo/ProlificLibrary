package com.abasscodes.prolificlibrary.interactions.edit_book;

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
import com.abasscodes.prolificlibrary.interactions.show_all_books.MainTabsActivity;
import com.abasscodes.prolificlibrary.model.api.APIClient;
import com.abasscodes.prolificlibrary.model.Book;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/12/16.
 */

public class AddBookFragment extends Fragment {


    private static final String TAG = AddBookFragment.class.getSimpleName();
    public static final int ADD_BOOK_CODE = 101;

    @Bind(R.id.edittext_book_title)
    EditText titleField;
    @Bind(R.id.edittext_book_author)
    EditText authorField;
    @Bind(R.id.edittext_categories)
    EditText categoryField;
    @Bind(R.id.edittext_publisher)
    EditText publisherField;

    protected String title;
    protected String author;
    protected String pubs;
    protected String tags;

    private APIClient client;
    private Book book;


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


    public void addBook(EditText... editTexts) {
        title =  editTexts[0].getText().toString();
        author = editTexts[1].getText().toString();
        pubs = editTexts[2].getText().toString();
        tags = editTexts[3].getText().toString();
        Call<Book> call = client.addBook(title, author, pubs, tags);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Log.d(TAG, response.message());
                book = response.body();
                if(book != null){
                    Toast.makeText(getActivity(), "New Book added : " + book.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainTabsActivity.class);
                    startActivityForResult(intent, ADD_BOOK_CODE);
                }else{
                    Toast.makeText(getActivity(), "Error : ", Toast.LENGTH_SHORT).show();

                }
//
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d(TAG, "error " + t);
            }
        });
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
                    addBook(titleField, authorField, publisherField, categoryField);
                } else {
                    Toast.makeText(getActivity(), "Please fill out the forms above", Toast.LENGTH_SHORT).show();
                }
                break;
            case android.R.id.home:
                startActivity(new Intent(getActivity(), MainTabsActivity.class));
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    protected boolean fieldsValid() {
        return hasText(titleField) && hasText(authorField) && hasText(publisherField) && hasText(categoryField);
    }

    protected boolean hasText(EditText editText) {
        return editText.getText().length() > 0;
    }


}
