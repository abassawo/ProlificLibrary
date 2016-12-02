package com.abasscodes.prolificlibrary.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.abasscodes.prolificlibrary.helpers.RegisterActivity;
import com.abasscodes.prolificlibrary.model.Book;
import com.abasscodes.prolificlibrary.model.PageNote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by C4Q on 12/1/16.
 */

public class BookContentProvider {

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

    private static ContentValues getContentValues(PageNote note, int bookId) {
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.NotesTable.Cols._ID, note.getId());
        cv.put(DBSchema.NotesTable.Cols.PAGE, note.getPageNumber());
        cv.put(DBSchema.NotesTable.Cols.BOOK_ID, bookId);
        cv.put(DBSchema.NotesTable.Cols.NOTE, note.getComments());
        cv.put(DBSchema.NotesTable.Cols._ID, note.getBookId());
        return cv;
    }


    public void saveBookContent(Book book) {
        for (PageNote note : book.getPageNotes()) {
            ContentValues cv = getContentValues(note, book.getId());
            database.insert(DBSchema.NotesTable.NAME, null, cv);
        }
    }

    public List<PageNote> getNotes(Book book) {
        return map.containsKey(book) ? map.get(book.getId()) : new ArrayList<PageNote>();
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
        populateMap(notes);
        return notes;
    }

    public void populateMap(List<PageNote> notes){
        for(PageNote note : notes){
            int bookid = note.getBookId();
            List<PageNote> pageNotes = map.get(bookid);
            if(pageNotes == null) pageNotes = new ArrayList<>();
            pageNotes.add(note);
            map.put(bookid, pageNotes);
        }
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
            PageNote pageNote = new PageNote(pageNum, note);
            pageNote.setId(id);
            pageNote.setBookId(bookId);
            return pageNote;
        }
    }

}
