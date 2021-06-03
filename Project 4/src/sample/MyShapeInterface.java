/*
* InterfaceMyShapeInterface is implemented by classMyShape.
* All subclasses of  the  hierarchy  must  be  amended  in  accordance
* with  the interface.The  interface includes  appropriate constants  and abstract,
* static,  and/or  default  methods  that describe  the  functions  and
* behaviors  of  the  specific  object  types  of  the  class hierarchy, including:
* */


package sample;



public interface MyShapeInterface {

    /*
    * abstract method returns the bounding rectangle of an object in the class hierarchy
    * */
     MyRectangle getMyBoundingRectangle();


    /*
    * abstract method returns true if a
    * point p is located within or on
    * the boundary of an object in the class hierarchy*/
    boolean pointInMyShape(MyPoint p);



    /*
    * static method returns the intersecting area—i.e.,
    * the set of  all  points  on or within  the  boundary
    * of  the  area —of
    * two  objects  in  the class hierarchy
    * if they do overlap;and null otherwise.
    * */
    static MyRectangle intersectMyShapes(MyShape s1, MyShape s2)
    {
        if(s1 instanceof MyLine || s2 instanceof MyLine)
        {
            return null;
        }

        MyRectangle R1 = s1.getMyBoundingRectangle();
        MyRectangle R2 = s2.getMyBoundingRectangle();

        double x = R1.getX();
        double y = R1.getY();

        double w = R1.getWidth();
        double h = R1.getHeight();

        double x2 = R2.getX();
        double y2 = R2.getY();

        double w2 = R2.getWidth();
        double h2 = R2.getHeight();

        if(x + w < x2 || x2 + w2 < x)
        {
            return null;
        }

        if(y + h < y2 || y2 + h2 < y)
        {
            return null;
        }

        double max_x = Math.max(x,x2);
        double max_y = Math.max(y,y2);

        double min_x = Math.min(x+w,x2+w2);
        double min_y = Math.min(y+h,y2+h2);

        double diff_x = Math.abs(min_x - max_x);
        double diff_y = Math.abs(min_y - max_y);

        return new MyRectangle(new MyPoint(max_x,max_y),diff_x,diff_y,MyColor.BLUE);

    }





}
