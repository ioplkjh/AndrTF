package ru.allmoyki.may.bus;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import ru.allmoyki.may.pojo.CurrentCarwashPojo;
import ru.allmoyki.may.pojo.SearchPojo;
import ru.allmoyki.may.pojo.UserPojo;


/**
 * BusProvider.
 */

/**
 * BusProvider.
 */
public final class BusProvider {

    private static BusProvider sBusProvider;
    private Bus bus;


    private CurrentCarwashPojo currentCarwashPojo;
    private UserPojo userPojo;
    private ServiceList serviceList;
    private ServiceListSelected serviceListSelected;
    private SearchCar searchCar;
    private ServiceSearch serviceSearch;
    private ServiceSearchProduce serviceSearchProduce;
    private SearchPojo searchPojo;
    private SearchProduce searchProduce;
    private OrderBus orderDatum;

    public static BusProvider getInstance() {
        if (sBusProvider == null) {
            sBusProvider = new BusProvider();
        }
        return sBusProvider;
    }

    private BusProvider() {
        bus = new Bus();
        bus.register(this);
    }

    public void register(Object object) {
        bus.register(object);
    }

    public void unregister(Object object) {
        bus.unregister(object);
    }

    public void post(Object object) {
        if (object != null) {
            bus.post(object);
        } else {
            Log.d(BusProvider.class.getSimpleName(), "object == null and not post");
        }
    }

    @Produce
    public CurrentCarwashPojo getCurrentCarwashPojo() {
        return currentCarwashPojo;
    }

    @Subscribe
    public void setCurrentCarwashPojo(CurrentCarwashPojo currentCarwashPojo) {
        this.currentCarwashPojo = currentCarwashPojo;
    }

    @Produce
    public UserPojo getUserPojo() {
        return userPojo;
    }

    @Subscribe
    public void setUserPojo(UserPojo userPojo) {
        this.userPojo = userPojo;
    }

    @Produce
    public ServiceList getServiceList() {
        return serviceList;
    }

    @Subscribe
    public void setServiceList(ServiceList serviceList) {
        this.serviceList = serviceList;
    }

    @Produce
    public ServiceListSelected getServiceListSelected() {
        return serviceListSelected;
    }

    @Subscribe
    public void setServiceListSelected(ServiceListSelected serviceListSelected) {
        this.serviceListSelected = serviceListSelected;
    }

    @Produce
    public SearchCar getSearchCar() {
        return searchCar;
    }

    @Subscribe
    public void setSearchCar(SearchCar searchCar) {
        this.searchCar = searchCar;
    }

    @Produce
    public ServiceSearch getServiceSearch() {
        return serviceSearch;
    }

    @Subscribe
    public void setServiceSearch(ServiceSearch serviceSearch) {
        this.serviceSearch = serviceSearch;
    }

    @Produce
    public ServiceSearchProduce getServiceSearchProduce() {
        return serviceSearchProduce;
    }

    @Subscribe
    public void setServiceSearchProduce(ServiceSearchProduce serviceSearchProduce) {
        this.serviceSearchProduce = serviceSearchProduce;
    }

    @Produce
    public SearchPojo getSearchPojo() {
        return searchPojo;
    }

    @Subscribe
    public void setSearchPojo(SearchPojo searchPojo) {
        this.searchPojo = searchPojo;
    }

    @Produce
    public SearchProduce getSearchProduce() {
        return searchProduce;
    }

    @Subscribe
    public void setSearchProduce(SearchProduce searchProduce) {
        this.searchProduce = searchProduce;
    }

    @Produce
    public OrderBus getOrderDatum() {
        return orderDatum;
    }

    @Subscribe
    public void setOrderDatum(OrderBus orderDatum) {
        this.orderDatum = orderDatum;
    }
}