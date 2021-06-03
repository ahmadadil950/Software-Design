/* Database holds and stores all of classes need to implement within the SQL database
*  This includes classes that will modify, store, and search through the data.
*  Database also connects with the external Database as well
* */


package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static Connection connection;
    private static Map<String, String[]> courses = new HashMap<>();
    private static Map<String, String[]> classes = new HashMap<>();



    //========================================================================================
    // Connects to the external database
    public static void connectToDatabase()
    {
        connection = null;
        try {

            // attempt to establish a connection to the specified database via the DriverManager
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/project4",
                    "postgres", "babugan12");
            if (connection != null) { // check the connection
                System.out.println("Successfully connected to the Database!");
            }
        } catch (Exception e)
        {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //========================================================================================


    //========================================================================================
    // Searches through the data given
    public static void parseData() throws IOException {
        List<String> content = Files.readAllLines(Paths.get(
                "/Users/adil/Desktop/CCNY/Computer Science 22100/scheduleSpring2021.txt"));
        for(int i = 1; i < content.size(); i++){
            String[] s = content.get(i).split("\t");

            String[] course_1 = new String[] {s[2], s[6]};
            courses.put(s[0], course_1);

            String[] class_array = new String[]{s[1], s[4], s[3]};
            classes.put(s[0], class_array);
        }

        for(Map.Entry<String, String[]> entry : classes.entrySet()){
            System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
        }
        addCourseThroughTextFile();
    }
    //========================================================================================


    //========================================================================================
    public static void addCourseThroughTextFile(){
        try {
            String SQL_Command = "INSERT INTO Courses " +
                    "(courseID, courseTitle, department) VALUES (?, ?, ?)";
            PreparedStatement c = connection.prepareStatement(SQL_Command);
            for (Map.Entry<String, String[]> entry : courses.entrySet()){
                c.setString(1, entry.getKey());
                String[] array = entry.getValue();
                c.setString(2, array[0]);
                c.setString(3, array[1]);
                c.addBatch();
            }
            c.executeBatch();
        } 
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //========================================================================================


    //========================================================================================
    /*create the tables for each item and stores them*/
    public static void createTables() {
        try {
            if (connection != null) {
                PreparedStatement statement_1 = connection.prepareStatement("CREATE TABLE Students " +
                        " (studentID INT NOT NULL, " +
                        " firstName VARCHAR(255), " +
                        " lastName VARCHAR(255), " +
                        " email VARCHAR(255), " +
                        " gender CHAR(1), " +
                        " PRIMARY KEY (studentID)," +
                        " CHECK (gender = 'U' OR gender = 'F' OR gender = 'M'))");;
                statement_1.execute();

                PreparedStatement statement_2 = connection.prepareStatement("CREATE TABLE Courses " +
                        " (courseID CHAR(10) NOT NULL, " +
                        " courseTitle VARCHAR(255), " +
                        " department CHAR(20), " +
                        " PRIMARY KEY (courseID))");
                statement_2.execute();

                PreparedStatement statement_3 = connection.prepareStatement("CREATE TABLE Classes " +
                        " (courseID CHAR(10) NOT NULL, " +
                        " studentID INT NOT NULL, " +
                        " section VARCHAR(255) NOT NULL, " +
                        " year INT, " +
                        " semester VARCHAR(6), " +
                        " grade CHAR(1), " +
                        " PRIMARY KEY (courseID, studentID, section)," +
                        " CHECK (grade = 'A' OR grade = 'B' OR grade = 'C' OR grade = 'D' OR grade = " +
                        "'F' OR grade = 'W'))");
                statement_3.execute();

                Database.showTables();
            }
        }  catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //========================================================================================

    //========================================================================================
    // if previous tables are present, this class will remove them
    public static void clearPreviousTables() {
        try {
            if (connection != null) {
                PreparedStatement statement_1 = connection.prepareStatement("DROP TABLE Students");
                statement_1.execute();

                PreparedStatement statement_2 = connection.prepareStatement("DROP TABLE Courses");
                statement_2.execute();

                PreparedStatement statement_3 = connection.prepareStatement("DROP TABLE Classes");
                statement_3.execute();

            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //========================================================================================

    //========================================================================================
    // inserts info into Students table
    public static void insertIntoStudents(int studentID, String firstName,
                                          String lastName, String email, char gender)
    {
        try 
        {
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO Students(studentID, firstName, lastName, email, gender)"
                    + "VALUES('"+studentID+"', '"+firstName+"', '"+lastName+"','"+email+"', '"+gender+"')");
            statement.executeUpdate();
        } 
        
        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //========================================================================================

    //========================================================================================
    // insert info into Courses table
    public static void insertIntoCourses(String courseID, String courseTitle, String department) {
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO Courses (courseID, courseTitle, department) VALUES('" +
                                    courseID + "', '" + courseTitle + "', '" + department + "')",
                            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate();
        }

        catch (SQLException e)
        {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //========================================================================================

    //========================================================================================
    // insert info into Classes table
    public static void insertIntoClasses(String courseID, int studentID,
                                         String section,int year,String semester,char grade) {
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO Classes(courseID, studentID, section, year, " +
                            "semester, grade) VALUES('" +
                            courseID + "', " + studentID + ", '" + section + "', " +
                            year + ", '" + semester + "', '" +
                            grade + "')", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //========================================================================================

    //========================================================================================
    // This class returns # of students enrolled
    public static void table_Enrolled_InCSC30400()
    {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT grade, " +
                    "COUNT(studentID) FROM Classes " +
                    "WHERE courseID = 'CSC 30400' AND year = 2021 AND semester = 'Spring' GROUP BY grade");
            ResultSet rSet = statement.executeQuery();
            Database.showResults("Classes", rSet);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

    }
    //========================================================================================

    //========================================================================================
    //HashMap with the # of students enrolled for each letter grade
    public static HashMap<Character, Integer> studAndGrades() {
        HashMap<Character, Integer> HASH_M = new HashMap<>(6);
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT grade FROM Classes");
            ResultSet res_Set = statement.executeQuery();
            String allGrades = "";
            while (res_Set.next()) {
                String row = res_Set.getString("grade");
                if (row != null) {
                    allGrades += row + "";
                }
            }
            for (int i = 0; i < allGrades.length(); i++) {
                char current_Grade = allGrades.charAt(i);
                if (!HASH_M.containsKey(current_Grade)) {
                    HASH_M.put(current_Grade, 1);
                } else {
                    int oldFreq = HASH_M.get(current_Grade);
                    HASH_M.replace(current_Grade, ++oldFreq);
                }
            }
        }  catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return HASH_M;
    }
    //========================================================================================

    //========================================================================================
    public static void showTables()
    {
        Database.showValues(connection);
    }
    //========================================================================================

    //========================================================================================
    public static void showValues(Connection connect) {
        try {
            PreparedStatement state_1 = connect.prepareStatement("SELECT * FROM Students");
            ResultSet res_set1 = state_1.executeQuery();
            Database.showResults("Students", res_set1);

            PreparedStatement state_2 = connect.prepareStatement("SELECT * FROM Courses");
            ResultSet res_set2 = state_2.executeQuery();
            Database.showResults("Courses", res_set2);

            PreparedStatement state_3 = connect.prepareStatement("SELECT * FROM Classes");
            ResultSet res_set3 = state_3.executeQuery();
            Database.showResults("Classes", res_set3);
        }
        catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //========================================================================================

    //========================================================================================
    public static void showResults(String tableName, ResultSet res_Set) {
        try {
            ResultSetMetaData RSMD = res_Set.getMetaData();
            int numColumns = RSMD.getColumnCount();
            String resultString = null;
            if (numColumns > 0) {
                resultString = "\nTable: " + tableName + "\n" +
                        "=======================================================\n";
                for (int colNum = 1; colNum <= numColumns; colNum++)
                    resultString += RSMD.getColumnLabel(colNum) + " ";
            }
            System.out.println(resultString);
            System.out.println(
                    "=======================================================");
            while (res_Set.next()) {
                resultString = "";
                for (int colNum = 1; colNum <= numColumns; colNum++) {
                    String column = res_Set.getString(colNum);
                    if (column != null)
                        resultString += column + " ";
                }
                System.out.println(resultString + '\n' +
                        "------------------------------------------------------------");
            }
        }  catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //========================================================================================

    //========================================================================================
    // Closes the database
    public static void closeDatabase()
    {
        try {
            if (connection != null) {
                connection.close();
            }
        }  catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //========================================================================================

}