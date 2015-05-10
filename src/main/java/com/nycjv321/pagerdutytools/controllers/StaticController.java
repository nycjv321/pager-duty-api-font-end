package com.nycjv321.pagerdutytools.controllers;

import com.nycjv321.pagerdutytools.JsonTemplateEngine;
import com.nycjv321.pagerdutytools.StaticQueries;
import com.nycjv321.pagerdutytools.models.Incident;
import com.nycjv321.pagerdutytools.utilities.PagerDutySynchronizer;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by jvelasquez on 4/19/15.
 */
public interface StaticController {

    default void home() {
        List<Incident> incidents = Incident.get(50);

        Map<String, List<?>> payload = new HashMap<>();
        payload.put("incidents", incidents);
        get("/", (request, response) -> new ModelAndView(
                payload, "root.mustache"
        ), new MustacheTemplateEngine());
    }

    default void reports() {
        get("/reports", (request, response) -> new ModelAndView(
                new Object(), "reports.mustache"
        ), new MustacheTemplateEngine());

    }

    default void triggerCreatorSummaries() {
        get("/incident_creators_count.json", ((request, response) ->
                new ModelAndView(StaticQueries.getTriggerCount(),
                        "incident_creators_count"
                )
        ), new JsonTemplateEngine());
    }

    default void incidentResolveCount() {
        get("/incident_resolve_count.json", ((request, response) ->
                new ModelAndView(StaticQueries.getResolveCount(),
                        "incident_resolve_count"
                )
        ), new JsonTemplateEngine());

    }

    default void about() {
        get("/about", (request, response) -> new ModelAndView(
                new Object(), "about.mustache"
        ), new MustacheTemplateEngine());
    }

    default void savedSearches() {
        get("/saved_searches", (request, response) -> new ModelAndView(
                new Object(), "saved_searches.mustache"
        ), new MustacheTemplateEngine());
    }

    default void tipsAndTricks() {
        get("/tips_and_tricks", (request, response) -> new ModelAndView(
                new Object(), "tips_and_tricks.mustache"
        ), new MustacheTemplateEngine());
    }

    default void synchronize() {
        get("/synchronize", ((request, response) -> new ModelAndView(PagerDutySynchronizer.synchronize(), "synchronization")), new JsonTemplateEngine());
    }

}
