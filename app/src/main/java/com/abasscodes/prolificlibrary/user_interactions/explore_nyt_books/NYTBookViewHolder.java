package com.abasscodes.prolificlibrary.user_interactions.explore_nyt_books;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.nytimes.pojos.Result;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/18/16.
 */
public class NYTBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = NYTBookViewHolder.class.getSimpleName();
    @Bind(R.id.book_item_title)
    TextView titleTV;
    @Bind(R.id.book_item_author)
    TextView authorTV;
    @Bind(R.id.book_thumbnail)
    ImageView thumbNail;
    @Bind(R.id.add_nyt_book_btn)
    ImageView addBtn;
    private Result result;


    public NYTBookViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        titleTV.setOnClickListener(this);
        authorTV.setOnClickListener(this);
        addBtn.setOnClickListener(this);
    }

    public void showDescription(Result result){
        FragmentManager fm = RegisterActivity.basePresenterActivity.getSupportFragmentManager();
        NYTNoteDialog.newInstance(result.getDescription()).show(fm, null);
    }

    public static View inflateView(ViewGroup parent) {
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        View view = infl.inflate(R.layout.nyt_book_item, parent, false);
        return view;
    }

    public void bindBook(Result result) {
        this.result = result;
        this.titleTV.setText(result.getTitle());
        this.authorTV.setText(result.getAuthor());
    }


    @Override
    public void onClick(View v) {
        if(v == itemView) showDescription(result);
        switch (v.getId()) {
            case R.id.add_nyt_book_btn:
                Book bookFromResult = new Book(result);
                Log.d(TAG, "Book specs " + bookFromResult);
                RegisterActivity.basePresenterActivity.fillOutNewBookForm(bookFromResult);
            case R.id.book_item_author:
            case R.id.book_item_title:
            case R.id.book_thumbnail:
                showDescription(result);
                break;

        }
    }


}
