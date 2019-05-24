package com.dubblej.notes;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;

public class TextColorPickerFragment extends DialogFragment {

    private static final String ARG_COLOR= "color";
    public static final String EXTRA_COLOR = "com.dubblej.notes.color";

    public static TextColorPickerFragment newInstance(int color) {
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, color);
        TextColorPickerFragment fragment = new TextColorPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int currentColor = getArguments().getInt(ARG_COLOR);

        String[] colors = {"Black", "Blue", "Cyan", "Light Gray", "Gray", "Dark Gray", "Green", "Magenta", "Red", "Yellow", "White"};

        return new AlertDialog.Builder(getActivity())
                .setTitle("Choose a Color:")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int color = Color.BLACK;
                        ListView lw = ((AlertDialog)dialog).getListView();
                        switch (lw.getCheckedItemPosition()) {
                            case 0:
                                color = Color.BLACK;
                                break;
                            case 1:
                                color = Color.BLUE;
                                break;
                            case 2:
                                color = Color.CYAN;
                                break;
                            case 3:
                                color = Color.LTGRAY;
                                break;
                            case 4:
                                color = Color.GRAY;
                                break;
                            case 5:
                                color = Color.DKGRAY;
                                break;
                            case 6:
                                color = Color.GREEN;
                                break;
                            case 7:
                                color = Color.MAGENTA;
                                break;
                            case 8:
                                color = Color.RED;
                                break;
                            case 9:
                                color = Color.YELLOW;
                                break;
                            case 10:
                                color = Color.WHITE;
                                break;

                        }
                        sendResult(Activity.RESULT_OK, color);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setSingleChoiceItems(colors, currentColor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }

    private void sendResult(int resultCode, int color) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_COLOR, color);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


}
