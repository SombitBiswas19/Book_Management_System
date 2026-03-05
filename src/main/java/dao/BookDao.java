package dao;

import com.Book;
import java.util.List;

public interface BookDao {
    // 1) Add a new Book record to the database.
    void add_new_book(Book book);


   //  2) Retrieve all Book records from the database.

    List<Book> get_all_books();

   //3) Update Book Price using Book Id and Book Author for verification.
   //   @return true if update was successful, false otherwise.
    boolean updatePrice_using_bookId_and_Author(int id, String author, double newPrice);

    /**
     * 4) Delete a Book record using its unique Book Id.
     * @return true if deletion was successful, false otherwise.
     */
    boolean delete_book_ById(int id);

    /**
     * 5) Search for a specific Book's details using its Title.
     */
    Book find_book_ByTitle(String title);
}
