package elements;

import primitives.*;
/**
 * class DirectionalLight extends Light implements LightSource
 * @author batya Lasry and Nehora Levy
 *
 */
public class DirectionalLight extends Light implements LightSource 
{
	/**
	 * the direction of the light
	 */
	private Vector _direction;
	/**
	 * parameter constructor
	 * @param intensity
	 * @param direction
	 */
	public DirectionalLight(Color intensity,Vector direction)
	{
		super(intensity);
		_direction=new Vector(direction.normalize());
		
	}
	/**
	 * return POSITIVE_INFINITY- the distance from the light 
	 * @param point
	 * @return double distance
	 */
	public double getDistance(Point3D point)
	{
		return Double.POSITIVE_INFINITY;
	}
	@Override
	public Color getIntensity(Point3D p)
	{
		return _intensity;
	}
	@Override
	public Vector getL(Point3D p) 
	{
		return _direction.normalize();
	}

}
