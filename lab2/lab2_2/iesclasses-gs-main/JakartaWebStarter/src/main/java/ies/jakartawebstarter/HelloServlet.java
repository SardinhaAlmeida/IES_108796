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

        PrintWriter out = response.getWriter();
   
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         
        try {
          // Write some content
          out.println("<html>");
          out.println("<head>");
          out.println("<title>LoginServlet</title>");
          out.println("</head>");
          out.println("<body>");
       
          if(username!=null && password!=null) {
            out.println("<h2>Welcome Friend</h2>");
          }else{
            out.println("<h2>Validate your self again.</h2>");
          }
           
          out.println("</body>");
          out.println("</html>");
        } finally {
          out.close();
        }
    //     // Hello
    //     PrintWriter out = response.getWriter();
    //     out.println("<html><body>");
    //     out.println("<h1>" + message + "</h1>");
    //     out.println("</body></html>");
    }

    public void destroy() {
    }
}