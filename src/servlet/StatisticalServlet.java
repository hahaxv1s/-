package servlet;

import deal.Statistical;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class StatisticalServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (verify(session)) {
            Statistical statistical = new Statistical();
            switch (request.getParameter("type")) {
                case "1":
                    try {
                        statistical.getExcel("Select * from payInfo where paid =1  ", response, "已支付人员清单");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        statistical.getExcel("Select * from payInfo where paid =0 ", response, "未支付人员清单");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    try {
                        statistical.getApplyExcel("Select * from applyInfo ", response, "报名表");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println(request.getParameter("type"));
            }

        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (verify(session)) {
        Statistical statistical = new Statistical();
        try {
            statistical.sta(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

    boolean verify(HttpSession session){//敏感操作，确认权限
        String menuItem= (String) session.getAttribute("menuItem"); //拿到session里面当前用户的菜单栏列表,不能为空
        String[] itemList = menuItem.split("!");//把列表转换为数组，方便遍历。
        boolean res = Arrays.asList(itemList).contains("Statistical");
        return res;
    }
}
