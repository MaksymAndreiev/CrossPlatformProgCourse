package serpack;

import java.io.Serializable;
import java.util.ArrayList;

class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private String libraryName;
    private ArrayList<BookStore> storeList = new ArrayList<>();
    private ArrayList<BookReader> bookReaders = new ArrayList<>();

    Library(String libraryName) {
        this.libraryName = libraryName;
    }

    public void addStore(BookStore bookStore) {
        this.storeList.add(bookStore);
    }

    public void addReader(BookReader bookReader) {
        this.bookReaders.add(bookReader);
    }

    public String getLibraryName() {
        return libraryName;
    }

    public ArrayList<BookStore> getStoreList() {
        return storeList;
    }

    public ArrayList<BookReader> getBookReaders() {
        return bookReaders;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public void setStoreList(ArrayList<BookStore> storeList) {
        this.storeList = storeList;
    }

    public void setBookReaders(ArrayList<BookReader> bookReaders) {
        this.bookReaders = bookReaders;
    }

    @Override
    public String toString() {
        String store = "";
        for (BookStore bookStore :
                storeList) {
            store += bookStore.toString() + "\n";
        }
        String readers = "";
        for (BookReader bookReader :
                bookReaders) {
            readers += bookReader.toString() + "\n";
        }
        return "Біблотека \"" + libraryName + "\"\n" +
                "сховища:\n" + store +
                "читачі:\n" + readers;
    }
}

