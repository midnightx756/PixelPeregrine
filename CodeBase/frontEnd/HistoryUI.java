package frontEnd;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("serial")
public class HistoryUI extends JFrame {

    public HistoryUI() {

        setTitle("Print History");
        setSize(600, 300);

        String[] cols = {"ID", "File Name", "Time", "Status", "Exec Time"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        JTable table = new JTable(model);

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:print_history.db");

            String sql = "SELECT * FROM PrintHistory ORDER BY id DESC LIMIT 5";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("file_name"),
                        rs.getString("timestamp"),
                        rs.getString("status"),
                        rs.getDouble("execution_time")
                });
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}