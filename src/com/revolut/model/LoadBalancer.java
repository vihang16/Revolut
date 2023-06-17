package com.revolut.model;

import java.net.URI;
import java.util.Set;

public class LoadBalancer {

    private Set<URI> urls;

    public Set<URI> getUrls() {
        return urls;
    }

    public void setUrls(Set<URI> urls) {
        this.urls = urls;
    }
}
