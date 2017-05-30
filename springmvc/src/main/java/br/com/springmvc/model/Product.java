package br.com.springmvc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import br.com.springmvc.validation.ProductValidator;

@Entity 
public class Product{
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	binder.setValidator(new ProductValidator());
	}
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String title;
	
	@Lob
	@NotEmpty
	private String dscription;
	
	@Min(30)
	private int pages;
	
	@DateTimeFormat(iso=ISO.DATE)
	private Calendar releaseDate;

	@ElementCollection//(targetClass=Price.class, fetch=FetchType.LAZY)// oneToMany
	//@CollectionTable(name="product_prices",joinColumns=@JoinColumn(name="Product_id"))
	private List<Price> prices = new ArrayList<Price>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Price> getPrices() {
		return prices;
	}
	public void setPrices(List<Price> valores) {
		this.prices = valores;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDscription() {
		return dscription;
	}
	public void setDscription(String dscription) {
		this.dscription = dscription;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public Calendar getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + title + ", descricao="
				+ dscription + ", numeroPaginas=" + pages + ", valores="
				+ prices + "]";
	}
	
	public BigDecimal priceFor(BookType bookType) {
		return prices
				.stream()
				.filter(price -> price.getBookType().equals(bookType))
				.findFirst().get().getValue();
	}
}
