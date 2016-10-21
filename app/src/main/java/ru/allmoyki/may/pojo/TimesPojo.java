package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Boichuk Dmitriy on 30.09.2015.
 */
public class TimesPojo {

    @SerializedName("data")
    @Expose
    private ArrayList<Datum> data = new ArrayList<Datum>();

    /**
     * @return The data
     */
    public ArrayList<Datum> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }


    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("wash_time")
        @Expose
        private String washTime;
        @SerializedName("half_hour")
        @Expose
        private String halfHour;

        /**
         * @return The id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return The washTime
         */
        public String getWashTime() {
            return washTime;
        }

        /**
         * @param washTime The wash_time
         */
        public void setWashTime(String washTime) {
            this.washTime = washTime;
        }

        /**
         * @return The halfHour
         */
        public String getHalfHour() {
            return halfHour;
        }

        /**
         * @param halfHour The half_hour
         */
        public void setHalfHour(String halfHour) {
            this.halfHour = halfHour;
        }

    }
}
