package detective_ivanna_clue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class GameGridBagLayout {

	private String str = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
	
	private JFrame frame;
	
	int buttonWidth = 90;
	int buttonHeight = 28;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGridBagLayout window = new GameGridBagLayout();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameGridBagLayout() {
		initialize();
		frame.setTitle("Detective Ivanna Clue");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 720);
		frame.setMinimumSize(new Dimension(550, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 0.5;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(15, 15, 15, 15);
		
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel panel2 = new JPanel(new GridBagLayout());
		JPanel panel3 = new JPanel(new GridBagLayout());
		JPanel panel4 = new JPanel(new GridBagLayout());
		JPanel panelSouth = new JPanel(new BorderLayout());
		
		// field to print out the console
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(str);
		
		// add scroll bars to textArea
		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		gbc.gridx = 0;
		gbc.gridy = 0;
			
		panel.add(scrollBar, gbc); 
		
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(15, 15, 15, 15);
		gbc2.fill = GridBagConstraints.BOTH;
		
		JButton btnGoNorth = new JButton("Go North");
		
		gbc2.gridx = 3;
		gbc2.gridy = 0;
		
		panel2.add(btnGoNorth, gbc2);

		JButton btnGoSouth = new JButton("Go South");
		
		gbc2.gridx = 3;
		gbc2.gridy = 2;
		
		panel2.add(btnGoSouth, gbc2);

		JButton btnGoWest = new JButton("Go West");
		
		gbc2.gridx = 2;
		gbc2.gridy = 1;
		
		panel2.add(btnGoWest, gbc2);
		
		JButton btnGoEast = new JButton("Go East");
		
		gbc2.gridx = 4; // 1
		gbc2.gridy = 1; // 2
		
		panel2.add(btnGoEast, gbc2);
		
		JButton btnBack = new JButton("Back");
		
		gbc2.gridx = 3;
		gbc2.gridy = 1;
		
		panel2.add(btnBack, gbc2);
		
		JButton btnAsk = new JButton("Ask");
		
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		
		panel3.add(btnAsk, gbc2);
		
		JButton btnLook = new JButton("Look");
		
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		
		panel3.add(btnLook, gbc2);
		
		JButton btnInventory = new JButton("Inventory");
		
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		
		panel4.add(btnInventory, gbc2);
		
		JButton btnTake = new JButton("Take");
		
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		
		panel4.add(btnTake, gbc2);
		
		JButton btnDrop = new JButton("Drop");
		
		gbc2.gridx = 0;
		gbc2.gridy = 2;
		
		panel4.add(btnDrop, gbc2);

		panelSouth.add(panel3, BorderLayout.WEST);
		panelSouth.add(panel2, BorderLayout.CENTER);
		panelSouth.add(panel4, BorderLayout.EAST);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(panelSouth, BorderLayout.SOUTH);
//		frame.getContentPane().add(panel2, BorderLayout.SOUTH);
	}

}
