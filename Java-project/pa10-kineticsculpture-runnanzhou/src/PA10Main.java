import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * 
 * COURSE: CSC210; Fall 2018;
 * PA10 File: PA10Main.java
 * PURPOSE: This program is to accept a user input at first and read in the 
 * file by the program. Then creat a stage that include several node which can
 * be divided to input node, passthrough node, and sink node. The circle in 
 * the list will be used one by one to transite into another node.  What user
 * can expect is to see the circle travel through the edge and passed to another
 * node.
 *  
 * Example inpput: delay: 1
input: RED, BLUE, YELLOW, GREEN, PURPLE, PINK, BLACK
0: input, (20,20)
1: passthrough, (70,40)
2: passthrough, (80,100)
3: passthrough, (80, 200)
4: passthrough, (140, 100)
5: passthrough, (140, 200)
6: sink, (600,150)
0 -> 1
0 -> 2
0 -> 3
1 -> 5
2 -> 4
3 -> 4
4 -> 6
5 -> 6


*/
public class PA10Main extends Application {

    public static double delay; // delay of the program
    public static ArrayList<String> colors; // list of colors
    public static int num_colors; // number of colors.
    private static Button button; // button.
    private static TextField cmd_in; // text filed
    public final static int size = 600; // constant of size


    public static void main(String[] args) {
        // the concise summary of the main.
        cmd_in = new TextField();
        colors = new ArrayList<>();
        launch(args);
    }

    public void start(Stage stage1) throws FileNotFoundException {
        /*
         * The start of the stage.
         * 
         * @Parameter: Stage stage1
         */
        PauseTransition wait = new PauseTransition(Duration.seconds(delay));
        setupStage(stage1);
        stage1.setScene(new Scene(JavaFXView.root));
        stage1.show();
        button.setOnAction((event) -> {
            try {
                readIn(cmd_in.getText());
                process();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void readIn(String input) throws FileNotFoundException {
        /*
         * read in the input and organize the data.
         * 
         * @parameter: String input
         */
        Scanner file = new Scanner(new File(input));
        delay = Double
                .parseDouble(file.nextLine().substring(7).replace(" ", ""));
        colors = getColors(file.nextLine());
        // Read over the file, and store the data.
        while (file.hasNextLine()) {
            String line = file.nextLine();
            if (line.contains(",")) {
                Graph.addNode(line);
            } else if (line.contains(">")) {
                Graph.addEdge(line);
            }
        }

    }

    private void process() {
        //This method is to process when the color list is not empty, then
        //take the first color to draw.
        PauseTransition wait = new PauseTransition(Duration.seconds(delay));
        List<Integer> start = new ArrayList<>();
        start.add(Graph.startNodeId);
        wait.setOnFinished((ActionEvent e) -> {
            if (!colors.isEmpty()) {
                draw(new ArrayList<>(), start, colors.get(0));
                colors.remove(0);
                wait.playFromStart();
            } else {
                wait.stop();
            }
        });
        wait.play();
    }


    private ArrayList<String> getColors(String command) {
        //This method is to get the color from the input file.
        // @parameter:String command
        ArrayList<String> colors = new ArrayList<>();
        String line = command.replace(":", ",");
        String[] factor = line.split(",");
        for (int i = 1; i < factor.length; i++) {
            colors.add(factor[i]);
        }
        return colors;
    }


    private void draw(List<Integer> past, List<Integer> start, String color) {
        // This method is to clear the past edge and draw the current edge.
        // @Parameter:List<Integer> past, List<Integer> start, String color
        PauseTransition wait = new PauseTransition(Duration.seconds(delay));
        wait.setOnFinished((ActionEvent e) -> {
            // delete the previous edge
            for (int i = 0; i < past.size(); i++) {
                if (Graph.neighbor.get(past.get(i)) != null) {
                    clearEdge(past, Graph.neighbor.get(past.get(i)), i);
                }
            }
            // draw the current edge
            List<Integer> next = new ArrayList<>();
            for (int i = 0; i < start.size(); i++) {
                if (Graph.neighbor.get(start.get(i)) != null) {
                    transition(start, color, Graph.neighbor.get(start.get(i)),
                            next, i);
                }
            }
            draw(start, next, color);
        });
        wait.play();
    }

    private void transition(List<Integer> start, String color, String targets,
            List<Integer> next, int i) {
        // This method is to do the edgeTransition for every cricle in the list
        // @Parameter:List<Integer> start, String color, String targets,
        // List<Integer> next, int i
        String[] current = targets.split(" ");
        for (int j = 0; j < current.length; j++) {
            next.add(Integer.parseInt(current[j]));
            JavaFXView.edgeTransition(start.get(i),
                    Integer.parseInt(current[j]), color, delay);
        }
    }

    private void clearEdge(List<Integer> past, String targets, int i) {
        // This method is to clear the past edge
        // @Parameter: List<Integer> past, String targets, int i
        String[] current = targets.split(" ");
        for (int j = 0; j < current.length; j++) {
            JavaFXView.clearEdge(past.get(i), Integer.valueOf(current[j]));
        }
    }

    private void setupStage(Stage stage1) {
        // This method is to set up the stage
        // @Parameter: Stage stage1
        BorderPane border = new BorderPane();
        TextField filename_field = new TextField();
        filename_field.setPrefWidth(200);
        JavaFXView view = new JavaFXView(size + 200, size);
        Label cmd = new Label("Type in the file path--->");
        HBox input_box = new HBox(3);
        setupBox(input_box, cmd);
        stage1.setTitle("Sculpture");
        stage1.setScene(new Scene(border));
    }


    private void setupBox(HBox input_box, Label cmd) {
        // This method is to set up the box
        // @Parameter: HBox input_box, Label cmd
        button = new Button("Go!");
        input_box.setSpacing(40);
        input_box.getChildren().add(cmd);
        input_box.getChildren().add(cmd_in);
        input_box.getChildren().add(button);
        JavaFXView.root.getChildren().add(input_box);
    }
}