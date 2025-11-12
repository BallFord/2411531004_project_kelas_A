package ui;

// Import untuk komponen Swing (GUI)
import javax.swing.*;

import error.ValidationException;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Import untuk kelas-kelas lain yang kita butuhkan
import model.User;
import service.LoginService;
import util.ValidationUtil;
// Import untuk menangani error database dan lainnya
import java.sql.SQLException;
import java.lang.NullPointerException;

public class LoginFrame extends JFrame {

    // Deklarasikan komponen di sini agar bisa diakses di seluruh kelas
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword; // Gunakan JPasswordField
    private JButton btnLogin;

    /**
     * Metode main untuk menjalankan aplikasi
     */
    public static void main(String[] args) {
        // Menjalankan di Event Dispatch Thread (cara terbaik)
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Coba gunakan Look & Feel sistem operasi
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor: Membuat frame dan semua komponen di dalamnya
     */
    public LoginFrame() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Ukuran frame
        setLocationRelativeTo(null); // Tampilkan di tengah layar

        // Inisialisasi content pane
        contentPane = new JPanel();
        contentPane.setLayout(null); // Menggunakan layout null (setBounds)
        setContentPane(contentPane);

        // Judul "Login Form"
        JLabel lblTitle = new JLabel("Login Form");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBounds(150, 20, 100, 25); // (x, y, width, height)
        contentPane.add(lblTitle);

        // Label Username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 70, 80, 25);
        contentPane.add(lblUsername);

        // Text Field Username
        txtUsername = new JTextField();
        txtUsername.setBounds(140, 70, 180, 25);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        // Label Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 110, 80, 25);
        contentPane.add(lblPassword);

        // Password Field Password
        txtPassword = new JPasswordField(); // Diubah ke JPasswordField
        txtPassword.setBounds(140, 110, 180, 25);
        contentPane.add(txtPassword);

        // Tombol Login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(140, 160, 100, 30);
        contentPane.add(btnLogin);

        // === INI BAGIAN LOGIKA PENTING ===
        // Menambahkan ActionListener ke tombol login
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Ambil data dari text field
                String userValue = txtUsername.getText();
                // Gunakan getPassword() untuk JPasswordField
                String passValue = new String(txtPassword.getPassword());

                // Create user object
                User user = new User(userValue, passValue);

                try {
                    // 1. Validasi input (dari util.ValidationUtil)
                    ValidationUtil.validate(user);
                    
                    // 2. Buat service (dari service.LoginService)
                    LoginService loginService = new LoginService();
                    
                    // 3. Coba autentikasi
                    if(loginService.authenticate(user)) {
                        // Jika BERHASIL
                        System.out.println("Login successful!");
                        
                        // Buka frame utama (dari view.MainFrame)
                        new MainFrame().setVisible(true);
                        
                        // Tutup frame login ini
                        dispose();
                    
                    } else {
                        // Jika GAGAL (username/password salah)
                        System.out.println("Invalid username or password.");
                        JOptionPane.showMessageDialog(null, "Login Gagal, Invalid username or password.");
                    }
                } catch (ValidationException | NullPointerException exception) {
                    // Jika GAGAL (data tidak valid/kosong)
                    System.out.println("Data tidak valid : " + exception.getMessage());
                    JOptionPane.showMessageDialog(null, "Login Gagal: " + exception.getMessage());
                } finally {
                    // Ini akan selalu tereksekusi
                    System.out.println("Selalu di eksekusi");
                }
            }
        });
    }
}