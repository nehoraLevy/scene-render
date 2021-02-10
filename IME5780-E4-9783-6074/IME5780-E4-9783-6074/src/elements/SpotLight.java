package elements;

import primitives.*;
/**
 * class SpotLight extends PointLight, it like a spot
 * @author batya Lasry and Nehora Levy
 *
 */
public class SpotLight extends PointLight 
{
	/**
	 * _direction- the field of the direction of the spot light
	 */
	private Vector _direction;
	/**
	 * parameter constructor,
	 * send to father
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param direction
	 */

	public SpotLight(Color intensity,Point3D position, Vector direction,double kC, double kL, double kQ)
	{
		super(intensity, position, kC, kL,kQ);
		_direction=new Vector(direction).normalized();
	}

	@Override
	public Color getIntensity(Point3D p) 
	{
        double max=_direction.dotProduct(getL(p));
        if (Util.isZero(max)) 
        {
            return Color.BLACK;
        }
        max=Math.max(0, max);
        
        return (super.getIntensity(p)).scale(max);
	}
}