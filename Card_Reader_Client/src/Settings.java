import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings {
	
	private JDialog settings;
	private JPanel settingsPanel, buttonPanel;
	private JLabel serverPortLbl, clientPortLbl, clientPortLbl2, roomIDLbl, broadcastIPLbl;
	private static JTextField serverPortInput;
	private static JTextField clientPortInput;
	private static JTextField roomIDInput;
	private static JTextField broadcastIPInput;
	private static JTextField clientPortInput2;
	private JButton submitBtn;
	private static JButton clearBtn;
	
	public static String clientPort = "4456", serverPort = "4455", roomID = "001", broadcastIP = "1", clientPort2 = "4454";
	
	public Settings(JFrame parentFrame) {
		settings = new JDialog(parentFrame, true);
		settings.setTitle("Settings");
		settings.setModal(true);
		settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settings.setLayout(new BoxLayout(settings.getContentPane(), BoxLayout.Y_AXIS));
		
		buildSettingsPanel();
		buildButtonPanel();
		
		settings.add(settingsPanel);
		settings.add(buttonPanel);
		
		settings.setLocationRelativeTo(null);
		settings.pack();
		settings.setVisible(true);
		settings.setResizable(false);
	}
	
	private void buildSettingsPanel(){
		
		settingsPanel = new JPanel();
	    settingsPanel.setLayout(new GridLayout(5,2));
	    
	    serverPortLbl = new JLabel("Server Port: ");
	    serverPortInput = new JTextField(serverPort);
	    clientPortLbl = new JLabel("Client Port: ");
	    clientPortInput = new JTextField(clientPort);
	    clientPortLbl2 = new JLabel("Client Port2: ");
	    clientPortInput2 = new JTextField(clientPort2);
	    roomIDLbl = new JLabel("Room ID: ");
	    roomIDInput = new JTextField(roomID);
	    broadcastIPLbl = new JLabel("Broadcast IP: ");
	    broadcastIPInput = new JTextField(broadcastIP);
	    
	    settingsPanel.add(serverPortLbl);
	    settingsPanel.add(serverPortInput);
	    settingsPanel.add(clientPortLbl);
	    settingsPanel.add(clientPortInput);
	    settingsPanel.add(clientPortLbl2);
	    settingsPanel.add(clientPortInput2);
	    settingsPanel.add(roomIDLbl);
	    settingsPanel.add(roomIDInput);
	    settingsPanel.add(broadcastIPLbl);
	    settingsPanel.add(broadcastIPInput);
	    
	}
	
	private void buildButtonPanel(){
		buttonPanel = new JPanel();
		
		submitBtn = new JButton("Submit");
	    clearBtn = new JButton("Clear");
	    
	    submitBtn.addActionListener(new actionListener());
	    clearBtn.addActionListener(new actionListener());
	    
	    buttonPanel.add(clearBtn);
	    buttonPanel.add(submitBtn);
	}
	
	
	 private class actionListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				Object src = e.getSource();
				if (src == clearBtn){
					serverPortInput.setText("");
					serverPort = "";
					clientPortInput.setText("");
					clientPort = "";
					clientPortInput2.setText("");
					clientPort2 = "";
					roomIDInput.setText("");
					roomID = "";
					broadcastIPInput.setText("");
					broadcastIP = "";
				}
				if (src == submitBtn){
					if (!serverPortInput.getText().equals("") && !clientPortInput.getText().equals("") && !roomIDInput.getText().equals("") && !broadcastIPInput.getText().equals("")){
						serverPort = serverPortInput.getText().trim();
						clientPort = clientPortInput.getText().trim();
						clientPort2 = clientPortInput2.getText().trim();
						roomID = roomIDInput.getText().trim();
						broadcastIP = broadcastIPInput.getText().trim();
					}
					else {
						JOptionPane.showMessageDialog(null,  "Please fill in all text fields first!", "Error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		}
}
