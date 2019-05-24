package com.dubblej.notes;

public class NoteDbSchema {

    public static final class NoteTable {
        public static final String NAME = "notes";

        public static final class Cols {

            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String CONTENTS = "contents";
            public static final String TEXT_SIZE = "textSize";
            public static final String TEXT_COLOR = "textColor";
            public static final String BACKGROUND_COLOR = "backgroundColor";
            public static final String TYPEFACENUM = "typeface";


        }
    }
}
