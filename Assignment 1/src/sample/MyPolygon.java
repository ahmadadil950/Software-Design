package sample;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;


public class MyPolygon extends MyShape
{
    private int sides;
    private double radius;
    double[] X_perimeter;
    double[] Y_perimeter;


    public MyPolygon(int x1, int y1, MyColor c, double radius, int sides) {
        super(x1, y1, c);
        this.sides = sides;
        this.radius = radius;
        X_perimeter = new double[sides];
        Y_perimeter = new double [sides];
        double angle = (2 * Math.PI)/sides;

        for (int i = 0; i<sides; i++)
        {
            X_perimeter[i] = x1 + (radius*(-1 * Math.sin(i*angle)));
            Y_perimeter[i] = y1 + (radius*(-1 * Math.sin((i*angle))));
        }
    }



//---------------------------------------------------------------------------------------

    public double Angle()
    {
        return 180/sides;
    }

    public double getSides()
    {
        return 2 * radius * Math.sin(Angle());
    }

    public double getApo()
    {
        return Math.cos(Math.toRadians(180 / sides) * radius);
    }

    @Override
    public double Perimeter()
    {
        return getSides() * sides;
    }

    public double getRadius()
    {
        return radius;
    }


    @Override
    public double Area()
    {
        return Math.pow(radius,2) * sides *
                Math.sin(Angle()) * 1/2;
    }

//---------------------------------------------------------------------------------------


    @Override
    public String toString()
    {
        return "MyPolygon:\n" + "Angle:" + Angle() + "Area:" + Area() + "Perimeter:" + Perimeter() + "side length" +
                getSides();
    }


    @Override
    public void draw(GraphicsContext GC)
    {
        GC.setFill(super.getColor().MyJavafxColor());
        GC.setStroke(super.getColor().MyJavafxColor());
        GC.setLineWidth(5);
        GC.strokePolygon(X_perimeter,Y_perimeter,sides);
        GC.fillPolygon(X_perimeter,Y_perimeter,sides);
    }

    public void boarder(GraphicsContext GC)
    {
        // change to mycolor
        GC.setStroke(super.getColor().MyJavafxColor());
        for (int i = 0; i<sides; i++)
        {
            GC.strokeLine(X_perimeter[i-1],Y_perimeter[i-1],
                    X_perimeter[i],Y_perimeter[i]);
        }
        GC.strokeLine(X_perimeter[sides - 1], Y_perimeter[sides - 1],
                      X_perimeter[0], Y_perimeter[0]);

    }
}
