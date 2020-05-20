package deal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Apply {
    public void apply(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
       // session.getAttribute("code")
            dbManage db = new dbManage();
            String sqlStatement=null;
            if(request.getParameter("name")!=null && request.getParameter("gender")!=null && request.getParameter("idNumber")!=null
                    &&request.getParameter("address")!=null&&request.getParameter("phoneNumber")!=null&&request.getParameter("email")!=null
                    &&request.getParameter("educationBackground")!=null&&request.getParameter("area")!=null){
                sqlStatement="INSERT INTO applyInfo VALUES ('"+session.getAttribute("userName")+"','"+request.getParameter("name")+"','"
                        +request.getParameter("gender")+"','"+request.getParameter("idNumber")+"','"+request.getParameter("address")
                        +"','"+request.getParameter("phoneNumber")+"','"+request.getParameter("email")+"','"
                        +request.getParameter("area")+"','"+request.getParameter("educationBackground")+"')";

            }
            else {
                sqlStatement=null;
            }
            if (sqlStatement!=null){
                try{
                db.setUser(sqlStatement);
                response.sendRedirect("/untitled/itemPage/applyPage.html");
            }catch (Exception e){
                    try{
                        db.setUser("DELETE FROM applyInfo where userName = '"+session.getAttribute("userName")+"'");
                        db.setUser(sqlStatement);
                        response.sendRedirect("/untitled/itemPage/applyPage.html");
                    }catch (Exception er ){
                        System.out.println(er);
                    }
                }
        }
    }

    public void getApplyInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        out.write(buildJsonString(session));
        out.close();
    }

    String buildJsonString(HttpSession session) throws Exception {
        dbManage dbManage = new dbManage();
        String[] applyInfoList=dbManage.getApplyInfo(session.getAttribute("userName"));//拿到报名表信息

            String applyInfo = "{";           //从这里开始生成json字符串
            applyInfo = applyInfo+"\"name\":\""+applyInfoList[0]+"\"";
            applyInfo = applyInfo+",";
            applyInfo = applyInfo+"\"gender\":\""+applyInfoList[1]+"\"";
            applyInfo = applyInfo+",";
            applyInfo = applyInfo+"\"idNumber\":\""+applyInfoList[2]+"\"";
            applyInfo = applyInfo+",";
            applyInfo = applyInfo+"\"address\":\""+applyInfoList[3]+"\"";
            applyInfo = applyInfo+",";
            applyInfo = applyInfo+"\"phoneNumber\":\""+applyInfoList[4]+"\"";
            applyInfo = applyInfo+",";
            applyInfo = applyInfo+"\"email\":\""+applyInfoList[5]+"\"";
            applyInfo = applyInfo+",";
            applyInfo = applyInfo+"\"area\":\""+applyInfoList[6]+"\"";
            applyInfo = applyInfo+",";
            applyInfo = applyInfo+"\"educationBackground\":\""+applyInfoList[7]+"\"";
            applyInfo = applyInfo+"}";
        return applyInfo;
    };
}


