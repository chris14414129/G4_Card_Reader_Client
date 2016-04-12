import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings {
	
	private JDialog settings;
	private JPanel settingsPanel;
	private JLabel ipAddressLbl, portLbl, roomIDLbl, broadcastIPLbl;
	private JTextField ipAddressField, portNoField, roomIDField, broadcastIPField;
	private JButton submitBtn, clearBtn;
	
	private String ipAddress = "", portNo = "", roomID = "", broadcastIP = "";
	private static boolean blank = true;
	
	public Settings(JFrame parentFrame) {
		settings.setTitle("Settings");
		settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		buildSettingsPanel();
		
		settings.add(settingsPanel);
		
		settings.setLocationRelativeTo(null);
		settings.pack();
		settings.setVisible(true);
		settings.setResizable(false);
	}
	
	private void buildSettingsPanel(){
		
		settingsPanel = new JPanel();
	    settingsPanel.setLayout(new GridLayout(5,2));
	    
	    ipAddressLbl = new JLabel("IP Address: ");
	    ipAddressField = new JTextField(ipAddress);
	    portLbl = new JLabel("Port Number: ");
	    portNoField = new JTextField(portNo);
	    roomIDLbl = new JLabel("Room ID: ");
	    roomIDField = new JTextField(roomID);
	    broadcastIPLbl = new JLabel("Broadcast IP: ");
	    broadcastIPField = new JTextField(broadcastIP);
	    
	    submitBtn = new JButton("Submit");
	    clearBtn = new JButton("Clear");
	    clearBtn.setEnabled(false);
	    
	    submitBtn.addActionListener(new actionListener());
	    clearBtn.addActionListener(new actionListener());
	    
	    
	}
	
	public static boolean returnBoolean(){
		return blank;
	}
	
	public void checkSettingInputs(){
		 
	      if (ipAddress.equals("") || portNo.equals("") || roomID.equals("") || broadcastIP.equals("")){
	    	  blank = true;
	      }
	      else {
	    	  blank = false;
	      }
	 }
	
	 private class actionListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				Object src = e.getSource();
				if (src == clearBtn){
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
				if (src == submitBtn){
					ipAddress = ipAddressField.getText().trim();
					portNo = portNoField.getText().trim();
					roomID = roomIDField.getText().trim();
					broadcastIP = broadcastIPField.getText().trim();
				}
				
			}
		}
}
