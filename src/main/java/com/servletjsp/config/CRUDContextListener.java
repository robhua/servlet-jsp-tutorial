package com.servletjsp.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CRUDContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Code initialize connection to database
        // and store it as a context attribute
        System.out.println("#########  CRUDContextListener.contextDestroyed().");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO code to close the database connection.
        System.out.println("#########  CRUDContextListener.contextInitialized().");
    }

}
