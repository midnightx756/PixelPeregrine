package printing;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class HardwareManager {
	    // ESC/P Commands
    private static final byte[] INIT = {0x1B, 0x40}; // ESC @ (Reset Printer)
    private static final byte[] LINE_SPACING_18 = {0x1B, 0x33, 0x12}; // ESC 3 18 (Sets line spacing for text)
    private static final byte[] FORM_FEED = {0x0C}; // Form Feed (Eject page)

    public static boolean sendToPrinter(String asciiData) {
        try (FileOutputStream out = new FileOutputStream("LPT1")) {
            // 1. Send Initialization
            out.write(INIT);
            out.write(LINE_SPACING_18);

            // 2. Send your ASCII data
            out.write(asciiData.getBytes());

            // 3. Send Eject command
            out.write(FORM_FEED);

            out.flush();
            return true;
        } catch (Exception e) {
            // Handle
            return false;
        }
    }
}
