package com.abasscodes.prolificlibrary.model;

import android.content.ContentValues;

import com.abasscodes.prolificlibrary.helpers.PreferenceHelper;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.PageNote;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by C4Q on 11/30/16.
 */
public class NotesRepository {
    private Map<Book, List<PageNote>> map;

    public static List<PageNote> getnotes(Book book) {
        return new ArrayList<>();
    }



}
