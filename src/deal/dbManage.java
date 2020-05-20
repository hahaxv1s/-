package deal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dbManage {
    static final String DB_URL = "jdbc:mysql://localhost:3306/myweb"+"?serverTimezone=GMT%2B8";;
    static final String USER = "root";
    static final String PASS = "admin";
    public static String[] logjudge(String userName) throws SQLException,Exception{
        Connection conn = null;
        Statement stat = null;
        String password = null;
        String menuItem = null;
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
       // String sql = "";
        String sql = "SELECT password,menuitem from user where username='"+userName+"'";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()) {
            password=rs.getString("password");
            menuItem=rs.getString("menuitem");
        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] s = new String[]{password,menuItem};
        return s;
    }

    public static String[] getItem(String itemName) throws SQLException,Exception{
        Connection conn = null;
        Statement stat = null;
        String itemText = null;
        String itemURL = null;
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        // String sql = "";
        String sql = "SELECT itemText,itemURL from menuitem where itemName='"+itemName+"'";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()) {
            itemText=rs.getString("itemText");
            itemURL=rs.getString("itemURL");
        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] s = new String[]{itemText,itemURL};
        return s;
    }

    public static ArrayList<String[]> getUserData() throws SQLException,Exception{
        Connection conn = null;
        Statement stat = null;
        String username = null;
        String menuItem = null;
        ArrayList<String[]> ArrayList = new ArrayList<String[]>();
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        // String sql = "";
        String sql = "SELECT username,menuitem from user ";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()) {
            ArrayList.add(new String[]{rs.getString("username"), rs.getString("menuitem")});
        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return ArrayList;
    }

    public static void setUser(String sqlStatement) throws SQLException,Exception{
        Connection conn = null;
        Statement stat = null;
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        // String sql = "";
        stat.execute(sqlStatement);
        // 输出查询结果
        // 关闭
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    public static void saveApply(String sqlStatement) throws SQLException,Exception{
        Connection conn = null;
        Statement stat = null;
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        // String sql = "";
        stat.execute(sqlStatement);
        // 输出查询结果
        // 关闭

            if (stat != null)
                stat.close();
                if (conn != null) {
                    conn.close();
                }
            }


    public static String getEmail(String userName) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stat = null;
        String email = null;

        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        // String sql = "";
        String sql = "SELECT email from user where userName='"+userName+"'";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()) {
            email=rs.getString("email");

        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return email;
    }

    public String[] getApplyInfo(Object userName) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stat = null;
        String name = null;
        String gender = null;
        String idNumber = null;
        String address = null;
        String phoneNumber = null;
        String email = null;
        String area = null;
        String educationBackground = null;
        String position = null;
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        // String sql = "";
        String sql = "SELECT name , gender , idNumber ,address , phoneNumber , email , area , educationBackground from applyInfo where userName='"+userName+"'";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()) {
            name=rs.getString("name");
            gender=rs.getString("gender");
            idNumber=rs.getString("idNumber");
            address=rs.getString("address");
            phoneNumber=rs.getString("phoneNumber");
            email=rs.getString("email");
            area=rs.getString("area");
            educationBackground=rs.getString("educationBackground");
        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] s = new String[]{name,gender,idNumber,address,phoneNumber,email,area,educationBackground};
        return s;
    }

    public static String[] getScore(String userName) throws SQLException,Exception{
        Connection conn = null;
        Statement stat = null;
        String sub1 = null;
        String sub2 = null;
        String sub3 = null;
        String sub4 = null;
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        // String sql = "";
        String sql = "SELECT sub1,sub2,sub3,sub4 from score where userName='"+userName+"'";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()) {
            sub1=Integer.toString(rs.getInt("sub1"));
            sub2=Integer.toString(rs.getInt("sub2"));
            sub3=Integer.toString(rs.getInt("sub3"));
            sub4=Integer.toString(rs.getInt("sub4"));
        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] s = new String[]{sub1,sub2,sub3,sub4};
        return s;
    }

    public static String get_out_trade_no(String userName) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stat = null;
        String get_out_trade_no = null;

        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        // String sql = "";
        String sql = "SELECT orderNumber from payInfo where userName='"+userName+"'";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()) {
            get_out_trade_no=rs.getString("orderNumber");

        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return get_out_trade_no;
    }

    public static String getPaid(String userName) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stat = null;
        String paid = null;

        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        // String sql = "";
        String sql = "SELECT Paid from payInfo where userName='"+userName+"'";
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()) {
            paid=rs.getString("paid");

        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return paid;
    }

    public static int getStatistical(String sql) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stat = null;
        int num = -1;

        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果
        while(rs.next()) {
            num=rs.getInt(1);
        }
        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return num;
    }

    public static List getPaidExcel(String sql) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stat = null;
        List<Map<String, String>> listDatas = new ArrayList<Map<String, String>>();
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();

        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果

        while(rs.next()) {
            Map<String ,String> map=new HashMap<String, String>();
            map.put("userName",rs.getString(1));
            map.put("orderNumber",rs.getString(2));
            map.put("paid",rs.getString(3));
            listDatas.add(map);
        }

        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return listDatas;
    }

    public static List getApplyExcel(String sql) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stat = null;
        List<Map<String, String>> listDatas = new ArrayList<Map<String, String>>();
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 创建链接
        conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stat = conn.createStatement();

        ResultSet rs = stat.executeQuery(sql);
        // 输出查询结果

        while(rs.next()) {
            Map<String ,String> map=new HashMap<String, String>();
            map.put("userName",rs.getString(1));
            map.put("name",rs.getString(2));
            map.put("gender",rs.getString(3));
            map.put("idNumber",rs.getString(4));
            map.put("address",rs.getString(5));
            map.put("phoneNumber",rs.getString(6));
            map.put("email",rs.getString(7));
            map.put("area",rs.getString(8));
            map.put("educationBackground",rs.getString(9));
            listDatas.add(map);
        }

        // 关闭
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return listDatas;
    }
}


