package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
/**
 * @author Batya Tamsot and Nehora Levy
 *
 */
public class IntegrationTest {

	/**
	 * 5 cases 
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int , int ,int , int , double , double , double)}.
	 *Test method for {@link geometries.Sphere#findIntersection(primitives.Ray)}.
	 */
	@Test
	public void IntegrationSphereTest() 
	{
		// TC01: 2 points
		Sphere sp= new Sphere(1, new Point3D(0,0,3));
		Camera ca=new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections;
		int counter=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections=sp.findIntersections(ca.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections!=null)
				{
					counter+=intersections.size();
				}
			}
		assertEquals(2,counter);


		// TC02: 18 points
		Sphere sp2= new Sphere(2.5, new Point3D(0,0,2.5));
		Camera ca2=new Camera(new Point3D(0,0,-0.5),new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections2;
		int counter2=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections2=sp2.findIntersections(ca2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections2!=null)
				{
					counter2+=intersections2.size();
				}
			}
		assertEquals(18,counter2);


		// TC03: 10 points
		Sphere sp3= new Sphere(2, new Point3D(0,0,2));
		Camera ca3=new Camera(new Point3D(0,0,-0.5),new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections3;
		int counter3=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections3=sp3.findIntersections(ca3.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections3!=null)
				{
					counter3+=intersections3.size();
				}
			}
		assertEquals(10,counter3);


		// TC04: 9 points
		Sphere sp4= new Sphere(4, new Point3D(0,0,2));
		Camera ca4=new Camera(new Point3D(0,0,1),new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections4;
		int counter4=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections4=sp4.findIntersections(ca4.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections4!=null)
				{
					counter4+=intersections4.size();
				}
			}
		assertEquals(9,counter4);

		// TC05: 0 points
		Sphere sp5= new Sphere(0.5, new Point3D(0,0,-1));
		Camera ca5=new Camera(new Point3D(0,0,0.5),new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections5;
		int counter5=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections5=sp5.findIntersections(ca5.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections5!=null)
				{
					counter5+=intersections5.size();
				}
			}
		assertEquals(0,counter5);

	}
	/**
	 * 3 cases
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int , int ,int , int , double , double , double)}.
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void IntegrationPlaneTest() 
	{
		// TC01: 9 points
		Plane pl1= new Plane( Point3D.ZERO,new Vector(0,0,-1));
		Camera ca1=new Camera(new Point3D(0,0,-2),new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections1;
		int counter1=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections1=pl1.findIntersections(ca1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections1!=null)
				{
					counter1+=intersections1.size();
				}
			}
		assertEquals(9,counter1);

		// TC02: 9 points
		Plane pl2 = new Plane(new Point3D(0, 0, 5), new Vector(0, -1, 2));
		Camera ca2=new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections2;
		int counter2=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections2=pl2.findIntersections(ca2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections2!=null)
				{
					counter2+=intersections2.size();
				}
			}
		assertEquals(9,counter2);

		// TC03: 6 points
		Plane pl3= new Plane(new Point3D(0, 0, 5), new Vector(0, -2, 2));
		Camera ca3=new Camera(new Point3D(0,0,3),new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections3;
		int counter3=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections3=pl3.findIntersections(ca3.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections3!=null)
				{
					counter3+=intersections3.size();
				}
			}
		assertEquals(6,counter3);
	}
	/**
	 * 2 cases
	 * Test method for {@link elements.Camera#constructRayThroughPixel(int , int ,int , int , double , double , double)}.
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void IntegrationTriangleTest() 
	{
		// TC01:1 point
		Triangle tr1= new Triangle( new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
		Camera ca1=new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections1;
		int counter1=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections1=tr1.findIntersections(ca1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections1!=null)
				{
					counter1+=intersections1.size();
				}
			}
		assertEquals(1,counter1);

		// TC02:2 point
		Triangle tr2= new Triangle( new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
		Camera ca2=new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(0,-1,0));
		List<GeoPoint>intersections2;
		int counter2=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				intersections2=tr2.findIntersections(ca2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersections2!=null)
				{
					counter2+=intersections2.size();
				}
			}
		assertEquals(2,counter2);



	}

}
