package com.nycjv321.pagerdutytools.utilities;

import com.google.gson.JsonObject;
import com.nycjv321.pagerdutytools.RestSynchronizationManager;

/**
 * Created by jvelasquez on 4/19/15.
 */
public class PagerDutySynchronizer {
    private static RestSynchronizationManager restSynchronizationManager = new RestSynchronizationManager();

    // Move me?
    public static JsonObject synchronize() {
        JsonObject result = new JsonObject();
        result.addProperty("title", "Synchronization Result");
        try {
            result.addProperty("updated", restSynchronizationManager.updateIncidents());
        } catch (RestSynchronizationManager.UnResolvedIncidentsException e) {
            result.addProperty("updated", false);
            result.addProperty("message", e.getMessage());
        }
        return result;
    }

}
