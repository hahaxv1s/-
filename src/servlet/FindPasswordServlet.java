package servlet;

import deal.FindPassword;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindPasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       /*
        System.out.println("处理get 请求...");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        out.println("<strong>Hello Servlet</strong>");

        */
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       FindPassword findPassword = new FindPassword();

        if(request.getParameter("requestType")!=null) {
            try {

                findPassword.sendCode(response, request);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else{
            try {

                findPassword.find(response, request);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }
}
