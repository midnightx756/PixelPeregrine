import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageEngine {

    /**
     * Squashes the image vertically to compensate for the tall rectangular
     * shape of Dot Matrix Printer characters (approx 1:1.6 ratio).
     */
    public static BufferedImage correctAspectRatio(BufferedImage originalImage) {

        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        // The magic number: 0.6 (or 1/1.6).
        // We shrink the height in software so the printer stretches it back to normal in hardware.
        int newWidth = originalWidth;
        int newHeight = (int) (originalHeight * 0.6);

        // 1. Create a new, blank canvas of the squashed size
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        // 2. Get the Graphics2D engine to draw on our new canvas
        Graphics2D g2d = scaledImage.createGraphics();

        // 3. Set rendering hints to ensure we don't lose quality during the squash
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // 4. Draw the original image onto the new canvas, forcing it into the new dimensions
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);

        // 5. CRITICAL: Always dispose of the graphics object to prevent memory leaks (Buffer Bloat)
        g2d.dispose();

        return scaledImage;
    }
}
