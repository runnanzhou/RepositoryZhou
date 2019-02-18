public class Animal {
    // This is the main class of object
    public String name; // name of the object
    public String gender; // gender of the object
    private Boolean alive; // whether the animal is alive
    public int count; // count of the steps
    int[] coordinate = new int[2]; // coordiante of the object
    public String type; // type of the object
    public static int row; // the number of row of the screen.
    public static int col; // the number of col of the screen.

    public Animal(String name, String gender, int[] coordinate, int row,
            int col, String type) {
        // The constructor of the class
        // @parameter:String name, String gender, int[] coordinate, int row,
        // int col, String type
        // @return:
        this.name = name;
        this.gender = gender;
        this.coordinate = coordinate;
        this.alive = true;
        this.type = type;
        Animal.row = row;
        Animal.col = col;

    }


    public void move() {
        // method to be overridden

    }

    public void move(int x, int y) {
        // method to be overridden
    }

    public void move(String type) {
        // method to be overridden

    }
}
