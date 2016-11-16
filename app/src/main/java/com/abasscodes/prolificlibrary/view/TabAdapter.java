package com.abasscodes.prolificlibrary.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.abasscodes.prolificlibrary.model.BookFilterer;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.view.tab_fragments.AbstractTabRVFragment;
import com.abasscodes.prolificlibrary.view.tab_fragments.AllBooksFragment;
import com.abasscodes.prolificlibrary.view.tab_fragments.CheckedOutBooksFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 *
 * TabAdapter Class - Designed to Adapt a collection of books into 4 tabs
 * representing different filters of the original data.
 */
public class TabAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, BookFilterer.FiltersReadyListener {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();

    public TabAdapter(ArrayList<Book> allBooks) {
        this(allBooks, RegisterActivity.basePresenterActivity);
    }


    private TabAdapter(ArrayList<Book> allBooks, AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
        addFragment(AllBooksFragment.newInstance(allBooks), "Library");
        new BookFilterer(this).filter(allBooks);
    }


    public void addFragment(Fragment fragment, String title){
        fragments.add(fragment);
        fragmentTitles.add(title);
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
    public void setCompletedBooks(ArrayList<Book> completedBooks) {
//        addFragment(CheckedOutBooksFragment.newInstance(completedBooks), "\u2713");
    }

    @Override
    public void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
        addFragment(CheckedOutBooksFragment.newInstance(checkedOutBooks), "Checked out");
    }

    @Override
    public void setArchivedBooks(ArrayList<Book> archivedBooks) {
//        addFragment(ReadLaterFragment.newInstance(archivedBooks), "Read Later");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        AbstractTabRVFragment fragment = (AbstractTabRVFragment) fragments.get(position);
//        fragment.refresh();
    }

    @Override
    public void onPageSelected(int position) {
//        AbstractTabRVFragment fragment = (AbstractTabRVFragment) fragments.get(position);
//        fragment.refresh();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
