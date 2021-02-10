package renderer;
import java.util.ArrayList;
import java.util.List;
import elements.*;
import primitives.*;
import static primitives.Util.*;
import scene.*;
import geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;
/**
 * class render with inner class Pixel, with threads and adaptive super sampling
 * @author batya lasry/Tamsot and nehora levy
 * 
 */
public class Render
{

	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;

	/**
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}

		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++_counter;
			if (col < _maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (_print && _counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) {
				col = 0;
				target.row = this.row;
				target.col = this.col;
				if (_print && _counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}
	/**
	 * the image writer
	 */
	private ImageWriter _imageWriter;
	/**
	 * the scene
	 */
	private Scene _scene;
	//private static final double DELTA = 0.1;
	/**
	 * the max depth of level in function calcColor
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/**
	 * the min depth of level in function calcColor
	 */
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * Render constructor
	 * @param _scene
	 */
	public Render(Scene _scene)
	{

		this._scene = _scene;

	}
	/**
	 * Render constructor
	 * @param imageWriter
	 * @param scene
	 */
	public Render(ImageWriter imageWriter, Scene scene) 
	{

		this._imageWriter = imageWriter;

		this._scene = scene;

	}
	/**
	 * scene getters
	 * @return the scene
	 */
	public Scene get_scene() 
	{

		return _scene;

	}

	/**
	 * write the image, with threads and adaptive super sampling
	 */
	public void renderImage( int numPixel)
	{
		final int nX = _imageWriter.getNx();
		final int nY = _imageWriter.getNy();
		// Generate threads
		Thread[] threads = new Thread[_threads];
		final Pixel thePixel = new Pixel(nY, nX);
		for (int i = _threads - 1; i >= 0; --i) {
			threads[i] = new Thread(() -> {
				Pixel pixel = new Pixel();
				while (thePixel.nextPixel(pixel)) 
				{
					final List<Color> colors=new ArrayList<Color>();
				    calcColorInPixel(pixel, colors, nX, nY,0);
				}
			}
					);
		}
		// Start threads
		for (Thread thread : threads) thread.start();

		// Wait for all threads to finish
		for (Thread thread : threads) 
			try 
		{ 
				thread.join(); 
		} 
		catch (Exception e) 
		{}
		if (_print)
			System.out.printf("\r100%%\n");
	}

	/**
	 * outer function of renderImage
	 */
	public void renderImage()
	{
		renderImage(1);
	}
	/**
	 * Recursive function
	 * adaptive Super sampling
	 * @param pixel
	 * @param rays
	 * @param colors
	 */
	public void calcColorInPixel(Pixel pixel, List<Color> colors,  int nX, int nY, int depth)
	{
		if(depth==4)
		{
			_imageWriter.writePixel(pixel.col, pixel.row, new Color(colors).getColor());
			return;
		}
		if(depth!=0&&colors.get(0).equal(colors.get(1))&&colors.get(2).equal(colors.get(3))&&colors.get(2).equal(colors.get(3)))//if the 4 "pixel" color are equal
		{// stop condition
			_imageWriter.writePixel(pixel.col, pixel.row, new Color(colors).getColor());
			return;
		}
		depth++;
		List<Ray> rays=new ArrayList<Ray>();
		final double distance=_scene.get_distance();
		final int width= (int) _imageWriter.getWidth();
		final int height= (int) _imageWriter.getHeight();
		rays.addAll(_scene.get_camera().constructListRayThroughPixel(nX*2, nY*2, pixel.col+1*pixel.col, pixel.row+1*pixel.row, distance, 
				width,height));
		rays.addAll(_scene.get_camera().constructListRayThroughPixel(nX*2, nY*2, pixel.col+2*pixel.col, pixel.row+1*pixel.row, distance, 
				width,height));
		rays.addAll(_scene.get_camera().constructListRayThroughPixel(nX*2, nY*2, pixel.col+1*pixel.col, pixel.row+2*pixel.row, distance, 
				width,height));
		rays.addAll(_scene.get_camera().constructListRayThroughPixel(nX*2, nY*2, pixel.col+2*pixel.col, pixel.row+2*pixel.row,distance, 
				width,height));
		colors.addAll(IntersectionsInPixel(rays,colors));
		calcColorInPixel(pixel, colors.subList(0, 4),nX*2, nY*2,depth);
		calcColorInPixel(pixel, colors.subList(0, 4),nX*2, nY*2,depth);
		calcColorInPixel(pixel, colors.subList(0, 4),nX*2, nY*2,depth);
		calcColorInPixel(pixel,colors.subList(0, 4),nX*2, nY*2,depth);
		return;
	}
	/**
	 * calculate the colors in the pixel by the intersections with the geometries
	 * @param rays
	 * @param colors
	 * @return list of colors
	 */
	public List<Color> IntersectionsInPixel(List<Ray>rays, List<Color> colors)
	{
		for(int k=0;k<(2*2);k++)
		{
			List<GeoPoint> intersectionPoints= _scene.get_geometries().findIntersections(rays.get(k));
			if (intersectionPoints==null)
			{
				colors.add(new Color(_scene.get_background().scale(1d/4)));
			}

			else
			{
				final GeoPoint closestPoint=findClosestIntersection(rays.get(k));
				if(closestPoint == null)
				{
					colors.add(new Color(_scene.get_background().scale(1d/4)));

				}
				else
				{
					colors.add(new Color(calcColor(closestPoint, rays.get(k)).scale(1d/4)));
				}
			}	
		}
		return colors;
	}

	/**
	 * calculate Color with Diffuse ,specular , and shadows,
	 * reflection  and refraction
	 * @param geopoint
	 * @param inRay
	 * @param level
	 * @param k
	 * @return Color
	 */
	private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) 	{
		if (level == 1)
			return Color.BLACK;

		List<LightSource> lightSources = _scene.get_lights();
		Color color=geoPoint.getGeometry().get_emission();
		Vector v = geoPoint.getPoint().subtract(_scene.get_camera().get_p0()).normalize();
		Vector n = geoPoint.getGeometry().getNormal(geoPoint.getPoint());

		Material material = geoPoint.getGeometry().get_material();
		int nShininess = material.get_nShininess();
		double kd = material.get_kD();
		double ks = material.get_kS();

		if (lightSources != null) 
		{
			for (LightSource lightSource : lightSources) {
				Vector l = lightSource.getL(geoPoint.getPoint());
				double nl = alignZero(n.dotProduct(l));
				//double nv = alignZero(n.dotProduct(v));
				if (n.dotProduct(l) * n.dotProduct(v) > 0)
				{
					double ktr = transparency(lightSource, l, n, geoPoint); 
					if (ktr * k > MIN_CALC_COLOR_K)
					{ 
						Color lightIntensity = lightSource.getIntensity(geoPoint.getPoint()).scale(ktr);
						color = color.add(
								calcDiffusive(kd, nl, lightIntensity),
								calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
					}
				}
			}
		}

		if (level == 1)
			return Color.BLACK;
		double kr = geoPoint.getGeometry().get_material().get_kR(), kkr = k * kr; 
		if (kkr > MIN_CALC_COLOR_K)
		{
			Ray reflectedRay = constructReflectedRay(n, geoPoint.getPoint(), inRay);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
		}
		double kt = geoPoint.getGeometry().get_material().get_kT(), kkt = k * kt; 
		if (kkt > MIN_CALC_COLOR_K)
		{ 
			Ray refractedRay = constructRefractedRay(geoPoint.getPoint(), inRay, n);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null) 
				color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt)); 
		}
		return color;	
	}

	/**
	 * calculate color in the point (External function)
	 * @param gp
	 * @param ray
	 * @return
	 */
	private Color calcColor(GeoPoint gp, Ray ray)
	{
		return calcColor(findClosestIntersection(ray), ray, MAX_CALC_COLOR_LEVEL, 1.0).
				add( _scene.get_ambientLight().getIntensity());

	}

	/**
	 * calculate specular light
	 * @param ks
	 * @param l
	 * @param n
	 * @param nl
	 * @param v
	 * @param nShininess
	 * @param ip
	 * @return Color
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip)
	{
		double p = nShininess;

		Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
		double minusVR = -alignZero(R.dotProduct(v));
		if (minusVR <= 0) {
			return Color.BLACK; // view from direction opposite to r vector
		}
		// [rs,gs,bs](-V.R)^p
		return ip.scale(ks * Math.pow(minusVR, p));
	}
	/**
	 * calculate Diffuse light
	 * @param kd
	 * @param nl
	 * @param ip
	 * @return Color
	 */
	private Color calcDiffusive(double kd, double nl, Color ip)
	{
		if (nl < 0)
		{
			nl = -nl;
		}
		return ip.scale(nl * kd);
	}
	/**
	 * 
	 * @param val
	 * @return
	 */
	/*private boolean sign(double val) 
	{
		return (val > 0d);
	}*/
	/**
	 * getClosestPoint from the scene
	 * @param GeoPoint list
	 * @return GeoPoint
	 */
	/*private GeoPoint getClosestPoint(List<GeoPoint> geoPoints)
	{
		double distance=Double.MAX_VALUE;
		GeoPoint closestPoint= null;
		Point3D p0 = this._scene.get_camera().get_p0();
		for(GeoPoint point : geoPoints)
		{    
			double _distance=p0.distance(point.getPoint());
			if(_distance<distance)
			{
				distance=_distance;
				closestPoint=point;
			}
		}
		return closestPoint;

	}*/
	/**
	 * print the grid
	 * @param interval
	 * @param color
	 */
	public void printGrid(int interval, java.awt.Color color)
	{

		int Nx = this._imageWriter.getNx();

		int Ny = this._imageWriter.getNy();

		for (int i = 0; i < Ny; i++) 
		{

			for (int j = 0; j < Nx; j++) 
			{

				if (i % interval == 0 || j % interval == 0) 
				{
					_imageWriter.writePixel(j, i, color);
				}
			}

		}
	}
	/**
	 * write to the image
	 */
	public void writeToImage() 
	{

		_imageWriter.writeToImage();

	}
	/**
	 * return if the point is shaded or not
	 * @param light
	 * @param l
	 * @param n
	 * @param geopoint
	 * @return boolean
	 */
	/*private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint)
	{
		Vector lightDirection = l.scale(-1); // from point to light source
		Point3D point = geopoint.getPoint();
		Ray lightRay = new Ray(point, lightDirection, n); 
		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
		if (intersections== null)
			return true; 
		double lightDistance = light.getDistance(geopoint.getPoint());
		for (GeoPoint gp : intersections)
		{
			double num=gp.getPoint().distance(geopoint.getPoint())-lightDistance;
			if (alignZero(num) <= 0 && gp.getGeometry().get_material().get_kT() == 0)
				return false;
		}
		return true;

	}*/
	/**
	 * the updated function of unshaded
	 * @param light
	 * @param l
	 * @param n
	 * @param geopoint
	 * @return double
	 */
	private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint)
	{
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
		List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
		if (intersections ==null )
			return 1.0;
		double lightDistance = light.getDistance(geopoint.getPoint());
		double ktr = 1.0; 
		for (GeoPoint gp : intersections)
		{ 
			if (alignZero(gp.getPoint().distance(geopoint.getPoint()) - lightDistance) <= 0)
			{ 
				ktr *= gp.getGeometry().get_material().get_kT(); 
				if (ktr < MIN_CALC_COLOR_K)
					return 0.0;
			}
		}
		return ktr;

	}
	/**
	 * find closest intersection of the head of the ray 
	 * @param ray
	 * @return
	 */
	private GeoPoint findClosestIntersection(Ray ray)
	{
		if (ray == null)
		{
			return null;
		}
		GeoPoint closestPoint = null;
		double closestDistance = Double.MAX_VALUE;
		List<GeoPoint> intersections=_scene.get_geometries().findIntersections(ray);
		if(intersections== null)
		{
			return null;
		}
		for(GeoPoint gp : intersections)
		{
			double dis=ray.get_p0().distance(gp.getPoint());
			if(dis< closestDistance)
			{
				closestDistance=dis;
				closestPoint=gp;
			}
		}
		return closestPoint;
	}

	/**
	 * construct Reflected Ray
	 * @param n
	 * @param point
	 * @param inRay
	 * @return Ray
	 */
	public Ray constructReflectedRay(Vector n, Point3D point, Ray inRay)
	{
		if(inRay.get_dir().dotProduct(n)==0)
		{
			return null;
		}

		Vector r = inRay.get_dir().subtract(n.scale(2 * inRay.get_dir().dotProduct(n)));
		return new Ray(point, r, n);
	}


	/**
	 * construct Refracted Ray
	 * @param point
	 * @param inRay
	 * @return Ray
	 */
	public Ray constructRefractedRay(Point3D point, Ray inRay, Vector n)
	{
		return new Ray(point, inRay.get_dir(),n);
	}

	// ...........

	/**
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads)
	{
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}

	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() 
	{
		_print = true;
		return this;
	}
}



