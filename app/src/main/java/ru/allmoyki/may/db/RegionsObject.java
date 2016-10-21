package ru.allmoyki.may.db;

import io.realm.RealmObject;

/**
 * Created by Boichuk Dmitriy on 28.09.2015.
 */
public class RegionsObject extends RealmObject {
    private String ragionName;
    private String regioonId;

    public String getRagionName() {
        return ragionName;
    }

    public void setRagionName(String ragionName) {
        this.ragionName = ragionName;
    }

    public String getRegioonId() {
        return regioonId;
    }

    public void setRegioonId(String regioonId) {
        this.regioonId = regioonId;
    }
}
