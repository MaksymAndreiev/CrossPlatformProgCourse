package serpack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

class BookReader extends Human implements Serializable {
    private static final long serialVersionUID = 1L;
    private int regNumber;
    private ArrayList<Book> listOfOwnedBooks = new ArrayList<>();

    public BookReader(String name, String surname, int regNumber) {
        setName(name);
        setSurname(surname);
        this.regNumber = regNumber;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    public int getRegNumber() {
        return regNumber;
    }

    public void addOwnedBook(Book... books) {
        this.listOfOwnedBooks.addAll(Arrays.asList(books));
    }

    @Override
    public String toString() {
        return "Читач: " + getName() + " " + getSurname() +
                ", регістраціоний номер " + regNumber +
                ", список отриманих книг: " + listOfOwnedBooks;
    }
}

