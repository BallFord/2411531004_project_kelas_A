package confg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDB {
    public static void main(String[] args) {
        // Sesuaikan dengan database kamu
        String url = "jdbc:mysql://localhost:3306/laundry_apps"; // tulis port biar jelas
        String user = "root"; // default user XAMPP
        String password = ""; // default kosong

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
