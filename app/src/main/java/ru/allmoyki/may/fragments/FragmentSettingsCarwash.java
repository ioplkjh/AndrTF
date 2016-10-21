package ru.allmoyki.may.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.activity.CarwashCarSettingsActivity;
import ru.allmoyki.may.adapters.AdapterMyCarSettings;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.db.CarsObject;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.CurrentUserPojo;
import ru.allmoyki.may.pojo.RegionsPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;
import ru.allmoyki.may.widget.NoDefaultSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSettingsCarwash extends Fragment {


    @InjectView(R.id.sAutoLogin)
    SwitchCompat sAutoLogin;
    @InjectView(R.id.sAutoCar)
    SwitchCompat sAutoCar;
    @InjectView(R.id.sAutoServices)
    SwitchCompat sAutoServices;
    @InjectView(R.id.eName)
    EditText eName;
    @InjectView(R.id.tvRegion)
    TextView tvRegion;

    @OnClick(R.id.btn_sen_user_info)
    public void sendUserInfo() {
        if (eName.getText().toString().length() < 2) {
            Toast.makeText(getActivity(), "Заполните имя", Toast.LENGTH_SHORT).show();
        } else {
            String id = "";
            if (spinner.getSelectedItemPosition() == Spinner.INVALID_POSITION) {
                id = MyPreferences.getRegion(getActivity());
            } else {
                id = "" + (spinner.getSelectedItemPosition() + 1);
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("access_token", MyPreferences.getUserPushToken(getActivity()));
            map.put("region_id", id);
            map.put("fio", eName.getText().toString());

            Response.ErrorListener error = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Ошибка", Toast.LENGTH_SHORT).show();
                }
            };
            Response.Listener<CurrentUserPojo> updaListener = new Response.Listener<CurrentUserPojo>() {
                @Override
                public void onResponse(CurrentUserPojo currentUserPojo) {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Данные сохранены", Toast.LENGTH_SHORT).show();
                    MyPreferences.setUser(getActivity(), currentUserPojo.getData().getFio());
                    MyPreferences.setRegion(getActivity(), currentUserPojo.getData().getRegionId());

                }
            };
            GsonRequest<CurrentUserPojo> login = Api.updateClient(updaListener, error, map);
            AppController.getInstance().addToRequestQueue(login);
            progressDialog.show();
        }
    }

    NoDefaultSpinner spinner;

    private Response.Listener<RegionsPojo> getRegionsListener = new Response.Listener<RegionsPojo>() {
        @Override
        public void onResponse(RegionsPojo regions) {
            ArrayList<String> regionsList = new ArrayList<>();
            for (RegionsPojo.Datum datum : regions.getData()) {
                regionsList.add(datum.getRegion());
                if (datum.getId().equals(MyPreferences.getRegion(getActivity()))) {
                    tvRegion.setText(datum.getRegion());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
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
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

        }
    };

    @OnClick(R.id.btnAddCar)
    public void addCar() {
        startActivity(new Intent(getActivity(), CarwashCarSettingsActivity.class));
    }

    public FragmentSettingsCarwash() {
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Realm realm;
    private RealmResults<CarsObject> carsList;
    private Dialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings_carwash, container, false);
        realm = Realm.getInstance(getActivity());
        ButterKnife.inject(this, view);
        progressDialog = ProgressDialogCustom.getProgressDialog(getActivity());

        spinner = (NoDefaultSpinner) view.findViewById(R.id.planets_spinner);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_cars);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        sAutoLogin.setChecked(MyPreferences.getAutoLogin(getActivity()));
        sAutoServices.setChecked(MyPreferences.getAutoServices(getActivity()));
        sAutoCar.setChecked(MyPreferences.getAutoCar(getActivity()));
        eName.setText(MyPreferences.getUser(getActivity()));

        sAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyPreferences.setAutoLogin(getActivity(), b);
            }
        });

        sAutoCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyPreferences.setAutoCar(getActivity(), b);
            }
        });
        sAutoServices.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MyPreferences.setAutoServices(getActivity(), b);
            }
        });

        GsonRequest<RegionsPojo> getRegions = Api.getRegions(getRegionsListener, errorListener, new HashMap<String, String>());
        AppController.getInstance().addToRequestQueue(getRegions);

        return view;
    }

    @Subscribe
    public void deleteCar(Integer pos) {
        realm.beginTransaction();
        carsList.remove((int) pos);
        realm.commitTransaction();
        setCars();
    }

    @Subscribe
    public void addCar(Boolean s) {
        setCars();
    }

    private void setCars() {
        carsList = realm.where(CarsObject.class).findAll();
        AdapterMyCarSettings adapterMyCar = new AdapterMyCarSettings(carsList);
        mRecyclerView.setAdapter(adapterMyCar);
        setListViewHeightBasedOnChildren(carsList);
    }

    @Override
    public void onResume() {
        super.onResume();
        setCars();
    }

    private void setListViewHeightBasedOnChildren(RealmResults<CarsObject> carsList) {
        int totalHeight = 0;
        for (int i = 0; i < carsList.size(); i++) {
            totalHeight += getResources().getDimensionPixelOffset(R.dimen.size_item_my_car);
        }

        ViewGroup.LayoutParams params = mRecyclerView.getLayoutParams();
        params.height = totalHeight;
        mRecyclerView.setLayoutParams(params);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
