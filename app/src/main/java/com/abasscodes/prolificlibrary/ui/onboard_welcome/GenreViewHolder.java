package com.abasscodes.prolificlibrary.ui.onboard_welcome;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.BaseViewHolder;
import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.ui.onboard_welcome.GenreAdapter;

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

    private GenreAdapter adapter;

    private boolean isSelected = false;
    private String item;

    public GenreViewHolder(ViewGroup parent, GenreAdapter adapter) {
        super(parent, R.layout.genre_item);
        this.adapter = adapter;
        ButterKnife.bind(this, itemView);
        checkbox.setOnClickListener(this);
    }


    @Override
    public void bind(String item) {
        super.bind(item);
        this.item = item;
        genreName.setText(item);
    }

    @Override
    public void onClick(View v) {
        checkbox.setChecked(!isSelected);
        if(checkbox.isChecked()) {
            adapter.getSelectedItems().add(item);
        }else adapter.getSelectedItems().remove(item);
    }


}
