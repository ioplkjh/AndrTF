package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boichuk Dmitriy on 02.09.2015.
 */
public class CurrentCarwashPojo {

    public class AdditionalService {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("service")
        @Expose
        private String service;
        @SerializedName("exists")
        @Expose
        private String exists;

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
         * @return The service
         */
        public String getService() {
            return service;
        }

        /**
         * @param service The service
         */
        public void setService(String service) {
            this.service = service;
        }

        /**
         * @return The exists
         */
        public String getExists() {
            return exists;
        }

        /**
         * @param exists The exists
         */
        public void setExists(String exists) {
            this.exists = exists;
        }

    }

    public class CarWashType {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("wash_type")
        @Expose
        private String washType;

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
         * @return The washType
         */
        public String getWashType() {
            return washType;
        }

        /**
         * @param washType The wash_type
         */
        public void setWashType(String washType) {
            this.washType = washType;
        }

    }

    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * @return The data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

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
        @SerializedName("additional_services")
        @Expose
        private List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();
        @SerializedName("wash_services")
        @Expose
        private List<WashService> washServices = new ArrayList<WashService>();
        @SerializedName("car_wash_type")
        @Expose
        private CarWashType carWashType;
        @SerializedName("disabled_posts")
        @Expose
        private List<Object> disabledPosts = new ArrayList<Object>();
        @SerializedName("work_time")
        @Expose
        private WorkTime workTime;
        @SerializedName("wash_times")
        @Expose
        private WashTimes washTimes;

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

        /**
         * @return The additionalServices
         */
        public List<AdditionalService> getAdditionalServices() {
            return additionalServices;
        }

        /**
         * @param additionalServices The additional_services
         */
        public void setAdditionalServices(List<AdditionalService> additionalServices) {
            this.additionalServices = additionalServices;
        }

        /**
         * @return The washServices
         */
        public List<WashService> getWashServices() {
            return washServices;
        }

        /**
         * @param washServices The wash_services
         */
        public void setWashServices(List<WashService> washServices) {
            this.washServices = washServices;
        }

        /**
         * @return The carWashType
         */
        public CarWashType getCarWashType() {
            return carWashType;
        }

        /**
         * @param carWashType The car_wash_type
         */
        public void setCarWashType(CarWashType carWashType) {
            this.carWashType = carWashType;
        }

        /**
         * @return The disabledPosts
         */
        public List<Object> getDisabledPosts() {
            return disabledPosts;
        }

        /**
         * @param disabledPosts The disabled_posts
         */
        public void setDisabledPosts(List<Object> disabledPosts) {
            this.disabledPosts = disabledPosts;
        }

        /**
         * @return The workTime
         */
        public WorkTime getWorkTime() {
            return workTime;
        }

        /**
         * @param workTime The work_time
         */
        public void setWorkTime(WorkTime workTime) {
            this.workTime = workTime;
        }

        /**
         * @return The washTimes
         */
        public WashTimes getWashTimes() {
            return washTimes;
        }

        /**
         * @param washTimes The wash_times
         */
        public void setWashTimes(WashTimes washTimes) {
            this.washTimes = washTimes;
        }

    }

    public class EndWashTime {

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

    public class StartWashTime {

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

    public class Today {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("open")
        @Expose
        private String open;

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
         * @return The time
         */
        public String getTime() {
            return time;
        }

        /**
         * @param time The time
         */
        public void setTime(String time) {
            this.time = time;
        }

        /**
         * @return The open
         */
        public String getOpen() {
            return open;
        }

        /**
         * @param open The open
         */
        public void setOpen(String open) {
            this.open = open;
        }

    }

    public class Tomorrow {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("open")
        @Expose
        private String open;

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
         * @return The time
         */
        public String getTime() {
            return time;
        }

        /**
         * @param time The time
         */
        public void setTime(String time) {
            this.time = time;
        }

        /**
         * @return The open
         */
        public String getOpen() {
            return open;
        }

        /**
         * @param open The open
         */
        public void setOpen(String open) {
            this.open = open;
        }

    }

    public class WashService {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("service")
        @Expose
        private String service;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("cost")
        @Expose
        private String cost;
        @Expose
        private String car_category_id;

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
         * @return The service
         */
        public String getService() {
            return service;
        }

        /**
         * @param service The service
         */
        public void setService(String service) {
            this.service = service;
        }

        /**
         * @return The time
         */
        public String getTime() {
            return time;
        }

        /**
         * @param time The time
         */
        public void setTime(String time) {
            this.time = time;
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

        public String getCar_category_id() {
            return car_category_id;
        }

        public void setCar_category_id(String car_category_id) {
            this.car_category_id = car_category_id;
        }
    }

    public class WashTimes {

        @SerializedName("yesterday")
        @Expose
        private List<Yesterday> yesterday = new ArrayList<Yesterday>();
        @SerializedName("today")
        @Expose
        private List<Today> today = new ArrayList<Today>();
        @SerializedName("tomorrow")
        @Expose
        private List<Tomorrow> tomorrow = new ArrayList<Tomorrow>();

        /**
         * @return The yesterday
         */
        public List<Yesterday> getYesterday() {
            return yesterday;
        }

        /**
         * @param yesterday The yesterday
         */
        public void setYesterday(List<Yesterday> yesterday) {
            this.yesterday = yesterday;
        }

        /**
         * @return The today
         */
        public List<Today> getToday() {
            return today;
        }

        /**
         * @param today The today
         */
        public void setToday(List<Today> today) {
            this.today = today;
        }

        /**
         * @return The tomorrow
         */
        public List<Tomorrow> getTomorrow() {
            return tomorrow;
        }

        /**
         * @param tomorrow The tomorrow
         */
        public void setTomorrow(List<Tomorrow> tomorrow) {
            this.tomorrow = tomorrow;
        }

    }

    public class WorkTime {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("car_wash_id")
        @Expose
        private String carWashId;
        @SerializedName("week_day")
        @Expose
        private String weekDay;
        @SerializedName("start_wash_time_id")
        @Expose
        private String startWashTimeId;
        @SerializedName("end_wash_time_id")
        @Expose
        private String endWashTimeId;
        @SerializedName("start_wash_time")
        @Expose
        private StartWashTime startWashTime;
        @SerializedName("end_wash_time")
        @Expose
        private EndWashTime endWashTime;

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
         * @return The weekDay
         */
        public String getWeekDay() {
            return weekDay;
        }

        /**
         * @param weekDay The week_day
         */
        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }

        /**
         * @return The startWashTimeId
         */
        public String getStartWashTimeId() {
            return startWashTimeId;
        }

        /**
         * @param startWashTimeId The start_wash_time_id
         */
        public void setStartWashTimeId(String startWashTimeId) {
            this.startWashTimeId = startWashTimeId;
        }

        /**
         * @return The endWashTimeId
         */
        public String getEndWashTimeId() {
            return endWashTimeId;
        }

        /**
         * @param endWashTimeId The end_wash_time_id
         */
        public void setEndWashTimeId(String endWashTimeId) {
            this.endWashTimeId = endWashTimeId;
        }

        /**
         * @return The startWashTime
         */
        public StartWashTime getStartWashTime() {
            return startWashTime;
        }

        /**
         * @param startWashTime The start_wash_time
         */
        public void setStartWashTime(StartWashTime startWashTime) {
            this.startWashTime = startWashTime;
        }

        /**
         * @return The endWashTime
         */
        public EndWashTime getEndWashTime() {
            return endWashTime;
        }

        /**
         * @param endWashTime The end_wash_time
         */
        public void setEndWashTime(EndWashTime endWashTime) {
            this.endWashTime = endWashTime;
        }

    }

    public class Yesterday {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("open")
        @Expose
        private String open;

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
         * @return The time
         */
        public String getTime() {
            return time;
        }

        /**
         * @param time The time
         */
        public void setTime(String time) {
            this.time = time;
        }

        /**
         * @return The open
         */
        public String getOpen() {
            return open;
        }

        /**
         * @param open The open
         */
        public void setOpen(String open) {
            this.open = open;
        }

    }
}