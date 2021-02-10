package unittests;

import org.junit.Test;

import elements.*;
import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
/**
 * Testing picture include all the parameters
 * @author Batya Tamsot and Nehora Levy
 */
public class final2 
{
	@Test
    public void test1() 
    {
      Scene scene = new Scene("Test scene");
      scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
      scene.setDistance(500);
      scene.setBackground(new Color(0,0,51));
      scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

      scene.addGeometries(
          //1.1
          new Triangle(new Material(0.5, 0.9, 100,0,0), new Color(65,105,225), new Point3D(-170,50,0), new Point3D(-65,50,0), new Point3D(-115,-50,10)),
          new Triangle(new Material(0.5, 0.9, 200, 0, 0), new Color(65,105,225), new Point3D(-65,50,0), new Point3D(-50,50,50), new Point3D(-115,-50,10)),
          new Triangle(new Material(0.5, 0.9, 100,0,0), new Color(65,105,225), new Point3D(-170,50,0), new Point3D(-50,50,50), new Point3D(-115,-50,10)),

          
          //1.2
          new Triangle(new Material(0.3, 0.9, 50,0.9,0), new Color(0,0,0), new Point3D(-50,50,0), new Point3D(55,50,0), new Point3D(5,-50,10)),
          new Triangle(new Material(0.3, 0.9, 100, 0.9, 0), new Color(0,0,0), new Point3D(55,50,0), new Point3D(70,52,50), new Point3D(5,-50,10)),
          new Triangle(new Material(0.3, 0.9, 50,0.9,0), new Color(0,0,0), new Point3D(-50,50,0), new Point3D(70,52,50), new Point3D(5,-50,10)),
          
          //1.3
          new Triangle(new Material(0.5, 0.9, 100,0,0),  new Color(135,206,235), new Point3D(65,50,0), new Point3D(175,50,0), new Point3D(125,-50,10)),
          new Triangle(new Material(0.5, 0.9, 200, 0, 0),  new Color(135,206,235), new Point3D(175,50,0), new Point3D(190,50,50), new Point3D(125,-50,10)),
          new Triangle(new Material(0.5, 0.9, 100,0,0),  new Color(135,206,235), new Point3D(70,50,0), new Point3D(190,50,50), new Point3D(125,-50,10)),
          
          //2.1
          new Triangle(new Material(0.5, 0.9, 100,1,0), new Color(0,0,51), new Point3D(-115,-50,0), new Point3D(-10,-50,0), new Point3D(-55,-150,10)),
          new Triangle(new Material(0.5, 0.9, 200, 1, 0), new Color(0,0,51), new Point3D(-10,-50,0), new Point3D(5,-50,50), new Point3D(-55,-150,10)),
          new Triangle(new Material(0.5, 0.9, 100,1,0), new Color(0,0,51), new Point3D(-115,-50,0), new Point3D(5,-50,50), new Point3D(-55,-150,10)),

          
          //2.2
          new Triangle(new Material(0.5, 0.9, 100,1,0), new Color(0,51,51), new Point3D(5,-50,50), new Point3D(110,-50,0), new Point3D(65,-150,10)),
          new Triangle(new Material(0.5, 0.9, 200, 1, 0), new Color(0,51,51), new Point3D(110,-50,0), new Point3D(125,-50,10), new Point3D(65,-150,10)),
          new Triangle(new Material(0.5, 0.9, 100,1,0), new Color(0,51,51), new Point3D(5,-50,50), new Point3D(125,-50,10), new Point3D(65,-150,10)),
          
          //
          new Sphere(new Material(0.5, 0.9, 100,1,0),new Color(0,0,51), 32, new Point3D(-51,-15,0)),
          new Sphere(new Material(0.5, 0.9, 100,1,0),new Color(0,51,51), 32, new Point3D(67,-15,0)),
          new Sphere(new Material(0.1, 0.9, 100,1,0),new Color(0,25.5,51), 22, new Point3D(5,25,0)),
          //stars
		  new Sphere(new Material(0.9, 0.3, 100, 0.8, 0),new Color(255,255,255),  1, new Point3D(180,-120, 30)),
		  new Sphere(new Material(0.9, 0.3, 100, 0.8, 0),new Color(255,255,255),  0.5, new Point3D(150,-80, 30)),
	      new Sphere(new Material(0.9, 0.3, 100, 0.8, 0),new Color(255,255,255), 1, new Point3D(100,-140, 30)),

          new Plane(new Material(0.2, 0.6, 100, 0.9, 0.07), new Color(0,0,51), new Point3D(-30, 50, -70), new Vector(0, -1, 0)));

      scene.addLights(new SpotLight(new Color(255, 255, 255),new Point3D(0, -100, 30), new Vector(1, 1, -1), 1,
          0.0004, 0.0000006),
          new SpotLight(new Color(255, 255, 255),new Point3D(40, -100, 30), new Vector(1, 1, -1), 1,
              0.0004, 0.0000006),
          
          new SpotLight(new Color(255, 255, 255),new Point3D(270, -100, 30), new Vector(-1,0, 1), 1,
              0.0004, 0.0000006),
          new SpotLight(new Color(255, 255, 255), new Point3D(-100,-100,0), new Vector(1, 1, 0), 1,0.0004, 0.0000006)
          );
          
          
      ImageWriter imageWriter = new ImageWriter("finalProject2", 200, 200, 600, 600);
      Render render = new Render(imageWriter, scene) //
          .setMultithreading(3) //
          .setDebugPrint();

          render.renderImage();
          render.writeToImage();


      //render.writeToImage();
    }

  }




