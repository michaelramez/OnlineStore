package signin;

import customer.Customer;
import customer.CustomerHandler;
import database.WebDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signin extends HttpServlet {

    WebDatabase con = new WebDatabase();
    CustomerHandler customerHandler =CustomerHandler.getCustomerHandler();
    HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username = (request.getParameter("username"));
        String password = (request.getParameter("pass"));

        Customer customer = new Customer(username, password);

        try {

            if (customerHandler.checkLogin(customer)) {
                out.print("user");
                HttpSession session = request.getSession(true);
                session.setAttribute("user", "yes");
                session.setAttribute("islogin", "yes");
                session.setAttribute("user_id", 0);//essraa i make db fuction for get login user id
                session.setAttribute("username", customer.getUsername());
                response.sendRedirect("Home.jsp");

            } else {

                out.print("please check userName Or Password");
            }
        } catch (Exception e) {
        }
    }

}
