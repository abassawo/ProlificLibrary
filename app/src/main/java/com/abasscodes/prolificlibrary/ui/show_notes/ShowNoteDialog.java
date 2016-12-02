package com.abasscodes.prolificlibrary.ui.show_notes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;

import com.abasscodes.prolificlibrary.model.PageNote;

/**
 * Created by C4Q on 12/2/16.
 */

public class ShowNoteDialog extends DialogFragment {

    private static ShowNoteDialog instance;
    private PageNote note;
    private String title;

    public static ShowNoteDialog newInstance(PageNote note){
        if(instance == null){
            instance = new ShowNoteDialog();
        }
        Bundle args = new Bundle();
        args.putParcelable("NOTE", note);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        note = (PageNote) getArguments().get("NOTE");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(note.getComment());
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        builder.setView(ll);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Store the submitted name and check out book

            }
        });

//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                // No action - Dismiss.
//            }
//        });

        Dialog dialog = builder.create();
        dialog.show();
        return super.onCreateDialog(savedInstanceState);
    }
}
