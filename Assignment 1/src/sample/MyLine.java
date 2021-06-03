package sample;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class MyLine extends MyShape {
    private int x1, y1, x2,y2;
    private  MyColor color;

    public MyLine(int x1,int y1, int x2, int y2,MyColor color)
    {
        super(x1,y1,color);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
    }

    public double length()
    {
        return Math.sqrt(Math.pow((x2-x1),2)
                +Math.pow((y2-y1),2));
    }

    public double xAngle()
    {
        return Math.toDegrees(Math.atan((x2-x1)/(y2-y1)));
    }


    @Override
    public double Area()
    {
        return 0;
    }

    @Override
    public double Perimeter()
    {
        return length();
    }

    public String toString()
    {
        return "MyLine:\n x1" + x1 + "\n y1" + y1 + "\n x2" + x2 + "\n y2" + "\n color" + color;
    }

    @Override
    public void draw(GraphicsContext GC)
    {
        GC.setStroke(super.getColor().MyJavafxColor());
        GC.strokeLine(x1,y1,x2,y2);
    }


}
