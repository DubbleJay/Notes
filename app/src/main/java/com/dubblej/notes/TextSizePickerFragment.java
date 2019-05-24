package com.dubblej.notes;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TextSizePickerFragment extends DialogFragment {

    private static final String ARG_TEXT_SIZE = "size";
    public static final String EXTRA_TEXT_SIZE = "com.dubblej.notes.textsize";



    public static TextSizePickerFragment newInstance(int textSize) {
        Bundle args = new Bundle();
        args.putInt(ARG_TEXT_SIZE, textSize);
        TextSizePickerFragment fragment = new TextSizePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Integer[] integers = new Integer[101];
        for(int i = 0; i < integers.length; i++)
            integers[i] = i;
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_singlechoice, integers);

        int currentTextSize = getArguments().getInt(ARG_TEXT_SIZE);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Text Size:")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListView lw = ((AlertDialog)dialog).getListView();
                        sendResult(Activity.RESULT_OK, lw.getCheckedItemPosition());
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setSingleChoiceItems(adapter, currentTextSize, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }

    private void sendResult(int resultCode, int textSize) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TEXT_SIZE, textSize);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


}
