import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;

import java.util.Scanner;
import java.awt.event.*;
import java.beans.PropertyChangeListener;



public class CardReaderClient extends JFrame {
	
	private JLabel startTime, endTime, currentTime, sessionCode, sessionName;
	private JTextField input;
	private JPanel displayPanel, buttonPanel, northDisplayPanel, southDisplayPanel;
	private JButton clearButton, submitButton;
	private JMenuBar menu;
	private JMenu fileMenu;
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
	      
	      exitItem = new JMenuItem("Exit");
	      exitItem.setMnemonic(KeyEvent.VK_X);
	      
	      exitItem.addActionListener(new actionListener());
	      
	      fileMenu.add(exitItem);
	      menu.add(fileMenu);
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
	    southDisplayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 60 )); 
		
	    startTime = new JLabel("Start Time");
        endTime = new JLabel("End Time");
        currentTime = new JLabel("Current Time");
        sessionCode = new JLabel("Session Code");
        sessionName = new JLabel("Session Name");
        
        /*
        Font font = new Font("Consolas", Font.BOLD, 12);
        startTime.setFont(font);
        endTime.setFont(font);
        currentTime.setFont(font);
        sessionCode.setFont(font);
        sessionName.setFont(font); 
        */
	      
	    southDisplayPanel.add(startTime);
	    southDisplayPanel.add(endTime);
	    southDisplayPanel.add(currentTime);
	    southDisplayPanel.add(sessionCode);
	    southDisplayPanel.add(sessionName);
	    
	      
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
		}
	} 
	
	 
	 /*
	 public static class GhostText implements FocusListener, DocumentListener, PropertyChangeListener {
	        private final JTextField textfield;
	        private boolean isEmpty;
	        private Color ghostColor;
	        private Color foregroundColor;
	        private final String ghostText;

	        protected GhostText(final JTextField textfield, String ghostText) {
	            super();
	            this.textfield = textfield;
	            this.ghostText = ghostText;
	            this.ghostColor = Color.LIGHT_GRAY;
	            textfield.addFocusListener(this);
	            registerListeners();
	            updateState();
	            if (!this.textfield.hasFocus()) {
	                focusLost(null);
	            }
	        } */
	
	public static void main(String[] args) {
		CardReaderClient CardReader = new CardReaderClient();
	}
}

