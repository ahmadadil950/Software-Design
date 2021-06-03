package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class MyCircle extends MyShape{
    private double radius;

    public MyCircle(int x1, int y1,double radius, MyColor c)
    {
        super(x1, y1, c);
        this.radius = radius;
    }


    @Override
    public double Perimeter()
    {
        return radius * 2 * Math.PI;
    }


    public double getRadius()
    {
        return radius;
    }


    @Override
    public double Area()
    {
        return Math.PI * Math.pow(radius,2);
    }


    @Override
    public String toString()
    {
        return "MyCircle:\n" + "Radius" + radius +
                "Perimeter" + Perimeter() + "Area" + Area()
                + "Color" + getColor();
    }


    public void draw(GraphicsContext GC)
    {
        GC.setFill(super.getColor().MyJavafxColor());
        GC.setStroke(super.getColor().MyJavafxColor());
        GC.strokeOval(getX(),getY(),radius,radius);
        GC.fillOval(getX(),getY(),radius,radius);


    }



}
