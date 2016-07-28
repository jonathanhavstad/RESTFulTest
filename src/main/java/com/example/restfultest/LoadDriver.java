package com.example.restfultest;

/**
 * Created by jonathanhavstad on 7/27/16.
 */
public class LoadDriver {
    private static boolean INITIALIZED;

    public static void init() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            INITIALIZED = true;
        } catch (Exception ex) {
            INITIALIZED = false;
            throw ex;
        }
    }

    public static boolean isINITIALIZED() {
        return INITIALIZED;
    }
}
