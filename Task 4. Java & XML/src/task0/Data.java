package task0;

public class Data {

    private String district;
    private double COORD_L, COORD_W;

    public Data(double COORD_L, double COORD_W) {
        super();
        this.COORD_L = COORD_L;
        this.COORD_W = COORD_W;
        district = "";
    }

    public Data() {
        this(0.0, 0.0);
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getCOORD_L() {
        return COORD_L;
    }

    public void setCOORD_L(double COORD_L) {
        this.COORD_L = COORD_L;
    }

    public double getCOORD_W() {
        return COORD_W;
    }

    public void setCOORD_W(double COORD_W) {
        this.COORD_W = COORD_W;
    }

    @Override
    public String toString() {
        return "Data [district=" + district + ", COORD_L=" + COORD_L + ", COORD_W=" + COORD_W + "]";
    }
}

