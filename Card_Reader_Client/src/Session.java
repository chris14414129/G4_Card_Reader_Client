import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Session extends Thread {
	private String broadcastIP;
	private int serverPort;
	private int roomID;
	
	public Session(String ip,int port, int room)
	{
		this.broadcastIP=ip;
		this.serverPort=port;
		this.roomID=room;
	}

	public void run()
	{
		
		
		
		 DatagramSocket socket = null;
		    DatagramPacket packet = null;
			BufferedReader in=null;
			String inputLine=null;
			
			
			
			
			String operation = null;
			while((!Thread.currentThread().isInterrupted()))
			{	
				try
				{
					socket = new DatagramSocket (4455);
			           
			            byte[] buf = new byte[256];
			 
			            packet = new DatagramPacket (buf, buf.length);
			            socket.receive (packet);
			            String received = new String (packet.getData());
			         //   System.out.println ("Received packet: " + received);
					
					System.out.println(received);
					
					
					String inRoomID = received.substring(0,3);
					operation = received.substring(3, 6);
					String sesCode = received.substring(6,17);
					String sesName = received.substring(17,47);
					String startTime = received.substring(47,55);
					String endTime = received.substring(55, 63);
					
				/*	System.out.println(inRoomID);
					System.out.println(operation);
					System.out.println(sesCode);
					System.out.println(sesName);
					System.out.println(startTime);
					System.out.println(endTime);*/
					
					//public static void sessionInfo(String sesCode,String sesName,String start, String end)
					
					
					if (inRoomID.equals("014"))	
					{
						//System.out.println("if1");
						
						if (operation.equals("UPD"))
						{
							//System.out.println("if2");
						CardReaderClient.sessionInfo(sesCode, sesName, startTime, endTime);
						}
						
					}
					socket.close();
				}
				catch (IOException e)
				{
					System.out.println(e);
				}
				
				//System.exit(0);

			}
			
	}
}
