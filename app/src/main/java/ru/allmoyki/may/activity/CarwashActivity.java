package ru.allmoyki.may.activity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.otto.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.ServiceList;
import ru.allmoyki.may.bus.ServiceListSelected;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.AddOrderPojo;
import ru.allmoyki.may.pojo.CurrentCarwashPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;
import ru.allmoyki.may.widget.state.StateImageButton;
import ru.allmoyki.may.widget.utils.AlarmReceiver;


public class CarwashActivity extends AppCompatActivity {


    @InjectView(R.id.tvCarMarkaModel)
    TextView tvCarMarkaModel;
    @InjectView(R.id.tvCarNumber)
    TextView tvCarNumber;
    @InjectView(R.id.tvTimeOpen)
    TextView tvTimeOpen;
    @InjectView(R.id.tvAdress)
    TextView tvAdress;
    @InjectView(R.id.ratingBar)
    RatingBar ratingBar;
    @InjectView(R.id.imgWiFi)
    ImageView imgWiFi;
    @InjectView(R.id.imgCoffee)
    ImageView imgCoffee;
    @InjectView(R.id.imgCard)
    ImageView imgCard;
    @InjectView(R.id.imgDivan)
    ImageView imgDivan;
    @InjectView(R.id.imgWC)
    ImageView imgWC;
    @InjectView(R.id.btnMap)
    StateImageButton btnMap;
    @InjectView(R.id.btnNavigator)
    StateImageButton btnNavigator;
    @InjectView(R.id.btnCall)
    StateImageButton btnCall;
    @InjectView(R.id.btnOtzyv)
    StateImageButton btnOtzyv;
    @InjectView(R.id.tvPrice)
    TextView tvPrice;
    @InjectView(R.id.tvTime)
    TextView tvTime;
    android.support.v7.widget.SwitchCompat sUpdateApp;
    private String title, lat, lon, phone, id;

    @InjectView(R.id.rToday)
    RadioButton rToday;
    @InjectView(R.id.rYesterday)
    RadioButton rYesterday;
    @InjectView(R.id.rTomorrow)
    RadioButton rTomorrow;
    @InjectView(R.id.rGDate)
    RadioGroup rGDate;
    @InjectView(R.id.rGTime)
    RadioGroup rGTime;

    @InjectView(R.id.tvTitleToolBar)
    TextView tvTitleToolBar;
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            progressDialog.dismiss();
            Log.e("Log", "error: " + volleyError.toString());
            Toast.makeText(CarwashActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
        }
    };
    private Response.Listener<AddOrderPojo> addOrderListener = new Response.Listener<AddOrderPojo>() {
        @Override
        public void onResponse(AddOrderPojo addOrderPojo) {
            progressDialog.dismiss();
            Log.d("Log", "status:" + addOrderPojo.getData().getOrderStatusId() + ":");
            try {
                if (addOrderPojo.getData().getOrderStatusId().equals("-1")) {
                    Toast.makeText(CarwashActivity.this, "Заказ добавлен", Toast.LENGTH_SHORT).show();
                    if (sUpdateApp.isChecked()) {
                        setTimer();
                    }
                    finish();
                } else if (addOrderPojo.getData().getOrderStatusId().equals("5")) {
                    Toast.makeText(CarwashActivity.this, "Нельзя записать на это время", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CarwashActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();

                }
            } catch (Exception e) {
                Toast.makeText(CarwashActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();

            }

        }
    };

    private void setTimer() {
//        calendar.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.SUNDAY, 8, 00, 00);
//
//        calendar.set(2015, 5, 1, 19, 55, 00);
//
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.add(Calendar.DAY_OF_MONTH, 5);


        Calendar cal = Calendar.getInstance();
        Log.d("Log", "dayId " + dayId);
        switch (dayId) {
            case "1":
                break;
            case "2":
                cal.add(Calendar.DATE, 1);
                break;
            case "3":
                cal.add(Calendar.DATE, 2);
                break;
        }


        try {
            String yourDateString =
                    cal.get(Calendar.MONTH) + 1 + "-" +
                            cal.get(Calendar.DAY_OF_MONTH) + "-" +
                            cal.get(Calendar.YEAR) + " " + timeText;
            SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy HH:mm");
            Date yourDate = sd.parse(yourDateString);
            Log.d("Log", "yourDateString " + yourDateString);
            Log.d("Log", "yourDate " + yourDate.toString());
            cal.setTime(yourDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long when = cal.getTimeInMillis() - (30 * 60000);
        Log.d("Log", when + " ");
        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, when, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }


    @OnClick(R.id.lModelCar)
    public void openCarModel() {
        Intent intent = new Intent(this, CarwashCarModelActivity.class);
        startActivityForResult(intent, 1);
    }

    private String dayId = "", timeId = "", timeText = "";
    private String carMarkaId = "", carModelId = "", carNumber = "", carCategoryId = "";
    private ServiceListSelected selectedServiceList;

    @OnClick(R.id.btnAddOrder)
    public void addOrder() {
        if (validateData() == false) {
            Toast.makeText(CarwashActivity.this, "Заполните все данные", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, String> orderData = new HashMap<>();
            orderData.put("car_wash_id", currentCarwashPojo.getData().getId());
            orderData.put("day_id", dayId);
            orderData.put("time_id", timeId);
            orderData.put("car_id", carMarkaId);
            orderData.put("model_id", carModelId);
            orderData.put("car_number", carNumber);
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            orderData.put("local_time", date);
            orderData.put("access_token", MyPreferences.getUserPushToken(CarwashActivity.this));
            orderData.put("order_source_id", "1");
            String services = "[";
            for (CurrentCarwashPojo.WashService washService : selectedServiceList.getWashServiceList()) {
                services += washService.getId() + ",";
            }
            services = services.substring(0, services.length() - 1);
            services += "]";
            orderData.put("services", services);
            Log.d("Log", "data: " + services);
            Log.d("Log", "data: " + orderData.toString());

            GsonRequest<AddOrderPojo> addOrder = Api.addOrder(addOrderListener, errorListener, orderData);
            AppController.getInstance().addToRequestQueue(addOrder);
            progressDialog.show();
        }


    }

    private boolean validateData() {
        boolean val = true;
        if (dayId.length() == 0 || timeId.length() == 0 || carMarkaId.length() == 0 ||
                carModelId.length() == 0 || selectedServiceList.getWashServiceList() == null) {
            val = false;
        }
        try {
            if (selectedServiceList.getWashServiceList().size() == 0) {
                val = false;
            }
        } catch (Exception e) {
            val = false;
        }

        return val;
    }

    @OnClick(R.id.btnMap)
    public void openMap() {

        Log.d("Log", title);
        Intent intent = new Intent(this, CarwashMapActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("lat", lat);
        intent.putExtra("lon", lon);
        intent.putExtra("how_to_go", currentCarwashPojo.getData().getHowToGo());
        startActivity(intent);
    }

    @OnClick(R.id.btnCall)
    public void openCall() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_custom_call);
        TextView tvPhoneNumber = (TextView) dialog.findViewById(R.id.tvPhoneNumber);
        tvPhoneNumber.setText(phone);

        dialog.findViewById(R.id.btnCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
                dialog.dismiss();

            }
        });

        dialog.findViewById(R.id.btnDismis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    @OnClick(R.id.btnOtzyv)
    public void openOtzyv() {
        Intent intent = new Intent(this, CarwashOtzyvActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @OnClick(R.id.tvTitleToolBar)
    public void close() {
        finish();
    }

    @OnClick(R.id.btn_service)
    public void getServices() {

        if (carCategoryId.length() < 1) {
            Toast.makeText(CarwashActivity.this, "Сначала выберите машину", Toast.LENGTH_SHORT).show();
        } else {
            List<CurrentCarwashPojo.WashService> selectList = new ArrayList<>();
            for (CurrentCarwashPojo.WashService serv : currentCarwashPojo.getData().getWashServices()) {
                if (serv.getCar_category_id().equals(carCategoryId)) {
                    selectList.add(serv);
                }
            }
            ServiceList serviceList = new ServiceList();
            serviceList.setWashServiceList(selectList);

            Intent intent = new Intent(this, CarwashServicesActivity.class);
            intent.putExtra("title", title);
            startActivity(intent);


            BusProvider.getInstance().post(serviceList);
        }

    }

    @Subscribe
    public void setListServices(ServiceListSelected serviceList) {
        selectedServiceList = serviceList;
        int price = 0;
        int time = 0;
        for (CurrentCarwashPojo.WashService washService : serviceList.getWashServiceList()) {
            price += Integer.parseInt(washService.getCost());
            time += Integer.parseInt(washService.getTime());
        }
        tvPrice.setText("Стоимость: " + price + " руб.");
        tvTime.setText("Длительность: " + time + " мин.");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        carMarkaId = data.getStringExtra("markaId");
        carCategoryId = data.getStringExtra("carCategoryId");
        carModelId = data.getStringExtra("modelId");
        carNumber = data.getStringExtra("number");

        String marka = data.getStringExtra("marka");
        String model = data.getStringExtra("model");
        String number = data.getStringExtra("number");
        tvCarMarkaModel.setText(marka + " " + model);
        tvCarNumber.setText(number);
    }

    @OnClick(R.id.btnNavigator)
    public void sendNavigator() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_custom_navigator);
        dialog.findViewById(R.id.tvYandex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("ru.yandex.yandexnavi.action.BUILD_ROUTE_ON_MAP");
                intent.setPackage("ru.yandex.yandexnavi");
                PackageManager pm = getPackageManager();
                List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
                if (infos == null || infos.size() == 0) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=ru.yandex.yandexnavi"));
                } else {
                    intent.putExtra("lat_to", Double.parseDouble(lat));
                    intent.putExtra("lon_to", Double.parseDouble(lon));
                }
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tvStandart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?   saddr=" + Double.parseDouble(lat) +
                                "," + Double.parseDouble(lon) + "&daddr=" + Double.parseDouble(lat) + "," +
                                Double.parseDouble(lon))
                );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.BOTTOM;

        dialog.show();
    }


    private CurrentCarwashPojo currentCarwashPojo;
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carwash);

        ButterKnife.inject(this);

        sUpdateApp = (android.support.v7.widget.SwitchCompat) findViewById(R.id.sUpdateApp);

        progressDialog = ProgressDialogCustom.getProgressDialog(this);
        rGDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                timeId = "";
                switch (id) {
                    case R.id.rToday:
                        dayId = "1";
                        setTimeRadio(0);
                        break;
                    case R.id.rTomorrow:
                        dayId = "2";
                        setTimeRadio(1);
                        break;
                    case R.id.rYesterday:
                        dayId = "3";
                        setTimeRadio(2);
                        break;
                }
            }
        });

        rGTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton r = (RadioButton) radioGroup.findViewById(i);
                Log.d("Log", "zzz " + r.getTag());
                timeId = r.getTag().toString();
                timeText = r.getText().toString();

            }
        });


    }

    private void setTimeRadio(int type) {
        rGTime.removeAllViews();

        if (type == 0) {

            for (CurrentCarwashPojo.Today time : currentCarwashPojo.getData().getWashTimes().getToday()) {

                LinearLayout lMargin = (LinearLayout) LayoutInflater.from(this)
                        .inflate(R.layout.item_margin, null, true);
                RadioButton rb = (RadioButton) LayoutInflater.from(this)
                        .inflate(R.layout.item_time_radiobutton, null, true);
                rb.setTag(time.getId());
                rb.setText(time.getTime());
                if (time.getOpen().equals("0")) {
                    rb.setEnabled(false);
                } else {
                    Calendar cal = Calendar.getInstance();

                    try {


                        String yourDateString =
                                cal.get(Calendar.MONTH) + 1 + "-" +
                                        cal.get(Calendar.DAY_OF_MONTH) + "-" +
                                        cal.get(Calendar.YEAR) + " " + time.getTime();
                        SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy HH:mm");
                        Date yourDate = sd.parse(yourDateString);
                        Date currentDate = Calendar.getInstance().getTime();

                        if (currentDate.getTime() > yourDate.getTime()) {
                            rb.setEnabled(false);
                        } else {
                            rb.setEnabled(true);
                        }
                    } catch (Exception e) {

                    }

                }

                rGTime.addView(lMargin);
                rGTime.addView(rb);
            }
        } else if (type == 1) {

            for (CurrentCarwashPojo.Tomorrow time : currentCarwashPojo.getData().getWashTimes().getTomorrow()) {

                LinearLayout lMargin = (LinearLayout) LayoutInflater.from(this)
                        .inflate(R.layout.item_margin, null, true);
                RadioButton rb = (RadioButton) LayoutInflater.from(this)
                        .inflate(R.layout.item_time_radiobutton, null, true);
                rb.setTag(time.getId());
                rb.setText(time.getTime());
                if (time.getOpen().equals("0")) {
                    rb.setEnabled(false);
                } else {
                    rb.setEnabled(true);
                }
                rGTime.addView(lMargin);
                rGTime.addView(rb);
            }
        } else if (type == 2) {
            for (CurrentCarwashPojo.Yesterday time : currentCarwashPojo.getData().getWashTimes().getYesterday()) {

                LinearLayout lMargin = (LinearLayout) LayoutInflater.from(this)
                        .inflate(R.layout.item_margin, null, true);
                RadioButton rb = (RadioButton) LayoutInflater.from(this)
                        .inflate(R.layout.item_time_radiobutton, null, true);
                rb.setTag(time.getId());
                rb.setText(time.getTime());
                if (time.getOpen().equals("0")) {
                    rb.setEnabled(false);
                } else {
                    rb.setEnabled(true);
                }
                rGTime.addView(lMargin);
                rGTime.addView(rb);
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        BusProvider.getInstance().unregister(this);

    }

    @Subscribe
    public void displayCarwash(CurrentCarwashPojo currentCarwashPojo) {
        this.currentCarwashPojo = currentCarwashPojo;
        title = currentCarwashPojo.getData().getTitle();
        lat = currentCarwashPojo.getData().getLatitude();
        lon = currentCarwashPojo.getData().getLongitude();
        phone = currentCarwashPojo.getData().getPhone();
        id = currentCarwashPojo.getData().getId();
        tvTitleToolBar.setText(currentCarwashPojo.getData().getTitle());
        tvTimeOpen.setText(currentCarwashPojo.getData().getWorkTime().getStartWashTime().getWashTime() + " - " + currentCarwashPojo.getData().getWorkTime().getEndWashTime().getWashTime());
        tvAdress.setText(currentCarwashPojo.getData().getAddress());

        for (CurrentCarwashPojo.AdditionalService additionalService : currentCarwashPojo.getData().getAdditionalServices()) {
            switch (additionalService.getId()) {
                case "1":
                    if (additionalService.getExists().equals("1")) {
                        imgWC.setImageResource(R.mipmap.uslugi_icon_wc_active);
                    } else {
                        imgWC.setImageResource(R.mipmap.uslugi_icon_wc);
                    }
                    break;
                case "2":
                    if (additionalService.getExists().equals("1")) {
                        imgWiFi.setImageResource(R.mipmap.uslugi_icon_wifi_active);
                    } else {
                        imgWiFi.setImageResource(R.mipmap.uslugi_icon_wifi);
                    }
                    break;
                case "3":
                    if (additionalService.getExists().equals("1")) {
                        imgCoffee.setImageResource(R.mipmap.uslugi_icon_coffee_active);
                    } else {
                        imgCoffee.setImageResource(R.mipmap.uslugi_icon_coffee);
                    }
                    break;
                case "4":
                    if (additionalService.getExists().equals("1")) {
                        imgCard.setImageResource(R.mipmap.uslugi_icon_payment_active);
                    } else {
                        imgCard.setImageResource(R.mipmap.uslugi_icon_payment);
                    }
                    break;
                case "5":
                    if (additionalService.getExists().equals("1")) {
                        imgDivan.setImageResource(R.mipmap.uslugi_icon_comfort_active);
                    } else {
                        imgDivan.setImageResource(R.mipmap.uslugi_icon_comfort);
                    }
                    break;
            }
        }
        ratingBar.setRating(Float.parseFloat(currentCarwashPojo.getData().getRating()));
        rToday.setChecked(true);


    }


}

