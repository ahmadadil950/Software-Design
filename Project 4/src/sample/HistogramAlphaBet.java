/*
* Implement a Java class HistogramAlphaBet that calculates the n
* most frequent alphabet  characters  in  “Emma” (file Emma.txt)  and  their
* probabilities.
* The HistogramAlphaBet class  utilizes  a
* Map collection  for  statistical  calculations and  the  drawing
* canvas  above  to  draw  a  pie  chart
* of  the  probabilities.
* It  also includes the MyPieChart class as an inner class.
* */

package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;



public class HistogramAlphaBet
{
    public Map<String, Integer> char_Frequency = new HashMap<String, Integer>();


    // copy constructor
    HistogramAlphaBet(Map<String, Integer> freq)
    {
        char_Frequency.putAll(freq);
    }


    // default constructor
    HistogramAlphaBet(String script)
    {
        String x = script.replaceAll("[^a-zA-z]", "").toLowerCase();
        for (int i = 0; i < x.length(); i++) {
            String key = x.charAt(i) + "";
            increment_Freq(char_Frequency, key);
        }
    }


    //========================================================================================
    // get methods

    public Map<String, Integer> getFrequency()
    {
        return char_Frequency;
    }

    public Integer getCumulative_Freq() {
        return char_Frequency.values().stream().
                reduce(0, Integer::sum);
    }



    //========================================================================================
    // sorting methods
    public Map<String, Integer> sortFrequencyUp() {
        return char_Frequency.entrySet().stream().sorted(Map.Entry.comparingByValue()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::
                        getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public Map<String, Integer> sortFrequencyDown() {
        return char_Frequency
                .entrySet()
                .stream()
                .sorted(Collections.
                        reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }


    //========================================================================================
    // override methods

    @Override
    public String toString() {
        StringBuilder S = new StringBuilder("----Frequency of String:---- \n\n");

        for (String C : char_Frequency.keySet()) {
            S.append(C).append(": ").append(char_Frequency.get(C)).append("\n\n");
        }
        return S.toString();
    }

    private static <K> void increment_Freq(Map<K, Integer> map, K key) {
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) + 1);
    }








    /*The inner class of HistogramAlphaBet*/
    /* Implement  a  Java  class MyPieChart that  displays  a circular pie  chart  of  the probabilities
    * of the n most frequent occurrences of an event to be specified in part 5
    * of the Assignment.  The probability of event is given by:In the pie chart:i.Each  event  is
    * represented  by  a slice of  the  pie  chart.
    * The  area  of the slice is proportional to the probability of the corresponding event:
    * ii.Each slice has a different color of your choice of type enumMyColor;
    * iii.Each slice has a legend showing the corresponding event
    * and its probability;iv.The slices are displayed in order of decreasing probability;
    * */
    public class MyPieChart
    {
        private Map<String, Double> prob = new HashMap<String, Double>();
        private Map<String, Slice> slices = new HashMap<String, Slice>();

        int num;
        MyPoint Center;
        double Radius;
        double start_Angle;


        MyPieChart(int n, MyPoint Center, double Radius, double start_Angle) {
            this.num = n;
            this.Center = Center;
            this.Radius = Radius;
            this.start_Angle = Optional.of(start_Angle).orElse(0.0);

            prob = getProb();
            slices = getMyPieChart();
        }

        //========================================================================================

        public Map<String, Slice> getMyPieChart()
        {

            MyColor[] COLOR = MyColor.getMyColor();
            int randColor = 0;
            for(MyColor R_COLOR : MyColor.values())
            {
                COLOR[randColor] = R_COLOR;
            }


            double startAngle = start_Angle;
            int counter = 0;
            double probabilityCounter = 0;


            for (String key : prob.keySet())
            {
                if (counter < num) {
                    double angle = 360.0 * prob.get(key);
                    slices.put(key, new Slice(Center, Radius, startAngle, angle,COLOR[randColor]));
                    startAngle += angle;
                    counter++;
                    randColor++;
                }

                else

                {
                    double angle = 360.0 * (1 - probabilityCounter);
                    slices.put("All other letters", new Slice(Center, Radius, startAngle,
                            angle, COLOR[randColor]));
                    break;
                }
                probabilityCounter += prob.get(key);

            }
            return slices;
        }

        public Map<String, Double> getProb() {
            double Cu_Freq = 1.0 / getCumulative_Freq();
            for (String Key : char_Frequency.keySet()) {
                prob.put(Key, (double) char_Frequency.get(Key) * Cu_Freq);
            }
            return prob.entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey,
                            Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        }



        public double Getsum_Prob()
        {
            return prob.values().stream().reduce(0.0, Double::sum);
        }

//========================================================================================
        // Draw method for both the text and slices within the piechart

        public void draw(GraphicsContext GC)
        {
                int counter = 0;
                DecimalFormat decimalFORMAT = new DecimalFormat("#.####");
                decimalFORMAT.setRoundingMode(RoundingMode.CEILING);

                for (String key : slices.keySet())
                {
                    double startAngle = slices.get(key).getStart_Angle();
                    if (counter == num) {
                        double x = Radius * Math.cos(Math.toRadians(startAngle));
                        double y = Radius * Math.sin(Math.toRadians(startAngle));
                        slices.get(key).draw(GC);
                        String letter = key + " = " + decimalFORMAT.format(slices.get(key).angleInRadians()/(2*Math.PI));
                        GC.setTextAlign(TextAlignment.LEFT);
                        GC.strokeText(letter, Center.getX() + x, Center.getY() - y);
                    }
                    else
                    {
                        double angle = 360.0 * slices.get(key).getAngle();
                        slices.get(key).draw(GC);
                        double x = Radius * Math.cos(Math.toRadians(startAngle));
                        double y = Radius * Math.sin(Math.toRadians(startAngle));
                        String letter = key + " = " + decimalFORMAT.format(slices.get(key).angleInRadians()/(2*Math.PI));
                        GC.setTextAlign(TextAlignment.RIGHT);
                        GC.strokeText(letter, Center.getX() + x, Center.getY() - y);
                    }
                }
        }

    }
}

