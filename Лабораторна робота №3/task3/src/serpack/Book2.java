package serpack;

import java.util.ArrayList;
import java.util.Arrays;

class Book2{
    private String title;
    private ArrayList<Author2> authors = new ArrayList<Author2>();
    private int year;
    private int number;

    public Book2(String title, int year, int number, Author2... authors) {
        this.title = title;
        this.authors.addAll(Arrays.asList(authors));
        this.year = year;
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(ArrayList<Author2> authors) {
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

    public ArrayList<Author2> getAuthors() {
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
