package primitives;
/**
 * Class Ray is the class representing a Ray in Cartesian
 * The class is based on Point3D class and Vector class 
 * Point 3D start point _p0
 * Vector the direction of Ray _dir
 * @author Batya Tamsot and Nehora Levy
 */
public class Ray 
{
	private static final double DELTA = 0.1;
	/**
	 * The point from which the ray starts 
	 */
	private final Point3D _p0;
	/**
	 * The direction of the ray.
	 */
	private final Vector _dir; 
	//constructors
	/**
	 * Constructor for creating a new instance of this class
	 * @param point the start of the ray.
	 * @param direction the direction of the ray.
	 */
	public Ray(Point3D point, Vector direction)
	{
		this._p0 = new Point3D(point);
		this._dir = new Vector (direction);
		this._dir.normalize();
	}
	/**
	 * ray constructor
	 * @param point
	 * @param direction
	 * @param normal
	 */
	public Ray(Point3D point, Vector direction, Vector normal) 
	{
		//point + normal.scale(±DELTA)
		_dir = new Vector(direction).normalized();
		double nv = normal.dotProduct(direction);
		Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
		_p0 = point.add(normalDelta);
	}
	/**
	 * Copy constructor for a deep copy of an Ray object.
	 * @param other the object that being copied
	 */
	public Ray(Ray other)
	{
		this._p0 = other.get_p0();
		this._dir = other.get_dir();   
	}

	//getters
	/**
	 * Ray _p0-the starting Point3D value getter
	 *
	 * @return Point3D _p0 value of Ray
	 */	 
	public Point3D get_p0() 
	{
		return new Point3D(_p0);
	}
	/**
	 * Ray _dir-the direction Vector getter
	 *
	 * @return Vector _dir value of Ray
	 */
	public Vector get_dir() 
	{
		return _dir;
	}

	/*************** Admin *****************/
	@Override
	public String toString() 
	{
		return "Ray: p0=" + _p0.toString() + ", direction=" + _dir.toString() + "";
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Ray oth = (Ray) obj;
		return _p0.equals(oth._p0) &&
				_dir.equals(oth._dir);
	}
	/**
	 * return point=p0+direction*factoring scale
	 * @param t- the factoring scale
	 * @return Point3D
	 */
	public Point3D getPoint(double t) 
	{
		Point3D point=new Point3D(this.get_p0().add(this.get_dir().scale(t)));
		return point;
	}
}




