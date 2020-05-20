package servlet;


import deal.ResultInquiry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResultInquiryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInquiry resultInquiry = new ResultInquiry();
        try{
            resultInquiry.getScore(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
