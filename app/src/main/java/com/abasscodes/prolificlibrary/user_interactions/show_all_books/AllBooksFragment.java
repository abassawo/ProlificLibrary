package com.abasscodes.prolificlibrary.user_interactions.show_all_books;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;
import com.abasscodes.prolificlibrary.helpers.ConnectionUtil;
import com.abasscodes.prolificlibrary.helpers.TextUtilHelper;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookRepository;
import com.abasscodes.prolificlibrary.view.RecyclerViewFragment;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */

public class AllBooksFragment extends RecyclerViewFragment implements BookRepository.BookCallback {

    public final static String TAG = "AllBooks";
    private static final String BOOKS_KEY = "Books_Key";
    private FragmentCommunication listener;
    private AllBooksAdapter adapter;
    private boolean recentlyDeleted = false;
    private ArrayList<Book> books;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add:
                RegisterActivity.basePresenterActivity.fillOutNewBookForm();
                break;
            case R.id.menu_item_deleteAll:
                prepareToDeleteAll();
                break;
        }
        return true;
    }

    public void showDeleteAllDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.delete_all_warning)
                .setMessage(getResources().getString(R.string.delete_all_prompt));

        builder.setPositiveButton("Delete Everything", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                final ArrayList<Book> deletedBooks = (ArrayList<Book>) adapter.getBooks();
                Call<Void> call = APIClient.getInstance().deleteAll();
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        recentlyDeleted = true;
                        adapter.clear();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Snackbar.make(getView(), "Error deleting ", Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // No action - Dismiss.
            }
        });
        Dialog dialog = builder.create();
        dialog.show();

    }

    public void prepareToDeleteAll() {
        if (books.size() == 0) {
            Toast.makeText(getActivity(), "Already Empty", Toast.LENGTH_SHORT).show();
        } else {
            showDeleteAllDialog();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(BOOKS_KEY, books);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                String temp = "";
                if (query.length() > temp.length()) {
                    final List<Book> filteredBooks = TextUtilHelper.filter(books, query);
                    adapter.setBooks(filteredBooks);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(0);
                } else if (query.length() <= 0) {
                    adapter.setBooks(books);
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(0);
                }
                return false;
            }
        });
    }


    @Override
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (FragmentCommunication) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString()
                    + " must implement Fragment Communication");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshContent();
    }


    public void showTipSnackBar() {
        Snackbar.make(getView(), "The library is empty. Add a new book or use the Explore tab", Snackbar.LENGTH_LONG)
                .setAction("New Book", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RegisterActivity.basePresenterActivity.fillOutNewBookForm();
                    }
                }).show();
    }


    @Override
    public void refreshContent() {
        super.refreshContent();
        if (ConnectionUtil.isConnected()) {
            new BookRepository(this).fetchBooks();
        }
    }


    @Override
    public void onBooksReady(ArrayList<Book> books) {
        if (books == null || books.isEmpty()) {
            if(!recentlyDeleted){
                showTipSnackBar();
            }
        }
        this.books = books;
        if (adapter == null) {
            adapter = new AllBooksAdapter(getActivity(), books);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setBooks(books);
        }
        if (adapter.getItemCount() == 0) showEmptyView();
        ArrayList<Book> myBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isCheckedOut()) myBooks.add(book);
        }
        listener.setCheckedOutBooks(myBooks);
    }

    @Override
    public void onDownloadFail() {
        if (isAdded()) {
            ConnectionUtil.showConnecitonError(getView());
        }
    }

    //Used to transfer list of books that are currently checked out to host activity and fragment B
    public interface FragmentCommunication {
        void setCheckedOutBooks(ArrayList<Book> books);
    }
}