package org.shop.servlets;

import org.shop.domain.Cart;
import org.shop.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class UpdateCartJSServlet extends HttpServlet {

    private Cart cart;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");

        Iterator<CartItem> cartItemIterator = cart.getAllCartItems();

        while (cartItemIterator.hasNext()) {
            //产品数量增加
            CartItem cartItem = (CartItem) cartItemIterator.next();
            String itemId = cartItem.getItem().getItemId();

            try {
                int quantity = Integer.parseInt((String) request.getParameter("quantity"));
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItemIterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("cart", cart);


        Cart cart2 = (Cart)session.getAttribute("cart");
        Iterator<CartItem> cartItemIterator2 = cart2.getAllCartItems();
        String quantityAll = "";
        while (cartItemIterator2.hasNext()) {
            //产品数量增加
            CartItem cartItem2 = cartItemIterator2.next();
            int quantity2 = cartItem2.getQuantity();
            quantityAll += quantity2 + "," + cartItem2.getTotal() + "," + cart2.getSubTotal();

        }

        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.write(quantityAll);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
