package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Boichuk Dmitriy on 23.09.2015.
 */
public class LoginPojo {

    @SerializedName("data")
    @Expose
    private Data data;

    /**
     *
     * @return
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("login")
        @Expose
        private String login;
        @SerializedName("role")
        @Expose
        private Integer role;
        @SerializedName("sms_code")
        @Expose
        private String smsCode;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("access_token")
        @Expose
        private String accessToken;

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
         * The role
         */
        public Integer getRole() {
            return role;
        }

        /**
         *
         * @param role
         * The role
         */
        public void setRole(Integer role) {
            this.role = role;
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
         * The password
         */
        public Object getPassword() {
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

    }
}
