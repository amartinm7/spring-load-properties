package com.amm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * Model class to store the application properties related with the external api's
 */
@Configuration
@ConfigurationProperties(prefix = "externalApi")
@lombok.Data
public class ExternalApiProperties {

    private List<Application> application;

    @lombok.Data
    public static class Application {
        private String name;
        private String domain;
        private List<Method> methods;
    }

    @lombok.Data
    public static class Method{
        private String name;
        private String path;
    }

}
