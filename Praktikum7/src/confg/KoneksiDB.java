package confg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/laundry_apps"; 
        String user = "root"; 
        String password = ""; 

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                JOptionPane.showMessageDialog(null, "Koneksi ke database berhasil!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
        }
    }
}
