package ru.allmoyki.may.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.adapters.ServicesSearchAdapter;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.ServiceSearch;
import ru.allmoyki.may.bus.ServiceSearchProduce;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.ServicesPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;

public class CarwashServiceSearchActivity extends AppCompatActivity {

    @InjectView(R.id.tvTitleToolBar)
    TextView tvTitleToolBar;
    private Dialog dialog;
    private ArrayList<ServicesPojo.Datum> selectList= new ArrayList<>();
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            dialog.dismiss();
        }
    };
    private Response.Listener<ServicesPojo> servicesListener = new Response.Listener<ServicesPojo>() {
        @Override
        public void onResponse(ServicesPojo servicesPojo) {
            selectList = new ArrayList<>();

            for (ServicesPojo.Datum datum : servicesPojo.getData()) {
                if (datum.getCarCategoryId().equals(carCategoryId)) {
                    selectList.add(datum);
                }
            }
//            ServicesSearchAdapter servicesSearchAdapter = new ServicesSearchAdapter(servicesPojo.getData());
            ServicesSearchAdapter servicesSearchAdapter = new ServicesSearchAdapter(selectList);
            mRecyclerView.setAdapter(servicesSearchAdapter);
            dialog.dismiss();

        }
    };


    @Subscribe
    public void service(ServiceSearchProduce serviceSearch) {
        if (selectList.size() > 0) {
            ServiceSearch service = new ServiceSearch();
            service.setServiceName(serviceSearch.getServiceName());
            service.setServiceId(serviceSearch.getServiceId());
            BusProvider.getInstance().post(service);
            finish();
        }
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private String carCategoryId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carwash_service_search);
        ButterKnife.inject(this);
        dialog = ProgressDialogCustom.getProgressDialog(this);
        tvTitleToolBar.setText("Услуга");
        tvTitleToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        carCategoryId = getIntent().getStringExtra("category");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_services);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("access_token", MyPreferences.getUserPushToken(this));
        dialog.show();
        GsonRequest<ServicesPojo> services = Api.services(servicesListener, errorListener, hashMap);
        AppController.getInstance().addToRequestQueue(services);
    }

    @Override
    public void onStart() {
        super.onStart();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        BusProvider.getInstance().unregister(this);
    }

}
