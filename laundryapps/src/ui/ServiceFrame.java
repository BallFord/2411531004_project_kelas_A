package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.ServiceRepo;
import table.TableService;
import model.Service;
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

public class ServiceFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtServiceName;
    private JTextField txtDescription;
    private JTextField txtPrice;
    private JTable tableService;
    private ServiceRepo serviceRepo = new ServiceRepo();
    private List<Service> ls;
    private String selectedId;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ServiceFrame frame = new ServiceFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void reset() {
        txtServiceName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        selectedId = null;
    }

    public void loadTable() {
        ls = serviceRepo.show();
        TableService tu = new TableService(ls);
        tableService.setModel(tu);
        tableService.getTableHeader().setVisible(true);
    }

    public ServiceFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 556, 586);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblServiceName = new JLabel("Nama Layanan");
        lblServiceName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblServiceName.setBounds(47, 40, 100, 32);
        contentPane.add(lblServiceName);

        JLabel lblDescription = new JLabel("Deskripsi");
        lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDescription.setBounds(47, 78, 92, 32);
        contentPane.add(lblDescription);

        JLabel lblPrice = new JLabel("Harga");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPrice.setBounds(47, 116, 70, 32);
        contentPane.add(lblPrice);

        txtServiceName = new JTextField();
        txtServiceName.setBounds(150, 44, 347, 28);
        contentPane.add(txtServiceName);
        txtServiceName.setColumns(10);

        txtDescription = new JTextField();
        txtDescription.setColumns(10);
        txtDescription.setBounds(150, 82, 347, 28);
        contentPane.add(txtDescription);

        txtPrice = new JTextField();
        txtPrice.setColumns(10);
        txtPrice.setBounds(150, 120, 347, 28);
        contentPane.add(txtPrice);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            Service service = new Service();
            service.setServiceName(txtServiceName.getText());
            service.setDescription(txtDescription.getText());
            try {
                service.setHarga(Double.parseDouble(txtPrice.getText()));
                serviceRepo.save(service);
                reset();
                loadTable();
                JOptionPane.showMessageDialog(this, "Data layanan berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Harga harus berupa angka.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnSave.setBounds(150, 159, 75, 32);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> {
            if (selectedId != null) {
                Service service = new Service();
                service.setServiceName(txtServiceName.getText());
                service.setDescription(txtDescription.getText());
                try {
                    service.setHarga(Double.parseDouble(txtPrice.getText()));
                    service.setId(selectedId);
                    serviceRepo.update(service);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(this, "Data layanan berhasil diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Harga harus berupa angka.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan di-update", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnUpdate.setBounds(231, 159, 92, 32);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            if (selectedId != null) {
                int confirmation = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus layanan ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    serviceRepo.delete(selectedId);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(this, "Data layanan berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
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

        tableService = new JTable();
        tableService.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableService.getSelectedRow();
                if (row >= 0) {
                    selectedId = tableService.getValueAt(row, 0).toString();
                    txtServiceName.setText(tableService.getValueAt(row, 1).toString());
                    txtDescription.setText(tableService.getValueAt(row, 2).toString());
                    txtPrice.setText(tableService.getValueAt(row, 3).toString());
                }
            }
        });
        scrollPane.setViewportView(tableService);
        
        loadTable();
    }
}