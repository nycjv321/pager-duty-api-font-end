package com.nycjv321.pagerdutytools.jsonserializers;

import com.google.gson.*;
import com.nycjv321.pagerdutytools.models.Incident;
import com.nycjv321.pagerdutytools.models.LogEntry;
import com.nycjv321.pagerdutytools.models.Note;
import com.nycjv321.pagerdutytools.models.User;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class IncidentSerializer implements JsonSerializer<Incident> {

    @Override
    public JsonElement serialize(Incident incident, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty("id", incident.getId());
        json.addProperty("incident_number", incident.getNumber());
        json.addProperty("summary", incident.getSummary());
        json.addProperty("created_on", incident.getCreationDate());
        json.addProperty("url", incident.getUrl());

        json.addProperty("status", incident.getStatus());
        json.addProperty("type", incident.getTriggerType());
        json.addProperty("escalations", incident.getEscalations());

//        json.add("service", SerializerFactory.getServiceSerializer().serialize(incident.getService(), Service.class, jsonSerializationContext));
        json.addProperty("team", incident.getTeam());

        List<LogEntry> notes = incident.getNotes();

        JsonArray notesArray = new JsonArray();
        for (LogEntry note : notes) {
            notesArray.add(SerializerFactory.getLogEntrySerializer().serialize(note, Note.class, jsonSerializationContext));
        }

        if (notesArray.size() != 0) {
            json.add("notes", notesArray);
        }

        User lastStateChangeBy = incident.getLastStateChangeBy();

        if (Objects.nonNull(lastStateChangeBy)) {
            json.add("last_state_changed_by", SerializerFactory.getUserSerializer().serialize(lastStateChangeBy, User.class, jsonSerializationContext));
        }

        User resolvedBy = incident.getResolvedBy();
        if (Objects.nonNull(resolvedBy)) {
            json.add("resolved_by", SerializerFactory.getUserSerializer().serialize(resolvedBy, User.class, jsonSerializationContext));
        }

        JsonArray logEntriesArray = new JsonArray();
        List<LogEntry> logEntries = incident.getLogEntries();
        if (Objects.nonNull(logEntries)) {
            for (LogEntry logEntry : logEntries) {
                logEntriesArray.add(SerializerFactory.getLogEntrySerializer().serialize(logEntry, LogEntry.class, jsonSerializationContext));
            }
            if (logEntriesArray.size() != 0) {
                json.add("log_entries", logEntriesArray);
            }
        }

        return json;
    }
}
