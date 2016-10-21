package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boichuk Dmitriy on 08.10.2015.
 */
public class OrderPojo {

    public class CarWash {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
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
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("region_id")
        @Expose
        private String regionId;
        @SerializedName("half_hour")
        @Expose
        private String halfHour;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("start_time_id")
        @Expose
        private String startTimeId;
        @SerializedName("end_time_id")
        @Expose
        private String endTimeId;
        @SerializedName("how_to_go")
        @Expose
        private String howToGo;

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
         * @return The phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         * @param phone The phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         * @return The address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * @return The email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * @return The latitude
         */
        public String getLatitude() {
            return latitude;
        }

        /**
         * @param latitude The latitude
         */
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        /**
         * @return The longitude
         */
        public String getLongitude() {
            return longitude;
        }

        /**
         * @param longitude The longitude
         */
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        /**
         * @return The postsCount
         */
        public String getPostsCount() {
            return postsCount;
        }

        /**
         * @param postsCount The posts_count
         */
        public void setPostsCount(String postsCount) {
            this.postsCount = postsCount;
        }

        /**
         * @return The carWashStatusId
         */
        public String getCarWashStatusId() {
            return carWashStatusId;
        }

        /**
         * @param carWashStatusId The car_wash_status_id
         */
        public void setCarWashStatusId(String carWashStatusId) {
            this.carWashStatusId = carWashStatusId;
        }

        /**
         * @return The carWashTypeId
         */
        public String getCarWashTypeId() {
            return carWashTypeId;
        }

        /**
         * @param carWashTypeId The car_wash_type_id
         */
        public void setCarWashTypeId(String carWashTypeId) {
            this.carWashTypeId = carWashTypeId;
        }

        /**
         * @return The title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @param title The title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return The regionId
         */
        public String getRegionId() {
            return regionId;
        }

        /**
         * @param regionId The region_id
         */
        public void setRegionId(String regionId) {
            this.regionId = regionId;
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

        /**
         * @return The userId
         */
        public String getUserId() {
            return userId;
        }

        /**
         * @param userId The user_id
         */
        public void setUserId(String userId) {
            this.userId = userId;
        }

        /**
         * @return The rating
         */
        public String getRating() {
            return rating;
        }

        /**
         * @param rating The rating
         */
        public void setRating(String rating) {
            this.rating = rating;
        }

        /**
         * @return The startTimeId
         */
        public String getStartTimeId() {
            return startTimeId;
        }

        /**
         * @param startTimeId The start_time_id
         */
        public void setStartTimeId(String startTimeId) {
            this.startTimeId = startTimeId;
        }

        /**
         * @return The endTimeId
         */
        public String getEndTimeId() {
            return endTimeId;
        }

        /**
         * @param endTimeId The end_time_id
         */
        public void setEndTimeId(String endTimeId) {
            this.endTimeId = endTimeId;
        }

        /**
         * @return The howToGo
         */
        public String getHowToGo() {
            return howToGo;
        }

        /**
         * @param howToGo The how_to_go
         */
        public void setHowToGo(String howToGo) {
            this.howToGo = howToGo;
        }

    }

    public class Client {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("fio")
        @Expose
        private String fio;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("reg_date")
        @Expose
        private String regDate;
        @SerializedName("total_spent_money")
        @Expose
        private String totalSpentMoney;
        @SerializedName("total_orders_count")
        @Expose
        private String totalOrdersCount;
        @SerializedName("total_rejects_count")
        @Expose
        private String totalRejectsCount;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("region_id")
        @Expose
        private String regionId;

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
         * @return The fio
         */
        public String getFio() {
            return fio;
        }

        /**
         * @param fio The fio
         */
        public void setFio(String fio) {
            this.fio = fio;
        }

        /**
         * @return The phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         * @param phone The phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         * @return The regDate
         */
        public String getRegDate() {
            return regDate;
        }

        /**
         * @param regDate The reg_date
         */
        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        /**
         * @return The totalSpentMoney
         */
        public String getTotalSpentMoney() {
            return totalSpentMoney;
        }

        /**
         * @param totalSpentMoney The total_spent_money
         */
        public void setTotalSpentMoney(String totalSpentMoney) {
            this.totalSpentMoney = totalSpentMoney;
        }

        /**
         * @return The totalOrdersCount
         */
        public String getTotalOrdersCount() {
            return totalOrdersCount;
        }

        /**
         * @param totalOrdersCount The total_orders_count
         */
        public void setTotalOrdersCount(String totalOrdersCount) {
            this.totalOrdersCount = totalOrdersCount;
        }

        /**
         * @return The totalRejectsCount
         */
        public String getTotalRejectsCount() {
            return totalRejectsCount;
        }

        /**
         * @param totalRejectsCount The total_rejects_count
         */
        public void setTotalRejectsCount(String totalRejectsCount) {
            this.totalRejectsCount = totalRejectsCount;
        }

        /**
         * @return The userId
         */
        public String getUserId() {
            return userId;
        }

        /**
         * @param userId The user_id
         */
        public void setUserId(String userId) {
            this.userId = userId;
        }

        /**
         * @return The regionId
         */
        public String getRegionId() {
            return regionId;
        }

        /**
         * @param regionId The region_id
         */
        public void setRegionId(String regionId) {
            this.regionId = regionId;
        }

    }

    public class ClientCar {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("car_model_id")
        @Expose
        private String carModelId;
        @SerializedName("gos_number")
        @Expose
        private String gosNumber;
        @SerializedName("client_id")
        @Expose
        private String clientId;

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
         * @return The carModelId
         */
        public String getCarModelId() {
            return carModelId;
        }

        /**
         * @param carModelId The car_model_id
         */
        public void setCarModelId(String carModelId) {
            this.carModelId = carModelId;
        }

        /**
         * @return The gosNumber
         */
        public String getGosNumber() {
            return gosNumber;
        }

        /**
         * @param gosNumber The gos_number
         */
        public void setGosNumber(String gosNumber) {
            this.gosNumber = gosNumber;
        }

        /**
         * @return The clientId
         */
        public String getClientId() {
            return clientId;
        }

        /**
         * @param clientId The client_id
         */
        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("client_id")
        @Expose
        private String clientId;
        @SerializedName("client_car_id")
        @Expose
        private String clientCarId;
        @SerializedName("car_wash_id")
        @Expose
        private String carWashId;
        @SerializedName("cost")
        @Expose
        private String cost;
        @SerializedName("order_source_id")
        @Expose
        private String orderSourceId;
        @SerializedName("order_status_id")
        @Expose
        private String orderStatusId;
        @SerializedName("wash_date")
        @Expose
        private String washDate;
        @SerializedName("post_number")
        @Expose
        private String postNumber;
        @SerializedName("wash_time_id")
        @Expose
        private String washTimeId;
        @SerializedName("car_category_id")
        @Expose
        private String carCategoryId;
        @SerializedName("order_source")
        @Expose
        private OrderSource orderSource;
        @SerializedName("order_status")
        @Expose
        private OrderStatus orderStatus;
        @SerializedName("client")
        @Expose
        private Client client;
        @SerializedName("client_car")
        @Expose
        private ClientCar clientCar;
        @SerializedName("car_wash")
        @Expose
        private CarWash carWash;
        @SerializedName("wash_time")
        @Expose
        private WashTime washTime;
        @SerializedName("wash_services")
        @Expose
        private List<WashServ> washServices = new ArrayList<WashServ>();
        @SerializedName("review")
        @Expose
        private Review review;

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
         * @return The clientId
         */
        public String getClientId() {
            return clientId;
        }

        /**
         * @param clientId The client_id
         */
        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        /**
         * @return The clientCarId
         */
        public String getClientCarId() {
            return clientCarId;
        }

        /**
         * @param clientCarId The client_car_id
         */
        public void setClientCarId(String clientCarId) {
            this.clientCarId = clientCarId;
        }

        /**
         * @return The carWashId
         */
        public String getCarWashId() {
            return carWashId;
        }

        /**
         * @param carWashId The car_wash_id
         */
        public void setCarWashId(String carWashId) {
            this.carWashId = carWashId;
        }

        /**
         * @return The cost
         */
        public String getCost() {
            return cost;
        }

        /**
         * @param cost The cost
         */
        public void setCost(String cost) {
            this.cost = cost;
        }

        /**
         * @return The orderSourceId
         */
        public String getOrderSourceId() {
            return orderSourceId;
        }

        /**
         * @param orderSourceId The order_source_id
         */
        public void setOrderSourceId(String orderSourceId) {
            this.orderSourceId = orderSourceId;
        }

        /**
         * @return The orderStatusId
         */
        public String getOrderStatusId() {
            return orderStatusId;
        }

        /**
         * @param orderStatusId The order_status_id
         */
        public void setOrderStatusId(String orderStatusId) {
            this.orderStatusId = orderStatusId;
        }

        /**
         * @return The washDate
         */
        public String getWashDate() {
            return washDate;
        }

        /**
         * @param washDate The wash_date
         */
        public void setWashDate(String washDate) {
            this.washDate = washDate;
        }

        /**
         * @return The postNumber
         */
        public String getPostNumber() {
            return postNumber;
        }

        /**
         * @param postNumber The post_number
         */
        public void setPostNumber(String postNumber) {
            this.postNumber = postNumber;
        }

        /**
         * @return The washTimeId
         */
        public String getWashTimeId() {
            return washTimeId;
        }

        /**
         * @param washTimeId The wash_time_id
         */
        public void setWashTimeId(String washTimeId) {
            this.washTimeId = washTimeId;
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
         * @return The orderSource
         */
        public OrderSource getOrderSource() {
            return orderSource;
        }

        /**
         * @param orderSource The order_source
         */
        public void setOrderSource(OrderSource orderSource) {
            this.orderSource = orderSource;
        }

        /**
         * @return The orderStatus
         */
        public OrderStatus getOrderStatus() {
            return orderStatus;
        }

        /**
         * @param orderStatus The order_status
         */
        public void setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
        }

        /**
         * @return The client
         */
        public Client getClient() {
            return client;
        }

        /**
         * @param client The client
         */
        public void setClient(Client client) {
            this.client = client;
        }

        /**
         * @return The clientCar
         */
        public ClientCar getClientCar() {
            return clientCar;
        }

        /**
         * @param clientCar The client_car
         */
        public void setClientCar(ClientCar clientCar) {
            this.clientCar = clientCar;
        }

        /**
         * @return The carWash
         */
        public CarWash getCarWash() {
            return carWash;
        }

        /**
         * @param carWash The car_wash
         */
        public void setCarWash(CarWash carWash) {
            this.carWash = carWash;
        }

        /**
         * @return The washTime
         */
        public WashTime getWashTime() {
            return washTime;
        }

        /**
         * @param washTime The wash_time
         */
        public void setWashTime(WashTime washTime) {
            this.washTime = washTime;
        }

        /**
         * @return The washServices
         */
        public List<WashServ> getWashServices() {
            return washServices;
        }

        /**
         * @param washServices The wash_services
         */
        public void setWashServices(List<WashServ> washServices) {
            this.washServices = washServices;
        }

        /**
         * @return The review
         */
        public Review getReview() {
            return review;
        }

        /**
         * @param review The review
         */
        public void setReview(Review review) {
            this.review = review;
        }

    }

    public class WashServ {
// "id":"1",
// "service":"Мойка",
// "car_category_id":"1"

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("service")
        @Expose
        private String service;
        @SerializedName("car_category_id")
        @Expose
        private String carCategoryId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getCarCategoryId() {
            return carCategoryId;
        }

        public void setCarCategoryId(String carCategoryId) {
            this.carCategoryId = carCategoryId;
        }
    }

    public class OrderSource {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("source")
        @Expose
        private String source;

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
         * @return The source
         */
        public String getSource() {
            return source;
        }

        /**
         * @param source The source
         */
        public void setSource(String source) {
            this.source = source;
        }

    }

    public class OrderStatus {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("status")
        @Expose
        private String status;

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
         * @return The status
         */
        public String getStatus() {
            return status;
        }

        /**
         * @param status The status
         */
        public void setStatus(String status) {
            this.status = status;
        }

    }


    @SerializedName("data")
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


    public class Review {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("car_wash_id")
        @Expose
        private String carWashId;
        @SerializedName("client_id")
        @Expose
        private String clientId;
        @SerializedName("added_date")
        @Expose
        private String addedDate;
        @SerializedName("client_order_id")
        @Expose
        private String clientOrderId;
        @SerializedName("mark")
        @Expose
        private String mark;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("answer")
        @Expose
        private String answer;

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
         * @return The carWashId
         */
        public String getCarWashId() {
            return carWashId;
        }

        /**
         * @param carWashId The car_wash_id
         */
        public void setCarWashId(String carWashId) {
            this.carWashId = carWashId;
        }

        /**
         * @return The clientId
         */
        public String getClientId() {
            return clientId;
        }

        /**
         * @param clientId The client_id
         */
        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        /**
         * @return The addedDate
         */
        public String getAddedDate() {
            return addedDate;
        }

        /**
         * @param addedDate The added_date
         */
        public void setAddedDate(String addedDate) {
            this.addedDate = addedDate;
        }

        /**
         * @return The clientOrderId
         */
        public String getClientOrderId() {
            return clientOrderId;
        }

        /**
         * @param clientOrderId The client_order_id
         */
        public void setClientOrderId(String clientOrderId) {
            this.clientOrderId = clientOrderId;
        }

        /**
         * @return The mark
         */
        public String getMark() {
            return mark;
        }

        /**
         * @param mark The mark
         */
        public void setMark(String mark) {
            this.mark = mark;
        }

        /**
         * @return The text
         */
        public String getText() {
            return text;
        }

        /**
         * @param text The text
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         * @return The answer
         */
        public String getAnswer() {
            return answer;
        }

        /**
         * @param answer The answer
         */
        public void setAnswer(String answer) {
            this.answer = answer;
        }

    }

    public class WashTime {

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
