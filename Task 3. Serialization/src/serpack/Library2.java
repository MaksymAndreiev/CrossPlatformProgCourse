package serpack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class Library2 implements Serializable {
    private String libraryName;
    private ArrayList<BookStore2> storeList = new ArrayList<>();
    private transient ArrayList<BookReader2> bookReaders = new ArrayList<>();

    public Library2(String libraryName) {
        this.libraryName = libraryName;
    }

    public void addStore(BookStore2 bookStore) {
        this.storeList.add(bookStore);
    }

    public void addReader(BookReader2 bookReader) {
        this.bookReaders.add(bookReader);
    }

    public String getLibraryName() {
        return libraryName;
    }

    public ArrayList<BookStore2> getStoreList() {
        return storeList;
    }

    public ArrayList<BookReader2> getBookReaders() {
        return bookReaders;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public void setStoreList(ArrayList<BookStore2> storeList) {
        this.storeList = storeList;
    }

    public void setBookReaders(ArrayList<BookReader2> bookReaders) {
        this.bookReaders = bookReaders;
    }

    @Override
    public String toString() {
        String store = "";
        for (BookStore2 bookStore :
                storeList) {
            store += bookStore.toString() + "\n";
        }
        String readers = "";
        for (BookReader2 bookReader :
                bookReaders) {
            readers += bookReader.toString() + "\n";
        }
        return "Біблотека \"" + libraryName + "\"\n" +
                "сховища:\n" + store +
                "читачі:\n" + readers;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(bookReaders.size());
        for (BookReader2 bookReader :
                bookReaders) {
            out.writeObject(bookReader.getName());
            out.writeObject(bookReader.getSurname());
            out.writeInt(bookReader.getListOfOwnedBooks().size());
            for (Book2 book :
                    bookReader.getListOfOwnedBooks()) {
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
            out.writeInt(bookReader.getRegNumber());
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int countReaders = in.readInt();
        ArrayList<BookReader2> readers = new ArrayList<>(countReaders);
        for (int i = 0; i < countReaders; i++) {
            String name = (String) in.readObject();
            String surname = (String) in.readObject();
            int countBooks = in.readInt();
            ArrayList<Book2> ownedBooks = new ArrayList<Book2>();
            for (int j = 0; j < countBooks; j++) {
                String title = (String) in.readObject();
                int year = in.readInt();
                int count = in.readInt();
                ArrayList<Author2> authors = new ArrayList<Author2>(count);
                for (int k = 0; k < count; k++) {
                    Author2 author = new Author2((String) in.readObject(), (String) in.readObject());
                    authors.add(k, author);
                }
                int number = in.readInt();
                Book2 book = new Book2(title, year, number, authors.toArray(new Author2[0]));
                ownedBooks.add(book);
            }
            BookReader2 bookReader = new BookReader2(name, surname, in.readInt());
            for (Book2 book :
                    ownedBooks) {
                bookReader.addOwnedBook(book);
            }
            readers.add(bookReader);
        }
        setBookReaders(readers);
    }
}

