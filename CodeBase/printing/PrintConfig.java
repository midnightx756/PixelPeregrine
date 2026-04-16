package printing;

public class PrintConfig {
     // Hardware path - Change to "USB0" or network path if LPT1 fails
    public String printerPort = "LPT1";

    // Protocol: Draft Mode (Speed)
    public byte[] draftMode = {0x1B, 0x78, 0x00};

    // Protocol: Bidirectional Printing (Speed)
    public byte[] biDirMode = {0x1B, 0x55, 0x01};

    // Protocol: Line Spacing (1/6 inch)
    public byte[] lineSpacing = {0x1B, 0x33, 0x12};
}
