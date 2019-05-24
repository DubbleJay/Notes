package com.dubblej.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.dubblej.notes.NoteDbSchema.NoteTable;

public class Model {

    private static Model model;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static Model get(Context context) {
        if (model == null) {
            model = new Model(context);
        }
        return model;
    }

    private Model(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();
    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();
        NoteCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                notes.add(cursor.getNote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return notes;
    }

    public void addNote(Note note) {
        ContentValues values = getContentValues(note);
        mDatabase.insert(NoteTable.NAME, null, values);
    }

    public void deleteNote(UUID noteId) {
        String uuidString = noteId.toString();
        mDatabase.delete(NoteTable.NAME, NoteTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    public Note getNote(UUID id) {
        NoteCursorWrapper cursor = queryCrimes(
                NoteTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getNote();
        } finally {
            cursor.close();
        }
    }

    public void updateNote(Note note) {
        String uuidString = note.getUuid().toString();
        ContentValues values = getContentValues(note);
        mDatabase.update(NoteTable.NAME, values, NoteTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    private static ContentValues getContentValues(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteTable.Cols.UUID, note.getUuid().toString());
        values.put(NoteTable.Cols.TITLE, note.getTitle());
        values.put(NoteTable.Cols.DATE, note.getCreationDate().getTime());
        values.put(NoteTable.Cols.CONTENTS, note.getContents());
        values.put(NoteTable.Cols.TEXT_SIZE, note.getTextSize());
        values.put(NoteTable.Cols.TEXT_COLOR, note.getTextColor());
        values.put(NoteTable.Cols.BACKGROUND_COLOR, note.getBackgroundColor());
        values.put(NoteTable.Cols.TYPEFACENUM, note.getTypeFaceNum());
        return values;
    }

    private NoteCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                NoteTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new NoteCursorWrapper(cursor);
    }
}
