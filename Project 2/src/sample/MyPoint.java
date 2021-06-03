/*
* Class MyPoint is used by class MyShape
* to define the reference point
* p(x, y) of the  Java  display  coordinate system,  and
* by  all  other  subclasses  in  the  class hierarchy   any   point   stipulated
* in the class definition.The class includes appropriate
* class constructors and methods, including methods
* that perform point related operations.
*
* */



package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyPoint
{
    private double x;
    private double y;

    public MyPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public void setX()
    {
        this.x = x;
    }

    public void setY()
    {
        this.y = y;
    }


    @Override
    public String toString()
    {
        return "----point:-------" + x + y;
    }



    public void draw(GraphicsContext GC)
    {
        GC.setFill(MyColor.BLACK.myJavafxColor());
        GC.fillOval(x,y,1,1);
    }
}
