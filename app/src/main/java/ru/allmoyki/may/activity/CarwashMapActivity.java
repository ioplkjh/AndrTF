package ru.allmoyki.may.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.R;

public class CarwashMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @InjectView(R.id.tvHowToGo)
    TextView tvHowToGo;
    private GoogleMap mMap;
    private Intent intent;

    @InjectView(R.id.tvTitleToolBar)
    TextView tvTitleToolBar;

    @OnClick(R.id.tvTitleToolBar)
    public void close() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carwash_map);

        ButterKnife.inject(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        intent = getIntent();
        tvTitleToolBar.setText(intent.getStringExtra("title"));
        tvHowToGo.setText(intent.getStringExtra("how_to_go"));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoom = 15.0f;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Double.parseDouble(intent.getStringExtra("lat")), Double.parseDouble(intent.getStringExtra("lon")));
        mMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapDescriptorFactory.fromResource(R.mipmap.sign_active)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoom));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
