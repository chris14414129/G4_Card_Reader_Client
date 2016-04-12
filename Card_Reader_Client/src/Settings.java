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
	private JLabel serverPortLbl, clientPortLbl, roomIDLbl, broadcastIPLbl;
	private JTextField serverPortInput, clientPortInput, roomIDInput, broadcastIPInput;
	private JButton submitBtn, clearBtn;
	
	public static String clientPort = "4456", serverPort = "4455", roomID = "111", broadcastIP = "127.0.0.1";
	private static boolean blank = true;
	
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
	    settingsPanel.setLayout(new GridLayout(4,2));
	    
	    serverPortLbl = new JLabel("Server Port: ");
	    serverPortInput = new JTextField(serverPort);
	    clientPortLbl = new JLabel("Client Port: ");
	    clientPortInput = new JTextField(clientPort);
	    roomIDLbl = new JLabel("Room ID: ");
	    roomIDInput = new JTextField(roomID);
	    broadcastIPLbl = new JLabel("Broadcast IP: ");
	    broadcastIPInput = new JTextField(broadcastIP);
	    
	    settingsPanel.add(serverPortLbl);
	    settingsPanel.add(serverPortInput);
	    settingsPanel.add(clientPortLbl);
	    settingsPanel.add(clientPortInput);
	    settingsPanel.add(roomIDLbl);
	    settingsPanel.add(roomIDInput);
	    settingsPanel.add(broadcastIPLbl);
	    settingsPanel.add(broadcastIPInput);
	    
	}
	
	private void buildButtonPanel(){
		buttonPanel = new JPanel();
		
		submitBtn = new JButton("Submit");
	    clearBtn = new JButton("Clear");
	    clearBtn.setEnabled(false);
	    
	    submitBtn.addActionListener(new actionListener());
	    clearBtn.addActionListener(new actionListener());
	    
	    buttonPanel.add(clearBtn);
	    buttonPanel.add(submitBtn);
	}
	
	public static boolean returnBoolean(){
		return blank;
	}
	
	public void checkSettingInputs(){
		 
	      if (serverPort.equals("") || clientPort.equals("") || roomID.equals("") || broadcastIP.equals("")){
	    	  clearBtn.setEnabled(false);
	    	  blank = true;
	      }
	      else {
	    	  clearBtn.setEnabled(true);
	    	  blank = false;
	      }
	 }
	
	 private class actionListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				Object src = e.getSource();
				if (src == clearBtn){
					serverPortInput.setText("");
					serverPort = "";
					clientPortInput.setText("");
					clientPort = "";
					roomIDInput.setText("");
					roomID = "";
					broadcastIPInput.setText("");
					broadcastIP = "";
					checkSettingInputs();
				}
				if (src == submitBtn){
					if (!serverPortInput.getText().equals("") && !clientPortInput.getText().equals("") && !roomIDInput.getText().equals("") && !broadcastIPInput.getText().equals("")){
						serverPort = serverPortInput.getText().trim();
						clientPort = clientPortInput.getText().trim();
						roomID = roomIDInput.getText().trim();
						broadcastIP = broadcastIPInput.getText().trim();
						checkSettingInputs();
					}
					else {
						JOptionPane.showMessageDialog(null,  "Please fill in all text fields first!", "Error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		}
}
