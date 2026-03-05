package dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.Book;

public class BookDaoImplementation implements BookDao {

	private static Scanner sc = new Scanner(System.in);

	private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("Book_Management_System");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

	@Override
	public void add_new_book(Book book) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
            em.getTransaction().begin();

            em.persist(book);

            em.getTransaction().commit();
            System.out.println("Book inserted successfully");

        } catch (Exception e) {
            System.out.println("Error in the inserting book: " + e.getMessage());

        } finally {
            em.close();
        }

	}

	@Override
	public List<Book> get_all_books() {
		String select = "Select book from Book book";

		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Query query = em.createQuery(select);
		
		List<Book> books=query.getResultList();
		em.close();
		
		return books;

	}

	@Override
	public boolean updatePrice_using_bookId_and_Author(int id, String author, double newPrice) {
		 if (newPrice < 0) {
	            System.out.println("Price cannot be negative");
	            return false;
	        }
		
		
		EntityManager em = getEntityManager();
		
		try {
			String update = "Update Book book set book.price=?1 where book.book_id=?2 AND book.author=?3";
			EntityTransaction et = em.getTransaction();
			et.begin();
			Query query = em.createQuery(update);
			query.setParameter(2, id);
			query.setParameter(3, author);
			query.setParameter(1, newPrice);
			int result = query.executeUpdate();
			et.commit();
			if (result > 0) {
                return true;
            } else {
                return false;
            }
		}
		catch(Exception e) {
			System.out.println("Update failed: "+e.getMessage());
			return  false;
		}
		
		finally {
			em.close();
		}
		
	}

	@Override
	public boolean delete_book_ById(int id) {
		
		EntityManager em = getEntityManager();
		
		try {
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			Book book=em.find(Book.class, id);
			
			if(book==null) {
				System.out.println("book not found.");
				return false;
			}
			em.remove(book);
			et.commit();
			return true;
			
		}
		catch(Exception e) {
			System.out.println("Deletion failed: " + e.getMessage());
            return false;

        } finally {
            em.close();
        }
	}

	@Override
	public Book find_book_ByTitle(String title) {
		

		EntityManager em = getEntityManager();
		try {
			String select = "Select book from Book book where book.title=:title";
			EntityTransaction et = em.getTransaction();
			et.begin();

			Query query = em.createQuery(select);
			query.setParameter("title", title);
			Book book =(Book) query.getSingleResult();
			
			return book;
		}
		catch(Exception e) {
			System.out.println("Book not found.");
			return null;
		}
		
		finally {
			em.close();
		}
		
		
	}

}
