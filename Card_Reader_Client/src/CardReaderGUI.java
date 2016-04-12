import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class CardReaderGUI {
	private JFrame window;
	private JLabel startTimeLbl;
	private static JLabel startTimeDisplay;
	private JLabel endTimeLbl;
	private static JLabel endTimeDisplay;
	private JLabel currentTimeLbl;
	private JLabel currentTimeDisplay; 
	private JLabel sessionCodeLbl;
	private static JLabel sessionCodeDisplay;
	private JLabel sessionNameLbl;
	private static JLabel sessionNameDisplay;
	private static JLabel imageLbl; 
	private JTextField input;
	private JPanel displayPanel, buttonPanel, northDisplayPanel, southDisplayPanel;
	private JButton clearBtn, submitBtn, settingsClearBtn;
	private JMenuBar menu;
	private JMenu fileMenu, optionsMenu;
	private JMenuItem exitItem, settingsItem;
	private static ImageIcon infoImage;
	private static Timer timer;
	private static boolean tick = true;
	private String startTime, endTime, sessionName, sessionCode;

	private String studentId;
	

	public CardReaderGUI() {
		window = new JFrame();
	      window.setTitle("Card Reader");
	      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
	      window.setJMenuBar(menu);
	      
	      window.add(displayPanel, BorderLayout.NORTH);
	      window.add(buttonPanel, BorderLayout.SOUTH);
	  
	      window.pack();
	      window.setVisible(true);
	      window.setResizable(false); 
	      
	      window.setLocationRelativeTo( null );
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
		imageLbl.setIcon(null);
	    northDisplayPanel.add(imageLbl);
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
	      
	      submitBtn = new JButton("SUBMIT");
	      submitBtn.setEnabled(false);
	      clearBtn = new JButton("CLEAR");
	      
	      submitBtn.addActionListener(new actionListener());
	      clearBtn.addActionListener(new actionListener());
	      
	      buttonPanel.add(submitBtn, BorderLayout.WEST);
	      buttonPanel.add(clearBtn, BorderLayout.EAST);
	}
	
	static void updateText(String startTime, String endTime, String sessionName, String sessionCode){
		
		startTimeDisplay.setText(startTime);
		endTimeDisplay.setText(endTime);
		sessionNameDisplay.setText(sessionName);
		sessionCodeDisplay.setText(sessionCode);
		
	}
	static void updateImage(int x){
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
				studentId = input.getText().trim();
				new Registration(Settings.broadcastIP, Integer.parseInt(Settings.serverPort), Integer.parseInt(Settings.clientPort), Integer.parseInt(Settings.roomID));
				new Session(Settings.broadcastIP, Integer.parseInt(Settings.serverPort), Integer.parseInt(Settings.roomID));
			}
			if (src == settingsItem){
				new Settings(window);
				if (Settings.returnBoolean() == false){
					submitBtn.setEnabled(true);
				}
			}
			if (src == settingsClearBtn){
				
			}
		}
	} 
}
