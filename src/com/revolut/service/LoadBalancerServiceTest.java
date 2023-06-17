package com.revolut.service;

import com.revolut.domain.exception.LimitExceedException;
import com.revolut.domain.exception.URIValidationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoadBalancerServiceTest {

    LoadBalancerService loadBalancerService;

    @BeforeEach
    public void setUp(){
        loadBalancerService = new LoadBalancerService();
    }


    @Test
    void addUrls() {
        assertTrue(loadBalancerService.addUrls(URI.create("http;//localhost:8080")));
        Set<URI> existingURIs = loadBalancerService.getAlreadyExistingURIs();
        assertEquals(existingURIs, Set.of(URI.create("http;//localhost:8080")));
    }

    @Test
    void addNullUrl(){
        assertThrows(URIValidationException.class, ()-> loadBalancerService.addUrls(null));
    }

    @Test
    void addMoreThanLimitUrl(){
        loadBalancerService.addUrls(URI.create("http;//localhost1:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost2:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost3:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost4:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost5:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost6:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost7:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost9:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost8:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost10:8080"));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost1:8080")));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost2:8080")));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost3:8080")));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost4:8080")));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost5:8080")));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost6:8080")));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost7:8080")));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost8:8080")));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost9:8080")));
        assertTrue(loadBalancerService.isURIExist(URI.create("http;//localhost10:8080")));
        assertThrows(LimitExceedException.class, ()->loadBalancerService.addUrls(URI.create("http://exceededurls:8080")));
    }
    @Test
    void addExistingUrl(){
        loadBalancerService.addUrls(URI.create("http;//localhost1:8080"));
        assertThrows(URIValidationException.class, ()->loadBalancerService.addUrls(URI.create("http;//localhost1:8080")));
    }

    @Test
    void getRandomURI(){
        loadBalancerService.addUrls(URI.create("http;//localhost1:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost2:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost3:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost4:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost5:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost6:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost7:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost9:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost8:8080"));
        loadBalancerService.addUrls(URI.create("http;//localhost10:8080"));
        URI firstURI = loadBalancerService.getUrl();
        URI secondURI = loadBalancerService.getUrl();
        assertNotEquals(firstURI, secondURI);
    }
}