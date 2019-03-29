import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import controller.ReversiController;
import model.Point;
import model.ReversiBoard;
import model.ReversiModel;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * This class will construct a Reversi game. It will allow player to click on the chessboard
 * to process a move, then the computer will move after. Once the game is finished, it will 
 * show if the player wins or loss.The implementation of the program is based 
 * on the database in the model and reversiboard class. The javafx make the 
 * data visible. The main idea is to revise the data base through the 
 * method and visualize it in the stage. It is the idea of the mvc design. By 
 * revising the view class, we make the gui and without revising much things
 * in the model class. It is the advantage of the mcv design. With the team 
 * work , we assign the job and deal with the difficulties together. We learn
 * a lot of things about cooperation through this team project. It is a really
 * nice experience.
 * 
 * @author Wen Zhu & Runnan Zhou
 *
 */
public class ReversiView extends Application implements Observer{

	private static final int TABLE_SIZE = 8;
	private static final int Insert = 8;
	private static final int Margin = 2;
	private boolean moved = false;
	private boolean end;
	private int statistics1, statistics2;
	private ReversiModel model = new ReversiModel();
	private Label statistic;

	/**
	 * This is the main method which will launch the command-line argument.
	 * @param args The command-line argument.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	/**
	 * This method is to initialize a stage which contains the following elements:
	 * 1. MenuBar: this is a drop down menu which contains a new game item.
	 * 2. TilePane: this is the chessboard that will be show to the player.
	 * 3. Label: this is to show the recent score of each player.
	 * 
	 * @param primaryStage The stage we need to initialize
	 */
	public void start(Stage primaryStage) {
		// Initialize the pane and the menu
		TilePane tp = new TilePane();
		statistic = new Label("White: 2 - Black: 2");
		VBox vbox = new VBox();
		MenuBar mBar = new MenuBar();
		Menu menu = new Menu("File");
		MenuItem newGame = new MenuItem("new game");
		// Load the data if it exist.
		ReversiBoard aboard = new ReversiBoard(model);
		aboard.isSave();
		model.setTable(aboard.getTable());
		String[][] table = model.getTable();
		end = false;
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override 
			public void handle(ActionEvent e) {
				gameInitial(tp, primaryStage);
			}
		});
		menu.getItems().add(newGame);
		mBar.getMenus().add(menu);
		// Drawing the chessboard on the TilePane.
		tp.setStyle("-fx-background-color:green");
		tp.setPrefColumns(TABLE_SIZE);
		tp.setPrefRows(TABLE_SIZE);
		StackPane root;
		Circle circle;
		tp.setPadding(new Insets(Insert, Insert, Insert, Insert));
		for(int i = 0; i < TABLE_SIZE; i ++) {
			for(int j = 0; j < TABLE_SIZE; j ++) {
				root = new StackPane();
				circle = new Circle(j*44, i*44, 20); 
				if (table[i][j].equals("W")){
					circle.setFill(Color.WHITE); 
				}else if (table[i][j].equals("B")){
					circle.setFill(Color.BLACK);
				}else {
					circle.setFill(Color.GREEN); 
				}
				root.setStyle("-fx-border-color:black");
				StackPane.setMargin(circle, new Insets(Margin, Margin, Margin, Margin));
				root.getChildren().add(circle);
				tp.getChildren().add(root);
			}
		}
		// Adding all of the elements to the vbox.
		vbox.getChildren().addAll(mBar, tp, statistic);

		// since we set up the primaryStage, we can process the game.
		gameProcess(tp, table, primaryStage);

		Scene scene = new Scene(vbox);
		primaryStage.resizableProperty().setValue(false);
		primaryStage.setTitle("Reversi");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	/**
	 * This method is to update the ReversiBoard every time we place a token
	 * on the board.
	 * 
	 * @param arg0 This one is null in this case.
	 * @param arg1 This is the ReversiBoard we get.
	 */
	public void update(Observable arg0, Object arg1) {
		String[][] table = ((ReversiBoard) arg1).getTable();
		for(int i = 0; i < TABLE_SIZE; i++) {
			for(int j = 0; j < TABLE_SIZE; j++) {
				System.out.print(table[i][j]+" ");
			}
			System.out.println();
		}
	}

	@Override
	@SuppressWarnings("resource")
	/**
	 * This method is to save the data once the window is closed.
	 * 
	 * @throws IOException This will be throw if we don't find the file.
	 */
	public void stop() throws IOException{
		// Save file
		FileOutputStream fileOut = new FileOutputStream(new File("save_game.dat"));
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		// Write objects to file
		objOut.writeObject(model.getTable());
	}

	/**
	 * This method will process the whole game, it will switch the turn from 
	 * player and computer.After that, it will check if the game is end or not 
	 * every time the player click. The implementation is based on the table 
	 * which is the data structure we choose to store the data. This represent
	 * the board of the reversi. Through change the things by click on the 
	 * tilepane, the data of the model will be changed. Thus, the gui will 
	 * draw a new graph every time the valid click has done.
	 * 
	 * @param tp The TilePane that we need to draw
	 * @param table The background table which is used to produce the TilePane
	 */
	private void gameProcess(TilePane tp, String[][] table, Stage primaryStage) {
		ReversiController controller = new ReversiController(model);
		HashSet<Point> computerPlaceablePositions = new HashSet<Point>();
		HashSet<Point> playerPlaceablePositions = new HashSet<Point>();
	    printStatistic();
		tp.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// The palyer's turn
				double sceneY =event.getSceneX();
				double sceneX =event.getSceneY();
				if ((sceneX > 8 && sceneX < 407) && (sceneY > 8 && sceneY < 376) && !end) {
					int x = (int)((sceneX-40)/46);
					int y = (int)((sceneY-8)/46);
					Point click = new Point(x, y);
					controller.getPlaceableLocations("W", "B", playerPlaceablePositions, model);
					for (Point p :playerPlaceablePositions) {
						if (p.equals(click)) {
							model.placeW(click.getRow(), click.getCol());
							controller.flipTheOpponent(p, "W", "B", model.getTable());
							moved = true;
						}
					}
				}
				printStatistic();
				redraw(table, tp);
				// Computer's turn
				if((moved || playerPlaceablePositions.isEmpty()) && !end) {
					moved = false;
					controller.getPlaceableLocations("B", "W", computerPlaceablePositions, model);
					Point temp = null;
					if(!computerPlaceablePositions.isEmpty()) {
						temp = computerPlace(computerPlaceablePositions, model, controller);
						computerTurn(temp, model, controller);
					}
				}
				// Show the scores of each player, and redraw the table with delay 0.5 second.

				PauseTransition wait = new PauseTransition(Duration.seconds(0.5));
				wait.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						printStatistic();
						redraw(table, tp);
					}
				});
				wait.play();

				// Check if the player or the computer wins, then clear the possible moves for
				// both players.
				if(playerPlaceablePositions.isEmpty() && computerPlaceablePositions.isEmpty()) {
					end = true;
					gameEnded(primaryStage);
				}else if(model.isFull()) {
					end = true;
					gameEnded(primaryStage);
				}
				
				



				
				playerPlaceablePositions.clear();
				computerPlaceablePositions.clear();
			}
		});
	}

	/**
	 * This method is to initial the game when we press the "new game" button.
	 * 
	 * @param tp Since we are initialing, we need to modify the old TilePane.
	 */
	private void gameInitial(TilePane tp, Stage primaryStage) {
		model = new ReversiModel();
		end = false;
		redraw(model.getTable(), tp);
		String[][] table = model.getTable();
		statistic.setText("White: 2 - Black: 2");
		gameProcess(tp, table, primaryStage);
	}

	/**
	 * This method will redraw the whole TilePane according to the table.
	 * 
	 * @param table The input table.
	 * @param tp This is the TilePane will be redrawn.
	 */
	private void redraw(String [][] table,TilePane tp) {
		for (int i = 0;i < TABLE_SIZE; i++) {
			for (int n = 0;n < TABLE_SIZE; n++) {
				setonmodel(i,n,tp,table[i][n]);
			}
		}
	}

	/**
	 * This method is to extract the StackPane from a specific position of the TilePane,
	 * then it will switch the circle to white or black.
	 * 
	 * @param x The x coordinate of the TilePane
	 * @param y The y coordinate of the TilePane
	 * @param tp The TilePane we need to modify
	 * @param user This will help us to switch the circle to white or black
	 */
	private void setonmodel(int x ,int y,TilePane tp,String user) {
		int index = (x*Insert+y);
		StackPane spane = (StackPane)tp.getChildren().get(index);
		Circle circle = (Circle) spane.getChildren().get(0);
		if (user.equals("W")){
			circle.setFill(Color.WHITE); 
		}else if (user.equals("B")){
			circle.setFill(Color.BLACK);
		}else {
			circle.setFill(Color.GREEN);
		}
	}

	/**
	 * This method will place a "B" at the board for the computer player.
	 * 
	 * @param placeablePositions The placeable positions for computer player.
	 * @param model The board of the game.
	 * @param controller The controller of the game.
	 * @return The point will be placed on the board.
	 */
	private static Point computerPlace(HashSet<Point> placeablePositions, ReversiModel model, ReversiController controller) {
		String[][] table = model.getTable();
		Iterator<Point> points = placeablePositions.iterator();
		int max = 0;
		int temp = 0;
		int row = 0;
		int col = 0;
		Point move = null;
		Point current = null;
		// Find the point that maximum the score.
		while(points.hasNext()) {
			current = points.next();
			row = current.getRow();
			col = current.getCol();
			table[row][col] = "B";
			controller.flipTheOpponent(current, "B", "W", table);
			temp = getSides("B", model);
			if(max < temp) {
				max = temp;
				move = current;
			}
			table = getTable(model);
		}
		return move;
	}

	/**
	 * This method represent the computer's turn.
	 * 
	 * @param placeablePositions All of the placeable positions that the computer player can place.
	 */
	private static void computerTurn(Point move, ReversiModel model, ReversiController controller) {
		// After we exit the loop, we place the point and flip other tokens.
		model.placeB(move.getRow(), move.getCol());
		controller.flipTheOpponent(move, "B", "W", model.getTable());
	}

	/**
	 * This method is to get the score of the side.
	 * 
	 * @param side The side that we need to get it's score.
	 * @return The score that the side get.
	 */
	private static int getSides(String side, ReversiModel model) {
		int count = 0;
		int size = model.getTableSize();
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(model.getTokenAt(i, j).equals(side)) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * This method will return the current table of the game.
	 *
	 * @param model The current table
	 * @return the current table.
	 */
	private static String[][] getTable(ReversiModel model){
		String[][] table = new String[8][8];
		for(int i = 0; i < TABLE_SIZE; i ++) {
			for(int j = 0; j < TABLE_SIZE; j ++) {
				table[i][j] = model.getTokenAt(i,j);
			}
		}
		return table;
	}
	/*
	 * This method is used or called when the game is over and pop up a new
	 * window and it will tell you whether you win or not.There is a button
	 * on the window and if you press it what you expect can be the closing of
	 * the window. The window will block you from any other things. 
	 * @parameter: primaryStage stage
	 */
	private void gameEnded(Stage primaryStage) {
		  final Stage dialog = new Stage();
		  dialog.setTitle("Message");
		     //Create Labels, then add graphic to it
		        Label lossLabel = new Label();
		        lossLabel.setGraphic(new ImageView(new Image("https://is5-ssl.mzstatic.com/image/thumb/Purple117/v4/10/7f/cc/107fcccf-7a4a-2695-6982-e02ef528ac90/mzl.erapelfi.jpg/320x0w.jpg")));
		        Label winLabel = new Label();
		        winLabel.setGraphic(new ImageView(new Image("https://obamadiary.files.wordpress.com/2014/06/victory.jpg")));

		  dialog.initModality(Modality.APPLICATION_MODAL);
		  dialog.initOwner(primaryStage);
		  VBox dialogVbox = new VBox(20);
		  Button okbtn =new Button("OK");
		  okbtn.setOnAction(new EventHandler<ActionEvent>(){
		   @Override
		   public void handle(ActionEvent event) {
		    dialog.close();
		   }
		  });

		  boolean win = (statistics1>statistics2);
		  if (win==true) {
		   dialogVbox.getChildren().addAll(winLabel, new Text("You win!"), okbtn);
		  }else {
		   dialogVbox.getChildren().addAll(lossLabel, new Text("You loss!"), okbtn);
		  }
		  Scene dialogScene = new Scene(dialogVbox);
		  dialog.setScene(dialogScene);
		  dialog.resizableProperty().setValue(false);
		  dialog.showAndWait();
		 }
	/*This is the function to print the statistic information at the bottom
	 * of the window. It will change with every single valid move being 
	 * made.
	 * 
	 */
		private void printStatistic() {
		  statistics1 = getSides("W", model);
		  statistics2 = getSides("B", model);
		  statistic.setText("White: "+statistics1+" - Black: "+statistics2);
		 }
}
