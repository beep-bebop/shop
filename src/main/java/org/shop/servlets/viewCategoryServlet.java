package org.shop.servlets;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.shop.domain.Account;
import org.shop.domain.Category;
import org.shop.domain.Product;
import org.shop.service.CatalogService;
import org.shop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;


public class viewCategoryServlet extends HttpServlet {
    private static final String VIEW_CATEGORY = "/WEB-INF/catalog/Category.jsp";
    private String categoryId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryId=req.getParameter("categoryId");

        CatalogService service = new CatalogService();
        Category category = service.getCategory(categoryId);
        List<Product> productList = service.getProductListByCategory(categoryId);
        JSONArray jsonList=JSONArray.fromObject(productList);
        System.out.println(jsonList);
        //将list转换为json对象
        String jsonStr="{'productList':"+jsonList.toString()+"}";
        PrintWriter out = resp.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
//        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
//        categoryId = new String(sb);
//        //将json字符串转换为json对象
//        JSONObject json=JSONObject.fromObject(sb.toString());

        HttpSession session = req.getSession();
        session.setAttribute("category", category);
        session.setAttribute("productList", productList);

        //HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");

//        if(account != null){
//            HttpServletRequest httpRequest= req;
//            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
//                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());
//
//            LogService logService = new LogService();
//            String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转到商品种类 " + category;
//            logService.insertLogInfo(account.getUserId(), logInfo);
//        }

        //跳转页面
        req.getRequestDispatcher(VIEW_CATEGORY).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
