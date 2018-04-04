package com.laputa.foundation.elasticsearch.repositories;

public class CityIndex {
    private String index;


    public String getIndex() {
        //ThreadLocal
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
