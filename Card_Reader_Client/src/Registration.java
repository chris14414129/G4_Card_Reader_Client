import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Registration {
	private String broadcastIP;
	private int serverPort;
	private int clientPort;
	private int roomID;

	public Registration(String ip,int serverPort, int clientPort, int room)
	{
		this.broadcastIP=ip;
		this.serverPort=serverPort;
		this.clientPort=clientPort;
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
          DatagramPacket packet = new DatagramPacket (buf, buf.length, address, this.clientPort);
          sendSocket.send(packet);
          
          sendSocket.close();
          //Receive UDP
          
          DatagramSocket recSocket = new DatagramSocket (this.serverPort);
          DatagramPacket recPacket;
          byte[] recBuf = new byte[256];

          recPacket = new DatagramPacket (recBuf, recBuf.length);
          recSocket.receive (recPacket);
          String received = new String (recPacket.getData());
          
          System.out.println ("Received packet: " + received);
          
          String RoomID = received.substring(0,3);
			String operation = received.substring(3, 6);
			String sesCode = received.substring(6,10);
			
			if (Integer.parseInt(RoomID) == (this.roomID))
			{
				if(operation.equals("PASS"))
				{
					CardReaderGUI.updateImage(0);
				}
				if(operation.equals("FAIL"))
				{
					CardReaderGUI.updateImage(2);
				}
				if(operation.equals("LATE"))
				{
					CardReaderGUI.updateImage(1);
				}
				
			}
          
          
          
          
          recSocket.close();

		}
		catch(IOException e)
		{
			
		}
		
		
			
			
			
}			
			
}
