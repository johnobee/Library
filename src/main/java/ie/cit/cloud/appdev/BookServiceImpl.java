package ie.cit.cloud.appdev;

import ie.cit.cloud.appdev.model.Book;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    @Qualifier("HibernateBookRepository")
    BookRepository repo;

    public Collection<Book> getAllBooks() {
	return repo.findAll();
    }
    
    public Collection<Book> getTop5Books() {
    	return repo.findTop5();
        }

    public Collection<Book> getCheckedOutBooks() {
    	return repo.findCheckedOut();
        }
    
    public void addNewBook(Book book) {
	repo.create(book);
    }
    public void update(Book book) {
	repo.update(book);
    }

    public void remove(Long id) {
	repo.delete(repo.get(id));
    }

    public Book getBook(Long id) {
	return repo.findById(id);
    }

    public Collection<Book> searchBook(String searchText) {
    	return repo.findByDesc(searchText);
        }
    
    public void updateBookWithId(Long id, Book book) {
    	Book oldBook = repo.findById(id);
    	oldBook.setTitle(book.getTitle());
    	oldBook.setAuthor(book.getAuthor());
    	oldBook.setDescription(book.getDescription());
    	oldBook.setAvailable(book.isAvailable());
    	oldBook.setYear(book.getYear());
    	oldBook.setCheckoutuser(book.getCheckoutuser());
    	repo.update(oldBook);
        }
   
    
  
    
}