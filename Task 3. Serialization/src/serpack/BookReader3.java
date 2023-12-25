package serpack;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;

class BookReader3 extends Human3 implements Externalizable {
    transient private int regNumber;
    transient private ArrayList<Book3> listOfOwnedBooks = new ArrayList<>();

    public BookReader3() {
    }

    public BookReader3(String name, String surname, int regNumber) {
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

    public ArrayList<Book3> getListOfOwnedBooks() {
        return listOfOwnedBooks;
    }

    public void addOwnedBook(Book3... books) {
        this.listOfOwnedBooks.addAll(Arrays.asList(books));
    }

    public void setRegNumber(int regNumber) {
        this.regNumber = regNumber;
    }

    public void setListOfOwnedBooks(ArrayList<Book3> listOfOwnedBooks) {
        this.listOfOwnedBooks = listOfOwnedBooks;
    }

    @Override
    public String toString() {
        return "Читач: " + getName() + " " + getSurname() +
                ", регістраціоний номер " + regNumber +
                ", список отриманих книг: " + listOfOwnedBooks;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
        out.writeObject(getSurname());
        out.writeInt(listOfOwnedBooks.size());
        for (Externalizable book :
                listOfOwnedBooks) {
            book.writeExternal(out);
        }
        out.writeInt(regNumber);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setName((String) in.readObject());
        setSurname((String) in.readObject());
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            Book3 book = new Book3();
            book.readExternal(in);
            listOfOwnedBooks.add(book);
        }
        regNumber = in.readInt();
    }
}

