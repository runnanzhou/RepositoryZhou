import javafx.scene.paint.Color;

public class Mosquitos extends Insects {
    // specific class inherited from insects.
    public boolean value2; // the value determine the reproduce
    public boolean value3;// the value determine the reproduce
    public static String type;
    private int count;

    public Mosquitos(String name, String gender, int[] coordinate, String type,
            boolean value, boolean value2, boolean value3) {
        // this is the constructor of the class
        super(name, gender, coordinate, value, type);
        this.value2 = value2;
        this.value3 = value3;
        Mosquitos.type = "mosquitos";
    }

    public Color getColor() {// return the color of the object
        return Color.BLACK;
    }
}
