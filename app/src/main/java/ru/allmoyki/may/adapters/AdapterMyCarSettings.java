package ru.allmoyki.may.adapters;

import android.support.v7.widget.RecyclerView;
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
import ru.allmoyki.may.widget.state.StateImageButton;
import ru.allmoyki.may.widget.state.StateTextView;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public class AdapterMyCarSettings extends RecyclerView.Adapter<AdapterMyCarSettings.ViewHolder> {

    private RealmResults<CarsObject> mDataset;
    private RealmResults<CarsObject> mCleanCopyDataset;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        // наш пункт состоит только из одного TextView
        public StateTextView mTextView;
        public StateImageButton btnDelete;
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
            btnDelete = (StateImageButton) v.findViewById(R.id.btnDelete);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
    }

    // Конструктор
    public AdapterMyCarSettings(RealmResults<CarsObject> dataset) {
        mDataset = dataset;
        mCleanCopyDataset = mDataset;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public AdapterMyCarSettings.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_car_settings, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.mTextView.setText(mDataset.get(position).getCarName() + " " + mDataset.get(position).getCarModel());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusProvider.getInstance().post(position);
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