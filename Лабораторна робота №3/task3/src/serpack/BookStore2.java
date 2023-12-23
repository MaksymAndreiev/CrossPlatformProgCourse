package serpack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class BookStore2 implements Serializable {
    private String name;
    private transient ArrayList<Book2> list = new ArrayList<>();

    public BookStore2(String name) {
        this.name = name;
    }

    public void addBook(Book2 book) {
        this.list.add(book);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book2> getList() {
        return list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(ArrayList<Book2> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Книжне сховище " + name +
                ", книжки у сховищі: " + list.toString();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(list.size());
        for (Book2 book :
                list) {
            out.writeObject(book.getTitle());
            out.writeInt(book.getYear());
            out.writeInt(book.getAuthors().size());
            for (Author2 author :
                    book.getAuthors()) {
                out.writeObject(author.getName());
                out.writeObject(author.getSurname());
            }
            out.writeInt(book.getNumber());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        list = new ArrayList<Book2>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String title = (String) in.readObject();
            int year = in.readInt();
            int count = in.readInt();
            ArrayList<Author2> authors = new ArrayList<Author2>(count);
            for (int j = 0; j < count; j++) {
                Author2 author = new Author2((String) in.readObject(), (String) in.readObject());
                authors.add(j, author);
            }
            int number = in.readInt();
            Book2 book = new Book2(title, year, number, authors.toArray(new Author2[0]));
            list.add(book);
        }
    }
}

