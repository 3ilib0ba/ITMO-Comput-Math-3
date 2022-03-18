import java.util.Scanner;

public class Main {

    private final static String eqsStr =
            "y = x^2\n" +
            "y = x^3 - 5x^2 + 3x - 16\n" +
            "y = 4x^3 - 5x^2 + 6x - 7\n" +
            "y = x^3 + x^2 - x - 1";

    private final static double[][] eqs = new double[4][4];

    private static void initEqs() {
        eqs[0] = new double[]{0, 0, 1, 0};
        eqs[1] = new double[]{-16, 3, -5, 1};
        eqs[2] = new double[]{-7, 6, -5, 4};
        eqs[3] = new double[]{-1, -1, 1, 1};
    }

    public static void main(String[] args) {
        try {
            initEqs();
            Scanner scanner = new Scanner(System.in);
            double a, b, accuracy;
            int numEq, numMth, numRect = -1;
            System.out.println(eqsStr);
            System.out.println("Choose the equation. Write from 1 to 4");
            numEq = scanner.nextInt();
            System.out.println("Choose method:\n" +
                    "1. Simpson\n" +
                    "2. Trapezoidal\n" +
                    "3. Rectangle");
            numMth = scanner.nextInt();
            if (numMth == 3) {
                System.out.println("Choose method:\n" +
                        "0 -> Right\n" +
                        "1 -> Central\n" +
                        "2 -> Left");
                numRect = scanner.nextInt();
            }
            System.out.println("Enter the interval");
            a = scanner.nextDouble();
            b = scanner.nextDouble();
            System.out.println("Enter the accuracy");
            accuracy = scanner.nextDouble();
            System.out.println("Answer: " + Solver.start(numMth, eqs[numEq - 1], a, b, accuracy, numRect));
        } catch (Exception e) {
            System.out.println("Incorrect input");
            System.out.println(e.getMessage());
        }
    }

}
