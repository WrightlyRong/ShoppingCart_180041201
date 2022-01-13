package com.example.shoppingcart_180041201;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class EditItemQuantityServlet extends HttpServlet {

    /**
     * A function for saving the quantity of the items in the cart
     * Setting the item quantity to 0 will remove the item
     * Takes the user to the Edit Cart Interface after saving
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String[] items = new String[4];
        String[] quantity = new String[4];

        for (int i = 0; i < 4; i++) {
            items[i] = session.getAttribute("shoppingCart[" + i + "]").toString();
            if (items[i] == "yes") {
                if (request.getParameter("number[" + i + "]") != null) {
                    quantity[i] = request.getParameter("number[" + i + "]");
                    session.setAttribute("itemQuantity[" + i + "]", quantity[i]);
                }
                else session.setAttribute("itemQuantity[" + i + "]", session.getAttribute("itemQuantity[" + i + "]"));
            }
            else session.setAttribute("itemQuantity[" + i + "]", "0");
        }
        RequestDispatcher rd = request.getRequestDispatcher("EditCartServlet");
        rd.forward(request, response);
    }


    /**
     * A function for showing the added items and setting their quantity
     * The "Save" button triggers the doGet method
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String[] items = new String[4];
        String[] quantity = new String[4];

        for (int i = 0; i < 4; i++) {
            items[i] = session.getAttribute("shoppingCart[" + i + "]").toString();
            quantity[i] = session.getAttribute("itemQuantity[" + i + "]").toString();
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Edit Items in Cart</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");

        out.println("<h2>Edit Cart</h2>");
        out.println("<h3>Set Quantity to 0 to remove the item from the cart</h2>");

        out.println("<form method = \"get\" action=\"EditItemQuantityServlet\">");

        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Item Name</th>");
        out.println("<th>Quantity</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        for (int i = 0; i < 4; i++) {
            if (items[i] == "yes") {
                out.println("<tr>");
                out.println("<td>" + session.getAttribute("itemName[" + i + "]").toString() + "</td> ");
                out.println("<td> <input type=\"number\" name=\"number[" + i + "]\" value=\"0\"> </td>");
                out.println("</tr>");
            }
        }
        out.println("</tbody>");
        out.println("</table>");

        out.println("<input type=\"submit\" value = \"Save\">");

        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>");
    }
}
