/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.catalogo.services;

import java.util.Properties;

/**
 *
 * @author Usuario
 */
public class PropertiesServices {
    // private instance, so that it can be
    // accessed by only by getInstance() method

    private static PropertiesServices instance;
    private Properties properties = new Properties();

    private PropertiesServices() {
        System.out.println("Create PropertiesServices Instance...");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            properties.load(classLoader.getResourceAsStream("main.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PropertiesServices getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (PropertiesServices.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new PropertiesServices();
                }

            }
        }
        return instance;
    }

    /**
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
