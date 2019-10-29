package org.shop.servlets;

import org.shop.domain.Product;
import org.shop.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CategoryShowJsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        System.out.println(categoryId+"out");
        CatalogService catalogService = new CatalogService();
        List<Product> productList = catalogService.getProductListByCategory(categoryId);
        String resp = "Product ID      Name\n";
        int i = 0;
        while(i < productList.size()){
            Product product = productList.get(i);
            resp += product.getProductId() + "      " + product.getName() + "\n";
            i++;
        }

        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.write(resp);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
