package org.shop.servlets;

import org.shop.domain.Account;
import org.shop.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameIsExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("username");
            Account account = new Account();
            account.setUserId(username);
            AccountService accountService = new AccountService();

            response.setContentType("text/xml");
            PrintWriter out = response.getWriter();

            if(accountService.getAccount(account.getUserId()) != null){
                out.println("<msg>Exist</msg>");
            }
            else {
                out.println("<msg>NotExist</msg>");
            }
            out.flush();
            out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
