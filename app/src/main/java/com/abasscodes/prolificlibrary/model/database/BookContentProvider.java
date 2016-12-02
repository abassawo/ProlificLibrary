package com.abasscodes.prolificlibrary.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.PageNote;
import com.abasscodes.prolificlibrary.presenter.BasePresenterActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by C4Q on 12/1/16.
 */

public class BookContentProvider {

    private static final String TAG = BookContentProvider.class.getSimpleName();
    private static BookContentProvider instance;
    private final Context context;
    private Map<Integer, List<PageNote>> map = new HashMap<>();
    private SQLiteDatabase database;
    private List<PageNote> allNotes;


    public static BookContentProvider getInstance() {
        Context ctx = RegisterActivity.basePresenterActivity;
        return getInstance(ctx, new DatabaseHelper(ctx).getReadableDatabase());
    }

    private static BookContentProvider getInstance(Context context, SQLiteDatabase db) {
        if (instance == null) {
            instance = new BookContentProvider(context, db);
        }
        return instance;
    }

    public BookContentProvider(Context context, SQLiteDatabase db) {
        this.context = context;
        this.database = db;
        allNotes = getAllNotes();
    }

    private static ContentValues getContentValues(PageNote note) {
        ContentValues cv = new ContentValues();
            cv.put(DBSchema.NotesTable.Cols._ID, note.getId());
            cv.put(DBSchema.NotesTable.Cols.PAGE, note.getPageNumber());
            cv.put(DBSchema.NotesTable.Cols.BOOK_ID, note.getBookId());
            cv.put(DBSchema.NotesTable.Cols.NOTE, note.getComment());
        return cv;
    }


    public void saveBookContent(Book book) {
        for (PageNote note : book.pageNoteMap.values()) {
            ContentValues cv = getContentValues(note);
            database.insert(DBSchema.NotesTable.NAME, null, cv);
        }

    }


    public List<PageNote> getNotes(int id) {
        List<PageNote> notes = new ArrayList<>();
        for(PageNote note : getAllNotes()){
            if(note.getBookId() == id) notes.add(note);
        }
        return notes;
    }


    public List<PageNote> getAllNotes() {
        List<PageNote> notes = new ArrayList<>();
        NoteCursorWrapper cursor = queryNotes(DBSchema.NotesTable.NAME, null, null);
        if (cursor.getCount() <= 0) return notes;
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                notes.add(cursor.getNote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(notes);
//        for(PageNote note : notes){
//
//        }
        return notes;
    }


    public NoteCursorWrapper queryNotes(String tableName, String whereClause, String[] whereArgs) {

        Cursor cursor = database.query(
                tableName,
                null,   //select all columns
                whereClause,
                whereArgs,
                null, //group by
                null,
                null);
        return new NoteCursorWrapper(cursor);


    }


    public class NoteCursorWrapper extends CursorWrapper {
        public NoteCursorWrapper(Cursor cursor) {
            super(cursor);
        }

        public PageNote getNote() {
            int id = getInt(getColumnIndex(DBSchema.NotesTable.Cols._ID));
            int pageNum = getInt(getColumnIndex(DBSchema.NotesTable.Cols.PAGE));
            int bookId = getInt(getColumnIndex(DBSchema.NotesTable.Cols.BOOK_ID));
            String note = getString(getColumnIndex(DBSchema.NotesTable.Cols.NOTE));
            PageNote pageNote;
            pageNote = new PageNote(pageNum, note, bookId);
            pageNote.setId(id);
            return pageNote;
        }
    }

}
