package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;

/**
 * Created by Boichuk Dmitriy on 15.09.2015.
 */
public class CheckSMSPojo {
    @Expose
    private String data;

    /**
     *
     * @return
     * The data
     */
    public String getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(String data) {
        this.data = data;
    }

}
