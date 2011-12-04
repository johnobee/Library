package ie.cit.cloud.appdev.model;



import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Domain object representing a Book item.
 * 
 * @author John O'Brien MSc in Cloud Computing */
@Entity
@Table(name = "BOOK")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Book {

  //  @Id
  //  private String id;
    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String title;
    
    @Basic
    @NotEmpty
    private  String author;

    @Basic
    private  String category;
    
    @Basic
    private  int year;

    @Basic
    @NotEmpty
    private  String description;

    @Basic
    private  boolean available;
    
    @Basic
    private  String checkoutuser;

    public Book() {
	// empty constructor to enable instantiation via reflection API
	this("");
    }

 //  public Book(String title, String author, String category, int year, 
	//	   String description, boolean available, String checkoutuser) {
//	this(id, title, author, category, year, description, true, checkoutuser);

  //  }

    //ID, Title, Author, Category, Year, Description,available, checkoutuser
    public Book(Long id, String title, String author, String category, 
    		int year, String description, Boolean available, String checkoutuser){
    this.setId(id);
	this.setTitle(title);
	this.setAuthor(author);
	this.setCategory(category);
	this.setYear(year);
	this.setDescription(description);
	this.setAvailable(available);
	this.setCheckoutuser(checkoutuser);
	
    }

	public Book(String string) {
		// BOOK Auto-generated constructor stub
	}

	public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public  String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public  String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCheckoutuser() {
		return checkoutuser;
	}

	public void setCheckoutuser(String checkoutuser) {
		this.checkoutuser = checkoutuser;
	}
}