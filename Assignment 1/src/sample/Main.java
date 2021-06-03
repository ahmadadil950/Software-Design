package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    final int height = 1000;
    final int width = 600;

    @Override
    public void start(Stage primaryStage) {
        try {

            primaryStage.setTitle("Assignment 1");
            Canvas canvas = new Canvas(width, height);
            GraphicsContext GC = canvas.getGraphicsContext2D();


            MyPolygon Poly1 = new MyPolygon(width / 2, height / 2, MyColor.HOTPINK, 200, 6);
            Poly1.draw(GC);
            MyCircle Circle1 = new MyCircle(width / 2, height / 2, Poly1.getApo(), MyColor.GREEN);
            Circle1.draw(GC);
            MyPolygon Poly2 = new MyPolygon(width / 2, height / 2, MyColor.BLUE, Circle1.getRadius(), 6);
            Poly2.draw(GC);
            MyCircle Circle2 = new MyCircle(width / 2, height / 2, Poly2.getApo(), MyColor.MAGENTA);
            Circle2.draw(GC);
            MyPolygon Poly3 = new MyPolygon(width / 2, height / 2, MyColor.RED, Circle2.getRadius(), 6);
            Poly3.draw(GC);


            System.out.println(Poly1 + "\n\n" + Circle1 + "\n\n" + Poly2 + "\n\n" + Circle2 + "\n\n" + Poly3 + "\n\n");

            MyLine L1 = new MyLine(0, 0, height, width, MyColor.BLACK);
            MyLine L2 = new MyLine(0, height, width, 0, MyColor.BLUE);
            L1.draw(GC);
            L2.draw(GC);
            GC.strokeRect(0,0,width,height);
            System.out.println(L1 + "\n\n" + L2 + "\n\n");



            StackPane root = new StackPane(canvas);
            Scene s = new Scene(root,width,height);
            primaryStage.setScene(s);
            primaryStage.show();
            GC.setLineWidth(4);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
