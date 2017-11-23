package com.amm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Main application for starting the side banner service.
 */
@EnableAutoConfiguration
@SpringBootApplication
public class Main {

    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Starts the application.
     *
     * @param args the command line arguments.
     * @throws Exception in case of error
     */
    public static void main(String[] args) throws Exception {
        logger.info("Starting uxt-side-banner...");
        SpringApplication app = new SpringApplication(Main.class);
        app.run(args);
    }

    /**
     * Adds the {@link RestTemplate} to the context.
     *
     * @param builder
     *
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


/*
    this part is an example to see how to create Beans the spring from classes that aren't no spring beans
    - MyProduct is a common class without any spring annotation
    - MyService is a common class without any spring annotation
    @Bean
    public MyProduct myProduct(RestTemplate restTemplate, ExternalApiManager externalApiManager) {
        return new MyProductImpl(restTemplate, externalApiManager);
    }

    @Bean
    public MyService myService(RestTemplate restTemplate, ExternalApiManager externalApiManager) {
        return new MyServiceImpl(productGroupsApiClient(restTemplate, externalApiManager), myProduct());
    }

    @Bean
    public Client getClient() {
        final List<SpaceSettings> settingsList = spaceSettingsService().getSpaceSettings(configurationSpaceSettings());
        return new DeliveryClientFactory().createClients(settingsList);
    }

  */
}
