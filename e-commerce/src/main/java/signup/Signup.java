package signup;

import customer.Customer;
import customer.CustomerHandler;
import database.WebDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet {

    WebDatabase con = WebDatabase.getDatabaseInstance();
    CustomerHandler customerHandler =CustomerHandler.getCustomerHandler();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        String username = request.getParameter("username");

        String mail = request.getParameter("mail");

        String password = request.getParameter("password");

        String job = request.getParameter("job");

        String birthday = request.getParameter("birthday");

        String phone = request.getParameter("phone");

        int creditLimit = Integer.parseInt(request.getParameter("creditLimit"));

        String address = request.getParameter("address");

        Customer customer = new Customer(name, username, mail, password, job, birthday, phone, creditLimit, address);

       if (customerHandler.addUserData(customer) == true && username != null) {

           response.sendRedirect("Login.html");
        } else {
            out.print(" useralready exist");
        }
    }

}
