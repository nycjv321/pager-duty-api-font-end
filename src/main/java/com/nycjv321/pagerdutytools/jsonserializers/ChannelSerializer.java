package com.nycjv321.pagerdutytools.jsonserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nycjv321.pagerdutytools.models.LogEntry;

import java.lang.reflect.Type;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class ChannelSerializer implements JsonSerializer<LogEntry.Channel> {

    @Override
    public JsonElement serialize(LogEntry.Channel channel, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty("subject", channel.getSubject());
        json.addProperty("date", channel.getDate());
        json.addProperty("to", channel.getTo());
        json.addProperty("body", channel.getBody());
        return json;

    }
}
