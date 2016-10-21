package ru.allmoyki.may.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.ServiceSearchProduce;
import ru.allmoyki.may.pojo.ServicesPojo;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public class ServicesSearchAdapter extends RecyclerView.Adapter<ServicesSearchAdapter.ViewHolder> {

    private List<ServicesPojo.Datum> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvService;


        public ViewHolder(View v) {
            super(v);
            tvService = (TextView) v.findViewById(R.id.tvService);

        }
    }

    // Конструктор
    public ServicesSearchAdapter(List<ServicesPojo.Datum> dataset) {
        mDataset = dataset;

    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public ServicesSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_services_search, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvService.setText(mDataset.get(position).getService());
        holder.tvService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceSearchProduce serviceSearch = new ServiceSearchProduce();
                serviceSearch.setServiceName(mDataset.get(position).getService());
                serviceSearch.setServiceId(mDataset.get(position).getId());
                BusProvider.getInstance().post(serviceSearch);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}