    /* Class MyArc inherits class MyShape.The MyArc
* object is a segment of the boundary of a MyOval object, defined by the endpoints (x1, y1) and
* (x2, y2), or their corresponding angles,  on  the MyOval boundary.
* The MyArc object  may be filled  with any  color of MyColor enum reference  type.
* The  class  includes  appropriate  class  constructors  and methods, including methods
* that perform the following operations:
* a.toString—returns a string representation of aMyArc object;b.draw—draws a MyArc object.
* */

package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape
{
    MyPoint point_Center;
    MyPoint P1, P2;
    double start_Angle;
    double Angle;
    double r_startAngle, r_centerAngle, r_endAngle;
    MyColor color;
    MyOval O;
    double width, height;


    MyArc(MyPoint P, int width, int height, double start_Angle, double angle, MyColor C)
    {
        super(new MyPoint(0,0),null);
        this.point_Center = P;
        this.width = width;
        this.height = height;
        this.start_Angle = start_Angle;
        this.Angle = Angle;
        this.color = C;

        this.r_startAngle = Math.toRadians(start_Angle);
        this.r_centerAngle = Math.toRadians(Angle);
        this.r_endAngle = Math.toRadians(start_Angle - Angle);

        double x = point_Center.getX();
        double y = point_Center.getY();

        int x1 = (int) (x + (double) (width + height) /Math.sqrt(Math.pow(height,2)
                + Math.pow(width * Math.tan(r_startAngle),2)));

        int y1 = (int) (y + (double) (width * height * Math.tan(r_startAngle)) / Math.sqrt(Math.pow(height,2))
                + Math.pow(width * Math.tan(r_startAngle),2));

        int x2 = (int) (x + (double) (width + height) /Math.sqrt(Math.pow(height,2)
                + Math.pow(width * Math.tan(r_endAngle),2)));

        int y2 = (int) (y + (double) (width * height * Math.tan(r_endAngle)) / Math.sqrt(Math.pow(height,2))
                + Math.pow(width * Math.tan(r_endAngle),2));

        this.P1 = new MyPoint(x1,y1);
        this.P2 = new MyPoint(x2,y2);

        this.O = new MyOval(point_Center,width,height,color);
    }



    //========================================================================================

    // Constructor for my arc
        MyArc(MyOval O, MyPoint P1, MyPoint P2, MyColor color)
        {
            super(new MyPoint(0,0), null);
            this.point_Center = O.getCenter();
            this.P1 = P1;
            this.P2 = P2;

            this.start_Angle = point_Center.angleX(P1);
            this.Angle = point_Center.angleX(P2) - start_Angle;

            this.color = color;
            this.r_startAngle = Math.toRadians(start_Angle);
            this.r_endAngle = Math.toRadians(start_Angle + Angle);
            this.r_centerAngle = Math.toRadians(Angle);

            this.width = O.getSemiMajor() * 2;
            this.O = O;
        }





    //========================================================================================
    // Get methods
        public MyPoint getPoint_Center()
    {
        return point_Center;
    }

    public double getStart_Angle()
    {
        return start_Angle;
    }

    public double getAngle()
    {
        return Angle;
    }

    public MyColor getColor()
    {
        return color;
    }



    /*https://math.stackexchange.com/questions/432902/
    * how-to-get-the-radius-of-an-ellipse-at-a-specific-angle-by-knowing-its-semi-majo*/
    public double getRadius()
    {
        return (width * height) / Math.sqrt(Math.pow(height,2) *
                Math.pow(Math.sin(r_centerAngle),2)
                + Math.pow(width,2)
                * Math.pow(Math.cos(r_centerAngle),2));
    }


    //override methods
    // https://en.wikipedia.org/wiki/Circular_sector
    @Override
    public double getArea()
    {
        double radius = getRadius();
        return (0.5) * r_centerAngle * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter()
    {
        double radius = getRadius();
        return r_centerAngle * radius + 2 * radius;
    }



    @Override
    public void draw(GraphicsContext GC)
    {
        final double centerX = point_Center.getX() - width;
        final double centerY = point_Center.getY() - height;
        GC.setFill(super.getColor().myJavafxColor());
        GC.setStroke(super.getColor().myJavafxColor());
        GC.strokeArc(centerX, centerY, width * 2,
                height * 2, start_Angle, Angle, ArcType.ROUND);
        GC.fillArc(centerX, centerY, width * 2,
                height * 2, start_Angle, Angle, ArcType.ROUND);
    }

    @Override
    public MyRectangle getMyBoundingRectangle()
    {
        return O.getMyBoundingRectangle();
    }

    @Override
    public boolean pointInMyShape(MyPoint P) {
        double pointAngle = point_Center.angleX(P);
        double dx = point_Center.getX() - P.getX();
        double dy = point_Center.getY() - P.getY();

        return Math.pow(height * dx, 2) + Math.pow(width * dy, 2) <= Math.pow(width * height, 2)
                && pointAngle >= start_Angle
                && pointAngle <= start_Angle + Angle;
    }


}
