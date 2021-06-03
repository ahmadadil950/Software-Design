/*
 *   Class MyShape is the hierarchy’s superclass and inherits the
 *   Java class Object. An implementation of the class defines a
 *   reference point Point and the color of the shape.
 * */




package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



//-----------------------------------------------------------------------------------------

public abstract class MyShape implements MyShapeInterface {
    private MyColor color;
    public MyPoint point;

    MyShape(MyPoint p, MyColor color){
        this.point = p;
        this.color = color;
    }

    //-----------------------------------------------------------------------------------------




    //-----------------------------------------------------------------------------------------
    public double getX()
    {
        return point.getX();
    }
    public double getY()
    {
        return point.getY();
    }
    public MyColor getColor()
    {
        return color;
    }


    //-----------------------------------------------------------------------------------------
    public abstract double getArea();

    public abstract double getPerimeter();
    //-----------------------------------------------------------------------------------------


    //-----------------------------------------------------------------------------------------
    public String toString(){
        return "------MyShape:--------------------\n" +
                "\nx: " + point.getX() +
                "\ny: " + point.getY() +
                "\ncolor: " + color;
    }
    public abstract void draw(GraphicsContext GC);
    //-----------------------------------------------------------------------------------------
}