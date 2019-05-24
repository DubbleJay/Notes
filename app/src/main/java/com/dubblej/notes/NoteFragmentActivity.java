package com.dubblej.notes;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.Menu;


import java.util.UUID;

public class NoteFragmentActivity extends FragmentActivityTemplate {

    public static final String EXTRA_NOTE_ID = "com.bignerdranch.android.criminalintent.crime_id";

    @Override
    protected Fragment createFragment() {
        return new NoteFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return false;
    }

    public static Intent newIntent(Context packageContext, UUID noteId) {
        Intent intent = new Intent(packageContext, NoteFragmentActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        return intent;
    }
}
