/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Triangle;
import primitives.*;

/**
 * @author Batya Tamsot and Nehora levy
 */
public class TriangleTest {

	@Test
	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
	 */
	public void testGetNormal()
	{
		//Test1
		Triangle tr=new Triangle(new Point3D(0.0,0.0,0.0),
				new Point3D(0.0,0.0,1.0),
				new Point3D(0.0,1.0,0.0));
		assertEquals(tr.getNormal(new Point3D(0.0,1.0,0.0)), new Vector(new Point3D(-1.0,0.0,0.0)));
	}
	@Test
	/**
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	public void testFindIntersections() 
	{
		Triangle tr=new Triangle(new Point3D(0,0,0), new Point3D(0,0,1), new Point3D(0,1,0));
		// ============ Equivalence Partitions Tests ==============

		// TC01: the intersection inside the triangle
		List<GeoPoint> result1=tr.findIntersections(new Ray(new Point3D(-4,0.5,0.25), new Vector(1,0,0)));
		assertEquals(1, result1.size());


		// TC02: the intersection outside against edge
		List<GeoPoint> result2=tr.findIntersections(new Ray(new Point3D(0,0.5,1.5), new Vector(1,1,1)));
		assertEquals(null, result2);

		// TC03: the intersection outside against vertex
		List<GeoPoint> result3=tr.findIntersections(new Ray(new Point3D(0,-0.5,2), new Vector(1,1,1)));
		assertEquals(null, result3);

		// =============== Boundary Values Tests ==================
		//BVA: Three cases (the ray begins "before" the plane)

		// TC04: the intersection on edge
		List<GeoPoint> result4=tr.findIntersections(new Ray(new Point3D(-1,0,0.5), new Vector(1,0,0)));
		assertEquals(null, result4);

		// TC05: the intersection on vertex
		List<GeoPoint> result5=tr.findIntersections(new Ray(new Point3D(-1,0,1), new Vector(1,0,0)));
		assertEquals(null, result5);


		// TC06: the intersection on edge's continuation
		List<GeoPoint> result6=tr.findIntersections(new Ray(new Point3D(-1,0,4), new Vector(1,0,0)));
		assertEquals(null, result6);


	}
}
