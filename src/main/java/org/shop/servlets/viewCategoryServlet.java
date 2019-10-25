package org.shop.servlets;

import net.sf.json.JSONArray;
import org.shop.domain.Category;
import org.shop.domain.Product;
import org.shop.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class viewCategoryServlet extends HttpServlet {
    private static final String VIEW_CATEGORY = "/WEB-INF/catalog/Category.html";
    private  String categoryId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryId = req.getParameter("categoryId");
        CatalogService service = new CatalogService();
        Category category = service.getCategory(categoryId);
        List<Product> productList = service.getProductListByCategory(categoryId);
        JSONArray jsonList=JSONArray.fromObject(productList);
        System.out.println(jsonList);
        //将list转换为json对象
        String jsonStr="{'userVO':"+jsonList.toString()+"}";
        PrintWriter out = resp.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
        req.getRequestDispatcher(VIEW_CATEGORY).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
