package com.servletjsp.tutorial.dao;

import java.util.List;

import entity.Category;

/**
 * 
 * @author Admin
 *
 */
public interface CategoryDao {
    /**
     * Get all category
     * 
     * @return {@code List<Category>}
     */
    public List<Category> getAllCategory();
}
