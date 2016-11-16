package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.abasscodes.prolificlibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/11/16.
 */

public class FragmentFactory {


    private static final String TAG = FragmentFactory.class.getSimpleName();

    public static List<Fragment> makeFragments(ArrayList<Book> allBooks) {
        List<Fragment> tabbedFragments = new ArrayList<>();
        tabbedFragments.add(AllBooksFragment.newInstance(allBooks));
        tabbedFragments.add( getCheckedOutTab(allBooks));
        tabbedFragments.add( getCompletedTab(allBooks));
        tabbedFragments.add(getArchivedTab(allBooks));
        return tabbedFragments;
    }

    private static ReadLaterFragment getArchivedTab(ArrayList<Book> allBooks) {
        return new ReadLaterFragment();
    }


    private static CheckedOutBooksFragment getCheckedOutTab(List<Book> allBooks) {
        ArrayList<Book> checkedOut = new ArrayList<>();
        for (Book book : allBooks) {
            Log.d(TAG, book.getTitle() + " last checked out " + book.getLastCheckedOut());
            if (book.getLastCheckedOut().length() > 0)
                checkedOut.add(book);
        }
        return (CheckedOutBooksFragment) CheckedOutBooksFragment.newInstance(checkedOut);
    }

    private static CompletedBooksFragment getCompletedTab(ArrayList<Book> allBooks) {
        ArrayList<Book> completed = new ArrayList<>();
        for(Book book : allBooks){
            if(book.isComplete()){
                completed.add(book);
            }
        }
        return (CompletedBooksFragment) CompletedBooksFragment.newInstance(completed);
    }


}
