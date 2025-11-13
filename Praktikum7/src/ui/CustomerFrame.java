package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.CustomerRepo;
import model.Customer;
import model.CustomerBuilder;
import table.TableCustomer;

public class CustomerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private JTextField txtCustomerName;
    private JTextField txtCustomerAddress;
    private JTextField txtCustomerPhone;
    private JTextField txtCustomerEmail;
    private JTable tableCustomers;
    
    private String id;
    List<Customer> ls;
    CustomerRepo customerRepo = new CustomerRepo();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerFrame frame = new CustomerFrame();
                    frame.setVisible(true);
                    frame.loadTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CustomerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(30, 30, 100, 20);
        contentPane.add(lblNama);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(140, 30, 300, 25);
        contentPane.add(txtCustomerName);
        txtCustomerName.setColumns(10);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(30, 70, 100, 20);
        contentPane.add(lblAlamat);

        txtCustomerAddress = new JTextField();
        txtCustomerAddress.setBounds(140, 70, 300, 25);
        contentPane.add(txtCustomerAddress);
        txtCustomerAddress.setColumns(10);

        JLabel lblHp = new JLabel("No HP");
        lblHp.setBounds(30, 110, 100, 20);
        contentPane.add(lblHp);

        txtCustomerPhone = new JTextField();
        txtCustomerPhone.setBounds(140, 110, 300, 25);
        contentPane.add(txtCustomerPhone);
        txtCustomerPhone.setColumns(10);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(30, 150, 100, 20);
        contentPane.add(lblEmail);

        txtCustomerEmail = new JTextField();
        txtCustomerEmail.setBounds(140, 150, 300, 25);
        contentPane.add(txtCustomerEmail);
        txtCustomerEmail.setColumns(10);

        JButton btnSave = new JButton("Simpan");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Customer customer = new CustomerBuilder()
                        .setNama(txtCustomerName.getText())
                        .setAlamat(txtCustomerAddress.getText())
                        .setHp(txtCustomerPhone.getText())
                        .setEmail(txtCustomerEmail.getText())
                        .build();
                
                customerRepo.save(customer);
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                reset();
                loadTable();
            }
        });
        btnSave.setBounds(140, 200, 90, 30);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Ubah");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(id != null) {
                    Customer customer = new CustomerBuilder()
                            .setId(id)
                            .setNama(txtCustomerName.getText())
                            .setAlamat(txtCustomerAddress.getText())
                            .setHp(txtCustomerPhone.getText())
                            .setEmail(txtCustomerEmail.getText())
                            .build();
                    
                    customerRepo.update(customer);
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan diubah");
                }
            }
        });
        btnUpdate.setBounds(240, 200, 90, 30);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Hapus");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(id != null) {
                    customerRepo.delete(id);
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus");
                }
            }
        });
        btnDelete.setBounds(340, 200, 90, 30);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Batal");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnCancel.setBounds(440, 200, 90, 30);
        contentPane.add(btnCancel);

        JPanel panel = new JPanel();
        panel.setBounds(10, 250, 560, 300);
        contentPane.add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 560, 300);
        panel.add(scrollPane);

        tableCustomers = new JTable();
        scrollPane.setViewportView(tableCustomers);
        
        tableCustomers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = tableCustomers.getSelectedRow();
                id = ls.get(rowIndex).getId(); 
                txtCustomerName.setText(ls.get(rowIndex).getNama());
                txtCustomerAddress.setText(ls.get(rowIndex).getAlamat());
                txtCustomerPhone.setText(ls.get(rowIndex).getHp());
                txtCustomerEmail.setText(ls.get(rowIndex).getEmail());
            }
        });
    }

    public void reset() {
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerPhone.setText("");
        txtCustomerEmail.setText("");
        id = null;
    }

    public void loadTable() {
        ls = customerRepo.show();
        TableCustomer tc = new TableCustomer(ls);
        tableCustomers.setModel(tc);
        tableCustomers.getTableHeader().setVisible(true);
    }
}