package com.dubblej.notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainMenuFragment extends Fragment {

    private RecyclerView notesRecyclerView;
    private NoteAdapter noteAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        notesRecyclerView = view.findViewById(R.id.notesRecyclerView);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu_options, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_item_new_note:

                Note note = new Note();
                Model.get(getActivity()).addNote(note);
                Intent intent = NoteFragmentActivity.newIntent(getActivity(), note.getUuid());
                startActivity(intent);
                Toast.makeText(getContext(), "New Note Created", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {

        Model model = Model.get(getActivity());
        int noteCount = model.getNotes().size();
        String subtitle;
        if(noteCount != 1)
            subtitle = getString(R.string.multiple_subtitle_format, noteCount);
        else
            subtitle = getString(R.string.single_subtitle_format, noteCount);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    public void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Name Your New Deck:");

        final EditText input = new EditText(getActivity());
        input.setText("Untitled Note");
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Note note = new Note();

                Model.get(getActivity()).addNote(note);

                Intent intent = NoteFragmentActivity.newIntent(getActivity(), note.getUuid());
                startActivity(intent);

                updateUI();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void updateUI() {
        Model model = Model.get(getActivity());
        List<Note> notes = model.getNotes();
        if (noteAdapter == null) {
            noteAdapter = new NoteAdapter(notes);
            notesRecyclerView.setAdapter(noteAdapter);
        } else {
            noteAdapter.setNotes(notes);
            noteAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView dateTextView;
        private Note note;

        public NoteHolder(View itemView)  {
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView = itemView.findViewById(R.id.note_title_text_view);
            dateTextView = itemView.findViewById(R.id.note_date_text_view);
        }




        public void bindNote(Note note) {
            this.note = note;
            titleTextView.setText(note.getTitle());
            dateTextView.setText(note.getCreationDate().toString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = NoteFragmentActivity.newIntent(getActivity(), note.getUuid());
            startActivity(intent);
        }
    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>  {

        private List<Note> notes;
        public NoteAdapter(List<Note> notes) {
            this.notes = notes;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);
            return new NoteHolder(view);
        }
        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {
            Note note = notes.get(position);
            holder.bindNote(note);
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }

        public void setNotes(List<Note> notes) {
           this.notes = notes;
        }
    }


}
