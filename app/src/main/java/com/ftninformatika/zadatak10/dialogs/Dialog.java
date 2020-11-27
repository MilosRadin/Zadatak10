package com.ftninformatika.zadatak10.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Dialog extends AlertDialog.Builder {
    public Dialog(Context context) {
        super(context);

        setTitle("Brisanje gledanih filmova");

        setMessage("Da li stvarno zelite da obrisete sve filmove");
        setPositiveButton("Da", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //DELETE ALL
                dialog.dismiss();
            }
        });

    }

    public AlertDialog prepareDialog() {
        AlertDialog dialog = create();
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }
}
