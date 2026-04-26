package frontEnd;

import java.sql.*;

public class DBSetup {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:print_history.db");

            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS PrintHistory (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "file_name TEXT," +
                    "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    "status TEXT," +
                    "execution_time REAL" +
                    ")";

            stmt.execute(sql);
            conn.close();

            System.out.println("Database Created Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}