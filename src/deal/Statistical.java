package deal;

import deal.Excel.CommonExcel;

import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Statistical {
    public void sta(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String menuItem= (String) session.getAttribute("menuItem"); //拿到session里面当前用户的菜单栏列表,不能为空
        String[] itemList = menuItem.split("!");//把列表转换为数组，方便遍历。
        for(String a : itemList){
            if(a.equals("Statistical")){
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                request.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.write(buildJsonString());
                out.close();
            }
        }


    }

    String buildJsonString() throws Exception {
        int paid=-1;
        int nopaid=-1;
        dbManage db = new dbManage();
        String paidSql="select count(distinct userName) from payInfo where paid='1';";
        String noPaidSql="select count(distinct userName) from payInfo where paid='0';";
        paid=db.getStatistical(paidSql);
        nopaid=db.getStatistical(noPaidSql);

        String applyInfo = "{";           //从这里开始生成json字符串
        applyInfo = applyInfo+"\"paid\":\""+Integer.toString(paid)+"\"";
        applyInfo = applyInfo+",";
        applyInfo = applyInfo+"\"nopaid\":\""+Integer.toString(nopaid)+"\"";
        applyInfo = applyInfo+"}";
        return applyInfo;
    };

    public void getExcel(String sql,HttpServletResponse response,String Title) throws SQLException, ClassNotFoundException {
        dbManage db = new dbManage();
        // Service层获取数据库的数据
        List userList=db.getPaidExcel(sql);

        // 定义标题（第一行）
        String title = Title;

        // 定义列名（第二行）
        String[] rowsName = new String[]{"序号","用户名","订单号","支付状态"};

        // 定义主题内容（第三行起）
        List<Object[]> dataList = new ArrayList<Object[]>();

        // 定义每一行的临时变量，并放入数据
        Object[] objs = null;
        for (int i = 0; i < userList.size(); i++) {
            Map map = (Map)userList.get(i);
            String userName = (String) map.get("userName");
            String orderNumber = (String) map.get("orderNumber");
            String paid = (String) map.get("paid");
            objs = new Object[rowsName.length];
            objs[0] = i;
            objs[1] = userName;
            objs[2] = orderNumber;
            objs[3] = paid;
            dataList.add(objs);
        }

        // 定义Excel文件名
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fileName=Title+format.format(date)+".xls";
        // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
        CommonExcel ex = new CommonExcel(title, rowsName, dataList,response,fileName);
        try {
            ex.downloadExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getApplyExcel(String sql,HttpServletResponse response,String Title) throws SQLException, ClassNotFoundException {
        dbManage db = new dbManage();
        // Service层获取数据库的数据
        List userList=db.getApplyExcel(sql);

        // 定义标题（第一行）
        String title = Title;

        // 定义列名（第二行）
        String[] rowsName = new String[]{"序号","用户名","姓名","性别","身份证号","地址","电话号码","邮箱","所属区域","教育程度"};

        // 定义主题内容（第三行起）
        List<Object[]> dataList = new ArrayList<Object[]>();

        // 定义每一行的临时变量，并放入数据
        Object[] objs = null;
        for (int i = 0; i < userList.size(); i++) {
            Map map = (Map)userList.get(i);
            String userName = (String) map.get("userName");
            String name = (String) map.get("name");
            String gender = (String) map.get("gender");
            String idNumber = (String) map.get("idNumber");
            String address = (String) map.get("address");
            String phoneNumber = (String) map.get("phoneNumber");
            String email = (String) map.get("email");
            String area = (String) map.get("area");
            String educationBackground = (String) map.get("educationBackground");
            objs = new Object[rowsName.length];
            objs[0] = i;
            objs[1] = userName;
            objs[2] = name;
            objs[3] = gender;
            objs[4] = idNumber;
            objs[5] = address;
            objs[6] = phoneNumber;
            objs[7] = email;
            objs[8] = area;
            objs[9] = educationBackground;
            dataList.add(objs);
        }

        // 定义Excel文件名
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fileName=Title+format.format(date)+".xls";
        // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
        CommonExcel ex = new CommonExcel(title, rowsName, dataList,response,fileName);
        try {
            ex.downloadExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
