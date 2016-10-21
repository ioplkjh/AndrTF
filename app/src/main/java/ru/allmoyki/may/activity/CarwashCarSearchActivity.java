package ru.allmoyki.may.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.otto.Subscribe;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.adapters.AdapterCarMarks;
import ru.allmoyki.may.adapters.AdapterCarModels;
import ru.allmoyki.may.adapters.AdapterMyCar;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.SearchCar;
import ru.allmoyki.may.db.CarsObject;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.AllCarMarkaPojo;
import ru.allmoyki.may.pojo.CarModelsPojo;
import ru.allmoyki.may.utils.ProgressDialogCustom;
import ru.allmoyki.may.widget.state.StateButton;

public class CarwashCarSearchActivity extends AppCompatActivity {
    @InjectView(R.id.btnAddCar)
    StateButton btnAddCar;
    @InjectView(R.id.eSearch)
    EditText eSearch;

    @OnClick(R.id.btnAddCar)
    public void addCadr() {
        visData(STEP.SECOND);
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    enum STEP {FIRTS, SECOND, THIRD}

    STEP currentStep;

    private Dialog progressDialog;
    private AdapterCarMarks carMarks;
    private AdapterCarModels carModel;
    private AdapterMyCar adapterMyCar;

    String marka = "", markaId = "", model = "", modelId = "", carCategoryId = "";
    CarModelsPojo carModels;

    @InjectView(R.id.tvTitleToolBar)
    TextView tvTitleToolBar;

    @OnClick(R.id.tvTitleToolBar)
    public void close() {
        backState();
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        backState();
    }

    private void backState() {
        if (currentStep == STEP.THIRD) {
            visData(STEP.SECOND);
        } else {
            finish();

        }
    }

    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carwash_car_model);
        ButterKnife.inject(this);
        realm = Realm.getInstance(this);
        tvTitleToolBar.setText("Марка и модель");

        progressDialog = ProgressDialogCustom.getProgressDialog(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.rvMyCar);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        RealmResults<CarsObject> carsList = realm.where(CarsObject.class).findAll();

        if (carsList.size() > 0) {
            visData(STEP.FIRTS);
            adapterMyCar = new AdapterMyCar(carsList);
            mRecyclerView.setAdapter(adapterMyCar);
            eSearch.setText("");
        } else {
            visData(STEP.SECOND);
        }
        eSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (currentStep) {
                    case FIRTS:

                        RealmResults<CarsObject> carsList = realm.where(CarsObject.class)
                                .contains("carNameSmall", charSequence.toString().toLowerCase()).or()
                                .contains("carModelSmall", charSequence.toString().toLowerCase()).findAll();
                        adapterMyCar = new AdapterMyCar(carsList);
                        mRecyclerView.setAdapter(adapterMyCar);

                        break;
                    case SECOND:
                        try {
                            carMarks.filter(charSequence.toString());
                        } catch (Exception e) {
                        }
                        break;
                    case THIRD:
                        try {
                            carModel.filter(charSequence.toString());
                        } catch (Exception e) {
                        }
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void visData(STEP step) {
        currentStep = step;
        switch (step) {
            case FIRTS:
                btnAddCar.setVisibility(View.VISIBLE);
                break;
            case SECOND:
                btnAddCar.setVisibility(View.GONE);
                getCarMark();
                break;
            case THIRD:
                btnAddCar.setVisibility(View.GONE);

                break;
        }
    }

    private void getCarMark() {
//        eSearch.setText("");
        HashMap<String, String> hashData = new HashMap<>();
        hashData.put("access_token", "123");

        GsonRequest<AllCarMarkaPojo> getCarwMarks = Api.getAllCarMarka(getCarwashFeedbacksPojoListener, errorListener, hashData);
        AppController.getInstance().addToRequestQueue(getCarwMarks);
        progressDialog.show();
    }

    private void getCarModel(String id) {
//        eSearch.setText("");
        HashMap<String, String> hashData = new HashMap<>();
        hashData.put("car_brand_id", id);
        hashData.put("access_token", "123");

        GsonRequest<CarModelsPojo> getCarwMarks = Api.getAllCarModels(getCarModelsListener, errorListener, hashData);
        AppController.getInstance().addToRequestQueue(getCarwMarks);
        progressDialog.show();
    }

    AllCarMarkaPojo carMarka;
    Response.Listener<AllCarMarkaPojo> getCarwashFeedbacksPojoListener = new Response.Listener<AllCarMarkaPojo>() {
        @Override
        public void onResponse(AllCarMarkaPojo allCarMarka) {

            carMarka = allCarMarka;
            carMarks = new AdapterCarMarks(allCarMarka);
            mRecyclerView.setAdapter(carMarks);
            progressDialog.dismiss();
            eSearch.setText("");
        }
    };


    private Response.Listener<CarModelsPojo> getCarModelsListener = new Response.Listener<CarModelsPojo>() {
        @Override
        public void onResponse(CarModelsPojo carModelss) {
            carModel = new AdapterCarModels(carModelss);
            carModels = carModelss;
            mRecyclerView.setAdapter(carModel);
            progressDialog.dismiss();
            eSearch.setText("");
        }
    };


    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            progressDialog.dismiss();
        }
    };


    @Subscribe
    public void displayCarwash(String id) {
        switch (currentStep) {
            case FIRTS:
                break;
            case SECOND:
                mRecyclerView.setAdapter(null);
                currentStep = STEP.THIRD;
                getCarModel(id);
                for (AllCarMarkaPojo.Datum datum : carMarka.getData()) {
                    if (datum.getId().equals(id)) {
                        marka = datum.getBrand();
                        markaId = datum.getId();
                    }
                }
                break;
            case THIRD:
                for (CarModelsPojo.Datum datum : carModels.getData()) {
                    if (datum.getId().equals(id)) {
                        model = datum.getModel();
                        modelId = datum.getId();
                        carCategoryId = datum.getCarCategoryId();
                    }
                }
                finished();
                break;
        }
    }


    private void finished() {
        SearchCar searchCar = new SearchCar();
        searchCar.setCarModelMara(marka + " " + model);
        searchCar.setCarCategoryId(carCategoryId);
        searchCar.setModelId(modelId);
        finish();
        BusProvider.getInstance().post(searchCar);
        Log.d("Log", "post");
    }

    @Subscribe
    public void getMyCar(CarsObject carsObject) {

        SearchCar searchCar = new SearchCar();
        searchCar.setCarModelMara(carsObject.getCarName() + " " + carsObject.getCarModel());
        searchCar.setModelId(carsObject.getCarModelId());
        searchCar.setCarCategoryId(carsObject.getCarCategoryId());
        finish();
        BusProvider.getInstance().post(searchCar);
        Log.d("Log", "post");
    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        BusProvider.getInstance().unregister(this);
    }
}
