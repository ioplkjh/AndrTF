package ru.allmoyki.may.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.RegionsPojo;
import ru.allmoyki.may.pojo.UserPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;
import ru.allmoyki.may.widget.NoDefaultSpinner;

/**
 * Created by Boichuk Dmitriy on 28.08.2015.
 */
public class RegistrationFirstStepActivity extends Activity {


    @InjectView(R.id.ePhoneNumber)
    EditText ePhoneNumber;
    @InjectView(R.id.eName)
    EditText eName;
    @InjectView(R.id.eSmsCode)
    EditText eSmsCode;

    private Response.ErrorListener errorListener = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            progressDialog.dismiss();
        }
    };
    private Response.ErrorListener errorListenerReg = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            progressDialog.dismiss();
            Toast.makeText(RegistrationFirstStepActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
        }
    };
    private Response.Listener<RegionsPojo> getCarModelsListener = new Response.Listener<RegionsPojo>() {
        @Override
        public void onResponse(RegionsPojo regions) {

            ArrayList<String> regionsList = new ArrayList<>();
            for (RegionsPojo.Datum datum : regions.getData()) {
                regionsList.add(datum.getRegion());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistrationFirstStepActivity.this,
                    R.layout.item_spinner, regionsList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    };



    @OnClick(R.id.btn_confirm_sms_code)
    public void getCode() {

        if (eName.getText().toString().length() < 2 ||
                spinner.getSelectedItemPosition() == Spinner.INVALID_POSITION ||
                eSmsCode.getText().toString().contains(" ")) {
            Toast.makeText(RegistrationFirstStepActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, String> hashData = new HashMap<>();
            int id = spinner.getSelectedItemPosition() + 1;
            hashData.put("phone", ePhoneNumber.getText().toString());
            hashData.put("region_id", "" + id);
            hashData.put("fio", eName.getText().toString());
            hashData.put("sms_code", eSmsCode.getText().toString());
            Response.Listener<UserPojo> registrationListener = new Response.Listener<UserPojo>() {
                @Override
                public void onResponse(UserPojo userPojo) {
                    Log.d("Log", "1234: " + userPojo.getData().getAccessToken());
                    MyPreferences.seUserPushToken(RegistrationFirstStepActivity.this, userPojo.getData().getAccessToken());
                    progressDialog.dismiss();
                    startActivity(new Intent(RegistrationFirstStepActivity.this, MainActivity.class));
                    finish();
                }
            };
            progressDialog.show();
            GsonRequest<UserPojo> registration = Api.registration(registrationListener, errorListenerReg, hashData);
            AppController.getInstance().addToRequestQueue(registration);

        }
    }

    NoDefaultSpinner spinner;
    Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_first_step);
        ButterKnife.inject(this);
        spinner = (NoDefaultSpinner) findViewById(R.id.planets_spinner);
        GsonRequest<RegionsPojo> getRegions = Api.getRegions(getCarModelsListener, errorListener, new HashMap<String, String>());
        AppController.getInstance().addToRequestQueue(getRegions);
        progressDialog = ProgressDialogCustom.getProgressDialog(this);

        Intent intent = getIntent();
        ePhoneNumber.setText(intent.getStringExtra("phone"));
        eSmsCode.setText(intent.getStringExtra("sms"));


    }
}
