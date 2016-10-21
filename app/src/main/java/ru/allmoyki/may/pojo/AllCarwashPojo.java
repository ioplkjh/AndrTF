package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boichuk Dmitriy on 30.08.2015.
 */
public class AllCarwashPojo {

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
        private String phone;
        @Expose
        private String address;
        @Expose
        private String email;
        @Expose
        private String password;
        @Expose
        private String latitude;
        @Expose
        private String longitude;
        @SerializedName("posts_count")
        @Expose
        private String postsCount;
        @SerializedName("car_wash_status_id")
        @Expose
        private String carWashStatusId;
        @SerializedName("car_wash_type_id")
        @Expose
        private String carWashTypeId;
        @Expose
        private String title;
        @SerializedName("wash_services")
        @Expose
        private List<WashService> washServices = new ArrayList<WashService>();
       @SerializedName("car_wash_type")
       @Expose
       private CarWashType carWashType;

       /**
        *
        * @return
        * The carWashType
        */
       public CarWashType getCarWashType() {
           return carWashType;
       }

       /**
        *
        * @param carWashType
        * The car_wash_type
        */
       public void setCarWashType(CarWashType carWashType) {
           this.carWashType = carWashType;
       }

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
         * The phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         *
         * @param phone
         * The phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         *
         * @return
         * The address
         */
        public String getAddress() {
            return address;
        }

        /**
         *
         * @param address
         * The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         *
         * @return
         * The email
         */
        public String getEmail() {
            return email;
        }

        /**
         *
         * @param email
         * The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         *
         * @return
         * The password
         */
        public String getPassword() {
            return password;
        }

        /**
         *
         * @param password
         * The password
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         *
         * @return
         * The latitude
         */
        public String getLatitude() {
            return latitude;
        }

        /**
         *
         * @param latitude
         * The latitude
         */
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        /**
         *
         * @return
         * The longitude
         */
        public String getLongitude() {
            return longitude;
        }

        /**
         *
         * @param longitude
         * The longitude
         */
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        /**
         *
         * @return
         * The postsCount
         */
        public String getPostsCount() {
            return postsCount;
        }

        /**
         *
         * @param postsCount
         * The posts_count
         */
        public void setPostsCount(String postsCount) {
            this.postsCount = postsCount;
        }

        /**
         *
         * @return
         * The carWashStatusId
         */
        public String getCarWashStatusId() {
            return carWashStatusId;
        }

        /**
         *
         * @param carWashStatusId
         * The car_wash_status_id
         */
        public void setCarWashStatusId(String carWashStatusId) {
            this.carWashStatusId = carWashStatusId;
        }

        /**
         *
         * @return
         * The carWashTypeId
         */
        public String getCarWashTypeId() {
            return carWashTypeId;
        }

        /**
         *
         * @param carWashTypeId
         * The car_wash_type_id
         */
        public void setCarWashTypeId(String carWashTypeId) {
            this.carWashTypeId = carWashTypeId;
        }

        /**
         *
         * @return
         * The title
         */
        public String getTitle() {
            return title;
        }

        /**
         *
         * @param title
         * The title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         *
         * @return
         * The washServices
         */
        public List<WashService> getWashServices() {
            return washServices;
        }

        /**
         *
         * @param washServices
         * The wash_services
         */
        public void setWashServices(List<WashService> washServices) {
            this.washServices = washServices;
        }

    }

    class WashService {

        @Expose
        private String id;
        @Expose
        private String service;
        @SerializedName("car_category_id")
        @Expose
        private String carCategoryId;

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

    }

    public class CarWashType {

        @Expose
        private String id;
        @SerializedName("wash_type")
        @Expose
        private String washType;

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
         * The washType
         */
        public String getWashType() {
            return washType;
        }

        /**
         *
         * @param washType
         * The wash_type
         */
        public void setWashType(String washType) {
            this.washType = washType;
        }

    }

}