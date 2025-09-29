package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.CustomerRepo;
import table.TableCustomer;
import model.Customer;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class CustomerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtEmail;
    private JTextField txtTelepon;
    private JTable tableCustomer;
    private CustomerRepo customerRepo = new CustomerRepo();
    private List<Customer> ls;
    private String selectedId;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerFrame frame = new CustomerFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void reset() {
        txtNama.setText("");
        txtEmail.setText("");
        txtTelepon.setText("");
        selectedId = null;
    }
    
    public void loadTable() {
        ls = customerRepo.show();
        TableCustomer tableModel = new TableCustomer(ls);
        tableCustomer.setModel(tableModel);
    }

    public CustomerFrame() {
        setTitle("Manajemen Data Pelanggan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 556, 586);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNama = new JLabel("Nama Pelanggan");
        lblNama.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNama.setBounds(21, 40, 120, 32);
        contentPane.add(lblNama);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(21, 78, 92, 32);
        contentPane.add(lblEmail);

        JLabel lblTelepon = new JLabel("No. Telepon");
        lblTelepon.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTelepon.setBounds(21, 116, 100, 32);
        contentPane.add(lblTelepon);

        txtNama = new JTextField();
        txtNama.setBounds(150, 44, 347, 28);
        contentPane.add(txtNama);
        txtNama.setColumns(10);

        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(150, 82, 347, 28);
        contentPane.add(txtEmail);

        txtTelepon = new JTextField();
        txtTelepon.setColumns(10);
        txtTelepon.setBounds(150, 120, 347, 28);
        contentPane.add(txtTelepon);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            Customer p = new Customer();
            p.setNama(txtNama.getText());
            p.setEmail(txtEmail.getText());
            p.setTelepon(txtTelepon.getText());
            customerRepo.save(p);
            reset();
            loadTable();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        });
        btnSave.setBounds(150, 159, 75, 32);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> {
            if (selectedId != null) {
                Customer p = new Customer();
                p.setId(selectedId);
                p.setNama(txtNama.getText());
                p.setEmail(txtEmail.getText());
                p.setTelepon(txtTelepon.getText());
                customerRepo.update(p);
                reset();
                loadTable();
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di-update");
            }
        });
        btnUpdate.setBounds(231, 159, 92, 32);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            if (selectedId != null) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    customerRepo.delete(selectedId);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
            }
        });
        btnDelete.setBounds(330, 159, 92, 32);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> reset());
        btnCancel.setBounds(429, 159, 92, 32);
        contentPane.add(btnCancel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 222, 500, 316);
        contentPane.add(scrollPane);

        tableCustomer = new JTable();
        tableCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableCustomer.getSelectedRow();
                if (row >= 0) {
                    selectedId = tableCustomer.getValueAt(row, 0).toString();
                    txtNama.setText(tableCustomer.getValueAt(row, 1).toString());
                    txtEmail.setText(tableCustomer.getValueAt(row, 2).toString());
                    Object teleponObj = tableCustomer.getValueAt(row, 3);
                    txtTelepon.setText(teleponObj != null ? teleponObj.toString() : "");
                }
            }
        });
        scrollPane.setViewportView(tableCustomer);
        
        loadTable();
    }
}