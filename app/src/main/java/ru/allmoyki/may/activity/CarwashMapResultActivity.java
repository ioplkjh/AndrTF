package ru.allmoyki.may.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
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
import com.squareup.otto.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.SearchProduce;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.CurrentCarwashPojo;
import ru.allmoyki.may.pojo.SearchPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;

public class CarwashMapResultActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Intent intent;

    @InjectView(R.id.tvTitleToolBar)
    TextView tvTitleToolBar;

    @OnClick(R.id.tvTitleToolBar)
    public void close() {
        finish();
    }

    private int pos = 0;
    private SearchProduce searchProduce;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carwash_map_result);

        ButterKnife.inject(this);
        progressDialog = ProgressDialogCustom.getProgressDialog(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        intent = getIntent();
        tvTitleToolBar.setText("Результаты поиска");
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
                View v = CarwashMapResultActivity.this.getLayoutInflater().inflate(R.layout.custom_marker_layout, null, false);

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
                hashData.put("access_token", MyPreferences.getUserPushToken(CarwashMapResultActivity.this));
                System.currentTimeMillis();

                GsonRequest<CurrentCarwashPojo> getCarwash = Api.getCurrentCarwash(responseSendDataCurrentCheck, errorListener, hashData);
                AppController.getInstance().addToRequestQueue(getCarwash);
                progressDialog.show();
            }
        });
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMyLocationEnabled(true);
        //            setUpMap(latitude, longitude);
//        } else {
//            Log.d("Log", "map NOT null");
//         }
    }


    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e("Log", "error");
            progressDialog.dismiss();
        }
    };
    Response.Listener<CurrentCarwashPojo> responseSendDataCurrentCheck = new Response.Listener<CurrentCarwashPojo>() {
        @Override
        public void onResponse(CurrentCarwashPojo getCurrentCarwashPojo) {
            progressDialog.dismiss();

//            BusProvider.getInstance().post(1);
            BusProvider.getInstance().post(getCurrentCarwashPojo);
            if (getCurrentCarwashPojo.getData().getCarWashType().getId().equals("2")) {
                startActivity(new Intent(CarwashMapResultActivity.this, CarwashActivity.class));
            } else {
                startActivity(new Intent(CarwashMapResultActivity.this, CarwashOfflineActivity.class));

            }

        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setMap();
        pos = searchProduce.getPosition();
        SearchPojo searchPojo = searchProduce.getSearchPojo();
        for (int i = 0, size = searchPojo.getData().size(); i < size; i++) {
            int idRes = 0;
            Log.d("Log", "result!!!" + searchPojo.getData().get(i).getCarWashTypeId());
            switch (searchPojo.getData().get(i).getCarWashTypeId()) {
                case "1":
                    idRes = R.mipmap.sign_no_active_search;

                    break;
                case "2":
                    idRes = R.mipmap.sign_active_but_not_time_search;

                    break;
                case "3":
                idRes = R.mipmap.sign_active_search;

                    break;
            }

            View markerView = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_result, null);
            TextView tvType = (TextView) markerView.findViewById(R.id.tvTypeAndNumber);
            tvType.setText((i + 1) + "");
            tvType.setBackgroundResource(idRes);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(searchPojo.getData().get(i).getLatitude()),
                            Double.parseDouble(searchPojo.getData().get(i).getLongitude())))
                    .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, markerView)))
                    .title(searchPojo.getData().get(i).getId() + "&" + searchPojo.getData().get(i).getTitle())
                    .snippet(searchPojo.getData().get(i).getAddress()));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15.0f));
                    marker.showInfoWindow();
                    return true;
                }
            });
            if (i == pos) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(searchPojo.getData().get(i).getLatitude()),
                        Double.parseDouble(searchPojo.getData().get(i).getLongitude())), 15.0f));
            }
        }
    }

    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    @Subscribe
    public void setMarkers(SearchProduce search) {
        searchProduce = search;
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
