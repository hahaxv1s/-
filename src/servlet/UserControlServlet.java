package servlet;

import deal.UserControl;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserControlServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserControl UserControl = new UserControl();

        //如果是获取用户数据

        switch (request.getParameter("requestType")) {
            case "userData":
                try {
                    UserControl.GetData(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "saveUser":
                try {
                    UserControl.saveUser(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "savePower":
                try {
                    UserControl.savePower(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("defauls");
                break;
        }

    }
}



