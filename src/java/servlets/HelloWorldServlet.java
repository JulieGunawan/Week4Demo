package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author awarsyle
 */
public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get the two parameters from the POST request
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        
        // set the attributes for the JSP
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        
        // validation: if the parameters don't exist or are empty, show the first page again
        if (firstname == null || firstname.equals("") || lastname == null || lastname.equals("")) {
            // set an attribute for a message
            request.setAttribute("message", "Invalid entry. Please enter both your first and last names.");
            
            // forward the request and response objects to the JSP
            // display the form again
            getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp")
                    .forward(request, response);
            return; // very important!
        }

        // display the second page
        getServletContext().getRequestDispatcher("/WEB-INF/sayHello.jsp")
                .forward(request, response);
    }
}
