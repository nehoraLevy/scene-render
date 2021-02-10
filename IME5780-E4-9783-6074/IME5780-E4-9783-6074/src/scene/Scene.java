package scene;


import geometries.*;
import primitives.Color;
import elements.*;
import java.util.LinkedList;
import java.util.List;
/**
 * class Scene, include all the objects in the picture
 * @author batya lasry/Tamsot and nehora levy
 * 
 */

public class Scene
{
	/**
	 * the name of the scene
	 */
	private String _name;
	/**
	 * the color background 
	 */
	private Color _background;
	/**
	 * the ambient light in the scene
	 */
	private AmbientLight _ambientLight;
	/**
	 * all the geometries in the scene
	 */
	private Geometries _geometries;
	/**
	 * the camera
	 */
	private Camera _camera;
	/**
	 * distance from the camera
	 */
	private double _distance;
	/**
	 * list of lights
	 */
	private List<LightSource>  _lights;
	/**
	 * getter of the name
	 * @return name scene
	 */
	public String get_name()
	{
		return _name;
	}
	/**
	 * getter of _lights
	 * @return List<LightSource>
	 */
	public List<LightSource> get_lights()
	{
		return _lights;
	}
	/**
	 * getter of background
	 * @return the color background
	 */
	public Color get_background() 
	{
		return _background;
	}
	/**
	 * getter of ambientLight
	 * @return the ambient light
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}
	/**
	 * getter of geometries
	 * @return the geometries list in the scene
	 */
	public Geometries get_geometries()
	{
		return _geometries;
	}
	/**
	 * getter of camera
	 * @return the camera in the scene
	 */
	public Camera get_camera()
	{
		return _camera;
	}
	/**
	 * getter of distance
	 * @return the distance between the geometry from the scene
	 */
	public double get_distance()
	{
		return _distance;
	}
	/**
	 * parameter constructor
	 * @param str
	 */
	public Scene(String str )
	{
		_name=str;
		_geometries=new Geometries();
		_lights=new LinkedList<LightSource>();
	}
	/**
	 * background setter
	 * @param background
	 */
	public void setBackground(Color background)
	{
	  _background=background;
	}
	/**
	 * ambient light setter
	 * @param ambientLight
	 */
	public void setAmbientLight(AmbientLight ambientLight)
	{
		_ambientLight=ambientLight;
	}
	/**
	 * camera setter
	 * @param camera
	 */
	public void setCamera(Camera camera)
	{
	  _camera=camera;
	}
	/**
	 * distance setter
	 * @param distance
	 */
	public void setDistance(double distance)
	{
	   _distance=distance;
	}
	/**
	 * add geometries to the geometries list
	 * @param geometries
	 */
	public void addGeometries(Intersectable... geometries) 
	{
		for( Intersectable geometry : geometries)
		{
			_geometries.add(geometry);
		}
	}
	/**
	 * add list of lights to _lights
	 * @param lights
	 */
	public void addLights(LightSource... lights) 
	{
		for( LightSource light : lights)
		{
			_lights.add(light);
		}
	}
}
