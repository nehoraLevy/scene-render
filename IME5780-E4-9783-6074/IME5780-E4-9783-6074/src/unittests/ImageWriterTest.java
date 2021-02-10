package unittests;

import java.awt.Color;

import org.junit.Test;

import renderer.ImageWriter;
/**
 * ImageWriterTest
 * @author Batya Tamsot and Nehora Levy
 */
public class ImageWriterTest {

	/**
	 * testing to the class ImageWriter
	 */
	@Test
	public void testWriteToImage() 
	{
		 ImageWriter imageWriter = new ImageWriter("image", 1600d, 1000d, 800, 500);

	        int Nx = imageWriter.getNx();

	        int Ny = imageWriter.getNy();

	        for (int i = 0; i < Ny; i++) {

	            for (int j = 0; j < Nx; j++) {

	                if (i % 50 == 0 || j % 50 == 0) {

	                    imageWriter.writePixel(j, i, Color.WHITE);

	                } else {

	                    imageWriter.writePixel(j, i, Color.BLACK);

	                }

	            }

	        }

	        imageWriter.writeToImage();
	        
	}

}
