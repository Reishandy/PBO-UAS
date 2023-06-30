// Author: Muhammad Akbar Reishandy
import logic.*;
import logic.books.*;

import java.net.CacheResponse;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Bookshelf shelf = new Bookshelf();
    public static void main(String[] args) {
        // Init default book
        TextBook textBook = new TextBook(122, "Matematika dasar", "Ayam", "Kuxinf.inc", 2001, 190);
        shelf.addBook(textBook);

        // init receipt
        Receipt receipt = null;

        // Call menu
        while (true) {
            int op = printMenu();
            switch (op) {
                case 1 -> {
                    receipt = borrow();
                    System.out.println("-----------------");
                    System.out.println(receipt);
                }
                case 2 -> System.out.println("TODO");
            }
            if (op == 0) break;
        }
        // print receipt and save?
    }

    private static Receipt borrow() {
        // init variable
        String name, email, returnDate;
        int id;
        Book borrowBook = null;

        // input data for person
        System.out.println("--- Input personal ID ---");
        System.out.print("Name: ");
        name = sc.nextLine();
        System.out.print("Email: ");
        email = sc.nextLine();
        while (true) {
            System.out.print("Library member ID: ");
            try {
                id = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception ignored) {}
        }

        // display books or add book
        System.out.println("\n--- Input or search book ---");
        System.out.println("//Buku default namanya matematika dasar dgn id 122");
        int inId; // keep track of input search type
        String inputSearch;
        while (true) {
            // Search book
            inId = 0;
            System.out.print("Search with title or id: ");
            inputSearch = sc.nextLine();
            try {
                borrowBook = shelf.search(Integer.parseInt(inputSearch));
                inId = 1;
            } catch (Exception ignored) {}

            if (inId == 0) borrowBook = shelf.search(inputSearch);

            // if book does not exist add book
            if (borrowBook == null) {
                System.out.print("Book not found, would you like to add book(Y/n)? ");
                if (sc.nextLine().equalsIgnoreCase("y")) addBookToShelf();
                System.out.println("-----------------");
                continue;
            }

            // check if book correct and select them if yes
            System.out.println("-----------------");
            System.out.println("Is this the book?");
            System.out.println(borrowBook);
            System.out.print("(y/N) > ");
            if (sc.nextLine().equalsIgnoreCase("y")) break;
        }

        // create all necessary element
        Person borrower = new Person(name, email, id);
        Receipt receipt = new Receipt();
        receipt.setBorrower(borrower);
        receipt.addBook(borrowBook);

        // input return date
        System.out.println("-----------------");
        System.out.printf("Borrowing date: %s\n", receipt.getBorrowDate());
        do {
            System.out.print("Insert return date ('yyyy-MM-dd'): ");
        } while (!receipt.setReturnDate(sc.nextLine()));

        return receipt;
    }

    private static void addBookToShelf() {
        // init var
        String author, publisher, title;
        int year, pages, id;

        System.out.println("--- Input new book data ---");
        System.out.print("Title: ");
        title = sc.nextLine();
        System.out.print("Author: ");
        author = sc.nextLine();
        System.out.print("Publisher: ");
        publisher = sc.nextLine();
        while (true) {
            System.out.print("Year: ");
            try {
                year = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception ignored) {}
        }
        while (true) {
            System.out.print("Pages: ");
            try {
                pages = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception ignored) {}
        }
        while (true) {
            System.out.print("Book ID: ");
            try {
                id = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception ignored) {}
        }

        // Need to check what types of book is available on hardcoded
        System.out.print("Type of book: \n1. TextBook\n2. masih ada satu tipe saja\n> ");
        int input = 0;
        while (true) {
            try {
                input = Integer.parseInt(sc.nextLine());
            } catch (Exception ignored) {}
            if (input == 1) break;
        }

        //Add book
        // warning because ther is only one types of book rn
        switch (input) {
            case 1 -> {
                TextBook book = new TextBook(id, title, author, publisher, year, pages);
                shelf.addBook(book);
            }
            default -> throw new RuntimeException();
        }

    }

    private static int printMenu() {
        int input = 0;
        System.out.println("--- Borrowing book ---");
        System.out.print("0. Exit\n1. Start\n2. Return book\n> ");
        while (true) {
            try {
                input = Integer.parseInt(sc.nextLine());
            } catch (Exception ignored) {}
            if (input == 1 || input == 2) return input;
        }
    }
}
