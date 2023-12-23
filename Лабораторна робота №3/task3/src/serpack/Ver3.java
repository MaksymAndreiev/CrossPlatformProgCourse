package serpack;

import java.io.*;


class LibraryDriver3 {

    public static void main(String[] args) {
        Library3 library = new Library3("Library3");
        Author3 author1 = new Author3("Macey", "Foster");
        Author3 author2 = new Author3("Ollie", "Field");
        Book3 book1 = new Book3("The Lost Soul", 1995, 1, author1);
        Book3 book2 = new Book3("New Planet", 1956, 2, author1);
        Book3 book3 = new Book3("Bizarre Robot", 1962, 2, author1, author2);
        Book3 book4 = new Book3("What’s Over There?", 1998, 2, author2);
        BookStore3 bookStore1 = new BookStore3("Fantasy");
        BookStore3 bookStore2 = new BookStore3("Horror");
        BookStore3 bookStore3 = new BookStore3("Detective");
        bookStore1.addBook(book2);
        bookStore1.addBook(book3);
        bookStore2.addBook(book1);
        bookStore3.addBook(book4);
        library.addStore(bookStore1);
        library.addStore(bookStore2);
        library.addStore(bookStore3);
        BookReader3 bookReader1 = new BookReader3("Nana", "Sheldon", 1);
        bookReader1.addOwnedBook(book2);
        library.addReader(bookReader1);
        BookReader3 bookReader2 = new BookReader3("Neel", "Stark", 2);
        bookReader2.addOwnedBook(book1, book4);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("ver3.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(library);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("ver3.dat"));
            Library3 library2 = (Library3) in.readObject();
            System.out.println(library2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
