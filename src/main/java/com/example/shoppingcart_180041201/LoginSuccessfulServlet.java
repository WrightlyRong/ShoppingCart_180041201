package com.example.shoppingcart_180041201;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class LoginSuccessfulServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * If login is successful, then takes the user to their homepage
     * Allows user to put items in their shopping cart, and viewing it
     * Items added will have an initial quantity of 1, users can further edit with the "Edit Cart" option from the "View Cart" page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("index.html");
        } else {
            String[] items = new String[4];
            String[] quantity = new String[4];
            String[] itemName = {"Table", "Chair", "Lamp", "Bed"};

            if (session.getAttribute("checkOut").toString().equals("selected") || session.getAttribute("checkOut").toString().equals("checked")) {
                for (int i = 0; i < 4; i++) {
                    items[i] = request.getParameter("item[" + i + "]");
                    if (items[i] == null) {
                        items[i] = "no";
                        quantity[i] = "0";
                    } else {
                        items[i] = "yes";
                        quantity[i] = "1";
                    }
                    session.setAttribute("shoppingCart[" + i + "]", items[i]);
                    session.setAttribute("itemQuantity[" + i + "]", quantity[i]);
                    session.setAttribute("itemName[" + i + "]", itemName[i]);
                }
                session.setAttribute("checkOut", "notChecked");
            }
            RequestDispatcher rd = request.getRequestDispatcher("ViewShoppingCartServlet");
            rd.forward(request, response);
        }
    }
}
