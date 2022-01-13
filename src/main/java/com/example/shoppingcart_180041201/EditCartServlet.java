package com.example.shoppingcart_180041201;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class EditCartServlet extends HttpServlet {

    /**
     * Gives the user 2 options - "Edit Item Quantity" and "Add More Items"
     * The "Save" button saves all the edits made to the cart
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Edit Items in Cart</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");

        out.println("<h2>Edit Cart</h2>");

        out.println("<form method = \"post\" action=\"EditItemQuantityServlet\">");
        out.println("<input type=\"submit\" value = \"Edit Item Quantity\">");
        out.println("</form>");

        out.println("<form method = \"post\" action=\"AddItemCartServlet\">");
        out.println("<input type=\"submit\" value = \"Add More Items\">");
        out.println("</form>");

        out.println("<form method = \"post\" action=\"EditCartServlet\">");
        out.println("<input type=\"submit\" value = \"Save\">");
        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>");
    }


    /**
     * A function for saving the edited information regarding the cart
     * Is triggered when the "Save" button is pressed
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("homePage.html");
        rd.forward(request, response);
    }
}
