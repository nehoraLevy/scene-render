
package unittests;
import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
/**
 * Testing picture
 * @author Batya Tamsot and Nehora Levy
 */
public class project_final {

	@Test
	public void test() 
	{
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(500);
		scene.setBackground(new Color(0,0,51));
		scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(
				new Sphere(new Material(0.7, 0.9, 100, 0.5, 0),new Color(138,43,226),  50, new Point3D(0, 0, 50)),
				new Sphere(new Material(0.5, 0.9, 100,0,0.5),new Color(java.awt.Color.RED),  25, new Point3D(80, 25, 50)),
				new Sphere(new Material(0.5, 0.5, 100),new Color(128,0,128),  25, new Point3D(0, 25, 50)),
				new Sphere(new Material(0.5, 0.9, 100,0.99,0),new Color(255,69,0),  100, new Point3D(-150,-55, 50)),
				new Sphere(new Material(0.9, 0.3, 100, 0.8, 0),new Color(255,255,255),  1, new Point3D(70,-60, 30)),
				new Sphere(new Material(0.9, 0.3, 100, 0.8, 0),new Color(255,255,255),  2, new Point3D(180,-120, 30)),
				new Sphere(new Material(0.5, 0.5, 500, 0, 0),new Color(32,178,170),  40, new Point3D(150,5, 30)),/////
				new Sphere(new Material(0.9, 0.3, 100, 0.8, 0),new Color(255,255,255),  1, new Point3D(0,-140, 70)),
				new Sphere(new Material(0.9, 0.3, 100, 0.8, 0),new Color(255,255,255), 1, new Point3D(100,-140, 30)),
				new Plane(new Material(0.4, 0.9, 100, 0.9, 0.07), new Color(0,0,51), new Point3D(-30, 50, -10), new Vector(0, -1, 0)));

		scene.addLights(new SpotLight(new Color(255, 255, 255), new Point3D(100, -100, 30), new Vector(1, 1, -1), 1,0.0004, 0.0000006),
				new PointLight(new Color(255, 255, 255), new Point3D(-70, 70, -800), 1,
						0.0004, 0.0000006),
				new SpotLight(new Color(255, 255, 255),new Point3D(-200, -200, 30), new Vector(1, 1, -1), 1,0.0004, 0.0000006));

		ImageWriter imageWriter = new ImageWriter("finalProject", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene) //
		.setMultithreading(3) //
		.setDebugPrint();

		render.renderImage();
		// render.printGrid(50, java.awt.Color.YELLOW);
		render.writeToImage();

		/*ImageWriter imageWriter = new ImageWriter("finalProject", 150, 150, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage(9);
		render.writeToImage();*/
	}
	// Example for setting multithreading and debug print in test files:
	


}
