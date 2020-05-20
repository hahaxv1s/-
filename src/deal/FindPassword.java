package deal;

import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Random;

public class FindPassword {

    public void find(HttpServletResponse response, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        System.out.println(request.getParameter("code"));
        System.out.println(session.getAttribute("code"));
        if(request.getParameter("code").equals(session.getAttribute("code"))){
            dbManage db = new dbManage();
            String sqlStatement=null;
            if(request.getParameter("userName")!=null && request.getParameter("password")!=null ){
                sqlStatement="update user set password='"+request.getParameter("password")+"' where username='"+request.getParameter("userName")+"';";
            }
            else {
                sqlStatement=null;
            }
            if (sqlStatement!=null){
                db.setUser(sqlStatement);
                response.sendRedirect("/untitled/index.jsp");
            }
        }
    }

    public void sendCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String code;
        code=generateRandCode(4);
        session.setAttribute("code",code);//把验证码session中
        sendHTMLFormattedEmail(request,code);//发邮件验证码
        PrintWriter out = response.getWriter();
        out.write("发送成功");
        out.close();
    }

    public static String generateRandCode(int n){
        StringBuffer res=new StringBuffer();
        String str="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd=new Random();
        for(int i=0;i<n;i++){
            int index=rnd.nextInt(str.length());// 从str中随机选取一个值的下标
            char ch=str.charAt(index);//返回位于字符串的指定索引处的字符,该字符串的索引从零开始。
            res.append(ch);	//连接字符
        }
        return res.toString();//将其转换为String类型
    }

    public void sendHTMLFormattedEmail(HttpServletRequest request,String code) throws Exception {
        String userName = request.getParameter("userName");
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.163.com");//邮箱的SMTP服务器
        email.setAuthentication("pengboabc@163.com","test6666");//设置发送人的邮箱和用户名和授权码(授权码是自己设置的)
        email.addTo(dbManage.getEmail(userName));//设置收件人
        email.setCharset("UTF-8");
        email.setFrom("pengboabc@163.com","某某网站密码找回");//发送人的邮箱为自己的，用户名可以随便填
        email.setSubject("这是您找回密码的验证信息");//设置发送主题
        email.setMsg("您好，你正在找回某某某网站的账号，这是你的四位验证码：    "+code+"    此验证码10分钟内有效");//设置发送内容
        email.send();
    }
}
