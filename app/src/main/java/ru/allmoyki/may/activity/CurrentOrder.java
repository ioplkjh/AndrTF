package ru.allmoyki.may.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.otto.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.OrderBus;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.CancelOrderPojo;
import ru.allmoyki.may.pojo.CurrentCarwashPojo;
import ru.allmoyki.may.pojo.OrderPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;
import ru.allmoyki.may.widget.state.StateButton;

public class CurrentOrder extends AppCompatActivity {
    @InjectView(R.id.tvTitleToolBar)
    TextView tvTitleToolBar;

    @OnClick(R.id.tvTitleToolBar)
    public void close() {
        finish();
    }

    @InjectView(R.id.tvOrderStatus)
    TextView tvOrderStatus;
    @InjectView(R.id.tvOrderTime)
    TextView tvOrderTime;
    @InjectView(R.id.tvOrderDate)
    TextView tvOrderDate;
    @InjectView(R.id.tvOrderCarwashName)
    TextView tvOrderCarwashName;
    @InjectView(R.id.tvAdress)
    TextView tvOrderAdress;
    @InjectView(R.id.tvOrderCost)
    TextView tvOrderCost;
    @InjectView(R.id.lReview)
    LinearLayout lReview;
    @InjectView(R.id.tvReview)
    TextView tvReview;
    @InjectView(R.id.tvAnswer)
    TextView tvAnswer;
    @InjectView(R.id.lServices)
    LinearLayout lServices;
    @InjectView(R.id.btnSendReview)
    StateButton btnSendReview;
    @InjectView(R.id.btnEndOrder)
    StateButton btnEndOrder;
    private Dialog progressDialog;
    OrderPojo.Datum datum;

    @OnClick(R.id.tvOrderCarwashName)
    public void clickCarWash() {

        HashMap<String, String> hashData = new HashMap<>();
        hashData.put("id", datum.getCarWashId());
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        hashData.put("local_time", date);
        hashData.put("access_token", MyPreferences.getUserPushToken(CurrentOrder.this));

        GsonRequest<CurrentCarwashPojo> getCarwash = Api.getCurrentCarwash(responseSendDataCurrentCheck, errorListener, hashData);
        AppController.getInstance().addToRequestQueue(getCarwash);
        progressDialog.show();
    }

    Response.Listener<CurrentCarwashPojo> responseSendDataCurrentCheck = new Response.Listener<CurrentCarwashPojo>() {
        @Override
        public void onResponse(CurrentCarwashPojo getCurrentCarwashPojo) {
            progressDialog.dismiss();

//            BusProvider.getInstance().post(1);
            BusProvider.getInstance().post(getCurrentCarwashPojo);
            if (getCurrentCarwashPojo.getData().getCarWashType().getId().equals("2")) {
                startActivity(new Intent(CurrentOrder.this, CarwashActivity.class));
            } else {
                startActivity(new Intent(CurrentOrder.this, CarwashOfflineActivity.class));

            }

        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e("Log", "error");
            progressDialog.dismiss();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        ButterKnife.inject(this);
        tvTitleToolBar.setText("Мои заказы");
        progressDialog = ProgressDialogCustom.getProgressDialog(CurrentOrder.this);

    }

    @Subscribe
    public void setData(OrderBus orderBus) {
        datum = orderBus.getDatum();
        btnSendReview.setVisibility(View.GONE);

        if (datum.getOrderStatus().getId().equals("1")) {
            tvOrderStatus.setText("Текущий заказ:");
            btnEndOrder.setVisibility(View.VISIBLE);
            btnSendReview.setVisibility(View.GONE);

        } else if (datum.getOrderStatus().getId().equals("2")) {
            btnEndOrder.setVisibility(View.GONE);
            tvOrderStatus.setText("Заказ выполнен:");
            if (datum.getReview() == null) {
                btnSendReview.setVisibility(View.VISIBLE);
            } else {
                btnSendReview.setVisibility(View.GONE);
            }
        } else if (datum.getOrderStatus().getId().equals("3")) {
            tvOrderStatus.setText("Заказ отменен:");
            btnEndOrder.setVisibility(View.GONE);
            btnSendReview.setVisibility(View.GONE);

        }

        tvOrderTime.setText(datum.getWashTime().getWashTime());
        tvOrderDate.setText("(" + datum.getWashDate() + ")");
        tvOrderCarwashName.setText("«" + datum.getCarWash().getTitle() + "»");
        tvOrderAdress.setText(datum.getCarWash().getAddress());
        tvOrderAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurrentOrder.this, CarwashMapActivity.class);
                intent.putExtra("title", datum.getCarWash().getTitle());
                intent.putExtra("lat", datum.getCarWash().getLatitude());
                intent.putExtra("lon", datum.getCarWash().getLongitude());
                intent.putExtra("how_to_go", datum.getCarWash().getHowToGo());
                startActivity(intent);
            }
        });
        tvOrderCost.setText("Сумма: " + datum.getCost() + " руб");
        for (int i = 0; i < datum.getWashServices().size(); i++) {
            TextView tv = (TextView) LayoutInflater.from(this)
                    .inflate(R.layout.item_service_order, null, true);
            tv.setText("-" + datum.getWashServices().get(i).getService());
            lServices.addView(tv);
        }

        Log.d("Log", "!!!!" + datum.getReview());

        if (datum.getReview() == null) {
            lReview.setVisibility(View.GONE);
        } else {
            lReview.setVisibility(View.VISIBLE);
            tvReview.setText("Отзыв: " + datum.getReview().getText());
            tvAnswer.setText("Ответ: " + datum.getReview().getAnswer());
            lReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tvReview.getLineCount() > 1 || tvAnswer.getLineCount() > 1) {
                        tvReview.setSingleLine(true);
                        tvAnswer.setSingleLine(true);
                    } else {
                        tvReview.setSingleLine(false);
                        tvAnswer.setSingleLine(false);
                    }
                }
            });
        }

        btnSendReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(datum);
            }
        });

        btnEndOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> hashData = new HashMap<String, String>();
                hashData.put("access_token", MyPreferences.getUserPushToken(CurrentOrder.this));
                hashData.put("id", datum.getId());
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(CurrentOrder.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                };
                Response.Listener<CancelOrderPojo> ordersListener = new Response.Listener<CancelOrderPojo>() {
                    @Override
                    public void onResponse(CancelOrderPojo cancelOrderPojo) {
                        progressDialog.dismiss();
                        Toast.makeText(CurrentOrder.this, "Заказ отменен", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                };

                progressDialog.show();
                GsonRequest<CancelOrderPojo> cancelOrder = Api.cancelOrder(ordersListener, errorListener, hashData);
                AppController.getInstance().addToRequestQueue(cancelOrder);
            }
        });

    }

    String mark = "0";
    String review = "";

    private void showDialog(final OrderPojo.Datum datum) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_carwash_review);
        review = "";
        final EditText eReview = (EditText) dialog.findViewById(R.id.eReview);
        final RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);
        TextView tvDisimis = (TextView) dialog.findViewById(R.id.btnCancelNumver);
        TextView tvApply = (TextView) dialog.findViewById(R.id.btnApplyNumber);
        mark = "0";
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mark = rating + "";
            }
        });
        tvDisimis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        tvApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                progressDialog.show();
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("access_token", MyPreferences.getUserPushToken(CurrentOrder.this));
                hashMap.put("car_wash_id", datum.getCarWash().getId());
                hashMap.put("client_order_id", datum.getId());
                hashMap.put("mark", mark);
                hashMap.put("text", eReview.getText().toString());
                Log.d("Log", "response: " + hashMap);
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(CurrentOrder.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                };
                Response.Listener<CancelOrderPojo> ordersListener = new Response.Listener<CancelOrderPojo>() {
                    @Override
                    public void onResponse(CancelOrderPojo cancelOrderPojo) {
                        progressDialog.dismiss();
                        Toast.makeText(CurrentOrder.this, "Отзыв добавлен", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                };

                progressDialog.show();
                GsonRequest<CancelOrderPojo> addReview = Api.addReview(ordersListener, errorListener, hashMap);
                AppController.getInstance().addToRequestQueue(addReview);
//                /CarWashReview/Add, принимает: access_token, car_wash_id, client_order_id, mark, text, возвращает добавленную модель CarWashReview

            }
        });

        dialog.show();
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
