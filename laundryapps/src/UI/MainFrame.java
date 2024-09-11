package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

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
		setBounds(100, 100, 880, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(48, 10, 221, 68);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("PESANAN");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(20, 88, 249, 123);
		contentPane.add(btnNewButton);
		
		JButton btnLayanan = new JButton("LAYANAN");
		btnLayanan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLayanan.setBounds(304, 88, 249, 123);
		contentPane.add(btnLayanan);
		
		JButton btnPelanggan = new JButton("PELANGGAN");
		btnPelanggan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPelanggan.setBounds(585, 88, 249, 123);
		contentPane.add(btnPelanggan);
		
		JButton btnPengguna = new JButton("PENGGUNA");
		btnPengguna.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPengguna.setBounds(20, 233, 249, 123);
		contentPane.add(btnPengguna);
		
		JButton btnLaporan = new JButton("LAPORAN");
		btnLaporan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLaporan.setBounds(304, 233, 249, 123);
		contentPane.add(btnLaporan);
		
		JButton btnProfile = new JButton("PROFILE");
		btnProfile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnProfile.setBounds(585, 233, 249, 123);
		contentPane.add(btnProfile);
		
		JButton btnNewButton_1 = new JButton("KELUAR");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(20, 366, 814, 68);
		contentPane.add(btnNewButton_1);
	}

}
