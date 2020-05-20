package servlet;

import deal.BuildUserPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BuildUserPage BuildUserPage = new BuildUserPage();
        try {
            BuildUserPage.buildPage(response,request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
