package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
/**
 * Testing Geometries
 * @author Batya Tamsot and Nehora Levy
 */
public class GeometriesTest 
{

	@Test
	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 * testing find intersections of Geometries
	 * include two Equivalence Partitions Tests and seven Boundary Values Tests
	 */
	public void testFindIntersections() 
	{
		Geometries geo=new Geometries();


		// =============== Boundary Values Tests ==================

		//TC01: collection of geometries empty
		List<GeoPoint> result1 =new ArrayList<GeoPoint>();
		result1=geo.findIntersections(new Ray(new Point3D(0,0,0),new Vector(1,1,1)));
		assertEquals(null,result1);


		//TC02:no intersection with any geometries
		geo._geometries.add(new Sphere(1d,new Point3D(1,0,0)));
		assertEquals(null,geo.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));


		//TC03: one geometry has an intersection
		//geo._geometries.add(new Sphere(1d,new Point3D(0,0,0)));
		assertEquals(1,geo.findIntersections(new Ray(new Point3D(0,0,0),new Vector(1,1,1))).size());



		//TC04: all the geometries has intersections
		geo.add(new Plane(new Point3D(0,0,0), new Vector(0,0,1))); //plane: z=0
		List<GeoPoint> result4=new ArrayList<GeoPoint>();
		result4=geo.findIntersections(new Ray(new Point3D(0,0,0),new Vector(1,1,1)));
		assertEquals(2,result4.size());


		// ============ Equivalence Partitions Tests ==============
		//TC00: some of the geometries has intersections but not all of them
		geo.add(new Sphere(1,new Point3D(-4,-4,-4)));
		List<GeoPoint> result0=new ArrayList<GeoPoint>();
		result0=geo.findIntersections(new Ray(new Point3D(0,0,0),new Vector(1,1,1)));
		assertEquals(2,result0.size());
	}

}
