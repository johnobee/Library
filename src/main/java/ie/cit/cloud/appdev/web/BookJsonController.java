package ie.cit.cloud.appdev.web;

import ie.cit.cloud.appdev.BookService;
import ie.cit.cloud.appdev.model.Book;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
@RequestMapping("api")
public class BookJsonController {
    @Autowired
    private BookService bookService;

    @RequestMapping("book")
    @ResponseBody
    public Collection<Book> index() {
	return bookService.getAllBooks();
    }

    @RequestMapping(value = "/book/{index}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Book getBookAt(@PathVariable("index") Long id) {
	return bookService.getBook(id);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody Book book, HttpServletRequest request,
	    HttpServletResponse response) {
    	bookService.addNewBook(book);
	response.addHeader("Location",
		getLocationForChildResource(request, book.getId()));
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBookAt(@RequestBody Book book,
	    @PathVariable("id") Long id) {
    	bookService.updateBookWithId(id, book);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookAt(@PathVariable("id") Long id) {
    	bookService.remove(id);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }

    private String getLocationForChildResource(HttpServletRequest request,
	    Object childIdentifier) {
	// get the current URL from the reguest
	final StringBuffer url = request.getRequestURL();
	// append the /xyz to the URL and make it a UriTemplate
	final UriTemplate template = new UriTemplate(url.append("/{childId}")
		.toString());
	return template.expand(childIdentifier).toASCIIString();
    }
}
