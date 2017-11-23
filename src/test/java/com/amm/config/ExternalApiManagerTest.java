package com.amm.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan({"com.amm"})
@ActiveProfiles("developer")
public class ExternalApiManagerTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExternalApiManager externalApiManager;

    @Test
    public void loadProperties(){
        final String expectedURL = "https://pixabay.com/api/?key=7104350-0a2ce7523574d82110e80561e&q=yellow+flowers&image_type=photo";
        logger.info(">>>loadProperties...");
        Assert.assertNotNull(externalApiManager.toString());
        final Map<String,String> pathParamsMap = new HashMap<>();
        pathParamsMap.put("{id}","17104350-0a2ce7523574d82110e80561e");
        final String obtainedURL = externalApiManager.getURL("pixabay", "getPhotos",pathParamsMap);
        Assert.assertNotNull(obtainedURL);
        Assert.assertEquals(expectedURL,obtainedURL);
        logger.info(">>>Pixabay url '{}'",obtainedURL);
    }
}
