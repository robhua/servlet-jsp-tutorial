package com.servletjsp.tutorial.dao;

import entity.Account;

/**
 * 
 * @author Admin
 *
 */
public interface UidDao {
    /**
     * Check login return account if exists
     * 
     * @param user User name
     * @param pass Password
     * @return {@code Account}
     */
    public Account login(String user, String pass);

    /**
     * Check acount exists
     * 
     * @param user User name
     * @return Account
     */
    public Account checkAccountExist(String user);

    /**
     * Execute sign up
     * 
     * @param user User name
     * @param pass Password
     */
    public void signUp(String user, String pass);
}
