package ru.allmoyki.may.pojo;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boichuk Dmitriy on 15.09.2015.
 */
public class RegionsPojo {

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
        private String region;

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
         * The region
         */
        public String getRegion() {
            return region;
        }

        /**
         *
         * @param region
         * The region
         */
        public void setRegion(String region) {
            this.region = region;
        }

    }
}
