package serpack;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

class Library3 implements Externalizable {
    private String libraryName;
    private ArrayList<BookStore3> storeList = new ArrayList<>();
    private ArrayList<BookReader3> bookReaders = new ArrayList<>();

    public Library3() {
    }

    public Library3(String libraryName) {
        this.libraryName = libraryName;
    }

    public void addStore(BookStore3 bookStore) {
        this.storeList.add(bookStore);
    }

    public void addReader(BookReader3 bookReader) {
        this.bookReaders.add(bookReader);
    }

    public String getLibraryName() {
        return libraryName;
    }

    public ArrayList<BookStore3> getStoreList() {
        return storeList;
    }

    public ArrayList<BookReader3> getBookReaders() {
        return bookReaders;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public void setStoreList(ArrayList<BookStore3> storeList) {
        this.storeList = storeList;
    }

    public void setBookReaders(ArrayList<BookReader3> bookReaders) {
        this.bookReaders = bookReaders;
    }

    @Override
    public String toString() {
        String store = "";
        for (BookStore3 bookStore :
                storeList) {
            store += bookStore.toString() + "\n";
        }
        String readers = "";
        for (BookReader3 bookReader :
                bookReaders) {
            readers += bookReader.toString() + "\n";
        }
        return "Біблотека \"" + libraryName + "\"\n" +
                "сховища:\n" + store +
                "читачі:\n" + readers;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(libraryName);
        out.writeInt(storeList.size());
        for (Externalizable bookStore :
                storeList) {
            bookStore.writeExternal(out);
        }
        out.writeInt(bookReaders.size());
        for (Externalizable reader :
                bookReaders) {
            reader.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        libraryName = (String) in.readObject();
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            BookStore3 bookStore = new BookStore3();
            bookStore.readExternal(in);
            storeList.add(bookStore);
        }
        count = in.readInt();
        for (int i = 0; i < count; i++) {
            BookReader3 bookReader = new BookReader3();
            bookReader.readExternal(in);
            bookReaders.add(bookReader);
        }
    }
}

