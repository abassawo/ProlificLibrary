package com.abasscodes.prolificlibrary.view.tab_fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.BookNote;

import butterknife.Bind;

/**
 * Created by C4Q on 11/30/16.
 */
public class BookNoteViewHolder extends RecyclerView.ViewHolder {
    //Circular preview of the page
    @Bind(R.id.page_number_tv) TextView pageNumber;

    public BookNoteViewHolder(ViewGroup parent) {
        super(inflateView(parent));
    }

    public static View inflateView(ViewGroup parent){
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.book_note_item, parent, false);
    }

    public void bindBookNote(BookNote note) {
        pageNumber.setText(note.getPageNumber());
    }
}
