package consoleTasks;

import java.util.*;

import static java.lang.Math.*;

public class ListInterpolation extends Interpolator {

    private List<Point2D> data = null;

    public ListInterpolation() {
        data = new ArrayList<Point2D>();
    }

    public ListInterpolation(List<Point2D> data) {
        this.data = data;
    }

    public ListInterpolation(Point2D[] data) {
        this();
        this.data.addAll(Arrays.asList(data));
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int numPoints() {
        return data.size();
    }

    @Override
    public void addPoint(Point2D pt) {
        data.add(pt);
    }

    @Override
    public Point2D getPoint(int i) {
        return data.get(i);
    }

    @Override
    public void setPoint(int i, Point2D pt) {
        data.set(i, pt);
    }

    @Override
    public void removeLastPoint() {
        data.remove(data.size() - 1);
    }

    @Override
    public void sort() {
        Collections.sort(data);
    }

    public static void main(String[] args) {
        ListInterpolation fun = new ListInterpolation();
        int num;
        double x;
        Scanner in = new Scanner(System.in);

        do {
            System.out.print("Кількість точок: ");
            num = in.nextInt();
        } while (num <= 0);

        for (int i = 0; i < num; i++) {
            x = 1.0 + (5.0 - 1.0) * random();
            fun.addPoint(new Point2D(x, sin(x)));
        }
        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }

        fun.sort();
        System.out.println("Отсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }

        System.out.println("Мінімальне значення х: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення х: " + fun.getPoint(fun.numPoints() - 1).getX());

        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + sin(x));
        System.out.println("Абсолютна помилка = " + abs(fun.evalf(x) - sin(x)));

        System.out.println("Видалення точки.");
        fun.removeLastPoint();
        System.out.println("Перезапис першої точки.");
        fun.setPoint(0, new Point2D(0, sin(0)));
        System.out.println("Інтерполяція по: " + fun.numPoints() + " точкам");
        System.out.println("Несортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }

        fun.sort();
        System.out.println("Отсортований набір: ");
        for (int i = 0; i < fun.numPoints(); i++) {
            System.out.println("Точка " + (i + 1) + ": " + fun.getPoint(i));
        }

        System.out.println("Мінімальне значення х: " + fun.getPoint(0).getX());
        System.out.println("Максимальне значення х: " + fun.getPoint(fun.numPoints() - 1).getX());

        x = 0.5 * (fun.getPoint(0).getX() + fun.getPoint(fun.numPoints() - 1).getX());
        System.out.println("Значення інтерполяції fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точне значення sin(" + x + ") = " + sin(x));
        System.out.println("Абсолютна помилка = " + abs(fun.evalf(x) - sin(x)));
    }
}
