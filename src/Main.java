// Author: Muhammad Akbar Reishandy
// Author: Silvi Kusuma Wardhani G.
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
        // TODO: Sorry for the mess, this is just the prototype after all, this should be in the GUI

        // Init default book
        TextBook textBook = new TextBook(122, "Matematika dasar", "Ayam", "Kuxinf.inc", 2001, 190, "Matematika");
        shelf.addBook(textBook);

        // Call menu
        while (true) {
            int op = printMenu();
            switch (op) {
                case 1 -> {
                    Receipt receipt = borrow();
                    System.out.println("-----------------");
                    System.out.println(receipt);
                    receipts.add(receipt);
                }
                case 2 -> {
                    System.out.println("--- Select Receipt ---");
                    // Check if empty
                    if (receipts.size() == 0) {
                        System.out.println("There is no recorded borrowing yet...");
                        break;
                    }
                    // Display all receipts
                    for (int i = 0; i < receipts.size(); i++) {
                        System.out.printf("%d. %s%n", (i + 1), receipts.get(i));
                    }

                    int input = -2;
                    while (true) {
                        System.out.print("(input number eg. 1)%n-1 to exit > ");
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
                        } else if (resultReturn >= 1) {
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
                System.out.print("Book not found, would you like to add book(y/n)? ");
                if (sc.nextLine().equalsIgnoreCase("y")) addBookToShelf();
                System.out.println("-----------------");
                continue;
            }

            // check if book correct and select them if yes
            System.out.println("-----------------");
            System.out.println("Is this the book?");
            System.out.println(borrowBook);
            System.out.print("(y/n) > ");
            if (sc.nextLine().equalsIgnoreCase("y")) break;
        }

        // create all necessary elements
        Person borrower = new Person(name, email, id);
        Receipt receipt = new Receipt();
        receipt.setBorrower(borrower);
        receipt.addBook(borrowBook);

        // input return date
        System.out.println("-----------------");
        System.out.printf("Borrowing date: %s%n", receipt.getBorrowDate());
        while (true) {
            System.out.print("Insert return date ('yyyy-MM-dd'): ");
            String date = sc.nextLine();
            int condition = receipt.setReturnDate(date); // Also inserting the date into receipt
            if (condition == 0) break;
            switch (condition) {
                case 1 -> System.out.println("Format 'yyyy-MM-dd' ex. 2020-01-30");
                case 2 -> System.out.println("Not a real date");
                case 3 -> System.out.println("Maximum return date is 4 weeks");
                case -1 -> System.out.println("Date must be more than borrow date");

            }
        }

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

        // Need to check what types of book are available on hardcoded
        System.out.println("Type of book: \n1. TextBook\n2. Novel\n3. Dictionary");
        int input = 0;
        do {
            System.out.print("> ");
            try {
                input = Integer.parseInt(sc.nextLine());
            } catch (Exception ignored) {
            }
        } while (input != 1 && input != 2 && input != 3);

        // Add book based on type
        Book book = null;
        switch (input) {
            case 1 -> {
                System.out.print("Subject: ");
                String subject = sc.nextLine();
                book = new TextBook(id, title, author, publisher, year, pages, subject);
            }
            case 2 -> {
                System.out.print("Genre: ");
                String genre = sc.nextLine();
                book = new Novel(id, title, author, publisher, year, pages, genre);
            }
            case 3 -> {
                System.out.print("Language: ");
                String language = sc.nextLine();
                book = new Dictionary(id, title, author, publisher, year, pages, language);
            }
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
            System.out.println("Input must be number and is on the list");
        }
    }
}
