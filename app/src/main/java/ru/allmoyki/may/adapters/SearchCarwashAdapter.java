package ru.allmoyki.may.adapters;

import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.fragments.FragmentMap;
import ru.allmoyki.may.pojo.SearchPojo;
import ru.allmoyki.may.widget.state.StateLinearLayout;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public class SearchCarwashAdapter extends RecyclerView.Adapter<SearchCarwashAdapter.ViewHolder> {

    private List<SearchPojo.Datum> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvWorkTime, tvAdress, tvNumberAndStatus, tvDistance;
        public StateLinearLayout lResult;
        public RatingBar ratingBar;

        public ViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvNameCarwash);
            tvDistance = (TextView) v.findViewById(R.id.tvKm);
            tvNumberAndStatus = (TextView) v.findViewById(R.id.tvTypeAndNumber);
            ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
            tvWorkTime = (TextView) v.findViewById(R.id.tvWorkTimes);
            tvAdress = (TextView) v.findViewById(R.id.tvAdress);
            lResult = (StateLinearLayout) v.findViewById(R.id.lResult);

        }
    }

    public SearchCarwashAdapter(List<SearchPojo.Datum> dataset) {
        mDataset = dataset;

    }

    @Override
    public SearchCarwashAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
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

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvName.setText(mDataset.get(position).getTitle());
        if (FragmentMap.locationMap != null) {
            Location myLoc = FragmentMap.locationMap;
            Location carloc = new Location("");
             carloc.setLatitude(Double.parseDouble(mDataset.get(position).getLatitude()));
            carloc.setLongitude(Double.parseDouble(mDataset.get(position).getLongitude()));
//            holder.tvDistance.setText(round(myLoc.distanceTo(carloc), 1) + "");
            holder.tvDistance.setText(round(CalculationByDistance(myLoc, carloc), 2) + "");
//            Log.d("Log", "my " + myLoc.toString());
//            Log.d("Log", "car " + carloc.toString());
        } else {
            holder.tvDistance.setText("");
        }
        holder.tvAdress.setText(mDataset.get(position).getAddress());
        holder.tvNumberAndStatus.setText((position + 1) + "");
        int idRes = 0;
        if (mDataset.get(position).getCarWashTypeId().equals("1")) {
            idRes = R.mipmap.sign_no_active_search;
        } else if (mDataset.get(position).getCarWashTypeId().equals("2")) {
            idRes = R.mipmap.sign_active_but_not_time_search;
        } else if (mDataset.get(position).getCarWashTypeId().equals("3")) {
            idRes = R.mipmap.sign_active_search;
//            idRes = R.mipmap.app_icon;
        }
        Log.d("Log","adapter: " + mDataset.get(position).getCarWashTypeId());
        holder.tvNumberAndStatus.setBackgroundResource(idRes);
        holder.ratingBar.setRating(Float.parseFloat(mDataset.get(position).getRating()));
        holder.tvWorkTime.setText(
                mDataset.get(position).getWorkTime().getStartWashTime().getWashTime() +
                        " - " + mDataset.get(position).getWorkTime().getEndWashTime().getWashTime());
        holder.lResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusProvider.getInstance().post(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}