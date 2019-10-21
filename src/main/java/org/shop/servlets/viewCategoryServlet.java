package org.shop.servlets;

import org.shop.domain.Category;
import org.shop.domain.Product;
import org.shop.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class viewCategoryServlet extends HttpServlet {
    private static final String VIEW_CATEGORY = "/WEB-INF/catalog/Category.jsp";
    private  String categoryId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryId = req.getParameter("categoryId");
        CatalogService service = new CatalogService();
        Category category = service.getCategory(categoryId);
        List<Product> productList = service.getProductListByCategory(categoryId);
        HttpSession session = req.getSession();
        session.setAttribute("category",category);
        session.setAttribute("productList",productList);
        req.getRequestDispatcher(VIEW_CATEGORY).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
