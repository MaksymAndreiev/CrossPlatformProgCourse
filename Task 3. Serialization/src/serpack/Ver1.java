package serpack;

import java.io.*;


class LibraryDriver {

    public static void serializeObject(String filename, Object obj) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(obj);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String fileName) {
        Object obj = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            obj = is.readObject();
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        Library library = new Library("Library1");
        Author author1 = new Author("Macey", "Foster");
        Author author2 = new Author("Ollie", "Field");
        Book book1 = new Book("The Lost Soul", 1995, 1, author1);
        Book book2 = new Book("New Planet", 1956, 2, author1);
        Book book3 = new Book("Bizarre Robot", 1962, 2, author1, author2);
        Book book4 = new Book("What’s Over There?", 1998, 2, author2);
        BookStore bookStore1 = new BookStore("Fantasy");
        BookStore bookStore2 = new BookStore("Horror");
        BookStore bookStore3 = new BookStore("Detective");
        bookStore1.addBook(book2);
        bookStore1.addBook(book3);
        bookStore2.addBook(book1);
        bookStore3.addBook(book4);
        library.addStore(bookStore1);
        library.addStore(bookStore2);
        library.addStore(bookStore3);
        BookReader bookReader1 = new BookReader("Nana", "Sheldon", 1);
        bookReader1.addOwnedBook(book2);
        library.addReader(bookReader1);
        BookReader bookReader2 = new BookReader("Neel", "Stark", 2);
        bookReader2.addOwnedBook(book1, book4);
        serializeObject("ver1.dat", library);
        Library deserLib = (Library) deSerializeObject("ver1.dat");
        System.out.println(deserLib);
    }
}