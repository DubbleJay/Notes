package com.dubblej.notes;

import android.graphics.Color;
import android.graphics.Typeface;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Note implements Serializable {

    private String title;
    private String contents;
    private Typeface typeface;
    private int textSize;
    private int textColor;
    private int backgroundColor;
    private Date creationDate;
    private UUID uuid;
    private int typeFaceNum;

    public Note(UUID uuid) {

        this.title = "Untitled Note ";
        creationDate = new Date();
        this.uuid = uuid;
        this.textSize = 20;
        this.textColor = Color.WHITE;
        this.backgroundColor = Color.BLACK;
        setTypefaceNum(2);
    }

    public Note() {
        this(UUID.randomUUID());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypefaceNum(int num) {

        this.typeFaceNum = num;

        switch (num) {
            case 0:
                this.typeface = Typeface.DEFAULT;
                break;

            case 1:
                this.typeface = Typeface.DEFAULT_BOLD;
                break;

            case 2:
                this.typeface = Typeface.MONOSPACE;
                break;

            case 3:
                this.typeface = Typeface.SANS_SERIF;
                break;

            case 4:
                this.typeface = Typeface.SERIF;
                break;
        }
    }

    public int getTypeFaceNum() {
        return typeFaceNum;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }


    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
