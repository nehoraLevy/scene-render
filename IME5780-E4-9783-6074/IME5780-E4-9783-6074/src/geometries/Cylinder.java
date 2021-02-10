package geometries;

import primitives.*;

/**
 * Class Cylinder that represents a Tube with a height,
 * the father is Tube
 * @author Batya Tamsot and Nehora Levy
 */
public class Cylinder extends Tube 
{
	/**
	 * _height- the field double value represents the height of Cylinder
	 */
	protected double _height;

	/** Cylinder constructor that get radius, ray and height
	 * @param _r- radius double value, send to super
	 * @param ray- axis ray value, send to super
	 * @param _h- _height double value field
	 */
	public Cylinder(double _r,Ray ray, double _h) 
	{
		super(_r,ray);
		_height=_h;
	}
	/**
	 * getter _height field
	 * @return double value _height
	 */
	public double get_height() 
	{
		return _height;
	}

	@Override

	public Vector getNormal(Point3D point)
	{
		return null;
	}

	/*************** Admin *****************/
	@Override
	public String toString() {
		return "Cylinder: height=" + _height + "";
	}


}

