package com.example;

import org.apache.log4j.Level;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 * Created by jonathanhavstad on 7/14/16.
 */
@ApplicationPath("rest")
public class RESTFulTestApplication extends Application {
    private static Logger logger = Logger.getLogger("RESTFulTestApplication");

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(RESTFulTestResource.class);

        logger.log(java.util.logging.Level.ALL, "Loaded classes for RESTFulTestApplication");

        return classes;
    }
}
