package com.revolut.service;

import com.revolut.domain.exception.LimitExceedException;
import com.revolut.domain.exception.URIValidationException;
import com.revolut.model.LoadBalancer;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.revolut.domain.Validator.validateURI;

public class LoadBalancerService {

    private static final int URI_LIMIT = 10;
    LoadBalancer loadBalancer;

    LoadBalancerService(){
        loadBalancer = new LoadBalancer();
        loadBalancer.setUrls(new HashSet<>());
    }
    public boolean addUrls(URI url){
        Set<URI> existingUrls = loadBalancer.getUrls();
        validte(url, existingUrls);
        existingUrls.add(url);//O(1)

        return true;
    }

    public Set<URI> getAlreadyExistingURIs(){
        return loadBalancer.getUrls();
    }

    public URI getUrl(){
        int index = new Random().nextInt(loadBalancer.getUrls().size()); //O(1)
        return listOfUrl.get(index); //O(1) O(n)
    }

    public boolean isURIExist(URI uri){
        return loadBalancer.getUrls().contains(uri);
    }

    private void validte(URI url, Set<URI> existingUrls) {
        if(!validateURI(url))
            throw new URIValidationException("error while validating URI, uri:"+ url);
        if(existingUrls.size() >= URI_LIMIT)
            throw new LimitExceedException("size exceeded. limit uris are:"+URI_LIMIT);
        if(existingUrls.contains(url))
            throw new URIValidationException("URI already present in loadbalancer. uri"+url);
    }
}
