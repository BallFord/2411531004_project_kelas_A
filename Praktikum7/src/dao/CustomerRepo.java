package dao;

import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import confg.Database;       
import model.Customer;
import model.CustomerBuilder; 

public class CustomerRepo implements CustomerDao {
    private Connection connection;
    
    private final String insert = "INSERT INTO pelanggan (nama, email, alamat, hp) VALUES (?,?,?,?);";
    private final String select = "SELECT * FROM pelanggan;";
    private final String delete = "DELETE FROM pelanggan WHERE id=?;";
    private final String update = "UPDATE pelanggan SET nama=?, email=?, alamat=?, hp=? WHERE id=?;";

    public CustomerRepo() {
        connection = Database.koneksi();
    }
    
    @Override
    public void save(Customer cs) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getHp());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Customer> show() {
        List<Customer> ls = null;
        try {
            ls = new ArrayList<Customer>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            
            while (rs.next()) {
                Customer cs = new CustomerBuilder()
                    .setId(rs.getString("id"))
                    .setNama(rs.getString("nama"))
                    .setEmail(rs.getString("email"))
                    .setAlamat(rs.getString("alamat"))
                    .setHp(rs.getString("hp"))
                    .build();
                ls.add(cs);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }

    @Override
    public void update(Customer cs) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, cs.getNama());
            st.setString(2, cs.getEmail());
            st.setString(3, cs.getAlamat());
            st.setString(4, cs.getHp());
            st.setString(5, cs.getId()); 
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id); 
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}