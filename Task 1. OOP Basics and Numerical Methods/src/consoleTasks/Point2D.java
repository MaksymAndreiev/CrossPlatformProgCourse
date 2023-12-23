package consoleTasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import static java.lang.Math.*;

public class Point2D extends Point implements Comparable<Point2D>{

    public Point2D(double x, double y) {
        super(2);
        setCoord(1, x);
        setCoord(2, y);
    }

    public Point2D() {
        this(0, 0);
    }

    public double getX() {
        return getCoord(1);
    }

    public void setX(double x) {
        setCoord(1, x);
    }

    public double getY() {
        return getCoord(2);
    }

    public void setY(double y) {
        setCoord(2, y);
    }

    @Override
    public int compareTo(Point2D pt) {
        return Double.compare(getX(), pt.getX());
    }

    public static void main(String[] args) {
        List<Point2D> data = new ArrayList<Point2D>();
        int num;
        double x;

        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Кількість точок: ");
            num = in.nextInt();
        } while (num <= 0);

        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0) * random();
            data.add(new Point2D(x, sin(x)));
        }

        System.out.println("Несортовані дані: ");
        for (Point2D pt: data) {
            System.out.println(pt);
        }
        System.out.println("\nОтсортовані дані: ");
        Collections.sort(data);
        for (Point2D pt:
             data) {
            System.out.println("x = " + pt.getX() + "\ty = " + pt.getY());
        }
    }
}
