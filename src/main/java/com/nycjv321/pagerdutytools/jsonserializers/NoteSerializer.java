package com.nycjv321.pagerdutytools.jsonserializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nycjv321.pagerdutytools.models.Note;

import java.lang.reflect.Type;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class NoteSerializer implements JsonSerializer<Note> {

    @Override
    public JsonElement serialize(Note note, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty("id", note.getId());
        json.addProperty("content", note.getContent());
        return json;
    }
}
