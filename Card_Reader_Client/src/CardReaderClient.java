
/* Commands for communication
 * XXXYYYREST
 * XXX == room_id e.g. 101
 * YYY == function e.g. UPD
 * REST == data
 * 
 * Receiving
 * UPD == Update display for session, - for demo purposes only, wont be in string
 * REST == Sessioncode(A)-Sessionname(B)-Starttime(C)-Endtime(d)
 * A== String 11
 * B== String 30
	 * C== String 8
		 * D== String 8
		 * 
		 * Example:
		 * 101UPDCSY1234/01Test Session                  10:00:0011:00:00
 * 
 * CON == confirmation of student registration
 * REST == String 4
 * PASS
 * FAIL
 * 
 * e.g
 * 101CONPASS
 * 
 * Sending
 * REG == student registration
 * REST == int 8 student_id
 * 
 * e.g.
 * 101REG12345678
 */



public class CardReaderClient {
	public static void main(String[] args) {
		
		 new CardReaderGUI();
		  Session ses = new  Session(Settings.broadcastIP, Integer.parseInt(Settings.clientPort), Integer.parseInt(Settings.roomID));
		 ses.start();
			Update up = new	Update(Integer.parseInt(Settings.clientPort2), Integer.parseInt(Settings.roomID));
			up.start();
		 
		 
	}
}

