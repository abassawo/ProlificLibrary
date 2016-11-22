package com.abasscodes.prolificlibrary.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookFilterer;
import com.abasscodes.prolificlibrary.view.tab_fragments.NotesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 * <p>
 * TabAdapter Class - Designed to Adapt a collection of books into 4 tabs
 * representing different filters of the original data.
 */
public class TabAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, BookFilterer.FiltersReadyListener {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();
    private ArrayList<Book> books;

    private String currentFragmentTitle;



    public TabAdapter(AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        new BookFilterer(this).filter(books);
    }


    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }

    public void addFragment(int index, Fragment fragment, String title) {
        fragments.add(index, fragment);
        fragmentTitles.add(index, title);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (books != null) {
                    return NotesFragment.getInstance(books);
                }
                RegisterActivity.basePresenterActivity.findViewById(R.id.fab).setVisibility(View.VISIBLE);
                break;
            case 1:
            case 2: RegisterActivity.basePresenterActivity.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String out = fragmentTitles.get(position).toLowerCase();
        return out;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentFragmentTitle = fragmentTitles.get(position);
        switch (position) {
            case 0:
                RegisterActivity.basePresenterActivity.findViewById(R.id.fab).setVisibility(View.VISIBLE);
                break;
            case 1:
            case 2: RegisterActivity.basePresenterActivity.findViewById(R.id.fab).setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void setCompletedBooks(ArrayList<Book> completedBooks) {
//        this.completedBooks = completedBooks;
    }

    @Override
    public void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
//        this.checkedOutBooks = checkedOutBooks;
    }

    @Override
    public void setArchivedBooks(ArrayList<Book> archivedBooks) {
//        this.archivedBooks = archivedBooks;
    }
}
