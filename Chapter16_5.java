/********************************************************************************************************************
 ********************************************************************************************************************
 *****                                         Chapter 16: Problem 5                                            *****
 *****__________________________________________________________________________________________________________*****
 *****                 5.  Create a GUI with a TextArea that is initially blank and 4 Buttons.                  *****
 *****                          The Buttons toggle whether the area is editable or not,                         *****
 *****                          save the text to a file, clear the text area, and quit.                         *****
 *****   The toggle button should display "Editable" when editable and "Not editable" when it is not editable.  *****
 *****                    For this, you will have to use button.setText to adjust the text                      *****
 *****                         based on whether the area is currently editable or not.                          *****
 *****        Use a boolean variable to store whether the area is editable or not, initialized to true.         *****
 *****          The second button will get the text in the text area and output it to a PrintWriter.            *****
 *****                           You will need to use a try-catch block for this code.                          *****
 *****                      When doing clear, just set the text area object's text to "".                       *****
 *****                               The "Quit" button just does System.exit(0);                                *****
 *****----------------------------------------------------------------------------------------------------------*****
 *****   NOTE:  it would be best to handle the second button in its own handler that is a nested inner class.   *****
 *****                  Remember to import java.io.* for PrintWriter and IOException handling.                  *****
 ********************************************************************************************************************
 ********************************************************************************************************************/

// IMPORTS of needed tools and plus-ins
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;


public class Chapter16_5 extends Application {

    // CLASS VARIABLE(s) declaration(s)
    private TextArea textArea;
    private Button btn1, btn2, btn3, btn4;
    private PrintWriter printWriter;
    private boolean isEditable = true;
    private String textAreaText = "";
    private String outputFileName;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage){

        // Initialize the TextArea and Set its Width and Height
        textArea = new TextArea(textAreaText);
        textArea.setPrefWidth(300);
        textArea.setPrefHeight(200);

        // Create and Initialize the Pane for the TextArea, Add the TextArea to the Pane and
            // Set the Pane's Width and Height
        Pane pane1 = new Pane();
        pane1.getChildren().add(textArea);
        pane1.setPrefWidth(300);
        pane1.setPrefHeight(200);

        // Initialize the Buttons with their initial text and Set their Width and Height
        btn1 = new Button("Editable");
        btn1.setPrefWidth(100);
        btn1.setPrefHeight(40);

        btn2 = new Button("Save");
        btn2.setPrefWidth(100);
        btn2.setPrefHeight(40);

        btn3 = new Button("Clear");
        btn3.setPrefWidth(100);
        btn3.setPrefHeight(40);

        btn4 = new Button("Quit");
        btn4.setPrefWidth(100);
        btn4.setPrefHeight(40);

        // Create and Initialize the FlowPane for the Buttons, Set its Orientation to VERTICAL, Add the Buttons and
            // Set the Vgap
        FlowPane pane2 = new FlowPane();
        pane2.setOrientation(Orientation.VERTICAL);
        pane2.getChildren().addAll(btn1, btn2, btn3, btn4);
        pane2.setVgap(20);

        // Create and Initialize the GridPane that will serve as the main Pane, Add the other Pane and FlowPane to it,
            // Set where the other Pane and FlowPane go in the GridPane and Set the Hgap
        GridPane mainPane = new GridPane();
        mainPane.add(pane1, 0, 0);
        mainPane.add(pane2, 1, 0);
        mainPane.setHgap(50);

        // Nested Inner Class to handle the ActionEvent EventHandler for Button btn2,
            // which Gets the String text from the TextArea and uses the PrintWriter to write the String text
            // to a File
        EventHandler<ActionEvent> handler = e -> {

            // Try block to handle the ActionEvent EventHandler for Button btn2 and the PrintWriter
            try {
                // Initialize and Set the File name to a String variable
                outputFileName = "Chapter16_5.txt";

                // Initialize the PrintWriter
                printWriter = new PrintWriter(outputFileName);

                // Get the String text from the TextArea and save it to the String variable textAreaText
                textAreaText = textArea.getText();

                // Output String text to the output File using the PrintWriter
                printWriter.println(textAreaText);
            }
            // Catch block to handle if there is an IOException when trying to use the PrintWriter
            catch (IOException iOExc){
                System.out.println(iOExc);
            }
            // Finally block to Close the PrintWriter
            finally {
                if(printWriter != null){
                    printWriter.close();
                }
            }
        };

        // ActionEvent EventHandler for Button btn1 (Set whether the TextArea is Editable or not and
            // change the Button text to reflect that
        btn1.setOnAction(e -> {
            if(textArea.isEditable()){
                btn1.setText("Not Editable");
                isEditable = false;
                textArea.setEditable(isEditable);
            } else{
                btn1.setText("Editable");
                isEditable = true;
                textArea.setEditable(isEditable);
            }
        });

        // ActionEvent EventHandler for Button btn2 (uses a Nested Inner Class to Get the String text from
            // the TextArea and a PrintWriter to write that String text to a File)
        btn2.setOnAction(handler);

        // ActionEvent EventHandler for Button btn3 (Clear the TextArea)
        btn3.setOnAction(e -> {
            textArea.setText("");
        });

        // ActionEvent EventHandler for Button btn4 (Quit the program)
        btn4.setOnAction(e -> {
            System.exit(0);
        });

        // Create and Initialize the Scene, Set the Title of the Stage, Set the Scene to the Stage and Show the Stage
        Scene scene = new Scene(mainPane, 450, 250);
        primaryStage.setTitle("Chapter 16: Problem 5");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
