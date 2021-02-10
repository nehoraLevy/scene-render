package elements;

import primitives.*;

/**
 * interface Light Source that all kinds of lights implements it and the functions
 * @author nehora levy and batya lasry
 *
 */
public interface LightSource
{
	/**
	 * the function to return the intensity of the light
	 * @param p-Point3D 
	 * @return Color
	 */
	public Color getIntensity(Point3D p);
	/**
	 * getter of the direction of the light
	 * @param p-Point3D
	 * @return Vector
	 */
	public Vector getL(Point3D p);
	/**
	 * getter of the distance between the point on picture to the light
	 * @param point-Point3D
	 * @return double
	 */
	public double getDistance(Point3D point);


}
