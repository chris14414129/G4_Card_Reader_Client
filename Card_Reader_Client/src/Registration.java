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
          
        //  System.out.println ("Received packet: " + received);
          
          boolean wait = false;
          
          while (wait == false)
          {
        	  System.out.println("wait");
        	  wait = true;
          }
          
          wait = false;
          recSocket.close();

		}
		catch(IOException e)
		{
			
		}
		
		
			
			
			
}			
			
}
