package deal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class BuildUserPage {
    /*我觉得这个json字符串的生成方法可以改一改用到其他地方，要是之后用的多的话，可以考虑单独写个类出来调用*/
    String buildJsonString(HttpSession session) throws Exception {
        dbManage dbManage = new dbManage();
        String menuItem= (String) session.getAttribute("menuItem"); //拿到session里面当前用户的菜单栏列表,不能为空
        String[] itemList = menuItem.split("!");//把列表转换为数组，方便遍历。

        String menuItemJson = "[";           //从这里开始生成json字符串

        for(String a : itemList){
            if (!menuItemJson.equals("[")){menuItemJson = menuItemJson+",{";}
            else menuItemJson = menuItemJson+" {";
            menuItemJson = menuItemJson+"\"itemText\":\""+dbManage.getItem(a)[0]+"\"";
            menuItemJson = menuItemJson+",";
            menuItemJson = menuItemJson+"\"itemURL\":\""+dbManage.getItem(a)[1]+"\"";
            menuItemJson = menuItemJson+"}";
        }
        menuItemJson = menuItemJson+"]";//生成完毕，返回字符串
        return menuItemJson;
    };



    public void buildPage(HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
            PrintWriter out = response.getWriter();
            out.write(buildJsonString(session));
            out.close();
    }
}
