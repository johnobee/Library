package ie.cit.cloud.appdev;

import ie.cit.cloud.appdev.model.Book;


import java.util.Collection;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;


//@SuppressWarnings("unused")
@Repository("HibernateBookRepository")
public class HibernateBookRepository implements BookRepository {

    @Autowired
    private SessionFactory sf;

    public Book get(Long id) {
	return (Book) session().get(Book.class, id);
    }

    public void create(Book book) {
    	book.setCheckoutuser(getCurrentUser());
	session().save(book);
    }

    public void update(Book book) {
	session().merge(book);
    }

    public void delete(Book book) {
	session().delete(book);
    }

    @SuppressWarnings("unchecked")
    public Collection<Book> findAll() {
	return session().createQuery("from Book").list();
    }

   @SuppressWarnings("unchecked")
	public Collection<Book> findTop5() {
	return session().createQuery("from Book order by id desc").setMaxResults(5).list();  
    }

    public Book findById(Long id) {
	return get(id);
    }
   
    @SuppressWarnings("unchecked")
	public Collection<Book>  findByDesc(String searchText) {
       return session().createQuery("from Book where title LIKE :searchForText OR author LIKE :searchForText").setParameter("searchForText","%"+searchText+"%").list();
    }
    
	@SuppressWarnings("unchecked")
	public Collection<Book> findCheckedOut() {
		  return session().createQuery("from Book where checkoutuser LIKE :loggedinuser").setParameter("loggedinuser",getCurrentUser()).list();
    }
    private Session session() {
	return sf.getCurrentSession();
    }
    private String getCurrentUser() {
    	return SecurityContextHolder.getContext().getAuthentication().getName();
        }


}