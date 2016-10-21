package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Boichuk Dmitriy on 28.09.2015.
 */
public class CurrentUserPojo {

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
}
