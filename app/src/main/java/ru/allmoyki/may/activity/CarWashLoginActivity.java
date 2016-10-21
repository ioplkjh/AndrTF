package ru.allmoyki.may.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gcm.GCMRegistrar;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.LoginPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;

public class CarWashLoginActivity extends Activity {

    private GCMReceiver mGCMReceiver;
    private IntentFilter mOnRegisteredFilter;
    private String token="";

    private class GCMReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String regId = intent.getStringExtra(MyPreferences.FIELD_REGISTRATION_ID);
            Log.e("Log", "2register: " + regId);
            token = regId;
//            sendIdToServer(regId);
        }
    }

    @InjectView(R.id.ePhoneNumber)
    EditText ePhoneNumber;
    private Dialog progressDialog;
    private Response.Listener<LoginPojo> loginListener = new Response.Listener<LoginPojo>() {
        @Override
        public void onResponse(LoginPojo loginPojo) {
            progressDialog.dismiss();
            if (loginPojo.getData().getAccessToken() == null) {
                Intent intent = new Intent(CarWashLoginActivity.this, RegistrationFirstStepActivity.class);
                intent.putExtra("phone", loginPojo.getData().getLogin());
                intent.putExtra("sms", loginPojo.getData().getSmsCode());
                startActivity(intent);
            } else {
                Intent intent = new Intent(CarWashLoginActivity.this, RegistrationSecondStepActivity.class);
                intent.putExtra("sms", loginPojo.getData().getSmsCode());
                startActivity(intent);
            }
            finish();
        }
    };

//    device_token (строка токена) и device_type (1-android,2-ios)

    @OnClick(R.id.btn_get_sms_code)
    public void login() {
        if (!android.util.Patterns.PHONE.matcher(ePhoneNumber.getText().toString()).matches()) {
            Toast.makeText(CarWashLoginActivity.this, "Введите корректный номер телефона", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("login", ePhoneNumber.getText().toString());
            hashMap.put("device_token", token);
            hashMap.put("device_type", "1");

            Log.d("Log", hashMap.toString());

            GsonRequest<LoginPojo> login = Api.login(loginListener, errorListener, hashMap);
            AppController.getInstance().addToRequestQueue(login);
            progressDialog.show();
        }
//        startActivity(new Intent(CarWashLoginActivity.this, RegistrationFirstStepActivity.class));
//        finish();
    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e("Log", volleyError.getMessage());
            progressDialog.dismiss();
            Toast.makeText(CarWashLoginActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash_login);
        ButterKnife.inject(this);
        progressDialog = ProgressDialogCustom.getProgressDialog(this);

        mGCMReceiver = new GCMReceiver();
        mOnRegisteredFilter = new IntentFilter();
        mOnRegisteredFilter.addAction(MyPreferences.ACTION_ON_REGISTERED);

        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        final String regId = GCMRegistrar.getRegistrationId(this);

        if (!regId.equals("")) {
            Log.e("Log", "1register: " + regId);
//            sendIdToServer(regId);
            token = regId;
        } else {
            GCMRegistrar.register(this, MyPreferences.SENDER_ID);
        }


    }


}
