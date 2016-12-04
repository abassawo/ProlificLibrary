package com.abasscodes.prolificlibrary.model.database;

/**
 * Created by C4Q on 12/1/16.
 */

public class DBSchema {
    public static final class NotesTable{

        public static final String NAME = "Notes_Table";

        public static final class Cols{
            public static final String _ID = "id";
            public static final String BOOK_ID = "book_id";
            public static final String PAGE = "page";
            public static final String NOTE = "note";
        }
    }


}
