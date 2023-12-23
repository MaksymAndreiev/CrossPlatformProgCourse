package serpack;

import java.util.ArrayList;
import java.util.Arrays;

class BookReader2 extends Human2{
    private int regNumber;
    private ArrayList<Book2> listOfOwnedBooks = new ArrayList<>();

    public BookReader2(String name, String surname, int regNumber) {
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

    public ArrayList<Book2> getListOfOwnedBooks() {
        return listOfOwnedBooks;
    }

    public void addOwnedBook(Book2... books) {
        this.listOfOwnedBooks.addAll(Arrays.asList(books));
    }

    @Override
    public String toString() {
        return "Читач: " + getName() + " " + getSurname() +
                ", регістраціоний номер " + regNumber +
                ", список отриманих книг: " + listOfOwnedBooks;
    }
}

