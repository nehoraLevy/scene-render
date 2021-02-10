package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
/**
 * Class Sphere that represents RadialGeometry with a center Point3D, 
 * the father is RadialGeometry
 * @author Batya Tamsot and Nehora Levy
 *
 */
public class Sphere extends RadialGeometry
{
	/**
	 * Point 3D field _center, the center of Sphere 
	 */
	protected Point3D _center;
	/**
	 * constructor that get radius and point
	 * @param _r- the radius double value, send to super
	 * @param point- the _center Point3D value
	 */
	public Sphere(double _r, Point3D point)
	{
		super(_r);
		_center=point;
	}
	/**
	 * additional parameter constructor, with color
	 * @param color
	 * @param _r-the radius double value, send to super 
	 * @param point -the _center Point3D value
	 */
	public Sphere(Color color,double _r, Point3D point)
	{
		super(color,_r);
		_center=new Point3D(point);
	}
	/**
	 * additional parameter constructor, with Material
	 * @param material
	 * @param color
	 * @param _r--the radius double value, send to super
	 * @param point -the _center Point3D value
	 */
	public Sphere(Material material,Color color,double _r, Point3D point)
	{
		super(material,color, _r);
		_center= new Point3D(point);
	}
	/**
	 * getter of the field _center
	 * @return Point3D
	 */
	public Point3D get_center() 
	{
		return _center;
	}
	
	@Override
	/**
	 * getNormal that get point and return the sphere normal
	 * @param point type:Point3D- send the normal from the point
	 * @return Vector
	 */
	public Vector getNormal(Point3D point)
	{
		Vector vec=new Vector(point.subtract(_center)); 
		Vector normal=new Vector(vec.normalize());
		return normal;
	}
	/**
	 * calculate the intersection of ray with the sphere
	 * @param ray
	 * @return List<Point3D> of intersection points
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray)
	{
		List<GeoPoint> lst=new ArrayList<GeoPoint>();
		Vector u=this._center.subtract(ray.get_p0());
		double tm=ray.get_dir().dotProduct(u);
		double d=Math.sqrt((u.length()*u.length())-(tm*tm));
		if(d>_radius)
			return null;
		double th=Math.sqrt((_radius*_radius)-(d*d));
		double t1=tm-th;
		double t2=tm+th;
		if(t1>0)		
			lst.add(new GeoPoint(this,ray.getPoint(t1)));
		if(t2>0)
			lst.add(new GeoPoint(this,ray.getPoint(t2)));
		if(lst.isEmpty())
			return null;
		return lst;
	}

	/*************** Admin *****************/
	@Override
	public String toString() {
		return "Sphere: center=" + _center.toString() + "";
	}
	
}

