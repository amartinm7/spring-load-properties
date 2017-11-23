package com.amm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Configuration of the Swagger parameters and the meta data.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final Logger logger = LoggerFactory.getLogger( getClass() );

    /**
     * Gets the API.
     * @return docket
     */
    @Bean
    public Docket api() {
        logger.debug("Loading Swagger2 configuration...");
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.any())
                .paths(regex("/api.*"))
                .build()
                //.apiInfo(metaData());//TODO fix
                .apiInfo(null);
    }

//    /**
//     * Gets the meta data.
//     * @return api info
//     */
//    private ApiInfo metaData() {
//        logger.debug("Loading Swagger2 API info...");
//        return new ApiInfo("pixabay Rest API",
//                            "pixabay Rest API",
//                            "1.0",
//                            "Terms of service",
//                            new Contact("pixabay Team", "https://github.com/amartinm7/spring-load-properties", "amartinm7@gmail.com"),
//                            "Apache License Version 2.0",
//                            "https://www.apache.org/licenses/LICENSE-2.0");
//    }
}
