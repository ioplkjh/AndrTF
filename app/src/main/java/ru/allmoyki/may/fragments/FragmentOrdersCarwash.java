package ru.allmoyki.may.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.otto.Subscribe;

import java.util.HashMap;

import ru.allmoyki.may.AppController;
import ru.allmoyki.may.R;
import ru.allmoyki.may.activity.CurrentOrder;
import ru.allmoyki.may.adapters.OrdersAdapter;
import ru.allmoyki.may.bus.BusProvider;
import ru.allmoyki.may.bus.OrderBus;
import ru.allmoyki.may.network.Api;
import ru.allmoyki.may.network.GsonRequest;
import ru.allmoyki.may.pojo.OrderPojo;
import ru.allmoyki.may.preferences.MyPreferences;
import ru.allmoyki.may.utils.ProgressDialogCustom;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOrdersCarwash extends Fragment {


    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "Ошибка", Toast.LENGTH_SHORT).show();
        }
    };
    private Response.Listener<OrderPojo> ordersListener = new Response.Listener<OrderPojo>() {
        @Override
        public void onResponse(OrderPojo orderPojo) {

            OrdersAdapter ordersAdapter = new OrdersAdapter(orderPojo.getData());
            mRecyclerView.setAdapter(ordersAdapter);
            progressDialog.dismiss();
        }
    };

    public FragmentOrdersCarwash() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Dialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_orders_carwash, container, false);

        progressDialog = ProgressDialogCustom.getProgressDialog(getActivity());
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_orders);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("access_token", MyPreferences.getUserPushToken(getActivity()));
        progressDialog.show();
        GsonRequest<OrderPojo> orders = Api.orders(ordersListener, errorListener, hashMap);
        AppController.getInstance().addToRequestQueue(orders);
    }

    @Subscribe
    public void getItem(OrderPojo.Datum datum) {
        OrderBus orderBus = new OrderBus();
        orderBus.setDatum(datum);
        startActivity(new Intent(getActivity(), CurrentOrder.class));
        BusProvider.getInstance().post(orderBus);
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
