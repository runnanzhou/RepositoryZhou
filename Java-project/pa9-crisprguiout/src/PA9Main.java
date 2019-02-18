
/*
 * 
 * COURSE: CSC210; Fall 2018;
 * PA9 File: PA9Main.java
 * PURPOSE: This program is to use the GUI to interact with the user. User can 
 * input the information in a bucket and by clicking the process. They can 
 * get the thing they want to display. Same as the previous PA, it use the 
 * Ecosystem background. Different animals will appear on the screen.
 *  
 * Command Line Usage:
 * 10 10


*/
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class PA9Main extends Application {
    private static int row = 0; // the row of the screen
    private static int col = 0; // the col of the screen
    private final static int sizeCell = 25; // the size of the basic cell
    private static Ecosystem ecosystem;// the ecosystem object contain the
    // data organziation.
    private static Screen screen; // the screen object to display
    private static List[][] drawList; // the list used to draw
    private static GraphicsContext gc; // tool used to draw
    private int x; // the value used to store old coordinate
    private int y; // the value used to store old coordinate
    private List<Animal> object = new ArrayList<Animal>(); // the list
    private TextArea command; // textarea which display the input message.
    // to contain the animal object


    public static void main(String[] args) {
        // the concise summary of the total object
        // @parameter String[] args
        readArgs(args);
        launch(args);

    }

    public static void readArgs(String[] args) {
        // the method used to read the command line
        // parameter String[] args
        row = Integer.parseInt(args[0]);
        col = Integer.parseInt(args[1]);
    }
    @Override
    public void start(Stage primaryStage) {
        // the start method used to start the stage and set the rest things.
        // @parameter Stage primaryStage
        // ===== set up the scene with a text box and button for input
        BorderPane p = new BorderPane();
        ecosystem = new Ecosystem(row, col);
        TextField cmd_in = new TextField();
        Button button = new Button("Process");
        Canvas canvas = new Canvas(col * sizeCell, row * sizeCell);
        // the width and length of the canvas.
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGREEN);
        // set background color.
        gc.fillRect(0, 0, col * sizeCell, row * sizeCell);
        p.setCenter(canvas);
        command = new TextArea();
        command.setEditable(false);
        final int num_items = 2;
        HBox input_box = new HBox(num_items);
        VBox output_box = new VBox(3);
        output_box.getChildren().add(input_box);
        output_box.getChildren().add(command);
        output_box.getChildren().add(cmd_in);
        // Set up the stage.
        input_box.getChildren().add(cmd_in);
        input_box.getChildren().add(button);
        p.setBottom(output_box);

        // === Example of how to set up processing input from a text area
        button.setOnAction(new HandleTextInput(cmd_in));

        // Alternative: using an anonymous class
        // button.setOnAction(new EventHandler<ActionEvent>() {
        // @Override
        // public void handle(ActionEvent event) {
        // System.out.println(cmd_in.getText());
        // }
        // });

        // Alternative: use lambda function
        // button.setOnAction((event) -> {
        // System.out.println(cmd_in.getText());
        // });

        // Connect the border pane into the scene and show the window.
        primaryStage.setTitle("Ecosystem");
        primaryStage.setScene(new Scene(p));
        primaryStage.show();
    }

    class HandleTextInput implements EventHandler<ActionEvent> {
        // the class used to handle the click process movement.
        public HandleTextInput(TextField cmd_in) {
            this.cmd_in = cmd_in;

        }

        @Override
        public void handle(ActionEvent event) {
            // it is used to draw the graph and display the stuff to the
            // user.
            // @parameter ActionEvent event
            String text = cmd_in.getText();
            command.appendText(cmd_in.getText() + '\n');
            gc.clearRect(0, 0, row * sizeCell, col * sizeCell);
            int[] coordinate = { 0, 1 };
            Animal example = new Animal("example", "male", coordinate, row - 1,
                    col - 1,
                    "Animal");
            // give the number of row and col to the animal class.
            ecosystem.readLine(text, object);
            screen = new Screen(row, col, ecosystem);
            drawList = screen.get();
            initScreen(gc, drawList, text);
            this.cmd_in.setText("");
        }

        private TextField cmd_in;
    }

    public void initScreen(GraphicsContext gc, List[][] drawList, String text) {
        // this method is to initialize the screen. Including the eraser method
        // draw the background stuff and draw the animal.
        // @parameter GraphicsContext gc, List[][] drawList, String text
        gc.clearRect(0, 0, row * sizeCell, col * sizeCell);
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, col * sizeCell, row * sizeCell);
        for (int i = 0; i < drawList.length; i++) {
            for (int j = 0; j < drawList[0].length; j++) {
                if (drawList[i][j].equals(".")) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(j * sizeCell, i * sizeCell, sizeCell, sizeCell);
                } else {
                    DrawAnimal(gc, i, j, drawList, x, y);
                    // draw the animal.
                    x = i;
                    y = j;
                }
            }
        }
    }

    public void DrawAnimal(GraphicsContext gc, int i, int j,
            List[][] drawList, int x, int y) {
        // this method is to draw the animals with different colors of different
        // animals. it will find what types of animal first and give the special
        // color that the animal owns.
        // @parameter GraphicsContext gc, int i, int j,
        // List[][] drawList, int x, int y


        String[] mal = { "[e]", "[r]", "[l]", "[c]", "[g]", "[z]" };
        String[] bir = { "[t]", "[o]", "[w]", "[d]", "[s]" };
        String[] ins = { "[b]", "[f]", "[h]", "[a]" };
        List<String> malList = new ArrayList<String>();
        List<String> birList = new ArrayList<String>();
        List<String> insList = new ArrayList<String>();
        for (String a : mal) {
            malList.add(a);
        }
        for (String b : bir) {
            birList.add(b);
        }
        for (String c : ins) {
            insList.add(c);
        } // Build three animal list to distinguish different animal types.
        List[][] ecoList = ecosystem.getall();
        if (malList.contains(drawList[i][j].toString())) {
            Mammals mammal = (Mammals) ecoList[i][j].get(0);
            gc.setFill(mammal.getColor());
            gc.fillRect(j * sizeCell, i * sizeCell, sizeCell, sizeCell);
            // Draw the rectangle with the special color to the screen.
        } else if (birList.contains(drawList[i][j].toString())) {
            Birds bird = (Birds) ecoList[i][j].get(0);
            gc.setFill(bird.getColor());
            gc.fillRect(j * sizeCell, i * sizeCell, sizeCell, sizeCell);
            // Draw the rectangle with the special color to the screen.
        } else if (insList.contains(drawList[i][j].toString())) {
            Insects insect = (Insects) ecoList[i][j].get(0);
            gc.setFill(insect.getColor());
            gc.fillRect(j * sizeCell, i * sizeCell, sizeCell, sizeCell);
            // Draw the rectangle with the special color to the screen.
        } else if (drawList[i][j].toString().equals("[m]")) {
            Mosquitos mosquito = (Mosquitos) ecoList[i][j].get(0);
            gc.setFill(mosquito.getColor());
            gc.fillRect(j * sizeCell, i * sizeCell, sizeCell, sizeCell);
            // Draw the rectangle with the special color to the screen.
        }
    }

}
