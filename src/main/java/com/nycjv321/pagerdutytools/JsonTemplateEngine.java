package com.nycjv321.pagerdutytools;

import com.nycjv321.pagerdutytools.utilities.GsonFactory;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 * Created by jvelasquez on 4/19/15.
 */
public class JsonTemplateEngine extends TemplateEngine {
    @Override
    public String render(ModelAndView modelAndView) {
        return GsonFactory.getGson().toJson(modelAndView.getModel());
    }
}
