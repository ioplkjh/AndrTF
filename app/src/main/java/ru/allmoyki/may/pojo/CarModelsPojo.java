package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boichuk Dmitriy on 09.09.2015.
 */
public class CarModelsPojo {

    @Expose
    private List<Datum> data = new ArrayList<Datum>();

    /**
     * @return The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }


    public class Datum {

        @Expose
        private String id;
        @SerializedName("car_brand_id")
        @Expose
        private String carBrandId;
        @Expose
        private String model;
        @SerializedName("car_category_id")
        @Expose
        private String carCategoryId;
        @SerializedName("car_brand")
        @Expose
        private CarBrand carBrand;

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
         * @return The carBrandId
         */
        public String getCarBrandId() {
            return carBrandId;
        }

        /**
         * @param carBrandId The car_brand_id
         */
        public void setCarBrandId(String carBrandId) {
            this.carBrandId = carBrandId;
        }

        /**
         * @return The model
         */
        public String getModel() {
            return model;
        }

        /**
         * @param model The model
         */
        public void setModel(String model) {
            this.model = model;
        }

        /**
         * @return The carCategoryId
         */
        public String getCarCategoryId() {
            return carCategoryId;
        }

        /**
         * @param carCategoryId The car_category_id
         */
        public void setCarCategoryId(String carCategoryId) {
            this.carCategoryId = carCategoryId;
        }

        /**
         * @return The carBrand
         */
        public CarBrand getCarBrand() {
            return carBrand;
        }

        /**
         * @param carBrand The car_brand
         */
        public void setCarBrand(CarBrand carBrand) {
            this.carBrand = carBrand;
        }
    }

    public class CarBrand {

        @Expose
        private String id;
        @Expose
        private String brand;

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
         * @return The brand
         */
        public String getBrand() {
            return brand;
        }

        /**
         * @param brand The brand
         */
        public void setBrand(String brand) {
            this.brand = brand;
        }

    }
}
