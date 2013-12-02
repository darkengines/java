package darkengines.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageHelper {
	public static BufferedImage resizeCenter(BufferedImage image, int width, int height) {
		int h = image.getHeight();
		int w = image.getWidth();
		int x = 0;
		int y = 0;
		int side = 0;
		if (h >= w) {
			y = (h - w)/2;
			side = w;
		}
		if (h <= w) {
			x = (w - h)/2;
			side = h;
		}
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, x, y, x+side, y+side, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		return resizedImage;
	}
}
