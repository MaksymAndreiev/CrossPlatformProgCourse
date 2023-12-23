package task1;

public class DataAnalyzer {

    private DataSheet datasheet;

    private double k, b;

    public DataAnalyzer(DataSheet datasheet) {
        super();
        this.datasheet = datasheet;
        k = 0.0;
        b = 0.0;
    }

    public DataAnalyzer() {
        super();
        this.datasheet = null;
        this.k = 0.0;
        this.b = 0.0;
    }

    public void setDatasheet(DataSheet datasheet) {
        this.datasheet = datasheet;
    }

    public DataSheet getDatasheet() {
        return this.datasheet;
    }

    public double getK() {
        return k;
    }

    public double getB() {
        return b;
    }

    public void Analyze() {
        double sumX = 0, sumY = 0, sumX2 = 0, sumXY = 0;
        double x = 0, y = 0;
        int num = datasheet.size();
        for (int i = 0; i < num; i++) {
            x = datasheet.getDataItem(i).getX();
            y = datasheet.getDataItem(i).getY();
            sumX += x;
            sumY += y;
            sumX2 += x * x;
            sumXY += x * y;
        }
        k = (sumXY - sumX * sumY / num) / (sumX2 - sumX * sumX / num);
        b = sumY / num - k * sumX / num;
    }

    @Override
    public String toString() {
        return "DataAnalyzer [k=" + k + ", b=" + b + "]";
    }
}