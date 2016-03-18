import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;

import java.util.Calendar;
import java.util.Scanner;

/* Commands for communication
 * XXXYYYREST
 * XXX == room_id e.g. 101
 * YYY == function e.g. UPD
 * REST == data
 * 
 * Receiving
 * UPD == Update display for session, - for demo purposes only, wont be in string
 * REST == Sessioncode(A)-Sessionname(B)-Starttime(C)-Endtime(d)
 * A== String 11
 * B== String 30
	 * C== String 8
		 * D== String 8
		 * 
		 * Example:
		 * 101UPDCSY1234/01Test Session                  10:00:0011:00:00
 * 
 * CON == confirmation of student registration
 * REST == String 4
 * PASS
 * FAIL
 * 
 * e.g
 * 101CONPASS
 * 
 * Sending
 * REG == student registration
 * REST == int 8 student_id
 * 
 * e.g.
 * 101REG12345678
 */



public class CardReaderClient extends JFrame {
	
	private JLabel startTimeLbl, startTimeDisplay, endTimeLbl, endTimeDisplay, currentTimeLbl, currentTimeDisplay, sessionCodeLbl, sessionCodeDisplay, sessionNameLbl, sessionNameDisplay;
	private JLabel ipAddressLbl, ipAddressDisplay, portLbl, portDisplay, roomIDLbl, roomIDDisplay, picLabel; 
	private JTextField input;
	private JPanel displayPanel, buttonPanel, northDisplayPanel, southDisplayPanel, settingsPanel;
	private JButton clearButton, submitButton;
	private JMenuBar menu;
	private JMenu fileMenu, optionsMenu;
	private JMenuItem exitItem, settingsItem;
	

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
	      optionsMenu = new JMenu("Settings");
	      optionsMenu.setMnemonic(KeyEvent.VK_S);
	      
	      exitItem = new JMenuItem("Exit");
	      exitItem.setMnemonic(KeyEvent.VK_X);
	      settingsItem = new JMenuItem("Settings");
	      settingsItem.setMnemonic(KeyEvent.VK_S);
	      
	      
	      settingsItem.addActionListener(new actionListener());
	      exitItem.addActionListener(new actionListener());
	      
	      fileMenu.add(exitItem);
	      optionsMenu.add(settingsItem);
	      menu.add(fileMenu);
	      menu.add(optionsMenu);
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
		
        currentTimeLbl = new JLabel("Current Time");
        currentTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        currentTimeDisplay = new JLabel();
        currentTimeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		
		Font font = new Font("Consolas", Font.BOLD, 20);
		input.setFont(font);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		int interval = 1000;
	    
	    new Timer(interval, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Calendar now = Calendar.getInstance();
	            currentTimeDisplay.setText(dateFormat.format(now.getTime()));
	        }
	    }).start();
		
	    /*Icon code
	     * In declare: 
	     * main code:
	     * try {
	     * whiteTile = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("images/white32x32.jpg")));
	     * }
	     * catch (Exception e)
	     * {
	     * private Icon	whiteTile;
	     * }
	     */
	    
	    BufferedImage myPicture = ImageIO.read(new File("path-to-file")); //http://www.softicons.com/toolbar-icons/build-icons-by-design-kindle [REFERENCE THIS]
	    picLabel = new JLabel(new ImageIcon(myPicture));
	    
		northDisplayPanel = new JPanel();
		northDisplayPanel.setLayout(new GridLayout(4,1));
		northDisplayPanel.add(input);
		northDisplayPanel.add(currentTimeLbl);
		northDisplayPanel.add(currentTimeDisplay);
		northDisplayPanel.add(picLabel);
	}
	
	private void buildSouthDisplayPanel(){
		
		southDisplayPanel = new JPanel();
		southDisplayPanel.setBackground(Color.CYAN);
	    southDisplayPanel.setLayout(new GridLayout(2, 5, 10, 10)); 
	    southDisplayPanel.setBorder(new EmptyBorder(50,50,50,50));
		
	    startTimeLbl = new JLabel("Start Time");
	    startTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    startTimeDisplay = new JLabel("11:00");
	    startTimeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        endTimeLbl = new JLabel("End Time");
        endTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        endTimeDisplay = new JLabel("13:00");
        endTimeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        sessionCodeLbl = new JLabel("Session Code");
        sessionCodeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        sessionCodeDisplay = new JLabel("CSY2027");
        sessionCodeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        sessionNameLbl = new JLabel("Session Name");
        sessionNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        sessionNameDisplay = new JLabel("Group Project");
        sessionNameDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        
	      
	    
	    southDisplayPanel.add(sessionCodeLbl);
	    southDisplayPanel.add(sessionNameLbl);
	    southDisplayPanel.add(startTimeLbl);
	    southDisplayPanel.add(endTimeLbl);
	    southDisplayPanel.add(sessionCodeDisplay);
	    southDisplayPanel.add(sessionNameDisplay);
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
			if (src == settingsItem){
				new settings();
			}
		}
	} 
	
	 public class settings extends JFrame {
		 
		 public settings(){
			  
			  setTitle("Settings");
		      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		      
		      
		      
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
		      
		      add(settingsPanel);
		      
		      pack();
		      
		      setVisible(true);
		      setResizable(false); 
		 }
	 }
		
	public static void main(String[] args) {
		CardReaderClient CardReader = new CardReaderClient();
		/* JOptionPane.showMessageDialog(null, "My Goodness, this is so concise"); */
	}
}

