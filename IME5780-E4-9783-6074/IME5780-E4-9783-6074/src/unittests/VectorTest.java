/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import primitives.*;
import primitives.Vector;
import static  primitives.Util.isZero;
/**
 * Testing Vector
 * @author Batya Tamsot and Nehora Levy
 */
public class VectorTest 
{
	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() 
	{
		Vector v1 = new Vector(1, 1, 1);
		Vector v2 = new Vector(-1, -1, -1.5);

		v1 = v1.add(v2);
		assertEquals(new Vector(0,0,-0.5),v1);

		v2 = v2.add(v1);
		assertEquals(new Vector(-1, -1, -2),v2);

	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() 
	{
		Vector v1=new Vector(1,1,1);
		Vector v2= new Vector(new Point3D(20, -1, 0.5));
		v1=v1.subtract(v2);
		assertEquals(v1, new Vector(-19, 2, 0.5));
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() 
	{
		Vector v=new Vector(0, -2, 0.25);
		v=v.scale(-2);
		assertEquals(v, new Vector(0, 4, -0.5));
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() 
	{
		Vector v1=new Vector(1,1,1);
		Vector v2= new Vector(new Point3D(20, -1, 0.5));
		double d=v1.dotProduct(v2);
		assertEquals(d, 19.5, 0.0);
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() 
	{

		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);

		// ============ Equivalence Partitions Tests ==============
		Vector v3 = new Vector(0, 3, -2);
		Vector vr = v1.crossProduct(v3);

		// Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
		assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

		// Test cross-product result orthogonality to its operands
		assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero((vr.dotProduct(v1))));
		assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero((vr.dotProduct(v3))));

		// =============== Boundary Values Tests ==================
		// test zero vector from cross-product of co-lined vectors
		try 
		{
			v1.crossProduct(v2);
			fail("crossProduct() for parallel vectors does not throw an exception");
		} 
		catch (Exception e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() 
	{
		Vector v=new Vector(0,2, -4);
		double l=v.lengthSquared();
		assertEquals(l, 20,0.0);
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() 
	{
		Vector v=new Vector(0,3, -4);
		double l=v.length();
		assertEquals(l,5,0.0);
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() 
	{
		Vector v = new Vector( 3.5, -5,10);
		v.normalize();
		assertEquals("", 1 , v.length() ,  1e-10);
		try
		{
			v= new Vector(0,0,0);
			v.normalize();
			fail("Didn't throw divide by zero");
		}
		catch( ArithmeticException e )
		{
			assertTrue(true);
		}
	}
	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() 
	{
		Vector v = new Vector( 3.5, 5,10);
		v.normalize();
		assertEquals("", 1 , v.length() ,  1e-10);
		try
		{
			v= new Vector(0,0,0);
			v.normalize();
			fail("Didn't throw divide by zero");
		}
		catch( ArithmeticException e )
		{
			assertTrue(true);
		}
	}

}
