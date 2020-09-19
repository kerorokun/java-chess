package btn.utils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class BufferedImageLoader {

	Map<String, BufferedImage> imageMapping;
	
	public BufferedImageLoader() {
		imageMapping = new HashMap<>();
	}

	/*
	 * Load a buffered image
	 * @param path: the path to the resource to load
	 * @return The loaded image or null if the image fails to load
	 */
	public BufferedImage loadImage(String path) {
		if (imageMapping.containsKey(path)) {
			return imageMapping.get(path);
		}
		
		BufferedImage img = null;
		
		try {
            img = ImageIO.read(getClass().getResource(path));
			imageMapping.put(path, img);
        } catch (Exception e) {
			System.err.println("Failed to load " + path);
			return null;
        }

		return img;
	}
}
