package com;

import java.util.List;
import java.util.Scanner;

import dao.BookDao;
import dao.BookDaoImplementation;

public class BookManagementMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        BookDao dao = new BookDaoImplementation();

        boolean running = true;

        while (running) {

            System.out.println("========= BOOK MANAGEMENT SYSTEM =========");
            System.out.println("1. Add New Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book Price");
            System.out.println("4. Delete Book");
            System.out.println("5. Search Book By Title");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
            case 1:

                System.out.println("Enter Book Title:");
                String title = sc.nextLine();

                System.out.println("Enter Author Name:");
                String author = sc.nextLine();

                System.out.println("Enter Book Price:");
                double price = sc.nextDouble();

                if (price < 0) {
                    System.out.println("Price cannot be negative.");
                    break;
                }

                Book newBook = new Book();
                newBook.setTitle(title);
                newBook.setAuthor(author);
                newBook.setPrice(price);

                dao.add_new_book(newBook);

                break;
            case 2:

                List<Book> books = dao.get_all_books();

                if (books.isEmpty()) {
                    System.out.println("No books found.");
                } else {
                    for (Book book : books) {
                        System.out.println(book);
                    }
                }

                break;
            case 3:

                System.out.println("Enter Book ID:");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter Author Name:");
                String updateAuthor = sc.nextLine();

                System.out.println("Enter New Price:");
                double newPrice = sc.nextDouble();

                boolean updated = dao.updatePrice_using_bookId_and_Author(id, updateAuthor, newPrice);

                if (updated)
                    System.out.println("Price Updated Successfully");
                else
                    System.out.println("Update Failed");

                break;

            case 4:

                System.out.println("Enter Book ID to delete:");
                int deleteId = sc.nextInt();

                boolean deleted = dao.delete_book_ById(deleteId);

                if (deleted)
                    System.out.println("Book Deleted Successfully");
                else
                    System.out.println("Book Not Found");

                break;

            case 5:

                System.out.println("Enter Book Title:");
                String searchTitle = sc.nextLine();

                Book book = dao.find_book_ByTitle(searchTitle);

                if (book != null)
                    System.out.println(book);
                else
                    System.out.println("Book not found");

                break;

            case 6:

                running = false;
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("Invalid Choice. Try Again.");
            }
        }

        sc.close();

	}

}
