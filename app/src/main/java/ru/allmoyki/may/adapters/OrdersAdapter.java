package ru.allmoyki.may.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.pojo.OrderPojo;
import ru.allmoyki.may.widget.state.StateLinearLayout;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private List<OrderPojo.Datum> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public StateLinearLayout lOrder;
        public View lDivider;
        public TextView tvOrderStatus,tvReview, tvAnswer, tvOrderTime, tvOrderCost, tvOderDate, tvOrderCarwashName, tvAdress;
        public LinearLayout lReview;
        public ViewHolder(View v) {
            super(v);
            tvOrderStatus = (TextView) v.findViewById(R.id.tvOrderStatus);
            tvAdress = (TextView) v.findViewById(R.id.tvOrderAdress);
            tvOrderCost = (TextView) v.findViewById(R.id.tvOrderCost);
            tvOrderCarwashName = (TextView) v.findViewById(R.id.tvOrderCarwashName);
            tvOrderTime = (TextView) v.findViewById(R.id.tvOrderTime);
            tvOderDate = (TextView) v.findViewById(R.id.tvOrderDate);
            tvReview= (TextView) v.findViewById(R.id.tvReview);
            tvAnswer= (TextView) v.findViewById(R.id.tvAnswer);
            lOrder = (StateLinearLayout) v.findViewById(R.id.lOrder);
            lDivider = (View) v.findViewById(R.id.vDivider);
            lReview= (LinearLayout) v.findViewById(R.id.lReview);

        }
    }

    public OrdersAdapter(List<OrderPojo.Datum> dataset) {
        mDataset = dataset;

    }

    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (position == 0) {
            holder.lDivider.setVisibility(View.GONE);
        }
        final OrderPojo.Datum datum = mDataset.get(position);

        if (datum.getOrderStatus().getId().equals("1")) {
            holder.tvOrderStatus.setText("Текущий заказ:");
        } else if (datum.getOrderStatus().getId().equals("2")) {
            holder.tvOrderStatus.setText("Заказ выполнен:");
        } else if (datum.getOrderStatus().getId().equals("3")) {
            holder.tvOrderStatus.setText("Заказ отменен:");
        }

        holder.tvOrderTime.setText(datum.getWashTime().getWashTime());
        holder.tvOderDate.setText("(" + datum.getWashDate() + ")");
        try {

        }catch (Exception e){
            holder.tvOrderCarwashName.setText("«" + datum.getCarWash().getTitle() + "»");

        }
        holder.tvAdress.setText(datum.getCarWash().getAddress());
        holder.tvOrderCost.setText(datum.getCost() + " руб");

        if(datum.getReview()==null){
            holder.lReview.setVisibility(View.GONE);
        }else{
            holder.tvReview.setText("Отзыв: " + datum.getReview().getText());
            holder.tvAnswer.setText("Ответ: " + datum.getReview().getAnswer());
        }

        holder.lOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusProvider.getInstance().post(datum);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}