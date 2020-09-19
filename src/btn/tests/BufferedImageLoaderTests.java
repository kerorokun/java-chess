package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import btn.utils.BufferedImageLoader;
import java.awt.image.BufferedImage;

public class BufferedImageLoaderTests {

	@Test
	public void testLoadImageValid() {
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage image = loader.loadImage("/assets/black_centaur.png");
		assertNotEquals(image, null);
	}

	@Test
	public void testLoadImageValidDuplicate() {
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage image = loader.loadImage("/assets/black_centaur.png");
		assertNotEquals(image, null);
		BufferedImage image2 = loader.loadImage("/assets/black_centaur.png");
		assertNotEquals(image2, null);
	}

	@Test
	public void testLoadImageInvalid() {
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage image = loader.loadImage("/fakepath");
		assertEquals(image, null);
	}
	
}
