import java.util.ArrayList;
import java.util.List;

public class Screen extends Ecosystem {
    // this is the class to print the ecosystem
    private List[][] list;

    public Screen(int row, int col, Ecosystem eco) {
        // The constructor of the class.
        super(row, col);
        list = new List[row][col];
        for (int i = 0; i < row; i++) {
            for (int n = 0; n < col; n++) {
                list[i][n] = new ArrayList<String>();

                if (eco.getall()[i][n].isEmpty()) {
                    list[i][n].add(".");
                } else {
                    String name = ((Animal) eco.getall()[i][n].get(0)).name
                            .substring(0,
                            1);
                    list[i][n].add(name);

                }

            }

        }

    }

    public List[][] get() {
        return list;
    }

}
