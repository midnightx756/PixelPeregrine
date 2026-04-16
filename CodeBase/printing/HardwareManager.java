package printing;

import java.io.FileOutputStream;

public class HardwareManager {

    public static boolean sendToPrinter(String asciiData, PrintConfig config) {
        // Use the port from your config object
        try (FileOutputStream out = new FileOutputStream(config.printerPort)) {

            // 1. Reset Printer (The Protocol)
            out.write(new byte[]{0x1B, 0x40});

            // 2. Inject Configs (The Dynamic Settings)
            out.write(config.draftMode);
            out.write(config.biDirMode);
            out.write(config.lineSpacing);

            // 3. Payload (The ASCII Data)
            out.write(asciiData.getBytes());

            // 4. Termination
            out.write(new byte[]{0x0C}); // Form Feed

            out.flush();
            return true;
        } catch (Exception e) {
            System.err.println("Hardware Error: " + e.getMessage());
            return false;
        }
    }
}
