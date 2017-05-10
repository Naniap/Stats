import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Canvas;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainApp extends JFrame {

	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
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
	public MainApp() {
		setTitle("Card and Dice Roll Simulator");
		frame = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 684, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon image = new ImageIcon("./resources/cards.png");
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 290, 346);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new CardRun(frame).setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel.setBounds(0, 0, 290, 346);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(image);
		lblNewLabel.setIcon(image);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(310, 11, 348, 346);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		ImageIcon image2 = new ImageIcon("./resources/dice.png");
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DiceRoll(frame).setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_1.setBounds(0, 0, 348, 346);
		lblNewLabel_1.setIcon(image2);
		panel_1.add(lblNewLabel_1);
	}
}
