package geometries;

import java.util.List;

import primitives.*;
/**
 * class Tube that her father is RadialGeometry, and is endless Cylinder
 * field _radius from RadialGeometry
 * @author Batya Tamsot and Nehora Levy
 */
public class Tube extends RadialGeometry
{
	/**
	 * _axisRay-the ray of the tube is single field in class
	 */
	protected Ray _axisRay;
	/**
	 * constructor that get _r-radius end ray
	 * @param _r-type:double value radius, and its send to super
	 * @param ray- type:Ray value, and its the _axisRay field
	 */
	public Tube(double _r, Ray ray)
	{
		super(_r);
		_axisRay=ray;
	}
	/**
	 * additional constructor with Color
	 * @param color
	 * @param _r
	 * @param ray
	 */
	public Tube(Color color,double _r, Ray ray)
	{
		super(color,_r);
		_axisRay=ray;
	}

	/**
	 * getter to the field _axisRay 
	 * @return Ray
	 */
	public Ray get_axisRay() 
	{
		return _axisRay;
	}


	@Override
	/**
	 * getNormal that get point and return the tube normal
	 * @param point-type:point3D to find the 
     O point in the tube
	 * @return Vector
	 */
	public Vector getNormal(Point3D point)
	{
		Vector v=new Vector(this.get_axisRay().get_dir());
		double t= (v.dotProduct(
				point.subtract(this.get_axisRay().get_p0())));
		Point3D pointO= (this.get_axisRay().get_p0()).add((v.scale(t))); 
		Vector vec=new Vector(point.subtract(pointO));//when the ray start.
		return vec;  
	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray)
	{
		return null;
	}
	/*************** Admin *****************/
	@Override
	public String toString() 
	{
		return "Tube: axisRay=" + _axisRay.toString() + "";
	}
}

