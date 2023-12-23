package consoleTasks;

import java.util.Scanner;

import static java.lang.Math.*;

public class FFunction implements Evaluetable {

    private double a;

    public FFunction(double a) {
        this.a = a;
    }

    public FFunction() {
        this(1.0);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    @Override
    public double evalf(double x) {
        return exp(-a * x * x) * sin(x);
    }

    public static void main(String[] args) {
        // write your code here
        System.out.println("FFunction");

        FFunction fun = new FFunction();

        Scanner in = new Scanner(System.in);
        System.out.println("xBeg: ");
        double xBeg = in.nextDouble();
        System.out.println("xEnd: ");
        double xEnd = in.nextDouble();
        System.out.println("xStep: ");
        double xStep = in.nextDouble();

        System.out.println("a: " + fun.getA());
        for (double x = xBeg; x <= xEnd; x += xStep) {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
        }

        System.out.println("x: ");
        double x = in.nextDouble();
        System.out.println("aBeg: ");
        double aBeg = in.nextDouble();
        System.out.println("aEnd: ");
        double aEnd = in.nextDouble();
        System.out.println("aStep: ");
        double aStep = in.nextDouble();

        System.out.println("x: " + x);
        for (double a = aBeg; a <= aEnd; a += aStep) {
            fun.setA(a);
            System.out.printf("a: %6.4f\tf: %6.4f\n", fun.getA(), fun.evalf(x));
        }
    }
}
