import java.io.BufferedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Registration {
	private String broadcastIP;
	private int serverPort;
	private int roomID;

	public void Registration(String ip,int port, int room)
	{
		this.broadcastIP=ip;
		this.serverPort=port;
		this.roomID= room;
	}

	public void run()
	{
		 DatagramSocket socket = null;
		    DatagramPacket packet = null;
			BufferedReader in=null;
			String inputLine=null;
			//String t =  CardReaderClient.getText()
			
			
			

	}
}
