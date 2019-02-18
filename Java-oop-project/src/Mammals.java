import java.util.List;

import javafx.scene.paint.Color;

public class Mammals extends Animal {
    // This is the mammal class.
    public String direction; // the direction of the animal
    public int reTime; // the count of the times of the object about reproduce
    public int[] coordinate;
    public int x;
    public int y;
    public static String type;
    private Boolean alive;
    private int count;
    private Color color;
    private String name;


    public Mammals(String name, String gender, int[] coordinate,
            String direction, String type) {
        // The constructor of the mammal class.
        super(name, gender, coordinate, row, col, type);
        this.name = name;
        this.direction = direction;
        this.coordinate = coordinate;
        this.reTime = 0;
        this.color = Color.GREEN;
        this.x = coordinate[0];
        this.y = coordinate[1];

    }

    public String getName() { // return the name of the object
        return this.name;
    }
    public Color getColor() {
        if (name.substring(0, 1).equals("e")) {
            return Color.AZURE;
        } else if (name.substring(0, 1).equals("r")) {
            return Color.AQUA;
        } else if (name.substring(0, 1).equals("c")) {
            return Color.BLACK;
        } else if (name.substring(0, 1).equals("l")) {
            return Color.RED;
        } else if (name.substring(0, 1).equals("g")) {
            return Color.CHOCOLATE;
        } else {
            return Color.CORAL;
        }
    }
    public String type() {
        return this.type();
    }

    public void move(List[][] eco) {
        // move method which make the mammal move.

        count += 1;
        if (count >= 100) {
            alive = false;
            return;
        }
        if (direction.equals("left")) {
            if (count % 2 != 0) {
                if (this.x != 0) {
                    this.x -= 1;
                } else {
                    this.x = row;
                }
            } else {
                if (this.y != 0) {
                    this.y -= 1;
                } else {
                    this.y = col;
                }
            }
        } else {
            if (count % 2 != 0) {
                if (this.x != row) {
                    this.x += 1;
                } else {
                    this.x = 0;
                }
            } else {
                if (this.y != col) {
                    this.y += 1;
                } else {
                    this.y = 0;
                }
            }
        }

        eco[this.x][this.y].add(this);

    }


}
