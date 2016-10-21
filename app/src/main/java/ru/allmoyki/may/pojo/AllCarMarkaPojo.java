package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public class AllCarMarkaPojo {

    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    /**
     *
     * @return
     * The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }



    public class Datum {

        @Expose
        private String id;
        @Expose
        private String brand;
        @SerializedName("car_models_count")
        @Expose
        private String carModelsCount;

        /**
         *
         * @return
         * The id
         */
        public String getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The brand
         */
        public String getBrand() {
            return brand;
        }

        /**
         *
         * @param brand
         * The brand
         */
        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getCarModelsCount() {
            return carModelsCount;
        }

        public void setCarModelsCount(String carModelsCount) {
            this.carModelsCount = carModelsCount;
        }
    }
}
