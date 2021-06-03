/* This class creates and builds the piechart that will contain
* and hold the database information.
* It will also give color to each slice within the piechart and
* Draw it within the window screen
* */




package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

import java.util.HashMap;
import java.util.Map;


public class Piechart
{
    public Piechart()
    {
        HASH_M = Database.studAndGrades();
    }



    //========================================================================================
    public int getTotalFrequency() {
        int freq = 0;
        for (Map.Entry<Character, Integer> it : HASH_M.entrySet()) {
            freq += it.getValue();
        }
        return freq;
    }

    //========================================================================================

    public void draw(GraphicsContext GC) {
        double x = GC.getCanvas().getWidth() / 2; // x-coordinate of center of canvas
        double y = GC.getCanvas().getHeight() / 2; // y-coordinate of center of canvas
        double radius = y / 2; // radius of pie chart
        int current_Color = 0, total_Freq = getTotalFrequency();
        float current_Angle = 0;
        for (char i = 0; i < 6; i++) {
            GC.setFill(colors[current_Color++].myJavafxColor());
            if (HASH_M.containsKey(GPAs[i])) {
                int current_Freq = HASH_M.get(GPAs[i]); // # of students
                float P_E = (float) current_Freq / total_Freq; // probability of event
                GC.fillArc(x - radius, y - radius, radius * 2,
                        radius * 2, current_Angle,
                        P_E * 360, ArcType.ROUND);
                GC.setFill(MyColor.BLACK.myJavafxColor()); // change fill for text
                double mid_ArcAngle = (current_Angle + (P_E * 180)) * Math.PI / 180;
                GC.fillText("GPA: " + GPAs[i] + ", " + current_Freq + " students",
                        x + (radius * Math.cos(mid_ArcAngle)) * 1.6,
                        y - (radius * Math.sin(mid_ArcAngle)) * 1.2);
                current_Angle += P_E * 360;
            }
        }
    }

    //========================================================================================

    private HashMap<Character, Integer> HASH_M;
    private static char[] GPAs = new char[]{'A', 'B', 'C', 'D', 'F', 'W'};
    private static MyColor[] colors = new MyColor[]{
            MyColor.GREEN,
            MyColor.blanched_almond,
            MyColor.mint_cream,
            MyColor.TURQUOISE,
            MyColor.RED,
            MyColor.slate_gray};
}
