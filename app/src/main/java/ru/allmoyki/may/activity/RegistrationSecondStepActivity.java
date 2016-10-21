package ru.allmoyki.may.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.CheckSMSPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;
import ru.allmoyki.may.widget.masked_edit_text.MaskedEditText;

/**
 * Created by Boichuk Dmitriy on 28.08.2015.
 */
public class RegistrationSecondStepActivity extends Activity {


    @InjectView(R.id.eSmsCode)
    MaskedEditText eSmsCode;
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            progressDialog.dismiss();
            Toast.makeText(RegistrationSecondStepActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
        }
    };
    private Response.Listener<CheckSMSPojo> checkSmsListener = new Response.Listener<CheckSMSPojo>() {
        @Override
        public void onResponse(CheckSMSPojo smsPojo) {
            progressDialog.dismiss();
            Log.d("Log", "sms " + smsPojo.getData());
            MyPreferences.seUserPushToken(RegistrationSecondStepActivity.this, smsPojo.getData());
            startActivity(new Intent(RegistrationSecondStepActivity.this, MainActivity.class));
            finish();

        }
    };

    @OnClick(R.id.btn_confirm_sms_code)
    public void confirmCode() {
        if (eSmsCode.getText().toString().contains(" ")) {
            Toast.makeText(RegistrationSecondStepActivity.this, "Введите корректный пароль", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();
            HashMap<String, String> hashData = new HashMap<>();
            hashData.put("sms_code", eSmsCode.getText().toString());
            GsonRequest<CheckSMSPojo> checkSms = Api.checkSmsCode(checkSmsListener, errorListener, hashData);
            AppController.getInstance().addToRequestQueue(checkSms);

        }
    }

    private Dialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_second_step);
        ButterKnife.inject(this);
        progressDialog = ProgressDialogCustom.getProgressDialog(this);
        eSmsCode.setText(getIntent().getStringExtra("sms"));

    }
}
