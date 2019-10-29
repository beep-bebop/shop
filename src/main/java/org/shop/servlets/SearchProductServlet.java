package org.shop.servlets;

import org.shop.domain.Account;
import org.shop.domain.Product;
import org.shop.service.CatalogService;
import org.shop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH_PRODUCTS = "/WEB-INF/catalog/SearchProducts.jsp";

    private String keyword;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyword = request.getParameter("keyword");
        CatalogService service = new CatalogService();
        List<Product> productList = service.searchProductList(keyword);
        HttpSession session = request.getSession();
        //保存数据
        if(productList == null) {
            Product none = new Product();
            none.setProductId("Not Exist");
            none.setName("Not Exist");
            productList.add(none);
            session.setAttribute("keyword", "Not Exist");
            session.setAttribute("productList", productList);
        }
        else {
            session.setAttribute("keyword", keyword);
            session.setAttribute("productList", productList);
        }

        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= request;
            String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 查找商品" + "  " + productList;
            logService.insertLogInfo(account.getUserId(), logInfo);
        }


        //跳转页面
        request.getRequestDispatcher(SEARCH_PRODUCTS).forward(request, response);
    }
}
