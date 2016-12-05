package com.abasscodes.prolificlibrary.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by C4Q on 12/1/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "AppDatabase";
    private static final String NOTES_TABLE = DBSchema.NotesTable.NAME;
    private static final int VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(initTable());
    }

    private String initTable() {
        String initSQL = "create table " + NOTES_TABLE + "(" +
                "_id integer primary key autoincrement, " +
                DBSchema.NotesTable.Cols._ID + ", " +
                DBSchema.NotesTable.Cols.BOOK_ID + ", " +
                DBSchema.NotesTable.Cols.NOTE+ ", " +
                DBSchema.NotesTable.Cols.PAGE + ")";
        return initSQL;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
        db.execSQL(initTable());
    }
}
