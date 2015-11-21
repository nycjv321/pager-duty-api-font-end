package com.nycjv321.pagerdutytools.utilities;

import com.google.gson.JsonObject;
import com.nycjv321.pagerdutytools.RestSynchronizationManager;
import com.nycjv321.pagerdutytools.UnResolvedIncidentsException;

import java.io.IOException;

/**
 * Created by jvelasquez on 4/19/15.
 */
public class PagerDutySynchronizer {
    private static RestSynchronizationManager restSynchronizationManager = new RestSynchronizationManager();

    public static void main(String[] args) throws IOException {
        restSynchronizationManager.init();
        synchronize();
    }

    // Move me?
    public static JsonObject synchronize() {
        JsonObject result = new JsonObject();
        result.addProperty("title", "Synchronization Result");
        try {
            result.addProperty("updated", restSynchronizationManager.processNewIncidents());
        } catch (UnResolvedIncidentsException e) {
            result.addProperty("updated", false);
            result.addProperty("message", e.getMessage());
        }
        return result;
    }

}
