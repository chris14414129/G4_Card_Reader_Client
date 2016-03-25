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
	private JLabel ipAddressLbl, portLbl, roomIDLbl, imageLbl, broadcastIPLbl; 
	private JTextField input, ipAddressField, portNoField, roomIDField, broadcastIPField;
	private JPanel displayPanel, buttonPanel, northDisplayPanel, southDisplayPanel, settingsPanel;
	private JButton clearBtn, submitBtn, settingsClearBtn, settingsSubmitBtn, testBtn;
	private JMenuBar menu;
	private JMenu fileMenu, optionsMenu;
	private JMenuItem exitItem, settingsItem;
	private ImageIcon infoImage;
	private String ipAddress = "", portNo = "", roomID = "", broadcastIP = "";
	private Timer timer;
	private boolean tick = true;
	
	

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
	      
	      setLocationRelativeTo( null );
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
		
        currentTimeLbl = new JLabel("Current Time");
        currentTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        currentTimeDisplay = new JLabel();
        currentTimeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        currentTimeDisplay.setFont(font);
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    
	    new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Calendar now = Calendar.getInstance();
	            currentTimeDisplay.setText(dateFormat.format(now.getTime()));
	        }
	    }).start();
		
	    
	    northDisplayPanel = new JPanel();
	    northDisplayPanel.setBackground(Color.CYAN);
		northDisplayPanel.setLayout(new GridLayout(4,1));
		northDisplayPanel.add(input);
		northDisplayPanel.add(currentTimeLbl);
		northDisplayPanel.add(currentTimeDisplay);
		
		imageLbl = new JLabel("", JLabel.CENTER);
	    northDisplayPanel.add(imageLbl);
	}
	
	private void updateImage(int x){
			  tick = true;
			timer = new Timer(3000, new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if (tick){
						  if (x == 0){ //0 meaning that the registration was successful (correct session etc.) TICK
				        	infoImage = new ImageIcon("images/Tick.png");
						    imageLbl.setIcon(infoImage);
						    tick = false;
						  }
						  
						  if (x == 1){ //1 meaning correct module but incorrect session ALERT
					        	infoImage = new ImageIcon("images/Alert.png");
							    imageLbl.setIcon(infoImage);
							    tick = false;
						  }
						  
						  if (x == 2){ //2 meaning that registration was unsuccessful CROSS 
					        	infoImage = new ImageIcon("images/Cross.png");
							    imageLbl.setIcon(infoImage);
							    tick = false;
						  }
					}
					else {
							  imageLbl.setIcon(null);
					}
				}
			});
			timer.setInitialDelay(0);
			timer.start();
			
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
	    		
	    setLocationRelativeTo(null);
	    
	      
	}
	
	private void buildButtonPanel(){

	      buttonPanel = new JPanel();
	      buttonPanel.setBackground(Color.LIGHT_GRAY);
	      buttonPanel.setPreferredSize(new Dimension(500, 45));
	      buttonPanel.setBorder(new LineBorder(Color.GRAY, 5));
	      
	      submitBtn = new JButton("SUBMIT");
	      clearBtn = new JButton("CLEAR");
	      testBtn = new JButton("TEST");
	      
	      submitBtn.addActionListener(new actionListener());
	      clearBtn.addActionListener(new actionListener());
	      testBtn.addActionListener(new actionListener());
	      
	      buttonPanel.add(submitBtn, BorderLayout.WEST);
	      buttonPanel.add(clearBtn, BorderLayout.EAST);
	      buttonPanel.add(testBtn);
	      checkSettingInputs();
	      
	      
	}
	
	 private class actionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object src = e.getSource();
			
			if (src == exitItem){
				System.exit(0);
			}
			if (src == clearBtn){
				input.setText("");
			}
			if (src == submitBtn){		
				input.setText("ID SUBMITTED!");
			}
			if (src == settingsItem){
				new settings();
			}
			if (src == settingsClearBtn){
				ipAddressField.setText("");
				ipAddress = "";
				portNoField.setText("");
				portNo = "";
				roomIDField.setText("");
				roomID = "";
				broadcastIPField.setText("");
				broadcastIP = "";
				checkSettingInputs();
			}
			if (src == settingsSubmitBtn){
				ipAddress = ipAddressField.getText();
				portNo = portNoField.getText();
				roomID = roomIDField.getText();
				broadcastIP = broadcastIPField.getText();
				checkSettingInputs();
			}
			if (src == testBtn){
				updateImage(1);
			}
		}
	} 
	
	 public class settings extends JFrame {
		 
		 public settings(){
			  
			  setTitle("Settings");
		      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		      
		      
		      
		      settingsPanel = new JPanel();
		      settingsPanel.setLayout(new GridLayout(5,2));
		      
		      
		      ipAddressLbl = new JLabel("IP Address: ");
		      ipAddressField = new JTextField(ipAddress);
		      ipAddressField.setEditable(true);
		      portLbl = new JLabel("Port Number: ");
		      portNoField = new JTextField(portNo);
		      portNoField.setEditable(true);
		      roomIDLbl = new JLabel("Room ID: ");
		      roomIDField = new JTextField(roomID);
		      broadcastIPLbl = new JLabel("Broadcast IP: ");
		      broadcastIPField = new JTextField(broadcastIP);
		      roomIDField.setEditable(true);
		      
		      settingsSubmitBtn = new JButton("Submit");
		      settingsClearBtn = new JButton("Clear");
		      
		      settingsSubmitBtn.addActionListener(new actionListener());
		      settingsClearBtn.addActionListener(new actionListener());
		      
		      settingsPanel.add(ipAddressLbl);
		      settingsPanel.add(ipAddressField);
		      settingsPanel.add(portLbl);
		      settingsPanel.add(portNoField);
		      settingsPanel.add(roomIDLbl);
		      settingsPanel.add(roomIDField);
		      settingsPanel.add(broadcastIPLbl);
		      settingsPanel.add(broadcastIPField);
		      settingsPanel.add(settingsSubmitBtn);
		      settingsPanel.add(settingsClearBtn);
		      
		      add(settingsPanel);
		      
		      setLocationRelativeTo(null);
		      
		      pack();
		      
		      setVisible(true);
		      setResizable(false); 
		      
		      setLocationRelativeTo( null );
		 }
	 }
	 
	 public void checkSettingInputs(){
		 
	      if (ipAddress.equals("") || portNo.equals("") || roomID.equals("") || broadcastIP.equals("")){
	    	  clearBtn.setEnabled(false);
	    	  submitBtn.setEnabled(false);
	      }
	      else {
	    	  clearBtn.setEnabled(true);
	    	  submitBtn.setEnabled(true);
	      }
	 }
	 
	 
		
	public static void main(String[] args) {
		CardReaderClient CardReader = new CardReaderClient();
		/* JOptionPane.showMessageDialog(null, "My Goodness, this is so concise"); */
	}
}

