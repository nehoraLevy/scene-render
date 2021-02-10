/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
/**
 * Testing Plane
 * @author Batya Tamsot and Nehora Levy
 *
 */

public class PlaneTest 
{

	/**
	 * Test method for {@link geometries.Plan#getNormal(primitives.Point3D)}.
	 *testGetNormal is test that test the plane get normal
	 */
	@Test
	public void testGetNormal()
	{
		Vector v1= new Vector(0,0,1);
		Plane pl=new Plane(new Point3D(1,-1,1), v1);
		Vector v2=pl.getNormal(new Point3D(1,-1,1));
		assertEquals(v1, v2);
	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 * testing find intersections of plane
	 * include two Equivalence Partitions Tests and seven Boundary Values Tests
	 */
	@Test
	public void testFindIntersections() 
	{
		Plane pl= new Plane(new Point3D(2,-1,0), new Vector(1,1,1));
		Plane pl1= new Plane(new Point3D(0,0,0),new Vector(1,0,0));
		// ============ Equivalence Partitions Tests ==============

		// TC01: Ray intersects the plane
		List<GeoPoint> result1 =pl.findIntersections(new Ray(new Point3D(2,-1,0), new Vector(1,1,1)));
		List<GeoPoint> correctResult1=new ArrayList<GeoPoint>();
		correctResult1.add(new GeoPoint(pl,new Point3D(2,-1,0)));
		assertEquals(1,result1.size());
		assertEquals(correctResult1.get(0),result1.get(0));

		// TC02: Ray does not intersect the plane
		List<GeoPoint> result2 =pl.findIntersections(new Ray(new Point3D(12,0,0), new Vector(1,1,1)));
		assertEquals(null,result2);

		// =============== Boundary Values Tests ==================
		// TC03:Ray is parallel to the plane and not included in the plane
		List<GeoPoint> result3 =pl1.findIntersections(new Ray(new Point3D(2,0,0), new Vector(2,1,0)));
		assertEquals(null,result3);

		// TC04:Ray is parallel to the plane and included in the plane
		List<GeoPoint> result4 =pl1.findIntersections(new Ray(new Point3D(0,0,1), new Vector(0,0,1)));
		assertEquals(null,result4);


		// TC05: Ray is orthogonal to the plane and P0 before the plane
		List<GeoPoint> result5 =pl1.findIntersections(new Ray(new Point3D(-10,0,0), new Vector(1,0,0)));
		List<GeoPoint> correctResult5=new ArrayList<GeoPoint>();
		correctResult5.add(new GeoPoint(pl1,new Point3D(0,0,0)));
		assertEquals(correctResult5.size(),result5.size());
		assertEquals(correctResult5.get(0),result5.get(0));

		// TC06: Ray is orthogonal to the plane and P0 in the plane
		List<GeoPoint> result6 =pl1.findIntersections(new Ray(new Point3D(0,0,0), new Vector(1,0,0)));
		List<GeoPoint> correctResult6=new ArrayList<GeoPoint>();
		correctResult6.add(new GeoPoint(pl1,new Point3D(0,0,0)));
		assertEquals(correctResult6.size(),result6.size());
		assertEquals(correctResult6.get(0),result6.get(0));

		// TC07: Ray is orthogonal to the plane and P0 after the plane
		List<GeoPoint> result7 =pl1.findIntersections(new Ray(new Point3D(10,0,0), new Vector(10,0,0)));
		assertEquals(null,result7);

		// TC08:Ray is not orthogonal or parallel to and begins at the plane (P0 is in the plane, but not the ray)
		List<GeoPoint> result8 =pl1.findIntersections(new Ray(new Point3D(0,10,5), new Vector(3,4,5)));
		assertEquals(null,result8);

		// TC09:Ray is not orthogonal or parallel to the plane and begins in the same point which appears as reference point in the plane
		List<GeoPoint> result9 =pl1.findIntersections(new Ray(new Point3D(0,0,0), new Vector(3,4,5)));
		List<GeoPoint> correctResult9=new ArrayList<GeoPoint>();
		correctResult9.add(new GeoPoint(pl1,new Point3D(0,0,0)));
		assertEquals(correctResult9.size(),result9.size());

	}





}
