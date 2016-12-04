package com.abasscodes.prolificlibrary.ui.show_notes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abasscodes.prolificlibrary.R;
import com.abasscodes.prolificlibrary.model.PageNote;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by C4Q on 12/2/16.
 */

public class ShowNoteDialog extends SupportBlurDialogFragment {

    private static ShowNoteDialog instance;
    private String note;


    public static ShowNoteDialog newInstance(String note){
        if(instance == null){
            instance = new ShowNoteDialog();
        }
        Bundle args = new Bundle();
        args.putString("NOTE", note);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                dismiss();
            }
        };
        handler.postDelayed(r, 2000);
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

    @Override
    protected float getDownScaleFactor() {
        // Allow to customize the down scale factor.
        return 2;
    }


    @Override
    protected int getBlurRadius() {
        // Allow to customize the blur radius factor.
        return 2;
    }

    @Override
    protected boolean isActionBarBlurred() {
        // Enable or disable the blur effect on the action bar.
        // Disabled by default.
        return true;
    }

    @Override
    protected boolean isDimmingEnable() {
        // Enable or disable the dimming effect.
        // Disabled by default.
        return true;
    }

    @Override
    protected boolean isRenderScriptEnable() {
        // Enable or disable the use of RenderScript for blurring effect
        // Disabled by default.
        return true;
    }

    @Override
    protected boolean isDebugEnable() {
        // Enable or disable debug mode.
        // False by default.
        return true;
    }
}
