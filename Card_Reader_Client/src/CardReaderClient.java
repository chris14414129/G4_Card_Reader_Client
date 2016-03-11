import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import java.util.Scanner;



public class CardReaderClient extends JFrame {
	
	private JLabel startTimeLbl, startTimeDisplay, endTimeLbl, endTimeDisplay, currentTimeLbl, currentTimeDisplay, sessionCodeLbl, sessionCodeDisplay, sessionNameLbl, sessionNameDisplay;
	private JLabel ipAddressLbl, ipAddressDisplay, portLbl, portDisplay, roomIDLbl, roomIDDisplay; 
	private JTextField input;
	private JPanel displayPanel, buttonPanel, northDisplayPanel, southDisplayPanel, settingsPanel;
	private JButton clearButton, submitButton;
	private JMenuBar menu;
	private JMenu fileMenu, settingsMenu;
	private JMenuItem exitItem;
	

	public CardReaderClient() {
	      setTitle("Card Reader");
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      buildButtonPanel();
	      buildSouthDisplayPanel();
	      buildNorthDisplayPanel();
	      buildDisplayPanel();
	      
	      menu = new JMenuBar();
	      fileMenu = new JMenu("File");
	      fileMenu.setMnemonic(KeyEvent.VK_F);
	      settingsMenu = new JMenu("Settings");
	      settingsMenu.setMnemonic(KeyEvent.VK_S);
	      
	      exitItem = new JMenuItem("Exit");
	      exitItem.setMnemonic(KeyEvent.VK_X);
	      
	      settingsMenu.addActionListener(new actionListener());
	      exitItem.addActionListener(new actionListener());
	      
	      fileMenu.add(exitItem);
	      menu.add(fileMenu);
	      menu.add(settingsMenu);
	      setJMenuBar(menu);
	      
	      
	      add(displayPanel, BorderLayout.NORTH);
	      add(buttonPanel, BorderLayout.SOUTH);
	  
	      pack();
	      
	      setVisible(true);
	      setResizable(false); 
	}
	
	private void buildDisplayPanel(){

	      displayPanel = new JPanel();
	      displayPanel.setLayout(new BorderLayout());
	      displayPanel.setBackground(Color.CYAN);
	      displayPanel.setPreferredSize(new Dimension(500, 350));
	      displayPanel.setBorder(new LineBorder(Color.GRAY, 5));
	      
	      displayPanel.add(northDisplayPanel, BorderLayout.NORTH);
	      displayPanel.add(southDisplayPanel, BorderLayout.SOUTH);
	      
	      
	}
	
	
	private void buildNorthDisplayPanel(){
		
		input = new JTextField("Enter Student ID Here");
		input.setHorizontalAlignment(JTextField.CENTER);
		
		Font font = new Font("Consolas", Font.BOLD, 20);
		input.setFont(font);
		
		northDisplayPanel = new JPanel();
		northDisplayPanel.setLayout(new BorderLayout());
		northDisplayPanel.add(input, BorderLayout.CENTER);
	}
	
	private void buildSouthDisplayPanel(){
		
		southDisplayPanel = new JPanel();
		southDisplayPanel.setBackground(Color.CYAN);
	    southDisplayPanel.setLayout(new GridLayout(2,5)); 
	    southDisplayPanel.setBorder(new EmptyBorder(50,50,50,50));
		
	    startTimeLbl = new JLabel("Start Time");
	    startTimeDisplay = new JLabel("11:00");
        endTimeLbl = new JLabel("End Time");
        endTimeDisplay = new JLabel("13:00");
        currentTimeLbl = new JLabel("Current Time");
        currentTimeDisplay = new JLabel("12:40");
        sessionCodeLbl = new JLabel("Session Code");
        sessionCodeDisplay = new JLabel("CSY2027");
        sessionNameLbl = new JLabel("Session Name");
        sessionNameDisplay = new JLabel("Group Project");
        
	      
	    
	    southDisplayPanel.add(sessionCodeLbl);
	    southDisplayPanel.add(sessionNameLbl);
	    southDisplayPanel.add(currentTimeLbl);
	    southDisplayPanel.add(startTimeLbl);
	    southDisplayPanel.add(endTimeLbl);
	    southDisplayPanel.add(sessionCodeDisplay);
	    southDisplayPanel.add(sessionNameDisplay);
	    southDisplayPanel.add(currentTimeDisplay);
	    southDisplayPanel.add(startTimeDisplay);
	    southDisplayPanel.add(endTimeDisplay);
	    		
	    	
	    
	      
	}
	
	private void buildButtonPanel(){

	      buttonPanel = new JPanel();
	      buttonPanel.setBackground(Color.LIGHT_GRAY);
	      buttonPanel.setPreferredSize(new Dimension(500, 45));
	      buttonPanel.setBorder(new LineBorder(Color.GRAY, 5));
	      
	      clearButton = new JButton("CLEAR");
	      submitButton = new JButton("SUBMIT");
	      
	      clearButton.addActionListener(new actionListener());
	      submitButton.addActionListener(new actionListener());
	      
	      buttonPanel.add(clearButton, BorderLayout.EAST);
	      buttonPanel.add(submitButton, BorderLayout.WEST);
	      
	      
	}
	
	 private class actionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object src = e.getSource();
			
			if (src == exitItem){
				System.exit(0);
			}
			if (src == clearButton){
				input.setText("");
			}
			if (src == submitButton){		
				input.setText("ID SUBMITTED!");
			}
			if (src == settingsMenu){
				settingsGUI();
			}
		}
	} 
	
	 
	private void settingsGUI(){
		  setTitle("Settings");
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      settingsPanel = new JPanel();
	      settingsPanel.setLayout(new GridLayout(3,2));
	      
	      ipAddressLbl = new JLabel("IP Address: ");
	      ipAddressDisplay = new JLabel("127.0.0.1");
	      portLbl = new JLabel("Port Number: ");
	      portDisplay = new JLabel("3001");
	      roomIDLbl = new JLabel("Room ID: ");
	      roomIDDisplay = new JLabel("MB8");
	      
	      settingsPanel.add(ipAddressLbl);
	      settingsPanel.add(ipAddressDisplay);
	      settingsPanel.add(portLbl);
	      settingsPanel.add(portDisplay);
	      settingsPanel.add(roomIDLbl);
	      settingsPanel.add(roomIDDisplay);
	      
	      pack();
	      
	      setVisible(true);
	      setResizable(false); 
	}
		
	public static void main(String[] args) {
		CardReaderClient CardReader = new CardReaderClient();
	}
}

