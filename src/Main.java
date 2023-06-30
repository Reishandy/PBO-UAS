// Author: Muhammad Akbar Reishandy
import logic.*;
import logic.books.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Bookshelf shelf = new Bookshelf();
    private static final ArrayList<Receipt> receipts = new ArrayList<>();
    public static void main(String[] args) {
        // TODO: rewrite the entire code with more modular method
        // TODO: Sorry for the mess, this is just the prototype after all, need refactoring

        // Init default book
        TextBook textBook = new TextBook(122, "Matematika dasar", "Ayam", "Kuxinf.inc", 2001, 190);
        shelf.addBook(textBook);

        // init receipt
        Receipt receipt;

        // Call menu
        while (true) {
            int op = printMenu();
            switch (op) {
                case 1 -> {
                    receipt = borrow();
                    System.out.println("-----------------");
                    System.out.println(receipt);
                    receipts.add(receipt);
                }
                case 2 -> {
                    System.out.println("--- Select Receipt ---");
                    // Display all receipt
                    for (int i = 0; i < receipts.size(); i ++) {
                        System.out.printf("%d. %s", (i + 1), receipts.get(i));
                    }

                    if (receipts.size() == 0) {
                        System.out.println("There is no recorded borrowing yet...");
                        break;
                    }

                    int input = -2;
                    while (true) {
                        System.out.print("\n(input number eg. 1)");
                        System.out.print("\n-1 to exit > ");
                        try {
                            input = Integer.parseInt(sc.nextLine());
                        } catch (Exception ignored) {}
                        if (input == -1) break;
                        if (input > 0 && input <= receipts.size()) break;
                    }
                    if (input == -1) break;

                    // check if already returned
                    Receipt currentReceipt = receipts.get(input - 1);
                    if (currentReceipt.isReturned()) {
                        System.out.println("Already returned...");
                        break;
                    }

                    System.out.println("-----------------");
                    System.out.println("Selected receipt: ");
                    System.out.println(currentReceipt);
                    System.out.println("-----------------");
                    while (true) {
                        System.out.print("Insert current date ('yyyy-MM-dd'): ");
                        int resultReturn = currentReceipt.returnBook(sc.nextLine());
                        if (resultReturn == -1) continue;
                        if (resultReturn == 0) {
                            System.out.println("Successfully returned");
                            break;
                        }
                        else if (resultReturn >= 1) {
                            System.out.printf("Returned but with a penalty of %d days (denda masih belum dihitung)%n", resultReturn);
                            break;
                        }
                    }
                }
            }

            if (op == 0) System.exit(1);
        }
    }

    private static Receipt borrow() {
        // init variable
        String name, email;
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
        // TODO: set a limit on how long to return it
        // TODO: add description on what's wrong with input
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
        System.out.print("Type of book: \n1. TextBook\n2. Novel\n3. Dictionary");
        int input = 0;
        do {
            System.out.print("\n> ");
            try {
                input = Integer.parseInt(sc.nextLine());
            } catch (Exception ignored) {
            }
        } while (input != 1 && input != 2 && input != 3);

        // Add book based on type
        Book book = null;
        switch (input) {
            case 1 -> book = new TextBook(id, title, author, publisher, year, pages);
            case 2 -> book = new Novel(id, title, author, publisher, year, pages);
            case 3 -> book = new Dictionary(id, title, author, publisher, year, pages);
        }
        shelf.addBook(book);
    }

    private static int printMenu() {
        int input = 0;
        System.out.println("--- Borrowing book ---");
        System.out.print("0. Exit\n1. Start\n2. Return book");
        while (true) {
            System.out.print("\n(input number eg. 1) > ");
            try {
                input = Integer.parseInt(sc.nextLine());
            } catch (Exception ignored) {}
            if (input == 1 || input == 2) return input;
        }
    }
}
