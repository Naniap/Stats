/**
 * @author Seth Bonacquisti
 * Date: 5/8/2017
 * Description: This program takes in 3 inputs and generates the probability of getting a One Pair, Two Pair
 * 				Three of a kind or Four of a kind.
 * 					1) The user enters the number of cards they wish to have in their hand.
 * 					2) The user enters the number of hands to generate.
 * 					3) The user selects the number choice to display the findings of either One Pair, Two Pair, Three of a kind
 * 						or Four of a kind.
 * 					4) Applet displays the number of findings based off the user's choice, displays the probability of choice and the probability
 * 						of the single run.
 * Note 1: Cards, Deck and Hand class code is from the following website: URL: http://math.hws.edu/javanotes/c5/s4.html
 * Note 2: Additional method(s) were added to the Cards, Deck and Hand classes. Those methods are commented with the author name.
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardRun extends JFrame implements ActionListener {
	private JFrame parent;
	private JFrame frame;
    JPanel contentPane;
    JTextField sizeOfHandTB;
    JTextField numOfHandTB;
    JTextField choiceTB;
    JLabel one_Pair, two_Pair,three_of_a_kind,four_of_a_kind;
    JButton btnReset;
	
	NumberFormat formatter = new DecimalFormat("#0.00000000");
	Deck deck = new Deck(); 
	Cards card;
	Probability prob = new Probability();
	Hand hand = new Hand();
	int sizeOfHand, numOfHand, choice, cardCount=0, onePair=0, twoPair=0, threeOfAKind=0, fourOfAKind=0;
	double result=0.0;
	private JButton btnBack;
	
	/**
	 * Main run of the interface
	 */
	public CardRun(JFrame parent)
	{
		setResizable(false);
			frame = this;
			this.parent = parent;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			sizeOfHandTB = new JTextField();
			
			sizeOfHandTB.setBounds(10, 44, 54, 20);
			contentPane.add(sizeOfHandTB);
			sizeOfHandTB.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Size of the hand?");
			lblNewLabel.setBounds(10, 31, 128, 14);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Card Probability Applet");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_1.setBounds(22, 11, 402, 20);
			contentPane.add(lblNewLabel_1);
			
			numOfHandTB = new JTextField();
			numOfHandTB.setColumns(10);
			numOfHandTB.setBounds(10, 84, 106, 20);
			contentPane.add(numOfHandTB);
			
			JLabel lblHowManyHands = new JLabel("How many hands should we generate?");
			lblHowManyHands.setBounds(10, 66, 225, 14);
			contentPane.add(lblHowManyHands);
			
			JLabel lblHowMany = new JLabel("(1)OnePairs, (2)TwoPairs, (3)ThreeOfAKinds, (4)FourOfAKinds");
			lblHowMany.setBounds(10, 109, 394, 14);
			contentPane.add(lblHowMany);
			
			choiceTB = new JTextField();
			choiceTB.setColumns(10);
			choiceTB.setBounds(10, 127, 106, 20);
			contentPane.add(choiceTB);
			
			
			JButton calculateButton = new JButton("Go!");		
			calculateButton.setBounds(335, 43, 89, 23);
			contentPane.add(calculateButton);
			calculateButton.addActionListener(this);

			
			btnReset = new JButton("Reset");
			btnReset.setBounds(335, 83, 89, 23);
			contentPane.add(btnReset);
			btnReset.addActionListener(this);

			JSeparator separator = new JSeparator();
			separator.setBounds(10, 158, 414, 2);
			contentPane.add(separator);
			
			one_Pair = new JLabel();
			one_Pair.setBounds(10, 171, 414, 79);
			contentPane.add(one_Pair);
			one_Pair.setVisible(false);
			
			two_Pair = new JLabel();
			two_Pair.setBounds(10, 171, 414, 79);
			contentPane.add(two_Pair);
			two_Pair.setVisible(false);
			
			three_of_a_kind = new JLabel();
			three_of_a_kind.setBounds(10, 171, 414, 79);
			contentPane.add(three_of_a_kind);
			three_of_a_kind.setVisible(false);
			
			four_of_a_kind = new JLabel();
			four_of_a_kind.setBounds(10, 171, 414, 79);
			contentPane.add(four_of_a_kind);
			
			btnBack = new JButton("Back");
			btnBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.setVisible(false);
					parent.setVisible(true);
				}
			});
			btnBack.setBounds(335, 227, 89, 23);
			contentPane.add(btnBack);
			four_of_a_kind.setVisible(false);	
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
			sizeOfHand =Integer.parseInt(sizeOfHandTB.getText());
			numOfHand = Integer.parseInt(numOfHandTB.getText());
			choice = Integer.parseInt(choiceTB.getText());
			
			
			
				
			if(choice == 1 && sizeOfHand <=1 || choice == 2 && sizeOfHand <=3 || choice == 3 && sizeOfHand <=2 || choice == 4 && sizeOfHand <=3)
			{
				JOptionPane.showMessageDialog(contentPane, "Invalid Number Of Cards!! Run Again");
				System.exit(0);
			}
				
				for(int i = 0; i < numOfHand; i++)
				{
					deck.shuffleDeck();
					
					for(int j = 0; j<sizeOfHand; j++)
					{
						
						card=deck.dealCard(); 
						hand.addCard(card); 
					}
					
					cardCount=hand.whatIsInHand(choice);
					
					
					if(cardCount == 1)
					{
						onePair++;
					}
					else if(cardCount == 2)
					{
						twoPair++;
					}
					else if(cardCount == 3)
					{
						threeOfAKind++;
					}
					else if(cardCount == 4)
					{
						fourOfAKind++;
					}

					
					hand.clearHand();
						
				}
				
				
				
				switch (choice)
				{
				case 1: result = (double) onePair/numOfHand;
				     one_Pair.setText("<html>Number of one pair(s) found: "+ onePair +"<br>"
						  + "The probability of getting one pair choosing "+ sizeOfHand +" cards is: "+ formatter.format(prob.getOnePairProb(sizeOfHand))+"<br>"
						  + "The probability of this run is: "+ result+"</html>");
				     one_Pair.setVisible(true);
				     break;
				
				case 2: result = (double) twoPair/numOfHand;
					two_Pair.setText("<html>Number of two pair(s) found: "+ twoPair +"<br>"
						  + "The probability of getting two pairs choosing "+ sizeOfHand +" cards is "+ formatter.format(prob.getTwoPairProb(sizeOfHand))+"<br>"
						  + "The probability of this run is "+ result+"</html>");
					two_Pair.setVisible(true);
					break;
					
				case 3: result = (double) threeOfAKind/numOfHand;
				three_of_a_kind.setText("<html>Number of three of a kind(s) found: "+ threeOfAKind +"<br>"
					  + "The probability of getting three of a kind choosing "+ sizeOfHand +" cards is "+ formatter.format(prob.getThreeOfAKindProb(sizeOfHand))+"<br>"
					  + "The probability of this run is "+ result+"</html>");
				three_of_a_kind.setVisible(true);
				break;
				
				case 4: result = (double) fourOfAKind/numOfHand;
				four_of_a_kind.setText("<html>Number of four of a kind(s) found: "+ twoPair +"<br>"
					  + "The probability of getting four of a kind choosing "+ sizeOfHand +" cards is "+ formatter.format(prob.getFourOfAKindProb(sizeOfHand))+"<br>"
					  + "The probability of this run is "+ result+"</html>");
				four_of_a_kind.setVisible(true);
				break;
				
				}
				
				if(e.getSource() == btnReset)
				{     sizeOfHandTB.setText("");
					  numOfHandTB.setText("");
					  choiceTB.setText("");
					  one_Pair.setVisible(false);
					  two_Pair.setVisible(false);
					  three_of_a_kind.setVisible(false);
					  four_of_a_kind.setVisible(false);
					  cardCount=0;
					  onePair=0;
					  twoPair=0;
					  threeOfAKind=0;
					  fourOfAKind=0;
					  result=0.0;
			  }
	
	
	
			
	}
				
			
			
			}

		
	
			
		
		
		

	

