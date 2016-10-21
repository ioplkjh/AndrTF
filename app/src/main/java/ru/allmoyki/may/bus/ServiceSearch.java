package ru.allmoyki.may.bus;

/**
 * Created by Boichuk Dmitriy on 01.10.2015.
 */
public class ServiceSearch {
    private String serviceName;
    private String serviceId;
    private boolean value;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
