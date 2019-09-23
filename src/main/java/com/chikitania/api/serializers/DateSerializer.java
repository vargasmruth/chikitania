package com.chikitania.api.serializers;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Ruth on 23/09/2019.
 */

public class DateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        int offset = TimeZone.getTimeZone("America/La_Paz").getOffset(date.getTime());
        jsonGenerator.writeNumber((date.getTime())/1000L);
    }
}
