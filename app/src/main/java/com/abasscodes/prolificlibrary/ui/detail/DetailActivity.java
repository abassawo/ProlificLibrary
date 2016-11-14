package com.abasscodes.prolificlibrary.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.api.APIClient;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.presenter.AbstractPresenterActivity;
import com.abasscodes.prolificlibrary.presenter.Presenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by C4Q on 11/11/16.
 */
public class DetailActivity extends AppCompatActivity{
    private String TAG = "DetailActivity";
    private ActionBar mActionBar;
    private Fragment fragment = null;
    private String bookTitle;
    private Integer bookId;
    private ShareActionProvider actionProvider;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mActionBar =  getSupportActionBar();
        book = getIntent().getParcelableExtra(AbstractPresenterActivity.BOOK_KEY);
        if(book == null) {
            bookId = getIntent().getIntExtra(AbstractPresenterActivity.BOOK_ID, 0);
            initRetrofit(bookId);
        }
        setupActionBar(mActionBar, getResources().getString(R.string.app_name));
        if(savedInstanceState == null) {
            //fixme
        }
        if(book != null){
            fragment = DetailFragment.newInstance(book);
            hostFragment(fragment);

        }
    }

    public void hostFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();
    }

    public void setupActionBar(ActionBar ab, String title) {
        ab.setTitle(title);
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE |
                ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite_this_book:
                if (item.getIcon() == getResources().getDrawable(R.drawable.ic_action_favorite)) {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_action_favorite_selected));
                } else {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_action_favorite));
                }
                break;
        }


        return super.onOptionsItemSelected(item);
    }

}
