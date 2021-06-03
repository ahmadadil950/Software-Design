/*
 * Enum MyColor sets up colors of selected RGB values
 * and opacities.
 * These are used in the lines and shapes in the project
 *
 * */




package sample;

import javafx.scene.paint.Color;

public enum MyColor{
    RED(255,0,0,255),
    BLUE(0,0,255,255),
    BEIGE(245, 245, 220, 255),
    LIME(0,255,0,255),
    ORANGE(255, 165, 0, 255),
    CYAN(0,255,255,255),
    GREEN(0,128,0,255),
    TURQUOISE(62, 224, 208, 255),
    GREY(128,128,128,255),
    MAGENTA(255,0,255,255),
    PURPLE(128,0,128,255),
    DARKRED(255, 99, 71, 255),
    VIOLET(148,0,211,255),
    NAVY(0, 0, 128, 255),
    YELLOW(255,255,0,255),
    WHITE(255,255,255,255),
    BLACK(0,0,0,255),
    PINK(255,192,203,255),
    DARKGREEN(0, 100, 0, 255),
    HOTPINK(255,105,180,255),
    blanched_almond(255,235,205,255),
    slate_gray(112,128,144,255),
    ghost_white(248,248,255,255),
    mint_cream(245,255,250,255);

    private int r,g,b,opacity;

    MyColor(int r, int g, int b, int opacity)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.opacity = opacity;
    }

    public static MyColor [] getMyColor()
    {
        return MyColor.values();
    }

    public Color myJavafxColor()
    {
        return Color.rgb(r, g, b, (opacity/255));
    }
}