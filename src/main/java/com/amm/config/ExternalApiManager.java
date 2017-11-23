package com.amm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class in charge of managing the application properties related with the external api's
 */
@Component
public class ExternalApiManager {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalApiManager.class);

    /**
     * The properties load from the application properties
     */
    private ExternalApiProperties externalApiProperties;

    /**
     * A storage to save the URL's of the external api's
     */
    public final Map<Map.Entry<String,String>,String> pool = new HashMap<>();

    /**
     * constructor
     * @param externalApiProperties
     */
    public ExternalApiManager(@Autowired ExternalApiProperties externalApiProperties){
        super();
        this.externalApiProperties = externalApiProperties;
    }

    /**
     * This method is in changing to get the URL for the external service
     * for the application name and method name combination
     * @param applicationName
     * @param methodName
     * @return String with the URL to call.
     */
    public String getURL( String applicationName, String methodName, Map<String,String> pathParams ) {
        final String rawUrl = getRawURL(applicationName,methodName);
        final String url = replacePlaceHoldersInURL(rawUrl, pathParams);
        return url;
    }

    /**
     * Get the URL with placeHolders pathParams
     * @param applicationName
     * @param methodName
     * @return the URL with placeholders
     */
    private String getRawURL( String applicationName, String methodName ){
        LOGGER.debug("Getting URL for the combination applicationName/methodName: '{}/{}'",applicationName,methodName);
        final Map.Entry<String,String> key = new AbstractMap.SimpleEntry<String, String>(applicationName, methodName);
        if (pool.containsKey(key)){
            return pool.get(key);
        }
        final Optional<ExternalApiProperties.Application> application =
                externalApiProperties.getApplication().stream()
                .filter(app -> app.getName().equals(applicationName)).findFirst();
        final String urlApp = application.map(app->app.getDomain())
                .orElseThrow(() -> new IllegalArgumentException(String.format("Application '%s' not found", applicationName )));
        final String pathMethod =
                application.get().getMethods().stream()
                .filter(method1 -> method1.getName().equals(methodName))
                        .findFirst()
                        .map(method1->method1.getPath())
                        .orElseThrow(() -> new IllegalArgumentException(String.format("Method '%s' not found for the application '%s'  ", methodName, applicationName )));
        final String url = String.format ("%s%s", urlApp, pathMethod);
        pool.put(key, url);
        LOGGER.debug("Obtained URL for the combination applicationName/methodName: '{}'",url);
        return url;
    }

    /**
     * replace PlaceHolders in URL
     * @param rawUrl
     * @param pathParams
     * @return the final URL
     */
    private String replacePlaceHoldersInURL(final String rawUrl, final Map<String,String> pathParams){
        LOGGER.debug("Replacing placeHolders in to the URL. URL '{}' placeholder '{}'",rawUrl, pathParams.toString());
        final AtomicReference<String> atomicReference = new AtomicReference(rawUrl);
        pathParams.forEach( (k,v)-> {
            atomicReference.set(atomicReference.get().replace(k,v));
        });
        LOGGER.debug("final URL without pathParams for the combination applicationName/methodName: '{}'",atomicReference.get());
        return atomicReference.get();
    }

}
