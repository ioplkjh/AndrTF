package ru.allmoyki.may.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.ButterKnife;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.activity.CarwashActivity;
import ru.allmoyki.may.activity.CarwashOfflineActivity;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.AllCarwashPojo;
import ru.allmoyki.may.pojo.CurrentCarwashPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;


public class FragmentMap extends Fragment {

    public static GoogleMap mMap;
    private boolean first_vis = true;
    private long time_update = 0;
    private OnMapReadyCallback mapReady = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            Log.d("Log", "map googleMap");

            mMap = googleMap;
            setMap();
        }
    };

    public FragmentMap() {
    }

    Dialog progressDialog;
    SupportMapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);


        FragmentManager fm = getActivity().getSupportFragmentManager();

        mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment == null) {
            Log.d("Log", "map mapFragment == null");
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        } else
            Log.d("Log", "map mapFragment == null");
        mapFragment.getMapAsync(mapReady);


        ButterKnife.inject(this, rootView);
        progressDialog = ProgressDialogCustom.getProgressDialog(getActivity());

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    void setMap() {
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                View v = getActivity().getLayoutInflater().inflate(R.layout.custom_marker_layout, null, false);

                String title = marker.getTitle().substring(marker.getTitle().indexOf("&") + 1);
                TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
                TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
                tvLat.setText(title);
                tvLng.setText(marker.getSnippet());


                return v;
            }

            @Override
            public View getInfoContents(Marker arg0) {
                return null;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String id = marker.getTitle().substring(0, marker.getTitle().indexOf("&"));

                HashMap<String, String> hashData = new HashMap<>();
                hashData.put("id", id);
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
                hashData.put("local_time", date);
                hashData.put("access_token", MyPreferences.getUserPushToken(getActivity()));

                GsonRequest<CurrentCarwashPojo> getCarwash = Api.getCurrentCarwash(responseSendDataCurrentCheck, errorListener, hashData);
                AppController.getInstance().addToRequestQueue(getCarwash);
                progressDialog.show();
            }
        });
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);


        LocationManager service = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Check if enabled and if not send user to the GPS settings
        if (!enabled) {
            Log.d("Log", "location none");
            float zoom = 15.0f;
            Location location = new Location("");
            location.setLatitude(52.721234);
            location.setLongitude(41.451799);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), zoom));
             getCarwasList(location);
            mMap.clear();
            time_update = System.currentTimeMillis();
        } else {
            Log.d("Log", "location yes");

        }

    }

    Response.Listener<CurrentCarwashPojo> responseSendDataCurrentCheck = new Response.Listener<CurrentCarwashPojo>() {
        @Override
        public void onResponse(CurrentCarwashPojo getCurrentCarwashPojo) {
            progressDialog.dismiss();

//            BusProvider.getInstance().post(1);
            BusProvider.getInstance().post(getCurrentCarwashPojo);
            if (getCurrentCarwashPojo.getData().getCarWashType().getId().equals("2")) {
                startActivity(new Intent(getActivity(), CarwashActivity.class));
            } else {
                startActivity(new Intent(getActivity(), CarwashOfflineActivity.class));

            }

        }
    };


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

    public static Location locationMap = null;

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            locationMap = location;
            float zoom = 15.0f;
            if (first_vis && location.getLatitude() != 0.0 || (location.getLatitude() == 52.721234 && location.getLongitude() == 41.451799)) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), zoom));
                first_vis = false;
                getCarwasList(location);
                mMap.clear();
                time_update = System.currentTimeMillis();
            }

        }
    };


    private void getCarwasList(Location location) {
        HashMap<String, String> hashData = new HashMap<>();
        hashData.put("latitude", location.getLatitude() + "");
        hashData.put("longitude", location.getLongitude() + "");
        hashData.put("max_distance", "20");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyy-mm-dd HH:MM");
        String formattedDate = df.format(c.getTime());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());

        hashData.put("local_time", date);
        hashData.put("access_token", MyPreferences.getUserPushToken(getActivity()));
        Log.d("Log", "hash: " + hashData.toString());
        GsonRequest<AllCarwashPojo> getCarwash = Api.getCarwashList(responseSendDataCheck, errorListener, hashData);
        AppController.getInstance().addToRequestQueue(getCarwash);

    }

    private Response.Listener<AllCarwashPojo> responseSendDataCheck = new Response.Listener<AllCarwashPojo>() {
        @Override
        public void onResponse(AllCarwashPojo getAllCarwashPojo) {
            for (int i = 0, size = getAllCarwashPojo.getData().size(); i < size; i++) {
                int idRes = 0;
                Log.d("Log", getAllCarwashPojo.getData().get(i).getCarWashTypeId());
                if (getAllCarwashPojo.getData().get(i).getCarWashTypeId().equals("1")) {
                    idRes = R.mipmap.sign_no_active;
                } else if (getAllCarwashPojo.getData().get(i).getCarWashTypeId().equals("2")) {
                    idRes = R.mipmap.sign_active_but_not_time;

                } else if (getAllCarwashPojo.getData().get(i).getCarWashTypeId().equals("3")) {
                    idRes = R.mipmap.sign_active;

                }
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(getAllCarwashPojo.getData().get(i).getLatitude()),
                                Double.parseDouble(getAllCarwashPojo.getData().get(i).getLongitude())))
                        .icon(BitmapDescriptorFactory.fromResource(idRes))
                        .title(getAllCarwashPojo.getData().get(i).getId() + "&" + getAllCarwashPojo.getData().get(i).getTitle())
                        .snippet(getAllCarwashPojo.getData().get(i).getAddress()));
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15.0f));
                        marker.showInfoWindow();
                        return true;
                    }
                });


            }
        }
    };
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e("Log", "error");
            progressDialog.dismiss();
        }
    };


}
