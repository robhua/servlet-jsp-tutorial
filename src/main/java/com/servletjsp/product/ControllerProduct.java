package com.servletjsp.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Admin
 *
 */
public class ControllerProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
        System.out.println("#########  ControllerProduct.init()");
        super.init();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Code to handler get method for /product.
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO code to handle post method for /product.
        super.doPost(req, resp);
    }
}
