import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ecosystem {
    private int row; // the row of the ecosystem.
    private int col; // the length of the ecosystem.
    private List[][] ecoList; // the list to store the animal object.
    private Ecosystem eco; // an object of the ecosystem. used to call method.
    public Ecosystem(int row, int col) {
        // the constructor of the ecosystem
        // @parameter int row, int col
        this.row = row;
        this.col = col;
        ecoList = new ArrayList[this.row][this.col];
        for (int i = 0; i < this.row; i++) {
            for (int n = 0; n < this.col; n++) {

                ecoList[i][n] = new ArrayList<Animal>();
                // creat the ecosystem list.
            }
        }

    }

    public void readLine(String file, List<Animal> object) {
        // this method is used to deal with the input method by the user.
        // @parameter: String file, List<Animal> object

        String[] mal = { "E", "R", "L", "C", "G", "Z" };
        String[] bir = { "T", "O", "W", "D", "S" };
        String[] ins = { "B", "F", "H", "A" };
        List<String> malList = new ArrayList<String>();
        List<String> birList = new ArrayList<String>();
        List<String> insList = new ArrayList<String>();
        for (String a : mal) {
            malList.add(a);
        }
        for (String b : bir) {
            birList.add(b);
        }
        for (String c : ins) {
            insList.add(c);
        }

        String place = "";
        String name = "";
        String gender = "";
        String feature = "";
        Ecosystem eco = new Ecosystem(0, 0);
        int[] coordinate = new int[2];
        List<String> action = new ArrayList<String>();
        String[] factor = file.split(" ");
        if (factor[0].startsWith("rows:")) {
            row = Integer.parseInt(factor[1]);
        } else if (factor[0].startsWith("cols:")) {
            col = Integer.parseInt(factor[1]);
            Animal example = new Animal(name, gender, coordinate, row - 1,
                    col - 1,
                    "Animal");
            eco = new Ecosystem(row, col);

        } else if (factor[0].startsWith("CREATE")) {

            place = factor[1];
            name = factor[2];
            gender = factor[3];
            feature = factor[4];
            Boolean feature2 = null;
            Boolean feature3 = null;
            if (factor.length > 5) {
                feature2 = Boolean.valueOf(factor[5]);
                feature3 = Boolean.valueOf(factor[6]);
            }
            coordinate[0] = Integer.parseInt(place.substring(1, 2));
            coordinate[1] = Integer
                    .parseInt(place.substring(3, place.length() - 1));
            if (malList.contains(name.substring(0, 1).toUpperCase())) {
                addMam(object, name, gender, feature, coordinate);

            } else if (birList.contains(name.substring(0, 1).toUpperCase())) {
                addBir(object, name, gender, feature, coordinate);
            } else if (insList.contains(name.substring(0, 1).toUpperCase())) {
                addIns(object, name, gender, feature, coordinate);
            }
            else if (name.substring(0, 1).toUpperCase().equals("M")) {
                addMos(object, name, gender, feature, coordinate, feature2,
                        feature3);
            }

        } else if (factor[0].isEmpty()) {
        } else {
            action.add(file);
        }
        for (String a : action) {

            setAction(object, malList, birList, insList, eco, a);
        }
    }

    private void setAction(List<Animal> object, List<String> malList,
            List<String> birList, List<String> insList, Ecosystem eco,
            String a) {
        // This method is used to deal with different action. Calling different
        // method to deal with the action.
        // @parameter List<Animal> object, List<String> malList,
        // List<String> birList, List<String> insList, Ecosystem eco,
        // String a
        if (a.toUpperCase().startsWith("MOVE")) {
            moveMethod(row, col, malList, birList, insList, ecoList, object, a);

        } else if (a.toUpperCase().startsWith("EAT")) {
            eatMethod(malList, birList, insList, eco, object);

        } else if (a.toUpperCase().startsWith("REPRODUCE")) {
            reproduceMethod(eco, object);
        }
    }

    private void addMos(List<Animal> object, String name, String gender,
            String feature, int[] coordinate, Boolean feature2,
            Boolean feature3) {
        /*
         * Add mosquitos to the screen.
         * 
         * @parameter List<Animal> object, String name, String gender,
         * String feature, int[] coordinate, Boolean feature2,
         * Boolean feature3
         */
        boolean clock = Boolean.valueOf(feature);
        Mosquitos m = new Mosquitos(name, gender, coordinate,
                "Mosquitos", clock, feature2, feature3);
        object.add(m);
        ecoList[coordinate[0]][coordinate[1]].add(m);
    }

    private void addIns(List<Animal> object, String name, String gender,
            String feature, int[] coordinate) {
        /*
         * Add insects tot the screen.
         * 
         * @parameter:List<Animal> object, String name, String gender,
         * String feature, int[] coordinate
         */

        boolean value = Boolean.valueOf(feature);
        Insects s = new Insects(name, gender, coordinate, value,
                "Insects");
        object.add(s);
        for (int i = 0; i < row; i++) {
            if (i == coordinate[0]) {
                for (int n = 0; n < col; n++) {
                    if (n == coordinate[1]) {
                        ecoList[i][n].add(s);
                    }
                }
            }
        }
    }

    private void addBir(List<Animal> object, String name, String gender,
            String feature, int[] coordinate) {
        /*
         * Add bird to the screen.
         * 
         * @parameter List<Animal> object, String name, String gender,
         * String feature, int[] coordinate
         */
        int number = Integer.parseInt(feature);
        Birds b = new Birds(name, gender, coordinate, number, "Birds");
        object.add(b);
        for (int i = 0; i < row; i++) {
            if (i == coordinate[0]) {
                for (int n = 0; n < col; n++) {
                    if (n == coordinate[1]) {
                        ecoList[i][n].add(b);
                    }
                }
            }
        }
    }

    private void addMam(List<Animal> object, String name, String gender,
            String feature, int[] coordinate) {
        /*
         * Add mammals to the screen.
         * 
         * @parameter List<Animal> object, String name, String gender,
         * String feature, int[] coordinate
         */
        Mammals m = new Mammals(name, gender, coordinate, feature,
                "Mammals");
        object.add(m);
        for (int i = 0; i < row; i++) {
            if (i == coordinate[0]) {
                for (int n = 0; n < col; n++) {
                    if (n == coordinate[1]) {
                        ecoList[i][n].add(m);
                    }
                }
            }
        }
    }

    private static void reproduceMethod(Ecosystem eco, List<Animal> object) {
        // call the reproduce method.
        // @parameter Ecosystem eco, List<Animal> object
        List[][] d = eco.getall();
        eco.reproduce(object);
        d = eco.getall();
    }

    private static void eatMethod(List<String> malList, List<String> birList,
            List<String> insList, Ecosystem eco, List<Animal> object) {
        // call the eat method.
        // @parameter List<String> malList, List<String> birList,
        // List<String> insList, Ecosystem eco, List<Animal> object
        List[][] d = eco.getall();
        eco.eat(malList, birList, insList, object);

        d = eco.getall();
    }

    public static List[][] getList(Ecosystem eco) {
        // return the list of the ecosystem.
        // @parameter: Ecosystem eco
        List[][] d = eco.getall();
        return d;

    }

    private static void moveMethod(int row, int col, List<String> malList,
            List<String> birList, List<String> insList, List[][] ecoList,
            List<Animal> object, String a) {
        /*
         * move the object when it is called.
         * 
         * @parameter: int row, int col, List<String> malList,
         * List<String> birList, List<String> insList, List[][] ecoList,
         * List<Animal> object, String a
         */


        if (a.length() == 4) {
            for (int i = 0; i < row; i++) {
                for (int n = 0; n < col; n++) {

                    ecoList[i][n] = new ArrayList<Animal>();
                }
            }
            for (Animal obj : object) {
                if (birList.contains(obj.name.substring(0, 1).toUpperCase())) {
                    Birds b = (Birds) obj;

                    b.move(ecoList);
                } else if (insList
                        .contains(obj.name.substring(0, 1).toUpperCase())) {
                    Insects i = (Insects) obj;
                    i.move(ecoList);
                } else if (malList
                        .contains(obj.name.substring(0, 1).toUpperCase())) {
                    Mammals m = (Mammals) obj;
                    m.move(ecoList);
                } else {
                    Mosquitos m = (Mosquitos) obj;
                    m.move(ecoList);
                }
            }
        } else {
            {
                for (int i = 0; i < row; i++) {
                    for (int n = 0; n < col; n++) {
                        ecoList[i][n] = new ArrayList<Animal>();
                    }
                }
                for (Animal obj : object) {
                    if (birList
                            .contains(obj.name.substring(0, 1).toUpperCase())) {
                        Birds b = (Birds) obj;

                        b.move(ecoList);
                    } else if (insList
                            .contains(obj.name.substring(0, 1).toUpperCase())) {
                        Insects i = (Insects) obj;
                        i.move(ecoList);
                    } else if (malList
                            .contains(obj.name.substring(0, 1).toUpperCase())) {
                        Mammals m = (Mammals) obj;
                        m.move(ecoList);
                    }
                }
            }
        }
    }


    public void eat(List<String> mal, List<String> bir, List<String> ins,
            List<Animal> list) {
        // Eat object when it is in the same coordinate.
        // @parameter List<String> mal, List<String> bir, List<String> ins,
        // List<Animal> list
        for (int i = 0; i < row; i++) {
            for (int n = 0; n < col; n++) {
                List<Animal> object = ecoList[i][n];
                if (object.size() >= 2) {
                if (mal.contains(
                        object.get(0).name.substring(0, 1).toUpperCase())
                        && mal.contains(object.get(1).name.substring(0, 1)
                                .toUpperCase())) {
                        list.remove(object.get(1));
                        ecoList[i][n].remove(1);
                } else if (bir.contains(
                            object.get(0).name.substring(0, 1).toUpperCase())
                            && ins.contains(object.get(1).name.substring(0, 1)
                                .toUpperCase())) {
                        list.remove(object.get(1));
                        ecoList[i][n].remove(1);
                } else if (ins.contains(
                        object.get(0).name.substring(0, 1).toUpperCase())
                            && bir.contains(object.get(1).name.substring(0, 1)
                                .toUpperCase())) {
                        list.remove(object.get(0));
                        ecoList[i][n].remove(0);


                }
                }
            }
        }

    }

    public void eat(int x, int y) {
        // Eat object when it is in the same coordinate.
        // @parameter int x int y
        for (int i = 0; i < row; i++) {
            if (i == x) {
                for (int n = 0; n < col; n++) {
                    if (n == y) {
                        List<Animal> object = ecoList[i][n];
                        if (object.get(0).type == "mammal"
                                && object.get(1).type == "mammal") {
                            ecoList[i][n].remove(1);
                        } else if (object.get(0).type == "bird"
                                && object.get(1).type == "insect") {
                            ecoList[i][n].remove(1);
                        } else if (object.get(0).type == "insect"
                                && object.get(1).type == "bird") {
                            ecoList[i][n].remove(0);

                        }
                    }

                }
            }

        }

    }

    public void reproduce(List<Animal> list) {
        // reproduce when there are male and female in the same corrdinate.
        // @parameter List<Animal> list
        String[] genderList = { "male", "female" };
        String[] directionList = { "left", "right" };
        Boolean[] booleanList = { true, false };
        Random random = new Random();
        int number = random.nextInt(2);
        int step = random.nextInt(10);
        boolean clockwise = booleanList[number];
        String gender = genderList[number];
        String direction = directionList[number];
        for (int i = 0; i < row; i++) {
            for (int n = 0; n < col; n++) {
                List<Animal> object = ecoList[i][n];
                if (!object.isEmpty()) {
                    if (object.get(0).getClass()
                            .equals(object.get(1).getClass())) {
                    if (object.get(0).gender != object.get(1).gender) {

                            if (object.get(0).type == "Mammals") {

                                mamReproduce(list, gender, direction, i, n,
                                        object);
                            } else if (object.get(0).type.equals("Birds")) {
                                birdReproduce(list, step, gender, i, n, object);
                            } else if (object.get(0).type.equals("Insects")) {
                                inseReproduce(list, clockwise, gender, i, n,
                                        object);
                        } else if (object.get(0).type == "mosquitos") {
                                mosReproduce(gender, i, n, object);
                        }
                        }
                    }
                }
            }
        }

    }

    private void mosReproduce(String gender, int i, int n,
            List<Animal> object) {
        // build a new object when it meet the reproduce requirement.
        // @parameter:String gender, int i, int n,
        // List<Animal> object
        Mosquitos m1 = (Mosquitos) object.get(0);
        Mosquitos m2 = (Mosquitos) object.get(1);
        if (m1.value2 == true && m1.value3 == true
                || m2.value2 == true && m2.value3 == true) {
        } else {
            boolean baby2 = m1.value2 || m1.value3;
            boolean baby3 = m2.value2 || m2.value3;
            Mosquitos newOne = new Mosquitos(m1.getName(), gender,
                    m1.coordinate,
                    "Mosquitos", false, baby2, baby3);
            ecoList[i][n].add(newOne);
        }
    }

    private void inseReproduce(List<Animal> list, boolean clockwise,
            String gender, int i, int n, List<Animal> object) {
        // build a new object when it meet the reproduce requirement.
        // @parameter:(List<Animal> list, boolean clockwise,
        // String gender, int i, int n, List<Animal> object
        Insects i1 = (Insects) object.get(0);
        Insects newOne = new Insects(i1.getName(), gender, i1.coordinate,
                clockwise,
                "Insects");
        list.add(newOne);
        ecoList[i][n].add(newOne);
    }

    private void birdReproduce(List<Animal> list, int step, String gender,
            int i, int n, List<Animal> object) {
        // build a new object when it meet the reproduce requirement.
        // @parameter:List<Animal> list, int step, String gender,
        // int i, int n, List<Animal> object
        Birds b1 = (Birds) object.get(0);
        Birds b2 = (Birds) object.get(1);
        if (b1.getCount() % 3 * b1.number < 2 * b1.number
                && b2.getCount() % 3 * b2.number < 2 * b2.number) {
            Birds newOne = new Birds(b1.getName(), gender, b1.getC(), step,
                    "Birds");
            list.add(newOne);
            ecoList[i][n].add(newOne);
        }
    }

    private void mamReproduce(List<Animal> list, String gender,
            String direction, int i, int n, List<Animal> object) {
        // build a new object when it meet the reproduce requirement.
        // @parameter:List<Animal> list, String gender,
        // String direction, int i, int n, List<Animal> object
        Mammals a1 = (Mammals) object.get(0);
        Mammals a2 = (Mammals) object.get(1);
        if (a1.reTime < 5 && a2.reTime < 5) {
            a1.reTime += 1;
            a2.reTime += 1;

            Mammals newOne = new Mammals(a1.getName(), gender, a1.coordinate,
                    direction, "Mammals");
            list.add(newOne);
            ecoList[i][n].add(newOne);
        }
    }

    public List[][] getall() {
        // return the list

        return ecoList;
    }



}
