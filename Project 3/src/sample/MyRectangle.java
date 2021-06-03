/*
* Class MyRectangle extends class MyShape.
* The MyRectangle object is a rectangle
* of height hand width w, and a top  left corner point p(x, y),
* and may be filled with a color.  The class includes appropriate
* class constructors and methods, including methods that perform the following operations:
* */



package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class MyRectangle extends MyShape {
    private double width;
    private double height;
    private MyPoint PR_TLC;


    MyRectangle(MyPoint PR, double width, double height, MyColor c)
    {
        super(PR,c);
        this.width = width;
        this.height = height;
        this.PR_TLC = PR;

    }

    public MyPoint getTLC()
    {
        return PR_TLC;
    }

    public double getWidth()
    {
        return width;
    }

    public double getHeight()
    {
        return height;
    }



    public double setWidth(int width)
    {
        return this.width = width;
    }

    public double setHeight(int height)
    {
        return this.height = height;
    }

    @Override
    public double getArea()
    {
        return width * height;
    }

    @Override
    public double getPerimeter()
    {
        return 2*height + 2*width;
    }


    @Override
    public String toString()
    {
       return "---Rectangle properties:-------------------- \n"
                + "Width: " + getWidth() + "\nHeight: " + getHeight() +
               "Top Left Corner point: (" + PR_TLC.getX() + ", " + PR_TLC.getY() + ")\n" +
                "\nPerimeter: " + getPerimeter() + "\nArea: " + getArea();
    }

    @Override
    public void draw(GraphicsContext GC)
    {
        GC.setFill(super.getColor().myJavafxColor());
        GC.fillRect(PR_TLC.getX(),PR_TLC.getY(),getWidth(),getHeight());
    }


    @Override
    public MyRectangle getMyBoundingRectangle() {
        return this;
    }

    @Override
    public boolean pointInMyShape(MyPoint p)
    {
        /*Top right corner point
        * BLC Corner point*/
        MyPoint TRC = new MyPoint(PR_TLC.getX() + width, PR_TLC.getY());
        MyPoint BLC = new MyPoint(this.PR_TLC.getX(), PR_TLC.getY() - height);

        /*Point lies between the X Cords of TRC and BLC
        * Point lies between the Y cords of TRC and BLC
        * This test to see if the point lies within the bounds of the rectangle*/
        return p.getX() >= BLC.getX() && p.getX() <= TRC.getX()
                && p.getY() >= BLC.getY() && p.getY() <= TRC.getY();
    }
}
