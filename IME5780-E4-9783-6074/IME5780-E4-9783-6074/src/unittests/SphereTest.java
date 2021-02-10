package unittests;




import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import primitives.*;
/**
* Testing Sphere
* @author Batya Tamsot and Nehora Levy
*/
public class SphereTest {

	@Test
	/**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
	public void testGetNormal() 
	{
		//Test 1
		Sphere sp= new Sphere(1, new Point3D(0,0,0));
		Vector normalSp=new Vector(0,0,1);
		assertEquals(normalSp, sp.getNormal(new Point3D(0,0,1)));
		//Test 2
		Sphere sp1= new Sphere(1.5, new Point3D(1.5,0,2.5));
		Vector normalSp1=new Vector(1,0,0);
		assertEquals(normalSp1, sp1.getNormal(new Point3D(3,0,2.5)));
		
	}
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections()
    {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                        sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));//true

       // TC02: Ray starts before and crosses the sphere (2 points)
        //Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        //Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<GeoPoint> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                                                                new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        /*List<GeoPoint>list1=new ArrayList<GeoPoint>();
        if (result.get(0).getPoint().get_x()._coord >result.get(1).getPoint().get_x()._coord)
        	list1.add(new GeoPoint(sphere,p1));
            list1.add(new GeoPoint(sphere,p2));*/ 
            //result = List.of(result.get(1), result.get(0));
        //assertEquals("Ray crosses sphere", /*List.of(new GeoPoint(sphere,p1),new GeoPoint(sphere,p2))*/list1, result);//false 

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals("Ray does'nt starts inside the sphere", 1,
                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 1, 0))).size());///true

        // TC04: Ray starts after the sphere (0 points)
        assertEquals("Ray's start in the sphere", null,
                sphere.findIntersections(new Ray(new Point3D(0, 0, 5), new Vector(1, 0, 10))));


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals("Ray's goes outside", 1,
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, -1, 0))).size());//true

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray's goes inside", null,
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(5, 1, 1))));

     // TC13: Ray starts before the sphere (2 points)
        assertEquals("Ray's dont start before the sphere", 2,
                sphere.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(1, 0, 0))).size());//true

        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals("Ray doesnt starts at sphere and goes inside", 1,
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 0))).size());//true

        // TC15: Ray starts inside (1 points)
        assertEquals("Ray doesnt starts inside", 1,
                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(3, 0, 0))).size());

        // TC16: Ray starts at the center (1 points)
        assertEquals("Ray doesnt starts at the center", 1,
                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(3, 0, 0))).size());

        // **** Group: Ray's line goes through the center
     // TC13: Ray starts before the sphere (2 points)
        assertEquals("Ray doesnt starts before the sphere", 2,
                sphere.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(1, 0, 0))).size());

        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals("Ray doesnt starts at sphere and goes inside", 1,
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 0))).size());

        // TC15: Ray starts inside (1 points)
        assertEquals("Ray doesnt starts inside", 1,
                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-1, 0, 0))).size());

        // TC16: Ray starts at the center (1 points)
        assertEquals("Ray doesnt starts at the center", 1,
                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-1, 0, 0))).size());
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray doesnt starts at sphere and goes outside", null,
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(5, 0, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertEquals("Ray doesnt starts after sphere", null,
                sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(5, 0, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertEquals("Ray doesnt starts before the tangent point", null,
                sphere.findIntersections(new Ray(new Point3D(2, 0, -2), new Vector(2, 0, 2))));

        // TC20: Ray starts at the tangent point
        assertEquals("Ray doesnt starts at the tangent point", null,
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(2, 0, 2))));

        // TC21: Ray starts after the tangent point
        assertEquals("Ray doesnt starts after the tangent point", null,
                sphere.findIntersections(new Ray(new Point3D(2, 0, 1), new Vector(2, 0, 2))));


        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line*/
        assertEquals("Ray doesnt starts after the tangent point", null,
                sphere.findIntersections(new Ray(new Point3D(1, 0, 2), new Vector(2, 0, 2))));


    }


}
