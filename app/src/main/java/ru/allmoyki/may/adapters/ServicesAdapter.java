package ru.allmoyki.may.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.ServiceClick;
import ru.allmoyki.may.pojo.CurrentCarwashPojo;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    private List<CurrentCarwashPojo.WashService> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder  {
         public TextView tvPrice;
        public CheckBox checkboxService;






        public ViewHolder(View v) {
            super(v);
            tvPrice = (TextView) v.findViewById(R.id.tvPrice);
            checkboxService = (CheckBox) v.findViewById(R.id.checkboxService);
//            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
        }
    }

    // Конструктор
    public ServicesAdapter(List<CurrentCarwashPojo.WashService> dataset) {
        mDataset = dataset;

    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvPrice.setText(mDataset.get(position).getCost());
        holder.checkboxService.setText(mDataset.get(position).getService());
        holder.checkboxService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("Log", "value " + b + " pos: " + position);
                BusProvider.getInstance().post(new ServiceClick(position,b));
            }
        });
//        holder.setClickListener(new RecyclerItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
////                BusProvider.getInstance().post(mDataset.getData().get(position).getId());
//                if (isLongClick) {
//                    Log.d("Log", "click: " + position);
//                } else {
//                    Log.d("Log", "click: " + position);
//                }
//            }
//        });

    }

     @Override

    public int getItemCount() {
        return mDataset.size();
    }

}