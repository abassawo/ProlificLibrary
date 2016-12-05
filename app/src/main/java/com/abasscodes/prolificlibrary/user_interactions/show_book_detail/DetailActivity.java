package com.abasscodes.prolificlibrary.user_interactions.show_book_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.prolific.APIClient;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */
public class DetailActivity extends BasePresenterActivity{
    private String TAG = "DetailActivity";
    private ActionBar actionBar;
    private Fragment fragment = null;
    private Integer bookId;
    private ShareActionProvider actionProvider;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingBar;
    @Bind(R.id.detail_appbar)
    AppBarLayout appBar;
    private Book book;
    private DetailPresenter presenter;


   public static final String BOOK_ID = "Book_id";
   public static final String BOOK_KEY = "Book";
    private String bookTitle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        actionBar =  getSupportActionBar();
        minimizeToolbar();
        presenter = new DetailPresenter(this);
        if(savedInstanceState != null) {
            book = savedInstanceState.getParcelable(BOOK_KEY);
        }else{
            book = getIntent().getParcelableExtra(BOOK_KEY);
        }
        if(book != null){
             updateUI(book);
        }
    }

    public void updateUI(Book book){
            setupActionBar(actionBar);
            bookTitle = book.getTitle();
            presenter.setToolbarTitle(book);
            fragment = DetailFragment.newInstance(book);
            hostFragment(fragment);

    }

    public void minimizeToolbar(){
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                if(appBar != null)
                    appBar.setExpanded(false, true);
            }
        };
        handler.postDelayed(r, 2000);
    }

    public void hostFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();
    }


    public void setupActionBar(ActionBar ab) {
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE |
                ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP);
        ab.setTitle(book.getTitle());
    }

    public void initRetrofit(final int bookId) {
        Call<Book> call = APIClient.getInstance().getBook(bookId);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                book = response.body();
                hostFragment(DetailFragment.newInstance(book));
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.d(TAG, "Error retrofitting " + t);
            }

        });
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_detail, menu);
        MenuItem actionItem = (MenuItem) menu.findItem(R.id.share_book);
        actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(actionItem);
        if (actionProvider == null) {
            return false;
        }
        actionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Checkout this awesome book: " + bookTitle);
        actionItem.setIcon(android.R.drawable.ic_menu_share);
        actionProvider.setShareIntent(shareIntent);
        actionProvider.setOnShareTargetSelectedListener(new ShareActionProvider.OnShareTargetSelectedListener() {

            @Override
            public boolean onShareTargetSelected(ShareActionProvider source, Intent intent) {
                String packageName = intent.getComponent().getPackageName();
                intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this awesome book");
                intent.putExtra(Intent.EXTRA_TEXT, bookTitle);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }




    public static Intent makeIntent(Context context, Book book) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(BOOK_ID, book.getId());
        intent.putExtra(BOOK_KEY, book);
        return intent;
    }

}
