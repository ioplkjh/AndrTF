package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Boichuk Dmitriy on 15.09.2015.
 */
public class UserPojo {


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
        @SerializedName("login")
        @Expose
        private String login;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("access_token")
        @Expose
        private String accessToken;
        @SerializedName("sms_code")
        @Expose
        private String smsCode;

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
         * The login
         */
        public String getLogin() {
            return login;
        }

        /**
         *
         * @param login
         * The login
         */
        public void setLogin(String login) {
            this.login = login;
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
         * The role
         */
        public String getRole() {
            return role;
        }

        /**
         *
         * @param role
         * The role
         */
        public void setRole(String role) {
            this.role = role;
        }

        /**
         *
         * @return
         * The accessToken
         */
        public String getAccessToken() {
            return accessToken;
        }

        /**
         *
         * @param accessToken
         * The access_token
         */
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        /**
         *
         * @return
         * The smsCode
         */
        public String getSmsCode() {
            return smsCode;
        }

        /**
         *
         * @param smsCode
         * The sms_code
         */
        public void setSmsCode(String smsCode) {
            this.smsCode = smsCode;
        }

    }
}
