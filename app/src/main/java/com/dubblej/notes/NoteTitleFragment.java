package com.dubblej.notes;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;

public class NoteTitleFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";
    public static final String EXTRA_TITLE = "com.dubblej.notes.title";


    public static NoteTitleFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        NoteTitleFragment fragment = new NoteTitleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String title = getArguments().getString(ARG_TITLE);

        final EditText editText = new EditText(getActivity());
        editText.setText(title);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Name Your Note:")
                .setView(editText)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(editText.getText().toString().trim().length() > 0) {
                            sendResult(Activity.RESULT_OK, editText.getText().toString());
                            Toast.makeText(getContext(), "Title changed to " + editText.getText(), Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    private void sendResult(int resultCode, String title) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
