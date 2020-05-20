package deal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ResultInquiry {
    public void getScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        out.write(buildJsonString(request));
        out.close();
    }

    String buildJsonString(HttpServletRequest request) throws Exception {
        dbManage dbManage = new dbManage();
        String[] scoreList=dbManage.getScore((String) request.getSession().getAttribute("userName"));//拿到报名表信息
        String score = "{";           //从这里开始生成json字符串
        score = score+"\"sub1\":\""+scoreList[0]+"\"";
        score = score+",";
        score = score+"\"sub2\":\""+scoreList[1]+"\"";
        score = score+",";
        score = score+"\"sub3\":\""+scoreList[2]+"\"";
        score = score+",";
        score = score+"\"sub4\":\""+scoreList[3]+"\"";
        score = score+"}";
        return score;
    };
}
