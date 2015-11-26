package com.nycjv321.pagerdutytools.jsonserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nycjv321.pagerdutytools.documents.models.User;

import java.lang.reflect.Type;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class UserSerializer implements JsonSerializer<User> {

    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty("id", user.getNoteId());
        json.addProperty("name", user.getName());
        json.addProperty("email", user.getEmail());
        json.addProperty("job_title", user.getJobTitle());
        return json;
    }
}
