package geometries;
import java.util.ArrayList;
import java.util.List;
import static primitives.Util.*;
import primitives.*;
/**
 * class Plane which represents an infinite plane and  implements the interface Geometry
 * @author Batya Tamsot and Nehora Levy
 *
 */
public class Plane extends Geometry
{
	/**
	 * field of Point3D
	 * point of the plane
	 */
	protected Point3D _p;
	/**
	 * field of Vector
	 * the vector normal of the plane
	 */
	protected Vector _normal;
	/**
	 * parameter constructor that get point and vector
	 * @param point-type:Point3D
	 * @param vec-type:Vector
	 */
	public Plane(Point3D point, Vector vec)
	{
		_p=point;
		_normal=vec;
	}
	
	
	/**
	 * parameter constructor that get 3 points
	 * @param p1-type:Point3D
	 * @param p2-type:Point3D
	 * @param p3-type:Point3D
	 */
	public Plane(Point3D p1,Point3D p2, Point3D p3)
	{
		_p=p1;
		Vector v1= new Vector(p2.subtract(p1));
		Vector v2=new Vector(p3.subtract(p1));
		Vector vec=new Vector(v1.crossProduct(v2));
		Vector normal= new Vector(vec.normalize());
		_normal=normal;
	}
	
	/**
	 * additional parameter constructor  with Color
	 * @param color
	 * @param point
	 * @param vec
	 */
	public Plane(Color color,Point3D point, Vector vec)
	{
		this(point,vec);
		_emission=new Color(color);
		_material=new Material(0,0,0);
	}
	/**
	 * additional parameter constructor with Material
	 * @param material
	 * @param color
	 * @param point
	 * @param vec
	 */
	public Plane(Material material,Color color,Point3D point, Vector vec)
	{
		this(color,point,vec);
		_material=material;
	}
	/**
	 * getter of the field _p
	 * @return Point3D
	 */
	public Point3D get_p() 
	{
		return _p;
	}
	/**
	 * getter of the field _normal
	 * @return Vector 
	 */
	public Vector get_normal() 
	{
		return _normal;
	}

	@Override
	/**
	  getNormal get a point and return the plan normal
	  @param point-type:Point3D
	  @return vector _normal
	 */
	public Vector getNormal(Point3D point) 
	{
		return _normal;
	}

	@Override
	/**
	 * find intersections of ray in the plane, calculate where the ray intersect the plane
	 * @param ray
	 * @return List<Point3D> intersection points
	 */
	public List<GeoPoint> findIntersections(Ray ray)
	{
		List<GeoPoint> intersection=new ArrayList<GeoPoint>();
		double numerator= this.get_normal().dotProduct((this.get_p()).subtract(ray.get_p0()));
		if((this.get_p()).equals(ray.get_p0()))
		{
			intersection.add(new GeoPoint(this,ray.get_p0()));
			return intersection;
		}
		if(isZero(numerator))
		{
			return null;
		}

		double denominator= this.get_normal().dotProduct(ray.get_dir());
		if(isZero(denominator))
		{
			return null;
		}
		double t= alignZero(numerator/denominator);
		if(t>0)
		{
			Point3D p=new Point3D(ray.getPoint(t));
			intersection.add(new GeoPoint(this,p));
			return intersection;
		}
		return null;

	}
	/*************** Admin *****************/
	@Override
	public String toString() 
	{
		return "Plane: point=" + _p.toString() + ", normal=" + _normal.toString() + "";
	}

}

