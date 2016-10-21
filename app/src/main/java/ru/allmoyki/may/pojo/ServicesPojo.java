package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Boichuk Dmitriy on 01.10.2015.
 */
public class ServicesPojo {

    @SerializedName("data")
    @Expose
    private ArrayList<Datum> data = new ArrayList<Datum>();

    /**
     *
     * @return
     * The data
     */
    public ArrayList<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public class CarCategory {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("category")
        @Expose
        private String category;

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
         * The category
         */
        public String getCategory() {
            return category;
        }

        /**
         *
         * @param category
         * The category
         */
        public void setCategory(String category) {
            this.category = category;
        }

    }
    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("service")
        @Expose
        private String service;
        @SerializedName("car_category_id")
        @Expose
        private String carCategoryId;
        @SerializedName("car_category")
        @Expose
        private CarCategory carCategory;

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
         * The service
         */
        public String getService() {
            return service;
        }

        /**
         *
         * @param service
         * The service
         */
        public void setService(String service) {
            this.service = service;
        }

        /**
         *
         * @return
         * The carCategoryId
         */
        public String getCarCategoryId() {
            return carCategoryId;
        }

        /**
         *
         * @param carCategoryId
         * The car_category_id
         */
        public void setCarCategoryId(String carCategoryId) {
            this.carCategoryId = carCategoryId;
        }

        /**
         *
         * @return
         * The carCategory
         */
        public CarCategory getCarCategory() {
            return carCategory;
        }

        /**
         *
         * @param carCategory
         * The car_category
         */
        public void setCarCategory(CarCategory carCategory) {
            this.carCategory = carCategory;
        }

    }
}
