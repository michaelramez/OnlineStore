package signin;

import customer.Customer;
import customer.CustomerHandler;
import database.WebDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signin extends HttpServlet {

    WebDatabase con = new WebDatabase();
    CustomerHandler customerHandler = new CustomerHandler();
    HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username = (request.getParameter("username"));
        String password = (request.getParameter("pass"));

        Customer customer = new Customer(username, password);

//            int id = customerHandler.addUserData(customer);
        try {

            if (customerHandler.checkLogin(customer)) {
//                if (con.isadmin(customer)) {
//            if (true) {
//            if (false) {
//                HttpSession session = request.getSession(true);
//                session.setAttribute("admin", "yes");
//                session.setAttribute("username", customer.getUsername());
//
//                session.setAttribute("islogin", "yes");
//                response.sendRedirect("indexProducts.jsp");
//
//            } else {
                out.print("user");
                HttpSession session = request.getSession(true);
                session.setAttribute("user", "yes");
                session.setAttribute("islogin", "yes");
                session.setAttribute("user_id", 0);
                session.setAttribute("username", customer.getUsername());
                response.sendRedirect("Home.jsp");
//            }

            } else {

                out.print("username doesnot match");
            }
        } catch (Exception e) {
        }
    }

}
