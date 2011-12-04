package ie.cit.cloud.appdev.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import ie.cit.cloud.appdev.BookService;
import ie.cit.cloud.appdev.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * Main Library Book Application Controller
 * 
 * @author John O'Brien MSc in Cloud Computing
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = { "index", "" }, method = GET)
    public String index(Model model) {
    	model.addAttribute("top5books", bookService.getTop5Books());
    	model.addAttribute("checkedoutbooks", bookService.getCheckedOutBooks());
	return "index";
    }
    
    
    /** Login / Logout Handlers **/
	@RequestMapping(value="/login", method = GET)
	public String login(Model model) {
		model.addAttribute("top5books", bookService.getTop5Books());
		model.addAttribute("checkedoutbooks", bookService.getCheckedOutBooks());
		return "login";
	}
	@RequestMapping(value="/loginfailed", method = GET)
	
	public String loginerror(Model model) {
		model.addAttribute("top5books", bookService.getTop5Books());
		model.addAttribute("checkedoutbooks", bookService.getCheckedOutBooks());
 		model.addAttribute("error", "true");
		return "login";
	}
	@RequestMapping(value="/logout", method = GET)
	public String logout(Model model) {
		model.addAttribute("top5books", bookService.getTop5Books());
		return "index";
	}
    
	/** End of Login / Logout Handlers **/
	
	
	// Handle  requests coming from the user pages
	

    @RequestMapping(value = { "editbook", "" }, method = GET)
    public String editbook(@RequestParam Long id, Model model) {
    	model.addAttribute("editbook", bookService.getBook(id));
    	model.addAttribute("top5books", bookService.getTop5Books());
    	model.addAttribute("checkedoutbooks", bookService.getCheckedOutBooks());	
	return "admin/editbook";
    }
    

    @RequestMapping(value = { "createbook", "" }, method = GET)
    public String createbook(Model model) {
    	model.addAttribute("top5books", bookService.getTop5Books());
    	model.addAttribute("checkedoutbooks", bookService.getCheckedOutBooks());
    	
	return "admin/createbook";
    }
    
    @RequestMapping(value = { "createbook", "" }, method = POST)
    public String createnewbook(
    		@RequestParam String author,
    		@RequestParam String title,
    		@RequestParam Integer year,
    		@RequestParam String description,
    		@RequestParam String category,    		
    		Model model) {
    	
    	Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setYear(year);
        book.setDescription(description);
        book.setCategory(category);
        
        bookService.addNewBook(book);  	
	return "redirect:search.html?searchText=";
    }
  
	@RequestMapping(value = { "books", "" }, method = POST)
    public String updateBooks(
    		@RequestParam Long id, 
    		
    		@RequestParam String author,
    		@RequestParam String title,
    		@RequestParam Integer year,
    		@RequestParam String description,
    		@RequestParam String category,    		
    		Model model) {
    	
    	Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setYear(year);
        book.setDescription(description);
        book.setCategory(category);
    bookService.updateBookWithId(id, book);

	return "redirect:search.html?searchText=";
    }

    @RequestMapping(value = { "search", "" }, method = GET)
    public String searchBook(@RequestParam String searchText, Model model) {
    	model.addAttribute("searchbooks", bookService.searchBook(searchText));
    	model.addAttribute("top5books", bookService.getTop5Books());
    	model.addAttribute("checkedoutbooks", bookService.getCheckedOutBooks());
    	return "index";
    }



    @RequestMapping(value = "deletebook", method = GET)
    public String delete(@RequestParam Long id, @RequestParam String query,Model model) {
	bookService.remove(id);
	return "redirect:search.html?searchText="+query;
   }
    
    
    @RequestMapping(value = "reservebook", method = POST)
     public String reserveBook(@RequestParam Long id,@RequestParam String query, @RequestParam Boolean checkInOut, Model model) {
    Book book = new Book();
    book = bookService.getBook(id);
    book.setAvailable(checkInOut);
  	SecurityContext context = SecurityContextHolder.getContext();
	String name = context.getAuthentication().getName();
 	book.setCheckoutuser(name);
 	bookService.updateBookWithId(id, book);
 	return "redirect:search.html?searchText="+query;
    }

   
    

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public void emptyResult() {
	// no code needed
    }
}