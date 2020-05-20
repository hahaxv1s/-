package deal;



import deal.PayConfig.PayConfig;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

public class Register {

    public void sendCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String code;
        code=generateRandCode(4);
        session.setAttribute("code",code);//把验证码session中
        session.setAttribute("email",request.getParameter("email"));//把验证码session中

        sendHTMLFormattedEmail(request,code);//发邮件验证码
        PrintWriter out = response.getWriter();
        out.write("发送成功");
        out.close();
    }


//产生随机验证码的函数
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

    public void sendHTMLFormattedEmail(HttpServletRequest request,String code) throws EmailException, MalformedURLException {

        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.163.com");//邮箱的SMTP服务器
        email.setAuthentication("*****","*****");//设置发送人的邮箱和用户名和授权码(授权码是自己设置的)
        email.addTo(request.getParameter("email"));//设置收件人
        email.setCharset("UTF-8");
        email.setFrom("pengboabc@163.com","某某注册网站");//发送人的邮箱为自己的，用户名可以随便填
        email.setSubject("这是新用户注册的验证信息");//设置发送主题
        email.setMsg("您好，你正在注册某某某网站的账号，这是你的四位验证码：    "+code+"    此验证码10分钟内有效");//设置发送内容
        email.send();
}


    public void newUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        if(request.getParameter("code").equals(session.getAttribute("code"))){
            dbManage db = new dbManage();
            String sqlStatement=null;
            if(request.getParameter("userName")!=null && request.getParameter("password")!=null && request.getParameter("email")!=null){
                sqlStatement="INSERT INTO user VALUES ('"+request.getParameter("userName")+"','"+request.getParameter("password")+"','apply!pay!resultInquiry','"+request.getParameter("email")+"')";

            }
            else {
                sqlStatement=null;
            }
            if (sqlStatement!=null){
                db.setUser(sqlStatement);
                sqlStatement="INSERT INTO score VALUES ('"+request.getParameter("userName")+"','-1','-1','-1','-1')";
                db.setUser(sqlStatement);
                get_out_trade_no(request.getParameter("userName"));
                response.sendRedirect("/untitled/index.jsp");
            }
        }
    }

    static void get_out_trade_no(String userName) throws Exception {//注册的时候就生成订单号
        dbManage db = new dbManage();
        String out_trade_no = dbManage.get_out_trade_no(userName);
        if(out_trade_no!=null){
            return;
        }else {
            out_trade_no = PayConfig.get_out_trade_no();
            System.out.println(out_trade_no);
            String sql = "INSERT INTO payInfo VALUES ('"+userName+"','"+out_trade_no+"','0')";
            System.out.println(sql);
            db.setUser(sql);
            return ;
        }
    }
}
