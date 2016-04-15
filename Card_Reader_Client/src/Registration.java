import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Registration {
	private String broadcastIP;
	private int serverPort;

	private int roomID;
	private boolean tick;

	public Registration(String ip,int serverPort,  int room)
	{
		this.broadcastIP=ip;
		this.serverPort=serverPort;
		this.roomID= room;
	}

public void register(String student_id)
{
		String studID = student_id;
	
		try
		{
			String r = Integer.toString(this.roomID);
			String room = String.format("%3s", r).replace(" ", "0");
		
			//send UDP
		  DatagramSocket sendSocket = new DatagramSocket ();
          byte[] buf = new byte[256];
         String messg = room + "REG" + studID;
     
        System.out.println(messg);
         
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

