/*
* Class Slice includes  appropriate  constructors and methods,  including  methods
* that perform the following operations:
* a.toString—returns a string representation of a Slice object;b.draw—draws a Slice object.
* */


package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class Slice {
    private double Radius;
    private MyPoint Center;

    private double start_Angle;

    private double Angle;

    private double arcStart_Angle;
    private double arcCenter_Angle;
    private double arcEnd_Angle;
    private MyColor c;


    Slice(MyPoint center, double radius, double startingAngle, double Angle, MyColor color) {
        this.Center = center;
        this.Radius = radius;
        this.start_Angle = startingAngle;
        this.Angle = Angle;
        this.c = color;
        this.arcCenter_Angle = Math.toRadians(Angle);
    }

//========================================================================================
    //Get methods

    public double getRadius()
    {
        return Radius;
    }

    public double getStart_Angle()
    {
        return start_Angle;
    }

    public double getArcStart_Angle()
    {
        return arcStart_Angle;
    }

    public double angleInRadians(){
        return Math.toRadians(Angle);
    }

    public double getArcCenter_Angle()
    {
        return arcCenter_Angle;
    }

    public double getArcEnd_Angle()
    {
        return arcEnd_Angle;
    }

    public double getAngle()
    {
        return Angle;
    }


    public MyPoint getCenter()
    {
        return Center;
    }

    //========================================================================================
    // override methods

    @Override
    public String toString()
    {
        return "---Slice properties------- \n"
                + getStart_Angle() + "\nAngle: " + getAngle()
                + getRadius() + "\n-- Starting angle --:"
                + "\n" + "-- Center ---: (" + Center.getX() + ", " + Center.getY() + ")\n";
    }


    public void draw(GraphicsContext GC)
    {
        GC.setFill(c.myJavafxColor());
        GC.fillArc(Center.getX() - Radius, Center.getY() - Radius, Radius * 2,
                2 * Radius, start_Angle, Angle,
                ArcType.ROUND);
    }


}

