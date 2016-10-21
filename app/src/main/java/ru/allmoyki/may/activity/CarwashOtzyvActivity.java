package ru.allmoyki.may.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.adapters.AdapterFeedbacks;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.CarwashFeedbacksPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;

public class CarwashOtzyvActivity extends Activity {

    String carwashId = "";
    private Dialog progressDialog;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @InjectView(R.id.tvTitleToolBar)
    TextView tvTitleToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carwash_otzyv);
        ButterKnife.inject(this);
        carwashId = getIntent().getStringExtra("id");
        progressDialog = ProgressDialogCustom.getProgressDialog(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_feedbacks);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        tvTitleToolBar.setText("Отзывы");
        tvTitleToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        HashMap<String, String> hashData = new HashMap<>();

        hashData.put("car_wash_id", carwashId);
        hashData.put("access_token", MyPreferences.getUserPushToken(CarwashOtzyvActivity.this));
        Log.d("Log",hashData.toString());
        GsonRequest<CarwashFeedbacksPojo> getCarwashFeedbacksPojoGsonRequest = Api.getCarwashFeedbacks(getCarwashFeedbacksPojoListener, errorListener, hashData);
        AppController.getInstance().addToRequestQueue(getCarwashFeedbacksPojoGsonRequest);
        progressDialog.show();
    }

    Response.Listener<CarwashFeedbacksPojo> getCarwashFeedbacksPojoListener = new Response.Listener<CarwashFeedbacksPojo>() {
        @Override
        public void onResponse(CarwashFeedbacksPojo carwashFeedbacks) {
            progressDialog.dismiss();
            mAdapter = new AdapterFeedbacks(carwashFeedbacks);
            mRecyclerView.setAdapter(mAdapter);
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e("Log", "error");
            progressDialog.dismiss();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
