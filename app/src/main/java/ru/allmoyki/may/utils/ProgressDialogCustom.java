package ru.allmoyki.may.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import ru.allmoyki.may.R;

/**
 * Created by Boichuk Dmitriy on 15.09.2015.
 */
public class ProgressDialogCustom {
    public static Dialog getProgressDialog(Context context) {
        Dialog progressDialog = new Dialog(context);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.dialog_progress);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}
