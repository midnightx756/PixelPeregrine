
import frontEnd.*;
import imageProcessing.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class MainDriver {
	
	public static void main(String args[]) {
		BufferedImage b = MainUI.getImageFromUser();
		b = ImageEngine.correctAspectRatio(b);
	}
}
