package com.dubblej.notes;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;

public class TypeFacePickerFragment extends DialogFragment {

    private static final String ARG_TYPE_FACE  = "typeface";
    public static final String EXTRA_TYPEFACE = "com.dubblej.notes.color";

    public static TypeFacePickerFragment newInstance(int typeface) {
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE_FACE, typeface);
        TypeFacePickerFragment fragment = new TypeFacePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

       int typeFaceSelection = getArguments().getInt(ARG_TYPE_FACE);

        String[] typeFaces = {"Default", "Default Bold", "Monospace", "Sans Serif", "Serif"};
        return new AlertDialog.Builder(getActivity())
                .setSingleChoiceItems(typeFaces, typeFaceSelection, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListView lw = ((AlertDialog)dialog).getListView();
                        sendResult(Activity.RESULT_OK, lw.getCheckedItemPosition());
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setTitle("Choose a Font:")
                .create();
    }

    private void sendResult(int resultCode, int typeFaceSelection) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TYPEFACE, typeFaceSelection);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
