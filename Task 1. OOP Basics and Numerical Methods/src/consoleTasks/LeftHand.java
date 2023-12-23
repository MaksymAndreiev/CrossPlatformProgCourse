package consoleTasks;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.Math.*;

public class LeftHand implements Evaluetable {

    private double a;

    public LeftHand(double a) {
        this.a = a;
    }

    public LeftHand() {
        this(0);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    @Override
    public double evalf(double x) {
        return 1.0 / pow(cosh(x), 2) - a * x;
    }

    public static void main(String[] args) throws IOException {
        LeftHand fun = new LeftHand();

        Scanner in = new Scanner(System.in);
        System.out.println("a: ");
        double a = in.nextDouble();
        fun.setA(a);
        System.out.println("xBeg: ");
        double xBeg = in.nextDouble();
        System.out.println("xEnd: ");
        double xEnd = in.nextDouble();
        System.out.println("xStep: ");
        double xStep = in.nextDouble();

        System.out.println("" + fun.getA());
        PrintWriter out = new PrintWriter(new FileWriter("LeftHand_A"+a+".dat"));
        for (double x = xBeg; x < xEnd; x+=xStep) {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
            out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
        }
        out.close();
    }
}
