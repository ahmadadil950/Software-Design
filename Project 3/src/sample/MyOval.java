/*
* Class MyOval extends class MyShape.    The MyOval
* object is  defined  by an ellipse of semi-major and semi-minor axes, a
* and b, and center pointp(x, y).  The MyOval object  may  be  filled  with  a  color.
* The  class  includes appropriate  class constructors
* and   methods, including   methods that   perform   the   following operations:
*
*
* */



package sample;


import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape
{
    private MyPoint center;
    private double mA, mB;


    MyOval(MyPoint center, double sMajor, double sMinor, MyColor c)
    {
        super(center,c);
        this.center = center;
        this.mA = sMajor;
        this.mB = sMinor;

    }

    public MyPoint getCenter()
    {
        return center;
    }

    public double getSemiMajor()
    {
        return mA;
    }

    public double getSemiMinor()
    {
        return mB;
    }


    @Override
    public double getArea()
    {
        return mA * mB * Math.PI;
    }

    @Override
    public double getPerimeter()
    {
        double width = mA * 2;
        double height = mB * 2;
        return Math.PI * 2 * Math.sqrt((Math.pow(height,2)) + (Math.pow(width,2))/2);
    }

    @Override
    public void draw(GraphicsContext GC)
    {
        GC.setFill(super.getColor().myJavafxColor());
        GC.fillOval(center.getX() - mA,center.getY()-mB,2*mA,2*mB);
    }


    @Override
    public MyRectangle getMyBoundingRectangle() {
        double x = point.getX() - getSemiMajor();
        double y = point.getY() - getSemiMinor();
        MyPoint TLC = new MyPoint(x, y);
        return new MyRectangle(TLC, 2 * getSemiMajor(), 2 * getSemiMinor(), MyColor.GREY);
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        /* Equation of an oval, divided into two parts
        *  // (x-h)^2 / a^2
        *  // (y-w)^2 / b^2
        * https://mathworld.wolfram.com/Ellipse.html
        * */
        double F_EQ = Math.pow(getCenter().getX() - p.getX(), 2) / Math.pow(getSemiMajor(), 2);
        double S_EQ = Math.pow(getCenter().getY() - p.getY(), 2) / Math.pow(getSemiMinor(), 2);
        /*
        * <1 meaning it is inside the oval
        * =1 meaning it is on the circumference
        * */
        if(F_EQ + S_EQ <= 1)
        {
            return true;
        }
        return false;
    }
}

