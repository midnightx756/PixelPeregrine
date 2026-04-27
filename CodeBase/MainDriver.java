
import frontEnd.*;
import imageProcessing.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class MainDriver {
	
	public static void main(String args[]) {
		// STEP 1: INITIALIZE CONFIGURATION
        // TODO: Instantiate PrintConfig object here.
        // This is our system-wide configuration object (The Contract).

        // STEP 2: LAUNCH UI LAYER
        // TODO: Trigger MainUI to start. The UI must return the 'BufferedImage'
        // selected by the user to this driver.

        // STEP 3: IMAGE PRE-PROCESSING (THE PIPELINE START)
        // TODO: Pass the raw BufferedImage to ImageEngine.correctAspectRatio().
        // Logic: This prepares the image geometry for the printer's specific aspect ratio.

        // STEP 4: ALGORITHMIC PROCESSING (THE ENGINE)
        // TODO: Pass the scaled image and the PrintConfig object to
        // ImageEngine.applyDitheringAndConvert().
        // Logic: This returns the final formatted String of ASCII characters.

        // STEP 5: HARDWARE COMMUNICATION (THE OUTPUT)
        // TODO: Send the ASCII string and PrintConfig to HardwareManager.sendToPrinter().
        // Logic: This triggers the ESC/P command injection and writes to the printer port.

        // STEP 6: AUDIT TRAIL (THE DATABASE LOG)
        // TODO: If sendToPrinter returns 'true', call HistoryLogger.logJob().
        // Logic: Log the filename and status (SUCCESS/FAILED) to the SQLite database.

        // STEP 7: ERROR HANDLING & SYSTEM SHUTDOWN
        // TODO: Implement a catch-all block to ensure the program exits gracefully
        // if any of the above steps throw an Exception.
		
		 try {
	            System.out.println("[SYSTEM] Initializing Drivers...");
	            // 1. Force Load SQLite Driver
	            Class.forName("org.sqlite.JDBC");

	            // 2. Initialize Database Schema (Runs your DBSetup logic)
	            DBSetup.main(args);

	            // 3. Launch UI
	            System.out.println("[SYSTEM] Booting UI Layer...");
	            MainUI.main(args);

	        } catch (ClassNotFoundException e) {
	            System.err.println("[CRITICAL] JDBC Driver not found! Check your JAR file.");
	        } catch (Exception e) {
	            System.err.println("[CRITICAL] System Failure: " + e.getMessage());
	            e.printStackTrace();
	        }
	}
}
