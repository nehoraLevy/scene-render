package elements;

import primitives.Color;
/**
 * The Ambient light is the color background to the picture,
 * class AmbientLight extends from Light class
 * @author batya Lasry and Nehora Levy
 *
 */
public class AmbientLight extends Light 
{
	/**
	 * Ambient Light constructor, and send to the father
	 * @param Ia
	 * @param Ka
	 */
	public AmbientLight(Color Ia, double Ka)
	{
		super(Ia.scale(Ka));
	}
	

}
