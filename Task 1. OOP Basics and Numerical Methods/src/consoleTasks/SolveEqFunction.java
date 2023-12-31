package consoleTasks;

import java.util.Scanner;

public class SolveEqFunction implements Evaluetable {

    private LeftHand fun;
    private double tol;
    private double rootApprox;

    public SolveEqFunction() {
        fun = new LeftHand();
        tol = 1.0e-7;
        rootApprox = 0.0;
    }

    public double getTol() {
        return tol;
    }

    public void setTol(double tol) {
        this.tol = tol;
    }

    public void setRootApprox(double rootApprox) {
        this.rootApprox = rootApprox;
    }

    public double checkRoot(double x) {
        return fun.evalf(x);
    }

    @Override
    public double evalf(double x) {
        fun.setA(x);
        rootApprox = NumMethods.findRoot(rootApprox, tol, fun);
        return rootApprox;
    }

    public static void main(String[] args) {
        SolveEqFunction fun = new SolveEqFunction();

        Scanner in = new Scanner(System.in);
        System.out.print("a: ");
        double a = in.nextDouble();
        System.out.print("xAppr: ");
        double xAppr = in.nextDouble();
        fun.setRootApprox(xAppr);
        double res = fun.evalf(a);
        System.out.println("Корінь: " + res + "\tточність: " + fun.getTol() + "\tf(root): " + fun.checkRoot(res));
    }
}
