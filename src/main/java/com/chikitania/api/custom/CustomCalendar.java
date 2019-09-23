package com.chikitania.api.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.chikitania.api.serializers.DateDeserializer;

import java.util.Date;

/**
 * Created by Ruth on 23/09/2019.
 */
 
public class CustomCalendar {
    @JsonProperty
    public long deliveryCalendarId;

    @JsonProperty
    public long contractId;

    @JsonProperty
    @JsonDeserialize(using = DateDeserializer.class)
    public Date end;

    @JsonProperty
    @JsonDeserialize(using = DateDeserializer.class)
    public Date start;

    @JsonProperty
    public String comment;

    @JsonProperty
    public String title;

    @JsonProperty
    public String url;

    @JsonProperty
    public String color;


    public CustomCalendar() {
    }

    public CustomCalendar(long deliveryCalendarId, long contractId, Date end, Date start,
                          String comment, String title, String url) {
        this.deliveryCalendarId = deliveryCalendarId;
        this.contractId = contractId;
        this.end = end;
        this.start = start;
        this.comment = comment;
        this.title = title;
        this.url = url;
    }
}