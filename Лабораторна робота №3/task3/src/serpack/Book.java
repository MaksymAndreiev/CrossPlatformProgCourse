package serpack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private ArrayList<Author> authors = new ArrayList<Author>();
    private int year;
    private int number;

    public Book(String title, int year, int number, Author... authors) {
        this.title = title;
        this.authors.addAll(Arrays.asList(authors));
        this.year = year;
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Назва книги: \"" + title + '"' +
                ", автор(и): " + authors +
                ", рік видання: " + year +
                ", номер видання: " + number;
    }
}

