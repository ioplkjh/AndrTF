package ru.allmoyki.may.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ru.allmoyki.may.R;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.pojo.CurrentCarwashPojo;
import ru.allmoyki.may.utils.ProgressDialogCustom;
import ru.allmoyki.may.widget.state.StateImageButton;


public class CarwashOfflineActivity extends AppCompatActivity {


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

    private String title, lat, lon, phone, id;


    @InjectView(R.id.tvTitleToolBar)
    TextView tvTitleToolBar;


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


    @OnClick(R.id.tvTitleToolBar)
    public void close() {
        finish();
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
        setContentView(R.layout.activity_carwash_offline);

        ButterKnife.inject(this);
        progressDialog = ProgressDialogCustom.getProgressDialog(this);


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


    }


}

