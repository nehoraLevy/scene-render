package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
/**
 * class Geometries implements Intersectable
 * @author Batya Tamsot and Nehora Levy
 *
 */
public class Geometries implements Intersectable
{
	/**
	 * _geometries-the field of list of geometries from kind of Intesectable
	 */
	public List<Intersectable>  _geometries;
	/**
	 * default constructor
	 */
	public Geometries()
	{
		_geometries=new ArrayList<Intersectable>();

	}
	/**
	 * parameter constructor
	 * @param geometries1-ArrayList<Intersectable>
	 */
	public Geometries(ArrayList<Intersectable>geometries1)
	{
		_geometries=new ArrayList<Intersectable>(geometries1.size());
		for(int i=0; i< geometries1.size(); i++)
		{
			_geometries.add(geometries1.get(i));
		}

	}
	/**
	 * add geometry to the list-_geometries
	 * @param geometry
	 */
	public void add(Intersectable geometry)
	{
		_geometries.add(geometry);
	}
	/**
	 * @param Ray
	 * @return List<Point3D> list of intersections of ray with all the geometries in _geometries
	 */
	public List<GeoPoint> findIntersections(Ray ray)
	{
		List<GeoPoint> intersections = null;

		for (Intersectable geo : _geometries) 
		{
			List<GeoPoint> tempIntersections = geo.findIntersections(ray);
			if (tempIntersections != null) {
				if (intersections == null)
					intersections = new ArrayList<>();
				intersections.addAll(tempIntersections);
			}
		}
		return intersections;

	}

}
