package frontEnd;

import java.sql.*;

public class DBSetup {
    public static void main(String[] args) {
        try {
        	
        	Class.forName("org.sqlite.JDBC"); 
        	// Use the current working directory explicitly
        	String dbPath = System.getProperty("user.dir") + "/print_history.db";
        	String url = "jdbc:sqlite:" + dbPath;

        	System.out.println("Connecting to: " + url);
        	Connection conn = DriverManager.getConnection(url);
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:print_history.db");

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