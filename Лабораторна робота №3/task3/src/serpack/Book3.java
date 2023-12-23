package serpack;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;

class Book3 implements Externalizable {
    transient private String title;
    transient private ArrayList<Author3> authors = new ArrayList<Author3>();
    transient private int year;
    transient private int number;

    public Book3() {
    }

    public Book3(String title, int year, int number, Author3... authors) {
        this.title = title;
        this.authors.addAll(Arrays.asList(authors));
        this.year = year;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Author3> getAuthors() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public int getNumber() {
        return number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(ArrayList<Author3> authors) {
        this.authors = authors;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Назва книги: \"" + title + '"' +
                ", автор(и): " + authors +
                ", рік видання: " + year +
                ", номер видання: " + number;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeInt(authors.size());
        for (Externalizable author :
                authors) {
            author.writeExternal(out);
        }
        out.writeInt(year);
        out.writeInt(number);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            Author3 author = new Author3();
            author.readExternal(in);
            authors.add(author);
        }
        year = in.readInt();
        number = in.readInt();
    }
}
