package ru.allmoyki.may.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.otto.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.activity.CarwashCarSearchActivity;
import ru.allmoyki.may.activity.CarwashSearchResultActivity;
import ru.allmoyki.may.activity.CarwashServiceSearchActivity;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.SearchCar;
import ru.allmoyki.may.bus.ServiceSearch;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.SearchPojo;
import ru.allmoyki.may.pojo.TimesPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;
import ru.allmoyki.may.widget.RangeSeekBar;
import ru.allmoyki.may.widget.state.StateImageButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearchCarwash extends Fragment {


    @InjectView(R.id.ratingBar)
    RatingBar ratingBar;
    @InjectView(R.id.imgSWiFi)
    ImageView imgSWiFi;
    @InjectView(R.id.imgSCoffee)
    ImageView imgSCoffee;
    @InjectView(R.id.imgSPayment)
    ImageView imgSPayment;
    @InjectView(R.id.imgSComfort)
    ImageView imgSComfort;
    @InjectView(R.id.imgSWc)
    ImageView imgSWc;
    @InjectView(R.id.tvTimeSearch)
    TextView tvTimeSearch;
    @InjectView(R.id.seekBarSearch)
    RangeSeekBar seekBarSearch;
    @InjectView(R.id.tvCar)
    TextView tvCar;
    @InjectView(R.id.tvService)
    TextView tvService;

    @InjectView(R.id.rToday)
    RadioButton rToday;
    @InjectView(R.id.btnClear)
    StateImageButton btnClear;
    @InjectView(R.id.rYesterday)
    RadioButton rYesterday;
    @InjectView(R.id.rTomorrow)
    RadioButton rTomorrow;
    @InjectView(R.id.rGDate)
    RadioGroup rGDate;
    ArrayList<TimesPojo.Datum> times;
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

        }
    };
    private Response.Listener<TimesPojo> timesListener = new Response.Listener<TimesPojo>() {
        @Override
        public void onResponse(TimesPojo timesPojo) {
            times = timesPojo.getData();
            tvTimeSearch.setText(times.get(0).getWashTime() + " - " + times.get(times.size() - 1).getWashTime());
            time_id_from = times.get(0).getId() + "";
            time_id_to = times.get(times.size() - 1).getId() + "";
        }
    };
    private Response.ErrorListener errorListenerSearch = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(getActivity(), "Ошибка", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    };
    private Response.Listener<SearchPojo> searchListener = new Response.Listener<SearchPojo>() {
        @Override
        public void onResponse(SearchPojo searchPojo) {
            progressDialog.dismiss();
            BusProvider.getInstance().post(searchPojo);
            startActivity(new Intent(getActivity(), CarwashSearchResultActivity.class));
        }
    };

    public FragmentSearchCarwash() {
    }

    @OnClick(R.id.btnClear)
    public void clear() {
        btnClear.setVisibility(View.INVISIBLE);
        tvCar.setText("");
        car_model_id = "";
        service_id = "";
        car_category_id = "";
        tvService.setText("");
    }

    @OnClick(R.id.btnService)
    public void service() {
        if (car_model_id.length() < 1) {
            Toast.makeText(getActivity(), "Сначала выберите машину", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(getActivity(), CarwashServiceSearchActivity.class).putExtra("category", car_category_id));

        }
    }

    @OnClick(R.id.btn_search)
    public void search() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("min_rating", min_rating);
        hashMap.put("car_model_id", car_model_id);
        hashMap.put("wash_service_id", service_id);
        hashMap.put("day_id", dayId);
        hashMap.put("time_id_from", time_id_from);
        hashMap.put("time_id_to", time_id_to);
        hashMap.put("access_token", MyPreferences.getUserPushToken(getActivity()));
//        private int sWifi = 0, sCoffee = 0, sPayment = 0, sComfort = 0, sWc = 0;

        if (sWifi == 1 || sCoffee == 1 || sPayment == 1 || sComfort == 1 || sWc == 1) {
            String additional_services = "";
            if (sWc == 1) {
                additional_services += "1";
            }
            if (sWifi == 1) {
                if (additional_services.length() > 0)
                    additional_services += ",";
                additional_services += "2";
            }
            if (sCoffee == 1) {
                if (additional_services.length() > 0)
                    additional_services += ",";
                additional_services += "3";
            }
            if (sPayment == 1) {
                if (additional_services.length() > 0)
                    additional_services += ",";
                additional_services += "4";
            }

            if (sComfort == 1) {
                if (additional_services.length() > 0)
                    additional_services += ",";
                additional_services += "5";
            }

            additional_services = "[" + additional_services + "]";
            hashMap.put("additional_services", additional_services);
        }
        progressDialog.show();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        hashMap.put("local_time", date);
        Log.d("Log", "hash: " + hashMap.toString());
        GsonRequest<SearchPojo> search = Api.search(searchListener, errorListenerSearch, hashMap);
        AppController.getInstance().addToRequestQueue(search);
//        Intent intent = new Intent(getActivity(), CarwashSearchResultActivity.class);
//        startActivity(intent);
    }

    @OnClick(R.id.imgSWiFi)
    public void clickWiFI() {
        if (sWifi == 1) {
            imgSWiFi.setImageResource(R.mipmap.uslugi_icon_wifi);
            sWifi = 0;
        } else {
            imgSWiFi.setImageResource(R.mipmap.uslugi_icon_wifi_active);
            sWifi = 1;
        }
    }

    @OnClick(R.id.imgSCoffee)
    public void imgSCoffee() {
        if (sCoffee == 1) {
            imgSCoffee.setImageResource(R.mipmap.uslugi_icon_coffee);
            sCoffee = 0;
        } else {
            imgSCoffee.setImageResource(R.mipmap.uslugi_icon_coffee_active);
            sCoffee = 1;
        }
    }

    @OnClick(R.id.imgSPayment)
    public void imgSPayment() {
        if (sPayment == 1) {
            imgSPayment.setImageResource(R.mipmap.uslugi_icon_payment);
            sPayment = 0;
        } else {
            imgSPayment.setImageResource(R.mipmap.uslugi_icon_payment_active);
            sPayment = 1;
        }
    }

    @OnClick(R.id.imgSComfort)
    public void imgSComfort() {
        if (sComfort == 1) {
            imgSComfort.setImageResource(R.mipmap.uslugi_icon_comfort);
            sComfort = 0;
        } else {
            imgSComfort.setImageResource(R.mipmap.uslugi_icon_comfort_active);
            sComfort = 1;
        }
    }

    @OnClick(R.id.imgSWc)
    public void imgSWc() {
        if (sWc == 1) {
            imgSWc.setImageResource(R.mipmap.uslugi_icon_wc);
            sWc = 0;
        } else {
            imgSWc.setImageResource(R.mipmap.uslugi_icon_wc_active);
            sWc = 1;
        }
    }

    @OnClick(R.id.btnCar)
    public void getCar() {
        startActivity(new Intent(getActivity(), CarwashCarSearchActivity.class));
    }

    @Subscribe
    public void setCar(SearchCar searchCar) {

        btnClear.setVisibility(View.VISIBLE);
        car_model_id = searchCar.getModelId();
        car_category_id = searchCar.getCarCategoryId();
        tvCar.setText(searchCar.getCarModelMara());
//        tvService.setText("124");
        service_id = "";
        if (car_model_id.equals("")) {
            btnClear.setVisibility(View.INVISIBLE);
        }
    }

    private int sWifi = 0, sCoffee = 0, sPayment = 0, sComfort = 0, sWc = 0;
    private String dayId = "1";
    private String car_model_id = "";
    private String car_category_id = "";
    private String service_id = "";
    private String min_rating = "0";
    private String time_id_from = "0";
    private String time_id_to = "0";

    private Dialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_carwash, container, false);
        ButterKnife.inject(this, rootView);
        progressDialog = ProgressDialogCustom.getProgressDialog(getActivity());
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                min_rating = (int) v + "";
                if (v < 1)
                    min_rating = "0";
            }
        });
        rGDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rToday:
                        dayId = "1";
                        break;
                    case R.id.rTomorrow:
                        dayId = "2";
                        break;
                    case R.id.rYesterday:
                        dayId = "3";
                        break;
                }
            }
        });
        rToday.setChecked(true);


        seekBarSearch.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                tvTimeSearch.setText(times.get((int) minValue).getWashTime() + " - " + times.get((int) maxValue).getWashTime());
                time_id_from = times.get((int) minValue).getId() + "";
                time_id_to = times.get((int) maxValue).getId() + "";
            }
        });

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("access_token", MyPreferences.getUserPushToken(getActivity()));

        GsonRequest<TimesPojo> times = Api.getTimes(timesListener, errorListener, hashMap);
        AppController.getInstance().addToRequestQueue(times);
        return rootView;
    }

    @Subscribe
    public void service(ServiceSearch serviceSearch) {
        Log.d("Log", "service");
        Log.d("Log", "car_category_id " + car_category_id + " size " + car_category_id.length());
        Log.d("Log", serviceSearch.getServiceName());
        //        if (tvCar.getText().toString().length() > 0) {
        //        tvCar.setText("123");
        tvService.setText(serviceSearch.getServiceName());
        service_id = serviceSearch.getServiceId();
        Log.d("Log", "11111111111111111111");

//        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        SearchCar searchCar = new SearchCar();
        searchCar.setCarModelMara("");
        searchCar.setCarCategoryId("");
        searchCar.setModelId("");
        BusProvider.getInstance().post(searchCar);
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
