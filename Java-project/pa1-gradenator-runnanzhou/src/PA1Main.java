
/* Runnan Zhou
 * 2018 fall
 * csc210
 * section friday
 * 
 * This function is to compute the average point of the class.
 * To use it, you need to make a input document with the same format.
 * then you can use it to compute your point!
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PA1Main {
    // this function is the summary of all.
    public static void main(String[] args) {
        Scanner fileName = userInput();
        String str = fileName.next();
        Double total = 0.0;
        Double totalAll = 0.0;

        try {
            Scanner input = readFile(str);
            while (input.hasNextLine()) {
                String[] variable = organize(input);
                String[] grade = variable[0].trim().split(" ");
                Double sum = 0.0;
                sum = average(grade, sum);

                total = totalNumber(total, variable, sum);
                totalAll = printTotal(totalAll, variable, sum);
            }
            printFunction(totalAll, total);
            closeFile(fileName, input);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static void printFunction(Double totalAll, double total) {
        // this print the total point
        System.out.format("TOTAL = %.1f%% out of %.1f%%", total, totalAll);
    }

    private static String[] organize(Scanner input) {
        // this organize the input data
        String line = input.nextLine();
        String[] variable = line.split(";");
        return variable;
    }

    private static Double printTotal(Double totalAll, String[] variable,
            double sum) {
        // this print the a part of the text
        double score = Double.valueOf(variable[2].replace("%", ""));

        totalAll = totalAll + Double.valueOf(variable[2].replace("%", ""));
        System.out
                .format("%s; %s%%; avg=%.1f\n", variable[1].trim(),
                        score, sum);

        return totalAll;
    }

    private static Double totalNumber(Double total, String[] variable,
            double sum) {
        // this compute for the total point of the class.
        total = total
                + sum / 100 * Double.valueOf(variable[2].replace("%", ""));
        return total;
    }

    private static void closeFile(Scanner fileName, Scanner input) {
        // close file
        fileName.close();
        input.close();
    }

    private static Double average(String[] grade, Double sum) {
        // compute the average of grade.
        for (int i = 0; i < grade.length; i++) {

            sum = sum + Double.valueOf(grade[i].trim());

        }
        sum = sum / (grade.length);
        return sum;
    }

    private static Scanner readFile(String str) throws FileNotFoundException {
        // open file
        Scanner input = new Scanner(new File(str));
        return input;
    }

    private static Scanner userInput() {
        // prompt for input
        Scanner fileName = new Scanner(System.in);
        System.out.println("File name?");
        return fileName;
    }



}
