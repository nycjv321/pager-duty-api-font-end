package com.nycjv321.pagerdutytools.jsonserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nycjv321.pagerdutytools.documents.models.Service;

import java.lang.reflect.Type;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class ServiceSerializer implements JsonSerializer<Service> {

    @Override
    public JsonElement serialize(Service service, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", service.getId());
        jsonObject.addProperty("name", service.getName());
        jsonObject.addProperty("url", service.getServiceUrl());
        return jsonObject;
    }
}
