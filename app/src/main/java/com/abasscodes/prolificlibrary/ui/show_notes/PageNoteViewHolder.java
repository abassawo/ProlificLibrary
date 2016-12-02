package com.abasscodes.prolificlibrary.ui.show_notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.PageNote;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/30/16.
 */
public class PageNoteViewHolder extends RecyclerView.ViewHolder {
    //Circular preview of the page
    @Bind(R.id.page_number_fab)
    TextView pageTV;

    public PageNoteViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        ButterKnife.bind(this, itemView);
    }

    public static View inflateView(ViewGroup parent){
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.page_note_item, parent, false);
    }

    public void bindBookNote(PageNote note) {
        String pageNum = String.valueOf(note.getPageNumber());
//        Bitmap background = TextToBitmap.render(pageNum, 22, R.color.black);
        pageTV.setText(pageNum);
    }
}
