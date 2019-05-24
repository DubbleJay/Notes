package com.dubblej.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dubblej.notes.NoteDbSchema.NoteTable;

public class NoteBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteBase.db";

    public NoteBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NoteTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                NoteTable.Cols.UUID + ", " +
                NoteTable.Cols.TITLE + ", " +
                NoteTable.Cols.DATE + ", " +
                NoteTable.Cols.CONTENTS + ", " +
                NoteTable.Cols.TEXT_SIZE + ", " +
                NoteTable.Cols.TEXT_COLOR+ ", " +
                NoteTable.Cols.BACKGROUND_COLOR  + ", " +
                NoteTable.Cols.TYPEFACENUM +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}