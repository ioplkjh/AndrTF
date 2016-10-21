package ru.allmoyki.may.bus;

import java.util.List;

import ru.allmoyki.may.pojo.CurrentCarwashPojo;

/**
 * Created by Boichuk Dmitriy on 17.09.2015.
 */
public class ServiceListSelected {
   private static List<CurrentCarwashPojo.WashService> washServices= null;

    public static List<CurrentCarwashPojo.WashService> getWashServiceList() {
        return washServices;
    }

    public static void setWashServiceList(List<CurrentCarwashPojo.WashService> washServiceList) {
         washServices = washServiceList;
    }
}
