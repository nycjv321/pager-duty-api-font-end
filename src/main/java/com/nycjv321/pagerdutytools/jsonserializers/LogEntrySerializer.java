package com.nycjv321.pagerdutytools.jsonserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nycjv321.pagerdutytools.models.LogEntry;
import com.nycjv321.pagerdutytools.models.Note;
import com.nycjv321.pagerdutytools.models.User;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class LogEntrySerializer implements JsonSerializer<LogEntry> {

    @Override
    public JsonElement serialize(LogEntry logEntry, Type type, JsonSerializationContext jsonSerializationContext) {
        ChannelSerializer channelSerializer = SerializerFactory.getChannelSerializer();

        JsonObject json = new JsonObject();
        json.addProperty("id", logEntry.getId());
        json.addProperty("type", logEntry.getType());
        json.addProperty("created_at", logEntry.getCreationDate());
        User agent = logEntry.getUser();
        if (Objects.nonNull(agent)) {
            json.add("user", SerializerFactory.getUserSerializer().serialize(agent, User.class, jsonSerializationContext));
        }
        if (logEntry.hasNotes()) {
            json.add("note", SerializerFactory.getNoteSerializer().serialize(logEntry.getNote(), Note.class, jsonSerializationContext));
        }

        if (logEntry.hasChannel()) {
            json.add("channel", channelSerializer.serialize(logEntry.getChannel(), LogEntry.Channel.class, jsonSerializationContext));
        }
        return json;
    }
}
