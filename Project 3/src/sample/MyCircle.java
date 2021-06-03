/* The MyCircle object is  defined  by  its radius, r,and  center  (x, y), and
 * may  be  filled  with  a  color.
 * This also inherits from the MyShape class
 * */


package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyCircle extends MyOval {
    private final double radius;
    public MyPoint pc;

    // MyCircle extends MyShape
    MyCircle(MyPoint pc, double radius, MyColor color)
    {
        super(pc,radius,radius,color);
        this.radius = radius;
    }

    // Overriden functions from MyShape
    @Override
    public double getArea()
    {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter()
    {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getX()
    {
        return pc.getX();
    }

    @Override
    public double getY()
    {
        return pc.getY();
    }

    // Radius from the circle
    public double get_Radius()
    {
        return radius;
    }


    @Override
    public String toString()
    {
        return "----Circle:-------------------- \n" +
                "\nPerimeter: " + getPerimeter() +
                "Radius: " + get_Radius() +
                "\nArea: " + getArea();
    }


    @Override
    public void draw(GraphicsContext GC)
    {
        GC.setFill(super.getColor().myJavafxColor());
        GC.fillOval(super.getX() - radius, super.getY() - radius, 2 * radius, 2 * radius);

    }
}