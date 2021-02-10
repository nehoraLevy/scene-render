package elements;

import primitives.*;

/**
 * abstract class Light that all kinds of lights extends it 
 * @author Batya Lasry/Tamsot and Nehora Levy
 *
 */
abstract class Light 
{
	/**
	 * the color of the light
	 */
	protected Color _intensity;
	/**
	 * parameter constructor
	 * @param color
	 */
	public Light(Color color)
	{
		_intensity=new Color(color);
	}

	/**
	 * getter of _intensity
	 * @return Color
	 */
	public Color getIntensity() 
	{
		return _intensity;
	}

}
