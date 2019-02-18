import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Sculpture {
    private int delay;
    private List<String> clist;
    private Group root;

    public void start(Stage primaryStage) {

        TextField filename_field = new TextField();
        filename_field.setPrefWidth(200);
        root = new Group();
        JavaFXView window = new JavaFXView(1000, 1000);
        root.getChildren().add(filename_field);
        primaryStage.setTitle("Ecosystem");
        primaryStage.setScene(new Scene(root));
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
            readFile(text);
        }

        private TextField cmd_in;
    }

    public void readFile(String text) {
        Scanner in = null;
        List<List<String>> info = new ArrayList<List<String>>();
        for (int i = 0; i < 4; i++) {
            info.add(new ArrayList<String>());
        }
        try {
            in = new Scanner(new File(text));
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        while (in.hasNextLine()) {
            String file = in.nextLine();
            String[] factor = file.split(" ");
            if (factor[0].equals("delay:")) {
                delay = Integer.parseInt(factor[1]);
            } else if (factor[0].equals("input:")) {
                for (int i = 1; i < factor.length; i++) {
                    clist.add(factor[i]);
                }
            } else if (factor.length > 3) {

            }
        }
    }
}
