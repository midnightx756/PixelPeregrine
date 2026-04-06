package printing;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class HardwareManager {
	public static boolean sendToPrinter(String asciiData) {
		OutputStream out = null;
		
		try {
			//Open printer port (example LPT1)
			out = new FileOutputStream("LPT1");
			
			//Convert String to raw Bytes 
			byte[] data = asciiData.getBytes();
			
			//Send Data
			out.write(data);
			
			//Ensure everything is sent
			out.flush();
			
			System.out.println("Print Job Successful");
			return true;
		}
		catch(Exception e) {
			//Handle all failures Gracefully
			System.err.println("Printing failed: " + e.getMessage());
			return false;
		}
		finally {
			//Ensure resource is always closed
			try {
				if(out != null) {
					out.close();
				}
			}
			catch(Exception e) {
				System.out.println("Error closing stream: " + e.getMessage());
			}
		}
	}
}
