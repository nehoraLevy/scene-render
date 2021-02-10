package geometries;
import primitives.*;
/**
 * abstract class Geometry based on the package primitives
 * @author Batya Tamsot and Nehora Levy
 *
 */
public abstract class Geometry implements Intersectable 
{
	/**
	 * the color of the geometry
	 */
	protected Color _emission;
	/**
	 * the Material of the geometry
	 */
	protected Material _material;
	
	/**
	 * getter of _emission
	 * @return Color 
	 */
	public Color get_emission() 
	{
		return _emission;
	}
	/**
	 * getter of _material
	 * @return Material
	 */
	public Material get_material() 
	{
		return _material;
	}
	/**
	 * parameter constructor
	 * Material(0,0,0)
	 * @param color
	 */
	public Geometry(Color color)
	{
		_emission= new Color(color);
		_material=new Material(0,0,0);
	}
	/**
	 * additional constructor- with parameter material
	 * @param color Color
	 * @param material Material
	 */
	public Geometry(Color color, Material material)
	{
		this(color);
		_material=material;
	}
	/**
	 * default constructor,
	 * Emission is Color.BLACK,
	 * material is Material(0,0,0)
	 */
	public Geometry()
	{
		_emission=Color.BLACK;
		_material=new Material(0,0,0);
	}
	/**
	 * calculate the normal to the point on the geometry
	 * @param point- type Point3D
	 * @return Vector-normal
	 */
	public abstract Vector getNormal(Point3D point);
}

