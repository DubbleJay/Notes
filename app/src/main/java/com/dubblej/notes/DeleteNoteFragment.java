package com.dubblej.notes;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class DeleteNoteFragment extends DialogFragment {

    private static final String ARG_TITLE = "title";
    public static final String EXTRA_RESULT = "result";



    public static DeleteNoteFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        DeleteNoteFragment fragment = new DeleteNoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String title = getArguments().getString(ARG_TITLE);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Delete " + title + "?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULT, resultCode);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
