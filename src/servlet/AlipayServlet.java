package servlet;



import deal.Alipay;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;




public class AlipayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Alipay alipay = new Alipay();
        try{
            alipay.flushOrder(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Alipay alipay = new Alipay();
        try {
            alipay.pay(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
