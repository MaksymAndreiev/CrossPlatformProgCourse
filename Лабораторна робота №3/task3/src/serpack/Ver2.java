package serpack;

import java.io.*;

class LibraryDriver2 {

    public static void main(String[] args) {
        Library2 library = new Library2("Library2");
        Author2 author1 = new Author2("Macey", "Foster");
        Author2 author2 = new Author2("Ollie", "Field");
        Book2 book1 = new Book2("The Lost Soul", 1995, 1, author1);
        Book2 book2 = new Book2("New Planet", 1956, 2, author1);
        Book2 book3 = new Book2("Bizarre Robot", 1962, 2, author1, author2);
        Book2 book4 = new Book2("What’s Over There?", 1998, 2, author2);
        BookStore2 bookStore1 = new BookStore2("Fantasy");
        BookStore2 bookStore2 = new BookStore2("Horror");
        BookStore2 bookStore3 = new BookStore2("Detective");
        bookStore1.addBook(book2);
        bookStore1.addBook(book3);
        bookStore2.addBook(book1);
        bookStore3.addBook(book4);
        library.addStore(bookStore1);
        library.addStore(bookStore2);
        library.addStore(bookStore3);
        BookReader2 bookReader1 = new BookReader2("Nana", "Sheldon", 1);
        bookReader1.addOwnedBook(book2);
        library.addReader(bookReader1);
        BookReader2 bookReader2 = new BookReader2("Neel", "Stark", 2);
        bookReader2.addOwnedBook(book1, book4);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ver2.dat"));
            out.writeObject(library);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("ver2.dat"));
            Library2 library2 = (Library2) in.readObject();
            System.out.println(library2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}