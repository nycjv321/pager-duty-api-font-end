package com.nycjv321.pagerdutytools.serializer;

import com.nycjv321.pagerdutytools.documents.models.Incident;
import com.nycjv321.pagerdutytools.jsonserializers.IncidentSerializer;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by jvelasquez on 4/18/15.
 */
public class IncidentTests {

    @Test
    public void testSerializeIncident() {
        List<Incident> incidents = Incident.get(1);
        IncidentSerializer incidentSerializer = new IncidentSerializer();
        for (Incident incident : incidents) {
            incidentSerializer.serialize(incident, Incident.class, null);
        }
    }

}
