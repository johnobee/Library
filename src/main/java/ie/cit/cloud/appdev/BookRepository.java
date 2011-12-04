package ie.cit.cloud.appdev;

import ie.cit.cloud.appdev.model.Book;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;

public interface BookRepository {

    /**
     * Returns existing book given by its ID
     * 
     * @param id
     *            book ID
     * @return book for given id, {@link EmptyResultDataAccessException} if no
     *         book was found
     */
    Book get(Long id);

    /**
     * Adds new book into repository
     * 
     * @param book
     */
    void create(Book book);

    /**
     * Updates existing book. book with the same ID as give book is updated
     * 
     * @param book
     *            new book values
     */
    void update(Book book);

    /**
     * Deletes book item from repository.
     * 
     * @param book
     */
    void delete(Book book);

    // ================ Find methods ================
    /**
     * Returns list of all books
     * 
     * @return all books
     */
    Collection<Book> findAll();
    
    /**
     * Returns list of TOP 5 books based on id DESC
     * 
     * 
     */
    
    Collection<Book> findTop5();
    
    
    /**
     * Returns list of all checked out books based on logged in security context
     * 
     * 
     */
    
    Collection<Book> findCheckedOut();

    /**
     * Returns book items given by its ID
     * 
     * @param id
     *            book ID
     * @return book for given id, null if book was not found
     */
    Book findById(Long id);
    
    /**
     * Returns book items given by its ID
     * Used for main search box on the 'index' view.
     * 
     * @param searchText
     *            
     * @return book for given searchText, null if book was not found
     */

    Collection<Book> findByDesc(String searchText);
}
