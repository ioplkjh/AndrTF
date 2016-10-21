package ru.allmoyki.may.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import ru.allmoyki.may.R;
import ru.allmoyki.may.pojo.CarwashFeedbacksPojo;

/**
 * Created by Boichuk Dmitriy on 08.09.2015.
 */
public class AdapterFeedbacks extends RecyclerView.Adapter<AdapterFeedbacks.ViewHolder> {
    private CarwashFeedbacksPojo carwashFeedbacksPojo;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFeedback, tvName, tvAnswer;
        public RatingBar ratingBar;
        public LinearLayout lMain;

        public ViewHolder(View v) {
            super(v);
            tvFeedback = (TextView) v.findViewById(R.id.tvFeedback);
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvAnswer = (TextView) v.findViewById(R.id.tvAnswer);
            ratingBar = (RatingBar) v.findViewById(R.id.ratingBarFeedback);
            lMain = (LinearLayout) v.findViewById(R.id.lMain);
        }
    }

    public AdapterFeedbacks(CarwashFeedbacksPojo carwashFeedbacksPojo) {
        this.carwashFeedbacksPojo = carwashFeedbacksPojo;
    }

    @Override
    public AdapterFeedbacks.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_otzyv, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvFeedback.setText(carwashFeedbacksPojo.getData().get(position).getText());
        holder.tvName.setText(carwashFeedbacksPojo.getData().get(position).getClientFio());
        holder.tvAnswer.setText(carwashFeedbacksPojo.getData().get(position).getAnswer());
        holder.ratingBar.setRating(Float.parseFloat(carwashFeedbacksPojo.getData().get(position).getMark()));
        holder.lMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.tvFeedback.getLineCount() > 1) {
                    holder.tvFeedback.setSingleLine(true);
                    holder.tvAnswer.setSingleLine(true);
                    holder.tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.icon_aarrow, 0);
                } else {
                    holder.tvName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.icon_arrow_down, 0);
                    holder.tvFeedback.setSingleLine(false);
                    holder.tvAnswer.setSingleLine(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return carwashFeedbacksPojo.getData().size();
    }
}
