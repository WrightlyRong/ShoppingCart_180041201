package com.example.shoppingcart_180041201;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class GoBackHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Takes the user to the homepage while making sure that the items added to cart and their quantity are safe
     * Resets all shopping items, their quantity and checkOut if View Cart shows no item
     * If not reset, then the checkOut(="notChecked") prevents the user from making any changes to the cart from the homepage
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Boolean flag = false;

        String[] items = new String[4];
        String[] quantity = new String[4];

        for (int i = 0; i < 4; i++) {
            items[i] = session.getAttribute("shoppingCart[" + i + "]").toString();
            quantity[i] = session.getAttribute("itemQuantity[" + i + "]").toString();
        }

        for (int i = 0; i < 4; i++) {
            if (items[i] == "yes" && Integer.parseInt(quantity[i]) > 0) {
                flag = true;
            }
        }

        if (flag == false) {
            session.setAttribute("checkOut", "selected");
            for (int i = 0; i < 4; i++) {
                session.setAttribute("itemQuantity[" + i + "]", "0");
                session.setAttribute("shoppingCart[" + i + "]", "no");
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("homePage.html");
        rd.forward(request, response);
    }
}
