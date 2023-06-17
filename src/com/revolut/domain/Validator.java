package com.revolut.domain;

import java.net.URI;

public class Validator {

    public static boolean  validateURI(URI uri){
        if(uri == null )
            return false;
        return true;

    }
}
