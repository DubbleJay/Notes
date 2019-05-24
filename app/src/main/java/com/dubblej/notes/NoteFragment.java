package com.dubblej.notes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class NoteFragment extends Fragment{

    private Note note;
    private EditText editText;
    private static final int REQUEST_TEXT_COLOR = 0;
    private static final int REQUEST_BACKGROUND_COLOR = 1;
    private static final int REQUEST_TEXT_SIZE = 2;
    private static final int REQUEST_TYPEFACE = 3;
    private static final int REQUEST_EDIT_TITLE = 4;
    private static final int REQUEST_DELETE_NOTE = 5;
    private static final String DIALOG_TEXT_COLOR = "DialogTextColor";
    private static final String DIALOG_TEXT_SIZE= "DialogTextSize";
    private static final String DIALOG_TYPEFACE = "DialogTypeFace";
    private static final String DIALOG_EDIT_TITLE = "DialogEditTitle";
    private static final String DIALOG_DELETE_NOTE = "DialogDeleteNote";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID noteId = (UUID) getActivity().getIntent().getSerializableExtra(NoteFragmentActivity.EXTRA_NOTE_ID);
        note = Model.get(getActivity()).getNote(noteId);
    }

    @Override
    public void onPause() {
        super.onPause();
        Model.get(getActivity()).updateNote(note);
    }

    @Override
    public void onResume() {
        super.onResume();
        Model.get(getActivity()).updateNote(note);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note, container, false);
        editText = v.findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                note.setContents(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        editText.setText(note.getContents());
        editText.setTextColor(note.getTextColor());
        editText.setBackgroundColor(note.getBackgroundColor());
        editText.setTypeface(note.getTypeface());
        editText.setTextSize(note.getTextSize());

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {

            case REQUEST_TEXT_COLOR:
                int color = data.getIntExtra(TextColorPickerFragment.EXTRA_COLOR, 0);
                note.setTextColor(color);
                editText.setTextColor(note.getTextColor());
                break;

            case REQUEST_BACKGROUND_COLOR:
                int backgroundColor = data.getIntExtra(TextColorPickerFragment.EXTRA_COLOR, 0);
                note.setBackgroundColor(backgroundColor);
                editText.setBackgroundColor(note.getBackgroundColor());
                break;

            case REQUEST_TEXT_SIZE:
                int textSize = data.getIntExtra(TextSizePickerFragment.EXTRA_TEXT_SIZE, 0);
                note.setTextSize(textSize);
                editText.setTextSize(note.getTextSize());
                break;

            case REQUEST_TYPEFACE:
                int typeFaceSelection = data.getIntExtra(TypeFacePickerFragment.EXTRA_TYPEFACE, 0);
                switch (typeFaceSelection) {
                    case 0:
                        note.setTypefaceNum(0);
                        editText.setTypeface(note.getTypeface());
                        break;

                    case 1:
                        note.setTypefaceNum(1);
                        editText.setTypeface(note.getTypeface());
                        break;
                    case 2:
                        note.setTypefaceNum(2);
                        editText.setTypeface(note.getTypeface());
                        break;
                    case 3:
                        note.setTypefaceNum(3);
                        editText.setTypeface(note.getTypeface());
                        break;
                    case 4:
                        note.setTypefaceNum(4);
                        editText.setTypeface(note.getTypeface());
                        break;

                }

                break;

            case REQUEST_EDIT_TITLE:
                String title = data.getStringExtra(NoteTitleFragment.EXTRA_TITLE);
                note.setTitle(title);
                break;

            case REQUEST_DELETE_NOTE:
                int resultl = data.getIntExtra(DeleteNoteFragment.EXTRA_RESULT, 0);
                if(resultl == Activity.RESULT_OK) {
                    UUID noteId = note.getUuid();
                    Model.get(getContext()).deleteNote(noteId);
                    Toast.makeText(getContext(), note.getTitle() + "successfully deleted.", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentManager manager = getFragmentManager();

        switch (item.getItemId()) {


            case R.id.typeface_item:
                TypeFacePickerFragment typeFaceDialog = TypeFacePickerFragment.newInstance(note.getTypeFaceNum());
                typeFaceDialog.setTargetFragment(NoteFragment.this, REQUEST_TYPEFACE);
                typeFaceDialog.show(manager, DIALOG_TYPEFACE);
                return true;

            case R.id.text_color_item:
                TextColorPickerFragment dialog = TextColorPickerFragment.newInstance(getSelectedColorPosition(note.getTextColor()));
                dialog.setTargetFragment(NoteFragment.this, REQUEST_TEXT_COLOR);
                dialog.show(manager, DIALOG_TEXT_COLOR);
                return true;

            case R.id.text_size:
                TextSizePickerFragment textSizeDialog = TextSizePickerFragment.newInstance(note.getTextSize());
                textSizeDialog.setTargetFragment(NoteFragment.this, REQUEST_TEXT_SIZE);
                textSizeDialog.show(manager, DIALOG_TEXT_SIZE);
                return true;

            case R.id.background_color_item:
                TextColorPickerFragment dialog1 = TextColorPickerFragment.newInstance(getSelectedColorPosition(note.getBackgroundColor()));
                dialog1.setTargetFragment(NoteFragment.this, REQUEST_BACKGROUND_COLOR);
                dialog1.show(manager, DIALOG_TEXT_COLOR);
                return true;

            case R.id.edit_note_title_item:
                NoteTitleFragment noteTitleFragment = NoteTitleFragment.newInstance(note.getTitle());
                noteTitleFragment.setTargetFragment(NoteFragment.this, REQUEST_EDIT_TITLE);
                noteTitleFragment.show(manager, DIALOG_EDIT_TITLE);
                return true;

            case R.id.delete_note_item:
                DeleteNoteFragment deleteNoteFragment = DeleteNoteFragment.newInstance(note.getTitle());
                deleteNoteFragment.setTargetFragment(NoteFragment.this, REQUEST_DELETE_NOTE);
                deleteNoteFragment.show(manager, DIALOG_DELETE_NOTE);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public int getSelectedColorPosition(int color) {
        int currentColor = 0;
        switch (color) {
            case Color.BLACK:
                currentColor = 0;
                break;
            case Color.BLUE:
                currentColor = 1;
                break;
            case Color.CYAN:
                currentColor = 2;
                break;
            case Color.LTGRAY:
                currentColor = 3;
                break;
            case Color.GRAY:
                currentColor = 4;
                break;
            case Color.DKGRAY:
                currentColor = 5;
                break;
            case Color.GREEN:
                currentColor = 6;
                break;
            case Color.MAGENTA:
                currentColor = 7;
                break;
            case Color.RED:
                currentColor = 8;
                break;
            case Color.YELLOW:
                currentColor= 9;
                break;
            case Color.WHITE:
                currentColor = 10;
                break;
        }
        return currentColor;
    }

}
