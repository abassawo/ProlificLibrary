package com.abasscodes.prolificlibrary.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments.AllBooksFragment;
import com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments.CheckedOutBooksFragment;
import com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments.CompletedBooksFragment;
import com.abasscodes.prolificlibrary.ui.tabbed_ui.fragments.ReadLaterFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 */
public class TabAdapter extends FragmentPagerAdapter
        implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();


    public TabAdapter(AppCompatActivity activity, ViewPager pager) {
        super(activity.getSupportFragmentManager());
        this.viewPager = pager;
        addFragment(new AllBooksFragment(), "Library");
        addFragment(new CheckedOutBooksFragment(), "Reading");
        addFragment(new CompletedBooksFragment(), "\u2713");
        addFragment(new ReadLaterFragment(), "Read Later");
        viewPager.setAdapter(this);
        viewPager.addOnPageChangeListener(this);
    }


    public int indexOf(TabType type) {
        switch(type){
            case AllBooksTab: return 0;
            case ToReadTab: return 1;
            case ReadTab: return 2;
            case ReadLaterTab: return 3;
            default: return -1;
        }
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public enum TabType {
        AllBooksTab, ToReadTab, ReadTab, ReadLaterTab
    }
}
