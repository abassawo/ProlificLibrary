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
import java.util.List;

import static com.abasscodes.prolificlibrary.model.database.DBSchema.NotesTable.Cols;
import static com.abasscodes.prolificlibrary.model.database.DBSchema.NotesTable.NAME;

/**
 * Created by C4Q on 12/1/16.
 */

public class BookContentProvider {

    private static final String TAG = BookContentProvider.class.getSimpleName();
    private static BookContentProvider instance;
    private SQLiteDatabase database;
    private List<PageNote> allNotes;


    public static BookContentProvider getInstance() {
        if (instance == null) {
            instance = new BookContentProvider();
        }
        return instance;
    }


    public BookContentProvider() {
        Context ctx = RegisterActivity.basePresenterActivity;
        this.database = new DatabaseHelper(ctx).getReadableDatabase();
        allNotes = getAllNotes();
    }

    private static ContentValues getContentValues(PageNote note) {
        ContentValues cv = new ContentValues();
        cv.put(Cols._ID, note.getId());
        cv.put(Cols.PAGE, note.getPageNumber());
        cv.put(Cols.BOOK_ID, note.getBookId());
        cv.put(Cols.NOTE, note.getComment());
        return cv;
    }


    public void savePageNote(PageNote pageNote) {
        ContentValues cv = getContentValues(pageNote);
        database.insert(NAME, null, cv);
    }

    public void updatePageNote(PageNote pageNote) {
        ContentValues cv = getContentValues(pageNote);
        int id = pageNote.getId();
        String selection = Cols.BOOK_ID + "=" + pageNote.getBookId() + " AND "
                + Cols.PAGE + "=" + pageNote.getPageNumber();
        database.update(NAME, cv, selection, new String[]{});
    }


    public List<PageNote> getNotes(int bookId) {
        List<PageNote> notes = new ArrayList<>();
        for (PageNote note : getAllNotes()) {
            if (note.getBookId() == bookId) notes.add(note);
        }
        return notes;
    }


    public List<PageNote> getAllNotes() {
        List<PageNote> notes = new ArrayList<>();
        NoteCursorWrapper cursor = queryNotes(NAME, null, null);
        if (cursor.getCount() <= 0) return notes;
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                notes.add(cursor.getNextNote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(notes);
        return notes;
    }

    public List<PageNote> getNotesForBook(Book book, int page) {
        List<PageNote> notes = new ArrayList<>();
        NoteCursorWrapper cursor = queryNotes(NAME, null, null);
        if (cursor.getCount() <= 0) return notes;
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                PageNote note = cursor.getNextNote();
                if (note.getBookId() == book.getId() &&
                        note.getPageNumber() == page) {
                    notes.add(cursor.getNextNote());
                }

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(notes);
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

    public PageNote getNoteForBook(Book book, int pageNum) {
        List<PageNote> notes = getNotesForBook(book, pageNum);
        if (notes == null) return null;
        return notes.isEmpty() ? null : notes.get(0);

    }


    public class NoteCursorWrapper extends CursorWrapper {
        public NoteCursorWrapper(Cursor cursor) {
            super(cursor);
        }

        public PageNote getNextNote() {
            int id = getInt(getColumnIndex(Cols._ID));
            int pageNum = getInt(getColumnIndex(Cols.PAGE));
            int bookId = getInt(getColumnIndex(Cols.BOOK_ID));
            String note = getString(getColumnIndex(Cols.NOTE));
            PageNote pageNote = new PageNote(pageNum, note, bookId);
            pageNote.setId(id);
            return pageNote;
        }
    }

}
