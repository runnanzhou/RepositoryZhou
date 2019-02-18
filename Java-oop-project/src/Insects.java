import java.util.List;

import javafx.scene.paint.Color;

public class Insects extends Animal {
    public boolean value; // the value of the insect is to mean
    // the direction of the insect. true when clockwise. vice versa
    public int[] coordinate;
    private int x;
    private int y;
    public static String type;
    private int count;
    private Boolean alive;
    private Color color;
    private String name;


    public Insects(String name, String gender, int[] coordinate,
            boolean value, String type) {
        super(name, gender, coordinate, Animal.row, Animal.col, type);
        this.value = value;
        this.name = name;
        this.color = null;
        this.coordinate = coordinate;
        this.x = coordinate[0];
        this.y = coordinate[1];

    }

    public String getName() {// return the name of the object
        return this.name;
    }
    public Color getColor() {
        if (name.substring(0, 1).equals("b")) {
            return Color.DARKCYAN;
        } else if (name.substring(0, 1).equals("f")) {
            return Color.DARKGOLDENROD;
        } else if (name.substring(0, 1).equals("h")) {
            return Color.DARKGRAY;
        } else {
            return Color.BEIGE;
        }
    }
    public void move(List[][] eco) {
        this.count += 1;
        if (this.count > 20) {
            alive = false;
            return;
        }

        if (value == true) {
            if (this.count == 2 || this.count == 7 || this.count == 8
                    || this.count == 16 || this.count == 17
                    || this.count == 18) {
                if (this.x != 0) {
                    this.x -= 1;
                } else {
                    this.x = row;
                }
            } else if (this.count == 1 || this.count == 5 || this.count == 6
                    || this.count == 13 || this.count == 14
                    || this.count == 15) {
                if (this.y != 0) {
                    this.y -= 1;
                } else {

                    this.y = col;
                }
            } else if (this.count == 3 || this.count == 9 || this.count == 10
                    || this.count == 19 || this.count == 20) {
                if (this.y != col) {
                    this.y += 1;
                } else {
                    this.y = 0;
                }
            } else if (this.count == 4 || this.count == 11
                    || this.count == 12) {

                if (this.x != row) {
                    this.x += 1;
                } else {
                    this.x = 0;
                }
            }
        } else {
            if (this.count == 1 || this.count == 5 || this.count == 6
                    || this.count == 13 || this.count == 14
                    || this.count == 15) {
                if (this.y != 0) {
                    this.y -= 1;
                } else {
                    this.y = col;
                }
            } else if (this.count == 2 || this.count == 7 || this.count == 8
                    || this.count == 16 || this.count == 17
                    || this.count == 18) {
                if (this.x != row) {
                    this.x += 1;
                } else {
                    this.x = 0;
                }
            } else if (this.count == 3 || this.count == 9 || this.count == 10
                    || this.count == 19 || this.count == 20) {
                
                if (this.y != col) {
                    this.y += 1;
                } else {
                    this.y = 0;
                }
            } else if (this.count == 4 || this.count == 11
                    || this.count == 12) {
                if (this.x != 0) {
                    this.x -= 1;
                } else {
                    this.x = row;
                }
            }
        }
        
        eco[x][y].add(this);

    }

    }

