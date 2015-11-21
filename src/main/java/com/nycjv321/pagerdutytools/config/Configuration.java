package com.nycjv321.pagerdutytools.config;

/**
 * Created by jvelasquez on 6/1/15.
 */
public class Configuration {

    public static class Incident {
        public static int getQueryCount() {
            return 0;
        }
    }

    public static class Spark {
        public static int getPort() {
            return 0;
        }

        public static String getFileLocation() {
            return "";
        }
    }

}
