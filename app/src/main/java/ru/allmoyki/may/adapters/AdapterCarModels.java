package ru.allmoyki.may.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.pojo.CarModelsPojo;
import ru.allmoyki.may.utils.RecyclerItemClickListener;
import ru.allmoyki.may.widget.state.StateTextView;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public class AdapterCarModels extends RecyclerView.Adapter<AdapterCarModels.ViewHolder> {

    private CarModelsPojo mDataset;
    private CarModelsPojo mCleanCopyDataset;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        // наш пункт состоит только из одного TextView
        public StateTextView mTextView;
        private RecyclerItemClickListener clickListener;

        public void setClickListener(RecyclerItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }

        public ViewHolder(View v) {
            super(v);
            mTextView = (StateTextView) v.findViewById(R.id.tvModel);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
    }

    // Конструктор
    public AdapterCarModels(CarModelsPojo dataset) {
        mDataset = dataset;
        mCleanCopyDataset = mDataset;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public AdapterCarModels.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car_model, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.getData().get(position).getModel());

        holder.setClickListener(new RecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                BusProvider.getInstance().post(mDataset.getData().get(position).getId());
                if (isLongClick) {
                    Log.d("Log", "click: " + position);
                } else {
                    Log.d("Log", "click: " + position);
                }
            }
        });

    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return mDataset.getData().size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mDataset = new CarModelsPojo();
        if (charText.length() == 0) {
            mDataset = mCleanCopyDataset;
        } else {
            List<CarModelsPojo.Datum> datumList = new ArrayList<>();
            for (CarModelsPojo.Datum item : mCleanCopyDataset.getData()) {
                if (item.getModel().toLowerCase(Locale.getDefault()).contains(charText)) {
                    datumList.add(item);

                }
            }
            mDataset.setData(datumList);
        }
        notifyDataSetChanged();
    }
}