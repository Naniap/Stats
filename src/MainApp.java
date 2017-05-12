import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Canvas;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
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
		setBounds(100, 100, 360, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon image = new ImageIcon("./resources/probability.png");
		
		JPanel panel = new JPanel();
		panel.setBounds(24, 275, 137, 36);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 137, 36);
		panel.add(lblNewLabel);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new CardRun(frame).setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel.setIcon(image);
		lblNewLabel.setIcon(image);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(171, 275, 137, 36);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		ImageIcon image2 = new ImageIcon("./resources/histogram.png");
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DiceRoll(frame).setVisible(true);
				frame.dispose();
			}
		});
		lblNewLabel_1.setBounds(0, 0, 137, 36);
		lblNewLabel_1.setIcon(image2);
		panel_1.add(lblNewLabel_1);
		ImageIcon image3 = new ImageIcon("./resources/homescreen.png");
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 360, 360);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(image3);
		panel_2.add(lblNewLabel_2);
	}
}
