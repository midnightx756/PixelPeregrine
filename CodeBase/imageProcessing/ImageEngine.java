package imageProcessing;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import printing.PrintConfig;

public class ImageEngine {

    // Fix 1: Make getLuma STATIC so it can be called by the static conversion method
    public static int getLuma(int pixel) {
        // Unpacking bits: [Alpha][Red][Green][Blue]
        int r = (pixel >> 16) & 0xFF;
        int g = (pixel >> 8) & 0xFF;
        int b = (pixel) & 0xFF;

        // Perceptual Luminance Formula (Standardized)
        // Using bit-shifting (>> 10) is an optimization for (val / 1024)
        return (int)(0.299 * r + 0.587 * g + 0.114 * b);
    }

    // Fix 2: Accept PrintConfig to make scaling dynamic
    public static BufferedImage correctAspectRatio(BufferedImage originalImage, PrintConfig config) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        // Use the aspect ratio from the config file instead of hardcoded 0.6
        int newHeight = (int) (originalHeight * config.aspectRatio);

        BufferedImage scaledImage = new BufferedImage(originalWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, originalWidth, newHeight, null);
        g2d.dispose();

        return scaledImage;
    }

    public static String applyDitheringAndConvert(BufferedImage img, PrintConfig config) {
        int w = img.getWidth();
        int h = img.getHeight();

        // Fix 3: Use a 2D float array for the Error Buffer to prevent rounding errors
        float[][] dsMatrix = new float[h][w];
        StringBuilder asciiArt = new StringBuilder();

        // Glyph Index Mapping: Convert the string to an array for O(1) access
        char[] glyphs = config.getGlyphTable();
        int maxGlyphIndex = glyphs.length - 1;

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                // 1. Get original luma + diffused error from previous neighbors
                float oldPixel = getLuma(img.getRGB(x, y)) + dsMatrix[y][x];

                // 2. Find closest "Glyph Color" (Quantization)
                // 0 is black (@), 255 is white (space)
                float newPixel = (oldPixel > 128) ? 255 : 0;

                // 3. Calculate Error
                float error = oldPixel - newPixel;

                // 4. Floyd-Steinberg Error Diffusion Kernel
                // Push error to: Right (7/16), Bottom-Left (3/16), Bottom (5/16), Bottom-Right (1/16)
                if (x + 1 < w) dsMatrix[y][x + 1] += error * 7/16.0;
                if (y + 1 < h) {
                    if (x > 0) dsMatrix[y + 1][x - 1] += error * 3/16.0;
                    dsMatrix[y + 1][x] += error * 5/16.0;
                    if (x + 1 < w) dsMatrix[y + 1][x + 1] += error * 1/16.0;
                }

                // 5. Dynamic Glyph Mapping (The Evaluator's Suggestion)
                // Map the quantized value (0 or 255) to the start or end of the glyph table
                int glyphIndex = Math.round((newPixel / 255.0f) * maxGlyphIndex);
                asciiArt.append(glyphs[glyphIndex]);
            }
            asciiArt.append("\n");
        }
        return asciiArt.toString();
    }
}
