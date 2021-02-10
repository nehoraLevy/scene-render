package geometries;
import java.util.List;

import primitives.*;
/**
 * interface Intersectable with inner class GeoPoint 
 * @author Batya Tamsot and Nehora Levy
 *
 */
public interface Intersectable 
{
	/**
	 * Finding cut points with the different geometries,
	 * findIntersections that all the geometries implements 
	 * @param ray
	 * @return List<GeoPoint> list of geoPoints
	 */
	public List<GeoPoint> findIntersections(Ray ray);
	/**
	 * static class , include point and its geometry
	 */
	public static class GeoPoint 
	{
		/**
		 * the geometry that the point is on it
		 */
		private Geometry geometry;
		/**
		 * the point
		 */
		private Point3D point;
		/**
		 * parameter constructor
		 * @param geo
		 * @param p
		 */
		public GeoPoint( Geometry geo, Point3D p)
		{
			geometry=geo;
			point=p;
		}
		/**
		 *  parameter constructor that get GeoPoint
		 * @param g-GeoPoint
		 */
		public GeoPoint(GeoPoint g) 
		{
			geometry=g.geometry;
			point=g.point;
		}
		/**
		 * getter of geomerty
		 * @return Geometry
		 */
		public Geometry getGeometry() 
		{
			return geometry;
		}
		/**
		 * getter of point 
		 * @return point3D
		 */
		public Point3D getPoint() 
		{
			return point;
		}
		@Override
		public boolean equals(Object obj) 
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}
		
	}


}
