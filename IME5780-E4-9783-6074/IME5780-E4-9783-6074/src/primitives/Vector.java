package primitives;

/**
 * Class Vector is the class representing a vector in Cartesian
 * The class is based on Point3D class
 * one Point 3D head
 * @author Batya Tamsot and Nehora Levy
*/
public class Vector 
{
	/** the start point of Vector is (0,0,0) 
	 * the final point of Vector is Point3D _head
	 */
	protected Point3D _head;
	//constructors
	/**
     * Vector constructor 3 Coordinate value
     *
     * @param x1 Coordinate value
     *  @param y1 Coordinate value
     *  @param z1 Coordinate value
     *  @throws IllegalArgumentException if the input is (0,0,0)
     */
	public Vector(Coordinate x1, Coordinate y1,Coordinate z1)
	{
		if(x1.equals(0)==true && y1.equals(0)==true && z1.equals(0)==true )
		{
			throw new IllegalArgumentException("The input is vector 0");
		}
		_head=new Point3D(x1, y1, z1);
	}
	/**
     * Vector constructor 3 double value for the head Point3D of the Vector
     *
     * @param x1 double value
     *  @param y1 double value
     *  @param z1 double value
     *  @throws IllegalArgumentException if the input is (0,0,0)
     */
	public Vector(double x1, double y1, double z1)
	{
		/*if( isZero(x1)  && isZero(y1) && isZero(z1))
		{
			throw new IllegalArgumentException("The input is vector 0");
		}*/
		_head=new Point3D(x1,y1,z1);
	}
	/**
     * Vector constructor Point3D value
     *
     * @param point Point3D value
     * @throws IllegalArgumentException if the input is (0,0,0)
     */
	public Vector(Point3D point)
	{
		if(point.equals(Point3D.ZERO))
		{
			throw new IllegalArgumentException("The input is vector 0");
		}
		_head=point;
	}
	/**
     * Copy constructor for Vector
     *
     * @param other
     */
	public Vector(Vector vec)
	{
		_head=vec._head;
	}
	//getter
	/**
     * Vector getter
     *
     * @return Point3D _head value head point of Vector
     */
	public Point3D get_head() 
	{
		return _head;
	}

	/**
	 * addition between two vectors
	 * @param vec- type is Vector
	 * @return Vector 
	 */
	public Vector add(Vector vec) 
	{
		return new Vector(this.get_head().add(vec));
	}
	/**
	 * sub
	 * @param vec-type is Vector
	 * @return Vector
	 */
	public Vector subtract(Vector vec)
	{
		return new Vector(this.get_head().subtract(vec.get_head()));
	}
	/**
	 * multiplication vector in double scalingFacor 
	 * @param scalingFacor-type is double
	 * @return Vector
	 */
	public Vector scale(double scalingFacor) //כפל בסקלר
	{
		return new Vector((this.get_head().get_x().get_coord()*scalingFacor),
				(this.get_head().get_y().get_coord()*scalingFacor),
				(this.get_head().get_z().get_coord()*scalingFacor));
				
	}
	/**
	 * dotProduct between two vectors u*v=u1*v1+u2*v2+u3*v3;
	 * @param vec- type Vector
	 * @return double value- the result of dotProduct 
	 */
	public double dotProduct(Vector vec) 
	{
		return (this.get_head().get_x().get_coord())*(vec.get_head().get_x().get_coord())+
				((this.get_head().get_y().get_coord())*(vec.get_head().get_y().get_coord()))+
				((this.get_head().get_z().get_coord())*(vec.get_head().get_z().get_coord()));
	}
	/**
	 * crossProduct between two vectors
	 * @param vec-type Vector
	 * @return Vector- the result of crossProduct
	 */
	public Vector crossProduct(Vector vec) 
	{
		double u1=this.get_head().get_x().get_coord();
		double u2=this.get_head().get_y().get_coord();
		double u3=this.get_head().get_z().get_coord();
		double v1=vec.get_head().get_x().get_coord();
		double v2=vec.get_head().get_y().get_coord();
		double v3=vec.get_head().get_z().get_coord();
		return new Vector(new Point3D((u2*v3)-(u3*v2),(u3*v1)-(u1*v3),(u1*v2)-(u2*v1)));
	}
	/**
	 * calculate the Squared length of vector
	 * @return double value-the result of calculation
	 */
	public double lengthSquared() //אורך בריבוע של וקטור
	{
		return (((this.get_head().get_x().get_coord())*(this.get_head().get_x().get_coord()))+
				((this.get_head().get_y().get_coord())*(this.get_head().get_y().get_coord()))+
				((this.get_head().get_z().get_coord())*(this.get_head().get_z().get_coord())));
	}
	/**
	 * calculate the length of vector, using in lengthSquared() function 
	 * @return double value- the length of vector
	 */
	public double length()
	{
		return Math.sqrt(this.lengthSquared());
	}
	
	/**
     * @return the same Vector after normalization of the Vector
     * @throws ArithmeticException if length = 0
     */
    public Vector normalize() 
    {
        double x = this.get_head().get_x().get_coord();
        double y = this.get_head().get_y().get_coord();
        double z = this.get_head().get_z().get_coord();
        double length=this.length();
        if (length== 0)
            throw new ArithmeticException("divide in zero");
        this._head._x = new Coordinate(x / length);
        this._head._y = new Coordinate(y / length);
        this._head._z = new Coordinate(z / length);
        return this;
    }
    /**
     * @return new Vector after normalization of the Vector
     */
	public Vector normalized() 
	{
		Vector vec=new Vector(this.normalize());
		return vec;
	}
	
	/*************** Admin *****************/
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector vec = (Vector) obj;
        return _head.equals(vec._head);
    }
	@Override
	public String toString() 
	{
		return "Vector:"+ _head.toString();
	} 

}

