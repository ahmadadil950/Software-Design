/*
 *   The MyLine object is a straight line defined by the endpoints (x1, y1) and (x2, y2).
 *   The MyLine object may be of any color.
 *   This also inherits from MyShape
 */


package sample;

import javafx.scene.canvas.GraphicsContext;

public class MyLine extends MyShape {
    private MyPoint P1;// Coordinates of two points of line
    private MyPoint P2;

    MyLine(MyPoint P1, MyPoint P2 ,MyColor color){
        super(P1,color);
        this.P1 = P1;
        this.P2 = P2;
    }

    /* Angle of the lines
     *  Found using the arctan formula*/
    public double xAngle()
    {
        return Math.toDegrees(Math.atan((P2.getY()-P1.getY())/(P2.getX()- P1.getX())));
    }

    /* Total length of the lines
     *   This includes the length of a line equation*/
    public double length()
    {
        return Math.sqrt(Math.pow(P2.getX()- P1.getX(),2) + Math.pow(P2.getY()-P1.getY(),2));
    }


    /*
     * Calculate distance between 2 points using distance formula
     * */
    public double calc_Distant(MyPoint p1, MyPoint p2)
    {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    @Override
    public double getArea()
    {
        return 0;
    }

    @Override
    public double getPerimeter()
    {
        return length();
    }

    @Override
    public String toString()
    {
        return "------Line:-------------------- \n"
                + "\nEndpoints: [("+ P1.getX() + ", " + P1.getY() + "), ("+ P2.getX() + ", " + P2.getY() + ")] \n" +
                "\nAngle: " + xAngle()+ "\nLength: " + length() ;
    }
    @Override
    public void draw(GraphicsContext GC){
        GC.setStroke(super.getColor().myJavafxColor());
        GC.strokeLine(P1.getX(), P1.getY(), P2.getX(), P2.getY());
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(P1, Math.abs(P2.getX()-P1.getX()),
                Math.abs(P2.getY()-P1.getY()), super.getColor());
    }

    @Override
    public boolean pointInMyShape(MyPoint p)
    {
        double d = calc_Distant(P1, p) + calc_Distant(p, P2);
        double d2 = calc_Distant(P1, P2);
        if(d == d2){
            return true;
        }
        return false;
    }
}