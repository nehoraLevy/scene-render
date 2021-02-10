package geometries;

import geometries.Geometry;
import geometries.RadialGeometry;
import primitives.*;

/**
 * abstract class RadialGeometry representing geometry with radius and  that implements Geometry 
 * @author Batya Tamsot and Nehora Levy
 *
 */
public abstract class RadialGeometry extends Geometry 
{
	/**
	 * double value-the radius Radial Geometry
	 */
	public double _radius;
	/**
	 * constructor
	 * @param _r-double value radius
	 */
	public RadialGeometry(double _r)
	{
		_radius=_r;

	}
	/**
	 * copy constructor
	 * @param other-type RadialGeometry
	 */
	public RadialGeometry(RadialGeometry other)
	{
		_radius=other._radius;
	}
	/**
	 * additional constructor, with color
	 * @param color
	 * @param _r-radius
	 */
	public RadialGeometry(Color color,double _r)
	{
		this(_r);
		_emission=new Color(color);
		_material=new Material(0,0,0);
	}
	/**
	 * additional constructor, with Material
	 * @param material
	 * @param color
	 * @param _r-radius
	 */
	public RadialGeometry(Material material,Color color,double _r)
	{
		this(color, _r);
		_material=material;
	}
	/**
	 * getter of the field _radius
	 * @return _radius
	 */
	public double get_radius()
	{
		return _radius;
	}

	/*************** Admin *****************/
	@Override
	public String toString()
	{
		return "RadialGeometry: radius=" + _radius + "";
	}

}

