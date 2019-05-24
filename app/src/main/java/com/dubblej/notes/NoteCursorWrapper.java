package com.dubblej.notes;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.dubblej.notes.NoteDbSchema.NoteTable;

import java.util.Date;
import java.util.UUID;

public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote() {

        String uuidString = getString(getColumnIndex(NoteTable.Cols.UUID));
        String title = getString(getColumnIndex(NoteTable.Cols.TITLE));
        long date = getLong(getColumnIndex(NoteTable.Cols.DATE));
        String contents = getString(getColumnIndex(NoteTable.Cols.CONTENTS));
        int textSize = getInt(getColumnIndex(NoteTable.Cols.TEXT_SIZE));
        int textColor = getInt(getColumnIndex(NoteTable.Cols.TEXT_COLOR));
        int backgroundColor= getInt(getColumnIndex(NoteTable.Cols.BACKGROUND_COLOR));
        int typefaceNum = getInt(getColumnIndex(NoteTable.Cols.TYPEFACENUM));

        Note note = new Note(UUID.fromString(uuidString));
        note.setTitle(title);
        note.setCreationDate(new Date(date));
        note.setContents(contents);
        note.setTextSize(textSize);
        note.setTextColor(textColor);
        note.setBackgroundColor(backgroundColor);
        note.setTypefaceNum(typefaceNum);

        return note;

    }
}
