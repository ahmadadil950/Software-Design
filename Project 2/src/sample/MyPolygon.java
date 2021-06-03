/*
 *   The MyPolygon object
 *   is a regular polygon defined by the integer parameter, N—
 *   the number of the polygon’s equal side lengths and equal
 *   interior angles, and the center (x, y) and radius, r, of the
 *   circle in which it is inscribed. The MyPolygon object may
 *   be filled with a color.
 * */



package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/*This is extended from MyShape*/
public class MyPolygon extends MyShape {
    private double radius;
    private int sides;
    private MyPoint PP;
    // This is for length of the sides
    double[] X_perimeter;
    double[] Y_perimeter;

    MyPolygon(MyPoint PP,int Sides, double Radius,MyColor C){
        super(PP,C);
        this.radius = Radius;
        this.sides = Sides;
        this.PP = PP;
        X_perimeter = new double[Sides];
        Y_perimeter = new double[Sides];



        double Angle = (2 * Math.PI)/Sides;
        for (int i = 0; i < Sides; ++i){
            X_perimeter[i] = PP.getX() + (Radius*(-1 * Math.sin(i*Angle)));
            Y_perimeter[i] = PP.getY() + (Radius*(-1 * Math.cos(i*Angle)));
        }
    }


    // Overriden from MyShape
    @Override
    public double getArea()
    {
        return Math.pow(radius,2) * sides * Math.sin(get_Angle()) * 0.5;
    }
    @Override
    public double getPerimeter()
    {
        return sides * get_Side();
    }
    @Override
    public double getX()
    {
        return PP.getX();
    }

    @Override
    public double getY()
    {
        return PP.getY();
    }


    /* a line from the center of a regular polygon at right angles to any of its sides.
     *   apothem = R * cos(pi/number of sides)
     * */
    public double get_Apo()
    {
        return radius * Math.cos(Math.toRadians(180 / sides));
    }


    public double get_Angle()
    {
        return Math.PI/sides;
    }

    public double get_Side()
    {
        return  radius * 2 * Math.sin(Math.PI/sides);
    }

    public MyPoint getPoint()
    {
        return PP;
    }


    @Override
    public String toString(){
        return "------Polygon:-------------------- \n" +
                "\nInterior angle: " + get_Angle()
                + "\nPerimeter: " + getPerimeter() +
                "\nSide length: " + get_Side() +
                "\nArea: " + getArea();
    }

    // border of the
    public void border(GraphicsContext GC){
        GC.setStroke(Color.BLACK);
        for (int i = 1; i < sides; ++i){
            GC.strokeLine(X_perimeter[i-1], Y_perimeter[i-1], X_perimeter[i], Y_perimeter[i]);
        }
        GC.strokeLine(X_perimeter[sides-1], Y_perimeter[sides-1], X_perimeter[0], Y_perimeter[0]);
    }



    @Override
    public void draw(GraphicsContext GC){
        GC.setFill(super.getColor().myJavafxColor());
        GC.setStroke(super.getColor().myJavafxColor());
        GC.setLineWidth(4);
        GC.strokePolygon(X_perimeter, Y_perimeter, sides);
        GC.fillPolygon(X_perimeter, Y_perimeter, sides);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        MyPoint TLC = new MyPoint(PP.getX() - radius, PP.getY() - radius);
        return new MyRectangle(TLC, 2 * radius, 2 * radius, MyColor.BEIGE);
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
            /*
            * equation of circle:
            * (x-h)^2 + (y-h)^2 = r^2
            * */

            if(Math.pow(p.getX() - PP.getX(), 2) + Math.pow(p.getY() -
                    PP.getY(), 2) <= Math.pow(radius, 2))
            {
                return true;
            }
            return false;
    }
}