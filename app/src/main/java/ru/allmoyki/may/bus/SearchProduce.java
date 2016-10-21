package ru.allmoyki.may.bus;

import ru.allmoyki.may.pojo.SearchPojo;

/**
 * Created by Boichuk Dmitriy on 02.10.2015.
 */
public class SearchProduce {
    private SearchPojo searchPojo;
    private int position;

    public SearchPojo getSearchPojo() {
        return searchPojo;
    }

    public void setSearchPojo(SearchPojo searchPojo) {
        this.searchPojo = searchPojo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
