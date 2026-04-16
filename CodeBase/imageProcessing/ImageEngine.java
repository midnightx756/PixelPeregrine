package imageProcessing;

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
    //[Alpha][Red][Green][Blue]

    public int getLuma(int pixel){
        int r = (pixel >> 16) & 0xFF; // Shift right 16
        int g = (pixel >> 8) & 0xFF;  // Shift right 8
        int b = (pixel) & 0xFF;       // No shift
        // Luminance formula: 0.299R + 0.587G + 0.114B
        // We multiply by 1024 (1<<10) to use integer math instead of double
        return (299 * r + 587 * g + 114 * b) >> 10;
    }

    public static String applyDitheringAndConvert(BufferedImage img, PrintConfig config) {
       int w = img.getWidth();
        int h = img.getHeight();
        float[][] errorBuffer = new float[h][w];
        StringBuilder asciiArt = new StringBuilder();

        // Use the dynamic string from config
        String chars = config.densityChars;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                // 1. Get current pixel brightness + added error from neighbors
                int rgb = img.getRGB(x, y);
                float oldPixel = (float) getLuma(rgb) + errorBuffer[y][x];

                // 2. Quantize (Binarize)
                float newPixel = (oldPixel > 128) ? 255 : 0;

                // 3. Calculate Error
                float error = oldPixel - newPixel;

                // 4. Distribute Error to neighbors (Floyd-Steinberg ratios)
                if (x + 1 < w) errorBuffer[y][x + 1] += error * 7/16.0;
                if (y + 1 < h) {
                    if (x > 0) errorBuffer[y + 1][x - 1] += error * 3/16.0;
                    errorBuffer[y + 1][x] += error * 5/16.0;
                    if (x + 1 < w) errorBuffer[y + 1][x + 1] += error * 1/16.0;
                }

                // 5. Map to ASCII (using brightness)
                int index = (int) (newPixel / 255.0 * (chars.length() - 1));
                asciiArt.append(chars.charAt(index));
            }
            asciiArt.append("\n"); // New line at row end
        }
        return asciiArt.toString();
    }
}
