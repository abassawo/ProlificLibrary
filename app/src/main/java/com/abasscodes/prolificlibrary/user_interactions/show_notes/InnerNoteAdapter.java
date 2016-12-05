package com.abasscodes.prolificlibrary.user_interactions.show_notes;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.prolificlibrary.model.PageNote;

import java.util.List;

/**
 * Created by C4Q on 11/30/16.
 */
public class InnerNoteAdapter extends RecyclerView.Adapter<PageNoteViewHolder> {
    public List<PageNote> pageNotes;


    public InnerNoteAdapter(List<PageNote> pageNotes) {
        this.pageNotes = pageNotes;
    }

    @Override
    public PageNoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PageNoteViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PageNoteViewHolder holder, int position) {
        PageNote note = pageNotes.get(position);
        holder.bindBookNote(note);
    }

    @Override
    public int getItemCount() {
        return pageNotes.size();
    }
}
