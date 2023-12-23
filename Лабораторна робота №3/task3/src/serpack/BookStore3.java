package serpack;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

class BookStore3 implements Externalizable {
    private String name;
    private ArrayList<Book3> list = new ArrayList<>();

    public BookStore3() {
    }

    public BookStore3(String name) {
        this.name = name;
    }

    public void addBook(Book3 book) {
        this.list.add(book);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book3> getList() {
        return list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(ArrayList<Book3> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Книжне сховище " + name +
                ", книжки у сховищі: " + list.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(list.size());
        for (Externalizable book :
                list) {
            book.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            Book3 book = new Book3();
            book.readExternal(in);
            list.add(book);
        }
    }
}

