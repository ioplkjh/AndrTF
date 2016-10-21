package ru.allmoyki.may.db;

import io.realm.RealmObject;

/**
 * Created by Boichuk Dmitriy on 23.09.2015.
 */
public class CarsObject extends RealmObject {
    private String carName;
    private String carNameSmall;
    private String carNameId;
    private String carModelSmall;
    private String carModel;
    private String carModelId;
    private String carNumber;
    private String carCategoryId;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNameId() {
        return carNameId;
    }

    public void setCarNameId(String carNameId) {
        this.carNameId = carNameId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(String carModelId) {
        this.carModelId = carModelId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModelSmall() {
        return carModelSmall;
    }

    public void setCarModelSmall(String carModelSmall) {
        this.carModelSmall = carModelSmall;
    }

    public String getCarNameSmall() {
        return carNameSmall;
    }

    public void setCarNameSmall(String carNameSmall) {
        this.carNameSmall = carNameSmall;
    }

    public String getCarCategoryId() {
        return carCategoryId;
    }

    public void setCarCategoryId(String carCategoryId) {
        this.carCategoryId = carCategoryId;
    }
}
