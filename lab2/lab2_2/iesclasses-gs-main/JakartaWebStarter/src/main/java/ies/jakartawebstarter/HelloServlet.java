package ies.jakartawebstarter;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        response.setStatus(HttpServletResponse.SC_OK);
   
        message = request.getParameter("msg");
       
        if (message != null) 
        {
            response.getWriter().println("<h1>" + message + "</h1>");
        } 
        else 
        {
            response.getWriter().println("<h1>Hi!</h1>"); 
        } 
          
    }

    public void destroy() {
    }
}