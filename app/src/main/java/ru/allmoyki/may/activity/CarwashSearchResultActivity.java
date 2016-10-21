package ru.allmoyki.may.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.R;
import ru.allmoyki.may.adapters.SearchCarwashAdapter;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.SearchProduce;
import ru.allmoyki.may.fragments.FragmentMap;
import ru.allmoyki.may.pojo.SearchPojo;

public class CarwashSearchResultActivity extends AppCompatActivity {
    @InjectView(R.id.tvTitleToolBar)
    TextView tvTitleToolBar;

    @OnClick(R.id.tvTitleToolBar)
    public void close() {
        finish();
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carwash_search_result);
        ButterKnife.inject(this);
        tvTitleToolBar.setText("Результаты поиска");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_result);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    SearchPojo searchPojoCurrent;

    @Subscribe
    public void setResult(SearchPojo result) {
        searchPojoCurrent = result;
        List<SearchPojo.Datum> minList = new ArrayList<>();
        for (SearchPojo.Datum item : result.getData()) {
            if (FragmentMap.locationMap != null) {
                Location myLoc = FragmentMap.locationMap;
                Location carloc = new Location("");
                carloc.setLatitude(Double.parseDouble(item.getLatitude()));
                carloc.setLongitude(Double.parseDouble(item.getLongitude()));
//            holder.tvDistance.setText(round(myLoc.distanceTo(carloc), 1) + "");
                double dist = 20.0;
//                if (round(CalculationByDistance(myLoc, carloc), 2) < dist) {
                    minList.add(item);
//                }
//            Log.d("Log", "my " + myLoc.toString());
//            Log.d("Log", "car " + carloc.toString());
            }
        }/*0.915.10.17.2241*/

//        SearchCarwashAdapter searchCarwashAdapter = new SearchCarwashAdapter(result.getData());
        SearchCarwashAdapter searchCarwashAdapter = new SearchCarwashAdapter(minList);
        mRecyclerView.setAdapter(searchCarwashAdapter);
        Log.d("Log", "SearchPojo ");
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public double CalculationByDistance(Location StartP, Location EndP) {
        int Radius = 6371;//radius of earth in Km
        double lat1 = StartP.getLatitude();
        double lat2 = EndP.getLatitude();
        double lon1 = StartP.getLongitude();
        double lon2 = EndP.getLongitude();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Log", "" + valueResult + "   KM  " + kmInDec + " Meter   " + meterInDec);

        return Radius * c;
    }

    @Subscribe
    public void getPosition(Integer pos) {
        SearchProduce searchProduce = new SearchProduce();
        searchProduce.setPosition((int) pos);
        searchProduce.setSearchPojo(searchPojoCurrent);
        startActivity(new Intent(CarwashSearchResultActivity.this, CarwashMapResultActivity.class));
        BusProvider.getInstance().post(searchProduce);
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
