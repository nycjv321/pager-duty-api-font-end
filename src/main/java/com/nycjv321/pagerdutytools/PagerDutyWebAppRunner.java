package com.nycjv321.pagerdutytools;

import com.nycjv321.pagerdutytools.controllers.IncidentController;
import com.nycjv321.pagerdutytools.controllers.StaticController;
import spark.Spark;

/**
 * Created by jvelasquez on 4/16/15.
 */
public class PagerDutyWebAppRunner implements StaticController, IncidentController {


    static {
        Spark.staticFileLocation("/");
        Spark.port(8081);
    }

    public static void main(String[] args) {
        PagerDutyWebAppRunner app = new PagerDutyWebAppRunner();

        // define static content
        app.home();
        app.savedSearches();
        app.about();
        app.tipsAndTricks();
        app.synchronize();
        app.reports();


        // define route for incidents (Rest Service)
        app.incidents();

        // restful methods
        app.triggerCreatorSummaries();
        app.incidentResolveCount();
        app.incidentTrigger();
        app.incidentNotes();
    }

}
