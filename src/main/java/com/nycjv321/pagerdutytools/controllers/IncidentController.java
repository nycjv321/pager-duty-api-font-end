package com.nycjv321.pagerdutytools.controllers;

import com.nycjv321.pagerdutytools.JsonTemplateEngine;
import com.nycjv321.pagerdutytools.documents.models.Incident;
import com.nycjv321.pagerdutytools.view.IncidentJsonView;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;

import static spark.Spark.get;

/**
 * Created by jvelasquez on 4/19/15.
 */
public interface IncidentController {
    default void incidents() {
        get("/incidents.json", ((request, response) ->
                new IncidentJsonView(getIncidents(request),
                        "incident_search"
                )
        ), new JsonTemplateEngine());
    }

    default Object getIncidents(Request request) {
        QueryParamsMap queryParamsMap = request.queryMap();
        return Incident.find(queryParamsMap.get("subject").value());
    }

    default void incidentTrigger() {
        get("/incident/:id/email.json", ((request, response) ->
                new ModelAndView(Incident.find(Integer.parseInt(request.params(":id"))).getEmail(),
                        "incident_email"
                )
        ), new JsonTemplateEngine());
    }

    default void incidentNotes() {
        get("/incident/:id/notes.json", ((request, response) ->
                new ModelAndView(Incident.find(Integer.parseInt(request.params(":id"))).getNotes(),
                        "notes"
                )
        ), new JsonTemplateEngine());

    }
}
