package frontEnd;

import java.sql.*;

public class DBHelper {

    public static void insertRecord(String fileName, String status, double time) {
        /*try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:print_history.db");

            String sql = "INSERT INTO PrintHistory(file_name, status, execution_time) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, fileName);
            pstmt.setString(2, status);
            pstmt.setDouble(3, time);

            pstmt.executeUpdate();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    	 // Use same path logic as DBSetup
        String dbPath = System.getProperty("user.dir") + "/print_history.db";
        String url = "jdbc:sqlite:" + dbPath;
        
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO PrintHistory(file_name, status, execution_time) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fileName);
            pstmt.setString(2, status);
            pstmt.setDouble(3, time);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
