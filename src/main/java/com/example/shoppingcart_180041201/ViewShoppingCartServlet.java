package com.example.shoppingcart_180041201;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ViewShoppingCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Shows the items in the cart along with their quantity
     * Lets user edit and check out the cart
     * Go back to homepage would take the user to the homepage but stores the items and their quantity
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String user = session.getAttribute("currentUser").toString();

        String[] items = new String[4];
        String[] quantity = new String[4];

        for (int i = 0; i < 4; i++) {
            items[i] = session.getAttribute("shoppingCart[" + i + "]").toString();
            quantity[i] = session.getAttribute("itemQuantity[" + i + "]").toString();
            if(quantity[i]==null) quantity[i] = "0";
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Items in Cart</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");

        out.println("<h1>" + user.toUpperCase() + "'s Cart</h1>");

        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Item Name</th>");
        out.println("<th>Quantity</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        for (int i = 0; i < 4; i++) {
            if (items[i] == "yes" && Integer.parseInt(quantity[i]) > 0) {
                out.println("<tr> <td>" + session.getAttribute("itemName[" + i + "]").toString() + "</td> <td>" + quantity[i] + "</td> </tr>");
            }
            else out.println("<tr></tr>");
        }
        out.println("</tbody>");
        out.println("</table>");

        out.println("<form name = \"edit-cart\" method = \"get\" action=\"EditCartServlet\"><input type=\"submit\" value = \"Edit Cart\"></form>");

        out.println("<br>");
        out.println("<form name = \"check-out\" method = \"post\" action=\"CheckOutServlet\"><input type=\"submit\" value = \"Check Out\"></form>");

        out.println("<br>");
        out.println("<form name = \"go-back\" method = \"post\" action=\"GoBackHomeServlet\"><input type=\"submit\" value = \"Go Back to Homepage\"></form>");

        out.println("</BODY>");
        out.println("</HTML>");
    }
}
