/*
 *
 * --------------------------------
 *   Ahmad Adil
 *   CSC 221
 *   Assignment 3
 *   4/12/2021
 * -------------------------------------
 *
 */




package sample;

/* Imports */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import javafx.stage.StageStyle;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.scene.control.TextInputDialog;
import java.util.Scanner;
import java.util.Optional;
import java.util.Map;
import java.util.NoSuchElementException;


public class Main extends Application {
    // This sets the boundary of the window size
    final int width = 1000;
    final int height = 600;

    // Main function to setup stage
    @Override
    public void start(Stage primaryStage) {
        try {

//========================================================================================
            /*This variable is for the number of inputs that are required for the
            * number of slices to be shown in the piechart*/
            int numberInput = 0;

            /*This function is the dialog popup that takes in the users input. Utilizes
            * javafx textinput class.
            * https://code.makery.ch/blog/javafx-dialogs-official/*/
            TextInputDialog popup = new TextInputDialog("");
            popup.setTitle("input");
            popup.setHeaderText("Enter Number of Events");
            popup.setContentText("Enter an integer");
            popup.initStyle(StageStyle.UTILITY);
            Optional<String> result = popup.showAndWait();
            if(result.isPresent())
            {
                numberInput = Integer.parseInt(result.get());
            }
            /*These variables take in the textfile from within local drive and store it within the program*/
            String fileName = "/Users/adil/Desktop/CCNY/Computer Science 22100/Emma.txt";
            Scanner fileInput;




//========================================================================================
            /*
             * This includes the title of the project along with the
             * Canvas class, that takes in the width and height and divides by 2
             * */
            primaryStage.setTitle("Ahmad Adil Assignment 3");
            Canvas canvas = new Canvas(width, height);
            GraphicsContext GC = canvas.getGraphicsContext2D();
            GC.setLineWidth(1);

            //center of canvas
            MyPoint center = new MyPoint(width /2.0, height /2.0);



//========================================================================================

            /*This is where the file is taken in by the program and replaces all uppercase letters
            * to a lower case.
            * Following that using the HistogramAlphaBet type, textInput and return the string in
            * the string builder.  */
            fileInput = new Scanner(Paths.get(fileName));
            StringBuilder F = new StringBuilder();
            while (fileInput.hasNext())
            {
                F.append(fileInput.nextLine().replaceAll("[^a-zA-Z]", "").toLowerCase());
            }


            HistogramAlphaBet textInput = new HistogramAlphaBet(F.toString());
            Map<String, Integer> sortedFrequency = textInput.sortFrequencyDown();


            // Here we print out the frequency of characters that appear
            sortedFrequency.forEach((X, V) -> System.out.println(X + ": " + V));

            /*This is the properties of the piechart that will be displayed
            * Taking in the number of inputs from the user and given a radius and starting angle of 0
            * to have it centered*/
            HistogramAlphaBet.MyPieChart P = textInput.new
                    MyPieChart(numberInput, center, 250, 0.0);


            Map<String, Slice> slices = P.getMyPieChart();
            slices.forEach((K, V) -> System.out.println("\n\n"+ K + ": " + slices.get(K)));

            double angle_Sum = 0.0;
            for (String key : slices.keySet())
            {
                angle_Sum += slices.get(key).getAngle();
            }
            System.out.println("\nSum of angles: " + angle_Sum);

            P.draw(GC);

            fileInput.close();



            // This prints out the sum of probabilities
            System.out.println("\n\n" + P.getProb());
            System.out.println( "\n\n"+ "Sum of probabilities: " + P.Getsum_Prob() + "\n");
//========================================================================================
            // set the stackPane and create the scene
            StackPane root = new StackPane(canvas);
            Scene scene = new Scene(root, width, height);
            primaryStage.setScene(scene);
            primaryStage.show();

        }
//========================================================================================
        // These catch exceptions are for if the file and or input is invalid
        catch (IOException ioException)
        {
            System.err.println("File not Found");
        }
        catch (NoSuchElementException elementException)
        {
            System.err.println("Invalid input revise your input");
        }

        catch (IllegalStateException stateException)
        {
            System.out.println("error in processing the file! ");
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
//========================================================================================
}

    public static void main(String[] args)
    {
        launch(args);
    }
}