package com.chikitania.api.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by Ruth on 23/09/2019.
 */
public class ProductResponse {

    @JsonProperty
    public Integer id;

    @JsonProperty
    public String name;

    @JsonProperty
    public Double price;

    public ProductResponse() {
    }

    public ProductResponse(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
