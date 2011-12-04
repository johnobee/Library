package ie.cit.cloud.appdev;

import ie.cit.cloud.appdev.model.Book;

import java.util.Collection;

/**
 * Main operations for the book service layer
 * 
 */
public interface BookService {

    Collection<Book> getAllBooks();
    
    Collection<Book> getTop5Books();
    
    Collection<Book> getCheckedOutBooks();

    void addNewBook(Book book);

    Book getBook(Long id);

    void update(Book book);

    void remove(Long id);
    
    Collection<Book> searchBook(String searchText);

    void updateBookWithId(Long id, Book book);

}