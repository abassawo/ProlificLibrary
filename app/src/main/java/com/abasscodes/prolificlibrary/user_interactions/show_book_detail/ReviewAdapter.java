package com.abasscodes.prolificlibrary.user_interactions.show_book_detail;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.idreambooks.pojos.CriticReview;

import java.util.ArrayList;
import java.util.List;

/***
 * Created by C4Q on 12/4/16.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {

    public List<CriticReview> reviews;

    public ReviewAdapter() {
        this.reviews = new ArrayList<>();
    }

    public void setReviews(List<CriticReview> reviews){
        this.reviews = reviews;
        notifyDataSetChanged();
    }


    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReviewHolder(parent, R.layout.review_item);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        holder.bind(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return reviews == null ? 0 : reviews.size();
    }
}
