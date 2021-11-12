package control;

import java.util.List;

import com.servletjsp.tutorial.dao.CategoryDao;
import com.servletjsp.tutorial.dao.ProductDao;
import com.servletjsp.tutorial.dao.imp.CategoryDaoImpl;
import com.servletjsp.tutorial.dao.imp.ProductDaoImpl;

import entity.Category;
import entity.Product;

public class CrudDelegator {
    private static CrudDelegator instance = new CrudDelegator();

    private CrudDelegator() {

    }

    public static CrudDelegator getInstance() {
        return instance;
    }

    public List<Category> getAllCategory() {
        CategoryDao categoryDao = new CategoryDaoImpl();
        return categoryDao.getAllCategory();
    }

    public List<Product> getTop3Product() {
        ProductDao dao = new ProductDaoImpl();
        return dao.getTop3();
    }

    public Product getLastProduct() {
        ProductDao dao = new ProductDaoImpl();
        return dao.getLast();
    }
}
