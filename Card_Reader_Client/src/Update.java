import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

class Update extends Thread
{
	private int roomID;
	private int clientPort;
	
	public Update(int clientPort, int roomID) 
	{
		this.roomID=roomID;
		this.clientPort=clientPort;
	}
	
	
	public void run() 
	{
		  //Receive UDP
		
		try
		{
        
        DatagramSocket recSocket = new DatagramSocket (this.clientPort);
        DatagramPacket recPacket;
        byte[] recBuf = new byte[256];

       
      
        while((!Thread.currentThread().isInterrupted()))
		{		
			
        	 recPacket = new DatagramPacket (recBuf, recBuf.length);
             recSocket.receive (recPacket);
      
        String received = new String (recPacket.getData());
        
        System.out.println ("Received packet: " + received);
        
        String RoomID = received.substring(0,3);
			String operation = received.substring(3, 6);
			String response = received.substring(6,10);
			
			System.out.println("regop: "+operation);
			
			if (Integer.parseInt(RoomID) == (this.roomID))
			{
				if(operation.equals("REG"))
				{
					if(response.equals("PASS"))
					{
						CardReaderGUI.updateImage(0);
					}
					if(response.equals("FAIL"))
					{
						CardReaderGUI.updateImage(2);
					}
					if(response.equals("LATE"))
					{
						CardReaderGUI.updateImage(1);
					}
				}
		
			}
				
			//recSocket.close();
			}
			System.out.println("post");
			
		}
		catch(IOException e)
		{
			
		}
		
	}
	
	
}

