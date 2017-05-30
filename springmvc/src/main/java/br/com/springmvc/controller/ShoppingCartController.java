package br.com.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.springmvc.daos.ProductDAO;
import br.com.springmvc.model.BookType;
import br.com.springmvc.model.Product;
import br.com.springmvc.model.ShoppingCart;
import br.com.springmvc.model.ShoppingItem;


@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
	
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ShoppingCart shoppingCart;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String items(){
		return "shoppingCart/item";
	}
	
	@RequestMapping(method=RequestMethod.POST)	
	public ModelAndView add(Integer productId,@RequestParam BookType bookType){
		System.out.println("Id : "+productId +" - "+ bookType);
		ShoppingItem item = createItem(productId, bookType);
		shoppingCart.add(item);
		System.out.println(shoppingCart.getQuantity());
		return new ModelAndView("redirect:/produtos");
	}

	private ShoppingItem createItem(Integer productId, BookType bookType) {
		Product product = productDAO.find(productId);
		ShoppingItem item = new ShoppingItem(product,bookType);
		return item;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/{productId}")
	public String remove(@PathVariable("productId") Integer productId,BookType bookType){
		shoppingCart.remove(createItem(productId, bookType));
		return "redirect:/produtos";
	}
}
