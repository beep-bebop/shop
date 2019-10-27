package org.shop.servlets;

import org.shop.domain.Account;
import org.shop.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JPetStoreDemoServlet extends HttpServlet {
    private static final String JPETSTOREDEMO = "/WEB-INF/jsp/common/JPetStoreDemo.html";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= request;
            String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转帮助界面";
            logService.insertLogInfo(account.getUserId(), logInfo);
        }

        request.getRequestDispatcher(JPETSTOREDEMO).forward(request, response);
    }
}
