/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servletjsp.tutorial.constant.WebConstant;
import com.servletjsp.tutorial.dao.CategoryDao;
import com.servletjsp.tutorial.dao.ProductDao;
import com.servletjsp.tutorial.dao.imp.CategoryDaoImpl;
import com.servletjsp.tutorial.dao.imp.ProductDaoImpl;

import entity.Account;
import entity.Category;
import entity.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManagerControl", urlPatterns = { WebConstant.URL_PATTERN_MANAGER })
public class ManagerControl extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(WebConstant.CONTENT_TYPE_TEXT_HTML);
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int id = a.getId();
        ProductDao dao = new ProductDaoImpl();
        List<Product> list = dao.getProductBySellID(id);
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> listC = categoryDao.getAllCategory();

        request.setAttribute("listCC", listC);
        request.setAttribute("listP", list);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        forwardRequest(request, response, "ManagerProduct.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        forwardRequest(request, response, "ManagerProduct.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    protected void forwardRequest(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
