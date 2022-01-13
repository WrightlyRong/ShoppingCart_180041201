package com.example.shoppingcart_180041201;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class AddItemCartServlet extends HttpServlet {

    /**
     * A function for saving the added items and their quantity in session
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
            if (items[i] == "no") {
                if (request.getParameter("numberAdd[" + i + "]") != null) {
                    quantity[i] = request.getParameter("numberAdd[" + i + "]");
                    if (Integer.parseInt(quantity[i]) > 0) {
                        session.setAttribute("itemQuantity[" + i + "]", quantity[i]);
                        session.setAttribute("shoppingCart[" + i + "]", "yes");
                    }
                } else session.setAttribute("itemQuantity[" + i + "]", "0");
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("EditCartServlet");
        rd.forward(request, response);
    }


    /**
     * A function for showing the Items that user has not saved in the Cart so that they may add that item
     * The "Save" button triggers the get method and sends the user back to the Edit Cart Interface
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
        out.println("<TITLE>Add Items to Cart</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");

        out.println("<h2>Add Items to Cart</h2>");

        out.println("<form method = \"get\" action=\"AddItemCartServlet\">");

        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Item Name</th>");
        out.println("<th>Quantity</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        for (int i = 0; i < 4; i++) {
            if (items[i] == "no") {
                out.println("<tr>");
                out.println("<td>" + session.getAttribute("itemName[" + i + "]").toString() + "</td> ");
                out.println("<td> <input type=\"number\" name=\"numberAdd[" + i + "]\" value=\"0\"> </td>");
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
