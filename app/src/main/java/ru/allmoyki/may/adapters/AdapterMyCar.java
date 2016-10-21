package ru.allmoyki.may.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.RealmResults;
import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.db.CarsObject;
import ru.allmoyki.may.utils.RecyclerItemClickListener;
import ru.allmoyki.may.widget.state.StateTextView;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public class AdapterMyCar extends RecyclerView.Adapter<AdapterMyCar.ViewHolder> {

    private RealmResults<CarsObject> mDataset;
    private RealmResults<CarsObject> mCleanCopyDataset;

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
    public AdapterMyCar(RealmResults<CarsObject> dataset) {
        mDataset = dataset;
        mCleanCopyDataset = mDataset;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public AdapterMyCar.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_car, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.get(position).getCarName() + " " + mDataset.get(position).getCarModel());
        holder.setClickListener(new RecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                BusProvider.getInstance().post(mDataset.get(position));
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
        return mDataset.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
//        mDataset = new AllCarMarkaPojo();

        for (int i = 0; i < mDataset.size(); i++) {
            mDataset.remove(i);
        }
        if (charText.length() == 0) {
            mDataset = mCleanCopyDataset;
        } else {
            List<CarsObject> datumList = new ArrayList<>();
            for (CarsObject item : mCleanCopyDataset) {
                if (item.getCarModel().toLowerCase(Locale.getDefault()).contains(charText) || item.getCarName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mDataset.add(item);

                }
            }
        }
        notifyDataSetChanged();
    }
}