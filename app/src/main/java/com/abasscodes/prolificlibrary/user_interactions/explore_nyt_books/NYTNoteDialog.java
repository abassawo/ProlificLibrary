package com.abasscodes.prolificlibrary.user_interactions.explore_nyt_books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;

/**
 * Created by C4Q on 12/2/16.
 */
public class NYTNoteDialog  extends DialogFragment {

    private String note;


    public static NYTNoteDialog newInstance(String note) {
        NYTNoteDialog instance = new NYTNoteDialog();
        Bundle args = new Bundle();
        args.putString("NOTE", note);
        instance.setArguments(args);
        return instance;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        note = getArguments().getString("NOTE");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_detail, container, false);
        TextView tv = (TextView) view.findViewById(R.id.note_detail);
        tv.setText(note);
        return view;
    }
}