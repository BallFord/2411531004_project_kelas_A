package tugas;

import javax.swing.*;
import java.awt.*;

public class DownloadManagerApp extends JFrame {

    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private JButton btnDownload;

    public DownloadManagerApp() {
        setTitle("Download Manager App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(null);

        JLabel lblTitle = new JLabel("Download Manager App", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
       
        lblTitle.setBounds(0, 10, 385, 30); 
        add(lblTitle);

        JLabel lblFile1 = new JLabel("File 1");
        lblFile1.setBounds(20, 60, 50, 20);
        add(lblFile1);

        progressBar1 = new JProgressBar(0, 100);
        progressBar1.setStringPainted(true);
        progressBar1.setBounds(80, 60, 280, 20);
        add(progressBar1);

        JLabel lblFile2 = new JLabel("File 2");
        lblFile2.setBounds(20, 100, 50, 20);
        add(lblFile2);

        progressBar2 = new JProgressBar(0, 100);
        progressBar2.setStringPainted(true);
        progressBar2.setBounds(80, 100, 280, 20);
        add(progressBar2);

        JLabel lblFile3 = new JLabel("File 3");
        lblFile3.setBounds(20, 140, 50, 20);
        add(lblFile3);

        progressBar3 = new JProgressBar(0, 100);
        progressBar3.setStringPainted(true);
        progressBar3.setBounds(80, 140, 280, 20);
        add(progressBar3);

        btnDownload = new JButton("Downloading");
        btnDownload.setBounds(240, 200, 120, 30);
        add(btnDownload);

        btnDownload.addActionListener(e -> {
            btnDownload.setEnabled(false);
            startDownloadTask(progressBar1, 50);
            startDownloadTask(progressBar2, 30);
            startDownloadTask(progressBar3, 60);
        });
    }

    private void startDownloadTask(JProgressBar bar, int sleepTime) {
        Thread task = new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    final int progress = i;
                    SwingUtilities.invokeLater(() -> bar.setValue(progress));
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        task.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DownloadManagerApp app = new DownloadManagerApp();
            app.setVisible(true);
        });
    }
}