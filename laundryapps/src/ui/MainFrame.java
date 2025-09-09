package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setForeground(new Color(255, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(54, 36, 157, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Pesanan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(39, 76, 100, 47);
		contentPane.add(btnNewButton);
		
		JButton btnLayanan = new JButton("Layanan");
		btnLayanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLayanan.setBounds(167, 76, 100, 47);
		contentPane.add(btnLayanan);
		
		JButton btnPelanggan = new JButton("Pelanggan");
		btnPelanggan.setBounds(295, 76, 100, 47);
		contentPane.add(btnPelanggan);
		
		JButton btnPengguna = new JButton("Pengguna");
		btnPengguna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPengguna.setBounds(39, 144, 100, 47);
		contentPane.add(btnPengguna);
		
		JButton btnLaporan = new JButton("Laporan");
		btnLaporan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLaporan.setBounds(167, 144, 100, 47);
		contentPane.add(btnLaporan);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProfile.setBounds(295, 144, 100, 47);
		contentPane.add(btnProfile);
		
		JButton btnNewButton_6 = new JButton("Keluar");
		btnNewButton_6.setBounds(54, 210, 326, 25);
		contentPane.add(btnNewButton_6);

	}

}
