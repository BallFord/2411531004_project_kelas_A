package ui;

import javax.swing.*;

import error.ValidationException;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.User;
import service.LoginService;
import util.ValidationUtil;
import java.sql.SQLException;
import java.lang.NullPointerException;

public class LoginFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword; 
    private JButton btnLogin;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
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

    public LoginFrame() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); 
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setLayout(null); 
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Login Form");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBounds(150, 20, 100, 25); 
        contentPane.add(lblTitle);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 70, 80, 25);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(140, 70, 180, 25);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 110, 80, 25);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField(); 
        txtPassword.setBounds(140, 110, 180, 25);
        contentPane.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(140, 160, 100, 30);
        contentPane.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String userValue = txtUsername.getText();
                String passValue = new String(txtPassword.getPassword());

                User user = new User(userValue, passValue);

                try {
                    ValidationUtil.validate(user);
                    
                    LoginService loginService = new LoginService();
                    
                    if(loginService.authenticate(user)) {
                        System.out.println("Login successful!");
                      
                        new MainFrame().setVisible(true);
                   
                        dispose();
                    
                    } else {
                        System.out.println("Invalid username or password.");
                        JOptionPane.showMessageDialog(null, "Login Gagal, Invalid username or password.");
                    }
                } catch (ValidationException | NullPointerException exception) {
                    System.out.println("Data tidak valid : " + exception.getMessage());
                    JOptionPane.showMessageDialog(null, "Login Gagal: " + exception.getMessage());
                } finally {
                    System.out.println("Selalu di eksekusi");
                }
            }
        });
    }
}