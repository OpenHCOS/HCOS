package com.cbh.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Tommy on 2017/6/5.
 */
public class Location {

    @JsonProperty("coordinates")
    private List<Double> coordinates;


    @JsonProperty("type")
    private String type;

    public void setCoordinates(List<Double> coordinates){
        this.coordinates = coordinates;
    }

    public List<Double> getCoordinates(){
        return coordinates;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
