package com.nycjv321.pagerdutytools.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jvelasquez on 6/1/15.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelAndViewDefinition {
    String path();

    Method method() default Method.GET;

    Class templateEngine();

    String viewName();

    enum Method {
        GET
    }
}
