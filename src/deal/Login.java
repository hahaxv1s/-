package deal;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Login {
    public void login(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        dbManage dbjudge = new dbManage();
        String[] result = dbjudge.logjudge(userName);

       if(password.equals(result[0])) {
           HttpSession session = request.getSession();
           session.setAttribute("menuItem",result[1]);//把数据库读过来的值中的菜单栏列表放到session中，这是个字符串，用逗号分割
           session.setAttribute("userName",userName);//把账号放进session中去
           request.getRequestDispatcher("/userpage.jsp").forward(request,response);

       }

       else {request.getRequestDispatcher("/LogErroPage.jsp").forward(request,response);}
    }
}
