package elements;

import primitives.*;

import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;
/**
 * class Camera
 * @author batya Lasry and Nehora Levy
 *
 */
public class Camera 
{
	/**
	 * the point of the camera
	 */
	private Point3D _p0;
	/**
	 * The Y axis
	 */
	private Vector _vUp;
	/**
	 * The Z axis
	 */
	private Vector _vTo;
	/**
	 * The X axis
	 */
	private Vector _vRight;
	/**
	 * parameter constructor, (do normalize to all the vectors)
	 * @param p0
	 * @param vUp
	 * @param vTo
	 */
	public Camera(Point3D p0,Vector vTo,Vector vUp)
	{
		this._p0=new Point3D(p0);
		this._vUp=vUp.normalized();
		this._vTo=vTo.normalized();
		this._vRight=(vTo.crossProduct(vUp)).normalize();
	}
	/**
	 * getter of p0
	 * @return Point3D-_p0
	 */
	public Point3D get_p0() {
		return _p0;
	}
	/**
	 * getter of vector vUp
	 * @return Vector-vUp
	 */
	public Vector get_vUp() {
		return _vUp;
	}
	/**
	 * getter of vector vTo
	 * @return Vector-vTo
	 */
	public Vector get_vTo() {
		return _vTo;
	}
	/**
	 * getter of vector vRight
	 * @return Vector-vRight
	 */
	public Vector get_vRight() {
		return _vRight;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camera other = (Camera) obj;
		if (_p0 == null) {
			if (other._p0 != null)
				return false;
		} else if (!_p0.equals(other._p0))
			return false;
		if (_vRight == null) {
			if (other._vRight != null)
				return false;
		} else if (!_vRight.equals(other._vRight))
			return false;
		if (_vTo == null) {
			if (other._vTo != null)
				return false;
		} else if (!_vTo.equals(other._vTo))
			return false;
		if (_vUp == null) {
			if (other._vUp != null)
				return false;
		} else if (!_vUp.equals(other._vUp))
			return false;
		return true;
	}
	/**
	 * constructor a ray through the center of one pixel in the view plane
	  * @param nX- count of pixels in width
	 * @param nY- count of pixels in height
	 * @param j-column
	 * @param i- row
	 * @param screenDistance -the distance from the camera
	 * @param screenWidth -the width of view plane
	 * @param screenHeight -the Height of view plane
	 * @throws IllegalArgumentException- when the distance is zero
	 * @return Ray
	 */
	public Ray constructRayThroughPixel(int nX, int nY,int j, int i, double screenDistance, double screenWidth, double screenHeight)
	{
		if (isZero(screenDistance)) {
			throw new IllegalArgumentException("distance cannot be 0");
		}
		Point3D pC=_p0.add(_vTo.scale(screenDistance));

		double rY= screenHeight/nY;
		double rX= screenWidth/nX;

		double xJ=(j-nX/2d)*rX+rX/2d;
		double yI=(i-nY/2d)*rY+rY/2d;

		Point3D pIJ= pC;

		if (!isZero(xJ)) {
			pIJ = pIJ.add(_vRight.scale(xJ));
		}
		if (!isZero(yI)) {
			pIJ = pIJ.add(_vUp.scale(-yI)); 
		}

		Vector vIJ=pIJ.subtract(_p0);
		return new Ray(_p0, vIJ);
	}

	/**
	 * construct List of 4 Rays Through Pixel from 4 edges
	 * return list of 4 rays through edges of the pixel 
	 * @param nX- count of pixels in width
	 * @param nY- count of pixels in height
	 * @param j-column
	 * @param i- row
	 * @param screenDistance -the distance from the camera
	 * @param screenWidth -the width of view plane
	 * @param screenHeight -the Height of view plane
	 * @throws IllegalArgumentException- when the distance is zero
	 * @return List<Ray>
	 */
	public List<Ray> constructListRayThroughPixel(int nX, int nY,int j, int i, double screenDistance, double screenWidth, double screenHeight)
	{

		if (isZero(screenDistance)) {
			throw new IllegalArgumentException("distance cannot be 0");
		}
		Point3D pC=_p0.add(_vTo.scale(screenDistance));
		List<Ray> list=new ArrayList<Ray>();

		double rY= screenHeight/nY;
		double rX= screenWidth/nX;
		//left up
		double xJ1=(j-nX/2d)*rX;
		double yI1=(i-nY/2d)*rY;
		//right up
		double xJ2=(j-nX/2d)*rX+rX;
		double yI2=(i-nY/2d)*rY;
		//left down
		double xJ3=(j-nX/2d)*rX;
		double yI3=(i-nY/2d)*rY+rY;
		//right down
		double xJ4=(j-nX/2d)*rX+rX;
		double yI4=(i-nY/2d)*rY+rY;
		
		Vector v1=constructVectorThroughPoint(xJ1, yI1,pC);
		Vector v2=constructVectorThroughPoint(xJ2, yI2,pC);
		Vector v3=constructVectorThroughPoint(xJ3, yI3,pC);
		Vector v4=constructVectorThroughPoint(xJ4, yI4,pC);
		list.add(new Ray(_p0, v1));
		list.add(new Ray(_p0, v2));
		list.add(new Ray(_p0, v3));
		list.add(new Ray(_p0, v4));

		/*super sampling
		for (int k=0;k<pixel;k++)
		{
			for(int w=0;w<pixel;w++)
			{
				double rY= screenHeight/nY1;
				double rX= screenWidth/nX1;

	            double xJ=(j*pixel+k-nX1/2d)*rX+rX/2d;
    		    double yI=(i*pixel+w-nY1/2d)*rY+rY/2d;

				Point3D pIJ= pC;

				if (!isZero(xJ)) {
					pIJ = pIJ.add(_vRight.scale(xJ));
				}
				if (!isZero(yI)) {
					pIJ = pIJ.add(_vUp.scale(-yI)); 
				}

				Vector vIJ=pIJ.subtract(_p0);
				list.add(new Ray(_p0, vIJ));
			}

		}*/
		return list;
	}
	
	
    /**
     * construct Vector Through Point with the pC- the point on the view plane
     * @param xJ
     * @param yI
     * @param pC
     * @return Vector
     */
	public Vector constructVectorThroughPoint(double xJ, double yI, Point3D pC)
	{
		Point3D pIJ= pC;

		if (!isZero(xJ)) {
			pIJ = pIJ.add(_vRight.scale(xJ));
		}
		if (!isZero(yI)) {
			pIJ = pIJ.add(_vUp.scale(-yI)); 
		}

		Vector vIJ=pIJ.subtract(_p0);
		return vIJ;
	}
}
