package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * class Triangle that represents a Polygon with 3 points 
 * the father is Polygon
 * @author Batya Tamsot and Nehora Levy
 */
public class Triangle extends Polygon
{
	/**
	 * constructor that get 3 points, and send them to the super
	 * @param p1-type:Point3D
	 * @param p2-type:Point3D
	 * @param p3-type:Point3D
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3)
	{
		super(p1, p2, p3);
	}
	/**
	 * parameter constructor with Colors
	 * @param color
	 * @param p1-type:Point3D
	 * @param p2-type:Point3D
	 * @param p3-type:Point3D
	 */
	public Triangle(Color color,Point3D p1, Point3D p2, Point3D p3)
	{
		super(color,p1, p2, p3);
		
	}
	/**
	 * additional parameter constructor with Material
	 * @param material
	 * @param color
	 * @param p1-type:Point3D
	 * @param p2-type:Point3D
	 * @param p3-type:Point3D
	 */
	public Triangle(Material material,Color color,Point3D p1, Point3D p2, Point3D p3)
	{
		super(material,color,p1,p2, p3);
	}
	/*************** Admin *****************/
	@Override
	public String toString() 
	{
		return "Triangle: vertices=" + _vertices + "plane=" + _plane.toString() + "";
	}

	@Override
	/**
	 * getNormal that get point and return the triangle normal
	 * @param point type:Point3D- send the normal from the point
	 * @return Vector
	 */
	public Vector getNormal(Point3D point) 
	{
		return super.getNormal(point);
	}
	/**
	 * find intersections of ray in the triangle, calculate where the ray intersect the triangle
	 * @param ray- type Ray
	 * @return List<Point3D> of intersection points
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray)
	{

		List<GeoPoint> intersections = _plane.findIntersections(ray);
		if (intersections == null)
		{
			return null;
		}

		Point3D p0 = ray.get_p0();
		Vector v = ray.get_dir().normalize();

		Vector v1 = _vertices.get(0).subtract(p0).normalize();
		Vector v2 = _vertices.get(1).subtract(p0).normalize();
		Vector v3 = _vertices.get(2).subtract(p0).normalize();

		double s1 = v.dotProduct(v1.crossProduct(v2));
		double s2 = v.dotProduct(v2.crossProduct(v3));
		double s3 = v.dotProduct(v3.crossProduct(v1));

		if(isZero(s1) || isZero(s2) || isZero(s3))
		{
			return null;
		}

		if((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))
		{
			return intersections;
		}
		return null;
	}
}

