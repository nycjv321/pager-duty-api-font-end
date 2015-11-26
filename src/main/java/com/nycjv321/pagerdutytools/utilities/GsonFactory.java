package com.nycjv321.pagerdutytools.utilities;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nycjv321.pagerdutytools.documents.models.*;
import com.nycjv321.pagerdutytools.jsonserializers.*;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class GsonFactory {

    public synchronized static Gson getSimpleGson() {
        return new Gson();
    }


    public synchronized static Gson getGson() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LogEntry.class, new LogEntrySerializer()).setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(Note.class, new NoteSerializer()).setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(User.class, new UserSerializer()).setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(Service.class, new ServiceSerializer()).setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(Incident.class, new IncidentSerializer()).setPrettyPrinting();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();

    }
}
