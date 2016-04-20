import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Session extends Thread {
	private int clientPort;
	private int roomID;
	
	public Session(int port, int room)
	{
		this.clientPort=port;
		this.roomID=room;
	}

	public void run()
	{

		 DatagramSocket socket = null;
		 DatagramPacket packet = null;
		 String operation = null;
		 
			while((!Thread.currentThread().isInterrupted()))
			{	
				try
				{
					System.out.println("test");
					socket = new DatagramSocket (this.clientPort);
			           
			            byte[] buf = new byte[256];
			 
			            packet = new DatagramPacket (buf, buf.length);
			            socket.receive (packet);
			            String received = new String (packet.getData());
			         //   System.out.println ("Received packet: " + received);
					
	
					
					
					String inRoomID = received.substring(0,3);
					operation = received.substring(3, 6);
					String sesCode = received.substring(6,17);
					String sesName = received.substring(17,47);
					String startTime = received.substring(47,55);
					String endTime = received.substring(55, 63);
					
					System.out.println("roomid: "+inRoomID);
					System.out.println("operation: "+operation);
					System.out.println("sescode: "+sesCode);
					System.out.println("sesname: "+sesName);
					System.out.println("starttime: "+startTime);
					System.out.println("endtime: "+endTime);
					
					//public static void sessionInfo(String sesCode,String sesName,String start, String end)
					
					String r = Integer.toString(this.roomID);
					String room = String.format("%3s", r).replace(" ", "0");
					
					
					System.out.println(Integer.parseInt(inRoomID));
					
					if (inRoomID.equals(room));
					{
						System.out.println("if1");
						
						if (operation.equals("UPD"))
						{
							System.out.println("if2");
				//		CardReaderClient.sessionInfo(sesCode, sesName, startTime, endTime);
							CardReaderGUI.updateText(startTime,endTime, sesName, sesCode);
							
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
