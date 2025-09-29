package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Service;
import confg.Database;

public class ServiceRepo {

    public void save(Service service) {
        
        String sql = "INSERT INTO service (nama_layanan, deskripsi, harga) VALUES (?, ?, ?)";
        try (Connection conn = Database.koneksi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, service.getServiceName());
            pstmt.setString(2, service.getDescription());
            pstmt.setDouble(3, service.getHarga());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Service> show() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM service";
        try (Connection conn = Database.koneksi();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getString("id"));
                service.setServiceName(rs.getString("nama_layanan"));
                service.setDescription(rs.getString("deskripsi"));
                service.setHarga(rs.getDouble("harga"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    public void update(Service service) {
        String sql = "UPDATE service SET nama_layanan = ?, deskripsi = ?, harga = ? WHERE id = ?";
        try (Connection conn = Database.koneksi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, service.getServiceName());
            pstmt.setString(2, service.getDescription());
            pstmt.setDouble(3, service.getHarga());
            pstmt.setInt(4, Integer.parseInt(service.getId())); 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM service WHERE id = ?";
        try (Connection conn = Database.koneksi();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}