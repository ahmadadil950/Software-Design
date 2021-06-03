/*
 *
 * --------------------------------
 *   Ahmad Adil
 *   CSC 221
 *   Assignment 4
 *   5/4/2021
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


public class Main extends Application {
    // This sets the boundary of the window size
    final int width = 1000;
    final int height = 600;

    // Main function to setup stage
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            StackPane root = new StackPane();
            Scene scene = new Scene(root);
            final Canvas canvas = new Canvas(1200, 800);
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

            Piechart PC = new Piechart();
            PC.draw(graphicsContext);

            root.getChildren().add(canvas);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


//========================================================================================


//========================================================================================


    public static void main(String[] args)
    {

        Database.connectToDatabase();
        Database.clearPreviousTables(); // if previous tables exist, delete them
        Database.createTables();

        try
        {
            Database.parseData();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


        Database.insertIntoStudents(2357400, "Linus", "Sebastian",
                "LSebastian000@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357401, "Mark", "Hamill",
                "MHamill000@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357402, "Ismail", "Abe",
                "iabe000@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357403, "Kyle", "Jill",
                "khill000@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357404, "Sam", "smith",
                "ssmith002@citymail.cuny.edu", 'F');
        Database.insertIntoStudents(2357405, "Sarah", "Conner",
                "sconner008@citymail.cuny.edu", 'F');
        Database.insertIntoStudents(2357406, "Jensen", "Huang",
                "jhuang002@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357407, "Jim", "Keller",
                "jkeller002@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357408, "Ahmad", "Adil",
                "aadil002@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357409, "Mary", "Su",
                "mSu020@citymail.cuny.edu", 'F');
        Database.insertIntoStudents(2357410, "Nicholas", "cage",
                "ncage009@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357411, "bill", "joe",
                "bjoe002@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357412, "Ben", "Skywalker",
                "bskywalker001@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357413, "Donald", "Trump",
                "dtrump000@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357414, "Cory", "Bill",
                "cbill007@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357415, "Clark", "Kent",
                "ckent008@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357416, "Ben", "Kari",
                "bkari000@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357417, "Mariam", "Hassan",
                "mhassan010@citymail.cuny.edu", 'F');
        Database.insertIntoStudents(2357418, "Wei", "Kuan",
                "wkuan003@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357419, "Maya", "Laban",
                "mlaban002@citymail.cuny.edu", 'F');
        Database.insertIntoStudents(2357420, "Miles", "Morales",
                "mmorales005@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357421, "Nelson", "link",
                "nlink004@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357422, "Eric", "Swan",
                "engansop000@citymail.cuny.edu", 'M');
        Database.insertIntoStudents(2357423, "Fnu", "Paln",
                "fpalak000@citymail.cuny.edu", 'F');
        Database.insertIntoStudents(2357424, "Son", "Goku",
                "sgoku005@citymail.cuny.edu", 'M');


        Database.insertIntoCourses("CSC 30400", "Software Design Laboratory (Lecture)",
                "CSC");

        Database.insertIntoClasses("CSC 30400", 2357400, "S", 2021,
                "Spring", 'A');
        Database.insertIntoClasses("CSC 30400", 2357401, "S", 2021,
                "Spring", 'B');
        Database.insertIntoClasses("CSC 30400", 2357402, "S", 2021,
                "Spring", 'C');
        Database.insertIntoClasses("CSC 30400", 2357403, "S", 2021,
                "Spring", 'D');
        Database.insertIntoClasses("CSC 30400", 2357404, "S", 2021,
                "Spring", 'F');
        Database.insertIntoClasses("CSC 30400", 2357405, "S", 2021,
                "Spring", 'D');
        Database.insertIntoClasses("CSC 30400", 2357406, "S", 2021,
                "Spring", 'A');
        Database.insertIntoClasses("CSC 30400", 2357407, "S", 2021,
                "Spring", 'B');
        Database.insertIntoClasses("CSC 30400", 2357408, "S", 2021,
                "Spring", 'C');
        Database.insertIntoClasses("CSC 30400", 2357409, "S", 2021,
                "Spring", 'D');
        Database.insertIntoClasses("CSC 30400", 2357410, "S", 2021,
                "Spring", 'F');
        Database.insertIntoClasses("CSC 30400", 2357411, "S", 2021,
                "Spring", 'W');
        Database.insertIntoClasses("CSC 30400", 2357412, "S", 2021,
                "Spring", 'A');
        Database.insertIntoClasses("CSC 30400", 2357413, "S", 2021,
                "Spring", 'B');
        Database.insertIntoClasses("CSC 30400", 2357414, "S", 2021,
                "Spring", 'C');
        Database.insertIntoClasses("CSC 30400", 2357415, "S", 2021,
                "Spring", 'B');
        Database.insertIntoClasses("CSC 30400", 2357416, "S", 2021,
                "Spring", 'B');
        Database.insertIntoClasses("CSC 30400", 2357417, "S", 2021,
                "Spring", 'C');
        Database.insertIntoClasses("CSC 30400", 2357418, "S", 2021,
                "Spring", 'D');
        Database.insertIntoClasses("CSC 30400", 2357419, "S", 2021,
                "Spring", 'F');
        Database.insertIntoClasses("CSC 30400", 2357420, "S", 2021,
                "Spring", 'W');
        Database.insertIntoClasses("CSC 30400", 2357421, "S", 2021,
                "Spring", 'A');
        Database.insertIntoClasses("CSC 30400", 2357422, "S", 2021,
                "Spring", 'B');
        Database.insertIntoClasses("CSC 30400", 2357423, "S", 2021,
                "Spring", 'C');
        Database.insertIntoClasses("CSC 30400", 2357424, "S", 2021,
                "Spring", 'W');

        Database.showTables();

        Database.table_Enrolled_InCSC30400();

        launch(args);

        Database.closeDatabase();
    }
}