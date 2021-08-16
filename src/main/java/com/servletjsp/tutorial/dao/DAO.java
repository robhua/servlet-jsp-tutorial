/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servletjsp.tutorial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.servletjsp.tutorial.constant.DBFields.PRODUCT;
import com.servletjsp.tutorial.constant.DBTables;

import context.DBContext;
import entity.Account;
import entity.Category;
import entity.Product;

/**
 *
 * @author Admin
 */
public class DAO {

    private static final String SELECT_ALL_PRODUCT =        " SELECT * " + 
                                                            " FROM " + 
                                                                DBTables.PRODUCT;

    private static final String SELECT_TOP_3_PRODUCT =      " SELECT * " + 
                                                            " FROM " +
                                                                DBTables.PRODUCT + 
                                                            " LIMIT 0, 3 ";

    private static final String NEXTT_TOP_3_PRODUCT =       " SELECT * " + 
                                                            " FROM " + 
                                                                    DBTables.PRODUCT + 
                                                            " ORDER BY " + 
                                                                    PRODUCT.PRODUCT_ID + 
                                                            " OFFSET ? ROWS  FETCH NEXT 3 ROWS ONLY ";
    private static final String SELECT_PRODUCT_BY_CATEGORY =   " SELECT * " +
                                                        " FROM " + 
                                                            DBTables.PRODUCT +  
                                                        " WHERE " + 
                                                            PRODUCT.CATEGORY_ID + " = ? " ;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * Get all product.
     * 
     * @return List<Product>
     */
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(SELECT_ALL_PRODUCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(createProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getTop3() {
        List<Product> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(SELECT_TOP_3_PRODUCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(createProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getNext3Product(int amount) {
        List<Product> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(NEXTT_TOP_3_PRODUCT);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(createProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(SELECT_PRODUCT_BY_CATEGORY);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(createProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getProductBySellID(int id) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n" + "where sell_ID = ?";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(createProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n" + "where [name] like ?";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(createProduct(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getProductByID(String id) {
        String query = "select * from product\n" + "where id = ?";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return createProduct(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
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

    public Product getLast() {
        String query = "select * from product order by CATEGORY_ID DESC LIMIT 1";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return createProduct(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public void singup(String user, String pass) {
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

    public void deleteProduct(String pid) {
        String query = "delete from product\n" + "where id = ?";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(String name, String image, String price, String title, String description,
            String category, int sid) {
        String query = "INSERT [dbo].[product] \n"
                + "([name], [image], [price], [title], [description], [cateID], [sell_ID])\n" + "VALUES(?,?,?,?,?,?,?)";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setInt(7, sid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editProduct(String name, String image, String price, String title, String description, String category,
            String pid) {
        String query = "update product\n" + "set [name] = ?,\n" + "[image] = ?,\n" + "price = ?,\n" + "title = ?,\n"
                + "[description] = ?,\n" + "cateID = ?\n" + "where id = ?";
        try {
            conn = DBContext.getConnection();// mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setString(7, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Product createProduct(ResultSet result) throws SQLException {
        Product _product = new Product();
        _product.setId(rs.getInt(1));
        _product.setName(rs.getString(2));
        _product.setImage(rs.getString(3));
        _product.setPrice(rs.getDouble(4));
        _product.setTitle(rs.getString(5));
        _product.setDescription(rs.getString(6));
        return _product;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();

        for (Product o : list) {
            System.out.println(o);
        }

        for (Category o : listC) {
            System.out.println(o);
        }
    }

}
