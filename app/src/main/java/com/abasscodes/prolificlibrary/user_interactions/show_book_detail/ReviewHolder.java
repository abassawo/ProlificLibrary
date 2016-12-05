package com.abasscodes.prolificlibrary.user_interactions.show_book_detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.idreambooks.pojos.CriticReview;
import com.abasscodes.prolificlibrary.model.idreambooks.pojos.ReviewResponse;
import com.abasscodes.prolificlibrary.view.BaseViewHolder;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 12/4/16.
 */
public class ReviewHolder extends BaseViewHolder<CriticReview>{

    @Bind(R.id.book_review_text)
    TextView reviewTV;

    public ReviewHolder(ViewGroup parent, Integer rowLayout) {
        super(parent, rowLayout);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(CriticReview item) {
        reviewTV.setText(item.getSnippet());
    }
}
