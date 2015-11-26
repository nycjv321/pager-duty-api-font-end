package com.nycjv321.pagerdutytools.view;

import spark.ModelAndView;

/**
 * Created by jvelasquez on 4/19/15.
 */
public class IncidentJsonView extends ModelAndView {

    /**
     * Constructs an instance with the provided model and view name
     *
     * @param model    the documents
     * @param viewName the view name
     */
    public IncidentJsonView(Object model, String viewName) {
        super(model, viewName);
    }
}
