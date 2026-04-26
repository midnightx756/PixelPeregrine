package printing;

public class PrintConfig {
    public String printerPort = "LPT1";
    public double aspectRatio = 0.6; // Vertical squash factor

    // The character set ordered from DARKEST to LIGHTEST
    public String densityChars = "@#8&o:*. ";

    public byte[] draftMode = {0x1B, 0x78, 0x00};
    public byte[] biDirMode = {0x1B, 0x55, 0x01};
    public byte[] lineSpacing = {0x1B, 0x33, 0x12};

    // Logic for Evaluator: Return characters as a searchable array
    public char[] getGlyphTable() {
        return densityChars.toCharArray();
    }
}
