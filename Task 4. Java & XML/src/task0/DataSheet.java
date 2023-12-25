package task0;

import java.util.ArrayList;

public class DataSheet {

    private String name;
    private ArrayList<Data> datasheet = null;

    public DataSheet() {
        super();
        name = "noname";
        datasheet = new ArrayList<Data>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int size() {
        return datasheet.size();
    }

    public Data getDataItem(int pos) {
        return datasheet.get(pos);
    }

    public void changeDataItem(Data d, int pos) {
        datasheet.set(pos, d);
    }

    public void addDataItem(Data d) {
        datasheet.add(d);
    }

    public void removeDataItem(int pos) {
        datasheet.remove(pos);
    }

    @Override
    public String toString() {
        return "DataSheet [name=" + name + ", datasheet=" + datasheet + "]";
    }
}

