package serpack;

import java.io.Serializable;
import java.util.ArrayList;

class BookStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<Book> list = new ArrayList<>();

    public BookStore(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        this.list.add(book);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(ArrayList<Book> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "Книжне сховище " + name +
                ", книжки у сховищі: " + list.toString();
    }
}

