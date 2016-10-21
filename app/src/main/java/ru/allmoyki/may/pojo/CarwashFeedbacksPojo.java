package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boichuk Dmitriy on 08.09.2015.
 */
public class CarwashFeedbacksPojo {
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
        @Expose
        private String mark;
        @Expose
        private String text;
        @Expose
        private String answer;
        @SerializedName("client_phone")
        @Expose
        private String clientPhone;
        @SerializedName("client_fio")
        @Expose
        private String clientFio;

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

        /**
         * @return The clientPhone
         */
        public String getClientPhone() {
            return clientPhone;
        }

        /**
         * @param clientPhone The client_phone
         */
        public void setClientPhone(String clientPhone) {
            this.clientPhone = clientPhone;
        }

        public String getClientFio() {
            return clientFio;
        }

        public void setClientFio(String clientFio) {
            this.clientFio = clientFio;
        }
    }
}
