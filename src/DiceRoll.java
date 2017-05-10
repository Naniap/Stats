import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.ui.RefineryUtilities;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

/***
 * 
 * @author Michael Coache
 *
 */

public class DiceRoll extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Die;
	private JLabel lblNumberOfRolls;
	private JLabel lblNumberOfSides;
	private JTextField txt_Roll;
	private JTextField txt_Side;
	private JFrame frame;
	private JButton btnBack;
	private JFrame parent;
	private JButton btnBack1;

	/**
	 * Create the frame.
	 */
	public DiceRoll(JFrame parent) {
		this.parent = parent;
		frame = this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 291, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumberOfDie = new JLabel("Number of die:");
		lblNumberOfDie.setBounds(7, 20, 96, 14);
		contentPane.add(lblNumberOfDie);
		
		txt_Die = new JTextField();
		txt_Die.setBounds(106, 17, 86, 20);
		contentPane.add(txt_Die);
		txt_Die.setColumns(10);
		
		lblNumberOfRolls = new JLabel("Number of rolls:");
		lblNumberOfRolls.setBounds(7, 45, 96, 14);
		contentPane.add(lblNumberOfRolls);
		
		lblNumberOfSides = new JLabel("Number of sides:");
		lblNumberOfSides.setBounds(7, 70, 96, 14);
		contentPane.add(lblNumberOfSides);
		
		txt_Roll = new JTextField();
		txt_Roll.setColumns(10);
		txt_Roll.setBounds(106, 42, 86, 20);
		contentPane.add(txt_Roll);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HistogramTest histo = new HistogramTest("", Integer.parseInt(txt_Roll.getText()), Integer.parseInt(txt_Die.getText()), Integer.parseInt(txt_Side.getText()), frame);
		        histo.pack();
		        RefineryUtilities.centerFrameOnScreen(histo);
		        histo.setVisible(true);
		        frame.dispose();
			}
		});
		
		txt_Side = new JTextField();
		txt_Side.setColumns(10);
		txt_Side.setBounds(106, 70, 86, 20);
		contentPane.add(txt_Side);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(0, 16, 74, -16);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				parent.setVisible(true);
			}
		});
		btnBack.setBounds(7, 109, 89, 23);
		contentPane.add(btnBack);
		btnSubmit.setBounds(106, 109, 89, 23);
		contentPane.add(btnSubmit);
		
		//btnBack1 = new JButton("Back");
		//btnBack1.setBounds(7, 109, 89, 23);
		//contentPane.add(btnBack1);
	}
}
