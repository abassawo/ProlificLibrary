package com.abasscodes.prolificlibrary.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.BookFilterer;
import com.abasscodes.prolificlibrary.user_interactions.show_notes.BookShelfFragment;
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
    public int currentIndex;


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
       this.currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
        if(getCount() < 3)
        addFragment(BookShelfFragment.newInstance(checkedOutBooks), "Notes");
    }


}
