package com.abasscodes.prolificlibrary.ui.onboard_welcome.genre_customizer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.BaseViewHolder;
import com.abasscodes.prolificlibrary.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/21/16.
 */
public class GenreViewHolder extends BaseViewHolder<String> implements View.OnClickListener {
    @Bind(R.id.genre_name)
    TextView genreName;
    @Bind(R.id.checkbox)
    CheckBox checkbox;

    private boolean isSelected = false;

    public GenreViewHolder(ViewGroup parent) {
        super(parent, R.layout.genre_item);
        ButterKnife.bind(this, itemView);
        checkbox.setOnClickListener(this);
//        genreName = (CheckedTextView) itemView.findViewById(R.id.genre_name);
    }

    @Override
    public void bind(String item) {
        super.bind(item);
        genreName.setText(item);
    }

    @Override
    public void onClick(View v) {
        checkbox.setChecked(!isSelected);
    }


}
