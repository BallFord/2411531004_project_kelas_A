package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

public class CustomerRepo {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/laundry_apps";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public List<Customer> show() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(String.valueOf(rs.getInt("id"))); 
                customer.setNama(rs.getString("nama"));
                customer.setEmail(rs.getString("email"));
                customer.setTelepon(rs.getString("telepon"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("Gagal mengambil data pelanggan: " + e.getMessage());
            e.printStackTrace();
        }
        return customerList;
    }

    public void save(Customer customer) {
        String sql = "INSERT INTO customer (nama, email, telepon) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getNama());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getTelepon());
            pstmt.executeUpdate(); 
            System.out.println("Data berhasil disimpan ke database!");

        } catch (SQLException e) {
            System.err.println("Gagal menyimpan data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(Customer customer) {
        String sql = "UPDATE customer SET nama = ?, email = ?, telepon = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getNama());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getTelepon());
            pstmt.setInt(4, Integer.parseInt(customer.getId())); 
            pstmt.executeUpdate();
            System.out.println("Data berhasil diupdate!");

        } catch (SQLException e) {
            System.err.println("Gagal mengupdate data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM customer WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Integer.parseInt(id)); 
            pstmt.executeUpdate();
            System.out.println("Data berhasil dihapus!");

        } catch (SQLException e) {
            System.err.println("Gagal menghapus data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}