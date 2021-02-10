package elements;

import primitives.*;
/**
 * class PointLight extends Light implements LightSource
 * @author batya Lasry and Nehora Levy
 *
 */
public class PointLight extends Light implements LightSource
{
	/**
	 * the position of the light
	 */
	protected Point3D _position;
	/**
	 *  the distance factors
	 */
	protected double _kC,_kL,_kQ;
	/**
	 * parameter constructor, send the intensity to the father
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ )
	{
		super(intensity);
		this._position=new Point3D(position);
		this._kC=kC;
		this._kL=kL;
		this._kQ=kQ;
	}
	/**
	 * get the distance between point to the position of PointLight
	 * @param point
	 * @return double distance
	 */
	public double getDistance(Point3D point)
	{
		return point.distance(_position);
	}
	@Override
	public Color getIntensity(Point3D p) 
	{
        double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        return (_intensity.scale(1/(_kC + _kL * d + _kQ * dsquared)));
	}
	@Override
	public Vector getL(Point3D p) 
	{
		if(p.equals(_position))
		{
			return null;
		}
		return p.subtract(_position).normalize();
	}

}
