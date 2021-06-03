/*
 *
 * --------------------------------
 *   Ahmad Adil
 *   CSC 221
 *   Assignment 2
 *   3/15/2021
 * -------------------------------------
 *
 */




package sample;

/* Imports */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
    // This sets the boundary of the window size
    final int width = 1000;
    final int height = 600;


    // Main function to setup stage
    @Override
    public void start(Stage primaryStage) {
        try {


            //========================================================================================

            /*
             * This includes the title of the project along with the
             * Canvas class, that takes in the constant width and
             * height for the window
             * */
            primaryStage.setTitle("Ahmad Adil Assignment 2");
            Canvas canvas = new Canvas(width, height);
            GraphicsContext GC = canvas.getGraphicsContext2D();
            GC.setLineWidth(4);
            //========================================================================================
            /* These are included to help with the resize of the shapes in different
             *   resolutions*/
            double w = width /2, h = height/2;


            //========================================================================================

            /*
             * These classes are made in order print out the hexagons,circles,rectangles and ovals in order
             * and various them by different colors
             * */
            MyPoint p = new MyPoint(width/2,height/2);
            MyPolygon Poly1 = new MyPolygon(p, 6, w/2,MyColor.RED);
            MyRectangle Rect1 = Poly1.getMyBoundingRectangle();
            Rect1.draw(GC);
            Poly1.draw(GC);
            MyCircle Circle1 = new MyCircle(p, w/3, MyColor.HOTPINK);
            MyRectangle Rect2 = Circle1.getMyBoundingRectangle();
            Rect2.draw(GC);
            Circle1.draw(GC);
            MyOval Oval1 = new MyOval(p,w/4,h/4,MyColor.TURQUOISE);
            MyRectangle Rect3 = Oval1.getMyBoundingRectangle();
            Rect3.draw(GC);
            Oval1.draw(GC);

            /*This lists out the properties of the circle and polygons */
            System.out.println(Poly1 + "\n\n" + Circle1 + "\n\n" + Rect1 + "\n\n" +
                    Rect2 + "\n\n" + Oval1 + "\n\n");
            //-----------------------------------------------------------------------------------------


            //-----------------------------------------------------------------------------------------

            /*Testing points and intersecting functions:*/


            //Polygon
            MyPoint point3 = new MyPoint(950, 450);
            point3.draw(GC);
            System.out.println(point3);
            if (Poly1.pointInMyShape(point3))
            {
                System.out.println("(" + point3.getX() + ", " + point3.getY() + ") " +
                        "is in the " + Poly1.getColor() + " polygon.\n");
            }
            else
            {
                System.out.println("(" + point3.getX() + ", " + point3.getY() + ") " +
                        "is NOT in the " + Poly1.getColor() + " polygon. \n");
            }



            // Ovals
            MyPoint point2 = new MyPoint(50, 250);
            point2.draw(GC);
            System.out.println(point2);
            if (Oval1.pointInMyShape(point2))
            {
                System.out.println("-----(" + point2.getX() + ", " + point2.getY() + ")-----" +
                        "is in the " + Oval1.getColor() + " oval.\n");
            }
            else
            {
                System.out.println("-----(" + point2.getX() + ", " + point2.getY() + ")-----" +
                        "is NOT in the " + Oval1.getColor() + " oval. \n");
            }



            // Circle
            MyPoint point4 = new MyPoint(50, 350);
            point4.draw(GC);
            System.out.println(point4);
            if (Circle1.pointInMyShape(point4))
            {
                System.out.println("(" + point4.getX() + ", " + point4.getY() + ") " +
                        "is in the " + Circle1.getColor() + " circle.\n");
            }
            else
            {
                System.out.println("(" + point4.getX() + ", " + point4.getY() + ") " +
                        "is NOT in the " + Circle1.getColor() + " circle. \n");
            }

            /*Rectangle*/
            System.out.println("Testing PointInMyShape");
            MyPoint point1 = new MyPoint(250, 630);
            point1.draw(GC);
            System.out.println("------"+point1+"------------");
            if (Rect1.pointInMyShape(point1))
            {
                System.out.println("-----(" + point1.getX() + "," + point1.getY() + ")-----" +
                        "is in the " + Rect1.getColor() + " rectangle. \n");
            }
            else {
                System.out.println("-----(" + point1.getX() + ", " + point1.getY() + ")-----" +
                        "is NOT in the " + Rect1.getColor() + " rectangle. \n");
            }




            //intersectMyShapes
            System.out.println("-----Testing intersectMyShapes------");
            MyPoint center = new MyPoint(width/2,height/2);
            MyCircle circle2 = new MyCircle(center, w/10,MyColor.LIME);
            MyPolygon poly2 = new MyPolygon(center, 6, w/11, MyColor.CYAN);
            circle2.draw(GC);
            poly2.draw(GC);
            MyRectangle rect4 = MyShapeInterface.intersectMyShapes(poly2, circle2);
            rect4.draw(GC);
            System.out.println("-----Intersecting Rectangle-------");
            System.out.println(rect4);
            //-----------------------------------------------------------------------------------------






            //-----------------------------------------------------------------------------------------

            /*
             * Draws out the two lines and gives them color
             * */
            MyLine L1 = new MyLine(new MyPoint(0,0),new MyPoint(width,height),MyColor.BLACK);
            MyLine L2 = new MyLine(new MyPoint(width,0),new MyPoint(0,height),MyColor.BLACK);
            L1.draw(GC);
            L2.draw(GC);
            GC.strokeRect(0, 0, width, height);

            /*Prints out the properties of the line*/
            System.out.println("\n"+L1 + "\n" + "\n"+L2 + "\n");
            //-----------------------------------------------------------------------------------------



            //-----------------------------------------------------------------------------------------
            // Makes the stackpane and creates the scene for the image to be built on
            StackPane root = new StackPane(canvas);
            Scene scene = new Scene(root, width, height);
            primaryStage.setScene(scene);
            primaryStage.show();
            //-----------------------------------------------------------------------------------------

        }

        //-----------------------------------------------------------------------------------------
        /*This is for the try function*/
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //-----------------------------------------------------------------------------------------


    }

    public static void main(String[] args)
    {
        launch(args);
    }
}