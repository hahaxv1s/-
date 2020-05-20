package servlet;

import deal.Login;
import deal.Register;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Register register = new Register();
        if(request.getParameter("requestType")!=null) {
            try {
                register.sendCode(request, response);
            } catch (Exception e) { }
        }
        else{
                try {
                    register.newUser(request,response);
                } catch (Exception e) {
                }

        }
    }
}
