import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    private static final ArrayList<Book> books = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
          System.out.println("6. Exit [Sort Feature]");

            System.out.println("8. Show Books Sorted by Title");

            System.out.print("Choose: ");
            int choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> showBooks();
                case 3 -> searchBook();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> { System.out.println("Exiting..."); return; }
                case 8 -> showBooksSorted();

                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added!");
    }

    private static void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i+1) + ". " + books.get(i));
            }
        }
    }

    private static void searchBook() {
        System.out.print("Enter title keyword: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) System.out.println("Book not found.");
    }

    private static void borrowBook() {
        showBooks();
        System.out.print("Book number to borrow: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < books.size() && !books.get(index).isBorrowed()) {
            books.get(index).borrow();
            System.out.println("Book borrowed.");
        } else {
            System.out.println("Invalid or already borrowed.");
        }
    }

    private static void returnBook() {
        showBooks();
        System.out.print("Book number to return: ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < books.size() && books.get(index).isBorrowed()) {
            books.get(index).returnBook();
            System.out.println("Book returned.");
        } else {
            System.out.println("Invalid or not borrowed.");
        }
    }
    private static void showBooksSorted() {
    books.stream()
         .sorted((a,b)->a.getTitle().compareToIgnoreCase(b.getTitle()))
         .forEach(System.out::println);
}

}
