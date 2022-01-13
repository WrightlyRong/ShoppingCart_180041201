package com.example.shoppingcart_180041201;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CheckOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    /**
     * A function for checking out
     * Empties the cart by removing the items from the cart and setting the quantity to 0
     * Returns user to their homepage
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        session.setAttribute("checkOut", "checked");

        String[] items = new String[4];

        for (int i = 0; i < 4; i++) {
            items[i] = session.getAttribute("shoppingCart[" + i + "]").toString();
            if (items[i] == "yes") {
                session.setAttribute("itemQuantity[" + i + "]", "0");
                session.setAttribute("shoppingCart[" + i + "]", "no");
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("homePage.html");
        rd.forward(request, response);
    }
}
