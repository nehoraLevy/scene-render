/**
 * Testing Tube
 * @author Batya Tamsot and Nehora Levy
 */

package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import geometries.Tube;
import primitives.*;

/**
* Testing Tube
* @author Batya Tamsot and Nehora Levy
*/
public class TubeTest 
{

	@Test
	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	public void testGetNormal()
	{
		//Test 1
		Tube tb=new Tube(1.1,new Ray(new Point3D(0,0,0),new Vector(1,0,0)));
		Vector normalTb=new Vector(0,0,1);
		assertEquals(normalTb, tb.getNormal(new Point3D(0,0,1)));
	}
}







