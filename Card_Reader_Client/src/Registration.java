import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Registration {
	private String broadcastIP;
	private int serverPort;
//	private int clientPort;
	private int roomID;
	private boolean tick;

	public Registration(String ip,int serverPort, int clientPort, int room)
	{
		this.broadcastIP=ip;
		this.serverPort=serverPort;
	//	this.clientPort=clientPort;
		this.roomID= room;
	}

public void register(String student_id)
{
		String studID = student_id;
	
		try
		{
			//send UDP
		  DatagramSocket sendSocket = new DatagramSocket ();
          byte[] buf = new byte[256];
         String messg = this.roomID + "REG" + studID;
     
          buf = messg.getBytes ();
          InetAddress address = InetAddress.getByName (this.broadcastIP);
          DatagramPacket packet = new DatagramPacket (buf, buf.length, address, this.serverPort);
          sendSocket.send(packet);
          
          sendSocket.close();
          System.out.println("close");
          
			
					
		
          
         

		}
		catch(IOException e)
		{
			
		}
		

			
			
}		



			
}

