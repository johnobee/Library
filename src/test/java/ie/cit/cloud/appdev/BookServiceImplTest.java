package ie.cit.cloud.appdev;

import ie.cit.cloud.appdev.model.Book;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BookServiceImplTest {

    private BookServiceImpl tested;
    private BookRepository repo;

    @Before
    public void setup() {
	repo = Mockito.mock(BookRepository.class);
	tested = new BookServiceImpl();
	tested.repo = repo;
    }

    @Test
    public void testAddNewBook() {
	Book book = new Book();
	tested.addNewBook(book);
	Mockito.verify(repo).create(book);
    }
    
    @Test
    public void testGetAllBooks() {
	tested.getAllBooks();
	Mockito.verify(repo).findAll();
    }
    
    @Test
    public void testGetBook() {
	Long id = (long) 1;
	tested.getBook(id);
	Mockito.verify(repo).findById(id);
    }
    
    @Test
    public void testGetCheckedOutBooks() {
	tested.getCheckedOutBooks();
	Mockito.verify(repo).findCheckedOut();
    }
    
    @Test
    public void testGetTop5Books() {
	tested.getTop5Books();
	Mockito.verify(repo).findTop5();
    }
    
    @Test
    public void testRemove() {
    Long id = (long) 1;
	tested.remove(id);
	Mockito.verify(repo).delete(repo.get(id));
    }
    
    @Test
    public void testSearch() {
    String searchText = "testString";
	tested.searchBook(searchText);
	Mockito.verify(repo).findByDesc(searchText);
    }
    
    @Test
    public void testUpdateBook() {
    Book book = new Book();
	tested.update(book);
	Mockito.verify(repo).update(book);
    }
  
   
   
}
