package sample;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;



public class MyShape {
    private MyColor color; // color of the shape
    private int x, y;   // reference point



 public MyShape(int x1, int y1,MyColor c)
 {
      x = x1;
      y = y1;
      color = c;

 }

//---------------------------------------------------------------------------------------
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public MyColor getColor()
    {
        return color;
    }

//-----------------------------------------------------------------
//---------------------------------------------------------------------------------------

public String toString()
{
    return "MyShape:\n" + "x" + x +"y" + y + "color" + color;
}

public void draw(GraphicsContext GC)
{
    GC.setFill(color.MyJavafxColor());
    GC.fill();
}

public double Area()
{
    return 0;
}

public double Perimeter()
{
    return 0;
}

}
