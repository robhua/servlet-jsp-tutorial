package com.servletjsp.tutorial.dao;

import java.util.List;

import entity.Product;

/**
 * 
 * @author Admin
 *
 */
public interface ProductDao {
    /**
     * Get all product.
     * 
     * @return {@code List<Category>}
     */
    public List<Product> getAllProduct();

    /**
     * Get top 3 products
     * 
     * @return {@code List<Category>}
     */
    public List<Product> getTop3();

    /**
     * Get next 3 product
     * 
     * @param amount
     * @return {@code List<Category>}
     */
    public List<Product> getNext3Product(int amount);

    /**
     * Get product by category id
     * 
     * @param cid Category id
     * @return {@code List<Category>}
     */
    public List<Product> getProductByCID(String cid);

    /**
     * Get product by sell id
     * 
     * @param id sell id
     * @return {@code List<Category>}
     */
    public List<Product> getProductBySellID(int id);

    /**
     * Get product by name
     * 
     * @param txtSearch product name
     * @return {@code List<Category>}
     */
    public List<Product> searchByName(String txtSearch);

    /**
     * Get product by id
     * 
     * @param id product id
     * @return Product
     */
    public Product getProductByID(String id);

    /**
     * Get last product
     * 
     * @return {@code Product}
     */
    public Product getLast();

    /**
     * Delete product by id
     * 
     * @param pid Product id
     */
    public void deleteProduct(String pid);

    /**
     * Insert new product
     * 
     * @param name
     * @param image
     * @param price
     * @param title
     * @param description
     * @param category
     * @param sid
     */
    public void insertProduct(String name, String image, String price, String title, String description,
            String category, int sid);

    /**
     * Edit a product
     * 
     * @param name
     * @param image
     * @param price
     * @param title
     * @param description
     * @param category
     */
    public void editProduct(String name, String image, String price, String title, String description, String category,
            String pid);
}
