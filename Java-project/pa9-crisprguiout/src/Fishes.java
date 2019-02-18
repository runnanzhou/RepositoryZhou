
public class Fishes extends Animal {
    // This is the class which designed by myself.
    public boolean value;
    private Boolean alive;
    private int count;

    public Fishes(String name, String gender, int[] coordinate, int row,
            int col, boolean value) {
        // this is the constructor of the class.
        super(name, gender, coordinate, row, col, gender);
        this.value = Boolean.valueOf(value);
    }

    public void move() {
        // Do nothing. The fish object is to keep at the same coordinate.

    }

    public String getName() {// return the name of the object
        return this.name;
    }
}
