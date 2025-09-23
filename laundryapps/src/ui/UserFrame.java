package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import DAO.UserRepo;
import model.User;
import table.TableUser;

public class UserFrame extends JFrame {

	UserRepo usr = new UserRepo();
	List<User> ls;
	public String id;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableUsers;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	
	public void reset() {
		txtName.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
	}
	
	public void loadTable() {
		ls = usr.show();
		TableUser tu = new TableUser(ls);
		tableUsers.setModel(tu);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserFrame() {
		// Inisialisasi 'usr = new UserRepo();' tidak lagi diperlukan di sini
		// karena sudah dilakukan di atas saat deklarasi variabel.
		
		setTitle("USERS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.LIGHT_GRAY);
		panel1.setBounds(10, 10, 626, 255);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblNama = new JLabel("Nama\r\n");
		lblNama.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNama.setBounds(30, 26, 69, 31);
		panel1.add(lblNama);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblUsername.setBounds(30, 67, 94, 31);
		panel1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblPassword.setBounds(30, 109, 69, 31);
		panel1.add(lblPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				
				user.setNama(txtName.getText());
				user.setUsername(txtUsername.getText());
				user.setPassword(txtPassword.getText());
				
				usr.save(user);
				
				reset();
				
				loadTable();
			}
		});
		btnSave.setBackground(new Color(39, 174, 96));
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnSave.setBounds(81, 185, 108, 31);
		panel1.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(41, 128, 185));
		btnUpdate.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnUpdate.setBounds(214, 185, 108, 31);
		panel1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(231, 76, 60));
		btnDelete.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnDelete.setBounds(352, 185, 108, 31);
		panel1.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.YELLOW);
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCancel.setBounds(487, 185, 108, 31);
		panel1.add(btnCancel);
		
		txtName = new JTextField();
		txtName.setToolTipText("Name");
		txtName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtName.setColumns(10);
		txtName.setBounds(131, 26, 464, 31);
		panel1.add(txtName);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("Username");
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtUsername.setColumns(10);
		txtUsername.setBounds(131, 67, 464, 31);
		panel1.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setToolTipText("Password");
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtPassword.setColumns(10);
		txtPassword.setBounds(131, 109, 464, 31);
		panel1.add(txtPassword);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 275, 626, 338);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 606, 318);
		panel2.add(scrollPane);
		
		tableUsers = new JTable();
		tableUsers.setToolTipText("Table Users");
		scrollPane.setViewportView(tableUsers);
	}
}