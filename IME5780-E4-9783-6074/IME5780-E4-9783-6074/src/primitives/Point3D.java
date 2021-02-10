package primitives;
import java.lang.Math;
/**
 * Class Point3D is the class representing a Point 3D in Cartesian
 * 3-Dimensional coordinate system. The class is based on Coordinate class
 * @author Batya Tamsot and Nehora Levy
*/

public class Point3D 
{
	/**
	 * const ZERO , value is point 3D (0,0,0)
	 */
	public final static Point3D ZERO=new Point3D(0,0,0); 
	/**
	 * Point3D-3 Coordinates in 3D (_x,_y,_z)
	 */
	protected Coordinate _x;
	protected Coordinate _y;
	protected Coordinate _z;
	//constructors
	/**
     * Point3D constructor 3 coordinates value
     *
     * @param x1 coordinate value
     *  @param y1 coordinate value
     *  @param z1 coordinate value
     */
	public Point3D(Coordinate x1, Coordinate y1, Coordinate z1)
	{
		_x=x1;
		_y=y1;
		_z=z1;
	}
	/**
     * Point3D constructor 3 double value
     *
     * @param x1 double value
     *  @param y1 double value
     *  @param z1 double value
     */
	public Point3D(double x1, double y1, double z1)
	{
		_x=new Coordinate(x1);
		_y=new Coordinate(y1);
		_z=new Coordinate(z1);
	}
	/**
     * Copy constructor for Point3D
     *
     * @param other
     */
	public Point3D(Point3D other)
	{
		_x=other._x;
		_y=other._y;
		_z=other._z;
	}
    //getters
	/**
     * Point3D x value getter
     *
     * @return coordinate _x value of Point3D
     */
	public Coordinate get_x() {
		return _x;
	}
	/**
     * Point3D y value getter
     *
     * @return coordinate _y value of Point3D
     */
	public Coordinate get_y() {
		return _y;
	}
	/**
     * Point3D z value getter
     *
     * @return coordinate _z value of Point3D
     */
	public Coordinate get_z() {
		return _z;
	}
	
	//functions
	/**
	 * subtract between two points
	 * @param point- type Point3D
	 * @return Vector
	 */
	public Vector subtract(Point3D point) 
	{
		return new Vector(this.get_x().get_coord()- point.get_x().get_coord(),
				 this.get_y().get_coord()- point.get_y().get_coord(),
				 this.get_z().get_coord()- point.get_z().get_coord());	
	}
	/**
	 * add between point to vector, return point
	 * @param vec-type Vector
	 * @return Point3D 
	 */
	public Point3D add(Vector vec)
	{ 
		return new Point3D((vec.get_head().get_x().get_coord()+this.get_x().get_coord()),
				(vec.get_head().get_y().get_coord()+this.get_y().get_coord()),
				(vec.get_head().get_z().get_coord()+this.get_z().get_coord()));
	}
	/**
	 * calculate distanceSquared between two points
	 * @param point-type is Point3D
	 * @return double value-distanceSquared
	 */
	public double distanceSquared(Point3D point)
	{
		double distanceSquared=
				(point.get_x().get_coord()-this.get_x().get_coord())*(point.get_x().get_coord()-this.get_x().get_coord())+
				(point.get_y().get_coord()-this.get_y().get_coord())*(point.get_y().get_coord()-this.get_y().get_coord())+
				(point.get_z().get_coord()-this.get_z().get_coord())*(point.get_z().get_coord()-this.get_z().get_coord());
		return distanceSquared;	
	}
	/**
	 * calculate distance, used on distanceSquared function
	 * @param point-type is Point3D
	 * @return double value-distance 
	 */
	public double distance(Point3D point)
	{
		return Math.sqrt(distanceSquared(point));
	}
	
	/*************** Admin *****************/
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Point3D oth = (Point3D) obj;
	    return _x.equals(oth._x) &&
	           _y.equals(oth._y) &&
	           _z.equals(oth._z);
	}
	@Override
	public String toString() 
	{
		return "(" + _x + "," + _y + "," + _z + "";
	}
	
}
