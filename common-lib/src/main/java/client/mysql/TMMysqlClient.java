package client.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TMMysqlClient
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/4/3
 * @Version V1.0
 **/
public class TMMysqlClient {


    private static String url;
    private static String username;
    private static String password;
    private void init(String url,String username,String password) throws SQLException {

        this.url = url;
        this.username = username;
        this.password = password;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

    }


    public static void executeUpdate(String sql) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        Statement st = conn.createStatement();
        int cnt = st.executeUpdate(sql);
        if (cnt > 0){
            //
        }
    }

    public List<List<HashMap<String, Object>>> getDataSet(String sqlStr) throws SQLException {
        List<List<HashMap<String, Object>>> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            try (CallableStatement cstmt = conn.prepareCall(sqlStr)) {
                if (cstmt.execute()) {
                    do {
                        List<HashMap<String, Object>> mapList = new ArrayList<>();
                        try (ResultSet rs = cstmt.getResultSet()) {
                            ResultSetMetaData rm = rs.getMetaData();
                            int count = rm.getColumnCount();
                            while (rs.next()) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                for (int i = 1; i <= count; i++) {
                                    hashMap.put(rm.getColumnName(i), rs.getObject(i));
                                }
                                mapList.add(hashMap);
                            }
                        }
                        list.add(mapList);

                    } while (cstmt.getMoreResults());
                }
            }
        }
        return list;
    }
}
