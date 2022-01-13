package com.example.shoppingcart_180041201;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final String name = "tasmia";
    private final String password = "tasmia123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Does login against hardcoded values
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username-field");
        String pw = request.getParameter("pw-field");

        if(username.equals(name) && pw.equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", username);
            session.setMaxInactiveInterval(30*60);

            session.setAttribute("checkOut", "selected");

            RequestDispatcher rd = request.getRequestDispatcher("homePage.html");
            rd.forward(request,response);
        }else {
            response.sendRedirect("index.html");
        }
    }
}
