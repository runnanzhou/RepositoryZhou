import java.util.List;

import javafx.scene.paint.Color;

public class Birds extends Animal {
    // bird class of animal. which is the subclass of the Animal class.
    public int number; //the number of the bird. which is a feature
    private int x; //coordiante of the bird
    private int y; //coordiante of the bird
    private int count;
    private Boolean alive;
    public static String type;
    private Color color;
    private String name;


    public Birds(String name, String gender, int[] coordinate, int number,
            String type) {
        // The constructor of the bird class.
        super(name, gender, coordinate, row, col, type);
        this.number = number;
        this.color = Color.RED;
        this.name = name;
        this.x = coordinate[0];
        this.y = coordinate[1];
    }

    public Color getColor() {
        // return the color of the specific animal.
        if (name.substring(0, 1).equals("t")) {
            return Color.FIREBRICK;
        }
        if (name.substring(0, 1).equals("o")) {
            return Color.GAINSBORO;
        }
        if (name.substring(0, 1).equals("w")) {
            return Color.HOTPINK;
        }
        if (name.substring(0, 1).equals("d")) {
            return Color.NAVAJOWHITE;
        } else {
            // the other case of the bird animal
            return Color.DARKORANGE;
        }
    }

    public String getName() {// return the name of the object
        return this.name;
    }
    public int[] getC() {
        // return the coordinate
        return coordinate;
    }

    public int getCount() {
        // return the count of the object
        return count;
    }
    public void move(List[][] eco) {
        // move the object.
        count += 1;
        if (count >= 50) {
            alive = false;
            return;
        }
        // do not move the object when it is not alive
        int rest = count % (3 * this.number);


        if (rest > 0 && rest <= this.number) {

            if (this.x != row) {
                this.x += 1;
            } else {
                this.x = 0;
            }

        } else if (rest >= this.number && rest <= 2 * this.number) {

            if (this.y != col) {
                this.y += 1;
            } else {
                this.y = 0;
            }

        } else if (rest >= 2 * this.number && rest < 3 * this.number
                || rest == 0) {

            if (this.x != 0) {
                this.x -= 1;
            } else {
                this.x = row;
            }

        }


        eco[x][y].add(this);
        // add the object to the screen list.
        }
    }

