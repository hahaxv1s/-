package deal;

//这个是用来设置用户，修改信息的，可以不要
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UserControl {
    String buildJsonString(ArrayList<String[]> s) throws Exception {

        String userDataJson = "[";           //从这里开始生成json字符串
        for(String[] a : s){
            if (!userDataJson.equals("[")){userDataJson = userDataJson+",{";}
            else userDataJson = userDataJson+" {";
            userDataJson = userDataJson+"\"userName\":\""+a[0]+"\"";
            userDataJson = userDataJson+",";
            userDataJson = userDataJson+"\"menuItem\":\""+a[1]+"\"";
            userDataJson = userDataJson+"}";

        }
        userDataJson = userDataJson+"]";//生成完毕，返回字符串

        return userDataJson;
    };
    public void GetData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        dbManage db = new dbManage();
        ArrayList<String[]> s= db.getUserData();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(buildJsonString(s));
        out.close();
    }

    public void saveUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        dbManage db = new dbManage();
        String sqlStatement=null;
        if(request.getParameter("newName")==null){
            if(request.getParameter("newPassword")!=null){
                System.out.println(request.getParameter("newPassword"));
                sqlStatement="update user set password='"+request.getParameter("newPassword")+"' where username='"+request.getParameter("userName")+"';";
            }
        }
        else {
            if (request.getParameter("newPassword")!=null){
                sqlStatement="update user set password='"+request.getParameter("newPassword")+"',username='"+request.getParameter("newName")+"' where username='"+request.getParameter("userName")+"';";
            }
            else {
                sqlStatement="update user set username='"+request.getParameter("newName")+"' where username='"+request.getParameter("userName")+"';";
            }
        }
        if (sqlStatement!=null){
            db.setUser(sqlStatement);
            PrintWriter out = response.getWriter();
            out.write("修改成功");
            out.close();
        }
    }


    public void savePower(HttpServletRequest request, HttpServletResponse response) throws Exception {
        dbManage db = new dbManage();
        String sqlStatement=null;
        sqlStatement="update user set menuitem='"+request.getParameter("newUserPower")+"' where username='"+request.getParameter("userName")+"';";
        db.setUser(sqlStatement);
        PrintWriter out = response.getWriter();
        out.write("修改成功");
        out.close();
    }

}
