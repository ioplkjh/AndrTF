package ru.allmoyki.may.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.fragments.FragmentMap;
import ru.allmoyki.may.fragments.FragmentOrdersCarwash;
import ru.allmoyki.may.fragments.FragmentSearchCarwash;
import ru.allmoyki.may.fragments.FragmentSettingsCarwash;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.CurrentUserPojo;
import ru.allmoyki.may.preferences.MyPreferences;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private RelativeLayout lDrawerContainer;
    private ImageButton btnStateDrawer;
    private TextView tvTitleToolBar;
    private String[] navMenuTitles;
    private LinearLayout[] lDraverItem = new LinearLayout[5];
    private Fragment fragment = null;
    private FragmentMap fragmentMap = null;
    FrameLayout frameLayout, frameLayoutMap;


    int position = 0;
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {

        }
    };
    private Response.Listener<CurrentUserPojo> clientListener = new Response.Listener<CurrentUserPojo>() {
        @Override
        public void onResponse(CurrentUserPojo client) {
            Log.d("Log", "onResponse: " + client.getData().getFio());
            MyPreferences.setUser(MainActivity.this, client.getData().getFio());
            MyPreferences.setRegion(MainActivity.this, client.getData().getRegionId());

        }
    };

    private String getCertificateSHA1Fingerprint() {
        PackageManager pm = getPackageManager();
        String packageName = getPackageName();
        int flags = PackageManager.GET_SIGNATURES;
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(packageName, flags);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Signature[] signatures = packageInfo.signatures;
        byte[] cert = signatures[0].toByteArray();
        InputStream input = new ByteArrayInputStream(cert);
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        X509Certificate c = null;
        try {
            c = (X509Certificate) cf.generateCertificate(input);
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        String hexString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(c.getEncoded());
            hexString = byte2HexFormatted(publicKey);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
        return hexString;
    }

    public static String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(arr.length * 2);
        for (int i = 0; i < arr.length; i++) {
            String h = Integer.toHexString(arr[i]);
            int l = h.length();
            if (l == 1) h = "0" + h;
            if (l > 2) h = h.substring(l - 2, l);
            str.append(h.toUpperCase());
            if (i < (arr.length - 1)) str.append(':');
        }
        return str.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);



        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("access_token", MyPreferences.getUserPushToken(this));

        GsonRequest<CurrentUserPojo> clientn = Api.client(clientListener, errorListener, hashMap);
        AppController.getInstance().addToRequestQueue(clientn);

        frameLayout = (FrameLayout) findViewById(R.id.frame_container);
        frameLayoutMap = (FrameLayout) findViewById(R.id.frame_container_map);
        Log.e("Log", "hash: " + getCertificateSHA1Fingerprint());

        PackageInfo info;
        try {

            info = getPackageManager().getPackageInfo(
                    "ru.allmoyki", PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
                System.out.println("Hash key" + something);
            }

        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }


        lDrawerContainer = (RelativeLayout) findViewById(R.id.list_slidermenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        lDraverItem[0] = (LinearLayout) findViewById(R.id.lMap);
        lDraverItem[1] = (LinearLayout) findViewById(R.id.lSearch);
        lDraverItem[2] = (LinearLayout) findViewById(R.id.lOrders);
        lDraverItem[3] = (LinearLayout) findViewById(R.id.lSettings);
        lDraverItem[4] = (LinearLayout) findViewById(R.id.lEnty);

        lDraverItem[0].setBackgroundResource(R.drawable.list_item_bg_pressed);

        for (int i = 0; i < lDraverItem.length; i++) {
            lDraverItem[i].setOnClickListener(listenerClickDrawerItem);
        }


        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items_entry);

        btnStateDrawer = (ImageButton) findViewById(R.id.btnStateDrawer);
        tvTitleToolBar = (TextView) findViewById(R.id.tvTitleToolBar);
        btnStateDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });
        tvTitleToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backState();
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.mipmap.ic_launcher, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();

                InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        tvTitleToolBar.setText(navMenuTitles[0]);
        if (savedInstanceState == null) {
            displayView(0);
        }
    }

    View.OnClickListener listenerClickDrawerItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (LinearLayout layout : lDraverItem) {
                layout.setBackgroundResource(R.drawable.list_item_bg_normal);
            }
            v.setBackgroundResource(R.drawable.list_item_bg_pressed);
            int pos = 0;
            switch (v.getId()) {
                case R.id.lMap:
                    pos = 0;
                    break;
                case R.id.lSearch:
                    pos = 1;
                    break;
                case R.id.lOrders:
                    pos = 2;
                    break;
                case R.id.lSettings:
                    pos = 3;
                    break;
                case R.id.lEnty:
                    MyPreferences.seUserPushToken(MainActivity.this, null);
                    startActivity(new Intent(MainActivity.this, CarWashLoginActivity.class));
                    finish();
                    pos = 4;
                    break;

            }
            changeFragment(pos);
        }
    };

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        backState();

    }

    private void backState() {
        if (position == 0) {
            finish();
        } else {
            displayView(0);
            for (LinearLayout layout : lDraverItem) {
                layout.setBackgroundResource(R.drawable.list_item_bg_normal);
            }
            lDraverItem[0].setBackgroundResource(R.drawable.list_item_bg_pressed);

        }
    }





    public void changeFragment(Integer position) {

        displayView(position);

    }


    void displayView(int position) {
        this.position = position;
        FragmentManager fragmentManager = getSupportFragmentManager();

        try {
            switch (position) {
                case 0:
                    if (fragmentMap == null) {
                        fragmentMap = new FragmentMap();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_container_map, fragmentMap).commit();
                    }
                    break;
                case 1:
                    fragment = new FragmentSearchCarwash();
                    break;
                case 2:
                    fragment = new FragmentOrdersCarwash();
                    break;
                case 3:
                    fragment = new FragmentSettingsCarwash();
                    break;
                default:
                    break;
            }


            if (position == 0) {
                if (frameLayoutMap.getVisibility() != View.VISIBLE) {
                    frameLayoutMap.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.GONE);
                }
            } else {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment).commit();
                frameLayoutMap.setVisibility(View.GONE);
                frameLayout.setVisibility(View.VISIBLE);
            }
//            fragmentManager.executePendingTransactions();

            tvTitleToolBar.setText(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(lDrawerContainer);
        } catch (Exception e) {

        }
    }

}
