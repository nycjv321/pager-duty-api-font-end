package com.nycjv321.pagerdutytools.jsonserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nycjv321.pagerdutytools.documents.models.Email;

import java.lang.reflect.Type;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class EmailSerializer implements JsonSerializer<Email> {

    @Override
    public JsonElement serialize(Email email, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty("from", email.getFrom());
        json.addProperty("to", email.getTo());
        json.addProperty("summary", email.getSummary());
        json.addProperty("body", email.getBody());
        return json;
    }
}
