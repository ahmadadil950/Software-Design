package sample;

import javafx.scene.paint.Color;

public enum MyColor {
    RED(255,0,0,255),
    BLUE(0,0,255,255),
    LIME(0,255,0,255),
    CYAN(0,255,255,255),
    GREEN(0,128,0,255),
    GREY(128,128,128,255),
    MAGENTA(255,0,255,255),
    PURPLE(128,0,128,255),
    VIOLET(148,0,211,255),
    YELLOW(255,255,0,255),
    WHITE(255,255,255,255),
    BLACK(0,0,0,255),
    HOTPINK(255,105,180,255);

     private int r, g, b,opacity;

    MyColor(int r, int g, int b, int opacity)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.opacity = opacity;
    }

    public Color MyJavafxColor()
    {
        return Color.rgb(r,g,b,(double) (opacity/255));
    }

}
