package servlet;

import deal.Apply;
import deal.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplyServlet extends HttpServlet {


        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Apply apply = new Apply();
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                response.setHeader("Content-Type","text/html;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");
                apply.getApplyInfo(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Apply apply = new Apply();
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                response.setHeader("Content-Type","text/html;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");
                apply.apply(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


