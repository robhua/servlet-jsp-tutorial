package com.servletjsp.tutorial.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.servletjsp.tutorial.dao.CategoryDao;

import context.DBContext;
import entity.Category;

/**
 * 
 * @author Admin
 *
 */
public class CategoryDaoImpl implements CategoryDao {
    private Connection        conn = null;
    private PreparedStatement ps   = null;
    private ResultSet         rs   = null;

    /**
     * {@inheritDoc}
     */
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<Category>();
        String query = "select * from Category";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
