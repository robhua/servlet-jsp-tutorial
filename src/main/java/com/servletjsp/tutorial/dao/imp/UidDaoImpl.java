package com.servletjsp.tutorial.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.servletjsp.tutorial.dao.UidDao;

import context.DBContext;
import entity.Account;

/**
 * 
 * @author Admin
 *
 */
public class UidDaoImpl implements UidDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * {@inheritDoc}
     */
    public Account login(String user, String pass) {
        String query = "select * from UID\n" + "where USER_NAME = ?\n" + "and PASSWORD = ?";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Account checkAccountExist(String user) {
        String query = "select * from account\n" + "where [user] = ?\n";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void signUp(String user, String pass) {
        String query = "insert into account\n" + "values(?,?,0,0)";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
